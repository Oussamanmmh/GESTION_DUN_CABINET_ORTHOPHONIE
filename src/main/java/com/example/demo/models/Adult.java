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
    public String getAdress()
    {
        return this.adress ;
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

    @Override
    public void setDateNaissance(Date dateNaissance) {
        super.setDateNaissance(dateNaissance);
    }

    @Override
    public void setAdress(String adress) {
        super.setAdress(adress);
    }
    public void setDiplome(String diplome)
    {
        this.diplome = diplome ;
    }


    public void setProfession(String profession)
    {
        this.profession = profession ;
    }
    public void setNumTel(String numTel)
    {
        this.numTel = numTel ;
    }



    public String getDiplome() {
        return diplome;
    }
}
