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

import com.jose.proyectos_institucionales.controlador.CtlActividad;
import com.jose.proyectos_institucionales.controlador.CtlUsuario;
import com.jose.proyectos_institucionales.modelo.Actividad;
import com.jose.proyectos_institucionales.modelo.Proyecto;

public class DetalleActividadPropia extends AppCompatActivity {

    TextView txtNombre,txtDescripcion,txtFechaInicio,TxtFechaFin,txtResponsable,txtProcentaje;
    Actividad actividad;
    Proyecto proyecto;
    CtlUsuario controladorUsuario;
    CtlActividad controladorActividad;
    Integer idUsuario;
    AlertDialog.Builder ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_actividad_propia);

        controladorUsuario = new CtlUsuario(this);
        controladorActividad = new CtlActividad(this);

        Bundle bundle = getIntent().getExtras();
        proyecto = (Proyecto) bundle.getSerializable("objProyecto");
        actividad = (Actividad) bundle.getSerializable("objActividad");
        idUsuario = bundle.getInt("idUsuario");

        txtNombre = findViewById(R.id.txtNomnbreActividadD);
        txtDescripcion = findViewById(R.id.txtDescripcionD);
        txtFechaInicio = findViewById(R.id.txtFechaInicioActividadD);
        TxtFechaFin = findViewById(R.id.txtFechaFinActividadD);
        txtResponsable = findViewById(R.id.txtResponsableD);
        txtProcentaje = findViewById(R.id.txtPorcentajeDA);

        txtNombre.setText(actividad.getNombre());
        txtDescripcion.setText(actividad.getDescripcion());
        txtFechaInicio.setText(actividad.getFechaInicio());
        TxtFechaFin.setText(actividad.getFechaFin());
        txtResponsable.setText(controladorUsuario.buscarUsuarioPorID(actividad.getIdResponsable()).getNombres()+" "+controladorUsuario.buscarUsuarioPorID(actividad.getIdResponsable()).getApellidos());
        txtProcentaje.setText(String.valueOf(actividad.getPorcentajeDesarrollado()));

    }

    public void listarTareas(View view){
        Intent intent = new Intent(this , ListadoDeTareasPropias.class);
        intent.putExtra("objProyecto", proyecto);
        intent.putExtra("objActividad", actividad);
        startActivity(intent);
    }

    public void regresar(View view){
        Intent intent = new Intent(this , ListaActividadesPropio.class);
        intent.putExtra("objProyecto",proyecto);
        intent.putExtra("objActividad",actividad);
        intent.putExtra("idUsuario",idUsuario);
        startActivity(intent);
    }

    public void  eliminarActividad(View view){
        final Context context = this;
        ad = new AlertDialog.Builder(context);
        ad.setTitle("Eliminar Actividad");
        ad.setMessage("Desea Eliminar la Actividad \n"+
                actividad.getNombre());
        ad.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                controladorActividad.eliminarActividad(actividad.getId());
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

    public void modificarDatos(View view){
        Intent intent = new Intent(this , RegistrarActividad.class);
        intent.putExtra("objProyecto", proyecto);
        intent.putExtra("objActividad", actividad);
        startActivity(intent);
    }

    public void atras(){
        Intent intent = new Intent(this , ListaActividadesPropio.class);
        intent.putExtra("objProyecto",proyecto);
        intent.putExtra("objActividad",actividad);
        intent.putExtra("idUsuario",idUsuario);
        startActivity(intent);
    }
}
