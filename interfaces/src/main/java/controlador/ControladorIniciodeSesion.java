/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mycompany.interfaces.Modelo.UsuarioPrueba;

/**
 *
 * @author driss
 */
public class ControladorIniciodeSesion {
    
    
    private UsuarioPrueba prueba;

    public ControladorIniciodeSesion(UsuarioPrueba prueba) {
        this.prueba = prueba;
    }

    public ControladorIniciodeSesion() {
    }

    public boolean autenticar(String usuario, String pass, String rol) {
        return prueba.validar(usuario, pass, rol);
    }
}
