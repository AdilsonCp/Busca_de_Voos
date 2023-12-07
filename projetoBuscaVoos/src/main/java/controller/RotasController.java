/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import projeto.projetobuscavoos.App;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class RotasController implements Initializable {
    @FXML
    private Label txCompAerea;
    @FXML
    private Label txDataPartida;
    @FXML
    private Label txPaisPartida;
    @FXML
    private Label txCidadePartida;  
    @FXML
    private Label txPaisDestino;
    @FXML
    private Label txCidadeDestino;  
    @FXML
    private Label txPreco; 
    @FXML
    private Label txId;
    
    public String idRota;
    
  
    /*
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    /*@FXML
    public void visualizar() throws IOException{        

        //FXMLLoader loader = new FXMLLoader(App.class.getResource("/telas/home.fxml"));
        //Scene scene = new Scene(loader.load());

        
        /*controller.setCompAerea(txCompAerea.getText());
        controller.setDataPartida(txDataPartida.getText());
        controller.setPaisPartida(txPaisPartida.getText());
        controller.setCidadePartida(txCidadePartida.getText());
        controller.setPaisDestino(txPaisDestino.getText());
        controller.setCidadeDestino(txCidadeDestino.getText());
        controller.setPreco(txPreco.getText());
        controller.setId(txId.getText());*/

        
        //this.idRota = txId.getText();
        //this.idRota = txId.getText();
        //System.out.println(this.idRota+" rotaC");
        //setIdRota(idRota);
        //this.controllers = controller;

        /*Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Informações");
        stage.show();

    }*/

    

    
    
    
}
