/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medifinder;
import com.mycompany.medifinder.enums.EstadoFarmacia;

public class Farmacia {
    private String nombre;
    private String nit;
    private String direccion;
    private String email;
    private EstadoFarmacia Estado;

    public Farmacia(String nombre, String nit, String direccion, String email) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EstadoFarmacia getEstadoFarmacia() {
        return Estado;
    }

    public void setEstadoFarmacia(EstadoFarmacia Estado) {
        this.Estado = Estado;
    }
       
}
