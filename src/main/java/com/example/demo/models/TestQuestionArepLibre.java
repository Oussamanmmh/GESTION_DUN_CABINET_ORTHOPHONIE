package com.example.demo.models;

import java.util.Set;

public class TestQuestionArepLibre extends TestQuestionnaire{



    public TestQuestionArepLibre(String nom , Set<? extends Question2> questions) {
        super(nom , questions);

    }
    @Override
    public String getNom() {
        return nom;
    }
}
