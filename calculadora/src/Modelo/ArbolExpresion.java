/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Seg_R
 */
public class ArbolExpresion{
    private Nodo root;
    private int nodo;
    
    public ArbolExpresion(){
        this.root  = null;
        this.nodo     = 0;
    }
    
    public ArbolExpresion(LinkedList exp){
        Object aux =  exp.get(0);
        Tupla t = new Tupla();
        t.setValor(aux.toString());
        if(operador(aux)){     
            t.setOperador(true);
        }else{
            t.setOperador(false);
        }
        Nodo op;
        Nodo q = new Nodo(t);
        Pila p = new Pila();
        boolean antesOperando = false;
        root = q;
        for(int i=1;i<exp.size(); i++ ){
            Object aux2 = exp.get(i);
            Tupla b = new Tupla(aux2.toString(),false);
            if(operador(aux2)){
                b.setOperador(true);
            }
            op = new Nodo(b);
            if(antesOperando){
                q = (Nodo) p.Sacar();
                q.setRight(op);
            }else{
                q.setLeft(op);
                p.Poner(q);
            }
            q = op;
            if(operador(exp.get(i))){
                     antesOperando = false;
            }else{
                antesOperando = true;
            }         
        }
        
    }
    
    public boolean isLeaf(Nodo R){
        return ((R.getLeft()==null)&&(R.getRight()==null));
    }
    
    public void setRoot(Nodo R){
        if(R!=null){
            this.root = R;
            nodo++;
        }else{
            this.root = R;
        }    
    }
    
    public Nodo getRoot(){
        return this.root;
    }
    
    public int getNodo(){
        return nodo;
    }
    
    private boolean operador(Object c){
        char operadores[] = {'+','-','*','/','^'};
        boolean existe = false;
        char aux =   c.toString().charAt(0);
        for(int i=0; ((i<5) && (!existe)); i++){
            if(aux == operadores[i]){
                existe = true;
            }        
        }
        return existe;
    }
    
    public double Evaluar(){
        return Evaluar(root);
    }
    
    private double Evaluar(Nodo R){
       double res=0;
       if(R==null){
            return res=0;
       }else{
            if(isLeaf(R)){
                String aux = R.getData().getValor();
                res = Double.parseDouble(aux);
            }else{
                double vizq = Evaluar(R.getLeft());
                double vder = Evaluar(R.getRight());
                Character op = R.getData().getValor().charAt(0);
                switch(op){
                    case '+' : res = vizq + vder;break;
                    case '-' : res = vizq - vder;break;
                    case '*' : res = vizq * vder;break;
                    case '/' : res = vizq / vder;break;
                    case '^' : res = Math.pow(vizq,vder);break;
                    default:; break;
                }
            }  
       }
       return res;
    }
    
    

}
