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

/**
 * FXML Controller class
 *
 * @author adils
 */
public class CartaoController extends PanesCadastroController implements Initializable {
    

    @FXML
    private TextField txNumero;

    @FXML
    private ComboBox<String> cbMes;

    @FXML
    private ComboBox<String> cbAno;

    @FXML
    private TextField txNome;

    @FXML
    private TextField txCod;

    @FXML
    private TextField txCPF;
    @FXML
    private Label lbAvisoNumero;

    @FXML
    private Label lbAvisoData;

    @FXML
    private Label lbAvisoNome;

    @FXML
    private Label lbAvisoCpf;

    @FXML
    private Label lbAvisoCod;
    
    
    Pessoa pessoa = new Pessoa();
    Datas datas = new Datas();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarDatas();
    }    
    
    public void carregarDatas() {
        cbMes.setItems(datas.carregaMes());
        ObservableList<String> ano = FXCollections.observableArrayList(
                "2024","2025","2026","2027","2028","2029","2030","20231","2031"); 
        cbAno.setItems(ano);
    }
    

    
    @Override
    public int validarCampos() {
        
        pessoa.setCpf(txCPF.getText());
        pessoa.setNome(txNome.getText());
        
        if(pessoa.getNome() && pessoa.getCpf() && cbMes.getValue() != null && cbAno.getValue() != null){
            if(txCod.getText().length() == 3 && txNumero.getText().length() == 16){
                return 1;
            }
            else {
                return 0;
            }
        }
        else{
            return 0;
        }
    }
    

    @Override
    public void avisos() {
        if(!pessoa.getNome())configAvisos(lbAvisoNome,  "Nome Inválido", true);
        else configAvisos(lbAvisoNome, null, false);
        
        if(!pessoa.getCpf())configAvisos(lbAvisoCpf,  "CPF inválido", true);
        else configAvisos(lbAvisoCpf, null, false);
        
        if(cbMes.getValue() == null || cbAno.getValue() == null)configAvisos(lbAvisoData,  "Campo vázio", true);
        else configAvisos(lbAvisoData, null, false);
        
        if(txCod.getText().length() != 3)configAvisos(lbAvisoCod,  "Código Inválido", true);
        else configAvisos(lbAvisoCod, null, false);
        
        if(txNumero.getText().length() != 16)configAvisos(lbAvisoNumero,  "Número Inválido", true);
        else configAvisos(lbAvisoNumero, null, false);

        
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
