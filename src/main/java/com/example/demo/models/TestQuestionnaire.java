package com.example.demo.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public  class TestQuestionnaire extends Test {
    protected Set<? extends Question> questions;

    public TestQuestionnaire(String nom,String capacite,List<Question> questions) {
        super(nom, capacite);
        this.questions=new HashSet<>(questions);

    }

    public double calculerScore() {
        int score = 0;
        for (Question question : questions) {
            score += question.getScore();
        }
        return score;
    }

    @Override
    public List<Question> getQuestions() {
        return new ArrayList<>(this.questions);
    }

}
