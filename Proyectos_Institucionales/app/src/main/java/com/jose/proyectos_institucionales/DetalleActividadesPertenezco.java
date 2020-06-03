package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    Integer idUsuario;
    CtlActividad conttroladorActividad;
    private Integer m_Text ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_actividades_pertenezco);

        controladorUsuario = new CtlUsuario(this);
        conttroladorActividad = new CtlActividad(this);

        Bundle bundle = getIntent().getExtras();
        proyecto = (Proyecto) bundle.getSerializable("objProyecto");
        actividad = (Actividad) bundle.getSerializable("objActividad");
        idUsuario = bundle.getInt("idUsuario");

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
        intent.putExtra("idUsuario", idUsuario);
        startActivity(intent);
    }

    public void regresar(View view){
        Intent intent = new Intent(this , listadoMisActividades.class);
        intent.putExtra("objProyecto",proyecto);
        intent.putExtra("objActividad",actividad);
        intent.putExtra("idUsuario",idUsuario);
        startActivity(intent);
    }

    public void cambiarPorcentaje(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("% De Avance");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                m_Text = Integer.parseInt(input.getText().toString());

                if (m_Text>=0 || m_Text<=100){
                    conttroladorActividad.modificarActividad(actividad.getId(),proyecto.getId(),actividad.getNombre(),actividad.getDescripcion(),actividad.getIdResponsable(),
                            actividad.getFechaInicio(),actividad.getFechaFin(),m_Text);
                }else {
                    Toast.makeText(getApplicationContext(), "El Numero no Es valido", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
