package projeto.buscarvoos;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.PanesCadastrarController;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class CredenciaisController implements Initializable {

    private Pane panePessoal;
    private Pane paneContato;
    private Pane paneUsuarioSenha;
    private Pane paneAtivo;
    Class<?> classeDoObjeto;

    @FXML
    private BorderPane paneView;
    @FXML
    private Button btnPessoal;
    @FXML
    private Button btnInfoContato;
    @FXML
    private Button btnUsuarioSenha;
    @FXML
    private Button btnProximo;
    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvar;

    private CredenciaisPessoalController cpController;
    private CredenciasContatoController ccController;
    private CredenciaisUsuarioSenhaController usController;
    
    Pessoa pessoa = new Pessoa();
    
    //flag pra dizer se os dados são válidos ou não
    int isValido = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPanes();
    }

    private void loadPanes() {

        try {
            FXMLLoader loader1 = new FXMLLoader(App.class.getResource("credenciaisPessoal.fxml"));
            panePessoal = loader1.load();
            cpController = loader1.getController();
            
            FXMLLoader loader2 = new FXMLLoader(App.class.getResource("credenciasContato.fxml"));
            paneContato = loader2.load();
            ccController = loader2.getController();
            
            FXMLLoader loader3 = new FXMLLoader(App.class.getResource("credenciaisUsuarioSenha.fxml"));
            paneUsuarioSenha = loader3.load();
            usController = loader3.getController();
            
            paneView.setCenter(panePessoal);
            paneAtivo = panePessoal;
            btnPessoal.setDisable(false);
            btnVoltar.setDisable(true);
            btnProximo.setDisable(false);


        } catch (IOException ex) {
            Logger.getLogger(CredenciaisController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void avancar() {      
        if (paneAtivo == panePessoal) {
            if(cpController.validarCampos() == 1){
                //cpController.avisos();
                configPanes(cpController, paneContato, btnInfoContato, btnPessoal, true, false, false, false);
                /*paneView.setCenter(paneContato);
                paneAtivo = paneContato;
                btnPessoal.setDisable(true);
                btnInfoContato.setDisable(false);
                btnVoltar.setDisable(false);
                btnProximo.setDisable(false);*/
            }else{
                cpController.avisos();
            }

        } else if (paneAtivo == paneContato) {
            if(ccController.validarCampos() == 1){
               // ccController.avisos();
                configPanes(ccController,paneUsuarioSenha, btnUsuarioSenha, btnInfoContato, true, false, false, true);
                /*paneView.setCenter(paneUsuarioSenha);
                paneAtivo = paneUsuarioSenha;
                btnInfoContato.setDisable(true);
                btnUsuarioSenha.setDisable(false);
                btnProximo.setDisable(true);
                btnVoltar.setDisable(false);*/
            }else{
                ccController.avisos();
            }
        }
    }

    @FXML
    public void voltar() {

        if (paneAtivo == paneUsuarioSenha) {
            if(usController.validarCampos() ==1){
               // usController.avisos();
                configPanes(usController,paneContato, btnInfoContato, btnUsuarioSenha, true, false, false, false);
                /*paneView.setCenter(paneContato);
                paneAtivo = paneContato;
                btnUsuarioSenha.setDisable(true);
                btnInfoContato.setDisable(false);
                btnVoltar.setDisable(false);
                btnProximo.setDisable(false);*/
            }else{
                usController.avisos();
            }
        } else if (paneAtivo == paneContato) {
            if(ccController.validarCampos() == 1){
                //ccController.avisos();
                configPanes(ccController, panePessoal, btnInfoContato, btnPessoal, false, true, true, false);
                /*paneView.setCenter(panePessoal);
                paneAtivo = panePessoal;
                btnPessoal.setDisable(false);
                btnInfoContato.setDisable(true);
                btnVoltar.setDisable(true);
                btnProximo.setDisable(false);*/
            }else{
                ccController.avisos();
            }
        }
    }

    @FXML
    public void cancelar() throws IOException {
        //Trocar a cena
        //Pode usar qualquer componente vinculado a classe
        Stage stage = (Stage) btnCancelar.getScene().getWindow();

        //Carregar a cena que desejamos exibir
        FXMLLoader tela = new FXMLLoader(App.class.getResource("login.fxml"));

        //Criar uma nova cena
        Scene cena = new Scene(tela.load());

        //Exibir a cena
        stage.setScene(cena);
        stage.setTitle("Entrar");
        stage.show();
    }
    
    public void configPanes(Object controller, Pane paneNode, Button btnNew, Button btnOld, boolean btnAtivo, boolean btnNot,
            boolean btnVol, boolean btnProx) {
        
        if (controller instanceof CredenciaisPessoalController){
            cpController.avisos();

        }
        else if (controller instanceof CredenciasContatoController){
            ccController.avisos();
        }
        else if (controller instanceof CredenciaisUsuarioSenhaController){
            usController.avisos();
        }
        
        paneView.setCenter(paneNode);
        paneAtivo = paneNode;
        btnOld.setDisable(btnAtivo);
        btnNew.setDisable(btnNot);
        btnVoltar.setDisable(btnVol);
        btnProximo.setDisable(btnProx);
        
    }
    
    
    

    
    

    
  
 


    
  

}
