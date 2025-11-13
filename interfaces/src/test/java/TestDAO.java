
import com.mycompany.interfaces.Modelo.Cliente;
import com.mycompany.interfaces.Modelo.EntidadesDAO.ClienteDAO;
import com.mycompany.interfaces.Modelo.EntidadesDAO.ServiciosDAO;
import com.mycompany.interfaces.Modelo.Servicios;
import java.time.LocalDateTime;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author driss
 */
public class TestDAO {

    public static void main(String[] args) {
        ServiciosDAO dao = new ServiciosDAO();

        /*Servicios nuevo = new Servicios("Abrillantado calva", 150, 10);
        boolean agregado = dao.agregarServicio(nuevo);
        System.out.println(agregado ? "Agregado correctamente" : "Error al agregar");*/
        Servicios s = dao.obtenerServicioPorId(11); 
        /*if (s != null) {
            System.out.println("Dato encontrado: " + s.getNombre() + " " + s.getPrecio());
        } else {
            System.out.println("Dato no encontrado");
        }*/
        /*s.setNombre("Brilli a tu calva");
        boolean actualizado = dao.actualizarServicio(s);
        System.out.println(actualizado ? "Servicio actualizado" : "Error al actualizar");*/
        boolean eliminado = dao.eliminarServicio(11);
        System.out.println(eliminado ? "Dato eliminado" : "Error al eliminar");
        
    }
}
