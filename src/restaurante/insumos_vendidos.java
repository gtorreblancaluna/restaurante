/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante;

import clases.sqlclass;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import static restaurante.principal.txt_fecha_inicial;

/**
 *
 * @author Gerardo Torreblanca
 */
public class insumos_vendidos extends javax.swing.JDialog {

    sqlclass funcion = new sqlclass();
    Object[][] dtconduc;
    String f_inicial, f_final, fecha_sistema;
    int contaFila = 1;
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet sheet = this.workbook.createSheet();
    /*La ruta donde se creará el archivo*/
    String rutaArchivo = System.getProperty("user.home") + "/ejemploExcelJava.xls";
    /*Se crea el objeto de tipo File con la ruta del archivo*/
    File archivoXLS = new File(rutaArchivo);

    /**
     * Creates new form insumos_vendidos
     */
    public insumos_vendidos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/culichi.png"));
        f_inicial = new SimpleDateFormat("dd/MM/yyyy").format(principal.txt_fecha_inicial.getDate());
        f_final = new SimpleDateFormat("dd/MM/yyyy").format(principal.txt_fecha_final.getDate());
        lblNotaConsultaDia.setText("Consulta correspondiente al dia: "+f_inicial);
        tabla();
        tabla1(); 
        fecha_sistema();
        reporte_diario();
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

    public void tabla() {   // Tabla venta       
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Id", "Vendido", "Insumo", "Fecha", "Cliente", "Mesa", "Atendio"};
        String[] colName = {"id_venta", "vendido", "nombre_insumo", "fecha", "cliente", "mesa", "atendio"};

        //nombre de columnas, tabla, instruccion sql
        //dtconduc = funcion.GetTabla(colName, "venta", "SELECT v.`id_venta`,CONCAT(c.`nombre`,\" \",c.`apellidos`) AS nombre_cliente , v.`fecha`, SUM(dc.`precio_publico` * d.`cantidad`) as total, v.`id_mesa`as mesa, CONCAT(e.`nombre`,\" \", e.`apellidos`)AS Atendio, ((v.`propina`/100) * (SUM(dc.`precio_publico` * d.`cantidad`)))AS Propina  FROM venta v, detalle_venta d, detalle_categorias dc, clientes c, empleado e\n"
        //        + "WHERE d.id_venta=v.id_venta AND d.id_detalle_categorias=dc.id_detalle_categorias AND v.id_clientes=c.id_clientes AND STR_TO_DATE(v.`fecha`,'%d/%m/%Y') BETWEEN STR_TO_DATE('" + filtro_fecha_inicial + "','%d/%m/%Y')  AND STR_TO_DATE('" + filtro_fecha_final + "','%d/%m/%Y') AND v.id_empleado=e.id_empleado GROUP BY d.`id_venta` ORDER BY v.`id_venta` DESC");
        dtconduc = funcion.GetTabla(colName, "detalle_venta", "SELECT  d.`id_venta`,(d.`cantidad` * c.`cantidad`) as vendido ,i.`nombre_insumo` ,v.`fecha`, Concat(cl.`nombre`,\" \", cl.`apellidos`)as cliente, m.`descripcion`as mesa, Concat(e.`nombre`,\" \",e.`apellidos`) as atendio\n"
                + "FROM detalle_venta d, detalle_categorias dc, consumibles_platillos c, insumos i, venta v, clientes cl, empleado e, detalle_categorias dc1, insumos i1, mesa m\n"
                + "WHERE d.id_detalle_categorias=dc.id_detalle_categorias AND c.id_detalle_cat=dc.id_detalle_categorias AND c.id_insumos=i.id_insumos AND d.id_venta=v.id_venta AND v.id_clientes=cl.id_clientes AND v.id_mesa=m.id_mesa AND v.id_empleado=e.id_empleado AND STR_TO_DATE(v.`fecha`,'%d/%m/%Y')\n"
                + "BETWEEN STR_TO_DATE( '" + f_inicial + "' ,'%d/%m/%Y')  AND STR_TO_DATE('" + f_final + "','%d/%m/%Y') AND d.id_detalle_categorias=dc1.id_detalle_categorias AND c.id_insumos=i1.id_insumos order by STR_TO_DATE(v.`fecha`,'%d/%m/%Y') desc ");

        /* int filas = dtconduc.length;
         String fecha, fecha2;

         for (int i = 0; i < filas; i++) {
         fecha = dtconduc[i][6].toString();
         //System.out.println("fecha"+" "+fecha);
         fecha2 = dia_semana(fecha);
         dtconduc[i][6] = fecha2;
         }*/
        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla.setModel(datos);

        DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
        centrar.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer derecha = new DefaultTableCellRenderer();
        derecha.setHorizontalAlignment(SwingConstants.RIGHT);

        int[] anchos = {10, 50, 150, 220, 190, 90, 190};
        for (int inn = 0; inn < tabla.getColumnCount(); inn++) {
            tabla.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(1).setCellRenderer(centrar);
        tabla.getColumnModel().getColumn(2).setCellRenderer(centrar);
        tabla.getColumnModel().getColumn(3).setCellRenderer(centrar);
        tabla.getColumnModel().getColumn(4).setCellRenderer(centrar);
        tabla.getColumnModel().getColumn(5).setCellRenderer(centrar);
        tabla.getColumnModel().getColumn(6).setCellRenderer(centrar);
    }

    public void tabla1() {   //Tabla con precio por producto vendido      
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Fecha", "Vendido", "Insumo", "U. Medida", "Anterior", "Stock"};
        String[] colName = {"fecha", "total", "nombre_insumo", "unidad_medida", "stock_anterior", "stock_actual"};

        //nombre de columnas, tabla, instruccion sql
        //dtconduc = funcion.GetTabla(colName, "venta", "SELECT v.`id_venta`,CONCAT(c.`nombre`,\" \",c.`apellidos`) AS nombre_cliente , v.`fecha`, SUM(dc.`precio_publico` * d.`cantidad`) as total, v.`id_mesa`as mesa, CONCAT(e.`nombre`,\" \", e.`apellidos`)AS Atendio, ((v.`propina`/100) * (SUM(dc.`precio_publico` * d.`cantidad`)))AS Propina  FROM venta v, detalle_venta d, detalle_categorias dc, clientes c, empleado e\n"
        //        + "WHERE d.id_venta=v.id_venta AND d.id_detalle_categorias=dc.id_detalle_categorias AND v.id_clientes=c.id_clientes AND STR_TO_DATE(v.`fecha`,'%d/%m/%Y') BETWEEN STR_TO_DATE('" + filtro_fecha_inicial + "','%d/%m/%Y')  AND STR_TO_DATE('" + filtro_fecha_final + "','%d/%m/%Y') AND v.id_empleado=e.id_empleado GROUP BY d.`id_venta` ORDER BY v.`id_venta` DESC");
        //
       /* dtconduc = funcion.GetTabla(colName, "detalle_venta", "SELECT v.`id_venta`, v.`fecha`, d.`cantidad`, dc.`descripcion`,(d.`cantidad`* dc.`precio_publico`)Publico, (d.`cantidad`*i.`ultimo_costo`)AS U_Costo, ((d.`cantidad`* dc.`precio_publico`)-(d.`cantidad`*i.`ultimo_costo`))As Utilidad, m.`descripcion`as mesa, CONCAT(e.`nombre`,\" \", e.`apellidos`)atendio\n"
                + "FROM venta v, detalle_venta d, detalle_categorias dc, empleado e, mesa m, insumos i, consumibles_platillos c, insumos i1\n"
                + "WHERE d.id_venta=v.id_venta AND d.id_detalle_categorias=dc.id_detalle_categorias AND v.id_empleado=e.id_empleado AND v.id_mesa=m.id_mesa AND c.id_detalle_cat=dc.id_detalle_categorias AND c.id_insumos=i1.id_insumos \n"
                + "AND STR_TO_DATE(v.`fecha`,'%d/%m/%Y')\n"
                + "BETWEEN STR_TO_DATE( '" + f_inicial + "' ,'%d/%m/%Y')  AND STR_TO_DATE('" + f_final + "','%d/%m/%Y') group by v.`id_venta`");*/
        
        
        
        dtconduc = funcion.GetTabla(colName, "detalle_venta", "SELECT v.`fecha`, TRUNCATE(SUM(c.`cantidad`*d.`cantidad`),2)as total, i.`nombre_insumo`,i.`unidad_medida`,TRUNCATE((SUM(c.`cantidad`*d.`cantidad`)+ i.`stock` ),2)as stock_anterior, TRUNCATE((i.`stock`),2) as stock_actual\n"
                + "FROM venta v, detalle_venta d, detalle_categorias dc, consumibles_platillos c, insumos i\n"
                + "WHERE d.id_venta=v.id_venta AND d.id_detalle_categorias=dc.id_detalle_categorias AND c.id_detalle_cat=dc.id_detalle_categorias AND c.id_insumos=i.id_insumos\n"
                + "AND STR_TO_DATE(v.`fecha`,'%d/%m/%Y')\n"
                + "BETWEEN STR_TO_DATE( '" + f_inicial + "' ,'%d/%m/%Y')  AND STR_TO_DATE('" + f_final + "','%d/%m/%Y')\n"
                + "GROUP BY i.`nombre_insumo`\n"
                + "ORDER BY i.`nombre_insumo`");
        
        

        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        tabla1.setModel(datos);

        DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
        centrar.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer derecha = new DefaultTableCellRenderer();
        derecha.setHorizontalAlignment(SwingConstants.RIGHT);

        int[] anchos = {90, 60, 120, 70, 90, 90};
        for (int inn = 0; inn < tabla1.getColumnCount(); inn++) {
            tabla1.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        tabla1.getColumnModel().getColumn(1).setCellRenderer(centrar);
        tabla1.getColumnModel().getColumn(2).setCellRenderer(centrar);
        tabla1.getColumnModel().getColumn(3).setCellRenderer(centrar);
        tabla1.getColumnModel().getColumn(4).setCellRenderer(centrar);
        tabla1.getColumnModel().getColumn(5).setCellRenderer(centrar);

    
    }

    public void reporte_diario() {   //Tabla con precio por producto vendido      
        reporte_diario.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        String[] columNames = {"Fecha", "Vendido", "Insumo", "U. Medida", "Anterior", "Stock"};
        String[] colName = {"fecha", "total", "nombre_insumo", "unidad_medida", "stock_anterior", "stock_actual"};

        /* dtconduc = funcion.GetTabla(colName, "detalle_venta", "SELECT v.`id_venta`, (c.`cantidad` * d.`cantidad`)cant,i.`nombre_insumo`,(i.`stock`+ ((c.`cantidad` * d.`cantidad`)))As anterior,i.`stock`,v.`fecha`\n"
         + "FROM venta v, detalle_venta d, detalle_categorias dc, consumibles_platillos c, insumos i, consumibles_platillos c1\n"
         + "WHERE d.id_venta=v.id_venta AND d.id_detalle_categorias=dc.id_detalle_categorias AND c.id_detalle_cat=dc.id_detalle_categorias\n"
         + "AND c.id_insumos=i.id_insumos AND STR_TO_DATE(v.`fecha`,'%d/%m/%Y')= STR_TO_DATE('" + fecha_sistema + "','%d/%m/%Y') AND c1.id_detalle_cat=dc.id_detalle_categorias Group by v.`id_venta`");*/
        dtconduc = funcion.GetTabla(colName, "detalle_venta", "SELECT v.`fecha`, TRUNCATE(SUM(c.`cantidad`*d.`cantidad`),2)as total, i.`nombre_insumo`,i.`unidad_medida`,TRUNCATE((SUM(c.`cantidad`*d.`cantidad`)+ i.`stock` ),2)as stock_anterior, TRUNCATE((i.`stock`),2) as stock_actual\n"
                + "FROM venta v, detalle_venta d, detalle_categorias dc, consumibles_platillos c, insumos i\n"
                + "WHERE d.id_venta=v.id_venta AND d.id_detalle_categorias=dc.id_detalle_categorias AND c.id_detalle_cat=dc.id_detalle_categorias AND c.id_insumos=i.id_insumos\n"
               // + "AND STR_TO_DATE(v.`fecha`,'%d/%m/%Y')= STR_TO_DATE('" + fecha_sistema + "','%d/%m/%Y')\n"
                // tomara la fecha que se puso al inicio
                + "AND STR_TO_DATE(v.`fecha`,'%d/%m/%Y')= STR_TO_DATE('" + f_inicial + "','%d/%m/%Y')\n"
                + "GROUP BY i.`nombre_insumo`\n"
                + "ORDER BY i.`nombre_insumo`");

        DefaultTableModel datos = new DefaultTableModel(dtconduc, columNames);
        reporte_diario.setModel(datos);

        DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
        centrar.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer derecha = new DefaultTableCellRenderer();
        derecha.setHorizontalAlignment(SwingConstants.RIGHT);

        int[] anchos = {90, 60, 120, 70, 90, 90};
        for (int inn = 0; inn < reporte_diario.getColumnCount(); inn++) {
            reporte_diario.getColumnModel().getColumn(inn).setPreferredWidth(anchos[inn]);
        }

        reporte_diario.getColumnModel().getColumn(1).setCellRenderer(centrar);
        reporte_diario.getColumnModel().getColumn(2).setCellRenderer(centrar);
        reporte_diario.getColumnModel().getColumn(3).setCellRenderer(centrar);
        reporte_diario.getColumnModel().getColumn(4).setCellRenderer(centrar);
        reporte_diario.getColumnModel().getColumn(5).setCellRenderer(centrar);

    }

    public void exportar(JTable table, File file) {
        try {
            TableModel model = table.getModel();
            FileWriter excel = new FileWriter(file);

            for (int i = 0; i < model.getColumnCount(); i++) {
                excel.write(model.getColumnName(i) + "\t");
            }

            excel.write("\n");

            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    excel.write(model.getValueAt(i, j).toString() + "\t");
                }
                excel.write("\n");
            }

            excel.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void llenarEncabezado(String id, String vendidos, String insumo, String fecha, String cliente, String mesa, String atendio) {
        HSSFCellStyle styleNumeros = this.workbook.createCellStyle();
        HSSFCellStyle styleCentrado = this.workbook.createCellStyle();
        HSSFFont font = this.workbook.createFont();

        HSSFRow row1 = this.sheet.createRow(contaFila);
        font.setFontName("Arial");

        styleCentrado.setFont(font);
        styleCentrado.setWrapText(true);
        styleNumeros.setFont(font);
        styleNumeros.setWrapText(true);

        HSSFCell cell1A = row1.createCell(0);
        cell1A.setCellValue(id);

        HSSFCell cell1B = row1.createCell(1);
        cell1B.setCellValue(Float.parseFloat(vendidos));
        cell1B.setCellStyle(styleNumeros);
        cell1B.setCellStyle(styleCentrado);

        HSSFCell cell1C = row1.createCell(2);
        cell1C.setCellValue(insumo);
        cell1C.setCellStyle(styleCentrado);

        HSSFCell cell1D = row1.createCell(3);
        cell1D.setCellValue(fecha);
        cell1D.setCellStyle(styleCentrado);

        HSSFCell cell1E = row1.createCell(4);
        cell1E.setCellValue(cliente);
        cell1E.setCellStyle(styleCentrado);

        HSSFCell cell1F = row1.createCell(5);
        cell1F.setCellValue(mesa);
        cell1F.setCellStyle(styleCentrado);

        HSSFCell cell1G = row1.createCell(6);
        cell1G.setCellValue(atendio);
        cell1G.setCellStyle(styleCentrado);

        contaFila++;

    }

    ///"Id", "Fecha", "Cantidad ", "Descripcion ", "P. publico", "Mesa", "Atendio"
    private void llenarEncabezado1(String id, String fecha, String cantidad, String descripcion, String p_publico, String mesa, String atendio) {
        HSSFCellStyle styleNumeros = this.workbook.createCellStyle();
        HSSFCellStyle styleCentrado = this.workbook.createCellStyle();
        HSSFFont font = this.workbook.createFont();

        HSSFRow row1 = this.sheet.createRow(contaFila);
        font.setFontName("Arial");

        styleCentrado.setFont(font);
        styleCentrado.setWrapText(true);
        styleNumeros.setFont(font);
        styleNumeros.setWrapText(true);

        HSSFCell cell1A = row1.createCell(0);
        cell1A.setCellValue(id);

        HSSFCell cell1B = row1.createCell(1);
        cell1B.setCellValue(fecha);
        cell1B.setCellStyle(styleCentrado);

        HSSFCell cell1C = row1.createCell(2);
        cell1C.setCellValue(Float.parseFloat(cantidad));
        cell1C.setCellStyle(styleNumeros);
        cell1C.setCellStyle(styleCentrado);

        HSSFCell cell1D = row1.createCell(3);
        cell1D.setCellValue(descripcion);
        cell1D.setCellStyle(styleCentrado);

        HSSFCell cell1E = row1.createCell(4);
        cell1E.setCellValue(Float.parseFloat(p_publico));
        cell1E.setCellStyle(styleNumeros);
        cell1E.setCellStyle(styleCentrado);

        HSSFCell cell1F = row1.createCell(5);
        cell1F.setCellValue(mesa);
        cell1F.setCellStyle(styleCentrado);

        HSSFCell cell1G = row1.createCell(6);
        cell1G.setCellValue(atendio);
        cell1G.setCellStyle(styleCentrado);

        contaFila++;

    }

    private void llenarEncabezado_rep_diario(String fecha, String vendido, String insumo, String u_medida, String anterior, String stock) {
        HSSFCellStyle styleNumeros = this.workbook.createCellStyle();
        HSSFCellStyle styleCentrado = this.workbook.createCellStyle();
        HSSFFont font = this.workbook.createFont();

        HSSFRow row1 = this.sheet.createRow(contaFila);
        font.setFontName("Arial");

        styleCentrado.setFont(font);
        styleCentrado.setWrapText(true);
        styleNumeros.setFont(font);
        styleNumeros.setWrapText(true);

        HSSFCell cell1A = row1.createCell(0);
        cell1A.setCellValue(fecha);
        cell1A.setCellStyle(styleCentrado);

        HSSFCell cell1B = row1.createCell(1);
        cell1B.setCellValue(Float.parseFloat(vendido));
        cell1B.setCellStyle(styleNumeros);
        cell1B.setCellStyle(styleCentrado);

        HSSFCell cell1C = row1.createCell(2);
        cell1C.setCellValue(insumo);
        cell1C.setCellStyle(styleCentrado);

        HSSFCell cell1D = row1.createCell(3);
        cell1D.setCellValue(u_medida);
        cell1D.setCellStyle(styleCentrado);

        HSSFCell cell1E = row1.createCell(4);
        cell1E.setCellValue(Float.parseFloat(anterior));
        cell1E.setCellStyle(styleNumeros);
        cell1E.setCellStyle(styleCentrado);

        HSSFCell cell1F = row1.createCell(5);
        cell1F.setCellValue(Float.parseFloat(stock));
        cell1F.setCellStyle(styleNumeros);
        cell1F.setCellStyle(styleCentrado);

        contaFila++;

    }

    private void crearTitulosConceptos() {
        HSSFCellStyle styleTituloConcepto = this.workbook.createCellStyle();
        styleTituloConcepto.setWrapText(true);
        styleTituloConcepto.setFillForegroundColor(new HSSFColor.WHITE().getIndex());
        org.apache.poi.ss.usermodel.Font hlink_font = this.workbook.createFont();
        hlink_font.setColor(IndexedColors.WHITE.getIndex());
        styleTituloConcepto.setFont(hlink_font);
        styleTituloConcepto.setFont(hlink_font);
        HSSFFont fontTituloConcepto = this.workbook.createFont();
        fontTituloConcepto.setFontName("Arial");
        styleTituloConcepto.setFont(fontTituloConcepto);
        styleTituloConcepto.setWrapText(true);
        HSSFRow row14 = this.sheet.createRow(0);
        ////////////////////////////////////////////////////////////////////////
        HSSFCell cell14A = row14.createCell(0);
        cell14A.setCellValue(new HSSFRichTextString("ID"));
        cell14A.setCellStyle(styleTituloConcepto);
        ////////////////////////////////////////////////////////////////////////////
        HSSFCell cell4B = row14.createCell(1);
        cell4B.setCellValue(new HSSFRichTextString("Vendido"));
        cell4B.setCellStyle(styleTituloConcepto);
        ///////////////////////////////////////////////////////////////////////////////
        HSSFCell cell14C = row14.createCell(2);
        cell14C.setCellValue(new HSSFRichTextString("Insumo"));
        cell14C.setCellStyle(styleTituloConcepto);

        HSSFCell cell14D = row14.createCell(3);
        cell14D.setCellValue(new HSSFRichTextString("Fecha"));
        cell14D.setCellStyle(styleTituloConcepto);

        HSSFCell cell14E = row14.createCell(4);
        cell14E.setCellValue(new HSSFRichTextString("Cliente"));
        cell14E.setCellStyle(styleTituloConcepto);

        HSSFCell cell14F = row14.createCell(5);
        cell14F.setCellValue(new HSSFRichTextString("Mesa"));
        cell14F.setCellStyle(styleTituloConcepto);

        HSSFCell cell14G = row14.createCell(6);
        cell14G.setCellValue(new HSSFRichTextString("Atendio"));
        cell14G.setCellStyle(styleTituloConcepto);

    }

    private void crearTitulosConceptos_rep_diario() {
        HSSFCellStyle styleTituloConcepto = this.workbook.createCellStyle();
        styleTituloConcepto.setWrapText(true);
        styleTituloConcepto.setFillForegroundColor(new HSSFColor.WHITE().getIndex());
        org.apache.poi.ss.usermodel.Font hlink_font = this.workbook.createFont();
        hlink_font.setColor(IndexedColors.WHITE.getIndex());
        styleTituloConcepto.setFont(hlink_font);
        styleTituloConcepto.setFont(hlink_font);
        HSSFFont fontTituloConcepto = this.workbook.createFont();
        fontTituloConcepto.setFontName("Arial");
        styleTituloConcepto.setFont(fontTituloConcepto);
        styleTituloConcepto.setWrapText(true);
        HSSFRow row14 = this.sheet.createRow(0);
        ////////////////////////////////////////////////////////////////////////
        HSSFCell cell14A = row14.createCell(0);
        cell14A.setCellValue(new HSSFRichTextString("Fecha"));
        cell14A.setCellStyle(styleTituloConcepto);
        ////////////////////////////////////////////////////////////////////////////
        HSSFCell cell4B = row14.createCell(1);
        cell4B.setCellValue(new HSSFRichTextString("Vendido"));
        cell4B.setCellStyle(styleTituloConcepto);
        ///////////////////////////////////////////////////////////////////////////////
        HSSFCell cell14C = row14.createCell(2);
        cell14C.setCellValue(new HSSFRichTextString("Insumo"));
        cell14C.setCellStyle(styleTituloConcepto);

        HSSFCell cell14D = row14.createCell(3);
        cell14D.setCellValue(new HSSFRichTextString("U. Medida"));
        cell14D.setCellStyle(styleTituloConcepto);

        HSSFCell cell14E = row14.createCell(4);
        cell14E.setCellValue(new HSSFRichTextString("Stock anterior"));
        cell14E.setCellStyle(styleTituloConcepto);

        HSSFCell cell14F = row14.createCell(5);
        cell14F.setCellValue(new HSSFRichTextString("Stock actual"));
        cell14F.setCellStyle(styleTituloConcepto);

    }

    private void crearTitulosConceptos1() {
        HSSFCellStyle styleTituloConcepto = this.workbook.createCellStyle();
        styleTituloConcepto.setWrapText(true);
        styleTituloConcepto.setFillForegroundColor(new HSSFColor.WHITE().getIndex());
        org.apache.poi.ss.usermodel.Font hlink_font = this.workbook.createFont();
        hlink_font.setColor(IndexedColors.WHITE.getIndex());
        styleTituloConcepto.setFont(hlink_font);
        styleTituloConcepto.setFont(hlink_font);
        HSSFFont fontTituloConcepto = this.workbook.createFont();
        fontTituloConcepto.setFontName("Arial");
        styleTituloConcepto.setFont(fontTituloConcepto);
        styleTituloConcepto.setWrapText(true);
        HSSFRow row14 = this.sheet.createRow(0);
        ////////////////////////////////////////////////////////////////////////
        HSSFCell cell14A = row14.createCell(0);
        cell14A.setCellValue(new HSSFRichTextString("ID"));
        cell14A.setCellStyle(styleTituloConcepto);
        ////////////////////////////////////////////////////////////////////////////
        HSSFCell cell4B = row14.createCell(1);
        cell4B.setCellValue(new HSSFRichTextString("Fecha"));
        cell4B.setCellStyle(styleTituloConcepto);
        ///////////////////////////////////////////////////////////////////////////////
        HSSFCell cell14C = row14.createCell(2);
        cell14C.setCellValue(new HSSFRichTextString("Cantidad"));
        cell14C.setCellStyle(styleTituloConcepto);

        HSSFCell cell14D = row14.createCell(3);
        cell14D.setCellValue(new HSSFRichTextString("Descripcion"));
        cell14D.setCellStyle(styleTituloConcepto);

        HSSFCell cell14E = row14.createCell(4);
        cell14E.setCellValue(new HSSFRichTextString("P. Publico"));
        cell14E.setCellStyle(styleTituloConcepto);

        HSSFCell cell14F = row14.createCell(5);
        cell14F.setCellValue(new HSSFRichTextString("Mesa"));
        cell14F.setCellStyle(styleTituloConcepto);

        HSSFCell cell14G = row14.createCell(6);
        cell14G.setCellValue(new HSSFRichTextString("Atendio"));
        cell14G.setCellStyle(styleTituloConcepto);

    }

    private void crearEsqueleto(String productos) {
        HSSFCellStyle style = this.workbook.createCellStyle();
        HSSFFont font = this.workbook.createFont();
        font.setFontName("Impact");
        style.setFont(font);

        HSSFRow row2 = this.sheet.createRow(1);
        HSSFCell cell = row2.createCell(0);
        //HSSFRichTextString titulo = new HSSFRichTextString(productos);
        //cell.setCellValue(titulo);
        cell.setCellStyle(style);
        HSSFCell celltot = row2.createCell(1);

        celltot.setCellStyle(style);

        crearTitulosConceptos();
    }

    private void crearEsqueleto_rep_diario(String productos) {
        HSSFCellStyle style = this.workbook.createCellStyle();
        HSSFFont font = this.workbook.createFont();
        font.setFontName("Impact");
        style.setFont(font);

        HSSFRow row2 = this.sheet.createRow(1);
        HSSFCell cell = row2.createCell(0);
        //HSSFRichTextString titulo = new HSSFRichTextString(productos);
        //cell.setCellValue(titulo);
        cell.setCellStyle(style);
        HSSFCell celltot = row2.createCell(1);

        celltot.setCellStyle(style);

        crearTitulosConceptos_rep_diario();
    }

    private void crearEsqueleto1(String productos) {
        HSSFCellStyle style = this.workbook.createCellStyle();
        HSSFFont font = this.workbook.createFont();
        font.setFontName("Impact");
        style.setFont(font);

        HSSFRow row2 = this.sheet.createRow(1);
        HSSFCell cell = row2.createCell(0);
        //HSSFRichTextString titulo = new HSSFRichTextString(productos);
        //cell.setCellValue(titulo);
        cell.setCellStyle(style);
        HSSFCell celltot = row2.createCell(1);

        celltot.setCellStyle(style);

        crearTitulosConceptos1();
    }
//generaXLS(vendidos, p_publico, u_costo, utilidad, insumo, fecha, cliente, mesa, atendio, chooser.getSelectedFile().getPath() + ".xls");

    public void generaXLS(String id, String vendidos, String insumo, String fecha, String cliente, String mesa, String atendio, String pathXLS) {
        try {
            this.sheet.setColumnWidth(0, 2000);
            this.sheet.setColumnWidth(1, 2000);
            this.sheet.setColumnWidth(2, 6000);
            this.sheet.setColumnWidth(3, 8000);
            this.sheet.setColumnWidth(4, 8000);
            this.sheet.setColumnWidth(5, 2000);
            this.sheet.setColumnWidth(6, 8000);
            this.sheet.setColumnWidth(7, 5000);

            int se;
            se = tabla.getRowCount();
            crearEsqueleto("Productos");
            llenarEncabezado(id, vendidos, insumo, fecha, cliente, mesa, atendio);

        } catch (Exception fos) {
            JOptionPane.showMessageDialog(null, "Error: " + fos.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(new File(pathXLS));
                this.workbook.write(fos);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (fos != null) {
                    try {
                        fos.flush();
                        fos.close();
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }
//fecha, vendidos, insumo, anterior, stock, chooser.getSelectedFile().getPath() + ".xls"

    public void generaXLS_rep_diario(String fecha, String vendidos, String insumo, String u_medida, String anterior, String stock, String pathXLS) {
        try {
            this.sheet.setColumnWidth(0, 10000);
            this.sheet.setColumnWidth(1, 2000);
            this.sheet.setColumnWidth(2, 8000);
            this.sheet.setColumnWidth(3, 6000);
            this.sheet.setColumnWidth(4, 6000);
            this.sheet.setColumnWidth(5, 3000);
            this.sheet.setColumnWidth(6, 3000);

            int se;
            se = reporte_diario.getRowCount();
            crearEsqueleto_rep_diario("Productos");
            llenarEncabezado_rep_diario(fecha, vendidos, insumo, u_medida, anterior, stock);

        } catch (Exception fos) {
            JOptionPane.showMessageDialog(null, "Error: " + fos.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(new File(pathXLS));
                this.workbook.write(fos);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (fos != null) {
                    try {
                        fos.flush();
                        fos.close();
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    ////"Id", "Fecha", "Cantidad ", "Descripcion ", "P. publico", "Mesa", "Atendio"
    public void generaXLS1(String id, String fecha, String cantidad, String descripcion, String p_publico, String mesa, String atendio, String pathXLS) {
        try {
            this.sheet.setColumnWidth(0, 2000);
            this.sheet.setColumnWidth(1, 6000);
            this.sheet.setColumnWidth(2, 2000);
            this.sheet.setColumnWidth(3, 8000);
            this.sheet.setColumnWidth(4, 5000);
            this.sheet.setColumnWidth(5, 2000);
            this.sheet.setColumnWidth(6, 8000);
            this.sheet.setColumnWidth(7, 5000);

            int se;
            se = tabla.getRowCount();
            crearEsqueleto1("Productos");
            llenarEncabezado1(id, fecha, cantidad, descripcion, p_publico, mesa, atendio);

        } catch (Exception fos) {
            JOptionPane.showMessageDialog(null, "Error: " + fos.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(new File(pathXLS));
                this.workbook.write(fos);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (fos != null) {
                    try {
                        fos.flush();
                        fos.close();
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        reporte_diario = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        jbtn_exportar_excel_rep_diario = new javax.swing.JButton();
        lblNotaConsultaDia = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};
        jbtn_exportar_excel = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jbtn_exportar_excel1 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex,int colIndex){return false;}};

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        reporte_diario.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        reporte_diario.setModel(new javax.swing.table.DefaultTableModel(
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
        reporte_diario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reporte_diarioMouseClicked(evt);
            }
        });
        reporte_diario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                reporte_diarioKeyPressed(evt);
            }
        });
        jScrollPane10.setViewportView(reporte_diario);

        jbtn_exportar_excel_rep_diario.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jbtn_exportar_excel_rep_diario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/excel 24x.png"))); // NOI18N
        jbtn_exportar_excel_rep_diario.setMnemonic('X');
        jbtn_exportar_excel_rep_diario.setText("Exportar a Excel");
        jbtn_exportar_excel_rep_diario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_exportar_excel_rep_diarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jbtn_exportar_excel_rep_diario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNotaConsultaDia, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtn_exportar_excel_rep_diario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNotaConsultaDia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Reporte diario", jPanel3);

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

        jbtn_exportar_excel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jbtn_exportar_excel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/excel 24x.png"))); // NOI18N
        jbtn_exportar_excel.setMnemonic('X');
        jbtn_exportar_excel.setText("Exportar a Excel");
        jbtn_exportar_excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_exportar_excelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbtn_exportar_excel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1026, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbtn_exportar_excel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Insumos vendidos", jPanel1);

        jbtn_exportar_excel1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jbtn_exportar_excel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/excel 24x.png"))); // NOI18N
        jbtn_exportar_excel1.setMnemonic('X');
        jbtn_exportar_excel1.setText("Exportar a Excel");
        jbtn_exportar_excel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_exportar_excel1ActionPerformed(evt);
            }
        });

        tabla1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        tabla1.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla1MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tabla1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jbtn_exportar_excel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1026, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbtn_exportar_excel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Ventas", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tablaMouseClicked

    private void jbtn_exportar_excelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_exportar_excelActionPerformed
        this.workbook = new HSSFWorkbook();
        this.sheet = this.workbook.createSheet();
        int guardarArchivo = 0;
        int se;
        String id = "", vendidos = "", insumo = "", fecha = "", cliente = "", mesa = "", atendio = "";

        se = tabla.getRowCount();
        if (se > 0) {
            try {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo Excel", new String[]{"xsl"});
                chooser.setFileFilter(filter);
                guardarArchivo = chooser.showSaveDialog(this);
                if (guardarArchivo == 0) {
                    int contador = 0;
                    while (contador < se) {
                        id = (String) tabla.getValueAt(contador, 0);
                        vendidos = (String) tabla.getValueAt(contador, 1);
                        insumo = (String) tabla.getValueAt(contador, 2);
                        fecha = (String) tabla.getValueAt(contador, 3);
                        cliente = (String) tabla.getValueAt(contador, 4);
                        mesa = (String) tabla.getValueAt(contador, 5);
                        atendio = (String) tabla.getValueAt(contador, 6);

                        System.out.println("ID es: " + id);
                        System.out.println("Vendidos : " + vendidos);

                        System.out.println("Insumo: " + insumo);
                        System.out.println("Fecha: " + fecha);
                        System.out.println("Cliente: " + cliente);
                        System.out.println("Mesa " + mesa);
                        System.out.println("Atendio: " + atendio);

                        generaXLS(id, vendidos, insumo, fecha, cliente, mesa, atendio, chooser.getSelectedFile().getPath() + ".xls");
                        contador++;
                    }
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this, "Proceso Finalizado. Se ha escrito el archivo en la ruta:\n" + chooser.getSelectedFile().getAbsolutePath() + ".xsl", "Generación de reporte", 1);
                }

            } catch (Exception ex) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "No se pudo generar el reporte en Excel.\n CODIGO DE ERROR: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            Toolkit.getDefaultToolkit().beep();
            String path_jar = null;
            JOptionPane.showMessageDialog(null, "<html> <b> La tabla detalle de venta esta vacia. </b></html>", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(path_jar + "/Icons/images/error.png"));
        }

    }//GEN-LAST:event_jbtn_exportar_excelActionPerformed

    private void jbtn_exportar_excel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_exportar_excel1ActionPerformed
        this.workbook = new HSSFWorkbook();
        this.sheet = this.workbook.createSheet();
        int guardarArchivo = 0;
        int se;//"Id", "Fecha", "Cantidad ", "Descripcion ", "P. publico", "Mesa", "Atendio"
        String id = "", fecha = "", cantidad = "", descripcion = "", p_publico = "", mesa = "", atendio = "";

        se = tabla.getRowCount();
        if (se > 0) {
            try {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo Excel", new String[]{"xsl"});
                chooser.setFileFilter(filter);
                guardarArchivo = chooser.showSaveDialog(this);
                if (guardarArchivo == 0) {
                    int contador = 0;
                    while (contador < se) {
                        id = (String) tabla1.getValueAt(contador, 0);
                        fecha = (String) tabla1.getValueAt(contador, 1);
                        cantidad = (String) tabla1.getValueAt(contador, 2);
                        descripcion = (String) tabla1.getValueAt(contador, 3);
                        p_publico = (String) tabla1.getValueAt(contador, 4);
                        mesa = (String) tabla1.getValueAt(contador, 5);
                        atendio = (String) tabla1.getValueAt(contador, 6);
//"Id", "Fecha", "Cantidad ", "Descripcion ", "P. publico", "Mesa", "Atendio"
                        generaXLS1(id, fecha, cantidad, descripcion, p_publico, mesa, atendio, chooser.getSelectedFile().getPath() + ".xls");
                        contador++;
                    }
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this, "Proceso Finalizado. Se ha escrito el archivo en la ruta:\n" + chooser.getSelectedFile().getAbsolutePath() + ".xsl", "Generación de reporte", 1);
                }

            } catch (Exception ex) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "No se pudo generar el reporte en Excel.\n CODIGO DE ERROR: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            Toolkit.getDefaultToolkit().beep();
            String path_jar = null;
            JOptionPane.showMessageDialog(null, "<html> <b> La tabla detalle de venta esta vacia. </b></html>", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(path_jar + "/Icons/images/error.png"));
        }
    }//GEN-LAST:event_jbtn_exportar_excel1ActionPerformed

    private void tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla1MouseClicked

    private void reporte_diarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reporte_diarioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_reporte_diarioMouseClicked

    private void jbtn_exportar_excel_rep_diarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_exportar_excel_rep_diarioActionPerformed
        // TODO add your handling code here:this.workbook = new HSSFWorkbook();
        this.workbook = new HSSFWorkbook();
        this.sheet = this.workbook.createSheet();
        int guardarArchivo = 0;
        int se;
        String fecha = "", vendidos = "", insumo = "", u_medida = "", anterior = "", stock = "";

        se = reporte_diario.getRowCount();
        if (se > 0) {
            try {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo Excel", new String[]{"xsl"});
                chooser.setFileFilter(filter);
                guardarArchivo = chooser.showSaveDialog(this);
                if (guardarArchivo == 0) {
                    int contador = 0;
                    while (contador < se) {
                        fecha = (String) reporte_diario.getValueAt(contador, 0);
                        vendidos = (String) reporte_diario.getValueAt(contador, 1);
                        insumo = (String) reporte_diario.getValueAt(contador, 2);
                        u_medida = (String) reporte_diario.getValueAt(contador, 3);
                        anterior = (String) reporte_diario.getValueAt(contador, 4);
                        stock = (String) reporte_diario.getValueAt(contador, 5);

                        generaXLS_rep_diario(fecha, vendidos, insumo, u_medida, anterior, stock, chooser.getSelectedFile().getPath() + ".xls");
                        contador++;
                    }
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this, "Proceso Finalizado. Se ha escrito el archivo en la ruta:\n" + chooser.getSelectedFile().getAbsolutePath() + ".xsl", "Generación de reporte", 1);
                }

            } catch (Exception ex) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "No se pudo generar el reporte en Excel.\n CODIGO DE ERROR: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            Toolkit.getDefaultToolkit().beep();
            String path_jar = null;
            JOptionPane.showMessageDialog(null, "<html> <b> La tabla detalle de venta esta vacia. </b></html>", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon(path_jar + "/Icons/images/error.png"));
        }


    }//GEN-LAST:event_jbtn_exportar_excel_rep_diarioActionPerformed

    private void reporte_diarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reporte_diarioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_reporte_diarioKeyPressed

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
            java.util.logging.Logger.getLogger(insumos_vendidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(insumos_vendidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(insumos_vendidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(insumos_vendidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                insumos_vendidos dialog = new insumos_vendidos(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbtn_exportar_excel;
    private javax.swing.JButton jbtn_exportar_excel1;
    private javax.swing.JButton jbtn_exportar_excel_rep_diario;
    private javax.swing.JLabel lblNotaConsultaDia;
    private javax.swing.JTable reporte_diario;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla1;
    // End of variables declaration//GEN-END:variables

}
