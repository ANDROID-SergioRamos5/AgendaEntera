package com.example.agendaentera;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.nio.charset.CoderResult;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adaptador adaptador;
    public static ArrayList<Datos> datos;
    SwipeDetector swipeDetector;
    private int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        añadirDatos();

        recyclerView = findViewById(R.id.lista);

        adaptador = new Adaptador(this);

        adaptador.setLongListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pos = recyclerView.getChildAdapterPosition(v);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("¿Eliminar contacto " + datos.get(pos).getNombre() + " ?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                datos.remove(pos);
                                adaptador.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            }
        });

        adaptador.ClickImagen(new OnClickImagen() {
            @Override
            public void onClickImagen(Datos datos, View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                LayoutInflater inflater = MainActivity.this.getLayoutInflater();

                View view = inflater.inflate(R.layout.dialogo_foto,null);

                builder.setView(view);
                builder.create();
                builder.show();
            }
        });
        swipeDetector = new SwipeDetector();
        adaptador.setTouchListener(swipeDetector);
        adaptador.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (swipeDetector.swipeDetected())
                {
                    if (swipeDetector.getAction() == SwipeDetector.Action.LR){
                        pos = recyclerView.getChildAdapterPosition(view);
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("¿Llamar a " + datos.get(pos).getNombre() + " ?")
                                .setPositiveButton("Llamar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intento = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+datos.get(pos).getTelefono()));
                                        startActivity(intento);

                                    }
                                })
                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                    else if (swipeDetector.getAction() == SwipeDetector.Action.RL)
                    {
                        pos = recyclerView.getChildAdapterPosition(view);
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("¿Enviar mensaje a " + datos.get(pos).getNombre() + " ?")
                                .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intento = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",datos.get(pos).getCorreo(), null));
                                        startActivity(intento);
                                    }
                                })
                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                    else
                        Toast.makeText(MainActivity.this, "tt", Toast.LENGTH_SHORT).show();
                }else
                {
                    pos = recyclerView.getChildAdapterPosition(view);

                    Intent intento = new Intent(MainActivity.this, EditContacto.class);
                    intento.putExtra("Dato", datos.get(pos));
                    startActivityForResult(intento,2);
                }
            }
        });



        recyclerView.setAdapter(adaptador);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL, false));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(MainActivity.this, AddContacto.class);
                startActivityForResult(intento,1);
            }
        });
    }

    public void onActivityResult(int codActividad, int codResultado, @Nullable Intent data) {
        super.onActivityResult(codActividad, codResultado, data);

        if (codActividad == 2)
        {
            if (codResultado == RESULT_OK)
            {
                datos.set(pos, (Datos) data.getExtras().getParcelable("SALIDA")) ;
                recyclerView.setAdapter(adaptador);
            }
        }
        if (codActividad == 1)
        {
            if (codResultado == RESULT_OK)
            {
                datos.add((Datos) data.getExtras().getParcelable("SALIDA"));
                recyclerView.setAdapter(adaptador);
            }
        }

    }

    private void añadirDatos()
    {
        datos = new ArrayList<Datos>();
        datos.add(new Datos("Sergio", "Ramos Santonja", "676813768","sergio@gmail.com"));
        datos.add(new Datos("Carlos", "Clemente Bellido", "659478945","carlos@gmail.com"));
        datos.add(new Datos("Aìtor", "Soto Jimenez", "675221581","aitor@gmail.com"));
        datos.add(new Datos("Paula", "Valero Ferrandez", "678912528","paula@gmail.com"));
        datos.add(new Datos("Andrea", "Ramos Santonja", "7548475254","andrea@gmail.com"));


    }
}
