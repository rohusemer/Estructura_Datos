/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author segur
 */
public class Calculadora extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Vistas/inicio.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/Vistas/css/style.css");

        primaryStage.setTitle("Calculadora de Expresiones");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
//        try {
//            
//            String s = "(10.3+5)*2 + (4/2)";
//            String c1 = "(A+B)-(C*D)/E^F";
//            String s1 = "2,10,5,10,2.2,2";
//            String aux = "";
//            
//            
//            System.out.println(aux);
//            Calc cal = new Calc();
//            LinkedList vl = cal.ListValor(s1);
//            String c = cal.covert(c1,vl);
//            
//            try {
//                Expresion e = new Expresion(s);
//                Expresion e1 = new Expresion(c);
//                System.out.println(cal.calcular(s));
//                System.out.println(c1);
//                System.out.println(c + "valor: " +cal.calcular(c));
//                
//                System.out.println(e.CompletoPrefija() + "copleta prefia");
//                
//                System.out.println(e.Completo()+" Notación prefija.");
//                
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//            Comprobador scanner = new Comprobador();
//            s = scanner.Scan(s);
//            Expresion e;
//            String error="";
//            if(s.equals("P")||s.equals("O"))
//            {
//                if(s.equals("P"))
//                    error="Verificar los paréntesis. Gracias";
//                else
//                    error="Verificar operadores. Gracias";
//                System.out.println(error);
//            }else
//            {
//                e = new Expresion(s);
//                LinkedList exp = e.CompletoPrefija();
//                if((exp.size()!=0)&&(exp.size()!=2))
//                {
//                    ArbolExpresion ar = new ArbolExpresion(exp);
//                    System.out.println(e.getExpresion());
//                    System.out.println(e.Infija());
//                    System.out.println(e.Prefija());
//                    
//                    System.out.println("Arbol de Expresiones:");
//                    
//                    LinkedList as = Recorridos.inOrden(ar.getRoot());
//                    System.out.println(Recorridos.Mostrar(as));
//                }else
//                    System.out.println("Ingrese expresion. Gracias.");
//                
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(Calculadora.class.getName()).log(Level.SEVERE, null,ex);
//        }
    }
    
}
