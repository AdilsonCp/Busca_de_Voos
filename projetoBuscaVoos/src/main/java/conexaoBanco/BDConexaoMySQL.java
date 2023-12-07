/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author adils
 */
public class BDConexaoMySQL extends BDConexao{
    
    public BDConexaoMySQL() {

        this.driver = "com.mysql.cj.jdbc.Driver";
        this.porta = 3306;
        this.servidor = "localhost";
        this.bd = "cadastro";
        this.usuario = "root";
        this.senha = "130521";
        //ifsp
    }

    @Override
    public Connection getConnection() {

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(getURL(), usuario, senha);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BDConexaoMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;

    }

    @Override
    public String getURL() {
        return "jdbc:mysql://" + this.servidor + ":" + this.porta + "/" + this.bd
                + "?useTimezone=true&serverTimezone=UTC";
    }    
}
