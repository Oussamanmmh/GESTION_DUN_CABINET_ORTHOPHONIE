package com.example.demo;

import com.example.demo.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;

import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;

import static net.fortuna.ical4j.util.Numbers.parseInt;

public class AgendaController implements Initializable {

    @FXML
    DatePicker datePicker;

    @FXML
    ComboBox hours;
    @FXML
    ComboBox minutes;
    @FXML
    ComboBox time;
    @FXML
    ChoiceBox MychoiceBox;
    @FXML
    VBox Myvbox;

    TextField nomField = new TextField();
    TextField prenomField = new TextField();
    TextField ageField = new TextField();
    TextField thematiqueField = new TextField();
    @FXML
    ChoiceBox choiceBox = new ChoiceBox();
    @FXML
    TableView tableRDV ;
    @FXML
    TableColumn<RendezVous ,String> type ;
    @FXML
    TableColumn<RendezVous , String> date ;
    @FXML
    TableColumn<RendezVous, String> heure ;
    @FXML
    Button ajouterObs ;

    TextField numDeDossier = new TextField();
    private Label label = new Label() ;

    ObservableList<RendezVous> list ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list = FXCollections.observableArrayList();
        nomField.setPromptText("Le nom de patient");
        prenomField.setPromptText("Le prenom de patient");
        ageField.setPromptText("L'age de patient");
        for (int i = 0; i <= 23; i++) {
            hours.getItems().add(String.format("%02d", i));
        }

        // Populate minutes
        for (int i = 0; i < 60; i++) {
            minutes.getItems().add(String.format("%02d", i));
        }

        // Populate AM/PM
        time.getItems().addAll("AM", "PM");
        hours.setPromptText("HH");
        minutes.setPromptText("MM");
        // Set default values
        hours.setValue(String.format("%02d", 0));
        minutes.setValue(String.format("%02d", 0));
        time.setValue("AM");


        type.setCellValueFactory(new PropertyValueFactory<RendezVous,String>("type"));
        date.setCellValueFactory(new PropertyValueFactory<RendezVous , String>("date"));
        heure.setCellValueFactory(new PropertyValueFactory<RendezVous , String>("heure"));
        //set item of table
        tableRDV.setItems(list);
        Myvbox.setAlignment(javafx.geometry.Pos.CENTER);
        label.setText("Veuillez choisir un type de rendz-vous");
        label.setFont(javafx.scene.text.Font.font(20));
        label.setStyle("-fx-font-weight: bold");
        Myvbox.getChildren().add(label);

        MychoiceBox.getItems().addAll("Consultation", "Suivez", "Atelier");
        MychoiceBox.setValue(null);


        choiceBox.getItems().addAll(DeroulementSeance.ENLIGNE, DeroulementSeance.ENPRESENTIEL);
        thematiqueField.setPromptText("Thematique");


        MychoiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            traitChoiceBox(newValue.toString());

        });
        tableRDV.setRowFactory(tv -> {
            TableRow<RendezVous> row = new TableRow<>();

            Tooltip tooltip = new Tooltip();
            row.itemProperty().addListener((obs, previousRendezVous, currentRendezVous) -> {
                if (currentRendezVous == null) {
                    tooltip.setText("");
                } else {
                    String details = "";
                    switch (currentRendezVous.getType()){
                        case "Consultation":
                    // Customize this string to display the details you want
                         details = "Nom : " + ((Consultation) currentRendezVous).getNomPatient() + "\n" +
                            "Prenom : " + ((Consultation) currentRendezVous).getPrenomPatient() + "\n" +
                            "Age : " + ((Consultation) currentRendezVous).getAgePatient() + "\n" ;
                            break;
                         case "Suivi":
                             details = "Numero de dossier : " + ((Suivi) currentRendezVous).getNumeroDossierDePatient() +"\n" +
                                     "Deroulement: "+ ((Suivi) currentRendezVous).getTypeSeance().toString() + "\n" ;

                            break;

                        case   "Atelier":
                            details = "Thematique : " + ((Atelier) currentRendezVous).getThematique() + "\n" ;
                            break;
                        }
                    tooltip.setText(details);


                }
            });
            row.setTooltip(tooltip);
            return row;
        });

// Define a comparator for the date column
        date.setComparator(new Comparator<String>() {
            @Override
            public int compare(String date1, String date2) {
                LocalDate d1 = LocalDate.parse(date1);
                LocalDate d2 = LocalDate.parse(date2);
                return d1.compareTo(d2);
            }
        });

// Define a comparator for the time column
        heure.setComparator(new Comparator<String>() {
            @Override
            public int compare(String time1, String time2) {
                LocalTime t1 = LocalTime.parse(time1);
                LocalTime t2 = LocalTime.parse(time2);
                return t1.compareTo(t2);
            }
        });
// Sort the table by date and time
       tableRDV.getSortOrder().add(date);
        tableRDV.getSortOrder().add(heure);


        nomField.textProperty().addListener((v, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z ]*") || newValue.startsWith(" ") )  {
                nomField.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
                alertDisplay("Veuillez entrer un nom valide");
            }
        });
        prenomField.textProperty().addListener((v, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z ]*") || newValue.startsWith(" ") )  {
                prenomField.setText(newValue.replaceAll("[^a-zA-Z]", ""));
                alertDisplay("Veuillez entrer un prenom valide");
            }
        });
        ageField.textProperty().addListener((v, oldValue, newValue) -> {
            if (!newValue.matches("[1-9]*") || newValue.startsWith(" ") )  {
                ageField.setText(newValue.replaceAll("[^\\s1-9]", ""));
                alertDisplay("Veuillez entrer un age valide");
            }
        });
        numDeDossier.textProperty().addListener((v, oldValue, newValue) -> {
            if (!newValue.matches("[1-9]*") || newValue.startsWith(" ") )  {
                numDeDossier.setText(newValue.replaceAll("[^\\s1-9]", ""));
                alertDisplay("Veuillez entrer un numero valide");
            }
        });
        thematiqueField.textProperty().addListener((v, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z ]*") || newValue.startsWith(" ") )  {
                thematiqueField.setText(newValue.replaceAll("[^a-zA-Z]", ""));
                alertDisplay("Veuillez entrer une text valide");
            }
        });



    }

    public void traitChoiceBox(String newValue) {

        switch (newValue) {
            case "Consultation":
                Myvbox.getChildren().clear();
                Myvbox.setMargin(nomField, new Insets(0, 0, 20, 0));
                Myvbox.setMargin(prenomField, new Insets(0, 0, 20, 0));
                Myvbox.setMargin(ageField, new Insets(0, 0, 20, 0));
                Myvbox.getChildren().addAll(nomField, prenomField, ageField);

                break;
            case "Suivez":
                System.out.println("Suivez");
                numDeDossier.setPromptText("Le numero de dossier de patient");
                Myvbox.getChildren().clear();
                Myvbox.setMargin(numDeDossier, new Insets(0, 0, 20, 0));
                // Myvbox.getChildren().add(numDeDossier);
                Myvbox.getChildren().addAll(numDeDossier, choiceBox);
                break;
            case "Atelier":
                Myvbox.getChildren().clear();
                Myvbox.setMargin(thematiqueField, new Insets(0, 0, 20, 0));
                Myvbox.getChildren().add(thematiqueField);
                break;
        }
    }

    public void onSubmit(ActionEvent event) {
        System.out.println("Submit button clicked here time");
        if (MychoiceBox.getValue() == null) {
            System.out.println("Veuillez choisir un trait");

            return;
        } else {
            String date = datePicker.getValue().toString();
            Time temp = new Time(Integer.parseInt(hours.getValue().toString()), Integer.parseInt(minutes.getValue().toString()), 0);
            switch (MychoiceBox.getValue().toString()) {
                case "Consultation":
                    System.out.println("Consultation");
                    System.out.println("Nom : " + nomField.getText());
                    if (nomField.getText().isEmpty() || prenomField.getText().isEmpty() || ageField.getText().isEmpty()) {
                        System.out.println("Veuillez remplir tous les champs");
                        alertDisplay("Veuillez remplir tous les champs");
                        return;
                    }

                    try {
                        Consultation consultation = new Consultation(parseInt(ageField.getText()), nomField.getText(), prenomField.getText(), date, temp);
                        String personne;
                        if (parseInt(ageField.getText()) >= 18) personne = TypePatient.ADULTE.toString();
                        else personne = TypePatient.ENFANT.toString();
                        HelloApplication.agendaIntegre.addRendezVous(consultation, personne);
                        list.add( consultation);
                        tableRDV.setItems(list);
                    } catch (IllegalArgumentException |  NullPointerException e){

                       this.alertDisplay(e.getMessage());

                        System.out.println("error" + e.getMessage());

                    }

                    break;
                case "Suivez":
                    if (numDeDossier.getText().isEmpty() || choiceBox.getValue() == null){
                        System.out.println("Veuillez remplir tous les champs");
                        alertDisplay("Veuillez remplir tous les champs");
                        return;
                    }
                    Suivi suivi = new Suivi(choiceBox.getValue() , parseInt(numDeDossier.getText()) , date , temp);
                    try {

                        HelloApplication.agendaIntegre.addRendezVous(suivi, "");
                        list.add(suivi);
                        tableRDV.setItems(list);
                    } catch (IllegalArgumentException e) {

                        this.alertDisplay(e.getMessage());

                        System.out.println("error" + e.getMessage());

                    }
                    break;
                case "Atelier":
                    try{
                        System.out.println("Atelier");
                        System.out.println("Thematique : " + thematiqueField.getText());
                        System.out.println("la date" + datePicker.getValue());
                        System.out.println("l'heure" + hours.getValue() + ":" + minutes.getValue() + " " + time.getValue());
                        Atelier atelier = new Atelier(datePicker.getValue().toString() , temp , thematiqueField.getText());
                        HelloApplication.agendaIntegre.addRendezVous(atelier, "");
                        list.add(atelier);
                        tableRDV.setItems(list);
                    }
                    catch (IllegalArgumentException e) {
                        this.alertDisplay(e.getMessage());

                    }

                    break;
            }
        }
    }

    public void alertDisplay(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void handleClick(ActionEvent event)
    {
        if(tableRDV.getSelectionModel().getSelectedItem() == null)
        {
            alertDisplay("Veuillez selectionner un rendez-vous");
            return; // Exit the method
        }

            // Create a new dialog
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Ajouter une observation");

            // Create a TextArea for the user to enter their observation
            TextArea textArea = new TextArea();
            textArea.setPromptText("Entrez votre observation ici...");

            // Set the content of the dialog to the TextArea
            dialog.getDialogPane().setContent(textArea);

            // Add a button to the dialog to submit the observation
            ButtonType submitButtonType = new ButtonType("Submit", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(submitButtonType, ButtonType.CANCEL);

            // Set the result converter to get the text from the TextArea when the submit button is clicked
            dialog.setResultConverter(buttonType -> {
                if (buttonType == submitButtonType) {
                    return textArea.getText();
                }
                return null;
            });

            // Show the dialog and get the result
            Optional<String> result = dialog.showAndWait();

            // If the user clicked the submit button, print the observation
            result.ifPresent(observation -> {
               RendezVous rdv =(RendezVous) tableRDV.getSelectionModel().getSelectedItem() ;


                     rdv.setObservation(observation);
                     tableRDV.refresh();


            });
        System.out.println("observation est "+   HelloApplication.agendaIntegre.getRendezVous().get(0).getObservation());
    }



}
