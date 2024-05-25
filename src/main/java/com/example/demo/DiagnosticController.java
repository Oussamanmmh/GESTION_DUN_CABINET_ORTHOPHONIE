package com.example.demo;

import com.example.demo.models.Trouble;
import com.example.demo.models.TroubleCatig;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DiagnosticController implements Initializable {
    ScrollPane scrollPane = new ScrollPane();
    Dialog dialog = new Dialog();
    GridPane gridPane = new GridPane();

    private List <Trouble> troubles=new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void diplayDialogue(){



        TextField nomTrouble = new TextField();
        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll(TroubleCatig.DEGLUTITION, TroubleCatig.COGNITIFS, TroubleCatig.NEURODEVELOPPEMENTAUX);
        Button ajouter = new Button("Ajouter");
        ajouter.setStyle("-fx-background-color:  #56c6d0; -fx-text-fill: white" );
        nomTrouble.setPromptText("Nom du trouble");
        choiceBox.setTooltip(new Tooltip("Catégorie du trouble"));
        ajouter.setTooltip(new Tooltip("Ajouter un nouveau trouble"));
        dialog.setTitle("Ajouter un diagnostic");

        gridPane.add(nomTrouble, 0, 0); // Add the TextField to the first column of the new row
        gridPane.add(choiceBox, 1, 0); // Add the ChoiceBox to the second column of the new row
        gridPane.add(ajouter, 2,0 ); // Add the "Ajouter" button to the third column of the new row
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        ajouter.setOnAction(event -> {
            ajouterNode(); ;
        });
        scrollPane.setContent(gridPane);
        dialog.getDialogPane().setContent(scrollPane);
        dialog.getDialogPane().setPrefSize(600,200);
        ButtonType buttonType = new ButtonType("Valider", ButtonBar.ButtonData.OK_DONE);
       ButtonType buttonType1 = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(buttonType, buttonType1);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == buttonType) {
             for (int i = 0; i < gridPane.getChildren().size(); i++) {
                 String nomTrouble1 = "";
                 String catig = "";
                 if (gridPane.getChildren().get(i) instanceof TextField) {
                     System.out.println(((TextField) gridPane.getChildren().get(i)).getText());
                     nomTrouble1 = ((TextField) gridPane.getChildren().get(i)).getText();
                 }
                 if (gridPane.getChildren().get(i) instanceof ChoiceBox) {
                     System.out.println(((ChoiceBox) gridPane.getChildren().get(i)).getValue());
                        catig = ((TroubleCatig) ((ChoiceBox) gridPane.getChildren().get(i)).getValue()).toString();
                 }
                    if (!nomTrouble1.equals("") && !catig.equals("")) {
                        troubles.add(new Trouble(nomTrouble1, TroubleCatig.valueOf(catig)));
                    }
             }



            }
            return null;
        });
        dialog.showAndWait();
    }



    private void ajouterNode() {
        TextField nomTrouble = new TextField();
        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll(TroubleCatig.DEGLUTITION, TroubleCatig.COGNITIFS, TroubleCatig.NEURODEVELOPPEMENTAUX);
        Button ajouter = new Button("Ajouter");
        ajouter.setStyle("-fx-background-color:  #56c6d0; -fx-text-fill: white" );

        Button supprimer = new Button("Supprimer");
        supprimer.setStyle("-fx-background-color: red; -fx-text-fill: white");
        nomTrouble.setPromptText("Nom du trouble");
        choiceBox.setTooltip(new Tooltip("Catégorie du trouble"));
        ajouter.setTooltip(new Tooltip("Ajouter un nouveau trouble"));
        supprimer.setTooltip(new Tooltip("Supprimer ce trouble"));

        int row = gridPane.getRowCount(); // Get the current number of rows in the gridPane

        gridPane.add(nomTrouble, 0, row); // Add the TextField to the first column of the new row
        gridPane.add(choiceBox, 1, row); // Add the ChoiceBox to the second column of the new row
        gridPane.add(ajouter, 2, row); // Add the "Ajouter" button to the third column of the new row
        gridPane.add(supprimer, 3, row); // Add the "Supprimer" button to the fourth column of the new row

        ajouter.setOnAction(event -> {
            ajouterNode();
        });
        supprimer.setOnAction(event -> {
            supprimerNode(nomTrouble ,choiceBox , ajouter , supprimer);
        });
    }

    private void supprimerNode(TextField nomTrouble, ChoiceBox choiceBox, Button ajouter, Button supprimer) {
        gridPane.getChildren().removeAll(nomTrouble, choiceBox, ajouter, supprimer);
    }
public List<Trouble> getTroubles() {
    return troubles;
}


}
