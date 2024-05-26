package com.example.demo.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestQCU extends TestQuestionnaire{

    public TestQCU( String nom , List<Qcu> questions) {
        //affecter les questions a la liste des questions
        super(nom, questions);

    }

    @Override
    public String getNom() {
        return nom;
    }
}
