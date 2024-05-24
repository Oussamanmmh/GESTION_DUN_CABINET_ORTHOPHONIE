package com.example.demo.models;

import java.time.Duration;
import java.util.Date;
import java.sql.Time;

public abstract class RendezVous {

    protected String observation;
    protected Date date;
    protected Time heure;
    protected Duration duree = Duration.ofHours(1);


    public RendezVous(Date date,Time heure){

        this.date=date;
        this.heure=heure;
    }

    // Getters and Setters
    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }

    public Duration getDuree() {
        return duree;
    }

    public void setDuree(Duration duree) {
        this.duree = duree;
    }
  public abstract String getType();
}
