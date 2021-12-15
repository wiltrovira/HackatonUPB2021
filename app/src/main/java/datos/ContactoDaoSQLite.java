package datos;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utilities.BaseDatos;
import application.HackatonUPB2021Application;
import domain.ContactoBuilder;
import domain.ContactoDTO;

import interfaces.IContactoDao;

/**
 * Clase para conectar con la base de datos SQLite
 */
public class ContactoDaoSQLite extends SQLiteOpenHelper implements IContactoDao {

    private ContactoDTO mContacto;


    public ContactoDaoSQLite(@Nullable Context context, ContactoDTO vContacto) {
        super(context, HackatonUPB2021Application.getDbName(), null, 1);
        this.mContacto = vContacto;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        BaseDatos.crearBaseDatos(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public ContactoDTO getContacto() {
        return this.mContacto;
    }

    public void setContacto(ContactoDTO vContacto) {
        this.mContacto = vContacto;
    }


    /**
     * @return
     * @throws SQLException
     */
    @Override
    public List<ContactoDTO> selectAll() throws SQLException {

        //Tiene acceso a la base de datos para escritura
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "SELECT " +
                BaseDatos.COL_CONTACTS_CONTACTOID +
                ", " + BaseDatos.COL_CONTACTS_USERID +
                ", " + BaseDatos.COL_CONTACTS_NOMBRE +
                ", " + BaseDatos.COL_CONTACTS_APELLIDO +
                ", " + BaseDatos.COL_CONTACTS_CORREO +
                ", " + BaseDatos.COL_CONTACTS_TELEFONO +
                ", " + BaseDatos.COL_CONTACTS_RELACION +
                " FROM " + BaseDatos.TBL_CONTACTS;

        //Ejecuta la consulta
        Cursor cursor = db.rawQuery(queryString, null);

        ContactoDTO contacto = null;
        List<ContactoDTO> contactos = new ArrayList<>();

        //Recorre el cursor
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int idContacto = cursor.getInt(0); //id del contacto
                int idUsuario = cursor.getInt(1); //id del usuario
                String nombre = cursor.getString(2); //nombre
                String apellido = cursor.getString(3); //apellido
                String correo = cursor.getString(4); //correo electrónico
                String telefono = cursor.getString(5); //teléfono
                String relacion = cursor.getString(6); //relacion

                ContactoBuilder contactoBuilder = new ContactoBuilder();
                contactoBuilder
                        .setIdContacto(idContacto)
                        .setIdUsuario(idUsuario)
                        .setNombre(nombre)
                        .setApellido(apellido)
                        .setCorreoElectronico(correo)
                        .setTelefono(telefono)
                        .setRelacion(relacion);

                contacto = contactoBuilder.getContactoDTO();
                contactos.add(contacto);
            }
        }

        //Cerrar el cursor y la conexión a la base de datos
        cursor.close();
        db.close();
        return contactos;

    }

    /**
     * Devuelve el primer objeto de tipo usuario que coincida con el id
     *
     * @param vIdUsuario
     * @return
     * @throws SQLException
     */
    @Override
    public ContactoDTO selectByUserId(int vIdUsuario) throws SQLException {

        //Tiene acceso a la base de datos para escritura
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "SELECT " +
                BaseDatos.COL_CONTACTS_CONTACTOID +
                ", " + BaseDatos.COL_CONTACTS_USERID +
                ", " + BaseDatos.COL_CONTACTS_NOMBRE +
                ", " + BaseDatos.COL_CONTACTS_APELLIDO +
                ", " + BaseDatos.COL_CONTACTS_CORREO +
                ", " + BaseDatos.COL_CONTACTS_TELEFONO +
                ", " + BaseDatos.COL_CONTACTS_RELACION +
                " FROM " + BaseDatos.TBL_CONTACTS +
                " WHERE " + BaseDatos.COL_CONTACTS_USERID +
                " = " + "\'" + vIdUsuario + "\'";

        //Ejecuta la consulta
        Cursor cursor = db.rawQuery(queryString, null);

        ContactoDTO contacto = null;
        //Recorre el cursor
        if (cursor.getCount() > 0) {
            cursor.moveToFirst(); //Selecciona el primero registro

            int idContacto = cursor.getInt(0); //id del contacto
            int idUsuario = cursor.getInt(1); //id del usuario
            String nombre = cursor.getString(2); //nombre
            String apellido = cursor.getString(3); //apellido
            String correo = cursor.getString(4); //correo electrónico
            String telefono = cursor.getString(5); //teléfono
            String relacion = cursor.getString(6); //relacion

            ContactoBuilder contactoBuilder = new ContactoBuilder();
            contactoBuilder
                    .setIdContacto(idContacto)
                    .setIdUsuario(idUsuario)
                    .setNombre(nombre)
                    .setApellido(apellido)
                    .setCorreoElectronico(correo)
                    .setTelefono(telefono)
                    .setRelacion(relacion);

            contacto = contactoBuilder.getContactoDTO();
        }

        //Cerrar el cursor y la conexión a la base de datos
        cursor.close();
        db.close();
        return contacto;
    }

    /**
     * Devuelve el primer objeto de tipo usuario que coincida con el id
     *
     * @param vIdContacto
     * @return
     * @throws SQLException
     */
    @Override
    public ContactoDTO selectByContactoId(int vIdContacto) throws SQLException {

        //Tiene acceso a la base de datos para escritura
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "SELECT " +
                BaseDatos.COL_CONTACTS_CONTACTOID +
                ", " + BaseDatos.COL_CONTACTS_USERID +
                ", " + BaseDatos.COL_CONTACTS_NOMBRE +
                ", " + BaseDatos.COL_CONTACTS_APELLIDO +
                ", " + BaseDatos.COL_CONTACTS_CORREO +
                ", " + BaseDatos.COL_CONTACTS_TELEFONO +
                ", " + BaseDatos.COL_CONTACTS_RELACION +
                " FROM " + BaseDatos.TBL_CONTACTS +
                " WHERE " + BaseDatos.COL_CONTACTS_CONTACTOID +
                " = " + "\'" + vIdContacto + "\'";

        //Ejecuta la consulta
        Cursor cursor = db.rawQuery(queryString, null);

        ContactoDTO contacto = null;
        //Recorre el cursor
        if (cursor.getCount() > 0) {
            cursor.moveToFirst(); //Selecciona el primero registro

            int idContacto = cursor.getInt(0); //id del contacto
            int idUsuario = cursor.getInt(1); //id del usuario
            String nombre = cursor.getString(2); //nombre
            String apellido = cursor.getString(3); //apellido
            String correo = cursor.getString(4); //correo electrónico
            String telefono = cursor.getString(5); //teléfono
            String relacion = cursor.getString(6); //relacion

            ContactoBuilder contactoBuilder = new ContactoBuilder();
            contactoBuilder
                    .setIdContacto(idContacto)
                    .setIdUsuario(idUsuario)
                    .setNombre(nombre)
                    .setApellido(apellido)
                    .setCorreoElectronico(correo)
                    .setTelefono(telefono)
                    .setRelacion(relacion);

            contacto = contactoBuilder.getContactoDTO();
        }

        //Cerrar el cursor y la conexión a la base de datos
        cursor.close();
        db.close();
        return contacto;
    }


    /**
     * Crea un nuevo contacto en la base de datos
     *
     * @return
     */
    @Override
    public long insertOne(ContactoDTO vContactoDTO) throws SQLException{

        //Tiene acceso a la base de datos para escritura
        SQLiteDatabase db = this.getWritableDatabase();

        //Define los valores de cada campo
        ContentValues contentValues = new ContentValues();
        contentValues.put(BaseDatos.COL_CONTACTS_USERID, vContactoDTO.getIdUsuario());
        contentValues.put(BaseDatos.COL_CONTACTS_NOMBRE, vContactoDTO.getNombre());
        contentValues.put(BaseDatos.COL_CONTACTS_APELLIDO, vContactoDTO.getApellido());
        contentValues.put(BaseDatos.COL_CONTACTS_CORREO, vContactoDTO.getCorreoElectronico());
        contentValues.put(BaseDatos.COL_CONTACTS_TELEFONO, vContactoDTO.getTelefono());
        contentValues.put(BaseDatos.COL_CONTACTS_RELACION, vContactoDTO.getRelacion());

        //Inserta el registro
        long insert = db.insert(BaseDatos.TBL_CONTACTS, null, contentValues);
        db.close(); //cierra la conexión
        //Valida el número de registros insertados

        return insert;
    }

    /**
     * Actualiza los datos de un contacto
     *
     * @param vContacto
     * @return
     * @throws SQLException
     */
    @Override
    public long deleteOne(ContactoDTO vContacto) throws SQLException {
        return 0;
    }

    /**
     * Actualiza un registro de un contacto
     *
     * @param vContacto
     * @return
     * @throws SQLException
     */
    @Override
    public long updateOne(ContactoDTO vContacto) throws SQLException {

        //Tiene acceso a la base de datos para escritura
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause =
                BaseDatos.COL_CONTACTS_CONTACTOID +
                        " = ?";

        //Define los valores de cada campo
        ContentValues contentValues = new ContentValues();
        contentValues.put(BaseDatos.COL_CONTACTS_NOMBRE, vContacto.getNombre());
        contentValues.put(BaseDatos.COL_CONTACTS_APELLIDO, vContacto.getApellido());
        contentValues.put(BaseDatos.COL_CONTACTS_CORREO, vContacto.getCorreoElectronico());
        contentValues.put(BaseDatos.COL_CONTACTS_TELEFONO, vContacto.getTelefono());
        contentValues.put(BaseDatos.COL_CONTACTS_RELACION, vContacto.getTelefono());

        //actualiza el registro

        long update = db.update(BaseDatos.TBL_CONTACTS, contentValues, whereClause, new String[]{String.valueOf(vContacto.getIdContacto())});
        //Valida el número de registros insertados

        db.close();
        return update;
    }
}


