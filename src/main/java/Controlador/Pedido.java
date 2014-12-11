/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import java.util.ArrayList;

/**
 *
 * @author kelvinjp
 */
public class Pedido {
    private int idpedido;
    private String correo;
    private int estado;
    private String ip; 
    private ArrayList<comidasxpedido> orden; 
    

       public String getIp() {
        return ip;
    }

    /**
     * @param ip the correo to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    /**
     * @return the idpedido
     */
    public int getIdpedido() {
        return idpedido;
    }

    /**
     * @param idpedido the idpedido to set
     */
    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the fecha
     */
  

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return the orden
     */
    public ArrayList<comidasxpedido> getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(ArrayList<comidasxpedido> orden) {
        this.orden = orden;
    }
}
