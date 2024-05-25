package com.example.demo.models;
import java.util.ArrayList;
import  java.util.List;


public class AnamneseEnfant extends Anamnese {

    private List<Question> questionsEnfant = new ArrayList<>(); // liste des questions anamnese enfant des questions à réponse libre
 
    public AnamneseEnfant() {
        // Constructeur par défaut
    }

    // Getter pour questionsEnfant
    public List<Question> getQuestionsEnfant() {
        return questionsEnfant;
    }

    // Setter pour questionsEnfant
    public void setQuestionsEnfant(List<Question> questionsEnfant) {
        this.questionsEnfant = questionsEnfant;
    }

    // Ajouter une question à la liste
    public void addQuestionEnfant(Question question) {
        this.questionsEnfant.add(question);
    }

    // Supprimer une question de la liste
    public void removeQuestionEnfant(Question question) {
        this.questionsEnfant.remove(question);
    }
}
