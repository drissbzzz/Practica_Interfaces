/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces.psp;

import java.util.concurrent.Semaphore;
import controlador.SimuladorControlador;

public class PeluqueriaPSP {

    private SimuladorControlador cntrl;

    private SitioAnticuado lavado;
    private SitioAnticuado corte;
    private SitioAnticuado tinte;
    private SitioAnticuado peinado;

    private Semaphore timbreGeneral = new Semaphore(0);

    // Variables para controlar el estado de simulacion
    private boolean pausado = false;
    private boolean detenido = false;
    private final Object cerrojoPausa = new Object(); //Este objeto es el que permitira pausar las cosas hasta que el decida liberarlas de nuevo

    public PeluqueriaPSP(SimuladorControlador cntrl) {
        this.cntrl = cntrl;
        this.lavado = new SitioAnticuado("Lavado", this);
        this.corte = new SitioAnticuado("Corte", this);
        this.tinte = new SitioAnticuado("Tinte", this);
        this.peinado = new SitioAnticuado("Peinado", this);
    }

    public SimuladorControlador getCntrl() {
        return cntrl;
    }

    public SitioAnticuado getLavado() {
        return lavado;
    }

    public SitioAnticuado getCorte() {
        return corte;
    }

    public SitioAnticuado getTinte() {
        return tinte;
    }

    public SitioAnticuado getPeinado() {
        return peinado;
    }

    public void alternarPausa() {
        synchronized (cerrojoPausa) { //Es snchronized porque si no no podemos usar los metodos notify ni wait y nos evita condiciones de carrera a la hora de pausar
            pausado = !pausado; // Cambia de true a false y viceversa
            if (!pausado) {
                cerrojoPausa.notifyAll(); // Despierta todos los hilos
            } else {
            }
        }
    }

    public void comprobarPausa() {
        synchronized (cerrojoPausa) {
            while (pausado) {
                try {
                    cerrojoPausa.wait(); // Siempre que este pausado en true todos esperan la notificacion dormidos
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public void detenerTodo() {
        this.detenido = true;
        // Despausamos por si acaso estaban dormidos, para que puedan morir
        synchronized (cerrojoPausa) {
            pausado = false;
            cerrojoPausa.notifyAll();
        }
    }

    public boolean esDetenido() {
        return detenido;
    }

    // El cliente llama a esto cuando se sienta en cualquier zona
    public void tocarTimbre() {
        timbreGeneral.release(); // Se activa el timbre para despertar a una peluquera
    }

    // La peluquera llama a esto para echarse la siesta (pero no la que le corresponde) hasta que haya trabajo
    public void esperarTimbre() {
        try {
            timbreGeneral.acquire(); // Se duerme si es 0. Despierta si es 1.
        } catch (InterruptedException e) {
            System.out.println("Error en el ticket general");
        }
    }

}
