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
 * @author alumno
 */
public class main {

        public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatLightLaf.setup();
        UIManager.put( "TabbedPane.selectedBackground", Color.white );

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }
}
