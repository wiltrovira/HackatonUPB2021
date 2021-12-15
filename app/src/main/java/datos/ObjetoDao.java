package datos;

import java.sql.Connection;

/**
 * Clase abstracta con propiedades para todas las conexiones
 * @author WiltR
 */
public abstract class ObjetoDao {

    protected Connection mConexion; //Conexión con la que se ejecutarán las operaciones
    protected boolean mEsConexionTransaccional; //Indica si la conexión es transaccional

    /**
     * Constructor
     */
    public ObjetoDao(){
        this.mConexion = null;
        this.mEsConexionTransaccional = true;
    }

    /**
     * Lee el valor de la conexión transaccional
     * @return
     */
    public boolean esConexionTransaccional() {
        return this.mEsConexionTransaccional;
    }

    /**
     * Establece que la conexión es transaccional
     * @param vEsConexionTransaccional
     */
    public void setEsConexionTransaccional(boolean vEsConexionTransaccional) {
        this.mEsConexionTransaccional = vEsConexionTransaccional;
    }


}
