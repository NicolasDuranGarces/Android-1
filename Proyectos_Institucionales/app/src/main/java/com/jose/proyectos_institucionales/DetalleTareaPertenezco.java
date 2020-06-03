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

import com.jose.proyectos_institucionales.controlador.CtlTarea;
import com.jose.proyectos_institucionales.modelo.Actividad;
import com.jose.proyectos_institucionales.modelo.Proyecto;
import com.jose.proyectos_institucionales.modelo.Tarea;

public class DetalleTareaPertenezco extends AppCompatActivity {

    TextView txtNombre, txtdescripcion , txtFechaInicio, txtFechaFin,txtporcen;
    Proyecto proyecto;
    Actividad actividad;
    Tarea tarea;
    Integer m_Text;
    CtlTarea controladorTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tarea_pertenezco);

        Bundle bundle = getIntent().getExtras();
        proyecto = (Proyecto) bundle.getSerializable("objProyecto");
        actividad = (Actividad) bundle.getSerializable("objActividad");
        tarea = (Tarea) bundle.getSerializable("objTarea");

        txtNombre = findViewById(R.id.txtNombreTareaD);
        txtdescripcion = findViewById(R.id.txtDescripcionTareaD);
        txtFechaInicio = findViewById(R.id.txtFechaInicioTareaD);
        txtFechaFin = findViewById(R.id.txtFechaFinTareaD);
        txtporcen = findViewById(R.id.txtPorcentaje);

        controladorTarea = new CtlTarea(this);

        txtNombre.setText(tarea.getNombre());
        txtdescripcion.setText(tarea.getDescripcion());
        txtFechaInicio.setText(tarea.getFechaInicio());
        txtFechaFin.setText(tarea.getFechaFin());
        txtporcen.setText(tarea.getPorcentajeDesarrollado());

    }

    public void listadoDeRecusos (View view){

    }

    public void regresar (View view){
        Intent intent = new Intent(this , ListadoDeTareasPertenezco.class);
        intent.putExtra("objProyecto", proyecto);
        intent.putExtra("objActividad", actividad);
        intent.putExtra("objTarea", tarea);
        intent.putExtra("idUsuario", proyecto.getIdDirector());
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
                    controladorTarea.modificarTarea(tarea.getId(),actividad.getId(),tarea.getNombre(),actividad.getDescripcion(),actividad.getFechaInicio(),actividad.getFechaFin(),m_Text);
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
