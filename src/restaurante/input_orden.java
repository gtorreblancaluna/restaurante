/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante;

import clases.conectate;
import clases.sqlclass;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
//import static restaurante.iniciar_sesion.administrador_global;
//import static restaurante.iniciar_sesion.apellidos_usuario_global;
//import static restaurante.iniciar_sesion.id_usuario_global;
//import static restaurante.iniciar_sesion.nombre_usuario_global;
//import static restaurante.iniciar_sesion.puesto_global;
import ve.edu.ucab.keyboard.Keyboard;
import ve.edu.ucab.logic.LogicComponent;
import ve.edu.ucab.logic.LogicListComponent;

/**
 *
 * @author KONESH
 */
public class input_orden extends javax.swing.JDialog {

    public static String num_comensales_global, nombre_cliente_global, id_clientes, id_empleado, cargo_trabajador, nombre_trabajador;
    boolean selecciono_clientes = false, like_nombre = false;
    conectate conexion = new conectate();
//    principal aux = new principal();
    private Keyboard keyboard;
    sqlclass funcion = new sqlclass();
    Object[][] dtconduc;

    /**
     * 
     * Creates new form input_orden
     */
    public input_orden(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tabla_clientes();
        this.setLocationRelativeTo(null);

        LogicListComponent components = new LogicListComponent();

        components.addComponent(new LogicComponent(this.txt_cant_comensales, 1, true, false, false, true));
        components.addComponent(new LogicComponent(this.txt_nombre_cliente, 2, true, false, false, false));

        components.addComponent(new LogicComponent(this.txt_apellidos_cliente, 3, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_celular_cliente, 4, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_num_trabajador, 5, true, false, false, true));

        keyboard = new Keyboard(components, true);
        //keyboard.
        panel_teclado.setLayout(new GridLayout(1, 1));
        panel_teclado.add(keyboard);
        principal.validar = false;
    }

    public void continuar() {
        if (this.txt_cant_comensales.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Indica el numero de personas que van a ocupar la mesa...", "Error", JOptionPane.INFORMATION_MESSAGE);
            this.txt_cant_comensales.requestFocus();
        } else {
            num_comensales_global = this.txt_cant_comensales.getText();
            /*if (!txt_nombre_cliente.getText().equals("")) {
             nombre_cliente_global = this.txt_nombre_cliente.getText();
             } else {
             nombre_cliente_global = "Publico en general";
             }*/
            if (!txt_nombre_cliente.getText().equals("") && !this.txt_apellidos_cliente.getText().equals("")) {
                if (this.txt_num_trabajador.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Indica tu contraseña para continuar ", "Error", JOptionPane.INFORMATION_MESSAGE);
                    this.txt_num_trabajador.setText("");
                    this.txt_num_trabajador.requestFocus();
                } else {
                    ///// VALIDAR TRABAJADOR
                    String pswd = new String(this.txt_num_trabajador.getPassword());
                    //String area = null, priv = null;
                    int inm = 0;
                    conectar();
                    try {
                        Connection con = conexion.getConnection();
                        Statement s = con.createStatement();
                        ResultSet rs = s.executeQuery("SELECT * FROM empleado WHERE contraseña = '" + pswd + "'");
                        if (rs.first()) { // Traemos datos
                            inm = 1;
                            id_empleado = rs.getString("id_empleado");
                            nombre_trabajador = rs.getString("nombre");
                            cargo_trabajador = rs.getString("puesto");
                        }
                        con.close();
                        if (inm == 1) { // Es correcta la validacion //
                            System.out.println("Entra a insert into");
                            nombre_cliente_global = this.txt_nombre_cliente.getText();
                            //// funcion.conectate();
                            String datos[] = {txt_nombre_cliente.getText(), txt_apellidos_cliente.getText(), this.txt_celular_cliente.getText()};
                            funcion.InsertarRegistro(datos, "insert into clientes (nombre,apellidos,tel_movil) values (?,?,?)");
                            id_clientes = funcion.ultimo_id("id_clientes", "clientes");
                            // funcion.desconecta();
                            principal.validar = true;
                            this.dispose();

                        } else {
                            JOptionPane.showMessageDialog(null, "No coincide contraseña ", "Error ", JOptionPane.ERROR_MESSAGE);
                            this.txt_num_trabajador.setText("");
                            this.txt_num_trabajador.requestFocus();
                        }
                    } catch (SQLException | HeadlessException ex) {
                        java.awt.Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "Error con la conexión a la base de datos: " + ex, "Error SQL", JOptionPane.ERROR_MESSAGE);
                    }

                    //// FINALIZA VALIDACION TRABAJADOR
                }
            } else {
                JOptionPane.showMessageDialog(null, "Faltan parametros para el Cliente ", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Jbtn_ok = new javax.swing.JButton();
        panel_teclado = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txt_nombre_cliente = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txt_cant_comensales = new javax.swing.JFormattedTextField();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tabla_clientes = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        txt_apellidos_cliente = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txt_celular_cliente = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txt_num_trabajador = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        Jbtn_ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Actions-dialog-ok-apply-icon.png"))); // NOI18N
        Jbtn_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_okActionPerformed(evt);
            }
        });

        panel_teclado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panel_tecladoLayout = new javax.swing.GroupLayout(panel_teclado);
        panel_teclado.setLayout(panel_tecladoLayout);
        panel_tecladoLayout.setHorizontalGroup(
            panel_tecladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 594, Short.MAX_VALUE)
        );
        panel_tecladoLayout.setVerticalGroup(
            panel_tecladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 207, Short.MAX_VALUE)
        );

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_nombre_cliente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txt_nombre_cliente.setToolTipText("");
        txt_nombre_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_nombre_clienteFocusGained(evt);
            }
        });
        txt_nombre_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombre_clienteActionPerformed(evt);
            }
        });
        txt_nombre_cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nombre_clienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_nombre_clienteKeyReleased(evt);
            }
        });
        jPanel1.add(txt_nombre_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 130, -1));

        jLabel31.setText("Nombre:");
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, -1));

        txt_cant_comensales.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txt_cant_comensales.setToolTipText("");
        txt_cant_comensales.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_cant_comensales.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_cant_comensalesFocusGained(evt);
            }
        });
        jPanel1.add(txt_cant_comensales, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 80, -1));

        jLabel29.setText("Comensales:");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        tabla_clientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabla_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_clientesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabla_clientesMouseEntered(evt);
            }
        });
        tabla_clientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_clientesKeyPressed(evt);
            }
        });
        jScrollPane18.setViewportView(tabla_clientes);

        jPanel1.add(jScrollPane18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 700, 180));

        txt_apellidos_cliente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txt_apellidos_cliente.setToolTipText("");
        txt_apellidos_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_apellidos_clienteFocusGained(evt);
            }
        });
        txt_apellidos_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_apellidos_clienteActionPerformed(evt);
            }
        });
        txt_apellidos_cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_apellidos_clienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_apellidos_clienteKeyReleased(evt);
            }
        });
        jPanel1.add(txt_apellidos_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 150, -1));

        jLabel32.setText("Apellidos:");
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, -1, -1));

        txt_celular_cliente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txt_celular_cliente.setToolTipText("");
        txt_celular_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_celular_clienteFocusGained(evt);
            }
        });
        txt_celular_cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_celular_clienteKeyPressed(evt);
            }
        });
        jPanel1.add(txt_celular_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 163, -1));

        jLabel33.setText("Celular:");
        jPanel1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, -1, -1));

        jLabel34.setText("Ingresa tu contraseña:");
        jPanel1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 140, -1));

        txt_num_trabajador.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_num_trabajadorFocusGained(evt);
            }
        });
        txt_num_trabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_num_trabajadorActionPerformed(evt);
            }
        });
        txt_num_trabajador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_num_trabajadorKeyPressed(evt);
            }
        });
        jPanel1.add(txt_num_trabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 120, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(panel_teclado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Jbtn_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Jbtn_ok)
                        .addGap(176, 176, 176))
                    .addComponent(panel_teclado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Jbtn_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_okActionPerformed
        // TODO add your handling code here:        
        continuar();


    }//GEN-LAST:event_Jbtn_okActionPerformed

    private void txt_nombre_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nombre_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(2, true);
        }
    }//GEN-LAST:event_txt_nombre_clienteFocusGained

    private void txt_nombre_clienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombre_clienteKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_nombre_clienteKeyPressed

    private void tabla_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_clientesMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            if (this.txt_cant_comensales.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Indica el numero de personas que van a ocupar la mesa...", "Error", JOptionPane.INFORMATION_MESSAGE);
                this.txt_cant_comensales.requestFocus();
            } else {
                if (this.txt_num_trabajador.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Indica tu contraseña para continuar ", "Error", JOptionPane.INFORMATION_MESSAGE);
                    this.txt_num_trabajador.setText("");
                    this.txt_num_trabajador.requestFocus();
                } else {

                    ///// VALIDAR TRABAJADOR
                    String pswd = new String(this.txt_num_trabajador.getPassword());
                    //String area = null, priv = null;
                    int inm = 0;
                    conectar();
                    try {
                        Connection con = conexion.getConnection();
                        Statement s = con.createStatement();
                        ResultSet rs = s.executeQuery("SELECT * FROM empleado WHERE contraseña = '" + pswd + "'");
                        if (rs.first()) { // Traemos datos
                            inm = 1;
                            id_empleado = rs.getString("id_empleado");
                            nombre_trabajador = rs.getString("nombre");
                            cargo_trabajador = rs.getString("puesto");
                        }
                        con.close();
                        if (inm == 1) { // Es correcta la validacion //
                            nombre_cliente_global = this.txt_nombre_cliente.getText();
                            //// funcion.conectate();
                            int sel = tabla_clientes.getSelectedRow();
                            nombre_cliente_global = tabla_clientes.getValueAt(sel, 1).toString();
                            num_comensales_global = this.txt_cant_comensales.getText();

                            //this.lbl_nombre_cliente.setText(this.tabla_clientes.getValueAt(sel, 1).toString() + " " + tabla_clientes.getValueAt(sel, 2).toString());
                            id_clientes = tabla_clientes.getValueAt(sel, 0).toString();
                            selecciono_clientes = true;
                            principal.validar = true;
                            this.dispose();

                        } else {
                            JOptionPane.showMessageDialog(null, "No coincide contraseña ", "Error ", JOptionPane.ERROR_MESSAGE);
                            this.txt_num_trabajador.setText("");
                            this.txt_num_trabajador.requestFocus();
                        }
                    } catch (SQLException | HeadlessException ex) {
                        java.awt.Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "Error con la conexión a la base de datos: " + ex, "Error SQL", JOptionPane.ERROR_MESSAGE);
                    }

                }

                //// FINALIZA VALIDACION TRABAJADOR  
            }
        }

    }//GEN-LAST:event_tabla_clientesMouseClicked

    private void tabla_clientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_clientesKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_clientesKeyPressed

    private void txt_apellidos_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_apellidos_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(3, true);
        }
    }//GEN-LAST:event_txt_apellidos_clienteFocusGained

    private void txt_apellidos_clienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellidos_clienteKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            continuar();

        }
    }//GEN-LAST:event_txt_apellidos_clienteKeyPressed

    private void txt_celular_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_celular_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(4, true);
        }
    }//GEN-LAST:event_txt_celular_clienteFocusGained

    private void txt_celular_clienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_celular_clienteKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            continuar();

        }
    }//GEN-LAST:event_txt_celular_clienteKeyPressed

    private void txt_nombre_clienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombre_clienteKeyReleased
        // TODO add your handling code here:
        like_nombre = true;
        tabla_clientes_like();
    }//GEN-LAST:event_txt_nombre_clienteKeyReleased

    private void txt_apellidos_clienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellidos_clienteKeyReleased
        // TODO add your handling code here:
        like_nombre = false;
        tabla_clientes_like();
    }//GEN-LAST:event_txt_apellidos_clienteKeyReleased

    private void txt_num_trabajadorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_num_trabajadorFocusGained
        // TODO add your handling code here
        if (keyboard != null) {
            keyboard.setComponentSelected(5, true);
        }
    }//GEN-LAST:event_txt_num_trabajadorFocusGained

    private void txt_num_trabajadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_num_trabajadorKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            if (this.txt_cant_comensales.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Indica el numero de personas que van a ocupar la mesa...", "Error", JOptionPane.INFORMATION_MESSAGE);
                this.txt_cant_comensales.requestFocus();
            } else {
                num_comensales_global = this.txt_cant_comensales.getText();
                /*if (!txt_nombre_cliente.getText().equals("")) {
                 nombre_cliente_global = this.txt_nombre_cliente.getText();
                 } else {
                 nombre_cliente_global = "Publico en general";
                 }*/
                

                    if (this.txt_num_trabajador.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Indica tu contraseña para continuar ", "Error", JOptionPane.INFORMATION_MESSAGE);
                        this.txt_num_trabajador.setText("");
                        this.txt_num_trabajador.requestFocus();
                    } else {

                        ///// VALIDAR TRABAJADOR
                        String pswd = new String(this.txt_num_trabajador.getPassword());
                        //String area = null, priv = null;
                        int inm = 0;
                        conectar();
                        try {
                            Connection con = conexion.getConnection();
                            Statement s = con.createStatement();
                            ResultSet rs = s.executeQuery("SELECT * FROM empleado WHERE contraseña = '" + pswd + "'");
                            if (rs.first()) { // Traemos datos
                                inm = 1;
                                id_empleado = rs.getString("id_empleado");
                                nombre_trabajador = rs.getString("nombre");
                                cargo_trabajador = rs.getString("puesto");
                            }
                            con.close();
                            if (inm == 1) { // Es correcta la validacion //
                                 // funcion.conectate();
                                 
                                if (txt_nombre_cliente.getText().equals("") && this.txt_apellidos_cliente.getText().equals("")) {
                                    nombre_cliente_global = "Publico en general";
                                    id_clientes = "1"; //asignamos el numero 1 para el cliente publico en general 
                                    // funcion.desconecta();
                                    principal.validar = true;
                                    this.dispose();
                                }else if (!txt_nombre_cliente.getText().equals("") && !this.txt_apellidos_cliente.getText().equals("")){                              
                                   
                                    nombre_cliente_global = this.txt_nombre_cliente.getText();                                   
                                    String datos[] = {iniciar_sesion.id_usuario_global, txt_nombre_cliente.getText(), txt_apellidos_cliente.getText(), this.txt_celular_cliente.getText()};
                                    funcion.InsertarRegistro(datos, "insert into clientes (id_empleado,nombre,apellidos,tel_movil) values (?,?,?,?)");
                                    id_clientes = funcion.ultimo_id("id_clientes", "clientes");
                                    // funcion.desconecta();
                                    principal.validar = true;
                                    this.dispose();
                                }else{
                                    JOptionPane.showMessageDialog(null, "Faltan parametros para el Cliente ", "Error", JOptionPane.INFORMATION_MESSAGE);
                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "No coincide contraseña ", "Error ", JOptionPane.ERROR_MESSAGE);
                                this.txt_num_trabajador.setText("");
                                this.txt_num_trabajador.requestFocus();
                            }
                        } catch (SQLException | HeadlessException ex) {
                            java.awt.Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog(null, "Error con la conexión a la base de datos: " + ex, "Error SQL", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    //// FINALIZA VALIDACION TRABAJADOR
//                } else {
//                    JOptionPane.showMessageDialog(null, "Faltan parametros para el Cliente ", "Error", JOptionPane.INFORMATION_MESSAGE);
//                }
            }

        }
    }//GEN-LAST:event_txt_num_trabajadorKeyPressed

    private void tabla_clientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_clientesMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_clientesMouseEntered

    private void txt_cant_comensalesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cant_comensalesFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(1, true);
        }
    }//GEN-LAST:event_txt_cant_comensalesFocusGained

    private void txt_num_trabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_num_trabajadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_num_trabajadorActionPerformed

    private void txt_nombre_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombre_clienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombre_clienteActionPerformed

    private void txt_apellidos_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_apellidos_clienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apellidos_clienteActionPerformed

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
            java.util.logging.Logger.getLogger(input_orden.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(input_orden.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(input_orden.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(input_orden.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                input_orden dialog = new input_orden(new javax.swing.JFrame(), true);
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

    public void tabla_clientes() {
        // funcion.conectate();
        tabla_clientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Nombre", "Apellidos", "Tel Cel", "Tel Casa"};
        String[] colName = {"id_clientes", "nombre", "apellidos", "tel_movil", "tel_fijo"};
        //nombre de columnas, tabla, instruccion sql        
        dtconduc = funcion.GetTabla(colName, "clientes", "SELECT * FROM clientes ORDER BY nombre");
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_clientes.setModel(datos);

        int[] anchos = {10, 100, 190, 100, 80};
        for (int inn = 0; inn < tabla_clientes.getColumnCount(); inn++) {
            tabla_clientes.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }
        tabla_clientes.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_clientes.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_clientes.getColumnModel().getColumn(0).setPreferredWidth(0);

        // funcion.desconecta();
    }

    public void tabla_clientes_like() {
        tabla_clientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Nombre", "Apellidos", "Tel Cel", "Tel Casa"};
        String[] colName = {"id_clientes", "nombre", "apellidos", "tel_movil", "tel_fijo"};
        //nombre de columnas, tabla, instruccion sql        
        if (like_nombre == true) {
            //// funcion.conectate();
            dtconduc = funcion.GetTabla(colName, "clientes", "SELECT * FROM clientes WHERE nombre like '%" + this.txt_nombre_cliente.getText() + "%' ORDER BY nombre");
            //// funcion.desconecta();
        } else {
            //// funcion.conectate();
            dtconduc = funcion.GetTabla(colName, "clientes", "SELECT * FROM clientes WHERE apellidos like '%" + this.txt_apellidos_cliente.getText() + "%' ORDER BY apellidos");
            //// funcion.desconecta();
        }
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_clientes.setModel(datos);

        int[] anchos = {10, 100, 190, 100, 80};
        for (int inn = 0; inn < tabla_clientes.getColumnCount(); inn++) {
            tabla_clientes.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }
        tabla_clientes.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_clientes.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_clientes.getColumnModel().getColumn(0).setPreferredWidth(0);

    }

    public void conectar() {
        // conexion.setIp(IpServer);
        conexion.conectate();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Jbtn_ok;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JPanel panel_teclado;
    private javax.swing.JTable tabla_clientes;
    private javax.swing.JTextField txt_apellidos_cliente;
    private javax.swing.JFormattedTextField txt_cant_comensales;
    private javax.swing.JTextField txt_celular_cliente;
    private javax.swing.JTextField txt_nombre_cliente;
    private javax.swing.JPasswordField txt_num_trabajador;
    // End of variables declaration//GEN-END:variables
}
