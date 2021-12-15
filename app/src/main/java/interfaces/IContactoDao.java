package interfaces;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;
import java.util.List;

import domain.ContactoDTO;
import domain.UsuarioDTO;

/**
 *
 * @author WiltR
 */
public interface IContactoDao {

    public List<ContactoDTO> selectAll() throws SQLException;

    public ContactoDTO selectByUserId(int vIdUsuario) throws SQLException ;

    public ContactoDTO selectByContactoId(int vIdContacto) throws SQLException ;

    public long insertOne(ContactoDTO contacto) throws SQLException;

    public long deleteOne(ContactoDTO contacto) throws SQLException ;

    public long updateOne(ContactoDTO contacto) throws SQLException ;

}

