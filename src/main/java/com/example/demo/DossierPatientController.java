package com.example.demo;

import com.example.demo.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Time;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private TableView<Rdv> rendezVousTable;
    @FXML
    private TableColumn<Rdv, String> typeColumn;
    @FXML
    private TableColumn<Rdv, Date> dateColumn;
    @FXML
    private TableColumn<Rdv, Time> heureColumn;
    @FXML
    Label dateDeNaissaceLabel;
    @FXML
    Label lieuDeNaissanceLabel;
    @FXML
    Label adresseLabel;


    public void displayDossier(DossierPatient dossier) {


        NDossierLabel.setText( ""+dossier.getNumeroDossier());
        nomLabel.setText( dossier.getPatient().getNom());
        prenomLabel.setText(dossier.getPatient().getPrenom());
        descriptionLabel.setText("Description: " + dossier.getPatient().getDescriptionTherapie());

        typeColumn.setCellValueFactory(new PropertyValueFactory<Rdv, String>("type"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Rdv, Date>("date"));
        heureColumn.setCellValueFactory(new PropertyValueFactory<Rdv, Time>("heure"));

        List<Rdv> rendezVousList = null;
        try {
            rendezVousList = List.of(
                    new Rdv("Consultation", new SimpleDateFormat("yyyy-MM-dd").parse("2023-05-01"), Time.valueOf("10:00:00")),
                    new Rdv("Suivi", new SimpleDateFormat("yyyy-MM-dd").parse("2023-05-15"), Time.valueOf("14:00:00")),
                    new Rdv("Bilan", new SimpleDateFormat("yyyy-MM-dd").parse("2023-06-01"), Time.valueOf("09:00:00"))
            );
            System.out.println(rendezVousList);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

// ObservableList pour le TableView
        ObservableList<Rdv> observableList = FXCollections.observableArrayList();
        List<RendezVous> Listrdv = dossier.getRendezVous();
        for (RendezVous rdv : Listrdv) {
            Rdv  rendezvous = new Rdv(rdv.getType(), rdv.getDate(), rdv.getHeure());


            observableList.add(rendezvous);
            System.out.println(rdv.getDate());
        }
        rendezVousTable.setItems(observableList);

    }


}
