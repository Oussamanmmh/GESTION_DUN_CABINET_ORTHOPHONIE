package com.example.demo.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Orthophoniste {
    private String nom ;
    private String prenom ;
    private String email ;
    private String mdp ;
    private String adr ;
    private String numeroTel ;

   private List<DossierPatient> dossierPatientList ;

    // constructeur
    public Orthophoniste(String nom , String prenom ,String email , String adr , String numeroTel , String mdp)
    {
        this.nom = nom ;
        this.prenom = prenom ;
        this.email = email ;
        this.adr = adr ;
        this.numeroTel = numeroTel ;
        this.mdp = mdp ;
     dossierPatientList = new ArrayList<>() ;
    }
    public String getEmail()
    {
        return email ;
    }

    public String getMdp() {
        return mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdr() {
        return adr;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void ajouterDossierPatient(DossierPatient dossier)
    {
        this.dossierPatientList.add(dossier) ;
    }

    public List<DossierPatient> getDossierPatientList()
    {
        return this.dossierPatientList ;
    }


}
