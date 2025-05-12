/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medifinder.metodos;

import com.mycompany.medifinder.Departamento;
import com.mycompany.medifinder.DocumentoSoporte;
import com.mycompany.medifinder.Municipio;
import com.mycompany.medifinder.RepresentanteFarmacia;
import com.mycompany.medifinder.SolicitudAfiliacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GuardarSolicitud {

    ConexionDB conexionDB = new ConexionDB();

    public boolean insertarSolicitud(SolicitudAfiliacion solicitud) {
        Connection conn = null;
        PreparedStatement stmtSolicitud = null;
        PreparedStatement stmtFarmacia = null;
        ResultSet generatedKeys = null;
        int idSolicitudGenerada = -1;
        int idFarmaciaGenerada = -1;

        try {
            conn = conexionDB.Conectar();
            conn.setAutoCommit(false); // Iniciar transacción

            // Insertar Farmacia
            String sqlFarmacia = "INSERT INTO Farmacia (nit, nombre, direccion, email) VALUES (?, ?, ?, ?)";
            stmtFarmacia = conn.prepareStatement(sqlFarmacia, Statement.RETURN_GENERATED_KEYS);
            stmtFarmacia.setString(1, solicitud.getNombre());
            stmtFarmacia.setString(2, solicitud.getNit());
            stmtFarmacia.setString(3, solicitud.getDireccion());
            stmtFarmacia.setString(4, solicitud.getEmail());

            int farmaciasInsertadas = stmtFarmacia.executeUpdate();
            if (farmaciasInsertadas > 0) {
                generatedKeys = stmtFarmacia.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idFarmaciaGenerada = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID de la farmacia.");
                }
            } else {
                throw new SQLException("Error al insertar la farmacia.");
            }

            // Insertar SolicitudAfiliacion
            String sqlSolicitud = "INSERT INTO SolicitudAfiliacion (fecha, estado, comentario, idMunicipio, idRepresentante, idFarmacia) VALUES (?, ?, ?, ?, ?, ?)";
            stmtSolicitud = conn.prepareStatement(sqlSolicitud, Statement.RETURN_GENERATED_KEYS);
            stmtSolicitud.setTimestamp(1, new java.sql.Timestamp(solicitud.getFecha().getTime()));
            stmtSolicitud.setString(2, solicitud.getEstadoSolicitud().name());
            stmtSolicitud.setString(3, solicitud.getComentario());
            stmtSolicitud.setString(4, solicitud.getMunicipio().getId());
            stmtSolicitud.setString(5, solicitud.getRepresentante().getDocld());
            stmtSolicitud.setInt(6, idFarmaciaGenerada);

            int solicitudesInsertadas = stmtSolicitud.executeUpdate();
            if (solicitudesInsertadas > 0) {
                generatedKeys = stmtSolicitud.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idSolicitudGenerada = generatedKeys.getInt(1);
                    guardarDocumentos(conn, solicitud.getDocumentos(), idSolicitudGenerada);
                    conn.commit();
                    return true;
                } else {
                    throw new SQLException("No se pudo obtener el ID de la solicitud.");
                }
            } else {
                throw new SQLException("Error al insertar la solicitud.");
            }

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.out.println("Error al hacer rollback: " + ex.getMessage());
                }
            }
            System.out.println("Error al insertar solicitud y/o farmacia: " + e.getMessage());
            return false;
        } finally {
            // Cerrar recursos (conn, stmt, rs)
            if (generatedKeys != null) try {
                generatedKeys.close();
            } catch (SQLException e) {
            }
            if (stmtSolicitud != null) try {
                stmtSolicitud.close();
            } catch (SQLException e) {
            }
            if (stmtFarmacia != null) try {
                stmtFarmacia.close();
            } catch (SQLException e) {
            }
            if (conn != null) try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
            }
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

    public Departamento insertarDepartamento(String nombre) {
        String sql = "INSERT INTO Departamento (nombre) VALUES (?)";
        try (Connection conn = conexionDB.Conectar(); PreparedStatement stmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, nombre);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idGenerado = generatedKeys.getInt(1);
                    return new Departamento(String.valueOf(idGenerado), nombre);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar departamento: " + e.getMessage());
        }
        return null; // O podrías lanzar una excepción
    }

    public Municipio insertarMunicipio(String nombre, Integer idDepartamento) {
        String sql = "INSERT INTO Municipio (nombre, idDepartamento) VALUES (?, ?)";
        try (Connection conn = conexionDB.Conectar(); PreparedStatement stmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, nombre);
            stmt.setInt(2, idDepartamento);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idGenerado = generatedKeys.getInt(1);
                    return new Municipio(String.valueOf(idGenerado), nombre);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar municipio: " + e.getMessage());
        }
        return null; // O podrías lanzar una excepción
    }

    public RepresentanteFarmacia insertarRepresentante(String nombre, String apellido, String telefono, String correo) {
        String sql = "INSERT INTO RepresentanteFarmacia (nombre, apellido, telefono, correo) VALUES (?, ?, ?, ?)";
        try (Connection conn = conexionDB.Conectar(); PreparedStatement stmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, telefono);
            stmt.setString(4, correo);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idGenerado = generatedKeys.getInt(1);
                    return new RepresentanteFarmacia(String.valueOf(idGenerado), nombre, apellido, correo, telefono);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar representante: " + e.getMessage());
        }
        return null; // O podrías lanzar una excepción
    }

    public int contarSolicitudes() {
        String sql = "SELECT COUNT(*) FROM SolicitudAfiliacion";
        try (Connection conn = conexionDB.Conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error al contar solicitudes: " + e.getMessage());
        }
        return -1; // Indica un error
    }

}
