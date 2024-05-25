package com.example.demo;

import com.example.demo.models.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HelloApplication extends Application {
    public static ApplicationDesktop applicationDesktop;
    public static Orthophoniste orthophoniste;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DossierPatient.fxml"));
        applicationDesktop = new ApplicationDesktop();
        orthophoniste = new Orthophoniste("oussama", "nemamcha", "admin", "alger", "093892", "123");
        applicationDesktop.setOrthophoniste(orthophoniste);
        Scene scene = new Scene(fxmlLoader.load());

        // Exemple avec un patient Enfant
        String dateDeNaissanceEnfantStr = "2010-05-15";
        Date dateDeNaissanceEnfant = null;
        try {
            dateDeNaissanceEnfant = new SimpleDateFormat("yyyy-MM-dd").parse(dateDeNaissanceEnfantStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Enfant enfant = new Enfant(
                "Dupont",
                "Jean",
                "123 Rue de Paris",
                dateDeNaissanceEnfant,
                "Paris",
                "CM2",
                "0123456789",
                "0987654321"
        );

        DossierPatient dossierEnfant = new DossierPatient(enfant);
        dossierEnfant.getPatient().setDescriptionTherapie("Exemple de description de thérapie pour le patient Jean Dupont.");
        List<RendezVous> rendezVousEnfantList = new ArrayList<>();

        try {
            rendezVousEnfantList.add(new Consultation(25, "Dupont", "Jean", new SimpleDateFormat("yyyy-MM-dd").parse("2023-05-01"), Time.valueOf("10:00:00")));
            rendezVousEnfantList.add(new Consultation(17, "Martin", "Marie", new SimpleDateFormat("yyyy-MM-dd").parse("2023-05-15"), Time.valueOf("11:00:00")));
            rendezVousEnfantList.add(new Suivi(DeroulementSeance.DIAGNOSTIC, 1001, new SimpleDateFormat("yyyy-MM-dd").parse("2023-06-01"), Time.valueOf("14:00:00")));
            rendezVousEnfantList.add(new Suivi(DeroulementSeance.TRAITEMENT, 1002, new SimpleDateFormat("yyyy-MM-dd").parse("2023-06-15"), Time.valueOf("15:00:00")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dossierEnfant.getRendezVous().addAll(rendezVousEnfantList);

        // Exemple avec un patient Adulte
        String dateDeNaissanceAdulteStr = "1985-04-20";
        Date dateDeNaissanceAdulte = null;
        try {
            dateDeNaissanceAdulte = new SimpleDateFormat("yyyy-MM-dd").parse(dateDeNaissanceAdulteStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Adult adulte = new Adult(
                "Martin",
                "Claire",
                "456 Avenue des Champs",
                dateDeNaissanceAdulte,
                "Lyon",
                "Master",
                "Ingénieur",
                "0987654321"
        );

        DossierPatient dossierAdulte = new DossierPatient(adulte);
        dossierAdulte.getPatient().setDescriptionTherapie("Exemple de description de thérapie pour le patient Claire Martin.");
        List<RendezVous> rendezVousAdulteList = new ArrayList<>();

        try {
            rendezVousAdulteList.add(new Consultation(35, "Martin", "Claire", new SimpleDateFormat("yyyy-MM-dd").parse("2023-07-01"), Time.valueOf("09:00:00")));
            rendezVousAdulteList.add(new Consultation(36, "Martin", "Claire", new SimpleDateFormat("yyyy-MM-dd").parse("2023-07-15"), Time.valueOf("10:00:00")));
            rendezVousAdulteList.add(new Suivi(DeroulementSeance.TRAITEMENT, 1003, new SimpleDateFormat("yyyy-MM-dd").parse("2023-08-01"), Time.valueOf("11:00:00")));
            rendezVousAdulteList.add(new Suivi(DeroulementSeance.TRAITEMENT, 1004, new SimpleDateFormat("yyyy-MM-dd").parse("2023-08-15"), Time.valueOf("14:00:00")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dossierAdulte.getRendezVous().addAll(rendezVousAdulteList);

        // Afficher les informations pour un dossier de patient, vous pouvez choisir dossierEnfant ou dossierAdulte
        DossierPatientController controller = fxmlLoader.getController();
        controller.displayDossier(dossierAdulte); // Remplacer par dossierEnfant pour afficher les infos de l'enfant

        stage.setScene(scene);
        stage.setTitle("Dossier Patient");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
