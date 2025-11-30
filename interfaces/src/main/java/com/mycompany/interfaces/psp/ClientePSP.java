
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces.psp;

import com.mycompany.interfaces.Modelo.Cliente;

/**
 *
 * @author driss
 */
public class ClientePSP extends Thread { //Esta clase es el hilo que hará la concuerrencia para el apartado Simulacion

    private Cliente c;
    private PeluqueriaPSP p;

    public Cliente getC() {
        return c;
    } //Recibe un cliente para poder ajustarse a la base de datos y sincronizarse

    public ClientePSP(Cliente c, PeluqueriaPSP peluqueria) {
        this.c = c;
        this.p = peluqueria;
    }

    @Override
    public void run() {
        
        int id = c.getId_cliente(); //Se coge el id del cliente
        try {
            p.getLavado().entrar(this);
            p.getCorte().entrar(this);
            p.getTinte().entrar(this);
            p.getPeinado().entrar(this);
            LoggerPSP.escribir("Cliente " + id + " ha salido fresquisimo y muy contento");
            p.getCntrl().escribirMensaje("Cliente " + id + " ha salido fresquisimo y muy contento");
            p.getCntrl().registrarSalidaCliente();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
