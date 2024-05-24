package com.example.demo.models;

import java.util.Date;
import java.util.List;

public abstract class Patient {
    protected String nom;
    protected String prenom;
    protected Date dateNaissance;
    protected String lieuNaissance;
    protected String adresse;
    protected List<Trouble> troubles;
    protected String descriptionTherapie; // description de la thérapie

    public Patient(String nom, String prenom, Date dateNaissance, String lieuNaissance, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.adresse = adresse;
    }

    // Getters
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

    public String getAdresse() {
        return adresse;
    }

    public List<Trouble> getTroubles() {
        return troubles;
    }

    public String getDescriptionTherapie() {
        return descriptionTherapie;
    }

    // Setters
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

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTroubles(List<Trouble> troubles) {
        this.troubles = troubles;
    }

    public void setDescriptionTherapie(String descriptionTherapie) {
        this.descriptionTherapie = descriptionTherapie;
    }
}


//public List<BilanOrt> getBilansOrt() {
    //    return bilansOrt;
    //}

    //public void setBilansOrt(List<BilanOrt> bilansOrt) {
    //    this.bilansOrt = bilansOrt;
    //}


