package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jose.proyectos_institucionales.controlador.CtlRecurso;
import com.jose.proyectos_institucionales.controlador.CtlTarea;
import com.jose.proyectos_institucionales.modelo.Actividad;
import com.jose.proyectos_institucionales.modelo.Proyecto;
import com.jose.proyectos_institucionales.modelo.Tarea;

public class RegistroRecursos extends AppCompatActivity {

    EditText txtnombre,txtDescripcion , txtCantidad , txtUbicacion;
    CtlRecurso controladorRecurso;
    Proyecto proyecto;
    Actividad actividad;
    Tarea tarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_recursos);

        Bundle bundle = getIntent().getExtras();
        proyecto = (Proyecto) bundle.getSerializable("objProyecto");

        controladorRecurso = new CtlRecurso(this);

        txtnombre = findViewById(R.id.txtNombreRecurso);
        txtDescripcion = findViewById(R.id.txtDescripcionRecurso);
        txtCantidad = findViewById(R.id.txtCantidadRecurso);
        txtUbicacion = findViewById(R.id.txtUbiacionRecurso);

    }
    
    public void registrarRecurso(View view){
        if (!txtnombre.getText().toString().equals("")&&!txtDescripcion.getText().toString().equals("")&&!txtCantidad.getText().toString().equals("")
        &&!txtUbicacion.getText().toString().equals("")){
            controladorRecurso.guardarRecurso(proyecto.getId(),txtnombre.getText().toString(),Integer.parseInt(txtCantidad.getText().toString()),txtDescripcion.getText().toString(),txtUbicacion.getText().toString());
            Toast.makeText( getApplicationContext(), "Registro Exitoso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, DetalleProyectoPropio.class);
            intent.putExtra("objProyecto", proyecto);
            startActivity(intent);
        }else{
            Toast.makeText( getApplicationContext(), "Todos Los datos son Obligatorios", Toast.LENGTH_SHORT).show();
        }
    }

    public void regresar(View view){
        Intent intent = new Intent(this, DetalleProyectoPropio.class);
        intent.putExtra("objProyecto", proyecto);
        startActivity(intent);
    }
}
