/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces.Modelo;

import java.time.LocalDateTime;

/**
 *
 * @author driss
 */
public class Cliente {
    
    //Para cumplir con la estructura MVC, he creado una clase para que represente cada tabla de la base de datos
    
    //Atributos (columnas) de la tabla
    private int id_cliente;
    private String nombre;
    private String apellidos;
    private boolean vip;
    private int n_visitas;
    private LocalDateTime fecha_alta;
    
    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public int getN_visitas() {
        return n_visitas;
    }

    public void setN_visitas(int n_visitas) {
        this.n_visitas = n_visitas;
    }

    public LocalDateTime getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(LocalDateTime fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    
    public Cliente(){}
    
    public Cliente(int id_cliente, String nombre, String apellidos, boolean vip, int n_visitas, LocalDateTime fecha_alta) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.vip = vip;
        this.n_visitas = n_visitas;
        this.fecha_alta = fecha_alta;
    }
    public Cliente(String nombre, String apellidos, boolean vip, int n_visitas, LocalDateTime fecha_alta) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.vip = vip;
        this.n_visitas = n_visitas;
        this.fecha_alta = fecha_alta;
    }
    
      
       
}
