/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Component;
import java.awt.Container;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class sqlclass {

    public conectate con;
       
    

    public sqlclass() {
       con = new conectate();
       
    }

    /*  METODO PARA DETERMINAR SI UN REGISTRO YA EXISTE EN LA BASE DE DATOS
     TIENE COMO PARAMETROS (nombre de la tabla, nombre columna,)
     */
    public void desconecta() {
        con.desconectar();
    }
    public void conectate(){
      
     con.conectate();
     
    }
    
    public Connection getConnection() {
        return con.getConnection();
    }
    
    
    
    public boolean existe(String tabla, String colName, String id) {
        int registros = 0;
        conectate();
        try {
            
            PreparedStatement pstm = con.getConnection().prepareStatement("SELECT count(1) as total FROM " + tabla + " where " + colName + " = "+ id +"");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (registros > 0) {
            return true;
        } else {
            return false;
        }
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
    
    public boolean existe_facturado(String id) {
        int registros = 0;
        conectate();
        try {
            
            PreparedStatement pstm = con.getConnection().prepareStatement("SELECT count(1) as total FROM venta where facturado = 1 AND id_venta = "+ id +"");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (registros > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean existe_empleado(String pass, String id_venta) {
        int registros = 0;
        conectate();
        try {            
            PreparedStatement pstm = con.getConnection().prepareStatement("SELECT COUNT(*)AS total FROM venta v WHERE v.id_empleado=(SELECT e.id_empleado FROM empleado e WHERE e.contraseña= "+ pass +")  AND v.preventa = '1' AND v.id_venta = "+ id_venta +"");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (registros > 0) {
            return true;
        } else {
            return false;
        }
    }
    public boolean existe_administrador(String pass) {
        int registros = 0;
        conectate();
        try {            
            PreparedStatement pstm = con.getConnection().prepareStatement("SELECT COUNT(*) AS total FROM empleado e WHERE e.contraseña= "+ pass +" AND e.administrador = 1");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (registros > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean existe_email() {
        int registros = 0;
        conectate();
        try {
            
            PreparedStatement pstm = con.getConnection().prepareStatement("select count(1) as total from email ");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (registros > 0) {
            return true;
        } else {
            return false;
        }
    }
    

 //Funcion para deshabilitar un panel
 public static void setEnableContainer(Container c, boolean band) {
        
    Component[] components = c.getComponents();
    c.setEnabled(band);
    for(int i = 0; i < components.length; i++){           
     components[i].setEnabled(band);

     if(components[i] instanceof Container){
      setEnableContainer((Container)components[i], band);
     }
   
 }       
} 
    
    /* INSERTA UN NUEVO REGISTRO EN LA BASE DE DATOS
     * PARAMETROS(Un array de String con los datos a insertar,la instruccion sql)
     */
    public void InsertarRegistro(String datos[], String sql) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement(sql);
            for (int i = 0; i <= datos.length - 1; i++) {
                pstm.setString(i + 1, datos[i]);
            }
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /* BORRA UN REGISTRO DE LA BASE DE DATOS
     * parametros (Nombre de la tabla, nombre columna, Codigo unico)
     */
    public void DeleteRegistro(String tabla, String col, String id) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("delete from " + tabla + " where " + col + " = ?");
            pstm.setString(1, id);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void ResetearTabla (String tabla) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("truncate " + tabla + " ");
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
     public String ContarFilas(String tabla) {
        String num_filas = null;

        try {

            Statement s = con.getConnection().createStatement();
            ResultSet rs = s.executeQuery("SELECT COUNT(id_mesa)FROM "+ tabla +" ");
            if (rs.next()) {
                num_filas = rs.getString(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return num_filas;

//SELECT SUM(cantidad) as total FROM especificaciones WHERE orden_trabajo_idorden_trabajo=19
    }
        
    public void folioactualiza(String id) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("UPDATE folio  SET folioactual=folioactual+1 where idfolio = ?");
            pstm.setString(1, id);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public String ultimoid() {
        String id_cliente = null;

        try {

            Statement s = con.getConnection().createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(id_clientes) AS id FROM clientes");
            if (rs.next()) {
                id_cliente = rs.getString(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return id_cliente;

//SELECT SUM(cantidad) as total FROM especificaciones WHERE orden_trabajo_idorden_trabajo=19
    }
       public String ultimoid_renta() {
        String id_renta = null;

        try {

            Statement s = con.getConnection().createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(id_renta) AS id FROM renta");
            if (rs.next()) {
                id_renta = rs.getString(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return id_renta;

//SELECT SUM(cantidad) as total FROM especificaciones WHERE orden_trabajo_idorden_trabajo=19
    }
       public String ultimo_id(String col,String tabla) {
        String id = null;

        try {

            Statement s = con.getConnection().createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX("+ col +") FROM "+ tabla +" ");
            if (rs.next()) {
                id = rs.getString(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;

//SELECT SUM(cantidad) as total FROM especificaciones WHERE orden_trabajo_idorden_trabajo=19
    }

    public String sumacant_detalle_cantidad(String id){

        String tot = null;

        try {

            Statement s = con.getConnection().createStatement();
            ResultSet rs = s.executeQuery("SELECT SUM(detalle_renta_precio) as total FROM detalle_renta WHERE id_renta='"+id+"'");
            if (rs.next()) {
                tot = rs.getString(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return tot;

    }
    
    public String sumacant_abonos(String id){

        String tot = null;

        try {

            Statement s = con.getConnection().createStatement();
            ResultSet rs = s.executeQuery("SELECT SUM(abonos_cantidad) as total FROM abonos WHERE id_renta='"+id+"'");
            if (rs.next()) {
                tot = rs.getString(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return tot;

    }
    /* ACTUALIZA UNA TABLA DE LA BASE DE DATOS
     * parametros (Un Array de string con los datos a actualizar, la instruccion sql )
     */

    public void UpdateRegistro(String datos[], String sql) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement(sql);
            for (int i = 0; i <= datos.length - 1; i++) {
                pstm.setString(i + 1, datos[i]);
            }
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /*  METODO PARA OBTENER TODOS LOS DATOS DE UNA TABLA
     *  parametros (Un array con los nombres de las columnas, el nombre de la tabla, la instruccion sql)
     */
    public Object[][] GetTabla(String colName[], String tabla, String sql) {
        int registros = 0;
        int cantFilas = 0;
        int count = 0;
        //obtenemos la cantidad de registros existentes en la tabla
        try {
            conectate();
            PreparedStatement pstm = con.getConnection().prepareStatement(sql);
            try (ResultSet res = pstm.executeQuery()) {
                while (res.next()) {
                    ++count;
                }

                System.out.println("Cantidad de filas" + count);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        Object[][] data = new String[count][colName.length];
        String col[] = new String[colName.length];

        //realizamos la consulta sql y llenamos los datos en "Object"
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement(sql);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                for (int j = 0; j <= colName.length - 1; j++) {
                    col[j] = res.getString(colName[j]);
                    data[i][j] = col[j];
                }
                i++;
                 
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return data;
       
    }
    
    /* METODO PARA TODOS LOS DATOS DE UNA COLUMNA
     * parametros (Nombre de la tabla, nombre columna, instruccion sql )
     */
    public Object[] GetColumna(String tabla, String colName, String sql) {
        int registros = 0;
        //obtenemos la cantidad de registros existentes en la tabla
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("SELECT count(1) as total FROM " + tabla);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        Object[] data = new String[registros];
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement(sql);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i] = res.getString(colName);
                i++;
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }

    /* METODO PARA OBTENER UN DATO DE UNA TABLA
     PARAMETROS (nombre columna, instruccion sql)
     */
    public String GetData(String colName, String sql) {
        String data = new String();
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement(sql);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                data = res.getString(colName);                
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }
}
