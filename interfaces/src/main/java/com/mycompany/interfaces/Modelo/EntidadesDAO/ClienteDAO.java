/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces.Modelo.EntidadesDAO;

import com.mycompany.interfaces.Modelo.Cliente;
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
public class ClienteDAO {
    
     private Connection conn;

    public ClienteDAO() {
        // Obtenemos la conexión mediante ConexionBD
        conn = ConexionBD.getConnection();
    }
    
    //Hay que implementar los métodos CRUD
    //Create, es boolean para devolver si funciono o no la inserción
    public boolean agregarCliente(Cliente c) {
        String sql = "INSERT INTO \"CLIENTES\" (\"NOMBRE\", \"APELLIDOS\", \"VIP\", \"N_DE_VISITAS\", \"ALTA_FECHA\") VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            /*Mediante la conexion a la bdd, realizamos un ps
             que nos permite realizar una consulta de manera segura, reemplazando los "?" por elementos reales*/
            ps.setString(1, c.getNombre()); // El 1 representa el primer "?" y asi sucesivamente
            ps.setString(2, c.getApellidos());
            ps.setBoolean(3, c.isVip());
            ps.setInt(4, c.getN_visitas());
            ps.setTimestamp(5, Timestamp.valueOf(c.getFecha_alta()));
            ps.executeUpdate(); //Ejecuta la consulta en la base de datos
            return true;
        } catch (SQLException e) {
            System.err.println("Error al realizar la insercion: " + e.getMessage());
            return false;
        }
    }
    //Read, nos devuelve un cliente de la base de datos mediante el id
    public Cliente obtenerClientePorId(int id) {
        String sql = "SELECT * FROM \"CLIENTES\" WHERE \"ID_CLIENTE\" = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(); //ResultSet nos permite almacenar las filas que obtenemos de la consulta ps
            if (rs.next()) { // Para comprobar lo que hay almacenado en Rs
               return new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getBoolean("vip"),
                    rs.getInt("n_de_visitas"),
                    rs.getTimestamp("alta_fecha").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
             System.err.println("Error al realizar la consulta: " + e.getMessage());
        }
        return null;
    }
    //Update, podemos modificar los clientes desde java a la base de datos
    public boolean actualizarCliente(Cliente c) {
        String sql = "UPDATE \"CLIENTES\" SET \"NOMBRE\" = ?, \"APELLIDOS\" = ?, \"VIP\" = ?, \"N_DE_VISITAS\" = ?, \"ALTA_FECHA\" = ? WHERE \"ID_CLIENTE\" = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellidos());
            ps.setBoolean(3, c.isVip());         //Practicamente como el de insercion pero señalando la id a modificar          
            ps.setInt(4, c.getN_visitas());
            ps.setTimestamp(5, Timestamp.valueOf(c.getFecha_alta()));
            ps.setInt(6, c.getId_cliente());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
             System.err.println("Error al realizar la modificacion: " + e.getMessage());
            return false;
        }
    }
    
    //Delete, borrar un cliente de la base de datos mediante su id
    public boolean eliminarCliente(int id) {
        String sql = "DELETE FROM \"CLIENTES\" WHERE \"ID_CLIENTE\" = ?";
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
