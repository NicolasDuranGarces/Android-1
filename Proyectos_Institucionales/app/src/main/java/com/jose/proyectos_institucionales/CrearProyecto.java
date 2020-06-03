package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jose.proyectos_institucionales.controlador.CtlProyecto;
import com.jose.proyectos_institucionales.controlador.CtlUsuario;
import com.jose.proyectos_institucionales.modelo.Proyecto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CrearProyecto extends AppCompatActivity {

    EditText txtNombre,txtFechaInicio,txtFechaFin;
    EditText porcentaje;
    CtlProyecto controladorProyecto;
    Integer idUsuario;
    String cedula;
    Proyecto proyecto;
    Button btnRegistrar,btnActilizar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_proyecto);

        txtNombre = findViewById(R.id.txtNombrePro);
        txtFechaInicio = findViewById(R.id.txtFechaInicio);
        txtFechaFin = findViewById(R.id.txtFechaFin);
        porcentaje = findViewById(R.id.txtPorcentajeTerminado);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnActilizar = findViewById(R.id.actualizarRegistro);

        controladorProyecto = new CtlProyecto(this);

        txtFechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                DatePickerDialog mDatePicker = new DatePickerDialog(CrearProyecto.this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker datepicker, int selectedyear,
                                                  int selectedmonth, int selectedday) {
                                txtFechaInicio.setText(selectedyear + "/" + selectedmonth + "/" + selectedday);
                            }
                        }, mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH),
                        mcurrentDate.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setTitle("Select date");
                mDatePicker.show();

            }
        });

        txtFechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                DatePickerDialog mDatePicker = new DatePickerDialog(CrearProyecto.this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker datepicker, int selectedyear,
                                                  int selectedmonth, int selectedday) {
                                txtFechaFin.setText(selectedyear + "/" + selectedmonth + "/" + selectedday);
                            }
                        }, mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH),
                        mcurrentDate.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setTitle("Select date");
                mDatePicker.show();

            }
        });

        Bundle bundle = getIntent().getExtras();
        cedula = bundle.getString("dni");
        idUsuario = bundle.getInt("idUsuario");

        proyecto = (Proyecto) bundle.getSerializable("objProyecto");

        if (proyecto != null){
            porcentaje.setVisibility(View.VISIBLE);
            btnActilizar.setVisibility(View.VISIBLE);
            btnRegistrar.setVisibility(View.GONE);
            porcentaje.setText(String.valueOf(proyecto.getPorcentajeDesarrollado()));
        }else {
            porcentaje.setVisibility(View.GONE);
            btnActilizar.setVisibility(View.GONE);
        }



    }

    public void crearProyecto(View view){


        if (!txtNombre.getText().toString().equals("")&&!txtFechaFin.getText().toString().equals("")&&!txtFechaInicio.getText().toString().equals("")){
            controladorProyecto.guardarProyecto(txtNombre.getText().toString(),idUsuario,txtFechaInicio.getText().toString(),txtFechaFin.getText().toString(),0);
            Toast.makeText( getApplicationContext(), "Se Registro Correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this , misProyectos.class);
            intent.putExtra("dni",cedula);
            intent.putExtra("idUsuario",idUsuario);
            startActivity(intent);
        }else{
            Toast.makeText( getApplicationContext(), "Todos los Campos Son Obligatorios", Toast.LENGTH_SHORT).show();

        }


    }

    public void regresar(View view){
        Intent intent = new Intent(this , misProyectos.class);
        intent.putExtra("dni",cedula);
        intent.putExtra("idUsuario",idUsuario);
        startActivity(intent);
    }

    public void modificarProyecto(View view){

        if (!txtNombre.getText().toString().equals("")&&!txtFechaFin.getText().toString().equals("")&&!txtFechaInicio.getText().toString().equals("")){
            controladorProyecto.modificarProyecto(proyecto.getId(),txtNombre.getText().toString(),idUsuario,txtFechaInicio.getText().toString(),txtFechaFin.getText().toString(),Integer.parseInt(porcentaje.getText().toString()));
            proyecto = controladorProyecto.buscarProyecto(proyecto.getId());
            Toast.makeText( getApplicationContext(), "Se Actualizo Correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this , misProyectos.class);
            intent.putExtra("dni",cedula);
            intent.putExtra("idUsuario",idUsuario);
            intent.putExtra("objProyecto",proyecto);
            startActivity(intent);
        }else{
            Toast.makeText( getApplicationContext(), "Todos los Campos Son Obligatorios", Toast.LENGTH_SHORT).show();

        }


    }


}
