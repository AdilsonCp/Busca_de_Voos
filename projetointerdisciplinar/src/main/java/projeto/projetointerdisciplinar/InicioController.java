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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author adils
 */
public class InicioController implements Initializable {
    
    @FXML
    private TextField textUsuario;
    @FXML
    private TextField textRegistro;
    @FXML
    private Button butEntrar;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void entrar() throws IOException{
         Stage stage = (Stage) textUsuario.getScene().getWindow();
         
         FXMLLoader tela = new FXMLLoader(App.class.getResource("home.fxml"));
         
         Scene cena = new Scene(tela.load());
         
         stage.setScene(cena);
         stage.setTitle("Home");
         stage.show();
    }
    
}
