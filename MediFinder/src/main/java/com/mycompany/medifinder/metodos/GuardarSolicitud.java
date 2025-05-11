/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medifinder.metodos;
import com.mycompany.medifinder.DocumentoSoporte;
import com.mycompany.medifinder.SolicitudAfiliacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class GuardarSolicitud {
    ConexionDB conexionDB = new ConexionDB();


  public boolean insertarSolicitud(SolicitudAfiliacion solicitud) {
        String sql = "INSERT INTO SolicitudAfiliacion (fecha, estado, comentario, idMunicipio, idRepresentante) VALUES (?, ?, ?, ?, ?)";
        int idSolicitudGenerada = -1;

        try (Connection conn = conexionDB.Conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            stmt.setTimestamp(1, new java.sql.Timestamp(solicitud.getFecha().getTime()));
            stmt.setString(2, solicitud.getEstado().name());
            stmt.setString(3, solicitud.getComentario());
            stmt.setString(4, solicitud.getMunicipio().getId());
            stmt.setString(5, solicitud.getRepresentante().getDocld());

            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idSolicitudGenerada = generatedKeys.getInt(1);
                    guardarDocumentos(conn, solicitud.getDocumentos(), idSolicitudGenerada);
                    return true;
                }
            }
            return false;

        } catch (SQLException e) {
            System.out.println("Error al insertar solicitud: " + e.getMessage());
            return false;
        }
    }
        private void guardarDocumentos(Connection conn, List<DocumentoSoporte> documentos, int idSolicitud) throws SQLException {
        String sql = "INSERT INTO Documento (idSolicitud, nombreDocumento, tipoDocumento, rutaArchivo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (DocumentoSoporte documento : documentos) {
                stmt.setInt(1, idSolicitud);
                stmt.setString(2, documento.getNombreArchivo());
                stmt.setString(3, documento.getTipo());
                stmt.setString(4, documento.getUrl());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar documentos: " + e.getMessage());
            throw e; 
        }
    }

}