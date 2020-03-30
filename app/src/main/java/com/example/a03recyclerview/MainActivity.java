package com.example.a03recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> lstNombre;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();
        initLstNombre();

        //Iniciando el layoutManager
        layoutManager = new LinearLayoutManager(this);
        //Iniciando el adaptador
        adapter = new MyAdapter(lstNombre, R.layout.recycler_view_item,
                new MyAdapter.OnItemClickListener() {
            //Esta es nuestra interfaz, que ahora estamos implementando
            @Override
            public void onItemClick(String nombre, int posicion) {
                Toast.makeText(MainActivity.this, nombre + " " + posicion,
                        Toast.LENGTH_LONG).show();
            }
        });
        //Asignando el adaptador y el layoutManager al recyclerView
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initLstNombre () {
        lstNombre = new ArrayList<String>(){{
            add("Gean");
            add("Carlo");
            add("Pepe");
            add("Jose");
            add("Luis");
        }};
    }

    private void initControls () {
        recyclerView = findViewById(R.id.recyclerViewMain);
    }
}
