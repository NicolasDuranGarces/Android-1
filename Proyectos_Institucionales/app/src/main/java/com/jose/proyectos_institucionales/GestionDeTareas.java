package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.jose.proyectos_institucionales.controlador.CtlTarea;
import com.jose.proyectos_institucionales.modelo.Actividad;
import com.jose.proyectos_institucionales.modelo.Proyecto;

import java.util.Calendar;

public class GestionDeTareas extends AppCompatActivity {

    EditText txtnombre,txtfechaInicio,txtfechaFin,txtDescripcion;
    Proyecto proyecto;
    Actividad actividad;
    CtlTarea controladorTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_de_tareas);

        Bundle bundle = getIntent().getExtras();
        proyecto = (Proyecto) bundle.getSerializable("objProyecto");
        actividad = (Actividad) bundle.getSerializable("objActividad");

        controladorTarea = new CtlTarea(this);

        txtnombre = findViewById(R.id.txtNombreTarea);
        txtfechaInicio = findViewById(R.id.txtFechaIniciotarea);
        txtfechaFin =  findViewById(R.id.txtFechaFinTarea);
        txtDescripcion = findViewById(R.id.txtDescripcionTarea);


        txtfechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                DatePickerDialog mDatePicker = new DatePickerDialog(GestionDeTareas.this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker datepicker, int selectedyear,
                                                  int selectedmonth, int selectedday) {
                                txtfechaInicio.setText(selectedyear + "/" + selectedmonth + "/" + selectedday);
                            }
                        }, mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH),
                        mcurrentDate.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        txtfechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                DatePickerDialog mDatePicker = new DatePickerDialog(GestionDeTareas.this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker datepicker, int selectedyear,
                                                  int selectedmonth, int selectedday) {
                                txtfechaFin.setText(selectedyear + "/" + selectedmonth + "/" + selectedday);
                            }
                        }, mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH),
                        mcurrentDate.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });


    }

    public void registrar (View view){
        if (!txtnombre.getText().toString().equals("") && !txtfechaInicio.getText().toString().equals("")&&!txtfechaFin.getText().toString().equals("")){
            controladorTarea.guardarTarea(actividad.getId(),txtnombre.getText().toString(),txtDescripcion.getText().toString(),txtfechaInicio.getText().toString(),
                    txtfechaFin.getText().toString(),0);
            Toast.makeText( getApplicationContext(), "Registro Exitoso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this , DetalleActividadPropia.class);
            intent.putExtra("objProyecto", proyecto);
            intent.putExtra("objActividad", actividad);
            startActivity(intent);

        }else {
            Toast.makeText( getApplicationContext(), "Todos Los Campos Son Obligatorios", Toast.LENGTH_SHORT).show();

        }

    }

    public void regresar (View view){
        Intent intent = new Intent(this , DetalleActividadPropia.class);
        intent.putExtra("objProyecto", proyecto);
        intent.putExtra("objActividad", actividad);
        startActivity(intent);
    }

}
