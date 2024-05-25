package com.example.demo;

import com.example.demo.models.Adult;
import com.example.demo.models.Patient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.lang.reflect.Type;
import java.net.URL;
import java.util.ResourceBundle;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DialogueAjouterPatientController implements Initializable {
    //handle the apply button click
    @FXML
    TextField nomField ;
    @FXML
    TextField prenomField ;
    @FXML
    DatePicker dateField ;
    @FXML
    TextField lieuField ;
    @FXML
    TextField diplomeField ;
    @FXML
    TextField professionField ;
    @FXML
    TextField contactField ;
    @FXML
    TextField adressField ;
    @FXML
    DialogPane dialogpan ;

    Patient patient ;

    public void applyButtonAction() {
        System.out.println("Apply button clicked");

            if(nomField.getText().isEmpty() || prenomField.getText().isEmpty() || dateField.getValue() == null || lieuField.getText().isEmpty() || diplomeField.getText().isEmpty() || professionField.getText().isEmpty() || contactField.getText().isEmpty() || adressField.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Veulliez remplire tous les champs");
                alert.showAndWait();
                return;
            }

            System.out.println("nom"+nomField.getText() + "data"+dateField.getValue());
            patient = new Adult(nomField.getText(),prenomField.getText(),adressField.getText(), Date.from(dateField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()) ,lieuField.getText(),diplomeField.getText(),professionField.getText(),contactField.getText());


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("dialogue ajouter patient controller");
        nomField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("^[a-zA-Z]*$")) ? change : null));
        prenomField.setTextFormatter(new TextFormatter<>(
                change ->(change.getControlNewText().matches("^[a-zA-Z]*$")) ? change : null));
        contactField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("^[0-9]*$")) ? change : null));




    }
    public Patient getPatient()
    {
        return patient ;
    }


}
