/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante;

import clases.ItemDB;
import clases.ItemDB_mesas;
import clases.JCMail;
import clases.JCMail_enviar_prueba;
import clases.Ticket;
import clases.conectate;
import clases.sqlclass;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import static restaurante.facturacion.validaFormatoRFC;
import ve.edu.ucab.keyboard.Keyboard;
import ve.edu.ucab.logic.LogicComponent;
import ve.edu.ucab.logic.LogicListComponent;

public class principal extends javax.swing.JFrame {
    
    // git commit all

    static sqlclass funcion = new sqlclass();
    sqlclass general = new sqlclass();
    Ticket vaucher = new Ticket();
    String fecha_sistema, hora_sistema, insumos_like, insumos_like_compras, total_venta, id_insumo_compra, descuento, cargo, propina;
    String filtro_fecha_inicial, filtro_fecha_final, dato_eliminar_caja, rutaPDF, rutaXML, rutaEntrada;
    int fila_sel_venta = -1;

    static boolean verifica_dato = false, hubo_cambios_venta = false, es_nueva_venta = false;
    conectate conexion = new conectate();

    JFileChooser dlg = new JFileChooser();
    JFileChooser dlg1 = new JFileChooser();

    JFileChooser dlgPDF = new JFileChooser();
    JFileChooser dlgXML = new JFileChooser();
    JFileChooser dlgEntrada = new JFileChooser();

    
    
    

    Image img2, img3;
    ImageIcon icon1;
    private Keyboard keyboard;
    String admin;
    int fila_seleccionada_tabla_categorias, fila_seleccionada_tabla_detalle, fila_seleccionada_tabla_insumos, sel_categorias, sel_insumos, NumMesas, sel_tabla_consultar_venta = -1;
    boolean nombre_cliente = true, nuevo = true, nuevo_cliente = false, tabla_categorias_seleccion = false, tabla_detalle_seleccion = false, tabla_empleados_seleccion = false, tabla_insumos_seleccion = false, tabla1 = false, tabla2 = false, tabla3 = false;
    boolean nuevoEmpleado = true, numeroMesas = false, se_guardo = false, por_fecha = false, generar_reporte = false, selecciono_fechas = false, sel_tabla_ingresos = false, sel_tabla_egresos = false, sel_tabla_caja = false, sel_tabla_insumos_compras = false, sel_tabla_compras = false;
    public static String id_detalle_categoria_global = "null", id_categorias_seleccion, id_detalle_seleccion, id_insumos_seleccion, id_detalle_categorias, id_Insumos, id_mesa_global, id_venta_global, id_caja_global, id_detalle_caja, id_caja, id_compras;
    // 2018.01.28 se agrega id_caja_consultada por problemas de mezcla de datos
    public static String id_caja_consultada="";
    public static boolean utiliza_conexion_TLS = false, utiliza_autenticacion = false, status;
    public static boolean validar_mesas = false, validar = false, validar_cerrar_ventana = false, caja_abierta = false, validar_caja = false, validar_ventana_facturacion = false, validar_ventana_notas = false, validar_trabajador = false, validar_administrador = false;
    public static float total_pagar = 0, total_venta_global = 0, total_cuenta = 0;
    public static String rutaPDF_email, rutaXML_email, platillo_global;
    public static String[][] array;
    public static String[][] array2;
    static Object[][] dtconduc, datosTablaCombo, obj_datos, obj_datos2;

    Object[] datos_combo;
    Object[][] edidatos = new Object[999][999];
    Object[][] edidatosXML = new Object[999][999];
    int contador = 0, contadorXML = 0;

    ItemDB general_itemDB = new ItemDB();
    ItemDB_mesas mesas_itemDB = new ItemDB_mesas();
    JCMail mail = new JCMail();

    static sqlclass general1 = new sqlclass();

    private final static int button_width = 75;        // button width
    private final static int button_height = 75;        // button height
    private final static int horizontalGap = 10;         // horizontal gap in button
    private final static int verticalGap = 10;         // verticle gap in button
    private final static int numberOfColumns = 5;          // number of colums in the button panel
    private final static int numberOfColumnsMesa = 12;          // number of colums in the button panel
    private final static int fontSize = 11;         // font size of button name
    private final static int fontType = Font.BOLD;  // font type
    private final static String fontName = "Comic Sans MS";    // font name
    private final static Color fontColor = new Color(3, 3, 3);  // font colot

    /**
     * Creates new form principal
     */
    public principal() {
        
        // se mandan a llamar todas las funciones       
        initComponents();
        funcion.conectate();
        ////////////////////////////////////////////////logito
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/culichi.png"));
        setIconImage(icon);
        /////////////////////////////////////////////////////////
        //check_administrador.setSelected(true);
        fecha_sistema();
        tablaCategorias();
        tablaInsumos();
        addMainMenue();
        addMainMenue_mesas();
        traer_datos_generales();
        formato_tabla_venta();
        // //  funcion.conectate();
        
        
        //tabla_consultar_venta();
        //tabla_consultar_venta_caja1();
        tablaInsumos_compras();
        formato_tabla_detalle_compra();
        tabla_consultar_compras();
        editar_series_no();
        tabla_clientes();
        EditarDatosClientes_no();
        //formato_tabla_pdf();
        //formato_tabla_xml();
        //mostrarPDF();
        //mostrarXML();
        editarDatosEmail_no();
        traer_datos_email();
        
        // termina las funciones.
        this.setLocationRelativeTo(null);

        funcion.setEnableContainer(this.panel_detalle, false);
        panel_c_ventas.setVisible(false);
        
        // funcion.desconecta();
        Jtbn_guardar_categoria.setEnabled(false);
        this.Jbtn_quitar_categoria.setEnabled(false);
        this.Jtbn_guardar_detalle.setEnabled(false);
        Jtbn_guardar_insumos.setEnabled(false);
        Jtbn_guardar_empleado.setEnabled(false);
        Jbtn_cambiar_contraseña.setEnabled(false);

        Jtbn_guardar_datos.setEnabled(false);
        Jbtn_actualizar_logo.setEnabled(false);
        Jbtn_actualizar_series.setVisible(false);
        this.txt_contraseña_series.setVisible(false);
        Jtbn_guardar_cliente.setEnabled(false);
        Jbtn_cancelar_email.setVisible(false);
        this.Jbtn_guardar_email.setVisible(false);
        panel_enviar_prueba.setVisible(false);
        panel_totales.setVisible(false);

        txt_total_pagar.setEditable(false);
        txt_total_pagar.setBorder(BorderFactory.createEmptyBorder());
        txt_total_pagar.setBackground(new Color(0, 0, 0, 0));

        txt_total_propina.setEditable(false);
        txt_total_propina.setBorder(BorderFactory.createEmptyBorder());
        txt_total_propina.setBackground(new Color(0, 0, 0, 0));

        txt_total.setEditable(false);
        txt_total.setBorder(BorderFactory.createEmptyBorder());
        txt_total.setBackground(new Color(0, 0, 0, 0));

        txt_cambio.setEditable(false);
        txt_cambio.setBorder(BorderFactory.createEmptyBorder());
        txt_cambio.setBackground(new Color(0, 0, 0, 0));

        txt_suma.setEditable(false);
        txt_suma.setBorder(BorderFactory.createEmptyBorder());
        txt_suma.setBackground(new Color(0, 0, 0, 0));

        txt_total_ventas.setEditable(false);
        txt_total_ventas.setBorder(BorderFactory.createEmptyBorder());
        txt_total_ventas.setBackground(new Color(0, 0, 0, 0));

        txt_total_ingresos.setEditable(false);
        txt_total_ingresos.setBorder(BorderFactory.createEmptyBorder());
        txt_total_ingresos.setBackground(new Color(0, 0, 0, 0));

        txt_total_egresos.setEditable(false);
        txt_total_egresos.setBorder(BorderFactory.createEmptyBorder());
        txt_total_egresos.setBackground(new Color(0, 0, 0, 0));

        txt_total_caja.setEditable(false);
        txt_total_caja.setBorder(BorderFactory.createEmptyBorder());
        txt_total_caja.setBackground(new Color(0, 0, 0, 0));

        txt_total_compra.setEditable(false);
        txt_total_compra.setBorder(BorderFactory.createEmptyBorder());
        txt_total_compra.setBackground(new Color(0, 0, 0, 0));

        txt_total_compra.setText("0.00");
        this.txt_monto.setText("0.00");

        jTabbedPane4.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(5, false);
        
        
        if (!this.txt_razon_social.getText().equals("")) {
            this.Jbtn_agregar_datos.setEnabled(false);

            EditarDatosGenerales_no();
            this.Jbtn_cambiar_logo.setEnabled(true);
            this.Jbtn_logo.setEnabled(false);
        } else {
            this.Jbtn_cambiar_logo.setEnabled(false);
        }
        this.lbl_nombre_logueo.setText(iniciar_sesion.nombre_usuario_global + " " + iniciar_sesion.apellidos_usuario_global);
        this.lbl_cargo_logueo.setText(iniciar_sesion.puesto_global);
        // VALIDAR SI ES ADMINISTRADOR
        if (!iniciar_sesion.administrador_global.equals("1")) {

            if (iniciar_sesion.ventas_global.equals("0")) { //deshabilitamos ventas
                jTabbedPane1.setEnabledAt(0, false);
            }
            if (iniciar_sesion.caja_global.equals("0")) { //deshabilitamos caja
                jTabbedPane1.setEnabledAt(1, false);
            }
            if (iniciar_sesion.insumos_global.equals("0")) { //deshabilitamos insumos
                jTabbedPane1.setEnabledAt(2, false);
            }
            if (iniciar_sesion.compras_global.equals("0")) { //deshabilitamos compras
                jTabbedPane1.setEnabledAt(3, false);
            }
            if (iniciar_sesion.utilerias_global.equals("0")) { //deshabilitamos utilerias
                jTabbedPane1.setEnabledAt(4, false);
            }

        }

        // TECLADO  (INICIO)
        LogicListComponent components = new LogicListComponent();
        // orden tab // Si es un jTxtField // Si es jPasswordField // Si es jTextArea // Al iniciar el foco
        // componentes de los datos generales
        components.addComponent(new LogicComponent(this.txt_razon_social, 1, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_calle, 2, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_ciudad, 3, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_estado, 4, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_rfc, 5, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_tel1, 6, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_tel2, 7, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_tel3, 8, true, false, false, false));

        components.addComponent(new LogicComponent(this.txt_leyenda1, 10, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_leyenda2, 11, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_leyenda3, 12, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_leyenda4, 13, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_leyenda5, 14, true, false, false, false));

        //componentes de los usuarios o empleados
        components.addComponent(new LogicComponent(this.txt_nombre_empleado, 15, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_apellidos_empleado, 16, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_tel_cel_empleado, 17, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_tel_casa_empleado, 18, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_puesto_empleado, 19, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_contraseña_empleado, 20, false, true, false, false));
        components.addComponent(new LogicComponent(this.txt_verificar_contraseña_empleado, 21, false, true, false, false));

        //componentes de las categorias
        components.addComponent(new LogicComponent(this.txt_agregar_categoria, 22, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_descripcion, 23, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_precio, 24, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_nombre_insumo, 25, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_stock, 26, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_unidad_medida, 27, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_ultimo_costo, 28, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_buscar_insumos, 29, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_cantidad_insumo, 30, true, false, false, false));

        components.addComponent(new LogicComponent(this.txt_monto, 31, true, false, false, false));
        components.addComponent(new LogicComponent(this.txt_nota, 32, true, false, false, false));

        components.addComponent(new LogicComponent(txt_buscar_insumos_compras, 33, true, false, false, false));
        components.addComponent(new LogicComponent(txt_cantidad_compra, 34, true, false, false, false));
        components.addComponent(new LogicComponent(txt_ultimo_costo_compras, 35, true, false, false, false));
        components.addComponent(new LogicComponent(txt_comentario_compras, 36, true, false, false, false));

        components.addComponent(new LogicComponent(txt_proveedor, 37, true, false, false, false));
        components.addComponent(new LogicComponent(txt_comentarios, 38, true, false, false, false));

        components.addComponent(new LogicComponent(txt_no_exterior, 39, true, false, false, false));
        components.addComponent(new LogicComponent(txt_no_interior, 40, true, false, false, false));
        components.addComponent(new LogicComponent(txt_colonia, 41, true, false, false, false));
        components.addComponent(new LogicComponent(txt_codigo_postal, 42, true, false, false, false));
        components.addComponent(new LogicComponent(txt_referencia, 43, true, false, false, false));

        components.addComponent(new LogicComponent(txt_serie, 44, true, false, false, false));
        components.addComponent(new LogicComponent(txt_folio_inicial, 45, true, false, false, false));
        components.addComponent(new LogicComponent(txt_folio_final, 46, true, false, false, false));
        components.addComponent(new LogicComponent(txt_folio_actual, 47, true, false, false, false));
        components.addComponent(new LogicComponent(txt_contraseña_series, 48, true, false, false, false));

        components.addComponent(new LogicComponent(txt_impuesto_descripcion, 49, true, false, false, false));
        components.addComponent(new LogicComponent(txt_impuesto_tasa, 50, true, false, false, false));

        components.addComponent(new LogicComponent(txt_nombre_cliente, 51, true, false, false, false));
        components.addComponent(new LogicComponent(txt_apellidos_cliente, 52, true, false, false, false));
        components.addComponent(new LogicComponent(txt_tel_movil_cliente, 53, true, false, false, false));
        components.addComponent(new LogicComponent(txt_tel_fijo_cliente, 54, true, false, false, false));
        components.addComponent(new LogicComponent(txt_rfc_cliente, 55, true, false, false, false));
        components.addComponent(new LogicComponent(txt_razon_social_cliente, 56, true, false, false, false));
        components.addComponent(new LogicComponent(txt_calle_cliente, 57, true, false, false, false));
        components.addComponent(new LogicComponent(txt_no_ext_cliente, 58, true, false, false, false));
        components.addComponent(new LogicComponent(txt_no_int_cliente, 59, true, false, false, false));
        components.addComponent(new LogicComponent(txt_colonia_cliente, 60, true, false, false, false));
        components.addComponent(new LogicComponent(txt_cp_cliente, 61, true, false, false, false));

        components.addComponent(new LogicComponent(txt_localidad_cliente, 62, true, false, false, false));
        components.addComponent(new LogicComponent(txt_delegacion_cliente, 63, true, false, false, false));
        components.addComponent(new LogicComponent(txt_estado_cliente, 64, true, false, false, false));
        components.addComponent(new LogicComponent(txt_pais_cliente, 65, true, false, false, false));
        components.addComponent(new LogicComponent(txt_email_cliente, 66, true, false, false, false));

        components.addComponent(new LogicComponent(txt_filtro_clientes, 67, true, false, false, false));

        components.addComponent(new LogicComponent(txt_lugar_expedicion, 68, true, false, false, false));
        components.addComponent(new LogicComponent(txt_regimen_fiscal, 69, true, false, false, false));

        components.addComponent(new LogicComponent(txt_cuenta_email, 70, true, false, false, false));
        components.addComponent(new LogicComponent(txt_contraseña_email, 71, true, false, false, false));
        components.addComponent(new LogicComponent(txt_verificar_contraseña_email, 72, true, false, false, false));
        components.addComponent(new LogicComponent(txt_servidor_email, 73, true, false, false, false));
        components.addComponent(new LogicComponent(txt_puerto_email, 74, true, false, false, false));

        components.addComponent(new LogicComponent(txt_enviar_a, 75, true, false, false, false));
        components.addComponent(new LogicComponent(txt_asunto, 76, true, false, false, false));
        components.addComponent(new LogicComponent(txt_mensaje, 77, true, false, false, false));

        components.addComponent(new LogicComponent(this.txt_descuento, 78, true, false, false, false));
        components.addComponent(new LogicComponent(txt_cargo, 79, true, false, false, false));

        keyboard = new Keyboard(components, true);
        //keyboard.
        panel_teclado.setLayout(new GridLayout(1, 1));
        panel_teclado.add(keyboard);
        ////////////////////////////////// TECLADO (FIN)        

        tablaDetalleCategorias2();
        tablaEmpleados();

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

        } catch (Exception ex) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // //  funcion.conectate();
        caja_abierta = funcion.existe("caja", "abierta", "1"); // verificamos si la caja esta abierta
        if (caja_abierta == true) {
            id_caja_global = funcion.GetData("id_caja", "select id_caja from caja where abierta=1");
            Jbtn_aperturar_caja.setEnabled(false);
            Jbtn_registrar_caja.setEnabled(true);
            tabla_consultar_venta_caja(); // llenamos la tabla con las ventas generadas con caja abierta
            tabla_ingresos();
            tabla_egresos();

            suma_ventas();
            suma_ingresos();
            suma_egresos();
            total_caja();

        } else {
            JOptionPane.showMessageDialog(rootPane, "Caja esta cerrada, debes aperturar para poder generar ventas...");
            jTabbedPane1.setEnabledAt(0, false);
            jTabbedPane1.setSelectedIndex(1);
            Jbtn_aperturar_caja.setEnabled(true);
            Jbtn_registrar_caja.setEnabled(false);
            Jbtn_cerrar_caja.setEnabled(false);
            txt_total_ventas.setText("0.00");
            txt_total_ingresos.setText("0.00");
            txt_total_egresos.setText("0.00");
            txt_total_caja.setText("0.00");
        }

        // funcion.desconecta();

    }

    public void aplicar_descuento() {

        // //  funcion.conectate();
        String propina = txt_propina.getText().toString();
        String datos[] = {this.txt_descuento.getText().toString(), this.txt_cargo.getText().toString(), propina, id_venta_global};
        funcion.UpdateRegistro(datos, "update venta set descuento=?,cargo=?,propina=? where id_venta=?");

        total_pagar();
        total();
        // funcion.desconecta();
    }

    public void mostrarPDF() {
        for (int i = 0; i < edidatos.length; i++) {
            edidatos[i][0] = null;
            edidatos[i][1] = null;
            edidatos[i][2] = null;
        }
        contador = 0;
        String sDirectorio = rutaPDF;

        if (rutaPDF != null) {
            if (!rutaPDF.equals("")) {
                File directorio = new File(sDirectorio);
                listarDirectorio(directorio, "");
                llenatablaPDF();
            }
        }
    }

    public void agregar_detalle_categorias() {
        if (this.txt_descripcion.getText().equals("") || (this.txt_precio.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "Faltan parametros", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String datos[] = {id_categorias_seleccion, this.txt_descripcion.getText().toString(), this.txt_precio.getText().toString(),"1"};
            // //  funcion.conectate();
            funcion.InsertarRegistro(datos, "insert into detalle_categorias (id_categorias,descripcion,precio_publico,activo) values(?,?,?,?)");
            // funcion.desconecta();
            tablaDetalleCategorias();
            this.txt_descripcion.setText("");
            this.txt_precio.setText("");
            this.txt_descripcion.requestFocus();

        }
    }

    public void actualizar_detalle_categorias() {

        if (this.txt_descripcion.equals("") || this.txt_precio.equals("")) {
            JOptionPane.showMessageDialog(this, "Faltan parametros", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {

            if (tabla_detalle_seleccion == false) {
                JOptionPane.showMessageDialog(this, "Selecciona un registro de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                // //  funcion.conectate();
                String datos[] = {this.txt_descripcion.getText().toString(), this.txt_precio.getText().toString(), id_detalle_seleccion};
                funcion.UpdateRegistro(datos, "update detalle_categorias set descripcion=?,precio_publico=? where id_detalle_categorias=?");
                JOptionPane.showMessageDialog(null, "Actualizacion exitosa");
                tablaDetalleCategorias();
                // funcion.desconecta();

                tabla_detalle_seleccion = false;
                Jtbn_guardar_detalle.setEnabled(false);
                Jbtn_agregar_detalle.setEnabled(true);
                this.txt_descripcion.setText("");
                this.txt_precio.setText("");
                nuevo = true;
            }
        }

    }

    public String conviertemoneda(String valor) {

        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');

        float entero = Float.parseFloat(valor);
        DecimalFormat formateador = new DecimalFormat("###,###.##", simbolo);
        String entero2 = formateador.format(entero);

        if (entero2.contains(".")) {
            entero2 = "$" + entero2;

        } else {
            entero2 = "$" + entero2 + ".00";
        }

        return entero2;

    }

    public void mostrarXML() {
        for (int i = 0; i < edidatosXML.length; i++) {
            edidatosXML[i][0] = null;
            edidatosXML[i][1] = null;
            edidatosXML[i][2] = null;
        }
        contadorXML = 0;

        String sDirectorio = rutaXML;

        if (rutaXML != null) {
            if (!rutaXML.equals("")) {
                File directorio = new File(sDirectorio);
                listarDirectorioXML(directorio, "");
                llenatablaXML();
            }
        }
    }

    public void hora_sistema() {
        Calendar fecha = Calendar.getInstance();

        String hora = Integer.toString(fecha.get(Calendar.HOUR_OF_DAY));
        String minutos = Integer.toString(fecha.get(Calendar.MINUTE));
        if (hora.length() == 1) {
            hora = "0" + hora;
        } else {
            hora = Integer.toString(fecha.get(Calendar.HOUR_OF_DAY));
        }
        if (minutos.length() == 1) {
            minutos = "0" + minutos;
        } else {
            minutos = Integer.toString(fecha.get(Calendar.MINUTE));
        }
        hora_sistema = hora + ":" + minutos;
        //hora_sistema = fecha.get(Calendar.HOUR_OF_DAY) + ":" + fecha.get(Calendar.MINUTE);
    }

    /*public void seleccionar_boton() {
     if (admin.equals("1")) {
     this.check_administrador.setSelected(true);
     } else {
     this.check_administrador.setSelected(false);
     }
     }*/
    public static boolean validaFormatoRFC(String rfc) {
        boolean res = false;
        if (rfc.matches("[A-Z,Ñ,&]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z,0-9]?[A-Z,0-9]?[0-9,A-Z]?")) {
            res = true;
        }
        return res;
    }

    public void mostrar_nota_venta() {
        JasperPrint jasperPrint;
        try {
            String archivo = "C:/reportes_restaurante/nota_venta.jasper";
            System.out.println("Cargando desde: " + archivo);
            if (archivo == null) {
                JOptionPane.showMessageDialog(rootPane, "No se encuentra el Archivo jasper");
                //System.exit(2);
            }
            JasperReport masterReport = null;
            try {
                masterReport = (JasperReport) JRLoader.loadObject(archivo);
            } catch (JRException e) {
                // System.out.println("Error cargando el reporte maestro: " + e.getMessage());
                JOptionPane.showMessageDialog(rootPane, "Error cargando el reporte maestro: " + e.getMessage());
                //System.exit(3);
            }
            Map parametro = new HashMap<>();
            //guardamos el parámetro

            if (generar_reporte == false) {
                total_venta = this.txt_total_pagar.getText();
            }
            parametro.put("id_venta", id_venta_global);
            parametro.put("total_pagar", total_venta);
            general.conectate();
            jasperPrint = JasperFillManager.fillReport(masterReport, parametro, general.getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/reportes_restaurante/reporte.pdf");
            File file2 = new File("C:/reportes_restaurante/reporte.pdf");
            try {
                Desktop.getDesktop().open(file2);
            } catch (IOException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            general.desconecta();
        } catch (Exception j) {
            System.out.println("Mensaje de Error:" + j.toString());
            JOptionPane.showMessageDialog(rootPane, "Mensaje de Error:" + j.toString());

        }
    }

    public void mostrar_reporte_insumos() {
        JasperPrint jasperPrint;
        try {
            String archivo = "C:/reportes_restaurante/inventario_insumos.jasper";
            System.out.println("Cargando desde: " + archivo);
            if (archivo == null) {
                JOptionPane.showMessageDialog(rootPane, "No se encuentra el Archivo jasper");
                //System.exit(2);
            }
            JasperReport masterReport = null;
            try {
                masterReport = (JasperReport) JRLoader.loadObject(archivo);
            } catch (JRException e) {
                // System.out.println("Error cargando el reporte maestro: " + e.getMessage());
                JOptionPane.showMessageDialog(rootPane, "Error cargando el reporte maestro: " + e.getMessage());
                //System.exit(3);
            }
            Map parametro = new HashMap<>();
            //guardamos el parámetro

            general.conectate();
            jasperPrint = JasperFillManager.fillReport(masterReport, parametro, general.getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/reportes_restaurante/reporte.pdf");
            File file2 = new File("C:/reportes_restaurante/reporte.pdf");
            try {
                Desktop.getDesktop().open(file2);
            } catch (IOException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            general.desconecta();
        } catch (Exception j) {
            System.out.println("Mensaje de Error:" + j.toString());
            JOptionPane.showMessageDialog(rootPane, "Mensaje de Error:" + j.toString());

        }
    }

    public void mostrar_reporte_compras() {
        JasperPrint jasperPrint;

        try {
            String archivo = "C:/reportes_restaurante/reporte_compras.jasper";
            System.out.println("Cargando desde: " + archivo);
            if (archivo == null) {
                JOptionPane.showMessageDialog(rootPane, "No se encuentra el Archivo jasper");
                //System.exit(2);
            }
            JasperReport masterReport = null;
            try {
                masterReport = (JasperReport) JRLoader.loadObject(archivo);
            } catch (JRException e) {
                // System.out.println("Error cargando el reporte maestro: " + e.getMessage());
                JOptionPane.showMessageDialog(rootPane, "Error cargando el reporte maestro: " + e.getMessage());
                //System.exit(3);
            }
            Map parametro = new HashMap<>();
            //guardamos el parámetro

            parametro.put("id_compras", id_compras);

            general.conectate();
            jasperPrint = JasperFillManager.fillReport(masterReport, parametro, general.getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/reportes_restaurante/reporte_compras.pdf");
            File file2 = new File("C:/reportes_restaurante/reporte_compras.pdf");
            try {
                Desktop.getDesktop().open(file2);
            } catch (IOException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            general.desconecta();
        } catch (Exception j) {
            System.out.println("Mensaje de Error:" + j.toString());
            JOptionPane.showMessageDialog(rootPane, "Mensaje de Error:" + j.toString());
        }
    }

    public void mostrar_reporte_caja() {
        JasperPrint jasperPrint;
        try {
            String archivo = "C:/reportes_restaurante/reporte_caja.jasper";
            System.out.println("Cargando desde: " + archivo);
            if (archivo == null) {
                JOptionPane.showMessageDialog(rootPane, "No se encuentra el Archivo jasper");
                //System.exit(2);
            }
            JasperReport masterReport = null;
            try {
                masterReport = (JasperReport) JRLoader.loadObject(archivo);
            } catch (JRException e) {
                // System.out.println("Error cargando el reporte maestro: " + e.getMessage());
                JOptionPane.showMessageDialog(rootPane, "Error cargando el reporte maestro: " + e.getMessage());
                //System.exit(3);
            }
            Map parametro = new HashMap<>();
            //guardamos el parámetro
            parametro.put("id_caja", id_caja);
            general.conectate();
            jasperPrint = JasperFillManager.fillReport(masterReport, parametro, general.getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/reportes_restaurante/reporte_caja.pdf");
            File file2 = new File("C:/reportes_restaurante/reporte_caja.pdf");
            try {
                Desktop.getDesktop().open(file2);
            } catch (IOException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            general.desconecta();
        } catch (Exception j) {
            System.out.println("Mensaje de Error:" + j.toString());
            JOptionPane.showMessageDialog(rootPane, "Mensaje de Error:" + j.toString());
        }
    }

    public void mostrar_nota_fechas() {
        JasperPrint jasperPrint;
        try {
            String archivo = "C:/reportes_restaurante/entre_fechas.jasper";
            System.out.println("Cargando desde: " + archivo);
            if (archivo == null) {
                JOptionPane.showMessageDialog(rootPane, "No se encuentra el Archivo jasper");
                //System.exit(2);
            }
            JasperReport masterReport = null;
            try {
                masterReport = (JasperReport) JRLoader.loadObject(archivo);
            } catch (JRException e) {
                // System.out.println("Error cargando el reporte maestro: " + e.getMessage());
                JOptionPane.showMessageDialog(rootPane, "Error cargando el reporte maestro: " + e.getMessage());
                //System.exit(3);
            }
            Map parametro = new HashMap<>();
            //guardamos el parámetro

            String suma = this.txt_suma.getText();

            parametro.put("fecha_inicial", filtro_fecha_inicial);
            parametro.put("fecha_final", filtro_fecha_final);
            parametro.put("suma", suma);

            general.conectate();
            jasperPrint = JasperFillManager.fillReport(masterReport, parametro, general.getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/reportes_restaurante/reporte_fechas.pdf");
            File file2 = new File("C:/reportes_restaurante/reporte_fechas.pdf");
            try {
                Desktop.getDesktop().open(file2);
            } catch (IOException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            general.desconecta();
        } catch (Exception j) {
            System.out.println("Mensaje de Error:" + j.toString());
            JOptionPane.showMessageDialog(rootPane, "Mensaje de Error:" + j.toString());
        }
    }

    public void conectar() {
        // conexion.setIp(IpServer);
        conexion.conectate();
    }

    public void desconectar() {
        conexion.desconectar();
    }

    public String dia_semana(String fecha) {
        //String fecha1[];  
        String[] fecha1 = fecha.split("/");
        if (fecha1.length != 3) {
            return null;
        }
        //Vector para calcular día de la semana de un año regular.  
        int[] regular = {0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5};
        //Vector para calcular día de la semana de un año bisiesto.  
        int[] bisiesto = {0, 3, 4, 0, 2, 5, 0, 3, 6, 1, 4, 6};
        //Vector para hacer la traducción de resultado en día de la semana.  
        String[] semana = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        //Día especificado en la fecha recibida por parametro.  
        int d = Integer.parseInt(fecha1[0]);
        //Módulo acumulado del mes especificado en la fecha recibida por parametro.  
        int m = Integer.parseInt(fecha1[1]) - 1;
        //Año especificado por la fecha recibida por parametros.  
        int a = Integer.parseInt(fecha1[2]);
        //Comparación para saber si el año recibido es bisiesto.  
        int dia = (int) d;
        int mes = (int) m;
        int anno = (int) a;
        if ((anno % 4 == 0) && !(anno % 100 == 0 && anno % 400 != 0)) {
            mes = bisiesto[mes];
        } else {
            mes = regular[mes];
        }
        //Se retorna el resultado del calculo del día de la semana. 
        int dd = (int) Math.ceil(Math.ceil(Math.ceil((anno - 1) % 7) + Math.ceil((Math.floor((anno - 1) / 4) - Math.floor((3 * (Math.floor((anno - 1) / 100) + 1)) / 4)) % 7) + mes + dia % 7) % 7);
        String DD = semana[dd].toString();
        String MM = meses[m].toString();
        String fechafinal = DD + " " + d + " de " + MM + " " + a;
        return fechafinal;
    }

    public void listarDirectorio(File f, String separador) {
        File[] ficheros = f.listFiles();
        String datos1, datos2;
        for (int x = 0; x < ficheros.length; x++) {
            System.out.println(separador + ficheros[x].getName());
            //System.out.println(separador + ficheros[x].getName()+"Ruta:"+ficheros[x].getAbsolutePath());
            if (ficheros[x].isFile()) {
                datos1 = ficheros[x].getName();
                datos2 = ficheros[x].getAbsolutePath();
                edidatos[contador][0] = contador;
                edidatos[contador][1] = datos1;
                edidatos[contador][2] = datos2;
                contador++;
                System.out.println("Ruta:" + ficheros[x].getAbsolutePath());
            }
            if (ficheros[x].isDirectory()) {
                String nuevo_separador;
                nuevo_separador = separador + " ";
                listarDirectorio(ficheros[x], nuevo_separador);
            }
        }
    }

    public static void listarDirectorioXML(File f, String separador){
    }
/////////////////////////////////////////////////

    public void llenatablaPDF() {
        tabla_pdf.removeAll();
        String[] columNames2 = {"Id", "NOMBRE DE ARCHIVO", "RUTA EN PC"};
        DefaultTableModel datos = new DefaultTableModel(edidatos, columNames2);
        //detalles1.setRowHeight(50);
        tabla_pdf.setModel(datos);
        int[] anchos = {30, 250, 250};
        for (int inn = 0; inn < tabla_pdf.getColumnCount(); inn++) {
            tabla_pdf.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }
    }

    public void llenatablaXML() {
        tabla_xml.removeAll();
        String[] columNames2 = {"Id", "NOMBRE DE ARCHIVO", "RUTA EN PC"};
        DefaultTableModel datos = new DefaultTableModel(edidatosXML, columNames2);
        //detalles1.setRowHeight(50);
        tabla_xml.setModel(datos);
        int[] anchos = {30, 250, 250};
        for (int inn = 0; inn < tabla_xml.getColumnCount(); inn++) {
            tabla_xml.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }
    }

    private Image getImage(byte[] bytes, boolean isThumbnail) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator readers = ImageIO.getImageReadersByFormatName("jpeg");
        ImageReader reader = (ImageReader) readers.next();
        Object source = bis; // File or InputStream
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        if (isThumbnail) {
            param.setSourceSubsampling(4, 4, 0, 0);
        }
        return reader.read(0, param);
    }

    public void mostrar_input() {
        input_orden ventana_orden = new input_orden(null, true);
        ventana_orden.setVisible(true);
        ventana_orden.setLocationRelativeTo(null);
    }

    public void mostrar_mesas() {
        mesas ventana_mesas = new mesas(null, true);
        ventana_mesas.setVisible(true);
        ventana_mesas.setLocationRelativeTo(null);
    }

    public void mostrar_permisos_empleados() {
        permisos_empleados ventana_p = new permisos_empleados(null, true);
        ventana_p.setVisible(true);
        ventana_p.setLocationRelativeTo(null);
    }

    public void mostrar_validar_administrador() {
        validar_administrador ventana_administrador = new validar_administrador(null, true);
        ventana_administrador.setVisible(true);
        ventana_administrador.setLocationRelativeTo(null);
    }

    public void mostrar_nueva_orden() {
        mandar_nueva_orden ventana_orden_nueva = new mandar_nueva_orden(null, true);
        ventana_orden_nueva.setVisible(true);
    }

    public void mostrar_validar_trabajador() {
        validar_empleado ventana_empleado = new validar_empleado(null, true);
        ventana_empleado.setVisible(true);
        ventana_empleado.setLocationRelativeTo(null);
    }

    public void mostrar_cerrar_ventana() {
        cerrar_venta ventana_cerrar_venta = new cerrar_venta(null, true);
        ventana_cerrar_venta.setVisible(true);
        ventana_cerrar_venta.setLocationRelativeTo(null);
    }

    public void mostrar_aperturar_caja() {
        aperturar_caja ventana_aperturar_caja = new aperturar_caja(null, true);
        ventana_aperturar_caja.setVisible(true);
        ventana_aperturar_caja.setLocationRelativeTo(null);
    }
    // 2018.01.28 muestra ventana para buscar elemento en la bd
    public void mostrar_buscar_elemento() {
        WinSearchElement x = new WinSearchElement(null, true);
        x.setVisible(true);
        x.setLocationRelativeTo(null);
    }

    public void mostrar_facturacion() {
        facturacion ventana_facturacion = new facturacion(null, true);
        ventana_facturacion.setVisible(true);
        ventana_facturacion.setLocationRelativeTo(null);

    }

    public void mostrar_enviar_email() {
        enviar_email ventana_enviar_email = new enviar_email(null, true);
        ventana_enviar_email.setVisible(true);
        ventana_enviar_email.setLocationRelativeTo(null);

    }

    public void mostrar_notas_platillos() {
        ver_notas_platillos ventana_notas_platillos = new ver_notas_platillos(null, true);
        ventana_notas_platillos.setVisible(true);
        ventana_notas_platillos.setLocationRelativeTo(null);

    }

    public static String EliminaCaracteres(String s_cadena, String s_caracteres) {
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

    public boolean isEmail(String correo) { //validar correo electronico
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            System.out.println("[" + mat.group() + "]");
            return true;
        } else {
            return false;
        }
    }

    public static void total_pagar() {
        int cant_filas = tabla_venta.getRowCount();
        total_cuenta = 0;

        for (int i = 0; i < cant_filas; i++) {
            total_cuenta = total_cuenta + Float.parseFloat(tabla_venta.getValueAt(i, 4).toString());
        }
        txt_total_pagar.setValue(total_cuenta);
    }

    public static void total() {
        //int cant_filas = tabla_venta.getRowCount();
        txt_total.setText("");
        float total = 0, cargoAux = 0, descuentoAux = 0;
        if (txt_cargo.getText().equals("")) {
            txt_cargo.setText("0");
        }
        if (txt_descuento.getText().equals("")) {
            txt_descuento.setText("0");
        }
        if (txt_propina.getText().equals("")) {
            txt_propina.setText("0");
        }
        cargoAux = Float.parseFloat(txt_cargo.getText());
        String total_pagar = EliminaCaracteres(txt_total_pagar.getText(), "$,");
        Float propina = (Float.parseFloat(txt_propina.getText().toString()) / 100) * (Float.parseFloat(total_pagar));
        txt_total_propina.setValue(propina);
        descuentoAux = Float.parseFloat(txt_descuento.getText());
        total = (total_cuenta - descuentoAux) + cargoAux + propina;
        System.out.println("el total es: " + total);
        txt_total.setValue(total);
    }

    public void calcular() {
        Float aux = Float.parseFloat(txt_propina.getText().toString());
        if (aux < 0 || aux > 100) {
            JOptionPane.showMessageDialog(null, "Indica un valor correcto ", "Error", JOptionPane.INFORMATION_MESSAGE);
            txt_propina.setText("0");
        } else {
            if (se_guardo == true || validar == false) {
                /*String total = EliminaCaracteres(txt_total_pagar.getText(), "$,");
                 Float porcentaje = Float.parseFloat(txt_propina.getText()) / 100;
                 System.out.println("% " + porcentaje);
                 Float propina = porcentaje * Float.parseFloat(total);
                 txt_total_propina.setValue(propina);
                 //hubo_cambios_venta = true;*/
                total_pagar();
                total();
                aplicar_descuento();
            } else {
                JOptionPane.showMessageDialog(null, "Guarda la venta, para realizar la accion ", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    public void mostrar_insumos_vendidos() {
        insumos_vendidos ventana_insumos = new insumos_vendidos(null, true);
        ventana_insumos.setVisible(true);
        ventana_insumos.setLocationRelativeTo(null);
    }

    public void mostrar_venta_cajas() {
        detalle_caja ventana_caja = new detalle_caja(null, true);
        ventana_caja.setVisible(true);
        ventana_caja.setLocationRelativeTo(null);
    }

    public void total_caja() {
        float total = 0;
        String ventas = EliminaCaracteres(txt_total_ventas.getText(), "$,");
        String ingresos = EliminaCaracteres(txt_total_ingresos.getText(), "$,");
        String egresos = EliminaCaracteres(txt_total_egresos.getText(), "$,");

        total = (Float.parseFloat(ventas) + Float.parseFloat(ingresos) - (Float.parseFloat(egresos)));

        this.txt_total_caja.setValue(total);

    }

    public void total_compras() {
        int cant_filas = tabla_detalle_compras.getRowCount();
        float total = 0;

        for (int i = 0; i < cant_filas; i++) {

            total = total + (Float.parseFloat(tabla_detalle_compras.getValueAt(i, 2).toString()) * Float.parseFloat(tabla_detalle_compras.getValueAt(i, 3).toString()));
        }
        this.txt_total_compra.setValue(total);
    }

    public void suma() {
        String z;
        int cant_filas = tabla_consultar_venta.getRowCount();
        float total = 0;
        for (int i = 0; i < cant_filas; i++) {
            z = EliminaCaracteres(tabla_consultar_venta.getValueAt(i, 3).toString(), "$,");
            System.out.println("Z = " + z);
            total = total + Float.parseFloat(z);
        }
        this.txt_suma.setValue(total);
    }

    public void suma_ventas() {
        String z;
        int cant_filas = tabla_consultar_caja.getRowCount();
        float total = 0;
        for (int i = 0; i < cant_filas; i++) {
            z = EliminaCaracteres(tabla_consultar_caja.getValueAt(i, 3).toString(), "$,");
            total = total + Float.parseFloat(z);
        }
        this.txt_total_ventas.setValue(total);
    }

    public void suma_ingresos() {
        int cant_filas = tabla_ingresos_caja.getRowCount();
        float total = 0;
        for (int i = 0; i < cant_filas; i++) {
            total = total + Float.parseFloat(tabla_ingresos_caja.getValueAt(i, 1).toString());
        }
        this.txt_total_ingresos.setValue(total);
    }

    public void suma_egresos() {
        int cant_filas = tabla_egresos_caja.getRowCount();
        float total = 0;
        for (int i = 0; i < cant_filas; i++) {
            total = total + Float.parseFloat(tabla_egresos_caja.getValueAt(i, 1).toString());
        }
        this.txt_total_egresos.setValue(total);
    }

    private void addMainMenue() {

        pnl_button.removeAll();

        repaint();

        Image img, sub;
        ImageIcon icon;
        String imagePath, imag = "/com/images/";

        ArrayList menue = new ArrayList();
        ArrayList itemName = new ArrayList();

        for (int size = 0; size < ItemDB.mainMenuCodes.length; size++) {
            menue.add(ItemDB.mainMenuCodes[size]);
            itemName.add(ItemDB.mainMenuDesc[size]);
        }

        JButton[] buttons = new JButton[menue.size()];

        for (int i = 0; i < buttons.length; i++) {

            imagePath = imag + menue.get(i).toString() + ".jpeg";

            URL url = getClass().getResource(imagePath);
//                System.out.println(imagePath +"   Get Res : " +getClass().getResource(imagePath));

            if (url != null) {
                img = Toolkit.getDefaultToolkit().getImage(url);
                sub = img.getScaledInstance(button_width - 8, button_height - 30, Image.SCALE_FAST);
                icon = new ImageIcon(sub);
            } else {
                icon = new ImageIcon();
            }

            buttons[i] = new JButton(itemName.get(i).toString());
            buttons[i].setVerticalTextPosition(AbstractButton.BOTTOM);
            buttons[i].setHorizontalTextPosition(AbstractButton.CENTER);

            buttons[i].setBorder(javax.swing.BorderFactory.createEtchedBorder());
            buttons[i].setFont(new java.awt.Font("Arial Narrow", 1, 11));
            buttons[i].setForeground(new java.awt.Color(3, 3, 3));

            buttons[i].setActionCommand(menue.get(i).toString());
            buttons[i].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    String choice = e.getActionCommand();
                    addSubmenue(choice);
                }
            });
        }

        int b = 0;
        int vGap = verticalGap;
        int hGap = horizontalGap;
        int bLength = buttons.length;
        int bRows = bLength / numberOfColumns + 1;

        L1:
        for (int j = 0; j < bRows; j++) {
            vGap = 10;
            for (int k = 0; k < numberOfColumns; k++) {

                pnl_button.add(buttons[b], new org.netbeans.lib.awtextra.AbsoluteConstraints(vGap, hGap, button_width, button_height));
                repaint();
                vGap += button_width + verticalGap;
                b++;
                if (b >= bLength) {
                    break L1;
                }
            }
            hGap += button_height + horizontalGap;
        }
        pack();

    }

    private void addMainMenue_mesas() {
        validar = false;
        panel_dinamico_mesas.removeAll();
        validar_cerrar_ventana = false;

        repaint();

        Image img, sub;
        ImageIcon icon;
        String imagePath, imag = "/com/images/";

        ArrayList menue = new ArrayList();
        ArrayList itemName = new ArrayList();
        ArrayList itemEdo = new ArrayList();

        for (int size = 0; size < ItemDB_mesas.mainMenuCodes.length; size++) {
            menue.add(ItemDB_mesas.mainMenuCodes[size]);
            itemName.add(ItemDB_mesas.mainMenuDesc[size]);
            itemEdo.add(ItemDB_mesas.mainMenuEdo[size]);
        }

        JButton[] buttons = new JButton[menue.size()];

        for (int i = 0; i < buttons.length; i++) {

            imagePath = imag + menue.get(i).toString() + ".jpeg";

            URL url = getClass().getResource(imagePath);
//                System.out.println(imagePath +"   Get Res : " +getClass().getResource(imagePath));

            if (url != null) {
                img = Toolkit.getDefaultToolkit().getImage(url);
                sub = img.getScaledInstance(button_width - 8, button_height - 30, Image.SCALE_FAST);
                icon = new ImageIcon(sub);
            } else {
                icon = new ImageIcon();
            }

            buttons[i] = new JButton(itemName.get(i).toString());
            buttons[i].setVerticalTextPosition(AbstractButton.BOTTOM);
            buttons[i].setHorizontalTextPosition(AbstractButton.CENTER);

            buttons[i].setBorder(javax.swing.BorderFactory.createEtchedBorder());
            buttons[i].setFont(new java.awt.Font("Arial Narrow", 1, 13));
            buttons[i].setForeground(new java.awt.Color(3, 3, 3));

            //buttons[i].setSize(new java.awt.Dimension(250, 150));
            buttons[i].setActionCommand(menue.get(i).toString());

            if (itemEdo.get(i).toString().equals("0")) {
                buttons[i].setBackground(new java.awt.Color(43, 94, 28));

            } else {
                buttons[i].setBackground(new java.awt.Color(118, 13, 13));

            }

            buttons[i].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    check_ticket_personalizado.setSelected(false);
                    se_guardo = false;
                    id_mesa_global = e.getActionCommand();
                    // //  funcion.conectate();
                    String estado = funcion.GetData("estado", "select estado from mesa where id_mesa = " + id_mesa_global + "");
                    String mesa = funcion.GetData("descripcion", "select descripcion from mesa where id_mesa = " + id_mesa_global + "");
                    //// funcion.desconecta();
                    id_venta_global = funcion.GetData("id_venta", "select id_venta from venta where id_mesa = " + id_mesa_global + " AND preventa = '1' ");

                    if (estado.equals("0")) {
                        mostrar_input();
                        if (validar == true) {
                            txt_total_pagar.setText("0.00");
                            txt_total.setText("0.00");
                            txt_descuento.setText("0.00");
                            txt_cargo.setText("0.00");
                            txt_total_propina.setText("0.00");
                            txt_dinero_recibido.setText("");
                            txt_propina.setText("");
                            txt_cambio.setText("");
                            panel_totales.setVisible(true);
                            primer_arreglo();
                            // Jbtn_cancelar_venta.setEnabled(false);
                            // Jbtn_quitar.setEnabled(false);
                            // Jbtn_resta.setEnabled(false);
                            lbl_producto_elegido.setText("");
                        }
                    } else {
                        validar_trabajador = false;
                        mostrar_validar_trabajador();
                        if (validar_trabajador == true) {
                        txt_propina.setText("");
                        txt_dinero_recibido.setText("");
                        txt_cambio.setText("");
                        lbl_producto_elegido.setText("");
                        //Jbtn_cancelar_venta.setEnabled(false);
                        //Jbtn_quitar.setEnabled(false);
                        //Jbtn_resta.setEnabled(false);
                        formato_tabla_venta();
                        //// //  funcion.conectate();

                        String nombreCliente = funcion.GetData("nombre_cliente", "SELECT CONCAT(c.`nombre`,\" \",c.`apellidos`)as nombre_cliente FROM venta v, clientes c\n"
                                + "WHERE v.id_clientes=c.id_clientes AND v.id_venta = " + id_venta_global + "  ");
                        String nombreEmpleado = funcion.GetData("nombre_empleado", "SELECT CONCAT(e.`nombre`,\" \",e.`apellidos`)AS nombre_empleado FROM venta v, empleado e\n"
                                + "WHERE v.id_empleado=e.id_empleado AND v.id_venta= " + id_venta_global + " ");
                        String cargoEmpleado = funcion.GetData("puesto", "SELECT e.puesto FROM venta v, empleado e\n"
                                + "WHERE v.id_empleado=e.id_empleado AND v.id_venta= " + id_venta_global + " ");
                        descuento = String.valueOf(funcion.GetData("descuento", "SELECT descuento from venta WHERE id_venta=" + id_venta_global + ""));
                        cargo = String.valueOf(funcion.GetData("cargo", "SELECT cargo from venta WHERE id_venta=" + id_venta_global + ""));
                        propina = String.valueOf(funcion.GetData("propina", "SELECT propina from venta WHERE id_venta=" + id_venta_global + ""));

                        System.out.println("decuento: " + descuento);
                        System.out.println("cargo: " + cargo);

                        if (descuento.equals("null") || descuento.equals("")) {
                            descuento = "0";
                        }
                        if (cargo.equals("null")) {
                            cargo = "0";
                        }
                        if (propina.equals("null")) {
                            propina = "0";
                        }
                        Float desc = Float.parseFloat(descuento);
                        Float cargoAux = Float.parseFloat(cargo);
                        txt_descuento.setValue(desc);
                        txt_cargo.setValue(cargoAux);
                        txt_propina.setValue(Float.parseFloat(propina));

                        lbl_nombre_atiende.setText(nombreEmpleado);
                        lbl_cargo_atiende.setText(cargoEmpleado);
                        lbl_nombre_cliente.setText(nombreCliente);

                        System.out.println("Nombre cliente: " + nombreCliente);
                        lbl_mesa.setText(mesa);
                        tabla_venta();

                        jTabbedPane4.setEnabledAt(1, true);
                        jTabbedPane4.setEnabledAt(0, false);
                        jTabbedPane4.setSelectedIndex(1);
                        addMainMenue();
                        validar = false; // validacion para ver si la venta existe

                        /*int filas = tabla_venta.getRowCount();
                         for (int i = 0; i < filas; i++) {
                         int cantidad = Integer.parseInt(tabla_venta.getValueAt(i, 1).toString());
                         int importe; //= Integer.parseInt(tabla_venta.getValueAt(i, 4).toString());
                         int precio = Integer.parseInt(tabla_venta.getValueAt(i, 3).toString());
                         importe = precio * cantidad;
                         //tabla_venta.setValueAt(cantidad,i, 1);
                         tabla_venta.setValueAt(importe, i, 4);
                         }*/
                        panel_totales.setVisible(true);
                        total_pagar();
                        total();
                        primer_arreglo();
                        }

                    }
                    // funcion.desconecta();

                    if (validar == true) {
                        formato_tabla_venta();
                        jTabbedPane4.setEnabledAt(1, true);
                        jTabbedPane4.setEnabledAt(0, false);
                        jTabbedPane4.setSelectedIndex(1);
                        addMainMenue();
                        lbl_nombre_cliente.setText(input_orden.nombre_cliente_global);
                        lbl_mesa.setText(mesa);
                        lbl_nombre_atiende.setText(input_orden.nombre_trabajador);
                        lbl_cargo_atiende.setText(input_orden.cargo_trabajador);
                        // Jbtn_cancelar_venta.setEnabled(false);
                        //Jbtn_quitar.setEnabled(false);
                        // Jbtn_resta.setEnabled(false);
                        primer_arreglo();

                    }
                    System.out.println("Choice: " + id_mesa_global);

                }
            });
        }

        int b = 0;
        int vGap = verticalGap;
        int hGap = horizontalGap;
        int bLength = buttons.length;
        int bRows = bLength / numberOfColumnsMesa + 1;

        L1:
        for (int j = 0; j < bRows; j++) {
            vGap = 10;
            for (int k = 0; k < numberOfColumnsMesa; k++) {

                panel_dinamico_mesas.add(buttons[b], new org.netbeans.lib.awtextra.AbsoluteConstraints(vGap, hGap, button_width, button_height));
                repaint();
                vGap += button_width + verticalGap;
                b++;
                if (b >= bLength) {
                    break L1;
                }
            }
            hGap += button_height + horizontalGap;
        }
        pack();
    }

    private void addSubmenue(String choice) {
        pnl_button.removeAll();
        repaint();

        Image img, sub;
        ImageIcon icon;
        String imagePath, imag = "/com/images/";

        ArrayList menue = new ArrayList();
        ArrayList itemName = new ArrayList();

        ArrayList list = ItemDB.getSubMenu(choice);
        String subCode[] = (String[]) list.get(0);
        String subDesc[] = (String[]) list.get(1);

        for (int size = 0; size < subCode.length; size++) {
            menue.add(subCode[size]);
            itemName.add(subDesc[size]);
        }

        JButton[] buttons = new JButton[menue.size()];

        for (int i = 0; i < buttons.length; i++) {

            imagePath = imag + menue.get(i).toString() + ".jpeg";

            URL url = getClass().getResource(imagePath);
//                System.out.println(imagePath +"   Get Reso : " +getClass().getResource(imagePath));

            if (url != null) {
                img = Toolkit.getDefaultToolkit().getImage(url);
                sub = img.getScaledInstance(button_width - 8, button_height - 30, Image.SCALE_FAST);
                icon = new ImageIcon(sub);
            } else {
                icon = new ImageIcon();
            }

            buttons[i] = new JButton(itemName.get(i).toString());
            buttons[i].setVerticalTextPosition(AbstractButton.BOTTOM);
            buttons[i].setHorizontalTextPosition(AbstractButton.CENTER);

            buttons[i].setBorder(javax.swing.BorderFactory.createEtchedBorder());
            buttons[i].setFont(new java.awt.Font("Arial Narrow", 1, 12));
            buttons[i].setForeground(new java.awt.Color(3, 3, 3));

            buttons[i].setActionCommand(menue.get(i).toString());
            buttons[i].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    String choice = e.getActionCommand();
                    addItems(choice);
                }
            });
        }
        int b = 0;
        int vGap = verticalGap;
        int hGap = horizontalGap;
        int bLength = buttons.length;
        int bRows = bLength / numberOfColumns + 1;

        L1:
        for (int j = 0; j < bRows; j++) {
            vGap = 10;
            for (int k = 0; k < numberOfColumns; k++) {

                pnl_button.add(buttons[b], new org.netbeans.lib.awtextra.AbsoluteConstraints(vGap, hGap, button_width, button_height));
                repaint();
                vGap += button_width + verticalGap;
                b++;
                if (b >= bLength) {
                    break L1;
                }
            }
            hGap += button_height + horizontalGap;
        }
        pack();
    }

    public void formato_tabla_venta() {
        String[] columnNames = {"Id", "Cant", "Descripcion", "Precio", "Imp.", "Nota1", "Nota2"};
        Object[][] data = {{"", "", "", "", "", "", ""}};
        DefaultTableModel TableModel = new DefaultTableModel(data, columnNames);
        tabla_venta.setModel(TableModel);

        int[] anchos = {10, 45, 400, 50, 50, 100, 100};
        for (int inn = 0; inn < tabla_venta.getColumnCount(); inn++) {
            tabla_venta.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        try {
            DefaultTableModel temp = (DefaultTableModel) tabla_venta.getModel();
            temp.removeRow(temp.getRowCount() - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            ;
        }

        tabla_venta.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_venta.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_venta.getColumnModel().getColumn(0).setPreferredWidth(0);

        tabla_venta.getColumnModel().getColumn(5).setMaxWidth(0);
        tabla_venta.getColumnModel().getColumn(5).setMinWidth(0);
        tabla_venta.getColumnModel().getColumn(5).setPreferredWidth(0);

        tabla_venta.getColumnModel().getColumn(6).setMaxWidth(0);
        tabla_venta.getColumnModel().getColumn(6).setMinWidth(0);
        tabla_venta.getColumnModel().getColumn(6).setPreferredWidth(0);

        DefaultTableCellRenderer TablaRenderer = new DefaultTableCellRenderer();
        TablaRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        tabla_venta.getColumnModel().getColumn(3).setCellRenderer(TablaRenderer);
        tabla_venta.getColumnModel().getColumn(4).setCellRenderer(TablaRenderer);

    }

    public void formato_tabla_pdf() {
        String[] columnNames = {"Id", "NOMBRE DE ARCHIVO", "RUTA EN PC"};
        Object[][] data = {{"", "", ""}};
        DefaultTableModel TableModel = new DefaultTableModel(data, columnNames);
        tabla_pdf.setModel(TableModel);

        int[] anchos = {30, 250, 250};
        for (int inn = 0; inn < tabla_pdf.getColumnCount(); inn++) {
            tabla_pdf.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        try {
            DefaultTableModel temp = (DefaultTableModel) tabla_pdf.getModel();
            temp.removeRow(temp.getRowCount() - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            ;
        }

    }

    public void formato_tabla_xml() {
        String[] columnNames = {"Id", "NOMBRE DE ARCHIVO", "RUTA EN PC"};
        Object[][] data = {{"", "", ""}};
        DefaultTableModel TableModel = new DefaultTableModel(data, columnNames);
        tabla_xml.setModel(TableModel);

        int[] anchos = {30, 250, 250};
        for (int inn = 0; inn < tabla_xml.getColumnCount(); inn++) {
            tabla_xml.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        try {
            DefaultTableModel temp = (DefaultTableModel) tabla_xml.getModel();
            temp.removeRow(temp.getRowCount() - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            ;
        }

    }

    public void tabla_venta() {   // Tabla venta       
        tabla_venta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Cant", "Descripcion", "Precio", "Imp.", "nota1", "nota2"};
        String[] colName = {"id_detalle_categorias", "cantidad", "descripcion", "precio_publico", "Imp", "nota1", "nota2"};

        //nombre de columnas, tabla, instruccion sql        
        dtconduc = funcion.GetTabla(colName, "detalle_venta", "SELECT d.`id_detalle_categorias`, d.`cantidad`, dc.`descripcion`, dc.`precio_publico`, (d.`cantidad`*dc.`precio_publico`)As Imp ,d.`nota1`, d.`nota2` FROM detalle_venta d, detalle_categorias dc\n"
                + "WHERE d.id_detalle_categorias=dc.id_detalle_categorias AND d.`id_venta`=" + id_venta_global + " ORDER BY d.`id_detalle_venta` ASC");
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_venta.setModel(datos);

        int[] anchos = {10, 45, 400, 50, 50, 50, 50};
        for (int inn = 0; inn < tabla_venta.getColumnCount(); inn++) {
            tabla_venta.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla_venta.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_venta.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_venta.getColumnModel().getColumn(0).setPreferredWidth(0);

        tabla_venta.getColumnModel().getColumn(5).setMaxWidth(0);
        tabla_venta.getColumnModel().getColumn(5).setMinWidth(0);
        tabla_venta.getColumnModel().getColumn(5).setPreferredWidth(0);

        tabla_venta.getColumnModel().getColumn(6).setMaxWidth(0);
        tabla_venta.getColumnModel().getColumn(6).setMinWidth(0);
        tabla_venta.getColumnModel().getColumn(6).setPreferredWidth(0);

        DefaultTableCellRenderer TablaRenderer = new DefaultTableCellRenderer();
        TablaRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        tabla_venta.getColumnModel().getColumn(3).setCellRenderer(TablaRenderer);
        tabla_venta.getColumnModel().getColumn(4).setCellRenderer(TablaRenderer);

    }

    public void primer_arreglo() {
        //obtener primer arreglo
//////////////////////////////////////////////////////
        int fila = tabla_venta.getRowCount();
        array = new String[fila][5];

        for (int i = 0; i < tabla_venta.getRowCount(); i++) {
            if (tabla_venta.getValueAt(i, 1) != null) {
                array[i][0] = (String) tabla_venta.getValueAt(i, 1).toString();
                System.out.println("Datos:" + array[i][0]);
            }
            if (tabla_venta.getValueAt(i, 2) != null) {
                array[i][1] = (String) tabla_venta.getValueAt(i, 2).toString();
                //System.out.println("Datos:" + dtconduc[i][1]);
            }
            if (tabla_venta.getValueAt(i, 3) != null) {
                array[i][2] = (String) tabla_venta.getValueAt(i, 3).toString();
                System.out.println("Datos:" + array[i][2]);
            }
            if (tabla_venta.getValueAt(i, 5) != null) {
                array[i][3] = (String) tabla_venta.getValueAt(i, 5).toString();
                System.out.println("Datos:" + array[i][3]);
            } else {
                array[i][3] = "";
            }
            if (tabla_venta.getValueAt(i, 6) != null) {
                array[i][4] = (String) tabla_venta.getValueAt(i, 6).toString();
                System.out.println("Datos:" + array[i][4]);
            } else {
                array[i][4] = "";
            }
        }
    }

    public void segundo_arreglo() {
        ////////////////////////////////////////////////obtener segundo arreglo

        int fila = tabla_venta.getRowCount();
        array2 = new String[fila][5];

        for (int i = 0; i < tabla_venta.getRowCount(); i++) {

            if (tabla_venta.getValueAt(i, 1) != null) {
                array2[i][0] = (String) tabla_venta.getValueAt(i, 1).toString();
                System.out.println("Datos:" + array2[i][0]);
            }
            if (tabla_venta.getValueAt(i, 2) != null) {
                array2[i][1] = (String) tabla_venta.getValueAt(i, 2).toString();
                System.out.println("Datos:" + array2[i][1]);
            }
            if (tabla_venta.getValueAt(i, 3) != null) {
                array2[i][2] = (String) tabla_venta.getValueAt(i, 3).toString();
                System.out.println("Datos:" + array2[i][2]);
            }
            if (tabla_venta.getValueAt(i, 5) != null) {

                array2[i][3] = (String) tabla_venta.getValueAt(i, 5).toString();
                System.out.println("Datos:" + array2[i][3]);
            } else {
                array2[i][3] = "";
            }
            if (tabla_venta.getValueAt(i, 6) != null) {
                array2[i][4] = (String) tabla_venta.getValueAt(i, 6).toString();
                System.out.println("Datos:" + array2[i][4]);
            } else {
                array2[i][4] = "";
            }

        }

    }

    public void tabla_consultar_venta() {   // Tabla venta       
        tabla_consultar_venta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Nombre Cliente", "Fecha", "Total", "Mesa", "Atendio", "Propina","Estado","ID caja"};
        String[] colName = {"id_venta", "nombre_cliente", "fecha", "total", "mesa", "Atendio", "Propina","estado_venta","id_caja"};

        //nombre de columnas, tabla, instruccion sql
        if (por_fecha == false) { // No eligio por fecha
            dtconduc = funcion.GetTabla(colName, "venta", "SELECT v.`id_venta`,CONCAT(c.`nombre`,\" \",c.`apellidos`) AS nombre_cliente , "
                    + "v.`fecha`, SUM(dc.`precio_publico` * d.`cantidad`) as total, m.`descripcion`as mesa, CONCAT(e.`nombre`,\" \", e.`apellidos`)AS Atendio, ((v.`propina`/100) * (SUM(dc.`precio_publico` * d.`cantidad`)))AS Propina, "
                    + "estado.descripcion AS estado_venta, v.id_caja "  
                    + "FROM venta v "
//                    + "FROM venta v, detalle_venta d, detalle_categorias dc, clientes c, empleado e,mesa m, estado_venta estado\n"
                    + "INNER JOIN detalle_venta d ON (d.id_venta = v.id_venta) "
                    + "INNER JOIN detalle_categorias dc ON (dc.id_detalle_categorias = d.id_detalle_categorias) "
                    + "INNER JOIN clientes c ON (c.id_clientes = v.id_clientes) "
                    + "INNER JOIN empleado e ON (e.id_empleado = v.id_empleado) "
                    + "INNER JOIN mesa m ON (m.id_mesa = v.id_mesa) "
                    + "INNER JOIN estado_venta estado ON (estado.id_estado_venta = v.id_estado_venta) "
//                    + "WHERE d.id_venta=v.id_venta AND m.id_mesa=v.id_mesa "
//                    + "AND d.id_detalle_categorias=dc.id_detalle_categorias AND v.id_clientes=c.id_clientes "
//                    + "AND estado.id_estado_venta = v.id_estado_venta"
//                    + "AND v.id_empleado=e.id_empleado "
//                    + "AND v.id_estado_venta = 1 "
                    + "GROUP BY d.`id_venta` ORDER BY v.`id_venta` DESC");
        } else { //si eligio por fecha
            dtconduc = funcion.GetTabla(colName, "venta", "SELECT v.`id_venta`,CONCAT(c.`nombre`,\" \",c.`apellidos`) AS nombre_cliente , v.`fecha`, SUM(dc.`precio_publico` * d.`cantidad`) as total, m.`descripcion`as mesa, CONCAT(e.`nombre`,\" \", e.`apellidos`)AS Atendio, ((v.`propina`/100) * (SUM(dc.`precio_publico` * d.`cantidad`)))AS Propina, "
                    + "estado.descripcion AS estado_venta, v.id_caja "            
                    + "FROM venta v "
                    + "INNER JOIN detalle_venta d ON (d.id_venta = v.id_venta) "
                    + "INNER JOIN detalle_categorias dc ON (dc.id_detalle_categorias = d.id_detalle_categorias) "
                    + "INNER JOIN clientes c ON (c.id_clientes = v.id_clientes) "
                    + "INNER JOIN empleado e ON (e.id_empleado = v.id_empleado) "
                    + "INNER JOIN mesa m ON (m.id_mesa = v.id_mesa) "
                    + "INNER JOIN estado_venta estado ON (estado.id_estado_venta = v.id_estado_venta) "
                    + "WHERE STR_TO_DATE(v.`fecha`,'%d/%m/%Y') "
                    + "BETWEEN STR_TO_DATE('" + filtro_fecha_inicial + "','%d/%m/%Y')  AND STR_TO_DATE('" + filtro_fecha_final + "','%d/%m/%Y') "
//                    + "FROM venta v, detalle_venta d, detalle_categorias dc, clientes c, empleado e, mesa m, estado_venta estado\n"
//                    + "WHERE d.id_venta=v.id_venta AND m.id_mesa=v.id_mesa AND d.id_detalle_categorias=dc.id_detalle_categorias "
//                    + "AND v.id_clientes=c.id_clientes AND STR_TO_DATE(v.`fecha`,'%d/%m/%Y') "
//                    + "AND estado.id_estado_venta = v.id_estado_venta "
//                    + "BETWEEN STR_TO_DATE('" + filtro_fecha_inicial + "','%d/%m/%Y')  AND STR_TO_DATE('" + filtro_fecha_final + "','%d/%m/%Y') "
//                    + "AND v.id_empleado=e.id_empleado "
//                    + "AND v.id_estado_venta = 1 "
                    + "GROUP BY d.id_venta ORDER BY v.id_venta DESC");
        }
        int filas = dtconduc.length;
        String fecha, fecha2;

        for (int i = 0; i < filas; i++) {
            fecha = dtconduc[i][2].toString();
            //System.out.println("fecha"+" "+fecha);
            fecha2 = dia_semana(fecha);
            dtconduc[i][2] = fecha2;
        }
        for (int i = 0; i < dtconduc.length; i++) {
            String valor = dtconduc[i][3].toString();
            dtconduc[i][3] = conviertemoneda(valor).toString();

        }

        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_consultar_venta.setModel(datos);

        DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
        centrar.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer derecha = new DefaultTableCellRenderer();
        derecha.setHorizontalAlignment(SwingConstants.RIGHT);

        int[] anchos = {10, 220, 200, 100, 55, 150, 55,100,55};
        for (int inn = 0; inn < tabla_consultar_venta.getColumnCount(); inn++) {
            tabla_consultar_venta.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla_consultar_venta.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_consultar_venta.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_consultar_venta.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabla_consultar_venta.getColumnModel().getColumn(3).setCellRenderer(derecha);
        tabla_consultar_venta.getColumnModel().getColumn(4).setCellRenderer(centrar);
        tabla_consultar_venta.getColumnModel().getColumn(5).setCellRenderer(centrar);
        tabla_consultar_venta.getColumnModel().getColumn(6).setCellRenderer(centrar);
    }

    public void formato_tabla_consultar_caja() {
        String[] columNames = {"Id", "Cliente", "Fecha", "Total", "Mesa"};
        Object[][] data = {{"", "", "", "", ""}};
        DefaultTableModel TableModel = new DefaultTableModel(data, columNames);
        tabla_consultar_caja.setModel(TableModel);

        int[] anchos = {10, 100, 200, 100, 50};
        for (int inn = 0; inn < tabla_consultar_caja.getColumnCount(); inn++) {
            tabla_consultar_caja.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        try {
            DefaultTableModel temp = (DefaultTableModel) tabla_consultar_caja.getModel();
            temp.removeRow(temp.getRowCount() - 1);

        } catch (ArrayIndexOutOfBoundsException e) {
            ;
        }

        tabla_consultar_caja.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_consultar_caja.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_consultar_caja.getColumnModel().getColumn(0).setPreferredWidth(0);

    }

    public void formato_tabla_consultar_caja1() {
        String[] columNames = {"Id", "Apertura", "Cierre", "Nota", "Atendio"};
        Object[][] data = {{"", "", "", "", ""}};
        DefaultTableModel TableModel = new DefaultTableModel(data, columNames);
        tabla_consultar_caja1.setModel(TableModel);

        int[] anchos = {10, 200, 200, 100, 150};
        for (int inn = 0; inn < tabla_consultar_caja1.getColumnCount(); inn++) {
            tabla_consultar_caja1.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        try {
            DefaultTableModel temp = (DefaultTableModel) tabla_consultar_caja1.getModel();
            temp.removeRow(temp.getRowCount() - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            ;
        }

        tabla_consultar_caja1.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_consultar_caja1.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_consultar_caja1.getColumnModel().getColumn(0).setPreferredWidth(0);

    }

    public void tabla_consultar_venta_caja1() {   //    
        String f_inicial = new SimpleDateFormat("dd/MM/yyyy").format(txt_fecha_inicial_caja.getDate());
        String f_final = new SimpleDateFormat("dd/MM/yyyy").format(this.txt_fecha_final_caja.getDate());

        tabla_consultar_caja1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Apertura", "Cierre", "Nota", "Atendio"};
        String[] colName = {"id_caja", "fecha_apertura", "fecha_cierre", "nota", "nombre"};

        //nombre de columnas, tabla, instruccion sql
        dtconduc = funcion.GetTabla(colName, "caja", "SELECT c.`id_caja`, c.`fecha_apertura`, c.`fecha_cierre`, c.`nota`, CONCAT(e.`nombre`,\" \",e.`apellidos`)as nombre  FROM caja c, empleado e\n"
                + "WHERE c.id_empleado=e.id_empleado AND STR_TO_DATE(c.`fecha_apertura`,'%d/%m/%Y') BETWEEN STR_TO_DATE('" + f_inicial + "','%d/%m/%Y')  "
                + "AND STR_TO_DATE('" + f_final + "','%d/%m/%Y') "
                + "ORDER BY c.`id_caja` DESC");

        int filas = dtconduc.length;
        String fecha, fecha2, fecha3, fecha4 = "";

        for (int i = 0; i < filas; i++) {
            fecha = dtconduc[i][1].toString();
            fecha3 = dtconduc[i][2].toString();
            //System.out.println("fecha"+" "+fecha);
            fecha2 = dia_semana(fecha);
            if (!fecha3.equals("")) {
                fecha4 = dia_semana(fecha3);
            }
            dtconduc[i][1] = fecha2;
            dtconduc[i][2] = fecha4;
        }

        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_consultar_caja1.setModel(datos);

        int[] anchos = {10, 200, 200, 100, 150};
        for (int inn = 0; inn < tabla_consultar_caja1.getColumnCount(); inn++) {
            tabla_consultar_caja1.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla_consultar_caja1.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_consultar_caja1.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_consultar_caja1.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

    public void tabla_consultar_venta_caja() {   // Tabla venta       
        tabla_consultar_caja.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Cliente", "Fecha", "Total", "Mesa"};
        String[] colName = {"id_venta", "Cliente", "fecha", "total", "mesa"};
        int j = 0;

        //nombre de columnas, tabla, instruccion sql
        dtconduc = funcion.GetTabla(colName, "venta", "SELECT v.`id_venta`, CONCAT(c.`nombre`,\" \",c.`apellidos`) AS Cliente, v.`fecha`, SUM(dc.`precio_publico` * d.`cantidad`) as total, m.`descripcion`as mesa  FROM venta v, detalle_venta d, detalle_categorias dc, clientes c, mesa m \n"
                + "WHERE d.id_venta=v.id_venta AND v.id_mesa=m.id_mesa AND d.id_detalle_categorias=dc.id_detalle_categorias AND v.`id_caja`= '" + id_caja_global + "' "
                        + "AND v.id_clientes=c.id_clientes "
                        + "AND v.preventa='0' "
                        + "AND v.id_estado_venta = 1 "
                        + "GROUP BY d.`id_venta` ORDER BY v.`id_venta` DESC");

        int filas = dtconduc.length;
        String fecha, fecha2;

        for (int i = 0; i < filas; i++) {
            fecha = dtconduc[i][2].toString();
            //System.out.println("fecha"+" "+fecha);
            fecha2 = dia_semana(fecha);
            dtconduc[i][2] = fecha2;
        }
        for (j = 0; j < dtconduc.length; j++) {
            String valor = dtconduc[j][3].toString();
            dtconduc[j][3] = conviertemoneda(valor).toString();

        }
        lbl_total_ventas.setText(String.valueOf(j) + " VENTAS..");

        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_consultar_caja.setModel(datos);

        DefaultTableCellRenderer derecha = new DefaultTableCellRenderer();
        derecha.setHorizontalAlignment(SwingConstants.RIGHT);

        DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
        centrar.setHorizontalAlignment(SwingConstants.CENTER);

        int[] anchos = {10, 100, 200, 100, 65};
        for (int inn = 0; inn < tabla_consultar_caja.getColumnCount(); inn++) {
            tabla_consultar_caja.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla_consultar_caja.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_consultar_caja.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_consultar_caja.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabla_consultar_caja.getColumnModel().getColumn(3).setCellRenderer(derecha);
        tabla_consultar_caja.getColumnModel().getColumn(4).setCellRenderer(centrar);
    }

    public void tabla_consultar_compras() {   // Tabla venta       
        tabla_consultar_compras.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Fecha", "Proveedor", "Nota", "Total"};
        String[] colName = {"id_compras", "fecha", "proveedor", "nota1", "total"};

        //nombre de columnas, tabla, instruccion sql
        // //  funcion.conectate();
        dtconduc = funcion.GetTabla(colName, "venta", "SELECT c.`id_compras`, c.`fecha`, c.`proveedor`, c.`nota1`, SUM(d.`cantidad` * d.`ultimo_costo`)AS TOTAL FROM compras c, detalle_compras d\n"
                + "WHERE d.id_compras=c.id_compras GROUP BY c.id_compras ORDER BY c.`id_compras` DESC");
        // funcion.desconecta();
        int filas = dtconduc.length;
        String fecha, fecha2;

        for (int i = 0; i < filas; i++) {
            fecha = dtconduc[i][1].toString();
            //System.out.println("fecha"+" "+fecha);
            fecha2 = dia_semana(fecha);
            dtconduc[i][1] = fecha2;
        }

        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_consultar_compras.setModel(datos);

        int[] anchos = {10, 230, 250, 350, 200};
        for (int inn = 0; inn < tabla_consultar_compras.getColumnCount(); inn++) {
            tabla_consultar_compras.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla_consultar_compras.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_consultar_compras.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_consultar_compras.getColumnModel().getColumn(0).setPreferredWidth(0);

    }

    public void formato_tabla_ingresos() {
        String[] columNames = {"Id", "Monto", "Nota"};
        Object[][] data = {{"", "", ""}};
        DefaultTableModel TableModel = new DefaultTableModel(data, columNames);
        tabla_ingresos_caja.setModel(TableModel);

        int[] anchos = {10, 45, 150};
        for (int inn = 0; inn < tabla_ingresos_caja.getColumnCount(); inn++) {
            tabla_ingresos_caja.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        try {
            DefaultTableModel temp = (DefaultTableModel) tabla_ingresos_caja.getModel();
            temp.removeRow(temp.getRowCount() - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            ;
        }

        tabla_ingresos_caja.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_ingresos_caja.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_ingresos_caja.getColumnModel().getColumn(0).setPreferredWidth(0);

    }

    public void formato_tabla_detalle_compra() {
        String[] columNames = {"Id", "Nombre", "Cant", "Costo", "Comentario"};
        Object[][] data = {{"", "", "", "", ""}};
        DefaultTableModel TableModel = new DefaultTableModel(data, columNames);
        tabla_detalle_compras.setModel(TableModel);

        int[] anchos = {10, 250, 100, 100, 160};
        for (int inn = 0; inn < tabla_detalle_compras.getColumnCount(); inn++) {
            tabla_detalle_compras.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        try {
            DefaultTableModel temp = (DefaultTableModel) tabla_detalle_compras.getModel();
            temp.removeRow(temp.getRowCount() - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            ;
        }

        tabla_detalle_compras.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_detalle_compras.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_detalle_compras.getColumnModel().getColumn(0).setPreferredWidth(0);

    }

    public void tabla_ingresos() {   // Tabla ingresos       
        tabla_ingresos_caja.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Monto", "Nota"};
        String[] colName = {"id_detalle_caja", "monto", "descripcion"};

        //nombre de columnas, tabla, instruccion sql        
        dtconduc = funcion.GetTabla(colName, "detalle_renta", "SELECT d.`id_detalle_caja`, d.`monto`, d.`descripcion` FROM detalle_caja d WHERE d.`id_caja`= '" + id_caja_global + "' AND d.`ingreso` = 1 ");
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_ingresos_caja.setModel(datos);

        int[] anchos = {10, 65, 150};
        for (int inn = 0; inn < tabla_ingresos_caja.getColumnCount(); inn++) {
            tabla_ingresos_caja.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla_ingresos_caja.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_ingresos_caja.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_ingresos_caja.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

    public void formato_tabla_egresos() {
        String[] columNames = {"Id", "Monto", "Nota"};
        Object[][] data = {{"", "", ""}};
        DefaultTableModel TableModel = new DefaultTableModel(data, columNames);
        tabla_egresos_caja.setModel(TableModel);

        int[] anchos = {10, 65, 150};
        for (int inn = 0; inn < tabla_egresos_caja.getColumnCount(); inn++) {
            tabla_egresos_caja.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        try {
            DefaultTableModel temp = (DefaultTableModel) tabla_egresos_caja.getModel();
            temp.removeRow(temp.getRowCount() - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            ;
        }

        tabla_egresos_caja.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_egresos_caja.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_egresos_caja.getColumnModel().getColumn(0).setPreferredWidth(0);

    }

    public void tabla_egresos() {   // Tabla ingresos       
        tabla_egresos_caja.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Monto", "Nota"};
        String[] colName = {"id_detalle_caja", "monto", "descripcion"};

        //nombre de columnas, tabla, instruccion sql        
        dtconduc = funcion.GetTabla(colName, "detalle_renta", "SELECT d.`id_detalle_caja`, d.`monto`, d.`descripcion` FROM detalle_caja d WHERE d.`id_caja`= '" + id_caja_global + "' AND d.`ingreso` = 0 ");
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_egresos_caja.setModel(datos);

        int[] anchos = {10, 65, 150};
        for (int inn = 0; inn < tabla_egresos_caja.getColumnCount(); inn++) {
            tabla_egresos_caja.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla_egresos_caja.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_egresos_caja.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_egresos_caja.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

    // metodo para agregar productos a la venta
    public static boolean addItems(String choice) {
        System.out.println("Entra a ADD ITEMS");
        verifica_dato = false;

        //String eleccion=null;
        // //  funcion.conectate();
        //eleccion = funcion.GetData("id_detalle_categorias", "SELECT  g.`id_detalle_categorias` FROM detalle_categorias g where id_detalle_categorias='"+choice+"'");
        String[] colName = {"descripcion", "precio_publico", "id_detalle_categorias"};

        obj_datos2 = funcion.GetTabla(colName, "detalle_categoria", "SELECT d.`descripcion`, d.`precio_publico`, d.`id_detalle_categorias` FROM detalle_categorias d where d.`id_detalle_categorias` = '" + choice + "' AND d.`activo`='1' ");
        
        // funcion.desconecta();
        
        if(obj_datos2.length == 0 || obj_datos2.equals(null)){
            // no se encontro en la tabla retornamos falso
            return false;
        }
        //JOptionPane.showMessageDialog(this, "Haz Seleccionado "+eleccion);
        //Agregamos a la tabla
        String descripcion, precio_publico, id_detalle_categorias;
        descripcion = obj_datos2[0][0].toString();
        precio_publico = obj_datos2[0][1].toString();
        id_detalle_categorias = obj_datos2[0][2].toString();

        int cant_filas = tabla_venta.getRowCount();

        String dato;

        for (int i = 0; i < cant_filas; i++) {
            dato = tabla_venta.getValueAt(i, 2).toString();
            System.out.println("dato seleccionado" + " " + " - " + dato + " - ");
            if (dato.equals(descripcion)) {
                verifica_dato = true;
                System.out.println("ENTRA descripcion" + " " + " - " + descripcion + " - ");
                break;

            }
        }

        if (verifica_dato == false) {
            // //  funcion.conectate();
            boolean existe = funcion.existe("consumibles_platillos", "id_detalle_cat", id_detalle_categorias);
            if (existe == true) {
                DefaultTableModel temp = (DefaultTableModel) tabla_venta.getModel();
                Object nuevo[] = {id_detalle_categorias, "1", descripcion, precio_publico, precio_publico};
                temp.addRow(nuevo);
                total_pagar();
                total();
                hubo_cambios_venta = true;
            } else {
                JOptionPane.showMessageDialog(null, "Tiene que agregar al menos un consumible (insumo) a este elemento para continuar  ", "Error...", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ya se encuentra el elemento en la lista  ", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        // funcion.desconecta();
        
        return true;
    }

    public void llenar_combo() {
        //
        // //  funcion.conectate();
        datos_combo = funcion.GetColumna("categorias", "nombre_categoria", "Select nombre_categoria from categorias");
        this.combo_categorias.removeAllItems();
        for (int i = 0; i <= datos_combo.length - 1; i++) {
            combo_categorias.addItem(datos_combo[i].toString());

        }
        // funcion.desconecta();

    }

    public void traer_datos_generales() {
        byte[] img = null;
        conectar();
        try {

            Connection con = conexion.getConnection();
            Statement s = con.createStatement();
            ResultSet res = s.executeQuery("SELECT * FROM datos_generales");

            res.next();

            this.txt_razon_social.setText(res.getString("razon_social"));
            this.txt_calle.setText(res.getString("calle"));
            this.txt_no_exterior.setText(res.getString("no_ext"));
            this.txt_no_interior.setText(res.getString("no_int"));
            this.txt_colonia.setText(res.getString("colonia"));
            this.txt_pais.setText(res.getString("pais"));
            this.txt_codigo_postal.setText(res.getString("cod_postal"));
            this.txt_referencia.setText(res.getString("referencia"));

            this.txt_ciudad.setText(res.getString("ciudad"));
            this.txt_estado.setText(res.getString("estado"));
            this.txt_rfc.setText(res.getString("RFC"));
            this.txt_tel1.setText(res.getString("tel_1"));
            this.txt_tel2.setText(res.getString("tel_2"));
            this.txt_tel3.setText(res.getString("tel_3"));
            this.txt_leyenda1.setText(res.getString("leyenda1"));
            this.txt_leyenda2.setText(res.getString("leyenda2"));
            this.txt_leyenda3.setText(res.getString("leyenda3"));
            this.txt_leyenda4.setText(res.getString("leyenda4"));
            this.txt_leyenda5.setText(res.getString("leyenda5"));

            this.txt_serie.setText(res.getString("serie"));
            this.txt_folio_inicial.setText(res.getString("folio_inicial"));
            this.txt_folio_final.setText(res.getString("folio_final"));
            this.txt_folio_actual.setText(res.getString("folio_actual"));

            this.txt_impuesto_descripcion.setText(res.getString("impuesto"));
            this.txt_impuesto_tasa.setText(res.getString("tasa_impuesto"));

            this.txt_lugar_expedicion.setText(res.getString("lugar_expedicion"));

            txt_regimen_fiscal.setText(res.getString("regimen_fiscal"));

            rutaPDF = (res.getString("ruta_pdf"));
            rutaXML = (res.getString("ruta_xml"));
            rutaEntrada = (res.getString("ruta_entrada"));

            System.out.println("ruta PDF: " + rutaPDF);

            /* if(rutaPDF.equals("") || rutaPDF.equals(null)){
             rutaPDF = "C:/";
             }
             if(rutaXML.equals("") || rutaXML.equals(null)){
             rutaXML = "C:/";
             }
             if(rutaEntrada.equals("") || rutaEntrada.equals(null)){
             rutaEntrada = "C:/";
             }*/
            img = res.getBytes("logo");
            res.close();
            // TODO add your handling code here:
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        try {

            Image imagen = getImage(img, false);
            Icon icon = new ImageIcon(imagen.getScaledInstance(202, 183, Image.SCALE_SMOOTH));
            this.lbl_logo.setIcon(icon);
            desconectar();

        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, "PARA PODER EDITAR UN REGISTRO,\nPRIMERO DEBE SELECCIONAR UNA FILA DE LA TABLA", "VERIFICAR", JOptionPane.WARNING_MESSAGE);
        }

        /*
        
         String[] colName = {"razon_social","direccion","ciudad","estado","RFC","tel_1","tel_2","tel_3","leyenda1","leyenda2","leyenda3","leyenda4","leyenda5","logo"};
            
         obj_datos = funcion.GetTabla(colName, "datos_generales", "Select * from datos_generales");
        
        
         this.txt_razon_social.setText((String) obj_datos[0][1]);
        
         /*this.txt_razon_social.setText((String) obj_datos[0][1]);
         this.txt_razon_social.setText((String) obj_datos[0][1]);
         this.txt_razon_social.setText((String) obj_datos[0][1]);
         this.txt_razon_social.setText((String) obj_datos[0][1]);
         this.txt_razon_social.setText((String) obj_datos[0][1]);*/
    }

    public void traer_datos_email() {
        byte[] img = null;
        conectar();
        try {
            String conexion_TLS, autenticacion, gmail, hotmail, personalizada;
            Connection con = conexion.getConnection();
            Statement s = con.createStatement();
            ResultSet res = s.executeQuery("SELECT * FROM email");

            res.next();
            this.txt_cuenta_email.setText(res.getString("cuenta_correo"));
            this.txt_contraseña_email.setText(res.getString("contraseña"));
            this.txt_verificar_contraseña_email.setText(res.getString("contraseña"));
            this.txt_servidor_email.setText(res.getString("servidor"));
            this.txt_puerto_email.setText(res.getString("puerto"));
            conexion_TLS = (res.getString("utiliza_conexion_TLS"));
            autenticacion = (res.getString("utiliza_autenticacion"));
            gmail = (res.getString("gmail"));
            hotmail = (res.getString("hotmail"));
            personalizada = (res.getString("personalizada"));

            if (conexion_TLS.equals("1")) {
                this.check_conexion_tls.setSelected(true);
            }
            if (autenticacion.equals("1")) {
                this.check_autenticacion.setSelected(true);
            }

            if (gmail.equals("1")) {
                this.check_gmail.setSelected(true);
            }
            if (hotmail.equals("1")) {
                this.check_hotmail.setSelected(true);
            }
            if (personalizada.equals("1")) {
                this.check_personalizada.setSelected(true);
            }

            res.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void Datos_generales(Image foto) {
        try {
            conectar();
            PreparedStatement pstm = conexion.getConnection().prepareStatement("INSERT INTO datos_generales (razon_social,direccion,ciudad,estado,RFC,tel_1,tel_2,tel_3,leyenda1,leyenda2,leyenda3,leyenda4,leyenda5,logo) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            File imagenBD = new File(dlg.getSelectedFile().getPath());

            FileInputStream fis = new FileInputStream(imagenBD);

            pstm.setString(1, txt_razon_social.getText());
            pstm.setString(2, this.txt_calle.getText());
            pstm.setString(3, this.txt_ciudad.getText());
            pstm.setString(4, this.txt_estado.getText());
            pstm.setString(5, this.txt_rfc.getText());
            pstm.setString(6, this.txt_tel1.getText());
            pstm.setString(7, txt_tel2.getText());
            pstm.setString(8, txt_tel3.getText());
            pstm.setString(9, txt_leyenda1.getText());
            pstm.setString(10, txt_leyenda2.getText());
            pstm.setString(11, txt_leyenda3.getText());
            pstm.setString(12, txt_leyenda4.getText());
            pstm.setString(13, txt_leyenda5.getText());
            pstm.setBinaryStream(14, fis, imagenBD.length());
            pstm.execute();

            desconectar();

        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

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

    public void limpiarEmpleados() {
        this.txt_nombre_empleado.setText("");
        this.txt_apellidos_empleado.setText("");
        this.txt_tel_cel_empleado.setText("");
        this.txt_tel_casa_empleado.setText("");
        this.txt_puesto_empleado.setText("");
        this.txt_contraseña_empleado.setText("");
        this.txt_verificar_contraseña_empleado.setText("");

    }

    public void limpiarDatosGenerales() {
        this.txt_razon_social.setText("");
        this.txt_calle.setText("");
        this.txt_tel1.setText("");
        this.txt_ciudad.setText("");
        this.txt_estado.setText("");
        this.txt_rfc.setText("");
        this.txt_tel2.setText("");
        this.txt_tel2.setText("");
        this.txt_tel3.setText("");
        this.txt_leyenda1.setText("");
        this.txt_leyenda2.setText("");
        this.txt_leyenda3.setText("");
        this.txt_leyenda4.setText("");
        this.txt_leyenda5.setText("");

    }

    public void limpiarDatosClientes() {
        this.txt_nombre_cliente.setText("");
        this.txt_apellidos_cliente.setText("");
        this.txt_tel_movil_cliente.setText("");
        this.txt_tel_fijo_cliente.setText("");
        this.txt_rfc_cliente.setText("");
        this.txt_razon_social_cliente.setText("");
        this.txt_calle_cliente.setText("");
        txt_cp_cliente.setText("");
        this.txt_no_ext_cliente.setText("");
        this.txt_no_int_cliente.setText("");
        this.txt_colonia_cliente.setText("");

        this.txt_localidad_cliente.setText("");
        this.txt_delegacion_cliente.setText("");
        this.txt_estado_cliente.setText("");
        this.txt_pais_cliente.setText("");
        this.txt_email_cliente.setText("");

    }

    public void EditarDatosClientes_no() {
        this.txt_nombre_cliente.setEditable(false);
        this.txt_apellidos_cliente.setEditable(false);
        this.txt_tel_movil_cliente.setEditable(false);
        this.txt_tel_fijo_cliente.setEditable(false);
        this.txt_rfc_cliente.setEditable(false);
        this.txt_razon_social_cliente.setEditable(false);
        this.txt_calle_cliente.setEditable(false);
        txt_cp_cliente.setEditable(false);
        this.txt_no_ext_cliente.setEditable(false);
        this.txt_no_int_cliente.setEditable(false);
        this.txt_colonia_cliente.setEditable(false);

        this.txt_localidad_cliente.setEditable(false);
        this.txt_delegacion_cliente.setEditable(false);
        this.txt_estado_cliente.setEditable(false);
        this.txt_pais_cliente.setEditable(false);
        this.txt_email_cliente.setEditable(false);

    }

    public void editarDatosEmail_no() {
        this.txt_cuenta_email.setEditable(false);
        this.txt_contraseña_email.setEditable(false);
        this.txt_verificar_contraseña_email.setEditable(false);
        this.txt_servidor_email.setEditable(false);
        this.txt_puerto_email.setEditable(false);
        this.check_gmail.setEnabled(false);
        this.check_hotmail.setEnabled(false);
        this.check_personalizada.setEnabled(false);

        this.check_conexion_tls.setEnabled(false);
        this.check_autenticacion.setEnabled(false);

    }

    public void editarDatosEmail() {
        this.txt_cuenta_email.setEditable(true);
        this.txt_contraseña_email.setEditable(true);
        this.txt_verificar_contraseña_email.setEditable(true);
        this.txt_servidor_email.setEditable(true);
        this.txt_puerto_email.setEditable(true);
        this.check_gmail.setEnabled(true);
        this.check_hotmail.setEnabled(true);
        this.check_personalizada.setEnabled(true);

        this.check_conexion_tls.setEnabled(true);
        this.check_autenticacion.setEnabled(true);
    }

    public void EditarDatosClientes() {
        this.txt_nombre_cliente.setEditable(true);
        this.txt_apellidos_cliente.setEditable(true);
        this.txt_tel_movil_cliente.setEditable(true);
        this.txt_tel_fijo_cliente.setEditable(true);
        this.txt_rfc_cliente.setEditable(true);
        this.txt_razon_social_cliente.setEditable(true);
        this.txt_calle_cliente.setEditable(true);
        txt_cp_cliente.setEditable(true);
        this.txt_no_ext_cliente.setEditable(true);
        this.txt_no_int_cliente.setEditable(true);
        this.txt_colonia_cliente.setEditable(true);

        this.txt_localidad_cliente.setEditable(true);
        this.txt_delegacion_cliente.setEditable(true);
        this.txt_estado_cliente.setEditable(true);
        this.txt_pais_cliente.setEditable(true);
        this.txt_email_cliente.setEditable(true);

    }

    public void EditarDatosGenerales_no() {
        this.txt_razon_social.setEditable(false);
        this.txt_calle.setEditable(false);

        this.txt_no_exterior.setEditable(false);
        this.txt_no_interior.setEditable(false);
        this.txt_colonia.setEditable(false);
        this.txt_pais.setEditable(false);
        txt_codigo_postal.setEditable(false);
        txt_referencia.setEditable(false);

        this.txt_tel1.setEditable(false);
        this.txt_ciudad.setEditable(false);
        this.txt_estado.setEditable(false);
        this.txt_rfc.setEditable(false);
        this.txt_tel2.setEditable(false);
        this.txt_tel2.setEditable(false);
        this.txt_tel3.setEditable(false);
        this.txt_leyenda1.setEditable(false);
        this.txt_leyenda2.setEditable(false);
        this.txt_leyenda3.setEditable(false);
        this.txt_leyenda4.setEditable(false);
        this.txt_leyenda5.setEditable(false);

        this.txt_impuesto_descripcion.setEditable(false);
        this.txt_impuesto_tasa.setEditable(false);

        txt_lugar_expedicion.setEditable(false);
        txt_regimen_fiscal.setEditable(false);

    }

    public void EditarDatosGenerales_si() {
        this.txt_razon_social.setEditable(true);
        this.txt_calle.setEditable(true);
        this.txt_no_exterior.setEditable(true);
        this.txt_no_interior.setEditable(true);
        this.txt_colonia.setEditable(true);
        this.txt_pais.setEditable(true);
        txt_codigo_postal.setEditable(true);
        txt_referencia.setEditable(true);

        this.txt_tel1.setEditable(true);
        this.txt_ciudad.setEditable(true);
        this.txt_estado.setEditable(true);
        this.txt_rfc.setEditable(true);
        this.txt_tel2.setEditable(true);
        this.txt_tel2.setEditable(true);
        this.txt_tel3.setEditable(true);
        this.txt_leyenda1.setEditable(true);
        this.txt_leyenda2.setEditable(true);
        this.txt_leyenda3.setEditable(true);
        this.txt_leyenda4.setEditable(true);
        this.txt_leyenda5.setEditable(true);

        this.txt_impuesto_descripcion.setEditable(true);
        this.txt_impuesto_tasa.setEditable(true);

        txt_lugar_expedicion.setEditable(true);
        txt_regimen_fiscal.setEditable(true);

    }

    public void editar_series() {
        this.txt_serie.setEditable(true);
        this.txt_folio_inicial.setEditable(true);
        this.txt_folio_final.setEditable(true);
        this.txt_folio_actual.setEditable(true);

    }

    public void editar_series_no() {
        this.txt_serie.setEditable(false);
        this.txt_folio_inicial.setEditable(false);
        this.txt_folio_final.setEditable(false);
        this.txt_folio_actual.setEditable(false);

    }

    public void tablaDetalleCategorias() {
        // //  funcion.conectate();
        tabla_detalle_categorias.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Codigo", "Nombre", "Precio"};
        String[] colName = {"id_detalle_categorias", "descripcion", "precio_publico"};
        //nombre de columnas, tabla, instruccion sql        
        dtconduc = funcion.GetTabla(colName, "detalle_categorias", "SELECT d.`id_detalle_categorias`, d.`descripcion`, d.`precio_publico` FROM restaurante.detalle_categorias d WHERE d.`id_categorias` = " + id_categorias_seleccion + " AND d.`activo`='1' ORDER BY d.`descripcion` ");
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_detalle_categorias.setModel(datos);

        DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
        centrar.setHorizontalAlignment(SwingConstants.CENTER);

        int[] anchos = {60, 200, 100};

        for (int inn = 0; inn < tabla_detalle_categorias.getColumnCount(); inn++) {
            tabla_detalle_categorias.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        // 2017.10.23 ya no ocultamos la primer columna para mostrar el id, seria el codigo del producto
        // se va a ocupar para los accesos rapidos
//        tabla_detalle_categorias.getColumnModel().getColumn(0).setMaxWidth(0);
//        tabla_detalle_categorias.getColumnModel().getColumn(0).setMinWidth(0);
//        tabla_detalle_categorias.getColumnModel().getColumn(0).setPreferredWidth(0);
//        tabla_detalle_categorias.getColumnModel().getColumn(2).setCellRenderer(centrar);

        // funcion.desconecta();
    }

    public void tablaDetalleCategorias_like() {
        // //  funcion.conectate();
        tabla_detalle_categorias.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Nombre", "Precio"};
        String[] colName = {"id_detalle_categorias", "descripcion", "precio_publico"};
        //nombre de columnas, tabla, instruccion sql        
        dtconduc = funcion.GetTabla(colName, "detalle_categorias", "SELECT d.`id_detalle_categorias`, d.`descripcion`, d.`precio_publico` FROM restaurante.detalle_categorias d WHERE d.`id_categorias` = " + id_categorias_seleccion + " AND d.`activo`='1' AND d.`descripcion` LIKE '%" + txt_descripcion.getText() + "%' ORDER BY d.`descripcion`"); //WHERE apellidos LIKE '%" + this.txt_filtro_clientes.getText() + "%'
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_detalle_categorias.setModel(datos);

        DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
        centrar.setHorizontalAlignment(SwingConstants.CENTER);

        int[] anchos = {30, 200, 100};

        for (int inn = 0; inn < tabla_detalle_categorias.getColumnCount(); inn++) {
            tabla_detalle_categorias.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla_detalle_categorias.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_detalle_categorias.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_detalle_categorias.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabla_detalle_categorias.getColumnModel().getColumn(2).setCellRenderer(centrar);

        // funcion.desconecta();
    }

    public void tablaDetalleCategorias2() {
        // //  funcion.conectate();
        tabla_detalle_categorias2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Nombre", "Precio"};
        String[] colName = {"id_detalle_categorias", "descripcion", "precio_publico"};
        //nombre de columnas, tabla, instruccion sql        
        dtconduc = funcion.GetTabla(colName, "detalle_categorias", "SELECT d.`id_detalle_categorias`, d.`descripcion`, d.`precio_publico` FROM restaurante.detalle_categorias d WHERE d.`id_categorias` = " + id_categorias_seleccion + "  ");
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_detalle_categorias2.setModel(datos);

        int[] anchos = {30, 200, 100};

        for (int inn = 0; inn < tabla_detalle_categorias2.getColumnCount(); inn++) {
            tabla_detalle_categorias2.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla_detalle_categorias2.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_detalle_categorias2.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_detalle_categorias2.getColumnModel().getColumn(0).setPreferredWidth(0);

        // funcion.desconecta();
    }

    public void tablaCategorias() {
        // //  funcion.conectate();
        tabla_categorias.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Nombre"};
        String[] colName = {"id_categorias", "nombre_categoria"};
        //nombre de columnas, tabla, instruccion sql        
        dtconduc = funcion.GetTabla(colName, "categorias", "SELECT d.`id_categorias`, d.`nombre_categoria` FROM restaurante.categorias d ORDER BY d.`nombre_categoria`");
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_categorias.setModel(datos);

        int[] anchos = {30, 200};

        for (int inn = 0; inn < tabla_categorias.getColumnCount(); inn++) {
            tabla_categorias.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla_categorias.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_categorias.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_categorias.getColumnModel().getColumn(0).setPreferredWidth(0);

        // funcion.desconecta();
    }

    public void tablaCategorias_like() {
        // //  funcion.conectate();
        tabla_categorias.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Nombre"};
        String[] colName = {"id_categorias", "nombre_categoria"};
        //nombre de columnas, tabla, instruccion sql        
        dtconduc = funcion.GetTabla(colName, "categorias", "SELECT d.`id_categorias`, d.`nombre_categoria` FROM restaurante.categorias d where d.`nombre_categoria` like '%" + txt_agregar_categoria.getText() + "%' order by d.`nombre_categoria`");
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_categorias.setModel(datos);

        int[] anchos = {30, 200};

        for (int inn = 0; inn < tabla_categorias.getColumnCount(); inn++) {
            tabla_categorias.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla_categorias.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_categorias.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_categorias.getColumnModel().getColumn(0).setPreferredWidth(0);

        // funcion.desconecta();
    }

    public void tablaEmpleados() {
        // //  funcion.conectate();
        tabla_empleados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Nombre", "Apellidos", "Tel Cel", "Tel Casa", "Puesto"};
        String[] colName = {"id_empleado", "nombre", "apellidos", "tel_cel", "tel_casa", "puesto"};
        //nombre de columnas, tabla, instruccion sql        
        dtconduc = funcion.GetTabla(colName, "empleado", "SELECT e.`id_empleado`, e.`nombre`, e.`apellidos`, e.`tel_cel`, e.`tel_casa`, e.`puesto` FROM empleado e ");
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_empleados.setModel(datos);

        int[] anchos = {30, 150, 230, 100, 100, 130};

        for (int inn = 0; inn < tabla_empleados.getColumnCount(); inn++) {
            tabla_empleados.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla_empleados.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_empleados.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_empleados.getColumnModel().getColumn(0).setPreferredWidth(0);

        // funcion.desconecta();
    }

    public void tabla_clientes() {
        // //  funcion.conectate();
        tabla_clientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Nombre", "Apellidos", "Tel Cel", "Tel Casa", "R F C", "Razon Social", "Calle", "no Ext", "no Int", "Colonia", "C.P.", "Localidad", "Delegacion", "Estado", "Pais", "E-mail"};
        String[] colName = {"id_clientes", "nombre", "apellidos", "tel_movil", "tel_fijo", "rfc", "razon_social", "calle", "noExt", "noInt", "colonia", "cp", "localidad", "delegacion", "estado", "pais", "email"};
        //nombre de columnas, tabla, instruccion sql        
        dtconduc = funcion.GetTabla(colName, "clientes", "SELECT * FROM clientes ORDER BY id_clientes DESC");
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_clientes.setModel(datos);

        int[] anchos = {10, 100, 190, 100, 80, 100, 200, 250, 60, 60, 90, 60, 90, 90, 90, 90, 90};

        for (int inn = 0; inn < tabla_clientes.getColumnCount(); inn++) {
            tabla_clientes.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla_clientes.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_clientes.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_clientes.getColumnModel().getColumn(0).setPreferredWidth(0);

        // funcion.desconecta();
    }

    public void tabla_clientes_like() {
        // //  funcion.conectate();
        tabla_clientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Nombre", "Apellidos", "Tel Cel", "Tel Casa", "R F C", "Razon Social", "Calle", "no Ext", "no Int", "Colonia", "C.P.", "Localidad", "Delegacion", "Estado", "Pais", "E-mail"};
        String[] colName = {"id_clientes", "nombre", "apellidos", "tel_movil", "tel_fijo", "rfc", "razon_social", "calle", "noExt", "noInt", "colonia", "cp", "localidad", "delegacion", "estado", "pais", "email"};
        //nombre de columnas, tabla, instruccion sql   
        if (nombre_cliente == false) {
            dtconduc = funcion.GetTabla(colName, "clientes", "SELECT * FROM clientes WHERE apellidos LIKE '%" + this.txt_filtro_clientes.getText() + "%' ORDER BY apellidos ");
            //dtconduc = funcion.GetTabla(colName, "insumos", "SELECT i.`id_insumos`,i.`nombre_insumo`, i.`unidad_medida`, i.`ultimo_costo`, i.`stock` FROM restaurante.insumos i WHERE i.`nombre_insumo` LIKE '%" + insumos_like + "%' ");

        } else {
            dtconduc = funcion.GetTabla(colName, "clientes", "SELECT * FROM clientes WHERE nombre LIKE '%" + this.txt_filtro_clientes.getText() + "%' ORDER BY nombre ");

        }
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_clientes.setModel(datos);

        int[] anchos = {10, 100, 190, 100, 80, 100, 200, 250, 60, 60, 90, 60, 90, 90, 90, 90, 90};

        for (int inn = 0; inn < tabla_clientes.getColumnCount(); inn++) {
            tabla_clientes.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla_clientes.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_clientes.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_clientes.getColumnModel().getColumn(0).setPreferredWidth(0);

        // funcion.desconecta();
    }

    public void tablaInsumos() {
        // //  funcion.conectate();
        tabla_insumos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Nombre", "Stock", "U.M.", "Costo"};
        String[] colName = {"id_insumos", "nombre_insumo", "stock", "unidad_medida", "ultimo_costo"};
        //nombre de columnas, tabla, instruccion sql        
        dtconduc = funcion.GetTabla(colName, "insumos", "select * from insumos where activo='1' order by nombre_insumo ASC");
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_insumos.setModel(datos);

        DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
        centrar.setHorizontalAlignment(SwingConstants.CENTER);

        int[] anchos = {30, 200, 70, 70, 70};

        for (int inn = 0; inn < tabla_insumos.getColumnCount(); inn++) {
            tabla_insumos.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla_insumos.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_insumos.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_insumos.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabla_insumos.getColumnModel().getColumn(4).setCellRenderer(centrar);

        // funcion.desconecta();
    }

    public void tablaInsumos_compras() {
        // //  funcion.conectate();
        tabla_insumos_compras.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Nombre", "Stock", "U.M.", "Costo"};
        String[] colName = {"id_insumos", "nombre_insumo", "stock", "unidad_medida", "ultimo_costo"};
        //nombre de columnas, tabla, instruccion sql        
        dtconduc = funcion.GetTabla(colName, "insumos", "select * from insumos where activo='1' order by nombre_insumo ASC");
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_insumos_compras.setModel(datos);

        int[] anchos = {10, 200, 60, 60, 60};

        for (int inn = 0; inn < tabla_insumos_compras.getColumnCount(); inn++) {
            tabla_insumos_compras.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla_insumos_compras.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_insumos_compras.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_insumos_compras.getColumnModel().getColumn(0).setPreferredWidth(0);

        // funcion.desconecta();
    }

    public void tablaConsumibles() {
        // //  funcion.conectate();
        tabla_consumibles_insumos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Insumo", "cantidad"};
        String[] colName = {"id_consumibles_platillos", "nombre_insumo", "cantidad"};
        //nombre de columnas, tabla, instruccion sql        
        dtconduc = funcion.GetTabla(colName, "consumibles_platillos", "SELECT c.`id_consumibles_platillos`, i.`nombre_insumo`, c.`cantidad` FROM restaurante.consumibles_platillos c, restaurante.insumos i\n"
                + "WHERE c.id_insumos=i.id_insumos AND  c.`id_detalle_cat` = '" + id_detalle_categorias + "' ");
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_consumibles_insumos.setModel(datos);

        int[] anchos = {30, 200, 60, 60, 60};

        for (int inn = 0; inn < tabla_consumibles_insumos.getColumnCount(); inn++) {
            tabla_consumibles_insumos.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla_consumibles_insumos.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_consumibles_insumos.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_consumibles_insumos.getColumnModel().getColumn(0).setPreferredWidth(0);

        // funcion.desconecta();
    }

    public void tablaInsumos2() {
        // //  funcion.conectate();
        tabla_insumos2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Nombre", "stock", "U.M.", "Costo"};
        String[] colName = {"id_insumos", "nombre_insumo", "stock", "unidad_medida", "ultimo_costo"};
        //nombre de columnas, tabla, instruccion sql        
        dtconduc = funcion.GetTabla(colName, "insumos", "select * from insumos where activo = '1' order by nombre_insumo ASC");
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_insumos2.setModel(datos);

        int[] anchos = {30, 200, 70, 70, 70};

        for (int inn = 0; inn < tabla_insumos2.getColumnCount(); inn++) {
            tabla_insumos2.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla_insumos2.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_insumos2.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_insumos2.getColumnModel().getColumn(0).setPreferredWidth(0);

        // funcion.desconecta();
    }

    public void tablaInsumos_like() {
        // //  funcion.conectate();
        tabla_insumos2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Nombre", "U.M.", "Costo", "stock"};
        String[] colName = {"id_insumos", "nombre_insumo", "unidad_medida", "ultimo_costo", "stock"};
        //nombre de columnas, tabla, instruccion sql        
        dtconduc = funcion.GetTabla(colName, "insumos", "SELECT i.`id_insumos`,i.`nombre_insumo`, i.`unidad_medida`, i.`ultimo_costo`, i.`stock` FROM restaurante.insumos i WHERE i.`nombre_insumo` LIKE '%" + insumos_like + "%' and activo='1' ORDER BY i.`nombre_insumo` ");
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_insumos2.setModel(datos);

        int[] anchos = {30, 200, 70, 70, 70};

        for (int inn = 0; inn < tabla_insumos2.getColumnCount(); inn++) {
            tabla_insumos2.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla_insumos2.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_insumos2.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_insumos2.getColumnModel().getColumn(0).setPreferredWidth(0);

        // funcion.desconecta();
    }

    public void tablaInsumos_like1() {
        // //  funcion.conectate();
        tabla_insumos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Nombre", "U.M.", "Costo", "stock"};
        String[] colName = {"id_insumos", "nombre_insumo", "unidad_medida", "ultimo_costo", "stock"};
        //nombre de columnas, tabla, instruccion sql        
        dtconduc = funcion.GetTabla(colName, "insumos", "SELECT i.`id_insumos`,i.`nombre_insumo`, i.`unidad_medida`, i.`ultimo_costo`, i.`stock` FROM restaurante.insumos i WHERE i.`nombre_insumo` LIKE '%" + insumos_like + "%' and activo ='1' ORDER BY i.`nombre_insumo` ");
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_insumos.setModel(datos);

        int[] anchos = {30, 200, 70, 70, 70};

        for (int inn = 0; inn < tabla_insumos.getColumnCount(); inn++) {
            tabla_insumos.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla_insumos.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_insumos.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_insumos.getColumnModel().getColumn(0).setPreferredWidth(0);

        // funcion.desconecta();
    }

    public void tablaInsumos_like_compras() {
        // //  funcion.conectate();
        tabla_insumos_compras.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Nombre", "U.M.", "Costo", "stock"};
        String[] colName = {"id_insumos", "nombre_insumo", "unidad_medida", "ultimo_costo", "stock"};
        //nombre de columnas, tabla, instruccion sql        
        dtconduc = funcion.GetTabla(colName, "insumos", "SELECT i.`id_insumos`,i.`nombre_insumo`, i.`unidad_medida`, i.`ultimo_costo`, i.`stock` FROM restaurante.insumos i WHERE i.`nombre_insumo` LIKE '%" + insumos_like_compras + "%' ");
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla_insumos_compras.setModel(datos);

        int[] anchos = {30, 200, 70, 70, 70};

        for (int inn = 0; inn < tabla_insumos_compras.getColumnCount(); inn++) {
            tabla_insumos_compras.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla_insumos_compras.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla_insumos_compras.getColumnModel().getColumn(0).setMinWidth(0);
        tabla_insumos_compras.getColumnModel().getColumn(0).setPreferredWidth(0);

        // funcion.desconecta();
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panel_ventas = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        panel_mesas = new javax.swing.JPanel();
        jsc_item1 = new javax.swing.JScrollPane();
        panel_dinamico_mesas = new javax.swing.JPanel();
        panel_escoger_orden = new javax.swing.JPanel();
        btn_back = new javax.swing.JButton();
        jsc_item = new javax.swing.JScrollPane();
        pnl_button = new javax.swing.JPanel();
        Jbtn_cerrar_venta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_venta = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        Jbtn_suma = new javax.swing.JButton();
        Jbtn_resta = new javax.swing.JButton();
        Jbtn_quitar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        lbl_nombre_cliente = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        lbl_mesa = new javax.swing.JLabel();
        Jbtn_guardar_venta1 = new javax.swing.JButton();
        Jbtn_cancelar_venta = new javax.swing.JButton();
        Jbtn_generar_nota1 = new javax.swing.JButton();
        Jbtn_ver_notas = new javax.swing.JButton();
        Jbtn_generar_ticket = new javax.swing.JButton();
        check_ticket_personalizado = new javax.swing.JCheckBox();
        Jbtn_quitar1 = new javax.swing.JButton();
        lbl_producto_elegido = new javax.swing.JLabel();
        txtFindCodeProduct = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jbtnBuscarElemento = new javax.swing.JButton();
        panel_consultar_ventas = new javax.swing.JPanel();
        txt_fecha_inicial = new com.toedter.calendar.JDateChooser();
        txt_fecha_final = new com.toedter.calendar.JDateChooser();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        Jbtn_buscar_fecha = new javax.swing.JButton();
        Jbtn_refrescar = new javax.swing.JButton();
        Jbtn_generar_nota = new javax.swing.JButton();
        Jbtn_generar_nota_por_fecha = new javax.swing.JButton();
        txt_suma = new javax.swing.JFormattedTextField();
        jLabel48 = new javax.swing.JLabel();
        jbtn_insumos_vendidos = new javax.swing.JButton();
        Jbtn_recuperar_ticket = new javax.swing.JButton();
        panel_c_ventas = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabla_consultar_venta = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        JbtnCancelarVentaHistorial = new javax.swing.JButton();
        panel_caja = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        panel_registrar_mov_caja = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tabla_consultar_caja = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        jScrollPane14 = new javax.swing.JScrollPane();
        tabla_ingresos_caja = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        jScrollPane15 = new javax.swing.JScrollPane();
        tabla_egresos_caja = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        Jbtn_aperturar_caja = new javax.swing.JButton();
        txt_monto = new javax.swing.JFormattedTextField();
        check_ingreso = new javax.swing.JCheckBox();
        check_egreso = new javax.swing.JCheckBox();
        jLabel52 = new javax.swing.JLabel();
        Jbtn_registrar_caja = new javax.swing.JButton();
        Jbtn_cerrar_caja = new javax.swing.JButton();
        txt_nota = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        txt_total_ventas = new javax.swing.JFormattedTextField();
        txt_total_ingresos = new javax.swing.JFormattedTextField();
        txt_total_egresos = new javax.swing.JFormattedTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        txt_total_caja = new javax.swing.JFormattedTextField();
        Jbtn_eliminar_monto = new javax.swing.JButton();
        jLabel57 = new javax.swing.JLabel();
        lbl_total_ventas = new javax.swing.JLabel();
        panel_consultar_caja = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tabla_consultar_caja1 = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        Jbtn_reporte_caja = new javax.swing.JButton();
        jLabel110 = new javax.swing.JLabel();
        txt_fecha_inicial_caja = new com.toedter.calendar.JDateChooser();
        jLabel111 = new javax.swing.JLabel();
        txt_fecha_final_caja = new com.toedter.calendar.JDateChooser();
        jbtn_insumos_vendidos1 = new javax.swing.JButton();
        panel_insumos = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        subPanel_agregarInsumos = new javax.swing.JPanel();
        panel_categorias = new javax.swing.JPanel();
        txt_agregar_categoria = new javax.swing.JTextField();
        Jbtn_agregar_categoria = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_categorias = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        Jbtn_quitar_categoria = new javax.swing.JButton();
        Jbtn_editar_categoria = new javax.swing.JButton();
        Jtbn_guardar_categoria = new javax.swing.JButton();
        Jbtn_nuevo_categoria = new javax.swing.JButton();
        panel_detalle_categorias = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_insumos = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        txt_nombre_insumo = new javax.swing.JTextField();
        txt_unidad_medida = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Jbtn_agregar_insumos = new javax.swing.JButton();
        Jbtn_quitar_insumo = new javax.swing.JButton();
        Jtbn_guardar_insumos = new javax.swing.JButton();
        Jbtn_editar_insumos = new javax.swing.JButton();
        Jbtn_nuevo_insumo = new javax.swing.JButton();
        txt_ultimo_costo = new javax.swing.JFormattedTextField();
        txt_stock = new javax.swing.JFormattedTextField();
        jButton2 = new javax.swing.JButton();
        Jbtn_reporte_insumos = new javax.swing.JButton();
        panel_detalle = new javax.swing.JPanel();
        txt_descripcion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Jbtn_agregar_detalle = new javax.swing.JButton();
        Jbtn_quitar_detalle = new javax.swing.JButton();
        Jtbn_guardar_detalle = new javax.swing.JButton();
        Jbtn_editar_detalle = new javax.swing.JButton();
        Jbtn_nuevo_detalle = new javax.swing.JButton();
        txt_precio = new javax.swing.JFormattedTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla_detalle_categorias = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        subPanel_editarInsumos = new javax.swing.JPanel();
        panel_1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabla_detalle_categorias2 = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        combo_categorias = new javax.swing.JComboBox();
        panel_2 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabla_insumos2 = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        txt_buscar_insumos = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Jbtn_relacionar = new javax.swing.JButton();
        txt_cantidad_insumo = new javax.swing.JFormattedTextField();
        lbl_nombre_insumo = new javax.swing.JLabel();
        panel3 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tabla_consumibles_insumos = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        Jbtn_quitar_insumo_relacion = new javax.swing.JButton();
        lbl_nombre_platillo = new javax.swing.JLabel();
        panel_compras = new javax.swing.JPanel();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        SubPanel_agregar_compra = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tabla_insumos_compras = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        txt_buscar_insumos_compras = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tabla_detalle_compras = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        txt_cantidad_compra = new javax.swing.JFormattedTextField();
        jLabel40 = new javax.swing.JLabel();
        txt_ultimo_costo_compras = new javax.swing.JFormattedTextField();
        jLabel42 = new javax.swing.JLabel();
        txt_comentario_compras = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        Jbtn_agregar_compras = new javax.swing.JButton();
        Jbtn_quitar_compras = new javax.swing.JButton();
        lbl_eleccion_insumo = new javax.swing.JLabel();
        txt_total_compra = new javax.swing.JFormattedTextField();
        jLabel44 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        txt_comentarios = new javax.swing.JTextPane();
        jLabel46 = new javax.swing.JLabel();
        Jtbn_registrar_compra = new javax.swing.JButton();
        txt_proveedor = new javax.swing.JTextField();
        subPanel_consultar_compras = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        tabla_consultar_compras = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        jLabel47 = new javax.swing.JLabel();
        Jbtn_reporte_compras = new javax.swing.JButton();
        panel_utilerias = new javax.swing.JPanel();
        jTbSystInfo = new javax.swing.JTabbedPane();
        panel_usuarios = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabla_empleados = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        txt_nombre_empleado = new javax.swing.JTextField();
        txt_apellidos_empleado = new javax.swing.JTextField();
        txt_tel_cel_empleado = new javax.swing.JTextField();
        txt_tel_casa_empleado = new javax.swing.JTextField();
        txt_puesto_empleado = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_contraseña_empleado = new javax.swing.JPasswordField();
        txt_verificar_contraseña_empleado = new javax.swing.JPasswordField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        Jbtn_agregar_empleado = new javax.swing.JButton();
        Jbtn_quitar_empleado = new javax.swing.JButton();
        Jbtn_editar_empleado = new javax.swing.JButton();
        Jtbn_guardar_empleado = new javax.swing.JButton();
        Jbtn_nuevo_empleado = new javax.swing.JButton();
        Jbtn_cambiar_contraseña = new javax.swing.JButton();
        Jbtn_asignar_contraseña = new javax.swing.JButton();
        jLabel67 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        panel_datos_generales = new javax.swing.JPanel();
        Jbtn_agregar_datos = new javax.swing.JButton();
        Jbtn_editar_datos = new javax.swing.JButton();
        Jtbn_guardar_datos = new javax.swing.JButton();
        txt_razon_social = new javax.swing.JTextField();
        txt_calle = new javax.swing.JTextField();
        txt_ciudad = new javax.swing.JTextField();
        txt_estado = new javax.swing.JTextField();
        txt_rfc = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_tel1 = new javax.swing.JTextField();
        txt_tel2 = new javax.swing.JTextField();
        txt_tel3 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        panel_leyendas = new javax.swing.JPanel();
        txt_leyenda5 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txt_leyenda1 = new javax.swing.JTextField();
        txt_leyenda2 = new javax.swing.JTextField();
        txt_leyenda3 = new javax.swing.JTextField();
        txt_leyenda4 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        Jbtn_logo = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        lbl_logo = new javax.swing.JLabel();
        Jbtn_cambiar_logo = new javax.swing.JButton();
        Jbtn_actualizar_logo = new javax.swing.JButton();
        txt_no_exterior = new javax.swing.JTextField();
        txt_no_interior = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        txt_colonia = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        txt_pais = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        txt_codigo_postal = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        txt_referencia = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        txt_serie = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        Jbtn_actualizar_series = new javax.swing.JButton();
        Jbtn_modificar_series = new javax.swing.JButton();
        txt_contraseña_series = new javax.swing.JPasswordField();
        txt_folio_inicial = new javax.swing.JFormattedTextField();
        txt_folio_actual = new javax.swing.JFormattedTextField();
        txt_folio_final = new javax.swing.JFormattedTextField();
        txt_impuesto_descripcion = new javax.swing.JTextField();
        txt_impuesto_tasa = new javax.swing.JFormattedTextField();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        txt_lugar_expedicion = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        txt_regimen_fiscal = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        lbl_ruta_xml = new javax.swing.JLabel();
        lbl_ruta_entrada = new javax.swing.JLabel();
        jbtn_mostrar_mesas = new javax.swing.JButton();
        subPanel_email = new javax.swing.JPanel();
        subPanel_opciones = new javax.swing.JPanel();
        Jbtn_editar_email = new javax.swing.JButton();
        Jbtn_guardar_email = new javax.swing.JButton();
        Jbtn_cancelar_email = new javax.swing.JButton();
        Jbtn_enviar_prueba_email = new javax.swing.JButton();
        subPanel_configuracion_email = new javax.swing.JPanel();
        check_personalizada = new javax.swing.JCheckBox();
        check_hotmail = new javax.swing.JCheckBox();
        check_gmail = new javax.swing.JCheckBox();
        txt_cuenta_email = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        txt_servidor_email = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        txt_puerto_email = new javax.swing.JTextField();
        check_conexion_tls = new javax.swing.JCheckBox();
        check_autenticacion = new javax.swing.JCheckBox();
        txt_contraseña_email = new javax.swing.JPasswordField();
        txt_verificar_contraseña_email = new javax.swing.JPasswordField();
        jLabel98 = new javax.swing.JLabel();
        panel_enviar_prueba = new javax.swing.JPanel();
        txt_enviar_a = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        txt_asunto = new javax.swing.JTextField();
        jScrollPane21 = new javax.swing.JScrollPane();
        txt_mensaje = new javax.swing.JTextPane();
        jLabel101 = new javax.swing.JLabel();
        Jbnt_enviar_prueba = new javax.swing.JButton();
        panel_clientes = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tabla_clientes = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        Jbtn_nuevo_cliente = new javax.swing.JButton();
        Jtbn_guardar_cliente = new javax.swing.JButton();
        Jbtn_editar_editar = new javax.swing.JButton();
        Jbtn_quitar_cliente = new javax.swing.JButton();
        Jbtn_agregar_cliente = new javax.swing.JButton();
        txt_nombre_cliente = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        txt_apellidos_cliente = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        txt_tel_movil_cliente = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        txt_tel_fijo_cliente = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        txt_rfc_cliente = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        txt_razon_social_cliente = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        txt_calle_cliente = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        txt_no_ext_cliente = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        txt_no_int_cliente = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        txt_colonia_cliente = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        txt_localidad_cliente = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        txt_delegacion_cliente = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        txt_estado_cliente = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        txt_pais_cliente = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        txt_email_cliente = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        txt_filtro_clientes = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        check_nombre_cliente = new javax.swing.JCheckBox();
        check_apellidos_cliente = new javax.swing.JCheckBox();
        txt_cp_cliente = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        panel_cfdi = new javax.swing.JPanel();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        sub_panel_consultar_cfdi = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tabla_pdf = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        jScrollPane20 = new javax.swing.JScrollPane();
        tabla_xml = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        Jbtn_abrir_xml = new javax.swing.JButton();
        Jbtn_abrir_pdf = new javax.swing.JButton();
        Jbtn_actualizar_pdf_xml = new javax.swing.JButton();
        Jbtn_enviar_xml = new javax.swing.JButton();
        Jbtn_enviar_pdf = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        lbl_nombre_logueo = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lbl_cargo_logueo = new javax.swing.JLabel();
        panel_teclado = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        lbl_nombre_atiende = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        lbl_cargo_atiende = new javax.swing.JLabel();
        panel_totales = new javax.swing.JPanel();
        txt_total_pagar = new javax.swing.JFormattedTextField();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        txt_total = new javax.swing.JFormattedTextField();
        jLabel107 = new javax.swing.JLabel();
        txt_aplicar = new javax.swing.JButton();
        txt_cargo = new javax.swing.JFormattedTextField();
        txt_descuento = new javax.swing.JFormattedTextField();
        jLabel41 = new javax.swing.JLabel();
        txt_propina = new javax.swing.JFormattedTextField();
        txt_total_propina = new javax.swing.JFormattedTextField();
        txt_dinero_recibido = new javax.swing.JFormattedTextField();
        txt_cambio = new javax.swing.JFormattedTextField();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        panel_ventas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_ventas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jTabbedPane4.setToolTipText("");
        jTabbedPane4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane4MouseClicked(evt);
            }
        });

        panel_mesas.setToolTipText("");
        panel_mesas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_mesasMouseClicked(evt);
            }
        });
        panel_mesas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jsc_item1.setBackground(new java.awt.Color(140, 205, 250));
        jsc_item1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsc_item1.setAutoscrolls(true);

        panel_dinamico_mesas.setBackground(new java.awt.Color(102, 153, 255));
        panel_dinamico_mesas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 0, 51))); // NOI18N
        panel_dinamico_mesas.setForeground(new java.awt.Color(140, 205, 250));
        panel_dinamico_mesas.setAutoscrolls(true);
        panel_dinamico_mesas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_dinamico_mesas.setEnabled(false);
        panel_dinamico_mesas.setOpaque(false);
        panel_dinamico_mesas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jsc_item1.setViewportView(panel_dinamico_mesas);

        panel_mesas.add(jsc_item1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1, 1140, 340));

        jTabbedPane4.addTab(" Mesas ", new javax.swing.ImageIcon(getClass().getResource("/imagenes/Food-Waiter-icon.png")), panel_mesas, ""); // NOI18N
        panel_mesas.getAccessibleContext().setAccessibleName("");

        panel_escoger_orden.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_escoger_ordenMouseClicked(evt);
            }
        });
        panel_escoger_orden.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/back-icon.png"))); // NOI18N
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        panel_escoger_orden.add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 44, 31));

        jsc_item.setBackground(new java.awt.Color(140, 205, 250));
        jsc_item.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsc_item.setAutoscrolls(true);

        pnl_button.setBackground(new java.awt.Color(102, 153, 255));
        pnl_button.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 0, 51))); // NOI18N
        pnl_button.setForeground(new java.awt.Color(140, 205, 250));
        pnl_button.setAutoscrolls(true);
        pnl_button.setEnabled(false);
        pnl_button.setOpaque(false);
        pnl_button.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jsc_item.setViewportView(pnl_button);

        panel_escoger_orden.add(jsc_item, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 510, 310));

        Jbtn_cerrar_venta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Cash-register-icon.png"))); // NOI18N
        Jbtn_cerrar_venta.setMnemonic('x');
        Jbtn_cerrar_venta.setToolTipText("Cerrar venta Alt+X");
        Jbtn_cerrar_venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_cerrar_ventaActionPerformed(evt);
            }
        });
        panel_escoger_orden.add(Jbtn_cerrar_venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 41, -1));

        tabla_venta.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        tabla_venta.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_venta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_ventaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabla_ventaMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_venta);

        panel_escoger_orden.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(529, 49, 580, 290));

        Jbtn_suma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/document-add-icon.png"))); // NOI18N
        Jbtn_suma.setMnemonic('s');
        Jbtn_suma.setToolTipText("Sumar cantidad Alt+S");
        Jbtn_suma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_sumaActionPerformed(evt);
            }
        });
        panel_escoger_orden.add(Jbtn_suma, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 260, 40, -1));

        Jbtn_resta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/document-remove-icon.png"))); // NOI18N
        Jbtn_resta.setMnemonic('t');
        Jbtn_resta.setToolTipText("Resta cantidad Alt+T");
        Jbtn_resta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_restaActionPerformed(evt);
            }
        });
        panel_escoger_orden.add(Jbtn_resta, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 300, 40, -1));

        Jbtn_quitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/remove-from-database-icon.png"))); // NOI18N
        Jbtn_quitar.setToolTipText("Quitar elemento");
        Jbtn_quitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_quitarActionPerformed(evt);
            }
        });
        panel_escoger_orden.add(Jbtn_quitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 220, 40, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/go-back-icon.png"))); // NOI18N
        jButton1.setMnemonic('r');
        jButton1.setToolTipText("Regresar Alt+R");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panel_escoger_orden.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(528, 11, 41, -1));

        lbl_nombre_cliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbl_nombre_cliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_nombre_cliente.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        panel_escoger_orden.add(lbl_nombre_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 0, 150, 20));

        jLabel31.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel31.setText("Mesa:");
        jLabel31.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        panel_escoger_orden.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, 50, 20));

        jLabel32.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel32.setText("Cliente:");
        jLabel32.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        panel_escoger_orden.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 0, 60, 20));

        lbl_mesa.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbl_mesa.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        panel_escoger_orden.add(lbl_mesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 0, 130, 20));

        Jbtn_guardar_venta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Save-icon.png"))); // NOI18N
        Jbtn_guardar_venta1.setMnemonic('g');
        Jbtn_guardar_venta1.setToolTipText("Guardar Alt+G");
        Jbtn_guardar_venta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_guardar_venta1ActionPerformed(evt);
            }
        });
        panel_escoger_orden.add(Jbtn_guardar_venta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(575, 11, 40, -1));

        Jbtn_cancelar_venta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/File-Delete-icon.png"))); // NOI18N
        Jbtn_cancelar_venta.setMnemonic('c');
        Jbtn_cancelar_venta.setToolTipText("Cancelar venta Alt+C");
        Jbtn_cancelar_venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_cancelar_ventaActionPerformed(evt);
            }
        });
        panel_escoger_orden.add(Jbtn_cancelar_venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 41, -1));

        Jbtn_generar_nota1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/document-archive-icon_1.png"))); // NOI18N
        Jbtn_generar_nota1.setToolTipText("Generar nota");
        Jbtn_generar_nota1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_generar_nota1ActionPerformed(evt);
            }
        });
        panel_escoger_orden.add(Jbtn_generar_nota1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 50, 44, 40));

        Jbtn_ver_notas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/task-notes-icon.png"))); // NOI18N
        Jbtn_ver_notas.setToolTipText("Ver notas del platillo");
        Jbtn_ver_notas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_ver_notasActionPerformed(evt);
            }
        });
        panel_escoger_orden.add(Jbtn_ver_notas, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 90, 44, 40));

        Jbtn_generar_ticket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cash-register-icon_2.png"))); // NOI18N
        Jbtn_generar_ticket.setMnemonic('p');
        Jbtn_generar_ticket.setToolTipText("Generar ticket Alt+P");
        Jbtn_generar_ticket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_generar_ticketActionPerformed(evt);
            }
        });
        panel_escoger_orden.add(Jbtn_generar_ticket, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 130, 44, 40));
        Jbtn_generar_ticket.getAccessibleContext().setAccessibleName("Alt+P");

        panel_escoger_orden.add(check_ticket_personalizado, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 170, -1, -1));

        Jbtn_quitar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit-icon.png"))); // NOI18N
        Jbtn_quitar1.setToolTipText("Habilitar botones");
        Jbtn_quitar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_quitar1ActionPerformed(evt);
            }
        });
        panel_escoger_orden.add(Jbtn_quitar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, 40, -1));

        lbl_producto_elegido.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        panel_escoger_orden.add(lbl_producto_elegido, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 30, 270, 20));

        txtFindCodeProduct.setToolTipText("Introduce codigo de producto");
        txtFindCodeProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindCodeProductActionPerformed(evt);
            }
        });
        txtFindCodeProduct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFindCodeProductKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindCodeProductKeyReleased(evt);
            }
        });
        panel_escoger_orden.add(txtFindCodeProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(429, 0, 80, 30));

        jLabel27.setText("Codigo del producto:");
        panel_escoger_orden.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(284, 4, 140, 30));

        jbtnBuscarElemento.setMnemonic('b');
        jbtnBuscarElemento.setText("Buscar elemento");
        jbtnBuscarElemento.setToolTipText("Buscar elemento Alt+B");
        jbtnBuscarElemento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBuscarElementoActionPerformed(evt);
            }
        });
        panel_escoger_orden.add(jbtnBuscarElemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 130, -1));

        jTabbedPane4.addTab(" Orden ", new javax.swing.ImageIcon(getClass().getResource("/imagenes/sales-report-icon.png")), panel_escoger_orden); // NOI18N

        panel_consultar_ventas.setToolTipText("Consultar ventas");
        panel_consultar_ventas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panel_consultar_ventas.add(txt_fecha_inicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(1006, 36, 140, -1));
        panel_consultar_ventas.add(txt_fecha_final, new org.netbeans.lib.awtextra.AbsoluteConstraints(1007, 94, 140, -1));

        jLabel37.setText("Fecha Inicial:");
        panel_consultar_ventas.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(1004, 11, 140, -1));

        jLabel38.setText("Fecha Final:");
        panel_consultar_ventas.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(1005, 74, 140, -1));

        Jbtn_buscar_fecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calendar-icon.png"))); // NOI18N
        Jbtn_buscar_fecha.setToolTipText("Buscar por fecha");
        Jbtn_buscar_fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_buscar_fechaActionPerformed(evt);
            }
        });
        panel_consultar_ventas.add(Jbtn_buscar_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 130, 44, 40));

        Jbtn_refrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reload-icon.png"))); // NOI18N
        Jbtn_refrescar.setToolTipText("Refrescar tabla");
        Jbtn_refrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_refrescarActionPerformed(evt);
            }
        });
        panel_consultar_ventas.add(Jbtn_refrescar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 130, 44, 40));

        Jbtn_generar_nota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/document-archive-icon_1.png"))); // NOI18N
        Jbtn_generar_nota.setToolTipText("Generar reporte");
        Jbtn_generar_nota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_generar_notaActionPerformed(evt);
            }
        });
        panel_consultar_ventas.add(Jbtn_generar_nota, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 180, 44, 40));

        Jbtn_generar_nota_por_fecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Actions-edit-paste-icon_1.png"))); // NOI18N
        Jbtn_generar_nota_por_fecha.setToolTipText("Generar reporte por fechas");
        Jbtn_generar_nota_por_fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_generar_nota_por_fechaActionPerformed(evt);
            }
        });
        panel_consultar_ventas.add(Jbtn_generar_nota_por_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 130, 44, 40));

        txt_suma.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        txt_suma.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_suma.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        panel_consultar_ventas.add(txt_suma, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 310, 142, 31));

        jLabel48.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel48.setText("Historial de ventas:");
        panel_consultar_ventas.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jbtn_insumos_vendidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search-icon.png"))); // NOI18N
        jbtn_insumos_vendidos.setText("insumos vendidos");
        jbtn_insumos_vendidos.setToolTipText("insumos vendidos");
        jbtn_insumos_vendidos.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jbtn_insumos_vendidos.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jbtn_insumos_vendidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_insumos_vendidosActionPerformed(evt);
            }
        });
        panel_consultar_ventas.add(jbtn_insumos_vendidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 230, 150, 40));

        Jbtn_recuperar_ticket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cash-register-icon_2.png"))); // NOI18N
        Jbtn_recuperar_ticket.setToolTipText("Generar ticket");
        Jbtn_recuperar_ticket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_recuperar_ticketActionPerformed(evt);
            }
        });
        panel_consultar_ventas.add(Jbtn_recuperar_ticket, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 180, 44, 40));

        tabla_consultar_venta.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        tabla_consultar_venta.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_consultar_venta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_consultar_ventaMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tabla_consultar_venta);

        javax.swing.GroupLayout panel_c_ventasLayout = new javax.swing.GroupLayout(panel_c_ventas);
        panel_c_ventas.setLayout(panel_c_ventasLayout);
        panel_c_ventasLayout.setHorizontalGroup(
            panel_c_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_c_ventasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_c_ventasLayout.setVerticalGroup(
            panel_c_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_c_ventasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel_consultar_ventas.add(panel_c_ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 990, 310));

        JbtnCancelarVentaHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/File-Delete-icon.png"))); // NOI18N
        JbtnCancelarVentaHistorial.setMnemonic('c');
        JbtnCancelarVentaHistorial.setToolTipText("Cancelar venta");
        JbtnCancelarVentaHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnCancelarVentaHistorialActionPerformed(evt);
            }
        });
        panel_consultar_ventas.add(JbtnCancelarVentaHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 180, 41, -1));

        jTabbedPane4.addTab(" Historial Pedidos ", new javax.swing.ImageIcon(getClass().getResource("/imagenes/document-archive-icon_1.png")), panel_consultar_ventas); // NOI18N

        javax.swing.GroupLayout panel_ventasLayout = new javax.swing.GroupLayout(panel_ventas);
        panel_ventas.setLayout(panel_ventasLayout);
        panel_ventasLayout.setHorizontalGroup(
            panel_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panel_ventasLayout.setVerticalGroup(
            panel_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ventasLayout.createSequentialGroup()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("VENTAS", new javax.swing.ImageIcon(getClass().getResource("/imagenes/dollar_48Pxs.png")), panel_ventas); // NOI18N

        panel_caja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tabla_consultar_caja.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        tabla_consultar_caja.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_consultar_caja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_consultar_cajaMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tabla_consultar_caja);

        tabla_ingresos_caja.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        tabla_ingresos_caja.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_ingresos_caja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_ingresos_cajaMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tabla_ingresos_caja);

        tabla_egresos_caja.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        tabla_egresos_caja.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_egresos_caja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_egresos_cajaMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(tabla_egresos_caja);

        jLabel50.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel50.setText("INGRESOS:");

        jLabel51.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel51.setText("EGRESOS:");

        Jbtn_aperturar_caja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/folder-open-icon_1.png"))); // NOI18N
        Jbtn_aperturar_caja.setText(" Abrir Caja ");
        Jbtn_aperturar_caja.setToolTipText("Aperturar caja");
        Jbtn_aperturar_caja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_aperturar_cajaActionPerformed(evt);
            }
        });

        txt_monto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txt_monto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_monto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_monto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_montoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_montoFocusLost(evt);
            }
        });

        buttonGroup1.add(check_ingreso);
        check_ingreso.setText("Ingreso");
        check_ingreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_ingresoActionPerformed(evt);
            }
        });

        buttonGroup1.add(check_egreso);
        check_egreso.setText("Egreso");

        jLabel52.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel52.setText("Monto:");

        Jbtn_registrar_caja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Save-icon.png"))); // NOI18N
        Jbtn_registrar_caja.setToolTipText("Registrar monto");
        Jbtn_registrar_caja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_registrar_cajaActionPerformed(evt);
            }
        });

        Jbtn_cerrar_caja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cross-icon.png"))); // NOI18N
        Jbtn_cerrar_caja.setText(" Cerrar Caja ");
        Jbtn_cerrar_caja.setToolTipText("Cerrar caja");
        Jbtn_cerrar_caja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_cerrar_cajaActionPerformed(evt);
            }
        });

        txt_nota.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_nota.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_nota.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_notaFocusGained(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel53.setText("Nota:");

        txt_total_ventas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        txt_total_ventas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_total_ventas.setText("0.00");
        txt_total_ventas.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txt_total_ingresos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        txt_total_ingresos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_total_ingresos.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        txt_total_egresos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        txt_total_egresos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_total_egresos.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jLabel54.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel54.setText("Ventas:");

        jLabel55.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel55.setText("Ingresos:");

        jLabel56.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel56.setText("Egresos:");

        txt_total_caja.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        txt_total_caja.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_total_caja.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        Jbtn_eliminar_monto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/remove-from-database-icon.png"))); // NOI18N
        Jbtn_eliminar_monto.setToolTipText("Eliminar monto");
        Jbtn_eliminar_monto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_eliminar_montoActionPerformed(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel57.setText("Total en caja:");

        lbl_total_ventas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        javax.swing.GroupLayout panel_registrar_mov_cajaLayout = new javax.swing.GroupLayout(panel_registrar_mov_caja);
        panel_registrar_mov_caja.setLayout(panel_registrar_mov_cajaLayout);
        panel_registrar_mov_cajaLayout.setHorizontalGroup(
            panel_registrar_mov_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_registrar_mov_cajaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panel_registrar_mov_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_registrar_mov_cajaLayout.createSequentialGroup()
                        .addGroup(panel_registrar_mov_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_registrar_mov_cajaLayout.createSequentialGroup()
                                .addComponent(lbl_total_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(273, 273, 273)
                                .addComponent(jLabel50)
                                .addGap(177, 177, 177)
                                .addComponent(jLabel51))
                            .addGroup(panel_registrar_mov_cajaLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(Jbtn_aperturar_caja, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Jbtn_cerrar_caja, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(182, 182, 182)
                        .addGroup(panel_registrar_mov_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52)
                            .addComponent(jLabel53))
                        .addGap(16, 16, 16)
                        .addGroup(panel_registrar_mov_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_monto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel_registrar_mov_cajaLayout.createSequentialGroup()
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panel_registrar_mov_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_registrar_mov_cajaLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(panel_registrar_mov_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_registrar_mov_cajaLayout.createSequentialGroup()
                                        .addComponent(check_ingreso)
                                        .addGap(7, 7, 7)
                                        .addComponent(check_egreso)
                                        .addGap(11, 11, 11)
                                        .addComponent(Jbtn_registrar_caja, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel_registrar_mov_cajaLayout.createSequentialGroup()
                                        .addGap(140, 140, 140)
                                        .addComponent(Jbtn_eliminar_monto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel_registrar_mov_cajaLayout.createSequentialGroup()
                                        .addComponent(jLabel54)
                                        .addGap(19, 19, 19)
                                        .addComponent(txt_total_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel57)
                                    .addGroup(panel_registrar_mov_cajaLayout.createSequentialGroup()
                                        .addGap(60, 60, 60)
                                        .addComponent(txt_total_caja, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panel_registrar_mov_cajaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel_registrar_mov_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_registrar_mov_cajaLayout.createSequentialGroup()
                                        .addComponent(jLabel56)
                                        .addGap(11, 11, 11)
                                        .addComponent(txt_total_egresos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel_registrar_mov_cajaLayout.createSequentialGroup()
                                        .addComponent(jLabel55)
                                        .addGap(9, 9, 9)
                                        .addComponent(txt_total_ingresos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
        );
        panel_registrar_mov_cajaLayout.setVerticalGroup(
            panel_registrar_mov_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_registrar_mov_cajaLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panel_registrar_mov_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_registrar_mov_cajaLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel50))
                    .addGroup(panel_registrar_mov_cajaLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel51))
                    .addGroup(panel_registrar_mov_cajaLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel52)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel53))
                    .addGroup(panel_registrar_mov_cajaLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txt_monto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txt_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_registrar_mov_cajaLayout.createSequentialGroup()
                        .addGroup(panel_registrar_mov_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Jbtn_aperturar_caja, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Jbtn_cerrar_caja, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_total_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panel_registrar_mov_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_registrar_mov_cajaLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panel_registrar_mov_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(check_ingreso)
                            .addComponent(check_egreso)
                            .addComponent(Jbtn_registrar_caja, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(Jbtn_eliminar_monto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addGroup(panel_registrar_mov_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_registrar_mov_cajaLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel54))
                            .addComponent(txt_total_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(panel_registrar_mov_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_total_ingresos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55))
                        .addGap(2, 2, 2)
                        .addGroup(panel_registrar_mov_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_total_egresos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel57)
                        .addGap(3, 3, 3)
                        .addComponent(txt_total_caja, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jTabbedPane5.addTab(" Resumen Caja ", new javax.swing.ImageIcon(getClass().getResource("/imagenes/Folder-New-icon_32.png")), panel_registrar_mov_caja); // NOI18N

        tabla_consultar_caja1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        tabla_consultar_caja1.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_consultar_caja1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_consultar_caja1MouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(tabla_consultar_caja1);

        Jbtn_reporte_caja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/excel 24x.png"))); // NOI18N
        Jbtn_reporte_caja.setToolTipText("Gerar reporte de caja");
        Jbtn_reporte_caja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_reporte_cajaActionPerformed(evt);
            }
        });

        jLabel110.setText("Fecha Inicial:");

        jLabel111.setText("Fecha Final:");

        jbtn_insumos_vendidos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search-icon.png"))); // NOI18N
        jbtn_insumos_vendidos1.setToolTipText("Busqueda por fecha en caja");
        jbtn_insumos_vendidos1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jbtn_insumos_vendidos1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jbtn_insumos_vendidos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_insumos_vendidos1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_consultar_cajaLayout = new javax.swing.GroupLayout(panel_consultar_caja);
        panel_consultar_caja.setLayout(panel_consultar_cajaLayout);
        panel_consultar_cajaLayout.setHorizontalGroup(
            panel_consultar_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_consultar_cajaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel_consultar_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Jbtn_reporte_caja, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel110)
                    .addComponent(txt_fecha_inicial_caja, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_consultar_cajaLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(panel_consultar_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbtn_insumos_vendidos1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_consultar_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel111)
                                .addComponent(txt_fecha_final_caja, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(251, Short.MAX_VALUE))
        );
        panel_consultar_cajaLayout.setVerticalGroup(
            panel_consultar_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_consultar_cajaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_consultar_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_consultar_cajaLayout.createSequentialGroup()
                        .addComponent(Jbtn_reporte_caja, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel110)
                        .addGap(11, 11, 11)
                        .addComponent(txt_fecha_inicial_caja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel111)
                        .addGap(6, 6, 6)
                        .addComponent(txt_fecha_final_caja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtn_insumos_vendidos1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane5.addTab(" Historial de Caja ", new javax.swing.ImageIcon(getClass().getResource("/imagenes/folder-documents-icon_32_1.png")), panel_consultar_caja); // NOI18N

        javax.swing.GroupLayout panel_cajaLayout = new javax.swing.GroupLayout(panel_caja);
        panel_caja.setLayout(panel_cajaLayout);
        panel_cajaLayout.setHorizontalGroup(
            panel_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5)
        );
        panel_cajaLayout.setVerticalGroup(
            panel_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5)
        );

        jTabbedPane1.addTab("CAJA ", new javax.swing.ImageIcon(getClass().getResource("/imagenes/Cash-register-icon_1.png")), panel_caja); // NOI18N

        panel_insumos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jTabbedPane2.setToolTipText("Administra tus platillos e insumos");
        jTabbedPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane2MouseClicked(evt);
            }
        });

        subPanel_agregarInsumos.setToolTipText("Agrega insumos y categorias");

        panel_categorias.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "CATEGORIAS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 12))); // NOI18N

        txt_agregar_categoria.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_agregar_categoria.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_agregar_categoriaFocusGained(evt);
            }
        });
        txt_agregar_categoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_agregar_categoriaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_agregar_categoriaKeyReleased(evt);
            }
        });

        Jbtn_agregar_categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Actions-go-down-icon.png"))); // NOI18N
        Jbtn_agregar_categoria.setToolTipText("Agrega a la tabla");
        Jbtn_agregar_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_agregar_categoriaActionPerformed(evt);
            }
        });

        tabla_categorias.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        tabla_categorias.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_categorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_categoriasMouseClicked(evt);
            }
        });
        tabla_categorias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_categoriasKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(tabla_categorias);

        Jbtn_quitar_categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/table-remove-icon.png"))); // NOI18N
        Jbtn_quitar_categoria.setToolTipText("Quita elemento de la tabla");
        Jbtn_quitar_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_quitar_categoriaActionPerformed(evt);
            }
        });

        Jbtn_editar_categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit-icon.png"))); // NOI18N
        Jbtn_editar_categoria.setToolTipText("Editar elemento");
        Jbtn_editar_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_editar_categoriaActionPerformed(evt);
            }
        });

        Jtbn_guardar_categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/database-check-icon.png"))); // NOI18N
        Jtbn_guardar_categoria.setToolTipText("Guardar");
        Jtbn_guardar_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jtbn_guardar_categoriaActionPerformed(evt);
            }
        });

        Jbtn_nuevo_categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Files-New-File-icon.png"))); // NOI18N
        Jbtn_nuevo_categoria.setToolTipText("Nuevo");
        Jbtn_nuevo_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_nuevo_categoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_categoriasLayout = new javax.swing.GroupLayout(panel_categorias);
        panel_categorias.setLayout(panel_categoriasLayout);
        panel_categoriasLayout.setHorizontalGroup(
            panel_categoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_categoriasLayout.createSequentialGroup()
                .addGroup(panel_categoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_categoriasLayout.createSequentialGroup()
                        .addGroup(panel_categoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_categoriasLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Jbtn_agregar_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Jbtn_quitar_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Jbtn_editar_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(Jtbn_guardar_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Jbtn_nuevo_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_categoriasLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(txt_agregar_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_categoriasLayout.createSequentialGroup()
                        .addGap(0, 2, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panel_categoriasLayout.setVerticalGroup(
            panel_categoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_categoriasLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(txt_agregar_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_categoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Jbtn_agregar_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_categoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Jtbn_guardar_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(Jbtn_quitar_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Jbtn_editar_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(Jbtn_nuevo_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel_detalle_categorias.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "INSUMOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 12))); // NOI18N
        panel_detalle_categorias.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla_insumos.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        tabla_insumos.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_insumos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_insumosMouseClicked(evt);
            }
        });
        tabla_insumos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_insumosKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_insumos);

        panel_detalle_categorias.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 103, 426, 220));

        txt_nombre_insumo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_nombre_insumo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_nombre_insumoFocusGained(evt);
            }
        });
        txt_nombre_insumo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_nombre_insumoKeyReleased(evt);
            }
        });
        panel_detalle_categorias.add(txt_nombre_insumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 190, -1));

        txt_unidad_medida.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_unidad_medida.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_unidad_medidaFocusGained(evt);
            }
        });
        panel_detalle_categorias.add(txt_unidad_medida, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 89, -1));

        jLabel1.setText("Nombre:");
        panel_detalle_categorias.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 17, -1, -1));

        jLabel2.setText("U.M.");
        panel_detalle_categorias.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 20, -1, -1));

        jLabel3.setText("Stock:");
        panel_detalle_categorias.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 20, -1, -1));

        jLabel4.setText("Ultimo costo:");
        panel_detalle_categorias.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, -1, -1));

        Jbtn_agregar_insumos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Actions-go-down-icon.png"))); // NOI18N
        Jbtn_agregar_insumos.setToolTipText("Agrega a la tabla");
        Jbtn_agregar_insumos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_agregar_insumosActionPerformed(evt);
            }
        });
        panel_detalle_categorias.add(Jbtn_agregar_insumos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 40, 28));

        Jbtn_quitar_insumo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/table-remove-icon.png"))); // NOI18N
        Jbtn_quitar_insumo.setToolTipText("Quita elemento de la tabla");
        Jbtn_quitar_insumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_quitar_insumoActionPerformed(evt);
            }
        });
        panel_detalle_categorias.add(Jbtn_quitar_insumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 40, 28));

        Jtbn_guardar_insumos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/database-check-icon.png"))); // NOI18N
        Jtbn_guardar_insumos.setToolTipText("Guardar");
        Jtbn_guardar_insumos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jtbn_guardar_insumosActionPerformed(evt);
            }
        });
        panel_detalle_categorias.add(Jtbn_guardar_insumos, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 40, 28));

        Jbtn_editar_insumos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit-icon.png"))); // NOI18N
        Jbtn_editar_insumos.setToolTipText("Editar elemento");
        Jbtn_editar_insumos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_editar_insumosActionPerformed(evt);
            }
        });
        panel_detalle_categorias.add(Jbtn_editar_insumos, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 34, 28));

        Jbtn_nuevo_insumo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Files-New-File-icon.png"))); // NOI18N
        Jbtn_nuevo_insumo.setToolTipText("Nuevo");
        Jbtn_nuevo_insumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_nuevo_insumoActionPerformed(evt);
            }
        });
        panel_detalle_categorias.add(Jbtn_nuevo_insumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 40, 28));

        txt_ultimo_costo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txt_ultimo_costo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_ultimo_costoFocusGained(evt);
            }
        });
        panel_detalle_categorias.add(txt_ultimo_costo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 62, -1));

        txt_stock.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txt_stock.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_stockFocusGained(evt);
            }
        });
        panel_detalle_categorias.add(txt_stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 54, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reload-icon.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        panel_detalle_categorias.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 40, 28));

        Jbtn_reporte_insumos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Actions-edit-paste-icon_1.png"))); // NOI18N
        Jbtn_reporte_insumos.setToolTipText("Reporte insumos");
        Jbtn_reporte_insumos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_reporte_insumosActionPerformed(evt);
            }
        });
        panel_detalle_categorias.add(Jbtn_reporte_insumos, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 40, 28));

        panel_detalle.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "DETALLE", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        txt_descripcion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_descripcion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_descripcionFocusGained(evt);
            }
        });
        txt_descripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_descripcionActionPerformed(evt);
            }
        });
        txt_descripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_descripcionKeyReleased(evt);
            }
        });

        jLabel5.setText("Descripcion:");

        jLabel6.setText("Precio al publico:");

        Jbtn_agregar_detalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Actions-go-down-icon.png"))); // NOI18N
        Jbtn_agregar_detalle.setToolTipText("Agrega a la tabla");
        Jbtn_agregar_detalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_agregar_detalleActionPerformed(evt);
            }
        });

        Jbtn_quitar_detalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/table-remove-icon.png"))); // NOI18N
        Jbtn_quitar_detalle.setToolTipText("Quita elemento de la tabla");
        Jbtn_quitar_detalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_quitar_detalleActionPerformed(evt);
            }
        });

        Jtbn_guardar_detalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/database-check-icon.png"))); // NOI18N
        Jtbn_guardar_detalle.setToolTipText("Guardar");
        Jtbn_guardar_detalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jtbn_guardar_detalleActionPerformed(evt);
            }
        });

        Jbtn_editar_detalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit-icon.png"))); // NOI18N
        Jbtn_editar_detalle.setToolTipText("Editar elemento");
        Jbtn_editar_detalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_editar_detalleActionPerformed(evt);
            }
        });

        Jbtn_nuevo_detalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Files-New-File-icon.png"))); // NOI18N
        Jbtn_nuevo_detalle.setToolTipText("Nuevo");
        Jbtn_nuevo_detalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_nuevo_detalleActionPerformed(evt);
            }
        });

        txt_precio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txt_precio.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_precio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_precioActionPerformed(evt);
            }
        });
        txt_precio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_precioFocusGained(evt);
            }
        });
        txt_precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_precioKeyPressed(evt);
            }
        });

        tabla_detalle_categorias.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        tabla_detalle_categorias.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_detalle_categorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_detalle_categoriasMouseClicked(evt);
            }
        });
        tabla_detalle_categorias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_detalle_categoriasKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tabla_detalle_categorias);

        javax.swing.GroupLayout panel_detalleLayout = new javax.swing.GroupLayout(panel_detalle);
        panel_detalle.setLayout(panel_detalleLayout);
        panel_detalleLayout.setHorizontalGroup(
            panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_detalleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_detalleLayout.createSequentialGroup()
                        .addGroup(panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txt_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(105, 105, 105))
                    .addGroup(panel_detalleLayout.createSequentialGroup()
                        .addGroup(panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_detalleLayout.createSequentialGroup()
                                .addComponent(Jbtn_agregar_detalle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Jbtn_quitar_detalle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Jbtn_editar_detalle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(Jtbn_guardar_detalle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Jbtn_nuevo_detalle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panel_detalleLayout.setVerticalGroup(
            panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_detalleLayout.createSequentialGroup()
                .addGroup(panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Jtbn_guardar_detalle, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(Jbtn_quitar_detalle, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Jbtn_agregar_detalle, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Jbtn_editar_detalle, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(Jbtn_nuevo_detalle, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );

        javax.swing.GroupLayout subPanel_agregarInsumosLayout = new javax.swing.GroupLayout(subPanel_agregarInsumos);
        subPanel_agregarInsumos.setLayout(subPanel_agregarInsumosLayout);
        subPanel_agregarInsumosLayout.setHorizontalGroup(
            subPanel_agregarInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subPanel_agregarInsumosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_categorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_detalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_detalle_categorias, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        subPanel_agregarInsumosLayout.setVerticalGroup(
            subPanel_agregarInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, subPanel_agregarInsumosLayout.createSequentialGroup()
                .addGroup(subPanel_agregarInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel_detalle_categorias, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                    .addComponent(panel_detalle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_categorias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane2.addTab(" Administrar recursos ", new javax.swing.ImageIcon(getClass().getResource("/imagenes/Toolbar-Add-Folder-icon.png")), subPanel_agregarInsumos); // NOI18N

        subPanel_editarInsumos.setToolTipText("Asigna insumos a los platillos y bebidas");

        panel_1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Categorias", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        tabla_detalle_categorias2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        tabla_detalle_categorias2.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_detalle_categorias2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_detalle_categorias2MouseClicked(evt);
            }
        });
        tabla_detalle_categorias2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_detalle_categorias2KeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(tabla_detalle_categorias2);

        combo_categorias.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        combo_categorias.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo_categorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                combo_categoriasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                combo_categoriasMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                combo_categoriasMousePressed(evt);
            }
        });
        combo_categorias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_categoriasItemStateChanged(evt);
            }
        });
        combo_categorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_categoriasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_1Layout = new javax.swing.GroupLayout(panel_1);
        panel_1.setLayout(panel_1Layout);
        panel_1Layout.setHorizontalGroup(
            panel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(combo_categorias, 0, 304, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_1Layout.setVerticalGroup(
            panel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(combo_categorias, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel_2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Insumos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        panel_2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla_insumos2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        tabla_insumos2.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_insumos2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_insumos2MouseClicked(evt);
            }
        });
        tabla_insumos2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_insumos2KeyPressed(evt);
            }
        });
        jScrollPane6.setViewportView(tabla_insumos2);

        panel_2.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 72, 412, 195));

        txt_buscar_insumos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_buscar_insumos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_buscar_insumosMousePressed(evt);
            }
        });
        txt_buscar_insumos.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                txt_buscar_insumosComponentAdded(evt);
            }
        });
        txt_buscar_insumos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_buscar_insumosActionPerformed(evt);
            }
        });
        txt_buscar_insumos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_buscar_insumosFocusGained(evt);
            }
        });
        txt_buscar_insumos.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                txt_buscar_insumosHierarchyChanged(evt);
            }
        });
        txt_buscar_insumos.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                txt_buscar_insumosCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txt_buscar_insumosInputMethodTextChanged(evt);
            }
        });
        txt_buscar_insumos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscar_insumosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscar_insumosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_buscar_insumosKeyTyped(evt);
            }
        });
        panel_2.add(txt_buscar_insumos, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 350, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search-icon.png"))); // NOI18N
        panel_2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel8.setText("Cantidad:");
        panel_2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 272, -1, -1));

        Jbtn_relacionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Actions-go-next-icon.png"))); // NOI18N
        Jbtn_relacionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_relacionarActionPerformed(evt);
            }
        });
        panel_2.add(Jbtn_relacionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 292, 41, 21));

        txt_cantidad_insumo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txt_cantidad_insumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cantidad_insumoActionPerformed(evt);
            }
        });
        txt_cantidad_insumo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_cantidad_insumoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_cantidad_insumoFocusLost(evt);
            }
        });
        panel_2.add(txt_cantidad_insumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 292, 76, -1));

        lbl_nombre_insumo.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        panel_2.add(lbl_nombre_insumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 50, 250, 20));

        panel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Consumibles", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        panel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla_consumibles_insumos.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        tabla_consumibles_insumos.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_consumibles_insumos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_consumibles_insumosMouseClicked(evt);
            }
        });
        tabla_consumibles_insumos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_consumibles_insumosKeyPressed(evt);
            }
        });
        jScrollPane9.setViewportView(tabla_consumibles_insumos);

        panel3.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 61, 323, 250));

        Jbtn_quitar_insumo_relacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/table-remove-icon.png"))); // NOI18N
        Jbtn_quitar_insumo_relacion.setToolTipText("Quita elemento de la tabla");
        Jbtn_quitar_insumo_relacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_quitar_insumo_relacionActionPerformed(evt);
            }
        });
        panel3.add(Jbtn_quitar_insumo_relacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 27, 40, 28));

        lbl_nombre_platillo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        panel3.add(lbl_nombre_platillo, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 27, 251, 30));

        javax.swing.GroupLayout subPanel_editarInsumosLayout = new javax.swing.GroupLayout(subPanel_editarInsumos);
        subPanel_editarInsumos.setLayout(subPanel_editarInsumosLayout);
        subPanel_editarInsumosLayout.setHorizontalGroup(
            subPanel_editarInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subPanel_editarInsumosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addContainerGap())
        );
        subPanel_editarInsumosLayout.setVerticalGroup(
            subPanel_editarInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subPanel_editarInsumosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(subPanel_editarInsumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane2.addTab(" Recetas ", new javax.swing.ImageIcon(getClass().getResource("/imagenes/Steak-icon.png")), subPanel_editarInsumos); // NOI18N

        javax.swing.GroupLayout panel_insumosLayout = new javax.swing.GroupLayout(panel_insumos);
        panel_insumos.setLayout(panel_insumosLayout);
        panel_insumosLayout.setHorizontalGroup(
            panel_insumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_insumosLayout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        panel_insumosLayout.setVerticalGroup(
            panel_insumosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTabbedPane1.addTab("INSUMOS ", new javax.swing.ImageIcon(getClass().getResource("/imagenes/carne_48Pxs.png")), panel_insumos); // NOI18N

        panel_compras.setToolTipText("Compras");
        panel_compras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        SubPanel_agregar_compra.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla_insumos_compras.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        tabla_insumos_compras.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_insumos_compras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_insumos_comprasMouseClicked(evt);
            }
        });
        tabla_insumos_compras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_insumos_comprasKeyPressed(evt);
            }
        });
        jScrollPane10.setViewportView(tabla_insumos_compras);

        SubPanel_agregar_compra.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 61, 426, 280));

        txt_buscar_insumos_compras.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_buscar_insumos_compras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_buscar_insumos_comprasMousePressed(evt);
            }
        });
        txt_buscar_insumos_compras.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                txt_buscar_insumos_comprasComponentAdded(evt);
            }
        });
        txt_buscar_insumos_compras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_buscar_insumos_comprasActionPerformed(evt);
            }
        });
        txt_buscar_insumos_compras.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_buscar_insumos_comprasFocusGained(evt);
            }
        });
        txt_buscar_insumos_compras.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                txt_buscar_insumos_comprasHierarchyChanged(evt);
            }
        });
        txt_buscar_insumos_compras.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                txt_buscar_insumos_comprasCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txt_buscar_insumos_comprasInputMethodTextChanged(evt);
            }
        });
        txt_buscar_insumos_compras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscar_insumos_comprasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscar_insumos_comprasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_buscar_insumos_comprasKeyTyped(evt);
            }
        });
        SubPanel_agregar_compra.add(txt_buscar_insumos_compras, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 380, 28));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search-icon.png"))); // NOI18N
        SubPanel_agregar_compra.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        tabla_detalle_compras.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        tabla_detalle_compras.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_detalle_compras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_detalle_comprasMouseClicked(evt);
            }
        });
        tabla_detalle_compras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_detalle_comprasKeyPressed(evt);
            }
        });
        jScrollPane11.setViewportView(tabla_detalle_compras);

        SubPanel_agregar_compra.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(454, 103, 426, 238));

        txt_cantidad_compra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txt_cantidad_compra.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_cantidad_compra.setText("0.00");
        txt_cantidad_compra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_cantidad_compraFocusGained(evt);
            }
        });
        SubPanel_agregar_compra.add(txt_cantidad_compra, new org.netbeans.lib.awtextra.AbsoluteConstraints(454, 31, 86, -1));

        jLabel40.setText("Cantidad:");
        SubPanel_agregar_compra.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(454, 11, -1, -1));

        txt_ultimo_costo_compras.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txt_ultimo_costo_compras.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_ultimo_costo_compras.setText("0.00");
        txt_ultimo_costo_compras.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_ultimo_costo_comprasFocusGained(evt);
            }
        });
        SubPanel_agregar_compra.add(txt_ultimo_costo_compras, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 31, 90, -1));

        jLabel42.setText("Ultimo costo:");
        SubPanel_agregar_compra.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 11, -1, -1));

        txt_comentario_compras.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_comentario_comprasFocusGained(evt);
            }
        });
        SubPanel_agregar_compra.add(txt_comentario_compras, new org.netbeans.lib.awtextra.AbsoluteConstraints(646, 31, 234, -1));

        jLabel43.setText("Comentario:");
        SubPanel_agregar_compra.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(646, 11, -1, -1));

        Jbtn_agregar_compras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Actions-go-down-icon.png"))); // NOI18N
        Jbtn_agregar_compras.setToolTipText("Agrega a la tabla");
        Jbtn_agregar_compras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_agregar_comprasActionPerformed(evt);
            }
        });
        SubPanel_agregar_compra.add(Jbtn_agregar_compras, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 69, 40, 28));

        Jbtn_quitar_compras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/table-remove-icon.png"))); // NOI18N
        Jbtn_quitar_compras.setToolTipText("Quita elemento de la tabla");
        Jbtn_quitar_compras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_quitar_comprasActionPerformed(evt);
            }
        });
        SubPanel_agregar_compra.add(Jbtn_quitar_compras, new org.netbeans.lib.awtextra.AbsoluteConstraints(793, 69, 40, 28));

        lbl_eleccion_insumo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        SubPanel_agregar_compra.add(lbl_eleccion_insumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(464, 74, 311, 23));

        txt_total_compra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        txt_total_compra.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_total_compra.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        SubPanel_agregar_compra.add(txt_total_compra, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 304, 121, 37));

        jLabel44.setText("Proveedor:");
        SubPanel_agregar_compra.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, -1, -1));

        txt_comentarios.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_comentariosFocusGained(evt);
            }
        });
        jScrollPane12.setViewportView(txt_comentarios);

        SubPanel_agregar_compra.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 103, 226, 70));

        jLabel46.setText("Comentarios:");
        SubPanel_agregar_compra.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(925, 79, -1, -1));

        Jtbn_registrar_compra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/shop-cart-apply-icon.png"))); // NOI18N
        Jtbn_registrar_compra.setToolTipText("Registrar compra");
        Jtbn_registrar_compra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jtbn_registrar_compraActionPerformed(evt);
            }
        });
        SubPanel_agregar_compra.add(Jtbn_registrar_compra, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 210, 57, 40));

        txt_proveedor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_proveedorFocusGained(evt);
            }
        });
        SubPanel_agregar_compra.add(txt_proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 40, 230, -1));

        jTabbedPane6.addTab(" Nuevas Compras ", new javax.swing.ImageIcon(getClass().getResource("/imagenes/shop-cart-apply-icon.png")), SubPanel_agregar_compra); // NOI18N

        tabla_consultar_compras.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        tabla_consultar_compras.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_consultar_compras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_consultar_comprasMouseClicked(evt);
            }
        });
        tabla_consultar_compras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_consultar_comprasKeyPressed(evt);
            }
        });
        jScrollPane17.setViewportView(tabla_consultar_compras);

        jLabel47.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel47.setText("Historial de compras:");

        Jbtn_reporte_compras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/document-archive-icon.png"))); // NOI18N
        Jbtn_reporte_compras.setToolTipText("Gerar reporte de caja");
        Jbtn_reporte_compras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Jbtn_reporte_comprasMouseClicked(evt);
            }
        });
        Jbtn_reporte_compras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_reporte_comprasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout subPanel_consultar_comprasLayout = new javax.swing.GroupLayout(subPanel_consultar_compras);
        subPanel_consultar_compras.setLayout(subPanel_consultar_comprasLayout);
        subPanel_consultar_comprasLayout.setHorizontalGroup(
            subPanel_consultar_comprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subPanel_consultar_comprasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(subPanel_consultar_comprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47)
                    .addGroup(subPanel_consultar_comprasLayout.createSequentialGroup()
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 1025, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Jbtn_reporte_compras, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        subPanel_consultar_comprasLayout.setVerticalGroup(
            subPanel_consultar_comprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, subPanel_consultar_comprasLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(subPanel_consultar_comprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Jbtn_reporte_compras, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane6.addTab(" Historial de Compras ", new javax.swing.ImageIcon(getClass().getResource("/imagenes/MI-Scare-Report-icon.png")), subPanel_consultar_compras); // NOI18N

        javax.swing.GroupLayout panel_comprasLayout = new javax.swing.GroupLayout(panel_compras);
        panel_compras.setLayout(panel_comprasLayout);
        panel_comprasLayout.setHorizontalGroup(
            panel_comprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane6)
        );
        panel_comprasLayout.setVerticalGroup(
            panel_comprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_comprasLayout.createSequentialGroup()
                .addComponent(jTabbedPane6)
                .addContainerGap())
        );

        jTabbedPane1.addTab("COMPRAS ", new javax.swing.ImageIcon(getClass().getResource("/imagenes/shop-cart-icon.png")), panel_compras); // NOI18N

        panel_utilerias.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel_utilerias.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        panel_usuarios.setToolTipText("Administra los empleados");
        panel_usuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla_empleados.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        tabla_empleados.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_empleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_empleadosMouseClicked(evt);
            }
        });
        tabla_empleados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_empleadosKeyPressed(evt);
            }
        });
        jScrollPane7.setViewportView(tabla_empleados);

        panel_usuarios.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 102, 1140, 240));

        txt_nombre_empleado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txt_nombre_empleado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_nombre_empleadoFocusGained(evt);
            }
        });
        panel_usuarios.add(txt_nombre_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 163, -1));

        txt_apellidos_empleado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txt_apellidos_empleado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_apellidos_empleadoFocusGained(evt);
            }
        });
        panel_usuarios.add(txt_apellidos_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 241, -1));

        txt_tel_cel_empleado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txt_tel_cel_empleado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_tel_cel_empleadoFocusGained(evt);
            }
        });
        panel_usuarios.add(txt_tel_cel_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 144, -1));

        txt_tel_casa_empleado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txt_tel_casa_empleado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_tel_casa_empleadoFocusGained(evt);
            }
        });
        panel_usuarios.add(txt_tel_casa_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, 135, -1));

        txt_puesto_empleado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txt_puesto_empleado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_puesto_empleadoFocusGained(evt);
            }
        });
        panel_usuarios.add(txt_puesto_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, 142, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Nombre:");
        panel_usuarios.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Apellidos:");
        panel_usuarios.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Telefono celular:");
        panel_usuarios.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, -1));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Telefono casa:");
        panel_usuarios.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, -1, -1));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Puesto:");
        panel_usuarios.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, -1, -1));

        txt_contraseña_empleado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txt_contraseña_empleado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_contraseña_empleadoFocusGained(evt);
            }
        });
        panel_usuarios.add(txt_contraseña_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 40, 121, -1));

        txt_verificar_contraseña_empleado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txt_verificar_contraseña_empleado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_verificar_contraseña_empleadoFocusGained(evt);
            }
        });
        panel_usuarios.add(txt_verificar_contraseña_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 40, 143, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Contraseña:");
        panel_usuarios.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, -1, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Verificar contraseña:");
        panel_usuarios.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 20, -1, -1));

        Jbtn_agregar_empleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Actions-go-down-icon.png"))); // NOI18N
        Jbtn_agregar_empleado.setToolTipText("Agrega a la tabla");
        Jbtn_agregar_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_agregar_empleadoActionPerformed(evt);
            }
        });
        panel_usuarios.add(Jbtn_agregar_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 40, 28));

        Jbtn_quitar_empleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/table-remove-icon.png"))); // NOI18N
        Jbtn_quitar_empleado.setToolTipText("Quita elemento de la tabla");
        Jbtn_quitar_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_quitar_empleadoActionPerformed(evt);
            }
        });
        panel_usuarios.add(Jbtn_quitar_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 40, 28));

        Jbtn_editar_empleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit-icon.png"))); // NOI18N
        Jbtn_editar_empleado.setToolTipText("Editar elemento");
        Jbtn_editar_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_editar_empleadoActionPerformed(evt);
            }
        });
        panel_usuarios.add(Jbtn_editar_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 40, 28));

        Jtbn_guardar_empleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/database-check-icon.png"))); // NOI18N
        Jtbn_guardar_empleado.setToolTipText("Guardar");
        Jtbn_guardar_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jtbn_guardar_empleadoActionPerformed(evt);
            }
        });
        panel_usuarios.add(Jtbn_guardar_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 40, 28));

        Jbtn_nuevo_empleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Files-New-File-icon.png"))); // NOI18N
        Jbtn_nuevo_empleado.setToolTipText("Nuevo");
        Jbtn_nuevo_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_nuevo_empleadoActionPerformed(evt);
            }
        });
        panel_usuarios.add(Jbtn_nuevo_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 40, 28));

        Jbtn_cambiar_contraseña.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Sign-Refresh-icon.png"))); // NOI18N
        Jbtn_cambiar_contraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_cambiar_contraseñaActionPerformed(evt);
            }
        });
        panel_usuarios.add(Jbtn_cambiar_contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 70, 40, 28));

        Jbtn_asignar_contraseña.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pencil-icon.png"))); // NOI18N
        Jbtn_asignar_contraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_asignar_contraseñaActionPerformed(evt);
            }
        });
        panel_usuarios.add(Jbtn_asignar_contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 70, 42, 28));

        jLabel67.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel67.setText("USUARIOS");
        panel_usuarios.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 150, 20));

        jButton3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        jButton3.setText("Permisos ...");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        panel_usuarios.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 130, -1));

        jTbSystInfo.addTab(" Administrar Usuarios ", new javax.swing.ImageIcon(getClass().getResource("/imagenes/User-icon.png")), panel_usuarios); // NOI18N

        panel_datos_generales.setToolTipText("Datos generales");
        panel_datos_generales.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Jbtn_agregar_datos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Actions-go-down-icon.png"))); // NOI18N
        Jbtn_agregar_datos.setToolTipText("Agrega a la tabla");
        Jbtn_agregar_datos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_agregar_datosActionPerformed(evt);
            }
        });
        panel_datos_generales.add(Jbtn_agregar_datos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 28));

        Jbtn_editar_datos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit-icon.png"))); // NOI18N
        Jbtn_editar_datos.setToolTipText("Editar elemento");
        Jbtn_editar_datos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_editar_datosActionPerformed(evt);
            }
        });
        panel_datos_generales.add(Jbtn_editar_datos, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 0, 40, 28));

        Jtbn_guardar_datos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/database-check-icon.png"))); // NOI18N
        Jtbn_guardar_datos.setToolTipText("Guardar");
        Jtbn_guardar_datos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jtbn_guardar_datosActionPerformed(evt);
            }
        });
        panel_datos_generales.add(Jtbn_guardar_datos, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 0, 40, 28));

        txt_razon_social.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_razon_social.setToolTipText("");
        txt_razon_social.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_razon_socialFocusGained(evt);
            }
        });
        panel_datos_generales.add(txt_razon_social, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 194, -1));

        txt_calle.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_calle.setToolTipText("");
        txt_calle.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_calleFocusGained(evt);
            }
        });
        panel_datos_generales.add(txt_calle, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 50, 391, -1));

        txt_ciudad.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_ciudad.setToolTipText("");
        txt_ciudad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_ciudadFocusGained(evt);
            }
        });
        panel_datos_generales.add(txt_ciudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(976, 50, 162, -1));

        txt_estado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_estado.setToolTipText("");
        txt_estado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_estadoFocusGained(evt);
            }
        });
        panel_datos_generales.add(txt_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 96, 134, -1));

        txt_rfc.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_rfc.setToolTipText("");
        txt_rfc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_rfcFocusGained(evt);
            }
        });
        panel_datos_generales.add(txt_rfc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 96, 194, -1));

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Razon social:");
        panel_datos_generales.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 34, -1, -1));

        jLabel17.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel17.setText("Calle:");
        panel_datos_generales.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 34, -1, -1));

        jLabel18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel18.setText("Ciudad:");
        panel_datos_generales.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(976, 34, -1, -1));

        jLabel19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel19.setText("Estado:");
        panel_datos_generales.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 77, -1, -1));

        jLabel20.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel20.setText("RFC:");
        panel_datos_generales.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 77, -1, -1));

        txt_tel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_tel1.setToolTipText("");
        txt_tel1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_tel1FocusGained(evt);
            }
        });
        panel_datos_generales.add(txt_tel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 96, 152, -1));

        txt_tel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_tel2.setToolTipText("");
        txt_tel2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_tel2FocusGained(evt);
            }
        });
        panel_datos_generales.add(txt_tel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1004, 96, 144, -1));

        txt_tel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_tel3.setToolTipText("");
        txt_tel3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_tel3FocusGained(evt);
            }
        });
        panel_datos_generales.add(txt_tel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 140, 148, -1));

        jLabel21.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel21.setText("Telefono 1:");
        panel_datos_generales.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 79, -1, -1));

        jLabel22.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel22.setText("Telefono 2:");
        panel_datos_generales.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1004, 77, -1, -1));

        jLabel23.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel23.setText("Telefono 3:");
        panel_datos_generales.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 120, -1, -1));

        panel_leyendas.setBorder(javax.swing.BorderFactory.createTitledBorder("leyendas"));
        panel_leyendas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_leyenda5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_leyenda5FocusGained(evt);
            }
        });
        panel_leyendas.add(txt_leyenda5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 230, -1));

        jLabel35.setText("Nota 5:");
        panel_leyendas.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, 20));

        jLabel34.setText("Nota 4:");
        panel_leyendas.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, 20));

        jLabel33.setText("Nota 3:");
        panel_leyendas.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, 20));

        jLabel36.setText("Nota 2:");
        panel_leyendas.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 20));

        jLabel24.setText("Nota 1:");
        panel_leyendas.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 20));

        txt_leyenda1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_leyenda1FocusGained(evt);
            }
        });
        panel_leyendas.add(txt_leyenda1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 230, -1));

        txt_leyenda2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_leyenda2FocusGained(evt);
            }
        });
        panel_leyendas.add(txt_leyenda2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 230, -1));

        txt_leyenda3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_leyenda3FocusGained(evt);
            }
        });
        panel_leyendas.add(txt_leyenda3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 230, -1));

        txt_leyenda4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_leyenda4FocusGained(evt);
            }
        });
        panel_leyendas.add(txt_leyenda4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 230, -1));

        panel_datos_generales.add(panel_leyendas, new org.netbeans.lib.awtextra.AbsoluteConstraints(858, 170, 290, 180));

        jLabel25.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel25.setText("DATOS GENERALES DE LA EMPRESA");
        panel_datos_generales.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 11, 266, -1));

        Jbtn_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Image-GIF-icon.png"))); // NOI18N
        Jbtn_logo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_logoActionPerformed(evt);
            }
        });
        panel_datos_generales.add(Jbtn_logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 161, 53, -1));

        jLabel26.setText("LOGO:");
        panel_datos_generales.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 141, -1, -1));

        lbl_logo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel_datos_generales.add(lbl_logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 141, 194, 143));

        Jbtn_cambiar_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Sign-Refresh-icon.png"))); // NOI18N
        Jbtn_cambiar_logo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_cambiar_logoActionPerformed(evt);
            }
        });
        panel_datos_generales.add(Jbtn_cambiar_logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 213, 40, 22));

        Jbtn_actualizar_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Actions-dialog-ok-apply-icon.png"))); // NOI18N
        Jbtn_actualizar_logo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_actualizar_logoActionPerformed(evt);
            }
        });
        panel_datos_generales.add(Jbtn_actualizar_logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 213, 40, 22));

        txt_no_exterior.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_no_exteriorFocusGained(evt);
            }
        });
        panel_datos_generales.add(txt_no_exterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, 50, 100, -1));

        txt_no_interior.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_no_interiorFocusGained(evt);
            }
        });
        panel_datos_generales.add(txt_no_interior, new org.netbeans.lib.awtextra.AbsoluteConstraints(721, 51, 100, -1));

        jLabel49.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel49.setText("No. Interior");
        panel_datos_generales.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(721, 35, 100, -1));

        jLabel58.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel58.setText("No. Exterior");
        panel_datos_generales.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(614, 34, 101, -1));

        txt_colonia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_coloniaFocusGained(evt);
            }
        });
        panel_datos_generales.add(txt_colonia, new org.netbeans.lib.awtextra.AbsoluteConstraints(826, 51, 140, -1));

        jLabel59.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel59.setText("Colonia:");
        panel_datos_generales.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(826, 34, -1, -1));
        panel_datos_generales.add(txt_pais, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 97, 144, -1));

        jLabel60.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel60.setText("Pais:");
        panel_datos_generales.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 77, -1, -1));

        txt_codigo_postal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_codigo_postalFocusGained(evt);
            }
        });
        panel_datos_generales.add(txt_codigo_postal, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 97, 148, -1));

        jLabel61.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel61.setText("Codigo Postal:");
        panel_datos_generales.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 77, -1, -1));

        txt_referencia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_referenciaFocusGained(evt);
            }
        });
        panel_datos_generales.add(txt_referencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(662, 97, 168, -1));

        jLabel62.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel62.setText("Referencia:");
        panel_datos_generales.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(662, 81, -1, -1));

        txt_serie.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_serie.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_serieFocusGained(evt);
            }
        });
        panel_datos_generales.add(txt_serie, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 312, 42, -1));

        jLabel63.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel63.setText("Serie:");
        panel_datos_generales.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 291, 38, -1));

        jLabel64.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel64.setText("Folio inicial:");
        panel_datos_generales.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 291, -1, -1));

        jLabel65.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel65.setText("Folio final:");
        panel_datos_generales.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 291, -1, -1));

        jLabel66.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel66.setText("Folio actual:");
        panel_datos_generales.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 291, -1, -1));

        Jbtn_actualizar_series.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Actions-dialog-ok-apply-icon.png"))); // NOI18N
        Jbtn_actualizar_series.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_actualizar_seriesActionPerformed(evt);
            }
        });
        panel_datos_generales.add(Jbtn_actualizar_series, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 312, 44, 21));

        Jbtn_modificar_series.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Sign-Refresh-icon.png"))); // NOI18N
        Jbtn_modificar_series.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_modificar_seriesActionPerformed(evt);
            }
        });
        panel_datos_generales.add(Jbtn_modificar_series, new org.netbeans.lib.awtextra.AbsoluteConstraints(324, 312, 44, 21));

        txt_contraseña_series.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_contraseña_series.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_contraseña_seriesFocusGained(evt);
            }
        });
        panel_datos_generales.add(txt_contraseña_series, new org.netbeans.lib.awtextra.AbsoluteConstraints(374, 312, 124, -1));

        txt_folio_inicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txt_folio_inicial.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_folio_inicial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_folio_inicialFocusGained(evt);
            }
        });
        panel_datos_generales.add(txt_folio_inicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 312, 65, -1));

        txt_folio_actual.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txt_folio_actual.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_folio_actual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_folio_actualFocusGained(evt);
            }
        });
        panel_datos_generales.add(txt_folio_actual, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 312, 49, -1));

        txt_folio_final.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txt_folio_final.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_folio_final.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_folio_finalFocusGained(evt);
            }
        });
        panel_datos_generales.add(txt_folio_final, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 312, 67, -1));

        txt_impuesto_descripcion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_impuesto_descripcionFocusGained(evt);
            }
        });
        panel_datos_generales.add(txt_impuesto_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 310, 123, -1));

        txt_impuesto_tasa.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_impuesto_tasa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_impuesto_tasaFocusGained(evt);
            }
        });
        panel_datos_generales.add(txt_impuesto_tasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 310, 50, 30));

        jLabel86.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel86.setText("Impuesto:");
        panel_datos_generales.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 310, 60, 20));

        jLabel87.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel87.setText("Tasa:");
        panel_datos_generales.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 310, -1, 20));

        txt_lugar_expedicion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_lugar_expedicionFocusGained(evt);
            }
        });
        panel_datos_generales.add(txt_lugar_expedicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 320, -1));

        jLabel88.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel88.setText("Lugar de expedicion:");
        panel_datos_generales.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, -1, -1));

        txt_regimen_fiscal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_regimen_fiscalFocusGained(evt);
            }
        });
        panel_datos_generales.add(txt_regimen_fiscal, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 140, 350, -1));

        jLabel89.setText("Regimen fiscal:");
        panel_datos_generales.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 120, -1, -1));

        lbl_ruta_xml.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        panel_datos_generales.add(lbl_ruta_xml, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, 430, 20));

        lbl_ruta_entrada.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        panel_datos_generales.add(lbl_ruta_entrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, 430, 20));

        jbtn_mostrar_mesas.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        jbtn_mostrar_mesas.setText("Mostrar mesas y editar");
        jbtn_mostrar_mesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_mostrar_mesasActionPerformed(evt);
            }
        });
        panel_datos_generales.add(jbtn_mostrar_mesas, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 230, -1));

        jTbSystInfo.addTab(" Informacion General ", new javax.swing.ImageIcon(getClass().getResource("/imagenes/Actions-edit-paste-icon_1.png")), panel_datos_generales); // NOI18N

        subPanel_opciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Jbtn_editar_email.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit-icon.png"))); // NOI18N
        Jbtn_editar_email.setToolTipText("Editar");
        Jbtn_editar_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_editar_emailActionPerformed(evt);
            }
        });
        subPanel_opciones.add(Jbtn_editar_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 39, 31));

        Jbtn_guardar_email.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Save-icon.png"))); // NOI18N
        Jbtn_guardar_email.setToolTipText("Guardar");
        Jbtn_guardar_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_guardar_emailActionPerformed(evt);
            }
        });
        subPanel_opciones.add(Jbtn_guardar_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 40, 31));

        Jbtn_cancelar_email.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/File-Delete-icon.png"))); // NOI18N
        Jbtn_cancelar_email.setToolTipText("cancelar");
        Jbtn_cancelar_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_cancelar_emailActionPerformed(evt);
            }
        });
        subPanel_opciones.add(Jbtn_cancelar_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 39, 31));

        Jbtn_enviar_prueba_email.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Mail-icon.png"))); // NOI18N
        Jbtn_enviar_prueba_email.setToolTipText("Enviar prueba");
        Jbtn_enviar_prueba_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_enviar_prueba_emailActionPerformed(evt);
            }
        });
        subPanel_opciones.add(Jbtn_enviar_prueba_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 40, 30));

        buttonGroup3.add(check_personalizada);
        check_personalizada.setText("Personalizada");
        check_personalizada.setToolTipText("Indica si es otra tipo de cuenta");
        check_personalizada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_personalizadaActionPerformed(evt);
            }
        });

        buttonGroup3.add(check_hotmail);
        check_hotmail.setText("Hotmail");
        check_hotmail.setToolTipText("Indica si es hotmail");
        check_hotmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_hotmailActionPerformed(evt);
            }
        });

        buttonGroup3.add(check_gmail);
        check_gmail.setText("Gmail");
        check_gmail.setToolTipText("Indica si es gmail");
        check_gmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                check_gmailMouseClicked(evt);
            }
        });
        check_gmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_gmailActionPerformed(evt);
            }
        });

        txt_cuenta_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_cuenta_emailFocusGained(evt);
            }
        });

        jLabel93.setText("Cuenta de correo:");

        jLabel94.setText("Contraseña:");

        jLabel95.setText("Confirmar contraseña:");

        txt_servidor_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_servidor_emailFocusGained(evt);
            }
        });

        jLabel96.setText("Servidor:");

        jLabel97.setText("Puerto:");

        txt_puerto_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_puerto_emailFocusGained(evt);
            }
        });

        check_conexion_tls.setText("Utiliza conexion TLS");

        check_autenticacion.setText("Utiliza Autenticación");

        txt_contraseña_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_contraseña_emailFocusGained(evt);
            }
        });

        txt_verificar_contraseña_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_verificar_contraseña_emailFocusGained(evt);
            }
        });

        javax.swing.GroupLayout subPanel_configuracion_emailLayout = new javax.swing.GroupLayout(subPanel_configuracion_email);
        subPanel_configuracion_email.setLayout(subPanel_configuracion_emailLayout);
        subPanel_configuracion_emailLayout.setHorizontalGroup(
            subPanel_configuracion_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subPanel_configuracion_emailLayout.createSequentialGroup()
                .addGroup(subPanel_configuracion_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(subPanel_configuracion_emailLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(subPanel_configuracion_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(subPanel_configuracion_emailLayout.createSequentialGroup()
                                .addGroup(subPanel_configuracion_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_cuenta_email, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel93))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(subPanel_configuracion_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel94)
                                    .addComponent(txt_contraseña_email, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(subPanel_configuracion_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel95)
                                    .addComponent(txt_verificar_contraseña_email, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(subPanel_configuracion_emailLayout.createSequentialGroup()
                                .addGroup(subPanel_configuracion_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_servidor_email, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel96))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(subPanel_configuracion_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel97)
                                    .addComponent(txt_puerto_email, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(subPanel_configuracion_emailLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(check_conexion_tls)
                        .addGap(18, 18, 18)
                        .addComponent(check_autenticacion))
                    .addGroup(subPanel_configuracion_emailLayout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(check_gmail)
                        .addGap(18, 18, 18)
                        .addComponent(check_hotmail)
                        .addGap(18, 18, 18)
                        .addComponent(check_personalizada)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        subPanel_configuracion_emailLayout.setVerticalGroup(
            subPanel_configuracion_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subPanel_configuracion_emailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(subPanel_configuracion_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(check_gmail)
                    .addComponent(check_hotmail)
                    .addComponent(check_personalizada))
                .addGap(7, 7, 7)
                .addGroup(subPanel_configuracion_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(subPanel_configuracion_emailLayout.createSequentialGroup()
                        .addComponent(jLabel97)
                        .addGap(3, 3, 3)
                        .addComponent(txt_puerto_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(subPanel_configuracion_emailLayout.createSequentialGroup()
                        .addGroup(subPanel_configuracion_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel93)
                            .addComponent(jLabel94)
                            .addComponent(jLabel95))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(subPanel_configuracion_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_cuenta_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_contraseña_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_verificar_contraseña_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel96)
                        .addGap(3, 3, 3)
                        .addComponent(txt_servidor_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(subPanel_configuracion_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(check_conexion_tls)
                    .addComponent(check_autenticacion))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jLabel98.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel98.setText("Configurar cuenta de correo del emisor, para envios de correo a los clientes.");

        jLabel99.setText("Enviar a:");

        jLabel100.setText("Asunto:");

        jScrollPane21.setViewportView(txt_mensaje);

        jLabel101.setText("Mensaje:");

        Jbnt_enviar_prueba.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Actions-dialog-ok-apply-icon.png"))); // NOI18N
        Jbnt_enviar_prueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbnt_enviar_pruebaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_enviar_pruebaLayout = new javax.swing.GroupLayout(panel_enviar_prueba);
        panel_enviar_prueba.setLayout(panel_enviar_pruebaLayout);
        panel_enviar_pruebaLayout.setHorizontalGroup(
            panel_enviar_pruebaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_enviar_pruebaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_enviar_pruebaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_enviar_pruebaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Jbnt_enviar_prueba, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane21)
                    .addComponent(txt_enviar_a, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_asunto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_enviar_pruebaLayout.createSequentialGroup()
                        .addGroup(panel_enviar_pruebaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel99)
                            .addComponent(jLabel100)
                            .addComponent(jLabel101))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel_enviar_pruebaLayout.setVerticalGroup(
            panel_enviar_pruebaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_enviar_pruebaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel99)
                .addGap(3, 3, 3)
                .addComponent(txt_enviar_a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel100)
                .addGap(3, 3, 3)
                .addComponent(txt_asunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel101)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Jbnt_enviar_prueba, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout subPanel_emailLayout = new javax.swing.GroupLayout(subPanel_email);
        subPanel_email.setLayout(subPanel_emailLayout);
        subPanel_emailLayout.setHorizontalGroup(
            subPanel_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subPanel_emailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(subPanel_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel98)
                    .addGroup(subPanel_emailLayout.createSequentialGroup()
                        .addGroup(subPanel_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(subPanel_configuracion_email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(subPanel_opciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(panel_enviar_prueba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        subPanel_emailLayout.setVerticalGroup(
            subPanel_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subPanel_emailLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel98)
                .addGap(18, 18, 18)
                .addGroup(subPanel_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(subPanel_emailLayout.createSequentialGroup()
                        .addComponent(subPanel_configuracion_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subPanel_opciones, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panel_enviar_prueba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );

        jTbSystInfo.addTab(" Email ", new javax.swing.ImageIcon(getClass().getResource("/imagenes/Mail-icon.png")), subPanel_email); // NOI18N

        jLabel68.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel68.setText("CLIENTES");

        tabla_clientes.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
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
        });
        tabla_clientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_clientesKeyPressed(evt);
            }
        });
        jScrollPane18.setViewportView(tabla_clientes);

        Jbtn_nuevo_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Files-New-File-icon.png"))); // NOI18N
        Jbtn_nuevo_cliente.setToolTipText("Nuevo");
        Jbtn_nuevo_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_nuevo_clienteActionPerformed(evt);
            }
        });

        Jtbn_guardar_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/database-check-icon.png"))); // NOI18N
        Jtbn_guardar_cliente.setToolTipText("Guardar");
        Jtbn_guardar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jtbn_guardar_clienteActionPerformed(evt);
            }
        });

        Jbtn_editar_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit-icon.png"))); // NOI18N
        Jbtn_editar_editar.setToolTipText("Editar elemento");
        Jbtn_editar_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_editar_editarActionPerformed(evt);
            }
        });

        Jbtn_quitar_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/table-remove-icon.png"))); // NOI18N
        Jbtn_quitar_cliente.setToolTipText("Quita elemento de la tabla");
        Jbtn_quitar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_quitar_clienteActionPerformed(evt);
            }
        });

        Jbtn_agregar_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Actions-go-down-icon.png"))); // NOI18N
        Jbtn_agregar_cliente.setToolTipText("Agrega a la tabla");
        Jbtn_agregar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_agregar_clienteActionPerformed(evt);
            }
        });

        txt_nombre_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_nombre_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_nombre_clienteFocusGained(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel69.setText("Nombre:");

        jLabel70.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel70.setText("Apellidos:");

        txt_apellidos_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_apellidos_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_apellidos_clienteFocusGained(evt);
            }
        });

        jLabel71.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel71.setText("Tel movil:");

        txt_tel_movil_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_tel_movil_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tel_movil_clienteActionPerformed(evt);
            }
        });
        txt_tel_movil_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_tel_movil_clienteFocusGained(evt);
            }
        });

        jLabel72.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel72.setText("Tel fijo:");

        txt_tel_fijo_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_tel_fijo_cliente.setToolTipText("");
        txt_tel_fijo_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_tel_fijo_clienteFocusGained(evt);
            }
        });

        jLabel73.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel73.setText("RFC");

        txt_rfc_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_rfc_cliente.setToolTipText("");
        txt_rfc_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_rfc_clienteFocusGained(evt);
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

        txt_no_ext_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_no_ext_cliente.setToolTipText("");
        txt_no_ext_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_no_ext_clienteFocusGained(evt);
            }
        });

        jLabel77.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel77.setText("No Int:");

        txt_no_int_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_no_int_cliente.setToolTipText("");
        txt_no_int_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_no_int_clienteFocusGained(evt);
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

        txt_localidad_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_localidad_cliente.setToolTipText("");
        txt_localidad_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_localidad_clienteFocusGained(evt);
            }
        });

        jLabel80.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel80.setText("Localidad:");

        txt_delegacion_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_delegacion_cliente.setToolTipText("");
        txt_delegacion_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_delegacion_clienteFocusGained(evt);
            }
        });

        jLabel81.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel81.setText("Delegacion:");

        txt_estado_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_estado_cliente.setToolTipText("");
        txt_estado_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_estado_clienteFocusGained(evt);
            }
        });

        jLabel82.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel82.setText("Estado:");

        txt_pais_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_pais_cliente.setToolTipText("");
        txt_pais_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_pais_clienteFocusGained(evt);
            }
        });

        jLabel83.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel83.setText("Pais:");

        txt_email_cliente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txt_email_cliente.setToolTipText("");
        txt_email_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_email_clienteFocusGained(evt);
            }
        });

        jLabel84.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel84.setText("Email");

        txt_filtro_clientes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_filtro_clientesFocusGained(evt);
            }
        });
        txt_filtro_clientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_filtro_clientesKeyReleased(evt);
            }
        });

        jLabel85.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search-icon.png"))); // NOI18N

        buttonGroup2.add(check_nombre_cliente);
        check_nombre_cliente.setText("Nombre");

        buttonGroup2.add(check_apellidos_cliente);
        check_apellidos_cliente.setText("Apellidos");

        txt_cp_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_cp_clienteFocusGained(evt);
            }
        });

        javax.swing.GroupLayout panel_clientesLayout = new javax.swing.GroupLayout(panel_clientes);
        panel_clientes.setLayout(panel_clientesLayout);
        panel_clientesLayout.setHorizontalGroup(
            panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_clientesLayout.createSequentialGroup()
                .addGroup(panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_clientesLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(Jbtn_agregar_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(Jbtn_quitar_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(Jbtn_editar_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(Jtbn_guardar_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(Jbtn_nuevo_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addGroup(panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel76)
                            .addComponent(txt_no_ext_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel77)
                            .addComponent(txt_no_int_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel78)
                            .addComponent(txt_colonia_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel79)
                            .addComponent(txt_cp_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel80)
                            .addComponent(txt_localidad_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel81)
                            .addComponent(txt_delegacion_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel82)
                            .addComponent(txt_estado_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_pais_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_clientesLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel83)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel84)
                            .addComponent(txt_email_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel_clientesLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 1131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_clientesLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel69)
                            .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel70)
                        .addGap(140, 140, 140)
                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel72)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel73)
                        .addGap(106, 106, 106)
                        .addComponent(jLabel74)
                        .addGap(223, 223, 223)
                        .addComponent(jLabel75))
                    .addGroup(panel_clientesLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txt_nombre_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_clientesLayout.createSequentialGroup()
                                .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txt_filtro_clientes, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(check_nombre_cliente)
                                .addGap(2, 2, 2)
                                .addComponent(check_apellidos_cliente))
                            .addGroup(panel_clientesLayout.createSequentialGroup()
                                .addComponent(txt_apellidos_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(txt_tel_movil_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(txt_tel_fijo_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(txt_rfc_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(txt_razon_social_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(txt_calle_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        panel_clientesLayout.setVerticalGroup(
            panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_clientesLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_clientesLayout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panel_clientesLayout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(txt_filtro_clientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(check_nombre_cliente)
                        .addComponent(check_apellidos_cliente)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel69)
                    .addComponent(jLabel70)
                    .addComponent(jLabel71)
                    .addComponent(jLabel72)
                    .addComponent(jLabel73)
                    .addComponent(jLabel74)
                    .addComponent(jLabel75))
                .addGap(6, 6, 6)
                .addGroup(panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_nombre_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_apellidos_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tel_movil_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tel_fijo_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_rfc_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_razon_social_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_calle_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_clientesLayout.createSequentialGroup()
                        .addComponent(jLabel76)
                        .addGap(6, 6, 6)
                        .addComponent(txt_no_ext_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_clientesLayout.createSequentialGroup()
                        .addComponent(jLabel77)
                        .addGap(6, 6, 6)
                        .addComponent(txt_no_int_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_clientesLayout.createSequentialGroup()
                        .addComponent(jLabel78)
                        .addGap(6, 6, 6)
                        .addComponent(txt_colonia_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_clientesLayout.createSequentialGroup()
                        .addComponent(jLabel79)
                        .addGap(6, 6, 6)
                        .addComponent(txt_cp_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_clientesLayout.createSequentialGroup()
                        .addComponent(jLabel80)
                        .addGap(6, 6, 6)
                        .addComponent(txt_localidad_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_clientesLayout.createSequentialGroup()
                        .addComponent(jLabel81)
                        .addGap(6, 6, 6)
                        .addComponent(txt_delegacion_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_clientesLayout.createSequentialGroup()
                        .addGroup(panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel82)
                            .addComponent(jLabel84))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_estado_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_pais_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_email_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel83)
                    .addGroup(panel_clientesLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Jbtn_agregar_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Jbtn_quitar_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Jbtn_editar_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Jtbn_guardar_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Jbtn_nuevo_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTbSystInfo.addTab(" Clientes ", new javax.swing.ImageIcon(getClass().getResource("/imagenes/Apps-system-users-icon.png")), panel_clientes); // NOI18N

        jLabel45.setText("Ultima actualizacion: 29 Enero 2018 :::: Se agrega ventana emergente para buscar elementos y agregar al pedido de la mesa asi como tambien codigo para agregar de manera mas rapida");

        jLabel92.setText("Contacto: Gerardo Torreblanca Luna :::: Email: gtorreblancaluna@email.com");

        jLabel112.setText("Objetivos: Satisfacer las necesidades de los chilpancingeños que tienen como giro un restaurante o alguna actividad a fin");

        jLabel113.setText("<html><body><p>Ventajas: Administrar tu negocio, controlar pedidos, inventario, recetas, balance de dinero, rapidez para las cuentas de tus mesas.</p></br><p>Ademas el sistema es adaptable y administrable. Si lo requieres se pueden hacer ajustes tecnicos para que puedas personalizarlo</p></body></html>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 986, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, 1130, Short.MAX_VALUE)
                        .addComponent(jLabel92, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel92)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel112)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(183, Short.MAX_VALUE))
        );

        jTbSystInfo.addTab("Informacion sistema", jPanel1);

        javax.swing.GroupLayout panel_utileriasLayout = new javax.swing.GroupLayout(panel_utilerias);
        panel_utilerias.setLayout(panel_utileriasLayout);
        panel_utileriasLayout.setHorizontalGroup(
            panel_utileriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_utileriasLayout.createSequentialGroup()
                .addComponent(jTbSystInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 1164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );
        panel_utileriasLayout.setVerticalGroup(
            panel_utileriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTbSystInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 398, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("UTILERIAS ", new javax.swing.ImageIcon(getClass().getResource("/imagenes/settings_48Pxs.png")), panel_utilerias); // NOI18N

        panel_cfdi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        sub_panel_consultar_cfdi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla_pdf.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        tabla_pdf.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_pdf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_pdfMouseClicked(evt);
            }
        });
        tabla_pdf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_pdfKeyPressed(evt);
            }
        });
        jScrollPane19.setViewportView(tabla_pdf);

        sub_panel_consultar_cfdi.add(jScrollPane19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 510, 310));

        tabla_xml.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        tabla_xml.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_xml.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_xmlMouseClicked(evt);
            }
        });
        tabla_xml.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_xmlKeyPressed(evt);
            }
        });
        jScrollPane20.setViewportView(tabla_xml);

        sub_panel_consultar_cfdi.add(jScrollPane20, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, 510, 310));

        jLabel90.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel90.setText("PDF");
        sub_panel_consultar_cfdi.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLabel91.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel91.setText("XML");
        sub_panel_consultar_cfdi.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, -1, -1));

        Jbtn_abrir_xml.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/File-Adobe-Dreamweaver-XML-01-icon.png"))); // NOI18N
        Jbtn_abrir_xml.setToolTipText("Abrir XML");
        Jbtn_abrir_xml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_abrir_xmlActionPerformed(evt);
            }
        });
        sub_panel_consultar_cfdi.add(Jbtn_abrir_xml, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 30, 40, 40));

        Jbtn_abrir_pdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Adobe-PDF-Document-icon.png"))); // NOI18N
        Jbtn_abrir_pdf.setToolTipText("Abrir PDF");
        Jbtn_abrir_pdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_abrir_pdfActionPerformed(evt);
            }
        });
        sub_panel_consultar_cfdi.add(Jbtn_abrir_pdf, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, 40, 40));

        Jbtn_actualizar_pdf_xml.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reload-icon.png"))); // NOI18N
        Jbtn_actualizar_pdf_xml.setToolTipText("Actualizar");
        Jbtn_actualizar_pdf_xml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_actualizar_pdf_xmlActionPerformed(evt);
            }
        });
        sub_panel_consultar_cfdi.add(Jbtn_actualizar_pdf_xml, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 298, 50, 40));

        Jbtn_enviar_xml.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Mail-icon.png"))); // NOI18N
        Jbtn_enviar_xml.setToolTipText("Abrir PDF");
        Jbtn_enviar_xml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_enviar_xmlActionPerformed(evt);
            }
        });
        sub_panel_consultar_cfdi.add(Jbtn_enviar_xml, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 80, 40, 40));

        Jbtn_enviar_pdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Mail-icon.png"))); // NOI18N
        Jbtn_enviar_pdf.setToolTipText("Abrir PDF");
        Jbtn_enviar_pdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_enviar_pdfActionPerformed(evt);
            }
        });
        sub_panel_consultar_cfdi.add(Jbtn_enviar_pdf, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, 40, 40));

        jTabbedPane7.addTab("", new javax.swing.ImageIcon(getClass().getResource("/imagenes/check-icon.png")), sub_panel_consultar_cfdi); // NOI18N

        javax.swing.GroupLayout panel_cfdiLayout = new javax.swing.GroupLayout(panel_cfdi);
        panel_cfdi.setLayout(panel_cfdiLayout);
        panel_cfdiLayout.setHorizontalGroup(
            panel_cfdiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane7)
        );
        panel_cfdiLayout.setVerticalGroup(
            panel_cfdiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("CFDI ", new javax.swing.ImageIcon(getClass().getResource("/imagenes/checks-icon.png")), panel_cfdi); // NOI18N

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 1180, 460));

        jLabel28.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel28.setText("Logueo:");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 660, 50, 30));

        lbl_nombre_logueo.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        getContentPane().add(lbl_nombre_logueo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 662, 188, 30));

        jLabel30.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel30.setText("Cargo:");
        getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 660, -1, 22));

        lbl_cargo_logueo.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        getContentPane().add(lbl_cargo_logueo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 662, 110, 20));

        panel_teclado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panel_tecladoLayout = new javax.swing.GroupLayout(panel_teclado);
        panel_teclado.setLayout(panel_tecladoLayout);
        panel_tecladoLayout.setHorizontalGroup(
            panel_tecladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 678, Short.MAX_VALUE)
        );
        panel_tecladoLayout.setVerticalGroup(
            panel_tecladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 188, Short.MAX_VALUE)
        );

        getContentPane().add(panel_teclado, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 474, 680, 190));
        getContentPane().add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 530, -1, -1));

        jLabel102.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel102.setText("Atiende mesa actual:");
        getContentPane().add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 660, -1, 22));

        lbl_nombre_atiende.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        getContentPane().add(lbl_nombre_atiende, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 662, 180, 20));

        jLabel103.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel103.setText("Cargo:");
        getContentPane().add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 660, -1, 22));

        lbl_cargo_atiende.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        getContentPane().add(lbl_cargo_atiende, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 660, 100, 22));

        panel_totales.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_total_pagar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        txt_total_pagar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_total_pagar.setText("00.00");
        txt_total_pagar.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_total_pagar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_total_pagar.setOpaque(false);
        txt_total_pagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_total_pagarActionPerformed(evt);
            }
        });
        panel_totales.add(txt_total_pagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 130, 30));

        jLabel104.setText("TOTAL:");
        panel_totales.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 40, 30));

        jLabel105.setText("CARGO:");
        panel_totales.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, -1, 30));

        jLabel106.setText("DESCUENTO:");
        panel_totales.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, -1, 30));

        txt_total.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        txt_total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_total.setText("00.00");
        txt_total.setToolTipText("Total a pagar");
        txt_total.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_total.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_total.setOpaque(false);
        txt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalActionPerformed(evt);
            }
        });
        panel_totales.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 130, 30));

        jLabel107.setText("SUBTOTAL:");
        panel_totales.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, -1, 30));

        txt_aplicar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Actions-dialog-ok-apply-icon.png"))); // NOI18N
        txt_aplicar.setToolTipText("Calcular y guardar");
        txt_aplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_aplicarActionPerformed(evt);
            }
        });
        panel_totales.add(txt_aplicar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 40, 30));

        txt_cargo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txt_cargo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_cargo.setToolTipText("Cargo extra a la cuenta");
        txt_cargo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_cargo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_cargoFocusGained(evt);
            }
        });
        txt_cargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cargoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cargoKeyReleased(evt);
            }
        });
        panel_totales.add(txt_cargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 130, 30));

        txt_descuento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txt_descuento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_descuento.setToolTipText("Descuento a la cuenta");
        txt_descuento.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_descuento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_descuentoFocusGained(evt);
            }
        });
        txt_descuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_descuentoActionPerformed(evt);
            }
        });
        txt_descuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_descuentoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_descuentoKeyReleased(evt);
            }
        });
        panel_totales.add(txt_descuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 130, 30));

        jLabel41.setText("Propina %");
        panel_totales.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, -1, 30));

        txt_propina.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txt_propina.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_propina.setToolTipText("Porcentaje de propina al mesero");
        txt_propina.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txt_propina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_propinaActionPerformed(evt);
            }
        });
        txt_propina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_propinaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_propinaKeyReleased(evt);
            }
        });
        panel_totales.add(txt_propina, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 40, 30));

        txt_total_propina.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        txt_total_propina.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_total_propina.setText("00.00");
        txt_total_propina.setToolTipText("Calculo de la propina");
        txt_total_propina.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_total_propina.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_total_propina.setOpaque(false);
        txt_total_propina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_total_propinaActionPerformed(evt);
            }
        });
        panel_totales.add(txt_total_propina, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, 90, 130, 30));

        txt_dinero_recibido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txt_dinero_recibido.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_dinero_recibido.setToolTipText("Indica el dinero recibido");
        txt_dinero_recibido.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_dinero_recibido.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_dinero_recibido.setOpaque(false);
        txt_dinero_recibido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dinero_recibidoActionPerformed(evt);
            }
        });
        txt_dinero_recibido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_dinero_recibidoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_dinero_recibidoKeyReleased(evt);
            }
        });
        panel_totales.add(txt_dinero_recibido, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 60, 30));

        txt_cambio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        txt_cambio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_cambio.setText("00.00");
        txt_cambio.setToolTipText("Total a pagar");
        txt_cambio.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_cambio.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        txt_cambio.setOpaque(false);
        txt_cambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cambioActionPerformed(evt);
            }
        });
        panel_totales.add(txt_cambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 130, 30));

        jLabel108.setText("Dinero recibido:");
        panel_totales.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 100, 30));

        jLabel109.setText("CAMBIO:");
        panel_totales.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 50, 30));

        getContentPane().add(panel_totales, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 480, 380, 180));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Jbtn_agregar_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_agregar_categoriaActionPerformed
        // TODO add your handling code here:        
        if (this.txt_agregar_categoria.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar texto para continuar", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String datos[] = {this.txt_agregar_categoria.getText().toString(), fecha_sistema, iniciar_sesion.id_usuario_global};
            //funcion.InsertarRegistro(datos2, "insert into renta (id_clientes,id_usuarios,id_estado_pedido,renta_fecha_contrato,renta_fecha_entrega,renta_fecha_devolucion,renta_descripcion,renta_hora_entrega) values(?,?,?,?,?,?,?,?)");
            // //  funcion.conectate();
            funcion.InsertarRegistro(datos, "insert into categorias (nombre_categoria,fecha_alta,empleado_id) values(?,?,?)");
            // funcion.desconecta();
            tablaCategorias();
            this.txt_agregar_categoria.setText("");
            this.txt_agregar_categoria.requestFocus();
            general_itemDB.llenar();
            addMainMenue();
        }
    }//GEN-LAST:event_Jbtn_agregar_categoriaActionPerformed

    private void tabla_categoriasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_categoriasKeyPressed
        // TODO add your handling code here:


    }//GEN-LAST:event_tabla_categoriasKeyPressed

    private void Jbtn_agregar_detalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_agregar_detalleActionPerformed
       agregar_detalle_categorias();
    }//GEN-LAST:event_Jbtn_agregar_detalleActionPerformed

    private void txt_precioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_precioActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_precioActionPerformed

    private void txt_precioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precioKeyPressed
        // TODO add your handling code here:
        if (nuevo == true) {
            if (evt.getKeyCode() == 10) {

                agregar_detalle_categorias();

            }
            if (evt.getKeyCode() == 20) {
                JOptionPane.showMessageDialog(this, "Bloq-Mayus Activada.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_txt_precioKeyPressed

    private void tabla_categoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_categoriasMouseClicked
        // TODO add your handling code here:
        fila_seleccionada_tabla_categorias = tabla_categorias.getSelectedRow();
        id_categorias_seleccion = (String) tabla_categorias.getValueAt(fila_seleccionada_tabla_categorias, 0);
        System.out.println("id categoria: " + id_categorias_seleccion);
        tablaDetalleCategorias();
        tabla_categorias_seleccion = true;
        tabla_detalle_seleccion = false;
        this.txt_descripcion.setText("");
        this.txt_precio.setText("");
        // //  funcion.conectate();
        funcion.setEnableContainer(this.panel_detalle, true);
        // funcion.desconecta();
    }//GEN-LAST:event_tabla_categoriasMouseClicked

    private void txt_agregar_categoriaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_agregar_categoriaKeyPressed
        if (nuevo = true) {
            if (evt.getKeyCode() == 10) {
                if (this.txt_agregar_categoria.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar texto para continuar", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String datos[] = {this.txt_agregar_categoria.getText().toString(), fecha_sistema, "1"};
                    //funcion.InsertarRegistro(datos2, "insert into renta (id_clientes,id_usuarios,id_estado_pedido,renta_fecha_contrato,renta_fecha_entrega,renta_fecha_devolucion,renta_descripcion,renta_hora_entrega) values(?,?,?,?,?,?,?,?)");
                    // //  funcion.conectate();
                    funcion.InsertarRegistro(datos, "insert into categorias (nombre_categoria,fecha_alta,empleado_id) values(?,?,?)");
                    // funcion.desconecta();
                    tablaCategorias();
                    this.txt_agregar_categoria.setText("");
                    this.txt_agregar_categoria.requestFocus();

                }

            }

        }


    }//GEN-LAST:event_txt_agregar_categoriaKeyPressed

    private void tabla_detalle_categoriasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_detalle_categoriasKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tabla_detalle_categoriasKeyPressed

    private void tabla_insumosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_insumosKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tabla_insumosKeyPressed

    private void Jbtn_editar_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_editar_categoriaActionPerformed
        // TODO add your handling code here:
        if (tabla_categorias_seleccion == true) {
            Jbtn_agregar_categoria.setEnabled(false);
            int sel = tabla_categorias.getSelectedRow();
            id_categorias_seleccion = (String) tabla_categorias.getValueAt(sel, 0);
            this.txt_agregar_categoria.setText((String) tabla_categorias.getValueAt(sel, 1).toString());
            this.txt_agregar_categoria.requestFocus();
            Jtbn_guardar_categoria.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Seleeciona un registro para editar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_Jbtn_editar_categoriaActionPerformed

    private void Jtbn_guardar_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jtbn_guardar_categoriaActionPerformed
        // TODO add your handling code here:

        if (this.txt_agregar_categoria.equals("")) {
            JOptionPane.showMessageDialog(this, "No puede estar vacio", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            // //  funcion.conectate();
            String datos[] = {this.txt_agregar_categoria.getText().toString(), id_categorias_seleccion};
            funcion.UpdateRegistro(datos, "update categorias set nombre_categoria=? where id_categorias=?");
            JOptionPane.showMessageDialog(null, "Actualizacion exitosa");
            tablaCategorias();
            // funcion.desconecta();

            tabla_categorias_seleccion = false;
            Jtbn_guardar_categoria.setEnabled(false);
            this.txt_agregar_categoria.setText("");
            Jbtn_agregar_categoria.setEnabled(true);

            general_itemDB.llenar();
            addMainMenue();

        }
    }//GEN-LAST:event_Jtbn_guardar_categoriaActionPerformed

    private void tabla_detalle_categoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_detalle_categoriasMouseClicked
        // TODO add your handling code here:
        fila_seleccionada_tabla_detalle = tabla_detalle_categorias.getSelectedRow();
        id_detalle_seleccion = (String) tabla_detalle_categorias.getValueAt(fila_seleccionada_tabla_detalle, 0);
        tabla_detalle_seleccion = true;
    }//GEN-LAST:event_tabla_detalle_categoriasMouseClicked

    private void tabla_insumosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_insumosMouseClicked
        // TODO add your handling code here:
        if (this.tabla_insumos.getRowCount() > 0) {
            tabla_insumos_seleccion = true;
            fila_seleccionada_tabla_insumos = tabla_insumos.getSelectedRow();
            id_insumos_seleccion = (String) tabla_insumos.getValueAt(fila_seleccionada_tabla_insumos, 0);
            System.out.println("si entra");
        } else {
            System.out.println("no entra");
        }
    }//GEN-LAST:event_tabla_insumosMouseClicked

    private void Jbtn_quitar_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_quitar_categoriaActionPerformed
        // TODO add your handling code here:
       /* if(tabla_categorias_seleccion == true){
            
         String dato_eliminar = (String) this.tabla_categorias.getValueAt(fila_seleccionada_tabla_categorias, 1).toString();              
         int seleccion = JOptionPane.showOptionDialog(this, "¿Eliminar registro?  " + dato_eliminar, "Confirme eliminacion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Si", "No"}, "Si");
         if ((seleccion + 1) == 1) {
                    
         funcion.DeleteRegistro("categorias", "id_categorias", id_categorias_seleccion);
         tablaCategorias();
         tabla_categorias_seleccion = false;
                    
        
         }
         }*/

    }//GEN-LAST:event_Jbtn_quitar_categoriaActionPerformed

    private void Jbtn_nuevo_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_nuevo_categoriaActionPerformed
        // TODO add your handling code here:
        this.txt_agregar_categoria.setText("");
        this.txt_agregar_categoria.requestFocus();
        tabla_categorias_seleccion = false;
    }//GEN-LAST:event_Jbtn_nuevo_categoriaActionPerformed

    private void Jbtn_editar_detalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_editar_detalleActionPerformed
        // TODO add your handling code here:
        if (tabla_detalle_seleccion == true) {

            this.txt_descripcion.setText((String) tabla_detalle_categorias.getValueAt(fila_seleccionada_tabla_detalle, 1).toString());
            this.txt_precio.setText((String) tabla_detalle_categorias.getValueAt(fila_seleccionada_tabla_detalle, 2).toString());

            this.txt_descripcion.requestFocus();
            this.Jtbn_guardar_detalle.setEnabled(true);
            Jbtn_agregar_detalle.setEnabled(false);
            nuevo = false;

        } else {
            JOptionPane.showMessageDialog(this, "Seleeciona un registro para editar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_Jbtn_editar_detalleActionPerformed

    private void Jtbn_guardar_detalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jtbn_guardar_detalleActionPerformed
        // TODO add your handling code here:
        actualizar_detalle_categorias();

    }//GEN-LAST:event_Jtbn_guardar_detalleActionPerformed

    private void Jbtn_quitar_detalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_quitar_detalleActionPerformed
        // TODO add your handling code here:
        if (tabla_detalle_seleccion == true) {
            String dato_eliminar = (String) this.tabla_detalle_categorias.getValueAt(fila_seleccionada_tabla_detalle, 1).toString();
            int seleccion = JOptionPane.showOptionDialog(this, "¿Eliminar registro?  " + dato_eliminar, "Confirme eliminacion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Si", "No"}, "Si");
            if ((seleccion + 1) == 1) {
                // //  funcion.conectate();

                String datos[] = {"0", id_detalle_seleccion};
                funcion.UpdateRegistro(datos, "update detalle_categorias set activo=? where id_detalle_categorias=?");

                tablaDetalleCategorias();

                tabla_detalle_seleccion = false;

                // funcion.desconecta();

            }
        }
    }//GEN-LAST:event_Jbtn_quitar_detalleActionPerformed

    private void Jbtn_nuevo_detalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_nuevo_detalleActionPerformed
        // TODO add your handling code here:
        tabla_detalle_seleccion = false;
        this.txt_descripcion.setText("");
        this.txt_precio.setText("");
        this.txt_descripcion.requestFocus();
        nuevo = true;

    }//GEN-LAST:event_Jbtn_nuevo_detalleActionPerformed

    private void Jbtn_agregar_insumosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_agregar_insumosActionPerformed
        // TODO add your handling code here:
        if (this.txt_nombre_insumo.getText().equals("") || (this.txt_ultimo_costo.getText().equals("")) || (this.txt_unidad_medida.getText().equals("")) || (this.txt_stock.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "Faltan parametros", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String datos[] = {this.txt_nombre_insumo.getText().toString(), this.txt_unidad_medida.getText().toString(), this.txt_ultimo_costo.getText().toString(), this.txt_stock.getText().toString(), "1"};
            // //  funcion.conectate();
            funcion.InsertarRegistro(datos, "insert into insumos (nombre_insumo,unidad_medida,ultimo_costo,stock,activo) values(?,?,?,?,?)");
            // funcion.desconecta();
            tablaInsumos();
            tablaInsumos_compras();

            tabla_insumos_seleccion = false;
            Jtbn_guardar_insumos.setEnabled(false);
            this.txt_nombre_insumo.setText("");
            this.txt_unidad_medida.setText("");
            this.txt_ultimo_costo.setText("");
            this.txt_stock.setText("");
            this.txt_nombre_insumo.requestFocus();

        }
    }//GEN-LAST:event_Jbtn_agregar_insumosActionPerformed

    private void Jbtn_editar_insumosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_editar_insumosActionPerformed
        // TODO add your handling code here:
        if (tabla_insumos_seleccion == true) {
            //String[] columNames = {"Id", "Nombre","Stock","U.M.","Costo"};
            this.txt_nombre_insumo.setText((String) tabla_insumos.getValueAt(fila_seleccionada_tabla_insumos, 1).toString());
            this.txt_stock.setText((String) tabla_insumos.getValueAt(fila_seleccionada_tabla_insumos, 2).toString());
            this.txt_unidad_medida.setText((String) tabla_insumos.getValueAt(fila_seleccionada_tabla_insumos, 3).toString());
            txt_ultimo_costo.setText((String) tabla_insumos.getValueAt(fila_seleccionada_tabla_insumos, 4).toString());

            Jtbn_guardar_insumos.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Seleeciona un registro para editar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_Jbtn_editar_insumosActionPerformed

    private void Jtbn_guardar_insumosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jtbn_guardar_insumosActionPerformed
        // TODO add your handling code here:
        if (this.txt_nombre_insumo.getText().equals("") || (this.txt_ultimo_costo.getText().equals("")) || (this.txt_unidad_medida.getText().equals("")) || (this.txt_stock.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "Faltan parametros", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {

            if (tabla_insumos_seleccion == false) {
                JOptionPane.showMessageDialog(this, "Selecciona un registro de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                // //  funcion.conectate();
                String datos[] = {this.txt_nombre_insumo.getText().toString(), this.txt_unidad_medida.getText().toString(), this.txt_ultimo_costo.getText().toString(), (EliminaCaracteres(txt_stock.getText().toString(), "$,")), id_insumos_seleccion};
                funcion.UpdateRegistro(datos, "update insumos set nombre_insumo=?,unidad_medida=?,ultimo_costo=?,stock=? where id_insumos=?");
                JOptionPane.showMessageDialog(null, "Actualizacion exitosa");
                tablaInsumos();
                tablaInsumos_compras();
                // funcion.desconecta();

                tabla_insumos_seleccion = false;
                Jtbn_guardar_insumos.setEnabled(false);
                this.txt_nombre_insumo.setText("");
                this.txt_unidad_medida.setText("");
                this.txt_ultimo_costo.setText("");
                this.txt_stock.setText("");
            }
        }
    }//GEN-LAST:event_Jtbn_guardar_insumosActionPerformed

    private void Jbtn_quitar_insumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_quitar_insumoActionPerformed
        // TODO add your handling code here:
        if (tabla_insumos_seleccion == true) {
            String dato_eliminar = (String) this.tabla_insumos.getValueAt(fila_seleccionada_tabla_insumos, 1).toString();
            int seleccion = JOptionPane.showOptionDialog(this, "¿Eliminar registro?  " + dato_eliminar, "Confirme eliminacion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Si", "No"}, "Si");
            if ((seleccion + 1) == 1) {
                // //  funcion.conectate();

                /*
                
                 String datos[] = {"0", id_detalle_seleccion};
                 funcion.UpdateRegistro(datos, "update detalle_categorias set activo=? where id_detalle_categorias=?");
                 */
                if (!funcion.existe("consumibles_platillos", "id_insumos", (String) tabla_insumos.getValueAt(tabla_insumos.getSelectedRow(), 0))) {
                    String datos[] = {"0", (String) tabla_insumos.getValueAt(tabla_insumos.getSelectedRow(), 0)};

                    funcion.UpdateRegistro(datos, "update insumos set activo=? where id_insumos=?");
                    tablaInsumos();
                    tablaInsumos_compras();
                    tabla_insumos_seleccion = false;
                    // funcion.desconecta();
                } else {
                    Object[][] res_aux;
                    String[] colName = {"id_consumibles_platillos", "descripcion", "id_insumos"};
                    res_aux = funcion.GetTabla(colName, "consumibles_platillos", "SELECT c.`id_consumibles_platillos`, d.`descripcion`,c.`id_insumos` FROM consumibles_platillos c, detalle_categorias d\n"
                            + "WHERE c.id_detalle_cat=d.id_detalle_categorias and c.`id_insumos`= '" + (String) tabla_insumos.getValueAt(tabla_insumos.getSelectedRow(), 0) + "' ");
                    String resultados = "";
                    for (int i = 0; i < res_aux.length; i++) {
                        if (i == 0) {
                            resultados = resultados + String.valueOf(res_aux[i][1]).toString();
                        } else {
                            resultados = resultados + ", " + String.valueOf(res_aux[i][1]).toString();
                        }
                    }

                    JOptionPane.showMessageDialog(null, "Error, quita las relaciones con este insumo para continuar -> : " + resultados, "Error", JOptionPane.INFORMATION_MESSAGE);
                    // funcion.desconecta();

                }

            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila", "Error", JOptionPane.INFORMATION_MESSAGE);

        }
    }//GEN-LAST:event_Jbtn_quitar_insumoActionPerformed

    private void Jbtn_nuevo_insumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_nuevo_insumoActionPerformed
        // TODO add your handling code here:
        this.txt_nombre_insumo.setText("");
        this.txt_unidad_medida.setText("");
        this.txt_ultimo_costo.setText("");
        this.txt_stock.setText("");
        tabla_insumos_seleccion = false;
        this.txt_nombre_insumo.requestFocus();
    }//GEN-LAST:event_Jbtn_nuevo_insumoActionPerformed

    private void tabla_detalle_categorias2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_detalle_categorias2MouseClicked
        // TODO add your handling code here:
        sel_categorias = tabla_detalle_categorias2.getSelectedRow();
        id_detalle_categorias = (String) tabla_detalle_categorias2.getValueAt(sel_categorias, 0);
        String nombre_platillo = (String) tabla_detalle_categorias2.getValueAt(sel_categorias, 1);
        lbl_nombre_platillo.setText(nombre_platillo);
        tabla1 = true;

        tablaConsumibles();

    }//GEN-LAST:event_tabla_detalle_categorias2MouseClicked

    private void tabla_detalle_categorias2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_detalle_categorias2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_detalle_categorias2KeyPressed

    private void combo_categoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_categoriasMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_combo_categoriasMouseClicked

    private void combo_categoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_categoriasActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_combo_categoriasActionPerformed

    private void combo_categoriasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_categoriasMousePressed
        // TODO add your handling code here:


    }//GEN-LAST:event_combo_categoriasMousePressed

    private void combo_categoriasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_categoriasMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_combo_categoriasMouseEntered

    private void combo_categoriasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_categoriasItemStateChanged
        // TODO add your handling code here:
        try {
            tabla1 = false;
            // //  funcion.conectate();
            String dato = this.combo_categorias.getSelectedItem().toString();
            String id_categoria = funcion.GetData("id_categorias", "select id_categorias from categorias where nombre_categoria = '" + dato + "' ");
            System.out.println("id categoria" + id_categoria);

            tabla_detalle_categorias2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            String[] columNames = {"Id", "Nombre", "Precio"};
            String[] colName = {"id_detalle_categorias", "descripcion", "precio_publico"};
            //nombre de columnas, tabla, instruccion sql        
            dtconduc = funcion.GetTabla(colName, "detalle_categorias", "SELECT d.`id_detalle_categorias`, d.`descripcion`, d.`precio_publico` FROM restaurante.detalle_categorias d WHERE d.`id_categorias` = '" + id_categoria + "' AND d.`activo`='1' ");
            DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
            tabla_detalle_categorias2.setModel(datos);

            int[] anchos = {30, 200, 100};

            for (int inn = 0; inn < tabla_detalle_categorias2.getColumnCount(); inn++) {
                tabla_detalle_categorias2.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
            }

            tabla_detalle_categorias2.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla_detalle_categorias2.getColumnModel().getColumn(0).setMinWidth(0);
            tabla_detalle_categorias2.getColumnModel().getColumn(0).setPreferredWidth(0);

            // funcion.desconecta();
        } catch (Exception j) {
            System.out.println(j.toString());
        }
    }//GEN-LAST:event_combo_categoriasItemStateChanged

    private void tabla_insumos2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_insumos2MouseClicked
        // TODO add your handling code here:        
        sel_insumos = tabla_insumos2.getSelectedRow();
        id_Insumos = (String) tabla_insumos2.getValueAt(sel_insumos, 0);
        lbl_nombre_insumo.setText("Seleccion: " + tabla_insumos2.getValueAt(tabla_insumos2.getSelectedRow(), 1));

        tabla2 = true;
    }//GEN-LAST:event_tabla_insumos2MouseClicked

    private void tabla_insumos2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_insumos2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_insumos2KeyPressed

    private void jTabbedPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseClicked
        // TODO add your handling code here:
        tablaInsumos2();
        llenar_combo();
        tabla1 = false;
        tabla2 = false;
        tabla3 = false;
        this.lbl_nombre_platillo.setText("");

    }//GEN-LAST:event_jTabbedPane2MouseClicked

    private void txt_buscar_insumosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_insumosKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_buscar_insumosKeyPressed

    private void txt_buscar_insumosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_insumosKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_buscar_insumosKeyTyped

    private void txt_buscar_insumosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_insumosKeyReleased
        // TODO add your handling code here:
        insumos_like = this.txt_buscar_insumos.getText().toString();
        tablaInsumos_like();
    }//GEN-LAST:event_txt_buscar_insumosKeyReleased

    private void tabla_consumibles_insumosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_consumibles_insumosMouseClicked
        // TODO add your handling code here:
        tabla3 = true;
    }//GEN-LAST:event_tabla_consumibles_insumosMouseClicked

    private void tabla_consumibles_insumosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_consumibles_insumosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_consumibles_insumosKeyPressed

    private void Jbtn_relacionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_relacionarActionPerformed
        // TODO add your handling code here:
        if ((tabla1 == false) || (tabla2 == false)) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento de la tabla", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {

            if (this.txt_cantidad_insumo.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Debe indicar la cantidad del consumible", "Error", JOptionPane.INFORMATION_MESSAGE);
            } else {

                String dato = (String) tabla_insumos2.getValueAt(sel_insumos, 1);
                System.out.println("el dato es: " + dato);
                //System.out.println("el dato de la tabla es: " + tabla_consumibles_insumos.getValueAt(0, 1).toString());
                boolean datoIgual = false;

                int filas = tabla_consumibles_insumos.getRowCount();
                System.out.println("FILAS: " + filas);

                if (tabla_consumibles_insumos.getRowCount() == 0) {

                } else {
                    for (int i = 0; i < tabla_consumibles_insumos.getRowCount(); i++) {
                        if (dato.equals(tabla_consumibles_insumos.getValueAt(i, 1).toString())) {
                            System.out.println("Si entra " + dato);
                            datoIgual = true;
                        }
                    }
                }

                if (datoIgual == true) {
                    JOptionPane.showMessageDialog(null, "Ya se encuentra el insumo agregado =P", "Error", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    String datos[] = {id_Insumos, id_detalle_categorias, this.txt_cantidad_insumo.getText().toString()};
                    // //  funcion.conectate();
                    funcion.InsertarRegistro(datos, "insert into consumibles_platillos (id_insumos,id_detalle_cat,cantidad) values(?,?,?)");
                    // funcion.desconecta();
                    tablaConsumibles();
                    this.txt_cantidad_insumo.setText("");
                    this.txt_cantidad_insumo.requestFocus();

                }
            }
        }
    }//GEN-LAST:event_Jbtn_relacionarActionPerformed

    private void Jbtn_quitar_insumo_relacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_quitar_insumo_relacionActionPerformed
        // TODO add your handling code here:
        if (tabla3 == true) {

            int sel = tabla_consumibles_insumos.getSelectedRow();
            String id = (String) tabla_consumibles_insumos.getValueAt(sel, 0);

            String dato_eliminar = (String) this.tabla_consumibles_insumos.getValueAt(sel, 1).toString();
            int seleccion = JOptionPane.showOptionDialog(this, "¿Eliminar registro?  " + dato_eliminar, "Confirme eliminacion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Si", "No"}, "Si");
            if ((seleccion + 1) == 1) {
                // //  funcion.conectate();
                funcion.DeleteRegistro("consumibles_platillos", "id_consumibles_platillos", id);
                tablaConsumibles();
                // funcion.desconecta();
                tabla3 = false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila", "Error", JOptionPane.INFORMATION_MESSAGE);

        }
    }//GEN-LAST:event_Jbtn_quitar_insumo_relacionActionPerformed

    private void tabla_empleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_empleadosMouseClicked
        // TODO add your handling code here:
        tabla_empleados_seleccion = true;

        this.txt_contraseña_empleado.setEnabled(false);
        this.txt_verificar_contraseña_empleado.setEnabled(false);

    }//GEN-LAST:event_tabla_empleadosMouseClicked

    private void tabla_empleadosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_empleadosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_empleadosKeyPressed

    private void Jbtn_agregar_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_agregar_empleadoActionPerformed
        // TODO add your handling code here:
        if (nuevoEmpleado == true) {

            if (iniciar_sesion.administrador_global.equals("1")) {
                if (this.txt_nombre_empleado.getText().equals("") || (this.txt_apellidos_empleado.getText().equals("")) || (this.txt_tel_cel_empleado.getText().equals("")) || (this.txt_puesto_empleado.getText().equals("")) || (this.txt_contraseña_empleado.getPassword().equals("")) || (this.txt_verificar_contraseña_empleado.getPassword().equals(""))) {
                    JOptionPane.showMessageDialog(null, "Faltan parametros", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else {

                    String contraseña = this.txt_contraseña_empleado.getText().toString();
                    String verificar = this.txt_verificar_contraseña_empleado.getText().toString();
                    System.out.println("contraseña: " + contraseña);

                    if (contraseña.equals(verificar)) {

                        String datos[] = {this.txt_nombre_empleado.getText().toString(), this.txt_apellidos_empleado.getText().toString(), this.txt_tel_cel_empleado.getText().toString(), this.txt_tel_casa_empleado.getText().toString(), this.txt_puesto_empleado.getText().toString(), contraseña, "0", "1", "0", "0", "0", "0"};
                        // //  funcion.conectate();
                        boolean existe = funcion.existe("empleado", "contraseña", contraseña);
                        if (existe == true) {
                            JOptionPane.showMessageDialog(null, "Esta contraseña ya esta asignada para otro usuario, porfavor registre otra diferente", "Error", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            funcion.InsertarRegistro(datos, "insert into empleado (nombre,apellidos,tel_cel,tel_casa,puesto,contraseña,administrador,ventas,caja,insumos,compras,utilerias) values(?,?,?,?,?,?,?,?,?,?,?,?)");
                            tablaEmpleados();
                            limpiarEmpleados();
                            this.txt_nombre_empleado.requestFocus();
                            tabla_empleados_seleccion = false;
                        }
                        // funcion.desconecta();
                    } else {
                        JOptionPane.showMessageDialog(null, "No coincide la contraseña  =P", "Error", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "No cuenta con permisos para realizar la accion... =(", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe de elegir el boton de nuevo ", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Jbtn_agregar_empleadoActionPerformed

    private void Jbtn_quitar_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_quitar_empleadoActionPerformed
        // TODO add your handling code here:
        int sel = tabla_empleados.getSelectedRow();
        System.out.println("sel: " + sel);
        if (sel != -1) {
            String dato_eliminar = (String.valueOf(tabla_empleados.getValueAt(sel, 1))) + " " + String.valueOf(tabla_empleados.getValueAt(sel, 2));
            String id = tabla_empleados.getValueAt(sel, 0).toString();
            int seleccion = JOptionPane.showOptionDialog(this, "¿Eliminar registro: " + dato_eliminar + "?", "Confirme eliminacion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Si", "No"}, "No");
            if ((seleccion + 1) == 1) {

                // //  funcion.conectate();
                boolean existe = funcion.existe("venta", "id_empleado", id);
                if (existe == true) {
                    JOptionPane.showMessageDialog(null, "No se puede eliminar, por que este usuario cuenta con ventas generadas.", "Error  ", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    funcion.DeleteRegistro("empleado", "id_empleado", id);
                    tablaEmpleados();
                }
                // funcion.desconecta();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar.  ", "Error  ", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Jbtn_quitar_empleadoActionPerformed

    private void Jbtn_editar_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_editar_empleadoActionPerformed
        // TODO add your handling code here:

        if (iniciar_sesion.administrador_global.equals("1")) {
            if (tabla_empleados_seleccion == true) {

                int sel = tabla_empleados.getSelectedRow();
                String id = tabla_empleados.getValueAt(sel, 0).toString();
                System.out.println("id: " + id);
                Jtbn_guardar_empleado.setEnabled(true);
                tabla_empleados_seleccion = false;
                nuevoEmpleado = false;
                // //  funcion.conectate();
                admin = funcion.GetData("administrador", "select administrador from empleado where id_empleado =" + id + "");
                System.out.println("administrador: " + admin);
                // funcion.desconecta();

                //seleccionar_boton();
                this.txt_nombre_empleado.setText(String.valueOf(tabla_empleados.getValueAt(sel, 1)));
                this.txt_apellidos_empleado.setText(String.valueOf(tabla_empleados.getValueAt(sel, 2)));
                this.txt_tel_cel_empleado.setText(String.valueOf(tabla_empleados.getValueAt(sel, 3)));
                this.txt_tel_casa_empleado.setText(String.valueOf(tabla_empleados.getValueAt(sel, 4)));
                this.txt_puesto_empleado.setText(String.valueOf(tabla_empleados.getValueAt(sel, 5)));
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una fila o.O ", "Error", JOptionPane.INFORMATION_MESSAGE);

            }
        } else {
            JOptionPane.showMessageDialog(null, "No cuenta con permisos suficientes =( ", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Jbtn_editar_empleadoActionPerformed

    private void Jtbn_guardar_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jtbn_guardar_empleadoActionPerformed
        // TODO add your handling code here:
        if (this.txt_nombre_empleado.getText().equals("") || (this.txt_apellidos_empleado.getText().equals("")) || (this.txt_tel_cel_empleado.getText().equals("")) || (this.txt_puesto_empleado.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "Faltan parametros", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {

            int sel = tabla_empleados.getSelectedRow();
            String id = (String) tabla_empleados.getValueAt(sel, 0);

            //String administrador;
            //String contraseña = this.txt_contraseña_empleado.getText().toString();
            //String verificar = this.txt_verificar_contraseña_empleado.getText().toString();
            //System.out.println("contraseña: "+contraseña);    

            /*if (check_administrador.isSelected()) {
             administrador = "1";
             } else {
             administrador = "0";
             }*/
            String datos[] = {this.txt_nombre_empleado.getText().toString(), this.txt_apellidos_empleado.getText().toString(), this.txt_tel_cel_empleado.getText().toString(), this.txt_tel_casa_empleado.getText().toString(), this.txt_puesto_empleado.getText().toString(), id};
            // //  funcion.conectate();
            //funcion.UpdateRegistro(datos, "update categorias set nombre_categoria=? where id_categorias=?");
            funcion.UpdateRegistro(datos, "update empleado set nombre=?,apellidos=?,tel_cel=?,tel_casa=?,puesto=?, where id_empleado=?");
            // funcion.desconecta();
            tablaEmpleados();
            limpiarEmpleados();
            this.txt_nombre_empleado.requestFocus();
            tabla_empleados_seleccion = false;
            nuevoEmpleado = false;

        }
    }//GEN-LAST:event_Jtbn_guardar_empleadoActionPerformed

    private void Jbtn_nuevo_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_nuevo_empleadoActionPerformed
        // TODO add your handling code here:
        limpiarEmpleados();
        tabla_empleados_seleccion = false;
        Jbtn_cambiar_contraseña.setEnabled(false);
        nuevoEmpleado = true;

        this.txt_contraseña_empleado.setEnabled(true);
        this.txt_verificar_contraseña_empleado.setEnabled(true);

    }//GEN-LAST:event_Jbtn_nuevo_empleadoActionPerformed

    private void Jbtn_cambiar_contraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_cambiar_contraseñaActionPerformed
        // TODO add your handling code here:

        String contraseña = this.txt_contraseña_empleado.getText().toString();
        String verificar = this.txt_verificar_contraseña_empleado.getText().toString();
        System.out.println("contraseña: " + contraseña);

        if (contraseña.equals(verificar)) {
            if (contraseña.equals("") || verificar.equals("")) {
                JOptionPane.showMessageDialog(null, "Faltan parametros ");
            } else {

                int sel = tabla_empleados.getSelectedRow();
                String id = (String) tabla_empleados.getValueAt(sel, 0);
                // //  funcion.conectate();
                boolean existe = funcion.existe("empleado", "contraseña", contraseña);
                if (existe == true) {
                    JOptionPane.showMessageDialog(null, "Esta contraseña ya esta asignada para otro usuario, porfavor registre otra diferente", "Error", JOptionPane.INFORMATION_MESSAGE);
                    this.txt_contraseña_empleado.setText("");
                    this.txt_verificar_contraseña_empleado.setText("");
                    this.txt_contraseña_empleado.requestFocus();
                } else {

                    String datos[] = {contraseña, id};

                    //array de datos , instruccion sql
                    funcion.UpdateRegistro(datos, "update empleado set contraseña=? where id_empleado=?");
                    JOptionPane.showMessageDialog(null, "Se actualizo la contraseña ");

                    limpiarEmpleados();
                    tabla_empleados_seleccion = false;
                    Jbtn_cambiar_contraseña.setEnabled(false);
                }
                // funcion.desconecta();
            }

        } else {
            JOptionPane.showMessageDialog(null, "No coincide la contraseña .. :(", "Error", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_Jbtn_cambiar_contraseñaActionPerformed

    private void Jbtn_asignar_contraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_asignar_contraseñaActionPerformed
        // TODO add your handling code here:
        if (iniciar_sesion.administrador_global.equals("1")) {
            if (tabla_empleados_seleccion == true) {

                this.txt_contraseña_empleado.setEnabled(true);
                this.txt_verificar_contraseña_empleado.setEnabled(true);
                this.Jbtn_cambiar_contraseña.setEnabled(true);
                tabla_empleados_seleccion = false;

            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para continuar.. ", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No cuenta con permisos para cambiar la contraseña..  ", "Error", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_Jbtn_asignar_contraseñaActionPerformed

    private void Jtbn_guardar_datosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jtbn_guardar_datosActionPerformed
        // TODO add your handling code here:

        if (this.txt_razon_social.getText().equals("") || this.txt_rfc.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Faltan parametros ", "Insertar datos", JOptionPane.INFORMATION_MESSAGE);
        } else {

            if (!this.txt_impuesto_tasa.getText().equals("")) {
                // //  funcion.conectate();
                String id = funcion.ultimo_id("id_datos_generales", "datos_generales");

                String datos[] = {this.txt_razon_social.getText().toString(), this.txt_calle.getText(), this.txt_no_exterior.getText(), this.txt_no_interior.getText(), this.txt_colonia.getText(), this.txt_ciudad.getText(), this.txt_estado.getText(), this.txt_rfc.getText(), this.txt_codigo_postal.getText(), this.txt_referencia.getText(), this.txt_tel1.getText(), this.txt_tel2.getText(), this.txt_tel3.getText(), this.txt_leyenda1.getText(), this.txt_leyenda2.getText(), this.txt_leyenda3.getText(), this.txt_leyenda4.getText(), this.txt_leyenda5.getText(), this.txt_impuesto_descripcion.getText(), this.txt_impuesto_tasa.getText(), txt_lugar_expedicion.getText(), this.txt_regimen_fiscal.getText(), id};

                funcion.UpdateRegistro(datos, "update datos_generales set razon_social=?,calle=?,no_ext=?,no_int=?,colonia=?,ciudad=?,estado=?,RFC=?,cod_postal=?,referencia=?,tel_1=?,tel_2=?,tel_3=?,leyenda1=?,leyenda2=?,leyenda3=?,leyenda4=?,leyenda5=?,impuesto=?,tasa_impuesto=?,lugar_expedicion=?,regimen_fiscal=? where id_datos_generales=? ");
                // funcion.desconecta();
                Jtbn_guardar_datos.setEnabled(false);
                EditarDatosGenerales_no();
                JOptionPane.showMessageDialog(null, "Se actualizo con exito..  ", "Guardar", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Debe de ingresar un porcentaje al impuesto traslado.  ", "Error ", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }//GEN-LAST:event_Jtbn_guardar_datosActionPerformed

    private void Jbtn_editar_datosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_editar_datosActionPerformed
        // TODO add your handling code here:
        if (iniciar_sesion.administrador_global.equals("1")) {
            EditarDatosGenerales_si();
            Jtbn_guardar_datos.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "No cuenta con permisos para cambiar la contraseña..  ", "Error", JOptionPane.INFORMATION_MESSAGE);

        }
    }//GEN-LAST:event_Jbtn_editar_datosActionPerformed

    private void Jbtn_agregar_datosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_agregar_datosActionPerformed
        // TODO add your handling code here:
        if (this.txt_razon_social.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo razon social no puede estar vacio... ", "Insertar datos", JOptionPane.INFORMATION_MESSAGE);
        } else {

            Datos_generales(img3);
            limpiarDatosGenerales();
            JOptionPane.showMessageDialog(null, "Se agrego con exito.. ", "Insertar datos", JOptionPane.INFORMATION_MESSAGE);

        }
    }//GEN-LAST:event_Jbtn_agregar_datosActionPerformed

    private void Jbtn_logoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_logoActionPerformed
        // TODO add your handling code here:

        img3 = null;

        //JFileChooser dlg = new JFileChooser();
        //Abre la ventana de dialogo
        int option = dlg.showOpenDialog(this);
        dlg.setDialogTitle("Elige una Fotografia");

        //Si hace click en el boton abrir del dialogo
        if (option == JFileChooser.APPROVE_OPTION) {
            //Obtiene nombre del archivo seleccionado
            String file = dlg.getSelectedFile().getPath();
            //jTextField3.setText(file);
            //jLblFoto.setIcon(new ImageIcon(file));
            //Modificando la imagen

            icon1 = new ImageIcon(file);
            //Se extrae la imagen del icono
            Image img1 = icon1.getImage();
            //Se modifica su tamaño
            Image newimg = img1.getScaledInstance(202, 183, java.awt.Image.SCALE_SMOOTH);
            //SE GENERA EL IMAGE ICON CON LA NUEVA IMAGEN
            ImageIcon newIcon = new ImageIcon(newimg);
            //img2 = newimg;
            //Se coloca el nuevo icono modificado
            this.lbl_logo.setIcon(newIcon);
            this.Jbtn_actualizar_logo.setEnabled(true);

        }
    }//GEN-LAST:event_Jbtn_logoActionPerformed

    private void Jbtn_cambiar_logoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_cambiar_logoActionPerformed
        // TODO add your handling code here:
        this.Jbtn_logo.setEnabled(true);
    }//GEN-LAST:event_Jbtn_cambiar_logoActionPerformed

    private void Jbtn_actualizar_logoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_actualizar_logoActionPerformed
        // TODO add your handling code here:
        try {
            conectar();
            // //  funcion.conectate();
            String id = funcion.ultimo_id("id_datos_generales", "datos_generales");
            // funcion.desconecta();
            PreparedStatement pstm = conexion.getConnection().prepareStatement("UPDATE datos_generales set logo=? WHERE id_datos_generales=? ");

            File imagenBD = new File(dlg.getSelectedFile().getPath());

            FileInputStream fis = new FileInputStream(imagenBD);

            pstm.setBinaryStream(1, fis, imagenBD.length());
            pstm.setString(2, id);
            pstm.execute();

            desconectar();

        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        Jbtn_actualizar_logo.setEnabled(false);
        Jbtn_cambiar_logo.setEnabled(true);
        Jbtn_logo.setEnabled(false);


    }//GEN-LAST:event_Jbtn_actualizar_logoActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        addMainMenue();
    }//GEN-LAST:event_btn_backActionPerformed

    private void panel_escoger_ordenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_escoger_ordenMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_panel_escoger_ordenMouseClicked

    private void jTabbedPane4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane4MouseClicked
        // TODO add your handling code here:
        por_fecha = false;
        selecciono_fechas = false;
        txt_suma.setText("00.00");
        sel_tabla_consultar_venta = -1;

    }//GEN-LAST:event_jTabbedPane4MouseClicked

    private void txt_razon_socialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_razon_socialFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(1, true);
        }
    }//GEN-LAST:event_txt_razon_socialFocusGained

    private void txt_calleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_calleFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(2, true);
        }
    }//GEN-LAST:event_txt_calleFocusGained

    private void txt_ciudadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_ciudadFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(3, true);
        }
    }//GEN-LAST:event_txt_ciudadFocusGained

    private void txt_estadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_estadoFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(4, true);
        }
    }//GEN-LAST:event_txt_estadoFocusGained

    private void txt_rfcFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rfcFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(5, true);
        }
    }//GEN-LAST:event_txt_rfcFocusGained

    private void txt_tel1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_tel1FocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(6, true);
        }
    }//GEN-LAST:event_txt_tel1FocusGained

    private void txt_tel2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_tel2FocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(7, true);
        }
    }//GEN-LAST:event_txt_tel2FocusGained

    private void txt_tel3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_tel3FocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(8, true);
        }
    }//GEN-LAST:event_txt_tel3FocusGained

    private void txt_leyenda1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_leyenda1FocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(10, true);
        }
    }//GEN-LAST:event_txt_leyenda1FocusGained

    private void txt_leyenda2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_leyenda2FocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(11, true);
        }
    }//GEN-LAST:event_txt_leyenda2FocusGained

    private void txt_leyenda3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_leyenda3FocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(12, true);
        }
    }//GEN-LAST:event_txt_leyenda3FocusGained

    private void txt_leyenda4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_leyenda4FocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(13, true);
        }
    }//GEN-LAST:event_txt_leyenda4FocusGained

    private void txt_leyenda5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_leyenda5FocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(14, true);
        }
    }//GEN-LAST:event_txt_leyenda5FocusGained

    private void txt_nombre_empleadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nombre_empleadoFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(15, true);
        }
    }//GEN-LAST:event_txt_nombre_empleadoFocusGained

    private void txt_apellidos_empleadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_apellidos_empleadoFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(16, true);
        }
    }//GEN-LAST:event_txt_apellidos_empleadoFocusGained

    private void txt_tel_cel_empleadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_tel_cel_empleadoFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(17, true);
        }
    }//GEN-LAST:event_txt_tel_cel_empleadoFocusGained

    private void txt_tel_casa_empleadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_tel_casa_empleadoFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(18, true);
        }
    }//GEN-LAST:event_txt_tel_casa_empleadoFocusGained

    private void txt_puesto_empleadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_puesto_empleadoFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(19, true);
        }
    }//GEN-LAST:event_txt_puesto_empleadoFocusGained

    private void txt_contraseña_empleadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_contraseña_empleadoFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(20, true);
        }
    }//GEN-LAST:event_txt_contraseña_empleadoFocusGained

    private void txt_verificar_contraseña_empleadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_verificar_contraseña_empleadoFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(21, true);
        }
    }//GEN-LAST:event_txt_verificar_contraseña_empleadoFocusGained

    private void txt_agregar_categoriaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_agregar_categoriaFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(22, true);
        }
    }//GEN-LAST:event_txt_agregar_categoriaFocusGained

    private void txt_descripcionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_descripcionFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(23, true);
        }
    }//GEN-LAST:event_txt_descripcionFocusGained

    private void txt_precioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_precioFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(24, true);
        }
    }//GEN-LAST:event_txt_precioFocusGained

    private void txt_nombre_insumoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nombre_insumoFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(25, true);
        }
    }//GEN-LAST:event_txt_nombre_insumoFocusGained

    private void txt_stockFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_stockFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(26, true);
        }
    }//GEN-LAST:event_txt_stockFocusGained

    private void txt_unidad_medidaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_unidad_medidaFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(27, true);
        }
    }//GEN-LAST:event_txt_unidad_medidaFocusGained

    private void txt_ultimo_costoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_ultimo_costoFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(28, true);
        }
    }//GEN-LAST:event_txt_ultimo_costoFocusGained

    private void txt_buscar_insumosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_buscar_insumosFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(29, true);
        }
    }//GEN-LAST:event_txt_buscar_insumosFocusGained

    private void txt_buscar_insumosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscar_insumosActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_buscar_insumosActionPerformed

    private void txt_buscar_insumosHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_txt_buscar_insumosHierarchyChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_buscar_insumosHierarchyChanged

    private void txt_buscar_insumosInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txt_buscar_insumosInputMethodTextChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_buscar_insumosInputMethodTextChanged

    private void txt_buscar_insumosCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txt_buscar_insumosCaretPositionChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_buscar_insumosCaretPositionChanged

    private void txt_buscar_insumosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_buscar_insumosMousePressed
        // TODO add your handling code here:


    }//GEN-LAST:event_txt_buscar_insumosMousePressed

    private void txt_buscar_insumosComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_txt_buscar_insumosComponentAdded
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_buscar_insumosComponentAdded

    private void Jbtn_cerrar_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_cerrar_ventaActionPerformed
        // TODO add your handling code here:
        float aux = tabla_venta.getRowCount();
        float cant = 0;
        float total_consumible = 0;
        String id;
        //ArrayList cant_consumible,id_insumo;
        Object[][] res;

        System.out.println("elementos tabla venta: " + aux);
        if (se_guardo == true || hubo_cambios_venta == false) {
            if (aux > 0) {

                int seleccion = JOptionPane.showOptionDialog(this, "Cerrar venta, ¿Deseas continuar?", "Advertencia...", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Si", "No"}, "Si");
                if (seleccion != -1) {

                    if ((seleccion + 1) == 1) { //presiono que si
                        // //  funcion.conectate();
                        String datos_mesa[] = {"0", id_mesa_global};
                        String datos[] = {"0", id_venta_global};
                        funcion.UpdateRegistro(datos_mesa, "update mesa set estado=? where id_mesa=?"); //Ponemos la mesa en desocupada

                        funcion.UpdateRegistro(datos, "update venta set preventa=? where id_venta=?"); //Cerramos la venta

                        for (int i = 0; i < aux; i++) { //descontamos del stock
                            cant = Float.parseFloat(tabla_venta.getValueAt(i, 1).toString());
                            id = tabla_venta.getValueAt(i, 0).toString();

                            String colName[] = {"cantidad", "id_insumos"};
                            res = funcion.GetTabla(colName, "consumibles_platillos", "select cantidad,id_insumos from consumibles_platillos where id_detalle_cat =" + id + "");

                            for (int j = 0; j < res.length; j++) {

                                System.out.print("J: " + j);
                                total_consumible = cant * Float.parseFloat(res[j][0].toString());

                                System.out.print("cantidad del insumo " + res[j][0]);

                                System.out.print("ID del insumo " + res[j][1]);
                                String stock_viejo = funcion.GetData("stock", "select stock from insumos where id_insumos=" + res[j][1].toString() + "");
                                System.out.print("Stock viejo " + stock_viejo);
                                float stock_nuevo = Float.parseFloat(stock_viejo) - total_consumible;
                                System.out.print("Stock nuevo " + stock_nuevo);

                                String datos_insumos[] = {Float.toString(stock_nuevo), res[j][1].toString()};

                                funcion.UpdateRegistro(datos_insumos, "update insumos set stock =? where id_insumos=? ");

                            }

                        }

                        formato_tabla_venta();
                        mesas_itemDB.llenar();
                        addMainMenue_mesas();
                        tablaInsumos();
                        tablaInsumos_compras();
                        //tabla_consultar_venta();

                        // funcion.desconecta();

                        String aux1 = EliminaCaracteres(txt_total_pagar.getText(), "$");
                        aux1 = EliminaCaracteres(aux1, ",");
                        System.out.println("Total a pagar: " + aux1);
                        total_pagar = Float.parseFloat(aux1);

                        //mostrar_cerrar_ventana();
                        //if (validar_cerrar_ventana == true) {
                        panel_totales.setVisible(false);
                        jTabbedPane4.setEnabledAt(1, false);
                        jTabbedPane4.setEnabledAt(0, true);
                        jTabbedPane4.setSelectedIndex(0);
                        hubo_cambios_venta = false;
                        this.txtFindCodeProduct.requestFocus();
                        // mostrar_nota_venta();
                        tabla_consultar_venta_caja();
                        suma_ventas();
                        total_caja();
                        //}

                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "No hay elementos para cerrar la venta, ingrese al menos un producto", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe guardar la venta antes de cerrar el pedido.", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Jbtn_cerrar_ventaActionPerformed

    private void tabla_ventaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ventaMouseClicked
        // TODO add your handling code here:
        fila_sel_venta = tabla_venta.getSelectedRow();
        platillo_global = tabla_venta.getValueAt(fila_sel_venta, 1).toString() + " " + tabla_venta.getValueAt(fila_sel_venta, 2).toString();
        id_detalle_categoria_global = tabla_venta.getValueAt(fila_sel_venta, 0).toString();
        System.out.print("id: " + id_detalle_categoria_global);
        lbl_producto_elegido.setText(tabla_venta.getValueAt(fila_sel_venta, 2).toString());
    }//GEN-LAST:event_tabla_ventaMouseClicked

    private void Jbtn_sumaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_sumaActionPerformed
        // TODO add your handling code here:
        if (fila_sel_venta == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona una fila.. ", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int cantidad = Integer.parseInt(this.tabla_venta.getValueAt(fila_sel_venta, 1).toString());
            float importe = Float.parseFloat(this.tabla_venta.getValueAt(fila_sel_venta, 4).toString());
            float precio = Float.parseFloat(this.tabla_venta.getValueAt(fila_sel_venta, 3).toString());

            if (cantidad == 99) {
                JOptionPane.showMessageDialog(this, " o.O ", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                cantidad = cantidad + 1;
                importe = precio * cantidad;

                tabla_venta.setValueAt(cantidad, fila_sel_venta, 1);
                tabla_venta.setValueAt(importe, fila_sel_venta, 4);
                total_pagar();
                total();
                hubo_cambios_venta = true;
            }

        }
    }//GEN-LAST:event_Jbtn_sumaActionPerformed

    private void Jbtn_restaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_restaActionPerformed
        // TODO add your handling code here:
        if (fila_sel_venta == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona una fila.. ", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int cantidad = Integer.parseInt(this.tabla_venta.getValueAt(fila_sel_venta, 1).toString());
            float importe = Float.parseFloat(this.tabla_venta.getValueAt(fila_sel_venta, 4).toString());
            float precio = Float.parseFloat(this.tabla_venta.getValueAt(fila_sel_venta, 3).toString());

            if (cantidad == 1) {
                JOptionPane.showMessageDialog(this, " o.O ", "Advertencia", JOptionPane.WARNING_MESSAGE);

            } else {
                cantidad = cantidad - 1;
                importe = precio * cantidad;

                tabla_venta.setValueAt(cantidad, fila_sel_venta, 1);
                tabla_venta.setValueAt(importe, fila_sel_venta, 4);
                total_pagar();
                total();
                hubo_cambios_venta = true;
            }

        }
    }//GEN-LAST:event_Jbtn_restaActionPerformed

    private void Jbtn_quitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_quitarActionPerformed
        // TODO add your handling code here:

        if (fila_sel_venta == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona una fila.. ", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int sel = tabla_venta.getSelectedRow();

            String dato_eliminar = (String) this.tabla_venta.getValueAt(sel, 2).toString();
            int seleccion = JOptionPane.showOptionDialog(this, "¿ Quitar el elemento: " + dato_eliminar + " ?", "Confirme eliminacion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Si", "No"}, "No");

            if ((seleccion + 1) == 1) {
                DefaultTableModel temp = (DefaultTableModel) tabla_venta.getModel();
                temp.removeRow(tabla_venta.getSelectedRow());
                fila_sel_venta = -1;
                total_pagar();
                total();
                hubo_cambios_venta = true;
            }
        }
    }//GEN-LAST:event_Jbtn_quitarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (hubo_cambios_venta == true) {

            int seleccion = JOptionPane.showOptionDialog(this, "Hay cambios sin guardar, ¿Deseas continuar?", "Advertencia...", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Si", "No"}, "No");
            if (seleccion != -1) {

                if ((seleccion + 1) == 1) { //presiono que si
                    jTabbedPane4.setEnabledAt(1, false);
                    jTabbedPane4.setEnabledAt(0, true);
                    jTabbedPane4.setSelectedIndex(0);
                    hubo_cambios_venta = false;
                    panel_totales.setVisible(false);
                }
            }

        } else {
            jTabbedPane4.setEnabledAt(1, false);
            jTabbedPane4.setEnabledAt(0, true);
            jTabbedPane4.setSelectedIndex(0);
            hubo_cambios_venta = false;
            panel_totales.setVisible(false);

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_total_pagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_total_pagarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_total_pagarActionPerformed

    private void Jbtn_guardar_venta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_guardar_venta1ActionPerformed
        // TODO add your handling code here:
        int filas = tabla_venta.getRowCount();
        if (filas > 0) {
            if (validar == true) { //La venta es nueva
                se_guardo = true;
                fecha_sistema();
                // //  funcion.conectate();

                String datos[] = {input_orden.id_empleado, id_mesa_global, input_orden.num_comensales_global, fecha_sistema, "1", id_caja_global, "0", input_orden.id_clientes,"1"};
                funcion.InsertarRegistro(datos, "insert into venta (id_empleado,id_mesa,comensales,fecha,preventa,id_caja,facturado,id_clientes,id_estado_venta) values(?,?,?,?,?,?,?,?,?) ");

                id_venta_global = funcion.ultimo_id("id_venta", "venta");

                //int filas = tabla_venta.getRowCount();
                for (int i = 0; i < filas; i++) {
                    String cantidad = tabla_venta.getValueAt(i, 1).toString();
                    String id_detalle_cat = tabla_venta.getValueAt(i, 0).toString();

                    String datos2[] = {id_venta_global, cantidad, id_detalle_cat};
                    funcion.InsertarRegistro(datos2, "insert into detalle_venta (id_venta,cantidad,id_detalle_categorias) values(?,?,?) ");

                }

                String datos_mesa[] = {"1", id_mesa_global};
                funcion.UpdateRegistro(datos_mesa, "update mesa set estado=? where id_mesa=?");
                //formato_tabla_venta();
                //this.txt_total_pagar.setText("00.00");
                       /* jTabbedPane4.setEnabledAt(1,false);
                 jTabbedPane4.setEnabledAt(0,true);
                 jTabbedPane4.setSelectedIndex(0);*/

                // funcion.desconecta();
                mesas_itemDB.llenar();
                addMainMenue_mesas();
                hubo_cambios_venta = false;

            } else { // Solo se actualiza la venta
                if (hubo_cambios_venta == true) {
                    se_guardo = true;
                    fecha_sistema();
                    // //  funcion.conectate();
                    funcion.DeleteRegistro("detalle_venta", "id_venta", id_venta_global);

                    //String datos[] = {iniciar_sesion.id_usuario_global,id_mesa_global,this.lbl_nombre_cliente.getText().toString(),input_orden.num_comensales_global,fecha_sistema,"1" };                        
                    //funcion.InsertarRegistro(datos, "insert into venta (id_empleado,id_mesa,nombre_cliente,comensales,fecha,preventa) values(?,?,?,?,?,?) ");                    
                    //String id_venta = funcion.ultimo_id("id_venta", "venta");
                    //int filas = tabla_venta.getRowCount();
                    for (int i = 0; i < filas; i++) {
                        String cantidad = tabla_venta.getValueAt(i, 1).toString();
                        String id_detalle_cat = tabla_venta.getValueAt(i, 0).toString();
                        String nota1 = String.valueOf(tabla_venta.getValueAt(i, 5));
                        String nota2 = String.valueOf(tabla_venta.getValueAt(i, 6));
                        if (nota1.equals("null")) {
                            nota1 = "";
                        }
                        if (nota2.equals("null")) {
                            nota2 = "";
                        }
                        String datos2[] = {id_venta_global, cantidad, id_detalle_cat, nota1, nota2};
                        funcion.InsertarRegistro(datos2, "insert into detalle_venta (id_venta,cantidad,id_detalle_categorias,nota1,nota2) values(?,?,?,?,?) ");
                    }
                   // JOptionPane.showMessageDialog(null, "Se guardo con exito  ", "guardar", JOptionPane.INFORMATION_MESSAGE);

                    //String datos_mesa[] = {"1",id_mesa_global};
                    //funcion.UpdateRegistro(datos_mesa, "update mesa set estado=? where id_mesa=?"); 
                    //formato_tabla_venta();
                        /*this.txt_total_pagar.setText("00.00");
                     jTabbedPane4.setEnabledAt(1,false);
                     jTabbedPane4.setEnabledAt(0,true);
                     jTabbedPane4.setSelectedIndex(0);*/
                    // funcion.desconecta();
                    /*mesas_itemDB.llenar();
                     addMainMenue_mesas();*/
                    hubo_cambios_venta = false;

                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay elementos para guardar  ", "Error ", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Jbtn_guardar_venta1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        tablaInsumos();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tabla_consultar_ventaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_consultar_ventaMouseClicked
        // TODO add your handling code here:
        sel_tabla_consultar_venta = tabla_consultar_venta.getSelectedRow();
        id_venta_global = tabla_consultar_venta.getValueAt(sel_tabla_consultar_venta, 0).toString();
        total_venta_global = Float.parseFloat(EliminaCaracteres(tabla_consultar_venta.getValueAt(sel_tabla_consultar_venta, 3).toString(), "$,"));
        System.out.println("venta_global: " + total_venta_global);
    }//GEN-LAST:event_tabla_consultar_ventaMouseClicked

    private void Jbtn_buscar_fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_buscar_fechaActionPerformed
        // TODO add your handling code here:

        if ((txt_fecha_inicial.getDate() == null) || (txt_fecha_final.getDate() == null)) {
            JOptionPane.showMessageDialog(null, "Faltan parametros");
        } else {
            panel_c_ventas.setVisible(true);
            selecciono_fechas = true;
            filtro_fecha_inicial = new SimpleDateFormat("dd/MM/yyyy").format(txt_fecha_inicial.getDate());
            filtro_fecha_final = new SimpleDateFormat("dd/MM/yyyy").format(this.txt_fecha_final.getDate());
            por_fecha = true;

            tabla_consultar_venta();
            suma();

        }

    }//GEN-LAST:event_Jbtn_buscar_fechaActionPerformed

    private void panel_mesasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_mesasMouseClicked
        // TODO add your handling code here:
        por_fecha = false;
    }//GEN-LAST:event_panel_mesasMouseClicked

    private void Jbtn_refrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_refrescarActionPerformed
        // TODO add your handling code here:
        panel_c_ventas.setVisible(true);
        por_fecha = false;
        tabla_consultar_venta();
        sel_tabla_consultar_venta = -1;
        txt_suma.setText("00.00");
    }//GEN-LAST:event_Jbtn_refrescarActionPerformed

    private void Jbtn_generar_notaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_generar_notaActionPerformed
        // TODO add your handling code here:
        if (sel_tabla_consultar_venta != -1) {
            generar_reporte = true;

            total_venta = tabla_consultar_venta.getValueAt(sel_tabla_consultar_venta, 3).toString();
            mostrar_nota_venta();
            generar_reporte = false;
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona una fila para generar el reporte  ", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Jbtn_generar_notaActionPerformed

    private void Jbtn_cancelar_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_cancelar_ventaActionPerformed
        // TODO add your handling code here:

        if (se_guardo == true || validar == false) {

            int seleccion = JOptionPane.showOptionDialog(this, "Cancelar venta, ¿Deseas continuar? /- No podrás deshacer esta acción", "Advertencia...", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Si", "No"}, "No");
            //if (seleccion != -1) {

            if ((seleccion + 1) == 1) { //presiono que si  
                // //  funcion.conectate();
                String datos_mesa[] = {"0", id_mesa_global};

                funcion.UpdateRegistro(datos_mesa, "update mesa set estado=? where id_mesa=?"); //Ponemos la mesa en desocupada

                funcion.DeleteRegistro("detalle_venta", "id_venta", id_venta_global);
                funcion.DeleteRegistro("venta", "id_venta", id_venta_global);
                // funcion.desconecta();

                formato_tabla_venta();
                mesas_itemDB.llenar();
                addMainMenue_mesas();

                jTabbedPane4.setEnabledAt(1, false);
                jTabbedPane4.setEnabledAt(0, true);
                jTabbedPane4.setSelectedIndex(0);
                panel_totales.setVisible(false);
                hubo_cambios_venta = false;
            }

        } else {
            JOptionPane.showMessageDialog(null, "No se ha registrado la venta para cancelar ", "Error", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_Jbtn_cancelar_ventaActionPerformed

    private void Jbtn_generar_nota_por_fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_generar_nota_por_fechaActionPerformed
        // TODO add your handling code here:
        if (selecciono_fechas == true) {

            mostrar_nota_fechas();
        } else {
            JOptionPane.showMessageDialog(null, "Elige los parametros, para generar el reporte ", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Jbtn_generar_nota_por_fechaActionPerformed

    private void Jbtn_generar_nota1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_generar_nota1ActionPerformed
        // TODO add your handling code here:
        if (se_guardo == true || hubo_cambios_venta == false && validar == false) {
            generar_reporte = false;
            mostrar_nota_venta();
        } else {
            JOptionPane.showMessageDialog(null, "Guarda la venta, para generar la nota ", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Jbtn_generar_nota1ActionPerformed

    private void txt_cantidad_insumoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cantidad_insumoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cantidad_insumoFocusLost

    private void txt_cantidad_insumoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cantidad_insumoFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(30, true);
        }
    }//GEN-LAST:event_txt_cantidad_insumoFocusGained

    private void txt_cantidad_insumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cantidad_insumoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cantidad_insumoActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        sel_tabla_ingresos = false;
        sel_tabla_egresos = false;
        sel_tabla_caja = false;
        sel_tabla_insumos_compras = false;
        sel_tabla_compras = false;
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void tabla_consultar_cajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_consultar_cajaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_consultar_cajaMouseClicked

    private void tabla_ingresos_cajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ingresos_cajaMouseClicked
        // TODO add your handling code here:
        int sel = tabla_ingresos_caja.getSelectedRow();
        id_detalle_caja = (String) tabla_ingresos_caja.getValueAt(sel, 0);
        dato_eliminar_caja = (String) tabla_ingresos_caja.getValueAt(sel, 1);
        sel_tabla_ingresos = true;
    }//GEN-LAST:event_tabla_ingresos_cajaMouseClicked

    private void tabla_egresos_cajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_egresos_cajaMouseClicked
        // TODO add your handling code here:
        int sel = tabla_egresos_caja.getSelectedRow();
        id_detalle_caja = (String) tabla_egresos_caja.getValueAt(sel, 0);
        dato_eliminar_caja = (String) tabla_egresos_caja.getValueAt(sel, 1);
        sel_tabla_egresos = true;
    }//GEN-LAST:event_tabla_egresos_cajaMouseClicked

    private void Jbtn_aperturar_cajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_aperturar_cajaActionPerformed
        // TODO add your handling code here:
        mostrar_aperturar_caja();

        if (validar_caja == true) {
            // //  funcion.conectate();

            //String datos[] = {iniciar_sesion.id_usuario_global,id_mesa_global,input_orden.nombre_cliente_global,input_orden.num_comensales_global,fecha_sistema,"1" };                        
            //     funcion.InsertarRegistro(datos, "insert into venta (id_empleado,id_mesa,nombre_cliente,comensales,fecha,preventa) values(?,?,?,?,?,?) ");
            String datos[] = {iniciar_sesion.id_usuario_global, fecha_sistema, " ", "1", aperturar_caja.comentario};

            funcion.InsertarRegistro(datos, "insert into caja (id_empleado,fecha_apertura,fecha_cierre,abierta,nota) values (?,?,?,?,?) ");
            id_caja_global = funcion.ultimo_id("id_caja", "caja");
            String datos2[] = {id_caja_global, "1", aperturar_caja.por_apertura, "apertura"};
            funcion.InsertarRegistro(datos2, "insert into detalle_caja (id_caja,ingreso,monto,descripcion) values (?,?,?,?)");
            jTabbedPane1.setEnabledAt(0, true);
            Jbtn_registrar_caja.setEnabled(true);
            Jbtn_cerrar_caja.setEnabled(true);
            Jbtn_aperturar_caja.setEnabled(false);
            tabla_ingresos();
            tabla_consultar_venta_caja();
            tabla_ingresos();
            suma_ingresos();
            total_caja();
            // funcion.desconecta();

        }
    }//GEN-LAST:event_Jbtn_aperturar_cajaActionPerformed

    private void txt_montoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_montoFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(31, true);
        }
        // this.txt_monto.setText("");
    }//GEN-LAST:event_txt_montoFocusGained

    private void check_ingresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_ingresoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_ingresoActionPerformed

    private void Jbtn_registrar_cajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_registrar_cajaActionPerformed
        // TODO add your handling code here:
        float auxMonto = 0, auxTotal = 0;
        if (!this.txt_monto.getText().equals("0.00") && !this.txt_monto.getText().equals("")) {
            if (this.check_ingreso.isSelected() || this.check_egreso.isSelected()) {

                String ingreso;
                if (check_ingreso.isSelected()) {
                    ingreso = "1";
                } else {
                    ingreso = "0";
                }

                if (ingreso.equals("0")) {
                    String auxtotal = EliminaCaracteres(this.txt_total_caja.getText().toString(), "$,");

                    auxTotal = Float.parseFloat(auxtotal);
                    auxMonto = Float.parseFloat(this.txt_monto.getText().toString());

                }

                if (auxMonto <= auxTotal) {

                    // //  funcion.conectate();

                    String datos2[] = {id_caja_global, ingreso, this.txt_monto.getText().toString(), txt_nota.getText().toString()};
                    funcion.InsertarRegistro(datos2, "insert into detalle_caja (id_caja,ingreso,monto,descripcion) values (?,?,?,?)");

                    if (ingreso.equals("1")) {
                        tabla_ingresos();
                        suma_ingresos();
                    } else {
                        tabla_egresos();
                        suma_egresos();
                    }

                    // funcion.desconecta();

                    this.txt_monto.setText("0.00");
                    this.txt_nota.setText("");

                    this.txt_monto.requestFocus();
                    this.check_ingreso.setSelected(false);
                    this.check_egreso.setSelected(false);
                    total_caja();
                } else {
                    JOptionPane.showMessageDialog(null, "la cantidad es mayor a la disponible en caja ", "Error", JOptionPane.INFORMATION_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Selecciona el tipo de monto... ", "Error", JOptionPane.INFORMATION_MESSAGE);

            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresa una cantidad al monto ", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Jbtn_registrar_cajaActionPerformed

    private void Jbtn_cerrar_cajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_cerrar_cajaActionPerformed
        // TODO add your handling code here

        int seleccion = JOptionPane.showOptionDialog(this, "Cerrar caja, ¿Deseas continuar?", "Advertencia...", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Si", "No"}, "No");
        if (seleccion == 0) { // presiono que si

            // //  funcion.conectate();
            fecha_sistema();

            String datos[] = {fecha_sistema, "0", id_caja_global};
            funcion.UpdateRegistro(datos, "update caja set fecha_cierre=?,abierta=? where id_caja=?");
            JOptionPane.showMessageDialog(null, "Se ha cerrado la caja ", "Caja ", JOptionPane.INFORMATION_MESSAGE);

            // funcion.desconecta();

            formato_tabla_consultar_caja();
            formato_tabla_ingresos();
            formato_tabla_egresos();
            //tabla_consultar_venta_caja1();
            Jbtn_aperturar_caja.setEnabled(true);
            Jbtn_cerrar_caja.setEnabled(false);
            Jbtn_registrar_caja.setEnabled(false);
            jTabbedPane1.setEnabledAt(0, false);

            this.txt_total_caja.setText("00.00");
            this.txt_total_ingresos.setText("00.00");
            this.txt_total_egresos.setText("00.00");
            this.txt_total_ventas.setText("00.00");

        }
    }//GEN-LAST:event_Jbtn_cerrar_cajaActionPerformed

    private void txt_notaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_notaFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(32, true);
        }

    }//GEN-LAST:event_txt_notaFocusGained

    private void Jbtn_eliminar_montoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_eliminar_montoActionPerformed
        // TODO add your handling code here:
        if (sel_tabla_ingresos == true || sel_tabla_egresos == true) {
            int seleccion = JOptionPane.showOptionDialog(this, "¿Eliminar el monto de: $ " + dato_eliminar_caja + "? ", "Advertencia...", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Si", "No"}, "No");
            if (seleccion == 0) { // presiono que si

                // //  funcion.conectate();
                funcion.DeleteRegistro("detalle_caja", "id_detalle_caja", id_detalle_caja);

                tablaDetalleCategorias();
                tabla_detalle_seleccion = false;
                // funcion.desconecta();

                if (sel_tabla_ingresos == true) {
                    tabla_ingresos();
                    suma_ingresos();
                } else {
                    tabla_egresos();
                    suma_egresos();

                }
                total_caja();
                sel_tabla_ingresos = false;
                sel_tabla_egresos = false;
            }

        } else {
            JOptionPane.showMessageDialog(null, "Elije un monto a eliminar", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Jbtn_eliminar_montoActionPerformed

    private void txt_montoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_montoFocusLost
        // TODO add your handling code here:
        // this.txt_monto.setText("0.00");
    }//GEN-LAST:event_txt_montoFocusLost

    private void tabla_consultar_caja1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_consultar_caja1MouseClicked
        // TODO add your handling code here:
        int sel = tabla_consultar_caja1.getSelectedRow();
        id_caja = (String) tabla_consultar_caja1.getValueAt(sel, 0);
        sel_tabla_caja = true;
    }//GEN-LAST:event_tabla_consultar_caja1MouseClicked

    private void Jbtn_reporte_cajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_reporte_cajaActionPerformed
        // TODO add your handling code here:

        if (!(tabla_consultar_caja1.getSelectedRow() == -1)) {
            String aux = (String) tabla_consultar_caja1.getValueAt(tabla_consultar_caja1.getSelectedRow(), 2);

            if (aux != null) {
                //2018.01.28
                  id_caja_consultada = (String) tabla_consultar_caja1.getValueAt(tabla_consultar_caja1.getSelectedRow(), 0);
//                id_caja_global = (String) tabla_consultar_caja1.getValueAt(tabla_consultar_caja1.getSelectedRow(), 0);
                //mostrar_reporte_caja();
                mostrar_venta_cajas();
            } else {
                JOptionPane.showMessageDialog(null, "No se puede generar el reporte cuando la caja esta abierta.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Elije una fila, para generar el reporte", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Jbtn_reporte_cajaActionPerformed

    private void tabla_insumos_comprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_insumos_comprasMouseClicked
        // TODO add your handling code here:
        /*sel_categorias = tabla_detalle_categorias2.getSelectedRow();
         id_detalle_categorias = (String) tabla_detalle_categorias2.getValueAt(sel_categorias, 0);
         String nombre_platillo = (String) tabla_detalle_categorias2.getValueAt(sel_categorias, 1);
         lbl_nombre_platillo.setText(nombre_platillo);
         tabla1 = true;*/

        int sel = tabla_insumos_compras.getSelectedRow();
        id_insumo_compra = (String) tabla_insumos_compras.getValueAt(sel, 0);
        lbl_eleccion_insumo.setText(tabla_insumos_compras.getValueAt(sel, 1).toString());
        sel_tabla_insumos_compras = true;

    }//GEN-LAST:event_tabla_insumos_comprasMouseClicked

    private void tabla_insumos_comprasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_insumos_comprasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_insumos_comprasKeyPressed

    private void txt_buscar_insumos_comprasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_buscar_insumos_comprasMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscar_insumos_comprasMousePressed

    private void txt_buscar_insumos_comprasComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_txt_buscar_insumos_comprasComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscar_insumos_comprasComponentAdded

    private void txt_buscar_insumos_comprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscar_insumos_comprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscar_insumos_comprasActionPerformed

    private void txt_buscar_insumos_comprasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_buscar_insumos_comprasFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(33, true);
        }
    }//GEN-LAST:event_txt_buscar_insumos_comprasFocusGained

    private void txt_buscar_insumos_comprasHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_txt_buscar_insumos_comprasHierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscar_insumos_comprasHierarchyChanged

    private void txt_buscar_insumos_comprasCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txt_buscar_insumos_comprasCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscar_insumos_comprasCaretPositionChanged

    private void txt_buscar_insumos_comprasInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txt_buscar_insumos_comprasInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscar_insumos_comprasInputMethodTextChanged

    private void txt_buscar_insumos_comprasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_insumos_comprasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscar_insumos_comprasKeyPressed

    private void txt_buscar_insumos_comprasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_insumos_comprasKeyReleased
        // TODO add your handling code here:
        insumos_like_compras = this.txt_buscar_insumos_compras.getText().toString();
        tablaInsumos_like_compras();
    }//GEN-LAST:event_txt_buscar_insumos_comprasKeyReleased

    private void txt_buscar_insumos_comprasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_insumos_comprasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscar_insumos_comprasKeyTyped

    private void tabla_detalle_comprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_detalle_comprasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_detalle_comprasMouseClicked

    private void tabla_detalle_comprasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_detalle_comprasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_detalle_comprasKeyPressed

    private void txt_cantidad_compraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cantidad_compraFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(34, true);
        }
        if (this.txt_cantidad_compra.getText().equals("0.00")) {
            txt_cantidad_compra.setText("");
        }
    }//GEN-LAST:event_txt_cantidad_compraFocusGained

    private void Jbtn_agregar_comprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_agregar_comprasActionPerformed
        // TODO add your handling code here:
        if (sel_tabla_insumos_compras == true) {

            if (!this.txt_cantidad_compra.getText().equals("0.00") && !txt_ultimo_costo_compras.getText().equals("0.00")) {

                DefaultTableModel temp = (DefaultTableModel) tabla_detalle_compras.getModel();
                Object nuevo[] = {id_insumo_compra, this.lbl_eleccion_insumo.getText().toString(), txt_cantidad_compra.getText().toString(), txt_ultimo_costo_compras.getText().toString(), txt_comentario_compras.getText().toString()};
                temp.addRow(nuevo);
                this.txt_cantidad_compra.setText("0.00");
                this.txt_ultimo_costo_compras.setText("0.00");
                this.txt_comentario_compras.setText("");
                txt_cantidad_compra.requestFocus();
                total_compras();
            } else {
                JOptionPane.showMessageDialog(null, "Faltan parametros ", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Elige un insumo de la tabla..", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Jbtn_agregar_comprasActionPerformed

    private void Jbtn_quitar_comprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_quitar_comprasActionPerformed
        // TODO add your handling code here:
        int i = tabla_detalle_compras.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Favor de seleccionar una fila");
        } else//de lo contrario si se selecciono la fila
        {
            try {
                DefaultTableModel temp = (DefaultTableModel) tabla_detalle_compras.getModel();
                temp.removeRow(tabla_detalle_compras.getSelectedRow());
                total_compras();

            } catch (ArrayIndexOutOfBoundsException e) {
                ;
            }
        }
    }//GEN-LAST:event_Jbtn_quitar_comprasActionPerformed

    private void txt_ultimo_costo_comprasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_ultimo_costo_comprasFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(35, true);
        }
        if (txt_ultimo_costo_compras.getText().equals("0.00")) {
            txt_ultimo_costo_compras.setText("");
        }
    }//GEN-LAST:event_txt_ultimo_costo_comprasFocusGained

    private void txt_comentario_comprasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_comentario_comprasFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(36, true);
        }
    }//GEN-LAST:event_txt_comentario_comprasFocusGained

    private void Jtbn_registrar_compraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jtbn_registrar_compraActionPerformed
        // TODO add your handling code here:
        if (this.tabla_detalle_compras.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No hay productos para continuar la compra. ");

        } else {

            if (txt_proveedor.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Indica el proveedor ");
                this.txt_proveedor.requestFocus();
            } else {

                // //  funcion.conectate();
                String datos[] = {iniciar_sesion.id_usuario_global, fecha_sistema, txt_proveedor.getText().toString(), this.txt_comentario_compras.getText().toString()}; //arreglo para mandar a la funcion
                //array de datos , instruccion sql
                funcion.InsertarRegistro(datos, "insert into compras (id_empleado,fecha,proveedor,nota1) values(?,?,?,?)");
                String id_ultima_compra = funcion.ultimo_id("id_compras", "compras");

                // Agregamos al detalle de la compra
                for (int i = 0; i < tabla_detalle_compras.getRowCount(); i++) {

                    String datos1[] = {id_ultima_compra, tabla_detalle_compras.getValueAt(i, 0).toString(), tabla_detalle_compras.getValueAt(i, 2).toString(), tabla_detalle_compras.getValueAt(i, 3).toString(), tabla_detalle_compras.getValueAt(i, 4).toString()};
                    funcion.InsertarRegistro(datos1, "insert into detalle_compras (id_compras,id_insumos,cantidad,ultimo_costo,notas) values(?,?,?,?,?)");

                    String stock_viejo = funcion.GetData("stock", "select stock from insumos where id_insumos =" + tabla_detalle_compras.getValueAt(i, 0).toString() + "");

                    float stock_nuevo = Float.parseFloat(stock_viejo) + Float.parseFloat(tabla_detalle_compras.getValueAt(i, 2).toString());

                    String datos_insumos[] = {Float.toString(stock_nuevo), tabla_detalle_compras.getValueAt(i, 3).toString(), tabla_detalle_compras.getValueAt(i, 0).toString()};

                    funcion.UpdateRegistro(datos_insumos, "update insumos set stock =?,ultimo_costo=? where id_insumos=? ");

                } // FIN FOR
                // funcion.desconecta();
                formato_tabla_detalle_compra();
                this.txt_cantidad_compra.setText("0.00");
                this.txt_comentario_compras.setText("");
                txt_comentarios.setText("");
                this.txt_ultimo_costo_compras.setText("0.00");
                txt_total_compra.setText("0.00");
                this.txt_proveedor.setText("");
                tablaInsumos_compras();
                tablaInsumos();
                tabla_consultar_compras();

                JOptionPane.showMessageDialog(null, "Se ha registrado la compra con exito", "Compras ", JOptionPane.INFORMATION_MESSAGE);

            }
        }
    }//GEN-LAST:event_Jtbn_registrar_compraActionPerformed

    private void txt_proveedorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_proveedorFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(37, true);
        }
    }//GEN-LAST:event_txt_proveedorFocusGained

    private void txt_comentariosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_comentariosFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(38, true);
        }
    }//GEN-LAST:event_txt_comentariosFocusGained

    private void tabla_consultar_comprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_consultar_comprasMouseClicked
        // TODO add your handling code here:
        int sel = tabla_consultar_compras.getSelectedRow();
        id_compras = (String) tabla_consultar_compras.getValueAt(sel, 0);
        sel_tabla_compras = true;
    }//GEN-LAST:event_tabla_consultar_comprasMouseClicked

    private void tabla_consultar_comprasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_consultar_comprasKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tabla_consultar_comprasKeyPressed

    private void Jbtn_reporte_comprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_reporte_comprasActionPerformed
        // TODO add your handling code here:
        if (sel_tabla_compras == true) {
            mostrar_reporte_compras();
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona una fila.. ", "Error  ", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Jbtn_reporte_comprasActionPerformed

    private void Jbtn_reporte_comprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jbtn_reporte_comprasMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_Jbtn_reporte_comprasMouseClicked

    private void jbtn_insumos_vendidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_insumos_vendidosActionPerformed
        // TODO add your handling code here:
        if (txt_fecha_inicial.getDate() == null || txt_fecha_final.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Faltan parametros  ", "Error  ", JOptionPane.INFORMATION_MESSAGE);
        } else {
            mostrar_insumos_vendidos();
        }

    }//GEN-LAST:event_jbtn_insumos_vendidosActionPerformed

    private void Jbtn_modificar_seriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_modificar_seriesActionPerformed
        // TODO add your handling code here:
        this.txt_contraseña_series.setVisible(true);
        txt_contraseña_series.setText("");
        this.Jbtn_actualizar_series.setVisible(true);
        this.txt_contraseña_series.requestFocus();

        editar_series();


    }//GEN-LAST:event_Jbtn_modificar_seriesActionPerformed

    private void Jbtn_actualizar_seriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_actualizar_seriesActionPerformed
        // TODO add your handling code here:        

        if (this.txt_contraseña_series.getText().equals("cambiarseries1022")) {
            // //  funcion.conectate();
            String id = funcion.ultimo_id("id_datos_generales", "datos_generales");
            String datos[] = {this.txt_serie.getText(), this.txt_folio_inicial.getText(), this.txt_folio_final.getText(), this.txt_folio_actual.getText(), id};

            funcion.UpdateRegistro(datos, "update datos_generales set serie=?,folio_inicial=?,folio_final=?,folio_actual=? where id_datos_generales=? ");
            // funcion.desconecta();

            editar_series_no();
            this.txt_contraseña_series.setVisible(false);
            this.Jbtn_actualizar_series.setVisible(false);
            JOptionPane.showMessageDialog(null, "Se actualizo con exito  ", "Actualizacion.  ", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No coincide la contraseña  ", "Error  ", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_Jbtn_actualizar_seriesActionPerformed

    private void txt_no_exteriorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_no_exteriorFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(39, true);
        }
    }//GEN-LAST:event_txt_no_exteriorFocusGained

    private void txt_no_interiorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_no_interiorFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(40, true);
        }
    }//GEN-LAST:event_txt_no_interiorFocusGained

    private void txt_coloniaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_coloniaFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(41, true);
        }
    }//GEN-LAST:event_txt_coloniaFocusGained

    private void txt_codigo_postalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_codigo_postalFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(42, true);
        }
    }//GEN-LAST:event_txt_codigo_postalFocusGained

    private void txt_referenciaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_referenciaFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(43, true);
        }
    }//GEN-LAST:event_txt_referenciaFocusGained

    private void txt_serieFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_serieFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(44, true);
        }
    }//GEN-LAST:event_txt_serieFocusGained

    private void txt_folio_inicialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_folio_inicialFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(45, true);
        }
    }//GEN-LAST:event_txt_folio_inicialFocusGained

    private void txt_folio_finalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_folio_finalFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(46, true);
        }
    }//GEN-LAST:event_txt_folio_finalFocusGained

    private void txt_folio_actualFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_folio_actualFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(47, true);
        }
    }//GEN-LAST:event_txt_folio_actualFocusGained

    private void txt_contraseña_seriesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_contraseña_seriesFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(48, true);
        }
    }//GEN-LAST:event_txt_contraseña_seriesFocusGained

    private void tabla_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_clientesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_clientesMouseClicked

    private void tabla_clientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_clientesKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_clientesKeyPressed

    private void Jbtn_nuevo_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_nuevo_clienteActionPerformed
        // TODO add your handling code here:
        nuevo_cliente = true;
        limpiarDatosClientes();
        EditarDatosClientes();
        this.txt_nombre_cliente.requestFocus();

    }//GEN-LAST:event_Jbtn_nuevo_clienteActionPerformed

    private void Jtbn_guardar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jtbn_guardar_clienteActionPerformed
        // TODO add your handling code here:

        int sel = tabla_clientes.getSelectedRow();
        String id = tabla_clientes.getValueAt(sel, 0).toString();
        if (this.txt_rfc_cliente.getText().equals("") || this.txt_razon_social_cliente.getText().equals("") || this.txt_pais_cliente.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Faltan parametros", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (validaFormatoRFC(txt_rfc_cliente.getText().trim()) == true) {
                if ((txt_cp_cliente.getText().length() != 0) && (txt_cp_cliente.getText().length() == 5)) {
                    // //  funcion.conectate();
                    //funcion.UpdateRegistro(datos, "update categorias set nombre_categoria=? where id_categorias=?");
                    String datos[] = {this.txt_nombre_cliente.getText(), this.txt_apellidos_cliente.getText(), this.txt_tel_movil_cliente.getText(), this.txt_tel_fijo_cliente.getText(), this.txt_rfc_cliente.getText(), this.txt_razon_social_cliente.getText(), this.txt_calle_cliente.getText(), this.txt_no_ext_cliente.getText(), this.txt_no_int_cliente.getText(), this.txt_colonia_cliente.getText(), this.txt_cp_cliente.getText(), this.txt_localidad_cliente.getText(), this.txt_delegacion_cliente.getText(), this.txt_estado_cliente.getText(), this.txt_pais_cliente.getText(), this.txt_email_cliente.getText(), id};
                    funcion.UpdateRegistro(datos, "update clientes set nombre=?,apellidos=?,tel_movil=?,tel_fijo=?,rfc=?,razon_social=?,calle=?,noExt=?,noInt=?,colonia=?,cp=?,localidad=?,delegacion=?,estado=?,pais=?,email=? where id_clientes=?");
                    // funcion.desconecta();
                    Jtbn_guardar_cliente.setEnabled(false);
                    nuevo_cliente = false;
                    tabla_clientes();
                    limpiarDatosClientes();
                    EditarDatosClientes_no();
                } else {
                    JOptionPane.showMessageDialog(null, "Codigo Postal invalido", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "RFC invalido", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_Jtbn_guardar_clienteActionPerformed

    private void Jbtn_editar_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_editar_editarActionPerformed
        // TODO add your handling code here:
        int sel = tabla_clientes.getSelectedRow();
        System.out.println("sel: " + sel);
        if (sel != -1) {
            this.txt_nombre_cliente.setText(String.valueOf(tabla_clientes.getValueAt(sel, 1)));
            this.txt_apellidos_cliente.setText(String.valueOf(tabla_clientes.getValueAt(sel, 2)));
            this.txt_tel_movil_cliente.setText(String.valueOf(tabla_clientes.getValueAt(sel, 3)));
            this.txt_tel_fijo_cliente.setText(String.valueOf(tabla_clientes.getValueAt(sel, 4)));

            this.txt_rfc_cliente.setText(String.valueOf(tabla_clientes.getValueAt(sel, 5)));
            this.txt_razon_social_cliente.setText(String.valueOf(tabla_clientes.getValueAt(sel, 6)));
            this.txt_calle_cliente.setText(String.valueOf(tabla_clientes.getValueAt(sel, 7)));
            this.txt_no_ext_cliente.setText(String.valueOf(tabla_clientes.getValueAt(sel, 8)));
            this.txt_no_int_cliente.setText(String.valueOf(tabla_clientes.getValueAt(sel, 9)));
            this.txt_colonia_cliente.setText(String.valueOf(tabla_clientes.getValueAt(sel, 10)));
            this.txt_cp_cliente.setText(String.valueOf(tabla_clientes.getValueAt(sel, 11)));
            this.txt_localidad_cliente.setText(String.valueOf(tabla_clientes.getValueAt(sel, 12)));
            this.txt_delegacion_cliente.setText(String.valueOf(tabla_clientes.getValueAt(sel, 13)));
            this.txt_estado_cliente.setText(String.valueOf(tabla_clientes.getValueAt(sel, 14)));
            this.txt_pais_cliente.setText(String.valueOf(tabla_clientes.getValueAt(sel, 15)));
            this.txt_email_cliente.setText(String.valueOf(tabla_clientes.getValueAt(sel, 16)));
            /*
             this.txt_nombre_cliente.setText(this.tabla_clientes.getValueAt(sel, 1).toString());
             this.txt_apellidos_cliente.setText(this.tabla_clientes.getValueAt(sel, 2).toString());
             this.txt_tel_movil_cliente.setText(this.tabla_clientes.getValueAt(sel, 3).toString());
             this.txt_tel_fijo_cliente.setText(this.tabla_clientes.getValueAt(sel, 4).toString());
            
            
             this.txt_rfc_cliente.setText(this.tabla_clientes.getValueAt(sel, 5).toString());
             this.txt_razon_social_cliente.setText(this.tabla_clientes.getValueAt(sel, 6).toString());
             this.txt_calle_cliente.setText(this.tabla_clientes.getValueAt(sel, 7).toString());
             this.txt_no_ext_cliente.setText(this.tabla_clientes.getValueAt(sel, 8).toString());
             this.txt_no_int_cliente.setText(this.tabla_clientes.getValueAt(sel, 9).toString());
             this.txt_colonia_cliente.setText(this.tabla_clientes.getValueAt(sel, 10).toString());
             this.txt_cp_cliente.setText(this.tabla_clientes.getValueAt(sel, 11).toString());
             this.txt_localidad_cliente.setText(this.tabla_clientes.getValueAt(sel, 12).toString());
             this.txt_delegacion_cliente.setText(this.tabla_clientes.getValueAt(sel, 13).toString());
             this.txt_estado_cliente.setText(this.tabla_clientes.getValueAt(sel, 14).toString());
             this.txt_pais_cliente.setText(this.tabla_clientes.getValueAt(sel, 15).toString());
             this.txt_email_cliente.setText(this.tabla_clientes.getValueAt(sel, 16).toString());*/
            EditarDatosClientes();
            nuevo_cliente = false;
            Jtbn_guardar_cliente.setEnabled(true);

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para editar.  ", "Error  ", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_Jbtn_editar_editarActionPerformed

    private void Jbtn_quitar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_quitar_clienteActionPerformed
        // TODO add your handling code here:
        int sel = tabla_clientes.getSelectedRow();
        System.out.println("sel: " + sel);
        if (sel != -1) {
            String dato_eliminar = (String.valueOf(tabla_clientes.getValueAt(sel, 1))) + " " + String.valueOf(tabla_clientes.getValueAt(sel, 2));
            String id = tabla_clientes.getValueAt(sel, 0).toString();
            int seleccion = JOptionPane.showOptionDialog(this, "¿Eliminar registro: " + dato_eliminar + "?", "Confirme eliminacion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Si", "No"}, "No");
            if ((seleccion + 1) == 1) {
                // //  funcion.conectate();
                funcion.DeleteRegistro("clientes", "id_clientes", id);
                tabla_clientes();
                // funcion.desconecta();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar.  ", "Error  ", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Jbtn_quitar_clienteActionPerformed

    private void Jbtn_agregar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_agregar_clienteActionPerformed
        // TODO add your handling code here:
        if (nuevo_cliente == true) {
            if (this.txt_rfc_cliente.getText().equals("") || this.txt_razon_social_cliente.getText().equals("") || this.txt_pais_cliente.getText().equals("") || txt_nombre_cliente.getText().equals("") || txt_apellidos_cliente.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Faltan parametros", "Error", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (validaFormatoRFC(txt_rfc_cliente.getText().trim()) == true) {
                    if ((txt_cp_cliente.getText().length() != 0) && (txt_cp_cliente.getText().length() == 5)) {
                        if (isEmail(txt_email_cliente.getText())) {
                            String datos[] = {iniciar_sesion.id_usuario_global, this.txt_nombre_cliente.getText(), this.txt_apellidos_cliente.getText(), this.txt_tel_movil_cliente.getText(), this.txt_tel_fijo_cliente.getText(), this.txt_rfc_cliente.getText(), this.txt_razon_social_cliente.getText(), this.txt_calle_cliente.getText(), this.txt_no_ext_cliente.getText(), this.txt_no_int_cliente.getText(), this.txt_colonia_cliente.getText(), this.txt_cp_cliente.getText(), this.txt_localidad_cliente.getText(), this.txt_delegacion_cliente.getText(), this.txt_estado_cliente.getText(), this.txt_pais_cliente.getText(), this.txt_email_cliente.getText()};
                            // //  funcion.conectate();
                            funcion.InsertarRegistro(datos, "insert into clientes (id_empleado,nombre,apellidos,tel_movil,tel_fijo,rfc,razon_social,calle,noExt,noInt,colonia,cp,localidad,delegacion,estado,pais,email) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                            // funcion.desconecta();
                            tabla_clientes();
                            limpiarDatosClientes();
                            this.txt_nombre_cliente.requestFocus();
                            nuevo_cliente = false;
                            // EditarDatosClientes_no();
                        } else {
                            JOptionPane.showMessageDialog(null, "Correo electronico invalido  ", "Error  ", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Codigo postal invalido  ", "Error  ", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "RFC invalido  ", "Error  ", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_Jbtn_agregar_clienteActionPerformed

    private void txt_tel_movil_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tel_movil_clienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tel_movil_clienteActionPerformed

    private void txt_filtro_clientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_filtro_clientesKeyReleased
        // TODO add your handling code here:
        if (check_nombre_cliente.isSelected()) {
            nombre_cliente = true;
        } else {
            nombre_cliente = false;
        }

        tabla_clientes_like();
    }//GEN-LAST:event_txt_filtro_clientesKeyReleased

    private void txt_impuesto_descripcionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_impuesto_descripcionFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(49, true);
        }
    }//GEN-LAST:event_txt_impuesto_descripcionFocusGained

    private void txt_impuesto_tasaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_impuesto_tasaFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(50, true);
        }
    }//GEN-LAST:event_txt_impuesto_tasaFocusGained

    private void txt_nombre_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nombre_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(51, true);
        }
    }//GEN-LAST:event_txt_nombre_clienteFocusGained

    private void txt_apellidos_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_apellidos_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(52, true);
        }
    }//GEN-LAST:event_txt_apellidos_clienteFocusGained

    private void txt_tel_movil_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_tel_movil_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(53, true);
        }
    }//GEN-LAST:event_txt_tel_movil_clienteFocusGained

    private void txt_tel_fijo_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_tel_fijo_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(54, true);
        }
    }//GEN-LAST:event_txt_tel_fijo_clienteFocusGained

    private void txt_rfc_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rfc_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(55, true);
        }
    }//GEN-LAST:event_txt_rfc_clienteFocusGained

    private void txt_razon_social_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_razon_social_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(56, true);
        }
    }//GEN-LAST:event_txt_razon_social_clienteFocusGained

    private void txt_calle_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_calle_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(57, true);
        }
    }//GEN-LAST:event_txt_calle_clienteFocusGained

    private void txt_no_ext_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_no_ext_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(58, true);
        }
    }//GEN-LAST:event_txt_no_ext_clienteFocusGained

    private void txt_no_int_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_no_int_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(59, true);
        }
    }//GEN-LAST:event_txt_no_int_clienteFocusGained

    private void txt_colonia_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_colonia_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(60, true);
        }
    }//GEN-LAST:event_txt_colonia_clienteFocusGained

    private void txt_localidad_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_localidad_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(62, true);
        }
    }//GEN-LAST:event_txt_localidad_clienteFocusGained

    private void txt_delegacion_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_delegacion_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(63, true);
        }
    }//GEN-LAST:event_txt_delegacion_clienteFocusGained

    private void txt_estado_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_estado_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(64, true);
        }
    }//GEN-LAST:event_txt_estado_clienteFocusGained

    private void txt_pais_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_pais_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(65, true);
        }
    }//GEN-LAST:event_txt_pais_clienteFocusGained

    private void txt_email_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_email_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(66, true);
        }
    }//GEN-LAST:event_txt_email_clienteFocusGained

    private void txt_filtro_clientesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_filtro_clientesFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(67, true);
        }
    }//GEN-LAST:event_txt_filtro_clientesFocusGained

    private void txt_lugar_expedicionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_lugar_expedicionFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(68, true);
        }
    }//GEN-LAST:event_txt_lugar_expedicionFocusGained

    private void txt_regimen_fiscalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_regimen_fiscalFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(69, true);
        }
    }//GEN-LAST:event_txt_regimen_fiscalFocusGained

    private void tabla_pdfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_pdfMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tabla_pdfMouseClicked

    private void tabla_pdfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_pdfKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_pdfKeyPressed

    private void tabla_xmlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_xmlMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_xmlMouseClicked

    private void tabla_xmlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_xmlKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_xmlKeyPressed

    private void Jbtn_abrir_pdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_abrir_pdfActionPerformed
        // TODO add your handling code here:
        String ruta;
        int i = tabla_pdf.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Favor de seleccionar una fila");
        } else//de lo contrario si se selecciono la fila
        {

            ruta = String.valueOf(tabla_pdf.getValueAt(i, 2));
            if (!ruta.equals("null")) {
                try {
                    File file = new File(ruta);
                    Desktop.getDesktop().open(file);
                } catch (Exception e) {

                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "No existe ruta para buscar el archivo PDF");
            }
        }
    }//GEN-LAST:event_Jbtn_abrir_pdfActionPerformed

    private void Jbtn_abrir_xmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_abrir_xmlActionPerformed
        // TODO add your handling code here:
        String ruta;
        int i = tabla_xml.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Favor de seleccionar una fila");
        } else//de lo contrario si se selecciono la fila
        {
            ruta = String.valueOf(tabla_xml.getValueAt(i, 2));
            if (!ruta.equals("null")) {
                try {
                    File file = new File(ruta);
                    Desktop.getDesktop().open(file);
                } catch (Exception e) {

                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "No existe ruta para buscar el archivo XML");
            }
        }
    }//GEN-LAST:event_Jbtn_abrir_xmlActionPerformed

    private void Jbtn_actualizar_pdf_xmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_actualizar_pdf_xmlActionPerformed
        // TODO add your handling code here:
        // //  funcion.conectate();
        rutaPDF = funcion.GetData("ruta_pdf", "select ruta_pdf from datos_generales");
        rutaXML = funcion.GetData("ruta_xml", "select ruta_xml from datos_generales");
        // funcion.desconecta();
        mostrarPDF();
        mostrarXML();
    }//GEN-LAST:event_Jbtn_actualizar_pdf_xmlActionPerformed

    private void txt_cp_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cp_clienteFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(61, true);
        }
    }//GEN-LAST:event_txt_cp_clienteFocusGained

    private void Jbtn_enviar_xmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_enviar_xmlActionPerformed
        // TODO add your handling code here:
        int i = tabla_xml.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Favor de seleccionar una fila");
        } else//de lo contrario si se selecciono la fila
        {
            rutaXML_email = String.valueOf(tabla_xml.getValueAt(i, 2));
            if (!rutaXML_email.equals("null")) {
                status = false;
                mostrar_enviar_email();
            } else {
                JOptionPane.showMessageDialog(null, "No existe ruta para enviar el archivo XML");
            }
        }
    }//GEN-LAST:event_Jbtn_enviar_xmlActionPerformed

    private void Jbtn_enviar_pdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_enviar_pdfActionPerformed
        // TODO add your handling code here:

        int i = tabla_pdf.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Favor de seleccionar una fila");
        } else//de lo contrario si se selecciono la fila
        {

            rutaPDF_email = String.valueOf(tabla_pdf.getValueAt(i, 2));
            if (!rutaPDF_email.equals("null")) {
                status = true;
                mostrar_enviar_email();
            } else {
                JOptionPane.showMessageDialog(null, "No existe ruta para enviar el archivo PDF");
            }
        }

    }//GEN-LAST:event_Jbtn_enviar_pdfActionPerformed

    private void check_gmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_gmailActionPerformed
        // TODO add your handling code here:
        this.txt_servidor_email.setText("");
        this.txt_puerto_email.setText("");
        this.txt_servidor_email.setText("smtp.gmail.com");
        this.txt_puerto_email.setText("587");
        this.check_conexion_tls.setSelected(true);
        this.check_autenticacion.setSelected(true);

        this.check_conexion_tls.setEnabled(false);
        this.check_autenticacion.setEnabled(false);

        this.txt_servidor_email.setEnabled(false);
        this.txt_puerto_email.setEnabled(false);
    }//GEN-LAST:event_check_gmailActionPerformed

    private void check_gmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_check_gmailMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_check_gmailMouseClicked

    private void Jbtn_editar_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_editar_emailActionPerformed
        // TODO add your handling code here:
        editarDatosEmail();
        this.Jbtn_guardar_email.setVisible(true);
        this.Jbtn_editar_email.setVisible(false);
        Jbtn_cancelar_email.setVisible(true);
    }//GEN-LAST:event_Jbtn_editar_emailActionPerformed

    private void Jbtn_guardar_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_guardar_emailActionPerformed
        // TODO add your handling code here:
        String conexion_tls = "0", autenticacion = "0", gmail = "0", hotmail = "0", personalizada = "0";
        if (this.check_conexion_tls.isSelected()) {
            conexion_tls = "1";
        }
        if (this.check_autenticacion.isSelected()) {
            autenticacion = "1";
        }
        if (this.check_gmail.isSelected()) {
            gmail = "1";
        }
        if (this.check_hotmail.isSelected()) {
            hotmail = "1";
        }
        if (this.check_personalizada.isSelected()) {
            personalizada = "1";
        }

        if (!this.txt_cuenta_email.getText().equals("") && !this.txt_contraseña_email.getText().equals("") && !this.txt_servidor_email.getText().equals("") && !this.txt_puerto_email.getText().equals("")) {
            if (this.txt_contraseña_email.getText().equals(this.txt_verificar_contraseña_email.getText())) {

                // //  funcion.conectate();
                boolean existe = funcion.existe_email();
                System.out.println("existe: " + existe);
                if (existe == true) {
                    String datos[] = {this.txt_cuenta_email.getText(), this.txt_contraseña_email.getText(), this.txt_servidor_email.getText(), this.txt_puerto_email.getText(), conexion_tls, autenticacion, gmail, hotmail, personalizada, "1"};
                    funcion.UpdateRegistro(datos, "update email set cuenta_correo=?,contraseña=?,servidor=?,puerto=?,utiliza_conexion_TLS=?,utiliza_autenticacion=?,gmail=?,hotmail=?,personalizada=? where id_email=? ");

                } else {
                    String datos2[] = {this.txt_cuenta_email.getText(), this.txt_contraseña_email.getText(), this.txt_servidor_email.getText(), this.txt_puerto_email.getText(), conexion_tls, autenticacion, gmail, hotmail, personalizada};
                    funcion.InsertarRegistro(datos2, "insert into email (cuenta_correo,contraseña,servidor,puerto,utiliza_conexion_TLS,utiliza_autenticacion,gmail,hotmail,personalizada) values (?,?,?,?,?,?,?,?,?) ");
                }
                this.Jbtn_guardar_email.setVisible(false);
                this.Jbtn_editar_email.setVisible(true);
                Jbtn_cancelar_email.setVisible(false);
                editarDatosEmail_no();

                // funcion.desconecta();
                JOptionPane.showMessageDialog(null, "Se guardo con exito. =) ");
            } else {
                JOptionPane.showMessageDialog(null, "No coincide la contraseña ");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Faltan parametros..");
        }

    }//GEN-LAST:event_Jbtn_guardar_emailActionPerformed

    private void check_hotmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_hotmailActionPerformed
        // TODO add your handling code here:
        this.txt_servidor_email.setText("");
        this.txt_puerto_email.setText("");
        this.txt_servidor_email.setText("smtp.live.com");
        this.txt_puerto_email.setText("587");
        this.check_conexion_tls.setSelected(true);
        this.check_autenticacion.setSelected(true);

        this.check_conexion_tls.setEnabled(false);
        this.check_autenticacion.setEnabled(false);
        this.txt_servidor_email.setEnabled(false);
        this.txt_puerto_email.setEnabled(false);
    }//GEN-LAST:event_check_hotmailActionPerformed

    private void check_personalizadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_personalizadaActionPerformed
        // TODO add your handling code here:
        this.txt_servidor_email.setText("");
        this.txt_puerto_email.setText("");
        this.txt_servidor_email.setText("");
        this.txt_puerto_email.setText("");
        this.check_conexion_tls.setSelected(false);
        this.check_autenticacion.setSelected(false);

        this.check_conexion_tls.setEnabled(true);
        this.check_autenticacion.setEnabled(true);
        this.txt_servidor_email.setEnabled(true);
        this.txt_puerto_email.setEnabled(true);
        this.txt_servidor_email.requestFocus();
    }//GEN-LAST:event_check_personalizadaActionPerformed

    private void Jbtn_cancelar_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_cancelar_emailActionPerformed
        // TODO add your handling code here:
        traer_datos_email();
        editarDatosEmail_no();
        this.Jbtn_cancelar_email.setVisible(false);
        this.Jbtn_editar_email.setVisible(true);
        this.Jbtn_guardar_email.setVisible(false);
    }//GEN-LAST:event_Jbtn_cancelar_emailActionPerformed

    private void Jbtn_enviar_prueba_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_enviar_prueba_emailActionPerformed
        // TODO add your handling code here:
        panel_enviar_prueba.setVisible(true);
        txt_enviar_a.requestFocus();

    }//GEN-LAST:event_Jbtn_enviar_prueba_emailActionPerformed

    private void Jbnt_enviar_pruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbnt_enviar_pruebaActionPerformed
        // TODO add your handling code here:
        if (!this.txt_enviar_a.getText().equals("")) {

            if (this.check_conexion_tls.isSelected()) {
                utiliza_conexion_TLS = true;
            } else {
                utiliza_conexion_TLS = false;
            }

            if (this.check_autenticacion.isSelected()) {
                utiliza_autenticacion = true;
            } else {
                utiliza_autenticacion = false;
            }

            JCMail_enviar_prueba mail_prueba = new JCMail_enviar_prueba();
            mail_prueba.setFrom(this.txt_cuenta_email.getText());
            mail_prueba.setPassword(this.txt_contraseña_email.getPassword());
            mail_prueba.setTo(this.txt_enviar_a.getText());
            mail_prueba.setSubject(this.txt_asunto.getText());
            mail_prueba.setMessage(this.txt_mensaje.getText());
            //mail.setArchive(this.txta.getText());
            mail_prueba.SEND();

        } else {
            JOptionPane.showMessageDialog(null, "Faltan parametros..");
        }

        panel_enviar_prueba.setVisible(false);
        this.txt_enviar_a.setText("");
        this.txt_asunto.setText("");
        this.txt_mensaje.setText("");


    }//GEN-LAST:event_Jbnt_enviar_pruebaActionPerformed

    private void txt_cuenta_emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cuenta_emailFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(70, true);
        }
    }//GEN-LAST:event_txt_cuenta_emailFocusGained

    private void txt_contraseña_emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_contraseña_emailFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(71, true);
        }
    }//GEN-LAST:event_txt_contraseña_emailFocusGained

    private void txt_verificar_contraseña_emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_verificar_contraseña_emailFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(72, true);
        }
    }//GEN-LAST:event_txt_verificar_contraseña_emailFocusGained

    private void txt_servidor_emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_servidor_emailFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(73, true);
        }
    }//GEN-LAST:event_txt_servidor_emailFocusGained

    private void txt_puerto_emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_puerto_emailFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(74, true);
        }
    }//GEN-LAST:event_txt_puerto_emailFocusGained

    private void Jbtn_ver_notasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_ver_notasActionPerformed
        // TODO add your handling code here:
        if (hubo_cambios_venta == false) {
            if (tabla_venta.getSelectedRow() != -1) {
                if (!id_detalle_categoria_global.equals("null")) {
                    mostrar_notas_platillos();
                } else {
                    JOptionPane.showMessageDialog(null, "Favor de seleccionar el producto nuevamente.. =( ", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona una fila para ver las notas del platillo ", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Guarda la venta, para ver las notas y/o editar ", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        if (validar_ventana_notas == true) {
            tabla_venta();
            validar_ventana_notas = false;
        }
    }//GEN-LAST:event_Jbtn_ver_notasActionPerformed

    private void tabla_ventaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ventaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_ventaMouseEntered

    public boolean imprimirTicket() throws Exception {
        // Armado de ticket
        DecimalFormat format = new DecimalFormat("###,###.##");
//        if (/*se_guardo == true ||*/hubo_cambios_venta == false /*&& validar == false*/) {   
            
        if (hubo_cambios_venta == true ) {
            JOptionPane.showMessageDialog(null, "Guarda la venta, para generar el ticket ", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }   
            StringBuilder articuloT = new StringBuilder();
            System.out.println("articulos 1: " + articuloT);
            int filas = tabla_venta.getRowCount();
            String ar,cant,precio,xPrecio = null;
            for (int ib = 0; ib < filas; ib++) {
                ar = (String) tabla_venta.getValueAt(ib, 2);
                cant = (String) tabla_venta.getValueAt(ib, 1).toString();
                precio = (String) tabla_venta.getValueAt(ib, 4).toString();
                // precio del articulo
                xPrecio = (String) tabla_venta.getValueAt(ib, 3).toString();

                
                System.out.println("Numero: "+precio+" FORMATO: "+format.format(Float.parseFloat(precio)));
                
                precio = format.format(Float.parseFloat(precio));
                xPrecio = format.format(Float.parseFloat(xPrecio));
                
                 if (xPrecio.length() == 1)
                    xPrecio = "    " + xPrecio;
                
                if (xPrecio.length() == 2)
                    xPrecio = "   " + xPrecio;
                
                if (xPrecio.length() == 3)
                    xPrecio = "  " + xPrecio;
                
                
                 if (precio.length() == 1)
                    precio = "    " + precio;
                
                if (precio.length() == 2)
                    precio = "   " + precio;
                
                if (precio.length() == 3)
                    precio = "  " + precio;
                
                
                
                if (cant.length() == 1)
                    cant = " " + cant;
                
                               
               /* if (precio.length() == 1) {
                    precio = "    " + precio;
                }
                if (precio.length() == 2) {
                    precio = "   " + precio;
                }
                if (precio.length() == 3) {
                    precio = "  " + precio;
                }
                */

                if (ar.length() >= 18) {
//                    articuloT.append("\n" + cant + " " + ar.substring(0, 18) + "    $ " + precio);
                    articuloT.append("\n").
                            append(cant).
                            append(" ").
                            append(ar.toUpperCase().substring(0, 17)).
                            append(" ").
                            append(xPrecio).
                            append(" ").
                            append(precio);
                }
                // 2018.11.06 se agrega el precio del articulo
//                if (ar.length() >= 18) {                   
//                    // vamos a ganar espacio :)
//                    int espacio = 5 - xPrecio.replaceAll(" ", "").length(); 
//                    int substring = 17 + espacio;
//                    xPrecio = xPrecio.replaceAll(" ", "");
//                    
//                    articuloT.append("\n").append(cant).append(" ").append(ar.substring(0, substring).toLowerCase()).append(" ").append(xPrecio).append(" ").append(precio);
//                }
                if (ar.length() < 18) {
                    ar = String.format("%1$-18s", ar);
                    articuloT.append("\n").append(cant).append(" ").append(ar.toUpperCase()).append(xPrecio).append(" ").append(precio);
                }
//                if (ar.length() < 18) {
//                    ar = String.format("%1$-18s", ar);
//                    articuloT.append("\n" + cant + " " + ar + "    $ " + precio);
//                }
                
                ar=null;cant=null;precio=null;xPrecio=null;
            }

            hora_sistema();
            if (this.check_ticket_personalizado.isSelected()) {
                segundo_arreglo();
                mostrar_nueva_orden();
            } else {
                segundo_arreglo();
                Ticket vaucher = new Ticket();
                System.out.println("articulos 2: " + articuloT);
                String direccion = (this.txt_calle.getText() + " " + this.txt_no_exterior.getText() + " " + this.txt_no_interior.getText());
                System.out.println("direccion: " + direccion);
                String direccion2 = ("Col. " + this.txt_colonia.getText() + " C.P. " + this.txt_codigo_postal.getText());
                String direccion3 = (this.txt_ciudad.getText() + " " + this.txt_estado.getText());

                //String subtotal= txt_total_pagar.getText().toString();
                String descuento = EliminaCaracteres(txt_descuento.getText().toString(),",$");
                String cargo = EliminaCaracteres(txt_cargo.getText().toString(), ",$");
                String propina = EliminaCaracteres(txt_total_propina.getText(), ",$");
                String subtotal = EliminaCaracteres(txt_total_pagar.getText(), "$");
                String total = EliminaCaracteres(txt_total.getText(), "$");
                String porcentaje_propina = txt_propina.getText().toString();

                String cambio = EliminaCaracteres(txt_cambio.getText(), "$");
                String recibido = EliminaCaracteres(txt_dinero_recibido.getText().toString(),"$,");
                System.out.println("Recibido es: " + recibido);
                System.out.println("Cambio es: " + cambio);
                System.out.println("Propina es: " + propina);
                
                
                if (cambio.equals("") || cambio.equals("0") || cambio.equals("0.00"))
                    cambio = "";
                else{
                    cambio = format.format(Float.parseFloat(cambio));
                    cambio = String.format("%1$9s", cambio);
                }
                
                if (recibido.equals("") || recibido.equals("0") || recibido.equals("0.00"))
                    recibido = "";
                else {
                    recibido = format.format(Float.parseFloat(recibido));
                    recibido = String.format("%1$9s", recibido);
                }
                
                if (descuento.equals("") || descuento.equals("0") || descuento.equals("0.00"))
                    descuento="";
                else{
                    descuento = format.format(Float.parseFloat(descuento));
                    descuento = String.format("%1$9s", descuento);
                }
                
                 if (cargo.equals("") || cargo.equals("0") || cargo.equals("0.00"))
                    cargo="";
                 else{
                    cargo = format.format(Float.parseFloat(cargo));
                    cargo = String.format("%1$9s", cargo);
                }
                 
                 if(propina.equals("") || propina.equals("0") || propina.equals("0.00"))
                    propina="";
                 else{
                    propina = format.format(Float.parseFloat(propina));
                    propina = String.format("%1$9s", propina);
                 }
                
                if (subtotal.length() > 9)
                    subtotal = subtotal.substring(0, 9);
                else
                    subtotal = String.format("%1$9s", subtotal);
                

//                if (descuento.length() > 9) {
//                    descuento = descuento.substring(0, 9);
//                } else {
//                    descuento = String.format("%1$9s", descuento);
//                }

//                if (cargo.length() > 9) {
//                    cargo = cargo.substring(0, 9);
//                } else {
//                    cargo = String.format("%1$9s", cargo);
//                }

                if (total.length() > 9)
                    total = total.substring(0, 9);
                else
                    total = String.format("%1$9s", total);
           

//                if (propina.length() > 9) {
//                    propina = propina.substring(0, 9);
//                } else {
//                    propina = String.format("%1$9s", propina);
//                }

//                if (recibido.length() > 9) {
//                    recibido = recibido.substring(0, 9);
//                } else {
//                    recibido = String.format("%1$9s", recibido);
//                }

//                if (cambio.length() > 9) {
//                    cambio = cambio.substring(0, 9);
//                } else {
//                    cambio = String.format("%1$9s", cambio);
//                }

                if (porcentaje_propina.length() == 2)
                    porcentaje_propina = " " + porcentaje_propina;

                if (porcentaje_propina.length() == 1)
                    porcentaje_propina = "  " + porcentaje_propina;

                vaucher.generar_ticket(this.txt_razon_social.getText().toString(), 
                        this.txt_rfc.getText().toString(), direccion, direccion2, direccion3, 
                        this.txt_lugar_expedicion.getText().toString(), 
                        this.txt_tel1.getText().toString(), "1", id_venta_global, 
                        this.lbl_nombre_atiende.getText().toString(), fecha_sistema, 
                        hora_sistema, articuloT.toString(), subtotal, descuento, cargo, total, 
                        propina, porcentaje_propina, recibido, cambio, "Efectivo", 
                        txt_leyenda1.getText().toString(), txt_leyenda2.getText().toString(), 
                        txt_leyenda3.getText().toString(), txt_leyenda4.getText().toString(), 
                        txt_leyenda5.getText().toString(), lbl_nombre_cliente.getText().toString(), 
                        lbl_mesa.getText().toString());
                vaucher.print();
                JOptionPane.showMessageDialog(null, "Se ha generado el ticket correctamente ", "TICKET", JOptionPane.INFORMATION_MESSAGE);
            }
//        } else {
//            JOptionPane.showMessageDialog(null, "Guarda la venta, para generar el ticket ", "Error", JOptionPane.INFORMATION_MESSAGE);
//        }
        
        return true;
    }
    
    private void Jbtn_generar_ticketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_generar_ticketActionPerformed
        try {       
            imprimirTicket();             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al enviar los datos a la impresora.\nINFORME DEL ERROR:\n"+e, "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Jbtn_generar_ticketActionPerformed

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalActionPerformed

    private void txt_aplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_aplicarActionPerformed
        // TODO add your handling code here:
        calcular();
    }//GEN-LAST:event_txt_aplicarActionPerformed

    private void txt_descuentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descuentoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            if (se_guardo == true || validar == false) {
                aplicar_descuento();
                // hubo_cambios_venta = false;
            } else {
                JOptionPane.showMessageDialog(null, "Guarda la venta, para realizar la accion ", "Error", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }//GEN-LAST:event_txt_descuentoKeyPressed

    private void txt_cargoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cargoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            if (se_guardo == true || validar == false) {
                aplicar_descuento();
                // hubo_cambios_venta = false;
            } else {
                JOptionPane.showMessageDialog(null, "Guarda la venta, para realizar la accion ", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_txt_cargoKeyPressed

    private void Jbtn_quitar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_quitar1ActionPerformed
        // TODO add your handling code here:
        /*if (iniciar_sesion.administrador_global.equals("1")) {
         Jbtn_quitar.setEnabled(true);
         Jbtn_cancelar_venta.setEnabled(true);
         Jbtn_resta.setEnabled(true);
         } else {
         mostrar_validar_administrador();
         if (validar_administrador == true) {
         Jbtn_quitar.setEnabled(true);
         Jbtn_cancelar_venta.setEnabled(true);
         Jbtn_resta.setEnabled(true);
         }

         }*/
    }//GEN-LAST:event_Jbtn_quitar1ActionPerformed

    private void txt_descuentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_descuentoFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(78, true);
        }
    }//GEN-LAST:event_txt_descuentoFocusGained

    private void txt_cargoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_cargoFocusGained
        // TODO add your handling code here:
        if (keyboard != null) {
            keyboard.setComponentSelected(79, true);
        }
    }//GEN-LAST:event_txt_cargoFocusGained

    private void txt_total_propinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_total_propinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_total_propinaActionPerformed

    private void txt_propinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_propinaActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_propinaActionPerformed

    private void txt_propinaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_propinaKeyPressed
        // TODO add your handling code here:f
        if (evt.getKeyCode() == 10) {
            calcular();
        }
    }//GEN-LAST:event_txt_propinaKeyPressed

    private void Jbtn_reporte_insumosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_reporte_insumosActionPerformed
        // TODO add your handling code here:
        mostrar_reporte_insumos();
    }//GEN-LAST:event_Jbtn_reporte_insumosActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        int opcion = JOptionPane.showConfirmDialog(this, "Desea cerrar la ventana actual", "Restaurant - Chilpo Systems", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
        }
// TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void Jbtn_recuperar_ticketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_recuperar_ticketActionPerformed
        // TODO add your handling code here:

        if (tabla_consultar_venta.getSelectedRow() != -1) {
            float sub = 0;
            int sel = tabla_consultar_venta.getSelectedRow();
            String id = tabla_consultar_venta.getValueAt(sel, 0).toString();

            String articuloT = "";
            System.out.println("articulos 1: " + articuloT);
            Object[][] res;
            String colName[] = {"cantidad", "descripcion", "precio_publico", "Importe"};
            res = funcion.GetTabla(colName, "detalle_venta", "SELECT d.`cantidad`, dc.`descripcion`, dc.`precio_publico`,(d.`cantidad` * dc.`precio_publico`) AS Importe FROM detalle_venta d, detalle_categorias dc\n"
                    + "WHERE d.id_detalle_categorias=dc.id_detalle_categorias AND d.`id_venta`=" + id + "");

            for (int j = 0; j < res.length; j++) {
                String ar = (String) res[j][1].toString();
                String cant = (String) res[j][0].toString();
                String precio = (String) res[j][3].toString();
                sub = sub + Float.parseFloat(res[j][3].toString());
                if (cant.length() == 1) {
                    cant = " " + cant;
                }
                if (precio.length() == 1) {
                    precio = "   " + precio;
                }
                if (precio.length() == 2) {
                    precio = "  " + precio;
                }
                if (precio.length() == 3) {
                    precio = " " + precio;
                }

                if (ar.length() >= 18) {
                    articuloT = articuloT + "\n" + cant + " " + ar.substring(0, 18) + "    $ " + precio;

                }
                if (ar.length() < 18) {
                    ar = String.format("%1$-18s", ar);
                    articuloT = articuloT + "\n" + cant + " " + ar + "    $ " + precio;
                }

            }
            //String colName1[] = {"cargo", "descuento","propina"};
            //res1 = funcion.GetTabla(colName1, "venta", "SELECT v.`cargo`, v.`descuento`, v.`propina` FROM venta v WHERE id_venta = " + id + "");
            Ticket vaucher = new Ticket();
            System.out.println("articulos 2: " + articuloT);
            String direccion = (this.txt_calle.getText() + " " + this.txt_no_exterior.getText() + " " + this.txt_no_interior.getText());
            System.out.println("direccion: " + direccion);
            String direccion2 = ("Col. " + this.txt_colonia.getText() + " C.P. " + this.txt_codigo_postal.getText());
            String direccion3 = (this.txt_ciudad.getText() + " " + this.txt_estado.getText());

            //String subtotal= txt_total_pagar.getText().toString();
            String descuento = String.valueOf(funcion.GetData("descuento", "SELECT v.`descuento` FROM venta v WHERE id_venta =" + id + ""));
            String cargo = String.valueOf(funcion.GetData("cargo", "SELECT v.`cargo` FROM venta v WHERE id_venta =" + id + ""));
            String porcentaje_propina = String.valueOf(funcion.GetData("propina", "SELECT v.`propina` FROM venta v WHERE id_venta =" + id + ""));
            String atendio = funcion.GetData("nombre", "SELECT CONCAT(e.`nombre`,\" \", e.`apellidos`)AS nombre FROM venta v, empleado e\n"
                    + "WHERE v.id_empleado=e.id_empleado AND id_venta =" + id + "");

            String subtotal = String.valueOf(sub);

            if (descuento.equals("null")) {
                descuento = "0.00";
            }
            if (cargo.equals("null")) {
                cargo = "0.00";
            }
            if (porcentaje_propina.equals("null")) {
                porcentaje_propina = "0.0";
            }

            Float auxDescuento = Float.parseFloat(descuento);
            Float auxCargo = Float.parseFloat(cargo);
            Float auxPorPropina = Float.parseFloat(porcentaje_propina) / 100;
            Float auxSubtotal = Float.parseFloat(subtotal);

            Float auxPropina = auxSubtotal * auxPorPropina;
            Float auxTotal = (auxSubtotal - auxDescuento) + auxCargo + auxPropina;
            String total = String.valueOf(auxTotal);
            String propina = String.valueOf(auxPropina);

            if (subtotal.length() > 9) {
                subtotal = subtotal.substring(0, 9);
            } else {
                subtotal = String.format("%1$9s", subtotal);
            }

            if (descuento.length() > 9) {
                descuento = descuento.substring(0, 9);
            } else {
                descuento = String.format("%1$9s", descuento);
            }

            if (cargo.length() > 9) {
                cargo = cargo.substring(0, 9);
            } else {
                cargo = String.format("%1$9s", cargo);
            }

            if (total.length() > 9) {
                total = total.substring(0, 9);
            } else {
                total = String.format("%1$9s", total);
            }

            if (propina.length() > 9) {
                propina = propina.substring(0, 9);
            } else {
                propina = String.format("%1$9s", propina);
            }

            if (porcentaje_propina.length() == 2) {
                porcentaje_propina = " " + porcentaje_propina;
            }
            if (porcentaje_propina.length() == 1) {
                porcentaje_propina = "  " + porcentaje_propina;
            }
            fecha_sistema();
            hora_sistema();
            // funcion.desconecta();
            System.out.println("El subtotal es: " + subtotal);
            vaucher.generar_ticket(this.txt_razon_social.getText().toString(), this.txt_rfc.getText().toString(), direccion, direccion2, direccion3, this.txt_lugar_expedicion.getText().toString(), this.txt_tel1.getText().toString(), "1", id, atendio, fecha_sistema, hora_sistema, articuloT, subtotal, descuento, cargo, total, propina, porcentaje_propina, "0", "0", "Efectivo", txt_leyenda1.getText().toString(), txt_leyenda2.getText().toString(), txt_leyenda3.getText().toString(), txt_leyenda4.getText().toString(), txt_leyenda5.getText().toString(), lbl_nombre_cliente.getText().toString(), (String) tabla_consultar_venta.getValueAt(tabla_consultar_venta.getSelectedRow(), 4));
            vaucher.print();
            JOptionPane.showMessageDialog(null, "Se ha generado el ticket correctamente ", "TICKET", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(null, "Selecciona una fila para generar el ticket  ", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Jbtn_recuperar_ticketActionPerformed

    private void txt_dinero_recibidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dinero_recibidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dinero_recibidoActionPerformed

    private void txt_cambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cambioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cambioActionPerformed

    private void txt_dinero_recibidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_dinero_recibidoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            String auxTotal = EliminaCaracteres(txt_total.getText(), "$,");
            float total = Float.parseFloat(auxTotal);
            float recibido = Float.parseFloat(txt_dinero_recibido.getText().toString());
            float cambio = recibido - total;
            if (recibido < total) {
                JOptionPane.showMessageDialog(null, "No se puede calcular.  ", "Error", JOptionPane.INFORMATION_MESSAGE);
            } else {
                txt_cambio.setValue(cambio);
            }
        }
    }//GEN-LAST:event_txt_dinero_recibidoKeyPressed

    private void jbtn_mostrar_mesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_mostrar_mesasActionPerformed

        // TODO add your handling code here:
        mostrar_mesas();
        if (validar_mesas == true) {
            mesas_itemDB.llenar();
            addMainMenue_mesas();
            validar_mesas = false;
        }

    }//GEN-LAST:event_jbtn_mostrar_mesasActionPerformed

    private void txt_nombre_insumoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombre_insumoKeyReleased
        // TODO add your handling code here:
        insumos_like = txt_nombre_insumo.getText().toString();
        tablaInsumos_like1();
    }//GEN-LAST:event_txt_nombre_insumoKeyReleased

    private void txt_descripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descripcionKeyReleased
        // TODO add your handling code here:
        tablaDetalleCategorias_like();
        if (nuevo == true) {
            if (evt.getKeyCode() == 10) {

                agregar_detalle_categorias();

            }
            if (evt.getKeyCode() == 20) {
                JOptionPane.showMessageDialog(this, "Bloq-Mayus Activada.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_txt_descripcionKeyReleased

    private void txt_descripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_descripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descripcionActionPerformed

    private void txt_agregar_categoriaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_agregar_categoriaKeyReleased
        tablaCategorias_like();
    }//GEN-LAST:event_txt_agregar_categoriaKeyReleased

    private void jbtn_insumos_vendidos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_insumos_vendidos1ActionPerformed
        // TODO add your handling code here:
        if ((txt_fecha_inicial_caja.getDate() == null) || (txt_fecha_final_caja.getDate() == null)) {
            JOptionPane.showMessageDialog(null, "Faltan parametros");
        } else {

            tabla_consultar_venta_caja1();

        }
    }//GEN-LAST:event_jbtn_insumos_vendidos1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (tabla_empleados.getSelectedRow() != -1) {
            mostrar_permisos_empleados();
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un usuario para ver los permisos...");
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtFindCodeProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindCodeProductActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txtFindCodeProductActionPerformed

    private void txtFindCodeProductKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindCodeProductKeyReleased
        txtFindCodeProduct.setText(txtFindCodeProduct.getText().replaceAll("[^0-9]", ""));        
        if (evt.getKeyCode() == 10) { 
            if(!txtFindCodeProduct.getText().isEmpty()){            
             String codeProduct = this.txtFindCodeProduct.getText();            
             if(!addItems(codeProduct)){
              JOptionPane.showMessageDialog(null,"No se encontro el elemento en la bd");
             }else{
               txtFindCodeProduct.setText("");
             }
            }else{
                JOptionPane.showMessageDialog(null,"Introduce el codigo a agregar");
            }
        }
       
    }//GEN-LAST:event_txtFindCodeProductKeyReleased

    private void txtFindCodeProductKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindCodeProductKeyPressed
       
    }//GEN-LAST:event_txtFindCodeProductKeyPressed

    private void jbtnBuscarElementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBuscarElementoActionPerformed
        mostrar_buscar_elemento();
    }//GEN-LAST:event_jbtnBuscarElementoActionPerformed

    private void txt_descuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_descuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descuentoActionPerformed

    private void txt_descuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descuentoKeyReleased
       txt_descuento.setText(txt_descuento.getText().replaceAll("[^0-9.]", ""));
    }//GEN-LAST:event_txt_descuentoKeyReleased

    private void txt_cargoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cargoKeyReleased
         txt_cargo.setText(txt_cargo.getText().replaceAll("[^0-9.]", ""));
    }//GEN-LAST:event_txt_cargoKeyReleased

    private void txt_propinaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_propinaKeyReleased
       txt_propina.setText(txt_propina.getText().replaceAll("[^0-9.]", ""));
    }//GEN-LAST:event_txt_propinaKeyReleased

    private void txt_dinero_recibidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_dinero_recibidoKeyReleased
        txt_dinero_recibido.setText(txt_dinero_recibido.getText().replaceAll("[^0-9.]", ""));
    }//GEN-LAST:event_txt_dinero_recibidoKeyReleased

    private void JbtnCancelarVentaHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnCancelarVentaHistorialActionPerformed
        
            if(tabla_consultar_venta.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null, "Selecciona una fila.. ", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            if(tabla_consultar_venta.getValueAt(tabla_consultar_venta.getSelectedRow(), 7).toString().toLowerCase().equals("cancelado")){
                JOptionPane.showMessageDialog(null, "Esta venta ya esta CANCELADA ", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        
            int seleccion = JOptionPane.showOptionDialog(this, "Cancelar venta, ¿Deseas continuar? /- No podrás deshacer esta acción", "Advertencia...", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Si", "No"}, "No");
           
            if ((seleccion + 1) == 1) { //presiono que si  
                String idVenta = tabla_consultar_venta.getValueAt(tabla_consultar_venta.getSelectedRow(), 0).toString();
                // //  funcion.conectate();
                String datos[] = { ApplicationConstants.ESTADO_VENTA_CANCELADO+"" , idVenta};
                funcion.UpdateRegistro(datos, "UPDATE venta SET id_estado_venta=? where id_venta=?"); //la venta la ponemos en cancelada
                
                // aumentamos el stock
                float cant = 0;
                float total_consumible = 0;
                String colName[] = {"cantidad", "id_insumos"};
                Object[][] resultado = funcion.GetTabla(colName, "consumibles_platillos", "select cantidad,id_insumos from consumibles_platillos where id_detalle_cat =" + idVenta + "");

                for (int j = 0; j < resultado.length; j++) {

                    System.out.print("J: " + j);
                    total_consumible = cant * Float.parseFloat(resultado[j][0].toString());

                    System.out.print("cantidad del insumo " + resultado[j][0]);

                    System.out.print("ID del insumo " + resultado[j][1]);
                    String stock_viejo = funcion.GetData("stock", "select stock from insumos where id_insumos=" + resultado[j][1].toString() + "");
                    System.out.print("Stock viejo " + stock_viejo);
                    float stock_nuevo = Float.parseFloat(stock_viejo) + total_consumible;
                    System.out.print("Stock nuevo " + stock_nuevo);

                    String datos_insumos[] = {Float.toString(stock_nuevo), resultado[j][1].toString()};

                    funcion.UpdateRegistro(datos_insumos, "update insumos set stock =? where id_insumos=? ");

                }
                // funcion.desconecta();
                tabla_consultar_venta_caja();
            }
     
    }//GEN-LAST:event_JbtnCancelarVentaHistorialActionPerformed

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
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Jbnt_enviar_prueba;
    private javax.swing.JButton JbtnCancelarVentaHistorial;
    private javax.swing.JButton Jbtn_abrir_pdf;
    private javax.swing.JButton Jbtn_abrir_xml;
    private javax.swing.JButton Jbtn_actualizar_logo;
    private javax.swing.JButton Jbtn_actualizar_pdf_xml;
    private javax.swing.JButton Jbtn_actualizar_series;
    private javax.swing.JButton Jbtn_agregar_categoria;
    private javax.swing.JButton Jbtn_agregar_cliente;
    private javax.swing.JButton Jbtn_agregar_compras;
    private javax.swing.JButton Jbtn_agregar_datos;
    private javax.swing.JButton Jbtn_agregar_detalle;
    private javax.swing.JButton Jbtn_agregar_empleado;
    private javax.swing.JButton Jbtn_agregar_insumos;
    private javax.swing.JButton Jbtn_aperturar_caja;
    private javax.swing.JButton Jbtn_asignar_contraseña;
    private javax.swing.JButton Jbtn_buscar_fecha;
    private javax.swing.JButton Jbtn_cambiar_contraseña;
    private javax.swing.JButton Jbtn_cambiar_logo;
    private javax.swing.JButton Jbtn_cancelar_email;
    private javax.swing.JButton Jbtn_cancelar_venta;
    private javax.swing.JButton Jbtn_cerrar_caja;
    private javax.swing.JButton Jbtn_cerrar_venta;
    private javax.swing.JButton Jbtn_editar_categoria;
    private javax.swing.JButton Jbtn_editar_datos;
    private javax.swing.JButton Jbtn_editar_detalle;
    private javax.swing.JButton Jbtn_editar_editar;
    private javax.swing.JButton Jbtn_editar_email;
    private javax.swing.JButton Jbtn_editar_empleado;
    private javax.swing.JButton Jbtn_editar_insumos;
    private javax.swing.JButton Jbtn_eliminar_monto;
    private javax.swing.JButton Jbtn_enviar_pdf;
    private javax.swing.JButton Jbtn_enviar_prueba_email;
    private javax.swing.JButton Jbtn_enviar_xml;
    private javax.swing.JButton Jbtn_generar_nota;
    private javax.swing.JButton Jbtn_generar_nota1;
    private javax.swing.JButton Jbtn_generar_nota_por_fecha;
    private javax.swing.JButton Jbtn_generar_ticket;
    private javax.swing.JButton Jbtn_guardar_email;
    private javax.swing.JButton Jbtn_guardar_venta1;
    private javax.swing.JButton Jbtn_logo;
    private javax.swing.JButton Jbtn_modificar_series;
    private javax.swing.JButton Jbtn_nuevo_categoria;
    private javax.swing.JButton Jbtn_nuevo_cliente;
    private javax.swing.JButton Jbtn_nuevo_detalle;
    private javax.swing.JButton Jbtn_nuevo_empleado;
    private javax.swing.JButton Jbtn_nuevo_insumo;
    private javax.swing.JButton Jbtn_quitar;
    private javax.swing.JButton Jbtn_quitar1;
    private javax.swing.JButton Jbtn_quitar_categoria;
    private javax.swing.JButton Jbtn_quitar_cliente;
    private javax.swing.JButton Jbtn_quitar_compras;
    private javax.swing.JButton Jbtn_quitar_detalle;
    private javax.swing.JButton Jbtn_quitar_empleado;
    private javax.swing.JButton Jbtn_quitar_insumo;
    private javax.swing.JButton Jbtn_quitar_insumo_relacion;
    private javax.swing.JButton Jbtn_recuperar_ticket;
    private javax.swing.JButton Jbtn_refrescar;
    private javax.swing.JButton Jbtn_registrar_caja;
    private javax.swing.JButton Jbtn_relacionar;
    private javax.swing.JButton Jbtn_reporte_caja;
    private javax.swing.JButton Jbtn_reporte_compras;
    private javax.swing.JButton Jbtn_reporte_insumos;
    private javax.swing.JButton Jbtn_resta;
    private javax.swing.JButton Jbtn_suma;
    private javax.swing.JButton Jbtn_ver_notas;
    private javax.swing.JButton Jtbn_guardar_categoria;
    private javax.swing.JButton Jtbn_guardar_cliente;
    private javax.swing.JButton Jtbn_guardar_datos;
    private javax.swing.JButton Jtbn_guardar_detalle;
    private javax.swing.JButton Jtbn_guardar_empleado;
    private javax.swing.JButton Jtbn_guardar_insumos;
    private javax.swing.JButton Jtbn_registrar_compra;
    private javax.swing.JPanel SubPanel_agregar_compra;
    private javax.swing.JButton btn_back;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JCheckBox check_apellidos_cliente;
    private javax.swing.JCheckBox check_autenticacion;
    private javax.swing.JCheckBox check_conexion_tls;
    private javax.swing.JCheckBox check_egreso;
    private javax.swing.JCheckBox check_gmail;
    private javax.swing.JCheckBox check_hotmail;
    private javax.swing.JCheckBox check_ingreso;
    private javax.swing.JCheckBox check_nombre_cliente;
    private javax.swing.JCheckBox check_personalizada;
    private javax.swing.JCheckBox check_ticket_personalizado;
    private javax.swing.JComboBox combo_categorias;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
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
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    public static javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTabbedPane jTbSystInfo;
    private javax.swing.JButton jbtnBuscarElemento;
    private javax.swing.JButton jbtn_insumos_vendidos;
    private javax.swing.JButton jbtn_insumos_vendidos1;
    private javax.swing.JButton jbtn_mostrar_mesas;
    private javax.swing.JScrollPane jsc_item;
    private javax.swing.JScrollPane jsc_item1;
    private javax.swing.JLabel lbl_cargo_atiende;
    private javax.swing.JLabel lbl_cargo_logueo;
    private javax.swing.JLabel lbl_eleccion_insumo;
    private javax.swing.JLabel lbl_logo;
    public static javax.swing.JLabel lbl_mesa;
    public static javax.swing.JLabel lbl_nombre_atiende;
    public static javax.swing.JLabel lbl_nombre_cliente;
    private javax.swing.JLabel lbl_nombre_insumo;
    private javax.swing.JLabel lbl_nombre_logueo;
    private javax.swing.JLabel lbl_nombre_platillo;
    private javax.swing.JLabel lbl_producto_elegido;
    private javax.swing.JLabel lbl_ruta_entrada;
    private javax.swing.JLabel lbl_ruta_xml;
    private javax.swing.JLabel lbl_total_ventas;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panel_1;
    private javax.swing.JPanel panel_2;
    private javax.swing.JPanel panel_c_ventas;
    private javax.swing.JPanel panel_caja;
    private javax.swing.JPanel panel_categorias;
    private javax.swing.JPanel panel_cfdi;
    private javax.swing.JPanel panel_clientes;
    private javax.swing.JPanel panel_compras;
    private javax.swing.JPanel panel_consultar_caja;
    private javax.swing.JPanel panel_consultar_ventas;
    private javax.swing.JPanel panel_datos_generales;
    private javax.swing.JPanel panel_detalle;
    private javax.swing.JPanel panel_detalle_categorias;
    private javax.swing.JPanel panel_dinamico_mesas;
    private javax.swing.JPanel panel_enviar_prueba;
    private javax.swing.JPanel panel_escoger_orden;
    private javax.swing.JPanel panel_insumos;
    private javax.swing.JPanel panel_leyendas;
    private javax.swing.JPanel panel_mesas;
    private javax.swing.JPanel panel_registrar_mov_caja;
    private javax.swing.JPanel panel_teclado;
    private javax.swing.JPanel panel_totales;
    private javax.swing.JPanel panel_usuarios;
    private javax.swing.JPanel panel_utilerias;
    private javax.swing.JPanel panel_ventas;
    private javax.swing.JPanel pnl_button;
    private javax.swing.JPanel subPanel_agregarInsumos;
    private javax.swing.JPanel subPanel_configuracion_email;
    private javax.swing.JPanel subPanel_consultar_compras;
    private javax.swing.JPanel subPanel_editarInsumos;
    private javax.swing.JPanel subPanel_email;
    private javax.swing.JPanel subPanel_opciones;
    private javax.swing.JPanel sub_panel_consultar_cfdi;
    private javax.swing.JTable tabla_categorias;
    private javax.swing.JTable tabla_clientes;
    private javax.swing.JTable tabla_consultar_caja;
    public static javax.swing.JTable tabla_consultar_caja1;
    private javax.swing.JTable tabla_consultar_compras;
    private javax.swing.JTable tabla_consultar_venta;
    private javax.swing.JTable tabla_consumibles_insumos;
    private javax.swing.JTable tabla_detalle_categorias;
    private javax.swing.JTable tabla_detalle_categorias2;
    private javax.swing.JTable tabla_detalle_compras;
    private javax.swing.JTable tabla_egresos_caja;
    public static javax.swing.JTable tabla_empleados;
    private javax.swing.JTable tabla_ingresos_caja;
    private javax.swing.JTable tabla_insumos;
    private javax.swing.JTable tabla_insumos2;
    private javax.swing.JTable tabla_insumos_compras;
    private javax.swing.JTable tabla_pdf;
    public static javax.swing.JTable tabla_venta;
    private javax.swing.JTable tabla_xml;
    private javax.swing.JTextField txtFindCodeProduct;
    private javax.swing.JTextField txt_agregar_categoria;
    private javax.swing.JTextField txt_apellidos_cliente;
    private javax.swing.JTextField txt_apellidos_empleado;
    private javax.swing.JButton txt_aplicar;
    private javax.swing.JTextField txt_asunto;
    private javax.swing.JTextField txt_buscar_insumos;
    private javax.swing.JTextField txt_buscar_insumos_compras;
    public static javax.swing.JTextField txt_calle;
    private javax.swing.JTextField txt_calle_cliente;
    public static javax.swing.JFormattedTextField txt_cambio;
    private javax.swing.JFormattedTextField txt_cantidad_compra;
    private javax.swing.JFormattedTextField txt_cantidad_insumo;
    public static javax.swing.JFormattedTextField txt_cargo;
    public static javax.swing.JTextField txt_ciudad;
    public static javax.swing.JTextField txt_codigo_postal;
    public static javax.swing.JTextField txt_colonia;
    private javax.swing.JTextField txt_colonia_cliente;
    private javax.swing.JTextField txt_comentario_compras;
    private javax.swing.JTextPane txt_comentarios;
    public static javax.swing.JPasswordField txt_contraseña_email;
    private javax.swing.JPasswordField txt_contraseña_empleado;
    private javax.swing.JPasswordField txt_contraseña_series;
    private javax.swing.JFormattedTextField txt_cp_cliente;
    public static javax.swing.JTextField txt_cuenta_email;
    private javax.swing.JTextField txt_delegacion_cliente;
    private javax.swing.JTextField txt_descripcion;
    public static javax.swing.JFormattedTextField txt_descuento;
    public static javax.swing.JFormattedTextField txt_dinero_recibido;
    private javax.swing.JTextField txt_email_cliente;
    private javax.swing.JTextField txt_enviar_a;
    public static javax.swing.JTextField txt_estado;
    private javax.swing.JTextField txt_estado_cliente;
    public static com.toedter.calendar.JDateChooser txt_fecha_final;
    public static com.toedter.calendar.JDateChooser txt_fecha_final_caja;
    public static com.toedter.calendar.JDateChooser txt_fecha_inicial;
    public static com.toedter.calendar.JDateChooser txt_fecha_inicial_caja;
    private javax.swing.JTextField txt_filtro_clientes;
    private javax.swing.JFormattedTextField txt_folio_actual;
    private javax.swing.JFormattedTextField txt_folio_final;
    private javax.swing.JFormattedTextField txt_folio_inicial;
    private javax.swing.JTextField txt_impuesto_descripcion;
    private javax.swing.JFormattedTextField txt_impuesto_tasa;
    public static javax.swing.JTextField txt_leyenda1;
    public static javax.swing.JTextField txt_leyenda2;
    public static javax.swing.JTextField txt_leyenda3;
    public static javax.swing.JTextField txt_leyenda4;
    public static javax.swing.JTextField txt_leyenda5;
    private javax.swing.JTextField txt_localidad_cliente;
    public static javax.swing.JTextField txt_lugar_expedicion;
    private javax.swing.JTextPane txt_mensaje;
    private javax.swing.JFormattedTextField txt_monto;
    private javax.swing.JTextField txt_no_ext_cliente;
    public static javax.swing.JTextField txt_no_exterior;
    private javax.swing.JTextField txt_no_int_cliente;
    public static javax.swing.JTextField txt_no_interior;
    private javax.swing.JTextField txt_nombre_cliente;
    private javax.swing.JTextField txt_nombre_empleado;
    private javax.swing.JTextField txt_nombre_insumo;
    private javax.swing.JTextField txt_nota;
    public static javax.swing.JTextField txt_pais;
    private javax.swing.JTextField txt_pais_cliente;
    private javax.swing.JFormattedTextField txt_precio;
    public static javax.swing.JFormattedTextField txt_propina;
    private javax.swing.JTextField txt_proveedor;
    public static javax.swing.JTextField txt_puerto_email;
    private javax.swing.JTextField txt_puesto_empleado;
    public static javax.swing.JTextField txt_razon_social;
    private javax.swing.JTextField txt_razon_social_cliente;
    public static javax.swing.JTextField txt_referencia;
    public static javax.swing.JTextField txt_regimen_fiscal;
    public static javax.swing.JTextField txt_rfc;
    private javax.swing.JTextField txt_rfc_cliente;
    private javax.swing.JTextField txt_serie;
    public static javax.swing.JTextField txt_servidor_email;
    private javax.swing.JFormattedTextField txt_stock;
    private javax.swing.JFormattedTextField txt_suma;
    public static javax.swing.JTextField txt_tel1;
    public static javax.swing.JTextField txt_tel2;
    public static javax.swing.JTextField txt_tel3;
    private javax.swing.JTextField txt_tel_casa_empleado;
    private javax.swing.JTextField txt_tel_cel_empleado;
    private javax.swing.JTextField txt_tel_fijo_cliente;
    private javax.swing.JTextField txt_tel_movil_cliente;
    public static javax.swing.JFormattedTextField txt_total;
    private javax.swing.JFormattedTextField txt_total_caja;
    private javax.swing.JFormattedTextField txt_total_compra;
    private javax.swing.JFormattedTextField txt_total_egresos;
    private javax.swing.JFormattedTextField txt_total_ingresos;
    public static javax.swing.JFormattedTextField txt_total_pagar;
    public static javax.swing.JFormattedTextField txt_total_propina;
    private javax.swing.JFormattedTextField txt_total_ventas;
    private javax.swing.JFormattedTextField txt_ultimo_costo;
    private javax.swing.JFormattedTextField txt_ultimo_costo_compras;
    private javax.swing.JTextField txt_unidad_medida;
    private javax.swing.JPasswordField txt_verificar_contraseña_email;
    private javax.swing.JPasswordField txt_verificar_contraseña_empleado;
    // End of variables declaration//GEN-END:variables
}
