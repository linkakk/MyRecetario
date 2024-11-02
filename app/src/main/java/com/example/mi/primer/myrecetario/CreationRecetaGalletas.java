package com.example.mi.primer.myrecetario;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

public class CreationRecetaGalletas extends AppCompatActivity {

    private TextInputEditText nombreGalleta, recetaDescripcionGalleta, preparacionHuevosGalletas, cantidadDeEsenciaGalletas,
            preparacionAzucarGalletas, cantidadDeMargarinaGalletas, harinaGalletas, feculaGalletas, polvoHornear,
            preparacionGalletas, temperaturaGalletas, porcionadoGalleta, almacemamientoProductoFinal,
            preparacionLimones, cantidadChocolate;
    private Button btnCreate;
    private DatabaseReference databaseRecetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_receta_galletas);

        // Inicialización de los campos de entrada
        nombreGalleta = findViewById(R.id.etCreationNombreGalleta);
        recetaDescripcionGalleta = findViewById(R.id.etCreationRecetaDescripcionGalleta);
        preparacionHuevosGalletas = findViewById(R.id.etCreationRecetaPreparacionHuevosGalletas);
        cantidadDeEsenciaGalletas = findViewById(R.id.etCreationRecetaPreparacionEsenciaGalletas);
        preparacionAzucarGalletas = findViewById(R.id.etCreationRecetaPreparacionAzucarGalletas);
        cantidadDeMargarinaGalletas = findViewById(R.id.etCreationRecetaPreparacionMargarinaGalleta);
        harinaGalletas = findViewById(R.id.etCreationRecetaPreparacionHarinaGalletas);
        feculaGalletas = findViewById(R.id.etCreationRecetaPreparacionFecula);
        polvoHornear = findViewById(R.id.etCreationRecetaPreparacionPolvoHornear);
        preparacionGalletas = findViewById(R.id.etCreationRecetaPreparacionGalletas);
        temperaturaGalletas = findViewById(R.id.etCreationRecetaTemperaturaGalletas);
        porcionadoGalleta = findViewById(R.id.etCreationRecetaPorcionadoGalleta);
        almacemamientoProductoFinal = findViewById(R.id.etCreationRecetaAlmacenamientoGalletas);
        preparacionLimones = findViewById(R.id.etCreationRecetaPreparacionLimones);
        cantidadChocolate = findViewById(R.id.etCreationRecetaPreparacionChocolate);

        // Inicializar referencia de Firebase
        databaseRecetas = FirebaseDatabase.getInstance().getReference("Galletas");

        btnCreate = findViewById(R.id.btnCreationRecetaGalletas);
        btnCreate.setOnClickListener(v -> guardarReceta());
    }

    private void guardarReceta() {
        // Obtener valores de los campos
        String nombre = nombreGalleta.getText().toString().trim();
        String descripcion = recetaDescripcionGalleta.getText().toString().trim();
        String huevos = preparacionHuevosGalletas.getText().toString().trim();
        String esencia = cantidadDeEsenciaGalletas.getText().toString().trim();
        String azucar = preparacionAzucarGalletas.getText().toString().trim();
        String margarina = cantidadDeMargarinaGalletas.getText().toString().trim();
        String harina = harinaGalletas.getText().toString().trim();
        String fecula = feculaGalletas.getText().toString().trim();
        String polvoParaHornear = polvoHornear.getText().toString().trim();
        String limones = preparacionLimones.getText().toString().trim();
        String chocolate = cantidadChocolate.getText().toString().trim();
        String preparacion = preparacionGalletas.getText().toString().trim();
        String temperatura = temperaturaGalletas.getText().toString().trim();
        String porcionado = porcionadoGalleta.getText().toString().trim();
        String almacenamiento = almacemamientoProductoFinal.getText().toString().trim();

        // Convertir las cantidades a valores numéricos y validar conversiones
        double cantidadHuevos = huevos.isEmpty() ? 0 : Double.parseDouble(huevos);
        double cantidadEsencia = esencia.isEmpty() ? 0 : Double.parseDouble(esencia);
        double cantidadAzucar = azucar.isEmpty() ? 0 : Double.parseDouble(azucar);
        double cantidadMargarina = margarina.isEmpty() ? 0 : Double.parseDouble(margarina);
        double cantidadHarina = harina.isEmpty() ? 0 : Double.parseDouble(harina);
        double cantidadLimones = limones.isEmpty() ? 0 : Double.parseDouble(limones);
        double cantidadChocolate = chocolate.isEmpty() ? 0 : Double.parseDouble(chocolate);
        double feculaMaiz = fecula.isEmpty() ? 0 : Double.parseDouble(fecula);
        double polvoHornearCant = polvoParaHornear.isEmpty() ? 0 : Double.parseDouble(polvoParaHornear);
        double pesoUnidad = porcionado.isEmpty() ? 0 : Double.parseDouble(porcionado);

        // Log de todos los valores capturados para verificación
        Log.d("CreationRecetaGalletas", "Valores: Nombre = " + nombre +
                ", Descripción = " + descripcion +
                ", Huevos = " + cantidadHuevos +
                ", Esencia = " + cantidadEsencia +
                ", Azúcar = " + cantidadAzucar +
                ", Margarina = " + cantidadMargarina +
                ", Harina = " + cantidadHarina +
                ", Fecula = " + feculaMaiz +
                ", Polvo para Hornear = " + polvoHornearCant +
                ", Limones = " + cantidadLimones +
                ", Chocolate = " + cantidadChocolate +
                ", Preparación = " + preparacion +
                ", Temperatura = " + temperatura +
                ", Porcionado = " + pesoUnidad +
                ", Almacenamiento = " + almacenamiento);

        // Crear la receta con todos los datos
        List<Ingredientes.Ingrediente> listaIngredientes = new ArrayList<>();
        listaIngredientes.add(new Ingredientes.Ingrediente("Huevos", cantidadHuevos));
        listaIngredientes.add(new Ingredientes.Ingrediente("Esencia", cantidadEsencia));
        listaIngredientes.add(new Ingredientes.Ingrediente("Azúcar", cantidadAzucar));
        listaIngredientes.add(new Ingredientes.Ingrediente("Margarina", cantidadMargarina));
        listaIngredientes.add(new Ingredientes.Ingrediente("Harina", cantidadHarina));
        listaIngredientes.add(new Ingredientes.Ingrediente("Cantidad de Limones", cantidadLimones));
        listaIngredientes.add(new Ingredientes.Ingrediente("Cantidad de Chocolate", cantidadChocolate));
        listaIngredientes.add(new Ingredientes.Ingrediente("Fecula de Maíz", feculaMaiz));
        listaIngredientes.add(new Ingredientes.Ingrediente("Polvo para Hornear", polvoHornearCant));

        RecetaGalletas recetaGalletas = new RecetaGalletas(
                nombre, descripcion, cantidadHuevos, cantidadEsencia, cantidadAzucar, cantidadMargarina,
                cantidadHarina, cantidadLimones, cantidadChocolate, preparacion, temperatura,
                pesoUnidad, feculaMaiz, polvoHornearCant, almacenamiento, listaIngredientes
        );

        // Guardar en Firebase y mostrar feedback
        databaseRecetas.push().setValue(recetaGalletas).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Receta guardada correctamente", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Error al guardar la receta", Toast.LENGTH_LONG).show();
            }
        });
    }

}
