/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;
import javax.swing.JFrame;
import org.jvnet.substance.SubstanceLookAndFeel;
import javax.swing.UIManager;
import restaurante.iniciar_sesion;
import restaurante.principal;

/**
 *
 * @author KONESH
 */
public class Temas {
    
    public static void main(String[] args){
    try {
            //ORDEN.setDefaultLookAndFeelDecorated(true);
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
                
               // JFrame.setDefaultLookAndFeelDecorated(true);
               
               iniciar_sesion.setDefaultLookAndFeelDecorated(true);
               
               // SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.OfficeSilver2007Skin");
               
               //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        
        } catch (Exception e) {
            e.printStackTrace();
        }
          
          new iniciar_sesion().show();
    
    }
    
}
