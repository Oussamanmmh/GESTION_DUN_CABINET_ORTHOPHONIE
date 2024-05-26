package com.example.demo.models;
import java.util.ArrayList;
import java.util.List;
public class BilanOrt {
    private Anamnese anamnese ;
    private List<EpreuveClinique> epreuvesCliniques ;
    public BilanOrt(Anamnese anamnese , List<EpreuveClinique> epreuvesCliniques)
    {
        this.anamnese = anamnese;
        this.epreuvesCliniques = new ArrayList<>(epreuvesCliniques) ;
    }
    public BilanOrt(List<EpreuveClinique> epreuvesCliniques)
    {
        this.epreuvesCliniques = new ArrayList<>(epreuvesCliniques) ;
    }

    public List<EpreuveClinique> getEpreuvesCliniques() {
        return epreuvesCliniques;
    }
    public Anamnese getAnamnese() {
        return anamnese;
    }
}

