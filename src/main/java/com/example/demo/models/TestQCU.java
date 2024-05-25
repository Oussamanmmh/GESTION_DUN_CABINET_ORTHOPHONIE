package com.example.demo.models;

import java.util.HashSet;
import java.util.Set;

public class TestQCU extends TestQuestionnaire{

    public TestQCU( String nom , Set<Qcu> questions) {
        //affecter les questions a la liste des questions
        super(nom, questions);

    }

    @Override
    public String getNom() {
        return nom;
    }
}
