/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mycompany.interfaces.Modelo.Cliente;
import com.mycompany.interfaces.Modelo.EntidadesDAO.ClienteDAO;
import com.mycompany.interfaces.psp.ClientePSP;     
import com.mycompany.interfaces.psp.PeluqueraPSP;
import com.mycompany.interfaces.psp.PeluqueriaPSP;
import controlador.SimulacionListener;
import java.util.List;

public class SimuladorControlador { //Actua como si fuese el main de PSP

    private SimulacionListener l;
    private PeluqueriaPSP p;
    private boolean simulacionActiva = false;
    //Variables para contabilizar datos
    private int totalAtendidos = 0;
    private int clientesPendientes = 0; 
    private int peluquerasTrabajando = 3;
    private int peluquerasDurmiendo = 0;
    private double gananciasTotales = 0.0;
    private int serviciosCompletados = 0;
    private int tiempoTotalTrabajado = 0;
    

    public void setListener(SimulacionListener l){
        this.l = l;
    }

    public void iniciarSimulacion() {
        this.p = new PeluqueriaPSP(this);        
        if (simulacionActiva) {
            return;
        }
        simulacionActiva = true;
        new Thread(() -> { //Todo este proceso lo realizará un hilo aparte de la vista
            //para evitar bloquearla
            for (int i = 1; i <= 3; i++) { //Se crean las 3 peluqueras
                PeluqueraPSP pelu = new PeluqueraPSP(i, this.p);
                pelu.setDaemon(true); //Asi desaparecen al final del programa cuando no haya mas hilos
                pelu.start(); 
            }
            
            ClienteDAO dao = new ClienteDAO();
            List<Cliente> ListaClientes = dao.getAll();
            clientesPendientes = ListaClientes.size();

            for (int i = 0; i < ListaClientes.size(); i++) {
                //En este bucle aparte de inicializar los hilos, se les da los datos de la base de datos
                Cliente c = ListaClientes.get(i);
                ClientePSP hiloC = new ClientePSP(c, this.p);//Aqui le pasamos al hilo la peluqueria a la que pertenece y los datos de cliente
                hiloC.setName("Hilo_" + c.getNombre());
                hiloC.start();
                try {
                    Thread.sleep(1500); //Para evitar una avalancha de clientes y que lleguen de manera escalonada
                } catch (InterruptedException ex) {
                }
            }
        }).start();   //Comienza el proceso    
    }
    
    public void pausarReanudar() {
        if (p != null) {
            p.alternarPausa();
        }
    }

    // Botón Detener
    public void detenerSimulacion() {
        simulacionActiva = false; //Importante para que todo se entere de que la simulacion paro
        if (p != null) { //Reinicio de las variables de contabilizacion
            totalAtendidos = 0;
            clientesPendientes = 0;
            peluquerasTrabajando = 3;
            peluquerasDurmiendo = 0;
            gananciasTotales = 0.0;
            serviciosCompletados = 0;
            tiempoTotalTrabajado = 0;
            totalAtendidos = 0;
            p.detenerTodo();
        }     
        notificarEstadisticas(); //Que la interfaz procese la detencion y lo muestre
    }
    
    //Actualizacion de los datos en vivo
    //Estos metodos son synchronizd para evitar casos donde se escriba a la vez y se pierdan datos al sobreescribirse entre ellos sin ver el cambio del otro
    public synchronized void sumarGananciaServicio(double importe, int tiempo) {
        gananciasTotales += importe;
        serviciosCompletados++; 
        tiempoTotalTrabajado += tiempo;
        notificarEstadisticas();
    }
    public synchronized void registrarSalidaCliente() {
        totalAtendidos++;          
        if (clientesPendientes > 0) {
            clientesPendientes--;
        }
        notificarEstadisticas();
    }
    public synchronized void registrarCambioPeluquera(boolean seVaADormir) {
        if (seVaADormir) {
            peluquerasTrabajando--;
            peluquerasDurmiendo++;
        } else {
            peluquerasTrabajando++;
            peluquerasDurmiendo--;
        }
        notificarEstadisticas();
    }
    public synchronized void notificarEstadisticas() {
        if (l != null && simulacionActiva ) {// Si la simulacion esta activa y hay un listener asignado
            String tiempoMedia = "0 s";
            if (serviciosCompletados > 0) { // Si se ha hecho un sevicio o mas
                double mediaSegundos = (double) tiempoTotalTrabajado / serviciosCompletados / 1000.0;// Se calcula la media de lo que tardan los servicios
                tiempoMedia = String.format("%.1f s", mediaSegundos);//Se le da formato a la media que hemos calculado antes
            }
            l.actualizarEstadisticas(totalAtendidos, clientesPendientes,peluquerasTrabajando,peluquerasDurmiendo,gananciasTotales,serviciosCompletados,tiempoMedia);
            //Se llama al metodo actualizarestadisticas y se le pasa todo al listener de la vista
        }
    }
    
    public void escribirMensaje(String mensaje){
        if (l != null && simulacionActiva) {
            l.hayNuevoMensaje(""+ mensaje);
        }
    }
    public void actualizarZona(String zona, String cliente, String peluquera, int porcentaje) {
        if (l != null && simulacionActiva) {
            l.hayCambioZona(zona, cliente, peluquera, porcentaje);
        } 
    }
    public void actualizarPeluquera(int id, int porcentaje, boolean durmiendo) {
        if (l != null && simulacionActiva) {
            l.hayCambioPeluquera(id, porcentaje, durmiendo);
        }
    }

}
