package projeto.buscarvoos;

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
import model.PanesCadastrarController;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class CredenciasContatoController extends PanesCadastrarController implements Initializable  {
   @FXML
    private ComboBox<String> cbUF;
    @FXML
    private TextField textTelefone;
    @FXML
    private TextField textCEP;
    @FXML
    private TextField textEndereco;
    @FXML
    private TextField textNumero;
    @FXML
    private TextField textComplemento;
    @FXML
    private TextField textBairro;
    @FXML
    private TextField textCidade;
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

    
    Pessoa pessoa = new Pessoa();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarUF();
    }
    
    public String lbTelefone(){return textTelefone.getText();}
    public String lbCep(){return textCEP.getText();}
    public String lbEndereco(){return textEndereco.getText();}
    public String lbNumero(){return textNumero.getText();}
    public String lbComplemento(){return textComplemento.getText();}
    public String lbBairro(){return textBairro.getText();}
    public String lbCidade(){return textCidade.getText();}
    public String lbUf(){return cbUF.getValue().toString();}
    
    public void setTelefone(String telefone){textTelefone.setText(telefone);}
    public void setCep(String cep){textCEP.setText(cep);}
    public void setEndereco(String endereco){textEndereco.setText(endereco);}
    public void setNumero(String numero){textNumero.setText(numero);}
    public void setComplemento(String complemento){
        if(complemento == null)textComplemento.setText("");
        else textComplemento.setText(complemento);
    }
    public void setBairro(String bairro){textBairro.setText(bairro);}
    public void setCidade(String cidade){textCidade.setText(cidade);}
    public void setUf(String uf){cbUF.setValue(uf);}
 
 
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
        pessoa.setTelefone(textTelefone.getText());
        pessoa.setCep(textCEP.getText());
        pessoa.setEndereco(textEndereco.getText());
        pessoa.setNumero(textNumero.getText());
        pessoa.setComplemento(textComplemento.getText());
        pessoa.setBairro(textBairro.getText());
        pessoa.setCidade(textCidade.getText());
        if(cbUF.getValue() != null){
            pessoa.setUf(cbUF.getValue());
        
            if(pessoa.getTelefone() && pessoa.getCep() && pessoa.getEndereco() &&
                    pessoa.getNumero() && pessoa.getComplemento() && 
                    pessoa.getBairro() && pessoa.getCidade()){
                return 1;
            }
            else return 0;
        }
        else return 0;
        
    }
    @Override
    public void avisos(){
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
