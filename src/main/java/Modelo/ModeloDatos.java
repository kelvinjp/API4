/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author KelvinJp
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author KelvinJp
 */
public class ModeloDatos {
    
    private Connection conn; 
    private String hostname;
    private String portnumber;
    private String database;
    private String username;
    private String password;
    private String url;
    private static ModeloDatos modelo;
    
    private ModeloDatos(){
        hostname = "localhost";
        portnumber = "80";
        database = "pruebasdb";
        username = "root";
        password = "";
        
        url = "jdbc:mysql://"+hostname+/*":"+portnumber+*/"/"+database+"?user="+username+"&password="+password;
        
        //loadDriver();
    
    }
    
    public static ModeloDatos getInstance(){
        if(modelo == null){
            modelo = new ModeloDatos();
        }
        return modelo;
    }
    
   private void loadDriver() {
   
       try{
           Class.forName("com.mysql.jdbc.Driver").newInstance();
       }
       catch(Exception e){
           System.out.println("error en la carga de driver");
           System.exit(1);
       }
   }
   
   public void connectar() throws SQLException{
       conn = DriverManager.getConnection(url);   
   }
   public void desconnectar(){
       if(getConn()!=null){
           try{
                getConn().close();
           }
           catch(Exception e){
           e.printStackTrace();
           }
       }
   }
   
  
    /**
     * @return the conn
     */
    public Connection getConn() {
        return conn;
    }
   
   
    
}
