/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mycompany.interfaces.Modelo.EntidadesDAO.PeluquerasDAO;
import com.mycompany.interfaces.Modelo.Peluqueras;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author driss
 */
public class PeluquerasControlador {
    
    private PeluquerasDAO dao;

    public PeluquerasControlador() {
    }
    
       public JTable tablaHecha() {
        dao = new PeluquerasDAO();
        List<Peluqueras> lista = dao.getAll();
        String[] columnas = {"ID", "Nombre", "Años de experiencia", "Estado"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        for (int i = 0; i < lista.size(); i++) {
            Peluqueras p = lista.get(i);
            modeloTabla.addRow(new Object[]{
                p.getId_peluquera(),
                p.getNombre(),
                p.getAnhos_exp(),
                p.getEstado(),
            });
        }
        JTable tabla = new JTable(modeloTabla);
        return tabla;
    }
}
