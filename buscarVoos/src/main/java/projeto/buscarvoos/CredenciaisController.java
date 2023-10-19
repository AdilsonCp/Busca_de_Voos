package projeto.buscarvoos;

import datas.CarregarDatas;
import datas.Ano;
import datas.Dia;
import datas.Mes;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class CredenciaisController implements Initializable {
    
    @FXML
    private ComboBox<Dia> cbDia;
    @FXML
    private ComboBox<Mes> cbMes;
    @FXML
    private ComboBox<Ano> cbAno;
    
    
    CarregarDatas datas = new CarregarDatas();
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarDatas();
    }
    
    public void carregarDatas(){
        cbDia.setItems(datas.carregaDias());
        cbMes.setItems(datas.carregaMes());
        cbAno.setItems(datas.carregaAno());
    }
    
}
