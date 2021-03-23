/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 * @author Seg_R
 */
public class Pila{
    private Object v[];
    private int tope, max;
    
    public Pila(){
        max = 100;
        v = new Object[max];
        tope = -1;
    }
    public Pila(int max){
        this.max = max;
        v = new Object[max];
        tope = -1;
    }
    
    public boolean Emty(){
        return tope == -1;
    }
    
    public boolean llena(){
        return tope == max-1;
    }
    
    public void Poner(Object dato){
        if(!llena()){
           v[++tope] = dato;
        }else{
           System.out.println("Pila Llena");
        }
    }
    
    public Object Sacar(){
        Object dato = null;
        if(!Emty()){
           dato = v[tope--];
        }else{
           System.out.println("Pila Vacia");
        }
        return dato;
    }
    
    public Object Cima(){
    	if(!Emty()){
    	   return v[tope];
        }else{
    	   return null; 
        }
    }	
}