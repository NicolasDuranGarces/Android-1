package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.jose.proyectos_institucionales.controlador.CtlUsuario;
import com.jose.proyectos_institucionales.modelo.Usuario;

import java.util.Calendar;

public class RegistroUsuario extends AppCompatActivity {

    EditText txtNombre,txtDNI,txtApellido,txtFecha,txtCorreo,txtContrasena,txtVerificacionContrasena;
    CtlUsuario controladorUsuario;
    String idUsuario;
    Button btnRegistrar,btnActilizar;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        txtNombre = findViewById(R.id.txtNombre);
        txtDNI = findViewById(R.id.txtDNI);
        txtApellido = findViewById(R.id.txtApellido);
        txtCorreo = findViewById(R.id.txtCorreoElectronico);
        txtContrasena = findViewById(R.id.txtPassword);
        txtVerificacionContrasena = findViewById(R.id.txtValidarPassword);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnActilizar = findViewById(R.id.btnActilizar);

        txtFecha = findViewById(R.id.txtFechaNacimiento);
        txtFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                DatePickerDialog mDatePicker = new DatePickerDialog(RegistroUsuario.this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker datepicker, int selectedyear,
                                                  int selectedmonth, int selectedday) {
                                txtFecha.setText(selectedyear + "/" + selectedmonth + "/" + selectedday);
                            }
                        }, mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH),
                        mcurrentDate.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        controladorUsuario = new CtlUsuario(this);

        Bundle bundle = getIntent().getExtras();
        idUsuario = bundle.getString("idUsuario");

         usuario = controladorUsuario.buscarUsuarioCedula(idUsuario);
        if (usuario != null){
            txtFecha.setEnabled(false);
            txtDNI.setEnabled(false);
            txtCorreo.setEnabled(false);
            btnRegistrar.setVisibility(View.GONE);
        }else {
            txtFecha.setEnabled(true);
            txtDNI.setEnabled(true);
            txtCorreo.setEnabled(true);
            btnRegistrar.setVisibility(View.VISIBLE);
            btnActilizar.setVisibility(View.GONE);
        }

    }

    public void regresar(View view){
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
    }

    public void registar(View view){

        if (txtNombre.getText().toString().equals("")|| txtApellido.getText().toString().equals("") || txtDNI.getText().toString().equals("")
        && txtCorreo.getText().toString().equals("") || txtContrasena.getText().toString().equals("") || txtVerificacionContrasena.getText().toString().equals("")){
            Toast.makeText( getApplicationContext(), "Todos Los Campos Son Obligatorios", Toast.LENGTH_SHORT).show();
        }else{
            if (txtContrasena.getText().toString().equals(txtVerificacionContrasena.getText().toString())){
                controladorUsuario.guardarUsuario(txtDNI.getText().toString(),txtNombre.getText().toString(),txtApellido.getText().toString(),txtFecha.getText().toString(),txtContrasena.getText().toString(),txtCorreo.getText().toString());
                Toast.makeText( getApplicationContext(), "Se Guardo Correctamente", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this , Login.class);
                startActivity(intent);

            }else {
                Toast.makeText( getApplicationContext(), "Las Contrasenas no Coinciden", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void modifcarusuarui(View view){

        if (txtNombre.getText().toString().equals("")|| txtApellido.getText().toString().equals("") || txtDNI.getText().toString().equals("")
                && txtCorreo.getText().toString().equals("") || txtContrasena.getText().toString().equals("") || txtVerificacionContrasena.getText().toString().equals("")){
            Toast.makeText( getApplicationContext(), "Todos Los Campos Son Obligatorios", Toast.LENGTH_SHORT).show();
        }else{
            if (txtContrasena.getText().toString().equals(txtVerificacionContrasena.getText().toString())){
                controladorUsuario.modificarUsuario(usuario.getId(),txtDNI.getText().toString(),txtNombre.getText().toString(),txtApellido.getText().toString(),txtFecha.getText().toString(),txtContrasena.getText().toString(),txtCorreo.getText().toString());
                Toast.makeText( getApplicationContext(), "Se Guardo Correctamente", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this , MenuPrincipal.class);
                intent.putExtra("dni", idUsuario);
                startActivity(intent);

            }else {
                Toast.makeText( getApplicationContext(), "Las Contrasenas no Coinciden", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
