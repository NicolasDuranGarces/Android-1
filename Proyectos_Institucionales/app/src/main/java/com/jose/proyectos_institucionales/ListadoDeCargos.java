package com.jose.proyectos_institucionales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jose.proyectos_institucionales.controlador.CtlCargo;
import com.jose.proyectos_institucionales.modelo.Cargo;
import com.jose.proyectos_institucionales.modelo.Proyecto;

import java.util.ArrayList;

public class ListadoDeCargos extends AppCompatActivity {

    ListView listVieww ;
    CtlCargo controladorCargo;
    ArrayList<Cargo> listaCargos;
    Integer idProyecto,idDirector;
    Proyecto proyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_de_cargos);
        listVieww = findViewById(R.id.listVieww);
        controladorCargo = new CtlCargo(this);

        Bundle bundle = getIntent().getExtras();
        proyecto = (Proyecto) bundle.getSerializable("objProyecto");

        listaCargos = (ArrayList<Cargo>) controladorCargo.listarCargosProyecto(proyecto.getId());

        if (listaCargos.size() != 0){
            cargarLista(listaCargos);
        }
    }

    public void cargarLista(ArrayList<Cargo> lista) {
        ArrayList<String> nombreCargo = new ArrayList<>();
        String entrada;
        for (Cargo cargo : lista){
            entrada=cargo.getNombre();
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

    public void registrarCargo(View view){
        Intent intent = new Intent(this, GestionDeCargos.class);
        intent.putExtra("objProyecto", proyecto);
        startActivity(intent);
    }

    public void regresar(View view){
        Intent intent = new Intent(this, DetalleProyectoPropio.class);
        intent.putExtra("objProyecto", proyecto);
        startActivity(intent);
    }
}
