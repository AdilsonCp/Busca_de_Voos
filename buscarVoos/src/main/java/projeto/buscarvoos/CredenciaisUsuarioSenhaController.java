/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projeto.buscarvoos;

import conexaoBanco.CredenciaisBD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.PanesCadastrarController;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class CredenciaisUsuarioSenhaController extends PanesCadastrarController implements Initializable {

    @FXML
    private TextField textEmail;
    @FXML
    private PasswordField textSenha;
    @FXML
    private Label avisoEmail;
    @FXML
    private Label avisoSenha;
    
    CredenciaisBD credenciais = new CredenciaisBD();
    Pessoa pessoa = new Pessoa();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public String lbEmail(){
        return textEmail.getText();
    }
    public String lbSenha (){
        return textSenha.getText();
    }
    public void setEmail(String email){
        textEmail.setText(email);
    }
    public void setSenha(String senha){
        textSenha.setText(senha);
    }
            
    
    
    

    @Override
    public int validarCampos() {
        pessoa.setEmail(textEmail.getText());
        pessoa.setSenha(textSenha.getText());
        
        if(pessoa.getEmail() && pessoa.getSenha() && !credenciais.selectEmai(textEmail.getText()))return 1;
        else return 0;
    }
    
    @Override
    public void avisos(){
        
        //Verifica se o Emial já cadastrado e se ele é válido
        if(!pessoa.getEmail() || credenciais.selectEmai(textEmail.getText())){
            if(credenciais.selectEmai(textEmail.getText()))configAvisos(avisoEmail, "Email já é cadastrado", true);
            else configAvisos(avisoEmail, "Email Invalido", true);
        }
        else configAvisos(avisoEmail, null, false);
        
        //Verifica se a senha é válida
        if(!pessoa.getSenha())configAvisos(avisoSenha, 
                "Senha Inválida", true);
        else configAvisos(avisoSenha, null, false);
    }

    @Override
    public void configAvisos(Label labelAviso, String aviso, boolean flag) {
       try{
            labelAviso.setText(aviso);
            labelAviso.setTextFill(Color.BLACK);
            labelAviso.setVisible(flag); 
       }catch(NullPointerException ex){
            labelAviso.setVisible(false);
       }    
    }
    
}
