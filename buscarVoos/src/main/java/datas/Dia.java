package datas;

/**
 *
 * @author adils
 */
public class Dia {
    private int dia;

    public Dia(int dia) {
        this.dia = dia;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return Integer.toString(this.dia);
    }

    
    
    
}
