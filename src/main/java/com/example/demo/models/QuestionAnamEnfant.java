package com.example.demo.models;

public class QuestionAnamEnfant extends Question {
    private String enonce;

    private CathegoriesEnf cathegorie;

    public QuestionAnamEnfant(String enonce, CathegoriesEnf cathegoriesEnf) {
        this.enonce = enonce;

        this.cathegorie = cathegoriesEnf;
    }

    public String getEnonce() {
        return enonce;
    }


    public CathegoriesEnf getCathegorie(){
        return this.cathegorie ;
    }
}
