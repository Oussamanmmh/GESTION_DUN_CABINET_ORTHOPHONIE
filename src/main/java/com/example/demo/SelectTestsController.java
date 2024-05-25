package com.example.demo;

import com.example.demo.models.*;
import com.example.demo.models.Test;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectTestsController {

    @FXML
    private Button validerButton;

    @FXML
    private SplitMenuButton anamneseMenuButton;
    @FXML
    private SplitMenuButton questionnaireMenuButton;
    @FXML
    private SplitMenuButton exerciceMenuButton;

    // Map to store CheckBox associated with each Test
    private Map<Test, CheckBox> testCheckBoxes = new HashMap<>();

    private BilanOrt bilanOrt;

    public void setBilanOrt(BilanOrt bilanOrt) {
        this.bilanOrt = bilanOrt;
    }

    public void initialize() {
        // Initialize the menu buttons with test data
        loadQuestionnaireTests();
        loadExerciceTests();

        // Set action for validerButton
        validerButton.setOnAction(event -> validerSelection());
    }



    private void loadQuestionnaireTests() {
        List<TestQuestionnaire> questionnaireTests = getQuestionnaireTests(List.of());
        for (TestQuestionnaire test : questionnaireTests) {
            CheckBox checkBox = new CheckBox(test.getNom());
            checkBox.setStyle("-fx-text-fill: #2c3e50;");
            CustomMenuItem menuItem = new CustomMenuItem(checkBox);
            menuItem.setHideOnClick(false);
            questionnaireMenuButton.getItems().add(menuItem);
            testCheckBoxes.put(test, checkBox);
        }
    }

    private void loadExerciceTests() {
        List<TestExercice> exerciceTests = getExerciceTests();
        for (TestExercice test : exerciceTests) {
            CheckBox checkBox = new CheckBox(test.getNom());
            checkBox.setStyle("-fx-text-fill: #2c3e50;");
            CustomMenuItem menuItem = new CustomMenuItem(checkBox);
            menuItem.setHideOnClick(false);
            exerciceMenuButton.getItems().add(menuItem);
            testCheckBoxes.put(test, checkBox);
        }
    }

    private List<TestQuestionnaire> getQuestionnaireTests(List<Test> tests) {
       // return List.of(new TestQuestionnaire("Questionnaire 1", "capacite 1"), new TestQuestionnaire("Questionnaire 2", "capacite 2"));

        List<TestQuestionnaire> testQuestionnaires=new ArrayList<>();
        for (Test test:tests){

            if(test instanceof TestQuestionnaire){
                testQuestionnaires.add((TestQuestionnaire) test);
            }
        }
        return testQuestionnaires;

    }

    private List<TestExercice> getExerciceTests() {
        return List.of(new TestExercice("Exercice 1", "capacite 1"), new TestExercice("Exercice 2", "capacite 2"));
    }

    private void validerSelection() {
        List<Test> selectedTests = getSelectedTests();
        if (selectedTests.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner au moins un test.");
            alert.showAndWait();
        } else {
            // Continue with the selected tests
            System.out.println("Tests sélectionnés : " + selectedTests);
        }
    }

    private List<Test> getSelectedTests() {
        List<Test> selectedTests = new ArrayList<>();
        selectedTests.addAll(getSelectedTestsFromMenu(questionnaireMenuButton));
        selectedTests.addAll(getSelectedTestsFromMenu(exerciceMenuButton));
        return selectedTests;
    }

    private List<Test> getSelectedTestsFromMenu(SplitMenuButton menuButton) {
        List<Test> selectedTests = new ArrayList<>();
        for (CustomMenuItem item : menuButton.getItems().stream().map(e -> (CustomMenuItem)e).toList()) {
            CheckBox checkBox = (CheckBox) item.getContent();
            if (checkBox.isSelected()) {
                selectedTests.add(getTestByCheckBox(checkBox));
            }
        }
        return selectedTests;
    }

    private Test getTestByCheckBox(CheckBox checkBox) {
        return testCheckBoxes.entrySet().stream()
                .filter(entry -> entry.getValue().equals(checkBox))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }
}
