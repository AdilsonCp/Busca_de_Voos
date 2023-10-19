package datas;

/**
 *
 * @author adils
 */
public class Ano {
    private int ano;

    public Ano(int ano) {
        this.ano = ano;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return Integer.toString(getAno());
    }
    
    
}
