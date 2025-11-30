/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mycompany.interfaces.Modelo.EntidadesDAO.ProductoDAO;
import com.mycompany.interfaces.Modelo.Productos;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author driss
 */
public class ProductosControlador {
    
    private ProductoDAO dao;

    public ProductosControlador() {
    }
    
    public JTable tablaHecha() {
        ProductoDAO dao = new ProductoDAO();
        List<Productos> lista = dao.getAll();
        String[] columnas = {"ID","Nombre", "Tipo", "Stock actual", "Stock maximo", "Proveedor"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        for (int i = 0; i < lista.size(); i++) {
            Productos p = lista.get(i);
            modeloTabla.addRow(new Object[]{    
                p.getId_producto(),
                p.getNombre(),
                p.getTipo(),
                p.getStock_actual(),
                p.getStock_max(),
                p.getProveedor()
            });
        }
        JTable tabla = new JTable(modeloTabla);
        return tabla;
    }
    public JTable tablaSC() {
        ProductoDAO dao = new ProductoDAO();
        List<Productos> lista = dao.getStockCriticos();
        String[] columnas = {"ID", "Producto","Stock actual", "Stock maximo"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        for (int i = 0; i < lista.size(); i++) {
            Productos p = lista.get(i);
            modeloTabla.addRow(new Object[]{
                p.getId_producto(),
                p.getNombre(),
                p.getStock_actual(),
                p.getStock_max(),
            });
        }
        JTable tabla = new JTable(modeloTabla);
        return tabla;
    }
}
