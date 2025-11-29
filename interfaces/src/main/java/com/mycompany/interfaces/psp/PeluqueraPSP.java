/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces.psp;

/**
 *
 * @author driss
 */
public class PeluqueraPSP extends Thread {

    private int id;
    private PeluqueriaPSP p;
    private int clientesAtendidos = 0;
    private int totalClientesHistorico = 0;

    public PeluqueraPSP(int id, PeluqueriaPSP p) {
        this.id = id;
        this.p = p;
    }

    public int getIdPeluquera() {
        return id;
    }

    public int getTotalClientesHistorico() {
        return totalClientesHistorico;
    }

    public void run() {
        while (true) {
            try {
                //Esperando ticket
                if (p.esDetenido()) return;
                p.comprobarPausa();
                p.esperarTimbre();
                boolean heTrabajado = false;

                try {
                    if (p.getPeinado().atender(this)) heTrabajado = true;
                    else if (p.getTinte().atender(this)) heTrabajado = true;
                    else if (p.getCorte().atender(this)) heTrabajado = true;
                    else if (p.getLavado().atender(this)) heTrabajado = true;
                } catch (Exception e) {
                    System.err.println("ERROR EN PELUQUERA " + id + ": " + e.getMessage()); 
                    e.printStackTrace(); // Veremos el error real   
                }

                if (!heTrabajado) {
                    p.tocarTimbre();
                    //Pequeña pausa
                    Thread.sleep(10);
                } else {
                    clientesAtendidos++;
                    totalClientesHistorico++;
                    if (clientesAtendidos >=3 ) {

                        LoggerPSP.escribir("Peluquera " + id + " se toma una siesta...");
                        p.getCntrl().registrarCambioPeluquera(true);
                        p.getCntrl().escribirMensaje("Peluquera " + id + " se toma una siesta...");
                        //Siesta larga
                        int tiempoSiesta = (int) (Math.random() * 2000 + 1000);
                        int partes = 20;
                        for (int i = 1; i <= partes; i++) {
                            int porcentaje = (i * 100) / partes;
                            p.getCntrl().actualizarPeluquera(id, porcentaje, true);
                            Thread.sleep(tiempoSiesta / partes);
                        }
                        LoggerPSP.escribir("Peluquera " + id + " vuelve al trabajo.");
                        p.getCntrl().escribirMensaje("Peluquera " + id + " vuelve al trabajo.");
                        p.getCntrl().registrarCambioPeluquera(false);
                        clientesAtendidos = 0;
                        p.getCntrl().actualizarPeluquera(id, 0, false);                       
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
