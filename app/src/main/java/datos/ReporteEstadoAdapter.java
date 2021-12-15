package datos;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.wiltrovira.hackatonupb2021.R;

import java.text.DecimalFormat;
import java.util.List;

import domain.ReporteEstadoDTO;

public class ReporteEstadoAdapter extends ArrayAdapter<ReporteEstadoDTO> {
    public ReporteEstadoAdapter(@NonNull Context context, List<ReporteEstadoDTO> reportesEstado) {
        super(context, 0, reportesEstado);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.reporte_estado_list_item, parent, false);
        }

        //Ubico el objeto en la posición dentro de la lista
        ReporteEstadoDTO currentReporteEstado = getItem(position);

        //Asocia los campos de la lista con una variable para poder configurar sus valores
        TextView tvEstadoSalud = (TextView) listItemView.findViewById(R.id.tv_estadoSalud);
        TextView tvEstadoSaludDescripcion = (TextView) listItemView.findViewById(R.id.tv_estadoSaludDescripcion);
        TextView tvCategoriaEvento = (TextView) listItemView.findViewById(R.id.tv_categoriaEvento);
        TextView tvDate = (TextView) listItemView.findViewById(R.id.tv_date);
        TextView tvTime = (TextView) listItemView.findViewById(R.id.tv_time);


        //Asigna los valores del objeto Earthquake actual a la lista
        tvEstadoSalud.setText(formatMagnitude(currentReporteEstado.getEstadoSalud()));
        tvEstadoSaludDescripcion.setText(currentReporteEstado.getEstadoSaludDescripcion());
        tvCategoriaEvento.setText(currentReporteEstado.getCategoriaEvento());
        tvDate.setText(currentReporteEstado.getFechaReporte());
        tvTime.setText(currentReporteEstado.getFechaReporte());


        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) tvEstadoSalud.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentReporteEstado.getEstadoSalud());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        return listItemView;
    }


    /**
     * Devuelve el color que se va a pintar
     * @param vEstadoSalud
     * @return
     */
    private int getMagnitudeColor(int vEstadoSalud) {
        int saludEstadoColorResourceId;
//        int saludEstado = (int) Math.floor(magnitude);
        switch (vEstadoSalud) {
            case 0:
            case 1:
                saludEstadoColorResourceId = R.color.saludEstado1;
                break;
            case 2:
                saludEstadoColorResourceId = R.color.saludEstado2;
                break;
            case 3:
                saludEstadoColorResourceId = R.color.saludEstado3;
                break;
            case 4:
                saludEstadoColorResourceId = R.color.saludEstado4;
                break;
            case 5:
                saludEstadoColorResourceId = R.color.saludEstado5;
                break;
            case 6:
                saludEstadoColorResourceId = R.color.saludEstado6;
                break;
            case 7:
                saludEstadoColorResourceId = R.color.saludEstado7;
                break;
            case 8:
                saludEstadoColorResourceId = R.color.saludEstado8;
                break;
            case 9:
                saludEstadoColorResourceId = R.color.saludEstado9;
                break;
            default:
                saludEstadoColorResourceId = R.color.saludEstado10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), saludEstadoColorResourceId);
    }

    /**
     * Formatea la magnitud con un decimal
     *
     * @param magnitude
     * @return
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
}
