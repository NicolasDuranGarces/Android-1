package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jose.proyectos_institucionales.controlador.CtlActividad;
import com.jose.proyectos_institucionales.controlador.CtlCargo;
import com.jose.proyectos_institucionales.controlador.CtlIntegrante;
import com.jose.proyectos_institucionales.controlador.CtlUsuario;
import com.jose.proyectos_institucionales.modelo.Cargo;
import com.jose.proyectos_institucionales.modelo.Integrante;
import com.jose.proyectos_institucionales.modelo.Proyecto;
import com.jose.proyectos_institucionales.modelo.Usuario;

import java.util.ArrayList;
import java.util.Calendar;

public class RegistrarActividad extends AppCompatActivity {

    EditText txtnombre,txtDescripcion,txtfechaInicio,txtFechaFin;
    Spinner spinerResponsables;
    CtlIntegrante controladorIntegrantes;
    CtlUsuario controladorUsuario;
    CtlActividad controladorActividad;
    CtlCargo controladorCargo;
    Proyecto proyecto;
    ArrayList<Integrante> integrantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_actividad);

        txtnombre = findViewById(R.id.txtNombreActividad);
        txtDescripcion = findViewById(R.id.txtDescripcionActividad);
        txtfechaInicio = findViewById(R.id.txtFechaIncioActividad);
        txtFechaFin = findViewById(R.id.txtFechaFinActividad);

        controladorIntegrantes = new CtlIntegrante(this);
        controladorUsuario = new CtlUsuario(this);
        controladorActividad = new CtlActividad(this);
        controladorCargo = new CtlCargo(this);

        spinerResponsables = findViewById(R.id.spinnerResponsable);

        txtfechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                DatePickerDialog mDatePicker = new DatePickerDialog(RegistrarActividad.this,
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

        txtFechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                DatePickerDialog mDatePicker = new DatePickerDialog(RegistrarActividad.this,
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
        proyecto = (Proyecto) bundle.getSerializable("objProyecto");
        integrantes = (ArrayList<Integrante>) controladorIntegrantes.listarIntegrantesProyecto(proyecto.getId());
        cargarOpciones();
    }

    public void registrar(View view){
        if(!txtnombre.getText().toString().equals("")&&!txtDescripcion.getText().toString().equals("")&&
                !txtfechaInicio.getText().toString().equals("")&&!txtFechaFin.getText().toString().equals("")){
                    int id = idResponsable(spinerResponsables.getSelectedItem().toString());
                controladorActividad.guardarActividad(proyecto.getId(),txtnombre.getText().toString(),txtDescripcion.getText().toString(),
                        id,txtfechaInicio.getText().toString(),
                        txtFechaFin.getText().toString(),0);
            Toast.makeText( getApplicationContext(), "Se Registro Con Exito", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this , DetalleProyectoPropio.class);
            intent.putExtra("objProyecto", proyecto);
            startActivity(intent);

        }else {
            Toast.makeText( getApplicationContext(), "Todos Los Campos Son Obligatorios", Toast.LENGTH_SHORT).show();
        }

    }

    public void cargarOpciones(){

        ArrayList<String> nombreIntegrantes = new ArrayList<>();
        Usuario integrante;
        String entrada;
        Cargo cargo;
        for (int i = 0 ; i < integrantes.size(); i ++){
            integrante = controladorUsuario.buscarUsuarioPorID(integrantes.get(i).getIdUsuario());
            cargo = controladorCargo.buscarCargo(integrantes.get(i).getId());
            entrada = integrante.getId() +"-"+integrante.getNombres()+"-"+integrante.getApellidos()+"-"+cargo.getNombre();
            nombreIntegrantes.add(entrada);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,nombreIntegrantes);
        spinerResponsables.setAdapter(adapter);
    }

    public void regresar(View view){
        Intent intent = new Intent(this , ListaActividadesPropio.class);
        intent.putExtra("objProyecto", proyecto);
        startActivity(intent);
    }

    public Integer idResponsable (String entradaSpinner){

        for (Integrante integrante : integrantes){
            Usuario usuario = controladorUsuario.buscarUsuarioPorID(integrante.getIdUsuario());
            Cargo cargo = controladorCargo.buscarCargo(integrante.getIdCargo());
            String entrada = integrante.getIdUsuario() +"-"+usuario.getNombres()+"-"+usuario.getApellidos()+"-"+cargo.getNombre();
            if (entrada.equals(entradaSpinner)){
                return  integrante.getIdUsuario();
            }
        }
        return null;
    }

}
