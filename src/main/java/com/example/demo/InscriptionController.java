package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InscriptionController implements Initializable {

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
    public void SeconnecterButton(ActionEvent event)  throws IOException {
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
            //verifier la format de emailField est une email
            if (!emailField.getText().matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))
            {
                erreurLabel.setText("Email invalide");
                return;
            }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("^[a-zA-Z]*$")) ? change : null));
        prenomField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("^[a-zA-Z]*$")) ? change : null));
        telField.setTextFormatter(new TextFormatter<>(change ->

                (change.getControlNewText().matches("^[0-9]*$")) ? change : null));
        //quant l'utilisateur taper lemail
        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))
            {
                emailField.setStyle("-fx-border-color: red");
            }
            else
            {
                emailField.setStyle("-fx-border-color: green");
            }
        });
    }

}

