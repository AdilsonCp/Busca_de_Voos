package projeto.buscarvoos;

import datas.Ano;
import datas.CarregarDatas;
import datas.Dia;
import datas.Mes;
import datas.ValidaFormataData;
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
public class CredenciaisPessoalController extends PanesCadastrarController implements Initializable {

    @FXML
    private ComboBox<Dia> cbDia;
    @FXML
    private ComboBox<Mes> cbMes;
    @FXML
    private ComboBox<Ano> cbAno;
    @FXML
    private TextField textNome;
    @FXML
    private TextField textCpf;
    @FXML
    private ComboBox<String> cbGenero;
    @FXML
    private Label avisoNome;
    @FXML
    private Label avisoCpf;
    @FXML
    private Label avisoGenero;
    @FXML
    private Label avisoData;
    
    CarregarDatas datas = new CarregarDatas();
    ValidaFormataData vFData = new ValidaFormataData();
    
    Pessoa pessoa = new Pessoa();
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarDatas();
        carregarGenero();
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
        pessoa.setNome(textNome.getText());
        pessoa.setCpf(textCpf.getText());
        
        if(cbDia.getValue() != null && cbMes.getValue() != null && 
                cbAno.getValue() != null && cbGenero.getValue() != null){
            pessoa.setDiaNasc(cbDia.getValue().toString());
            pessoa.setMesNasc(cbMes.getValue().toString());
            pessoa.setAnoNasc(cbAno.getValue().toString());
            
            vFData.validaData(pessoa.getMesNasc(), 
                    pessoa.getDiaNasc());
        
            if(pessoa.getNome() && pessoa.getCpf() && 
                    cbGenero.getValue() != null &&
                    vFData.validaData(pessoa.getMesNasc(), 
                            pessoa.getDiaNasc())) return 1;
            else return 0;
            
        }else return 0;
    }
    
    @Override
    public void avisos(){
        
       
        if(!pessoa.getNome())configAvisos(avisoNome, 
                "Nome Inválido", true);
        else configAvisos(avisoNome, null, false);

        if(!pessoa.getCpf())configAvisos(avisoCpf, 
                "CPF Inválido", true);
        else configAvisos(avisoCpf, null, false);

        if(cbDia.getValue() == null || cbMes.getValue() == null || 
                cbAno.getValue() == null)configAvisos(avisoData, 
                        "Campo Vázio", true);
        else if(!vFData.validaData(cbMes.getValue().toString(),
                cbDia.getValue().toString()))configAvisos(avisoData, 
                        "Data Inválida", true);
        else configAvisos(avisoData, null, false);

        if(cbGenero.getValue() == null)configAvisos(avisoGenero, 
                "Campo Vázio", true);
        else configAvisos(avisoGenero, null, false);

    }

    @Override
    public void configAvisos(Label labelAviso, String aviso, boolean  flag) {
       try{
            labelAviso.setText(aviso);
            labelAviso.setTextFill(Color.BLACK);
            labelAviso.setVisible(flag); 
       }catch(NullPointerException ex){
            labelAviso.setVisible(false);
       }
    }
}

