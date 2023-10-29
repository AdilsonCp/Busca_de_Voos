package projeto.buscarvoos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import projeto.buscarvoos.App;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class CredenciaisController implements Initializable {
    
    private Pane panePessoal;
    private Pane paneContato;
    private Pane paneUsuarioSenha;
    private Pane paneAtivo;
    
    @FXML
    private BorderPane paneView;
    @FXML
    private Button btnPessoal;
    @FXML
    private Button btnInfoContato;
    @FXML
    private Button btnUsuarioSenha;
    @FXML
    private Button btnProximo;
    @FXML
    private Button btnVoltar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPanes();
    }
    private void loadPanes() {

        try {
            panePessoal = FXMLLoader.load(App.class.getResource("credenciaisPessoal.fxml"));
            paneContato = FXMLLoader.load(App.class.getResource("credenciasContato.fxml"));
            paneUsuarioSenha = FXMLLoader.load(App.class.getResource("credenciaisUsuarioSenha.fxml"));
            paneView.setCenter(panePessoal);
            paneAtivo = panePessoal;
            btnPessoal.setDisable(false);
            btnVoltar.setDisable(true);
            btnProximo.setDisable(false);
        } catch (IOException ex) {
            Logger.getLogger(CredenciaisController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    public void avancar() {

        if (paneAtivo == panePessoal) {
            paneView.setCenter(paneContato);
            paneAtivo = paneContato;
            btnPessoal.setDisable(true);
            btnInfoContato.setDisable(false);
            btnVoltar.setDisable(false);
            btnProximo.setDisable(false);
        }
        else if (paneAtivo == paneContato) {
            paneView.setCenter(paneUsuarioSenha);
            paneAtivo = paneUsuarioSenha;
            btnInfoContato.setDisable(true);
            btnUsuarioSenha.setDisable(false);
            btnProximo.setDisable(true);
            btnVoltar.setDisable(false);         
        }
    }
    @FXML
    public void voltar(){
        
        if(paneAtivo == paneUsuarioSenha){
            paneView.setCenter(paneContato);
            paneAtivo = paneContato;
            btnUsuarioSenha.setDisable(true);
            btnInfoContato.setDisable(false);
            btnVoltar.setDisable(false);
            btnProximo.setDisable(false);
        }
        else if(paneAtivo == paneContato){
            paneView.setCenter(panePessoal);
            paneAtivo = panePessoal;
            btnPessoal.setDisable(false);
            btnInfoContato.setDisable(true);
            btnVoltar.setDisable(true);
            btnProximo.setDisable(false);
        }
    }

    

    
 
}
