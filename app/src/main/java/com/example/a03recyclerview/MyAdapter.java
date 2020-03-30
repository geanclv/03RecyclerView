package com.example.a03recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> lstNombre;
    private int layout;
    private OnItemClickListener itemClickListener;

    public MyAdapter (List<String> lstNombre, int layout, OnItemClickListener itemClickListener) {
        this.lstNombre = lstNombre;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflando la vista con el contexto del padre y el layout que tenemos como input
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        //Inicializamos el ViewHolder y le pasamos la vista
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    //Método que se ejecuta al construir el RecyclerView, no se actualiza si se llenan más elementos
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Llamamos a nuestro método bind
        holder.bind(lstNombre.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return lstNombre.size();
    }

    //1. ViewHolder para el Adaptador de RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewNombre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewNombre = itemView.findViewById(R.id.textViewNombre);
        }

        //Método para llenar los datos a nuestra vista, como parámetros tiene:
        // 1. Nuestro modelo (en este caso un string, puede ser un objeto)
        // 2. Nuestro listener (onItemClickListener)
        public void bind (final String nombre, final OnItemClickListener listener) {
            //Llenando la vista
            this.textViewNombre.setText(nombre);
            //Implementamos el click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Llamamos al método que hemos definido en nuestra interface, recibe como parámetro:
                    // 1. El valor que estamos trabajando (ahora un string, puede ser un objeto)
                    // 2. La posición
                    listener.onItemClick(nombre, getAdapterPosition());
                }
            });
        }
    }

    //2. Interface para implementar nuestro propio OnItemClickListener
    public interface OnItemClickListener {
        void onItemClick (String nombre, int posicion);
    }
}
