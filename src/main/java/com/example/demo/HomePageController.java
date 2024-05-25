package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.events.MouseEvent;
import com.jfoenix.controls.JFXButton ;

import java.io.IOException;
import javafx.scene.control.Label;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    private Stage stage ;
    private Parent root ;
    private Scene scene ;
    @FXML
    Label timeLabel ;
    @FXML
    Label bienvenue ;
    @FXML
     JFXButton quitterButton ;
    @FXML
     JFXButton linkPatients ;
    @FXML
    JFXButton gotoAgenda ;
    @FXML
    JFXButton paramButton ;
    @FXML
    JFXButton compteButton ;
    public void quiterButton (ActionEvent event) {
        Dialog dialog = new Dialog();
        dialog.setTitle("Quitter");
        dialog.setContentText("Voulez vous vraiment quitter ?");
        ButtonType oui = new ButtonType("Oui");
        ButtonType non = new ButtonType("Non");
        dialog.getDialogPane().getButtonTypes().addAll(oui,non);
        dialog.showAndWait().ifPresent(response -> {
            if (response == oui){
                System.exit(0);
            }

        });

    }


    public void gotoListPatients(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Listdespatients.fxml"));
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Il ya une erreur");
        }
        JFXButton button = (JFXButton) event.getSource();
        stage = (Stage) button.getScene().getWindow();
        stage.setTitle("List patients");
        stage.setScene(scene);
        stage.show();
    }
    public void gotoAgenda(ActionEvent event) {
        System.out.println("go to agenda");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Agenda.fxml"));

        try{
          scene  = new Scene(fxmlLoader.load());
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("Il ya une erreur");
        }

        JFXButton button = (JFXButton) event.getSource();
        stage = (Stage) button.getScene().getWindow();
        stage.setTitle("Agenda");
        stage.setScene(scene);
        stage.show();

    }
    public void gotoParam(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Settings.fxml"));
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Il ya une erreur");
        }
        JFXButton button = (JFXButton) event.getSource();
        stage = (Stage) button.getScene().getWindow();
        stage.setTitle("Parametres");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //l'heure
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();

            timeLabel.setText(currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }),
                new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();

        DiagnosticController diagnosticController = new DiagnosticController();
        compteButton.setOnAction(e -> {
            diagnosticController.diplayDialogue();
        });
        bienvenue.setText("Bienvenue Dr "+HelloApplication.applicationDesktop.getOrthophoniste().getNom());

    }
}
