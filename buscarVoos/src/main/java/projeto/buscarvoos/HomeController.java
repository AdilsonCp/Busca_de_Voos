/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projeto.buscarvoos;

import conexaoBanco.RotasBD;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author adils
 */

public class HomeController implements Initializable {
    
    @FXML
    private Label empresa;
    @FXML
    private Label dataPartida;
    @FXML
    private Label paisOrigem;
    @FXML
    private Label cidadeOrigem;
    @FXML
    private Label paisDestino;
    @FXML
    private Label cidadeDestino;
    @FXML
    private Label preco;
    
    
    @FXML
    private GridPane rotas;

    private List<Rotas> listaRotas;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        buscarVoo();
    }    
    

   /*public void buscarVoo() {
        if (listaRotas == null) {
            RotasBD rotasBD = new RotasBD();
            listaRotas = rotasBD.getListaRotas();
        

            // Cria um GridPane manualmente
            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            
            int j=0;   
            for(int i=0; i<1; i++){
                j =0;


                Rotas rot = listaRotas.get(i);
                // Adiciona os labels ao GridPane
                empresa.setText(rot.getEmpresa());
                gridPane.add(empresa, j,i);
                j++;

                //dataPartida.setText(rot.getDataPartida());
                dataPartida.setText("dataPartida");
                gridPane.add(dataPartida, j, i);
                j++;

                paisOrigem.setText(rot.getPaisOrigem());
                gridPane.add(paisOrigem, j, i);
                j++;

                cidadeOrigem.setText(rot.getCidadeOrigem());
                gridPane.add(cidadeOrigem, j, i);
                j++;

                paisDestino.setText(rot.getPaisDestino());
                gridPane.add(paisDestino, j, i);
                j++;

                cidadeDestino.setText(rot.getCidadeDestino());
                gridPane.add(cidadeDestino, j, i);
                j++;

                preco.setText( Double.toString( rot.getPreco()));
                gridPane.add(preco, j, i);



                // Adiciona o GridPane ao rotas GridPane
                rotas.getChildren().add(gridPane);

            }
        }
    }*/
    
    public void buscarVoo() {
        if (listaRotas == null) {
            RotasBD rotasBD = new RotasBD();
            //listaRotas = rotasBD.getListaRotas();

            for (Rotas rot : listaRotas) {
                // Cria um novo GridPane para cada rota
                GridPane gridPane = new GridPane();
                gridPane.setHgap(10);

                int row = 0;

                // Adiciona os labels ao GridPane
                Label empresaLabel = new Label(rot.getEmpresa());
                gridPane.add(empresaLabel, 0, row++);

                Label dataPartidaLabel = new Label(rot.getDataPartida());
                gridPane.add(dataPartidaLabel, 0, row++);

                Label paisOrigemLabel = new Label(rot.getPaisOrigem());
                gridPane.add(paisOrigemLabel, 0, row++);

                Label cidadeOrigemLabel = new Label(rot.getCidadeOrigem());
                gridPane.add(cidadeOrigemLabel, 0, row++);

                Label paisDestinoLabel = new Label(rot.getPaisDestino());
                gridPane.add(paisDestinoLabel, 0, row++);

                Label cidadeDestinoLabel = new Label(rot.getCidadeDestino());
                gridPane.add(cidadeDestinoLabel, 0, row++);

                Label precoLabel = new Label(Double.toString(rot.getPreco()));
                gridPane.add(precoLabel, 0, row);

                // Adiciona o GridPane ao rotas GridPane
                rotas.add(gridPane, 0, listaRotas.indexOf(rot));
            }
        }
    }
}
     
      
   



                /*Label dataPartida = new Label("Data de Partida");
                gridPane.add(dataPartida, 0, 1);
                
                Label paisOrigem = new Label("País de Origem");
                gridPane.add(paisOrigem, 0, 2);
                
                Label cidadeOrigem = new Label("Cidade de Origem");
                gridPane.add(cidadeOrigem, 0, 3);
                
                Label paisDestino = new Label("País de Destino");
                gridPane.add(paisDestino, 0, 4);
                
                Label cidadeDestino = new Label("Cidade de Destino");
                gridPane.add(cidadeDestino, 1, 5);
                
                Label preco = new Label("Preço");
                gridPane.add(preco, 0, 6);*/


    

