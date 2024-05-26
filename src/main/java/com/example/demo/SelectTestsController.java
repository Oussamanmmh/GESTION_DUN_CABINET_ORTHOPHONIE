package com.example.demo;

import com.example.demo.models.*;
import com.example.demo.models.Test;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.*;

public class SelectTestsController {

    @FXML
    private Button validerButton;

    @FXML
     SplitMenuButton anamneseMenuButton;
    @FXML
     SplitMenuButton questionnaireMenuButton;
    @FXML
     SplitMenuButton exerciceMenuButton;

    // Map to store CheckBox associated with each Test
    private Map<Test, CheckBox> testCheckBoxes = new HashMap<>();
    private Map<Anamnese , CheckBox> anamneseCheckBoxes = new HashMap<>();

    private BilanOrt bilanOrt;
    private DossierPatient dossierPatientCourant;

    public void setBilanOrt(BilanOrt bilanOrt) {
        this.bilanOrt = bilanOrt;
    }

    public void initialize(DossierPatient dossierPatientCourant) {
        this.dossierPatientCourant = dossierPatientCourant;

        if(dossierPatientCourant.getBilanOrt().isEmpty()) {
            System.out.println("premier rendez-vous");
            loadQuestionAnamneses();
        }
        loadQuestionnaireTests();
        loadExerciceTests();

        // Set action for validerButton
        validerButton.setOnAction(event -> validerSelection());
    }


    private void rendrePageSuivante() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("qcm.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            qcmController controller = fxmlLoader.getController();
            controller.initialize(dossierPatientCourant);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch ( IOException e) {
            e.printStackTrace();
        }
    }

    private void loadQuestionAnamneses() {
        List<Anamnese> anamneses = HelloApplication.anamnese;
        for (Anamnese anamnese : anamneses) {
            CheckBox checkBox = new CheckBox(anamnese.getType());
            checkBox.setStyle("-fx-text-fill: #2c3e50;");
            CustomMenuItem menuItem = new CustomMenuItem(checkBox);
            menuItem.setHideOnClick(false);
            anamneseMenuButton.getItems().add(menuItem);
            anamneseCheckBoxes .put(anamnese, checkBox);
        }

    }


    private void loadQuestionnaireTests() {
        List<TestQuestionnaire> questionnaireTests = getQuestionnaireTests(HelloApplication.epreuveClinique.getTests());
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
        List<TestExercice> exerciceTests = getExerciceTests(HelloApplication.epreuveClinique.getTests());
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

    private List<TestExercice> getExerciceTests(List<Test> tests) {
        //return List.of(new TestExercice("Exercice 1", "capacite 1"), new TestExercice("Exercice 2", "capacite 2"));
        List<TestExercice> testExercices=new ArrayList<>();
        for (Test test:tests){

            if(test instanceof TestExercice){
                testExercices.add((TestExercice) test);
            }
        }
        return testExercices;
    }

    private void validerSelection() {
        List<Test> selectedTests = getSelectedTests();
        List <Anamnese> selectedAnamneses = getSelectedAnamneses();
        if (selectedTests.isEmpty() && selectedAnamneses.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner au moins un test.");
            alert.showAndWait();
        } else {
            EpreuveClinique epreuveClinique = new EpreuveClinique();
            selectedTests.forEach(epreuveClinique::ajouterTest);
           Anamnese anamnese = selectedAnamneses.get(0);
            BilanOrt bilanOrt = new BilanOrt(anamnese, List.of(epreuveClinique));
            dossierPatientCourant.ajouterBilan(bilanOrt);
            rendrePageSuivante();

            // Continue with the selected tests
            System.out.println("Tests sélectionnés : " + selectedTests + selectedAnamneses);
        }
    }

    private List<Anamnese> getSelectedAnamneses() {
        List<Anamnese> selectedAnamneses = new ArrayList<>();
        for (CustomMenuItem item : anamneseMenuButton.getItems().stream().map(e -> (CustomMenuItem)e).toList()) {
            CheckBox checkBox = (CheckBox) item.getContent();
            if (checkBox.isSelected()) {
                selectedAnamneses.add(getAnamneseByCheckBox(checkBox));
            }
        }
        return selectedAnamneses;
    }

    private Anamnese getAnamneseByCheckBox(CheckBox checkBox) {
        return anamneseCheckBoxes.entrySet().stream()
                .filter(entry -> entry.getValue().equals(checkBox))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
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
