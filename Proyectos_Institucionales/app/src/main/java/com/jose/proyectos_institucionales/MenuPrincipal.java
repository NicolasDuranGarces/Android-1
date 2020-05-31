package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.jose.proyectos_institucionales.controlador.CtlUsuario;
import com.jose.proyectos_institucionales.modelo.Usuario;

public class MenuPrincipal extends AppCompatActivity {

    TextView txtNombrePersona;
    CtlUsuario controladorUsuario;
    String cedula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        txtNombrePersona = findViewById(R.id.txtNombrePersona);
        controladorUsuario = new CtlUsuario(this);
        Bundle bundle = getIntent().getExtras();
        cedula = bundle.getString("dni");
        Usuario usuario = controladorUsuario.buscarUsuarioCedula(cedula);
        txtNombrePersona.setText(usuario.getNombres() +" "+usuario.getApellidos());
    }
}
