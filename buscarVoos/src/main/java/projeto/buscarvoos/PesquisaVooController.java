package projeto.buscarvoos;

import conexaoBanco.RotasBD;
import datas.Ano;
import datas.CarregarDatas;
import datas.Dia;
import datas.Mes;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
    @FXML 
    private ComboBox<String> cbPaisOri;
    @FXML 
    private ComboBox<String> cbCidadeOri;
    @FXML 
    private ComboBox<String> cbPaisDesti;
    @FXML 
    private ComboBox<String> cbCidadeDesti;
    @FXML
    private ToggleGroup grupo;
    @FXML
    private ComboBox<Dia> cbDia;
    @FXML
    private ComboBox<Mes> cbMes;
    @FXML
    private ComboBox<String> cbAno;
    @FXML
    private Pane pPreco;
    @FXML
    private Pane pData;
    @FXML
    private TextField txPreco;

    
    CarregarDatas datas = new CarregarDatas();
    //private List<Rotas> listaRotas;
    
    
     //RotasBD rotas = new RotasBD();
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pData.setVisible(false);
        carregarPaises();
        carregarDatas();
        
        //buscaVoo();
    }
    public void carregarDatas() {
        cbDia.setItems(datas.carregaDias());
        cbMes.setItems(datas.carregaMes());
        cbAno.setItems(datas.carregaAno());

    }
    
    public void carregarPaises(){
        ObservableList<String> paises = FXCollections.observableArrayList(
                "África do Sul","Alemanha", "Argentina", "Arábia Saudita", "Austrália", "Brasil",
                "Canadá", "China", "Coreia do Sul", "Espanha", "Estados Unidos",
                "França", "Índia", "Indonésia", "Itália", "Japão", "México",
                "Países Baixos", "Peru", "Portugal", "Reino Unido", "Rússia",
                "Suíça", "Turquia"
        );

        cbPaisDesti.setItems(paises);
        cbPaisOri.setItems(paises);

    }
    
    @FXML
    public void carregarCidades(){
        //Pais de origem
        if(cbPaisOri.getValue() != null){
            carregarCidade(cbPaisOri.getValue().toUpperCase(), "ORIGEM");
        }
        else{
            cbCidadeOri.setValue(null);
        }
        
        //Pais de destino
        if(cbPaisDesti.getValue() != null){
            carregarCidade(cbPaisDesti.getValue().toUpperCase(), "DESTINO");
        }
        else{
            cbCidadeDesti.setValue(null);
        }
    }
    
    public void carregarCidade(String pais, String campo){
        RotasBD rotas = new RotasBD();
        
        List<String> listaCidades = rotas.getCidade(pais);
      
        ObservableList<String> cidadeOri = FXCollections.observableArrayList(
                listaCidades
        );

       if(campo.equals("ORIGEM"))cbCidadeOri.setItems(cidadeOri);
       else if (campo.equals("DESTINO"))cbCidadeDesti.setItems(cidadeOri);
    }
    
    @FXML
    public void selecionar(){
        RadioButton radio = (RadioButton) grupo.getSelectedToggle();
        
        if(radio.getText().equals("Filtra por preço")){
            pPreco.setVisible(true);
            pData.setVisible(false);
            cbDia.setValue(null);
            cbMes.setValue(null);
            cbAno.setValue(null);
        }
        else if(radio.getText().equals("Filtra por data")){
            pPreco.setVisible(false);
            pData.setVisible(true);
            txPreco.setText(null);    
        }
    }
    
    @FXML
    public void buscar(){

        RadioButton radio = (RadioButton) grupo.getSelectedToggle();
       
        String paisOri = cbPaisOri.getValue().toUpperCase();
        String cidadeOrig = cbCidadeOri.getValue();
        String paisDes = cbPaisOri.getValue().toUpperCase();
        String cidadeDes = cbCidadeDesti.getValue();
        
         if(radio.getText().equals("Filtra por preço")){
             buscaVoo(paisOri, cidadeOrig, paisDes, cidadeDes, txPreco.getText());
         }
         else if(radio.getText().equals("Filtra por data")){
         
         }
    }

  
    
    private void buscaVoo(String paisOri, String cidadeOrig, String paisDes, String cidadeDes, String precoData){
        System.out.println("Aqui");
        
        List<Rotas> listaRotas = null;
                
        if(listaRotas == null){
            RotasBD rotas = new RotasBD();
            listaRotas = rotas.getListaRotas(paisOri, cidadeOrig, paisDes, cidadeDes, precoData);
            
            System.out.println("lista");
            
            vboxRotas.getChildren().clear();
            
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
        System.out.println("Final");
    }
    

   
    public void clearVbox(){
        vboxRotas.getChildren().clear();
    }
    
    public double rotacao(double angulo){
        if(angulo == 0)return 180;
        else return 0;
    }
 
}
