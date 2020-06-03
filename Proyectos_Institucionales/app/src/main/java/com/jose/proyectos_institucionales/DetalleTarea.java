package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jose.proyectos_institucionales.modelo.Actividad;
import com.jose.proyectos_institucionales.modelo.Proyecto;
import com.jose.proyectos_institucionales.modelo.Tarea;

public class DetalleTarea extends AppCompatActivity {

    TextView txtNombre, txtdescripcion , txtFechaInicio, txtFechaFin;
    Proyecto proyecto;
    Actividad actividad;
    Tarea tarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tarea);

        Bundle bundle = getIntent().getExtras();
        proyecto = (Proyecto) bundle.getSerializable("objProyecto");
        actividad = (Actividad) bundle.getSerializable("objActividad");
        tarea = (Tarea) bundle.getSerializable("objTarea");

        txtNombre = findViewById(R.id.txtNombreTareaD);
        txtdescripcion = findViewById(R.id.txtDescripcionTareaD);
        txtFechaInicio = findViewById(R.id.txtFechaInicioTareaD);
        txtFechaFin = findViewById(R.id.txtFechaFinTareaD);

        txtNombre.setText(tarea.getNombre());
        txtdescripcion.setText(tarea.getDescripcion());
        txtFechaInicio.setText(tarea.getFechaInicio());
        txtFechaFin.setText(tarea.getFechaFin());


    }

    public void listadoDeRecusos (View view){
        Intent intent = new Intent(this , ListadoDeRecursos.class);
        intent.putExtra("objProyecto", proyecto);
        intent.putExtra("objActividad", actividad);
        intent.putExtra("objTarea", tarea);
        startActivity(intent);
    }

    public void regresar (View view){
        Intent intent = new Intent(this , ListadoDeTareasPropias.class);
        intent.putExtra("objProyecto", proyecto);
        intent.putExtra("objActividad", actividad);
        intent.putExtra("objTarea", tarea);
        intent.putExtra("idUsuario", proyecto.getIdDirector());
        startActivity(intent);
    }


}
