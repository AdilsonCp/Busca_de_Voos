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
    
    public List<Rotas> getListaRotas(){
    
        Connection conn = new BDConexaoMySQL().getConnection();

        String sql = "SELECT empresa,"
                + "date_format(data_horario_partida,\"%d/%m/%Y - %Hh:%mm\") as "
                + "data_horario_partida,pais_origem, cidade_origem, "
                + "pais_destino, cidade_destino,preco \n" +
"	FROM rota_aereas WHERE data_horario_partida >= utc_timestamp();";

        List<Rotas> list = new ArrayList<>();
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            
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
}
