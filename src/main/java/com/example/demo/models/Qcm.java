package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class Qcm extends Question2{
    // plusieurs reponses possible
    private final String type = "QCM" ;
    private List<String> propositions ;//les propositions de la question
    private List<String> reponses ;// les reponses correctes
    private List<String> reponsesPatient ;// les reponses du patient
    public Qcm (String enonce , List<String> propositions ){
        super(enonce);
        this.propositions =  new ArrayList<>(propositions);
    }
    public String getType() {
        return type;
    }

    public Qcm(String enonce, List<String> propositions, List<String> reponses) {
        super(enonce);
        this.propositions = propositions;
        this.reponses = reponses;
    }


    public String getEnonce() {
        return enonce;
    }

    public List<String> getPropositions() {
        return propositions;
    }
    public List<String> getReponses() {
        return reponses;
    }
}
