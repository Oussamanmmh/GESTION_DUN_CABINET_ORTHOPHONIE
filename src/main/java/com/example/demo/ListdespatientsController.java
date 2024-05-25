package com.example.demo;

import com.example.demo.models.*;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.collections.FXCollections;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

//Ce controller permet de gerer la liste des patients (ajouter , supprimer , afficher)
//Il permet aussi de switcher entre les tables des adultes et des enfants
//Il permet aussi d'ajouter un patient (adulte ou enfant)
//Il permet aussi de supprimer un patient
public class ListdespatientsController implements Initializable {
    private Scene scene;
    private Stage stage;

    private final String choix[] = {"Adult", "Enfant"}; //les choix possibles pour le choixBox

    @FXML
    Label linkHomePage;
    @FXML
    Image iconHomePage;
    @FXML
    private ChoiceBox<String> MychoiceBox; // le choixBox pour choisir entre adulte et enfant
    @FXML
    JFXButton supprimerpatient; //le bouton pour supprimer un patient
    @FXML
    TableView<Patient> tablePatient; //la table pour afficher les patients
    @FXML
    TableColumn<Patient, String> nom; //la colonne pour afficher le nom
    @FXML
    TableColumn<Patient, String> prenom; //la colonne pour afficher le prenom
    @FXML
    TableColumn<Patient, String> adress;//la colonne pour afficher l'adress
    @FXML
    TextField rechercheField ;
    @FXML
    TableColumn<Patient, String> tel; //la colonne pour afficher le numero de telephone dans le cas d'un adulte
    @FXML
    TableColumn<Patient, String> profession;//la colonne pour afficher la profession dans le cas d'un adulte
    @FXML
    TableColumn<Patient, String> diplome;//la colonne pour afficher le diplome dans le cas d'un adulte
    @FXML
    TableColumn<Patient, Date> datedenaissance;//la colonne pour afficher la date de naissance
    @FXML
    TableColumn<Patient, String> lieudenaissance;//la colonne pour afficher le lieu de naissance
    @FXML
    JFXButton ajouterpatient;//le bouton pour ajouter un patient
    @FXML
    JFXButton voirDetailsPatients ;


    public void retourPageAc(javafx.scene.input.MouseEvent mouseEvent) //methode pour retourner a la page d'accueil
    {
        System.out.println("clicked clicked");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("erreur");
        }

        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setTitle("Page d'accueil");
        stage.setScene(scene);
        stage.show();
    }


    ObservableList<Patient> list = FXCollections.observableArrayList( //la liste des patients adult par defaut
            new Adult("oussama", "nemamcha", "alger", new Date(), "alger", "licence", "etudiant", "093892"),
            new Adult("mouhamed", "nemamcha", "alger", new Date(), "alger", "licence", "etudiant", "093892"),
            new Adult("oussama", "nemamcha", "alger", new Date(), "alger", "licence", "etudiant", "093892"),
            new Adult("oussama", "nemamcha", "alger", new Date(), "alger", "licence", "etudiant", "093892"),
            new Adult("oussama", "nemamcha", "alger", new Date(), "alger", "licence", "etudiant", "093892"),
            new Adult("oussama", "nemamcha", "alger", new Date(), "alger", "licence", "etudiant", "093892"),
            new Adult("oussama", "nemamcha", "alger", new Date(), "alger", "licence", "etudiant", "093892"),
            new Adult("oussama", "nemamcha", "alger", new Date(), "alger", "licence", "etudiant", "093892")
    );


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //methode pour initialiser le controller

        MychoiceBox.getItems().addAll(choix); //ajouter les choix possibles au choixBox(adulte , enfant)

        this.MychoiceBox.getSelectionModel().select(0);//le choix est par defaut Adult

        //initialiser les colonnes de la table dans ce cas le patient est un adulte
        nom.setCellValueFactory(new PropertyValueFactory<Patient, String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Patient, String>("prenom"));
        adress.setCellValueFactory(new PropertyValueFactory<Patient, String>("adress"));
        tel.setCellValueFactory(new PropertyValueFactory<Patient, String>("numTel"));
        profession.setCellValueFactory(new PropertyValueFactory<Patient, String>("profession"));
        diplome.setCellValueFactory(new PropertyValueFactory<Patient, String>("diplome"));
        datedenaissance.setCellValueFactory(new PropertyValueFactory<Patient, Date>("dateNaissance"));
        lieudenaissance.setCellValueFactory(new PropertyValueFactory<Patient, String>("lieuNaissance"));


        tablePatient.setItems(list); //ajouter la liste des patients a la table pour les afficher

        //ajouter un listener pour le choixBox pour switcher entre les tables (adulte , enfant)
        MychoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("new value = " + newVal);
            //switcher les tables
            switchTable(newVal);
        });
        rechercheField.setOnKeyReleased(e -> {
            if (rechercheField.getText().isEmpty()) {
                tablePatient.setItems(list);
                return;
            }
            ObservableList<Patient> listRecherche = FXCollections.observableArrayList();
            for (Patient patient : list) {
                if (patient.getNom().toLowerCase().contains(rechercheField.getText().toLowerCase()) || patient.getPrenom().toLowerCase().contains(rechercheField.getText().toLowerCase())) {
                    listRecherche.add(patient);
                }
            }
            tablePatient.setItems(listRecherche);
        });
        //voir details des patients
        voirDetailsPatients.setOnAction(e -> {
            if (tablePatient.getSelectionModel().getSelectedIndex() != -1) {
                Patient patient = tablePatient.getSelectionModel().getSelectedItem();
                voirDetailsPatient(patient);
            }
        });
    }

    private void voirDetailsPatient(Patient patient) {
        int numDossier = patient.getNumeroDossier();
        DossierPatient dossierCourant ;
        for(DossierPatient dossierPatient : HelloApplication.orthophoniste.getDossierPatientList())
        {
            if(dossierPatient.getNumeroDossier()==numDossier)
            {
                dossierCourant = dossierPatient ;
            }
        }


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(".fxml"));
        //to be continue


    }

    //pour ajouter un patient
    public void ajouterPatient(ActionEvent event) { //methode pour ajouter un patient
        //initialiser le dialogue pour ajouter un patient
        FXMLLoader fxmlLoader;
        //si le choix est un adulte on affiche le dialogue pour ajouter un adulte
        if (MychoiceBox.getSelectionModel().getSelectedIndex() == 0) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DialogueAjouterPatient.fxml"));
        } else {
            //si le choix est un enfant on affiche le dialogue pour ajouter un enfant
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DialogueAjouterEnfant.fxml"));
        }

        try {
            DialogPane dialogePatient = fxmlLoader.load();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(dialogePatient);
            dialog.setTitle("Ajouter un patient");
            dialog.showAndWait().ifPresent(response -> {
                //si l'utilisateur appuie sur le bouton apply on ajoute le patient a la liste
                if (response == ButtonType.APPLY) {
                    //recuperer le controller du dialogue
                    if (MychoiceBox.getSelectionModel().getSelectedIndex() == 0){
                    DialogueAjouterPatientController dlg = fxmlLoader.getController();
                        //appliquer l'action du bouton apply
                        dlg.applyButtonAction();
                        //ajouter le patient a la liste
                        list.add(dlg.getPatient());
                        //afficher la liste des patients
                        tablePatient.setItems(list);
                    }
                    else {
                        DialogueAjouterEnfantController dlg = fxmlLoader.getController();
                        //appliquer l'action du bouton apply
                        dlg.applyButtonAction();
                        //ajouter le patient a la liste
                        list.add(dlg.getPatient());
                        //afficher la liste des patients
                        tablePatient.setItems(list);
                    }

                } else if (response == ButtonType.CANCEL) {
                    //si l'utilisateur appuie sur le bouton cancel on affiche un message

                    System.out.println("Cancel button pressed");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("erreur");
        }

        //showing the dialoge for the user


    }


    public void supprimerPatient(ActionEvent event) { //methode pour supprimer un patient
        //si la liste n'est pas vide et si un patient est selectionne on affiche un message de confirmation
        if (!list.isEmpty() && tablePatient.getSelectionModel().getSelectedIndex() != -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Appuyez sur OK pour confirmer, sinon appuyez sur Annuler.");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    //si l'utilisateur appuie sur le bouton OK on supprime le patient de la liste
                    System.out.println("OK button pressed");
                    //recuperer l'index du patient selectionne
                    int index = tablePatient.getSelectionModel().getSelectedIndex();
                    System.out.println("index = " + index);
                    //supprimer le patient de la liste

                    list.remove(index);
                    //afficher la liste des patients
                    tablePatient.setItems(list);
                } else if (response == ButtonType.CANCEL) {
                    System.out.println("Cancel button pressed");
                }
            });


        } else {
            System.out.println("la liste est vide");
        }
    }

    //methode pour switche les tables (enfant , adult)
    public void switchTable(String newval) {
        //si le choix est un adulte on affiche la table des adultes
        if (newval.equals("Adult")) {
            //initialiser les colonnes de la table pour les adultes
            diplome.setText("Diplome");
            profession.setText("Profession");
            tel.setText("Contact");
            //initialiser les colonnes de la table pour les adultes
            profession.setCellValueFactory(new PropertyValueFactory<Patient, String>("profession"));
            diplome.setCellValueFactory(new PropertyValueFactory<Patient, String>("diplome"));
            tel.setCellValueFactory(new PropertyValueFactory<Patient, String>("numTel"));
            list = FXCollections.observableArrayList(
                    new Adult("oussama", "nemamcha", "alger", new Date(), "alger", "licence", "etudiant", "093892"),
                    new Adult("mouhamed", "nemamcha", "alger", new Date(), "alger", "licence", "etudiant", "093892"),
                    new Adult("oussama", "nemamcha", "alger", new Date(), "alger", "licence", "etudiant", "093892"),
                    new Adult("oussama", "nemamcha", "alger", new Date(), "alger", "licence", "etudiant", "093892"),
                    new Adult("oussama", "nemamcha", "alger", new Date(), "alger", "licence", "etudiant", "093892"),
                    new Adult("oussama", "nemamcha", "alger", new Date(), "alger", "licence", "etudiant", "093892"),
                    new Adult("oussama", "nemamcha", "alger", new Date(), "alger", "licence", "etudiant", "093892"),
                    new Adult("oussama", "nemamcha", "alger", new Date(), "alger", "licence", "etudiant", "093892")
            );
            //afficher la liste des patients
            tablePatient.setItems(list);
        } else {
            //si le choix est un enfant on affiche la table des enfants
            //on change les titres des colonnes
            diplome.setText("Etude");
            profession.setText("Contact de mere");
            tel.setText("Contact de pere");

            //initialiser les colonnes de la table pour les enfants
            profession.setCellValueFactory(new PropertyValueFactory<Patient, String>("numTelmere"));
            diplome.setCellValueFactory(new PropertyValueFactory<Patient, String>("classdetude"));
            tel.setCellValueFactory(new PropertyValueFactory<Patient, String>("numTelpere"));
            list = FXCollections.observableArrayList(
                    new Enfant("oussama", "nemamcha", "alger", new Date(), "guelma", "licence", "32321", "093892")

            );
            //afficher la liste des patients
            tablePatient.setItems(list);
        }
    }


}


