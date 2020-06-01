package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jose.proyectos_institucionales.controlador.CtlCargo;

import java.util.ArrayList;

public class GestionDeCargos extends AppCompatActivity {
    EditText txtNombre,txtSalario,txtHorario,txtDescripcion;
    CtlCargo controlaroCargo;
    Integer idProyecto,idDirector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_de_cargos);
        controlaroCargo = new CtlCargo(this);
        txtNombre = findViewById(R.id.txtNombreCargo);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtSalario = findViewById(R.id.txtSalario);
        txtHorario = findViewById(R.id.txtDescripcion);

        Bundle bundle = getIntent().getExtras();
        idProyecto = bundle.getInt("idProyecto");
        idDirector = bundle.getInt("idDirector");

    }

    public void regresar(View view){
        Intent intent = new Intent(this , DetalleProyectoPropio.class);
        startActivity(intent);
    }

    public void registroCargo(View view){
        if (!txtNombre.getText().toString().equals("")&& !txtSalario.getText().toString().equals("")
        && !txtHorario.getText().toString().equals("") && !txtDescripcion.getText().toString().equals("")){
            controlaroCargo.guardarCargo(idProyecto,txtNombre.getText().toString(),txtDescripcion.getText().toString(),
                    txtHorario.getText().toString(),idDirector);
            Toast.makeText( getApplicationContext(), "Se Registro Correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this , ListadoDeCargos.class);
            startActivity(intent);

        }else {
            Toast.makeText( getApplicationContext(), "Todos Los Campos Son Obligatorios", Toast.LENGTH_SHORT).show();
        }
    }


}
