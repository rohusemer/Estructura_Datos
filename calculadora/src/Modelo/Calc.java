/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Persistencia.Persistencia;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Seg_R
 */
public class Calc extends Persistencia{
    private String expresiones[];
    private String valores[];
    private Integer nroPreguntas;
    
    public Calc(){ 
        lines = readAllText("src/Persistencia/ExpresioesM.txt");
    }

    public String[] getExpresiones() {
        return expresiones;
    }

    public void setExpresiones(String[] expresiones) {
        this.expresiones = expresiones;
    }
    
    public void setExpresiones(String expresiones_path) {
        this.expresiones = obtenerExpresion(expresiones_path);
    }

    public String[] getValores() {
        return valores;
    }

    public void setValores(String[] valores) {
        this.valores = valores;
    }
    public void setValores(String valores_path) {
        this.valores = obtenerValores(valores_path);
    }

    public Integer getNroPreguntas() {
        return nroPreguntas;
    }

    public void setNroPreguntas(Integer nroPreguntas) {
        this.nroPreguntas = nroPreguntas;
    }
    
    public double calcular(String Expre) throws Exception{
        double resultado=0;
        String aux = Expre;
        Comprobador scanner = new Comprobador();
        aux = scanner.Scan(aux);        
        if(aux.equals("P")||aux.equals("O")){     
            if(aux.equals("P")){
              throw new Exception("Verificar los paréntesis. Gracias.");
            }else{
              throw new Exception("El número de operadores no es correcto. Gracias.");
            }
        }else{
            Expresion e;
            e = new Expresion(aux);
            LinkedList exp = e.CompletoPrefija();
            if((exp.size()!=0)&&(exp.size()!=2)){
                ArbolExpresion ar = new ArbolExpresion(exp);
                resultado = ar.Evaluar(); 
            }else{
                throw new Exception("Verifique la expresión. Gracias.");
            }
        }
        return resultado;   
    }
    
    public LinkedList ListValor(String valor){
        int i=0;
        int n = valor.length()-1;
        String aux1,aux2;
        aux2=valor;
        aux1="";
        LinkedList e = new LinkedList();
        while((i<=n)){
            while((i<=n)&&(!Character.isDigit(aux2.charAt(i)))){
                i++;
            }
            aux1="";
            while((i<=n)&&((Character.isDigit(aux2.charAt(i)))||(aux2.charAt(i)=='.'))){    
                aux1=aux1+aux2.charAt(i);
                i++;
            }
            e.add(aux1);
        }
        return e;
    }
    
    public String covert(String c, LinkedList valor){
        String expr = " ";
        
        LinkedList completo = new LinkedList();
        Iterator<String> it = completo.iterator();
        int n = valor.size(),j=0;
        
        for(int i=0; ((i<c.length())&&(j<=n)) ;i++){
            
            if(Character.isLetter(c.charAt(i))){
                expr = expr + valor.get(j);
                j++;
            }else{
                expr = expr + c.charAt(i);
            }
        }
//        for(int i=0; ((i<c.length())&&(j<=n)) ;i++){
//            completo.addLast(c.charAt(i));
//        }
//        while(it.hasNext()){
//            if(it.next().equals(completo[i])){
//                
//            }
//        }
        
            
        return expr;
    }
    
    private String sep(String valor)
    {
        LinkedList a = ListValor(valor);
        String val=" "; 
        for(int i=0; i<a.size();i++)
        {
            if((i+1)==a.size())
                val = val + a.get(i).toString();
            else
                val = val + a.get(i).toString() + "  ";
        }
        val = val + " ";
        return val;
    }
    
    public void escribir(String exp, String valo){
        String valor = sep(valo);
        if (!isLineContained(lines, exp)) {
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(new File("src/Persistencia/ExpresioesM.txt"), true);
                PrintWriter pw = new PrintWriter(fos);
                if (lines[0].equals("")) {
                    pw.append(exp);
                } else {
                    pw.append("\n" + exp);
                }
                pw.close();

                lines = readAllText("src/Persistencia/ExpresioesM.txt");

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    fos.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (!isLineContained(lines, valo)) {
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(new File("src/Persistencia/ValoressM.txt"), true);
                PrintWriter pw = new PrintWriter(fos);
                if (lines[0].equals("")) {
                    pw.append(valo);
                } else {
                    pw.append("\n" + valo);
                }
                pw.close();

                lines = readAllText("src/Persistencia/ValoresM.txt");

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    fos.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        
    }
        
}
