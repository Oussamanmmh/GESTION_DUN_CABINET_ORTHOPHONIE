package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {
    @FXML
    private TextField adressField;

    @FXML
    private TextField contactField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField mdpField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;
    @FXML
    Button sauvgarder ;

    Stage stage ;
    Scene scene ;


    public void initialize() {

        nomField.setText(HelloApplication.orthophoniste.getNom());
        prenomField.setText(HelloApplication.orthophoniste.getPrenom());
        emailField.setText(HelloApplication.orthophoniste.getEmail());
        adressField.setText(HelloApplication.orthophoniste.getAdr());
        contactField.setText(HelloApplication.orthophoniste.getNumeroTel());


        //regular expression
        nomField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                nomField.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        prenomField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                prenomField.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        contactField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                contactField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
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
    public void sauvgarderButton()
    {
        HelloApplication.orthophoniste.setNom(nomField.getText());
        HelloApplication.orthophoniste.setPrenom(prenomField.getText());
        HelloApplication.orthophoniste.setEmail(emailField.getText());
        HelloApplication.orthophoniste.setAdr(adressField.getText());
        HelloApplication.orthophoniste.setNumeroTel(contactField.getText());
        HelloApplication.orthophoniste.setMdp(mdpField.getText());
    }

    public void retourButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
        try{
            Scene scene = new Scene(fxmlLoader.load());
            Button button = (Button) event.getSource();
            stage = (Stage) button.getScene().getWindow();
            stage.setTitle("Page d'acceuil");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load FXML file");
        }

    }

}
