/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces.Modelo;

/**
 *
 * @author alumno
 */
public class Peluqueras {
    
    private int id_peluquera;
    private String nombre;
    private int anhos_exp;
    private String estado;
    private int id_Servicios;

    public int getId_peluquera() {
        return id_peluquera;
    }

    public void setId_peluquera(int id_peluquera) {
        this.id_peluquera = id_peluquera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnhos_exp() {
        return anhos_exp;
    }

    public void setAnhos_exp(int anhos_exp) {
        this.anhos_exp = anhos_exp;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_Servicios() {
        return id_Servicios;
    }

    public void setId_Servicios(int id_Servicios) {
        this.id_Servicios = id_Servicios;
    }

    public Peluqueras(int id_peluquera, String nombre, int anhos_exp, String estado, int id_Servicios) {
        this.id_peluquera = id_peluquera;
        this.nombre = nombre;
        this.anhos_exp = anhos_exp;
        this.estado = estado;
        this.id_Servicios = id_Servicios;
    }
    
    public Peluqueras(){}
}
