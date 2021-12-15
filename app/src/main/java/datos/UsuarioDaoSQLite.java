package datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utilities.BaseDatos;
import Utilities.PasswordSalt;
import application.HackatonUPB2021Application;
import domain.UsuarioBuilder;
import domain.UsuarioDTO;
import interfaces.IUsuarioDao;

/**
 * Clase para conectar con la base de datos SQLite
 */
public class UsuarioDaoSQLite extends SQLiteOpenHelper implements IUsuarioDao {

    private UsuarioDTO mUsuario;



    public UsuarioDaoSQLite(@Nullable Context context, UsuarioDTO vUsuario) {
        super(context, HackatonUPB2021Application.getDbName(), null, 1);
        this.mUsuario = vUsuario;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        BaseDatos.crearBaseDatos(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public UsuarioDTO getUsuario() {
        return this.mUsuario;
    }

    public void setUsuario(UsuarioDTO vUsuario) {
        this.mUsuario = vUsuario;
    }


    /**
     * @return
     * @throws SQLException
     */
    @Override
    public List<UsuarioDTO> selectAll() throws SQLException {

        //Tiene acceso a la base de datos para escritura
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "SELECT " +
                BaseDatos.COL_USERS_USERID +
                ", " + BaseDatos.COL_USERS_USERNAME +
                ", " + BaseDatos.COL_USERS_PASSWORD +
                ", " + BaseDatos.COL_USERS_SALT +
                ", " + BaseDatos.COL_USERS_NOMBRE +
                ", " + BaseDatos.COL_USERS_APELLIDO +
                ", " + BaseDatos.COL_USERS_CORREO +
                ", " + BaseDatos.COL_USERS_TELEFONO +
                " FROM " + BaseDatos.TBL_USERS;

        //Ejecuta la consulta
        Cursor cursor = db.rawQuery(queryString, null);

        UsuarioDTO usuario = null;
        List<UsuarioDTO> usuarios = new ArrayList<>();

        //Recorre el cursor
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int idUsuario = cursor.getInt(0); //id del usuario
                String username = cursor.getString(1); //username
                String password = cursor.getString(2); //password
                String salt = cursor.getString(3); //salt
                String nombre = cursor.getString(4); //nombre
                String apellido = cursor.getString(5); //apellido
                String correo = cursor.getString(6); //correo electrónico
                String telefono = cursor.getString(7); //teléfono

                UsuarioBuilder usuarioBuilder = new UsuarioBuilder();
                usuarioBuilder
                        .setIdUsuario(idUsuario)
                        .setUserName(username)
                        .setPassword(password)
                        .setSalt(salt)
                        .setNombre(nombre)
                        .setApellido(apellido)
                        .setCorreoElectronico(correo)
                        .setTelefono(telefono);

                usuario = usuarioBuilder.getUsuarioDTO();
                usuarios.add(usuario);
            }
        }

        //Cerrar el cursor y la conexión a la base de datos
        cursor.close();
        db.close();
        return usuarios;

    }

    /**
     * Devuelve el primer objeto de tipo usuario que coincida con el id
     *
     * @param vIdUsuario
     * @return
     * @throws SQLException
     */
    @Override
    public UsuarioDTO selectById(int vIdUsuario) throws SQLException {

        //Tiene acceso a la base de datos para escritura
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "SELECT " +
                BaseDatos.COL_USERS_USERID +
                ", " + BaseDatos.COL_USERS_USERNAME +
                ", " + BaseDatos.COL_USERS_PASSWORD +
                ", " + BaseDatos.COL_USERS_SALT +
                ", " + BaseDatos.COL_USERS_NOMBRE +
                ", " + BaseDatos.COL_USERS_APELLIDO +
                ", " + BaseDatos.COL_USERS_CORREO +
                ", " + BaseDatos.COL_USERS_TELEFONO +
                " FROM " + BaseDatos.TBL_USERS +
                " WHERE " + BaseDatos.COL_USERS_USERID +
                " = " + "\'" + vIdUsuario + "\'";

        //Ejecuta la consulta
        Cursor cursor = db.rawQuery(queryString, null);

        UsuarioDTO usuario = null;
        //Recorre el cursor
        if (cursor.getCount() > 0) {
            cursor.moveToFirst(); //Selecciona el primero registro

            int idUsuario = cursor.getInt(0); //id del usuario
            String username = cursor.getString(1); //username
            String password = cursor.getString(2); //password
            String salt = cursor.getString(3); //salt
            String nombre = cursor.getString(4); //nombre
            String apellido = cursor.getString(5); //apellido
            String correo = cursor.getString(6); //correo electrónico
            String telefono = cursor.getString(7); //teléfono

            UsuarioBuilder usuarioBuilder = new UsuarioBuilder();
            usuarioBuilder
                    .setIdUsuario(idUsuario)
                    .setUserName(username)
                    .setPassword(password)
                    .setSalt(salt)
                    .setNombre(nombre)
                    .setApellido(apellido)
                    .setCorreoElectronico(correo)
                    .setTelefono(telefono);

            usuario = usuarioBuilder.getUsuarioDTO();
        }

        //Cerrar el cursor y la conexión a la base de datos
        cursor.close();
        db.close();
        return usuario;
    }

    /**
     * Devuelve el primer objeto de tipo usuario que coincida con el Username
     *
     * @param vUsername nombre de usuario a buscar
     * @return
     * @throws SQLException
     */
    @Override
    public UsuarioDTO selectByUsername(String vUsername) throws SQLException {

        //Tiene acceso a la base de datos para escritura
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "SELECT " +
                BaseDatos.COL_USERS_USERID +
                ", " + BaseDatos.COL_USERS_USERNAME +
                ", " + BaseDatos.COL_USERS_PASSWORD +
                ", " + BaseDatos.COL_USERS_SALT +
                ", " + BaseDatos.COL_USERS_NOMBRE +
                ", " + BaseDatos.COL_USERS_APELLIDO +
                ", " + BaseDatos.COL_USERS_CORREO +
                ", " + BaseDatos.COL_USERS_TELEFONO +
                " FROM " + BaseDatos.TBL_USERS +
                " WHERE " + BaseDatos.COL_USERS_USERNAME +
                " = " + "\'" + vUsername + "\'";

        //Ejecuta la consulta
        Cursor cursor = db.rawQuery(queryString, null);

        UsuarioDTO usuario = null;

        //Recorre el cursor
        if (cursor.getCount() > 0) {
            cursor.moveToFirst(); //Selecciona el primer registro

            int idUsuario = cursor.getInt(0); //id del usuario
            String username = cursor.getString(1); //username
            String password = cursor.getString(2); //password
            String salt = cursor.getString(3); //salt
            String nombre = cursor.getString(4); //nombre
            String apellido = cursor.getString(5); //apellido
            String correo = cursor.getString(6); //correo electrónico
            String telefono = cursor.getString(7); //teléfono

            UsuarioBuilder usuarioBuilder = new UsuarioBuilder();
            usuarioBuilder
                    .setIdUsuario(idUsuario)
                    .setUserName(username)
                    .setPassword(password)
                    .setSalt(salt)
                    .setNombre(nombre)
                    .setApellido(apellido)
                    .setCorreoElectronico(correo)
                    .setTelefono(telefono);

            usuario = usuarioBuilder.getUsuarioDTO();

        }

        //Cerrar el cursor y la conexión a la base de datos
        cursor.close();
        db.close();
        return usuario;
    }


    /**
     * Crea un nuevo usuario en la base de datos
     *
     * @return
     */
    @Override
    public long insertOne(UsuarioDTO vUsuarioDTO) throws NoSuchProviderException, NoSuchAlgorithmException {

        //Tiene acceso a la base de datos para escritura
        SQLiteDatabase db = this.getWritableDatabase();

        //Genera el Salt y el password seguro (Hass)
        String newSalt = PasswordSalt.getSalt();
        String securePassword = PasswordSalt.getSecurePassword(vUsuarioDTO.getPassword(), newSalt);

        //Define los valores de cada campo
        ContentValues contentValues = new ContentValues();
        contentValues.put(BaseDatos.COL_USERS_USERNAME, vUsuarioDTO.getUserName());
        contentValues.put(BaseDatos.COL_USERS_PASSWORD, securePassword);
        contentValues.put(BaseDatos.COL_USERS_SALT, newSalt);

        //Inserta el registro
        long insert = db.insert(BaseDatos.TBL_USERS, null, contentValues);
        db.close(); //cierra la conexión
        //Valida el número de registros insertados

        return insert;
    }

    /**
     * @param usuario
     * @return
     * @throws SQLException
     */
    @Override
    public long deleteOne(UsuarioDTO usuario) throws SQLException {
        return 0;
    }

    /**
     * Actualiza un registro del usuario
     * @param vUsuario
     * @return
     * @throws SQLException
     */
    @Override
    public long updateOne(UsuarioDTO vUsuario) throws SQLException {

        //Tiene acceso a la base de datos para escritura
        SQLiteDatabase db = this.getWritableDatabase();

//        UsuarioDTO usuario = this.selectById(vUsuario.getIdUsuario());

        String whereClause =
                BaseDatos.COL_USERS_USERNAME +
                " = ?";

        //Define los valores de cada campo
        ContentValues contentValues = new ContentValues();
//        contentValues.put(BaseDatos.COL_USERS_USERNAME, vUsuario.getNombre());
        contentValues.put(BaseDatos.COL_USERS_NOMBRE, vUsuario.getNombre());
        contentValues.put(BaseDatos.COL_USERS_APELLIDO, vUsuario.getApellido());
        contentValues.put(BaseDatos.COL_USERS_CORREO, vUsuario.getCorreoElectronico());
        contentValues.put(BaseDatos.COL_USERS_TELEFONO, vUsuario.getTelefono());

        //actualiza el registro

        long update = db.update(BaseDatos.TBL_USERS, contentValues, whereClause, new String[]{vUsuario.getUserName()});
        //Valida el número de registros insertados

        db.close();
        return update;
    }

    /**
     * @return
     * @throws SQLException
     */
    @Override
    public boolean checkUsername() throws SQLException {

        //Tiene acceso a la base de datos para escritura
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "SELECT " +
                BaseDatos.COL_USERS_USERNAME +
                " FROM " + BaseDatos.TBL_USERS +
                " WHERE " + BaseDatos.COL_USERS_USERNAME +
                " = " + "\'" + this.mUsuario.getUserName() + "\'";

        Cursor cursor = db.rawQuery(queryString, null);

        // Si no tiene registros, devuelve 0
        if (cursor.getCount() == 0) {
            cursor.close();
            db.close();

            return false;
        }
        cursor.close();
        db.close();

        return true;
    }

    /**
     * Valida si la combinación de usuario y password es correcta
     *
     * @return
     */
    @Override
    public boolean checkUsernamePassword() throws NoSuchProviderException, NoSuchAlgorithmException {

        //Tiene acceso a la base de datos para escritura
        SQLiteDatabase db = this.getReadableDatabase();

        //Datos suministrados por el usuario en pantalla
        String usuarioActual = this.mUsuario.getUserName();
        String passwordActual = this.mUsuario.getPassword();

        //Busca el usuario en la base de datos
        String queryString = "SELECT " +
                BaseDatos.COL_USERS_USERID +
                ", " + BaseDatos.COL_USERS_USERNAME +
                ", " + BaseDatos.COL_USERS_PASSWORD +
                ", " + BaseDatos.COL_USERS_SALT +
                " FROM " + BaseDatos.TBL_USERS +
                " WHERE " + BaseDatos.COL_USERS_USERNAME +
                " = " + "\'" + this.mUsuario.getUserName() + "\'";

        try (Cursor cursor = db.rawQuery(queryString, null)) {

            //Recorre el cursor para hacer la comparación
            if (cursor.getCount() > 0) {
                cursor.moveToNext(); //Solo utiliza el primer registro

                //Datos recuperados de la base de datos
                int idUsuario = cursor.getInt(0); //id del usuario
                String username = cursor.getString(1); //username
                String password_hash = cursor.getString(2); //password
                String salt = cursor.getString(3); //salt

                //Genera un password cifrado haciendo uso del salt del usuario
                String presuntoPassword = PasswordSalt.getSecurePassword(passwordActual, salt);

                cursor.close();
                db.close();

                //Si el presuntoPassword es igual al password real
                if (username.equals(usuarioActual) && password_hash.equals(presuntoPassword)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                cursor.close();
                db.close();
                return false;
            }
        }
    }

}

