package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author WiltR
 */
public class ConexionMySQL extends ConexionDB {

    //Datasource para el pool de conexiones
//    private static final BasicDataSource mBasicPoolDataSource = new BasicDataSource();
    private static final String JDBC_URL = "jdbc:mysql://altar26.supremepanel26.com:3306/cosmohos_hackatonupb2021?useSSL=true";
    private static final String JDBC_USERNAME = "cosmohos_hackatonupb2021app";
    private static final String JDBC_PASSWORD = "Bs~0A6OJRIFV";

    /**
     * Constructor
     */
    private ConexionMySQL() {
    }

    /**
     * Crea la conexión a la base de datos
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            //Si la conexión es nula, crea una nueva conexión
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(ConexionMySQL.JDBC_URL, ConexionMySQL.JDBC_USERNAME, ConexionMySQL.JDBC_PASSWORD);  //Crea el pool de conexiones
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
        return conn;
    }

    /**
     * Cierra el ResultSet
     *
     * @param rs
     */
    public static void close(ResultSet rs) {
        try {
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Cierra el Statement
     *
     * @param statement
     */
    public static void close(Statement statement) {
        try {
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Cierra el PreparedStatement
     *
     * @param statement
     */
    public static void close(PreparedStatement statement) {
        try {
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Cierra la conexión
     *
     * @param conn
     */
    public static void close(Connection conn) {
        try {
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
