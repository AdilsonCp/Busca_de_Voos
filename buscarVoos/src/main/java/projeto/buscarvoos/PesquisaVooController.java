package projeto.buscarvoos;

import conexaoBanco.RotasBD;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import projeto.buscarvoos.Rotas;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class PesquisaVooController implements Initializable {
    
    @FXML
    private VBox vboxRotas;
    
    private List<Rotas> listaRotas;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        buscaVoo();
    } 
    
    private void buscaVoo(){
        
        if(listaRotas == null){
            RotasBD rotas = new RotasBD();
            listaRotas = rotas.getListaRotas();
            
            for(int i=0; i<listaRotas.size(); i++){
                
                Rotas rot = listaRotas.get(i);
                
                try{
                    FXMLLoader loader = new FXMLLoader(
                            App.class.getResource("rotas.fxml"));
                    HBox rota = loader.load();
                    
                    Label empresa = (Label) rota.getChildren().get(0);
                    empresa.setText(rot.getEmpresa());
                    
                    vboxRotas.getChildren().add(rota);
                }catch(IOException ex){
                    Logger.getLogger(PesquisaVooController.class.getName())
                            .log(Level.SEVERE, null,ex);
                }
            }
        }
       
    } 
    

    
}
