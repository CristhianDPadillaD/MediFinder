package com.mycompany.medifinder;

import com.mycompany.medifinder.enums.EstadoSolicitud; 
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class SolicitudAfiliacion {
    private Date fecha;
    private EstadoSolicitud estado; 
    private String comentario;
    private Municipio municipio; 
    private RepresentanteFarmacia representante;
    private List<DocumentoSoporte> documentos; 


    public SolicitudAfiliacion(Date fecha, EstadoSolicitud estado, String comentario, Municipio municipio, RepresentanteFarmacia representante) {
        this.fecha = fecha;
        this.estado = estado;
        this.comentario = comentario;
        this.municipio = municipio;
        this.representante = representante;
        this.documentos = new ArrayList<>(); 
    }

    public void agregarDocumentoSoporte(DocumentoSoporte documento) {
        this.documentos.add(documento);
    }

    public List<DocumentoSoporte> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentoSoporte> documentos) {
        this.documentos = documentos;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public RepresentanteFarmacia getRepresentante() {
        return representante;
    }

    public void setRepresentante(RepresentanteFarmacia representante) {
        this.representante = representante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}