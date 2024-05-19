package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;
import com.jfoenix.controls.JFXButton ;

import java.io.IOException;

public class HomePageController {

    private Stage stage ;
    private Parent root ;
    private Scene scene ;
    @FXML
     AnchorPane quitterButton ;
    @FXML
     JFXButton linkPatients ;
    @FXML
    JFXButton gotoAgenda ;
    public void quiterButton (javafx.scene.input.MouseEvent mouseEvent) {

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
}
