package com.example.mi.primer.myrecetario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.Arrays;
import java.util.List;

public class WelcomeSession extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_session);

        // Referencia al BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // RecyclerView para mostrar contenido
        recyclerView = findViewById(R.id.recycler_view);

        // Lista de ejemplo para el RecyclerView
        List<String> recipes = Arrays.asList("Pizza", "Pastel de Chocolate", "Empanadas", "Tacos", "Sushi");

        // Configuración del RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecipeAdapter(recipes));

        // Lógica para ocultar/mostrar la barra de navegación según el desplazamiento
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && bottomNavigationView.isShown()) {
                    bottomNavigationView.animate().translationY(bottomNavigationView.getHeight());
                } else if (dy < 0) {
                    bottomNavigationView.animate().translationY(0);
                }
            }
        });

        // Manejar los eventos de los botones de forma individual

        // Botón de búsqueda
        MenuItem searchItem = bottomNavigationView.getMenu().findItem(R.id.navigation_search);
        searchItem.setOnMenuItemClickListener(item -> {
            Toast.makeText(WelcomeSession.this, "Has escogido la opción: Buscar", Toast.LENGTH_SHORT).show();
            // Añadir lógica para buscar aquí
            return true;
        });

        // Botón de productos
        MenuItem productsItem = bottomNavigationView.getMenu().findItem(R.id.navigation_products);
        productsItem.setOnMenuItemClickListener(item -> {
            Toast.makeText(WelcomeSession.this, "Has escogido la opción: Productos", Toast.LENGTH_SHORT).show();
            // Añadir lógica para mostrar productos aquí
            return true;
        });

        // Botón para agregar productos
        MenuItem addItem = bottomNavigationView.getMenu().findItem(R.id.navigation_add);
        addItem.setOnMenuItemClickListener(item -> {
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
