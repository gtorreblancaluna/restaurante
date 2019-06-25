/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante;

import clases.sqlclass;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import static restaurante.principal.txt_total_pagar;

/**
 *
 * @author Gerardo Torreblanca
 */
public class detalle_caja extends javax.swing.JDialog {

    sqlclass funcion = new sqlclass();
    Object[][] dtconduc;

    /**
     * Creates new form detalle_caja
     */
    public detalle_caja(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tabla();
        tabla1();
        sumas();

        txt_ingresos.setEditable(false);
        txt_ingresos.setBorder(BorderFactory.createEmptyBorder());
        txt_ingresos.setBackground(new Color(0, 0, 0, 0));

        txt_egresos.setEditable(false);
        txt_egresos.setBorder(BorderFactory.createEmptyBorder());
        txt_egresos.setBackground(new Color(0, 0, 0, 0));

        txt_ventas.setEditable(false);
        txt_ventas.setBorder(BorderFactory.createEmptyBorder());
        txt_ventas.setBackground(new Color(0, 0, 0, 0));

        txt_saldo.setEditable(false);
        txt_saldo.setBorder(BorderFactory.createEmptyBorder());
        txt_saldo.setBackground(new Color(0, 0, 0, 0));

        lbl_descripcion.setText("Apertura: " + (String) principal.tabla_consultar_caja1.getValueAt(principal.tabla_consultar_caja1.getSelectedRow(), 1) + " -- Cierre: " + (String) principal.tabla_consultar_caja1.getValueAt(principal.tabla_consultar_caja1.getSelectedRow(), 2));
    }

    public void sumas() {

        float ingresos = 0, egresos = 0, ventas = 0, saldo = 0;

        for (int i = 0; i < caja.getRowCount(); i++) {
            System.out.println("INGRESOS: " + (String) caja.getValueAt(i, 3));
            if (caja.getValueAt(i, 3).equals("(+) Ingreso")) {

                ingresos = ingresos + Float.parseFloat((String) caja.getValueAt(i, 4));
            } else {
                egresos = egresos + Float.parseFloat((String) caja.getValueAt(i, 4));
            }

        }
        for (int i = 0; i < ventas_por_caja.getRowCount(); i++) {
            ventas = ventas + Float.parseFloat((String) ventas_por_caja.getValueAt(i, 6));

        }
        txt_ingresos.setValue(ingresos);
        txt_egresos.setValue(egresos);
        txt_ventas.setValue(ventas);
        saldo = (ingresos - egresos) + ventas;
        txt_saldo.setValue(saldo);

    }

    public void tabla() {   // Tabla venta       
        caja.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Fecha apertura", "Fecha cierre", "Ingreso/Egreso", "Monto", "Descripcion"};
        String[] colName = {"id_caja", "fecha_apertura", "fecha_cierre", "ingreso", "monto", "descripcion"};

        //nombre de columnas, tabla, instruccion sql
        //dtconduc = funcion.GetTabla(colName, "venta", "SELECT v.`id_venta`,CONCAT(c.`nombre`,\" \",c.`apellidos`) AS nombre_cliente , v.`fecha`, SUM(dc.`precio_publico` * d.`cantidad`) as total, v.`id_mesa`as mesa, CONCAT(e.`nombre`,\" \", e.`apellidos`)AS Atendio, ((v.`propina`/100) * (SUM(dc.`precio_publico` * d.`cantidad`)))AS Propina  FROM venta v, detalle_venta d, detalle_categorias dc, clientes c, empleado e\n"
        //        + "WHERE d.id_venta=v.id_venta AND d.id_detalle_categorias=dc.id_detalle_categorias AND v.id_clientes=c.id_clientes AND STR_TO_DATE(v.`fecha`,'%d/%m/%Y') BETWEEN STR_TO_DATE('" + filtro_fecha_inicial + "','%d/%m/%Y')  AND STR_TO_DATE('" + filtro_fecha_final + "','%d/%m/%Y') AND v.id_empleado=e.id_empleado GROUP BY d.`id_venta` ORDER BY v.`id_venta` DESC");
        dtconduc = funcion.GetTabla(colName, "detalle_venta", "SELECT c.`id_caja` ,c.`fecha_apertura`, c.`fecha_cierre`, d.`ingreso`, d.`monto`, d.`descripcion`FROM caja c, detalle_caja d\n"
//                + "WHERE d.id_caja=c.id_caja AND c.`id_caja`= '" + principal.id_caja_global + "' ORDER BY d.`ingreso` ");
                + "WHERE d.id_caja=c.id_caja AND c.`id_caja`= '" + principal.id_caja_consultada + "' ORDER BY d.`ingreso` ");
        
        //2018.01.28 se agrega la variable id_caga_consultada
        /* int filas = dtconduc.length;
         String fecha, fecha2;

         for (int i = 0; i < filas; i++) {
         fecha = dtconduc[i][6].toString();
         //System.out.println("fecha"+" "+fecha);
         fecha2 = dia_semana(fecha);
         dtconduc[i][6] = fecha2;
         }*/

        for (int i = 0; i < dtconduc.length; i++) {
            if (dtconduc[i][3].equals("1")) {
                dtconduc[i][3] = "(+) Ingreso";
            } else {
                dtconduc[i][3] = "(-) Egreso";
            }
        }
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        caja.setModel(datos);

        DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
        centrar.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer derecha = new DefaultTableCellRenderer();
        derecha.setHorizontalAlignment(SwingConstants.RIGHT);

        int[] anchos = {10, 90, 90, 90, 90, 190};
        for (int inn = 0; inn < caja.getColumnCount(); inn++) {
            caja.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        caja.getColumnModel().getColumn(0).setMaxWidth(0);
        caja.getColumnModel().getColumn(0).setMinWidth(0);
        caja.getColumnModel().getColumn(0).setPreferredWidth(0);
        caja.getColumnModel().getColumn(1).setCellRenderer(centrar);
        caja.getColumnModel().getColumn(2).setCellRenderer(centrar);
        caja.getColumnModel().getColumn(3).setCellRenderer(centrar);
        caja.getColumnModel().getColumn(4).setCellRenderer(centrar);
        caja.getColumnModel().getColumn(5).setCellRenderer(centrar);

    }

    public void tabla1() {   // Tabla venta       
        ventas_por_caja.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Fecha ", "Cargo ", "Descuento ", "Propina ", "Atendio", "Total ", "Mesa"};
        String[] colName = {"id_venta", "fecha", "cargo", "descuento", "propina", "Atendio", "total", "mesa"};

        //nombre de columnas, tabla, instruccion sql
        //dtconduc = funcion.GetTabla(colName, "venta", "SELECT v.`id_venta`,CONCAT(c.`nombre`,\" \",c.`apellidos`) AS nombre_cliente , v.`fecha`, SUM(dc.`precio_publico` * d.`cantidad`) as total, v.`id_mesa`as mesa, CONCAT(e.`nombre`,\" \", e.`apellidos`)AS Atendio, ((v.`propina`/100) * (SUM(dc.`precio_publico` * d.`cantidad`)))AS Propina  FROM venta v, detalle_venta d, detalle_categorias dc, clientes c, empleado e\n"
        //        + "WHERE d.id_venta=v.id_venta AND d.id_detalle_categorias=dc.id_detalle_categorias AND v.id_clientes=c.id_clientes AND STR_TO_DATE(v.`fecha`,'%d/%m/%Y') BETWEEN STR_TO_DATE('" + filtro_fecha_inicial + "','%d/%m/%Y')  AND STR_TO_DATE('" + filtro_fecha_final + "','%d/%m/%Y') AND v.id_empleado=e.id_empleado GROUP BY d.`id_venta` ORDER BY v.`id_venta` DESC");
        dtconduc = funcion.GetTabla(colName, "detalle_venta", "SELECT v.`id_venta`, v.`fecha`, v.`cargo`, v.`descuento`, v.`propina`, CONCAT(e.`nombre`,\" \", e.`apellidos`)Atendio, SUM(d.`cantidad`*dc.`precio_publico`)As total, m.`descripcion`as mesa\n"
                + "FROM venta v, detalle_venta d, empleado e, detalle_categorias dc, mesa m\n"
//                + "WHERE d.id_venta=v.id_venta AND v.id_empleado=e.id_empleado AND d.id_detalle_categorias=dc.id_detalle_categorias AND v.id_mesa=m.id_mesa AND v.`id_caja`='" + principal.id_caja_global + "' group by id_venta ");
                + "WHERE d.id_venta=v.id_venta AND v.id_empleado=e.id_empleado AND d.id_detalle_categorias=dc.id_detalle_categorias "
                + "AND v.id_mesa=m.id_mesa AND v.id_estado_venta = 1 AND v.`id_caja`='" + principal.id_caja_consultada + "' group by id_venta ");
        
        /* int filas = dtconduc.length;
         String fecha, fecha2;

         for (int i = 0; i < filas; i++) {
         fecha = dtconduc[i][6].toString();
         //System.out.println("fecha"+" "+fecha);
         fecha2 = dia_semana(fecha);
         dtconduc[i][6] = fecha2;
         }*/
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        ventas_por_caja.setModel(datos);

        DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
        centrar.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer derecha = new DefaultTableCellRenderer();
        derecha.setHorizontalAlignment(SwingConstants.RIGHT);

        int[] anchos = {10, 120, 80, 80, 80, 190, 90, 90};
        for (int inn = 0; inn < ventas_por_caja.getColumnCount(); inn++) {
            ventas_por_caja.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        ventas_por_caja.getColumnModel().getColumn(0).setMaxWidth(0);
        ventas_por_caja.getColumnModel().getColumn(0).setMinWidth(0);
        ventas_por_caja.getColumnModel().getColumn(0).setPreferredWidth(0);
        ventas_por_caja.getColumnModel().getColumn(1).setCellRenderer(centrar);
        ventas_por_caja.getColumnModel().getColumn(2).setCellRenderer(centrar);
        ventas_por_caja.getColumnModel().getColumn(3).setCellRenderer(centrar);
        ventas_por_caja.getColumnModel().getColumn(4).setCellRenderer(centrar);
        ventas_por_caja.getColumnModel().getColumn(5).setCellRenderer(centrar);
        ventas_por_caja.getColumnModel().getColumn(6).setCellRenderer(centrar);
        ventas_por_caja.getColumnModel().getColumn(7).setCellRenderer(centrar);

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
        jScrollPane12 = new javax.swing.JScrollPane();
        caja = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        jScrollPane13 = new javax.swing.JScrollPane();
        ventas_por_caja = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_descripcion = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_ingresos = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_egresos = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_ventas = new javax.swing.JFormattedTextField();
        txt_saldo = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        caja.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        caja.setModel(new javax.swing.table.DefaultTableModel(
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
        caja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cajaMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(caja);

        ventas_por_caja.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        ventas_por_caja.setModel(new javax.swing.table.DefaultTableModel(
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
        ventas_por_caja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ventas_por_cajaMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(ventas_por_caja);

        jLabel1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel1.setText("Movimientos en caja...");

        jLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel2.setText("Ventas generadas, mientas caja estuvo abierta...");

        lbl_descripcion.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        lbl_descripcion.setText("descripcion");

        jLabel3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        jLabel3.setText("Movimientos en caja:");

        jLabel4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        jLabel4.setText("Ingresos:");

        txt_ingresos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        txt_ingresos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_ingresos.setText("00.00");
        txt_ingresos.setToolTipText("Total a pagar");
        txt_ingresos.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_ingresos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_ingresos.setOpaque(false);
        txt_ingresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ingresosActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        jLabel5.setText("Egresos:");

        txt_egresos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        txt_egresos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_egresos.setText("00.00");
        txt_egresos.setToolTipText("Total a pagar");
        txt_egresos.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_egresos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_egresos.setOpaque(false);
        txt_egresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_egresosActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        jLabel6.setText("Total de ventas:");

        txt_ventas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        txt_ventas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_ventas.setText("00.00");
        txt_ventas.setToolTipText("Total a pagar");
        txt_ventas.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_ventas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_ventas.setOpaque(false);
        txt_ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ventasActionPerformed(evt);
            }
        });

        txt_saldo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        txt_saldo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_saldo.setText("00.00");
        txt_saldo.setToolTipText("Total a pagar");
        txt_saldo.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_saldo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_saldo.setOpaque(false);
        txt_saldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_saldoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        jLabel7.setText("Saldo:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txt_ingresos, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txt_egresos, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(345, Short.MAX_VALUE)
                .addComponent(lbl_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(297, 297, 297))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(lbl_descripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane12)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ingresos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_egresos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addGap(9, 9, 9)
                        .addComponent(txt_saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cajaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cajaMouseClicked

    private void ventas_por_cajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ventas_por_cajaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ventas_por_cajaMouseClicked

    private void txt_ingresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ingresosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ingresosActionPerformed

    private void txt_egresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_egresosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_egresosActionPerformed

    private void txt_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ventasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ventasActionPerformed

    private void txt_saldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_saldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_saldoActionPerformed

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
            java.util.logging.Logger.getLogger(detalle_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(detalle_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(detalle_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(detalle_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                detalle_caja dialog = new detalle_caja(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable caja;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JLabel lbl_descripcion;
    public static javax.swing.JFormattedTextField txt_egresos;
    public static javax.swing.JFormattedTextField txt_ingresos;
    public static javax.swing.JFormattedTextField txt_saldo;
    public static javax.swing.JFormattedTextField txt_ventas;
    private javax.swing.JTable ventas_por_caja;
    // End of variables declaration//GEN-END:variables
}
