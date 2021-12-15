package com.wiltrovira.hackatonupb2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.media.metrics.Event;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import dialogos.DialogoContinuarCancelar;
import domain.ReporteEstadoDTO;

/*
 *
 */
public class EventCategoryActivity extends AppCompatActivity
        implements DialogoContinuarCancelar.INoticeDialogListenerInterface {

    TextView tvDerrumbes, tvInundaciones, tvHuracanes, tvTerremotos, tvIncendios, tvVolcanes, tvTsunamis, tvOtrosEventos;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_category);

        tvDerrumbes = findViewById(R.id.tv_derrumbes);
        tvInundaciones = findViewById(R.id.tv_inundaciones);
        tvHuracanes = findViewById(R.id.tv_huracanes);
        tvTerremotos = findViewById(R.id.tv_terremotos);
        tvIncendios = findViewById(R.id.tv_incendios);
        tvVolcanes = findViewById(R.id.tv_volcanes);
        tvTsunamis = findViewById(R.id.tv_tsunamis);
        tvOtrosEventos = findViewById(R.id.tv_otrosEventos);


        /*
         * Botón de Derrumbes
         */
        tvDerrumbes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Crea una nueva caja de diálogo
                DialogoContinuarCancelar dialogoContinuarCancelar = new DialogoContinuarCancelar();
                dialogoContinuarCancelar.show(getSupportFragmentManager(), String.valueOf(ReporteEstadoDTO.CATEGORIA_EVENTOS.DERRUMBRES));
            }
        });


        /*
         * Botón de Inundaciones
         */
        tvInundaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoContinuarCancelar dialogoContinuarCancelar = new DialogoContinuarCancelar();
                dialogoContinuarCancelar.show(getSupportFragmentManager(), String.valueOf(ReporteEstadoDTO.CATEGORIA_EVENTOS.INUNDACIONES));
            }
        });


        /*
         * Botón de Huracanes
         */
        tvHuracanes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoContinuarCancelar dialogoContinuarCancelar = new DialogoContinuarCancelar();
                dialogoContinuarCancelar.show(getSupportFragmentManager(), String.valueOf(ReporteEstadoDTO.CATEGORIA_EVENTOS.HURACANES));
            }
        });


        /*
         * Botón de Terremotos
         */
        tvTerremotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoContinuarCancelar dialogoContinuarCancelar = new DialogoContinuarCancelar();
                dialogoContinuarCancelar.show(getSupportFragmentManager(), String.valueOf(ReporteEstadoDTO.CATEGORIA_EVENTOS.TERREMOTOS));
            }
        });


        /*
         * Botón de Incendios
         */
        tvIncendios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoContinuarCancelar dialogoContinuarCancelar = new DialogoContinuarCancelar();
                dialogoContinuarCancelar.show(getSupportFragmentManager(), String.valueOf(ReporteEstadoDTO.CATEGORIA_EVENTOS.INCENDIOS));
            }
        });


        /*
         * Botón de Volcanes
         */
        tvVolcanes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoContinuarCancelar dialogoContinuarCancelar = new DialogoContinuarCancelar();
                dialogoContinuarCancelar.show(getSupportFragmentManager(), String.valueOf(ReporteEstadoDTO.CATEGORIA_EVENTOS.VOLCANES));
            }
        });


        /*
         * Botón de Tsunamis
         */
        tvTsunamis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoContinuarCancelar dialogoContinuarCancelar = new DialogoContinuarCancelar();
                dialogoContinuarCancelar.show(getSupportFragmentManager(), String.valueOf(ReporteEstadoDTO.CATEGORIA_EVENTOS.TSUNAMIS));
            }
        });


        /*
         * Botón de Otros eventos
         */
        tvOtrosEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoContinuarCancelar dialogoContinuarCancelar = new DialogoContinuarCancelar();
                dialogoContinuarCancelar.show(getSupportFragmentManager(), String.valueOf(ReporteEstadoDTO.CATEGORIA_EVENTOS.OTROS_EVENTOS));
            }
        });
    }

    /**
     * Callback - Acción cuando el usuario presionó el botón Continuar
     *
     * @param dialog Cuadro de diálogo
     */
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Toast.makeText(
                EventCategoryActivity.this,
                "Confirmado: " + dialog.getTag(),
                Toast.LENGTH_SHORT)
                .show();

        Intent reportarEventoIntent = new Intent (EventCategoryActivity.this, ReportarEventoActivity.class);
        //Envía parámetros a la vista
        reportarEventoIntent.putExtra("paramCategoriaEvento", dialog.getTag());
        startActivity(reportarEventoIntent);

        //
//        if (dialog.getTag() != null) {
//            switch(dialog.getTag()){
//                case "DERRUMBES":
//                    break;
//                case "INUNDACIONES":
//                    break;
//                case "HURACANES":
//                    break;
//                case "TERREMOTOS":
//                    break;
//                case "INCENDIOS":
//                    break;
//                case "VOLCANES":
//                    break;
//                case "TSUNAMIS":
//                    break;
//                case "OTROS_EVENTOS":
//                    break;
//                default:
//                    throw new IllegalStateException("Tag no esperado en el cuadro de diálogo: " + dialog.getTag());
//            }
//        }

        return;
    }

    /**
     * Callback - Acción cuando el usuario presionó el botón Cancelar
     *
     * @param dialog Cuadro de diálogo
     */
    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(
                EventCategoryActivity.this,
                "Acción cancelada: " + dialog.getTag(),
                Toast.LENGTH_SHORT)
                .show();
        return;
    }
}