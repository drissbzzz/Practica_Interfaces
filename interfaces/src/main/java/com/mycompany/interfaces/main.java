/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.interfaces;

import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.interfaces.Vista.Login;
import com.mycompany.interfaces.Vista.Vista;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author driss
 */
public class main {

        public static void main(String args[]) {      
        FlatLightLaf.setup(); //importamos una libreria look and feel que le da un aspecto más moderno y nos permite
        //la implementacion del modo oscuro de manera mas sencilla
        
        //Personalizacion de los elementos: TabbedPane y ProgressBar
        UIManager.put( "TabbedPane.selectedBackground", Color.white );
        UIManager.put( "ProgressBar.arc", 999 );

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }
}
