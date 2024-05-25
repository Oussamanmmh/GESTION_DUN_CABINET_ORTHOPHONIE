package com.example.demo.models;

import java.util.Set;

public class TestQCM extends TestQuestionnaire{

    public TestQCM(  String nom  , Set<Qcm> questions ) {
        super(nom, questions) ;
       // super.questions = new HashSet<Qcm>(questions);



    }

    public String getNom() {
        return nom;
    }

    @Override
    public void ajouterQuestion(Question2 question) {
    questions.add( question);

    }
}
