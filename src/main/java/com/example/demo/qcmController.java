package com.example.demo;

import com.example.demo.models.*;
import com.example.demo.models.Test;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.*;

public class qcmController {

    @FXML
    private VBox questionContainer;
    @FXML
    private Button repondreButton;
    @FXML
    private Button precedentButton;
    @FXML
    private Button suivantButton;
    @FXML
    private Label nomTestLabel;
    @FXML
    private Label testIndexLabel;

    // Map pour stocker les CheckBox associées à chaque question
    private Map<Question, List<CheckBox>> questionCheckBoxes = new HashMap<>();
    // Map pour stocker les RadioButton associées à chaque question QCU
    private Map<Question, ToggleGroup> questionRadioButtons = new HashMap<>();
    // Map pour stocker les TextField associées à chaque question libre
    private Map<Question, TextField> questionTextFields = new HashMap<>();

    private List<Test> tests = new ArrayList<>();
    private int currentTestIndex = 0;

    private BilanOrt bilanOrt;

    public void setBilanOrt(BilanOrt bilanOrt) {
        this.bilanOrt = bilanOrt;
    }

    @FXML
    public void initialize() {
        // Simuler la récupération des tests depuis une source de données
        tests = getTestList();

        // Afficher le premier test
        displayTest(tests.get(currentTestIndex));

        // Ajouter un gestionnaire d'événements pour les boutons
        repondreButton.setOnAction(event -> handleRepondreButton(tests.get(currentTestIndex).getQuestions()));
        precedentButton.setOnAction(event -> showPreviousTest());
        suivantButton.setOnAction(event -> showNextTest());
    }

    private List<Test> getTestList() {
        // Exemple de tests pour démonstration
        Test test1 = new TestQuestionnaire("Test 1", "capacite 1", getQcmList());
        Test test2 = new TestQuestionnaire("Test 2", "capacite 1", getQcmList());
        return Arrays.asList(test1, test2);
    }

    private List<Question> getQcmList() {
        // Exemple de questions QCM, QCU et libres statiques pour démonstration
        Qcm qcm1 = new Qcm("Question à choix multiple 1:",
                Arrays.asList("Réponse une", "Réponse deux", "Réponse trois"),
                Arrays.asList("Réponse une", "Réponse deux"));

        Qcu qcu1 = new Qcu("Question à choix unique 1:",
                Arrays.asList("Réponse A", "Réponse B", "Réponse C"),
                "Réponse B");

        QesrepLibre ql1 = new QesrepLibre("Question libre 1:");

        return Arrays.asList(qcm1, qcu1, ql1);
    }

    private void displayTest(Test test) {
        nomTestLabel.setText(test.getNom());
        testIndexLabel.setText("Test " + (currentTestIndex + 1) + "/" + tests.size());
        questionContainer.getChildren().clear();
        displayQuestions(test.getQuestions());
    }

    private void displayQuestions(List<Question> questions) {
        for (Question question : questions) {
            Label questionLabel = new Label(question.getEnonce());
            questionLabel.setStyle("-fx-text-fill: #4682b4; -fx-font-size: 18px; -fx-font-weight: bold;");
            questionContainer.getChildren().add(questionLabel);

            if (question instanceof Qcm) {
                List<CheckBox> checkBoxes = new ArrayList<>();
                for (String proposition : ((Qcm) question).getPropositions()) {
                    CheckBox checkBox = new CheckBox(proposition);
                    checkBox.setStyle("-fx-text-fill: black; -fx-font-size: 14px;");
                    questionContainer.getChildren().add(checkBox);
                    checkBoxes.add(checkBox);
                }
                questionCheckBoxes.put(question, checkBoxes);
            } else if (question instanceof Qcu) {
                ToggleGroup toggleGroup = new ToggleGroup();
                for (String proposition : ((Qcu) question).getPropositions()) {
                    RadioButton radioButton = new RadioButton(proposition);
                    radioButton.setToggleGroup(toggleGroup);
                    radioButton.setStyle("-fx-text-fill: black; -fx-font-size: 14px;");
                    questionContainer.getChildren().add(radioButton);
                }
                questionRadioButtons.put(question, toggleGroup);
            } else if (question instanceof QesrepLibre) {
                TextField textField = new TextField();
                textField.setPromptText("Votre réponse...");
                textField.setStyle("-fx-font-size: 14px;");
                questionContainer.getChildren().add(textField);
                questionTextFields.put(question, textField);
            }
        }
    }

    private void showPreviousTest() {
        if (currentTestIndex > 0) {
            currentTestIndex--;
            displayTest(tests.get(currentTestIndex));
        }
    }

    private void showNextTest() {
        if (currentTestIndex < tests.size() - 1) {
            currentTestIndex++;
            displayTest(tests.get(currentTestIndex));
        }
    }

    private void handleRepondreButton(List<Question> questions) {
        boolean hasError = false;
        for (Question question : questions) {
            if (question instanceof Qcm) {
                List<CheckBox> checkBoxes = questionCheckBoxes.get(question);
                boolean isAnySelected = checkBoxes.stream().anyMatch(CheckBox::isSelected);

                if (!isAnySelected) {
                    hasError = true;
                    // Afficher un message d'erreur
                    System.out.println("Erreur: Aucune réponse sélectionnée pour la question: " + question.getEnonce());
                } else {
                    // Récupérer les réponses sélectionnées
                    List<String> selectedAnswers = new ArrayList<>();
                    for (CheckBox checkBox : checkBoxes) {
                        if (checkBox.isSelected()) {
                            selectedAnswers.add(checkBox.getText());
                        }
                    }
                    // Utiliser les réponses sélectionnées pour d'autres tests
                    System.out.println("Réponses sélectionnées pour la question: " + question.getEnonce() + " -> " + selectedAnswers);
                    ((Qcm) question).setReponsesPatient(selectedAnswers);
                }
            } else if (question instanceof Qcu) {
                ToggleGroup toggleGroup = questionRadioButtons.get(question);
                RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();

                if (selectedRadioButton == null) {
                    hasError = true;
                    // Afficher un message d'erreur
                    System.out.println("Erreur: Aucune réponse sélectionnée pour la question: " + question.getEnonce());
                } else {
                    String selectedAnswer = selectedRadioButton.getText();
                    // Utiliser la réponse sélectionnée pour d'autres tests
                    System.out.println("Réponse sélectionnée pour la question: " + question.getEnonce() + " -> " + selectedAnswer);
                    ((Qcu) question).setReponsePatient(selectedAnswer);
                }
            } else if (question instanceof QesrepLibre) {
                TextField textField = questionTextFields.get(question);
                String userAnswer = textField.getText().trim();

                if (userAnswer.isEmpty()) {
                    hasError = true;
                    // Afficher un message d'erreur
                    System.out.println("Erreur: Aucune réponse saisie pour la question: " + question.getEnonce());
                } else {
                    // Utiliser la réponse saisie pour d'autres tests
                    System.out.println("Réponse saisie pour la question: " + question.getEnonce() + " -> " + userAnswer);
                    ((QesrepLibre) question).setReponsePatient(userAnswer);
                }
            }
        }

        if (hasError) {
            // Afficher une boîte de dialogue d'erreur à l'utilisateur
            Alert alert = new Alert(Alert.AlertType.ERROR, "Certaines questions n'ont pas de réponses sélectionnées ou saisies !");
            alert.showAndWait();
            System.out.println("Certaines questions n'ont pas de réponses sélectionnées ou saisies !");
        } else {
            System.out.println("Toutes les questions ont des réponses sélectionnées ou saisies !");
        }
    }
}
