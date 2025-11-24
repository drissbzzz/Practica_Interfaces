/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces.Modelo;

/**
 *
 * @author driss
 */
public class UsuarioPrueba {

    public boolean validar(String usuario, String pass, String rol) {

        if (usuario.equals("Jose") && pass.equals("Apruebame2025_") && rol.equals("Administrador")) {
            return true;
        }

        if (usuario.equals("Driss") && pass.equals("Fucktacobell123") && rol.equals("Empleado")) {
            return true;
        }

        return false;
    }
}
