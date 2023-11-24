package projeto.buscarvoos;

import java.io.IOException;
import java.net.URL;
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
import conexaoBanco.CredenciaisBD;
import javafx.scene.control.Alert;


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
    private boolean flag;
    
    private String id;

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
    
    CredenciaisBD credenciais = new CredenciaisBD();


    public void initializeData(String id, boolean flag) {
        this.flag = flag;//true se for para visualizar seus dados
        this.id =id;
        credenciais.carregarPessoa(this.id);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.flag = false;//false se for para cadastro
        credenciais.setCredenciaisController(this);
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
                configPanes(cpController, paneContato, btnInfoContato,
                        btnPessoal, true, false, false, false);
            }else{
                cpController.avisos();
            }

        } else if (paneAtivo == paneContato) {
            if(ccController.validarCampos() == 1){
                configPanes(ccController,paneUsuarioSenha, btnUsuarioSenha,
                        btnInfoContato, true, false, false, true);
            }else{
                ccController.avisos();
            }
        }
    }

    @FXML
    public void voltar() {

        if (paneAtivo == paneUsuarioSenha) {
            if(usController.validarCampos() ==1){
                configPanes(usController,paneContato, btnInfoContato, 
                        btnUsuarioSenha, true, false, false, false);
            }else{
                usController.avisos();
            }
        } else if (paneAtivo == paneContato) {
            if(ccController.validarCampos() == 1){
                configPanes(ccController, panePessoal, btnInfoContato,
                        btnPessoal, false, true, true, false);
            }else{
                ccController.avisos();
            }
        }
    }

    @FXML
    public void cancelar() throws IOException {
        
        if(this.id == null){
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
        else{
            ((Stage) btnPessoal.getScene().getWindow()).close();
        }
    }
 
                
    
    @FXML
    public void salvar() throws IOException{
        
        //try{
            if(cpController.validarCampos() == 1 && ccController.validarCampos() == 1 && usController.validarCampos() == 1){   
                cpController.avisos();
                ccController.avisos();
                usController.avisos();

                credenciais.insertPessoa(cpController.lbNome(), cpController.lbCpf(), cpController.lbGenero(), cpController.lbData());
                credenciais.insertEndereco(ccController.lbCep(), ccController.lbEndereco(), ccController.lbNumero(), ccController.lbComplemento(), ccController.lbBairro(), ccController.lbCidade(), ccController.lbUf());
                credenciais.insertCredenciais(usController.lbEmail(), usController.lbSenha());
                credenciais.insertContato(usController.lbEmail(), ccController.lbTelefone());

                System.out.println("Cadastro finalizado");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Cadastro finalizado");
                alert.show();

                if(this.flag == false)carregarPerfil();
                else ((Stage) btnPessoal.getScene().getWindow()).close();


            }
            else {
                if(paneAtivo == panePessoal)cpController.avisos();
                else if(paneAtivo == paneContato)ccController.avisos();
                else if(paneAtivo == paneUsuarioSenha)usController.avisos();
            }
        /*}catch(NullPointerException ex){
            System.out.println("Preencha todos os campo em vermelho");
        }*/

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
    
    public void carregarPerfil() throws IOException{
        //Trocar a cena
        //Pode usar qualquer componente vinculado a classe
        Stage stage = (Stage) btnCancelar.getScene().getWindow();

        //Carregar a cena que desejamos exibir
        FXMLLoader tela = new FXMLLoader(App.class.getResource("perfil.fxml"));

        //Criar uma nova cena
        Scene cena = new Scene(tela.load());

        //Exibir a cena
        stage.setScene(cena);
        stage.setTitle("Entrar");
        stage.show();
    }
    

    public void setId(String id){
        this.id = id;
    }
    public Button buttonSalvar(){
        return btnSalvar;
    }
    
    public void carregarRegistro(String nome, String cpf, String genero, String dia, String mes, String ano,
                                    String telefon, String cep, String  endereco, String numero, String complemento,
                                    String bairro, String cidade, String uf,
                                    String email, String senha){
        
        cpController.setNome(nome);
        cpController.setCpf(cpf);
        cpController.setGenero(genero);
        cpController.setDataNasc(dia, mes, ano);
        
        ccController.setTelefone(telefon);
        ccController.setCep(cep);
        ccController.setEndereco(endereco);
        ccController.setNumero(numero);
        ccController.setComplemento(complemento);
        ccController.setBairro(bairro);
        ccController.setCidade(cidade);
        ccController.setUf(uf);
        
        usController.setEmail(email);
        usController.setSenha(senha);
    }
    

}
