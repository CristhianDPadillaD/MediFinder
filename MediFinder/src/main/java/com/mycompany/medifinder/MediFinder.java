package com.mycompany.medifinder;

import java.util.List;
import java.util.ArrayList;

public class MediFinder {
    private List <Departamento> departamento; 
    private List<Farmacia> farmacias; 

    public MediFinder() {
        this.farmacias = new ArrayList<>();
        this.departamento = new ArrayList<>(); 
    }

    public List<Departamento> getDepartamento() {
        return departamento;
    }

    public void setDepartamento(List <Departamento> departamento) {
        this.departamento = departamento;
    }

    public List<Farmacia> getFarmacias() {
        return farmacias;
    }

    public void setFarmacias(List<Farmacia> farmacias) {
        this.farmacias = farmacias;
    }

}