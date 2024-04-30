package com.example.demo.models;

public abstract class Test { //la note de test comprise entre 1 et 10
    private String nom ;
    private String capacite ;
    protected String observation ;

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

}
