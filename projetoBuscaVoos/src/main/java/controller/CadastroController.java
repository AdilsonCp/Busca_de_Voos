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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import pessoasDatas.Pessoa;
import projeto.projetobuscavoos.App;
import conexaoBanco.AcessoBDCadastroBD;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class CadastroController implements Initializable {
    private Pane panePessoal;
    private Pane paneContato;
    private Pane paneUsuarioSenha;
    private Pane paneAtivo;
    
    private PaneDadosPessoaisController ppController;
    private PaneContatoController pcController;
    private PaneCredenciaisController pcrdController;
    
    Pessoa pessoa = new Pessoa();
    
    public boolean flagTela;
    
    
    @FXML
    private BorderPane paneView;
    @FXML
    private Button btnAvancar;
    @FXML
    private Button btnVoltar;
    @FXML
    private Label avisoSalvar;
    @FXML
    private Button btnSalvar; 
    
    public String idPesso;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadPanes();
        avisoSalvar.setVisible(false);
        
        

    } 
    
    public void loadPanes(){
        try {
            FXMLLoader loader1 = new FXMLLoader(App.class.getResource("/telas/paneDadosPessoais.fxml"));
            panePessoal = loader1.load();
            ppController = loader1.getController();
            
            FXMLLoader loader2 = new FXMLLoader(App.class.getResource("/telas/paneContato.fxml"));
            paneContato = loader2.load();
            pcController = loader2.getController();
            
            FXMLLoader loader3 = new FXMLLoader(App.class.getResource("/telas/paneCredenciais.fxml"));
            paneUsuarioSenha = loader3.load();
            pcrdController = loader3.getController();
            
            paneView.setTop(panePessoal);
            paneAtivo = panePessoal;
            btnVoltar.setDisable(true);
            btnAvancar.setDisable(false);
            
            /*btnPessoal.setDisable(false);
            btnVoltar.setDisable(true);
            btnProximo.setDisable(false);*/
        } catch (IOException ex) {
            Logger.getLogger(PaneCredenciaisController.class.getName()).log(Level.SEVERE, null, ex);
        }            
    }
    
    @FXML
    public void avancar(){
        avisoSalvar.setVisible(false);
        if(paneAtivo == panePessoal){
            if(ppController.validarCampos() == 1){
                ppController.avisos();
                configPane(paneContato, false, false);
            }
            else ppController.avisos();
            
        }
        else if(paneAtivo == paneContato){
            if(pcController.validarCampos() == 1){
                pcController.avisos();
                configPane(paneUsuarioSenha, false, true);
            }
            else pcController.avisos();
            
        }
    }
    
    @FXML
    public void voltar(){
        avisoSalvar.setVisible(false);
        if(paneAtivo == paneUsuarioSenha){
            if(pcrdController.validarCampos() == 1){
                pcrdController.avisos();
                configPane(paneContato, false, false);
            }
            else pcrdController.avisos();
        }
        else if(paneAtivo == paneContato){
            if(pcController.validarCampos() == 1){
                pcController.avisos();
                configPane(panePessoal, true, false);
            }
            else pcController.avisos();       
        }
    }
    
    public void configPane(Pane panNovo, boolean btnVoltar, boolean btnAvancar){ 
        paneView.setTop(panNovo);
        paneAtivo = panNovo;
        this.btnAvancar.setDisable(btnAvancar);
        this.btnVoltar.setDisable(btnVoltar);
    }
    
    @FXML
    public void salvar() throws IOException{
        
        try{
            if(pcController.validarCampos() == 1 && pcController.validarCampos() == 1 && pcrdController.validarCampos() == 1){
                ppController.avisos();
                pcController.avisos();
                pcrdController.avisos();
                System.out.println("Cadastro realizado");
                carregarBanco();

            }
            else{
                if(paneAtivo == panePessoal)ppController.avisos();
                else if(paneAtivo == paneContato)pcController.avisos();
                else if(paneAtivo == paneUsuarioSenha)pcrdController.avisos();
            }
        }catch(NullPointerException ex){
            avisoSalvar.setText("Preencha todos os campos ou aperte em 'Proximo' ou 'Anterior'");
            avisoSalvar.setVisible(true);
            avisoSalvar.setTextFill(Color.RED);
        }
        
    }
    
    public void carregarBanco() throws IOException{

        AcessoBDCadastroBD acesso = new AcessoBDCadastroBD();
        
        String nome = ppController.lbNome();
        String cpf = ppController.lbCPF();
        String genero = ppController.lbGenero();
        String data = ppController.lbData();
        
        String telefone = pcController.lbTelefone();
        String cep = pcController.lbCEP();
        String endereco = pcController.lbEndereco();
        String numero = pcController.lbNumero();
        String complemento = pcController.lbComplemento();
        String bairro = pcController.lbBairro();
        String cidade = pcController.lbCidade();
        String uf = pcController.lbUf();
        
        String email = pcrdController.lbEmail();
        String senha = pcrdController.lbSenha();
        
        acesso.insertPessoa(nome, cpf, genero, data, telefone, cep, endereco, numero, complemento, bairro, cidade, uf, email, senha);
        
        
        Stage stage = (Stage) btnAvancar.getScene().getWindow();  
        //Carregar a cena que desejamos exibir
        FXMLLoader tela = new FXMLLoader(App.class.getResource("/telas/login.fxml"));
        
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getBounds();
        stage.setX((bounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((bounds.getHeight() - stage.getHeight()) / 2);
        
        //Criar uma nova cena
        Scene cena = new Scene(tela.load());
        
        //Exibir a cena
        stage.setScene(cena);
        stage.setTitle("Home");
        stage.show();     

    }
    
    @FXML
    public void cancelar() throws IOException{
            Stage stage = (Stage) btnAvancar.getScene().getWindow();
        
            //Carregar a cena que desejamos exibir
            FXMLLoader tela = new FXMLLoader(App.class.getResource("/telas/login.fxml"));
                      
            //Criar uma nova cena
            Scene cena = new Scene(tela.load());
 
            //Exibir a cena
            stage.setScene(cena);
            stage.setTitle("Login");
            stage.show(); 
    }
    



    
    
    
    
    
}
