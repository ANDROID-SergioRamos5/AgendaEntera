package com.example.agendaentera;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EditContacto extends AppCompatActivity {

    private FloatingActionButton fab;
    private Datos datos;
    EditText nombre, apellido, telefono, correo;


    @Override
    public void onCreate(Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.editar_contacto);

        fab = findViewById(R.id.fab2);
        nombre = findViewById(R.id.nombre_edit);
        apellido = findViewById(R.id.apellido_edit);
        telefono = findViewById(R.id.telefono_edit);
        correo = findViewById(R.id.email_edit);

        datos = getIntent().getExtras().getParcelable("Dato");


        nombre.setText(datos.getNombre());
        apellido.setText(datos.getApellidos());
        correo.setText(datos.getCorreo());
        telefono.setText(datos.getTelefono());


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datos.setNombre(nombre.getText().toString());
                datos.setApellidos(apellido.getText().toString());
                datos.setCorreo(correo.getText().toString());
                datos.setTelefono(telefono.getText().toString());

                Intent intent = new Intent();
                intent.putExtra("SALIDA", datos);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
