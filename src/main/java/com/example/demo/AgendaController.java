package com.example.demo;

import com.example.demo.models.Consultation;
import com.example.demo.models.DeroulementSeance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;

import static net.fortuna.ical4j.util.Numbers.parseInt;

public class AgendaController implements Initializable {

    @FXML
    DatePicker datePicker;

    @FXML
    ComboBox hours;
    @FXML
    ComboBox minutes;
    @FXML
    ComboBox time;
    @FXML
    ChoiceBox MychoiceBox;
    @FXML
    VBox Myvbox;

    TextField nomField = new TextField();
    TextField prenomField = new TextField();
    TextField ageField = new TextField();
    TextField thematiqueField = new TextField();
    @FXML
    ChoiceBox choiceBox = new ChoiceBox();
    TextField numDeDossier = new TextField();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nomField.setPromptText("Le nom de patient");
        prenomField.setPromptText("Le prenom de patient");
        ageField.setPromptText("L'age de patient");
        for (int i = 1; i <= 12; i++) {
            hours.getItems().add(i);
        }

        // Populate minutes
        for (int i = 0; i < 60; i++) {
            minutes.getItems().add(i);
        }

        // Populate AM/PM
        time.getItems().addAll("AM", "PM");

        // Set default values
        hours.setValue(1);
        minutes.setValue(0);
        time.setValue("AM");

        MychoiceBox.getItems().addAll("Consultation", "Suivez", "Atelier");
        MychoiceBox.setValue(null);
        ;

        choiceBox.getItems().addAll(DeroulementSeance.ENLIGNE, DeroulementSeance.ENPRESENTIEL);
        thematiqueField.setPromptText("Thematique");


        MychoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            System.out.println("Trait : " + newValue);
            traitChoiceBox(newValue.toString());

        });


    }

    public void traitChoiceBox(String newValue) {

        switch (newValue) {
            case "Consultation":
                Myvbox.getChildren().clear();
                Myvbox.setMargin(nomField, new Insets(0, 0, 20, 0));
                Myvbox.setMargin(prenomField, new Insets(0, 0, 20, 0));
                Myvbox.setMargin(ageField, new Insets(0, 0, 20, 0));
                Myvbox.getChildren().addAll(nomField, prenomField, ageField);

                break;
            case "Suivez":
                System.out.println("Suivez");
                numDeDossier.setPromptText("Le numero de dossier de patient");
                Myvbox.getChildren().clear();
                Myvbox.setMargin(numDeDossier, new Insets(0, 0, 20, 0));
                // Myvbox.getChildren().add(numDeDossier);
                Myvbox.getChildren().addAll(numDeDossier, choiceBox);
                break;
            case "Atelier":
                Myvbox.getChildren().clear();
                Myvbox.setMargin(thematiqueField, new Insets(0, 0, 20, 0));
                Myvbox.getChildren().add(thematiqueField);
                break;
        }
    }

    public void onSubmit(ActionEvent event){
        System.out.println("Submit button clicked");
        if (MychoiceBox.getValue() == null) {
            System.out.println("Veuillez choisir un trait");

            return;
        } else {
            String date = datePicker.getValue().toString();
            Time temp = new Time(Integer.parseInt(hours.getValue().toString()), Integer.parseInt(minutes.getValue().toString()), 0);
            switch (MychoiceBox.getValue().toString()) {
                case "Consultation":
                    System.out.println("Consultation");
                    System.out.println("Nom : " + nomField.getText());
                    System.out.println("Prenom : " + prenomField.getText());
                    System.out.println("Age : " + ageField.getText());
                    System.out.println("la date" + datePicker.getValue());
                    System.out.println("l'heure" + hours.getValue() + ":" + minutes.getValue() + " " + time.getValue());
                    Consultation consultation = new Consultation(parseInt(ageField.getText()), nomField.getText(), prenomField.getText(), date, temp);
                    try {
                        HelloApplication.agendaIntegre.addRendezVous(consultation, "Adult");
                    }
                    catch(IllegalArgumentException e ){

                        System.out.println("error"+ e.getMessage());

                    }

                    break;
                case "Suivez":
                    System.out.println("Suivez");
                    System.out.println("Numero de dossier : " + numDeDossier.getText());
                    System.out.println("Deroulement de seance : " + choiceBox.getValue());
                    break;
                case "Atelier":
                    System.out.println("Atelier");
                    System.out.println("Thematique : " + thematiqueField.getText());
                    break;
            }
        }
    }
}
