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

import java.security.PublicKey;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HelloApplication extends Application {
    public static ApplicationDesktop applicationDesktop ;
    public static Orthophoniste orthophoniste ;
    public static AgendaIntegre agendaIntegre ;
    public static AnamneseEnfant anamneseEnfant ;
    public static AnamneseAdult anamneseAdult ;
    public static EpreuveClinique epreuveClinique ;



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AnamnesTest.fxml"));
        applicationDesktop = new ApplicationDesktop();
        orthophoniste = new Orthophoniste("oussama" , "nemamcha" , "admin" , "alger","093892", "123") ;
        agendaIntegre = new AgendaIntegre();
        applicationDesktop.setOrthophoniste(orthophoniste);
        //On va creer des question pour test ;
        //avec categhor

        QuestionAnamEnfant q1 = new QuestionAnamEnfant( "question sur l enfant" , CathegoriesEnf.ANTYCIDENT_FAMILIALE) ;
        QuestionAnamEnfant q2 = new QuestionAnamEnfant( "question sur l enfant" , CathegoriesEnf.ANTYCIDENT_FAMILIALE) ;
        QuestionAnamEnfant q3 = new QuestionAnamEnfant( "question sur l enfant" , CathegoriesEnf.COMPORTENENT) ;
        QuestionAnamEnfant q4 = new QuestionAnamEnfant( "question sur l enfant" , CathegoriesEnf.COMPORTENENT) ;

        anamneseEnfant = new AnamneseEnfant(Arrays.asList(q1,q2,q3,q4)) ;


        //on va creer des question pour tester pour adult

        QuestionAnamAdult q5 = new QuestionAnamAdult( "question sur l adulte" , CathegorieAdult.HISTOIRE_MALADIE) ;
        QuestionAnamAdult q6 = new QuestionAnamAdult( "question sur l adulte" , CathegorieAdult.HISTOIRE_MALADIE) ;
        QuestionAnamAdult q7 = new QuestionAnamAdult( "question sur l adulte" , CathegorieAdult.SUIVI_MEDICAL) ;
        anamneseAdult = new AnamneseAdult(Arrays.asList(q5,q6,q7)) ;
        for(QuestionAnamAdult q : anamneseAdult.getQuestions()){
            System.out.println(q.getEnonce());
        }

        epreuveClinique = new EpreuveClinique();

        Qcm qcm1 = new Qcm("question 1", Arrays.asList("reponse1", "reponse2", "reponse3"), Arrays.asList("reponse1", "reponse2"));
        Qcm qcm2 = new Qcm("question 2", Arrays.asList("reponse1", "reponse2", "reponse3"), Arrays.asList("reponse1", "reponse2"));
        Qcm qcm3 = new Qcm("question 3", Arrays.asList("reponse1", "reponse2", "reponse3"), Arrays.asList("reponse1", "reponse2"));
        Qcu qcu1 = new Qcu("question 4", Arrays.asList("reponse1", "reponse2", "reponse3"), "reponse1");
        Qcu qcu2 = new Qcu("question 5", Arrays.asList("reponse1", "reponse2", "reponse3"), "reponse1");
        Qcu qcu3 = new Qcu("question 6", Arrays.asList("reponse1", "reponse2", "reponse3"), "reponse1");
        QesrepLibre qesrepLibre = new QesrepLibre("question 7", "reponse libre");
        QesrepLibre qesrepLibre1 = new QesrepLibre("question 8", "reponse libre");
        QesrepLibre qesrepLibre2 = new QesrepLibre("question 9", "reponse libre");
        QesrepLibre qesrepLibre3 = new QesrepLibre("question 10", "reponse libre");
        Exercice exo1 = new Exercice("exo1", "consign1" , "nomM1");
        Exercice exo2 = new Exercice("exo2", "consign2" , "nomM2");
        Exercice exo3 = new Exercice("exo3", "consign3", "nomM3");
        Set<Qcm> qcmSet = new HashSet<>(Arrays.asList(qcm1, qcm2, qcm3));
        Set<Qcu> qcuSet = new HashSet<>(Arrays.asList(qcu1, qcu2, qcu3));
        Set<QesrepLibre> qesrepLibreSet = new HashSet<>(Arrays.asList(qesrepLibre, qesrepLibre1, qesrepLibre2, qesrepLibre3));
        TestQuestionArepLibre testQuestionArepLibre = new TestQuestionArepLibre("test Q", qesrepLibreSet);
        TestExercice testExercice = new TestExercice("test exo", Arrays.asList(exo1, exo2, exo3));
        TestQCM testQcm = new TestQCM("test qcm", qcmSet);
        TestQCU testQcu = new TestQCU("test qcu", qcuSet);

        epreuveClinique.ajouterTest(testQcm);
        epreuveClinique.ajouterTest(testQcu);
        epreuveClinique.ajouterTest(testQuestionArepLibre);
        epreuveClinique.ajouterTest(testExercice);
        System.out.println("le nom de tst est"+testQuestionArepLibre.getNom());

        Scene scene = new Scene(fxmlLoader.load());













        stage.setScene(scene);
        stage.setTitle("Se connecter");
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}