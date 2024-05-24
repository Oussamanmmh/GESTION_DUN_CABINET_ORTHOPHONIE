package com.example.demo;
import com.example.demo.models.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HelloApplication extends Application {
    public static ApplicationDesktop applicationDesktop ;
    public static Orthophoniste orthophoniste ;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("qcm.fxml"));
        applicationDesktop = new ApplicationDesktop();
        orthophoniste = new Orthophoniste("oussama" , "nemamcha" , "admin" , "alger","093892", "123") ;
        applicationDesktop.setOrthophoniste(orthophoniste);
        Scene scene = new Scene(fxmlLoader.load());
/*
        String dateDeNaissanceStr = "2010-05-15";
        Date dateDeNaissance = null;
        try {
            dateDeNaissance = new SimpleDateFormat("yyyy-MM-dd").parse(dateDeNaissanceStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Création d'une instance de Enfant
        Enfant enfant = new Enfant(
                "Dupont",
                "Jean",
                "123 Rue de Paris",
                dateDeNaissance,
                "Paris",
                "CM2",
                "0123456789",
                "0987654321"
        );

        DossierPatient dossier = new DossierPatient(enfant);
        dossier.getPatient().setDescriptionTherapie("kjk kjdk dkjfs kjdsjf ksdjk lqsl oaizd czbab qoqzo dksqsqkj kqsjfq");
        List<RendezVous> rendezVousList = new ArrayList<>();

        // Ajouter des consultations
        rendezVousList.add(new Consultation(25, "Dupont", "Jean", new Date(), Time.valueOf("10:00:00")));
        rendezVousList.add(new Consultation(17, "Martin", "Marie", new Date(), Time.valueOf("11:00:00")));

        // Ajouter des suivis
        rendezVousList.add(new Suivi(DeroulementSeance.DIAGNOSTIC, 1001, new Date(), Time.valueOf("14:00:00")));
        rendezVousList.add(new Suivi(DeroulementSeance.TRAITEMENT, 1002, new Date(), Time.valueOf("15:00:00")));

        dossier.getRendezVous().addAll(rendezVousList);
        DossierPatientController controller = fxmlLoader.getController();
        controller.displayDossier(dossier);
*/
        stage.setScene(scene);
        stage.setTitle("Dossier Patient");
        stage.show();



    }

    public static void main(String[] args) {
        launch();
    }
}
