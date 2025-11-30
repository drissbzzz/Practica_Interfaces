/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces.Modelo;

/**
 *
 * @author driss
 */
public class Ticket {
 
    private int importe;
    private String metodo_pago;
    private int id_ticket;
    private int id_Cita;

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public int getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public int getId_Cita() {
        return id_Cita;
    }

    public void setId_Cita(int id_Cita) {
        this.id_Cita = id_Cita;
    }

    public Ticket() {
    }

    public Ticket(int importe, String metodo_pago, int id_ticket, int id_Cita) {
        this.importe = importe;
        this.metodo_pago = metodo_pago;
        this.id_ticket = id_ticket;
        this.id_Cita = id_Cita;
    }
   
}
