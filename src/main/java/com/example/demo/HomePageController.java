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

import java.io.IOException;

public class HomePageController {

    private Stage stage ;
    private Parent root ;
    private Scene scene ;
    @FXML
    private AnchorPane quitterButton ;
    @FXML
    private AnchorPane linkPatients ;
    public void quiterButton (javafx.scene.input.MouseEvent mouseEvent) {

    }


    public void gotoListPatients(javafx.scene.input.MouseEvent mouseEvent) {
        System.out.println("helllo woled");
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Listdespatients.fxml")) ;
        try
        {
            scene = new Scene(loader.load());

        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("error");
        }
        stage =  (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setTitle("List patients");
        stage.setScene(scene);
        stage.show();
    }
}
