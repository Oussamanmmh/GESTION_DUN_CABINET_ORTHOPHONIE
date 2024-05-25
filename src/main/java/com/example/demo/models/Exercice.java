package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class Exercice {

    private String nomExercice;
    private String consigne;
    private String nomMateriel;
    private int score;
    private List<Integer> notes = new ArrayList<>();

    public Exercice(String nomExercice, String consigne, String nomMateriel, int score) {
        this.nomExercice = nomExercice;
        this.consigne = consigne;
        this.nomMateriel = nomMateriel;
        this.score = score;
    }

    public void ajouterNote(int note) {
        if (note >= 0 && note <= 10) {
            this.notes.add(note);
        }
    }

    public int calculerScore() {
        int resultat = 0;
        int i = 0;
        for (Integer note : notes) {
            resultat += note;
            i++;
        }
        if (i == 0)
            return 0;
        else
            return resultat / i;
    }

    public String getNom() {
        return nomExercice;
    }

    public String getConsigne() {
        return consigne;
    }

    public String getNomMateriel() {
        return nomMateriel;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Integer> getNotes() {
        return notes;
    }
}
