/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medifinder.metodos;
import com.mycompany.medifinder.SolicitudAfiliacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class GuardarSolicitud {
    private ConexionDB conexionDB;


public boolean insertarSolicitud(SolicitudAfiliacion solicitud) {
        String sql = "INSERT INTO SolicitudAfiliacion (fecha, estado, comentario, idMunicipio, idFarmacia, idRepresentante) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = conexionDB.Conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, new java.sql.Timestamp(solicitud.getFecha().getTime()));
            stmt.setString(2, solicitud.getEstado().name());
            stmt.setString(3, solicitud.getComentario());
            stmt.setString(4, solicitud.getMunicipio().getId());
            stmt.setString(5, solicitud.getFarmacia().getNombre());
            stmt.setString(6, solicitud.getRepresentante().getDocld());

            int filasInsertadas = stmt.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar solicitud: " + e.getMessage());
            return false;
        }
    }
}