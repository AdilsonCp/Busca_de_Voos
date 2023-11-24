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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author adils
 */
public class PerfilController implements Initializable {
    private String nome;
    private String id;
    
    @FXML
    private Label labelNome;
    @FXML
    private Button btnbuscarVoo;
    @FXML
    private Button btnPerfil;
    @FXML
    private Button btnHistorico;
    @FXML
    private Button btnSair;
    @FXML
    private BorderPane bordPane;
    
    /**
     * Initializes the controller class.
     */
    public void initializeData(String nome, String id) {
        this.id =id;
        this.nome = nome;
        //System.out.println(this.id);

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //loadPane();  
    }
    
    @FXML
    public void meuPefil() throws IOException{
        FXMLLoader loader = new FXMLLoader(App.class.getResource("credenciais.fxml"));
        Scene scene = new Scene(loader.load());

        //CredenciaisController controller = loader.getController();
        //controller.setId(this.id);
        
        
        CredenciaisController crperfil = loader.getController();
        crperfil.initializeData( this.id, true);
        

        //controller.setInfoEmpregado(empregado);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Editar");
        stage.show();
        
    }
    
    @FXML
    public void sair() throws IOException{
            //Trocar a cena
            //Pode usar qualquer componente vinculado a classe
            Stage stage = (Stage) btnPerfil.getScene().getWindow();

            //Carregar a cena que desejamos exibir
            FXMLLoader tela = new FXMLLoader(App.class.getResource("login.fxml"));

            //Criar uma nova cena
            Scene cena = new Scene(tela.load());

            //Exibir a cena
            stage.setScene(cena);
            stage.setTitle("Entrar");
            stage.show();
    }
    

    public void setLbNome(String nome){
        labelNome.setText(nome);
        this.nome = nome;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getNome(){
        return this.nome;
    }
    public String getId(){
        return this.id;
    }
    
    
}
