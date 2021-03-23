/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author segur
 */
public class InicioController implements Initializable {

    @FXML
    private Button btnAut;
    @FXML
    private Button btnMan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void automatic(ActionEvent event) {
        try {
            Pane principal = (Pane) new FXMLLoader(getClass().getResource("/Vistas/Automatic.fxml")).load();
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
    private void manual(ActionEvent event) {
        try {
            Pane principal = (Pane) new FXMLLoader(getClass().getResource("/Vistas/Manual.fxml")).load();
            Stage secondStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            secondStage.setTitle("Play Manual");
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
