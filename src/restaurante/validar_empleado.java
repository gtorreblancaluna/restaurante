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
import static restaurante.iniciar_sesion.administrador_global;
import static restaurante.iniciar_sesion.apellidos_usuario_global;
import static restaurante.iniciar_sesion.id_usuario_global;
import static restaurante.iniciar_sesion.nombre_usuario_global;
import static restaurante.iniciar_sesion.puesto_global;
import ve.edu.ucab.keyboard.Keyboard;
import ve.edu.ucab.logic.LogicComponent;
import ve.edu.ucab.logic.LogicListComponent;

/**
 *
 * @author KONESH
 */
public class validar_empleado extends javax.swing.JDialog {

    public static String num_comensales_global, nombre_cliente_global, id_clientes, id_empleado, cargo_trabajador, nombre_trabajador;
    boolean selecciono_clientes = false, like_nombre = false;
    conectate conexion = new conectate();
//    principal aux = new principal();
    private Keyboard keyboard;
    sqlclass funcion = new sqlclass();
    Object[][] dtconduc;

    /**
     * Creates new form input_orden
     */
    public validar_empleado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        funcion.conectate();
        this.lbl_nombre_empleado.setText("Ingresa tu contraseña "+funcion.GetData("nombre_empleado", "SELECT CONCAT(e.`nombre`,\" \", e.`apellidos`)AS nombre_empleado FROM empleado e, venta v WHERE v.id_empleado=e.id_empleado AND v.id_venta=(SELECT id_venta FROM venta WHERE id_venta = "+ principal.id_venta_global +")"));
//        funcion.desconecta();
        this.setLocationRelativeTo(null);

        LogicListComponent components = new LogicListComponent();

        components.addComponent(new LogicComponent(this.txt_num_trabajador, 1, true, false, false, true));

        keyboard = new Keyboard(components, true);
        //keyboard.
        panel_teclado.setLayout(new GridLayout(1, 1));
        panel_teclado.add(keyboard);
    }

    public void validar_empleado() {
//        funcion.conectate();
        boolean existe = funcion.existe_empleado(this.txt_num_trabajador.getText(), principal.id_venta_global);
//        funcion.desconecta();
        if (existe == true) {
            principal.validar_trabajador = true;
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "No coincide la contraseña con el trabajador que inicio la mesa... ", "Error", JOptionPane.INFORMATION_MESSAGE);
            txt_num_trabajador.setText("");
            txt_num_trabajador.requestFocus();
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
        lbl_nombre_empleado = new javax.swing.JLabel();
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
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_tecladoLayout.setVerticalGroup(
            panel_tecladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 207, Short.MAX_VALUE)
        );

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_nombre_empleado.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jPanel1.add(lbl_nombre_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 330, 20));

        txt_num_trabajador.setToolTipText("Contraseña del empleado que aperturo la mesa");
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
        jPanel1.add(txt_num_trabajador, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 140, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_teclado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Jbtn_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 87, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Jbtn_ok))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_teclado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Jbtn_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_okActionPerformed
        // TODO add your handling code here:   
        validar_empleado();

    }//GEN-LAST:event_Jbtn_okActionPerformed

    private void txt_num_trabajadorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_num_trabajadorFocusGained
        // TODO add your handling code here
        if (keyboard != null) {
            keyboard.setComponentSelected(1, true);
        }
    }//GEN-LAST:event_txt_num_trabajadorFocusGained

    private void txt_num_trabajadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_num_trabajadorKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {

            validar_empleado();

        }

    }//GEN-LAST:event_txt_num_trabajadorKeyPressed

    private void txt_num_trabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_num_trabajadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_num_trabajadorActionPerformed

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
            java.util.logging.Logger.getLogger(validar_empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(validar_empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(validar_empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(validar_empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                validar_empleado dialog = new validar_empleado(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton Jbtn_ok;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_nombre_empleado;
    private javax.swing.JPanel panel_teclado;
    private javax.swing.JPasswordField txt_num_trabajador;
    // End of variables declaration//GEN-END:variables
}
