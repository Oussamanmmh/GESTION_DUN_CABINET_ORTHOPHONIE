package com.example.demo.models;

import java.util.List;

public abstract class Test { //la note de test comprise entre 1 et 10
    private String nom ;
    private String capacite ;
    protected String observation ; // conclusion redige n


    public Test(String nom,String capacite){
        this.nom=nom;
        this.capacite=capacite;
    }
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
    public String getNom(){return this.nom;}

    public abstract List<Question> getQuestions() ;
}
