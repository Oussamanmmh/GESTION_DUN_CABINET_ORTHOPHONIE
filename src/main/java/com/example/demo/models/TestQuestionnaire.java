package com.example.demo.models;

import java.util.Set;

public abstract class TestQuestionnaire extends Test {
    protected Set<? extends Question> questions;

    public double calculerScore() {
        int score = 0;
        for (Question question : questions) {
            score += question.getScore();
        }
        return score;
    }

}
