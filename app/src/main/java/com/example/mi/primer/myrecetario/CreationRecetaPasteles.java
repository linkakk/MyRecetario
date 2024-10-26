package com.example.mi.primer.myrecetario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CreationRecetaPasteles extends AppCompatActivity {
    private TextInputEditText nombrePastel, recetaDescripcionPastel, aguaPastel, esenciaPastel,
            preparacionAzucarPastel, salPastel, margarinaPastel, harinaPastel, vitinaPastel,
            temperaturaPastel, preparacionPastel, porcionadoPastel, almacemamientoPastel;
    private Button btnCreate;
    private DatabaseReference databaseRecetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_receta_pasteles2);

        // Inicialización de los campos de entrada
        nombrePastel = findViewById(R.id.etCreationNombrePastel);
        recetaDescripcionPastel = findViewById(R.id.etCreationRecetaDescripcionPastel);
        aguaPastel = findViewById(R.id.etCreationRecetaPreparacionAguaPastel);
        esenciaPastel = findViewById(R.id.etCreationRecetaPreparacionEsenciaPastel);
        preparacionAzucarPastel = findViewById(R.id.etCreationRecetaPreparacionAzucarPastel);
        salPastel = findViewById(R.id.etCreationRecetaPreparacionSalPastel);
        margarinaPastel = findViewById(R.id.etCreationRecetaPreparacionMargarinaPastel);
        harinaPastel = findViewById(R.id.etCreationRecetaPreparacionHarinaPastel);
        vitinaPastel = findViewById(R.id.etCreationRecetaVitinaPastel);
        temperaturaPastel = findViewById(R.id.etCreationRecetaTemperaturaPastel);
        preparacionPastel = findViewById(R.id.etCreationRecetaPreparacionPastel);
        porcionadoPastel = findViewById(R.id.etCreationRecetaPorcionadoPastel);
        almacemamientoPastel = findViewById(R.id.etCreationRecetaAlmacenamientoPastel);
        databaseRecetas = FirebaseDatabase.getInstance().getReference("Pasteles");

        btnCreate = findViewById(R.id.btnCreationRecetaPastel);
        btnCreate.setOnClickListener(v -> guardarReceta());
    }

    private void guardarReceta() {
        try {
            // Obtener valores de los campos
            String nombre = nombrePastel.getText().toString().trim();
            String descripcion = recetaDescripcionPastel.getText().toString().trim();
            String agua = aguaPastel.getText().toString().trim();
            String esencia = esenciaPastel.getText().toString().trim();
            String azucar = preparacionAzucarPastel.getText().toString().trim();
            String sal = salPastel.getText().toString().trim();
            String margarina = margarinaPastel.getText().toString().trim();
            String harina = harinaPastel.getText().toString().trim();
            String vitina = vitinaPastel.getText().toString().trim();
            String temperatura = temperaturaPastel.getText().toString().trim();
            String preparacion = preparacionPastel.getText().toString().trim();
            String porcionado = porcionadoPastel.getText().toString().trim();
            String almacenamiento = almacemamientoPastel.getText().toString().trim();

            // Verificar si el campo nombre está vacío
            if (nombre.isEmpty()) {
                Toast.makeText(this, "El nombre es obligatorio", Toast.LENGTH_LONG).show();
                return;
            }

            // Convertir las cantidades
            double cantidadAgua = agua.isEmpty() ? 0 : Double.parseDouble(agua);
            double cantidadEsencia = esencia.isEmpty() ? 0 : Double.parseDouble(esencia);
            double cantidadAzucar = azucar.isEmpty() ? 0 : Double.parseDouble(azucar);
            double cantidadSal = sal.isEmpty() ? 0 : Double.parseDouble(sal);
            double cantidadMargarina = margarina.isEmpty() ? 0 : Double.parseDouble(margarina);
            double cantidadHarina = harina.isEmpty() ? 0 : Double.parseDouble(harina);
            double cantidadVitina = vitina.isEmpty() ? 0 : Double.parseDouble(vitina);
            double temperaturaHorno = temperatura.isEmpty() ? 0 : Double.parseDouble(temperatura);

            Log.d("CreationRecetaPasteles", "Valores capturados correctamente.");

            // Crear lista de ingredientes
            List<Ingredientes.Ingrediente> listaIngredientes = new ArrayList<>();
            listaIngredientes.add(new Ingredientes.Ingrediente("Cantidad agua", cantidadAgua));
            listaIngredientes.add(new Ingredientes.Ingrediente("Cantidad esencia", cantidadEsencia));
            listaIngredientes.add(new Ingredientes.Ingrediente("Cantidad azúcar", cantidadAzucar));
            listaIngredientes.add(new Ingredientes.Ingrediente("Cantidad sal", cantidadSal));
            listaIngredientes.add(new Ingredientes.Ingrediente("Cantidad margarina", cantidadMargarina));
            listaIngredientes.add(new Ingredientes.Ingrediente("Cantidad harina", cantidadHarina));
            listaIngredientes.add(new Ingredientes.Ingrediente("Cantidad vitina", cantidadVitina));

            // Crear objeto RecetaPastel
            RecetaPastel recetaPastel = new RecetaPastel(
                    nombre,
                    descripcion,
                    cantidadAgua,
                    cantidadEsencia,
                    cantidadAzucar,
                    cantidadSal,
                    cantidadMargarina,
                    cantidadHarina,
                    cantidadVitina,
                    temperaturaHorno,
                    preparacion,
                    porcionado,
                    almacenamiento,
                    listaIngredientes
            );

            // Guardar en Firebase
            databaseRecetas.push().setValue(recetaPastel).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Receta guardada correctamente", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Error al guardar la receta: " + task.getException(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Error en el formato de números", Toast.LENGTH_SHORT).show();
            Log.e("CreationRecetaPasteles", "Error de formato: ", e);
        } catch (Exception e) {
            Toast.makeText(this, "Error inesperado al guardar la receta", Toast.LENGTH_SHORT).show();
            Log.e("CreationRecetaPasteles", "Error inesperado: ", e);
        }
    }

}
