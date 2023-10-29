package projeto.buscarvoos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class CredenciasContatoController implements Initializable {
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    
}
