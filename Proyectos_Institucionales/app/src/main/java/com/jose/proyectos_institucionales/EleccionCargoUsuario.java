package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

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

    Integrante integrante;
    CtlCargo controladorCargo;
    ArrayList<Cargo> listaCargos ;
    CtlIntegrante controladorIntegrante;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_cargo_usuario);

        listView = findViewById(R.id.listView);


        Bundle bundle = getIntent().getExtras();
        integrante  = (Integrante) bundle.getSerializable("integrante");

        controladorCargo = new CtlCargo(this);
        controladorIntegrante = new CtlIntegrante(this);

        listaCargos = (ArrayList<Cargo>) controladorCargo.listarCargosProyecto(integrante.getIdProyecto());

        if (listaCargos.size()!=0){
            cargarLista(listaCargos);
        }

    }

    public void regresarPersonas(){
        Intent intent = new Intent(this , GestionIntegrantesProyecto.class);
        startActivity(intent);
    }


    public void cargarLista(ArrayList<Cargo> lista) {
        ArrayList<String> nombreCargo = new ArrayList<>();
        String entrada;
        for (Cargo cargo : lista){
            entrada=cargo.getNombre() ;
            nombreCargo.add(entrada);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreCargo);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                integrante.setIdCargo(listaCargos.get(posicion).getId());
                controladorIntegrante.guardarIntegrante(integrante.getIdProyecto(),integrante.getIdUsuario(),integrante.getIdCargo());
                Toast.makeText(getApplicationContext(), "Registro Exitos", Toast.LENGTH_SHORT).show();
                regresarPersonas();
            }
        });

    }
}
