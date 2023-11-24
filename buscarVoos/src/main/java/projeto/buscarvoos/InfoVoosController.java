/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projeto.buscarvoos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class InfoVoosController implements Initializable {

    @FXML
    private TextField textCompAerea;
    @FXML
    private TextField textDataPartida;
    @FXML
    private TextField textPaisPartida;
    @FXML
    private TextField textCidadePartida;  
    @FXML
    private TextField textPaisDestino;
    @FXML
    private TextField textCidadeDestino;  
    @FXML
    private TextField textPreco; 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setCompAerea(String empresa){
        textCompAerea.setText(empresa);
    }
    public void setDataPartida(String Data){
        textDataPartida.setText(Data);
    }
    public void setPaisPartida(String pais){
        textPaisPartida.setText(pais);
    }
    public void setCidadePartida(String cidade){
        textCidadePartida.setText(cidade);
    }
    public void setPaisDestino(String pais){
        textPaisDestino.setText(pais);
    }
    public void setCidadeDestino(String cidade){
        textCidadeDestino.setText(cidade);
    }
    public void setPreco(String preco){
        textPreco.setText(preco);
    }
    
}
