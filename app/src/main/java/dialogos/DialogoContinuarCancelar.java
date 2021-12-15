package dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.wiltrovira.hackatonupb2021.R;

public class DialogoContinuarCancelar extends DialogFragment {

    private String mTitulo = "Confirme la acción";

    /*
       La actividad que crea una instancia del cuadro de diálogo debe implementar esta interface
       con el fin d recibir el evento callback
       Cada método pasa el DialogFragment en caso de que necesite consultarlo
    */
    public interface INoticeDialogListenerInterface {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    INoticeDialogListenerInterface listener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (INoticeDialogListenerInterface) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(getActivity().toString()
                    + " debe implementar INoticeDialogListenerInterface");
        }
    }

    /**
     *
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Usa una clase Builder (Patrón builder) para crear el cuadro de diálogo
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

        dialogBuilder
                .setMessage(R.string.dialog_estaSeguro)
                .setTitle(this.mTitulo)
                .setPositiveButton(R.string.dialog_botonContinuar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        // Send the positive button event back to the host activity
                        listener.onDialogPositiveClick(DialogoContinuarCancelar.this);
                    }
                })
                .setNegativeButton(R.string.dialog_botonCancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        // Send the negative button event back to the host activity
                        listener.onDialogNegativeClick(DialogoContinuarCancelar.this);
                    }
                });
        // Crea el cuadro de diálogo y lo devuelve
        return dialogBuilder.create();
    }
}