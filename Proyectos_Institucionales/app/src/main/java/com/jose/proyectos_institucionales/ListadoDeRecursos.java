package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jose.proyectos_institucionales.controlador.CtlRecurso;
import com.jose.proyectos_institucionales.modelo.Actividad;
import com.jose.proyectos_institucionales.modelo.Integrante;
import com.jose.proyectos_institucionales.modelo.Proyecto;
import com.jose.proyectos_institucionales.modelo.Recurso;
import com.jose.proyectos_institucionales.modelo.Tarea;

import java.util.ArrayList;

public class ListadoDeRecursos extends AppCompatActivity {

    ListView listaRecursos;
    Proyecto proyecto;
    Actividad actividad;
    Tarea tarea;

    CtlRecurso controladorRecurso;
    ArrayList<Recurso> recursos;

    AlertDialog.Builder ad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_de_recursos);

        controladorRecurso = new CtlRecurso(this);

        listaRecursos = findViewById(R.id.listaViewRecuersos);

        Bundle bundle = getIntent().getExtras();
        proyecto = (Proyecto) bundle.getSerializable("objProyecto");
        actividad = (Actividad) bundle.getSerializable("objActividad");
        tarea = (Tarea) bundle.getSerializable("objTarea");

        recursos = (ArrayList<Recurso>) controladorRecurso.listarRecursosProyecto(proyecto.getId());

        if (recursos.size()!= 0){
            cargarLista(recursos);
        }else {
            final Context context = this;
            ad = new AlertDialog.Builder(context);
            ad.setTitle("Error");
            ad.setMessage("No hay Ningun Recurso , Desea Agregar ? ");
            ad.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    registrarRecurso();
                }
            });
            ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    dialog.cancel();
                }
            });
            ad.show();
        }

    }

    public void cargarLista(final ArrayList<Recurso> lista) {
        ArrayList<String> nombreRecurso = new ArrayList<>();
        String entrada;
        for (Recurso recurso : lista){
            entrada=recurso.getNombre();
            nombreRecurso.add(entrada);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreRecurso);
        listaRecursos.setAdapter(adapter);
        listaRecursos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                elegir(lista.get(posicion).getId());
            }
        });

    }

    public void elegir(Integer pos){
        final Recurso re = controladorRecurso.buscarRecurso(pos);
        final Context context = this;
        ad = new AlertDialog.Builder(context);
        ad.setTitle("Recurso");
        ad.setMessage("Desea Vincular El Recurso " + re.getNombre());
        ad.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                controladorRecurso.guardarRecursoTarea(tarea.getId(),re.getId());
                regresarMetodo();

            }
        });
        ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });
        ad.show();
    }

    public void regresar (View view){
        regresarMetodo();
    }

    public void regresarMetodo(){
        Toast.makeText( getApplicationContext(), "Se Registro Con Exito", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this , DetalleTarea.class);
        intent.putExtra("objProyecto", proyecto);
        intent.putExtra("objActividad", actividad);
        intent.putExtra("objTarea", tarea);
        startActivity(intent);
    }

    public void registrarRecurso(){
        Intent intent = new Intent(this, RegistroRecursos.class);
        intent.putExtra("objProyecto", proyecto);
        startActivity(intent);
    }


}
