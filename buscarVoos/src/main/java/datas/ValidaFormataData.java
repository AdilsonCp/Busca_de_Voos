/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datas;

/**
 *
 * @author adils
 */
public class ValidaFormataData {
    private int dia;
    private int mesNumero;
    private String mesString;
    private int ano;

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMesNumero() {
        return mesNumero;
    }

    public void setMesNumero(int mesNumero) {
        this.mesNumero = mesNumero;
    }

    public String getMesString() {
        return mesString;
    }

    public void setMesString(String mesString) {
        this.mesString = mesString;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
    public int numMes(String mes){
        int numMes = 0;
        switch (mes) {
            case "Janeiro":
                numMes = 1;
                break;
            case "Fevereiro":
                numMes =2;
                break;
            case "Março":
                numMes = 3;
                break;
            case "Abril":
                numMes = 4;
                break;
            case "Maio":
                numMes = 5;
                break;
            case "Junho":
                numMes = 6;
                break;
            case "Julho":
                numMes = 7;
                break;
            case "Agosto":
                numMes = 8;
                break;
            case "Setembro":
                numMes = 9;;
                break;
            case "Outubro":
                numMes = 10;
                break;
            case "Novembro":
                numMes = 11;
                break;
            case "Dezembro":
                numMes = 12;
                break;
        }
        return numMes;
    }
    
    public String strMes(int mes){
        String strMes = " ";
        switch (mes) {
            case 1:
                strMes = "Janeiro";
                break;
            case 2:
                strMes ="Fevereiro";
                break;
            case 3:
                strMes = "Março";
                break;
            case 4:
                strMes = "Abril";
                break;
            case 5:
                strMes = "Maio";
                break;
            case 6:
                strMes = "Junho";
                break;
            case 7:
                strMes = "Julho";
                break;
            case 8:
                strMes = "Agosto";
                break;
            case 9:
                strMes = "Setembro";
                break;
            case 10:
                strMes = "Outubro";
                break;
            case 11:
                strMes = "Novembro";
                break;
            case 12:
                strMes = "Dezembro";
                break;
        }
        return strMes;
    }
    public boolean validaData(String mes, String dia){
        int mesNum = numMes(mes);
        int diaNum = Integer.parseInt(dia);
        boolean flag = false;
        
        if(mesNum == 1 || mesNum == 3 || mesNum == 5 || mesNum == 7 ||
                mesNum == 8 || mesNum == 10 || mesNum == 12 ){
            if(diaNum >=1 && diaNum <= 31)flag = true;
        }
        
        else if(mesNum == 2){
            if(diaNum >= 1 && diaNum <= 28) flag = true;
        }
        
        else if(mesNum == 4 || mesNum == 6 || mesNum == 9 || mesNum == 11){
            if(diaNum >=  1 && diaNum <= 30) flag = true;
        }
        
        else flag = false;
        
        return  flag;
    }

    
 
}
