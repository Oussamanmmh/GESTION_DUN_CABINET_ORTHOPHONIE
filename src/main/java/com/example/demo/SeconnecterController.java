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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;

public class SeconnecterController implements Initializable {

    private Stage stage ;
    private Scene scene ;
    private Parent root ;

    @FXML
   private TextField emailField ;

    @FXML
    private TextField mdpField ;

    @FXML
   private Label erreurLabel ;



    public  void CreeUnCompteButton(ActionEvent event ) throws IOException {
        System.out.println("buuton clicked");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Inscription.fxml"));

        scene = new Scene(fxmlLoader.load()) ;
        Button button = (Button) event.getSource();

        stage = (Stage) button.getScene().getWindow();
        stage.setTitle("Inscription");
        stage.setScene(scene);
        stage.show();



    }
    public void  seConnecterButton(ActionEvent event){
        String email = emailField.getText() ;
        String mdps = mdpField.getText() ;
        System.out.println("mdps = "+ mdps + "email ="+email);
        if (email.isEmpty() ||mdps.isEmpty()){
            erreurLabel.setText("Veulliez remplire les deux champs");
            return;
        }

        if(HelloApplication.applicationDesktop.isExiste(email))
        {
            if (HelloApplication.applicationDesktop.getOrthophoniste().getMdp().equals(mdps))
            {
                System.out.println("connnection valide ");
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
                try {
                    scene = new Scene(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Il ya une erreur");
                }

                Button button = (Button) event.getSource();
                stage = (Stage) button.getScene().getWindow();
                stage.setTitle("Page d'acceuil");
                stage.setScene(scene);
                stage.show();
            }
            else {
                mdpField.clear();
                erreurMdpEmail("Mot de pass");
            }

        }
        else {
         mdpField.clear();
        erreurMdpEmail("Email");
        }


    }

 public void erreurMdpEmail(String mot){
        erreurLabel.setText(mot +" est incorrect");
 }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
