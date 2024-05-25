package com.example.demo.models;

import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

public class AnamneseAdult extends Anamnese {

    List<QuestionAnamAdult> questions; // liste des questions anamnese adulte des questions a reponse libre

    private final String type = "Adult";


    public String getType() {
        return type;
    }

    public List<QuestionAnamAdult> getQuestions() {
        return questions;
    }

    public AnamneseAdult(List<QuestionAnamAdult> list)
    {
        this.questions = new ArrayList<>(list);
    }

    public void addQuestion(QuestionAnamAdult question){
        this.questions.add( question);
    }
}
