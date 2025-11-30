/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mycompany.interfaces.Modelo.Cliente;
import com.mycompany.interfaces.Modelo.EntidadesDAO.ExportadorDatos;
import java.io.File;
import java.time.LocalDateTime;
import javax.swing.JTable;


/**
 *
 * @author alumno
 */
 public class mainControlador{

    //Se declaran todos los subcontroladres
    private ClientesControlador cliCon;
    private ProductosControlador proCon;
    private PeluquerasControlador pelCon;
    private ServiciosControlador serCon; 
    private SimuladorControlador simCon;
    
    public mainControlador(){
        //Se inician al iniciar el main
        cliCon = new ClientesControlador();
        proCon= new ProductosControlador();
        pelCon= new PeluquerasControlador();
        serCon = new ServiciosControlador();
        simCon = new SimuladorControlador();              
    }
    
    
    public JTable iniciar(String tablaSeleccionada) {
        
        //Decide que tabla mandar a la interfaz segun el string que recibio
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
    
    public void pausarSimulacionDesdeVista() {
        if (simCon != null) simCon.pausarReanudar();
    }
    
    public void detenerSimulacion() {
        if (simCon != null) simCon.detenerSimulacion();
    }
    
    public void iniciarListener(SimulacionListener l){ //Le envia la referencia del listener de la vista al controlador de la simulacon
        simCon.setListener(l);
    }
    public void comenzarSimulacion() {
            simCon.iniciarSimulacion();
    }
    public void modificacionCliente(int id, String nombre, String apellidos, String vip, int n_visitas, LocalDateTime fecha_alta){      
        cliCon.modificarDatos(cliCon.manipulacionCliente(id, nombre, apellidos, vip, n_visitas, fecha_alta));
    }
    public void añadirCliente(String nombre, String apellidos, String vip, int n_visitas){      
        prepararInsercion();
        cliCon.crearNuevo(cliCon.creacionCliente (nombre, apellidos, vip, n_visitas));
    }
    public void prepararInsercion(){
        cliCon.prepararInsercion();
    }
    
    public boolean exportarTabla(String nombreTablaVisual) { //Metodo al que llama la vista cuando se aprieta el boton de export
        String nombreTablaBDD = nombreTablaVisual.toUpperCase(); //Le damos el formato mayuscula al titulo de la tabla seleccionada
        String nombreArchivo = nombreTablaVisual + ".csv";//Le damos un nombre al archivo
        File archivo = new File(nombreArchivo);//Creamos el archivo
        ExportadorDatos e = new ExportadorDatos(); //Iniciamos la clase exportadora de datos
        return e.generarCSV(nombreArchivo, archivo); //Devolvemos si todo se realizo correctamente para enviar un mensaje de acuerdo al resultado
    }
   
}
