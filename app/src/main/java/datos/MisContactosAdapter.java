package datos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wiltrovira.hackatonupb2021.R;

import java.util.List;

import domain.ContactoDTO;

public class MisContactosAdapter extends ArrayAdapter<ContactoDTO> {

    public MisContactosAdapter(@NonNull Context context, List<ContactoDTO> misContactos) {
        super(context, 0, misContactos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.mis_contactos_list_item, parent, false);
        }

        //Ubico el objeto en la posición dentro de la lista
        ContactoDTO currentContact = getItem(position);

        //Asocia los campos de la lista con una variable para poder configurar sus valores
        TextView tvNombreApellidoContacto = (TextView) listItemView.findViewById(R.id.tv_nombreApellidoContacto);
        TextView tvRelacionContacto = (TextView) listItemView.findViewById(R.id.tv_relacionContacto);
        TextView tvTelefonoContacto = (TextView) listItemView.findViewById(R.id.tv_telefonoContacto);
        TextView tvCorreoContacto = (TextView) listItemView.findViewById(R.id.tv_correoElectronicoContacto);


        //Asigna los valores del objeto Earthquake actual a la lista
        tvNombreApellidoContacto.setText(currentContact.getNombre() + "" + currentContact.getApellido());
        tvRelacionContacto.setText(currentContact.getRelacion());
        tvTelefonoContacto.setText(currentContact.getTelefono());
        tvCorreoContacto.setText(currentContact.getCorreoElectronico());

        return listItemView;
    }

}
