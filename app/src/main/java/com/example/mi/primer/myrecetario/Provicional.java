package com.example.mi.primer.myrecetario;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class Provicional extends AppCompatActivity {

    private static final String TAG = "ProvicionalActivity";
    private RecyclerView recyclerView;
    private TextView resultadoBusqueda;
    private RecipeAdapterProvisional recipeAdapter;
    private List<RecetaBatidos> listaDeRecetas;
    private DatabaseReference tortasRef;
    private Button esteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provicional);
        esteBtn = findViewById(R.id.estaeslaprueba);
        inicializarVistas();
        configurarRecyclerView();
        inicializarFirebase();
        cargarDatosDeTortas();
        esteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ir();
            }
        });
    }

    private void inicializarVistas() {
        resultadoBusqueda = findViewById(R.id.resultadoBusquedaPp);
        recyclerView = findViewById(R.id.recycler_view);
    }

    private void configurarRecyclerView() {
        listaDeRecetas = new ArrayList<>();
        recipeAdapter = new RecipeAdapterProvisional(listaDeRecetas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recipeAdapter);
    }

    private void inicializarFirebase() {
        tortasRef = FirebaseDatabase.getInstance().getReference("Tortas");
    }

    private void cargarDatosDeTortas() {
        tortasRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaDeRecetas.clear();
                Log.d(TAG, "onDataChange: Recibido snapshot con " + snapshot.getChildrenCount() + " elementos.");
                for (DataSnapshot recetaSnapshot : snapshot.getChildren()) {
                    RecetaBatidos receta = recetaSnapshot.getValue(RecetaBatidos.class);
                    if (receta != null) {
                        Log.d(TAG, "Receta cargada: " + receta.getNombreDelBatido());
                        listaDeRecetas.add(receta);
                    } else {
                        Log.w(TAG, "Receta nula en snapshot");
                    }
                }
                actualizarUI();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Error al cargar recetas de Firebase: " + error.getMessage());
            }
        });
    }

    private void actualizarUI() {
        Log.d(TAG, "actualizarUI: Total de recetas en la lista: " + listaDeRecetas.size());
        if (listaDeRecetas.isEmpty()) {
            resultadoBusqueda.setVisibility(View.VISIBLE);
            resultadoBusqueda.setText("No se encontraron recetas.");
            recyclerView.setVisibility(View.GONE);
        } else {
            resultadoBusqueda.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recipeAdapter.notifyDataSetChanged();
        }
    }

    public void ir() {
        Intent intent = new Intent(this, CreaTuTarea.class);
        startActivity(intent);
    }
}
