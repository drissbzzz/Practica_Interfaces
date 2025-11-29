/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces.psp;

import com.mycompany.interfaces.Modelo.Cliente;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author driss
 */
public class SitioAnticuado {

    private PeluqueriaPSP p;
    private String nombre;
    
    private Semaphore silla = new Semaphore(1);
    private ReentrantLock cerrojoPeluquera = new ReentrantLock();
    private Semaphore finServicio = new Semaphore(0);
    private boolean ocupado = false;
    
    
    private ClientePSP hiloActual = null; //Variable para identificar que hilo esta ocupando la región critica

    public SitioAnticuado(String nombre, PeluqueriaPSP p) {
        this.nombre = nombre;
        this.p = p;
    }

    public void entrar(ClientePSP c) {
        try {
            silla.acquire();  // Cliente coge un ticket del semaforo de la silla   
            hiloActual = c; 
            Cliente datos = c.getC();
            LoggerPSP.escribir("Cliente " + datos.getId_cliente() + " se sienta en " + nombre + " y espera...");
            p.getCntrl().escribirMensaje("Cliente " + datos.getId_cliente() + " se sienta en " + nombre + " y espera...");
            ocupado = true; //Para que la peluquera antes de actuar confirme que hay un cliente
            String nombreCli = c.getC().getNombre();
            p.getCntrl().actualizarZona(nombre, nombreCli, "Esperando...", 0);
            p.tocarTimbre();// Cliente libera un ticket general para indicar que hay trabajo pendiente
            finServicio.acquire(); //Intenta adquirir un ticket de servicio terminado
            LoggerPSP.escribir("Cliente " + datos.getId_cliente() + " sale de " + nombre);
            p.getCntrl().escribirMensaje("Cliente " + datos.getId_cliente() + " sale de " + nombre);
            silla.release(); //Libera el ticket para que otro pueda ocupar la silla
        } catch (InterruptedException e) {
            System.out.println("Error en la entrada del cliente");
        }
    }

    public boolean atender(PeluqueraPSP pelu) {

        if (cerrojoPeluquera.tryLock()) { //La peluquera intenta coger la llave a la atencion
            try {
                // Si no hay cliente me voy.
                if (!ocupado) {
                    return false;
                }
                if (hiloActual == null) {
                    return false;
                }
                String nombreCli = hiloActual.getC().getNombre();
                String nombrePelu = "Peluquera " + pelu.getIdPeluquera();
                LoggerPSP.escribir("Peluquera " + pelu.getIdPeluquera() + " atendiendo en " + nombre);
                p.getCntrl().escribirMensaje("Peluquera " + pelu.getIdPeluquera() + " atendiendo en " + nombre);
                //Actualización de la barra de progreso
                int tiempoSer = (int) (Math.random() * 4000 + 2000);
                int partes = 20;

                for (int i = 1; i <= partes; i++) {
                    if (p.esDetenido()) return false;
                    p.comprobarPausa();
                    try {
                        Thread.sleep(tiempoSer / partes);
                    } catch (Exception e) {
                    }
                    int porcentaje = (i * 100) / partes;
                    p.getCntrl().actualizarZona(nombre, nombreCli, nombrePelu, porcentaje);
                }      
                double precio = 0;
                switch (nombre) {
                    case "Lavado":
                        precio = 18.0;
                        break;
                    case "Corte":
                        precio = 30.0;
                        break;
                    case "Tinte":
                        precio = 40.0;
                        break;
                    case "Peinado":
                        precio = 23.0;
                        break;
                }               
                p.getCntrl().sumarGananciaServicio(precio, tiempoSer);
                finServicio.release();
                p.getCntrl().actualizarZona(nombre, "", "", 0);// Aviso de que el servicio ya está realizado         
                ocupado = false; // La peluquera termina de atender y pone como que el sitio ya no está ocupado
                return true;
            } finally {
                cerrojoPeluquera.unlock();
            }
        } else {}
        return false;
    }

}
