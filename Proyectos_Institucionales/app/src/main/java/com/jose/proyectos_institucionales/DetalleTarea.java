package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jose.proyectos_institucionales.controlador.CtlTarea;
import com.jose.proyectos_institucionales.modelo.Actividad;
import com.jose.proyectos_institucionales.modelo.Proyecto;
import com.jose.proyectos_institucionales.modelo.Tarea;

public class DetalleTarea extends AppCompatActivity {

    TextView txtNombre, txtdescripcion , txtFechaInicio, txtFechaFin;
    Proyecto proyecto;
    Actividad actividad;
    Tarea tarea;
    AlertDialog.Builder ad;
    CtlTarea controladorTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tarea);

        Bundle bundle = getIntent().getExtras();
        proyecto = (Proyecto) bundle.getSerializable("objProyecto");
        actividad = (Actividad) bundle.getSerializable("objActividad");
        tarea = (Tarea) bundle.getSerializable("objTarea");

        controladorTarea = new CtlTarea(this);

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
        Intent intent = new Intent(this , DetalleActividadPropia.class);
        intent.putExtra("objProyecto", proyecto);
        intent.putExtra("objActividad", actividad);
        intent.putExtra("objTarea", tarea);
        startActivity(intent);
    }

    public void modificar (View view){

        Intent intent = new Intent(this , ListadoDeRecursos.class);
        intent.putExtra("objProyecto", proyecto);
        intent.putExtra("objActividad", actividad);
        intent.putExtra("objTarea", tarea);
        startActivity(intent);

    }

    public void eliminar(){

        final Context context = this;
        ad = new AlertDialog.Builder(context);
        ad.setTitle("Eliminar Proyecto");
        ad.setMessage("Desea Eliminar El Proyecto \n"+
                proyecto.getNombre());
        ad.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                controladorTarea.eliminarTarea(tarea.getId());
                Toast.makeText(getApplicationContext(), "Se Elimino Correctamente", Toast.LENGTH_SHORT).show();
                atras();
            }
        });
        ad.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });
        ad.show();


    }

    public void atras ( ){
        Intent intent = new Intent(this , DetalleActividadPropia.class);
        intent.putExtra("objProyecto", proyecto);
        intent.putExtra("objActividad", actividad);
        intent.putExtra("objTarea", tarea);
        startActivity(intent);
    }



}
