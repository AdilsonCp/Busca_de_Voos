package projeto.buscarvoos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InicioController implements Initializable {

    @FXML
    private Button btncadLogin;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    public void cadastraLogin() throws IOException{
        
        //Trocar a cena
        //Pode usar qualquer componente vinculado a classe
        Stage stage = (Stage) btncadLogin.getScene().getWindow();
        
        //Carregar a cena que desejamos exibir
        FXMLLoader tela = new FXMLLoader(App.class.getResource("credenciais.fxml"));
        
        //Criar uma nova cena
        Scene cena = new Scene(tela.load());
        
        //Exibir a cena
        stage.setScene(cena);
        stage.setTitle("Entrar");
        stage.show();
    }
    
}
