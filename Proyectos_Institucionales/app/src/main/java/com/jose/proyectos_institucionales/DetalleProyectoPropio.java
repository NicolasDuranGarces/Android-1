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
    Integer idProyecto;
    CtlProyecto controladorProyecto;
    Proyecto pro;
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
        idProyecto = bundle.getInt("idProyecto");

        pro = controladorProyecto.buscarProyecto(idProyecto);

        txtNombre.setText("Nombre: "+pro.getNombre());
        txtFechaInicio.setText("Fecha Incio: "+pro.getFechaInicio());
        txtFechaFin.setText("Fecha Fin: "+pro.getFechaFin());
        txtPorcentaje.setText("% Terminado: "+pro.getPorcentajeDesarrollado());

    }

    public void gestionIntegranes(View view){
        Intent intent = new Intent(this, GestionIntegrantesProyecto.class);
        intent.putExtra("idProyecto", idProyecto);
        startActivity(intent);
    }
}
