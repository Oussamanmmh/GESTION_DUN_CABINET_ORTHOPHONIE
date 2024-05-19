package com.example.demo;
import com.example.demo.models.AgendaIntegre;
import com.example.demo.models.ApplicationDesktop;
import com.example.demo.models.Orthophoniste;
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

import java.time.LocalDate;
import java.time.LocalTime;

public class HelloApplication extends Application {
    public static ApplicationDesktop applicationDesktop ;
    public static Orthophoniste orthophoniste ;
    public static AgendaIntegre agendaIntegre ;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Agenda.fxml"));
        applicationDesktop = new ApplicationDesktop();
        orthophoniste = new Orthophoniste("oussama" , "nemamcha" , "admin" , "alger","093892", "123") ;
        agendaIntegre = new AgendaIntegre();
        applicationDesktop.setOrthophoniste(orthophoniste);
        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.setTitle("Se connecter");
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}