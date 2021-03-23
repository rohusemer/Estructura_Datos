/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 * @author Seg_R
 */
public class Nodo{
    private Nodo left;
    private Tupla Data;
    private Nodo right;
    
    public Nodo(){
        Data = new Tupla();
        left = null;
        right = null;
    }
    public Nodo( Tupla dat){
        this.Data = dat;
        this.left = null;
        this.right = null;
    }
    
    public Nodo(Nodo Hisq, Tupla dat, Nodo Hder){
        this.Data.setValor(dat.getValor());
        this.Data.setOperador(dat.getOperator());
        this.left = Hisq;
        this.right = Hder;
    }
    
    public void setData(Tupla dat){
        this.Data.setValor(dat.getValor());
        this.Data.setOperador(dat.getOperator());
    }
    
    public void setLeft(Nodo Hisq){
        this.left = Hisq;
    }
    
    public void setRight(Nodo Hder){
        this.right = Hder;
    }
    
    public Tupla getData(){
        return this.Data;
    }
    
    public Nodo getLeft(){
        return this.left;
    }
    
    public Nodo getRight(){
        return this.right;
    }
    
}
