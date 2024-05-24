package com.example.demo.models;

public class AnamneseAdult extends Anamnese {
    private String antecedentsPersonnels;
    private String suiviMedicale;

    public AnamneseAdult() {
        // Constructeur par défaut
    }

    // Getters et Setters
    public String getAntecedentsPersonnels() {
        return antecedentsPersonnels;
    }

    public void setAntecedentsPersonnels(String antecedentsPersonnels) {
        this.antecedentsPersonnels = antecedentsPersonnels;
    }

    public String getSuiviMedicale() {
        return suiviMedicale;
    }

    public void setSuiviMedicale(String suiviMedicale) {
        this.suiviMedicale = suiviMedicale;
    }
}
