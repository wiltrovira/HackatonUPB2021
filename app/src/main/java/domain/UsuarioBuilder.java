package domain;

public class UsuarioBuilder {


    private int mIdUsuario;
    private String mUserName;
    private String mPassword;
    private String mSalt;
    private String mNombre;
    private String mApellido;
    private String mCorreoElectronico;
    private String mTelefono;

    public UsuarioBuilder() {
    }

    public UsuarioBuilder setIdUsuario(int vIdUsuario) {
        this.mIdUsuario = vIdUsuario;
        return this;
    }

    public UsuarioBuilder setUserName(String vUserName) {
        this.mUserName = vUserName;
        return this;
    }

    public UsuarioBuilder setPassword(String vPassword) {
        this.mPassword = vPassword;
        return this;

    }

    public UsuarioBuilder setSalt(String vSalt) {
        this.mSalt = vSalt;
        return this;

    }

    public UsuarioBuilder setNombre(String vNombre) {
        this.mNombre = vNombre;
        return this;

    }

    public UsuarioBuilder setApellido(String vApellido) {
        this.mApellido = vApellido;
        return this;

    }

    public UsuarioBuilder setCorreoElectronico(String vCorreoElectronico) {
        this.mCorreoElectronico = vCorreoElectronico;
        return this;

    }

    public UsuarioBuilder setTelefono(String vTelefono) {
        this.mTelefono = vTelefono;
        return this;

    }

    public UsuarioDTO getUsuarioDTO() {
        return new UsuarioDTO(mIdUsuario, mUserName, mPassword, mSalt, mNombre, mApellido, mCorreoElectronico, mTelefono);
    }

}
