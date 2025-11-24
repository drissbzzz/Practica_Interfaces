/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mycompany.interfaces.Modelo.Cliente;
import com.mycompany.interfaces.Modelo.EntidadesDAO.ClienteDAO;
import java.time.LocalDateTime;
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
    public JTable tablaVips() {
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> lista = dao.getVips();
        String[] columnas = {"Nombre", "Apellidos","Num Visitas"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        for (int i = 0; i < lista.size(); i++) {
            Cliente c = lista.get(i);
            modeloTabla.addRow(new Object[]{
                c.getNombre(),
                c.getApellidos(),
                c.getN_visitas()
            });
        }
        JTable tabla = new JTable(modeloTabla);
        return tabla;
    }
    
    public Cliente manipulacionCliente(int id, String nombre, String apellidos, String vip, int n_visitas, LocalDateTime fecha_alta){
        Cliente mod = new Cliente();
        mod.setId_cliente(id);
        mod.setNombre(nombre);
        mod.setApellidos(apellidos);
        if(vip.equals("true")){
            mod.setVip(true);
        }
        else{
            mod.setVip(false);
        }
        mod.setN_visitas(n_visitas);
        mod.setFecha_alta(fecha_alta);
        return mod;
    }
    public Cliente creacionCliente(String nombre, String apellidos, String vip, int n_visitas){
        Cliente mod = new Cliente();
        mod.setNombre(nombre);
        mod.setApellidos(apellidos);
        if(vip.equals("true")){
            mod.setVip(true);
        }
        else{
            mod.setVip(false);
        }
        mod.setN_visitas(n_visitas);
        mod.setFecha_alta(LocalDateTime.now());
        return mod;
    }
    public void modificarDatos(Cliente Cliente){
        ClienteDAO dao = new ClienteDAO();
        dao.actualizarCliente(Cliente);      
    }
    public void crearNuevo(Cliente Cliente){
        ClienteDAO dao = new ClienteDAO();
        dao.agregarCliente(Cliente);
    }
    public Cliente comprobarCliente(int id){
        Cliente mod = new Cliente();
        ClienteDAO dao = new ClienteDAO();
        mod = dao.obtenerClientePorId(id);
        return mod;        
    }
    public void prepararInsercion(){
        ClienteDAO dao = new ClienteDAO();
        dao.setSecuenciaClientes();
    }
         
                       
    /*public static void main(String[] args) {

    }*/
    //FUNCIONA

}
