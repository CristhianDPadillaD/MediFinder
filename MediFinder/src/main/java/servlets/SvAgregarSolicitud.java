/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.mycompany.medifinder.DocumentoSoporte;
import com.mycompany.medifinder.Municipio;
import com.mycompany.medifinder.RepresentanteFarmacia;
import com.mycompany.medifinder.SolicitudAfiliacion;
import com.mycompany.medifinder.enums.EstadoSolicitud;
import com.mycompany.medifinder.metodos.GuardarSolicitud;
import com.mycompany.medifinder.tests.MediFinderTest;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet(name = "SvAgregarSolicitud", urlPatterns = {"/SvAgregarSolicitud"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)  // 50MB
public class SvAgregarSolicitud extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         GuardarSolicitud metodosGuardarSolicitud = new GuardarSolicitud();
         Date fechaSolicitud = new Date();
        metodosGuardarSolicitud.insertarDepartamento(departamento);
        Municipio municipio = metodosGuardarSolicitud.insertarMunicipio("Pasto", 1);
        RepresentanteFarmacia representante = metodosGuardarSolicitud.insertarRepresentante("Luisa", "Portilla", "3138196821", "Luisa123@gmail.com");
        DocumentoSoporte registroCamara = new DocumentoSoporte("registro_camara.pdf", "Registro Cámara de Comercio", "");
     DocumentoSoporte licenciaFuncionamiento = new DocumentoSoporte("licencia.pdf", "Licencia de Funcionamiento", "");
        DocumentoSoporte registroSanitario = new DocumentoSoporte("sanitario.pdf", "Registro Sanitario", "");

         List<DocumentoSoporte> documentos = new ArrayList<>();
        documentos.add(registroCamara);
        documentos.add(licenciaFuncionamiento);
        documentos.add(registroSanitario);

  

         
             ServletContext context = getServletContext();
            MediFinderTest test = new MediFinderTest();
            String nombre = request.getParameter("nombre");
            String departamento = request.getParameter("departamento");
            String direccion = request.getParameter("direccion");
            String email = request.getParameter("correo");
            String nit = request.getParameter("nit");
            String ciudad = request.getParameter ("ciudad");
            String representanteLegal = request.getParameter("representanteLegal");
            
            Part filePart = request.getPart("camara");
             String fileName = "";
      
      if(filePart != null && filePart.getSize() > 0){
          
      
        fileName = filePart.getSubmittedFileName();

        // Get the file upload directory
      String uploadDir = context.getRealPath("/camara");
      //String pdfFilePath = uploadDir + File.separator + fileName;
      File uploadFolder = new File(uploadDir);
  if (!uploadFolder.exists()) {
    uploadFolder.mkdir();
  }
  File destFile = new File(uploadFolder, fileName);

  // Copy the uploaded file to the destination path
  try (InputStream input = filePart.getInputStream(); OutputStream output = new FileOutputStream(destFile)) {
    byte[] buffer = new byte[1024];
    int length;
    while ((length = input.read(buffer)) > 0) {
      output.write(buffer, 0, length);
    }
  }
}
          Part filePartLicencia = request.getPart("licencia");
             String fileNameLicencia = "";
      
      if(filePartLicencia != null && filePartLicencia.getSize() > 0){
          
      
        fileNameLicencia = filePartLicencia.getSubmittedFileName();

        // Get the file upload directory
      String uploadDir = context.getRealPath("/licencia");
      //String pdfFilePath = uploadDir + File.separator + fileName;
      File uploadFolder = new File(uploadDir);
  if (!uploadFolder.exists()) {
    uploadFolder.mkdir();
  }
  File destFile = new File(uploadFolder, fileName);

  // Copy the uploaded file to the destination path
  try (InputStream input = filePartLicencia.getInputStream(); OutputStream output = new FileOutputStream(destFile)) {
    byte[] buffer = new byte[1024];
    int length;
    while ((length = input.read(buffer)) > 0) {
      output.write(buffer, 0, length);
    }
  }
}
          Part filePartRegistro = request.getPart("registroSanitario");
             String fileNameRegistro = "";
      
      if(filePartRegistro != null && filePartRegistro.getSize() > 0){
          
      
        fileNameRegistro = filePartRegistro.getSubmittedFileName();

        // Get the file upload directory
      String uploadDir = context.getRealPath("/registroSanitario");
      //String pdfFilePath = uploadDir + File.separator + fileName;
      File uploadFolder = new File(uploadDir);
  if (!uploadFolder.exists()) {
    uploadFolder.mkdir();
  }
  File destFile = new File(uploadFolder, fileName);

  // Copy the uploaded file to the destination path
  try (InputStream input = filePartRegistro.getInputStream(); OutputStream output = new FileOutputStream(destFile)) {
    byte[] buffer = new byte[1024];
    int length;
    while ((length = input.read(buffer)) > 0) {
      output.write(buffer, 0, length);
    }
  }
}
      String comentario = request.getParameter("comentario");
   
        SolicitudAfiliacion solicitud = new SolicitudAfiliacion(fechaSolicitud, EstadoSolicitud.PENDIENTE, comentario, ciudad, representante, documentos, nombre, nit, direccion, email);
            
            test.testGuardarSolicitud();
             response.sendRedirect("Formulario.jsp");
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
