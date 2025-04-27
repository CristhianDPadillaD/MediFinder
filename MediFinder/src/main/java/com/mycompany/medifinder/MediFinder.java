package com.mycompany.medifinder;

import java.util.List;
import java.util.ArrayList;

public class MediFinder {
    private String id; 
    private Departamento departamento; 
    private List<Farmacia> farmacias; 

    public MediFinder(String id) {
        this.id = id;
        this.farmacias = new ArrayList<>();
        this.departamento = null; 
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Farmacia> getFarmacias() {
        return farmacias;
    }

    public void setFarmacias(List<Farmacia> farmacias) {
        this.farmacias = farmacias;
    }

}