package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jose.proyectos_institucionales.controlador.CtlProyecto;
import com.jose.proyectos_institucionales.modelo.Proyecto;

import java.util.ArrayList;

public class ListadoDeProyectoPertenezco extends AppCompatActivity {

    ListView listaProyectos;
    ArrayList<Proyecto> proyectoPertenzco;
    CtlProyecto controladorProyecto;
    Integer idUsuario;
    String cedula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_de_proyecto_pertenezco);
        listaProyectos = findViewById(R.id.listaProyectosPertenezco);
        controladorProyecto = new CtlProyecto(this);

        Bundle bundle = getIntent().getExtras();
        idUsuario = bundle.getInt("idUsuario");
        cedula = bundle.getString("dni");

        proyectoPertenzco = (ArrayList<Proyecto>) controladorProyecto.listarProyectosQueIntegro(idUsuario);

        if (proyectoPertenzco.size()!=0){
            cargarLista(proyectoPertenzco);
        }

    }

    public void cargarLista(ArrayList<Proyecto> lista) {
        ArrayList<String> nombreProyectos = new ArrayList<>();
        String entrada;
        for (Proyecto proyecto : lista){
            entrada=proyecto.getNombre() ;
            nombreProyectos.add(entrada);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreProyectos);
        listaProyectos.setAdapter(adapter);
        listaProyectos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                abrirDetalle(posicion);
            }
        });

    }

    public void abrirDetalle(int posicion){
        Intent intent = new Intent(this, DetalleProyectoPertenezco.class);
        Proyecto proyecto = proyectoPertenzco.get(posicion);
        intent.putExtra("objProyecto", proyecto);
        intent.putExtra("idUsuario",idUsuario);
        startActivity(intent);
    }

    public void regresar(){
        Intent intent = new Intent(this, MenuPrincipal.class);
        intent.putExtra("idUsuario",idUsuario);
        intent.putExtra("dni",cedula);
        startActivity(intent);
    }
}
