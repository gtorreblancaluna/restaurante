/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante;

import clases.sqlclass;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static restaurante.principal.id_categorias_seleccion;

/**
 *
 * @author Gerardo Torreblanca
 */
public class mesas extends javax.swing.JDialog {
    
    sqlclass funcion = new sqlclass();
    Object[][] dtconduc;
    boolean agregar = false, modf = false;
    int id_mesa;

    /**
     * Creates new form mesas
     */
    public mesas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        llenar_tabla();
        jbtn_actualizar.setEnabled(false);
        principal.validar_mesas = true;
    }
    
    public void llenar_tabla() {   //      

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Descripcion", "Estado", "Orden"};
        String[] colName = {"id_mesa", "descripcion", "estado", "orden"};

        //nombre de columnas, tabla, instruccion sql
        dtconduc = funcion.GetTabla(colName, "mesa", "SELECT * FROM mesa m where activo=1 order by orden");
        
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla.setModel(datos);
        
        int[] anchos = {10, 200, 90, 90};
        for (int inn = 0; inn < tabla.getColumnCount(); inn++) {
            tabla.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }
        
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    
    public void agregar() {

        //boolean existe = false;

        /*for (int i = 0; i < tabla.getRowCount(); i++) {
         if (txt_mesa.getText().equals((String) tabla.getValueAt(i, 1))) {
         existe = true;
         break;
         }
         }*/
        //  if (existe == false) {
        if (!txt_mesa.getText().equals("") && !txt_orden.getText().equals("")) {
            // funcion.conectate();
            String datos[] = {txt_mesa.getText(), "0", "1", txt_orden.getText()};
            funcion.InsertarRegistro(datos, "insert into mesa (descripcion,estado,activo,orden) values(?,?,?,?) ");
            //  funcion.desconecta();
            llenar_tabla();
            txt_mesa.setText("");
            txt_orden.setText("");
            txt_mesa.requestFocus();
            agregar = false;
        } else {
            JOptionPane.showMessageDialog(null, "No puede ir vacio... ", "Error", JOptionPane.INFORMATION_MESSAGE);
            
        }
        /* } else {
         JOptionPane.showMessageDialog(null, "No se permiten duplicados, ya existe una mesa con ese nomrbe ", "Error", JOptionPane.INFORMATION_MESSAGE);
         }*/
        
    }
    
    public void modificar() {
        boolean existe = false;

        /*for (int i = 0; i < tabla.getRowCount(); i++) {
         if (txt_mesa.getText().equals((String) tabla.getValueAt(i, 1))) {
         existe = true;
         break;
         }
         }*/
        //if (existe == false) {
        if (!txt_mesa.getText().equals("") && !txt_orden.getText().equals("")) {
            // funcion.conectate();
            String datos[] = {txt_mesa.getText(), txt_orden.getText(), (String) tabla.getValueAt(tabla.getSelectedRow(), 0)};
            funcion.UpdateRegistro(datos, "update mesa set descripcion=?,orden=? where id_mesa=?");
            JOptionPane.showMessageDialog(null, "Actualizacion exitosa");
            
            //  funcion.desconecta();
            llenar_tabla();
            modf = false;
            txt_mesa.setText("");
            txt_orden.setText("");
            txt_mesa.requestFocus();
            jbtn_actualizar.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "No puede ir vacio ", "Error", JOptionPane.INFORMATION_MESSAGE);
            
        }
        /*} else {
         JOptionPane.showMessageDialog(null, "No se permiten duplicados, ya existe una mesa con ese nomrbe ", "Error", JOptionPane.INFORMATION_MESSAGE);
         }*/
        
    }
    
    public void eliminar() {
        int opcion = JOptionPane.showConfirmDialog(this, "Desea Eliminar " + (String) tabla.getValueAt(tabla.getSelectedRow(), 1), "Eliminar...", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            // funcion.conectate();
            String datos[] = {"0", (String) tabla.getValueAt(tabla.getSelectedRow(), 0)};
            funcion.UpdateRegistro(datos, "update mesa set activo=? where id_mesa=?");
            JOptionPane.showMessageDialog(null, "Se a quitado de la lista....");
            //  funcion.desconecta();
            llenar_tabla();
            
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        lbl_seleccion_mesa = new javax.swing.JLabel();
        txt_mesa = new javax.swing.JTextField();
        jbtn_agrear = new javax.swing.JButton();
        jbtn_editar = new javax.swing.JButton();
        jbtn_eliminar = new javax.swing.JButton();
        jbtn_actualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_orden = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabla.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tabla);

        lbl_seleccion_mesa.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N

        txt_mesa.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        txt_mesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_mesaActionPerformed(evt);
            }
        });
        txt_mesa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_mesaKeyPressed(evt);
            }
        });

        jbtn_agrear.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jbtn_agrear.setText("Agregar");
        jbtn_agrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_agrearActionPerformed(evt);
            }
        });

        jbtn_editar.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jbtn_editar.setText("Editar");
        jbtn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_editarActionPerformed(evt);
            }
        });

        jbtn_eliminar.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jbtn_eliminar.setText("Eliminar");
        jbtn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_eliminarActionPerformed(evt);
            }
        });

        jbtn_actualizar.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jbtn_actualizar.setText("Actualizar");
        jbtn_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_actualizarActionPerformed(evt);
            }
        });

        jLabel1.setText("Orden:");

        txt_orden.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txt_orden.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_orden.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_ordenKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jbtn_editar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbtn_eliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_mesa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbtn_actualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(jbtn_agrear, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbl_seleccion_mesa, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txt_orden, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_seleccion_mesa, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_mesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_orden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jbtn_agrear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtn_editar)
                        .addGap(2, 2, 2)
                        .addComponent(jbtn_actualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtn_eliminar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
        lbl_seleccion_mesa.setText("Has seleccionado: " + (String) tabla.getValueAt(tabla.getSelectedRow(), 1));

    }//GEN-LAST:event_tablaMouseClicked

    private void jbtn_agrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_agrearActionPerformed
        // TODO add your handling code here:

        agregar();
    }//GEN-LAST:event_jbtn_agrearActionPerformed

    private void jbtn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_editarActionPerformed
        // TODO add your handling code here:
        modf = true;
        if (tabla.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona una fila para modificar...");
        } else {
            txt_mesa.setText((String) tabla.getValueAt(tabla.getSelectedRow(), 1));
            txt_orden.setText((String) tabla.getValueAt(tabla.getSelectedRow(), 3));
            jbtn_actualizar.setEnabled(true);
        }

    }//GEN-LAST:event_jbtn_editarActionPerformed

    private void txt_mesaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_mesaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            
            if (modf == true) {
                modificar();
            } else {
                agregar();
            }
        }
    }//GEN-LAST:event_txt_mesaKeyPressed

    private void jbtn_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_actualizarActionPerformed
        // TODO add your handling code here:
        modificar();
    }//GEN-LAST:event_jbtn_actualizarActionPerformed

    private void txt_mesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_mesaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_mesaActionPerformed

    private void jbtn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_eliminarActionPerformed
        // TODO add your handling code here:
        if (tabla.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar...");
        } else {
            eliminar();
        }
    }//GEN-LAST:event_jbtn_eliminarActionPerformed

    private void txt_ordenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ordenKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            
            if (modf == true) {
                modificar();
            } else {
                agregar();
            }
        }
    }//GEN-LAST:event_txt_ordenKeyPressed

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
            java.util.logging.Logger.getLogger(mesas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mesas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mesas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mesas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mesas dialog = new mesas(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JButton jbtn_actualizar;
    private javax.swing.JButton jbtn_agrear;
    private javax.swing.JButton jbtn_editar;
    private javax.swing.JButton jbtn_eliminar;
    private javax.swing.JLabel lbl_seleccion_mesa;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txt_mesa;
    private javax.swing.JFormattedTextField txt_orden;
    // End of variables declaration//GEN-END:variables
}
