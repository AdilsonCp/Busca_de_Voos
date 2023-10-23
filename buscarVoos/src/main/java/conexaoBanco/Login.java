package conexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author adils
 */
public class Login {
    
    public boolean login(String email, String senha) {

        //Criar uma conexão com o banco de dados
        Connection conexao = new BDConexaoMySQL().getConnection();

        //Preparar a consulta
        String select = "SELECT * FROM credenciais WHERE email = ? "
                + "AND senha = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(select);
            ps.setString(1, email);
            ps.setString(2, senha);

            //Executar a consulta e obter o resultado
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String u = rs.getString("email");
                String s = rs.getString("senha");
                System.out.println("email: " + u);
                System.out.println("senha: " + s);
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
}
