/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante;

import clases.conectate;
import clases.sqlclass;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import ve.edu.ucab.keyboard.Keyboard;
import ve.edu.ucab.logic.LogicComponent;
import ve.edu.ucab.logic.LogicListComponent;

/**
 *
 * @author KONESH
 */
public class facturacion extends javax.swing.JDialog {

    private Keyboard keyboard;
    String fecha_sistema, fecha, serie;
    String rfcEm, razon_socialEm, calleEm, no_extEm, no_intEm, coloniaEm, ciudadEm, estadoEm, paisEm, cpEm, referenciaEm, lugar_expedicionEm, regimen_fiscalEm, rutaPDF, rutaXML, rutaEntrada;
    int folio_actual, folio_final;
    float iva_global, iva_txt;
    int index_cursor_rfc = 0;
    boolean rutas_existen = false;
    sqlclass funcion = new sqlclass();
    sqlclass general = new sqlclass();
    static sqlclass general1 = new sqlclass();
    conectate conexion = new conectate();

    /**
     * Creates new form facturacion
     */
    public facturacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txt_subtotal.setText("0.00");
        txt_total.setText("0.00");
        txt_descuento.setText("0.00");
        txt_forma_pago.setText("UNA SOLA EXHIBICION");

        txt_serie.setEditable(false);
        txt_fecha.setEditable(false);

        txt_precio_cuenta.setEditable(false);
        txt_precio_cuenta.setBorder(BorderFactory.createEmptyBorder());
        txt_precio_cuenta.setBackground(new Color(0, 0, 0, 0));

        txt_subtotal.setEditable(false);
        txt_subtotal.setBorder(BorderFactory.createEmptyBorder());
        txt_subtotal.setBackground(new Color(0, 0, 0, 0));

        txt_iva.setEditable(false);
        txt_iva.setBorder(BorderFactory.createEmptyBorder());
        txt_iva.setBackground(new Color(0, 0, 0, 0));

        txt_descuento.setEditable(false);
        txt_descuento.setBorder(BorderFactory.createEmptyBorder());
        txt_descuento.setBackground(new Color(0, 0, 0, 0));

        txt_total.setEditable(false);
        txt_total.setBorder(BorderFactory.createEmptyBorder());
        txt_total.setBackground(new Color(0, 0, 0, 0));

        txt_precio_cuenta.setValue(principal.total_venta_global);
        fecha_sistema();
        traer_datos();
        fecha();
        txt_fecha.setText(fecha_sistema);

        this.setLocationRelativeTo(null);

        LogicListComponent components = new LogicListComponent();

        components.addComponent(new LogicComponent(txt_rfc_cliente, 1, true, false, false, false));
        components.addComponent(new LogicComponent(txt_razon_social_cliente, 2, true, false, false, false));
        components.addComponent(new LogicComponent(txt_calle_cliente, 3, true, false, false, false));
        components.addComponent(new LogicComponent(txt_noexterior_cliente, 4, true, false, false, false));
        components.addComponent(new LogicComponent(txt_nointerior_cliente, 5, true, false, false, false));
        components.addComponent(new LogicComponent(txt_colonia_cliente, 6, true, false, false, false));
        components.addComponent(new LogicComponent(txt_codigopostal_cliente, 7, true, false, false, false));
        components.addComponent(new LogicComponent(txt_localidad_cliente, 8, true, false, false, false));
        components.addComponent(new LogicComponent(txt_municipio_cliente, 9, true, false, false, false));
        components.addComponent(new LogicComponent(txt_estado_cliente, 10, true, false, false, false));
        components.addComponent(new LogicComponent(txt_pais_cliente, 11, true, false, false, false));
        components.addComponent(new LogicComponent(txt_correo_cliente, 12, true, false, false, false));

        components.addComponent(new LogicComponent(txt_forma_pago, 13, true, false, false, false));
        components.addComponent(new LogicComponent(txt_condiciones_pago, 14, true, false, false, false));
        components.addComponent(new LogicComponent(txt_metodo_pago, 15, true, false, false, false));
        components.addComponent(new LogicComponent(txt_cuenta_pago, 16, true, false, false, false));

        components.addComponent(new LogicComponent(txt_notas, 17, true, false, false, false));

        keyboard = new Keyboard(components, true);
        //keyboard.
        panel_teclado.setLayout(new GridLayout(1, 1));
        panel_teclado.add(keyboard);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panel_teclado = new javax.swing.JPanel();
        panel_datos_pago = new javax.swing.JPanel();
        txt_serie = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txt_fecha = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_forma_pago = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_condiciones_pago = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_metodo_pago = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_cuenta_pago = new javax.swing.JTextField();
        panel_datos_cliente = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        txt_rfc_cliente = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        txt_razon_social_cliente = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        txt_calle_cliente = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        txt_noexterior_cliente = new javax.swing.JTextField();
        txt_nointerior_cliente = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        txt_colonia_cliente = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        txt_localidad_cliente = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        txt_municipio_cliente = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        txt_estado_cliente = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        txt_pais_cliente = new javax.swing.JTextField();
        txt_correo_cliente = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        txt_referencia = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        txt_codigopostal_cliente = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        panel_totales = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txt_precio_cuenta = new javax.swing.JFormattedTextField();
        txt_notas = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txt_subtotal = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        lbl_impuesto = new javax.swing.JLabel();
        txt_iva = new javax.swing.JFormattedTextField();
        txt_descuento = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_total = new javax.swing.JFormattedTextField();
        check_calcular = new javax.swing.JCheckBox();
        check_desglosar = new javax.swing.JCheckBox();
        Jbtn_generar_cfdi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_teclado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panel_tecladoLayout = new javax.swing.GroupLayout(panel_teclado);
        panel_teclado.setLayout(panel_tecladoLayout);
        panel_tecladoLayout.setHorizontalGroup(
            panel_tecladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 628, Short.MAX_VALUE)
        );
        panel_tecladoLayout.setVerticalGroup(
            panel_tecladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 221, Short.MAX_VALUE)
        );

        getContentPane().add(panel_teclado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 630, -1));

        txt_serie.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_serie.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel1.setText("Serie y folio:");

        txt_fecha.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel2.setText("Fecha:");

        txt_forma_pago.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_forma_pago.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_forma_pagoFocusGained(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel3.setText("Forma de pago:");

        txt_condiciones_pago.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_condiciones_pago.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_condiciones_pagoFocusGained(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel4.setText("Condiciones de pago:");

        txt_metodo_pago.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_metodo_pago.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_metodo_pagoFocusGained(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel5.setText("Metodo de pago:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel6.setText("Cuenta de pago:");

        txt_cuenta_pago.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_cuenta_pago.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_cuenta_pagoFocusGained(evt);
            }
        });

        javax.swing.GroupLayout panel_datos_pagoLayout = new javax.swing.GroupLayout(panel_datos_pago);
        panel_datos_pago.setLayout(panel_datos_pagoLayout);
        panel_datos_pagoLayout.setHorizontalGroup(
            panel_datos_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_datos_pagoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_datos_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datos_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_datos_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_forma_pago, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_datos_pagoLayout.createSequentialGroup()
                                .addGroup(panel_datos_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_datos_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(txt_serie, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(txt_condiciones_pago, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_metodo_pago, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_datos_pagoLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_datos_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txt_cuenta_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_datos_pagoLayout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(68, 68, 68))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        panel_datos_pagoLayout.setVerticalGroup(
            panel_datos_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_datos_pagoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_datos_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_pagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_serie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jLabel3)
                .addGap(1, 1, 1)
                .addComponent(txt_forma_pago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(1, 1, 1)
                .addComponent(txt_condiciones_pago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(1, 1, 1)
                .addComponent(txt_metodo_pago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_cuenta_pago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        getContentPane().add(panel_datos_pago, new org.netbeans.lib.awtextra.AbsoluteConstraints(681, 11, -1, 250));

        jLabel73.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel73.setText("RFC");

        txt_rfc_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_rfc_cliente.setToolTipText("");
        txt_rfc_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_rfc_clienteFocusGained(evt);
            }
        });
        txt_rfc_cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_rfc_clienteKeyReleased(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel74.setText("Razon social:");

        txt_razon_social_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_razon_social_cliente.setToolTipText("");
        txt_razon_social_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_razon_social_clienteFocusGained(evt);
            }
        });

        jLabel75.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel75.setText("Calle:");

        txt_calle_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_calle_cliente.setToolTipText("");
        txt_calle_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_calle_clienteFocusGained(evt);
            }
        });

        jLabel76.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel76.setText("No Ext:");

        jLabel77.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel77.setText("No Int:");

        txt_noexterior_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_noexterior_cliente.setToolTipText("");
        txt_noexterior_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_noexterior_clienteFocusGained(evt);
            }
        });

        txt_nointerior_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_nointerior_cliente.setToolTipText("");
        txt_nointerior_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_nointerior_clienteFocusGained(evt);
            }
        });

        jLabel78.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel78.setText("Colonia:");

        txt_colonia_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_colonia_cliente.setToolTipText("");
        txt_colonia_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_colonia_clienteFocusGained(evt);
            }
        });

        jLabel79.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel79.setText("C.P.");

        jLabel80.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel80.setText("Localidad:");

        txt_localidad_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_localidad_cliente.setToolTipText("");
        txt_localidad_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_localidad_clienteFocusGained(evt);
            }
        });

        jLabel81.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel81.setText("Delegacion / municipio:");

        txt_municipio_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_municipio_cliente.setToolTipText("");
        txt_municipio_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_municipio_clienteFocusGained(evt);
            }
        });

        jLabel82.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel82.setText("Estado:");

        txt_estado_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_estado_cliente.setToolTipText("");
        txt_estado_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_estado_clienteFocusGained(evt);
            }
        });

        jLabel83.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel83.setText("Pais:");

        txt_pais_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_pais_cliente.setToolTipText("");
        txt_pais_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_pais_clienteFocusGained(evt);
            }
        });

        txt_correo_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_correo_cliente.setToolTipText("");
        txt_correo_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_correo_clienteFocusGained(evt);
            }
        });

        jLabel84.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel84.setText("Email");

        txt_referencia.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_referencia.setToolTipText("");
        txt_referencia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_referenciaFocusGained(evt);
            }
        });

        jLabel85.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel85.setText("Referencia:");

        txt_codigopostal_cliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("FACTURACON CFDI");

        javax.swing.GroupLayout panel_datos_clienteLayout = new javax.swing.GroupLayout(panel_datos_cliente);
        panel_datos_cliente.setLayout(panel_datos_clienteLayout);
        panel_datos_clienteLayout.setHorizontalGroup(
            panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_datos_clienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datos_clienteLayout.createSequentialGroup()
                        .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel73)
                            .addGroup(panel_datos_clienteLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txt_rfc_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_razon_social_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel74))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel75)
                            .addComponent(txt_calle_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_datos_clienteLayout.createSequentialGroup()
                        .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_pais_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel83))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel85)
                            .addComponent(txt_referencia, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel84)
                            .addComponent(txt_correo_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel_datos_clienteLayout.createSequentialGroup()
                        .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel_datos_clienteLayout.createSequentialGroup()
                                .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_noexterior_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel76))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel77)
                                    .addComponent(txt_nointerior_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel78)
                                    .addComponent(txt_colonia_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel79)
                                    .addComponent(txt_codigopostal_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel80)
                                    .addComponent(txt_localidad_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panel_datos_clienteLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)))
                        .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_municipio_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panel_datos_clienteLayout.createSequentialGroup()
                                .addComponent(jLabel82)
                                .addGap(77, 77, 77))
                            .addGroup(panel_datos_clienteLayout.createSequentialGroup()
                                .addComponent(txt_estado_cliente)
                                .addGap(18, 18, 18))))))
        );
        panel_datos_clienteLayout.setVerticalGroup(
            panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_datos_clienteLayout.createSequentialGroup()
                .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_datos_clienteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel_datos_clienteLayout.createSequentialGroup()
                                .addComponent(jLabel74)
                                .addGap(26, 26, 26))
                            .addGroup(panel_datos_clienteLayout.createSequentialGroup()
                                .addComponent(jLabel73)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_rfc_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_datos_clienteLayout.createSequentialGroup()
                        .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel75)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_razon_social_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_calle_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_datos_clienteLayout.createSequentialGroup()
                        .addComponent(jLabel78)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_colonia_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_datos_clienteLayout.createSequentialGroup()
                        .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel76)
                            .addComponent(jLabel77))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_noexterior_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_nointerior_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txt_codigopostal_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_datos_clienteLayout.createSequentialGroup()
                        .addComponent(jLabel79)
                        .addGap(26, 26, 26))
                    .addGroup(panel_datos_clienteLayout.createSequentialGroup()
                        .addComponent(jLabel80)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_localidad_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_datos_clienteLayout.createSequentialGroup()
                        .addComponent(jLabel81)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_municipio_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_datos_clienteLayout.createSequentialGroup()
                        .addComponent(jLabel82)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_estado_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_datos_clienteLayout.createSequentialGroup()
                        .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel83)
                            .addComponent(jLabel84))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_datos_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_pais_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_correo_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel_datos_clienteLayout.createSequentialGroup()
                        .addComponent(jLabel85)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_referencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(panel_datos_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 670, 150));

        jLabel12.setText("Precio de la cuenta:");

        txt_precio_cuenta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        txt_precio_cuenta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_precio_cuenta.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        txt_notas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_notasFocusGained(evt);
            }
        });

        jLabel9.setText("Nota:");

        javax.swing.GroupLayout panel_totalesLayout = new javax.swing.GroupLayout(panel_totales);
        panel_totales.setLayout(panel_totalesLayout);
        panel_totalesLayout.setHorizontalGroup(
            panel_totalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_totalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_totalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_totalesLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_precio_cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel_totalesLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_notas)
                        .addContainerGap())))
        );
        panel_totalesLayout.setVerticalGroup(
            panel_totalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_totalesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_totalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_precio_cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_totalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_notas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap())
        );

        getContentPane().add(panel_totales, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 655, 70));

        txt_subtotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        txt_subtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_subtotal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel8.setText("Subtotal:");

        txt_iva.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        txt_iva.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_iva.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txt_descuento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        txt_descuento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_descuento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel10.setText("Descuento:");

        jLabel11.setText("Total:");

        txt_total.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        txt_total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_total.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalActionPerformed(evt);
            }
        });

        buttonGroup1.add(check_calcular);
        check_calcular.setText("Calcular IVA");
        check_calcular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                check_calcularMouseClicked(evt);
            }
        });
        check_calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_calcularActionPerformed(evt);
            }
        });

        buttonGroup1.add(check_desglosar);
        check_desglosar.setText("Desglosar IVA");
        check_desglosar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                check_desglosarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl_impuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 1, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_subtotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_iva, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_descuento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_total, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(check_calcular)
                            .addComponent(check_desglosar))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(check_calcular)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_desglosar)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_impuesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_iva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_descuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(5, 5, 5))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 280, -1, -1));

        Jbtn_generar_cfdi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/check-icon.png"))); // NOI18N
        Jbtn_generar_cfdi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_generar_cfdiActionPerformed(evt);
            }
        });
        getContentPane().add(Jbtn_generar_cfdi, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 260, 49, 33));

        pack();
    }// </editor-fold>//GEN-END:initComponents
public String EliminaCaracteres(String s_cadena, String s_caracteres) {
        String nueva_cadena = "";
        Character caracter = null;
        boolean valido = true;

        /* Va recorriendo la cadena s_cadena y copia a la cadena que va a regresar,
         sólo los caracteres que no estén en la cadena s_caracteres */
        for (int i = 0; i < s_cadena.length(); i++) {
            valido = true;
            for (int j = 0; j < s_caracteres.length(); j++) {
                caracter = s_caracteres.charAt(j);

                if (s_cadena.charAt(i) == caracter) {
                    valido = false;
                    break;
                }
            }
            if (valido) {
                nueva_cadena += s_cadena.charAt(i);
            }
        }

        return nueva_cadena;
    }
/////////////////////////////////funcion

    public void escribir(String nombreArchivo) {
        Calendar c = new GregorianCalendar();
        File f;
        f = new File(nombreArchivo);
//Escritura
        try {

            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);

            int hora = c.get(Calendar.HOUR_OF_DAY);
            int minutos = c.get(Calendar.MINUTE);
            int segundos = c.get(Calendar.SECOND);

            String subtotal = EliminaCaracteres(this.txt_subtotal.getText(), "$,");
            String total = EliminaCaracteres(this.txt_total.getText(), "$,");
            String descuento = EliminaCaracteres(this.txt_descuento.getText(), "$,");
            String iva = EliminaCaracteres(this.txt_iva.getText(), "$,");

            wr.println("#COMPROBANTE	");
            wr.println("SERIE:" + serie);
            wr.println("FOLIO:" + folio_actual);
            wr.println("FECHA: " + fecha + "T" + hora + ":" + minutos + ":" + segundos + "");
            wr.println("FORMADEPAGO: " + this.txt_forma_pago.getText());
            wr.println("CONDICIONESDEPAGO: " + this.txt_condiciones_pago.getText());
            wr.println("SUBTOTAL: " + subtotal);
            wr.println("TOTAL: " + total);
            wr.println("TOTALDESCUENTO: " + descuento);
            wr.println("MONEDA: MN	");
            wr.println("TIPOCAMBIO:");
            wr.println("MOTIVODELDESCUENTO:	");
            wr.println("TIPODECOMPROBANTE: INGRESO");
            wr.println("METODODEPAGO: " + txt_metodo_pago.getText());
            wr.println("CUENTADEPAGO: " + txt_cuenta_pago.getText());
            wr.println("LUGAREXPEDICION: " + lugar_expedicionEm);
            wr.println("NUMERODECUENTADEPAGO: N/A");
            wr.println("#FIN COMPROBANTE");
            wr.println("#EMISOR	");
            wr.println("NOMBREEM: " + razon_socialEm);
            wr.println("RFCEM: " + rfcEm);
            wr.println("REGIMENFISCALEM1: " + regimen_fiscalEm);
            wr.println("CALLEEM:" + calleEm);
            wr.println("NOEXTERIOREM:" + no_extEm);
            wr.println("COLONIAEM:" + coloniaEm);
            wr.println("LOCALIDADEM:" + ciudadEm);
            wr.println("MUNICIPIOEM: " + txt_municipio_cliente.getText());
            wr.println("ESTADOEM: " + estadoEm);
            wr.println("PAISEM: " + paisEm);
            wr.println("CODIGOPOSTALEM:" + cpEm);
            wr.println("REFERENCIAEM:");
            wr.println("EXP_CALLE:");
            wr.println("EXP_COLONIA:");
            wr.println("EXP_LOCALIDAD:");
            wr.println("EXP_MUNICIPIO:");
            wr.println("EXP_ESTADO:");
            wr.println("EXP_PAIS:");
            wr.println("EXP_CODIGOPOSTAL:");
            wr.println("EXP_REFERENCIA:");
            wr.println("#FIN EMISOR	");
            wr.println("#RECEPTOR	");
            wr.println("NOMBRERE: " + this.txt_razon_social_cliente.getText());
            wr.println("RFCRE: " + txt_rfc_cliente.getText());
            wr.println("CALLERE: " + this.txt_calle_cliente.getText());
            wr.println("NOEXTERIORRE: " + this.txt_noexterior_cliente.getText());
            wr.println("NOINTERIORRE:" + this.txt_nointerior_cliente.getText());
            wr.println("COLONIARE:" + this.txt_colonia_cliente.getText());
            wr.println("REFERENCIARE:" + txt_referencia.getText());
            wr.println("LOCALIDADRE: " + this.txt_localidad_cliente.getText());
            wr.println("MUNICIPIORE: " + this.txt_municipio_cliente.getText());
            wr.println("ESTADORE: " + this.txt_estado_cliente.getText());
            wr.println("PAISRE: MEXICO");
            wr.println("CODIGOPOSTALRE: " + this.txt_codigopostal_cliente.getText());
            wr.println("#FIN RECEPTOR	");
            wr.println("#CONCEPTOS	");
            wr.println("LINEA1:|1|Consumo de alimentos|" + subtotal + "|" + subtotal + "|N/A|||");
            wr.println("#FIN CONCEPTOS	");
            wr.println("#TOTALIMPUESTOS	");
            wr.println("TOTALIMPRETENIDOS:");
            wr.println("TOTALIMPTRASLADADOS:0.00");
            wr.println("#FIN TOTALIMPUESTOS");
            wr.println("#TRASLADOS");
            wr.println("TRASLADO1: |IVA|" + iva_txt + "%|" + iva + "");
            wr.println("#FIN TRASLADOS	");
            wr.println("#RETENCIONES");
            wr.println("#FIN RETENCIONES");
            wr.println("#LEYENDAS");
            wr.println("LEYENDA1: " + this.txt_notas.getText());
            wr.println("LEYENDA2:");
            wr.println("LEYENDA3:");
            wr.println("LEYENDA4:");
            wr.println("LEYENDA5:");
            wr.println("#FIN LEYENDAS	");
            wr.println("#EXTRAS	");
            wr.println("customData_CorreoElectronico:" + this.txt_correo_cliente.getText());
            wr.println("customData_A:");
            wr.println("customData_B:");
            wr.println("customData_C:");
            wr.println("customData_D:");
            wr.println("customData_E:");
            wr.println("#FIN EXTRAS	");

            wr.close();

            bw.close();
            //val++;

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL PROCESAR LA INFORMACION " + e);

        };

    }

    public static boolean validaFormatoRFC(String rfc) {
        boolean res = false;
        if (rfc.matches("[A-Z,Ñ,&]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z,0-9]?[A-Z,0-9]?[0-9,A-Z]?")) {
            res = true;
        }
        return res;
    }

    public void conectar() {
        // conexion.setIp(IpServer);
        conexion.conectate();
    }

    public void desconectar() {
        conexion.desconectar();
    }

    public void traer_datos() {
        conectar();

        try {
            Connection con = conexion.getConnection();
            Statement s = con.createStatement();
            ResultSet res = s.executeQuery("SELECT * FROM datos_generales");

            res.next();

            this.txt_serie.setText(res.getString("serie") + " - " + res.getString("folio_actual"));
            serie = res.getString("serie");
            this.lbl_impuesto.setText(res.getString("impuesto") + " " + res.getString("tasa_impuesto") + " %");
            iva_txt = Float.parseFloat(res.getString("tasa_impuesto"));
            iva_global = (float) iva_txt / 100;
            folio_actual = Integer.parseInt(res.getString("folio_actual"));
            folio_final = Integer.parseInt(res.getString("folio_final"));

            rfcEm = res.getString("RFC");
            razon_socialEm = res.getString("razon_social");
            calleEm = res.getString("calle");
            no_extEm = res.getString("no_ext");
            no_intEm = res.getString("no_int");
            coloniaEm = res.getString("colonia");
            ciudadEm = res.getString("ciudad");
            estadoEm = res.getString("estado");
            paisEm = res.getString("pais");
            cpEm = res.getString("cod_postal");
            referenciaEm = res.getString("referencia");
            lugar_expedicionEm = res.getString("lugar_expedicion");
            regimen_fiscalEm = res.getString("regimen_fiscal");

            rutaPDF = res.getString("ruta_pdf");
            rutaXML = res.getString("ruta_xml");
            rutaEntrada = res.getString("ruta_entrada");

            if (rutaPDF != null && rutaXML != null && rutaEntrada != null) {
                if (!rutaPDF.equals("") && !rutaXML.equals("") && !rutaEntrada.equals("")) { //validamos que esten guardadas las rutas para guardar los archivos PDF, TXT, XML
                    rutas_existen = true;
                }
            }

            System.out.println("iva globlal: " + iva_global);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        desconectar();
    }

    public void fecha_sistema() {
        Calendar fecha = Calendar.getInstance();
        String mes = Integer.toString(fecha.get(Calendar.MONTH) + 1);
        String dia = Integer.toString(fecha.get(Calendar.DATE));
        String auxMes = null, auxDia = null;

        if (mes.length() == 1) {
            auxMes = "0" + mes;
            fecha_sistema = fecha.get(Calendar.DATE) + "/" + auxMes + "/" + fecha.get(Calendar.YEAR);

            if (dia.length() == 1) {
                auxDia = "0" + dia;
                fecha_sistema = auxDia + "/" + auxMes + "/" + fecha.get(Calendar.YEAR);

            }

        } else {
            fecha_sistema = fecha.get(Calendar.DATE) + "/" + (fecha.get(Calendar.MONTH) + 1) + "/" + fecha.get(Calendar.YEAR);
        }
    }

    public void fecha() {
        Calendar c = new GregorianCalendar();

        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH));
        String annio = Integer.toString(c.get(Calendar.YEAR));

        int mesaux = (Integer.parseInt(mes)) + 1;
        String mesaux2 = Integer.toString(mesaux);
        if (mesaux2.length() == 1) {
            mesaux2 = "0" + mesaux2;
        }

        fecha = annio + "-" + mesaux2 + "-" + dia;
    }

    public void calcuar() {
        float cuenta = 0, subtotal = 0, iva = 0, total = 0;
        String auxCuenta;
        auxCuenta = EliminaCaracteres(txt_precio_cuenta.getText(), "$,");
        cuenta = Float.parseFloat(auxCuenta);

        if (check_calcular.isSelected()) {
            subtotal = cuenta;
            iva = (float) (cuenta * iva_global);
            total = subtotal + iva;

            txt_subtotal.setValue(subtotal);
            txt_iva.setValue(iva);
            txt_total.setValue(total);

        } else {
            total = cuenta;
            iva = (float) ((cuenta / (iva_global + 1)) * iva_global);
            subtotal = total - iva;

            txt_subtotal.setValue(subtotal);
            txt_iva.setValue(iva);
            txt_total.setValue(total);
        }

    }
    private void txt_rfc_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rfc_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(1, true);
        }
    }//GEN-LAST:event_txt_rfc_clienteFocusGained

    private void txt_razon_social_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_razon_social_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(2, true);
        }
    }//GEN-LAST:event_txt_razon_social_clienteFocusGained

    private void txt_calle_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_calle_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(3, true);
        }
    }//GEN-LAST:event_txt_calle_clienteFocusGained

    private void txt_noexterior_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_noexterior_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(4, true);
        }
    }//GEN-LAST:event_txt_noexterior_clienteFocusGained

    private void txt_nointerior_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nointerior_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(5, true);
        }
    }//GEN-LAST:event_txt_nointerior_clienteFocusGained

    private void txt_colonia_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_colonia_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(6, true);
        }
    }//GEN-LAST:event_txt_colonia_clienteFocusGained

    private void txt_localidad_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_localidad_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(8, true);
        }
    }//GEN-LAST:event_txt_localidad_clienteFocusGained

    private void txt_municipio_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_municipio_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(9, true);
        }
    }//GEN-LAST:event_txt_municipio_clienteFocusGained

    private void txt_estado_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_estado_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(10, true);
        }
    }//GEN-LAST:event_txt_estado_clienteFocusGained

    private void txt_pais_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_pais_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(11, true);
        }
    }//GEN-LAST:event_txt_pais_clienteFocusGained

    private void txt_correo_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_correo_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(12, true);
        }
    }//GEN-LAST:event_txt_correo_clienteFocusGained

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalActionPerformed

    private void check_calcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_calcularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_calcularActionPerformed

    private void txt_forma_pagoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_forma_pagoFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(13, true);
        }
    }//GEN-LAST:event_txt_forma_pagoFocusGained

    private void txt_condiciones_pagoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_condiciones_pagoFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(14, true);
        }
    }//GEN-LAST:event_txt_condiciones_pagoFocusGained

    private void txt_metodo_pagoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_metodo_pagoFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(15, true);
        }
    }//GEN-LAST:event_txt_metodo_pagoFocusGained

    private void txt_cuenta_pagoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cuenta_pagoFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(16, true);
        }
    }//GEN-LAST:event_txt_cuenta_pagoFocusGained

    private void check_calcularMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_check_calcularMouseClicked
        // TODO add your handling code here:
        calcuar();
    }//GEN-LAST:event_check_calcularMouseClicked

    private void check_desglosarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_check_desglosarMouseClicked
        // TODO add your handling code here:
        calcuar();
    }//GEN-LAST:event_check_desglosarMouseClicked

    private void Jbtn_generar_cfdiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_generar_cfdiActionPerformed
        // TODO add your handling code here:
        if (rutas_existen == true) {
            if (folio_actual <= folio_final) {
                if (!this.txt_rfc_cliente.getText().equals("") || !this.txt_razon_social_cliente.getText().equals("") || !this.txt_pais_cliente.getText().equals("")) {
                    if ((txt_codigopostal_cliente.getText().length() != 0) && (txt_codigopostal_cliente.getText().length() == 5)) {
                        if (validaFormatoRFC(txt_rfc_cliente.getText().trim()) == true) {
                            if (!txt_total.getText().equals("0.00")) {
                                if (!txt_forma_pago.getText().equals("")) {
                                    if (!txt_metodo_pago.getText().equals("")) {
                                        if ((funcion.isEmail(txt_correo_cliente.getText())) || (txt_correo_cliente.getText().equals(""))) {
                                            if (!this.txt_municipio_cliente.getText().equals("")) {
                                                //escribir("C:/FACTURAS/factura "+serie+"-"+folio_actual+".txt");
                                                escribir(rutaEntrada + "/" + serie + "-" + folio_actual + ".txt");
                                                try {
                                                    Thread.sleep(3000);
                                                } catch (InterruptedException ex) {
                                                    Logger.getLogger(facturacion.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                                JOptionPane.showMessageDialog(null, "Todo va bien \nrevisa el estatus en consultas de CFDI ", "ok", JOptionPane.INFORMATION_MESSAGE);
                                                principal.jTabbedPane1.setSelectedIndex(5);

                                                principal.validar_ventana_facturacion = true;

                                                // Agregar el cliente si no se ecnuentra en la base de datos
                                                funcion.conectate();
                                                boolean existe = funcion.existe("clientes", "rfc", "'" + this.txt_rfc_cliente.getText() + "'");
                                                if (existe == false) {
                                                    String datos[] = {iniciar_sesion.id_usuario_global, this.txt_rfc_cliente.getText(), this.txt_razon_social_cliente.getText(), this.txt_calle_cliente.getText(), this.txt_noexterior_cliente.getText(), this.txt_nointerior_cliente.getText(), this.txt_colonia_cliente.getText(), this.txt_codigopostal_cliente.getText(), this.txt_localidad_cliente.getText(), this.txt_municipio_cliente.getText(), this.txt_estado_cliente.getText(), this.txt_pais_cliente.getText(), this.txt_correo_cliente.getText()};
                                                    funcion.InsertarRegistro(datos, "insert into clientes (id_empleado,rfc,razon_social,calle,noExt,noInt,colonia,cp,localidad,delegacion,estado,pais,email) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                                                }
                                                funcion.desconecta();
                                                this.dispose();
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Municipio no puede ir vacio.. =( ", "Error ", JOptionPane.INFORMATION_MESSAGE);
                                                txt_municipio_cliente.requestFocus();
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Correo electronico no valido ", "Error ", JOptionPane.INFORMATION_MESSAGE);
                                            txt_correo_cliente.requestFocus();
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Metodo de pago es obligatorio ", "Error ", JOptionPane.INFORMATION_MESSAGE);
                                        txt_metodo_pago.requestFocus();
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Forma de pago es obligatorio ", "Error ", JOptionPane.INFORMATION_MESSAGE);
                                    txt_forma_pago.requestFocus();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Total no puede ir en ceros. =( ", "Error ", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Favor de revisar que este bien escrito el RFC", "RFC INVALIDO", 0);
                            txt_rfc_cliente.requestFocus();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Codigo postal invalido", "Error ", 0);
                        txt_codigopostal_cliente.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Faltan parametros", "Error", 0);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Se han agotado los folios asignados", "Error", 0);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No esta configurada la ruta para guardar los archivos de facturacion \nConfigura las  3 rutas en datos generales: \nLa ruta para PDF, XML y TXT (de entrada)", "Error", 0);
        }
    }//GEN-LAST:event_Jbtn_generar_cfdiActionPerformed

    private void txt_rfc_clienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rfc_clienteKeyReleased
        // TODO add your handling code here:

        this.index_cursor_rfc = txt_rfc_cliente.getText().trim().length();
        if ((evt.getKeyCode() == 8) || (evt.getKeyCode() == 127)) {
            txt_razon_social_cliente.setText("");
            txt_calle_cliente.setText("");
            txt_noexterior_cliente.setText("");
            txt_nointerior_cliente.setText("");
            txt_colonia_cliente.setText("");
            txt_localidad_cliente.setText("");
//txt_referencia_cliente.setText("");
            txt_codigopostal_cliente.setText("");
            txt_municipio_cliente.setText("");
            txt_estado_cliente.setText("");
            txt_pais_cliente.setText("");
        } else {
            if ((!txt_rfc_cliente.getText().trim().substring(0, this.index_cursor_rfc).matches("[a-zA-Z0-9]+")) && (!txt_rfc_cliente.getText().trim().contains("&"))) {
                return;
            }
            if (txt_rfc_cliente.getText().trim().equals("")) {
                txt_razon_social_cliente.setText("");
                txt_calle_cliente.setText("");
                txt_noexterior_cliente.setText("");
                txt_nointerior_cliente.setText("");
                txt_colonia_cliente.setText("");
                txt_localidad_cliente.setText("");
//txt_referencia_cliente.setText("");
                txt_codigopostal_cliente.setText("");
                txt_municipio_cliente.setText("");
                txt_estado_cliente.setText("");
                txt_pais_cliente.setText("");
            } else {
                if ((evt.getKeyCode() == 16) || (evt.getKeyCode() == 20) || (evt.getKeyCode() == 35) || (evt.getKeyCode() == 36) || (evt.getKeyCode() == 37) || (evt.getKeyCode() == 39) || (evt.getKeyCode() == 40)) {
                    return;
                }
                conectar();
                Statement instruccion = null;
                Connection canal = null;
                ResultSet rs = null;
                try {
                    canal = conexion.getConnection();
                    instruccion = canal.createStatement();

                    rs = instruccion.executeQuery("SELECT rfc, razon_social, calle, noExt, noInt, colonia, cp, localidad, delegacion, estado, pais, email FROM clientes WHERE RFC LIKE \"" + txt_rfc_cliente.getText().trim() + "%" + "\"");
                    if (rs.next()) {
                        this.index_cursor_rfc = txt_rfc_cliente.getText().trim().length();

                        txt_rfc_cliente.setText(rs.getString("rfc").substring(this.index_cursor_rfc, txt_rfc_cliente.getText().trim().length()));
                        txt_rfc_cliente.setText(txt_rfc_cliente.getText() + rs.getString("RFC").substring(txt_rfc_cliente.getText().trim().length(), rs.getString("rfc").length()));
                        txt_rfc_cliente.setSelectionStart(this.index_cursor_rfc);
                        txt_razon_social_cliente.setText(rs.getString("razon_social"));
                        txt_calle_cliente.setText(rs.getString("calle"));
                        txt_noexterior_cliente.setText(rs.getString("noExt"));
                        txt_nointerior_cliente.setText(rs.getString("noInt"));
                        txt_colonia_cliente.setText(rs.getString("colonia"));
                        txt_localidad_cliente.setText(rs.getString("localidad"));
                        //txt_referencia_cliente.setText(rs.getString("Referencia"));
                        txt_codigopostal_cliente.setText(rs.getString("cp"));
                        txt_municipio_cliente.setText(rs.getString("delegacion"));
                        txt_estado_cliente.setText(rs.getString("estado"));
                        txt_pais_cliente.setText(rs.getString("pais"));
                        txt_correo_cliente.setText(rs.getString("email"));
                    } else {
                        txt_razon_social_cliente.setText("");
                        txt_calle_cliente.setText("");
                        txt_noexterior_cliente.setText("");
                        txt_nointerior_cliente.setText("");
                        txt_colonia_cliente.setText("");
                        txt_localidad_cliente.setText("");
                        //txt_referencia_cliente.setText("");
                        txt_codigopostal_cliente.setText("");
                        txt_municipio_cliente.setText("");
                        txt_estado_cliente.setText("");
                        txt_pais_cliente.setText("");
                        txt_correo_cliente.setText("");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Hubo un error al traer los datos del cliente: " + ex, "Error", 0);
                } finally {
                    if (canal != null) {
                        try {
                            canal.close();
                        } catch (SQLException ex) {
                            //    java.util.logging.Logger.getLogger(JIFMod_Facturacion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    if (instruccion != null) {
                        try {
                            instruccion.close();
                        } catch (SQLException ex) {
                            // java.util.logging.Logger.getLogger(JIFMod_Facturacion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    if (rs != null) /*       */ {
                        try {
                            rs.close();
                        } catch (SQLException ex) {
                            //   java.util.logging.Logger.getLogger(JIFMod_Facturacion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }

        desconectar();
    }//GEN-LAST:event_txt_rfc_clienteKeyReleased

    private void txt_notasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_notasFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(17, true);
        }
    }//GEN-LAST:event_txt_notasFocusGained

    private void txt_referenciaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_referenciaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_referenciaFocusGained

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(facturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(facturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(facturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(facturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                facturacion dialog = new facturacion(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Jbtn_generar_cfdi;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox check_calcular;
    private javax.swing.JCheckBox check_desglosar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_impuesto;
    private javax.swing.JPanel panel_datos_cliente;
    private javax.swing.JPanel panel_datos_pago;
    private javax.swing.JPanel panel_teclado;
    private javax.swing.JPanel panel_totales;
    private javax.swing.JTextField txt_calle_cliente;
    private javax.swing.JFormattedTextField txt_codigopostal_cliente;
    private javax.swing.JTextField txt_colonia_cliente;
    private javax.swing.JTextField txt_condiciones_pago;
    private javax.swing.JTextField txt_correo_cliente;
    private javax.swing.JTextField txt_cuenta_pago;
    private javax.swing.JFormattedTextField txt_descuento;
    private javax.swing.JTextField txt_estado_cliente;
    private javax.swing.JTextField txt_fecha;
    private javax.swing.JTextField txt_forma_pago;
    private javax.swing.JFormattedTextField txt_iva;
    private javax.swing.JTextField txt_localidad_cliente;
    private javax.swing.JTextField txt_metodo_pago;
    private javax.swing.JTextField txt_municipio_cliente;
    private javax.swing.JTextField txt_noexterior_cliente;
    private javax.swing.JTextField txt_nointerior_cliente;
    private javax.swing.JTextField txt_notas;
    private javax.swing.JTextField txt_pais_cliente;
    private javax.swing.JFormattedTextField txt_precio_cuenta;
    private javax.swing.JTextField txt_razon_social_cliente;
    private javax.swing.JTextField txt_referencia;
    private javax.swing.JTextField txt_rfc_cliente;
    private javax.swing.JTextField txt_serie;
    private javax.swing.JFormattedTextField txt_subtotal;
    private javax.swing.JFormattedTextField txt_total;
    // End of variables declaration//GEN-END:variables
}
