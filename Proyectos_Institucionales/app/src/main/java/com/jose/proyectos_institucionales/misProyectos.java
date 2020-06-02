package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jose.proyectos_institucionales.controlador.CtlProyecto;
import com.jose.proyectos_institucionales.controlador.CtlUsuario;
import com.jose.proyectos_institucionales.modelo.Proyecto;
import com.jose.proyectos_institucionales.modelo.Usuario;

import java.util.ArrayList;

public class misProyectos extends AppCompatActivity {
    Integer idUsuario;
    ArrayList<Proyecto> proyectos;
    ListView listaProyectos;
    CtlProyecto controladorProyecto;
    CtlUsuario controladorUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_proyectos);

        listaProyectos = findViewById(R.id.listaProyectos);

        controladorProyecto = new CtlProyecto(this);
        controladorUsuario = new CtlUsuario(this);

        Bundle bundle = getIntent().getExtras();
        idUsuario = bundle.getInt("idUsuario");

        proyectos = (ArrayList<Proyecto>) controladorProyecto.listarProyectosQueDirijo(idUsuario);

        if (proyectos.size()!=0){
            cargarLista(proyectos);
        }


    }

    public void crearProyecto(View view){
        Intent intent = new Intent(this , CrearProyecto.class);
        intent.putExtra("idUsuario",idUsuario);
        startActivity(intent);
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
        Intent intent = new Intent(this, DetalleProyectoPropio.class);
        Proyecto proyecto = proyectos.get(posicion);
        intent.putExtra("objProyecto", proyecto);
        intent.putExtra("idUsuario",idUsuario);
        startActivity(intent);
    }





}
