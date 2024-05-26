package com.example.demo.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public  class TestQuestionnaire extends Test {
    protected List<Question2> questions;

    public TestQuestionnaire (String nom , List<? extends Question2> questions) {
        super(nom);
        this.questions = new ArrayList<>(questions);
    }

    public List<Question2> getQuestions() {
        return questions;
    }


    public double calculerScore() {
        int score = 0;
        for (Question2 question : questions) {
            score += question.getScore();
        }
        return score;
    }

    @Override
    public String getNom() {
        return nom;
    }

    public void ajouterQuestion(Question2 question) {
        questions.add(question);
    }

    //redefini la fonction qui return le hashcode


}
