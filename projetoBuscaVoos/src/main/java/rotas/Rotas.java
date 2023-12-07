/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rotas;

/**
 *
 * @author adils
 */
public class Rotas {
    private String empresa;
    private String dataPartida;
    private String paisOrigem;
    private String cidadeOrigem;
    private String paisDestino;
    private String cidadeDestino;
    private double preco;
    private String id;
    
    public Rotas(String id){
        this.id = id;
    }

    public Rotas(String empresa, String dataPartida, String paisOrigem, String cidadeOrigem, String paisDestino, String cidadeDestino, double preco, String id) {
        this.empresa = empresa;
        this.dataPartida = dataPartida;
        this.paisOrigem = paisOrigem;
        this.cidadeOrigem = cidadeOrigem;
        this.paisDestino = paisDestino;
        this.cidadeDestino = cidadeDestino;
        this.preco = preco;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataPartida() {
        return dataPartida;
    }
    
    public String getEmpresa() {
        return empresa;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public String getCidadeOrigem() {
        return cidadeOrigem;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public String getCidadeDestino() {
        return cidadeDestino;
    }

    public double getPreco() {
        return preco;
    }   
}
