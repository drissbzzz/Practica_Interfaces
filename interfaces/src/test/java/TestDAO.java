
import com.mycompany.interfaces.Modelo.Cliente;
import com.mycompany.interfaces.Modelo.EntidadesDAO.ClienteDAO;
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
        ClienteDAO dao = new ClienteDAO();

        /*Cliente nuevo = new Cliente("Driss", "Beidouri", true, 1, LocalDateTime.now());
        boolean agregado = dao.agregarCliente(nuevo);

        if (agregado) {
            System.out.println("Cliente agregado correctamente.");
        } else {
            System.out.println("Error al agregar cliente.");
        }*/
        Cliente c = dao.obtenerClientePorId(502); 
        if (c != null) {
            System.out.println("Cliente encontrado: " + c.getNombre() + " " + c.getApellidos());
        } else {
            System.out.println("Cliente no encontrado");
        }
        /*c.setNombre("Driss Actualizado");
        boolean actualizado = dao.actualizarCliente(c);
        System.out.println(actualizado ? "Cliente actualizado" : "Error al actualizar");*/
        boolean eliminado = dao.eliminarCliente(c.getId_cliente());
        System.out.println(eliminado ? "Cliente eliminado" : "Error al eliminar");
        
    }
}
