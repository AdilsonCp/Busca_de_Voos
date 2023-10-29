package projeto.buscarvoos;

import datas.Ano;
import datas.CarregarDatas;
import datas.Dia;
import datas.Mes;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import projeto.buscarvoos.Pessoa;
//import projeto.buscarvoos.Pessoa;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class CredenciaisPessoalController implements Initializable {
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
    
    CarregarDatas datas = new CarregarDatas();
    Pessoa pessoa = new Pessoa();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarDatas();
        carregarGenero();
    }    
    
    public void carregarGenero(){
        ObservableList<String> generos = FXCollections.observableArrayList(
            "Masculino",
            "Feminino",
            "Outro"
        );
        // Associe a lista de gêneros ao ComboBox
        cbGenero.setItems(generos);
    }
    
    public void carregarDatas(){
        cbDia.setItems(datas.carregaDias());
        cbMes.setItems(datas.carregaMes());
        cbAno.setItems(datas.carregaAno());
    }
}
