package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jose.proyectos_institucionales.controlador.CtlTarea;
import com.jose.proyectos_institucionales.modelo.Actividad;
import com.jose.proyectos_institucionales.modelo.Proyecto;
import com.jose.proyectos_institucionales.modelo.Tarea;

import java.util.ArrayList;

public class ListadoDeTareasPertenezco extends AppCompatActivity {

    Proyecto proyecto;
    Actividad actividad;

    CtlTarea controladorTarea;

    ListView listViewTareas;
    ArrayList<Tarea> listaTareas ;
    Integer idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_de_tareas_pertenezco);


        controladorTarea = new CtlTarea(this);

        Bundle bundle = getIntent().getExtras();
        proyecto = (Proyecto) bundle.getSerializable("objProyecto");
        actividad = (Actividad) bundle.getSerializable("objActividad");
        idUsuario = bundle.getInt("idUsuario");
        listViewTareas = findViewById(R.id.listaTareasView);


        listaTareas = (ArrayList<Tarea>) controladorTarea.listarTareasActividad(actividad.getId());

        if (listaTareas.size() != 0){
            cargarLista(listaTareas);
        }
    }

    public void cargarLista(ArrayList<Tarea> lista) {
        ArrayList<String> nombreTareas = new ArrayList<>();
        String entrada;
        for (Tarea tarea : lista){
            entrada=tarea.getNombre();
            nombreTareas.add(entrada);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreTareas);
        listViewTareas.setAdapter(adapter);
        listViewTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                datalleTarea(posicion);
            }
        });

    }

    public  void datalleTarea(int posicion){
        Tarea tarea = listaTareas.get(posicion);
        Intent intent = new Intent(this , DetalleTareaPertenezco.class);
        intent.putExtra("objProyecto", proyecto);
        intent.putExtra("objActividad", actividad);
        intent.putExtra("objTarea", tarea);
        startActivity(intent);
    }

    public void regresar(View view){
        Intent intent = new Intent(this , DetalleActividadesPertenezco.class);
        intent.putExtra("objProyecto", proyecto);
        intent.putExtra("objActividad", actividad);
        intent.putExtra("idUsuario", idUsuario);
        startActivity(intent);
    }
}
