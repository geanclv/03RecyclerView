package com.example.a03recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Sesión 1: Implementación de un RecyclerView básico
    //Sesión 2: Mejoras al recyclerView, menu de opciones,
    private List<String> lstNombre;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();
        initLstNombre();

        //Iniciando el layoutManager
//        layoutManager = new LinearLayoutManager(this); // Vista de lista
//        layoutManager = new GridLayoutManager(this, 2); //Vista de grid
        layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL); //Vista vertical u horizontal de grid
        //Iniciando el adaptador
        adapter = new MyAdapter(lstNombre, R.layout.recycler_view_item,
                new MyAdapter.OnItemClickListener() {
                    //Esta es nuestra interfaz, que ahora estamos implementando
                    @Override
                    public void onItemClick(String nombre, int posicion) {
//                        Toast.makeText(MainActivity.this, nombre + " " + posicion,
//                                Toast.LENGTH_LONG).show();
                        eliminarNombre(posicion);
                    }
                });

        //Sesión 2: Mejoras al recyclerView:
        // 1. Cuando conocemos que el tamaño no va variar, mejoramos el uso de recursos
        recyclerView.setHasFixedSize(true);
        // 2. Animaciones para los eventos
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Asignando el adaptador y el layoutManager al recyclerView
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    //Sesion 2: creando el menu de opciones
    //Inflando el menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //Asignando opciones a los diferentes items del menu de opciones
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_nombre:
                this.agregarNombre(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void agregarNombre(int posicion) {
        lstNombre.add(posicion, "Nombre " + (++contador));
        //se optimiza el notifyDataSetChange del List & GridView, pero necesita la posicion
        // donde se agregó el item
        adapter.notifyItemInserted(posicion);
        //Moviendo la vista a la posición donde se agregó el elemento. Por defecto la vista no se mueve
        layoutManager.scrollToPosition(posicion);
    }

    private void eliminarNombre(int posicion) {
        lstNombre.remove(posicion);
        adapter.notifyItemRemoved(posicion);
    }

    //Sesion 1
    private void initLstNombre() {
        lstNombre = new ArrayList<String>() {{
            add("Gean");
            add("Carlo");
            add("Pepe");
            add("Jose");
            add("Luis");
        }};
    }

    private void initControls() {
        recyclerView = findViewById(R.id.recyclerViewMain);
    }
}
