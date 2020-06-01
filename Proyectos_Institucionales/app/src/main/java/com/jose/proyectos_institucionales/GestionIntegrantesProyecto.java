package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jose.proyectos_institucionales.controlador.CtlCargo;
import com.jose.proyectos_institucionales.controlador.CtlIntegrante;
import com.jose.proyectos_institucionales.controlador.CtlUsuario;
import com.jose.proyectos_institucionales.modelo.Cargo;
import com.jose.proyectos_institucionales.modelo.Integrante;
import com.jose.proyectos_institucionales.modelo.Proyecto;
import com.jose.proyectos_institucionales.modelo.Usuario;

import java.util.ArrayList;


public class GestionIntegrantesProyecto extends AppCompatActivity  {

    AlertDialog.Builder ad;
    EditText txtCedula;
    Integer idProyecto;

    CtlUsuario controladorUsuario;
    CtlIntegrante controladorIntegrantes;
    CtlCargo controladorCargo;


    ListView listVieww;

    Integrante integrante;

    ArrayList<Integrante> listaDeIntegrantes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_integrantes_proyecto);

        txtCedula = findViewById(R.id.txtCedulaUsuario);
        listVieww = findViewById(R.id.listVieww);

        Bundle bundle = getIntent().getExtras();
        idProyecto = bundle.getInt("idProyecto");

        controladorUsuario = new CtlUsuario(this);
        controladorIntegrantes = new CtlIntegrante(this);
        controladorCargo = new CtlCargo(this);

        listaDeIntegrantes = (ArrayList<Integrante>) controladorIntegrantes.listarIntegrantesProyecto(idProyecto);


        if (listaDeIntegrantes.size()!=0){
            cargarLista(listaDeIntegrantes);
        }


    }

    public void seleccionarCargo(){
        Intent intent = new Intent(this , EleccionCargoUsuario.class);
        intent.putExtra("integrante",integrante);
        startActivity(intent);
    }

    public void regresar(View view){
        Intent intent = new Intent(this , DetalleProyectoPropio.class);
        startActivity(intent);
    }

    public void cargarLista(ArrayList<Integrante> lista) {

        ArrayList<String> nombreCargo = new ArrayList<>();
        String entrada;
        for (Integrante integrante : lista){
            int idUsuario = integrante.getIdUsuario();
            Usuario usuario = controladorUsuario.buscarUsuarioPorID(idUsuario);
            Cargo cargo = controladorCargo.buscarCargo(integrante.getId());
            entrada = usuario.getNombres() + " "+ usuario.getApellidos() + " " + cargo.getNombre();
            nombreCargo.add(entrada);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreCargo);
        listVieww.setAdapter(adapter);
        listVieww.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {

            }
        });

    }


    public void buscarUsuarioAgregar(View view) {
        if (!txtCedula.getText().toString().equals("")) {
            final Usuario usuario = controladorUsuario.buscarUsuarioCedula(txtCedula.getText().toString());
            if (usuario != null) {
                final Context context = this;
                ad = new AlertDialog.Builder(context);
                ad.setTitle("Integrantes");
                ad.setMessage("Desea Vincular a :\n" + usuario.getNombres() + " " + usuario.getApellidos());
                ad.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        integrante.setIdProyecto(idProyecto);
                        integrante.setIdUsuario(usuario.getId());
                        seleccionarCargo();
                    }
                });
                ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        txtCedula.setText("");
                        dialog.cancel();
                    }
                });
                ad.show();
            } else {
                Toast.makeText(getApplicationContext(), "No hay Ninguna persona con este # De Documento", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Debe de Llenar El Campo", Toast.LENGTH_SHORT).show();
        }

        cargarLista(listaDeIntegrantes);
    }
}
