package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jose.proyectos_institucionales.controlador.CtlUsuario;
import com.jose.proyectos_institucionales.modelo.Usuario;

public class MenuPrincipal extends AppCompatActivity {

    TextView txtNombrePersona;
    CtlUsuario controladorUsuario;
    String cedula;
    Integer idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        txtNombrePersona = findViewById(R.id.txtNombrePersona);
        controladorUsuario = new CtlUsuario(this);
        Bundle bundle = getIntent().getExtras();
        cedula = bundle.getString("dni");
        Usuario usuario = controladorUsuario.buscarUsuarioCedula(cedula);

        txtNombrePersona.setText(usuario.getNombres() + " " + usuario.getApellidos());
        idUsuario = usuario.getId();
    }

    public void misProyectos(View view) {
        Intent intent = new Intent(this , misProyectos.class);
        intent.putExtra("dni",cedula);
        intent.putExtra("idUsuario",idUsuario);
        startActivity(intent);

    }

    public void proyectosQueHagoParte(View view) {
        Intent intent = new Intent(this , ListadoDeProyectoPertenezco.class);
        intent.putExtra("dni",cedula);
        intent.putExtra("idUsuario",idUsuario);
        startActivity(intent);
    }

    public void editarDatos(View view) {
        Intent intent = new Intent(this , RegistroUsuario.class);
        intent.putExtra("dni",cedula);
        startActivity(intent);
    }

    public void salir(View view){
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
        this.finish();

    }


}
