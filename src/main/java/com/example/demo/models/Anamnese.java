package com.example.demo.models;

import javafx.scene.Node;

import java.util.List;

public abstract class Anamnese {
    protected List<? extends Question> questions;

    public List<? extends Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<? extends Question> questions) {
        this.questions = questions;
    }

    public abstract String getType() ;



}
