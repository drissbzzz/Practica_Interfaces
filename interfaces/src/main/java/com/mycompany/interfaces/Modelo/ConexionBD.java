/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces.Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author driss
 */

public class ConexionBD {

    // Datos para realizar la conexión a la base de datos
    private static final String url = "jdbc:postgresql://localhost:5432/peluqueriaspaquita"; 
    private static final String usuario = "postgres";
    private static final String contrasena = "1234";
    private static Connection conexion = null;

    // Método que devuelve la conexión 
    public static Connection getConnection() {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(url, usuario, contrasena);
                System.out.println("Conexion establecida correctamente.");
            }
        } catch (SQLException e) {
                System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conexion;
    }
    // Método para cerrar la conexión si hace falta
    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexion cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
