/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mycompany.interfaces.Modelo.Cliente;
import com.mycompany.interfaces.Modelo.EntidadesDAO.ClienteDAO;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alumno
 */
public class ClientesControlador {
    
    private ClienteDAO dao;

    public ClientesControlador() {
    }
    
    public JTable tablaHecha() {
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> lista = dao.getAll();
        String[] columnas = {"ID", "Nombre", "Apellidos", "VIP", "N de visitas", "Fecha de Alta"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        for (int i = 0; i < lista.size(); i++) {
            Cliente c = lista.get(i);
            modeloTabla.addRow(new Object[]{
                c.getId_cliente(),
                c.getNombre(),
                c.getApellidos(),
                c.isVip(),
                c.getN_visitas(),
                c.getFecha_alta()
            });
        }
        JTable tabla = new JTable(modeloTabla);
        return tabla;
    }
    
    /*public static void main(String[] args) {

        ClientesControlador hola = new ClientesControlador();
        JTable tabla = hola.tablaHecha();
        if (tabla.getRowCount() == 0) {
            System.out.println("La tabla está vacía o no se ha cargado correctamente.");
        } else {
            System.out.println("La tabla se ha cargado correctamente. Contenido:");
        }
    }FUNCIONA*/

}
