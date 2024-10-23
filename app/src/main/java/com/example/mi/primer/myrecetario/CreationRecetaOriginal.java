package com.example.mi.primer.myrecetario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CreationRecetaOriginal extends AppCompatActivity {

    private Spinner spinnerRecipe;
    private String selectPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_receta_original);

        spinnerRecipe = findViewById(R.id.spinnerRecipe);
        String[] tipoDeReceta = new String[]{"Galletas", "Panes Aliñados", "Pasteles", "Rollo", "Tortas"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipoDeReceta);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRecipe.setAdapter(adapter);

        // Agregar el listener para detectar selección del usuario
        spinnerRecipe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtener la selección del spinner
                selectPost = parent.getItemAtPosition(position).toString();

                // Dirigir al formulario correspondiente según la selección
                switch (selectPost) {
                    case "Galletas":
                        Intent intentGalletas = new Intent(CreationRecetaOriginal.this, CreationRecetaGalletas.class);
                        startActivity(intentGalletas);
                        break;
                    case "Panes Aliñados":
                        Intent intentPanes = new Intent(CreationRecetaOriginal.this, CreationReceta.class);
                        startActivity(intentPanes);
                        break;
                    case "Pasteles":
                        Intent intentPasteles = new Intent(CreationRecetaOriginal.this, CreationRecetaPasteles.class);
                        startActivity(intentPasteles);
                        break;
                    case "Rollo":
                        Intent intentRollo = new Intent(CreationRecetaOriginal.this, CreationRecetaRollo.class);
                        startActivity(intentRollo);
                        break;
                    case "Tortas":
                        Intent intentTortas = new Intent(CreationRecetaOriginal.this, CreationRecetaBatidos.class);
                        startActivity(intentTortas);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada si no hay selección
            }
        });
    }
}
