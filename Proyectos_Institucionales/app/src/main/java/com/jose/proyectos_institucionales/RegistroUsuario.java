package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.jose.proyectos_institucionales.controlador.CtlUsuario;

import java.util.Calendar;

public class RegistroUsuario extends AppCompatActivity {

    EditText txtNombre,txtDNI,txtApellido,txtFecha,txtCorreo,txtContrasena,txtVerificacionContrasena;
    CtlUsuario controladorUsuario;
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

    }

    public void regresar(View view){
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
    }

    public void registar(View view){

        if (!txtNombre.getText().equals("")&& !txtApellido.getText().equals("") && !txtDNI.getText().equals("")
        && !txtCorreo.getText().equals("") && !txtContrasena.getText().equals("") && !txtVerificacionContrasena.getText().equals("")){
            if (txtContrasena.getText().equals(txtVerificacionContrasena.getText())){
               // controladorUsuario.guardarUsuario(null,txtDNI.getText())
            }else {

            }
        }else{

        }

        Intent intent = new Intent(this , Login.class);
        startActivity(intent);
    }
}
