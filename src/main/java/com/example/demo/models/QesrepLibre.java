package com.example.demo.models;

public class QesrepLibre extends Question {
    private String reponse; // la reponse correcte (si applicable)
    private String reponsePatient; // la reponse du patient

    public QesrepLibre(String enonce) {
        super(enonce);
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
