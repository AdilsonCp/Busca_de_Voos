/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import conexaoBanco.RotasBD;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pessoasDatas.Datas;
import projeto.projetobuscavoos.App;
import rotas.Rotas;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class PesquisaVooController implements Initializable {
    
    @FXML
    private VBox vboxRotas;
    @FXML 
    private ComboBox<String> cbPaisOri;
    @FXML 
    private ComboBox<String> cbCidadeOri;
    @FXML 
    private ComboBox<String> cbPaisDesti;
    @FXML 
    private ComboBox<String> cbCidadeDesti;
    @FXML
    private CheckBox checPreco;
    @FXML
    private CheckBox checData;
    @FXML
    private Label dataValida;
    @FXML
    private Label localPartida;
    @FXML
    private Label localDestino;
    @FXML
    private Label precoVoo;
    @FXML
    private ComboBox<String> cbDia;
    @FXML
    private ComboBox<String> cbMes;
    @FXML
    private ComboBox<String> cbAno;
    @FXML 
    private ComboBox<String> cbPreco;

    
    Datas datas = new Datas();
    RotasController rController;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataValida.setVisible(false);
        localPartida.setVisible(false);
        localDestino.setVisible(false);
        precoVoo.setVisible(false);
        carregarPaises();
        carregarDatas();
        ObservableList<String> ano = FXCollections.observableArrayList(
                "2023","2024"); 
        ObservableList<String> preco = FXCollections.observableArrayList(
                preco()
        ); 
        cbPreco.setItems(preco);
        cbAno.setItems(ano);
        
    }   
    
    public List<String> preco(){
        List<String>list = new ArrayList<>();
        for(int i = 1000; i<= 14000; i+=1000){
            list.add(Integer.toString(i));
        }
        return list;
    }
   
    public void carregarDatas() {
        cbDia.setItems(datas.carregaDias());
        cbMes.setItems(datas.carregaMes());
    }
 
    public void carregarPaises(){
        RotasBD rotas = new RotasBD();
        List<String> listaPaises = rotas.getPaises();
      
        ObservableList<String> lista = FXCollections.observableArrayList(
                listaPaises);
        
        cbPaisOri.setItems(lista);
    }
    @FXML
    public void carregarCidades(){       
        if(cbPaisOri.getValue() != null){
            RotasBD rotas = new RotasBD();

            List<String> listaCidades = rotas.getCidade(cbPaisOri.getValue());

            ObservableList<String> lista = FXCollections.observableArrayList(
                    listaCidades);
            
            cbCidadeOri.setItems(lista);
        }
        else{
            cbCidadeOri.setValue(null);
        }
    }

    
    @FXML
    public void carregarPaisesDestino(){
        //Carregar pais de Destino
        RotasBD rotas = new RotasBD();
        
        if(cbCidadeOri.getValue() != null && cbPaisOri.getValue() != null){
            List<String> listaPaises = rotas.getPaisesDestino(cbPaisOri.getValue(), cbCidadeOri.getValue());

            ObservableList<String> lista = FXCollections.observableArrayList(
                    listaPaises );

            cbPaisDesti.setItems(lista);            
        }
        else cbPaisDesti.setValue(null);  
    }
    
    @FXML
    public void carregarCidadeDestino(){
        //Carregar pais de Destino
        RotasBD rotas = new RotasBD();
        
        if(cbCidadeOri.getValue() != null && cbPaisOri.getValue() != null && cbPaisDesti.getValue() != null){
            List<String> cidades = rotas.getCidadeDestino(cbPaisOri.getValue(), cbCidadeOri.getValue(), cbPaisDesti.getValue());

            ObservableList<String> lista = FXCollections.observableArrayList(
                    cidades );

            cbCidadeDesti.setItems(lista);
        }
        else if (cbPaisDesti.getValue() == null || cbCidadeOri.getValue() == null) cbCidadeDesti.setValue(null);
    }
    
    @FXML
    public void selecionar(){
        if(checPreco.isSelected() || checData.isSelected()){
            cbPaisDesti.setDisable(true);
            cbCidadeDesti.setDisable(true);
            if(checPreco.isSelected())checData.setSelected(false);
            else if(checData.isSelected())checPreco.setSelected(false);
            
        }
        else{
            cbPaisDesti.setDisable(false);
            cbCidadeDesti.setDisable(false); 
        }
    }
    
    @FXML
    public void buscar(){
        dataValida.setVisible(false);
        localDestino.setVisible(false);
        precoVoo.setVisible(false);     
        localPartida.setVisible(false);
        String paisOri = null;
        String cidadeOrig = null;
        
        
        if(cbPaisOri.getValue() == null || cbCidadeOri.getValue() == null){
            localPartida.setVisible(true);
            localPartida.setText("Preencha todos os campos");
        }
        else{
            localPartida.setVisible(false);
            paisOri = cbPaisOri.getValue().toUpperCase();
            cidadeOrig = cbCidadeOri.getValue();
            
            if(checData.isSelected()){  
               cbPaisDesti.setValue(null);
               cbCidadeDesti.setValue(null);
               cbPreco.setValue(null);

               if(cbDia.getValue() == null || cbMes.getValue() == null || cbAno.getValue() == null){
                   dataValida.setVisible(true);
                   dataValida.setText("Preencha todos os campos de data");
               }
               else{
                   if(datas.validaData(cbMes.getValue(), cbDia.getValue())){
                       dataValida.setVisible(false);
                       String mes = Integer.toString(datas.numMes(cbMes.getValue()));
                       String data = cbAno.getValue()+"-"+mes+"-"+cbDia.getValue()+" 00:00:00";
                       buscaVoo(paisOri, cidadeOrig, null, null, null,data);    
                   } 
                   else {
                       dataValida.setVisible(true);
                       dataValida.setText("Data Inválida");
                   }
               }
            }
            else if(checPreco.isSelected()){
                cbDia.setValue(null);
                cbMes.setValue(null);
                cbAno.setValue(null); 
                cbPaisDesti.setValue(null);
                cbCidadeDesti.setValue(null);     
                
                if(cbPreco.getValue() == null){
                    precoVoo.setVisible(true);
                    precoVoo.setText("Campo vázio");
                }
                else{
                    precoVoo.setVisible(false);
                    buscaVoo(paisOri, cidadeOrig, null, null, cbPreco.getValue(),null);
                }
            }
            else{
                cbDia.setValue(null);
                cbMes.setValue(null);
                cbAno.setValue(null);
                cbPreco.setValue(null);
                
                if(cbPaisDesti.getValue() == null || cbCidadeDesti.getValue() == null){
                    localDestino.setVisible(true);
                    localDestino.setText("Preencha todos os campos");
                }
                else{
                    localDestino.setVisible(false);
                    String paisDes = cbPaisDesti.getValue().toUpperCase();
                    String cidadeDes = cbCidadeDesti.getValue();
                    buscaVoo(paisOri, cidadeOrig, paisDes, cidadeDes, null,null);
                }
                
            }
        }        
    }

    private void buscaVoo(String paisOri, String cidadeOrig, String paisDes, String cidadeDes, String valor, String data){
        
        List<Rotas> listaRotas = null;
                
        if(listaRotas == null){
            
            RotasBD rotas = new RotasBD();
            listaRotas = rotas.getListaRotas(paisOri, cidadeOrig, paisDes, cidadeDes, valor, data);
            
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
    

    

    

    


}
