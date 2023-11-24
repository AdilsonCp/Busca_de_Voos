package conexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import projeto.buscarvoos.LoginControlle;

/**
 *
 * @author adils
 */
public class Login {
    
    private String nome;
    private String id;
            
    
    public boolean login(String email, String senha) {
        

        //Criar uma conexão com o banco de dados
        Connection conexao = new BDConexaoMySQL().getConnection();

        //Preparar a consulta
        /*String select = "SELECT * FROM credenciais WHERE email = ? "
                + "AND senha = ?";*/
        
        String select  = "select pessoas.nome, pessoas.pessoa_id "               
		+ "from pessoas inner join credenciais on pessoas.pessoa_id = credenciais.pessoa_id "
                + "where email = ? and senha = ?";
     
        try {
            PreparedStatement ps = conexao.prepareStatement(select);
            ps.setString(1, email);
            ps.setString(2, senha);

            //Executar a consulta e obter o resultado
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.id = rs.getString("pessoa_id");
                this.nome = rs.getString("nome");
                //System.out.println(this.id);
                return true;
            }
            
            rs.close();
            ps.close();
            conexao.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        } catch(NullPointerException ex) {
            System.out.println("Campo vázio");
        }
        return false;
    }
    public String getId(){return this.id;}
    public String getNome(){return this.nome;}
}
