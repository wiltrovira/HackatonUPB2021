package domain;

public class ContactoBuilder {

    private int mIdUsuario;
    private int mIdContacto;
    private String mNombre;
    private String mApellido;
    private String mCorreoElectronico;
    private String mTelefono;
    private String mRelacion;

    public ContactoBuilder() {
    }

    public ContactoBuilder setIdContacto(int vIdContacto) {
        this.mIdContacto = vIdContacto;
        return this;
    }
    public ContactoBuilder setIdUsuario(int vIdUsuario) {
        this.mIdUsuario = vIdUsuario;
        return this;
    }

    public ContactoBuilder setRelacion(String vRelacion) {
        this.mRelacion = vRelacion;
        return this;
    }

    public ContactoBuilder setNombre(String vNombre) {
        this.mNombre = vNombre;
        return this;
    }

    public ContactoBuilder setApellido(String vApellido) {
        this.mApellido = vApellido;
        return this;
    }

    public ContactoBuilder setCorreoElectronico(String vCorreoElectronico) {
        this.mCorreoElectronico = vCorreoElectronico;
        return this;
    }

    public ContactoBuilder setTelefono(String vTelefono) {
        this.mTelefono = vTelefono;
        return this;
    }

    public ContactoDTO getContactoDTO() {
        return new ContactoDTO(mIdUsuario, mNombre, mApellido, mCorreoElectronico, mTelefono, mRelacion);
    }

}
