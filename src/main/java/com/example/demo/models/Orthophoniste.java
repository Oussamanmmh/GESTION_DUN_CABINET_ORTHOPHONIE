package com.example.demo.models;

public class Orthophoniste {
    private String nom ;
    private String prenom ;
    private String email ;
    private String mdp ;
    private String adr ;
    private String numeroTel ;


    // constructeur
    public Orthophoniste(String nom , String prenom ,String email , String adr , String numeroTel , String mdp)
    {
        this.nom = nom ;
        this.prenom = prenom ;
        this.email = email ;
        this.adr = adr ;
        this.numeroTel = numeroTel ;
        this.mdp = mdp ;
    }
    public String getEmail()
    {
        return email ;
    }

    public String getMdp() {
        return mdp;
    }
}
