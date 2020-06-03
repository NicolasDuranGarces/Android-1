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
import com.jose.proyectos_institucionales.modelo.Proyecto;
import com.jose.proyectos_institucionales.modelo.Usuario;

import java.util.ArrayList;

public class listadoMisActividades extends AppCompatActivity {

    ListView listaActividades;
    Proyecto proyecto;
    Integer idUsuario;
    CtlActividad controladorActividad;
    ArrayList<Actividad> listaDeACtiviadePertenesco;
    Actividad actividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_mis_actividades);

        controladorActividad = new CtlActividad(this);

        Bundle bundle = getIntent().getExtras();
        proyecto = (Proyecto) bundle.getSerializable("objProyecto");
        idUsuario = bundle.getInt("idUsuario");

        listaActividades = findViewById(R.id.listaMisActividades);

        listaDeACtiviadePertenesco = (ArrayList<Actividad>) controladorActividad.listarMisActividadesProyecto(proyecto.getId(),idUsuario);

        if (listaDeACtiviadePertenesco.size()!=0){
            cargarLista(listaDeACtiviadePertenesco);
        }

    }

    public void cargarLista(ArrayList<Actividad> lista) {
        ArrayList<String> nombreActividades = new ArrayList<>();
        String entrada;
        for (Actividad actividades : lista){
            entrada=actividades.getNombre() + " " + actividades.getPorcentajeDesarrollado();
            nombreActividades.add(entrada);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreActividades);
        listaActividades.setAdapter(adapter);
        listaActividades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                detalleActividadPropio(posicion);
            }
        });

    }

    public void regresar(View view){
        Intent intent = new Intent(this , DetalleProyectoPertenezco.class);
        intent.putExtra("objProyecto", proyecto);
        intent.putExtra("idUsuario", proyecto.getIdDirector());
        startActivity(intent);
    }

    public void detalleActividadPropio(int pos){

        actividad = controladorActividad.buscarActividad(listaDeACtiviadePertenesco.get(pos).getId());

        Intent intent = new Intent(this , DetalleActividadesPertenezco.class);
        intent.putExtra("objProyecto", proyecto);
        intent.putExtra("objActividad", actividad);
        startActivity(intent);
    }
}
