package domain;

import java.util.Date;

public class MiHistorialBuilder {

    private int mIdHistoria;
    private int mIdUsuario;
    private long mFechaReporte;
    private int mEstadoSalud;
    private String mCategoriaEvento;
    private String mUbicacion;
    private String mUbicacionDescripcion;

    public MiHistorialBuilder() {
    }

    public MiHistorialBuilder setIdUsuario(int vIdUsuario) {
        this.mIdUsuario = vIdUsuario;
        return this;
    }

    public MiHistorialBuilder setIdHistoria(int vIdHistoria) {
        this.mIdHistoria = vIdHistoria;
        return this;
    }

    public MiHistorialBuilder setFechaReporte(long vFechaReporte) {
        this.mFechaReporte = vFechaReporte;
        return this;
    }

    public MiHistorialBuilder setEstadoSalud(int vEstadoSalud) {
        this.mEstadoSalud = vEstadoSalud;
        return this;
    }

    public MiHistorialBuilder setCategoriaEvento(String vCategoriaEvento) {
        this.mCategoriaEvento = vCategoriaEvento;
        return this;
    }

    public MiHistorialBuilder setUbicacion(String vUbicacion) {
        this.mUbicacion = vUbicacion;
        return this;
    }

    public MiHistorialBuilder setUbicacionDescripcion(String vUbicacionDescripcion) {
        this.mUbicacionDescripcion = vUbicacionDescripcion;
        return this;
    }

    public MiHistorialDTO getMiHistorialDTO() {
        return new MiHistorialDTO(mIdHistoria, mIdUsuario, mFechaReporte, mUbicacion, mUbicacionDescripcion, mEstadoSalud, mCategoriaEvento);
    }

}
