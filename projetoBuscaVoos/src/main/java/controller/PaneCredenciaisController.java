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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.PanesCadastroController;
import pessoasDatas.Pessoa;
import projeto.projetobuscavoos.App;
import conexaoBanco.AcessoBDCadastroBD;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class PaneCredenciaisController extends PanesCadastroController implements Initializable {
    
    
    @FXML
    private TextField txEmail;
    @FXML
    private PasswordField txSenha;
    @FXML
    private Label avisoEmail;
    @FXML
    private Label avisoSenha;
    
    Pessoa pessoa = new Pessoa();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO      
    }   

    public String lbEmail(){
        return txEmail.getText();
    }
    public String lbSenha(){
        return txSenha.getText();
    }
    
    @Override
    public int validarCampos() {
        AcessoBDCadastroBD acesso = new AcessoBDCadastroBD();
        pessoa.setEmail(txEmail.getText());
        pessoa.setSenha(txSenha.getText());
        
        if(pessoa.validaCredenciais() && !acesso.selectEmai(txEmail.getText()))return 1;
        else return 0;
    }

    @Override
    public void avisos() {
        AcessoBDCadastroBD acesso = new AcessoBDCadastroBD();
        //Verifica se o Emial já cadastrado e se ele é válido
        if(!pessoa.getEmail() || acesso.selectEmai(txEmail.getText())){
            if(acesso.selectEmai(txEmail.getText()))configAvisos(avisoEmail, "Email já é cadastrado", true);
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
            labelAviso.setTextFill(Color.BLACK);
            labelAviso.setVisible(visible); 
       }catch(NullPointerException ex){
            labelAviso.setVisible(false);
       }  
    }
    

    
}
