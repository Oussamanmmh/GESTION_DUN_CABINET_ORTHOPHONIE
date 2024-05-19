package com.example.demo;

import com.example.demo.models.Adult;
import com.example.demo.models.Enfant;
import com.example.demo.models.Patient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class DialogueAjouterEnfantController implements Initializable {
    @FXML
    TextField nomField;
    @FXML
    TextField prenomField;
    @FXML
    DatePicker dateField;
    @FXML
    TextField lieuField;
    @FXML
    TextField etudeField;
    @FXML
    TextField numField;
    @FXML
    TextField contactField;
    @FXML
    TextField adressField;
    @FXML
    DialogPane dialogpan;

  private   Patient patient;

    public void applyButtonAction() {
        System.out.println("Apply button clicked");

        if (nomField.getText().isEmpty() || prenomField.getText().isEmpty() ||etudeField.getText().isEmpty() ||dateField.getValue() == null || lieuField.getText().isEmpty() || numField.getText().isEmpty() ||  contactField.getText().isEmpty() || adressField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veulliez remplire tous les champs");
            alert.showAndWait();
            return;
        }

        System.out.println("nom" + nomField.getText() + "data" + dateField.getValue());
        patient = new Enfant(nomField.getText(), prenomField.getText(), adressField.getText(), Date.from(dateField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), lieuField.getText(),  etudeField.getText(), numField.getText(),contactField.getText());


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("dialogue ajouter patient controller");


    }

    public Patient getPatient() {
        return patient;
    }

}
