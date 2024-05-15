package com.example.demo;

import com.example.demo.models.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    TableColumn<Adult , String> datedenaissance ;
    @FXML
    TableColumn<Adult , String> lieudenaissance ;





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
            new Adult("oussama" , "nemamcha" , "admin" , new Date() , "alger","ingénieur" , "ingénieur" , "123456789"),
            new Adult("oussama" , "nemamcha" , "admin" , new Date() , "alger","ingénieur" , "ingénieur" , "123456789"),
            new Adult("oussama" , "nemamcha" , "admin" , new Date() , "alger","ingénieur" , "ingénieur" , "123456789"),
            new Adult("oussama" , "nemamcha" , "admin" , new Date() , "alger","ingénieur" , "ingénieur" , "123456789"),
            new Adult("oussama" , "nemamcha" , "admin" , new Date() , "alger","ingénieur" , "ingénieur" , "123456789"),
            new Adult("oussama" , "nemamcha" , "admin" , new Date() , "alger","ingénieur" , "ingénieur" , "123456789"),
            new Adult("oussama" , "nemamcha" , "admin" , new Date() , "alger","ingénieur" , "ingénieur" , "123456789"),
            new Adult("oussama" , "nemamcha" , "admin" , new Date() , "alger","ingénieur" , "ingénieur" , "123456789"),
            new Adult("oussama" , "nemamcha" , "admin" , new Date() , "alger","ingénieur" , "ingénieur" , "123456789")
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        MychoiceBox.getItems().addAll(choix);
        this.MychoiceBox.getSelectionModel().select(0);
       nom.setCellValueFactory(new PropertyValueFactory<Adult, String>("nom"));
      prenom.setCellValueFactory(new PropertyValueFactory<Adult, String>("prenom"));
        adress.setCellValueFactory(new PropertyValueFactory<Adult, String>("adress"));
      tel.setCellValueFactory(new PropertyValueFactory<Adult, String>(" numTel"));
        profession.setCellValueFactory(new PropertyValueFactory<Adult, String>("profession"));
        diplome.setCellValueFactory(new PropertyValueFactory<Adult, String>(" diplome"));
       datedenaissance.setCellValueFactory(new PropertyValueFactory<Adult, String>("dateNaissance"));
     lieudenaissance.setCellValueFactory(new PropertyValueFactory<Adult, String>("lieuNaissance"));


        adulttable.setItems(list);
    }
}


