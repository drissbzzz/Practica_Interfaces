/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces.Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class Productos {
    
    private String nombre;
    private int id_producto;
    private String tipo;
    private int stock_actual;
    private int stock_max;
    private String proveedor;
    private List<Servicios> servicios;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getStock_actual() {
        return stock_actual;
    }

    public void setStock_actual(int stock_actual) {
        this.stock_actual = stock_actual;
    }

    public int getStock_max() {
        return stock_max;
    }

    public void setStock_max(int stock_max) {
        this.stock_max = stock_max;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    
    public void addServicio(Servicios s){
        servicios.add(s);
    }
    public void removeServicio(Servicios s){
        servicios.remove(s);
    }
    public List<Servicios> getServicios(){
        return servicios;
    }

    public Productos(String nombre, int id_producto, String tipo, int stock_actual, int stock_max, String proveedor) {
        this.nombre = nombre;
        this.id_producto = id_producto;
        this.tipo = tipo;
        this.stock_actual = stock_actual;
        this.stock_max = stock_max;
        this.proveedor = proveedor;
        this.servicios= new ArrayList<>();
    }
    
    public Productos(){}
}
