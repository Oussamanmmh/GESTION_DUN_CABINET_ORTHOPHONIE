package com.example.demo.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Patient {
    protected String nom;
    protected String prenom;
    protected int numeroDossier;
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
        troubles = new ArrayList<>() ;
    }


    //public List<BilanOrt> getBilansOrt() {
    //    return bilansOrt;
    //}

    //public void setBilansOrt(List<BilanOrt> bilansOrt) {
    //    this.bilansOrt = bilansOrt;
    //}

 public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public String getAdress() {
        return adress;
    }

    public List<Trouble> getTroubles() {
        return troubles;
    }

    public String getDecriptionTherapie() {
        return DecriptionTherapie;
    }

    public void setDecriptionTherapie(String decriptionTherapie) {
        DecriptionTherapie = decriptionTherapie;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setTroubles(List<Trouble> troubles) {
        this.troubles = troubles;
    }


    public void  ajouterTrouble (Trouble trouble)
    {
        this.troubles.add(trouble) ;
    }

    public int getNumeroDossier() {
        return numeroDossier;
    }

    public String getDescriptionTherapie() {
       return DecriptionTherapie;
    }
}
