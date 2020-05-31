package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class misProyectos extends AppCompatActivity {
    Integer idUsuario;
    String cedula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_proyectos);

        Bundle bundle = getIntent().getExtras();
        cedula = bundle.getString("dni");
        idUsuario = bundle.getInt("idUsuario");


    }

    public void crearProyecto(View view){
        Intent intent = new Intent(this , CrearProyecto.class);
        intent.putExtra("dni",cedula);
        intent.putExtra("idUsuario",idUsuario);
        startActivity(intent);
    }
}
