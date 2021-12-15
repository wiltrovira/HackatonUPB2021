package Utilities;

import android.database.sqlite.SQLiteDatabase;

public class BaseDatos {

    /*
    Tabla de usuarios
     */
    public static final String TBL_USERS = "tbl_usuarios";
    public static final String UQ_USERS_USERNAME = "uq_username";
    public static final String COL_USERS_USERID = "id_usuario";
    public static final String COL_USERS_USERNAME = "username";
    public static final String COL_USERS_PASSWORD = "password_hash";
    public static final String COL_USERS_SALT = "salt";
    public static final String COL_USERS_NOMBRE = "nombre";
    public static final String COL_USERS_APELLIDO = "apellido";
    public static final String COL_USERS_CORREO = "correo_electronico";
    public static final String COL_USERS_TELEFONO = "telefono";

    /*
    Tabla de contactos
     */
    public static final String TBL_CONTACTS = "tbl_contactos";
    public static final String COL_CONTACTS_USERID = "id_usuario";
    public static final String COL_CONTACTS_CONTACTOID = "id_contacto";
    public static final String COL_CONTACTS_NOMBRE = "nombre";
    public static final String COL_CONTACTS_APELLIDO = "apellido";
    public static final String COL_CONTACTS_CORREO = "correo_electronico";
    public static final String COL_CONTACTS_TELEFONO = "telefono";
    public static final String COL_CONTACTS_RELACION = "relacion";

    /**
     *
     */
    public static void crearBaseDatos(SQLiteDatabase vDb){
        //Comando de SQL para crear una tabla
        String createTableContacts = "CREATE TABLE " + TBL_CONTACTS + " (\n" +
                "    " + COL_CONTACTS_CONTACTOID + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    " + COL_CONTACTS_USERID + " INTEGER NOT NULL,\n" +
                "    " + COL_CONTACTS_NOMBRE + " STRING (45) ,\n" +
                "    " + COL_CONTACTS_APELLIDO + " STRING (45) ,\n" +
                "    " + COL_CONTACTS_CORREO + " STRING (45) ,\n" +
                "    " + COL_CONTACTS_TELEFONO + " STRING (45) ,\n" +
                "    " + COL_CONTACTS_RELACION + " STRING (45)\n" +
                ");";

        //Comando de SQL para crear una tabla
        String createTableUsers = "CREATE TABLE " + TBL_USERS + " (\n" +
                "    " + COL_USERS_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    " + COL_USERS_USERNAME + " STRING (45) NOT NULL,\n" +
                "    " + COL_USERS_PASSWORD + " STRING (100),\n" +
                "    " + COL_USERS_SALT + " STRING (65),\n" +
                "    " + COL_USERS_NOMBRE + " STRING (45) ,\n" +
                "    " + COL_USERS_APELLIDO + " STRING (45) ,\n" +
                "    " + COL_USERS_CORREO + " STRING (45) ,\n" +
                "    " + COL_USERS_TELEFONO + " STRING (45)\n" +
                ");";

        String createIndexUsers = "CREATE UNIQUE INDEX " + UQ_USERS_USERNAME + "\n" +
                " ON " + TBL_USERS +
                " (\n" +
                COL_USERS_USERNAME + "\n" +
                ");";

        /*
        Ejecuta las sentencias
         */
        vDb.execSQL(createTableUsers); //Crea la tabla de usuarios
        vDb.execSQL(createIndexUsers); //Crea el índice de usuarios
        vDb.execSQL(createTableContacts); //Crea la tabla de contactos

    }
}
