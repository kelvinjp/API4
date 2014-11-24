package Controlador;

import Modelo.ModeloDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Maneja los requests 
 */
@Controller
public class Controlador {
	
	private static final Logger logger = LoggerFactory.getLogger(Controlador.class);
	
    Horario horario;
    
    public Controlador(){
        horario = new Horario();
        horario(); 
}
     
        
    /*
    -------------------------------------------------Cliente------------------------------------------------------------
    */
    
    
    /**
     * agrega un nuevo cliente a la base de datos 
     * @param nombre
     * @param apellido
     * @param correo
     * @param direccion
     * @param telefono
     * @return el cliente agregado. 
     */
    @RequestMapping("/addCliente")
    public @ResponseBody Cliente agregarCliente(
            @RequestParam(value="nombre", required=true) String nombre,
            @RequestParam(value="apellido", required=true) String apellido,
            @RequestParam(value="correo", required=false) String correo,
            @RequestParam(value="direccion", required=false) String direccion,            
            @RequestParam(value="telefono", required=true) String telefono){
         System.out.println("++++++++AGREGANDO CLIENTE++++-");
         
         
         
        PreparedStatement pstmt1;
        ModeloDatos md = ModeloDatos.getInstance();
      /*
        hacemos un insert a la base de datos con los paramentros recibidos 
        */
        String sql1;
           Cliente cmds = null;    
        sql1  = "INSERT INTO `cliente` (`correo`,`nombre`,`apellido`,`direccion`, `telefono`) VALUES (?,?,?,?,?) ";
        
       
        try{        
            md.connectar();            
            pstmt1 = md.getConn().prepareStatement(sql1);           
            pstmt1.setString(1, correo);
            pstmt1.setString(2,nombre);
            pstmt1.setString(3, apellido);
            pstmt1.setString(4, direccion);  
            pstmt1.setString(5, telefono);  
            int exito = pstmt1.executeUpdate();
           
            if(exito == 0 )
                System.out.println("No funciono "+exito);
            else
                System.out.println("Si FUNCIONO"+exito);
            
           
                cmds = new Cliente(correo, nombre, apellido, direccion, telefono);
            
        }
        catch(Exception e){
         e.printStackTrace();
        }finally{
            return cmds;
        }  
    }
    
    @RequestMapping("/getCliente")
    public @ResponseBody Cliente getCliente(            
            @RequestParam(value="correo", required=true) String correo){
         System.out.println("Correo: "+ correo);
        
        Statement stmt;
       String querry =  "SELECT * FROM cliente WHERE correo ='"+correo+"'";
        ModeloDatos md = ModeloDatos.getInstance();
        Cliente cmds = null; 
       try{
           md.connectar();
           stmt =  ModeloDatos.getInstance().getConn().createStatement() ;
           ResultSet rs = stmt.executeQuery(querry);
          while(rs.next())
           {   
                    String nombre  =rs.getString("nombre");
                    String apellido  =rs.getString("apellido");
                    String direccion  =rs.getString("direccion");
                    String telefono  =rs.getString("telefono");
                    
                     cmds = new Cliente(correo, nombre, apellido, direccion, telefono);
                     return cmds;
            }
       }
       catch(Exception e){
           e.printStackTrace();
       }
        return  new Cliente("", "", "", "", "");
    }

    
    /*
    ---------------------------------COMIDA---------------------------------------
    */   
    @RequestMapping("/menu")
    public @ResponseBody ArrayList<Comidas> getComidas(){
       System.out.println("++===Retornado Comidas==");
        
        Statement stmt;
       ArrayList<Comidas> comidas;
            comidas = new ArrayList<Comidas>();
       String querry =  "SELECT * FROM comidas";
        ModeloDatos md = ModeloDatos.getInstance();
      
       try{
           md.connectar();
           stmt =  ModeloDatos.getInstance().getConn().createStatement() ;
           ResultSet rs = stmt.executeQuery(querry);
          while(rs.next())
           {   
                     int id =rs.getInt("id");
                    String nombre  =rs.getString("nombre");
                     double precio =rs.getDouble("precio");
                     String descripcion =rs.getString("descripcion"); 
                     int tipo=rs.getInt("tipo");
                     Comidas cmds = new Comidas(id, nombre, precio, descripcion, tipo);
                     comidas.add(cmds);
               
            }
       }
       catch(Exception e){
           e.printStackTrace();
       }
       
    return comidas;
    }
          
         @RequestMapping("/editarItem")
    public @ResponseBody Comidas editarItem(
            @RequestParam(value="id", required=true) int id,
            @RequestParam(value="nombre", required=true) String nombre,
            @RequestParam(value="precio", required=true) double precio,
            @RequestParam(value="descripcion", required=false) String descripcion,
            @RequestParam(value="tipo", required=false) int tipo){
         System.out.println("++===Actualizando Comida==");
         Comidas mds = null; 
        ModeloDatos modelodatos;
             PreparedStatement pstmt;
        
        String sql1 = "UPDATE `COMIDAS` SET `nombre`=?, `precio`=?, `descripcion`=?, `tipo`=?   WHERE `id`= ? ";
        try{
            System.out.println(id);
            
            modelodatos = ModeloDatos.getInstance();
            modelodatos.connectar();
            pstmt = modelodatos.getConn().prepareStatement(sql1);
            pstmt.setString(1, nombre);
            pstmt.setDouble(2, precio);
            pstmt.setString(3, descripcion);
            pstmt.setInt(4, tipo);
            pstmt.setInt(5, id);
          int x =  pstmt.executeUpdate();
          System.out.println(x);
                    
          mds = getComida(id);
          return mds;
          
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            return mds;
        }
    }
    
    
    public Comidas getComida(int id){
       Statement stmt;
       String querry =  "SELECT * FROM comidas WHERE ID="+id;
        ModeloDatos md = ModeloDatos.getInstance();
        Comidas cmds = null; 
      
       try{
           md.connectar();
           stmt =  ModeloDatos.getInstance().getConn().createStatement() ;
           ResultSet rs = stmt.executeQuery(querry);
          while(rs.next())
           {   
                    String nombre  =rs.getString("nombre");
                     double precio =rs.getDouble("precio");
                     String descripcion =rs.getString("descripcion"); 
                     int tipo=rs.getInt("tipo");
                     cmds = new Comidas(id, nombre, precio, descripcion, tipo);
                     return cmds;
            }
       }
       catch(Exception e){
           e.printStackTrace();
       }
       
    return cmds;  
    }
    
    
    /**
     *
     * @param id
     */
    @RequestMapping("/eliminarItem")
    public @ResponseBody int eliminarItem(
            @RequestParam(value="id", required=true) int id){
        
        ModeloDatos md = ModeloDatos.getInstance();
       PreparedStatement pstmt = null;
        
        
        String sql = "DELETE FROM  `comidas` WHERE `id` = ?";
        
      
        try{
            md.connectar();
            pstmt = md.getConn().prepareStatement(sql);
            pstmt.setInt(1, id);
           
            pstmt.executeUpdate();
                    
            
        }catch(Exception e){
        }
        finally{
            return id;
        }
    }
    
    /**
     * 
     * @param nombre
     * @param precio
     * @param descripcion
     * @param tipo
     * @return 
     */
    @RequestMapping("/agregarItem")
    public @ResponseBody Comidas agregarComida(
            @RequestParam(value="nombre", required=true) String nombre,
            @RequestParam(value="precio", required=true) double precio,
            @RequestParam(value="descripcion", required=false) String descripcion,
            @RequestParam(value="tipo", required=false) int tipo){
         
         int completada =0;
        PreparedStatement pstmt1 = null;
        ModeloDatos md = ModeloDatos.getInstance();
        //Connection conn;
        
        String sql1 = null;
           Comidas cmds = null;    
        sql1  = "INSERT INTO `comidas` (`nombre`,`precio`,`descripcion`,`tipo`) VALUES (?,?,?,?) ";
        
       
        try{        
            md.connectar();            
            pstmt1 = md.getConn().prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);           
            pstmt1.setString(1, nombre);
            pstmt1.setDouble(2,precio);
            
            pstmt1.setString(3, descripcion);
            pstmt1.setInt(4, tipo);            
            
            pstmt1.executeUpdate();
            ResultSet  rs = pstmt1.getGeneratedKeys();
            if(rs.next()){
              completada = rs.getInt(1); 
              System.out.println(completada);
                cmds = new Comidas(completada, nombre, precio, descripcion, tipo);
            }    
        }
        catch(Exception e){
         e.printStackTrace();
        }finally{
            return cmds;
        }  
    }
    /*
    -------------------------------------Login
    */
    /**
     * 
     * @param username
     * @param password
     * @return 
     */
    @RequestMapping("/login")
    public @ResponseBody Usuario login(
            @RequestParam(value="username", required=true) String username,
             @RequestParam(value="password", required=true) String password){
        
        System.out.println(password);
        
        Statement stmt;
       String querry =  "SELECT * FROM usuario WHERE user= '"+username+"' AND pass= '"+password+"'";
        ModeloDatos md = ModeloDatos.getInstance();
        Comidas cmds = null; 
      
       try{
           md.connectar();
           stmt =  ModeloDatos.getInstance().getConn().createStatement() ;
           ResultSet rs = stmt.executeQuery(querry);
          while(rs.next())
           {   
                    String nombre  =rs.getString("nombre");
                     Usuario user = new Usuario(0, username, password, nombre);
                     return user; 
            }
       }
       catch(Exception e){
           e.printStackTrace();
       }
       
        
        Usuario  usuario = null;
            usuario = new Usuario(0, "wrong","wrong", "wrong");
         return usuario;
    }
    
	  /*
        -----------------------------------PEDIDOS--------------------
        */
	@RequestMapping(value ="/verOrden", method = RequestMethod.POST)
	public @ResponseBody Pedido getOrden(@RequestBody int idpdd) {
		
             Statement stmt;
       int idpedido=-1;
       Pedido p = new Pedido();
       ArrayList<comidasxpedido> detalles = new ArrayList<comidasxpedido>() ; 
       comidasxpedido deta;  
       String querry =  "SELECT * FROM pedidos INNER JOIN comidasxpedido "
               + "ON pedidos.idpedidos = comidasxpedido.idpedidos "
               + "INNER JOIN comidas ON comidasxpedido.comidas = comidas.id "
               + "WHERE pedidos.`idpedidos` = "+idpdd+" LIMIT 0 , 30";
        ModeloDatos md = ModeloDatos.getInstance();
      
       try{
           md.connectar();
           stmt =  ModeloDatos.getInstance().getConn().createStatement() ;
           ResultSet rs = stmt.executeQuery(querry);
          while(rs.next())
           {   
               if(idpedido!=rs.getInt("idpedidos")){
                     idpedido =rs.getInt("idpedidos");
                    String correo =rs.getString("correo");
                     int estado =rs.getInt("estado");
                     int idcomida =rs.getInt("comidas");
                     int cantidad=rs.getInt("cantidad"); 
                     String nombre=rs.getString("nombre");
                     double precio = rs.getDouble("precio");
                     String descripcion=rs.getString("descripcion");
                     int tipo=rs.getInt("tipo"); 
                     
                     detalles = new ArrayList<comidasxpedido>();
                     
                     deta = new comidasxpedido();
                     deta.setCantidad(cantidad);
                     deta.setIdcomida(idcomida);
                     deta.setNombre(nombre);
                     deta.setPrecio(precio);
                     
                     //agregamos el producto de esa fila a los detalles
                     detalles.add(deta);
                     //Pedido p =  new Pedido();
                     p.setCorreo(correo);
                     p.setEstado(estado);
                     p.setIdpedido(idpedido);
                     p.setOrden(detalles);
                     //agregamos la nueva factura 
                     
                     //obtenemos el producto de esa fila 
                     
                     //agregamos los detalles a esa factura
                     
               }else{
                  
                    deta = new comidasxpedido();
                    deta.setCantidad(rs.getInt("cantidad"));
                     deta.setIdcomida(rs.getInt("comidas"));
                     deta.setNombre(rs.getString("nombre"));
                     deta.setPrecio(rs.getDouble("precio"));
                     //agregamos el producto de esa fila a los detalles
                     detalles.add(deta);
                    p.setOrden(detalles);
               }
               
            }
       }
       catch(Exception e){
           e.printStackTrace();
       }
         return p;
        }

      
        /**
         * 
         * @param pedido
         * @return 
         */
        @RequestMapping(value = "/actulizarOrden", method = RequestMethod.POST)
	public @ResponseBody ArrayList<Pedido> actualizarOrden(@RequestBody Pedido pedido
                ) {
		logger.info("Actulalizando Pedido");
                
                
                Comidas mds = null; 
        ModeloDatos modelodatos;
             PreparedStatement pstmt;
        
        String sql1 = "UPDATE `pedidos` SET `estado`=?   WHERE `idpedidos`= ? ";
        try{
            modelodatos = ModeloDatos.getInstance();
            modelodatos.connectar();
            pstmt = modelodatos.getConn().prepareStatement(sql1);
            
            pstmt.setInt(1, pedido.getEstado());
            pstmt.setInt(2, pedido.getIdpedido());
          int x =  pstmt.executeUpdate();
          System.out.println("1 Se actualizo el pedido, 2 no se actualizo: "+x);
       
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            return getPedidos();
        }
        }
	/**
         * Crea un nuevo pedido 
         * @param pedido
         * @return 
         */
	@RequestMapping(value = "/rest/emp/create", method = RequestMethod.POST)
	public @ResponseBody Pedido crearPedido(@RequestBody Pedido pedido
                ) {
            
            
             int completada =0;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;
        ModeloDatos md = ModeloDatos.getInstance();
        //Connection conn;
        
        String sql1 = null;
        String sql2 = null;
        String sql3 = null;
        int idpedido = 0; 
               
        sql1  = "INSERT INTO `pedidos` (`correo`,`estado`) VALUES (?,?) ";
        
        sql2 = "INSERT INTO `comidasxpedido` (`idpedidos`, `comidas`,`cantidad`)"
                + " VALUES(?,?,?)";
        sql3 = "INSERT INTO `fechapedido` (`idpedidos`, `fecha`)"
                + " VALUES(?,?)";
        
        try{        
            md.connectar();            
            pstmt1 = md.getConn().prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);           
            pstmt1.setString(1, pedido.getCorreo());
            pstmt1.setInt(2,pedido.getEstado() );  
            pstmt1.executeUpdate();
            ResultSet  rs = pstmt1.getGeneratedKeys();
            //--------------------Agregamos la fecha
           if(rs.next()){
            
            pstmt3 = md.getConn().prepareStatement(sql3);
            pstmt3.setInt(1, rs.getInt(1));
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            pstmt3.setTimestamp(2, date);
            pstmt3.executeUpdate();
            //-------------------Agregamos las comidas del pedido
            pstmt2 = md.getConn().prepareStatement(sql2);
            
               System.out.println("Ïnsertando comida EN PEDIDO: "+rs.getInt(1));
               idpedido = rs.getInt(1); 
                for(comidasxpedido x : pedido.getOrden()){
                  System.out.println("COMIDA:"+x.getIdcomida());
                  System.out.println("cantidad:"+x.getCantidad());
                    
                  pstmt2.setInt(1, rs.getInt(1));
                   pstmt2.setInt(2, x.getIdcomida());
                   pstmt2.setInt(3, x.getCantidad());
                   pstmt2.executeUpdate();  
                }                       
            }                               
        }
        catch(Exception e){
         e.printStackTrace();
        }finally{
            return getOrden(idpedido);
        }
        }
	 
    
        /**
         * 
         * @return Todos los pedidos
         */
        @RequestMapping("/pedidos")
        public @ResponseBody ArrayList<Pedido> getPedidos(){
            
             Statement stmt;
       int idpedido=-1;
       ArrayList<Pedido> pedidos= new ArrayList<Pedido>();
       ArrayList<comidasxpedido> detalles = new ArrayList<comidasxpedido>() ; 
       comidasxpedido deta;  
       String querry =  "SELECT * FROM pedidos \n" +
"INNER JOIN comidasxpedido \n" +
"ON pedidos.idpedidos = comidasxpedido.idpedidos \n "+
               "INNER JOIN comidas\n" +
"ON comidasxpedido.comidas = comidas.id";
        ModeloDatos md = ModeloDatos.getInstance();
      
       try{
           md.connectar();
           stmt =  ModeloDatos.getInstance().getConn().createStatement() ;
           ResultSet rs = stmt.executeQuery(querry);
          while(rs.next())
           {   
               if(idpedido!=rs.getInt("idpedidos")){
                     idpedido =rs.getInt("idpedidos");
                    String correo =rs.getString("correo");
                     int estado =rs.getInt("estado");
                     int idcomida =rs.getInt("comidas");
                     int cantidad=rs.getInt("cantidad"); 
                     String nombre=rs.getString("nombre");
                     double precio = rs.getDouble("precio");
                     String descripcion=rs.getString("descripcion");
                     int tipo=rs.getInt("tipo"); 
                     
                     detalles = new ArrayList<comidasxpedido>();
                     
                     deta = new comidasxpedido();
                     deta.setCantidad(cantidad);
                     deta.setIdcomida(idcomida);
                     deta.setNombre(nombre);
                     deta.setPrecio(precio);
                     //agregamos el producto de esa fila a los detalles
                     detalles.add(deta);
                     Pedido p =  new Pedido();
                     p.setCorreo(correo);
                     p.setEstado(estado);
                     p.setIdpedido(idpedido);
                     p.setOrden(detalles);
                     //agregamos la nueva factura 
                     pedidos.add(p);
                     //obtenemos el producto de esa fila 
                     
                     //agregamos los detalles a esa factura
                     
               }else{
                  
                    deta = new comidasxpedido();
                    deta.setCantidad(rs.getInt("cantidad"));
                     deta.setIdcomida(rs.getInt("comidas"));
                     deta.setNombre(rs.getString("nombre"));
                     deta.setPrecio(rs.getDouble("precio"));
                     //agregamos el producto de esa fila a los detalles
                     detalles.add(deta);
                    pedidos.get(pedidos.size()-1).setOrden(detalles);
               }
               
            }
       }
       catch(Exception e){
           e.printStackTrace();
       }
         return pedidos;
        }
    
        
        
        
        
        
    /*
        -----------------------------------------------HORARIO
        
      
        */   
    @RequestMapping(value = "/actulizarHorario", method = RequestMethod.POST)
	public @ResponseBody Horario actualizarHorario(
                @RequestBody Horario hr                
                ) {
            
            horario.setHoraApertura(hr.getHoraApertura());
            horario.setHoracierre(hr.getHoracierre());
            horario.setMinutoApertura(hr.getMinutoApertura());
            horario.setMinutocierre(hr.getMinutocierre());
	
            java.util.Date apertura =  horario.getHoraa();
            java.util.Date cierre =  horario.getHorac();
            
            
             Comidas mds = null; 
        ModeloDatos modelodatos;
             PreparedStatement pstmt;
        
        String sql1 = "UPDATE `horario` SET `apertura`=?, `cierre`=?  WHERE `idhorario`= ? ";
        java.sql.Time theTime = new java.sql.Time(apertura.getTime());
        java.sql.Time theTime2 = new java.sql.Time(cierre.getTime());
        try{
            
            modelodatos = ModeloDatos.getInstance();
            modelodatos.connectar();
            pstmt = modelodatos.getConn().prepareStatement(sql1);
            
            pstmt.setTime(1, theTime);
            pstmt.setTime(2, theTime2);
            pstmt.setInt(3, 1);
            
          int x =  pstmt.executeUpdate();
          System.out.println("1 SE GUARDO LA HORA, 0 NO SE GUARDO: "+x);
          
          
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            return hr;
        }
        }
        
        @RequestMapping(value = "/horarioDisponible", method = RequestMethod.GET)
	public @ResponseBody Disponible isHorario() {
		
            Calendar now = Calendar.getInstance();
            
            int hora = now.get(Calendar.HOUR_OF_DAY); 
                int min = now.get(Calendar.MINUTE); 
                
                System.out.println("Server Hours: "+hora+":"+min);
                
                System.out.println(hora);
                Disponible disp = new Disponible();
                disp.setHora(hora);
                disp.setMin(min);
                disp.setHa(horario.getHoraApertura());
                disp.setHc(horario.getHoracierre());
                disp.setMa(horario.getMinutoApertura());
                disp.setMc(horario.getMinutocierre());
                
                
                boolean x = horario.isTime2(hora,min);
                System.out.println("Horario disponible: "+x);
                
                disp.setDisponible(x);
                return disp;
	} 
        
        
        /**
         * 
         * @return 
         */
        @RequestMapping(value = "/horario", method = RequestMethod.GET)
	public @ResponseBody Horario horario() {
            
            Statement stmt;
       String querry =  "SELECT * FROM horario";
        ModeloDatos md = ModeloDatos.getInstance();
        Comidas cmds = null; 
      
       try{
           md.connectar();
           stmt =  ModeloDatos.getInstance().getConn().createStatement() ;
           ResultSet rs = stmt.executeQuery(querry);
          while(rs.next())
           {   
                    java.sql.Time theTime  =rs.getTime("apertura");
                    java.sql.Time theTime2  =rs.getTime("cierre");
                    horario.setHoraApertura(theTime.getHours());
                    horario.setMinutoApertura(theTime.getMinutes());
                    horario.setHoracierre(theTime2.getHours());
                    horario.setMinutocierre(theTime2.getMinutes());
            }
       }
       catch(Exception e){
           e.printStackTrace();
       }
       
                return horario;
	} 
//----------------TIPOS DE COMIDA--------------------------------------------
	/**
         * 
         * @return retorna todas las comidas 
         */
         @RequestMapping("/tiposdecomida")
    public @ResponseBody ArrayList<tiposdecomida> getTiposdecomidas(){
       System.out.println("++===Retornado tipos de  Comidas==");
        
        Statement stmt;
       ArrayList<tiposdecomida> tdc;
            tdc = new ArrayList<tiposdecomida>();
       String querry =  "SELECT * FROM tiposdecomida";
        ModeloDatos md = ModeloDatos.getInstance();
      
       try{
           md.connectar();
           stmt =  ModeloDatos.getInstance().getConn().createStatement() ;
           ResultSet rs = stmt.executeQuery(querry);
          while(rs.next())
           {   
                     int value =rs.getInt("value");
                    String text  =rs.getString("text");
                     
                     tiposdecomida cmds = new tiposdecomida(value, text);
                     tdc.add(cmds);               
            }
       }
       catch(SQLException e){
           e.printStackTrace();
       }       
    return tdc;
    }
          
         @RequestMapping("/editartiposdecomida")
    public @ResponseBody tiposdecomida editarTipodecomida(
            @RequestParam(value="value", required=true) int value,
            @RequestParam(value="text", required=true) String text){
         System.out.println("++===Actualizando Tipo de Comida==");
         tiposdecomida mds = null; 
        ModeloDatos modelodatos;
             PreparedStatement pstmt;
        
        String sql1 = "UPDATE `tiposdecomida` SET  `text`=?   WHERE `value`= ? ";
        try{
            modelodatos = ModeloDatos.getInstance();
            modelodatos.connectar();
            pstmt = modelodatos.getConn().prepareStatement(sql1);
            pstmt.setString(1, text);
            pstmt.setDouble(2, value);
          int x =  pstmt.executeUpdate();
          System.out.println(x);
                    
          mds = gettipodeomida(value);
          return mds;
          
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            return mds;
        }
    }
    
    
    public tiposdecomida gettipodeomida(int id){
       Statement stmt;
       String querry =  "SELECT * FROM tiposdecomida WHERE value="+id;
        ModeloDatos md = ModeloDatos.getInstance();
        tiposdecomida cmds = null; 
      
       try{
           md.connectar();
           stmt =  ModeloDatos.getInstance().getConn().createStatement() ;
           ResultSet rs = stmt.executeQuery(querry);
          while(rs.next())
           {   
                    int value =rs.getInt("value");
                    String text  =rs.getString("text");
                     cmds = new tiposdecomida(value, text);
                     return cmds;
            }
       }
       catch(Exception e){
           e.printStackTrace();
       }
       
    return cmds;  
    }
    
    
    /**
     *
     * @param value
     */
    @RequestMapping("/eliminartiposdecomida")
    public @ResponseBody int eliminartiposdecomida(
            @RequestParam(value="value", required=true) int value){
        
        ModeloDatos md = ModeloDatos.getInstance();
       PreparedStatement pstmt = null;
        
        
        String sql = "DELETE FROM  `tiposdecomida` WHERE `value` = ?";
        
      
        try{
            md.connectar();
            pstmt = md.getConn().prepareStatement(sql);
            pstmt.setInt(1, value);
           
            pstmt.executeUpdate();                    
            
        }catch(Exception e){
        }
        finally{
            return value;
        }
    }
    
    /**
     * 
     * @param value
     * @param text
     * @param descripcion
     * @param tipo
     * @return 
     */
    @RequestMapping("/agregartiposdecomida")
    public @ResponseBody tiposdecomida agregartiposdecomida(
            @RequestParam(value="text", required=true) String text){
         
         int completada =0;
        PreparedStatement pstmt1 = null;
        ModeloDatos md = ModeloDatos.getInstance();
        //Connection conn;
        
        String sql1 = null;
           tiposdecomida cmds = null;    
        sql1  = "INSERT INTO `tiposdecomida` (`text`) VALUES (?) ";
        
       
        try{        
            md.connectar();            
            pstmt1 = md.getConn().prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);           
            pstmt1.setString(1, text);
            
            pstmt1.executeUpdate();
            ResultSet  rs = pstmt1.getGeneratedKeys();
            if(rs.next()){
              completada = rs.getInt(1); 
              System.out.println(completada);
                cmds = new tiposdecomida(completada, text);
            }    
        }
        catch(Exception e){
         e.printStackTrace();
        }finally{
            return cmds;
        }  
    } 
}
