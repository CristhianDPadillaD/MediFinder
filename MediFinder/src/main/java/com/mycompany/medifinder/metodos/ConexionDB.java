/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.medifinder.metodos;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
     String URL = "jdbc:mysql://localhost:3306/Medifinder";
    String USUARIO = "root";
    String CONTRASEÑA = "admin";
    String driver = "com.mysql.cj.jdbc.Driver";
    java.sql.Connection cx; // Objeto Conexion para la conexión a la base de datos
    
    
    
       public java.sql.Connection Conectar() {
        try {
            // Cargar el controlador JDBC
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            System.out.println("No se encontró el controlador JDBC: " + ex.getMessage());
        }
        try {
            // Establecer la conexión y almacenarla en la variable cx
            cx = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
        } catch (SQLException e) {
            System.out.println("Error al conectar la base de datos: " + e.getMessage());
        }
        // Imprimir mensaje de conexión exitosa
   
        return cx;
    }
}
