
import com.mycompany.interfaces.Modelo.Cliente;
import com.mycompany.interfaces.Modelo.EntidadesDAO.ClienteDAO;
import com.mycompany.interfaces.Vista.Vista;
import controlador.ClientesControlador;
import controlador.mainControlador;
import java.util.List;
import javax.swing.JTable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alumno
 */
public class TestMostrar {
    
    public static void main(String[] args) {
        mainControlador mainCon = new mainControlador();
        
        // Simulamos la selección de "Clientes"
        JTable tabla = mainCon.iniciar("Clientes");

        if (tabla == null) {
            System.out.println("No se devolvió ninguna tabla.");
        } else if (tabla.getRowCount() == 0) {
            System.out.println("La tabla está vacía.");
        } else {
            System.out.println("Tabla cargada correctamente.");
        }
    }
}
