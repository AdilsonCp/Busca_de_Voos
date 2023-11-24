/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projeto.buscarvoos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class RotasController implements Initializable {
    @FXML
    private Label textCompAerea;
    @FXML
    private Label textDataPartida;
    @FXML
    private Label textPaisPartida;
    @FXML
    private Label textCidadePartida;  
    @FXML
    private Label textPaisDestino;
    @FXML
    private Label textCidadeDestino;  
    @FXML
    private Label textPreco; 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
    public void editar() throws IOException{
        FXMLLoader loader = new FXMLLoader(App.class.getResource("infoVoos.fxml"));
        Scene scene = new Scene(loader.load());

        InfoVoosController controller = loader.getController();
        controller.setCompAerea(textCompAerea.getText());
        controller.setDataPartida(textDataPartida.getText());
        controller.setPaisPartida(textPaisPartida.getText());
        controller.setCidadePartida(textCidadePartida.getText());
        controller.setPaisDestino(textPaisDestino.getText());
        controller.setCidadeDestino(textCidadeDestino.getText());
        controller.setPreco(textPreco.getText());
    

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Editar empregado");
        stage.show();
    }
    
}
