package com.example.demo;
import com.example.demo.models.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;

import java.security.PublicKey;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class HelloApplication extends Application {
    public static ApplicationDesktop applicationDesktop ;
    public static Orthophoniste orthophoniste ;
    public static AgendaIntegre agendaIntegre ;

    public static List<Anamnese>  anamnese = new ArrayList<>();

    public static EpreuveClinique epreuveClinique ;



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Agenda.fxml"));
        applicationDesktop = new ApplicationDesktop();
        orthophoniste = new Orthophoniste("oussama" , "nemamcha" , "admin" , "alger","093892", "123") ;
        agendaIntegre = new AgendaIntegre();
        applicationDesktop.setOrthophoniste(orthophoniste);

        QuestionAnamEnfant q1 = new QuestionAnamEnfant("Qui vit avec l'enfant à la maison ?",CathegoriesEnf.STRUCTURE_FAMILIALE);
        QuestionAnamEnfant q2 = new QuestionAnamEnfant("Quelle est la relation de l'enfant avec les autres membres de la famille ?",CathegoriesEnf.DYNAMIQUE_FAMILIALE);
        QuestionAnamEnfant q3 = new QuestionAnamEnfant("L'enfant a-t-il déjà été hospitalisé ?",CathegoriesEnf.ANTYCIDENT_FAMILIALE);
        QuestionAnamEnfant q4 = new QuestionAnamEnfant("L'enfant est-il né à terme ?",CathegoriesEnf.CONDITION_NATALE);
        AnamneseEnfant anamneseEnfant = new AnamneseEnfant(Arrays.asList(q1,q2,q3,q4));

        QuestionAnamAdult q5 = new QuestionAnamAdult("Quelles maladies avez-vous eues dans le passé ?",CathegorieAdult.HISTOIRE_MALADIE);
        QuestionAnamAdult q6 = new QuestionAnamAdult("Avez-vous un médecin généraliste régulier ?",CathegorieAdult.SUIVI_MEDICAL);
        AnamneseAdult anamneseAdult = new AnamneseAdult(Arrays.asList(q5,q6));
        anamnese.addAll(Arrays.asList(anamneseEnfant,anamneseAdult));

        Qcm qcm1 = new Qcm("Quelle est la capitale de la France ?", FXCollections.observableArrayList("Paris", "Londres", "Berlin", "Madrid"), Arrays.asList("Paris"));
        Qcm qcm2 = new Qcm("Quelle est la capitale de l'Espagne ?", FXCollections.observableArrayList("Paris", "Londres", "Berlin", "Madrid"), Arrays.asList("Madrid"));
        Qcu qcu1 = new Qcu("Quelle est la capitale de l'Allemagne ?", FXCollections.observableArrayList("Paris", "Londres", "Berlin", "Madrid"), "Berlin");
        Qcu qcu2 = new Qcu("Quelle est la capitale de l'Italie ?", FXCollections.observableArrayList("Paris", "Londres", "Rome", "Madrid"), "Rome");
        QesrepLibre qesrepLibre1 = new QesrepLibre("Quelle est la capitale de la Belgique ?", "Bruxelles");
        QesrepLibre qesrepLibre2 = new QesrepLibre("Quelle est la capitale du Maroc ?", "Rabat");
        Exercice exo1 = new Exercice("Exercice 01 ", "Faites une phrase avec les mots suivants : chien, chat, souris", "Papier, stylo");
        Exercice exo2 = new Exercice("Exercice 02", "Faites une phrase avec les mots suivants : voiture, vélo, moto", "Papier, stylo");
        Test testExercice = new TestExercice("Test de français", Arrays.asList(exo1, exo2));
        Test testQuestionnaire = new TestQuestionnaire("Test de géographie", Arrays.asList(qcm1, qcm2, qcu1, qcu2, qesrepLibre1, qesrepLibre2));

     epreuveClinique = new EpreuveClinique();
        epreuveClinique.ajouterTest(testExercice);
        epreuveClinique.ajouterTest(testQuestionnaire);




        Scene scene = new Scene(fxmlLoader.load());













        stage.setScene(scene);
        stage.setTitle("Se connecter");
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}