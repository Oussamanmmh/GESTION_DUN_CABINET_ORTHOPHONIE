package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class Qcu extends Question {
    // une seule reponse possible
    private List<String> propositions = new ArrayList<>(); // les propositions de la question
    private String reponse; // la reponse correcte
    private String reponsePatient; // la reponse du patient

    public Qcu(String enonce, List<String> propositions, String reponse) {
        super(enonce);
        this.propositions.addAll(propositions);
        this.reponse = reponse;
    }

    public List<String> getPropositions() {
        return propositions;
    }

    public String getReponse() {
        return reponse;
    }

    public String getReponsePatient() {
        return reponsePatient;
    }

    public void setReponsePatient(String reponsePatient) {
        this.reponsePatient = reponsePatient;
    }
    public int getNote() {
        if (reponse != null && reponse.equals(reponsePatient)) {
            return score;
        } else {
            return 0;
        }
    }

}
