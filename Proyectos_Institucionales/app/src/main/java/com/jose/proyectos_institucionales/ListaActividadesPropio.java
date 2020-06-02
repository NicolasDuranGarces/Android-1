package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jose.proyectos_institucionales.controlador.CtlActividad;
import com.jose.proyectos_institucionales.modelo.Actividad;
import com.jose.proyectos_institucionales.modelo.Cargo;
import com.jose.proyectos_institucionales.modelo.Proyecto;

import java.util.ArrayList;

public class ListaActividadesPropio extends AppCompatActivity {

    ListView listaViewActividades;
    CtlActividad controladorActividad;
    Proyecto proyecto;
    ArrayList<Actividad> actividades;
    Actividad actividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_actividades_propio);

        listaViewActividades = findViewById(R.id.listViewActividades);

        Bundle bundle = getIntent().getExtras();
        proyecto = (Proyecto) bundle.getSerializable("objProyecto");

        controladorActividad = new CtlActividad(this);

        actividades = (ArrayList<Actividad>) controladorActividad.listarActividadesProyecto(proyecto.getId());

        if (actividades.size()!=0){
            cargarLista(actividades);
        }

    }

    public void cargarLista(ArrayList<Actividad> lista) {
        ArrayList<String> nombreActividades = new ArrayList<>();
        String entrada;
        for (Actividad actividad : lista){
            entrada=actividad.getNombre();
            nombreActividades.add(entrada);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreActividades);
        listaViewActividades.setAdapter(adapter);
        listaViewActividades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                detalleActividadPropio(posicion);
            }
        });

    }

    public void registrarActividad(View view){
        Intent intent = new Intent(this , RegistrarActividad.class);
        intent.putExtra("objProyecto", proyecto);
        startActivity(intent);
    }

    public void detalleActividadPropio(int pos){

        actividad = controladorActividad.buscarActividad(actividades.get(pos).getId());

        Intent intent = new Intent(this , DetalleActividadPropia.class);
        intent.putExtra("objProyecto", proyecto);
        intent.putExtra("objActividad", actividad);
        startActivity(intent);
    }

    public void regresar(View view){
        Intent intent = new Intent(this , ListaActividadesPropio.class);
        intent.putExtra("objProyecto", proyecto);
        startActivity(intent);
    }



}
