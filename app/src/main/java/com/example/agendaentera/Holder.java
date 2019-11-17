package com.example.agendaentera;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class Holder extends RecyclerView.ViewHolder {

    TextView nombre, apellidos, correo, telefono;


    public Holder(@NonNull View itemView) {
        super(itemView);


        nombre = itemView.findViewById(R.id.Nombre);
        apellidos = itemView.findViewById(R.id.Apellido);
        telefono = itemView.findViewById(R.id.Telefono);
        correo = itemView.findViewById(R.id.Correo);
    }

    public void bind(Datos datos, int pos) {

        nombre.setText(datos.getNombre());
        apellidos.setText(datos.getApellidos());
        telefono.setText(datos.getTelefono());
        correo.setText(datos.getCorreo());

    }


}
