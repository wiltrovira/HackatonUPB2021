package interfaces;

import java.sql.SQLException;
import java.util.List;
import domain.MiHistorialDTO;

public interface IMiHistorialDao {

    public List<MiHistorialDTO> selectAll() throws SQLException;

    public MiHistorialDTO selectByHistoryId(int vIdHistoria) throws SQLException ;

     public long insertOne(MiHistorialDTO vMiHistoria) throws SQLException;

    public long deleteOne(MiHistorialDTO vMiHistoria) throws SQLException ;

    public long updateOne(MiHistorialDTO vMiHistoria) throws SQLException ;
}
