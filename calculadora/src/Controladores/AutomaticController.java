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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author segur
 */
public class AutomaticController implements Initializable {

    @FXML
    private TextField txtExp;
    @FXML
    private TextField txtVa;
    @FXML
    private TextField txtExpf;
    @FXML
    private Button btnCalcular;
    @FXML
    private ListView<String> listResult;
    @FXML
    private Button play;
    
    private String qsts_path;

    private String anws_path;
    
    public static Calc partida;
    @FXML
    private Spinner<Integer> nbsQuestionsSpinner;
    @FXML
    private Button btnNext;
    
    private Iterator<String> it;
    private Iterator<String> it2;
    private int count;
    public static StringBuilder input;
    private ObservableList<String> items;
    Calc calc = new Calc();
    Expresion e;
    Comprobador scanner= new Comprobador();
    String[] expres;
    String[] valores;
    LinkedList resultados;
    @FXML
    private Label lbResl;
    @FXML
    private TextField txtOrde;
    @FXML
    private ComboBox<String> cbOrd;
    @FXML
    private Label lbAdv;
    @FXML
    private TextField txtR;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        count = 0;
        nbsQuestionsSpinner.setVisible(false);
        btnNext.setDisable(true);
        btnCalcular.setDisable(true);
        items = FXCollections.observableArrayList();
        nbsQuestionsSpinner.setVisible(false);
        resultados = new LinkedList();
        lbAdv.setVisible(false);
        lbResl.setVisible(false);
        cbOrd.getItems().addAll("PreOrden","InOrden","PostOrden");
        listResult.setVisible(false);
        //expres = calc.getExpresiones();
        //it = Arrays.stream(expres).iterator();
        //valores = calc.getValores();
        //it2 = Arrays.stream(valores).iterator();
        //count++;
    }    

    @FXML
    private void Calc(ActionEvent event) {
        
        try {
            Evaluar();            
        } catch (Exception e) {
            
            //javax.swing.JOptionPane.showMessageDialog(e, e.getMessage());
        }   
    }

    @FXML
    private void Save(ActionEvent event) {
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

    @FXML
    private void Next(ActionEvent event) {
//        String[] expres = calc.getExpresiones();
//        it = Arrays.stream(expres).iterator();
//        String[] valores = calc.getValores();
//        it2 = Arrays.stream(valores).iterator();
        txtExp.setText(" ");
        txtVa.setText(" ");
        txtExpf.setText(" ");
        txtR.setText(" ");
        txtOrde.setText(" ");
        if (it.hasNext() && it2.hasNext()) {
                
                count++;
                txtExp.setText(it.next());
                txtVa.setText(it2.next());
                btnCalcular.setDisable(false);
                btnNext.setDisable(true);
                
                
        }else{
            System.out.println("Ingrese expresion. Gracias.");
           
            btnNext.setDisable(false);

            System.out.println(resultados);
            Extra.generarFlashLabel(lbAdv, Integer.MAX_VALUE);
            lbResl.setVisible(true);
            listResult.getItems().setAll(items);
            listResult.setVisible(true);
        }
           
  
    }

    @FXML
    private void exp(ActionEvent event) {
        if (!Extra.areFilesChoosed(qsts_path, anws_path)) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select questions file");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(null);
            qsts_path = file.getAbsolutePath();
            calc.setExpresiones(qsts_path);
            if (Extra.areFilesChoosed(qsts_path, anws_path)) {
                setSpinner(calc.getExpresiones().length);
            }
        }
    }

    @FXML
    private void val(ActionEvent event) {
        if (!Extra.areFilesChoosed(qsts_path, anws_path)) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select anwsers file");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(null);
            anws_path = file.getAbsolutePath();
            calc.setValores(anws_path);
            if (Extra.areFilesChoosed(qsts_path, anws_path)) {
                setSpinner(calc.getExpresiones().length);
            }

        }
    }
    
    private void setSpinner(int n) {

        SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, n);
        nbsQuestionsSpinner.setValueFactory(factory);
        // need this code because it would not editable 
        TextFormatter<Integer> formatter = new TextFormatter<>(factory.getConverter(), factory.getValue());
        nbsQuestionsSpinner.getEditor().setTextFormatter(formatter);
        nbsQuestionsSpinner.setVisible(true);
    }


    @FXML
    private void playAut(ActionEvent event) {
        if (Extra.areFilesChoosed(qsts_path, anws_path)) {
            //btnNext.setDisable(false);
            btnCalcular.setDisable(false);
            expres = calc.getExpresiones();
            it = Arrays.stream(expres).iterator();
            valores = calc.getValores();
            it2 = Arrays.stream(valores).iterator();
            count++;
            txtExp.setText(it.next());
            txtVa.setText(it2.next());
            nbsQuestionsSpinner.setVisible(true);
            
        }else {
            Extra.generarFlashLabel(lbAdv, 3);
            lbAdv.setVisible(true);

        }
    }
    
    private void Evaluar() throws Exception 
     {
        //items = FXCollections.observableArrayList();
        String exp = txtExp.getText();
        String exp1 = txtVa.getText();
        LinkedList vl = calc.ListValor(exp1);
        String c = calc.covert(exp,vl);
        txtExpf.setText(c);
        txtR.setText(String.valueOf(calc.calcular(c)));
        resultados.addLast(String.valueOf(calc.calcular(c)));
        items.setAll(resultados);
        btnNext.setDisable(false);
        btnCalcular.setDisable(true);
                 
        
    }

    @FXML
    private void mostrar(ActionEvent event) throws Exception {
        String exp = txtExp.getText();
        String exp1 = txtVa.getText();
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
    
}
