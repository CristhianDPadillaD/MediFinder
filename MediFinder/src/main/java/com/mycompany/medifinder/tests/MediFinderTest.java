/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medifinder.tests;
import com.mycompany.medifinder.DocumentoSoporte;
import com.mycompany.medifinder.Municipio;
import com.mycompany.medifinder.RepresentanteFarmacia;
import java.util.Date;
import javax.validation.constraints.AssertFalse;
import junit.framework.TestCase;
import com.mycompany.medifinder.SolicitudAfiliacion;
import com.mycompany.medifinder.enums.EstadoSolicitud;
import com.mycompany.medifinder.metodos.GuardarSolicitud;

public class MediFinderTest extends TestCase {
    
    public void testRegistroSolicitud1(){
        GuardarSolicitud guardarSolicitud = new GuardarSolicitud();
        Date fechaSolicitud  = new Date ();
        
        try{
                Municipio municipio = new Municipio("1", "Pasto");
                RepresentanteFarmacia representante = new RepresentanteFarmacia("1","Luisa", "Portilla", "Luisa123@gmail.com", "3138196821");
                DocumentoSoporte registroCamara = new DocumentoSoporte("registro_camara.pdf", "Registro Cámara de Comercio", "");
                DocumentoSoporte licenciaFuncionamiento = new DocumentoSoporte("licencia.pdf", "Licencia de Funcionamiento", "");
                DocumentoSoporte registroSanitario = new DocumentoSoporte("sanitario.pdf","Registro Sanitario","");
                
                SolicitudAfiliacion solicitud = new SolicitudAfiliacion(fechaSolicitud, EstadoSolicitud.PENDIENTE, "Comentario de prueba para la inserción de una nueva solicitud.", municipio, representante);
                solicitud.agregarDocumentoSoporte(registroCamara);
                solicitud.agregarDocumentoSoporte(licenciaFuncionamiento);
                solicitud.agregarDocumentoSoporte(registroSanitario);
               boolean insertado = guardarSolicitud.insertarSolicitud(solicitud);
               assertTrue(insertado);
                }catch (Exception e){
                        fail("\n"+ e.getMessage());
                        }
    }
         
    
}
