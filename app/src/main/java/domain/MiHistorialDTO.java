package domain;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MiHistorialDTO {

    public enum CATEGORIA_EVENTOS{
        DERRUMBES,
        INUNDACIONES,
        HURACANES,
        TERREMOTOS,
        INCENDIOS,
        VOLCANES,
        TSUNAMIS,
        OTROS_EVENTOS
    }

    private int mIdHistoria;
    private int mIdUsuario;
    private long mFechaReporte;
    private int mEstadoSalud;
    private String mCategoriaEvento;
    private String mUbicacion;
    private String mUbicacionDescripcion;

    public MiHistorialDTO(int vIdHistoria, int vIdUsuario, long vFechaReporte, String vUbicacion, String vUbicacionDescripcion, int vEstadoSalud, String vCategoriaEvento) {
        this.mIdHistoria = vIdHistoria;
        this.mIdUsuario = vIdUsuario;
        this.mFechaReporte = vFechaReporte;
        this.mUbicacion = vUbicacion;
        this.mUbicacionDescripcion = vUbicacionDescripcion;
        this.mEstadoSalud = vEstadoSalud;
        this.mCategoriaEvento= vCategoriaEvento;
    }

    public String getUbicacion() {
        return this.mUbicacion;
    }

    public void setUbicacion(String vUbicacion) {
        this.mUbicacion = vUbicacion;
    }

    public String getUbicacionDescripcion() {
        return this.mUbicacionDescripcion;
    }

    public void setUbicacionDescripcion(String vUbicacionDescripcion) {
        this.mUbicacionDescripcion = vUbicacionDescripcion;
    }

    public String getDateFormat() {
        long timeMilliseconds = this.mFechaReporte;
        Date dateObject = new Date(timeMilliseconds);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, YYYY");

        return simpleDateFormat.format(timeMilliseconds);
    }


    public String getTimeFormat() {
        long timeMilliseconds = this.mFechaReporte;
         Date dateObject = new Date(timeMilliseconds);

        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("h:mm a");

        return simpleTimeFormat.format(dateObject);
    }


    public int getIdUsuario() {
        return this.mIdUsuario;
    }

    public void setIdHistoria(int vIdHistoria) {
        this.mIdHistoria = vIdHistoria;
    }

    public int getIdHistoria() {
        return this.mIdHistoria;
    }

    public void setIdUsuario(int mIdUsuario) {
        this.mIdUsuario = mIdUsuario;
    }

    public long getFechaReporte() {
        return this.mFechaReporte;
    }

    public void setFechaReporte(long vFechaReporte) {
        this.mFechaReporte = vFechaReporte;
    }

    public int getEstadoSalud() {
        return this.mEstadoSalud;
    }

    public void setEstadoSalud(int mEstadoSalud) {
        this.mEstadoSalud = mEstadoSalud;
    }

    public String getCategoriaEvento() {
        return this.mCategoriaEvento;
    }

    public void setCategoriaEvento(String vCategoriaEvento) {
        this.mCategoriaEvento = vCategoriaEvento;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReporteEstadoDTO{");
        sb.append("IdUsuario=").append(mIdUsuario);
        sb.append(", FechaReporte=").append(mFechaReporte);
        sb.append(", EstadoSalud=").append(mEstadoSalud);
        sb.append('}');
        return sb.toString();
    }



    public String getEstadoSaludDescripcion() {
        String estadoSaludDescripcion = "";
        switch(this.mEstadoSalud){
            case 0:
                estadoSaludDescripcion = "Estado de salud 0";
                break;
            case 1:
                estadoSaludDescripcion = "Estado de salud 1";
                break;
            case 2:
                estadoSaludDescripcion = "Estado de salud 2";
                break;
            case 3:
                estadoSaludDescripcion = "Estado de salud 3";
                break;
            case 4:
                estadoSaludDescripcion = "Estado de salud 4";
                break;
            case 5:
                estadoSaludDescripcion = "Estado de salud 5";
                break;
            case 6:
                estadoSaludDescripcion = "Estado de salud 6";
                break;
            case 7:
                estadoSaludDescripcion = "Estado de salud 7";
                break;
            case 8:
                estadoSaludDescripcion = "Estado de salud 8";
                break;
            case 9:
                estadoSaludDescripcion = "Estado de salud 9";
                break;
            case 10:
                estadoSaludDescripcion = "Estado de salud 10";
                break;
            default:
                estadoSaludDescripcion = "Estado de salud Default";
                break;
        }
        return estadoSaludDescripcion;
    }
}

