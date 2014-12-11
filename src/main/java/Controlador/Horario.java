/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import java.util.Calendar;


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
      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.HOUR_OF_DAY, h);
      cal.set(Calendar.MINUTE, m);
	  java.util.Date tiempo1 = cal.getTime(); 
       
	  Calendar cal2 = Calendar.getInstance();
      cal2.set(Calendar.HOUR_OF_DAY, horaApertura);
      cal2.set(Calendar.MINUTE, minutoApertura); 
      
       java.util.Date tiempo2 = cal2.getTime();
       
       Calendar cal3 = Calendar.getInstance();
       cal3.set(Calendar.HOUR_OF_DAY, horacierre);
       cal3.set(Calendar.MINUTE, minutocierre); 
       java.util.Date tiempo3 = cal3.getTime();
       
      return tiempo1.after(tiempo2) && tiempo1.before(tiempo3);
  }
  public java.util.Date getHoraa(){
      
	  Calendar cal2 = Calendar.getInstance();
      cal2.set(Calendar.HOUR_OF_DAY, horaApertura);
      cal2.set(Calendar.MINUTE, minutoApertura); 
      
       java.util.Date tiempo2 = cal2.getTime();
       return tiempo2; 
  }
   public java.util.Date getHorac(){
      
	   Calendar cal3 = Calendar.getInstance();
       cal3.set(Calendar.HOUR_OF_DAY, horacierre);
       cal3.set(Calendar.MINUTE, minutocierre); 
       java.util.Date tiempo3 = cal3.getTime();
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