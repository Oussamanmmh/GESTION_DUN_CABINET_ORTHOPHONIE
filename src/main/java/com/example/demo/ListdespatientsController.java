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

public class ListdespatientsController implements Initializable {
    private Scene scene ;
    private Stage stage ;

    private final String  choix[] = {"Adult" ,  "Enfant"};

    @FXML
    private   Label linkHomePage ;
    @FXML
    private Image iconHomePage ;
  @FXML
   private ChoiceBox<String> MychoiceBox ;
  @FXML
  JFXButton supprimerpatient ;
  @FXML
    TableView<Adult> adulttable ;
   @FXML
    TableColumn<Adult , String> nom ;
    @FXML
    TableColumn<Adult , String> prenom ;
    @FXML
    TableColumn<Adult , String> adress ;

    @FXML
    TableColumn<Adult , String> tel ;
    @FXML
    TableColumn<Adult , String> profession ;
    @FXML
    TableColumn<Adult , String> diplome ;
    @FXML
    TableColumn<Adult , Date> datedenaissance ;
    @FXML
    TableColumn<Adult , String> lieudenaissance ;
   @FXML
   JFXButton ajouterpatient ;




    public void retourPageAc(javafx.scene.input.MouseEvent mouseEvent)
    {
        System.out.println("clicked clicked");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
        try {
            scene= new Scene(fxmlLoader.load());
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("erreur");
        }

        stage =  (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setTitle("Page d'accueil");
        stage.setScene(scene);
        stage.show();
    }


    ObservableList<Adult> list = FXCollections.observableArrayList(
           new Adult("oussama" , "nemamcha" , "alger" , new Date() , "alger" , "licence" , "etudiant" , "093892") ,
                new Adult("mouhamed" , "nemamcha" , "alger" , new Date() , "alger" , "licence" , "etudiant" , "093892") ,
                new Adult("oussama" , "nemamcha" , "alger" , new Date() , "alger" , "licence" , "etudiant" , "093892") ,
                new Adult("oussama" , "nemamcha" , "alger" , new Date() , "alger" , "licence" , "etudiant" , "093892") ,
                new Adult("oussama" , "nemamcha" , "alger" , new Date() , "alger" , "licence" , "etudiant" , "093892") ,
                new Adult("oussama" , "nemamcha" , "alger" , new Date() , "alger" , "licence" , "etudiant" , "093892") ,
                new Adult("oussama" , "nemamcha" , "alger" , new Date() , "alger" , "licence" , "etudiant" , "093892") ,
                new Adult("oussama" , "nemamcha" , "alger" , new Date() , "alger" , "licence" , "etudiant" , "093892")
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        MychoiceBox.getItems().addAll(choix);
        this.MychoiceBox.getSelectionModel().select(0);
       nom.setCellValueFactory(new PropertyValueFactory<Adult, String>("nom"));
      prenom.setCellValueFactory(new PropertyValueFactory<Adult, String>("prenom"));
        adress.setCellValueFactory(new PropertyValueFactory<Adult, String>("adress"));
      tel.setCellValueFactory(new PropertyValueFactory<Adult, String>("numTel"));
        profession.setCellValueFactory(new PropertyValueFactory<Adult, String>("profession"));
        diplome.setCellValueFactory(new PropertyValueFactory<Adult, String>("diplome"));
        datedenaissance.setCellValueFactory(new PropertyValueFactory<Adult, Date>("dateNaissance"));
     lieudenaissance.setCellValueFactory(new PropertyValueFactory<Adult, String>("lieuNaissance"));


        adulttable.setItems(list);
    }

    //pour ajouter un patient
    public void ajouterPatient(ActionEvent event) {
        System.out.println("clicked clicked");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DialogueAjouterPatient.fxml"));

        try {
            DialogPane dialogePatient  = fxmlLoader.load();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(dialogePatient);
            dialog.setTitle("Ajouter un patient");
            dialog.showAndWait().ifPresent(response -> {
                if (response == ButtonType.APPLY) {

                    DialogueAjouterPatientController dlg  =  fxmlLoader.getController();
                    dlg.applyButtonAction();
                    list.add((Adult) dlg.getPatient()) ;
                    adulttable.setItems(list) ;
                } else if (response == ButtonType.CANCEL) {

                    System.out.println("Cancel button pressed");
                }
            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("erreur");
        }

        //showing the dialoge for the user


    }


    public void supprimerPatient (ActionEvent event){
        if (!list.isEmpty() && adulttable.getSelectionModel().getSelectedIndex() != -1 ){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Voulez-vous vraiment supprimer ce patient ?");
            alert.setContentText("Appuyez sur OK pour confirmer, sinon appuyez sur Annuler.");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    System.out.println("OK button pressed");
                    int index = adulttable.getSelectionModel().getSelectedIndex();
                    System.out.println("index = " + index);
                    list.remove(index);
                    adulttable.setItems(list);
                } else if (response == ButtonType.CANCEL) {
                    System.out.println("Cancel button pressed");
                }
            });


        }
        else {
            System.out.println("la liste est vide");
        }
    }



}


