/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces.Modelo.EntidadesDAO;

import com.mycompany.interfaces.Modelo.Citas;
import com.mycompany.interfaces.Modelo.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author driss
 */
public class CitasDAO { 
    
    private Connection conn;

    public CitasDAO() {
        // Obtenemos la conexión mediante ConexionBD
        conn = ConexionBD.getConnection();
    }
    
    //Hay que implementar los métodos CRUD
    //Create, es boolean para devolver si funciono o no la inserción
    public boolean agregarCita(Citas c) {
        String sql = "INSERT INTO \"CITAS\" (\"TIEMPO_TARDADO\", \"FECHA\", \"ID_CLIENTE_CLIENTES\") VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            /*Mediante la conexion a la bdd, realizamos un ps
             que nos permite realizar una consulta de manera segura, reemplazando los "?" por elementos reales*/
            ps.setInt(1, c.getTiempo_tardado()); // El 1 representa el primer "?" y asi sucesivamente
            ps.setTimestamp(2, Timestamp.valueOf(c.getFecha()));
            ps.setInt(3, c.getId_Cliente());
            ps.executeUpdate(); //Ejecuta la consulta en la base de datos
            return true;
        } catch (SQLException e) {
            System.err.println("Error al realizar la insercion: " + e.getMessage());
            return false;
        }
    }
    //Read, nos devuelve un cliente de la base de datos mediante el id
    public Citas obtenerCitaPorId(int id) {
        String sql = "SELECT * FROM \"CITAS\" WHERE \"ID_CITA\" = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(); //ResultSet nos permite almacenar las filas que obtenemos de la consulta ps
            if (rs.next()) { // Para comprobar lo que hay almacenado en Rs
               return new Citas(
                    rs.getInt("id_cita"),
                    rs.getInt("tiempo_tardado"),
                    rs.getTimestamp("fecha").toLocalDateTime(),
                    rs.getInt("id_cliente_clientes")               
                );
            }
        } catch (SQLException e) {
             System.err.println("Error al realizar la consulta: " + e.getMessage());
        }
        return null;
    }
    //Update, podemos modificar los clientes desde java a la base de datos
    public boolean actualizarCita(Citas c) {
        String sql = "UPDATE \"CITAS\" SET \"TIEMPO_TARDADO\" = ?, \"FECHA\" = ?, \"ID_CLIENTE_CLIENTES\" = ? WHERE \"ID_CITA\" = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, c.getTiempo_tardado()); // El 1 representa el primer "?" y asi sucesivamente
            ps.setTimestamp(2, Timestamp.valueOf(c.getFecha()));
            ps.setInt(3, c.getId_Cliente());
            ps.setInt(4, c.getId_cita());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
             System.err.println("Error al realizar la modificacion: " + e.getMessage());
            return false;
        }
    }
    
    //Delete, borrar un cliente de la base de datos mediante su id
    public boolean eliminarCita(int id) {
        String sql = "DELETE FROM \"CITAS\" WHERE \"ID_CITA\" = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();   // Se elimina un cliente señalando la id
            return true;
        } catch (SQLException e) {
            System.err.println("Error al realizar la eliminacion: " + e.getMessage());
            return false;
        }
    }
}
