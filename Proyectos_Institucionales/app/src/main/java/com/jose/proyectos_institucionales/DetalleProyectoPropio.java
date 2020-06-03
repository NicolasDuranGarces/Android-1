package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jose.proyectos_institucionales.controlador.CtlProyecto;
import com.jose.proyectos_institucionales.controlador.CtlUsuario;
import com.jose.proyectos_institucionales.modelo.Proyecto;

public class DetalleProyectoPropio extends AppCompatActivity {

    TextView txtNombre,txtFechaInicio,txtFechaFin,txtPorcentaje;
    Proyecto proyecto;
    CtlProyecto controladorProyecto;
    Integer idUsuario;
    CtlUsuario controladorUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_proyecto_propio);

        txtNombre = findViewById(R.id.txtNomProyectoPro);
        txtFechaInicio = findViewById(R.id.txtFechaInicioProy);
        txtFechaFin = findViewById(R.id.txtFechaFinProyectoy);

        txtPorcentaje = findViewById(R.id.txtPorcentaje);

        controladorProyecto = new CtlProyecto(this);
        controladorUsuario = new CtlUsuario(this);

        Bundle bundle = getIntent().getExtras();
        proyecto = (Proyecto) bundle.getSerializable("objProyecto");
        idUsuario = bundle.getInt("idUsuario");

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
        intent.putExtra("idUsuario",idUsuario);
        startActivity(intent);
    }

    public void gestionRecursos(View view){
        Intent intent = new Intent(this, RegistroRecursos.class);
        intent.putExtra("objProyecto", proyecto);
        startActivity(intent);
    }
    public  void regresar(View view){
        String cedula = controladorUsuario.buscarUsuarioPorID(idUsuario).getNumeroDocumento();
        Intent intent = new Intent(this, misProyectos.class);
        intent.putExtra("idUsuario",idUsuario);
        intent.putExtra("dni",cedula);
        startActivity(intent);
    }

}
