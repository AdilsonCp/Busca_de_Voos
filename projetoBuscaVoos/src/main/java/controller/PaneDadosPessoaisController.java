/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.PanesCadastroController;
import pessoasDatas.Datas;
import pessoasDatas.Pessoa;
import conexaoBanco.AcessoBDCadastroBD;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class PaneDadosPessoaisController extends PanesCadastroController implements Initializable {
    
    Datas datas = new Datas();
    Pessoa pessoa = new Pessoa();
    
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarGenero();
        carregarDatas();
        
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
    
    public String lbNome(){
        return txNome.getText();
    } 
    public String lbCPF(){
        return txCPF.getText();
    }
    public String lbGenero(){
        return cbGenero.getValue();
    }
    public String lbData(){
        return cbAno.getValue()+"-"+datas.numMes(cbMes.getValue())+"-"+cbDia.getValue();
    }
            
            
    @Override
    public int validarCampos() {
        AcessoBDCadastroBD acesso = new AcessoBDCadastroBD();
        
        pessoa.setNome(txNome.getText());
        pessoa.setCpf(txCPF.getText());
        
        //Verifica se os comboBox estão com valores
        if(cbDia.getValue() != null && cbMes.getValue() != null && 
                cbAno.getValue() != null && cbGenero.getValue() != null){
            
            pessoa.setDiaNasc(cbDia.getValue());
            pessoa.setMesNasc(cbMes.getValue());
            pessoa.setAnoNasc(cbAno.getValue());
            pessoa.setGenero(cbGenero.getValue());
            
            if(pessoa.validaDadosPessoal() && !acesso.selectCpf(txCPF.getText())) return 1;
            else return 0;     
        }
        else return 0;
    }
    

    @Override
    public void avisos() {
        
        AcessoBDCadastroBD acesso = new AcessoBDCadastroBD();
        //Verifica o nome
        if(!pessoa.getNome()){
            configAvisos(avisoNome,  "Nome Inválido", true);
        }
        else{
            configAvisos(avisoNome, null, false);
        }

        //Verifica se o cpf é válido e se ele já é cadastrado
        if(!pessoa.getCpf() || acesso.selectCpf(txCPF.getText())){
            if(acesso.selectCpf(txCPF.getText()))configAvisos(avisoCpf, "CPF já cadastrado", true);
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
    }

    @Override
    public void configAvisos(Label labelAviso, String aviso, boolean visible) {
        try{
            labelAviso.setText(aviso);
            labelAviso.setTextFill(Color.BLACK);
            labelAviso.setVisible(visible); 
       }catch(NullPointerException ex){
            labelAviso.setVisible(false);
       }    
    }
    
    

    
}
