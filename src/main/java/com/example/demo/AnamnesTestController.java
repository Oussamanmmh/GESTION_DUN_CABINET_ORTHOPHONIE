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
import java.util.ResourceBundle;


public class AnamnesTestController implements Initializable {
    @FXML
    AnchorPane parentPan;
    @FXML
    Button buttonaname;
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
    Button buttonqcm;

    @FXML
    Button buttonqcu;

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


        listaname.addAll(HelloApplication.anamneseAdult, HelloApplication.anamneseEnfant);
        listDesEpreuvesCliniques.addAll(HelloApplication.epreuveClinique.getTests());
        System.out.println("initialize+:" + listaname.size());

        buttonaname.setStyle("-fx-background-color: #ffffff");
        buttonaname.setOnAction(e -> {

            handlAnamnese();


        });

        buttonqcm.setOnAction(e -> {
            rendreTestQcm();
        });
        buttonqcu.setOnAction(e -> {
            rendreTestQcu();
        });
        buttonquestion.setOnAction(e -> {
            rendreQuestionAreponseLibre();
        });
        buttonexercice.setOnAction(e -> {
            rendreExercice();
        });


        enonce.setCellValueFactory(new PropertyValueFactory<>("enonce"));
        catg.setCellValueFactory(new PropertyValueFactory<>("cathegorie"));

    }


    public void handlAnamnese() {
        griddisplay.getChildren().clear();
        pandetails.getChildren().clear();
        if (!pandetails.getChildren().contains(tabledetails)) {
            pandetails.getChildren().add(tabledetails);
        }

        // displaypane.getChildren().add(griddisplay);
        if (!displaypane.getChildren().contains(griddisplay)) {
            displaypane.getChildren().add(griddisplay);
        }

        System.out.println("anamnese");
        buttonexercice.setStyle("-fx-background-color: #ffffff");
        buttonquestion.setStyle("-fx-background-color: #ffffff");
        buttonqcm.setStyle("-fx-background-color: #ffffff");
        buttonaname.setStyle("-fx-background-color: #ff0000");
        buttonqcu.setStyle("-fx-background-color: #ffffff");
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
                ajouterAnam(a.getType());
            });

        }


    }

    public void handlShowDetails(Anamnese anamnese) {
        System.out.println("show details");
        questionLabel.setText("Anamnese : " + anamnese.getType());
        tabledetails.getItems().clear();

        tabledetails.getItems().addAll(anamnese.getQuestions());

    }


    public void ajouterAnam(String type) {

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
                    HelloApplication.anamneseAdult.addQuestion((QuestionAnamAdult) q);
                    tabledetails.getItems().add(q);
                } else {
                    q = new QuestionAnamEnfant(enonceField.getText(), cathegorieEnfChoiceBox.getValue());
                    HelloApplication.anamneseEnfant.addQuestion((QuestionAnamEnfant) q);
                    tabledetails.getItems().add(q);
                }
                listaname.addAll(HelloApplication.anamneseAdult, HelloApplication.anamneseEnfant);
                //tabledetails.getItems().clear();

            }
            return null;
        });

        // Afficher le dialogue et obtenir le résultat
        dialog.showAndWait();


    }

    public void rendreTestQcm() {

        pandetails.getChildren().remove(tabledetails);
        questionLabel.setText("Test :");
        griddisplay.getChildren().clear();
        //displaypane.getChildren().add(0,griddisplay);
        buttonexercice.setStyle("-fx-background-color: #ffffff");
        buttonquestion.setStyle("-fx-background-color: #ffffff");
        buttonqcu.setStyle("-fx-border-color: #ffffff");
        buttonaname.setStyle("-fx-background-color: #ffffff");
        buttonqcm.setStyle("-fx-background-color: #ff0000");
        int i = 0;

        for (Test qcm : listDesEpreuvesCliniques) {

            if (qcm instanceof TestQCM) {

                griddisplay.setHgap(10); // Change this value to your preferred horizontal gap
                griddisplay.setVgap(10);
                Label questionLabel = new Label();
                // Create a TitledPane with some content
                Button detailsButton = new Button("Show Details");
                Button ajouteQCM = new Button("ajouter ");
                Label label = new Label(qcm.getNom());
                questionLabel.setText("Test Qcm : " + i);

                griddisplay.add(questionLabel, 0, i);
                griddisplay.add(label, 1, i);
                griddisplay.add(detailsButton, 2, i);
                griddisplay.add(ajouteQCM, 3, i);
                detailsButton.setOnAction(e -> {
                    handlShowDetailsTestQ((TestQuestionnaire) qcm);
                });
                ajouteQCM.setOnAction(event -> {
                    ajouterQCM(qcm);
                });

                i++;
            }

        }

    }

    private void ajouterQCM(Test test) {
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
        grid.setVgap(10);

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


    private void handlShowDetailsTestQ(TestQuestionnaire test) {
        System.out.println("show details");
        VBox vBox = new VBox();
        pandetails.getChildren().add(vBox);
        questionLabel.setText("Test : " + test.getNom());

        ObservableList<String> questions = FXCollections.observableArrayList();
//        if (test instanceof TestQCM) {
//                TestQCM testQCM = (TestQCM) test;
//                for (Question2 question : testQCM.getQuestions()) {
//                    if (question instanceof Qcm) {
//                        Qcm qcm = (Qcm) question;
//                        questions.add("Énoncé: " + qcm.getEnonce());
//                        questions.add("Propositions: " + String.join(", ", qcm.getPropositions()));
//                        questions.add("Réponses justes: " + String.join(", ", qcm.getReponses()));
//                    }
//                }
//            }

        //Parcourir la liste des tests QCM et ajouter les détails de chaque question à l'Accordion

        if (test instanceof TestQCM) {
            TestQCM testQCM = (TestQCM) test;
            for (Question2 question : testQCM.getQuestions()) {
                System.out.println("nombre des question :" + testQCM.getQuestions().size());
                Accordion accordion = new Accordion();
                accordion.setPrefWidth(437);

                if (question instanceof Qcm) {
                    Qcm qcm = (Qcm) question;
                    // Créer un TitledPane pour chaque question
                    TitledPane titledPane = new TitledPane();
                    titledPane.setText("Énoncé ");

                    // Créer le contenu du TitledPane
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
                    //content.getChildren().add(new Label("Réponses justes: " + String.join(", ", qcm.getReponses())));

                    // Ajouter le TitledPane à l'Accordion
                    //   accordion.getPanes().add(titledPane);
                }
                vBox.getChildren().add(accordion);

            }
        }


// Créer une ListView et y ajouter la liste observable
        ListView<String> listView = new ListView<>(questions);
        listView.setPrefWidth(437);


    }

    public void rendreTestQcu() {
        griddisplay.getChildren().clear();
        pandetails.getChildren().remove(tabledetails);
        questionLabel.setText("Test :");
        griddisplay.getChildren().clear();
        //displaypane.getChildren().add(0,griddisplay);
        buttonexercice.setStyle("-fx-background-color: #ffffff");
        buttonquestion.setStyle("-fx-background-color: #ffffff");
        buttonaname.setStyle("-fx-background-color: #ffffff");
        buttonqcm.setStyle("-fx-background-color: #ffffff");
        buttonqcu.setStyle("-fx-background-color: #ff0000");

        int i = 0;

        for (Test qcu : listDesEpreuvesCliniques) {
            if (qcu instanceof TestQCU) {


                griddisplay.setHgap(10); // Change this value to your preferred horizontal gap
                griddisplay.setVgap(10);
                Label questionLabel = new Label();
                // Create a TitledPane with some content
                Button detailsButton = new Button("Show Details");
                Button ajouteQCM = new Button("ajouter ");
                Label label = new Label(qcu.getNom());
                questionLabel.setText("Test Qcu : " + i);

                griddisplay.add(questionLabel, 0, i);
                griddisplay.add(label, 1, i);
                griddisplay.add(detailsButton, 2, i);
                griddisplay.add(ajouteQCM, 3, i);
                detailsButton.setOnAction(e -> {
                    showDetailsTestQcu((TestQCU) qcu);
                });
                ajouteQCM.setOnAction(event -> {
                    ajouterQCU(qcu);
                });
            i++ ;
            }


        }
    }

    private void ajouterQCU(Test test) {
        Dialog<Qcu> dialog = new Dialog<>();
        dialog.setWidth(300);
        dialog.setHeight(300);
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

        dialog.getDialogPane().setContent(grid);
        grid.setVgap(10);

        ButtonType applyButtonType = new ButtonType("Apply", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(applyButtonType, cancelButtonType);

        // Définir le résultat du dialogue
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

    public void showDetailsTestQcu(TestQCU test) {
        System.out.println("show details");
        VBox vBox = new VBox();
        pandetails.getChildren().add(vBox);
        questionLabel.setText("Test : " + test.getNom());

        ObservableList<String> questions = FXCollections.observableArrayList();


        for (Question2 question : test.getQuestions()) {
            System.out.println("nombre des question :" + test.getQuestions().size());
            Accordion accordion = new Accordion();
            accordion.setPrefWidth(437);


            Qcu qcu = (Qcu) question;
            // Créer un TitledPane pour chaque question
            TitledPane titledPane = new TitledPane();
            titledPane.setText("Énoncé ");

            // Créer le contenu du TitledPane
            VBox content = new VBox();
            content.getChildren().add(new Label(qcu.getEnonce()));
            titledPane.setContent(content);
            accordion.getPanes().add(titledPane);
            System.out.println("le nombre de proposition : " + qcu.getPropositions().size());
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

            //content.getChildren().add(new Label("Réponses justes: " + String.join(", ", qcu.getReponses())));

            // Ajouter le TitledPane à l'Accordion
            //   accordion.getPanes().add(titledPane);
            vBox.getChildren().add(accordion);
        }


    }

    public void rendreQuestionAreponseLibre() {

        griddisplay.getChildren().clear();
        pandetails.getChildren().remove(tabledetails);
        questionLabel.setText("Test :");
        //displaypane.getChildren().add(0,griddisplay);
        buttonexercice.setStyle("-fx-background-color: #ffffff");
        buttonaname.setStyle("-fx-background-color: #ffffff");
        buttonqcm.setStyle("-fx-background-color: #ffffff");
        buttonqcu.setStyle("-fx-background-color: #ffffff");
        buttonquestion.setStyle("-fx-background-color: #ff0000");

        int i= 0 ;
        for (Test t : listDesEpreuvesCliniques) {
            if (t instanceof TestQuestionArepLibre) {

                System.out.println("le nom de test "+t.toString());
                Label questionLabel = new Label();
                // Create a TitledPane with some content
                Button detailsButton = new Button("Show Details");
                Button ajouteQ = new Button("ajouter ");
                Label label = new Label(t.getNom());
                questionLabel.setText("Test Question a reponse libre : " + i);
                System.out.println("le nom de test "+t.getNom());
                griddisplay.add(questionLabel, 0, i);
                griddisplay.add(label, 1, i);
                griddisplay.add(detailsButton, 2, i);
                griddisplay.add(ajouteQ, 3, i);
                detailsButton.setOnAction(e -> {
                    handlShowDetailsTestQLibr((TestQuestionnaire) t);
                });
                ajouteQ.setOnAction(event -> {
                    ajouterQrepLibre((TestQuestionArepLibre) t);
                });

                i++;
            }

        }

    }

    private void ajouterQrepLibre(TestQuestionArepLibre t) {
        Dialog<QesrepLibre> dialog = new Dialog<>() ;
        dialog.setWidth(400);
        dialog.setHeight(400);
        TextField enonceField = new TextField() ;
        enonceField.setPromptText("L'enonce de question ...");
        TextField reponseField = new TextField() ;
        reponseField.setPromptText("La reponse sur la question ...");
         VBox vBox = new VBox() ;
        vBox.getChildren().addAll(new Label("Enonce : "),enonceField,new Label("Reponse : "),reponseField) ;
        vBox.setSpacing(10);

        dialog.getDialogPane().setContent(vBox);
        ButtonType applyButtonType = new ButtonType("Apply", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(applyButtonType, cancelButtonType);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == applyButtonType) {
                QesrepLibre q = new QesrepLibre(enonceField.getText(),reponseField.getText());
                t.ajouterQuestion(q);
                return q;
            }
            return null;
        });
        dialog.showAndWait();


    }

    private void handlShowDetailsTestQLibr(TestQuestionnaire t) {
        VBox vBox = new VBox();
        pandetails.getChildren().add(vBox);
        questionLabel.setText("Test : " + t.getNom());

        ObservableList<String> questions = FXCollections.observableArrayList();


        for (Question2 question : t.getQuestions()) {
            System.out.println("nombre des question :" + t.getQuestions().size());
            Accordion accordion = new Accordion();
            accordion.setPrefWidth(437);


            QesrepLibre q = (QesrepLibre) question;
            // Créer un TitledPane pour chaque question
            TitledPane titledPane = new TitledPane();
            titledPane.setText("Énoncé ");

            // Créer le contenu du TitledPane
            VBox content = new VBox();
            content.getChildren().add(new Label(q.getEnonce()));
            titledPane.setContent(content);
            accordion.getPanes().add(titledPane);

            String reponse = q.getReponse();
            VBox contentRep = new VBox();
            TitledPane titledPaneRep = new TitledPane();
            titledPaneRep.setText(" La reponse : ");
            contentRep.getChildren().add(new Label(reponse));
            titledPaneRep.setContent(contentRep);
            accordion.getPanes().add(titledPaneRep);

            //content.getChildren().add(new Label("Réponses justes: " + String.join(", ", q.getReponses())));

            // Ajouter le TitledPane à l'Accordion
            //   accordion.getPanes().add(titledPane);
            vBox.getChildren().add(accordion);
        }

    }

    public void rendreExercice (){

        griddisplay.getChildren().clear();
        pandetails.getChildren().remove(tabledetails);
        questionLabel.setText("Test :");
        //displaypane.getChildren().add(0,griddisplay);
        buttonaname.setStyle("-fx-background-color: #ffffff");
        buttonqcm.setStyle("-fx-background-color: #ffffff");
        buttonqcu.setStyle("-fx-background-color: #ffffff");
        buttonquestion.setStyle("-fx-background-color: #ffffff");
        buttonexercice.setStyle("-fx-background-color: #ff0000");

        int i= 0 ;
        for (Test t : listDesEpreuvesCliniques) {
            if (t instanceof TestExercice) {

                System.out.println("le nom de test "+t.toString());
                Label questionLabel = new Label();
                // Create a TitledPane with some content
                Button detailsButton = new Button("Show Details");
                Button ajouterExo = new Button("ajouter ");
                Label label = new Label(t.getNom());
                questionLabel.setText("Exercice: " + i);
                System.out.println("le nom de test "+t.getNom());
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



    private void showDetailsExo(TestExercice t) {
        VBox vBox = new VBox();
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
                Exercice q ;
                if(!nomMaterielField.getText().isEmpty()){
                 q = new Exercice(nomExoField.getText(),consigneField.getText(),nomMaterielField.getText());}
                else{
                     q = new Exercice(nomExoField.getText(),consigneField.getText());
                }
                t.ajouterExercice(q);
                return q;
            }
            return null;
        });
        dialog.showAndWait();
    }
}




