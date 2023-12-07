/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import conexaoBanco.RotasBD;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import projeto.projetobuscavoos.App;
import rotas.Rotas;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class CompraController implements Initializable {

    @FXML
    private ComboBox<String> cbnumVoo;
    @FXML
    private Pane paneBtn;
    @FXML
    private BorderPane bordView;
    @FXML
    private Label lbAviso;
    @FXML
    private Button btnPagamento;
    
    
    private String idVoo;
    private String idPessoa;
    private String idPoltrona;

    
    private InfoVooController infoController;
    private PagamentoController pagController;
    
    private Pane panePagamento;
    private Pane paneinvoController;
    private Pane paneAtivo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnPagamento.setVisible(false);
        carregarNumVoos();
    }  
    
    public void carregarNumVoos(){
        RotasBD rotas = new RotasBD();
        List<String> listaNumm = rotas.getNumm();
      
        ObservableList<String> lista = FXCollections.observableArrayList(
                listaNumm);
        
        cbnumVoo.setItems(lista);    
    }
    
    @FXML
    public void ataulizarPoltronas(){
           atualizarPoltronasAux();
    }
    
    public void atualizarPoltronasAux(){
        bordView.setCenter(null);
        RotasBD rotas = new RotasBD(); 
        for(int i=0; i<20;i++){
            
            Button botao = (Button) paneBtn.getChildren().get(i); 
            
            if(rotas.getListPoltrona(cbnumVoo.getValue(), botao.getText())){
                botao.setDisable(true);
                botao.setStyle("-fx-background-color: black; -fx-text-fill: white;");
                
            }
            else {
                botao.setDisable(false);
                botao.setStyle("");
            }
                
               
        } 
    }
    
    @FXML
    public void poltronas(ActionEvent event) throws IOException{
        atualizarPoltronasAux();
        if(cbnumVoo.getValue() == null){
            lbAviso.setVisible(true);
            lbAviso.setText("Selecione um Voo");
            btnPagamento.setVisible(false);

        }
        else{
            btnPagamento.setVisible(true);
            lbAviso.setVisible(false);
            Button bt = (Button) event.getSource();

            FXMLLoader loader = new FXMLLoader(App.class.getResource("/telas/infoVoo.fxml"));
            paneinvoController = loader.load();
            infoController = loader.getController();
            paneAtivo = paneinvoController;

            bordView.setCenter(paneinvoController);

            carregarVoo(bt.getText(),cbnumVoo.getValue());   
            
        }     
    }
    
    public void carregarVoo(String assento, String numVoo){
        idPoltrona = assento;
        
        List<Rotas> listaRotas = null;
        
        RotasBD rotas = new RotasBD();
        listaRotas = rotas.getListaVoosEscolhidos(null,numVoo,true);

        if(!listaRotas.isEmpty()){
            Rotas rot = listaRotas.get(0);       

            infoController .setCompAerea(rot.getEmpresa());
            infoController .setDataPartida(rot.getDataPartida());
            infoController .setPaisPartida(rot.getPaisOrigem());
            infoController .setCidadePartida(rot.getCidadeOrigem());
            infoController .setPaisDestino(rot.getPaisDestino());
            infoController .setCidadeDestino(rot.getCidadeDestino());
            infoController .setPreco(Double.toString(rot.getPreco()));
            infoController.setTxAssento(assento);
        }    
    }
    
    @FXML
    public void pagamento() throws IOException{
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/telas/pagamento.fxml"));
        panePagamento = loader.load();
        pagController = loader.getController();
        paneAtivo = panePagamento;
        
        bordView.setCenter(paneAtivo);
        btnPagamento.setVisible(false);   
        
        pagController.setIdPessoa(idPessoa);
        pagController.setIdPoltrona(idPoltrona);
        pagController.setIdVoo(cbnumVoo.getValue());
    }
    
    

    public void setIdVoo(String idVoo) {
        this.idVoo = idVoo;
    }
    
    public void setIdPessoa(String idPessoa) {
        this.idPessoa = idPessoa;
    }
    
    
}
