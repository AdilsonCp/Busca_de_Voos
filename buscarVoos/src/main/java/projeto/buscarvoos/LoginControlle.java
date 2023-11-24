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
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Screen;


public class LoginControlle implements Initializable {

    @FXML
    private Button btncadLogin;
    @FXML
    private TextField textUsuario;
    @FXML
    private TextField textSenha;
    @FXML
    private Label textErro;
    
    private String nome;
    private String id;
    
    Login loginBD = new Login();//Banco de dados

    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        textUsuario.setText("maria@hotmail.com");
        textSenha.setText("Horto123");
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
        
        stage.setFullScreen(true);
        
        // Defina a posição da nova janela para o centro da tela principal
       /* Screen sc = Screen.getPrimary();
        double larguraTela = sc.getBounds().getWidth();
        double alturaTela = sc.getBounds().getHeight();
        stage.setX((larguraTela - 1500) / 2); // Defina a posição X
        stage.setY((alturaTela - 1860) / 2);  // Defina a posição Y*/
        
        
        //Exibir a cena
        stage.setScene(cena);
        stage.setTitle("Busca");
        stage.show();
    }
    
    @FXML
    public void logar() throws IOException{
        
        String email = textUsuario.getText();
        String senha = textSenha.getText();
        
        boolean acesso = loginBD.login(email, senha);
        
        
        if(acesso){
            textErro.setVisible(false);
            
            //Trocar a cena
            //Pode usar qualquer componente vinculado a classe
            Stage stage = (Stage) btncadLogin.getScene().getWindow();

            //Carregar a cena que desejamos exibir
            FXMLLoader tela = new FXMLLoader(App.class.getResource("perfil.fxml"));

            //Criar uma nova cena
            Scene cena = new Scene(tela.load());
            
            setId();
            setNome();
            System.out.println(this.id+" "+this.nome);
            

            PerfilController perfil = tela.getController();
            perfil.setLbNome(this.nome);//passar o nome do usuario 
            perfil.initializeData(this.nome, this.id);
            
            // Defina a posição da nova janela para o centro da tela principal
            Screen sc = Screen.getPrimary();
            double larguraTela = sc.getBounds().getWidth();
            double alturaTela = sc.getBounds().getHeight();
            stage.setX((larguraTela - 1038) / 2); // Defina a posição X
            stage.setY((alturaTela - 731) / 2);  // Defina a posição Y

            //Exibir a cena
            stage.setScene(cena);
            stage.setTitle("Entrar");
            stage.show();

        
        }
        else{
            textErro.setText("Acesso negado");
            textErro.setTextFill(Color.RED);
            textErro.setVisible(true);
            
        }
        
        
        
        /*Janela de teste*/
        if(email.equals("teste")){
            //Trocar a cena
            //Pode usar qualquer componente vinculado a classe
            Stage stage = (Stage) btncadLogin.getScene().getWindow();

            //Carregar a cena que desejamos exibir
            FXMLLoader tela = new FXMLLoader(App.class.getResource("home.fxml"));

            //Criar uma nova cena
            Scene cena = new Scene(tela.load());

            //Exibir a cena
            stage.setScene(cena);
            stage.setTitle("teste");
            stage.show();
        }
        
    }
    
    public void setNome(){this.nome = loginBD.getNome();}
    public void setId(){this.id = loginBD.getId();}
 
}
