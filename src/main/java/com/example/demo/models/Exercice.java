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
    public Exercice(String nomExercice , String consigne ){
        this.nomExercice = nomExercice;
        this.consigne = consigne;
    }
    public Exercice(String nomExercice , String consigne , String nomMateriel){
        this.nomExercice = nomExercice;
        this.consigne = consigne;
        this.nomMateriel = nomMateriel;

    }
    public void setNomMateriel(String nom){
        this.nomMateriel = nom;
    }
    public String getNomMateriel(){
        return this.nomMateriel;
    }
    public String getConsigne(){
        return this.consigne;
    }

}
