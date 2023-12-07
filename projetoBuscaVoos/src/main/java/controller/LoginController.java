/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import conexaoBanco.AcessoBDCadastroBD;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import projeto.projetobuscavoos.App;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class LoginController implements Initializable {
    
    @FXML
    private TextField txUsuario;
    @FXML
    private PasswordField psSenha;
    @FXML
    private Label lbErro;
    
    
    //Conexao com banco para Login
    AcessoBDCadastroBD login = new AcessoBDCadastroBD();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO     
    }
    
    
    @FXML
    public void logar() throws IOException{
        String usuario = txUsuario.getText();
        String senha = psSenha.getText();
        
        boolean acesso = login.login(usuario, senha);
        
        if(acesso){
            
            lbErro.setVisible(false);
            Stage stage = (Stage) txUsuario.getScene().getWindow();
        
            //Carregar a cena que desejamos exibir
            FXMLLoader tela = new FXMLLoader(App.class.getResource("/telas/home.fxml"));
            
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getBounds();
            stage.setX(0);
            stage.setY(0);
            
            //Criar uma nova cena
            Scene cena = new Scene(tela.load());
            
            HomeController perfil = tela.getController();
            perfil.dados(login.getNome(), login.getIdPessoa());
            
            

            //Exibir a cena
            stage.setScene(cena);
            stage.setTitle("Home");
            stage.show(); 
                    }
        else{
            lbErro.setText("Acesso negado");
            lbErro.setTextFill(Color.RED);
            lbErro.setVisible(true);
        }
    }
    
    @FXML
    public void cadastro() throws IOException{
        
        Stage stage = (Stage) txUsuario.getScene().getWindow();
        
        //Carregar a cena que desejamos exibir
        FXMLLoader tela = new FXMLLoader(App.class.getResource("/telas/cadastro.fxml"));
        
        //Criar uma nova cena
        Scene cena = new Scene(tela.load());
        
        
        //Exibir a cena
        stage.setScene(cena);
        stage.setTitle("Cadastro");
        stage.show();      
    }
    
    @FXML
    public void buscarVoo() throws IOException{
        
        //Stage stage = (Stage) txUsuario.getScene().getWindow();  
        //Carregar a cena que desejamos exibir
        FXMLLoader tela = new FXMLLoader(App.class.getResource("/telas/pesquisaVoo.fxml"));
        
        //Criar uma nova cena
        Scene cena = new Scene(tela.load());
        
        Stage stage = new Stage();
        
        //Exibir a cena
        stage.setScene(cena);
        stage.setTitle("Procurar Voo");
        stage.show();     
    }
    

    
       
  
    
}
