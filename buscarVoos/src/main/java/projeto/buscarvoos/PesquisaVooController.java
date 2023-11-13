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
import javafx.scene.image.ImageView;
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
    @FXML
    private ImageView image;
    
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
                    
                    Label dataPartida = (Label) rota.getChildren()
                            .get(1);
                    dataPartida.setText(rot.getDataPartida());
                    
                    Label paisOrigem = (Label) rota.getChildren().get(2);
                    paisOrigem.setText(rot.getPaisOrigem());
                            
                    Label cidadeOrigem = (Label) rota.getChildren().get(3);
                    cidadeOrigem.setText(rot.getCidadeOrigem());
                            
                    Label paisDestino = (Label) rota.getChildren().get(4);
                    paisDestino.setText(rot.getPaisDestino());
                            
                    Label cidadeDestino = (Label) rota.getChildren().get(5);
                    cidadeDestino.setText(rot.getCidadeDestino());
                    
                    Label preco = (Label) rota.getChildren().get(6);
                    preco.setText(Double.toString(rot.getPreco()));
                    
                    vboxRotas.getChildren().add(rota);
                }catch(IOException ex){
                    Logger.getLogger(PesquisaVooController.class.getName())
                            .log(Level.SEVERE, null,ex);
                }
            }
        }     
    }
    
    @FXML
    public void empresaOrde(){
        image.setRotate(rotacao(image.getRotate()));
    }
    
    public double rotacao(double angulo){
        if(angulo == 0)return 180;
        else return 0;
    }
    

    
}
