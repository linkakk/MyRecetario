package com.example.mi.primer.myrecetario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WelcomeSession extends AppCompatActivity {
    private static final String TAG = "WelcomeSession";
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    private TextInputEditText etSearch;
    private Spinner spinnerAmount;
    private String selectPost;
    private TextInputLayout searchInputLayout;
    private TextView resultadoBusqueda;

    private List<Object> recipes;
    private List<Object> filteredRecipes;

    private DatabaseReference recipesRef, galletasRef, pastelesRef, tortasRef;
    private int nodeCount = 0;
    private final int totalNodes = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_session);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        searchInputLayout = findViewById(R.id.textInputLayout);
        etSearch = findViewById(R.id.etsearchReceta);
        resultadoBusqueda = findViewById(R.id.resultadoBusqueda);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        spinnerAmount = findViewById(R.id.spinnerAmount);

        configureBottomNavigationView();

        recipes = new ArrayList<>();
        filteredRecipes = new ArrayList<>();

        configurarRecyclerView();

        recipesRef = FirebaseDatabase.getInstance().getReference("recetas");
        galletasRef = FirebaseDatabase.getInstance().getReference("Galletas");
        pastelesRef = FirebaseDatabase.getInstance().getReference("Pasteles");
        tortasRef = FirebaseDatabase.getInstance().getReference("Tortas");

        loadRecipesFromFirebase();

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterRecipes(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        spinnerAmount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectPost = parent.getItemAtPosition(position).toString();
                double cantidadSeleccionada = Double.parseDouble(selectPost);
                ajustarIngredientes(cantidadSeleccionada);
                recipeAdapter.setCantidadSeleccionada(cantidadSeleccionada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void configurarRecyclerView() {
        double cantidadSeleccionada = obtenerCantidadSeleccionada();
        recipeAdapter = new RecipeAdapter(filteredRecipes, cantidadSeleccionada);
        recyclerView.setAdapter(recipeAdapter);

        recyclerView.setVisibility(View.GONE);
        resultadoBusqueda.setVisibility(View.VISIBLE);
        resultadoBusqueda.setText("Escribe algo para buscar recetas");
    }

    private void loadRecipesFromFirebase() {
        recipes.clear();
        nodeCount = 0;
        loadFromNode(recipesRef, RecetaMasa.class);
        loadFromNode(galletasRef, RecetaGalletas.class);
        loadFromNode(pastelesRef, RecetaPastel.class);
        loadFromNode(tortasRef, RecetaBatidos.class);
    }

    private <T> void loadFromNode(DatabaseReference nodeRef, Class<T> recipeClass) {
        nodeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot recipeSnapshot : snapshot.getChildren()) {
                    T recipe = recipeSnapshot.getValue(recipeClass);
                    if (recipe != null) {
                        recipes.add(recipe);
                    }
                }
                nodeCount++;
                if (nodeCount == totalNodes) {
                    filteredRecipes.clear();
                    recipeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(WelcomeSession.this, "Error al cargar datos de Firebase: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Dentro de la clase WelcomeSession
    private void filterRecipes(String query) {
        filteredRecipes.clear();

        if (query.isEmpty()) {
            resultadoBusqueda.setVisibility(View.VISIBLE);
            resultadoBusqueda.setText("Escribe algo para buscar recetas");
            recyclerView.setVisibility(View.GONE);
            ocultarSpinner(); // Ocultar el Spinner si no hay búsqueda activa
        } else {
            for (Object recipe : recipes) {
                if (recipe instanceof RecetaMasa) {
                    String nombreMasa = ((RecetaMasa) recipe).getNombreDeLaMasa();
                    if (nombreMasa != null && nombreMasa.toLowerCase().contains(query.toLowerCase())) {
                        filteredRecipes.add(recipe);
                        configurarSpinnerParaPanes(); // Configura el Spinner para panes
                    }
                } else if (recipe instanceof RecetaPastel) {
                    String nombrePastel = ((RecetaPastel) recipe).getNombrePastel();
                    if (nombrePastel != null && nombrePastel.toLowerCase().contains(query.toLowerCase())) {
                        filteredRecipes.add(recipe);
                        configurarSpinnerParaPasteles(); // Configura el Spinner para pasteles
                    }
                } else if (recipe instanceof RecetaBatidos) {
                    String nombreBatido = ((RecetaBatidos) recipe).getNombreDelBatido();
                    if (nombreBatido != null && nombreBatido.toLowerCase().contains(query.toLowerCase())) {
                        filteredRecipes.add(recipe);
                        ocultarSpinner(); // Oculta el Spinner para batidos
                    }
                } else if (recipe instanceof RecetaGalletas) {
                    String nombreGalleta = ((RecetaGalletas) recipe).getNombreGalleta();
                    if (nombreGalleta != null && nombreGalleta.toLowerCase().contains(query.toLowerCase())) {
                        filteredRecipes.add(recipe);
                        ocultarSpinner(); // Oculta el Spinner para galletas
                    }
                }
            }

            if (filteredRecipes.isEmpty()) {
                resultadoBusqueda.setVisibility(View.VISIBLE);
                resultadoBusqueda.setText("No se encontraron recetas para: " + query);
                recyclerView.setVisibility(View.GONE);
            } else {
                resultadoBusqueda.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        }

        recipeAdapter.notifyDataSetChanged();
    }

    private void configurarSpinnerParaPanes() {
        spinnerAmount.setVisibility(View.VISIBLE);
        String[] cantidadesPanes = new String[]{"8", "12", "15", "16", "18", "20"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cantidadesPanes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAmount.setAdapter(adapter);
    }

    private void configurarSpinnerParaPasteles() {
        spinnerAmount.setVisibility(View.VISIBLE);
        String[] cantidadesPasteles = new String[]{"1", "2", "4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cantidadesPasteles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAmount.setAdapter(adapter);
    }

    private void ocultarSpinner() {
        spinnerAmount.setVisibility(View.GONE);
    }


    private void ajustarIngredientes(double cantidadSeleccionada) {
        for (Object recipe : filteredRecipes) {
            if (recipe instanceof RecetaMasa) {
                ((RecetaMasa) recipe).getIngredientesAjustados(cantidadSeleccionada);
            } else if (recipe instanceof RecetaBatidos) {
                ((RecetaBatidos) recipe).getIngredientesAjustados(cantidadSeleccionada);
            }
        }
        recipeAdapter.notifyDataSetChanged();
    }

    public double obtenerCantidadSeleccionada() {
        if (spinnerAmount != null && spinnerAmount.getVisibility() == View.VISIBLE && spinnerAmount.getSelectedItem() != null) {
            return Double.parseDouble(spinnerAmount.getSelectedItem().toString());
        } else {
            return 1.0; // Valor predeterminado si el Spinner no está visible o no tiene selección
        }
    }



    private void configureBottomNavigationView() {
        bottomNavigationView.getMenu().findItem(R.id.navigation_search).setOnMenuItemClickListener(item -> {
            searchInputLayout.setVisibility(View.VISIBLE);
            return true;
        });

        bottomNavigationView.getMenu().findItem(R.id.navigation_products).setOnMenuItemClickListener(item -> {
            startActivity(new Intent(this, Provicional.class));
            return true;
        });

        bottomNavigationView.getMenu().findItem(R.id.navigation_add).setOnMenuItemClickListener(item -> {
            startActivity(new Intent(this, CreationRecetaOriginal.class));
            return true;
        });

        bottomNavigationView.getMenu().findItem(R.id.navigation_logout).setOnMenuItemClickListener(item -> {
            logout();
            return true;
        });
        bottomNavigationView.getMenu().findItem(R.id.navigation_tareas).setOnMenuItemClickListener(item -> {
            btnTareas();
            return true;
        });
    }

    private void btnTareas(){
        startActivity(new Intent(this, ListaDeTareas.class));
    }
    private void logout() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
