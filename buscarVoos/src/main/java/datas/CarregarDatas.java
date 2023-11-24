package datas;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author adils
 */
public class CarregarDatas {
    
    private List<Dia> dia;
    private List<Mes> mes;
    private List<Ano> ano;
    private ObservableList<Dia> obsDia;
    private ObservableList<Mes> obsMes;
    private ObservableList<Ano> obsAno;
    
    public CarregarDatas(){
        this.dia = new ArrayList<>();
        this.mes = new ArrayList<>();
        this.ano = new ArrayList<>();   
        
    }
    
    public ObservableList carregaDias(){
        for(int i=1; i<=31; i++){
            Dia dia = new Dia(i);
            this.dia.add(dia);
        }
        obsDia = FXCollections.observableArrayList(this.dia);
        return obsDia;
        
    }
    public ObservableList carregaMes(){
        Mes mes1 = new Mes("Janeiro");
        Mes mes2 = new Mes("Fevereiro");
        Mes mes3 = new Mes("Março");
        Mes mes4 = new Mes("Abril");
        Mes mes5 = new Mes("Maio");
        Mes mes6 = new Mes("Junho");
        Mes mes7 = new Mes("Julho");
        Mes mes8 = new Mes("Agosto");
        Mes mes9 = new Mes("Setembro");
        Mes mes10 = new Mes("Outubro");
        Mes mes11 = new Mes("Novembro");
        Mes mes12 = new Mes("Dezembro");
        
        this.mes.add(mes1);
        this.mes.add(mes2);
        this.mes.add(mes3);
        this.mes.add(mes4);
        this.mes.add(mes5);
        this.mes.add(mes6);
        this.mes.add(mes7);
        this.mes.add(mes8);
        this.mes.add(mes9);
        this.mes.add(mes10);
        this.mes.add(mes11);
        this.mes.add(mes12);
        
        obsMes = FXCollections.observableArrayList(this.mes);
        return obsMes;
        
    }
    
    public ObservableList carregaAno(){
        Calendar cal = Calendar.getInstance();
        int anoAtual = cal.get(Calendar.YEAR);
        
        for(int cont = anoAtual; cont>=1900; cont--){
            Ano ano = new Ano(cont);  
            this.ano.add(ano);
        }
        
        obsAno = FXCollections.observableArrayList(this.ano);
        return obsAno;
    }
}
