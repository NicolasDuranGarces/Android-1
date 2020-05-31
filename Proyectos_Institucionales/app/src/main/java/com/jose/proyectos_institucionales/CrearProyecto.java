package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jose.proyectos_institucionales.controlador.CtlProyecto;
import com.jose.proyectos_institucionales.controlador.CtlUsuario;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CrearProyecto extends AppCompatActivity {

    EditText txtNombre,txtFechaInicio,txtFechaFin;
    TextView tiempoProyecto;
    CtlProyecto controladorProyecto;
    Integer idUsuario;
    String cedula;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_proyecto);

        txtNombre = findViewById(R.id.txtNombrePro);
        txtFechaInicio = findViewById(R.id.txtFechaInicio);
        txtFechaFin = findViewById(R.id.txtFechaFin);
        tiempoProyecto = findViewById(R.id.txtTiempo);

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



    }

    public void crearProyecto(View view){

        calcularTiempo();

        if (!txtNombre.getText().toString().equals("")&&!txtFechaFin.getText().toString().equals("")&&!txtFechaInicio.getText().toString().equals("")){
            controladorProyecto.guardarProyecto(txtNombre.getText().toString(),idUsuario,txtFechaInicio.getText().toString(),txtFechaFin.getText().toString(),0);
            Toast.makeText( getApplicationContext(), "Se Registro Correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this , misProyectos.class);
            startActivity(intent);
        }else{
            Toast.makeText( getApplicationContext(), "Todos los Campos Son Obligatorios", Toast.LENGTH_SHORT).show();

        }


    }

    public void regresar(View view){
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
    }

    public void calcularTiempo(){

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());

            Date dateStart = dateFormat.parse(txtFechaInicio.getText().toString());
            Date dateEnd = dateFormat.parse(txtFechaFin.getText().toString());

            long difference = Math.abs(dateEnd.getTime() - dateStart.getTime());

            difference= difference / (60 * 60 * 1000);
            tiempoProyecto.setText((int) difference);

        }catch(Exception e){
            e.printStackTrace();
        }

    }




}
