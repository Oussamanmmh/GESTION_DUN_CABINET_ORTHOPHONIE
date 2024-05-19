package com.example.demo;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ConsultationController implements Initializable {
    private TextField nomField = new TextField() ;
    private TextField prenomField = new TextField();
    private TextField ageField= new TextField() ;

    private String nom ;
    private String prenom ;
    private int age ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        nomField.setPromptText("Nom") ;
        prenomField.setPromptText("Prenom") ;
        ageField.setPromptText("Age") ;

        nomField.onActionProperty().addListener((v, oldValue, newValue) -> {
            System.out.println("Nom : " + newValue);
            this.nom = newValue.toString() ;
        });
        prenomField.onActionProperty().addListener((v, oldValue, newValue) -> {
            System.out.println("Prenom : " + newValue);
            this.prenom = newValue.toString() ;

        });
        ageField.onActionProperty().addListener((v, oldValue, newValue) -> {
            System.out.println("Age : " + newValue);
            this.age = Integer.parseInt(newValue.toString()) ;
        });

    }

    public String getNom() {
        return this.nom ;
    }
    public String getPrenom() {
        return this.prenom ;
    }
    public int getAge() {
        return this.age ;
    }
}
