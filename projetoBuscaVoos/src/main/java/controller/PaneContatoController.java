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
import pessoasDatas.Pessoa;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class PaneContatoController extends PanesCadastroController implements Initializable {

    Pessoa pessoa = new Pessoa();
    
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    
    @Override
    public int validarCampos() {
        pessoa.setTelefone(txTelefone.getText());
        pessoa.setCep(txCEP.getText());
        pessoa.setEndereco(txEndereco.getText());
        pessoa.setNumero(txNumero.getText());
        pessoa.setComplemento(txComplemento.getText());
        pessoa.setBairro(txBairro.getText());
        pessoa.setCidade(txCidade.getText());
        if(cbUF.getValue() != null){
            pessoa.setUf(cbUF.getValue());
            
            if(pessoa.validaContato()) return 1;
            else return 0;
        }
        else return 0;
    }
    
    public String lbTelefone(){
        return txTelefone.getText();
    }
    public String lbCEP(){
        return txCEP.getText();
    }
    public String lbEndereco(){
        return txEndereco.getText();
    }
    public String lbNumero(){
        return txNumero.getText();
    }
    public String lbComplemento(){
        return txComplemento.getText();
    }
    public String lbBairro(){
        return txBairro.getText();
    }
    public String lbCidade(){
        return txCidade.getText();
    }
    public String lbUf(){
        return cbUF.getValue();
    }


    @Override
    public void avisos() {
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
