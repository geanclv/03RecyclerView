package com.example.a03recyclerview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> lstNombre;
    private int layout;
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //1. ViewHolder para el Adaptador de RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.textViewNombre);
        }
    }

    //2. Interface para implementar nuestro propio OnItemClickListener
    public interface OnItemClickListener {
        void onItemClick (String nombre, int posicion);
    }
}
