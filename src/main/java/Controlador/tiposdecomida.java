/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

/**
 *
 * @author kelvinjp
 */
public class tiposdecomida {
    private int value;
    private String text;    
    public  tiposdecomida (int value, String text) {
    this.value=value;
     this.text=text;
}

    /**
     * @return the correo
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the nombre
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the nombre to set
     */
    public void setText(String text) {
        this.text = text;
    }

}
