package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void regresar(View view){
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
    }

    public void iniciar(View view){
        Intent intent = new Intent(this , Login.class);
        startActivity(intent);
    }
}
