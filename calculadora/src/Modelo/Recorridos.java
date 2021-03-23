/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.util.LinkedList;

/**
 * @author Seg_R
 */
public class Recorridos{
    
    public static LinkedList preOrden(Nodo r){
        LinkedList l= new LinkedList();
        preOrden(r,l);
        return l;
    }
    
    private static void preOrden(Nodo r, LinkedList l){
        if(r!=null) {
            Tupla tpre = r.getData();  
            l.add(tpre.getValor());
            preOrden(r.getLeft(),l);
            preOrden(r.getRight(),l);           
        } 
    }
    public static LinkedList inOrden(Nodo r){
        LinkedList l= new LinkedList();
        inOrden(r,l);
        return l;
    }
    private static void inOrden(Nodo r,LinkedList l){
        if(r!=null){
           inOrden(r.getLeft(),l);
           Tupla tIn = r.getData();  
           l.add(tIn.getValor());
           inOrden(r.getRight(),l);
        }

    }
    
    public static LinkedList postOrden(Nodo r){
        LinkedList l= new LinkedList();
        postOrden(r,l);
        return l;
    }
    private static void postOrden(Nodo r,LinkedList l){
        if(r!=null){
            postOrden(r.getLeft(),l);
            postOrden(r.getRight(),l);
            Tupla tPost = r.getData();  
            l.add(tPost.getValor());
        }
    }
    
    public static String Mostrar(LinkedList l){
        LinkedList a = l;
        String expre=" "; 
        for(int i=0; i<a.size();i++){
            if((i+1)==a.size()){
                expre = expre + a.get(i).toString();
            }else{
                expre = expre + a.get(i).toString() + "  ";
            }
        }
        expre = expre + " ";
        return expre;
    }
    
//    private boolean Hoja(Nodo R)
//    {
//        return ((R.getLeft()==null)&&(R.getRight()==null));
//    }
    
}
