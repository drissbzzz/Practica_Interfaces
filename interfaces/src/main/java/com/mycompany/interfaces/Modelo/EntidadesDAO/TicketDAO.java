/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces.Modelo.EntidadesDAO;

import com.mycompany.interfaces.Modelo.ConexionBD;
import com.mycompany.interfaces.Modelo.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author driss
 */
public class TicketDAO {
    
    private Connection conn;

    public TicketDAO() {
        // Obtenemos la conexión mediante ConexionBD
        conn = ConexionBD.getConnection();
    }
    
    //Hay que implementar los métodos CRUD
    //Create, es boolean para devolver si funciono o no la inserción
    public boolean agregarTicket(Ticket t) {
        String sql = "INSERT INTO \"TICKET\" (\"IMPORTE\", \"METODO_DE_PAGO\", \"ID_CITA_CITAS\") VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            /*Mediante la conexion a la bdd, realizamos un ps
             que nos permite realizar una consulta de manera segura, reemplazando los "?" por elementos reales*/
            ps.setInt(1, t.getImporte()); // El 1 representa el primer "?" y asi sucesivamente
            ps.setString(2, t.getMetodo_pago());
            ps.setInt(3, t.getId_Cita());
            ps.executeUpdate(); //Ejecuta la consulta en la base de datos
            return true;
        } catch (SQLException e) {
            System.err.println("Error al realizar la insercion: " + e.getMessage());
            return false;
        }
    }
    //Read, nos devuelve un cliente de la base de datos mediante el id
    public Ticket obtenerTicketPorId(int id) {
        String sql = "SELECT * FROM \"TICKET\" WHERE \"ID_TICKET\" = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(); //ResultSet nos permite almacenar las filas que obtenemos de la consulta ps
            if (rs.next()) { // Para comprobar lo que hay almacenado en Rs
               return new Ticket(
                    rs.getInt("importe"),
                    rs.getString("metodo_de_pago"),
                    rs.getInt("id_ticket"), 
                    rs.getInt("id_cita_citas")                               
                );
            }
        } catch (SQLException e) {
             System.err.println("Error al realizar la consulta: " + e.getMessage());
        }
        return null;
    }
    //Update, podemos modificar los clientes desde java a la base de datos
    public boolean actualizarTicket(Ticket t) {
        String sql = "UPDATE \"TICKET\" SET \"IMPORTE\" = ?, \"METODO_DE_PAGO\" = ?, \"ID_CITA_CITAS\" = ? WHERE \"ID_TICKET\" = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, t.getImporte()); // El 1 representa el primer "?" y asi sucesivamente
            ps.setString(2, t.getMetodo_pago());
            ps.setInt(3, t.getId_Cita());
            ps.setInt(4, t.getId_ticket());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
             System.err.println("Error al realizar la modificacion: " + e.getMessage());
            return false;
        }
    }
    
    //Delete, borrar un cliente de la base de datos mediante su id
    public boolean eliminarTicket(int id) {
        String sql = "DELETE FROM \"TICKET\" WHERE \"ID_TICKET\" = ?";
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
