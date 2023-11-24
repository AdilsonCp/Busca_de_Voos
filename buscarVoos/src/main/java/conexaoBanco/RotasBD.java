package conexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import projeto.buscarvoos.Rotas;

/**
 *
 * @author adils
 */
public class RotasBD {
    
    
    public List<Rotas> getListaRotas(String paisOri, String cidadeOrig, String paisDes, String cidadeDes, String precoData){
        
        Connection conn = new BDConexaoMySQL().getConnection();

        String sql = "SELECT empresa,"
                + "date_format(data_horario_partida,\"%d/%m/%Y - %Hh:%mm\") as "
                + "data_horario_partida,pais_origem, cidade_origem, "
                + "pais_destino, cidade_destino,preco \n" +
"	FROM rota_aereas WHERE data_horario_partida >= utc_timestamp() AND "
                + "pais_origem=? ;" ;//AND cidade_origem=? AND pais_destino=? AND cidade_destino=? AND preco <=?;";

        List<Rotas> list = new ArrayList<>();
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, paisOri);
            /*ps.setString(2, cidadeOrig);
            ps.setString(3, paisDes);
            ps.setString(4, cidadeDes);
            ps.setString(5, precoData);*/
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String empresa = rs.getString("empresa");
                String dataPartida = rs.getString("data_horario_partida");
                String paisOrigem = rs.getString("pais_origem");
                String cidadeOrigem = rs.getString("cidade_origem");
                String paisDestino = rs.getString("pais_destino");
                String cidadeDestino = rs.getString("cidade_destino");
                double preco = rs.getDouble("preco");
                Rotas r = new Rotas(empresa, dataPartida, paisOrigem, cidadeOrigem, paisDestino, cidadeDestino, preco);
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
    
    public List<String> getCidade(String pais){
        Connection conn = new BDConexaoMySQL().getConnection();

        String sql = "select cidades.cidade from cidades inner join paises on cidades.id_paises = paises.id_paises " +
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
}
