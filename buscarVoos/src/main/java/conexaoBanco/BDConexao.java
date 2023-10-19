package conexaoBanco;

import java.sql.Connection;

/**
 *
 * @author adils
 */
public abstract class BDConexao{
    protected Connection con;
    protected String servidor;
    protected String bd;
    protected String usuario;
    protected String senha;
    protected String driver;
    protected int porta;

    public abstract Connection getConnection();

    public abstract  String getURL();
}
