package com.example.demo.models;
import javafx.scene.Node;

import java.util.ArrayList;
import  java.util.List;

public class AnamneseEnfant extends  Anamnese{

    private List<QuestionAnamEnfant> questions; // liste des questions anamnese enfant des questions a reponse libre

    public List <QuestionAnamEnfant> getQuestionsEnfant() {
        return questions;
    }

    private final String type = "Enfant";

    public String getType() {
        return type;
    }




    public List<QuestionAnamEnfant> getQuestions() {
        return questions;
    }
    public  AnamneseEnfant(List<QuestionAnamEnfant> list)
    {
        this.questions = new ArrayList<>(list);
    }
    public void addQuestion(QuestionAnamEnfant question){
        this.questions.add(question);
    }

}
