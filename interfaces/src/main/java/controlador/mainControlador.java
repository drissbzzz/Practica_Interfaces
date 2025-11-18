/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

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
}
