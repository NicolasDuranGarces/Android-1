package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jose.proyectos_institucionales.controlador.CtlProyecto;
import com.jose.proyectos_institucionales.controlador.CtlUsuario;
import com.jose.proyectos_institucionales.modelo.Proyecto;

public class DetalleProyectoPertenezco extends AppCompatActivity {

    TextView txtNombre,txtFechaInicio,txtFechaFin,txtPorcentaje;
    CtlProyecto controladorProyecto;
    Proyecto proyecto;
    Integer idUsuario;
    CtlUsuario controladorUsuario;
    String cedula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_proyecto_pertenezco);

        txtNombre = findViewById(R.id.txtNomProyectoPro);
        txtFechaInicio = findViewById(R.id.txtFechaInicioProy);
        txtFechaFin = findViewById(R.id.txtFechaFinProyectoy);

        txtPorcentaje = findViewById(R.id.txtPorcentaje);

        controladorProyecto = new CtlProyecto(this);
        controladorUsuario = new CtlUsuario(this);

        Bundle bundle = getIntent().getExtras();
        proyecto = (Proyecto) bundle.getSerializable("objProyecto");
        idUsuario = bundle.getInt("idUsuario");

        cedula = controladorUsuario.buscarUsuarioPorID(idUsuario).getNumeroDocumento();

        txtNombre.setText("Nombre: "+proyecto.getNombre());
        txtFechaInicio.setText("Fecha Incio: "+proyecto.getFechaInicio());
        txtFechaFin.setText("Fecha Fin: "+proyecto.getFechaFin());
        txtPorcentaje.setText("% Terminado: "+proyecto.getPorcentajeDesarrollado());
    }

    public void listadoActividades(View view){
        Intent intent = new Intent(this, listadoMisActividades.class);
        intent.putExtra("objProyecto", proyecto);
        intent.putExtra("idUsuario",idUsuario);
        startActivity(intent);
    }


    public  void regresar(View view){
        Intent intent = new Intent(this, MenuPrincipal.class);
        intent.putExtra("dni",cedula);
        startActivity(intent);
    }
}
