package com.example.demo.models;

public class Exercice   {

    // class d'un exercice
    private String nomExercice;
    private String consigne;

    private String nomMateriel;

    private int score;

    public String getNom()
    {
        return this.nomExercice;
    }

    public boolean equales (Exercice e)
    {
        return this.nomExercice.equals(e.getNom());
    }

    public int getScore()
    {
        return score ;
    }
}
