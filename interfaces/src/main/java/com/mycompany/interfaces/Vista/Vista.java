/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.interfaces.Vista;

import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.interfaces.Modelo.Cliente;
import controlador.SimulacionListener;
import controlador.mainControlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author alumno
 */
public class Vista extends javax.swing.JFrame implements SimulacionListener {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Vista.class.getName());
    mainControlador ctrl = new mainControlador();
    /**
     * Creates new form Vista
     */
    public Vista() {
        initComponents();
        initStyles();
        setResizable(false);
        iniciarTablas();
        ctrl.iniciarListener(this);
    }
    
    public Vista(String rol) {
        initComponents();
        initStyles();
        setResizable(false);
        iniciarTablas();
        aplicarPermisos(rol);
        ctrl.iniciarListener(this);
    }

    private void aplicarPermisos(String rol) {
        if (rol.equals("Empleado")) {
            añadirBoton.setEnabled(false);
            editarBoton.setEnabled(false);
        }       
    }
    
    public void mostrarTablaBDD(JTable tabladatos){
        JScrollPane scrollPane = new JScrollPane(tabladatos);
        scrollPane.setPreferredSize(new java.awt.Dimension(693, 421));
        panelTabla.removeAll();
        panelTabla.add(scrollPane, BorderLayout.CENTER);
        panelTabla.revalidate();
        panelTabla.repaint();
    }
    public void mostrarTablaVips(JTable tabladatos){
        JScrollPane scrollPane = new JScrollPane(tabladatos);
        scrollPane.setPreferredSize(new java.awt.Dimension(328, 428));
        tablaCV.removeAll();
        tablaCV.add(scrollPane, BorderLayout.CENTER);
        tablaCV.revalidate();
        tablaCV.repaint();   
    }
    public void mostrarTablaSMR(JTable tabladatos){
        JScrollPane scrollPane = new JScrollPane(tabladatos);
        scrollPane.setPreferredSize(new java.awt.Dimension(328, 428));
        tablaSMR.removeAll();
        tablaSMR.add(scrollPane, BorderLayout.CENTER);
        tablaSMR.revalidate();
        tablaSMR.repaint();   
    }
    public void mostrarTablaSC(JTable tabladatos){
        JScrollPane scrollPane = new JScrollPane(tabladatos);
        scrollPane.setPreferredSize(new java.awt.Dimension(328, 428));
        tablaSC.removeAll();
        tablaSC.add(scrollPane, BorderLayout.CENTER);
        tablaSC.revalidate();
        tablaSC.repaint();   
    }

    public void mostrarCliente(Cliente c) {
        NombreField.setText(c.getNombre());
        ApellidosField.setText(c.getApellidos());
        VipField.setText(String.valueOf(c.isVip()));
        NDVisitasField.setText(String.valueOf(c.getN_visitas()));
        FechadeAltaField.setText(String.valueOf(c.getFecha_alta()));             
    }
    public void initStyles(){
        añadirBoton.putClientProperty( "JButton.buttonType", "roundRect" );
        editarBoton.putClientProperty( "JButton.buttonType", "roundRect" );
        editarBoton.putClientProperty( "FlatLaf.styleClass", "h4" );
        añadirBoton.putClientProperty( "FlatLaf.styleClass", "h4" );
        seleccionTabla.putClientProperty("FlatLaf.styleClass", "h4");
        seleccionTabla.putClientProperty( "JComponent.roundRect", true );
        tituloLabel.putClientProperty( "FlatLaf.style", "font: $h1.font" );
        NombreLabel.putClientProperty( "FlatLaf.style", "font: bold " );
        IDLabel.putClientProperty( "FlatLaf.style", "font: bold " );
        ApellidosLabel.putClientProperty( "FlatLaf.style", "font: bold " );
        VipLabel.putClientProperty( "FlatLaf.style", "font: bold " );  
        NDeVisitasLabel.putClientProperty( "FlatLaf.style", "font: bold " );
        FechadAltaLabel.putClientProperty( "FlatLaf.style", "font: bold " );
        tituloCVips.putClientProperty( "FlatLaf.style", "font: bold " );
        tituloServiciosR.putClientProperty( "FlatLaf.style", "font: bold " );
        tituloSCrit.putClientProperty( "FlatLaf.style", "font: bold " );
        stop.putClientProperty( "JButton.buttonType", "roundRect" );
        start.putClientProperty( "JButton.buttonType", "roundRect" );
        pause.putClientProperty( "JButton.buttonType", "roundRect" );
    }
    
    public void iniciarTablas(){
        tablaCV.setLayout(new BorderLayout());
        JTable tablaVips = ctrl.iniciarVips();
        mostrarTablaVips(tablaVips);
        tablaSMR.setLayout(new BorderLayout());
        JTable tablaSMR = ctrl.iniciarSMR();
        mostrarTablaSMR(tablaSMR);
        tablaSC.setLayout(new BorderLayout());
        JTable tablaSC = ctrl.iniciarSC();
        mostrarTablaSC(tablaSC);
    }
    
    //Metodos para la simulacion 
     @Override
    public void hayNuevoMensaje(String mensaje) {
        // Evitamos que se congele la interfaz usando el invokeLater
        SwingUtilities.invokeLater(() -> {
            areaLog.append(mensaje + "\n");
            areaLog.setCaretPosition(areaLog.getDocument().getLength());
        });
    }

    @Override
    public void hayCambioZona(String zona, String cliente, String peluquera, int porcentaje) {
        SwingUtilities.invokeLater(() -> {
            // Un Switch para saber qué barra mover según lo que manda el hilo
            switch (zona) {
                case "Lavado":
                    cliLavField.setText(cliente);
                    pelLavField.setText(peluquera);
                    barraLavado.setValue(porcentaje);
                    break;
                case "Corte":
                    cliCorField.setText(cliente);
                    pelCorField.setText(peluquera);
                    barraCorte.setValue(porcentaje);
                    break;
                case "Tinte":
                    cliTinField.setText(cliente);
                    pelTinField.setText(peluquera);
                    barraTinte.setValue(porcentaje);
                    break;
                case "Peinado":
                    cliPeiField.setText(cliente);
                    pelPeiField.setText(peluquera);
                    barraPeinado.setValue(porcentaje);
                    break;
            }
        });
    }

    @Override
    public void hayCambioPeluquera(int id, int porcentaje, boolean durmiendo) {
        SwingUtilities.invokeLater(() -> {
            javax.swing.JProgressBar barra = null; //Creamos una barra para saber que vamos a trabajar en ella

            // Asignamos la barra creada a la que nos pasaron por id
            if (id == 1) {
                barra = barraSiesta1;
            } else if (id == 2) {
                barra = barraSiesta2;
            } else if (id == 3) {
                barra = barraSiesta3;
            }
            if (barra != null) { //Modificamos la barra
                barra.setValue(porcentaje);
            }
        });
    }
    @Override
    public void actualizarEstadisticas(int atendidos, int pendientes, int peluquerasActivas, int peluquerasSiesta, double ganancias, int serviciosCompletados, String tiempoMedia) {
        SwingUtilities.invokeLater(() -> {
            // Actualizamos los campos de texto
            cliAteField.setText(String.valueOf(atendidos));
            cliPenField.setText(String.valueOf(pendientes));
            pelActField.setText(String.valueOf(peluquerasActivas));
            pelSieField.setText(String.valueOf(peluquerasSiesta));
            
            GananciasField.setText(""+ganancias);          
            ServiciosField.setText(String.valueOf(serviciosCompletados));                      
            TiemposField.setText(tiempoMedia); 
        });
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondo = new javax.swing.JPanel();
        navGeneral = new javax.swing.JTabbedPane();
        Simulacion = new javax.swing.JPanel();
        VistaPeluquera = new javax.swing.JPanel();
        barraLavado = new javax.swing.JProgressBar();
        barraCorte = new javax.swing.JProgressBar();
        barraTinte = new javax.swing.JProgressBar();
        barraPeinado = new javax.swing.JProgressBar();
        corteLabel = new javax.swing.JLabel();
        lavadoLabel = new javax.swing.JLabel();
        peinadoLabel = new javax.swing.JLabel();
        tinteLabel = new javax.swing.JLabel();
        cliLavField = new javax.swing.JTextField();
        pelLavField = new javax.swing.JTextField();
        pelCorField = new javax.swing.JTextField();
        cliCorField = new javax.swing.JTextField();
        pelTinField = new javax.swing.JTextField();
        cliTinField = new javax.swing.JTextField();
        pelPeiField = new javax.swing.JTextField();
        cliPeiField = new javax.swing.JTextField();
        cliLavLabel = new javax.swing.JLabel();
        pelLavLabel = new javax.swing.JLabel();
        cliCorLabel = new javax.swing.JLabel();
        pelCorLabel = new javax.swing.JLabel();
        pelPeiLabel = new javax.swing.JLabel();
        cliPeiLabel = new javax.swing.JLabel();
        pelTinLabel = new javax.swing.JLabel();
        cliTinLabel = new javax.swing.JLabel();
        cliAtendidosLabel = new javax.swing.JLabel();
        cliPendientesLabel = new javax.swing.JLabel();
        cliAteField = new javax.swing.JTextField();
        cliPenField = new javax.swing.JTextField();
        pelSieField = new javax.swing.JTextField();
        pelSieLabel = new javax.swing.JLabel();
        pelActLabel = new javax.swing.JLabel();
        pelActField = new javax.swing.JTextField();
        barraSiesta1 = new javax.swing.JProgressBar();
        barraSiesta2 = new javax.swing.JProgressBar();
        barraSiesta3 = new javax.swing.JProgressBar();
        SiestasTitulo = new javax.swing.JLabel();
        idSiesta1 = new javax.swing.JLabel();
        idSiesta2 = new javax.swing.JLabel();
        idSiesta3 = new javax.swing.JLabel();
        Stats = new javax.swing.JPanel();
        start = new javax.swing.JButton();
        stop = new javax.swing.JButton();
        pause = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaLog = new javax.swing.JTextArea();
        GananciasLabel = new javax.swing.JLabel();
        TiemposLabel = new javax.swing.JLabel();
        ServiciosLabel = new javax.swing.JLabel();
        GananciasField = new javax.swing.JTextField();
        TiemposField = new javax.swing.JTextField();
        ServiciosField = new javax.swing.JTextField();
        Gestion = new javax.swing.JPanel();
        BDDPanel = new javax.swing.JPanel();
        seleccionTabla = new javax.swing.JComboBox<>();
        separacionTablas = new javax.swing.JPanel();
        panelTabla = new javax.swing.JPanel();
        PanelGestion = new javax.swing.JPanel();
        separacionGestion = new javax.swing.JPanel();
        añadirBoton = new javax.swing.JButton();
        editarBoton = new javax.swing.JButton();
        panelEdicion = new javax.swing.JPanel();
        NombreLabel = new javax.swing.JLabel();
        IDLabel = new javax.swing.JLabel();
        ApellidosLabel = new javax.swing.JLabel();
        VipLabel = new javax.swing.JLabel();
        NDeVisitasLabel = new javax.swing.JLabel();
        FechadAltaLabel = new javax.swing.JLabel();
        tituloLabel = new javax.swing.JLabel();
        IDField = new javax.swing.JTextField();
        ApellidosField = new javax.swing.JTextField();
        NombreField = new javax.swing.JTextField();
        VipField = new javax.swing.JTextField();
        NDVisitasField = new javax.swing.JTextField();
        FechadeAltaField = new javax.swing.JTextField();
        DCriticos = new javax.swing.JPanel();
        ServiciosMasRentablesPanel = new javax.swing.JPanel();
        tituloServiciosR = new javax.swing.JLabel();
        tablaSMR = new javax.swing.JPanel();
        ClientesVIPPanel = new javax.swing.JPanel();
        tituloCVips = new javax.swing.JLabel();
        tablaCV = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        ProdStockCriticoPanel = new javax.swing.JPanel();
        tituloSCrit = new javax.swing.JLabel();
        tablaSC = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fondo.setBackground(new java.awt.Color(250, 235, 215));
        fondo.setMaximumSize(new java.awt.Dimension(1081, 645));

        navGeneral.setBackground(new java.awt.Color(255, 250, 240));
        navGeneral.setMaximumSize(new java.awt.Dimension(1081, 537));
        navGeneral.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                navGeneralFocusGained(evt);
            }
        });

        Simulacion.setBackground(new java.awt.Color(255, 250, 240));

        VistaPeluquera.setBackground(new java.awt.Color(250, 235, 215));
        VistaPeluquera.setMaximumSize(new java.awt.Dimension(430, 490));
        VistaPeluquera.setMinimumSize(new java.awt.Dimension(430, 490));

        barraLavado.setBackground(new java.awt.Color(245, 245, 245));
        barraLavado.setForeground(new java.awt.Color(143, 188, 143));

        barraCorte.setBackground(new java.awt.Color(245, 245, 245));
        barraCorte.setForeground(new java.awt.Color(143, 188, 143));

        barraTinte.setBackground(new java.awt.Color(245, 245, 245));
        barraTinte.setForeground(new java.awt.Color(143, 188, 143));

        barraPeinado.setBackground(new java.awt.Color(245, 245, 245));
        barraPeinado.setForeground(new java.awt.Color(143, 188, 143));

        corteLabel.setText("CORTE");

        lavadoLabel.setText("LAVADO");

        peinadoLabel.setText("PEINADO");

        tinteLabel.setText("TINTE");

        cliLavField.setFocusable(false);

        pelLavField.setFocusable(false);

        pelCorField.setFocusable(false);

        cliCorField.setFocusable(false);

        pelTinField.setFocusable(false);

        cliTinField.setFocusable(false);

        pelPeiField.setFocusable(false);

        cliPeiField.setFocusable(false);

        cliLavLabel.setText("Cliente");

        pelLavLabel.setText("Peluquera");

        cliCorLabel.setText("Cliente");

        pelCorLabel.setText("Peluquera");

        pelPeiLabel.setText("Peluquera");

        cliPeiLabel.setText("Cliente");

        pelTinLabel.setText("Peluquera");

        cliTinLabel.setText("Cliente");

        cliAtendidosLabel.setText("Clientes Atendidos");

        cliPendientesLabel.setText("Clientes Pendientes");

        cliAteField.setFocusable(false);

        cliPenField.setFocusable(false);

        pelSieField.setFocusable(false);

        pelSieLabel.setText("Peluqueras en Siesta");

        pelActLabel.setText("Peluqueras Activas");

        pelActField.setFocusable(false);

        barraSiesta1.setBackground(new java.awt.Color(245, 245, 245));
        barraSiesta1.setForeground(new java.awt.Color(205, 92, 92));

        barraSiesta2.setBackground(new java.awt.Color(245, 245, 245));
        barraSiesta2.setForeground(new java.awt.Color(205, 92, 92));

        barraSiesta3.setBackground(new java.awt.Color(245, 245, 245));
        barraSiesta3.setForeground(new java.awt.Color(205, 92, 92));

        SiestasTitulo.setText("SIESTAS");

        idSiesta1.setText("1");

        idSiesta2.setText("2");

        idSiesta3.setText("3");

        javax.swing.GroupLayout VistaPeluqueraLayout = new javax.swing.GroupLayout(VistaPeluquera);
        VistaPeluquera.setLayout(VistaPeluqueraLayout);
        VistaPeluqueraLayout.setHorizontalGroup(
            VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VistaPeluqueraLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VistaPeluqueraLayout.createSequentialGroup()
                        .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(VistaPeluqueraLayout.createSequentialGroup()
                                .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(VistaPeluqueraLayout.createSequentialGroup()
                                            .addComponent(pelSieLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(pelSieField, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(VistaPeluqueraLayout.createSequentialGroup()
                                            .addComponent(pelActLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(pelActField, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(VistaPeluqueraLayout.createSequentialGroup()
                                        .addComponent(cliPendientesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cliPenField, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(42, 42, 42)
                                .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(idSiesta1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(idSiesta2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(idSiesta3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(barraSiesta2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(barraSiesta3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(barraSiesta1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(VistaPeluqueraLayout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(lavadoLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(corteLabel)
                                .addGap(75, 75, 75))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, VistaPeluqueraLayout.createSequentialGroup()
                                .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VistaPeluqueraLayout.createSequentialGroup()
                                            .addComponent(pelLavLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(pelLavField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(VistaPeluqueraLayout.createSequentialGroup()
                                            .addComponent(cliLavLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cliLavField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(barraLavado, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VistaPeluqueraLayout.createSequentialGroup()
                                            .addGap(87, 87, 87)
                                            .addComponent(tinteLabel)
                                            .addGap(85, 85, 85))
                                        .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(barraTinte, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(VistaPeluqueraLayout.createSequentialGroup()
                                                .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(pelTinLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(VistaPeluqueraLayout.createSequentialGroup()
                                                        .addComponent(cliTinLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(12, 12, 12)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(pelTinField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cliTinField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addGap(62, 62, 62)
                                .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(VistaPeluqueraLayout.createSequentialGroup()
                                        .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cliCorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(pelCorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(pelCorField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cliCorField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(barraCorte, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VistaPeluqueraLayout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addComponent(peinadoLabel)
                                            .addGap(70, 70, 70))
                                        .addComponent(barraPeinado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VistaPeluqueraLayout.createSequentialGroup()
                                            .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(VistaPeluqueraLayout.createSequentialGroup()
                                                    .addComponent(cliPeiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 0, Short.MAX_VALUE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VistaPeluqueraLayout.createSequentialGroup()
                                                    .addGap(0, 0, Short.MAX_VALUE)
                                                    .addComponent(pelPeiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(18, 18, 18)
                                            .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(SiestasTitulo)
                                                .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(pelPeiField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cliPeiField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                        .addGap(51, 51, 51))
                    .addGroup(VistaPeluqueraLayout.createSequentialGroup()
                        .addComponent(cliAtendidosLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cliAteField, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        VistaPeluqueraLayout.setVerticalGroup(
            VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VistaPeluqueraLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(VistaPeluqueraLayout.createSequentialGroup()
                        .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(corteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lavadoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(barraCorte, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(barraLavado, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(VistaPeluqueraLayout.createSequentialGroup()
                                .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cliLavField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cliLavLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(pelLavField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pelLavLabel)))
                            .addGroup(VistaPeluqueraLayout.createSequentialGroup()
                                .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cliCorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cliCorLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(pelCorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pelCorLabel))))
                        .addGap(18, 18, 18)
                        .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(VistaPeluqueraLayout.createSequentialGroup()
                                .addComponent(peinadoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(barraPeinado, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cliPeiField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cliPeiLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(pelPeiField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pelPeiLabel)))
                            .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VistaPeluqueraLayout.createSequentialGroup()
                                    .addComponent(tinteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(barraTinte, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cliTinField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cliTinLabel))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(pelTinField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(pelTinLabel, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(VistaPeluqueraLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cliAtendidosLabel)
                                    .addComponent(cliAteField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VistaPeluqueraLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SiestasTitulo)))
                        .addGap(18, 18, 18)
                        .addComponent(barraSiesta1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addGroup(VistaPeluqueraLayout.createSequentialGroup()
                        .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idSiesta1)
                            .addComponent(cliPenField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cliPendientesLabel))
                        .addGap(6, 6, 6)))
                .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(idSiesta2)
                        .addComponent(barraSiesta2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pelActField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pelActLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VistaPeluqueraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pelSieLabel)
                        .addComponent(pelSieField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(barraSiesta3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idSiesta3))
                .addContainerGap(98, Short.MAX_VALUE))
        );

        Stats.setBackground(new java.awt.Color(250, 235, 215));
        Stats.setMaximumSize(new java.awt.Dimension(507, 490));
        Stats.setMinimumSize(new java.awt.Dimension(507, 490));
        Stats.setPreferredSize(new java.awt.Dimension(507, 490));

        start.setText("Comenzar");
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });

        stop.setText("Detener");
        stop.setEnabled(false);
        stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopActionPerformed(evt);
            }
        });

        pause.setText("Pausar");
        pause.setEnabled(false);
        pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseActionPerformed(evt);
            }
        });

        areaLog.setColumns(20);
        areaLog.setRows(5);
        areaLog.setFocusable(false);
        jScrollPane1.setViewportView(areaLog);

        GananciasLabel.setText("Ganancias estimadas");

        TiemposLabel.setText("Tiempos medios");

        ServiciosLabel.setText("Servicios completados");

        GananciasField.setFocusable(false);

        TiemposField.setFocusable(false);

        ServiciosField.setFocusable(false);
        ServiciosField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ServiciosFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout StatsLayout = new javax.swing.GroupLayout(Stats);
        Stats.setLayout(StatsLayout);
        StatsLayout.setHorizontalGroup(
            StatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StatsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(StatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(StatsLayout.createSequentialGroup()
                        .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pause, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stop, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StatsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(StatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StatsLayout.createSequentialGroup()
                        .addComponent(GananciasLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GananciasField, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(StatsLayout.createSequentialGroup()
                        .addComponent(TiemposLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TiemposField, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StatsLayout.createSequentialGroup()
                        .addComponent(ServiciosLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ServiciosField, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42))
        );
        StatsLayout.setVerticalGroup(
            StatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StatsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(StatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(StatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(stop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pause, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(47, 47, 47)
                .addGroup(StatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GananciasLabel)
                    .addComponent(GananciasField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(StatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TiemposLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TiemposField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(StatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ServiciosLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ServiciosField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout SimulacionLayout = new javax.swing.GroupLayout(Simulacion);
        Simulacion.setLayout(SimulacionLayout);
        SimulacionLayout.setHorizontalGroup(
            SimulacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SimulacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(VistaPeluquera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Stats, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        SimulacionLayout.setVerticalGroup(
            SimulacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SimulacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SimulacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Stats, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                    .addComponent(VistaPeluquera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        navGeneral.addTab("Simulación", Simulacion);

        Gestion.setBackground(new java.awt.Color(255, 250, 240));

        BDDPanel.setBackground(new java.awt.Color(250, 235, 215));
        BDDPanel.setMaximumSize(new java.awt.Dimension(705, 69));

        seleccionTabla.setBackground(new java.awt.Color(255, 250, 240));
        seleccionTabla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Clientes", "Productos", "Peluqueras", "Servicios" }));
        seleccionTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionTablaActionPerformed(evt);
            }
        });

        separacionTablas.setBackground(new java.awt.Color(255, 250, 240));
        separacionTablas.setForeground(new java.awt.Color(204, 204, 255));
        separacionTablas.setPreferredSize(new java.awt.Dimension(0, 3));

        javax.swing.GroupLayout separacionTablasLayout = new javax.swing.GroupLayout(separacionTablas);
        separacionTablas.setLayout(separacionTablasLayout);
        separacionTablasLayout.setHorizontalGroup(
            separacionTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        separacionTablasLayout.setVerticalGroup(
            separacionTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        panelTabla.setBackground(new java.awt.Color(255, 250, 240));
        panelTabla.setMaximumSize(new java.awt.Dimension(693, 421));
        panelTabla.setPreferredSize(new java.awt.Dimension(693, 421));
        panelTabla.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout BDDPanelLayout = new javax.swing.GroupLayout(BDDPanel);
        BDDPanel.setLayout(BDDPanelLayout);
        BDDPanelLayout.setHorizontalGroup(
            BDDPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BDDPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BDDPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(separacionTablas, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
                    .addGroup(BDDPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(seleccionTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        BDDPanelLayout.setVerticalGroup(
            BDDPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BDDPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(seleccionTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separacionTablas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelGestion.setBackground(new java.awt.Color(250, 235, 215));
        PanelGestion.setMaximumSize(new java.awt.Dimension(352, 490));

        separacionGestion.setBackground(new java.awt.Color(255, 250, 240));
        separacionGestion.setForeground(new java.awt.Color(204, 204, 255));
        separacionGestion.setPreferredSize(new java.awt.Dimension(0, 3));

        javax.swing.GroupLayout separacionGestionLayout = new javax.swing.GroupLayout(separacionGestion);
        separacionGestion.setLayout(separacionGestionLayout);
        separacionGestionLayout.setHorizontalGroup(
            separacionGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        separacionGestionLayout.setVerticalGroup(
            separacionGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        añadirBoton.setBackground(new java.awt.Color(255, 250, 240));
        añadirBoton.setText("Añadir");
        añadirBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añadirBotonActionPerformed(evt);
            }
        });

        editarBoton.setBackground(new java.awt.Color(255, 250, 240));
        editarBoton.setText("Editar Datos");
        editarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarBotonActionPerformed(evt);
            }
        });

        panelEdicion.setBackground(new java.awt.Color(255, 250, 240));

        NombreLabel.setText("Nombre");

        IDLabel.setText("ID");

        ApellidosLabel.setText("Apellidos");

        VipLabel.setText("VIP");

        NDeVisitasLabel.setText("Nº De Visitas");

        FechadAltaLabel.setText("Fecha de alta");

        tituloLabel.setText("CLIENTES");

        IDField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDFieldActionPerformed(evt);
            }
        });

        ApellidosField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApellidosFieldActionPerformed(evt);
            }
        });

        NombreField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreFieldActionPerformed(evt);
            }
        });

        VipField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VipFieldActionPerformed(evt);
            }
        });

        NDVisitasField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NDVisitasFieldActionPerformed(evt);
            }
        });

        FechadeAltaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FechadeAltaFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEdicionLayout = new javax.swing.GroupLayout(panelEdicion);
        panelEdicion.setLayout(panelEdicionLayout);
        panelEdicionLayout.setHorizontalGroup(
            panelEdicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEdicionLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelEdicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NombreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelEdicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FechadAltaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                        .addComponent(NDeVisitasLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(VipLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ApellidosLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(panelEdicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ApellidosField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NombreField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VipField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NDVisitasField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FechadeAltaField, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(panelEdicionLayout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(tituloLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEdicionLayout.setVerticalGroup(
            panelEdicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEdicionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tituloLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(panelEdicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelEdicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NombreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NombreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelEdicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ApellidosLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ApellidosField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelEdicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VipLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VipField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEdicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NDeVisitasLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NDVisitasField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelEdicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FechadAltaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FechadeAltaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout PanelGestionLayout = new javax.swing.GroupLayout(PanelGestion);
        PanelGestion.setLayout(PanelGestionLayout);
        PanelGestionLayout.setHorizontalGroup(
            PanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelGestionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelGestionLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(editarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(separacionGestion, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                    .addComponent(panelEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(añadirBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        PanelGestionLayout.setVerticalGroup(
            PanelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelGestionLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(añadirBoton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separacionGestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout GestionLayout = new javax.swing.GroupLayout(Gestion);
        Gestion.setLayout(GestionLayout);
        GestionLayout.setHorizontalGroup(
            GestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GestionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BDDPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelGestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        GestionLayout.setVerticalGroup(
            GestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GestionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(GestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PanelGestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BDDPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        navGeneral.addTab("Gestion", Gestion);

        DCriticos.setBackground(new java.awt.Color(255, 250, 240));
        DCriticos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                DCriticosFocusGained(evt);
            }
        });

        ServiciosMasRentablesPanel.setBackground(new java.awt.Color(250, 235, 215));
        ServiciosMasRentablesPanel.setMaximumSize(new java.awt.Dimension(340, 490));
        ServiciosMasRentablesPanel.setPreferredSize(new java.awt.Dimension(340, 490));

        tituloServiciosR.setText("SERVICIOS MÁS RENTABLES");

        tablaSMR.setBackground(new java.awt.Color(255, 250, 240));
        tablaSMR.setMaximumSize(new java.awt.Dimension(328, 428));

        javax.swing.GroupLayout tablaSMRLayout = new javax.swing.GroupLayout(tablaSMR);
        tablaSMR.setLayout(tablaSMRLayout);
        tablaSMRLayout.setHorizontalGroup(
            tablaSMRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        tablaSMRLayout.setVerticalGroup(
            tablaSMRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ServiciosMasRentablesPanelLayout = new javax.swing.GroupLayout(ServiciosMasRentablesPanel);
        ServiciosMasRentablesPanel.setLayout(ServiciosMasRentablesPanelLayout);
        ServiciosMasRentablesPanelLayout.setHorizontalGroup(
            ServiciosMasRentablesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ServiciosMasRentablesPanelLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(tituloServiciosR)
                .addContainerGap(101, Short.MAX_VALUE))
            .addGroup(ServiciosMasRentablesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tablaSMR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        ServiciosMasRentablesPanelLayout.setVerticalGroup(
            ServiciosMasRentablesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ServiciosMasRentablesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tituloServiciosR, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tablaSMR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        ClientesVIPPanel.setBackground(new java.awt.Color(250, 235, 215));
        ClientesVIPPanel.setMaximumSize(new java.awt.Dimension(340, 490));

        tituloCVips.setText("CLIENTES VIP");

        tablaCV.setBackground(new java.awt.Color(255, 250, 240));
        tablaCV.setMaximumSize(new java.awt.Dimension(328, 428));

        javax.swing.GroupLayout tablaCVLayout = new javax.swing.GroupLayout(tablaCV);
        tablaCV.setLayout(tablaCVLayout);
        tablaCVLayout.setHorizontalGroup(
            tablaCVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
        );
        tablaCVLayout.setVerticalGroup(
            tablaCVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 428, Short.MAX_VALUE)
        );

        jButton1.setBackground(new java.awt.Color(242, 242, 242));
        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\driss\\Documents\\NetBeansProjects\\Practica_Interfaces\\interfaces\\src\\main\\java\\com\\mycompany\\interfaces\\Vista\\images\\boton.png")); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setPreferredSize(new java.awt.Dimension(25, 25));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ClientesVIPPanelLayout = new javax.swing.GroupLayout(ClientesVIPPanel);
        ClientesVIPPanel.setLayout(ClientesVIPPanelLayout);
        ClientesVIPPanelLayout.setHorizontalGroup(
            ClientesVIPPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClientesVIPPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ClientesVIPPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ClientesVIPPanelLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(tituloCVips))
                    .addComponent(tablaCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ClientesVIPPanelLayout.setVerticalGroup(
            ClientesVIPPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClientesVIPPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ClientesVIPPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloCVips, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tablaCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ProdStockCriticoPanel.setBackground(new java.awt.Color(250, 235, 215));
        ProdStockCriticoPanel.setMaximumSize(new java.awt.Dimension(340, 490));
        ProdStockCriticoPanel.setPreferredSize(new java.awt.Dimension(340, 490));

        tituloSCrit.setText("STOCK CRÍTICO");

        tablaSC.setBackground(new java.awt.Color(255, 250, 240));
        tablaSC.setMaximumSize(new java.awt.Dimension(328, 428));

        javax.swing.GroupLayout tablaSCLayout = new javax.swing.GroupLayout(tablaSC);
        tablaSC.setLayout(tablaSCLayout);
        tablaSCLayout.setHorizontalGroup(
            tablaSCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        tablaSCLayout.setVerticalGroup(
            tablaSCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ProdStockCriticoPanelLayout = new javax.swing.GroupLayout(ProdStockCriticoPanel);
        ProdStockCriticoPanel.setLayout(ProdStockCriticoPanelLayout);
        ProdStockCriticoPanelLayout.setHorizontalGroup(
            ProdStockCriticoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProdStockCriticoPanelLayout.createSequentialGroup()
                .addContainerGap(127, Short.MAX_VALUE)
                .addComponent(tituloSCrit)
                .addGap(130, 130, 130))
            .addGroup(ProdStockCriticoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tablaSC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        ProdStockCriticoPanelLayout.setVerticalGroup(
            ProdStockCriticoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProdStockCriticoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tituloSCrit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tablaSC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout DCriticosLayout = new javax.swing.GroupLayout(DCriticos);
        DCriticos.setLayout(DCriticosLayout);
        DCriticosLayout.setHorizontalGroup(
            DCriticosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DCriticosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ServiciosMasRentablesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ClientesVIPPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ProdStockCriticoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        DCriticosLayout.setVerticalGroup(
            DCriticosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DCriticosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DCriticosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ClientesVIPPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProdStockCriticoPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                    .addComponent(ServiciosMasRentablesPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE))
                .addContainerGap())
        );

        navGeneral.addTab("Datos Críticos", DCriticos);

        logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\driss\\Documents\\NetBeansProjects\\Practica_Interfaces\\interfaces\\src\\main\\java\\com\\mycompany\\interfaces\\Vista\\images\\icono.png")); // NOI18N

        javax.swing.GroupLayout fondoLayout = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(fondoLayout);
        fondoLayout.setHorizontalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(navGeneral, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(fondoLayout.createSequentialGroup()
                .addGap(388, 388, 388)
                .addComponent(logo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        fondoLayout.setVerticalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(navGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void editarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarBotonActionPerformed
        // TODO add your handling code here: 
        if (IDField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "No se referió a ningún cliente. Debe añadir el ID objetivo");
            IDField.setText("");
            NombreField.setText("");
            ApellidosField.setText("");
            VipField.setText("");
            NDVisitasField.setText("");
            FechadeAltaField.setText("");
        } else {
            int id_cliente = Integer.parseInt(IDField.getText());
            String nombre = NombreField.getText();
            String apellidos = ApellidosField.getText();
            String vip = VipField.getText();
            int visitas = Integer.parseInt(NDVisitasField.getText());
            LocalDateTime fecha = LocalDateTime.parse(FechadeAltaField.getText());

            ctrl.modificacionCliente(id_cliente, nombre, apellidos, vip, visitas, fecha);
            String seleccion = (String) seleccionTabla.getSelectedItem();
            mostrarTablaBDD(ctrl.iniciar(seleccion));
        }      
    }//GEN-LAST:event_editarBotonActionPerformed

    private void seleccionTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionTablaActionPerformed
        
        String seleccion =(String)seleccionTabla.getSelectedItem();
        //System.out.println(seleccion);
        mostrarTablaBDD(ctrl.iniciar(seleccion));
        
       
    }//GEN-LAST:event_seleccionTablaActionPerformed

    private void IDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDFieldActionPerformed
        // TODO add your handling code here:
        if (ctrl.comprobarDatos(Integer.parseInt(IDField.getText())) != null) {
            int id = Integer.parseInt(IDField.getText());
            mostrarCliente(ctrl.comprobarDatos(id));
        } else {
            JOptionPane.showMessageDialog(null, "No existe un cliente con ese ID");
            IDField.setText("");
            NombreField.setText("");
            ApellidosField.setText("");
            VipField.setText("");
            NDVisitasField.setText("");
            FechadeAltaField.setText("");
        }
    }//GEN-LAST:event_IDFieldActionPerformed

    private void ApellidosFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApellidosFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ApellidosFieldActionPerformed

    private void NombreFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreFieldActionPerformed

    private void VipFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VipFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_VipFieldActionPerformed

    private void NDVisitasFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NDVisitasFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NDVisitasFieldActionPerformed

    private void FechadeAltaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FechadeAltaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FechadeAltaFieldActionPerformed

    private void añadirBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añadirBotonActionPerformed
        // TODO add your handling code here
        IDField.setText("");
        FechadeAltaField.setText("");
        String nombre = NombreField.getText();
        String apellidos = ApellidosField.getText();
        String vip = VipField.getText();
        int visitas = 0;
        visitas = Integer.parseInt(NDVisitasField.getText());
        if (nombre.equals("") || apellidos.equals("") || vip.equals("") || visitas == 0) {
            JOptionPane.showMessageDialog(null, "Se deben rellenar nombre, apellidos, vip y numero de visitas");
        } else {
            ctrl.añadirCliente(nombre, apellidos, vip, visitas);
            String seleccion = (String) seleccionTabla.getSelectedItem();
            mostrarTablaBDD(ctrl.iniciar(seleccion));
        }
    }//GEN-LAST:event_añadirBotonActionPerformed

    private void navGeneralFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_navGeneralFocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_navGeneralFocusGained

    private void DCriticosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DCriticosFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_DCriticosFocusGained

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        iniciarTablas();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void pauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseActionPerformed
        // TODO add your handling code here:
        ctrl.pausarSimulacionDesdeVista(); // Tienes que crear este puente en mainControlador       
        // Cambio estético del texto
        if (pause.isSelected()) {
            pause.setText("Reanudar");
        } else {
            pause.setText("Pausar");
        }
    }//GEN-LAST:event_pauseActionPerformed

    private void ServiciosFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ServiciosFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ServiciosFieldActionPerformed

    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
        // TODO add your handling code here:       
        start.setEnabled(false);
        start.setText("Funcionando...");
        ctrl.comenzarSimulacion();  
        pause.setEnabled(true);
        stop.setEnabled(true);
    }//GEN-LAST:event_startActionPerformed

    private void stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopActionPerformed
        // TODO add your handling code here:
        ctrl.detenerSimulacionDesdeVista(); // Puente en mainControlador
        
        // Reseteamos botones
        start.setEnabled(true);
        start.setText("Comenzar");
        stop.setEnabled(false);
        pause.setEnabled(false);
        pause.setSelected(false);
        pause.setText("Pausar");
        cliLavField.setText("");
        pelLavField.setText("");
        barraLavado.setValue(0);
        cliCorField.setText("");
        pelCorField.setText("");
        barraCorte.setValue(0);
        cliTinField.setText("");
        pelTinField.setText("");
        barraTinte.setValue(0);
        cliPeiField.setText("");
        pelPeiField.setText("");
        barraPeinado.setValue(0);
        barraSiesta1.setValue(0);
        barraSiesta2.setValue(0);
        barraSiesta3.setValue(0);
        cliAteField.setText("");
        cliPenField.setText(""); 
        pelActField.setText(""); // Vuelven a estar 3 activas
        pelSieField.setText("");
        GananciasField.setText("");
        TiemposField.setText("");
        ServiciosField.setText("");
        areaLog.setText("");
    }//GEN-LAST:event_stopActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ApellidosField;
    private javax.swing.JLabel ApellidosLabel;
    private javax.swing.JPanel BDDPanel;
    private javax.swing.JPanel ClientesVIPPanel;
    private javax.swing.JPanel DCriticos;
    private javax.swing.JLabel FechadAltaLabel;
    private javax.swing.JTextField FechadeAltaField;
    private javax.swing.JTextField GananciasField;
    private javax.swing.JLabel GananciasLabel;
    private javax.swing.JPanel Gestion;
    private javax.swing.JTextField IDField;
    private javax.swing.JLabel IDLabel;
    private javax.swing.JTextField NDVisitasField;
    private javax.swing.JLabel NDeVisitasLabel;
    private javax.swing.JTextField NombreField;
    private javax.swing.JLabel NombreLabel;
    private javax.swing.JPanel PanelGestion;
    private javax.swing.JPanel ProdStockCriticoPanel;
    private javax.swing.JTextField ServiciosField;
    private javax.swing.JLabel ServiciosLabel;
    private javax.swing.JPanel ServiciosMasRentablesPanel;
    private javax.swing.JLabel SiestasTitulo;
    private javax.swing.JPanel Simulacion;
    private javax.swing.JPanel Stats;
    private javax.swing.JTextField TiemposField;
    private javax.swing.JLabel TiemposLabel;
    private javax.swing.JTextField VipField;
    private javax.swing.JLabel VipLabel;
    private javax.swing.JPanel VistaPeluquera;
    private javax.swing.JTextArea areaLog;
    private javax.swing.JButton añadirBoton;
    private javax.swing.JProgressBar barraCorte;
    private javax.swing.JProgressBar barraLavado;
    private javax.swing.JProgressBar barraPeinado;
    private javax.swing.JProgressBar barraSiesta1;
    private javax.swing.JProgressBar barraSiesta2;
    private javax.swing.JProgressBar barraSiesta3;
    private javax.swing.JProgressBar barraTinte;
    private javax.swing.JTextField cliAteField;
    private javax.swing.JLabel cliAtendidosLabel;
    private javax.swing.JTextField cliCorField;
    private javax.swing.JLabel cliCorLabel;
    private javax.swing.JTextField cliLavField;
    private javax.swing.JLabel cliLavLabel;
    private javax.swing.JTextField cliPeiField;
    private javax.swing.JLabel cliPeiLabel;
    private javax.swing.JTextField cliPenField;
    private javax.swing.JLabel cliPendientesLabel;
    private javax.swing.JTextField cliTinField;
    private javax.swing.JLabel cliTinLabel;
    private javax.swing.JLabel corteLabel;
    private javax.swing.JButton editarBoton;
    private javax.swing.JPanel fondo;
    private javax.swing.JLabel idSiesta1;
    private javax.swing.JLabel idSiesta2;
    private javax.swing.JLabel idSiesta3;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lavadoLabel;
    private javax.swing.JLabel logo;
    private javax.swing.JTabbedPane navGeneral;
    private javax.swing.JPanel panelEdicion;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JToggleButton pause;
    private javax.swing.JLabel peinadoLabel;
    private javax.swing.JTextField pelActField;
    private javax.swing.JLabel pelActLabel;
    private javax.swing.JTextField pelCorField;
    private javax.swing.JLabel pelCorLabel;
    private javax.swing.JTextField pelLavField;
    private javax.swing.JLabel pelLavLabel;
    private javax.swing.JTextField pelPeiField;
    private javax.swing.JLabel pelPeiLabel;
    private javax.swing.JTextField pelSieField;
    private javax.swing.JLabel pelSieLabel;
    private javax.swing.JTextField pelTinField;
    private javax.swing.JLabel pelTinLabel;
    public javax.swing.JComboBox<String> seleccionTabla;
    private javax.swing.JPanel separacionGestion;
    private javax.swing.JPanel separacionTablas;
    private javax.swing.JButton start;
    private javax.swing.JButton stop;
    private javax.swing.JPanel tablaCV;
    private javax.swing.JPanel tablaSC;
    private javax.swing.JPanel tablaSMR;
    private javax.swing.JLabel tinteLabel;
    private javax.swing.JLabel tituloCVips;
    private javax.swing.JLabel tituloLabel;
    private javax.swing.JLabel tituloSCrit;
    private javax.swing.JLabel tituloServiciosR;
    // End of variables declaration//GEN-END:variables

   

}
