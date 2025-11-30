/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mycompany.interfaces.Modelo.Cliente;
import com.mycompany.interfaces.Modelo.EntidadesDAO.ClienteDAO;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author driss
 */
public class ClientesControlador {

    private ClienteDAO dao; //Metodo que habla con la base de datos

    public ClientesControlador() {
    }

    public JTable tablaHecha() { //Este es el metodo que le pasa la tabla de clientes a la vista
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> lista = dao.getAll(); //Usamos y guardamos la lista de todos los clientes
        String[] columnas = {"ID", "Nombre", "Apellidos", "VIP", "N de visitas", "Fecha de Alta"}; //Preparamos las columnas
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);//Iniciamos la tabla con las columnas y 0 rows
        for (int i = 0; i < lista.size(); i++) { //Por cada registro
            Cliente c = lista.get(i);
            modeloTabla.addRow(new Object[]{ //Le añadimos una row a la tabla con la division de los datos
                c.getId_cliente(),
                c.getNombre(),
                c.getApellidos(),
                c.isVip(),
                c.getN_visitas(),
                c.getFecha_alta()
            });
        }
        JTable tabla = new JTable(modeloTabla); //Pasamos a un objeto aplicable a Swing
        return tabla;
    }

    public JTable tablaVips() { //Funciona igual que el anterior
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> lista = dao.getVips();
        String[] columnas = {"Nombre", "Apellidos", "Num Visitas"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        for (int i = 0; i < lista.size(); i++) {
            Cliente c = lista.get(i);
            modeloTabla.addRow(new Object[]{
                c.getNombre(),
                c.getApellidos(),
                c.getN_visitas()
            });
        }
        JTable tabla = new JTable(modeloTabla);
        return tabla;
    }

    public Cliente manipulacionCliente(int id, String nombre, String apellidos, String vip, int n_visitas, LocalDateTime fecha_alta) {
        //Para modificar un cliente hay que recibir todos los datos nuevos 
        Cliente mod = new Cliente(); //Hay que preparar el objeto plano
        mod.setId_cliente(id);
        mod.setNombre(nombre);
        mod.setApellidos(apellidos);
        if (vip.equals("true")) {
            mod.setVip(true);
        } else {
            mod.setVip(false);
        }
        mod.setN_visitas(n_visitas);
        mod.setFecha_alta(fecha_alta);
        return mod; //Devolver el objeto plano
    }

    public Cliente creacionCliente(String nombre, String apellidos, String vip, int n_visitas) {
        //La unica diferencia con la modificacion es que no se pasa el tiempo ni el id porque se ponen 
        Cliente mod = new Cliente();
        mod.setNombre(nombre);
        mod.setApellidos(apellidos);
        if (vip.equals("true")) {
            mod.setVip(true);
        } else {
            mod.setVip(false);
        }
        mod.setN_visitas(n_visitas);
        mod.setFecha_alta(LocalDateTime.now());
        return mod;
    }

    public void modificarDatos(Cliente Cliente) {
        //Recibimos el objeto cliente y lo modificamos usando el DAO
        ClienteDAO dao = new ClienteDAO();
        dao.actualizarCliente(Cliente);
    }

    public void crearNuevo(Cliente Cliente) {
        //Recibimos el objeto cliente y lo creamos usando el DAO
        ClienteDAO dao = new ClienteDAO();
        dao.agregarCliente(Cliente);
    }

    public Cliente comprobarCliente(int id) {
        //Si queremos usar el filtrado, hay que localizar el Cliente y pasarselo como objeto plano a la vista
        Cliente mod = new Cliente();
        ClienteDAO dao = new ClienteDAO();
        mod = dao.obtenerClientePorId(id);
        return mod;
    }

    public void prepararInsercion() { 
        //Siempre vamos a necesitar esto para añadir los clientes, ya que en diferentes pcs la base de datos tendra secuencias distintas y hay que ponerlas al 
        // maximo para que podamos añadir
        ClienteDAO dao = new ClienteDAO();
        dao.setSecuenciaClientes();
    }
}
