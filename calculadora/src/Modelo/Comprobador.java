/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 * @author Seg_R
 */
public class Comprobador 
{
    public String Scan(String Exp) throws Exception {
        String x = limpiarEspacios(Exp);
        if(okParentesis(x)){
            if(okOperadores(x)){
                return x;
            }else{
                x="O";
            }
        }else{
            x="P";
        }
        return x;
    }
    
    private String limpiarEspacios(String exo){
        String aux = "";
        for(int i=0; i<exo.length();i++){
            if(exo.charAt(i)!=' '){
                aux = aux + exo.charAt(i);
            }
        }
        return aux;
    }
    private boolean okParentesis(String exp){
        Object aux;
        Pila p = new Pila();
        for(int i=0; i<exp.length();i++){
            if(exp.charAt(i)=='('){
                p.Poner(exp.charAt(i));
            }else{
                if(exp.charAt(i)==')'){
                    if(p.Emty()){
                        return false;
                    }else{
                       aux = p.Sacar();
                    }
                }
            }
        }
        return (p.Emty()==true);     
    }
    
    private boolean okOperadores(String exp) {
        String cad = limpiarParentesis(exp);
        boolean sw;
        if(Operador(cad.charAt(0))||(Operador(cad.charAt(cad.length()-1)))){
            return false;
        }else{
            sw=true;
            for(int i=1; i<cad.length()-1;i++){
                if(Operador(cad.charAt(i))&&(Operador(cad.charAt(i+1)))){
                    return false;
                }
            }
            return sw;
        }
    }
    private String limpiarParentesis(String exp){
        String aux="";
        for(int i=0; i<exp.length();i++){
            if((exp.charAt(i)!='(')&&(exp.charAt(i)!=')')){
                aux = aux + exp.charAt(i);
            }
        }
        return aux;
    }
    
    private boolean Operador(char op){
        boolean sig = false;
        switch(op){
            case '+' : sig = true; break;
            case '-' : sig = true; break;
            case '*' : sig = true; break;
            case '/' : sig = true; break;
            case '^' : sig = true; break;
        }  
	  return sig;
    }

}
