/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pessoasDatas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author adils
 */
public class Pessoa {
    
    private String nome;
    private String cpf;
    private String genero;
    private String diaNasc;
    private String mesNasc;
    private String anoNasc;
    
    private String telefone;
    private String cep;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    
    private String email;
    private String senha;
    
    Datas data = new Datas();

    public Pessoa() {
        this.nome = null;
        this.cpf = null;
        this.genero = null;
        this.diaNasc = null;
        this.mesNasc = null;
        this.anoNasc = null;
        this.telefone = null;
        this.cep = null;
        this.endereco = null;
        this.numero = null;
        this.complemento = null;
        this.bairro = null;
        this.cidade = null;
        this.uf = null;
        this.email = null;
        this.senha = null;
    }

    public boolean getNome() {
        if(this.nome.length() >= 5)return true;
        else return false;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getCpf() {
        if(this.cpf.length() == 11 && this.cpf.matches("^[0-9]+$"))return true;
        else return false;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    /*public String getDiaNasc() {
        return diaNasc;
    }*/

    public void setDiaNasc(String diaNasc) {
        this.diaNasc = diaNasc;
    }

    /*public String getMesNasc() {
        return mesNasc;
    }*/

    public void setMesNasc(String mesNasc) {
        this.mesNasc = mesNasc;
    }

    /*public String getAnoNasc() {
        return anoNasc;
    }*/

    public void setAnoNasc(String anoNasc) {
        this.anoNasc = anoNasc;
    }
    
    public boolean validarData(){
        return data.validaData(this.mesNasc, this.diaNasc);
    }
    
    
    public boolean getTelefone() {
        if(this.telefone.matches("^[0-9]+$") && 
                this.telefone.length() == 11)return true;
        else return false;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean getCep() {
        if(this.cep.matches("^[0-9]+$") && 
                this.cep.length() ==8)return true;
        else return false;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public boolean getEndereco() {
        if(this.endereco.length() >= 4 && this.endereco.length() <=100){
            return true;
        }
        else return false;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public boolean getNumero() {
        if(this.numero.matches("^[0-9]+$") &&
                this.numero.length() >=1 && this.numero.length() <=10){
            return true;
        }
        else return false;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public boolean getComplemento() {
        if(this.complemento.length() == 0 ||(this.complemento.length() >= 0 &&
                this.complemento.length()<= 100)) {
            return true;
        }
        else return false;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public boolean getBairro() {
        if(this.bairro.length()>= 4 && this.bairro.length() <= 100) {
            return true;
        }
        else return false;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public boolean getCidade() {
        if(this.cidade.length()>= 4 && this.cidade.length() <= 100) {
            return true;
        }
        else return false; 
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public boolean getEmail() {
        if(this.email.length() >=8 && this.email.length() <= 100 && validarEmail(this.email))return true;
        else return false;
    }
    
    public static boolean validarEmail(String email) {
        String padraoEmail = "^[a-zA-Z0-9-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-]+$";
        Pattern pattern = Pattern.compile(padraoEmail);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getSenha() {
        if(this.senha.length() >=8 && this.senha.length() <= 100)return true;
        else return false;

    }

    public void setSenha(String senha) {
        this.senha = senha;
    } 
    
    
    public boolean validaDadosPessoal(){
        if(getNome() == true && getCpf() == true && validarData() == true){
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean validaContato(){
        if(getTelefone() == true && getCep() == true && getEndereco() == true &&
                getNumero() == true && getComplemento() == true && getBairro() == true &&  getCidade() == true){
            return true;
        }
        else return false;
        
    }
    
    public boolean validaCredenciais(){
        if(getEmail() == true && getSenha() == true) return true;
        else return false;
    }
    
    
}
