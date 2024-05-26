package com.example.demo.models;

import java.util.List;

public abstract class Test { //la note de test comprise entre 1 et 10
    protected String nom ;
   protected String capacite ;
    protected String observation ; // conclusion redige n

    public abstract double calculerScore();

    public String getObservation() {
        return observation;
    }
    public void setObservation(String observation) {
        this.observation = observation;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Test(String nom) {
        this.nom = nom;
    }
    public abstract String getNom();
    public abstract List<Question2> getQuestions();
    public abstract void  ajouterQuestion(Question2 question);

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

    public String getCapacite() {
        return capacite;
    }
}
