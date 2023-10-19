package datas;

/**
 *
 * @author adils
 */
public class Mes {
    private String mes;

    public Mes(String mes) {
        this.mes = mes;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    @Override
    public String toString() {
        return getMes();
    }
    
}
