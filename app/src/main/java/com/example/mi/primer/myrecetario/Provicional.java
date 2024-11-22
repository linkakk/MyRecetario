package com.example.mi.primer.myrecetario;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase; // Asegúrate de tener este import
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Provicional extends AppCompatActivity {

    private static final String TAG = "ProvicionalActivity";
    private RecyclerView recyclerView;
    private TextView resultadoBusqueda;
    private RecipeAdapterProvisional recipeAdapter;
    private List<Object> recipes;
    private DatabaseReference recipesRef, galletasRef, pastelesRef, tortasRef;
    private int nodeCount = 0;
    private final int totalNodes = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provicional);

        inicializarVistas();
        configurarRecyclerView();
        inicializarFirebase();
        cargarTodasLasRecetas();
    }

    private void inicializarVistas() {
        resultadoBusqueda = findViewById(R.id.resultadoBusquedaPp);
        recyclerView = findViewById(R.id.recyclerView_allRecipes);
    }

    private void configurarRecyclerView() {
        recipes = new ArrayList<>();
        recipeAdapter = new RecipeAdapterProvisional(recipes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recipeAdapter);
    }

    private void inicializarFirebase() {
        recipesRef = FirebaseDatabase.getInstance().getReference("recetas");
        galletasRef = FirebaseDatabase.getInstance().getReference("Galletas");
        pastelesRef = FirebaseDatabase.getInstance().getReference("Pasteles");
        tortasRef = FirebaseDatabase.getInstance().getReference("Tortas");
    }

    private void cargarTodasLasRecetas() {
        recipes.clear();
        nodeCount = 0;

        cargarDatosDeNodo(recipesRef, RecetaMasa.class);
        cargarDatosDeNodo(galletasRef, RecetaGalletas.class);
        cargarDatosDeNodo(pastelesRef, RecetaPastel.class);
        cargarDatosDeNodo(tortasRef, RecetaBatidos.class);
    }

    private <T> void cargarDatosDeNodo(DatabaseReference ref, Class<T> recetaClase) {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(TAG, "onDataChange: Recibido snapshot con " + snapshot.getChildrenCount() + " elementos de nodo " + ref.getKey());
                for (DataSnapshot recetaSnapshot : snapshot.getChildren()) {
                    T receta = recetaSnapshot.getValue(recetaClase);
                    if (receta != null) {
                        recipes.add(receta);
                        Log.d(TAG, "Receta cargada: " + obtenerNombreReceta(receta));
                    } else {
                        Log.w(TAG, "Receta nula en snapshot del nodo " + ref.getKey());
                    }
                }
                nodeCount++;
                if (nodeCount == totalNodes) {
                    actualizarUI();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Error al cargar recetas de Firebase del nodo " + ref.getKey() + ": " + error.getMessage());
                nodeCount++;
                if (nodeCount == totalNodes) {
                    actualizarUI();
                }
            }
        });
    }

    private void actualizarUI() {
        Log.d(TAG, "actualizarUI: Total de recetas en la lista: " + recipes.size());
        if (recipes.isEmpty()) {
            resultadoBusqueda.setVisibility(View.VISIBLE);
            resultadoBusqueda.setText("No se encontraron recetas.");
            recyclerView.setVisibility(View.GONE);
        } else {
            resultadoBusqueda.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recipeAdapter.notifyDataSetChanged();
        }
    }

    private String obtenerNombreReceta(Object receta) {
        if (receta instanceof RecetaMasa) {
            return ((RecetaMasa) receta).getNombreDeLaMasa();
        } else if (receta instanceof RecetaGalletas) {
            return ((RecetaGalletas) receta).getNombreGalleta();
        } else if (receta instanceof RecetaPastel) {
            return ((RecetaPastel) receta).getNombrePastel();
        } else if (receta instanceof RecetaBatidos) {
            return ((RecetaBatidos) receta).getNombreDelBatido();
        } else {
            return "Receta sin nombre";
        }
    }
}
