package com.example.demo;

import com.example.demo.models.ApplicationDesktop;
import com.example.demo.models.Orthophoniste;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static ApplicationDesktop applicationDesktop ;
    public static Orthophoniste orthophoniste ;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Seconnecter.fxml"));
        applicationDesktop = new ApplicationDesktop();
        orthophoniste = new Orthophoniste("oussama" , "nemamcha" , "admin" , "alger","093892", "123") ;
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