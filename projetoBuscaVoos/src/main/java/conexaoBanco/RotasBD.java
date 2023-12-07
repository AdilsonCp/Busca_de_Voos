/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rotas.Rotas;

/**
 *
 * @author adils
 */
public class RotasBD {
    
    //Retorna todos os paises
    public List<String> getPaises(){
        Connection conn = new BDConexaoMySQL().getConnection();

        String sql = "select pais from paises order by pais asc";
        
        List<String> list = new ArrayList<>();
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String paises = rs.getString("pais");
                list.add(paises);
            }
            rs.close();
            ps.close();
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return list;
    }  
    
    //Retorna as cidades com bases os paises
   public List<String> getCidade(String pais){
        Connection conn = new BDConexaoMySQL().getConnection();

        String sql = "select cidades.cidade from cidades inner join paises on cidades.paises_id_paises = paises.id_paises " +
            " where paises.pais = ?";
        
        List<String> list = new ArrayList<>();
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pais);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String cidade = rs.getString("cidades.cidade");
                list.add(cidade);
            }
            rs.close();
            ps.close();
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return list;
    }
   
   //Retorna os paises destinos com base no loca de partida(pais e cidade)
   public List<String> getPaisesDestino(String pais, String cidade){
        Connection conn = new BDConexaoMySQL().getConnection();

        String sql = "select distinct paisDestino.pais from rota_aereas inner join cidades on rota_aereas.cidades_origemid_cidades = cidades.id_cidades\n" +
                        "	join paises on rota_aereas.paisorigem_paises_id_paises = paises.id_paises\n" +
                        "       inner join paises  as paisDestino on rota_aereas.paisdestino_paises_id_paises = paisDestino.id_paises\n" +
                        "       where cidades.cidade = ? and paises.pais = ? and data_horario_partida >= utc_timestamp();";
        
        List<String> list = new ArrayList<>();
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cidade);
            ps.setString(2, pais);
            
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String paises = rs.getString("paisDestino.pais");
                list.add(paises);
            }
            rs.close();
            ps.close();
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return list;
    }
   
     public List<String> getCidadeDestino(String pais, String cidade, String paisDestino){
        Connection conn = new BDConexaoMySQL().getConnection();

        String sql = "select distinct cidadeDestino.cidade from rota_aereas inner join cidades on rota_aereas.cidades_origemid_cidades = cidades.id_cidades\n" +
                  "	join paises on rota_aereas.paisorigem_paises_id_paises = paises.id_paises\n" +
                  "     inner join paises  as paisDestino on rota_aereas.paisdestino_paises_id_paises = paisDestino.id_paises\n"
                + "     inner join cidades as cidadeDestino on rota_aereas.cidadesdestino_id_cidades = cidadeDestino.id_cidades" +
                  "     where cidades.cidade = ? and paises.pais = ? and paisDestino.pais = ? and data_horario_partida >= utc_timestamp();";
        
        List<String> list = new ArrayList<>();
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cidade);
            ps.setString(2, pais);
            ps.setString(3, paisDestino);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String cidadeDestino = rs.getString("cidadeDestino.cidade");
                list.add(cidadeDestino);
            }
            rs.close();
            ps.close();
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return list;
    }

    public List<Rotas> getListaRotas(String paisOri, String cidadeOrig, String paisDes, String cidadeDes, String valor, String data){
        
        Connection conn = new BDConexaoMySQL().getConnection();
        String clausula = null;
        String sql = null;
        if(data != null){ 
            clausula = " where cidades.cidade = ? and paises.pais = ? and data_horario_partida >= ? and data_horario_partida >= utc_timestamp();";   
        }
        else if (valor != null){ 
            clausula = "  where cidades.cidade = ? and paises.pais = ? and rota_aereas.valores <= ? and data_horario_partida >= utc_timestamp(); ";
        }
        else{
            clausula = " where cidades.cidade = ? and paises.pais = ? and  paisDestino.pais = ? and cidadeDestino.cidade = ? and data_horario_partida >= utc_timestamp(); ";
           
        }
        sql = "select rota_aereas.empresa, date_format(rota_aereas.data_horario_partida,\"%d/%m/%Y - %Hh:%mm\") as data_horario_partida, paises.pais, cidades.cidade, paisDestino.pais, cidadeDestino.cidade, rota_aereas.valores, rota_aereas.id_rotas_aereas\n" +
            " from rota_aereas inner join cidades on rota_aereas.cidades_origemid_cidades = cidades.id_cidades\n" +
            " inner join paises on rota_aereas.paisorigem_paises_id_paises = paises.id_paises\n" +
            " inner join cidades as cidadeDestino on rota_aereas.cidadesdestino_id_cidades = cidadeDestino.id_cidades\n" +
            " inner join paises  as paisDestino on rota_aereas.paisdestino_paises_id_paises = paisDestino.id_paises\n " +
                clausula;       
        
        List<Rotas> list = new ArrayList<>();
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cidadeOrig);
            ps.setString(2, paisOri);
            if(data != null){
                ps.setString(3, data);
                System.out.println(data);
            }
            else if (valor != null){
                ps.setString(3, valor);
            }
            else{
                System.out.println(data);
                ps.setString(3, paisDes);
                ps.setString(4, cidadeDes);
            }
            
           // ps.setString(5, precoData);*/
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String empresa = rs.getString("rota_aereas.empresa");
                String dataPartida = rs.getString("data_horario_partida");
                String paisOrigem = rs.getString("paises.pais");
                String cidadeOrigem = rs.getString("cidades.cidade");
                String paisDestino = rs.getString("paisDestino.pais");
                String cidadeDestino = rs.getString("cidadeDestino.cidade");
                double preco = rs.getDouble("rota_aereas.valores");
                String id = rs.getString("rota_aereas.id_rotas_aereas");
                Rotas r = new Rotas(empresa, dataPartida, paisOrigem, cidadeOrigem, paisDestino, cidadeDestino, preco,id);
                list.add(r);
            }
            rs.close();
            ps.close();
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return list;
    }
    
    //Retorna lista dos números dos Voos disponiveis 
    public List<String> getNumm(){
        Connection conn = new BDConexaoMySQL().getConnection();

        String sql = "select id_rotas_aereas from rota_aereas where  data_horario_partida >= utc_timestamp() order by id_rotas_aereas asc;";
        
        List<String> list = new ArrayList<>();
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String idvoo = rs.getString("id_rotas_aereas");
                list.add(idvoo);
            }
            rs.close();
            ps.close();
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return list;
    }  
    
    //Retorna lista dos números dos Voos disponiveis para cancelamento pertencente a um usuario 
    public List<String> getHistoricoNumVoo(String pessoaId){
        Connection conn = new BDConexaoMySQL().getConnection();

        String sql = "select rota_aereas.id_rotas_aereas \n" +
"	from rota_aereas inner join passagem on rota_aereas.id_rotas_aereas = passagem.poltrona_id_rotas_aereas\n" +
"    where rota_aereas.data_horario_partida > utc_timestamp() and passagem.pessoa_id = ? order by rota_aereas.id_rotas_aereas  asc ;";
        
        List<String> list = new ArrayList<>();
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pessoaId);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String idvoo = rs.getString("rota_aereas.id_rotas_aereas");
                list.add(idvoo);
            }
            rs.close();
            ps.close();
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return list;
    }

   public List<Rotas> getListaVoosEscolhidos(String idPessoa, String idVoo, boolean isTelaConfirma){
        
       String clausula = null;
       if(isTelaConfirma){
           clausula = "  where rota_aereas.id_rotas_aereas=?;";
       }
       else{
           if(idVoo == null) clausula  = "  inner join passagem on rota_aereas.id_rotas_aereas = passagem.poltrona_id_rotas_aereas\n where pessoa_id = ? order by rota_aereas.data_horario_partida asc;";
           else clausula = " inner join passagem on rota_aereas.id_rotas_aereas = passagem.poltrona_id_rotas_aereas\n   where pessoa_id = ? and rota_aereas.id_rotas_aereas=?;"; 
       } 
       
        Connection conn = new BDConexaoMySQL().getConnection();
        String sql = "select rota_aereas.empresa, date_format(rota_aereas.data_horario_partida,\"%d/%m/%Y - %Hh:%mm\") as data_horario_partida, rota_aereas.valores, cidades.cidade, paises.pais, cidadeDestino.cidade, paisDestino.pais, rota_aereas.id_rotas_aereas\n" +
                        "   from rota_aereas inner join cidades on rota_aereas.cidades_origemid_cidades = cidades.id_cidades\n" +
                        "   inner join paises on rota_aereas.paisorigem_paises_id_paises = paises.id_paises\n" +
                        "   inner join cidades as cidadeDestino on rota_aereas.cidadesdestino_id_cidades = cidadeDestino.id_cidades\n" +
                        "   inner join paises  as paisDestino on rota_aereas.paisdestino_paises_id_paises = paisDestino.id_paises\n" +
                                clausula;            
        
        
        List<Rotas> list = new ArrayList<>();
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            if(isTelaConfirma){
                ps.setString(1, idVoo);
            }
            else{
                ps.setString(1, idPessoa);
                if(idVoo != null )ps.setString(2, idVoo);
            }
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String empresa = rs.getString("rota_aereas.empresa");
                String dataPartida = rs.getString("data_horario_partida");
                String paisOrigem = rs.getString("paises.pais");
                String cidadeOrigem = rs.getString("cidades.cidade");
                String paisDestino = rs.getString("paisDestino.pais");
                String cidadeDestino = rs.getString("cidadeDestino.cidade");
                double preco = rs.getDouble("rota_aereas.valores");
                String id = rs.getString("rota_aereas.id_rotas_aereas");
                Rotas r = new Rotas(empresa, dataPartida, paisOrigem, cidadeOrigem, paisDestino, cidadeDestino, preco,id);
                list.add(r);
            }
            rs.close();
            ps.close();
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return list;
    }
   
    public String deletarPassagem(String idPessoa, String idVoo){
        //Criar uma conexão com o banco de dados
        String poltrona = null;
        Connection conexao = new BDConexaoMySQL().getConnection();
        
        //Preparar a consulta
        String select =  "select poltrona_id_poltrona from passagem where pessoa_id = ? and poltrona_id_rotas_aereas=? ";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(select);
            ps.setString(1, idPessoa);
            ps.setString(2, idVoo);


            //Executar a consulta e obter o resultado
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                poltrona = rs.getString("poltrona_id_poltrona");
                return poltrona;
                
            }
            rs.close();
            ps.close();
            conexao.close();
            
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch(NullPointerException ex) {
            System.out.println("Campo vázio");
        }
        return poltrona;
    }
    
    public void deletarPassagem1(String idPessoa, String idVoo){
            //Cria conexão com o banco de dados
        Connection conn = new BDConexaoMySQL().getConnection();

        //Prepara a consulta
        String sql = "delete from passagem where pessoa_id = ? and poltrona_id_rotas_aereas=?  ";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, idPessoa);
            stmt.setString(2, idVoo);

            //Executa e obtém o resultado
            stmt.execute();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(BDConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void atualizarPoltronas(String idPoltrona, String idVoo) {

        //Cria conexão com o banco de dados
        Connection conn = new BDConexaoMySQL().getConnection();

        try {
            String updateQuery = "update poltrona set statuss = 0 where id_poltrona= ? and id_rotas_aereas = ?";
            PreparedStatement ps;
            ps = conn.prepareStatement(updateQuery);
           // Definir parâmetros
            ps.setString(1, idPoltrona);
            ps.setString(2, idVoo);
            
            
            // Executar a query
            ps.executeUpdate();
            

        } catch (SQLException ex) {
            Logger.getLogger(BDConexaoMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public boolean getListPoltrona(String idVoo, String idPoltrona){
        Connection conn = new BDConexaoMySQL().getConnection();

        String sql = "select id_poltrona, statuss from poltrona where id_rotas_aereas = ? and id_poltrona = ?;";
        
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idVoo);
            ps.setString(2, idPoltrona);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String poltrona = rs.getString("id_poltrona");
                int  status= rs.getInt("statuss");
    
                
                if(status == 1) return true;
                //return status.equals('1');
            }
            if(!rs.next())return false;
          
           
            
            
            rs.close();
            ps.close();
            conn.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return false;
    }
    
    public void insertPoltrona(String idVoo,  String idPoltrona){
       
        //Criar uma conexão com o banco de dados
       Connection conexao = new BDConexaoMySQL().getConnection(); 
        
       try {
            String sql = "INSERT INTO `cadastro`.`poltrona` (`id_poltrona`, `id_rotas_aereas`, `statuss`) VALUES (?,?,?);";

            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setString(1, idPoltrona);
                preparedStatement.setString(2, idVoo);
                preparedStatement.setInt(3, 1);
            
                int linhasAfetadas = preparedStatement.executeUpdate();
 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }
    
    public void insertPassagem(String idPessoa, String idVoo,  String idPoltrona){
       
        //Criar uma conexão com o banco de dados
       Connection conexao = new BDConexaoMySQL().getConnection(); 
        
       try {
            String sql = "INSERT INTO `cadastro`.`passagem` (`pessoa_id`, `poltrona_id_poltrona`, `poltrona_id_rotas_aereas`) VALUES (?,?,?);";

            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setString(1, idPessoa);
                preparedStatement.setString(2, idPoltrona);
                preparedStatement.setString(3, idVoo);
            
                int linhasAfetadas = preparedStatement.executeUpdate();
 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }
}
