/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces.Modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class Citas {
    
    private int id_cita;
    private int tiempo_tardado;
    private LocalDateTime fecha;
    private int id_Cliente;
    private List<Servicios> servicios;

    public int getId_cita() {
        return id_cita;
    }

    public void setId_cita(int id_cita) {
        this.id_cita = id_cita;
    }

    public int getTiempo_tardado() {
        return tiempo_tardado;
    }

    public void setTiempo_tardado(int tiempo_tardado) {
        this.tiempo_tardado = tiempo_tardado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(int id_Cliente) {
        this.id_Cliente = id_Cliente;
    }
    
    public void addServicio(Servicios s) {
        servicios.add(s);
    }

    public void removeServicio(Servicios s) {
        servicios.remove(s);
    }

    public List<Servicios> getServicios() {
        return servicios;
    }

    public Citas(int id_cita, int tiempo_tardado, LocalDateTime fecha, int id_Cliente) {
        this.id_cita = id_cita;
        this.tiempo_tardado = tiempo_tardado;
        this.fecha = fecha;
        this.id_Cliente = id_Cliente;
        this.servicios = new ArrayList<>();
    }
    
    public Citas(){}
 }
