/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 * @author Seg_R
 */
public class Tupla{
    private String  dat;
    private boolean operador;
    
    public Tupla(){
        operador = false;
        dat= "";
    }
    
    public Tupla(String va , boolean  op){
        dat = va;
        operador = op;
    }
    
    public void setValor(String va){
        this.dat = va;
    }
    
    public void setOperador(boolean op){
        this.operador = op;
    }
    
    public String getValor(){
        return dat;
    }
    
    public boolean getOperator(){
        return operador;
    }
}
