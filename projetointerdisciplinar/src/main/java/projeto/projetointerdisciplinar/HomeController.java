/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projeto.projetointerdisciplinar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class HomeController implements Initializable {

    @FXML 
    private Button butSair;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    public void sair() throws IOException{
         Stage stage = (Stage) butSair.getScene().getWindow();
         
         FXMLLoader tela = new FXMLLoader(App.class.getResource("inicio.fxml"));
         
         Scene cena = new Scene(tela.load());
         
         stage.setScene(cena);
         //stage.setTitle("");
         stage.show();       
    }
    
}
