/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces.Modelo.EntidadesDAO;

import com.mycompany.interfaces.Modelo.Citas;
import com.mycompany.interfaces.Modelo.ConexionBD;
import com.mycompany.interfaces.Modelo.Productos;
import com.mycompany.interfaces.Modelo.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author driss
 */
public class ProductoDAO {
    
    private Connection conn;

    public ProductoDAO() {
        // Obtenemos la conexión mediante ConexionBD
        conn = ConexionBD.getConnection();
    }
    
    //Hay que implementar los métodos CRUD
    //Create, es boolean para devolver si funciono o no la inserción
    public boolean agregarProducto(Productos p) {
        String sql = "INSERT INTO \"PRODUCTOS\" (\"NOMBRE\", \"TIPO\", \"STOCK_ACTUAL\", \"STOCK_MAX\", \"PROVEEDOR\") VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            /*Mediante la conexion a la bdd, realizamos un ps
             que nos permite realizar una consulta de manera segura, reemplazando los "?" por elementos reales*/
            ps.setString(1, p.getNombre()); // El 1 representa el primer "?" y asi sucesivamente
            ps.setString(2, p.getTipo());
            ps.setInt(3, p.getStock_actual());
            ps.setInt(4, p.getStock_max());
            ps.setString(5, p.getProveedor());
            ps.executeUpdate(); //Ejecuta la consulta en la base de datos
            return true;
        } catch (SQLException e) {
            System.err.println("Error al realizar la insercion: " + e.getMessage());
            return false;
        }
    }
    
    //Read, nos devuelve un cliente de la base de datos mediante el id
    public Productos obtenerProductoPorId(int id) {
        String sql = "SELECT * FROM \"PRODUCTOS\" WHERE \"ID_PRODUCTO\" = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(); //ResultSet nos permite almacenar las filas que obtenemos de la consulta ps
            if (rs.next()) { // Para comprobar lo que hay almacenado en Rs
               return new Productos(
                    rs.getString("nombre"),
                    rs.getInt("id_producto"),
                    rs.getString("tipo"),
                    rs.getInt("stock_actual"), 
                    rs.getInt("stock_max"),
                    rs.getString("proveedor")
                );
            }
        } catch (SQLException e) {
             System.err.println("Error al realizar la consulta: " + e.getMessage());
        }
        return null;
    }
    public List<Productos> getAll() {
        List<Productos> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM \"PRODUCTOS\"";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Productos p = new Productos();             
                p.setNombre(rs.getString("nombre"));
                p.setId_producto(rs.getInt("id_producto"));
                p.setTipo(rs.getString("tipo"));
                p.setStock_actual(rs.getInt("stock_actual"));
                p.setStock_max(rs.getInt("stock_max"));
                p.setProveedor(rs.getString("proveedor"));           
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar la consulta: " + e.getMessage());
        }
        return lista;
    }
    
    public List<Productos> getStockCriticos() {
        List<Productos> lista = new ArrayList<>();
        try {
            String sql =  "SELECT \"ID_PRODUCTO\",\"NOMBRE\",\"STOCK_ACTUAL\", \"STOCK_MAX\"\n"
                        + "FROM \"PRODUCTOS\"\n"
                        + "WHERE\"STOCK_ACTUAL\" < 0.1 * \"STOCK_MAX\"\n"
                        + "ORDER BY \"STOCK_ACTUAL\" ASC;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Productos p = new Productos();  
                p.setId_producto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));                
                p.setStock_actual(rs.getInt("stock_actual"));
                p.setStock_max(rs.getInt("stock_max"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar la consulta: " + e.getMessage());
        }
        return lista;
    }
    public boolean actualizarProducto(Productos p) {
        String sql = "UPDATE \"PRODUCTOS\" SET \"NOMBRE\" = ?, \"TIPO\" = ?, \"STOCK_ACTUAL\" = ?, \"STOCK_MAX\" = ?, \"PROVEEDOR\" = ? WHERE \"ID_PRODUCTO\" = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNombre()); // El 1 representa el primer "?" y asi sucesivamente
            ps.setString(2, p.getTipo());
            ps.setInt(3, p.getStock_actual());
            ps.setInt(4, p.getStock_max());
            ps.setString(5, p.getProveedor());
            ps.setInt(6, p.getId_producto());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
             System.err.println("Error al realizar la modificacion: " + e.getMessage());
            return false;
        }
    }
     //Delete, borrar un cliente de la base de datos mediante su id
    public boolean eliminarProducto(int id) {
        String sql = "DELETE FROM \"PRODUCTO\" WHERE \"ID_PRODUCTO\" = ?";
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
