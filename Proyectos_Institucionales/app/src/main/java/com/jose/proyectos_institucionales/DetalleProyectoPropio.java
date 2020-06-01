package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jose.proyectos_institucionales.controlador.CtlProyecto;
import com.jose.proyectos_institucionales.modelo.Proyecto;

public class DetalleProyectoPropio extends AppCompatActivity {

    TextView txtNombre,txtFechaInicio,txtFechaFin,txtPorcentaje,txtTiempo;
    Proyecto proyecto;
    CtlProyecto controladorProyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_proyecto_propio);
        txtNombre = findViewById(R.id.txtNombreProyect);
        txtFechaInicio = findViewById(R.id.txtFechaInicioPro);
        txtFechaFin = findViewById(R.id.txtFechaFinProyecto);
        txtTiempo = findViewById(R.id.txtTiempo);
        txtPorcentaje = findViewById(R.id.txtNombreProyect);

        controladorProyecto = new CtlProyecto(this);

        Bundle bundle = getIntent().getExtras();
        proyecto = (Proyecto) bundle.getSerializable("objProyecto");

        txtNombre.setText("Nombre: "+proyecto.getNombre());
        txtFechaInicio.setText("Fecha Incio: "+proyecto.getFechaInicio());
        txtFechaFin.setText("Fecha Fin: "+proyecto.getFechaFin());
        txtPorcentaje.setText("% Terminado: "+proyecto.getPorcentajeDesarrollado());

    }

    public void gestionIntegranes(View view){
        Intent intent = new Intent(this, GestionIntegrantesProyecto.class);
        intent.putExtra("objProyecto", proyecto);
        startActivity(intent);
    }

    public void gestionCargo(View view){
        Intent intent = new Intent(this, ListadoDeCargos.class);
        intent.putExtra("objProyecto", proyecto);
        startActivity(intent);
    }

    public void gestionActividad(View view){
        Intent intent = new Intent(this, ListaActividadesPropio.class);
        intent.putExtra("objProyecto", proyecto);
        startActivity(intent);
    }
}
