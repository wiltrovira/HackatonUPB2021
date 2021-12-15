package domain;

/**
 *
 * @author WiltR
 */
public class UsuarioDTO extends ObjetoDTO {

    private int mIdUsuario;
    private String mUserName;
    private String mPassword;
    private String mSalt;
    private String mNombre;
    private String mApellido;
    private String mCorreoElectronico;
    private String mTelefono;


    /**
     * Constructor vacío
     */
    public UsuarioDTO() {}

    /**
     * Constructor
     *
     * @param vUserName nombre de usuario o login
     * @param vPassword Contraseña
     */
    public UsuarioDTO(String vUserName, String vPassword) {
        this.mUserName = vUserName;
        this.mPassword = vPassword;
    }

    /**
     *
     * @param vIdUsuario
     * @param vUserName
     * @param vPassword
     */
    public UsuarioDTO(int vIdUsuario, String vUserName, String vPassword) {
        this.mIdUsuario = vIdUsuario;
        this.mUserName = vUserName;
        this.mPassword = vPassword;
    }

    /**
     *
     * @param vIdUsuario
     * @param vUserName
     * @param vPassword
     * @param vSalt
     */
    public UsuarioDTO(int vIdUsuario, String vUserName, String vPassword, String vSalt) {
        this.mIdUsuario = vIdUsuario;
        this.mUserName = vUserName;
        this.mPassword = vPassword;
        this.mSalt = vSalt;
    }

    public UsuarioDTO(int vIdUsuario, String vUserName, String vPassword, String vSalt, String vNombre, String vApellido, String vCorreo, String vTelefono) {
        this.mIdUsuario = vIdUsuario;
        this.mUserName = vUserName;
        this.mPassword = vPassword;
        this.mSalt = vSalt;
        this.mNombre = vNombre;
        this.mApellido = vApellido;
        this.mCorreoElectronico = vCorreo;
        this.mTelefono = vTelefono;
    }
    /**
     * Constructor: Crea un objeto con el nombre de usuario
     * @param vUserName
     */
    public UsuarioDTO(String vUserName) {
        this.mUserName = vUserName;
    }

    /**
     *
     * @return
     */
    public int getIdUsuario() {
        return this.mIdUsuario;
    }

    /**
     *
     * @param vIdUsuario
     */
    public void setIdUsuario(int vIdUsuario) {
        this.mIdUsuario = vIdUsuario;
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return this.mUserName;
    }

    /**
     *
     * @param vUserName
     */
    public void setUserName(String vUserName) {
        this.mUserName = vUserName;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return this.mPassword;
    }

    /**
     *
     * @param vPassword
     */
    public void setmPassword(String vPassword) {
        this.mPassword = vPassword;
    }

    public String getSalt() {
        return this.mSalt;
    }

    public void setSalt(String vSalt) {
        this.mSalt = vSalt;
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

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{IdUsuario=").append(mIdUsuario);
        sb.append(", UserName=").append(mUserName);
        sb.append(", Password=").append(mPassword);
        sb.append('}');
        return sb.toString();
    }


}
