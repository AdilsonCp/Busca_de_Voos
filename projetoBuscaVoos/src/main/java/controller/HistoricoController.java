/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import conexaoBanco.AcessoBDCadastroBD;
import conexaoBanco.RotasBD;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import projeto.projetobuscavoos.App;
import rotas.Rotas;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class HistoricoController implements Initializable {
    
    @FXML
    private VBox vboxRotas;
    @FXML
    private ComboBox cbNumVoo;
    @FXML
    private Label lbAviso;
    @FXML
    private Label lbAvisoVoo;
    @FXML
    private Pane paneNumVoo;
    @FXML
    private Button btndeletar;
    
    private String pessoaId;
    private String idVoo;
    private boolean listaIsNull;


    
    RotasController rController;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
   
        
    }
    public void carregarNumVoos(){
  
        RotasBD rotas = new RotasBD();
        List<String> listaNumm = rotas.getHistoricoNumVoo(pessoaId);
      
        ObservableList<String> lista = FXCollections.observableArrayList(
                listaNumm);
        
        cbNumVoo.setItems(lista); 
    }
    
    
    private void buscaVoo(){
        
        List<Rotas> listaRotas = null;
                
        if(listaRotas == null){
            
            RotasBD rotas = new RotasBD();
            listaRotas = rotas.getListaVoosEscolhidos(pessoaId,idVoo,false);
            
            if(listaRotas.isEmpty())listaIsNull = true;
            else listaIsNull = false;
            
            vboxRotas.getChildren().clear();
            
            for(int i=0; i<listaRotas.size(); i++){
                
                
                Rotas rot = listaRotas.get(i);
                
                try{
                    FXMLLoader loader = new FXMLLoader(
                            App.class.getResource("/telas/rotas.fxml"));
                    HBox rota = loader.load();
                    rController =loader.getController();
                                     
                                       
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
                    
                    Label id = (Label) rota.getChildren().get(7);
                    id.setText(rot.getId());
   
                    
                    vboxRotas.getChildren().add(rota);
                }catch(IOException ex){
                    Logger.getLogger(PesquisaVooController.class.getName())
                            .log(Level.SEVERE, null,ex);
                }
            }
        } 
    }
    
    public String getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(String pessoaId) {
        btndeletar.setVisible(false);
        this.pessoaId = pessoaId;
        carregarNumVoos();
        buscaVoo();
        if(listaIsNull){
            lbAvisoVoo.setVisible(true);
            paneNumVoo.setVisible(false);
            lbAvisoVoo.setText("Você não tem nenhuma compra registrada");
        }
        else{
            paneNumVoo.setVisible(true);
            lbAvisoVoo.setVisible(false);
        }
    }
    
    @FXML
    public void selecionar(){
        if(cbNumVoo.getValue() != null){
            lbAviso.setVisible(false);
            idVoo = cbNumVoo.getValue().toString();
            buscaVoo();
            btndeletar.setVisible(true);
            cbNumVoo.setValue(null);
        }
        else {
            btndeletar.setVisible(false);
            lbAviso.setVisible(true);
            lbAviso.setText("Selecione o númro do voo");
        }
    }
    @FXML
    public void deletar(){
        RotasBD rotas = new RotasBD();
        String poltrona = null;
        if(idVoo != null){
            
            poltrona = rotas.deletarPassagem(pessoaId, idVoo);
            rotas.deletarPassagem1(pessoaId, idVoo);
            rotas.atualizarPoltronas(poltrona, idVoo);
            System.out.println("DELETADO");
            carregarNumVoos();
            buscaVoo();
        }
    }
    
    
}
