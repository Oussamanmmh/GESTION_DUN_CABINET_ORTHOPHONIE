package com.example.demo.models;

import java.util.HashSet;
import java.util.Set;

public  class TestQuestionnaire extends Test {
    protected Set<Question2> questions;

    public TestQuestionnaire (String nom , Set<? extends Question2> questions) {
        super(nom);
        this.questions = new HashSet<>(questions)  ;
    }

    public Set<Question2> getQuestions() {
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
