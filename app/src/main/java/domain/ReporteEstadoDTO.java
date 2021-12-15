package domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class ReporteEstadoDTO {

    public enum CATEGORIA_EVENTOS{
        DERRUMBRES,
        INUNDACIONES,
        HURACANES,
        TERREMOTOS,
        INCENDIOS,
        VOLCANES,
        TSUNAMIS,
        OTROS_EVENTOS
    }

//
//    public static final String CATEGORIA_DERRUMBES = "DERRUMBES";
//    public static final String CATEGORIA_INUNDACIONES = "INUNDACIONES";
//    public static final String CATEGORIA_HURACANES = "HURACANES";
//    public static final String CATEGORIA_TERREMOTOS = "TERREMOTOS";
//    public static final String CATEGORIA_INCENDIOS = "INCENDIOS";
//    public static final String CATEGORIA_VOLCANES = "VOLCANES";
//    public static final String CATEGORIA_TSUNAMIS = "TSUNAMIS";
//    public static final String CATEGORIA_OTROS_EVENTOS = "OTROS EVENTOS";



    private int mIdUsuario;
    private String mFechaReporte;
    private int mEstadoSalud;
    private String mCategoriaEvento;

    public ReporteEstadoDTO(int vIdUsuario, String vFechaReporte, int vEstadoSalud, String vCategoriaEvento) {
        this.mIdUsuario = vIdUsuario;
        this.mFechaReporte = vFechaReporte;
        this.mEstadoSalud = vEstadoSalud;
        this.mCategoriaEvento= vCategoriaEvento;
    }

    public String getDateFormat() {
//        TimeZone tz = TimeZone.getTimeZone("UTC-5");
        DateFormat df = new SimpleDateFormat("MMM DD, yyyy"); // Quoted "Z" to indicate UTC, no timezone offset
//        df.setTimeZone(tz);
        String nowAsISO = df.format(this.mFechaReporte);

//
//        long timeMilliseconds = Long.parseLong(this.mFechaReporte);
//        Date dateObject = new Date(timeMilliseconds);
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM DD, yyyy");

        return nowAsISO; //simpleDateFormat.format(dateObject);
    }


    public String getTimeFormat() {

//        long timeMilliseconds = Long.parseLong(this.mFechaReporte);
        String dateObject = this.mFechaReporte; //new Date(timeMilliseconds);

        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("h:mm a");

        return simpleTimeFormat.format(dateObject);
    }


    public int getIdUsuario() {
        return this.mIdUsuario;
    }

    public void setIdUsuario(int mIdUsuario) {
        this.mIdUsuario = mIdUsuario;
    }

    public String getFechaReporte() {
        return this.mFechaReporte;
    }

    public void setFechaReporte(String mFechaReporte) {
        this.mFechaReporte = mFechaReporte;
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

