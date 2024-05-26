package com.example.demo;

import com.example.demo.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public class DossierPatientController {

    @FXML
    Label NDossierLabel;
    @FXML
    Label nomLabel;
    @FXML
    Label prenomLabel;
    @FXML
    Button billanButton;
    @FXML
    Button ficheButton;
    @FXML
    Button dossierButton;
    @FXML
    Button retourButton;
    @FXML
    Label descriptionLabel;
    @FXML
    private TableView<RendezVous> rendezVousTable;
    @FXML
    private TableColumn<RendezVous, String> typeColumn;
    @FXML
    private TableColumn<RendezVous, Date> dateColumn;
    @FXML
    private TableColumn<RendezVous, Time> heureColumn;
    @FXML
    Label dateDeNaissanceLabel;
    @FXML
    Label lieuDeNaissanceLabel;
    @FXML
    Label adresseLabel;
    @FXML
    Label extraInfoLabel;
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

    public void displayDossier(DossierPatient dossier) {
        this.currentDossier = dossier;
        Patient patient = dossier.getPatient();

        NDossierLabel.setText("" + dossier.getNumeroDossier());
        nomLabel.setText(patient.getNom());
        prenomLabel.setText(patient.getPrenom());
        dateDeNaissanceLabel.setText("" + patient.getDateNaissance());
        lieuDeNaissanceLabel.setText(patient.getLieuNaissance());
        adresseLabel.setText(patient.getAdress());
        descriptionLabel.setText("Description: " + patient.getDescriptionTherapie());

        if (patient instanceof Enfant) {
            Enfant enfant = (Enfant) patient;

            diplome_Label.setText("Classe d'étude: ");
            diplomeLabel.setText(enfant.getClassdetude());

            proffessionLabel.setText(enfant.getNumTelmere());
            proffession_Label.setText("n° mère: ");

            num_Label.setText("n° père: ");
            numLabel.setText(enfant.getNumTelpere());

        } else if (patient instanceof Adult) {
            Adult adult = (Adult) patient;

            diplome_Label.setText("Diplome: ");
            diplomeLabel.setText(adult.getDiplome());

            proffessionLabel.setText(adult.getProfession());
            proffession_Label.setText("Profession: ");

            num_Label.setText("n° tel:");
            numLabel.setText(adult.getNumTel());
        } else {
            extraInfoLabel.setText("");
        }

        typeColumn.setCellValueFactory(new PropertyValueFactory<RendezVous, String>("type"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<RendezVous, Date>("date"));
        heureColumn.setCellValueFactory(new PropertyValueFactory<RendezVous, Time>("heure"));

        List<RendezVous> rendezVousList = dossier.getRendezVous();

        ObservableList<RendezVous> observableList = FXCollections.observableArrayList(rendezVousList);
        rendezVousTable.setItems(observableList);
    }
    public void initialize(DossierPatient currentDossier) {
        this.currentDossier = currentDossier;
        System.out.println("initialize"+currentDossier.getNumeroDossier());
        displayDossier(currentDossier);
        dossierButton.setOnAction(event -> showModifierDossierDialog());
    }
    @FXML
    private void showModifierDossierDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierDossier.fxml"));
            DialogPane dialogPane = loader.load();

            ModifierDossierController controller = loader.getController();
            controller.setDossier(currentDossier); // Passer le dossier actuel au contrôleur du dialogue

            Dialog dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
//            dialog.initModality(Modality.APPLICATION_MODAL);
//            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.showAndWait().ifPresent(response -> {
                controller.handleSave();
                //si l'utilisateur appuie sur le bouton apply on ajoute le patient a la liste
                if (response == ButtonType.APPLY) {
                    System.out.println("handle save");
                    System.out.println("handle save");


                } else if (response == ButtonType.CANCEL) {
                    //si l'utilisateur appuie sur le bouton cancel on affiche un message

                    System.out.println("Cancel button pressed");
                }
            });
           //  controller.handleSave();
            // Après la fermeture du dialogue, mettre à jour les informations affichées
            displayDossier(currentDossier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setCurrentDossier(DossierPatient currentDossier) {
        this.currentDossier = currentDossier;
    }
}
