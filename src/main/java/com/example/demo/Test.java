package com.example.demo;

import com.example.demo.models.DossierPatient;
import com.example.demo.models.Enfant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Test.class.getResource("qcm.fxml"));

        Scene scene = new Scene(fxmlLoader.load());


        stage.setScene(scene);
        stage.setTitle("Dossier Patient");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
