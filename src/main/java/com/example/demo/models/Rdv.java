package com.example.demo.models;

import java.sql.Time;
import java.util.Date;

public class Rdv {
    private String type;
    private Date date;
    private Time heure;

    public Rdv(String type,Date date, Time heure) {
        this.type = type;
        this.date = date;
        this.heure = heure;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}