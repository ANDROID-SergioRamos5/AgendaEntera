package com.example.agendaentera;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adaptador adaptador;
    Datos[] datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        añadirDatos();

        recyclerView = findViewById(R.id.lista);

        adaptador = new Adaptador(this);

        recyclerView.setAdapter(adaptador);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL, false));
    }

    private void añadirDatos()
    {
        datos = new Datos[]{
                new Datos("Sergio", "Ramos Santonja", "676813768","sergio@gmail.com"),
                new Datos("Carlos", "Clemente Bellido", "659478945","carlos@gmail.com"),
                new Datos("Aìtor", "Soto Jimenez", "675221581","aitor@gmail.com"),
                new Datos("Paula", "Valero Ferrandez", "678912528","paula@gmail.com"),
                new Datos("Andrea", "Ramos Santonja", "7548475254","andrea@gmail.com")
        };
    }
}
