/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces.Modelo.EntidadesDAO;

import com.mycompany.interfaces.Modelo.ConexionBD;
import com.mycompany.interfaces.Modelo.Productos;
import com.mycompany.interfaces.Modelo.Servicios;
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
public class ServiciosDAO {
    private Connection conn;

    public ServiciosDAO() {
        // Obtenemos la conexión mediante ConexionBD
        conn = ConexionBD.getConnection();
    }
    
    //Hay que implementar los métodos CRUD
    //Create, es boolean para devolver si funciono o no la inserción
    public boolean agregarServicio(Servicios s){
        String sql = "INSERT INTO \"SERVICIOS\" (\"NOMBRE\", \"PRECIO\", \"DURACION_MEDIA\") VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            /*Mediante la conexion a la bdd, realizamos un ps
        que nos permite realizar una consulta de manera segura, reemplazando los "?" por elementos reales*/
            ps.setString(1, s.getNombre());
            ps.setInt(2, s.getPrecio());
            ps.setInt(3, s.getDuracion_media());
            ps.executeUpdate(); //Ejecuta la consulta en la base de datos
            return true;
        } catch (SQLException e) {
            System.err.println("Error al realizar la insercion: " + e.getMessage());
            return false;
        }
    }
    
    //READ
    public Servicios obtenerServicioPorId(int id) {
        String sql = "SELECT * FROM \"SERVICIOS\" WHERE \"ID_SERVICIO\" = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(); //ResultSet nos permite almacenar las filas que obtenemos de la consulta ps
            if (rs.next()) {
                return new Servicios(
                        rs.getInt("id_servicio"),
                        rs.getString("nombre"),
                        rs.getInt("precio"),
                        rs.getInt("duracion_media")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar la insercion: " + e.getMessage());           
        }
        return null;
    }
    public List<Servicios> getAll() {
        List<Servicios> lista = new ArrayList<>();
        if (conn == null) {
            return lista;
        }
        try {
            String sql = "SELECT * FROM \"SERVICIOS\"";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Servicios s = new Servicios();  
                s.setId_servicios(rs.getInt("id_servicio"));
                s.setNombre(rs.getString("nombre"));                
                s.setPrecio(rs.getInt("precio"));
                s.setDuracion_media(rs.getInt("duracion_media"));
                lista.add(s);
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar la consulta: " + e.getMessage());
        }
        return lista;
    }
    public List<Servicios> getMasRentables() {
        List<Servicios> lista = new ArrayList<>();
        if (conn == null) {
            return lista;
        }
        try {
            String sql = "SELECT \"SERVICIOS\".\"ID_SERVICIO\",\"SERVICIOS\".\"NOMBRE\", SUM(\"TICKET\".\"IMPORTE\") AS \"INGRESOS_TOTALES\"\n"
                    + "FROM \"SERVICIOS\"\n"
                    + "JOIN \"many_SERVICIOS_has_many_CITAS\" ON \"SERVICIOS\".\"ID_SERVICIO\" = \"many_SERVICIOS_has_many_CITAS\".\"ID_SERVICIO_SERVICIOS\"\n"
                    + "JOIN \"CITAS\" ON \"many_SERVICIOS_has_many_CITAS\".\"ID_CITA_CITAS\" = \"CITAS\".\"ID_CITA\"\n"
                    + "JOIN \"TICKET\" ON \"CITAS\".\"ID_CITA\" = \"TICKET\".\"ID_CITA_CITAS\"\n"
                    + "GROUP BY  \"SERVICIOS\".\"ID_SERVICIO\", \"SERVICIOS\".\"NOMBRE\"\n"
                    + "ORDER BY \"INGRESOS_TOTALES\" DESC\n"
                    + "LIMIT 5;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Servicios s = new Servicios();  
                s.setId_servicios(rs.getInt("id_servicio"));
                s.setNombre(rs.getString("nombre"));                
                s.setPrecio((int) rs.getDouble("INGRESOS_TOTALES"));
                lista.add(s);
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar la consulta: " + e.getMessage());
        }
        return lista;
    }
    
    
    //UPDATE
    public boolean actualizarServicio(Servicios s){
        String sql = "UPDATE \"SERVICIOS\"  SET \"NOMBRE\" = ?, \"PRECIO\" = ?, \"DURACION_MEDIA\" = ? WHERE \"ID_SERVICIO\" = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            /*Mediante la conexion a la bdd, realizamos un ps
        que nos permite realizar una consulta de manera segura, reemplazando los "?" por elementos reales*/
            ps.setString(1, s.getNombre());
            ps.setInt(2, s.getPrecio());
            ps.setInt(3, s.getDuracion_media());
            ps.setInt(4, s.getId_servicios());
            ps.executeUpdate(); //Ejecuta la consulta en la base de datos
            return true;
        } catch (SQLException e) {
            System.err.println("Error al realizar la actualizacion: " + e.getMessage());
            return false;
        }
    }
    
    //DELETE
    public boolean eliminarServicio(int id){
        String sql ="DELETE FROM \"SERVICIOS\" WHERE \"ID_SERVICIO\" = ?" ;
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.err.println("Error al realizar la eliminacion: " + e.getMessage());
            return false;
        }
    }


    //public static void main(String[] args) {}
       
}

