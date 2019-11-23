package com.example.agendaentera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adaptador extends RecyclerView.Adapter implements View.OnLongClickListener,View.OnClickListener, View.OnTouchListener{

    Holder holder;
    Context context;
    Datos datos;
    View.OnClickListener listener;
    View.OnLongClickListener longListener;
    View.OnTouchListener listenerTouch;
    OnClickImagen listenerImagen;

    public Adaptador(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder, parent,false);
        holder = new Holder(view);
        view.setOnLongClickListener(this);
        view.setOnClickListener(this);
        view.setOnTouchListener(this);

        holder.ClickImagen(new OnClickImagen() {
            @Override
            public void onClickImagen(Datos datos, View v) {
                listenerImagen.onClickImagen(datos,view);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((Holder)holder).bind(((MainActivity)context).datos.get(position), position);
    }

    @Override
    public int getItemCount() {
        return((MainActivity)context).datos.size();
    }


    public void setClickListener(View.OnClickListener listener)
    {
        if(listener != null)
            this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) listener.onClick(v);
    }


    public void setLongListener(View.OnLongClickListener longListener)
    {
        if (longListener != null)
            this.longListener = longListener;
    }

    @Override
    public boolean onLongClick(View v) {
        if (longListener != null)
            longListener.onLongClick(v);
        return true;
    }

    public void setTouchListener(View.OnTouchListener listenerTouch)
    {
        if (listenerTouch != null)
            this.listenerTouch = listenerTouch;
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        if (listenerTouch != null)
            listenerTouch.onTouch(view, motionEvent);
        return false;
    }

    public void ClickImagen(OnClickImagen listenerImagen)
    {
        if (listenerImagen != null) this.listenerImagen = listenerImagen;
    }
}
