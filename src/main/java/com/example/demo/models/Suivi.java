package com.example.demo.models;

import java.sql.Time;

public class Suivi extends RendezVous{
    private DeroulementSeance typeSeance;
    private int numeroDossierDePatient;
    private final String type = "Suivi" ;

    public  Suivi(Object deroulementSeance , int numeroDossierDePatient , String date , Time time)
    {
        super(date , time);
        this.typeSeance = (DeroulementSeance) deroulementSeance;
        this.numeroDossierDePatient = numeroDossierDePatient;

    }
    public  String getType()
    {
        return this.type;
    }
    public  int getNumeroDossierDePatient()
    {
        return this.numeroDossierDePatient;
    }
public DeroulementSeance getTypeSeance(){
    return this.typeSeance;
}


  }
