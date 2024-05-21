package com.example.demo.models;

public class QuestionAnamAdult extends Question{
    private String enonce;

    private CathegorieAdult cathegorieAdult;

    public QuestionAnamAdult(String enonce, CathegorieAdult cathegorieAdult) {
        this.enonce = enonce;

        this.cathegorieAdult = cathegorieAdult;
    }

    public String getEnonce() {
        return enonce;
    }


    public CathegorieAdult getCathegorieAdult() {
        return cathegorieAdult;
    }
}
