/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mycompany.interfaces.Modelo.Cliente;
import java.time.LocalDateTime;
import javax.swing.JTable;


/**
 *
 * @author alumno
 */
 public class mainControlador{

    private ClientesControlador cliCon;
    private ProductosControlador proCon;
    private PeluquerasControlador pelCon;
    private ServiciosControlador serCon; 
   
    
    public mainControlador(){
        cliCon = new ClientesControlador();
        proCon= new ProductosControlador();
        pelCon= new PeluquerasControlador();
        serCon = new ServiciosControlador();
    }
    
    public JTable iniciar(String tablaSeleccionada) {
        
        switch(tablaSeleccionada){
            case "Clientes":
            {
                return cliCon.tablaHecha();
            }
            case "Productos":
            {
                return proCon.tablaHecha();
            }
            case "Peluqueras":
            {
                return pelCon.tablaHecha();
            }
            case "Servicios":
            {
                return serCon.tablaHecha();
            }
            default: return null;
        }
    }
    
    public JTable iniciarVips(){
        return cliCon.tablaVips();
    }
    public JTable iniciarSMR(){
        return serCon.tablaSMR();              
    }
    public JTable iniciarSC(){
        return proCon.tablaSC();
    }
    
    public Cliente comprobarDatos(int id){
        return cliCon.comprobarCliente(id);        
    }
    public void modificacionCliente(int id, String nombre, String apellidos, String vip, int n_visitas, LocalDateTime fecha_alta){      
        cliCon.modificarDatos(cliCon.manipulacionCliente(id, nombre, apellidos, vip, n_visitas, fecha_alta));
    }
    public void añadirCliente(String nombre, String apellidos, String vip, int n_visitas){      
        cliCon.crearNuevo(cliCon.creacionCliente (nombre, apellidos, vip, n_visitas));
    }
   
}
