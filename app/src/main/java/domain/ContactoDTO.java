package domain;

/**
 * @author WiltR
 */
public class ContactoDTO extends ObjetoDTO {

    private int mIdUsuario;
    private int mIdContacto;
    private String mRelacion;
    private String mNombre;
    private String mApellido;
    private String mCorreoElectronico;
    private String mTelefono;


    /**
     * Constructor vacío
     */
    public ContactoDTO() {
    }

    /**
     * Constructor
     * @param vIdUsuario
     * @param vNombre
     * @param vApellido
     * @param vCorreo
     * @param vTelefono
     * @param vRelacion
     */
    public ContactoDTO(int vIdUsuario, String vNombre, String vApellido, String vCorreo, String vTelefono, String vRelacion) {
        this.mIdUsuario = vIdUsuario;
        this.mNombre = vNombre;
        this.mApellido = vApellido;
        this.mCorreoElectronico = vCorreo;
        this.mTelefono = vTelefono;
        this.mRelacion = vRelacion;
    }

    /**
     * @return
     */
    public int getIdUsuario() {
        return this.mIdUsuario;
    }

    /**
     * @param vIdUsuario
     */
    public void setIdUsuario(int vIdUsuario) {
        this.mIdUsuario = vIdUsuario;
    }

    /**
     * @return
     */
    public int getIdContacto() {
        return this.mIdContacto;
    }

    /**
     * @param vIdContacto
     */
    public void setIdContacto(int vIdContacto) {
        this.mIdUsuario = vIdContacto;
    }



    public String getNombre() {
        return this.mNombre;
    }

    public void setNombre(String vNombre) {
        this.mNombre = vNombre;
    }

    public String getApellido() {
        return this.mApellido;
    }

    public void setApellido(String vApellido) {
        this.mApellido = vApellido;
    }

    public String getCorreoElectronico() {
        return this.mCorreoElectronico;
    }

    public void setCorreoElectronico(String vCorreoElectronico) {
        this.mCorreoElectronico = vCorreoElectronico;
    }

    public String getTelefono() {
        return this.mTelefono;
    }

    public void setTelefono(String vTelefono) {
        this.mTelefono = vTelefono;
    }

    public String getRelacion() {
        return this.mRelacion;
    }

    public void setRelacion(String vRelacion) {
        this.mRelacion = vRelacion;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContactoDTO{");
        sb.append("IdContacto=").append(mIdContacto);
        sb.append("IdUsuario=").append(mIdUsuario);
        sb.append(", Relacion='").append(mRelacion).append('\'');
        sb.append(", Nombre='").append(mNombre).append('\'');
        sb.append(", Apellido='").append(mApellido).append('\'');
        sb.append(", CorreoElectronico='").append(mCorreoElectronico).append('\'');
        sb.append(", Telefono='").append(mTelefono).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

