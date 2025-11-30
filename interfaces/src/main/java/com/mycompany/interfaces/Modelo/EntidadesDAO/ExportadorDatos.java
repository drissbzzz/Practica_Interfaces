/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces.Modelo.EntidadesDAO;

import com.mycompany.interfaces.Modelo.ConexionBD;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author driss
 */
public class ExportadorDatos { //EXTRA, Es la clase que se encargará de consultar a la base de datos
    //paa escribir todos los datos en un archivo y en formato CSV

    private Connection conn;

    public ExportadorDatos() {
        conn = ConexionBD.getConnection();
    }

    public boolean generarCSV(String tablaSelec, File archivo) {
        if (conn == null) {
            return false;
        }
        String sql = "SELECT * FROM \"" + tablaSelec + "\"";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery(); BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {

            ResultSetMetaData metaData = rs.getMetaData(); //Adquiere las columnas de manera automatica para no tener que escribir todas manualmente
            int columnCount = metaData.getColumnCount(); //coger el numero de columnas para realizar el bucle para el encabezado
            for (int i = 1; i <= columnCount; i++) {
                bw.write(metaData.getColumnName(i));
                if (i < columnCount) {
                    bw.write(";");
                }
            }
            bw.newLine();
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) { //Escribiendo en cada columna
                    Object valor = rs.getObject(i); //Obtener lo que vamos a escribir
                    bw.write(valor == null ? "" : valor.toString()); // Lo escribimos si no es nulo, si es nulo lo dejamos en blanco
                    if (i < columnCount) { //Si no es la ultima columna, lo separamos
                        bw.write(";");
                    }
                }
                bw.newLine(); //Saltamos de linea
            }
            System.out.println("Exportación de " + tablaSelec + " completada.");
            return true;

        } catch (SQLException e) {
            System.err.println("Error SQL al exportar: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al guardar archivo: " + e.getMessage());
            return false;
        }

    }
}
