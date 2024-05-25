package com.example.demo.models;

import java.util.Date;

public class Adult extends Patient {
    private String diplome ;
    private String profession ;
    private String numTel ;
    public Adult (String nom , String prenom , String adress , Date dateDeNaissance , String lieuDeNaissance , String diplome , String profession , String numTel)
    {
        super(nom , prenom , dateDeNaissance , lieuDeNaissance , adress);
        this.diplome = diplome ;
        this.profession = profession ;
        this.numTel = numTel ;
    }
    public String getNom()
 {
     return this.nom ;
 }
    public String getPrenom()
    {
        return this.prenom ;
    }
    public String getAdresse()
    {
        return this.adresse ;
    }

    public String getProfession()
    {
        return this.profession ;
    }
    public String getNumTel()
    {
        return this.numTel ;
    }
    public String getLieuNaissance()
    {
        return this.lieuNaissance ;
    }
    public Date getDateNaissance()
    {
        return this.dateNaissance ;
    }


    public String getDiplome() {
        return diplome;
    }

    public String getType(){return "Adult";}

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }
}

