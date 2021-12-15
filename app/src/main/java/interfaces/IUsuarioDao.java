package interfaces;

import domain.UsuarioDTO;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author WiltR
 */
public interface IUsuarioDao {

    public List<UsuarioDTO> selectAll() throws SQLException ;

    public UsuarioDTO selectById(int vIdUsuario) throws SQLException ;

    public UsuarioDTO selectByUsername(String vUsername) throws SQLException;

    public long insertOne(UsuarioDTO usuario) throws SQLException, NoSuchProviderException, NoSuchAlgorithmException;

    public long deleteOne(UsuarioDTO usuario) throws SQLException ;

    public long updateOne(UsuarioDTO usuario) throws SQLException ;

    public boolean checkUsername() throws SQLException ;

    public boolean checkUsernamePassword() throws SQLException, NoSuchProviderException, NoSuchAlgorithmException;
}
