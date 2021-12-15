package interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author WiltR
 */
public interface IConexion {

    public Connection getConnection();

    public abstract void close(ResultSet rs);

    public abstract void close(Statement statement);

    public abstract void close(PreparedStatement statement);

    public abstract void close(Connection conn);
}
