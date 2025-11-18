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
    
    public mainControlador(){
        cliCon = new ClientesControlador();
        
    }
    
    public JTable iniciar(String tablaSeleccionada) {
        
        switch(tablaSeleccionada){
            case "Clientes":
            {
                return cliCon.tablaHecha();
            }
            default: return null;
        }

    }
}
