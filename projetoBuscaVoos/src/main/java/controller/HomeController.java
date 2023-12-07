/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import projeto.projetobuscavoos.App;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class HomeController implements Initializable {
    
    @FXML 
    private Button btnSair;
    @FXML
    private Button btnbuscarVoo;
    @FXML
    private Button btnPerfil;
    @FXML
    private Button btnHistorico;
    @FXML
    private BorderPane bordPaneView;
    @FXML
    private Label lbNome;

    private String idPessoa;
    
    private Pane panePesquisaVoos;
    private Pane paneCompra;
    private Pane paneMeuCadastro;
    private Pane paneHistoricoVoo;
    private Pane paneAtivo;
    
    
    private PesquisaVooController pesquisaController;
    private CompraController compraController;
    private MeuCadastroController meuCadastroController;
    private HistoricoController historicoController;
    
    /**
     * Initializes the controller class.
     */
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadPane();
    } 
    
    @FXML
    public void sair() throws IOException{
        Stage stage = (Stage) btnSair.getScene().getWindow();  
        //Carregar a cena que desejamos exibir
        FXMLLoader tela = new FXMLLoader(App.class.getResource("/telas/login.fxml"));
        
        
        //Criar uma nova cena
        Scene cena = new Scene(tela.load());
        
        //Exibir a cena
        stage.setScene(cena);
        stage.setTitle("Login");
        stage.show();     
    }     
    
    public void loadPane(){
        try {
           
            FXMLLoader loader2 = new FXMLLoader(App.class.getResource("/telas/pesquisaVoo.fxml"));
            panePesquisaVoos = loader2.load();
            pesquisaController = loader2.getController();
            
            FXMLLoader loader4 = new FXMLLoader(App.class.getResource("/telas/compra.fxml"));
            paneCompra  = loader4.load();
            compraController = loader4.getController();
            
            FXMLLoader loader5 = new FXMLLoader(App.class.getResource("/telas/meuCadastro.fxml"));
            paneMeuCadastro  = loader5.load();
            meuCadastroController = loader5.getController();
            
            FXMLLoader loader6 = new FXMLLoader(App.class.getResource("/telas/historico.fxml"));
            paneHistoricoVoo  = loader6.load();
            historicoController = loader6.getController();
            
            
            bordPaneView.setCenter(panePesquisaVoos);
            paneAtivo = panePesquisaVoos;

        } catch (IOException ex) {
            Logger.getLogger(CadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    @FXML
    public void pesquisarVoo(){
        loadPane();
        bordPaneView.setCenter(null);
        bordPaneView.setTop(null);
        bordPaneView.setCenter(panePesquisaVoos);
        paneAtivo = panePesquisaVoos;

    }
    @FXML
    public void meuPerfil(){
        loadPane();
        bordPaneView.setCenter(null);
        bordPaneView.setTop(null);
        bordPaneView.setCenter(paneMeuCadastro);
        paneAtivo = paneMeuCadastro;
        meuCadastroController.setIdPessoa(idPessoa);
        
    } 

    @FXML
    public void compra(){
        loadPane();
        bordPaneView.setCenter(null);
        bordPaneView.setTop(null);
        bordPaneView.setCenter(paneCompra);
        paneAtivo = paneCompra;
        compraController.setIdPessoa(idPessoa);        
   

    }
    
    @FXML
    public void historicoVoo(){
        loadPane();
        bordPaneView.setCenter(null);
        bordPaneView.setTop(null);
        bordPaneView.setCenter(paneHistoricoVoo);
        paneAtivo = paneHistoricoVoo;
        historicoController.setPessoaId(idPessoa);
        
    }

    public void dados(String nome, String idPessoa) {
        lbNome.setText(nome);  
        this.idPessoa = idPessoa;
    }


    


    
    

        
    
    
}
