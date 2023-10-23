package projeto.buscarvoos;

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
import conexaoBanco.Login;

public class InicioController implements Initializable {

    @FXML
    private Button btncadLogin;
    @FXML
    private TextField textUsuario;
    @FXML
    private TextField textSenha;
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
    
    @FXML
    public void pesquisaVoo() throws IOException{
        
        //Trocar a cena
        //Pode usar qualquer componente vinculado a classe
        Stage stage = (Stage) btncadLogin.getScene().getWindow();
        
        //Carregar a cena que desejamos exibir
        FXMLLoader tela = new FXMLLoader(App.class.getResource("pesquisaVoo.fxml"));
        
        //Criar uma nova cena
        Scene cena = new Scene(tela.load());
        
        //Exibir a cena
        stage.setScene(cena);
        stage.setTitle("Busca");
        stage.show();
    }
    
    @FXML
    public void logar() throws IOException{
        String email = textUsuario.getText();
        String senha = textSenha.getText();
        
        Login login = new Login();
        boolean acesso = login.login(email, senha);
        
        if(acesso){
            System.out.println("Acessado");
        }
        else{
            System.out.println("Acesso negado");
        }
        
    }
    
}
