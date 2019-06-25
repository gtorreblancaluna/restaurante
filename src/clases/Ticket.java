/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Toolkit;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JOptionPane;

/**
 *
 *
 */
public class Ticket {
    //Ticket attribute content

    private StringBuilder contentTicket = new StringBuilder();
//    private String contentTicket = "\n"
//            + "{{nameLocal}}\n"
//            + "RFC: {{rfc}}\n"
//            + "{{direccion}}\n"
//            + "{{direccion2}}\n"
//            + "{{direccion3}}\n"
//            + "{{expedition}}\n"
//            + "{{telefono}}\n"
//            + "================================ \n"
//            + "Caja #{{box}} - No. venta #{{ticket}}\n"
//            + "Atendio: {{cajero}}\n"
//            + "Fecha:{{fecha}}  Hora:{{dateTime}}\n"
//            + "================================ \n"
//            + "Cant Descripcion       Importe\n"
//            + "{{items}}                     \n"
//            + "================================ \n"
//            + "SUBTOTAL:             ${{SubTotal}}\n"
//            + "Descuento:            ${{Descuento}} \n"
//            + "Cargo:                ${{Cargo}} \n"
//           /* + "La propina es opcional.......... \n"*/
//            + "Propina del {{prop}}%      ${{propina}} \n"
//            + "TOTAL:                ${{total}}\n\n"
//            + "RECIBIDO:             ${{recibido}}\n"
//            + "CAMBIO:               ${{change}}\n"
//            + "\n"
//            + "\n"
//            //+ "*****FORMA DE PAGO******\n"
//            //+ "   {{pago}}                                   \n"
//            + "================================\n"
//            + "{{leyenda1}}\n"
//            + "{{leyenda2}}\n"
//            + "{{leyenda3}}\n"
//            + "{{leyenda4}}\n"
//            + "{{leyenda5}}\n"
//            //+ "\n"
//            //+ "\n"
//            + "================================ \n"
//            + "Cliente: {{cliente}}\n"
//            + "Mesa: {{mesa}}\n"
//            + "**Gracias por su preferencia**\n"
//            + "\n"
//            + "\n"
//            + "\n";

    //El constructor que setea los valores a la instancia
//  Ticket(String nameLocal, String expedition, String box, String ticket, String caissier, String dateTime, String items, String subTotal, String tax, String total, String recibo, String change) {
//    this.contentTicket = this.contentTicket.replace("{{nameLocal}}", nameLocal);
//    this.contentTicket = this.contentTicket.replace("{{expedition}}", expedition);
//    this.contentTicket = this.contentTicket.replace("{{box}}", box);
//    this.contentTicket = this.contentTicket.replace("{{ticket}}", ticket);
//    this.contentTicket = this.contentTicket.replace("{{cajero}}", caissier);
//    this.contentTicket = this.contentTicket.replace("{{dateTime}}", dateTime);
//    this.contentTicket = this.contentTicket.replace("{{items}}", items);
//    this.contentTicket = this.contentTicket.replace("{{subTotal}}", subTotal);
//    this.contentTicket = this.contentTicket.replace("{{tax}}", tax);
//    this.contentTicket = this.contentTicket.replace("{{total}}", total);
//    this.contentTicket = this.contentTicket.replace("{{recibo}}", recibo);
//    this.contentTicket = this.contentTicket.replace("{{change}}", change);
//  }
    /**
     *
     * @param nameLocal
     * @param rfc
     * @param direccion
     * @param direccion2
     * @param direccion3
     * @param expedition
     * @param telefono
     * @param box
     * @param ticket
     * @param caissier
     * @param fecha
     * @param dateTime
     * @param items
     * @param total
     * @param pago
     * @param leyenda1
     * @param leyenda2
     * @param leyenda3
     * @param leyenda4
     * @param leyenda5
     * @param nombre_cliente
     */
    public void generar_ticket(String nameLocal, String rfc, String direccion, String direccion2, String direccion3, String expedition, String telefono, String box, String ticket, String caissier, String fecha, String dateTime, String items, String subTotal, String descuento, String cargo, String total, String propina, String prop, String recibido, String cambio, String pago, String leyenda1, String leyenda2, String leyenda3, String leyenda4, String leyenda5, String nombre_cliente, String mesa) {
        try {
            System.out.println("ticket items: " + items);
//            this.contentTicket = this.contentTicket.replace("{{nameLocal}}", nameLocal);
//            this.contentTicket = this.contentTicket.replace("{{rfc}}", rfc);
//            this.contentTicket = this.contentTicket.replace("{{direccion}}", direccion);
//            this.contentTicket = this.contentTicket.replace("{{direccion2}}", direccion2);
//            this.contentTicket = this.contentTicket.replace("{{direccion3}}", direccion3);
//            this.contentTicket = this.contentTicket.replace("{{expedition}}", expedition);
//            this.contentTicket = this.contentTicket.replace("{{telefono}}", telefono);
//            this.contentTicket = this.contentTicket.replace("{{box}}", box);
//            this.contentTicket = this.contentTicket.replace("{{ticket}}", ticket);
//            this.contentTicket = this.contentTicket.replace("{{cajero}}", caissier);
//
//            this.contentTicket = this.contentTicket.replace("{{fecha}}", fecha);
//            this.contentTicket = this.contentTicket.replace("{{dateTime}}", dateTime);
//
//            this.contentTicket = this.contentTicket.replace("{{items}}", items);
////            System.out.println("ticket items: " + items);
//            //this.contentTicket = this.contentTicket.replace("{{subTotal}}", subTotal);
//            //this.contentTicket = this.contentTicket.replace("{{desc}}", desc);
//            //this.contentTicket = this.contentTicket.replace("{{tax}}", tax);
//            this.contentTicket = this.contentTicket.replace("{{SubTotal}}", SubTotal);
//            this.contentTicket = this.contentTicket.replace("{{Descuento}}", Descuento);
//            this.contentTicket = this.contentTicket.replace("{{Cargo}}", Cargo);
//            this.contentTicket = this.contentTicket.replace("{{total}}", total);
//            this.contentTicket = this.contentTicket.replace("{{propina}}", propina);
//            this.contentTicket = this.contentTicket.replace("{{prop}}", prop);
//            this.contentTicket = this.contentTicket.replace("{{pago}}", pago);
//            this.contentTicket = this.contentTicket.replace("{{recibido}}", Recibido);
//            this.contentTicket = this.contentTicket.replace("{{change}}", Cambio);
//            //this.contentTicket = this.contentTicket.replace("{{recibo}}", recibo);
//            //this.contentTicket = this.contentTicket.replace("{{change}}", change);
//            this.contentTicket = this.contentTicket.replace("{{leyenda1}}", leyenda1);
//            this.contentTicket = this.contentTicket.replace("{{leyenda2}}", leyenda2);
//            this.contentTicket = this.contentTicket.replace("{{leyenda3}}", leyenda3);
//            this.contentTicket = this.contentTicket.replace("{{leyenda4}}", leyenda4);
//            this.contentTicket = this.contentTicket.replace("{{leyenda5}}", leyenda5);
//
//            this.contentTicket = this.contentTicket.replace("{{cliente}}", nombre_cliente);
//            this.contentTicket = this.contentTicket.replace("{{mesa}}", mesa);
//            System.out.println("CARGO: "+cargo+"\nDESCUENTO: "+descuento+"\nPROPINA: "+propina+"\nRECIBIDO: "+recibido+"\nCAMBIO: "+cambio);
            boolean x = false;
            if(leyenda1.equals("") && leyenda2.equals("") && leyenda3.equals("") && leyenda4.equals("") && leyenda5.equals(""))
                x=true;
            //2018.01.30 armado del ticket
            contentTicket.append("\n")
            .append(nameLocal+"\n")
            .append(!rfc.equals("")?"RFC: "+rfc+"\n":"")
            .append(!direccion.equals("") ? direccion+"\n":"")
            .append(!direccion2.equals("") ? direccion2+"\n":"")
            .append(!direccion3.equals("")? direccion3+"\n":"")
            .append(!expedition.equals("")? expedition+"\n":"")
            .append(!telefono.equals("")? telefono+"\n":"")
            .append("================================ \n")
            .append("Caja #"+box+" venta #"+ticket+"\n")
            .append("Atendio: "+caissier+"\n")
            .append("Fecha: "+fecha+"  Hora: "+dateTime+"\n")
            .append("================================ \n")
//            .append("Cant Descripcion       Importe"+"\n")
            .append("Cant Descripcion     Imp.  Subt."+"\n")
            .append("--------------------------------")
            .append(items+"                     \n")
            .append("================================ \n")
            .append(!subTotal.equals(total)?"Subtotal:             $"+subTotal+"\n":"")
            .append(!descuento.equals("")?"Descuento:            $"+descuento+"\n":"")
            .append(!cargo.equals("")?"Cargo:                $"+cargo+"\n":"")
            .append(!propina.equals("")?"Propina del "+prop+"%      $"+propina+"\n":"")
            .append("Total:                $"+total+"\n")
            .append(!recibido.equals("")?"Recibido:             $"+recibido+"\n":"")
            .append(!cambio.equals("")?"Cambio:               $"+cambio+"\n":"")
            .append("================================\n")
            .append(!leyenda1.equals("")?leyenda1+"\n":"")
            .append(!leyenda2.equals("")?leyenda2+"\n":"")
            .append(!leyenda3.equals("")?leyenda3+"\n":"")
            .append(!leyenda4.equals("")?leyenda4+"\n":"")
            .append(!leyenda5.equals("")?leyenda5+"\n":"")
            .append(!x?"================================\n":"")
            .append("Cliente: "+nombre_cliente+"\n")
            .append("Mesa: "+mesa+"\n")
            .append("**Gracias por su preferencia**\n")
            .append("\n\n");
            
            
            System.out.println("ticket: " + contentTicket);
        } catch (Exception ex) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Ocurrio un error al generar el ticket de venta.\n" + ex.getMessage(), "Error al generar el ticket", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void resetear() {
        contentTicket = null;
    }

    /*public void print() {
     //Especificamos el tipo de dato a imprimir
     //Tipo: bytes; Subtipo: autodetectado
     DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
     //Aca obtenemos el servicio de impresion por defatul
     //Si no quieres ver el dialogo de seleccionar impresora usa esto
     PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
     //Con esto mostramos el dialogo para seleccionar impresora
     //Si quieres ver el dialogo de seleccionar impresora usalo
     //Solo mostrara las impresoras que soporte arreglo de bits
     PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
     PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
     //        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
     //        PrintService service = ServiceUI.printDialog(null, 700, 200, printService, defaultService, flavor, pras);
     //Creamos un arreglo de tipo byte
     byte[] bytes;
     //Aca convertimos el string(cuerpo del ticket) a bytes tal como
     //lo maneja la impresora(mas bien ticketera :p)
     bytes = this.contentTicket.getBytes();
     //Creamos un documento a imprimir, a el se le appendeara
     //el arreglo de bytes
     Doc doc = new SimpleDoc(bytes, flavor, null);
     //Creamos un trabajo de impresión
     DocPrintJob job = defaultService.createPrintJob();
     //Imprimimos dentro de un try de a huevo
        
     try {
     //El metodo print imprime
     job.print(doc, null);
     } catch (Exception er) {
     JOptionPane.showMessageDialog(null, "Error al imprimir: " + er.getMessage());
     }
        
     }*/
    public void print() {
        try {
            //Especificamos el tipo de dato a imprimir
            //Tipo: bytes; Subtipo: autodetectado
            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            //Aca obtenemos el servicio de impresion por defatul
            //Si no quieres ver el dialogo de seleccionar impresora usa esto
            PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();

            //Con esto mostramos el dialogo para seleccionar impresora
            //Si quieres ver el dialogo de seleccionar impresora usalo
            //Solo mostrara las impresoras que soporte arreglo de bits
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
    //        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
    //        PrintService service = ServiceUI.printDialog(null, 700, 200, printService, defaultService, flavor, pras);
            //Creamos un arreglo de tipo byte
            byte[] bytes;
            //Aca convertimos el string(cuerpo del ticket) a bytes tal como
            //lo maneja la impresora(mas bien ticketera :p)
            bytes = this.contentTicket.toString().getBytes();
            //Creamos un documento a imprimir, a el se le appendeara
            //el arreglo de bytes
            Doc doc = new SimpleDoc(bytes, flavor, null);
            //Creamos un trabajo de impresión
            DocPrintJob job = defaultService.createPrintJob();
            //Imprimimos dentro de un try de a huevo
            //El metodo print imprime
            job.print(doc, null);
        } catch (Exception er) {
            JOptionPane.showMessageDialog(null, "Error al imprimir: " + er.getMessage());
        }
    }

}
