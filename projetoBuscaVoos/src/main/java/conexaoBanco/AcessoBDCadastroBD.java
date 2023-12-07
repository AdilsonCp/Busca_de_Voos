/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexaoBanco;
import controller.MeuCadastroController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import controller.MeuCadastroController;
import java.util.logging.Level;
import java.util.logging.Logger;
import pessoasDatas.Datas;


/**
 *
 * @author adils
 */
public class AcessoBDCadastroBD {
    
    private MeuCadastroController meuCadastro;
    
    public void setMeucadastro(MeuCadastroController meucadastros){
        this.meuCadastro = meucadastros;
    }
    
     private String nome;
     private String idPessoa;

    public boolean login (String email, String senha){
        Connection conexao = new BDConexaoMySQL().getConnection();
        
        String select = "select pessoa_id, nome from pessoas where "
                + "email = ? and senha = ? ;";
     try {
            PreparedStatement ps = conexao.prepareStatement(select);
            ps.setString(1, email);
            ps.setString(2, senha);

            //Executar a consulta e obter o resultado
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.idPessoa = rs.getString("pessoa_id");
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

    public String getNome() {
        return this.nome;
    }
    public String getIdPessoa() {
        return this.idPessoa;
    }
    
    //Verificar se não tem CPF já registrado
   public boolean selectCpf(String cpf){
        //Criar uma conexão com o banco de dados
        Connection conexao = new BDConexaoMySQL().getConnection();
        
        //Preparar a consulta
        String select =  "SELECT pessoa_id FROM pessoas where cpf= ?";

        try {
            PreparedStatement ps = conexao.prepareStatement(select);
            ps.setString(1, cpf);

            //Executar a consulta e obter o resultado
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

            rs.close();
            ps.close();
            conexao.close();
            
            return false;
     
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch(NullPointerException ex) {
            System.out.println("Campo vázio");
        }
        return false;
    }
   
   //Verificar se não tem Email já registrado
    public boolean selectEmai(String email){
        //Criar uma conexão com o banco de dados
        Connection conexao = new BDConexaoMySQL().getConnection();

        //Preparar a consulta
        String select =  "SELECT pessoa_id FROM pessoas where email= ?";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(select);
            ps.setString(1, email);


            //Executar a consulta e obter o resultado
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            rs.close();
            ps.close();
            conexao.close();

            return false;
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch(NullPointerException ex) {
            System.out.println("Campo vázio");
        }
        return false;
    }
    public void insertPessoa(String nome, String cpf, String genero, String dataNasc, String telefone, String cep, String endereco, 
            String numero, String complemento, String bairro, String cidade, String uf, String email, String senha){
       
        //Criar uma conexão com o banco de dados
       Connection conexao = new BDConexaoMySQL().getConnection(); 
        
       try {
            String sql = "INSERT INTO `cadastro`.`pessoas` (`nome`, `cpf`, `genero`, `data_nascimento`, `email`, `senha`, `telefone`, `endereco`, `cep`, `numero`, `complemento`, `bairro`, `cidade`, `uf`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setString(1, nome);
                preparedStatement.setString(2, cpf);
                preparedStatement.setString(3, genero);
                preparedStatement.setString(4, dataNasc);
                preparedStatement.setString(5, email);
                preparedStatement.setString(6, senha);
                preparedStatement.setString(7, telefone);
                preparedStatement.setString(8, endereco);
                preparedStatement.setString(9, cep);
                preparedStatement.setString(10, numero);
                if(complemento != null )preparedStatement.setString(11, complemento);
                else preparedStatement.setString(11, null);
                preparedStatement.setString(12, bairro);
                preparedStatement.setString(13, cidade);
                preparedStatement.setString(14, uf);
                
                int linhasAfetadas = preparedStatement.executeUpdate();
                    
                
                
 

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }
    
    public boolean selectCpfEmail(int idPessoa, String cpf){
        Connection conexao = new BDConexaoMySQL().getConnection();

        //Preparar a consulta
        String select =  "SELECT cpf FROM pessoas where cpf = ? AND pessoa_id <> ?;";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(select);
            ps.setString(1, cpf);
            ps.setString(2, Integer.toString(idPessoa));


            //Executar a consulta e obter o resultado
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            rs.close();
            ps.close();
            conexao.close();

            return false;
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch(NullPointerException ex) {
            System.out.println("Campo vázio");
        }
        return false; 
    }
     public boolean selectCpfEmail(String idPessoa, String email){
        Connection conexao = new BDConexaoMySQL().getConnection();

        //Preparar a consulta
        String select =  "SELECT email FROM pessoas where email = ? AND pessoa_id <> ?;";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(select);
            ps.setString(1, email);
            ps.setString(2, idPessoa);


            //Executar a consulta e obter o resultado
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            rs.close();
            ps.close();
            conexao.close();

            return false;
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch(NullPointerException ex) {
            System.out.println("Campo vázio");
        }
        return false; 
    }
     
 public void carregarPessoa(String id){
        Datas datas = new Datas();
 
       //Criar uma conexão com o banco de dados
       Connection conexao = new BDConexaoMySQL().getConnection();           
       /*String select = "select nome, cpf, genero, data_nascimento"
               + " from pessoas where pessoa_id = ?";*/
       
       String select = "select  nome, cpf, genero, data_nascimento,"
		+ " telefone,"
		+ " cep, endereco, numero, complemento, bairro, cidade, uf," 
		+ " email, senha from pessoas "
               + " where pessoa_id=? ;";

       try {
          PreparedStatement ps = conexao.prepareStatement(select);
          ps.setString(1, id);


           //Executar a consulta e obter o resultado
          ResultSet rs = ps.executeQuery();
          if (rs.next()) {
              String data = rs.getString("data_nascimento");
              String[] partes = data.split(" ");
              //separar data da hora
              String parteData = partes[0];
              String[] stringData = parteData.split("-");
              String ano = stringData[0];
              String mes = stringData[1];
              String dia = stringData[2];
              
              
              meuCadastro.carregarRegistro(rs.getString("nome"),
              rs.getString("cpf"),rs.getString("genero"),dia, datas.strMes(Integer.parseInt(mes)), ano,
              rs.getString("telefone"), rs.getString("cep"), rs.getString("endereco"),
              rs.getString("numero"), rs.getString("complemento"), rs.getString("bairro"),
              rs.getString("cidade"), rs.getString("uf"), rs.getString("email"),
              rs.getString("senha")
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
 
    public boolean atualizarDados(String idPessoa,String nome, String cpf, String genero, String dia, String mes, String ano,
                                    String telefone, String cep, String  endereco, String numero, String complemento,
                                    String bairro, String cidade, String uf,
                                    String email, String senha) {

        //Cria conexão com o banco de dados
        Connection conn = new BDConexaoMySQL().getConnection();

        try {
            String updateQuery = "UPDATE pessoas SET nome =?, cpf= ?, genero=?, data_nascimento=?, telefone=?,"
		+ " cep=?, endereco=?, numero=?, complemento=?, bairro=?, cidade=?, uf=?, email=?, senha=? "
                    + " where pessoa_id=?";
            PreparedStatement ps;
            ps = conn.prepareStatement(updateQuery);
            
            String dataNasc = ano+"-"+mes+"-"+dia+" 00:00:00";

            // Definir parâmetros
            ps.setString(1, nome);
            ps.setString(2, cpf);
            ps.setString(3, genero);
            ps.setString(4, dataNasc);
            ps.setString(5, telefone);
            ps.setString(6, cep);
            ps.setString(7, endereco);
            ps.setString(8, numero);
            ps.setString(9, complemento);
            ps.setString(10, bairro);
            ps.setString(11, cidade);
            ps.setString(12, uf);
            ps.setString(13, email);
            ps.setString(14, senha);
            ps.setString(15, idPessoa);
            

            // Executar a query
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(BDConexaoMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
}
