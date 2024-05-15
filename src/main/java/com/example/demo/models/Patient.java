package com.example.demo.models;

import java.util.Date;
import java.util.List;

public abstract class Patient {
    protected String nom;
    protected String prenom;

    protected Date dateNaissance;

    protected String lieuNaissance;
    protected String adress;

    protected List<Trouble> troubles;

    protected  String DecriptionTherapie; //description de la therapie

    //protected List<BilanOrt> bilansOrt; //liste des bilans orthophoniques sur le patient

   public Patient(String nom, String prenom, Date dateNaissance, String lieuNaissance, String adress) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.adress = adress;
    }


    //public List<BilanOrt> getBilansOrt() {
    //    return bilansOrt;
    //}

    //public void setBilansOrt(List<BilanOrt> bilansOrt) {
    //    this.bilansOrt = bilansOrt;
    //}






}
