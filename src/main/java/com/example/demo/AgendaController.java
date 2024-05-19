package com.example.demo;

import com.example.demo.models.DeroulementSeance;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AgendaController implements Initializable {

    @FXML
    ComboBox hours ;
    @FXML
    ComboBox minutes ;
    @FXML
    ComboBox time ;
    @FXML
    ChoiceBox MychoiceBox ;
    @FXML
    VBox Myvbox ;

    TextField nomField = new TextField() ;
    TextField prenomField = new TextField() ;
    TextField ageField = new TextField() ;

    @FXML
     ChoiceBox choiceBox = new ChoiceBox() ;
    TextField numDeDossier = new TextField() ;



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

        MychoiceBox.getItems().addAll("Consultation" , "Suivez" , "Atelier" ) ;
        MychoiceBox.setValue("Consultation") ;

        choiceBox.getItems().addAll(DeroulementSeance.ENLIGNE ,DeroulementSeance.ENPRESENTIEL);

        MychoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            System.out.println("Trait : " + newValue);
            traitChoiceBox(newValue.toString());

        });


    }

    public void traitChoiceBox(String newValue)  {

        switch (newValue){
            case "Consultation" :
                Myvbox.getChildren().clear();
                Myvbox.setMargin(nomField , new Insets(0, 0, 20, 0));
                Myvbox.setMargin(prenomField , new Insets(0, 0, 20, 0));
                Myvbox.setMargin(ageField , new Insets(0, 0, 20, 0));
               Myvbox.getChildren().addAll(nomField , prenomField , ageField);

                break ;
            case "Suivez" :
                System.out.println("Suivez");
                numDeDossier.setPromptText("Le numero de dossier de patient");
                Myvbox.getChildren().clear();
                Myvbox.setMargin(numDeDossier , new Insets(0, 0, 20, 0));
               // Myvbox.getChildren().add(numDeDossier);
                Myvbox.getChildren().addAll( numDeDossier ,choiceBox );
                break ;
            case "Atelier" :
                System.out.println("Atelier");
                break ;
        }




    }
}
