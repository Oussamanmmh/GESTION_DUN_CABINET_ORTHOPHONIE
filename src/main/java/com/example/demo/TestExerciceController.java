package com.example.demo;

import com.example.demo.models.BilanOrt;
import com.example.demo.models.Exercice;
import com.example.demo.models.TestExercice;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestExerciceController {

    @FXML
    private VBox exerciceContainer;
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
    @FXML
    private Button diagnostiqueButton;

    private List<TestExercice> tests;
    private int currentTestIndex = 0;

    // Map pour stocker les Sliders associées à chaque Exercice
    private Map<Exercice, Slider> exerciceSliders = new HashMap<>();

    private BilanOrt bilanOrt;

    public void setBilanOrt(BilanOrt bilanOrt) {
        this.bilanOrt = bilanOrt;
    }

    @FXML
    public void initialize() {
        // Simuler la récupération des tests depuis une source de données
        tests = getTestExerciceList();

        // Afficher le premier test
        displayTest(tests.get(currentTestIndex));

        // Ajouter un gestionnaire d'événements pour les boutons
        repondreButton.setOnAction(event -> handleRepondreButton(tests.get(currentTestIndex).getExercices()));
        precedentButton.setOnAction(event -> showPreviousTest());
        suivantButton.setOnAction(event -> showNextTest());
        diagnostiqueButton.setOnAction(event -> handlediagnostiqueButton());
    }

    private List<TestExercice> getTestExerciceList() {
        // Exemple de tests pour démonstration
        TestExercice test1 = new TestExercice("Test Exercice 1", "Capacité 1");
        test1.setExercices(List.of(
                new Exercice("Exercice 1", "Consigne 1", "Materiel 1", 0),
                new Exercice("Exercice 2", "Consigne 2", "Materiel 2", 0)
        ));

        TestExercice test2 = new TestExercice("Test Exercice 2", "Capacité 2");
        test2.setExercices(List.of(
                new Exercice("Exercice 3", "Consigne 3", "Materiel 3", 0),
                new Exercice("Exercice 4", "Consigne 4", "Materiel 4", 0)
        ));

        return List.of(test1, test2);
    }

    private void displayTest(TestExercice test) {
        nomTestLabel.setText(test.getNom());
        testIndexLabel.setText("Exercice " + (currentTestIndex + 1) + "/" + tests.size());
        exerciceContainer.getChildren().clear();
        displayExercices(test.getExercices());
    }

    private void displayExercices(List<Exercice> exercices) {
        for (Exercice exercice : exercices) {
            Label consigneLabel = new Label(exercice.getConsigne());
            consigneLabel.setStyle("-fx-text-fill: #4682b4; -fx-font-size: 16px; -fx-font-weight: bold;");
            exerciceContainer.getChildren().add(consigneLabel);

            Slider slider = new Slider(0, 10, 0);
            slider.setShowTickLabels(true);
            slider.setShowTickMarks(true);
            slider.setMajorTickUnit(1);
            slider.setBlockIncrement(1);
            exerciceContainer.getChildren().add(slider);
            exerciceSliders.put(exercice, slider);

            Button refaireButton = new Button("Refaire l'exercice");
            refaireButton.setStyle("-fx-background-color: #56c6d0; -fx-text-fill: white; -fx-font-weight: bold;");
            refaireButton.setOnAction(event -> handleRefaireButton(exercice, slider));
            exerciceContainer.getChildren().add(refaireButton);
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

    private void handleRepondreButton(List<Exercice> exercices) {
        for (Exercice exercice : exercices) {
            Slider slider = exerciceSliders.get(exercice);
            int score = (int) slider.getValue();
            exercice.ajouterNote(score);
            System.out.println("Note ajoutée pour" + exercice.getNom() + " : " + score);
        }
        System.out.println("Notes enregistrés pour le test " + tests.get(currentTestIndex).getNom());
    }

    private void handleRefaireButton(Exercice exercice, Slider slider) {
        int score = (int) slider.getValue();
        exercice.ajouterNote(score);
        System.out.println("Note ajoutée pour " + exercice.getNom() + " : " + score);
    }

    private void handlediagnostiqueButton(){
        boolean hasError=false;
        for(TestExercice test :tests){

            for(Exercice exercice: test.getExercices()){
                if(exercice.getNotes().isEmpty()){
                   hasError=true;
                   break;
                }
                if (hasError)
                    break;
            }
            if (hasError) {
                // Afficher une boîte de dialogue d'erreur à l'utilisateur
                Alert alert = new Alert(Alert.AlertType.ERROR, "Certaines exercices n'ont pas été notés !");
                alert.showAndWait();
                System.out.println("Certaines exercices n'ont pas été notés !!");
            } else {
                System.out.println("Toutes les exercices ont des notes !");
                //passer à la page du diagnostique
            }
        }
    }
}
