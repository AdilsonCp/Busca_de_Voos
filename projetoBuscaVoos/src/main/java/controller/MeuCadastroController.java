/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import conexaoBanco.AcessoBDCadastroBD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.PanesCadastroController;
import pessoasDatas.Datas;
import pessoasDatas.Pessoa;
import projeto.projetobuscavoos.App;
import conexaoBanco.AcessoBDCadastroBD;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class MeuCadastroController extends PanesCadastroController implements Initializable {
    
    
    Datas datas = new Datas();
    Pessoa pessoa = new Pessoa();
    AcessoBDCadastroBD acesso = new AcessoBDCadastroBD();
    
    @FXML
    private TextField txNome;
    @FXML
    private TextField txCPF;
    @FXML
    private ComboBox<String> cbGenero;
    @FXML
    private ComboBox<String> cbDia;
    @FXML
    private ComboBox<String> cbMes;
    @FXML
    private ComboBox<String> cbAno;
    @FXML
    private Label avisoNome;
    @FXML
    private Label avisoCpf;
    @FXML
    private Label avisoGenero;
    @FXML
    private Label avisoData;
    @FXML
    private ComboBox<String> cbUF;
    @FXML
    private TextField txTelefone;
    @FXML
    private TextField txCEP;
    @FXML
    private TextField txEndereco;
    @FXML
    private TextField txNumero;
    @FXML
    private TextField txComplemento;
    @FXML
    private TextField txBairro;
    @FXML
    private TextField txCidade;
    @FXML
    private Label avisoTelefone;
    @FXML
    private Label avisoCep;
    @FXML
    private Label avisoEndereco;
    @FXML
    private Label avisoNumero;
    @FXML
    private Label avisoComplemento;
    @FXML
    private Label avisoBairro;
    @FXML
    private Label avisoCidade;
    @FXML
    private Label avisoUf;
    @FXML
    private TextField txEmail;
    @FXML
    private PasswordField txSenha;
    @FXML
    private Label avisoEmail;
    @FXML
    private Label avisoSenha;
    
    
    private String idPessoa;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        acesso.setMeucadastro(this);
        carregarGenero();
        carregarDatas();
        carregarUF();
        
        
    } 
    public void carregarUF(){
        ObservableList<String> uf = FXCollections.observableArrayList(
            "Acre","Alagoas","Amapá","Amazonas","Bahia",
            "Ceará", "Distrito Federal","Espírito Santo", "Goiás",
            "Maranhão","Mato Grosso","Mato Grosso do Sul",
            "Minas Gerais","Pará","Paraíba","Paraná",
            "Pernambuco","Piauí","Rio de Janeiro",
            "Rio Grande do Norte","Rio Grande do Sul","Rondônia",
            "Roraima ","Santa Catarina","São Paulo","Sergipe",
            "Tocantins"
        );
        // Associe a lista de gêneros ao ComboBox
        cbUF.setItems(uf);
    }
    
    public void carregarGenero() {
        ObservableList<String> generos = FXCollections.observableArrayList(
                "Masculino",
                "Feminino",
                "Outro"
        );
        // Associe a lista de gêneros ao ComboBox
        cbGenero.setItems(generos);
    }  
    public void carregarDatas() {
        cbDia.setItems(datas.carregaDias());
        cbMes.setItems(datas.carregaMes());
        cbAno.setItems(datas.carregaAno());
    }

    @Override
    public int validarCampos() {
        AcessoBDCadastroBD acesso = new AcessoBDCadastroBD();
        
        pessoa.setNome(txNome.getText());
        pessoa.setCpf(txCPF.getText());
        pessoa.setTelefone(txTelefone.getText());
        pessoa.setCep(txCEP.getText());
        pessoa.setEndereco(txEndereco.getText());
        pessoa.setNumero(txNumero.getText());
        pessoa.setComplemento(txComplemento.getText());
        pessoa.setBairro(txBairro.getText());
        pessoa.setCidade(txCidade.getText());
        pessoa.setEmail(txEmail.getText());
        pessoa.setSenha(txSenha.getText());
        
        
        //Verifica se os comboBox estão com valores
        if(cbDia.getValue() != null && cbMes.getValue() != null && 
                cbAno.getValue() != null && cbGenero.getValue() != null && cbUF.getValue() != null){
            
            pessoa.setUf(cbUF.getValue());
            pessoa.setDiaNasc(cbDia.getValue());
            pessoa.setMesNasc(cbMes.getValue());
            pessoa.setAnoNasc(cbAno.getValue());
            pessoa.setGenero(cbGenero.getValue());
            
            if(pessoa.validaDadosPessoal() && !acesso.selectCpfEmail(this.idPessoa,txEmail.getText()) && !acesso.selectCpfEmail(Integer.parseInt( this.idPessoa), txCPF.getText())){
                if(pessoa.validaContato() && pessoa.validarData() && pessoa.validaCredenciais())return 1;
                else return 0;
            }
            else return 0;     
        }
        else return 0;
    }

    @Override
    public void avisos() {
        
        AcessoBDCadastroBD acesso = new AcessoBDCadastroBD();
        //Verifica o nome
        if(!pessoa.getNome()) configAvisos(avisoNome,  "Nome Inválido", true);
        else configAvisos(avisoNome, null, false);
        

        //Verifica se o cpf é válido e se ele já é cadastrado
        if(!pessoa.getCpf() || acesso.selectCpfEmail(Integer.parseInt( this.idPessoa), txCPF.getText())){
            if(acesso.selectCpfEmail(Integer.parseInt( this.idPessoa), txCPF.getText()))configAvisos(avisoCpf, "CPF já cadastrado", true);
            else configAvisos(avisoCpf, "CPF Inválido", true);
        }
        else configAvisos(avisoCpf, null, false);

        
        //Verifica se o campo data está vázio e se a data está inválida
        if(cbDia.getValue() == null || cbMes.getValue() == null || cbAno.getValue() == null){
            configAvisos(avisoData, "Campo Vázio", true);
        }
        else if(!datas.validaData(cbMes.getValue(),cbDia.getValue())){
            configAvisos(avisoData, "Data Inválida", true);
        }
        else {
            configAvisos(avisoData, null, false);
        }
          
        //Verifica se o campo genero está vázio
        if(cbGenero.getValue() == null)configAvisos(avisoGenero, 
                "Campo Vázio", true);
        else configAvisos(avisoGenero, null, false);
       if(!pessoa.getTelefone())configAvisos(avisoTelefone, 
                "Telefone Inválido", true);
        else configAvisos(avisoTelefone, null, false);
        
        if(!pessoa.getCep())configAvisos(avisoCep, 
                "CEP Inválido", true);
        else configAvisos(avisoCep, null, false);
        
        if(!pessoa.getEndereco())configAvisos(avisoEndereco, 
                "Endereco Inválido ", true);
        else configAvisos(avisoEndereco, null, false);
        
        if(!pessoa.getNumero())configAvisos(avisoNumero, 
                "Numero Incorreto", true);
        else configAvisos(avisoNumero, null, false);
        
        if(!pessoa.getComplemento())configAvisos(avisoComplemento, 
                "Complemento Incorreto", true); 
        else configAvisos(avisoComplemento, null, false);
        
        if(!pessoa.getBairro())configAvisos(avisoBairro, 
                "Bairro Incorreto", true);
        else configAvisos(avisoBairro, null, false);
        
        if(!pessoa.getCidade())configAvisos(avisoCidade, 
                "Cidade Incorreto", true);
        else configAvisos(avisoCidade, null, false);
        
        if(cbUF.getValue() == null)configAvisos(avisoUf, 
                "Campo vázio", true);
        else configAvisos(avisoUf, null, false);

        //Verifica se o Emial já cadastrado e se ele é válido
        if(!pessoa.getEmail() || acesso.selectCpfEmail(this.idPessoa,txEmail.getText())){
            if(acesso.selectCpfEmail(this.idPessoa,txEmail.getText()))configAvisos(avisoEmail, "Email já é cadastrado", true);
            else configAvisos(avisoEmail, "Email Invalido", true);
        }
        else configAvisos(avisoEmail, null, false);
        
        //Verifica se a senha é válida
        if(!pessoa.getSenha())configAvisos(avisoSenha, 
                "Senha Inválida", true);
        else configAvisos(avisoSenha, null, false);
    
    }

    @Override
    public void configAvisos(Label labelAviso, String aviso, boolean visible) {
       try{
            labelAviso.setText(aviso);
            labelAviso.setTextFill(Color.RED);
            labelAviso.setVisible(visible); 
       }catch(NullPointerException ex){
            labelAviso.setVisible(false);
       }
    }
    
    @FXML
    public void atualizar(){
        
        if(validarCampos() == 1){
            
            avisos();
            acesso.atualizarDados(idPessoa, txNome.getText(), txCPF.getText(), cbGenero.getValue(), cbDia.getValue(), Integer.toString(datas.numMes(cbMes.getValue())), cbAno.getValue(), 
                    txTelefone.getText(), txCEP.getText(), txEndereco.getText(), txNumero.getText(), txComplemento.getText(), txBairro.getText(),
                    txCidade.getText(), cbUF.getValue(), txEmail.getText(), txSenha.getText());
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Cadastro atualizado");
            alert.show();
        }
        else{
            avisos();
        }
    }   
    
   public void carregarRegistro(String nome, String cpf, String genero, String dia, String mes, String ano,
                                    String telefon, String cep, String  endereco, String numero, String complemento,
                                    String bairro, String cidade, String uf,
                                    String email, String senha){
        
        txNome.setText(nome);
        txCPF.setText(cpf);
        cbGenero.setValue(genero);
        cbAno.setValue(ano);
        cbDia.setValue(dia);
        cbMes.setValue(mes);
        
        txTelefone.setText(telefon);
        txCEP.setText(cep);
        txEndereco.setText(endereco);
        txComplemento.setText(complemento);
        txNumero.setText(numero);
        txBairro.setText(bairro);
        txCidade.setText(cidade);
        cbUF.setValue(uf);
        
        txEmail.setText(email);
        txSenha.setText(senha);
    }
   
   public void setIdPessoa(String id){
       this.idPessoa = id;
       acesso.carregarPessoa(idPessoa);
   }
    
    
    
    
}
