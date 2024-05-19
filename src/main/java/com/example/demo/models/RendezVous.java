package com.example.demo.models;
import java.time.Duration;
import  java.util.* ;
import java.sql.Time ;

public abstract class RendezVous {

    protected String obserbarvation ;

    protected String date ;
    protected Time heure ;

    protected Duration duree = Duration.ofHours(1) ;

 public String getDate()
 {
     return date ;
 }
 public Time getHeure()
 {
     return heure ;
 }

 public RendezVous(String date , Time time)
 {
     this.date = date ;
     this.heure = time ;
 }

    public void setObservation(String observation)
    {
        this.obserbarvation = observation ;
    }

    public String getObservation()
    {
        return obserbarvation ;
    }

    public int getTempsEnMinutes()
    {
        return heure.getHours()* 60 + heure.getMinutes() ;

    }




}
