/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projeto.projetobuscavoos.App;
import rotas.Rotas;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class InfoVooController implements Initializable {
    @FXML
    private TextField txCompAerea;
    @FXML
    private TextField txDataPartida;
    @FXML
    private TextField txPaisPartida;
    @FXML
    private TextField txCidadePartida;  
    @FXML
    private TextField txPaisDestino;
    @FXML
    private TextField txCidadeDestino;  
    @FXML
    private TextField txPreco; 
    @FXML
    private TextField txAssento;
    
    public String idRota;
    
    private StringProperty idRota_aeres = new SimpleStringProperty();
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 



    public void setTxAssento(String poltrona) {
        txAssento.setText(poltrona);
    }
    
    
    public String getId() {
        return this.idRota;
    }
    public void setId(String id) {
        this.idRota = id;
    }
    public void setCompAerea(String empresa){
        txCompAerea.setText(empresa);
    }
    public void setDataPartida(String Data){
        txDataPartida.setText(Data);
    }
    public void setPaisPartida(String pais){
        txPaisPartida.setText(pais);
    }
    public void setCidadePartida(String cidade){
        txCidadePartida.setText(cidade);
    }
    public void setPaisDestino(String pais){
        txPaisDestino.setText(pais);
    }
    public void setCidadeDestino(String cidade){
        txCidadeDestino.setText(cidade);
    }
    public void setPreco(String preco){
        txPreco.setText(preco);
    }

    
}
