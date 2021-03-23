/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.ArbolExpresion;
import Modelo.Calc;
import Modelo.Comprobador;
import Modelo.Expresion;
import Modelo.Extra.Extra;
import Modelo.Recorridos;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author segur
 */
public class ManualController implements Initializable {

    @FXML
    private TextField txtExpre;
    @FXML
    private TextField txtValores;
    @FXML
    private Button btnCalcular;
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField txtExprf;
    @FXML
    private TextField result;
    @FXML
    private TextField txtOrde;
    @FXML
    private ComboBox<String> cbOrd;
    Calc calc = new Calc();
    Expresion e;
    Comprobador scanner= new Comprobador();
    @FXML
    private Label lbAdv;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbOrd.setDisable(true);
        cbOrd.getItems().addAll("PreOrden","InOrden","PostOrden");
        lbAdv.setVisible(false);
    }    

    @FXML
    private void validValor(KeyEvent event) {
//        char c = event.getCharacter().charAt(0);
//        if(!(Character.isDigit(c))) {
//            event.consume();
//        }
    }

    @FXML
    private void Calcular(ActionEvent event) {
        try {
            Evaluar();            
        } catch (Exception e) {
            
            //javax.swing.JOptionPane.showMessageDialog(e, e.getMessage());
        }   
    }

    @FXML
    private void Save(ActionEvent event) {
        String exp = txtExpre.getText();
        String valo = txtValores.getText();
        LinkedList vl = calc.ListValor(valo);
        if(exp=="" && valo==""){
            Extra.generarFlashLabel(lbAdv, 3);
            lbAdv.setVisible(true);
        }else{
            calc.escribir(exp, valo); 
        }
    }
    
    private void Evaluar() throws Exception 
     {
        String exp = txtExpre.getText();
        String exp1 = txtValores.getText();
        LinkedList vl = calc.ListValor(exp1);
        String c = calc.covert(exp,vl);
        result.setText( String.valueOf(calc.calcular(c)));
        txtExprf.setText(c);
        txtOrde.setText(" ");
        cbOrd.setDisable(false);
        btnGuardar.setDisable(false);
        System.out.println(txtExprf.getText());
    }
    

    @FXML
    private void mostrar(ActionEvent event) throws Exception{
        String exp = txtExpre.getText();
        String exp1 = txtValores.getText();
        LinkedList vl = calc.ListValor(exp1);
        String c = calc.covert(exp,vl);
        c = scanner.Scan(c);
        Object opcion = cbOrd.getValue();
//        txtOrde.clear();
        
        if(c.equals("P")||c.equals("O")){     
            if(c.equals("P"))
              System.out.println("Verificar los paréntesis. Gracias");
            else
              System.out.println("Verificar operadores. Gracias");
        }else{
            e = new Expresion(c);
            LinkedList exp2 = e.CompletoPrefija();
            if (opcion.equals("InOrden") && ((exp2.size()!=0)&&(exp2.size()!=2))) {
               txtOrde.setText(" ");
                ArbolExpresion ar = new ArbolExpresion(exp2);         
                LinkedList as = Recorridos.inOrden(ar.getRoot()); 
                txtOrde.setText(Recorridos.Mostrar(as));
            }
            if (opcion.equals("PreOrden") && ((exp2.size()!=0)&&(exp2.size()!=2))) {
                txtOrde.setText(" ");
                ArbolExpresion ar = new ArbolExpresion(exp2);         
                LinkedList as = Recorridos.preOrden(ar.getRoot()); 
                txtOrde.setText(Recorridos.Mostrar(as));
               
            }
            if (opcion.equals("PostOrden") && ((exp2.size()!=0)&&(exp2.size()!=2))) {
                 txtOrde.setText(" ");
                ArbolExpresion ar = new ArbolExpresion(exp2);         
                LinkedList as = Recorridos.postOrden(ar.getRoot()); 
                txtOrde.setText(Recorridos.Mostrar(as));
                
            }
        }
        
    }

    @FXML
    private void atras(ActionEvent event) {
        try {
            Pane principal = (Pane) new FXMLLoader(getClass().getResource("/Vistas/inicio.fxml")).load();
            Stage secondStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            secondStage.setTitle("Play Automatic");
            Scene scene = new Scene(principal);
            scene.getStylesheets().add("/Vistas/css/style.css");
            secondStage.setScene(scene);
            secondStage.setResizable(false);
            secondStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
