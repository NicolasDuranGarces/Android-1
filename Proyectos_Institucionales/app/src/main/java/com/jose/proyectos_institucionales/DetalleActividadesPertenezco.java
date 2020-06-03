package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jose.proyectos_institucionales.controlador.CtlActividad;
import com.jose.proyectos_institucionales.controlador.CtlProyecto;
import com.jose.proyectos_institucionales.controlador.CtlUsuario;
import com.jose.proyectos_institucionales.modelo.Actividad;
import com.jose.proyectos_institucionales.modelo.Proyecto;

import java.util.ArrayList;

public class DetalleActividadesPertenezco extends AppCompatActivity {

    TextView txtNombre,txtDescripcion,txtFechaInicio,TxtFechaFin,txtResponsable,txtProcentaje;
    Actividad actividad;
    Proyecto proyecto;
    CtlUsuario controladorUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_actividades_pertenezco);

        controladorUsuario = new CtlUsuario(this);

        Bundle bundle = getIntent().getExtras();
        proyecto = (Proyecto) bundle.getSerializable("objProyecto");
        actividad = (Actividad) bundle.getSerializable("objActividad");

        txtNombre = findViewById(R.id.txtNomnbreActividadPertenezco);
        txtDescripcion = findViewById(R.id.txtDescripcionD);
        txtFechaInicio = findViewById(R.id.txtFechaInicioActividadD);
        TxtFechaFin = findViewById(R.id.txtFechaFinActividadD);
        txtResponsable = findViewById(R.id.txtResponsableD);
        txtProcentaje = findViewById(R.id.txtPorcentajeDA);

        txtNombre.setText(actividad.getNombre());
        txtDescripcion.setText(actividad.getDescripcion());
        txtFechaInicio.setText(actividad.getFechaInicio());
        TxtFechaFin.setText(actividad.getFechaFin());
        txtResponsable.setText(controladorUsuario.buscarUsuarioPorID(actividad.getIdResponsable()).getNombres() + " " + controladorUsuario.buscarUsuarioPorID(actividad.getIdResponsable()).getApellidos());

    }

    public void listarTareas(View view){
        Intent intent = new Intent(this , ListadoDeTareasPertenezco.class);
        intent.putExtra("objProyecto", proyecto);
        intent.putExtra("objActividad", actividad);
        startActivity(intent);
    }

    public void regresar(View view){
        Intent intent = new Intent(this , DetalleProyectoPropio.class);
        intent.putExtra("objProyecto",proyecto);
        intent.putExtra("objActividad",actividad);
        startActivity(intent);
    }
}
