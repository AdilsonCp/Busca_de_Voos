/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import projeto.projetobuscavoos.App;
import conexaoBanco.RotasBD;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author adils
 */
public class PagamentoController implements Initializable {
    
    @FXML
    private BorderPane paneView;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Label lbConfirm;
    
    private CartaoController cardController;
    private BoletoController boletoController;
    private PixController pixController;
    private CompraController compraController;
      
    private Pane paneAtivo;
    private Pane paneCartao;
    private Pane panePix;
    private Pane paneBoleto;
    
    private String idPessoa;
    private String idVoo;
    private String idPoltrona;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            lbConfirm.setVisible(false);
            btnConfirmar.setVisible(false);
            loadPanes();
        } catch (IOException ex) {
            Logger.getLogger(PagamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void loadPanes() throws IOException{
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/telas/cartao.fxml"));
        paneCartao = loader.load();
        cardController = loader.getController();
        
        
        FXMLLoader loader1 = new FXMLLoader(App.class.getResource("/telas/pix.fxml"));
        panePix = loader1.load();
        pixController = loader1.getController();
        
        
        FXMLLoader loader2 = new FXMLLoader(App.class.getResource("/telas/boleto.fxml"));
        paneBoleto = loader2.load();
        boletoController = loader2.getController();
        
    }
    
    @FXML
    public void cartao(){
        paneAtivo = paneCartao;
        btnConfirmar.setVisible(true);
        paneView.setCenter(paneAtivo);
    }
 
    @FXML
    public void pix(){
        paneAtivo = panePix;
        btnConfirmar.setVisible(true);
        paneView.setCenter(paneAtivo);
        pixController.setCodigoPix(gerarCodigo(true));
    }

    @FXML
    public void boleto(){
        paneAtivo = paneBoleto;
        btnConfirmar.setVisible(true);
        paneView.setCenter(paneAtivo);
        boletoController.codigoBoleto(gerarCodigo(false));
        
        
    }
    
    public String gerarCodigo(boolean isPix) {
        Random random = new Random();

        String caracteres = null;
        int tamanho = 0;
        
        if(isPix){
            caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            tamanho = 40;
        }
        else {
            caracteres = "0123456789";
            tamanho = 30;
        }

        StringBuilder codigo = new StringBuilder(tamanho);

        // Gere o código aleatório caracter por caracter
        for (int i = 0; i < tamanho; i++) {
            int indice = random.nextInt(caracteres.length());
            char caractereAleatorio = caracteres.charAt(indice);
            codigo.append(caractereAleatorio);
        }

        return codigo.toString();
    }
    
    @FXML
    public void confirmaPag(){
        if(paneAtivo == paneCartao){
            if(cardController.validarCampos() == 1){
                cardController.avisos();
                //System.out.println("Confirmado");
                finalizarCompra();
                
            }
            else{
                cardController.avisos();
                //System.out.println("Errado");
            }
        }
        else if(paneAtivo == panePix){
            finalizarCompra();
        }
        else{
            finalizarCompra();
        }
    }
    
    public void finalizarCompra(){
        RotasBD  acesso = new RotasBD();
        acesso.insertPoltrona(idVoo, idPoltrona);
        acesso.insertPassagem(idPessoa, idVoo, idPoltrona);
        paneView.setCenter(null);
        paneView.setCenter(lbConfirm);
        lbConfirm.setVisible(true);
        lbConfirm.setText("Compra Realizada com Sucesso");
        btnConfirmar.setDisable(true);
    }
    
    public void setIdVoo(String idVoo) {
        this.idVoo = idVoo;
    }
    
    public void setIdPessoa(String idPessoa) {
        this.idPessoa = idPessoa;
    }
    
    public void setIdPoltrona(String idPoltrona){
        this.idPoltrona = idPoltrona;
    }
    
    
}
