package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jose.proyectos_institucionales.controlador.CtlUsuario;
import com.jose.proyectos_institucionales.modelo.Usuario;

public class Login extends AppCompatActivity {

    TextView txtCorreo,txtContrasena;
    CtlUsuario controladorUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtCorreo = findViewById(R.id.txtCedula);
        txtContrasena = findViewById(R.id.txtPassword);

        controladorUsuario = new CtlUsuario(this);
    }

    public void regresar(View view){
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
    }

    public void iniciar(View view){
        if (!txtContrasena.getText().toString().equals("") && !txtCorreo.getText().toString().equals("")){
            Usuario user = controladorUsuario.buscarUsuarioCorreo(txtCorreo.getText().toString());
            if (user.getClave().equals(txtContrasena.getText().toString())){
                Toast.makeText( getApplicationContext(), "Ingreso Exitoso", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText( getApplicationContext(), "Correo / Contrasena Incorrecta", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText( getApplicationContext(), "Debe de Llenar Todos ", Toast.LENGTH_SHORT).show();

        }

    }
}
