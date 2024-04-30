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

    protected  String DecriptionTherapie; //description de la therapie

    //protected List<BilanOrt> bilansOrt; //liste des bilans orthophoniques sur le patient






}
