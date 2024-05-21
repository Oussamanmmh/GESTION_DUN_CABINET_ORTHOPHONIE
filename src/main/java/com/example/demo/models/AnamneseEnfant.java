package com.example.demo.models;
import java.util.HashSet;
import  java.util.List;
import java.util.Set;

public class AnamneseEnfant extends  Anamnese{

    private Set<QuestionAnamEnfant> questionsEnfant; // liste des questions anamnese enfant des questions a reponse libre

    public Set <QuestionAnamEnfant> getQuestionsEnfant() {
        return questionsEnfant;
    }

    public AnamneseEnfant(){
        this.questionsEnfant = new HashSet<>();
    }

}
