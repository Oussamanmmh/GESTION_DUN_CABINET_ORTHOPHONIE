package com.example.demo.models;

import java.util.List;

public class TestExercice extends Test {
    private List<Exercice> exercices;


    public TestExercice(String nom, String capacite) {
        super(nom, capacite);
    }

    @Override
    public double calculerScore() {
        int somme=0;
       for(Exercice exercice:exercices){

           somme+= exercice.calculerScore();
       }
       return somme;
    }

    public List<Exercice> getExercices() {
        return exercices;
    }

    public void setExercices(List<Exercice> exercices) {
        this.exercices = exercices;
    }

    @Override
    public List<Question> getQuestions() {
        return List.of();
    }
}
