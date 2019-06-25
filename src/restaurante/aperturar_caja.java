/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restaurante;

import java.awt.GridLayout;
import java.security.Principal;
import javax.swing.JOptionPane;
import static restaurante.principal.total_pagar;
import ve.edu.ucab.keyboard.Keyboard;
import ve.edu.ucab.logic.LogicComponent;
import ve.edu.ucab.logic.LogicListComponent;

/**
 *
 * @author KONESH
 */
public class aperturar_caja extends javax.swing.JDialog {
    private Keyboard keyboard;
    /**
     * Creates new form cerrar_venta
     */
    public static String por_apertura,comentario;
    public aperturar_caja(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setUndecorated(true);
        initComponents();
        this.txt_dinero_apertura.requestFocus();
        this.setLocationRelativeTo(null);
        
        
        LogicListComponent components = new LogicListComponent();
        
        components.addComponent(new LogicComponent(this.txt_dinero_apertura, 1, true, false, false, true));
        components.addComponent(new LogicComponent(this.txt_comentario_apertura, 2, true, false, false, false));
        
        
        keyboard = new Keyboard(components, true);
        //keyboard.
        panel_teclado.setLayout(new GridLayout(1, 1));
        panel_teclado.add(keyboard);
    }
  
  public String EliminaCaracteres(String s_cadena, String s_caracteres){
  String nueva_cadena = "";
  Character caracter = null;
  boolean valido = true;
 
  /* Va recorriendo la cadena s_cadena y copia a la cadena que va a regresar,
     sólo los caracteres que no estén en la cadena s_caracteres */
  for (int i=0; i<s_cadena.length(); i++)
      {
       valido = true;
       for (int j=0; j<s_caracteres.length(); j++)
           {
            caracter = s_caracteres.charAt(j);
 
            if (s_cadena.charAt(i) == caracter)
               {
                valido = false;
                break;
               }
           }
       if (valido)
           nueva_cadena += s_cadena.charAt(i);
      }
 
  return nueva_cadena;
}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_dinero_apertura = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        Jbtn_ok = new javax.swing.JButton();
        panel_teclado = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_comentario_apertura = new javax.swing.JTextField();
        Jbtn_cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        txt_dinero_apertura.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txt_dinero_apertura.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_dinero_apertura.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        txt_dinero_apertura.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_dinero_aperturaFocusGained(evt);
            }
        });
        txt_dinero_apertura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_dinero_aperturaKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("CANTIDAD PARA APERTURAR CAJA:");

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
            .addGap(0, 743, Short.MAX_VALUE)
        );
        panel_tecladoLayout.setVerticalGroup(
            panel_tecladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 221, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("COMENTARIO:");

        txt_comentario_apertura.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_comentario_apertura.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_comentario_aperturaFocusGained(evt);
            }
        });

        Jbtn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/go-back-icon.png"))); // NOI18N
        Jbtn_cancelar.setToolTipText("Cancelar");
        Jbtn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel_teclado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txt_comentario_apertura))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_dinero_apertura, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Jbtn_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Jbtn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Jbtn_ok, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_dinero_apertura, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Jbtn_cancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_comentario_apertura, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panel_teclado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Jbtn_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_okActionPerformed
        // TODO add your handling code here:
        if(this.txt_dinero_apertura.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Indica el dinero por apertura...", "Error", JOptionPane.INFORMATION_MESSAGE);
            this.txt_dinero_apertura.requestFocus();
        }else{
        principal.validar_caja=true;
        por_apertura = this.txt_dinero_apertura.getText();
        comentario = this.txt_comentario_apertura.getText();
        this.dispose();
        }
    }//GEN-LAST:event_Jbtn_okActionPerformed

    private void txt_dinero_aperturaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_dinero_aperturaFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(1, true);
        }
    }//GEN-LAST:event_txt_dinero_aperturaFocusGained

    private void txt_comentario_aperturaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_comentario_aperturaFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(2, true);
        }
    }//GEN-LAST:event_txt_comentario_aperturaFocusGained

    private void txt_dinero_aperturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_dinero_aperturaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            if(this.txt_dinero_apertura.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Indica el dinero por apertura...", "Error", JOptionPane.INFORMATION_MESSAGE);
            this.txt_dinero_apertura.requestFocus();
        }else{
        principal.validar_caja=true;
        por_apertura = this.txt_dinero_apertura.getText();
        comentario = this.txt_comentario_apertura.getText();
        this.dispose();
        }
        }
    }//GEN-LAST:event_txt_dinero_aperturaKeyPressed

    private void Jbtn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_cancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_Jbtn_cancelarActionPerformed

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
            java.util.logging.Logger.getLogger(aperturar_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(aperturar_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(aperturar_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(aperturar_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                aperturar_caja dialog = new aperturar_caja(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton Jbtn_cancelar;
    private javax.swing.JButton Jbtn_ok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panel_teclado;
    private javax.swing.JTextField txt_comentario_apertura;
    public static javax.swing.JFormattedTextField txt_dinero_apertura;
    // End of variables declaration//GEN-END:variables
}
