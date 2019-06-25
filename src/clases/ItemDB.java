package clases;

import static clases.ItemDB_mesas.general;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JSupport
 */
public class ItemDB {

    static sqlclass general = new sqlclass();
    Object[][] edi2;
    static Object[][] edi3;
    String colNameed[] = {"id_categorias", "nombre_categoria"};
    static String colNameed2[] = {"id_detalle_categorias", "descripcion"};
    /////////////////////////
    public static String[] mainMenuCodes; //= {"FOOD", "BEVE", "FOOD", "BEVE", "FOOD", "BEVE"};
    //public static String mainMenuDesc[] = {"FOOD", "BEVERAGES", "FOOD", "BEVERAGES", "FOOD", "BEVERAGES"};
    public static String[] mainMenuDesc;
    private static ArrayList list;

    public ItemDB() {
        
        llenar();
        /*edi2 = general.GetTabla(colNameed, "categorias", "SELECT g.`id_categorias`, g.`nombre_categoria` FROM categorias g order by g.`id_categorias`");
        System.out.println("cantidad de resultados" + edi2.length);

        mainMenuCodes = new String[edi2.length];
        mainMenuDesc = new String[edi2.length];
        String lineas;

        for (int i = 0; i < edi2.length; i++) {
            //System.out.println(edi2[i][1].toString()); //nombre de platillos
            // "<html><p>linea1</p><p>linea2</p></html>"
            if (edi2[i][1].toString().contains(" ")) {

                String[] palabras = edi2[i][1].toString().split(" ");
                lineas = "<html>";
                for (int j = 0; j < palabras.length; j++) {
                    //System.out.println(palabras[j]);   
                    lineas = lineas + "<p ALIGN=center>" + palabras[j] + "</p>";

                }
                lineas = lineas + "</html>";
                mainMenuDesc[i] = lineas;
                System.out.println(lineas);
                lineas = "";

            } else {
                mainMenuDesc[i] = (String) edi2[i][1];

            }

            mainMenuCodes[i] = (String) edi2[i][0];
            //System.out.println(edi2[i][0].toString()); //id platillos

        }*/
    }
    
    public void llenar(){
        general.conectate();
        edi2 = general.GetTabla(colNameed, "categorias", "SELECT g.`id_categorias`, g.`nombre_categoria` FROM categorias g order by g.`id_categorias`");
        general.desconecta();
        System.out.println("cantidad de resultados" + edi2.length);

        mainMenuCodes = new String[edi2.length];
        mainMenuDesc = new String[edi2.length];
        String lineas;

        for (int i = 0; i < edi2.length; i++) {
            //System.out.println(edi2[i][1].toString()); //nombre de platillos
            // "<html><p>linea1</p><p>linea2</p></html>"
            if (edi2[i][1].toString().contains(" ")) {

                String[] palabras = edi2[i][1].toString().split(" ");
                lineas = "<html>";
                for (int j = 0; j < palabras.length; j++) {
                    //System.out.println(palabras[j]);   
                    lineas = lineas + "<p ALIGN=center>" + palabras[j].toLowerCase() + "</p>";

                }
                lineas = lineas + "</html>";
                mainMenuDesc[i] = lineas;
                System.out.println(lineas);
                lineas = "";

            } else {
                mainMenuDesc[i] = (String) edi2[i][1].toString().toLowerCase();

            }

            mainMenuCodes[i] = (String) edi2[i][0];
            //System.out.println(edi2[i][0].toString()); //id platillos

        }
    
    
    
    
    }
    //DefaultTableModel datos = new DefaultTableModel(edi, columNames);
    //grformato.setModel(datos);

    public static ArrayList getSubMenu(String mainMenuCodes) {

        list = new ArrayList();
        String valor = mainMenuCodes.toString();
       
        general.conectate();
        edi3 = general.GetTabla(colNameed2, "detalle_categorias", "SELECT g.`id_detalle_categorias`, g.`descripcion` FROM detalle_categorias g where id_categorias='"+valor+"' AND g.`activo`='1' order by g.`id_categorias` ");
        general.desconecta();

        String subCode[] = new String[edi3.length];
        String subDesc[] = new String[edi3.length];
        StringBuilder lineas = new StringBuilder();
       
        for (int i = 0; i < edi3.length; i++) {
            System.out.println(edi3[i][1].toString()); //nombre de platillos
            // "<html><p>linea1</p><p>linea2</p></html>"            
               
            lineas.append("<html><p ALIGN=center>" + edi3[i][1].toString().toLowerCase() + "</p></html>");          
            subDesc[i] = lineas.toString();
            System.out.println(lineas.toString());
            
            lineas.setLength(0);
            lineas = new StringBuilder();
            
            
//            if (edi3[i][1].toString().contains(" ")) {
//                String[] palabras = edi3[i][1].toString().split(" ");
//                lineas.append("<html>");
//                for (int j = 0; j < palabras.length; j++) {
//                    //System.out.println(palabras[j]);   
//                    lineas.append("<p ALIGN=center>" + palabras[j].toLowerCase() + "</p>");
//                }
//                lineas.append("</html>");
//                subDesc[i] = lineas.toString();
//                //System.out.println(lineas);
//                lineas.setLength(0);
//                lineas = new StringBuilder();
//
//            } else {
//                subDesc[i] = (String) edi3[i][1].toString().toLowerCase();
//
//            }

            subCode[i] = (String) edi3[i][0]; //id platillo
            //System.out.println(edi2[i][0].toString()); //id platillos

        }


        list.add(subCode);
        list.add(subDesc);



        /*
         if (mainMenuCodes.equals("FOOD")) {
         String subCode[] = {"P", "B"};
         String subDesc[] = {"PIZZA", "BURGER"};

         list.add(subCode);
         list.add(subDesc);

         } else if (mainMenuCodes.equals("BEVE")) {
         String subCode[] = {"FJ", "HB"};
         String subDesc[] = {"Fruit Juice", "Hot Beverages"};

         list.add(subCode);
         list.add(subDesc);
         }
         */





        return list;
    }
}
