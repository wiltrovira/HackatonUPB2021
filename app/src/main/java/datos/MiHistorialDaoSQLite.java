package datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utilities.BaseDatos;
import application.HackatonUPB2021Application;

import domain.MiHistorialBuilder;
import domain.MiHistorialDTO;
import interfaces.IMiHistorialDao;

public class MiHistorialDaoSQLite extends SQLiteOpenHelper implements IMiHistorialDao {


    private MiHistorialDTO mMiHistorial;


    public MiHistorialDaoSQLite(@Nullable Context context, MiHistorialDTO vMiHistorial) {
        super(context, HackatonUPB2021Application.getDbName(), null, 1);
        this.mMiHistorial = vMiHistorial;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        BaseDatos.crearBaseDatos(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public MiHistorialDTO getMiHistorial() {
        return this.mMiHistorial;
    }

    public void setContacto(MiHistorialDTO vMiHistorial) {
        this.mMiHistorial = vMiHistorial;
    }


    /**
     * @return
     * @throws SQLException
     */
    @Override
    public List<MiHistorialDTO> selectAll() throws SQLException {

        //Tiene acceso a la base de datos para escritura
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "SELECT " +
                BaseDatos.COL_HISTORY_HISTORYID +
                ", " + BaseDatos.COL_HISTORY_USERID +
                ", " + BaseDatos.COL_HISTORY_FECHA_REPORTE +
                ", " + BaseDatos.COL_HISTORY_UBICACION +
                ", " + BaseDatos.COL_HISTORY_UBICACION_DESCRIPCION +
                ", " + BaseDatos.COL_HISTORY_ESTADO_SALUD +
                ", " + BaseDatos.COL_HISTORY_CATEGORIA_EVENTO +
                " FROM " + BaseDatos.TBL_HISTORY;

        //Ejecuta la consulta
        Cursor cursor = db.rawQuery(queryString, null);

        MiHistorialDTO miHistoria = null;
        List<MiHistorialDTO> miHistoriaList = new ArrayList<>();

        //Recorre el cursor
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int idHistoria = cursor.getInt(0); //id del contacto
                int idUsuario = cursor.getInt(1); //id del usuario
                long fechaReporte = cursor.getLong(2); //nombre
                String ubicacion = cursor.getString(3); //apellido
                String ubicacionDescripcion = cursor.getString(4); //correo electrónico
                int estadoSalud = cursor.getInt(5); //teléfono
                String categoriaEvento = cursor.getString(6); //relacion

                MiHistorialBuilder miHistorialBuilder = new MiHistorialBuilder();
                miHistorialBuilder
                        .setIdHistoria(idHistoria)
                        .setIdUsuario(idUsuario)
                        .setFechaReporte(fechaReporte)
                        .setUbicacion(ubicacion)
                        .setUbicacionDescripcion(ubicacionDescripcion)
                        .setEstadoSalud(estadoSalud)
                        .setCategoriaEvento(categoriaEvento);

                miHistoria = miHistorialBuilder.getMiHistorialDTO();
                miHistoriaList.add(miHistoria);
            }
        }

        //Cerrar el cursor y la conexión a la base de datos
        cursor.close();
        db.close();
        return miHistoriaList;

    }

    /**
     * Devuelve el primer objeto de tipo usuario que coincida con el id
     *
     * @param vIdHistoria
     * @return
     * @throws SQLException
     */
    @Override
    public MiHistorialDTO selectByHistoryId(int vIdHistoria) throws SQLException {

        //Tiene acceso a la base de datos para escritura
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "SELECT " +
                BaseDatos.COL_HISTORY_HISTORYID +
                ", " + BaseDatos.COL_HISTORY_USERID +
                ", " + BaseDatos.COL_HISTORY_FECHA_REPORTE +
                ", " + BaseDatos.COL_HISTORY_UBICACION +
                ", " + BaseDatos.COL_HISTORY_UBICACION_DESCRIPCION +
                ", " + BaseDatos.COL_HISTORY_ESTADO_SALUD +
                ", " + BaseDatos.COL_HISTORY_CATEGORIA_EVENTO +
                " FROM " + BaseDatos.TBL_HISTORY +
                " WHERE " + BaseDatos.COL_HISTORY_HISTORYID +
                " = " + "\'" + vIdHistoria + "\'";

        //Ejecuta la consulta
        Cursor cursor = db.rawQuery(queryString, null);

        MiHistorialDTO miHistorialDTO = null;
        //Recorre el cursor
        if (cursor.getCount() > 0) {
            cursor.moveToFirst(); //Selecciona el primero registro

            int idHistoria = cursor.getInt(0); //id del contacto
            int idUsuario = cursor.getInt(1); //id del usuario
            long fechaReporte = cursor.getLong(2); //nombre
            String ubicacion = cursor.getString(3); //apellido
            String ubicacionDescripcion = cursor.getString(4); //correo electrónico
            int estadoSalud = cursor.getInt(5); //teléfono
            String categoriaEvento = cursor.getString(6); //relacion

            MiHistorialBuilder miHistorialBuilder = new MiHistorialBuilder();
            miHistorialBuilder
                    .setIdHistoria(idHistoria)
                    .setIdUsuario(idUsuario)
                    .setFechaReporte(fechaReporte)
                    .setUbicacion(ubicacion)
                    .setUbicacionDescripcion(ubicacionDescripcion)
                    .setEstadoSalud(estadoSalud)
                    .setCategoriaEvento(categoriaEvento);

            miHistorialDTO = miHistorialBuilder.getMiHistorialDTO();
        }

        //Cerrar el cursor y la conexión a la base de datos
        cursor.close();
        db.close();
        return miHistorialDTO;
    }


    /**
     * Crea un nuevo evento en la base de datos
     *
     * @return
     */
    @Override
    public long insertOne(MiHistorialDTO vMiHistorialDTO) throws SQLException{

        //Tiene acceso a la base de datos para escritura
        SQLiteDatabase db = this.getWritableDatabase();

        //Define los valores de cada campo
        ContentValues contentValues = new ContentValues();
        contentValues.put(BaseDatos.COL_HISTORY_USERID, vMiHistorialDTO.getIdUsuario());
        contentValues.put(BaseDatos.COL_HISTORY_FECHA_REPORTE, vMiHistorialDTO.getFechaReporte());
        contentValues.put(BaseDatos.COL_HISTORY_UBICACION, vMiHistorialDTO.getUbicacion());
        contentValues.put(BaseDatos.COL_HISTORY_UBICACION_DESCRIPCION, vMiHistorialDTO.getUbicacionDescripcion());
        contentValues.put(BaseDatos.COL_HISTORY_ESTADO_SALUD, vMiHistorialDTO.getEstadoSalud());
        contentValues.put(BaseDatos.COL_HISTORY_CATEGORIA_EVENTO, vMiHistorialDTO.getCategoriaEvento());

        //Inserta el registro
        long insert = db.insert(BaseDatos.TBL_HISTORY, null, contentValues);
        db.close(); //cierra la conexión
        //Valida el número de registros insertados

        return insert;
    }

    /**
     * Actualiza los datos de una historia
     *
     * @param vMiHistorialDTO
     * @return
     * @throws SQLException
     */
    @Override
    public long deleteOne(MiHistorialDTO vMiHistorialDTO) throws SQLException {
        return 0;
    }

    /**
     * Actualiza un registro de un contacto
     *
     * @param vMiHistorialDTO
     * @return
     * @throws SQLException
     */
    @Override
    public long updateOne(MiHistorialDTO vMiHistorialDTO) throws SQLException {

        //Tiene acceso a la base de datos para escritura
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause =
                BaseDatos.COL_HISTORY_HISTORYID +
                        " = ?";

        //Define los valores de cada campo
        ContentValues contentValues = new ContentValues();
        contentValues.put(BaseDatos.COL_HISTORY_USERID, vMiHistorialDTO.getIdUsuario());
        contentValues.put(BaseDatos.COL_HISTORY_FECHA_REPORTE, vMiHistorialDTO.getFechaReporte());
        contentValues.put(BaseDatos.COL_HISTORY_UBICACION, vMiHistorialDTO.getUbicacion());
        contentValues.put(BaseDatos.COL_HISTORY_UBICACION_DESCRIPCION, vMiHistorialDTO.getUbicacionDescripcion());
        contentValues.put(BaseDatos.COL_HISTORY_ESTADO_SALUD, vMiHistorialDTO.getEstadoSalud());
        contentValues.put(BaseDatos.COL_HISTORY_CATEGORIA_EVENTO, vMiHistorialDTO.getCategoriaEvento());


        //actualiza el registro

        long update = db.update(BaseDatos.TBL_HISTORY, contentValues, whereClause, new String[]{String.valueOf(vMiHistorialDTO.getIdHistoria())});
        //Valida el número de registros insertados

        db.close();
        return update;
    }
}
