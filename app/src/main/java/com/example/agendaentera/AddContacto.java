package com.example.agendaentera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddContacto extends AppCompatActivity {

    Datos datos;
    EditText nombre, apellidos, telefono, correo;
    FloatingActionButton fab;

    @Override
    public void onCreate(Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.nuevo_contacto);

        nombre = findViewById(R.id.eT_Nombre);
        apellidos = findViewById(R.id.eT_Apellido);
        telefono = findViewById(R.id.eT_Telefono);
        correo = findViewById(R.id.eT_email);

        datos = new Datos();

        fab = findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datos.setNombre(nombre.getText().toString());
                datos.setApellidos(apellidos.getText().toString());
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
