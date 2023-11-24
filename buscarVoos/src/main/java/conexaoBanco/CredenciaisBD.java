package conexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import projeto.buscarvoos.CredenciaisController;


/**
 *
 * @author adils
 */
public class CredenciaisBD {
    
    private String pessoa_id;
    
    private CredenciaisController cController;
    
    public void setCredenciaisController(CredenciaisController cController) {
        this.cController = cController;
    }

    
    
    public boolean selectCpf(String cpf){
        this.pessoa_id = null;
    
        
        //Criar uma conexão com o banco de dados
        Connection conexao = new BDConexaoMySQL().getConnection();
        
        //Preparar a consulta
        String select =  "SELECT pessoa_id FROM pessoas where cpf= ?";
  
        
        try {
            PreparedStatement ps = conexao.prepareStatement(select);
            ps.setString(1, cpf);


            //Executar a consulta e obter o resultado
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                this.pessoa_id = rs.getString("pessoa_id");
            }

            
            rs.close();
            ps.close();
            conexao.close();

            if(this.pessoa_id != null)return true;//id existe
            else return false;//id não existe
     
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch(NullPointerException ex) {
            System.out.println("Campo vázio");
        }
        return false;
    }
    
    public boolean selectEmai(String email){
        String IsEmail = null;
        //Criar uma conexão com o banco de dados
        Connection conexao = new BDConexaoMySQL().getConnection();

        //Preparar a consulta
        String select =  "SELECT pessoa_id FROM credenciais where email= ?";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(select);
            ps.setString(1, email);


            //Executar a consulta e obter o resultado
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IsEmail = rs.getString("pessoa_id");
                //System.out.println("email: " + IsEmail+" Já cadastrado");
            }
            
            rs.close();
            ps.close();
            conexao.close();

            if(IsEmail != null)return true;//email existe
            else return false;//email não existe
     
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch(NullPointerException ex) {
            System.out.println("Campo vázio");
        }
        return false;
    }
    
    public void insertPessoa(String nome, String cpf, String genero, String dataNasc){
       //Criar uma conexão com o banco de dados
       Connection conexao = new BDConexaoMySQL().getConnection(); 
        
       try {
            String sql = "INSERT INTO pessoas (nome, cpf, genero, data_nascimento) VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setString(1, nome);
                preparedStatement.setString(2, cpf);
                preparedStatement.setString(3, genero);
                preparedStatement.setString(4, dataNasc);

                int linhasAfetadas = preparedStatement.executeUpdate();
                    
                /*if (linhasAfetadas > 0) {
                    System.out.println("Inserção da tabela Pessoa realizada com sucesso!");
                } else System.out.println("Falha na inserção tabela Pessoa.");*/
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        selectCpf(cpf);
        
    }
    
    public void insertEndereco(String cep, String endereco, String numero, String complemento, String bairro, String cidade, String uf){
       //Criar uma conexão com o banco de dados
       Connection conexao = new BDConexaoMySQL().getConnection(); 
        
       try {
            String sql = "insert into endereco(pessoa_id, cep, endereco, numero, complemento, bairro, cidade, uf ) \n" +
"	               values (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setString(1, this.pessoa_id);
                preparedStatement.setString(2, cep);
                preparedStatement.setString(3, endereco);
                preparedStatement.setString(4, numero);
                if(complemento != null)preparedStatement.setString(5, complemento);
                else preparedStatement.setString(5, null);
                preparedStatement.setString(6, bairro);
                preparedStatement.setString(7, cidade);
                preparedStatement.setString(8, uf);

                int linhasAfetadas = preparedStatement.executeUpdate();
                    
                /*if (linhasAfetadas > 0) {
                    System.out.println("Inserção da tabela Endereco realizada com sucesso!");
                } else System.out.println("Falha na inserção tabela Endereco.");*/
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void insertCredenciais(String email, String senha){
       //Criar uma conexão com o banco de dados
       Connection conexao = new BDConexaoMySQL().getConnection(); 
        
       try {
            String sql = "insert into credenciais(pessoa_id, email, senha) values (?, ?, ?)";

            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setString(1, this.pessoa_id);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, senha);

                int linhasAfetadas = preparedStatement.executeUpdate();
                    
                /*if (linhasAfetadas > 0) {
                    System.out.println("Inserção da tabela credenciais realizada com sucesso!");
                } else System.out.println("Falha na inserção tabela credenciais.");*/
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }
    
    public void insertContato(String email, String telefone){
               //Criar uma conexão com o banco de dados
       Connection conexao = new BDConexaoMySQL().getConnection(); 
        
       try {
            String sql = "insert into contato(pessoa_id, telefone, email) values (?, ?, ?)";

            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setString(1, this.pessoa_id);
                preparedStatement.setString(2, telefone);
                preparedStatement.setString(3, email);
                

                int linhasAfetadas = preparedStatement.executeUpdate();
                    
                /*if (linhasAfetadas > 0) {
                    System.out.println("Inserção da tabela contato realizada com sucesso!");
                } else System.out.println("Falha na inserção tabela contato.");*/
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void carregarPessoa(String id){
        this.pessoa_id = id;
 
       //Criar uma conexão com o banco de dados
       Connection conexao = new BDConexaoMySQL().getConnection();           
       /*String select = "select nome, cpf, genero, data_nascimento"
               + " from pessoas where pessoa_id = ?";*/
       
       String select = "select  pessoas.nome, pessoas.cpf, pessoas.genero, pessoas.data_nascimento,"
		+ " contato.telefone,"
		+ " endereco.cep, endereco.endereco, endereco.numero, endereco.complemento, endereco.bairro, endereco.cidade, endereco.uf," 
		+ " credenciais.email, credenciais.senha "
		+ " from pessoas inner join contato on pessoas.pessoa_id = contato.pessoa_id "
					 + " inner join endereco on pessoas.pessoa_id = endereco.pessoa_id "
					 + " inner join credenciais on pessoas.pessoa_id = credenciais.pessoa_id "
                     +" where pessoas.pessoa_id=? ";

       try {
          PreparedStatement ps = conexao.prepareStatement(select);
          ps.setString(1, this.pessoa_id);


           //Executar a consulta e obter o resultado
          ResultSet rs = ps.executeQuery();
          if (rs.next()) {
              String data = rs.getString("pessoas.data_nascimento");
              String[] partes = data.split(" ");
              //separar data da hora
              String parteData = partes[0];
              String[] stringData = parteData.split("-");
              String ano = stringData[0];
              String mes = stringData[1];
              String dia = stringData[2];
              
              
              cController.carregarRegistro(rs.getString("pessoas.nome"),
              rs.getString("pessoas.cpf"),rs.getString("pessoas.genero"),dia, mes, ano,
              rs.getString("contato.telefone"), rs.getString("endereco.cep"), rs.getString("endereco.endereco"),
              rs.getString("endereco.numero"), rs.getString("endereco.complemento"), rs.getString("endereco.bairro"),
              rs.getString("endereco.cidade"), rs.getString("endereco.uf"), rs.getString("credenciais.email"),
              rs.getString("credenciais.senha")
              );             
          }

          rs.close();
          ps.close();
          conexao.close();

          } catch (SQLException ex) {
              System.out.println(ex);
          } catch(NullPointerException ex) {
              System.out.println(ex);
          }                        
    }
    
    
    
       
    
    
}
