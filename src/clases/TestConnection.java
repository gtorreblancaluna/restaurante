package clases;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * 
 */
import java.sql.*;
import javax.swing.JOptionPane;




public class TestConnection {
    
     public String bd = "restaurante";
     public String login = "root";
     public String password = "america";
     public String url = "jdbc:mysql://localhost/"+bd;
     Connection conect = null;
     PreparedStatement psd= null;
     Statement stSentencias=null;
     Connection conn = null;
     private String ip1 = null;
     
     
        
    public Connection conexion()
    {
    
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            conect=DriverManager.getConnection(url, login,password);
//            JOptionPane.showMessageDialog(null,"Conectado");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error"+e,"Error de Conexion",JOptionPane.ERROR_MESSAGE);
        }
        return null;
                
        
    }
public Connection getConnection(){
      return conect;
   }
  public void ejecutar( String sql)  throws SQLException
  {
      try{
          stSentencias.execute(sql);
          
      }catch(SQLException ex){
          throw ex;
      }
  }
   
     public void desconectar(){
      conect = null;
      System.out.println("La conexion a la  base de datos "+bd+" a terminado");
   }
    
}
  
        
  

