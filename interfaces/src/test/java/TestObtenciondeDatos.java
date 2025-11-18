
import com.mycompany.interfaces.Modelo.Cliente;
import com.mycompany.interfaces.Modelo.EntidadesDAO.ClienteDAO;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alumno
 */
public class TestObtenciondeDatos {
    
    public static void main(String[] args) {
        ClienteDAO dao =  new ClienteDAO();
        List<Cliente> lista = dao.getAll();
        for (Cliente c : lista) {
            System.out.println(
                c.getId_cliente() + " | " +
                c.getNombre() + " | " +
                c.getApellidos() + " | " +
                c.isVip() + " | " +
                c.getN_visitas() + " | " +
                c.getFecha_alta()
            );
        }
    }
}
