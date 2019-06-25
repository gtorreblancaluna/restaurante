package clases;


import java.sql.*;

public class conectate {
   
        
    public String bd = "restaurante";
    public String login = "root";
    public String password = "root";
    // clave america es para el culichi
//     public String password = "america";
    
    // clave para bucaramanga
//    public String password = "@mericaboxes100485";
    public String url = "jdbc:mysql://localhost/"+bd;
    //private String ip1 = null;
    Connection conn = null;
    /**
     * Constructor de DbConnection
     */
    public void conectate() {
        
        /*try {
            //obtenemos el driver de para mysql
            Class.forName("org.sqlite.JDBC");
            String ruta = "C:/restaurante/restaurante.db"; //especificamos la ruta de la base
            File base = new File(ruta);
            //obtenemos la conexión
            if (base.exists()) {       //si la base existe
                conn = DriverManager.getConnection("jdbc:sqlite:" + ruta); //conexion con la base
                JOptionPane.showMessageDialog(null, " Se ha conectado exitosamente!", "Conexion ", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "La base de datos no existe o no se encuentra en la ruta especificada.");
            }

           
            if (conn != null) {
                System.out.println("Conexión a base de datos listo");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }*/    
        
        //public String url = "jdbc:mysql://localhost/"+bd;
        
        try {
            //obtenemos el driver de para mysql
            
            Class.forName("com.mysql.jdbc.Driver");
            //obtenemos la conexión
            conn = DriverManager.getConnection(url, login, password);
            if (conn != null) {
                System.out.println("Conexion a base de datos " + bd + ". listo");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
    
    
    /**
     * Permite retornar la conexión
     */
    public Connection getConnection() {
        return conn;
    }
    
    public void desconectar() {
        conn = null;
        System.out.println("La conexion a la  base de datos " + bd + " a terminado");
    }

    //public void setIp(String value) {
     //   this.ip1 = value;
    //}

  //  public String getIp() {
  //      return this.ip1;
   // }
}
