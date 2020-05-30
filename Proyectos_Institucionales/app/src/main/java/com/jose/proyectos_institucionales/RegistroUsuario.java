package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegistroUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
    }

    public void regresar(View view){
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
    }

    public void registar(View view){

        Intent intent = new Intent(this , Login.class);
        startActivity(intent);
    }
}
