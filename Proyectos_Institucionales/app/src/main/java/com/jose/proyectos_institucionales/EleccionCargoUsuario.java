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
import android.widget.ListView;
import android.widget.Toast;

import com.jose.proyectos_institucionales.controlador.CtlCargo;
import com.jose.proyectos_institucionales.controlador.CtlIntegrante;
import com.jose.proyectos_institucionales.modelo.Cargo;
import com.jose.proyectos_institucionales.modelo.Integrante;
import com.jose.proyectos_institucionales.modelo.Proyecto;

import java.util.ArrayList;

public class EleccionCargoUsuario extends AppCompatActivity {

    AlertDialog.Builder ad;
    Integrante integrante;
    Proyecto proyecto;
    CtlCargo controladorCargo;
    ArrayList<Cargo> listaCargos ;
    CtlIntegrante controladorIntegrante;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_cargo_usuario);

        listView = findViewById(R.id.listaViewIntegrantes);

        Bundle bundle = getIntent().getExtras();
        integrante = (Integrante) bundle.getSerializable("objIntegrante");
        proyecto = (Proyecto) bundle.getSerializable("objProyecto");

        controladorCargo = new CtlCargo(this);
        controladorIntegrante = new CtlIntegrante(this);

        listaCargos = (ArrayList<Cargo>) controladorCargo.listarCargosProyecto(integrante.getIdProyecto());

        if (listaCargos.size()!=0){
            cargarLista(listaCargos);
        }else{
            final Context context = this;
            ad = new AlertDialog.Builder(context);
            ad.setTitle("Error");
            ad.setMessage("No hay Ningun cargo , Desea Agregar ? ");
            ad.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    registrarRecurso();
                }
            });
            ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    dialog.cancel();
                }
            });
            ad.show();
        }

    }

    public void regresarPersonas(){
        Intent intent = new Intent(this , GestionIntegrantesProyecto.class);
        intent.putExtra("objProyecto",proyecto);
        startActivity(intent);
    }



    public void cargarLista(final ArrayList<Cargo> lista) {
        final ArrayList<String> nombreCargo = new ArrayList<>();
        String entrada;
        for (Cargo cargo : lista){
            entrada=cargo.getId() +" - " +cargo.getNombre();
            nombreCargo.add(entrada);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreCargo);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {

                integrante.setIdCargo(lista.get(posicion).getId());
                controladorIntegrante.guardarIntegrante(integrante.getIdProyecto(),integrante.getIdUsuario(),integrante.getIdCargo());
                Toast.makeText(getApplicationContext(), "Registro Exitos", Toast.LENGTH_SHORT).show();

                regresarPersonas();
            }
        });

    }

    public void registrarRecurso(){
        Intent intent = new Intent(this , GestionDeCargos.class);
        intent.putExtra("objProyecto",proyecto);
        startActivity(intent);
    }
}
