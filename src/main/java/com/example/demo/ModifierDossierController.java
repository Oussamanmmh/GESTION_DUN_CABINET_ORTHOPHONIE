package com.example.demo;

import com.example.demo.models.Adult;
import com.example.demo.models.DossierPatient;
import com.example.demo.models.Enfant;
import com.example.demo.models.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ModifierDossierController {

    @FXML
    private DialogPane dialogPane;
    @FXML
    private TextField numDossierField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private DatePicker dateNaissancePicker;
    @FXML
    private TextField lieuNaissanceField;
    @FXML
    private TextField adresseField;

    @FXML
    private TextField diplomeField;
    @FXML
    private TextField professionField;
    @FXML
    private TextField numTelField;


    @FXML
    Label diplomeLabel;
    @FXML
    Label diplome_Label;
    @FXML
    Label proffessionLabel;
    @FXML
    Label proffession_Label;
    @FXML
    Label numLabel;
    @FXML
    Label num_Label;

    private DossierPatient currentDossier;

    public void setDossier(DossierPatient dossier) {
        this.currentDossier = dossier;

        Patient patient = dossier.getPatient();

       // numDossierField.setText("" + dossier.getNumeroDossier());
        nomField.setText(patient.getNom());
        prenomField.setText(patient.getPrenom());

        // Conversion de la date de naissance en LocalDate
        if (patient.getDateNaissance() != null) {
            LocalDate dateNaissance = patient.getDateNaissance();
            dateNaissancePicker.setValue(dateNaissance);
        }

        lieuNaissanceField.setText(patient.getLieuNaissance());
        adresseField.setText(patient.getAdress());

        if (patient instanceof Enfant) {
            Enfant enfant = (Enfant) patient;
            diplomeField.setText(enfant.getClassdetude());
            professionField.setText(enfant.getNumTelmere());
            numTelField.setText(enfant.getNumTelpere());

            diplome_Label.setText("Classe d'etude:");
            proffession_Label.setText("N° mére:");
            num_Label.setText("N° pére:");

        } else if (patient instanceof Adult) {
            Adult adult = (Adult) patient;
            diplomeField.setText(adult.getDiplome());
            professionField.setText(adult.getProfession());
            numTelField.setText(adult.getNumTel());

            diplomeField.setVisible(true);
            professionField.setVisible(true);
            numTelField.setVisible(true);


        }
    }


    public void handleSave() {
        Patient patient = currentDossier.getPatient();

        patient.setNom(nomField.getText());
        patient.setPrenom(prenomField.getText());
        System.out.println(prenomField.getText());

        // Conversion de la LocalDate en Date
        LocalDate dateNaissance = dateNaissancePicker.getValue();
        if (dateNaissance != null) {
            LocalDate dateNaissanceDate = dateNaissancePicker.getValue(); ;
            patient.setDateNaissance(dateNaissanceDate);
        }

        patient.setLieuNaissance(lieuNaissanceField.getText());
        patient.setAdress(adresseField.getText());

        if (patient instanceof Enfant) {
            Enfant enfant = (Enfant) patient;
            enfant.setClassdetude(diplomeField.getText());
            enfant.setNumTelmere(professionField.getText());
            enfant.setNumTelpere(numDossierField.getText());
        } else if (patient instanceof Adult) {
            Adult adult = (Adult) patient;
            adult.setDiplome(diplomeField.getText());
            adult.setProfession(professionField.getText());
            adult.setNumTel(numTelField.getText());
        }


    }
}


