package com.example.demo.models;

public class QuestionAnamEnfant extends Question {
    private String enonce;

    private CathegoriesEnf cathegoriesEnf;

    public QuestionAnamEnfant(String enonce, CathegoriesEnf cathegoriesEnf) {
        this.enonce = enonce;

        this.cathegoriesEnf = cathegoriesEnf;
    }

    public String getEnonce() {
        return enonce;
    }


    public CathegoriesEnf getCathegoriesEnf() {
        return cathegoriesEnf;
    }
}
