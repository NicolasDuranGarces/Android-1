package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jose.proyectos_institucionales.controlador.CtlCargo;
import com.jose.proyectos_institucionales.modelo.Proyecto;

import java.util.ArrayList;

public class GestionDeCargos extends AppCompatActivity {
    EditText txtNombre,txtSalario,txtHorario,txtDescripcion;
    CtlCargo controlaroCargo;
    Proyecto proyecto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_de_cargos);

        txtNombre = findViewById(R.id.txtNombreCargo);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtSalario = findViewById(R.id.txtSalario);
        txtHorario = findViewById(R.id.txtHorario);

        controlaroCargo = new CtlCargo(this);

        Bundle bundle = getIntent().getExtras();
        proyecto = (Proyecto) bundle.getSerializable("objProyecto");

    }

    public void regresar(View view){
        Intent intent = new Intent(this , DetalleProyectoPropio.class);
        startActivity(intent);
    }

    public void registroCargo(View view){
        if (!txtNombre.getText().toString().equals("")&& !txtSalario.getText().toString().equals("")
        && !txtHorario.getText().toString().equals("") && !txtDescripcion.getText().toString().equals("")){


            controlaroCargo.guardarCargo(proyecto.getId(),txtNombre.getText().toString(),txtDescripcion.getText().toString(),
                    txtHorario.getText().toString(),Integer.parseInt(txtSalario.getText().toString()));
            Toast.makeText( getApplicationContext(), "Se Registro Correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this , DetalleProyectoPropio.class);
            intent.putExtra("objProyecto", proyecto);
            startActivity(intent);

        }else {
            Toast.makeText( getApplicationContext(), "Todos Los Campos Son Obligatorios", Toast.LENGTH_SHORT).show();
        }
    }


}
