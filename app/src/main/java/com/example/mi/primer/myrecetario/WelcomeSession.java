package com.example.mi.primer.myrecetario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    private TextInputEditText etSearch;
    private Spinner spinnerAmount;
    private String selectPost;
    private TextInputLayout searchInputLayout;
    private TextView resultadoBusqueda;

    private List<RecetaMasa> recipes;  // Lista completa de recetas desde Firebase
    private List<RecetaMasa> filteredRecipes;  // Lista de recetas filtradas

    private DatabaseReference recipesRef;  // Referencia a Firebase Database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_session);

        // Referencia al BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Referencia al TextInputLayout y TextInputEditText para búsqueda
        searchInputLayout = findViewById(R.id.textInputLayout);
        etSearch = findViewById(R.id.etsearchReceta);

        // TextView para mostrar "Receta no encontrada"
        resultadoBusqueda = findViewById(R.id.resultadoBusqueda);

        // RecyclerView para mostrar contenido
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Configurar la barra de navegación inferior
        configureBottomNavigationView();
        // Inicialización de listas
        recipes = new ArrayList<>();
        filteredRecipes = new ArrayList<>();

        // Inicializar el spinner
        spinnerAmount = findViewById(R.id.spinnerAmount);
        String[] cantidades = new String[]{"8", "12", "15", "18", "20"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cantidades);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAmount.setAdapter(adapter);

        // Configurar el RecyclerView
        configurarRecyclerView();

        // Inicializar referencia a Firebase
        recipesRef = FirebaseDatabase.getInstance().getReference("recetas");

        // Cargar recetas desde Firebase
        loadRecipesFromFirebase();


        // Lógica para filtrar las recetas a medida que el usuario escribe
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

        // Manejar la selección del spinner para ajustar ingredientes
        spinnerAmount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectPost = parent.getItemAtPosition(position).toString();
                double cantidadSeleccionada = Integer.parseInt(selectPost);
                ajustarIngredientes(cantidadSeleccionada);  // Método para ajustar la receta según la selección
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    // Método para configurar el RecyclerView
    private void configurarRecyclerView() {
        double cantidadSeleccionada = obtenerCantidadSeleccionada();
        recipeAdapter = new RecipeAdapter(filteredRecipes, cantidadSeleccionada);
        recyclerView.setAdapter(recipeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Método para cargar recetas desde Firebase
    private void loadRecipesFromFirebase() {
        recipesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                recipes.clear();  // Limpiar la lista existente
                for (DataSnapshot recipeSnapshot : snapshot.getChildren()) {
                    RecetaMasa recipe = recipeSnapshot.getValue(RecetaMasa.class);  // Cambiar a tu modelo
                    if (recipe != null) {
                        recipes.add(recipe);  // Agregar objeto RecetaMasa completo
                    }
                }
                recipeAdapter.notifyDataSetChanged();  // Notificar cambios al adaptador
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(WelcomeSession.this, "Error al cargar datos de Firebase: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para filtrar las recetas según el texto ingresado
    private void filterRecipes(String query) {
        filteredRecipes.clear();

        // Si el texto de búsqueda no está vacío
        if (!query.isEmpty()) {
            for (RecetaMasa recipe : recipes) {
                if (recipe != null && recipe.getNombreDeLaMasa() != null &&
                        recipe.getNombreDeLaMasa().toLowerCase().contains(query.toLowerCase())) {
                    filteredRecipes.add(recipe);
                }
            }

            // Mostrar mensaje si no se encontraron recetas
            if (filteredRecipes.isEmpty()) {
                resultadoBusqueda.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            } else {
                resultadoBusqueda.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        }

        recipeAdapter.notifyDataSetChanged();
    }

    // Método para ajustar los ingredientes según la cantidad seleccionada
    private void ajustarIngredientes(double cantidadSeleccionada) {
        for (RecetaMasa recipe : filteredRecipes) {
            if (recipe != null) {
                double cantidadOriginal = recipe.getCantidadOriginal();

                // Aplicar regla de tres solo si la cantidad seleccionada es distinta de la original
                if (cantidadSeleccionada != cantidadOriginal) {
                    double factor = cantidadSeleccionada * cantidadOriginal;

                    // Ajustar todos los ingredientes de la receta utilizando la regla de tres
                    for (Ingredientes.Ingrediente ingrediente : recipe.getIngredientes()) {
                        double cantidadOriginalIngrediente = ingrediente.getCantidad();
                        double cantidadAjustada = cantidadOriginalIngrediente * factor;
                        ingrediente.setCantidad(cantidadAjustada);  // Actualizar la cantidad ajustada
                    }
                }
            }
        }

        recipeAdapter.notifyDataSetChanged();  // Actualiza el RecyclerView
    }

    // Método para cerrar sesión
    private void logout() {
        Toast.makeText(this, "Cerrando sesión...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public double obtenerCantidadSeleccionada() {
        Spinner spinner = findViewById(R.id.spinnerAmount);
        return Double.parseDouble(spinner.getSelectedItem().toString()); // Asegúrate de que el spinner contenga valores numéricos
    }
    private void configureBottomNavigationView() {
        // Botón de búsqueda
        MenuItem searchItem = bottomNavigationView.getMenu().findItem(R.id.navigation_search);
        searchItem.setOnMenuItemClickListener(item -> {
            Toast.makeText(WelcomeSession.this, "Has escogido la opción: Buscar", Toast.LENGTH_SHORT).show();
            searchInputLayout.setVisibility(View.VISIBLE);  // Mostrar campo de búsqueda
            return true;
        });

        // Botón de productos
        MenuItem productsItem = bottomNavigationView.getMenu().findItem(R.id.navigation_products);
        productsItem.setOnMenuItemClickListener(item -> {
            Toast.makeText(WelcomeSession.this, "Has escogido la opción: Productos", Toast.LENGTH_SHORT).show();
            // Lógica para mostrar productos aquí
            return true;
        });

        // Botón para agregar productos
        MenuItem addItem = bottomNavigationView.getMenu().findItem(R.id.navigation_add);
        addItem.setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(this, CreationReceta.class);
            startActivity(intent);
            Toast.makeText(WelcomeSession.this, "Has escogido la opción: Agregar", Toast.LENGTH_SHORT).show();
            return true;
        });

        // Botón para salir y cerrar sesión
        MenuItem logoutItem = bottomNavigationView.getMenu().findItem(R.id.navigation_logout);
        logoutItem.setOnMenuItemClickListener(item -> {
            Toast.makeText(WelcomeSession.this, "Has escogido la opción: Salir", Toast.LENGTH_SHORT).show();
            logout(); // Lógica para cerrar sesión
            return true;
        });
    }
}
