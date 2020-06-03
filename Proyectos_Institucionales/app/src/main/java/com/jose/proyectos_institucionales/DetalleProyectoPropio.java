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

import com.jose.proyectos_institucionales.controlador.CtlProyecto;
import com.jose.proyectos_institucionales.controlador.CtlUsuario;
import com.jose.proyectos_institucionales.modelo.Cargo;
import com.jose.proyectos_institucionales.modelo.Proyecto;
import com.jose.proyectos_institucionales.modelo.Usuario;

public class DetalleProyectoPropio extends AppCompatActivity {

    TextView txtNombre,txtFechaInicio,txtFechaFin,txtPorcentaje;
    Proyecto proyecto;
    CtlProyecto controladorProyecto;
    Integer idUsuario;
    CtlUsuario controladorUsuario;
    AlertDialog.Builder ad;

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
    public void modificarProyecto(View view){
        Intent intent = new Intent(this, CrearProyecto.class);
        intent.putExtra("idUsuario",idUsuario);
        intent.putExtra("objProyecto",proyecto);
        startActivity(intent);
    }
    public void eliminarProyecto(View view){

        final Context context = this;
        ad = new AlertDialog.Builder(context);
        ad.setTitle("Eliminar Proyecto");
        ad.setMessage("Desea Eliminar El Proyecto \n"+
                proyecto.getNombre());
        ad.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                controladorProyecto.eliminarProyecto(proyecto.getId());
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

    public void atras(){
        String cedula = controladorUsuario.buscarUsuarioPorID(idUsuario).getNumeroDocumento();
        Intent intent = new Intent(this, misProyectos.class);
        intent.putExtra("idUsuario",idUsuario);
        intent.putExtra("dni",cedula);
        startActivity(intent);
    }


}
