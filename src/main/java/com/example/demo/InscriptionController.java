package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.io.IOException;

public class InscriptionController {

    private Stage stage;
    private Scene scene;

    private Parent root;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;
    @FXML
    private TextField adressField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField telField;
    @FXML
    private TextField mdpField;
    @FXML
    private Label erreurLabel;
    public void SeconnecterButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlload = new FXMLLoader(HelloApplication.class.getResource("Seconnecter.fxml"));

        Scene scene = new Scene(fxmlload.load());
        Button button = (Button) event.getSource();


        stage = (Stage) button.getScene().getWindow();
        stage.setTitle("Se connecter");
        stage.setScene(scene);

        stage.show();
    }

    public void inscrirButton(ActionEvent event) throws IOException {
        System.out.println("button clicked");
        if(nomField.getText().isEmpty() || prenomField.getText().isEmpty() || adressField.getText().isEmpty() || emailField.getText().isEmpty() || telField.getText().isEmpty() || mdpField.getText().isEmpty())
        {
            erreurLabel.setText("Veuillez remplir tous les champs");
        }
        else
        {
            if (HelloApplication.applicationDesktop.isExiste(emailField.getText()))
            {

                invalidInscription();
            }
            else {
                // creer instance de orthophonist
                System.out.println("nouveau orthophoniste");
            }

        }

    }



public void invalidInscription(){
        erreurLabel.setText("Ce compte deja existe ");
}
}

