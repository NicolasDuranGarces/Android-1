package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jose.proyectos_institucionales.controlador.CtlUsuario;
import com.jose.proyectos_institucionales.modelo.Actividad;
import com.jose.proyectos_institucionales.modelo.Proyecto;

public class DetalleActividadPropia extends AppCompatActivity {

    TextView txtNombre,txtDescripcion,txtFechaInicio,TxtFechaFin,txtResponsable;
    Actividad actividad;
    Proyecto proyecto;
    CtlUsuario controladorUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_actividad_propia);

        controladorUsuario = new CtlUsuario(this);

        Bundle bundle = getIntent().getExtras();
        proyecto = (Proyecto) bundle.getSerializable("objProyecto");
        actividad = (Actividad) bundle.getSerializable("objActividad");

        txtNombre = findViewById(R.id.txtNomnbreActividadD);
        txtDescripcion = findViewById(R.id.txtDescripcionD);
        txtFechaInicio = findViewById(R.id.txtFechaInicioActividadD);
        TxtFechaFin = findViewById(R.id.txtFechaFinActividadD);
        txtResponsable = findViewById(R.id.txtResponsableD);

        txtNombre.setText(actividad.getNombre());
        txtDescripcion.setText(actividad.getDescripcion());
        txtFechaInicio.setText(actividad.getFechaInicio());
        TxtFechaFin.setText(actividad.getFechaFin());
        txtResponsable.setText(controladorUsuario.buscarUsuarioPorID(actividad.getIdResponsable()).getNombres()+" "+controladorUsuario.buscarUsuarioPorID(actividad.getIdResponsable()).getApellidos());

    }

    public void listarTareas(View view){
        Intent intent = new Intent(this , RegistrarActividad.class);
        intent.putExtra("objProyecto", proyecto);
        intent.putExtra("objActividad", actividad);
        startActivity(intent);
    }
}
