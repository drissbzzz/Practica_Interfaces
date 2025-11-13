/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces.Modelo.EntidadesDAO;


import com.mycompany.interfaces.Modelo.ConexionBD;
import com.mycompany.interfaces.Modelo.Peluqueras;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author driss
 */
public class PeluquerasDAO {
    
    private Connection conn;

    public PeluquerasDAO() {
        // Obtenemos la conexión mediante ConexionBD
        conn = ConexionBD.getConnection();
    }
    
    //Hay que implementar los métodos CRUD
    //Create, es boolean para devolver si funciono o no la inserción
    public boolean agregarPeluquera(Peluqueras p) {
        String sql = "INSERT INTO \"PELUQUERAS\" (\"NOMBRE\", \"AÑOS_EXP\", \"ESTADO\", \"ID_SERVICIO_SERVICIOS\") VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            /*Mediante la conexion a la bdd, realizamos un ps
             que nos permite realizar una consulta de manera segura, reemplazando los "?" por elementos reales*/
            ps.setString(1, p.getNombre()); // El 1 representa el primer "?" y asi sucesivamente
            ps.setInt(2, p.getAnhos_exp());
            ps.setString(3, p.getEstado());
            ps.setInt(4, p.getId_Servicios());
            ps.executeUpdate(); //Ejecuta la consulta en la base de datos
            return true;
        } catch (SQLException e) {
            System.err.println("Error al realizar la insercion: " + e.getMessage());
            return false;
        }
    }
    //Read, nos devuelve un cliente de la base de datos mediante el id
    public Peluqueras obtenerPeluqueraPorId(int id) {
        String sql = "SELECT * FROM \"PELUQUERAS\" WHERE \"ID_PELUQUERA\" = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(); //ResultSet nos permite almacenar las filas que obtenemos de la consulta ps
            if (rs.next()) { // Para comprobar lo que hay almacenado en Rs
               return new Peluqueras(
                    rs.getInt("id_peluquera"),
                    rs.getString("nombre"),
                    rs.getInt("años_exp"),
                    rs.getString("estado"),
                    rs.getInt("id_servicio_servicios")               
                );
            }
        } catch (SQLException e) {
             System.err.println("Error al realizar la consulta: " + e.getMessage());
        }
        return null;
    }
    //Update, podemos modificar los clientes desde java a la base de datos
    public boolean actualizarPeluquera(Peluqueras p) {
        String sql = "UPDATE \"PELUQUERAS\" SET \"NOMBRE\" = ?, \"AÑOS_EXP\" = ?, \"ESTADO\" = ?, \"ID_SERVICIO_SERVICIOS\" = ? WHERE \"ID_PELUQUERA\" = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNombre()); // El 1 representa el primer "?" y asi sucesivamente
            ps.setInt(2, p.getAnhos_exp());
            ps.setString(3, p.getEstado());
            ps.setInt(4, p.getId_Servicios());
            ps.setInt(5, p.getId_peluquera());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
             System.err.println("Error al realizar la modificacion: " + e.getMessage());
            return false;
        }
    }
    
    //Delete, borrar un cliente de la base de datos mediante su id
    public boolean eliminarPeluquera(int id) {
        String sql = "DELETE FROM \"PELUQUERAS\" WHERE \"ID_PELUQUERA\" = ?";
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
