package com.example.demo.models;

public class QesrepLibre extends Question2{
    private String reponse ;


    public QesrepLibre(String enonce , String reponse) {
        super(enonce);
        this.reponse = reponse;

    }

public String getReponse() {
        return reponse;
    }

    @Override
    public String getEnonce() {
        return enonce;
    }
}
