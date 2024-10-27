package com.example.mi.primer.myrecetario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log; // Import necesario para Logcat
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
    private static final String TAG = "WelcomeSession"; // Tag para Logcat
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

    private DatabaseReference recipesRef, galletasRef, pastelesRef, tortasRef;
    private int nodeCount = 0;
    private final int totalNodes = 4;  // Actualizar si agregas o eliminas nodos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_session);

        Log.d(TAG, "onCreate: Iniciando WelcomeSession");

        // Referencia al BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        Log.d(TAG, "BottomNavigationView inicializado");

        // Referencia al TextInputLayout y TextInputEditText para búsqueda
        searchInputLayout = findViewById(R.id.textInputLayout);
        etSearch = findViewById(R.id.etsearchReceta);
        Log.d(TAG, "TextInputLayout y TextInputEditText inicializados");

        // TextView para mostrar "Receta no encontrada"
        resultadoBusqueda = findViewById(R.id.resultadoBusqueda);
        Log.d(TAG, "TextView para resultadoBusqueda inicializado");

        // RecyclerView para mostrar contenido
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d(TAG, "RecyclerView inicializado y layout manager establecido");

        // Configurar la barra de navegación inferior
        configureBottomNavigationView();

        // Inicialización de listas
        recipes = new ArrayList<>();
        filteredRecipes = new ArrayList<>();
        Log.d(TAG, "Listas de recetas inicializadas");

        // Inicializar el spinner
        spinnerAmount = findViewById(R.id.spinnerAmount);
        String[] cantidades = new String[]{"8", "12", "15", "18", "20"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cantidades);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAmount.setAdapter(adapter);
        Log.d(TAG, "Spinner inicializado con cantidades: 8, 12, 15, 18, 20");

        // Configurar el RecyclerView
        configurarRecyclerView();

        // Inicializar referencia a Firebase
        recipesRef = FirebaseDatabase.getInstance().getReference("recetas");
        galletasRef = FirebaseDatabase.getInstance().getReference("Galletas");
        pastelesRef = FirebaseDatabase.getInstance().getReference("Pasteles");
        tortasRef = FirebaseDatabase.getInstance().getReference("Tortas");

        Log.d(TAG, "Referencia a Firebase Database inicializada");

        // Cargar recetas desde Firebase
        loadRecipesFromFirebase();

        // Lógica para filtrar las recetas a medida que el usuario escribe
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(TAG, "onTextChanged: Buscando recetas con query: " + charSequence.toString());
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
                double cantidadSeleccionada = Double.parseDouble(selectPost); // Usar parseDouble para precisión
                Log.d(TAG, "onItemSelected: Cantidad seleccionada del spinner: " + cantidadSeleccionada);
                ajustarIngredientes(cantidadSeleccionada);  // Método para ajustar la receta según la selección
                // Actualizar la cantidad seleccionada en el adaptador
                recipeAdapter.setCantidadSeleccionada(cantidadSeleccionada);
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
        Log.d(TAG, "RecyclerView configurado con RecipeAdapter y cantidadSeleccionada: " + cantidadSeleccionada);
    }

    // Método para cargar recetas desde Firebase
    private void loadRecipesFromFirebase() {
        // Limpiar la lista antes de cargar para evitar duplicados
        recipes.clear();
        nodeCount = 0;  // Reinicia el contador antes de cada carga
        loadFromNode(recipesRef);
        loadFromNode(galletasRef);
        loadFromNode(pastelesRef);
        loadFromNode(tortasRef);
    }

    private void loadFromNode(DatabaseReference nodeRef) {
        nodeRef.addListenerForSingleValueEvent(new ValueEventListener() { // Cambiado a addListenerForSingleValueEvent
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot recipeSnapshot : snapshot.getChildren()) {
                    RecetaMasa recipe = recipeSnapshot.getValue(RecetaMasa.class);
                    if (recipe != null) {
                        if (recipe.getIngredientes() != null) {
                            recipe.setIngredientesOriginales(new ArrayList<>(recipe.getIngredientes()));
                        } else {
                            recipe.setIngredientesOriginales(new ArrayList<>());
                        }
                        recipes.add(recipe);
                    }
                }

                nodeCount++;  // Incrementa el contador de nodos cargados
                if (nodeCount == totalNodes) {  // Verifica si se cargaron todos los nodos
                    // Clona recipes a filteredRecipes para mostrar todos los resultados
                    filteredRecipes.clear();
                    filteredRecipes.addAll(recipes);
                    recipeAdapter.notifyDataSetChanged();  // Notifica cambios al adaptador
                }
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
        Log.d(TAG, "filterRecipes: Filtrando recetas con query: " + query);

        // Si el texto de búsqueda no está vacío
        if (!query.isEmpty()) {
            for (RecetaMasa recipe : recipes) {
                if (recipe != null && recipe.getNombreDeLaMasa() != null &&
                        recipe.getNombreDeLaMasa().toLowerCase().contains(query.toLowerCase())) {
                    filteredRecipes.add(recipe);
                    Log.d(TAG, "filterRecipes: Receta filtrada: " + recipe.getNombreDeLaMasa());
                }
            }

            // Mostrar mensaje si no se encontraron recetas
            if (filteredRecipes.isEmpty()) {
                resultadoBusqueda.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                Log.d(TAG, "filterRecipes: No se encontraron recetas para: " + query);
            } else {
                resultadoBusqueda.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                Log.d(TAG, "filterRecipes: Recetas encontradas: " + filteredRecipes.size());
            }
        } else {
            // Si la búsqueda está vacía, mostrar todas las recetas
            filteredRecipes.addAll(recipes);
            Log.d(TAG, "filterRecipes: Búsqueda vacía, mostrando todas las recetas: " + filteredRecipes.size());
            resultadoBusqueda.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

        recipeAdapter.notifyDataSetChanged();
        Log.d(TAG, "filterRecipes: RecyclerView actualizado con notifyDataSetChanged()");
    }

    // Método para ajustar los ingredientes según la cantidad seleccionada
    private void ajustarIngredientes(double cantidadSeleccionada) {
        Log.d(TAG, "ajustarIngredientes: Ajustando ingredientes para la cantidad seleccionada: " + cantidadSeleccionada);

        for (RecetaMasa recipe : filteredRecipes) {
            if (recipe != null) {
                double cantidadOriginal = recipe.getCantidadOriginal();
                Log.d(TAG, "ajustarIngredientes: Cantidad original de la receta " + recipe.getNombreDeLaMasa() + ": " + cantidadOriginal);

                if (cantidadSeleccionada != cantidadOriginal) {
                    double factor = cantidadSeleccionada / cantidadOriginal; // Corregido: División en lugar de Multiplicación
                    Log.d(TAG, "ajustarIngredientes: Factor de ajuste: " + factor);

                    List<Ingredientes.Ingrediente> ingredientesAjustados = recipe.getIngredientesAjustados(cantidadSeleccionada);


                    for (Ingredientes.Ingrediente ingrediente : ingredientesAjustados) {
                        Log.d(TAG, "ajustarIngredientes: Ingrediente ajustado: " + ingrediente.getNombre() + " - Nueva cantidad: " + ingrediente.getCantidad());
                    }
                }
            }
        }

        // Actualizar el adaptador para reflejar los nuevos ingredientes ajustados
        recipeAdapter.notifyDataSetChanged();
        Log.d(TAG, "ajustarIngredientes: RecyclerView actualizado con notifyDataSetChanged()");
    }

    // Método para cerrar sesión
    private void logout() {
        Log.d(TAG, "logout: Cerrando sesión...");
        Toast.makeText(this, "Cerrando sesión...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public double obtenerCantidadSeleccionada() {
        Spinner spinner = findViewById(R.id.spinnerAmount);
        double cantidadSeleccionada = Double.parseDouble(spinner.getSelectedItem().toString()); // Asegúrate de usar parseDouble
        Log.d(TAG, "obtenerCantidadSeleccionada: Cantidad seleccionada: " + cantidadSeleccionada);
        return cantidadSeleccionada;
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
            Intent intent = new Intent(this, CreationRecetaOriginal.class);
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
