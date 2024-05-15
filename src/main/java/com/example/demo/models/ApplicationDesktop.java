package com.example.demo.models;

public class ApplicationDesktop {

    private Orthophoniste orthophoniste;

    public boolean isExiste ( String email)
    {
        return orthophoniste.getEmail().equals(email);
    }

    public Orthophoniste getOrthophoniste() {
        return orthophoniste;
    }

    public void setOrthophoniste(Orthophoniste orthophoniste)
    {
        this.orthophoniste = orthophoniste ;
    }



}
