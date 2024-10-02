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

        // Inicialización de listas
        recipes = new ArrayList<>();
        filteredRecipes = new ArrayList<>();

        // Configuración del adaptador
        recipeAdapter = new RecipeAdapter(filteredRecipes);
        recyclerView.setAdapter(recipeAdapter);

        // Inicializar referencia a Firebase
        recipesRef = FirebaseDatabase.getInstance().getReference("recetas");  // Asegúrate que "recipes" es la referencia correcta

        // Cargar recetas desde Firebase
        loadRecipesFromFirebase();

        // Configurar la barra de navegación inferior
        configureBottomNavigationView();

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
    }

    // Método para configurar los botones de la barra de navegación inferior
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
            showAddProductDialog(); // Mostrar diálogo para agregar productos
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

    // Método para cargar recetas desde Firebase
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
    // Método para filtrar las recetas según el texto ingresado
    // Método para filtrar las recetas según el texto ingresado
    private void filterRecipes(String query) {
        filteredRecipes.clear();

        if (!query.isEmpty()) {
            for (RecetaMasa recipe : recipes) {
                // Asegúrate de que estás llamando a toLowerCase() en el campo String correcto
                if (recipe.getNombreDeLaMasa() != null &&
                        recipe.getNombreDeLaMasa().toLowerCase().contains(query.toLowerCase())) {
                    filteredRecipes.add(recipe); // Agregar objeto RecetaMasa completo
                }
            }

            // Si no hay coincidencias, mostrar el mensaje "Receta no encontrada"
            if (filteredRecipes.isEmpty()) {
                resultadoBusqueda.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            } else {
                resultadoBusqueda.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        } else {
            // Si no hay texto en el campo de búsqueda, mostrar todas las recetas
            filteredRecipes.addAll(recipes);
            resultadoBusqueda.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

        // Notificar al adaptador que los datos han cambiado
        recipeAdapter.notifyDataSetChanged();
    }



    // Método para mostrar diálogo y agregar productos
    private void showAddProductDialog() {
        Toast.makeText(this, "Diálogo para agregar productos", Toast.LENGTH_SHORT).show();
        // Aquí puedes mostrar un diálogo personalizado
    }

    // Método para cerrar sesión
    private void logout() {
        Toast.makeText(this, "Cerrando sesión...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Finaliza la actividad actual
    }
}
