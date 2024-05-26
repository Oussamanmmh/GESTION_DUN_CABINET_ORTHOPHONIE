package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class Exercice   {

    // class d'un exercice
    private String nomExercice;
    private String consigne;

    private String nomMateriel;
   private List<Integer> notes = new ArrayList<>();
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
    public void setConsigne(String consigne){
        this.consigne = consigne;
    }
    public void ajouterNote (int note)

    {

        notes.add(note);
    }
    public List<Integer> getNotes()
    {
        return notes;
    }

}
