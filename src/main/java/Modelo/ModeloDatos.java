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


/**
 *
 * @author KelvinJp
 */
public class ModeloDatos {
    
    private Connection conn; 
    private final String hostname;
    private final String portnumber;
    private final String database;
    private final String username;
    private final String password;
    private final String url;
    private static ModeloDatos modelo;
    
    private ModeloDatos(){
     
    	 hostname = "69.195.124.204";
        portnumber = "3306";
        database = "grufacar_pruebasdb";
        username = "grufacar_kelvin";
        password = "kj4233pb";
        
        url = "jdbc:mysql://"+hostname+":"+portnumber+"/"+database+"?user="+username+"&password="+password;
        
        
        loadDriver();
    
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
       catch(ClassNotFoundException e){
           e.printStackTrace();
           System.out.println("error en la carga de driver");
           System.exit(1);
       } catch (InstantiationException e) {
           e.printStackTrace();
           System.out.println("error en la carga de driver");
           System.exit(1);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
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
