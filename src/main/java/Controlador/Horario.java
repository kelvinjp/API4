/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 *
 * @author kelvinjp
 */
public class Horario {
  private int horaApertura;
  private int horacierre;
  private int minutoApertura;
  private int minutocierre;
  
  
 
  
  


  public boolean isTime(int h, int m){
      if(h >= horaApertura && h < getHoracierre()){
          System.out.println("Es tiempo");
          
          return true;
          
      }else return false;
  }
  /**
   * 
   * @param h
   * @param m
   * @return 
   */
  public boolean isTime2(int h, int m){
       java.util.Date tiempo1 = new Time(h, m, 0); 
       
       java.util.Date tiempo2 = new Time(horaApertura,minutoApertura,0);
       java.util.Date tiempo3 = new Time(horacierre,minutocierre,0);
       
      return tiempo1.after(tiempo2) && tiempo1.before(tiempo3);
  }
  public java.util.Date getHoraa(){
      
       java.util.Date tiempo2 = new Time(horaApertura,minutoApertura,0);
       return tiempo2; 
  }
   public java.util.Date getHorac(){
      
       java.util.Date tiempo3 = new Time(horacierre,minutocierre,0);
       return tiempo3; 
  }

    /**
     * @return the horaApertura
     */
    public int getHoraApertura() {
        return horaApertura;
    }

    /**
     * @param horaApertura the horaApertura to set
     */
    public void setHoraApertura(int horaApertura) {
        this.horaApertura = horaApertura;
    }

    /**
     * @return the horacierre
     */
    public int getHoracierre() {
        return horacierre;
    }

    /**
     * @param horacierre the horacierre to set
     */
    public void setHoracierre(int horacierre) {
        this.horacierre = horacierre;
    }

    /**
     * @return the minutoApertura
     */
    public int getMinutoApertura() {
        return minutoApertura;
    }

    /**
     * @param minutoApertura the minutoApertura to set
     */
    public void setMinutoApertura(int minutoApertura) {
        this.minutoApertura = minutoApertura;
    }

    /**
     * @return the minutocierre
     */
    public int getMinutocierre() {
        return minutocierre;
    }

    /**
     * @param minutocierre the minutocierre to set
     */
    public void setMinutocierre(int minutocierre) {
        this.minutocierre = minutocierre;
    }
  
}
