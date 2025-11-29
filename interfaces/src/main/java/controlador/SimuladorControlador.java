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

public class SimuladorControlador {

    private SimulacionListener l;
    private PeluqueriaPSP p;
    private boolean simulacionActiva = false;
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
            for (int i = 1; i <= 3; i++) {
                PeluqueraPSP pelu = new PeluqueraPSP(i, this.p);
                pelu.setDaemon(true); 
                pelu.start(); 
            }
            
            ClienteDAO dao = new ClienteDAO();
            List<Cliente> ListaClientes = dao.getAll();
            clientesPendientes = ListaClientes.size();

            for (int i = 0; i < ListaClientes.size(); i++) {

                Cliente c = ListaClientes.get(i);
                ClientePSP hiloC = new ClientePSP(c, this.p);
                hiloC.setName("Hilo_" + c.getNombre());
                hiloC.start();
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ex) {
                }
            }
        }).start();       
    }
    
    public void pausarReanudar() {
        if (p != null) {
            p.alternarPausa();
        }
    }

    // Botón Detener
    public void detenerSimulacion() {
        if (p != null) {
            totalAtendidos = 0;
            clientesPendientes = 0;
            peluquerasTrabajando = 3;
            peluquerasDurmiendo = 0;
            gananciasTotales = 0.0;
            serviciosCompletados = 0;
            tiempoTotalTrabajado = 0;
            p.detenerTodo();
        }
        simulacionActiva = false; // Permitimos que el botón Start funcione de nuevo
        totalAtendidos = 0;
        notificarEstadisticas();
    }
    
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
        if (l != null) {
            String tiempoMedia = "0 s";
            if (serviciosCompletados > 0) {
                double mediaSegundos = (double) tiempoTotalTrabajado / serviciosCompletados / 1000.0;
                tiempoMedia = String.format("%.1f s", mediaSegundos);
            }
            l.actualizarEstadisticas(totalAtendidos, clientesPendientes,peluquerasTrabajando,peluquerasDurmiendo,gananciasTotales,serviciosCompletados,tiempoMedia);
        }
    }
    
    public void escribirMensaje(String mensaje){
        if (l != null) {
            l.hayNuevoMensaje(""+ mensaje);
        }
    }
    public void actualizarZona(String zona, String cliente, String peluquera, int porcentaje) {
        if (l != null) {
            l.hayCambioZona(zona, cliente, peluquera, porcentaje);
        } 
    }
    public void actualizarPeluquera(int id, int porcentaje, boolean durmiendo) {
        if (l != null) {
            l.hayCambioPeluquera(id, porcentaje, durmiendo);
        }
    }

}
