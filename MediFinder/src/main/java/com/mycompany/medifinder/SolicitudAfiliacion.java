/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medifinder;
import com.mycompany.medifinder.enums.EstadoFarmacia;
import java.util.Date;

public class SolicitudAfiliacion {
    private Date fecha;
    private EstadoFarmacia estado;
    private String comentario;
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public EstadoFarmacia getEstado() {
        return estado;
    }

    public void setEstado(EstadoFarmacia estado) {
        this.estado = estado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}
