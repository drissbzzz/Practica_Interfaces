/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mycompany.interfaces.Modelo.EntidadesDAO.ServiciosDAO;
import com.mycompany.interfaces.Modelo.Servicios;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author driss
 */
public class ServiciosControlador {
    
    private ServiciosDAO dao;

    public ServiciosControlador() {
    }
    
    public JTable tablaHecha() {
        dao = new ServiciosDAO();
        List<Servicios> lista = dao.getAll();
        String[] columnas = {"ID", "Nombre", "Precio", "Duracion Media"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        for (int i = 0; i < lista.size(); i++) {
            Servicios s = lista.get(i);
            modeloTabla.addRow(new Object[]{
                s.getId_servicios(),
                s.getNombre(),
                s.getPrecio(),
                s.getDuracion_media()
            });
        }
        JTable tabla = new JTable(modeloTabla);
        return tabla;
    }
}
