package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void registrarUsuario(View view){
        Intent intent = new Intent(this , RegistroUsuario.class);
        startActivity(intent);
    }

    public void iniciarSesion(View view){
        Intent intent = new Intent(this , Login.class);
        startActivity(intent);
    }
}
