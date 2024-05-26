package com.example.demo;

import com.example.demo.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.*;


public class AnamnesTestController implements Initializable {
    @FXML
    AnchorPane parentPan;
    @FXML
    Button buttonaname;
    @FXML
    Button buttonquestionnaire ;
    @FXML
    GridPane griddisplay;
    @FXML
    AnchorPane displaypane;
    @FXML
    TableView tabledetails;
    @FXML
    Button buttonexercice;
    @FXML
    AnchorPane pandetails;




    @FXML
    Button creerTestAnam;

    @FXML
    Button buttonquestion;
    @FXML
    TableColumn<Question, String> qst;
    @FXML
    TableColumn<Question, String> enonce;
    @FXML
    TableColumn<Question, String> catg;
    @FXML
    Label questionLabel;

    ObservableList<Anamnese> listaname = FXCollections.observableArrayList();
    //ObservableList<Question> listQuestions = FXCollections.observableArrayList();
    ObservableList<Test> listDesEpreuvesCliniques = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listDesEpreuvesCliniques.addAll(HelloApplication.epreuveClinique.getTests());


        // listaname.addAll(HelloApplication.anamneseAdult, HelloApplication.anamneseEnfant);
        listaname.addAll(HelloApplication.anamnese);
       //  listDesEpreuvesCliniques.addAll(HelloApplication.epreuveClinique.getTests());

        buttonaname.setStyle("-fx-background-color: #ffffff");
        buttonaname.setOnAction(e -> {

            handlAnamnese();


        });
        buttonquestionnaire.setOnAction(e -> {
            rendreTestQuestionnair();
        });


        buttonexercice.setOnAction(e -> {
            rendreExercice();
        });


        enonce.setCellValueFactory(new PropertyValueFactory<>("enonce"));
        catg.setCellValueFactory(new PropertyValueFactory<>("cathegorie"));

    }


    private void ajouterTestAnam() {
        Dialog<Anamnese> dialog = new Dialog<>();
        dialog.setTitle("Ajouter un test anamnese");
        ChoiceBox<String> choiceBox = new ChoiceBox<>();

        choiceBox.setItems(FXCollections.observableArrayList("Adult", "Enfant"));

        dialog.getDialogPane().setContent(choiceBox);
        ButtonType applyButtonType = new ButtonType("Apply", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(applyButtonType, cancelButtonType);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == applyButtonType) {
                if (choiceBox.getValue().equals("Adult")) {
                    HelloApplication.anamnese.add(new AnamneseAdult(Arrays.asList()));
                    listaname.add(new AnamneseAdult(Arrays.asList()));
                } else {
                    HelloApplication.anamnese.add(new AnamneseEnfant(Arrays.asList()));
                    listaname.add(new AnamneseEnfant(Arrays.asList()));
                    handlAnamnese();
                }
                return null;
            }
            return null;
        });
        dialog.showAndWait();
    }


    public void handlAnamnese() {
        creerTestAnam.setOnAction(event -> {
            ajouterTestAnam();
        });
        griddisplay.getChildren().clear();
        pandetails.getChildren().clear();
        if (!pandetails.getChildren().contains(tabledetails)) {
            pandetails.getChildren().add(tabledetails);
        }

        // displaypane.getChildren().add(griddisplay);
        if (!displaypane.getChildren().contains(griddisplay)) {
            displaypane.getChildren().add(griddisplay);
        }


        buttonexercice.setStyle("-fx-background-color: #ffffff");

        buttonaname.setStyle("-fx-background-color: #ff0000");

        //  handlAnamnese();
        questionLabel.setText("Anamnese");


        for (Anamnese a : listaname) {

            griddisplay.setHgap(10); // Change this value to your preferred horizontal gap
            griddisplay.setVgap(10);
            Label questionLabel = new Label();
            // Create a TitledPane with some content
            Button detailsButton = new Button("Show Details");
            Button ajouteranam = new Button("ajouter ");
            Label label = new Label(a.getType());
            questionLabel.setText("Anamnese : " + listaname.indexOf(a));

            griddisplay.add(questionLabel, 0, listaname.indexOf(a));
            griddisplay.add(label, 1, listaname.indexOf(a));
            griddisplay.add(detailsButton, 2, listaname.indexOf(a));
            griddisplay.add(ajouteranam, 3, listaname.indexOf(a));
            detailsButton.setOnAction(e -> {
                handlShowDetails(a);
            });
            ajouteranam.setOnAction(event -> {
                ajouterAnam(a.getType(), a);
            });

        }


    }

    public void handlShowDetails(Anamnese anamnese) {
        System.out.println("show details");
        questionLabel.setText("Anamnese : " + anamnese.getType());
        tabledetails.getItems().clear();

        tabledetails.getItems().addAll(anamnese.getQuestions());

    }


    public void ajouterAnam(String type, Anamnese a) {

        Dialog<Question> dialog = new Dialog<>();
        dialog.setTitle("Ajouter une question");

        // Créer les contrôles
        TextField enonceField = new TextField();
        enonceField.setPromptText("Énoncé");
        // ChoiceBox<String> typeChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList("Adulte", "Enfant"));
        ChoiceBox<CathegorieAdult> cathegorieAdultChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(CathegorieAdult.values()));
        ChoiceBox<CathegoriesEnf> cathegorieEnfChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(CathegoriesEnf.values()));

        // Ajouter les contrôles au dialogue
        GridPane grid = new GridPane();

        grid.add(new Label("Énoncé:"), 0, 0);
        grid.add(enonceField, 1, 0);
        grid.add(new Label("Catégorie: "), 0, 2);
        grid.add(cathegorieAdultChoiceBox, 1, 2);
        grid.add(cathegorieEnfChoiceBox, 1, 2);
        dialog.getDialogPane().setContent(grid);
        grid.setVgap(10);


        if (type == "Adult") {
            cathegorieAdultChoiceBox.setVisible(true);
            cathegorieEnfChoiceBox.setVisible(false);

        } else {
            cathegorieAdultChoiceBox.setVisible(false);
            cathegorieEnfChoiceBox.setVisible(true);
        }

        ButtonType applyButtonType = new ButtonType("Apply", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(applyButtonType, cancelButtonType);

        // Définir le résultat du dialogue
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == applyButtonType) {

                Question q;
                if (type.equals("Adult")) {
                    q = new QuestionAnamAdult(enonceField.getText(), cathegorieAdultChoiceBox.getValue());
                    // HelloApplication.anamneseAdult.addQuestion((QuestionAnamAdult) q);
                    ((AnamneseAdult) a).addQuestion((QuestionAnamAdult) q);
                    tabledetails.getItems().add(q);
                } else {
                    q = new QuestionAnamEnfant(enonceField.getText(), cathegorieEnfChoiceBox.getValue());
                    //HelloApplication.anamneseEnfant.addQuestion((QuestionAnamEnfant) q);
                    ((AnamneseEnfant) a).addQuestion((QuestionAnamEnfant) q);
                    tabledetails.getItems().add(q);
                }
                listaname.addAll(HelloApplication.anamnese);
                //tabledetails.getItems().clear();

            }
            return null;
        });

        // Afficher le dialogue et obtenir le résultat
        dialog.showAndWait();


    }








    public void rendreExercice() {
        creerTestAnam.setOnAction(event -> {
            creerTestExercice();
        });

        griddisplay.getChildren().clear();
        pandetails.getChildren().remove(tabledetails);
        questionLabel.setText("Test :");
        //displaypane.getChildren().add(0,griddisplay);
        buttonaname.setStyle("-fx-background-color: #ffffff");
        buttonexercice.setStyle("-fx-background-color: #ff0000");

        int i = 0;
        for (Test t : listDesEpreuvesCliniques) {
            if (t instanceof TestExercice) {

                System.out.println("le nom de test " + t.toString());
                Label questionLabel = new Label();
                // Create a TitledPane with some content
                Button detailsButton = new Button("Show Details");
                Button ajouterExo = new Button("ajouter ");
                Label label = new Label(t.getNom());
                questionLabel.setText("Exercice: " + i);
                System.out.println("le nom de test " + t.getNom());
                griddisplay.add(questionLabel, 0, i);
                griddisplay.add(label, 1, i);
                griddisplay.add(detailsButton, 2, i);
                griddisplay.add(ajouterExo, 3, i);
                detailsButton.setOnAction(e -> {
                    showDetailsExo((TestExercice) t);
                });
                ajouterExo.setOnAction(event -> {
                    ajouterExo((TestExercice) t);
                });

                i++;
            }

        }


    }

    private void creerTestExercice() {
        Dialog<TestExercice> dialog = new Dialog<>();
        dialog.setTitle("Ajouter un test Exercice");
        TextField nomField = new TextField();
        nomField.setPromptText("Nom du test");
        dialog.getDialogPane().setContent(nomField);
        ButtonType applyButtonType = new ButtonType("Apply", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(applyButtonType, cancelButtonType);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == applyButtonType) {

                TestExercice testExercice = new TestExercice(nomField.getText(), Arrays.asList());
                HelloApplication.epreuveClinique.ajouterTest(testExercice);
                listDesEpreuvesCliniques.add(testExercice);
                rendreExercice();
                return testExercice;
            }
            return null;
        });
        dialog.showAndWait();
    }


    private void showDetailsExo(TestExercice t) {
        VBox vBox = new VBox();
        pandetails.getChildren().clear();
        pandetails.getChildren().add(vBox);
        questionLabel.setText("Test : " + t.getNom());

        ObservableList<String> Exercice = FXCollections.observableArrayList();


        for (Exercice exo : t.getExercices()) {
            System.out.println("nombre des exo :" + t.getExercices().size());
            Accordion accordion = new Accordion();
            accordion.setPrefWidth(437);


            // Créer un TitledPane pour chaque exo
            TitledPane titledPane = new TitledPane();
            titledPane.setText("Le nom d'exercice ");

            // Créer le contenu du TitledPane
            VBox content = new VBox();
            content.getChildren().add(new Label(exo.getNom()));
            titledPane.setContent(content);
            accordion.getPanes().add(titledPane);

            String consigne = exo.getConsigne();
            VBox contentconsign = new VBox();
            TitledPane titledPaneRep = new TitledPane();
            titledPaneRep.setText(" La consigne : ");
            contentconsign.getChildren().add(new Label(consigne));
            titledPaneRep.setContent(contentconsign);
            accordion.getPanes().add(titledPaneRep);
            VBox contentNomMateriel = new VBox();
            TitledPane titledPaneNomDemateriel = new TitledPane();
            titledPaneNomDemateriel.setText(" Le materiel : ");
            contentNomMateriel.getChildren().add(new Label(exo.getNomMateriel()));
            titledPaneNomDemateriel.setContent(contentNomMateriel);
            accordion.getPanes().add(titledPaneNomDemateriel);
            ;
            vBox.getChildren().add(accordion);
        }
    }

    private void ajouterExo(TestExercice t) {
        Dialog<Exercice> dialog = new Dialog<>();

        dialog.setTitle("Ajouter un exercice");

        // Créer les contrôles
        TextField nomExoField = new TextField();
        nomExoField.setPromptText("Veuillez saisier le nom d'exercice ");
        TextField consigneField = new TextField();
        consigneField.setPromptText("Veulliez saisir la consigne ");
        TextField nomMaterielField = new TextField();
        nomMaterielField.setPromptText("Veulliez saisir le nom de materiel ");
        // Ajouter les contrôles au dialogue
        GridPane grid = new GridPane();

        grid.add(new Label("Nom d'exercice:"), 0, 0);
        grid.add(nomExoField, 1, 0);
        grid.add(new Label("Consigne:"), 0, 1);
        grid.add(consigneField, 1, 1);
        grid.add(new Label("Nom de materiel:"), 0, 2);
        grid.add(nomMaterielField, 1, 2);

        dialog.getDialogPane().setContent(grid);
        grid.setVgap(10);

        ButtonType applyButtonType = new ButtonType("Apply", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(applyButtonType, cancelButtonType);

        // Définir le résultat du dialogue
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == applyButtonType) {
                Exercice q;
                if (!nomMaterielField.getText().isEmpty()) {
                    q = new Exercice(nomExoField.getText(), consigneField.getText(), nomMaterielField.getText());
                } else {
                    q = new Exercice(nomExoField.getText(), consigneField.getText());
                }
                t.ajouterExercice(q);
                return q;
            }
            return null;
        });
        dialog.showAndWait();
    }

    public void rendreTestQuestionnair() {
        creerTestAnam.setOnAction(event -> {
            creerTestQuestionnaire();
        });

        griddisplay.getChildren().clear();
        pandetails.getChildren().remove(tabledetails);
        questionLabel.setText("Test :");
        //displaypane.getChildren().add(0,griddisplay);
        buttonexercice.setStyle("-fx-background-color: #ffffff");
        buttonaname.setStyle("-fx-background-color: #ffffff");


        int i = 0;
        for (Test t : listDesEpreuvesCliniques) {
            if (t instanceof TestQuestionnaire) {

                System.out.println("le nom de test " + t.toString());
                Label questionLabel = new Label();
                // Create a TitledPane with some content
                Button detailsButton = new Button("Show Details");
                Button ajouterQ = new Button("ajouter ");
                Label label = new Label(t.getNom());
                questionLabel.setText("Questionnaire: " + i);
                System.out.println("le nom de test " + t.getNom());
                griddisplay.add(questionLabel, 0, i);
                griddisplay.add(label, 1, i);
                griddisplay.add(detailsButton, 2, i);
                griddisplay.add(ajouterQ, 3, i);
                detailsButton.setOnAction(e -> {
                    showDetailsQuestionnaire((TestQuestionnaire) t);
                });
                ajouterQ.setOnAction(event -> {
                    ajouterQuestionAQuestionnaire((TestQuestionnaire) t);
                });

                i++;
            }

        }

    }

    private void ajouterQuestionAQuestionnaire(TestQuestionnaire t) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Create a Question");

// Create controls
        ChoiceBox<String> typeChoiceBox = new ChoiceBox<>();
        typeChoiceBox.setItems(FXCollections.observableArrayList("QCM", "QCU", "Question à réponse libre"));
        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, cancelButtonType);


// Add controls to dialog
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.add(new Label("Type:"), 0, 0);
        grid.add(typeChoiceBox, 1, 0);
        dialog.getDialogPane().setContent(grid);

// Define the result of the dialog
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return typeChoiceBox.getValue();
            }
            return null;
        });

// Show the dialog and get the result
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(type -> {
            switch (type) {
                case "QCM":
                    // Show dialog to create a QCM question
                    createQcmDialog(t);
                    break;
                case "QCU":
                    // Show dialog to create a QCU question
                    createQcuDialog(t);
                    break;
                case "Question à réponse libre":
                    // Show dialog to create a free response question
                    createFreeResponseDialog(t);
                    break;
            }
        });
    }

    private void createFreeResponseDialog(TestQuestionnaire t) {
        Dialog<QesrepLibre> dialog = new Dialog<>();
        dialog.setTitle("Ajouter une question");

        // Créer les contrôles
        TextField enonceField = new TextField();
        enonceField.setPromptText("Veuillez saisier l'enonce ");
        TextField reponseField = new TextField();
        reponseField.setPromptText("Veulliez saisir la reponse ");
        // Ajouter les contrôles au dialogue
        GridPane grid = new GridPane();

        grid.add(new Label("Énoncé:"), 0, 0);
        grid.add(enonceField, 1, 0);
        grid.add(new Label("Reponse:"), 0, 1);
        grid.add(reponseField, 1, 1);

        dialog.setWidth(300);
        dialog.getDialogPane().setContent(grid);
        grid.setVgap(20);
        grid.setHgap(20);

        ButtonType applyButtonType = new ButtonType("Apply", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(applyButtonType, cancelButtonType);

        // Définir le résultat du dialogue
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == applyButtonType) {
                QesrepLibre q = new QesrepLibre(enonceField.getText(), reponseField.getText());
                t.ajouterQuestion(q);
                return q;
            }
            return null;
        });
        dialog.showAndWait();
    }

    private void createQcuDialog(TestQuestionnaire t) {
        Dialog<Qcu> dialog = new Dialog<>();

        dialog.setTitle("Ajouter une question");

        // Créer les contrôles
        TextField enonceField = new TextField();
        enonceField.setPromptText("Veuillez saisier l'enonce ");
        TextField reponse1Field = new TextField();
        reponse1Field.setPromptText("Veulliez saisir la proposition 1");
        TextField reponse2Field = new TextField();
        reponse2Field.setPromptText("Veulliez saisir la proposition 2");
        TextField reponse3Field = new TextField();
        reponse3Field.setPromptText("Veulliez saisir la proposition 3");
        TextField reponse4Field = new TextField();
        reponse4Field.setPromptText("Veulliez saisir la proposition 4");
        RadioButton reponse1Radio = new RadioButton();
        RadioButton reponse2Radio = new RadioButton();
        RadioButton reponse3Radio = new RadioButton();
        RadioButton reponse4Radio = new RadioButton();
        // Create a ToggleGroup
        ToggleGroup group = new ToggleGroup();
        reponse1Radio.setToggleGroup(group);
        reponse2Radio.setToggleGroup(group);
        reponse3Radio.setToggleGroup(group);
        reponse4Radio.setToggleGroup(group);

        // Ajouter les contrôles au dialogue
        GridPane grid = new GridPane();

        grid.add(new Label("Énoncé:"), 0, 0);
        grid.add(enonceField, 1, 0);
        grid.add(new Label("Proposition 1:"), 0, 1);
        grid.add(reponse1Field, 1, 1);
        grid.add(reponse1Radio, 2, 1);
        grid.add(new Label("Proposition 2:"), 0, 2);
        grid.add(reponse2Field, 1, 2);
        grid.add(reponse2Radio, 2, 2);
        grid.add(new Label("Proposition 3:"), 0, 3);
        grid.add(reponse3Field, 1, 3);
        grid.add(reponse3Radio, 2, 3);
        grid.add(new Label("Proposition 4:"), 0, 4);
        grid.add(reponse4Field, 1, 4);
        grid.add(reponse4Radio, 2, 4);
       // ToggleGroup group = new ToggleGroup();

        dialog.getDialogPane().setContent(grid);
        grid.setVgap(20);
        grid.setHgap(20);
        ButtonType applyButtonType = new ButtonType("Apply", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(applyButtonType, cancelButtonType);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == applyButtonType) {
                String reponse = null;
                if (reponse1Radio.isSelected()) {
                    reponse = reponse1Field.getText();
                }
                if (reponse2Radio.isSelected()) {
                    reponse = reponse2Field.getText();
                }
                if (reponse3Radio.isSelected()) {
                    reponse = reponse3Field.getText();
                }
                if (reponse4Radio.isSelected()) {
                    reponse = reponse4Field.getText();
                }
                Qcu q = new Qcu(enonceField.getText(), FXCollections.observableArrayList(reponse1Field.getText(), reponse2Field.getText(), reponse3Field.getText(), reponse4Field.getText()), reponse);

                t.ajouterQuestion(q);
                ;
                Test t1 = HelloApplication.epreuveClinique.getTests().get(0);
                System.out.println("helloapplication :" + t1.getQuestions().size());

                return q;
            }
            return null ;
        });
        dialog.showAndWait();
    }

    private void createQcmDialog(TestQuestionnaire test) {
        Dialog<Qcm> dialog = new Dialog<>();

        dialog.setTitle("Ajouter une question");

        // Créer les contrôles
        TextField enonceField = new TextField();
        enonceField.setPromptText("Veuillez saisier l'enonce ");
        TextField reponse1Field = new TextField();
        reponse1Field.setPromptText("Veulliez saisir la proposition 1");
        TextField reponse2Field = new TextField();
        reponse2Field.setPromptText("Veulliez saisir la proposition 2");
        TextField reponse3Field = new TextField();
        reponse3Field.setPromptText("Veulliez saisir la proposition 3");
        TextField reponse4Field = new TextField();
        reponse4Field.setPromptText("Veulliez saisir la proposition 4");
        CheckBox reponse1CheckBox = new CheckBox();
        CheckBox reponse2CheckBox = new CheckBox();
        CheckBox reponse3CheckBox = new CheckBox();
        CheckBox reponse4CheckBox = new CheckBox();

        // Ajouter les contrôles au dialogue
        GridPane grid = new GridPane();

        grid.add(new Label("Énoncé:"), 0, 0);
        grid.add(enonceField, 1, 0);
        grid.add(new Label("Proposition 1:"), 0, 1);
        grid.add(reponse1Field, 1, 1);
        grid.add(reponse1CheckBox, 2, 1);
        grid.add(new Label("Proposition 2:"), 0, 2);
        grid.add(reponse2Field, 1, 2);
        grid.add(reponse2CheckBox, 2, 2);
        grid.add(new Label("Proposition 3:"), 0, 3);
        grid.add(reponse3Field, 1, 3);
        grid.add(reponse3CheckBox, 2, 3);
        grid.add(new Label("Proposition 4:"), 0, 4);
        grid.add(reponse4Field, 1, 4);
        grid.add(reponse4CheckBox, 2, 4);

        dialog.getDialogPane().setContent(grid);
        grid.setVgap(20);
        grid.setHgap(20);

        ButtonType applyButtonType = new ButtonType("Apply", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(applyButtonType, cancelButtonType);

        // Définir le résultat du dialogue
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == applyButtonType) {
                Qcm q = new Qcm(enonceField.getText(), FXCollections.observableArrayList(reponse1Field.getText(), reponse2Field.getText(), reponse3Field.getText(), reponse4Field.getText()), FXCollections.observableArrayList());
                if (reponse1CheckBox.isSelected()) {
                    q.getReponses().add(reponse1Field.getText());
                }
                if (reponse2CheckBox.isSelected()) {
                    q.getReponses().add(reponse2Field.getText());
                }
                if (reponse3CheckBox.isSelected()) {
                    q.getReponses().add(reponse3Field.getText());
                }
                if (reponse4CheckBox.isSelected()) {
                    q.getReponses().add(reponse4Field.getText());
                }
                test.ajouterQuestion(q);
                ;
                Test t = HelloApplication.epreuveClinique.getTests().get(0);
                System.out.println("helloapplication :" + t.getQuestions().size());

                return q;
            }


            return null;
        });
        dialog.showAndWait();

    }

    private void showDetailsQuestionnaire(TestQuestionnaire t) {
        pandetails.getChildren().clear();
        VBox vBox = new VBox();
        pandetails.getChildren().add(vBox);
        questionLabel.setText("Test : " + t.getNom());
        for (Question2 question : t.getQuestions()) {

            if (question instanceof Qcm) {
                Qcm qcm = (Qcm) question;
                Accordion accordion = new Accordion();
                accordion.setPrefWidth(437);
                TitledPane titledPane = new TitledPane();
                titledPane.setText("Énoncé ");
                VBox content = new VBox();
                content.getChildren().add(new Label(qcm.getEnonce()));
                titledPane.setContent(content);
                accordion.getPanes().add(titledPane);
                for (String proposition : qcm.getPropositions()) {
                    VBox contentProp = new VBox();
                    TitledPane titledPanePro = new TitledPane();
                    titledPanePro.setText("Proposition 0" + (qcm.getPropositions().indexOf(proposition) + 1));
                    contentProp.getChildren().add(new Label(proposition));
                    titledPanePro.setContent(contentProp);
                    accordion.getPanes().add(titledPanePro);
                }
                for (String reponse : qcm.getReponses()) {
                    VBox contentRep = new VBox();
                    TitledPane titledPaneRep = new TitledPane();
                    titledPaneRep.setText("Reponse 0" + (qcm.getReponses().indexOf(reponse) + 1));
                    contentRep.getChildren().add(new Label(reponse));
                    titledPaneRep.setContent(contentRep);
                    accordion.getPanes().add(titledPaneRep);
                }
                vBox.getChildren().add(accordion);
            } else if (question instanceof Qcu) {
                Qcu qcu = (Qcu) question;
                Accordion accordion = new Accordion();
                accordion.setPrefWidth(437);
                TitledPane titledPane = new TitledPane();
                titledPane.setText("Énoncé ");
                VBox content = new VBox();
                content.getChildren().add(new Label(qcu.getEnonce()));
                titledPane.setContent(content);
                accordion.getPanes().add(titledPane);
                for (String proposition : qcu.getPropositions()) {
                    VBox contentProp = new VBox();
                    TitledPane titledPanePro = new TitledPane();
                    titledPanePro.setText("Proposition 0" + (qcu.getPropositions().indexOf(proposition) + 1));
                    contentProp.getChildren().add(new Label(proposition));
                    titledPanePro.setContent(contentProp);
                    accordion.getPanes().add(titledPanePro);
                }
                String reponse = qcu.getReponse();
                VBox contentRep = new VBox();
                TitledPane titledPaneRep = new TitledPane();
                titledPaneRep.setText(" La reponse : ");
                contentRep.getChildren().add(new Label(reponse));
                titledPaneRep.setContent(contentRep);
                accordion.getPanes().add(titledPaneRep);
                vBox.getChildren().add(accordion);
            } else if (question instanceof QesrepLibre) {
                QesrepLibre qesrepLibre = (QesrepLibre) question;
                Accordion accordion = new Accordion();
                accordion.setPrefWidth(437);
                TitledPane titledPane = new TitledPane();
                titledPane.setText("Énoncé ");
                VBox content = new VBox();
                content.getChildren().add(new Label(qesrepLibre.getEnonce()));
                titledPane.setContent(content);
                accordion.getPanes().add(titledPane);
                String reponse = qesrepLibre.getReponse();
                VBox contentRep = new VBox();
                TitledPane titledPaneRep = new TitledPane();
                titledPaneRep.setText(" La reponse : ");
                contentRep.getChildren().add(new Label(reponse));
                titledPaneRep.setContent(contentRep);
                accordion.getPanes().add(titledPaneRep);
                vBox.getChildren().add(accordion);

            }

            }
        }

        private void creerTestQuestionnaire () {
            Dialog<TestQuestionnaire> dialog = new Dialog<>();
            dialog.setTitle("Ajouter un test Questionnaire");
            TextField nomField = new TextField();
            nomField.setPromptText("Nom du test");
            dialog.getDialogPane().setContent(nomField);
            dialog.setHeight(300);
            ButtonType applyButtonType = new ButtonType("Apply", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(applyButtonType, cancelButtonType);
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == applyButtonType) {
                   List<Question2> vide = new ArrayList<>();
                    TestQuestionnaire testQuestionnaire = new TestQuestionnaire(nomField.getText(), vide);
                    HelloApplication.epreuveClinique.ajouterTest(testQuestionnaire);
                    listDesEpreuvesCliniques.add(testQuestionnaire);
                    rendreTestQuestionnair();
                    return testQuestionnaire;
                }
                return null;
            });
            dialog.showAndWait();
        }


//    private List <Test> genereLesEpreuvesCliniques() {
//        Qcm qcm1 = new Qcm("Quelle est la capitale de la France ?", FXCollections.observableArrayList("Paris", "Londres", "Berlin", "Madrid"), FXCollections.observableArrayList("Paris"));
//        Qcm qcm2 = new Qcm("Quelle est la capitale de l'Espagne ?", FXCollections.observableArrayList("Paris", "Londres", "Berlin", "Madrid"), FXCollections.observableArrayList("Madrid"));
//        Qcu qcu1 = new Qcu("Quelle est la capitale de l'Allemagne ?", FXCollections.observableArrayList("Paris", "Londres", "Berlin", "Madrid"), "Berlin");
//        Qcu qcu2 = new Qcu("Quelle est la capitale de l'Italie ?", FXCollections.observableArrayList("Paris", "Londres", "Rome", "Madrid"), "Rome");
//        QesrepLibre qesrepLibre1 = new QesrepLibre("Quelle est la capitale de la Belgique ?", "Bruxelles");
//        QesrepLibre qesrepLibre2 = new QesrepLibre("Quelle est la capitale du Maroc ?", "Rabat");
//        Exercice exo1 = new Exercice("Exercice 1", "Faites une phrase avec les mots suivants : chien, chat, souris", "Papier, stylo");
//        Exercice exo2 = new Exercice("Exercice 2", "Faites une phrase avec les mots suivants : voiture, vélo, moto", "Papier, stylo");
//        Test testExercice = new TestExercice("Test de français", FXCollections.observableArrayList(exo1, exo2));
//        TestQuestionnaire testQuestionnaire = new TestQuestionnaire("Test de géographie", FXCollections.observableArrayList(qcm1, qcm2, qcu1, qcu2, qesrepLibre1, qesrepLibre2));
//        List<Test> tests = FXCollections.observableArrayList(testExercice, testQuestionnaire);
//    return tests ;
//
//    }


}




