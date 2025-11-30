/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author driss
 */
public interface SimulacionListener { //Para evitar pasar la vista al controlador y estropear la estructura del MVC, el controlador avisa al listener y este 
    // avisa a la vista de que hay que actualizarse
    
    public void hayNuevoMensaje(String mensaje);
    public void hayCambioZona(String zona, String cliente, String peluquera, int porcentaje);
    public void hayCambioPeluquera(int id, int porcentaje, boolean durmiendo);
    public void actualizarEstadisticas(int atendidos, int pendientes, int peluquerasActivas, int peluquerasSiesta, double ganancias, int serviciosCompletados, String tiempoMedia);
}
