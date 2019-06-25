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
 * @author DM KONESH
 */
public class Ticket_nueva_orden {
    //Ticket attribute content

    private String contentTicket = "\n"
            //+ "{{nameLocal}}\n"
            //+ "RFC: {{rfc}}\n"
            //+ "{{direccion}}\n"
            //+ "{{direccion2}}\n"
            //+ "{{direccion3}}\n"
            //+ "{{expedition}}\n"
            //+ "{{telefono}}\n"
            + "============================= \n"
            + "No venta # {{ticket}} - Mesa # {{mesa}}\n"
            + "Peticion: {{cajero}}           \n"
            + "Fecha:{{fecha}}  Hora:{{dateTime}}\n"
            + "============================= \n"
            + "Cant      Articulo           \n"
            + "{{items}}                   \n"
            + "============================= \n"
            //+ "SUBTOTAL:        ${{subTotal}}\n"
            //+ "IVA:             ${{tax}}\n"
            //+ "Descuento:       ${{desc}} \n"
            //+ "TOTAL:             {{total}}\n"
            //+ "RECIBIDO:        ${{recibo}}\n"
            //+ "CAMBIO:          ${{change}}\n\n"
            + "\n"
            + "\n"
            //+ "*****FORMA DE PAGO******\n"
            //+ "   {{pago}}                                   \n"
            + "=============================\n"
            + "ORDEN DE PEDIDO \n"
            //+ "{{leyenda2}}\n"
            //+ "{{leyenda3}}\n"
            //+ "{{leyenda4}}\n"
            + "\n"
            //+ "\n"
            //+ "\n"
            + "============================= \n"
            + "Cliente: {{cliente}}\n"
            + "***Systems Chilpo***\n"
            //+ "**Gracias por su preferencia**\n"
            + "\n"
            + "\n";

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
    public void generar_ticket(String fecha, String hora, String items,String mesa,String mesero,String nombre_cliente,String ticket) {
        try {
            //this.contentTicket = this.contentTicket.replace("{{nameLocal}}", nameLocal);
            //this.contentTicket = this.contentTicket.replace("{{expedition}}", expedition);
            //this.contentTicket = this.contentTicket.replace("{{box}}", box);
            this.contentTicket = this.contentTicket.replace("{{ticket}}", ticket);
            this.contentTicket = this.contentTicket.replace("{{cajero}}", mesero);
            
            this.contentTicket = this.contentTicket.replace("{{fecha}}", fecha);
            this.contentTicket = this.contentTicket.replace("{{dateTime}}", hora);
            
            this.contentTicket = this.contentTicket.replace("{{items}}", items);
            this.contentTicket = this.contentTicket.replace("{{mesa}}", mesa);
            //this.contentTicket = this.contentTicket.replace("{{desc}}", desc);
            //this.contentTicket = this.contentTicket.replace("{{tax}}", tax);
           //this.contentTicket = this.contentTicket.replace("{{total}}", total);
            //this.contentTicket = this.contentTicket.replace("{{recibo}}", recibo);
            //this.contentTicket = this.contentTicket.replace("{{change}}", change);
            //this.contentTicket = this.contentTicket.replace("{{direccion}}", direccion);
            //this.contentTicket = this.contentTicket.replace("{{direccion2}}", direccion2);
            //this.contentTicket = this.contentTicket.replace("{{direccion3}}", direccion3);
            //this.contentTicket = this.contentTicket.replace("{{rfc}}", rfc);
            //this.contentTicket = this.contentTicket.replace("{{leyenda1}}", leyenda1);
           // this.contentTicket = this.contentTicket.replace("{{leyenda2}}", leyenda2);
            //this.contentTicket = this.contentTicket.replace("{{leyenda3}}", leyenda3);
            //this.contentTicket = this.contentTicket.replace("{{leyenda4}}", leyenda4);
            //this.contentTicket = this.contentTicket.replace("{{telefono}}", telefono);
           // this.contentTicket = this.contentTicket.replace("{{pago}}", pago);
            this.contentTicket = this.contentTicket.replace("{{cliente}}", nombre_cliente);
            System.out.println("ticket: "+contentTicket);
        } catch (Exception ex) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"Ocurrio un error al generar el ticket de venta.\n"+ex.getMessage(),"Error al generar el ticket",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void resetear() {
        contentTicket = null;
    }

    public void print() {
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
    }
}
