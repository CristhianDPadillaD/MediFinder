package com.mycompany.medifinder.tests;
import com.mycompany.medifinder.DocumentoSoporte;
import com.mycompany.medifinder.Municipio;
import com.mycompany.medifinder.RepresentanteFarmacia;
import java.util.Date;
import junit.framework.TestCase;
import com.mycompany.medifinder.SolicitudAfiliacion;
import com.mycompany.medifinder.enums.EstadoSolicitud;
import com.mycompany.medifinder.metodos.GuardarSolicitud;
import java.util.ArrayList;
import java.util.List;

public class MediFinderTest extends TestCase {

    private GuardarSolicitud guardarSolicitud;
    private Date fechaSolicitud;
    private Municipio municipio;
    private RepresentanteFarmacia representante;
    private SolicitudAfiliacion solicitud;

 public void setupEscenario1() throws Exception {
    guardarSolicitud = new GuardarSolicitud();
    fechaSolicitud = new Date();
    guardarSolicitud.insertarDepartamento("Nariño");
    municipio = guardarSolicitud.insertarMunicipio("Pasto", 1);
    representante = guardarSolicitud.insertarRepresentante("Luisa", "Portilla", "3138196821", "Luisa123@gmail.com");
    DocumentoSoporte registroCamara = new DocumentoSoporte("registro_camara.pdf", "Registro Cámara de Comercio", "");
    DocumentoSoporte licenciaFuncionamiento = new DocumentoSoporte("licencia.pdf", "Licencia de Funcionamiento", "");
    DocumentoSoporte registroSanitario = new DocumentoSoporte("sanitario.pdf", "Registro Sanitario", "");

    List<DocumentoSoporte> documentos = new ArrayList<>();
    documentos.add(registroCamara);
    documentos.add(licenciaFuncionamiento);
    documentos.add(registroSanitario);

    solicitud = new SolicitudAfiliacion(fechaSolicitud, EstadoSolicitud.PENDIENTE, "Comentario de prueba para la inserción de una nueva solicitud.", municipio, representante, documentos,
            "Mi Farmacia de Prueba", "123456789", "Calle Falsa 123", "info@mifarmacia.com");
}

    public void testGuardarSolicitud() {
        try {
            setupEscenario1(); 
            guardarSolicitud.insertarSolicitud(solicitud);
            assertEquals(2, guardarSolicitud.contarSolicitudes());
        } catch (Exception e) {
            fail("\n" + e.getMessage());
        }
    }
}