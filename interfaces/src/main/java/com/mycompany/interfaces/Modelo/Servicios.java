/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces.Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author driss
 */
public class Servicios {
    
    private int id_servicios;
    private String nombre;
    private int precio;
    private int duracion_media;
    private List<Citas> citas;
    private List<Productos> productos;

    public int getId_servicios() {
        return id_servicios;
    }

    public void setId_servicios(int id_servicios) {
        this.id_servicios = id_servicios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getDuracion_media() {
        return duracion_media;
    }

    public void setDuracion_media(int duracion_media) {
        this.duracion_media = duracion_media;
    }
    public void addCita(Citas c) {
        citas.add(c);
    }

    public void removeCita(Citas c) {
        citas.remove(c);
    }

    public List<Citas> getCitas() {
        return citas;
    }
    public void addProducto(Productos p) {
        productos.add(p);
    }

    public void removeProducto(Productos p) {
        productos.remove(p);
    }

    public List<Productos> getProductos() {
        return productos;
    }
    
    public Servicios(int id_servicios, String nombre, int precio, int duracion_media) {
        this.id_servicios = id_servicios;
        this.nombre = nombre;
        this.precio = precio;
        this.duracion_media = duracion_media;
        this.citas = new ArrayList<>();
        this.productos = new ArrayList<>();
    }
    public Servicios(String nombre, int precio, int duracion_media) {
        this.nombre = nombre;
        this.precio = precio;
        this.duracion_media = duracion_media;
        this.citas = new ArrayList<>();
        this.productos = new ArrayList<>();
    }
    
    public Servicios(){}
  
}
