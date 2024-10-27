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

public class CreationRecetaBatidos extends AppCompatActivity {
    TextInputEditText nombreBatido, descripcionBatido, huevosBatido, esenciaBatido, azucarBatido, grasaBatido,
            harinaBatido, polvoHornearBatido, lecheBatido, limonBatido, limonRalladoBatido, chocolateBatido, zanahoriaBatido,
            preparacionBatido, temperaturaBatido, porcionadoBatido, almacenamientoBatido;
    Button btnCreateBatido;

    private DatabaseReference databaseRecetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_receta_batidos);

        // Inicialización de los campos de entrada
        nombreBatido = findViewById(R.id.etCreationNombreTorta);
        descripcionBatido = findViewById(R.id.etCreationRecetaDescripcionTorta);
        huevosBatido = findViewById(R.id.etCreationRecetaPreparacionHuevosTorta);
        esenciaBatido = findViewById(R.id.etCreationRecetaPreparacionEsenciaTorta);
        azucarBatido = findViewById(R.id.etCreationRecetaPreparacionAzucarTorta);
        grasaBatido = findViewById(R.id.etCreationRecetaPreparacionMargarinaTorta);
        harinaBatido = findViewById(R.id.etCreationRecetaPreparacionHarinaTorta);
        polvoHornearBatido = findViewById(R.id.etCreationRecetaPreparacionPolvoHornearTorta);
        lecheBatido = findViewById(R.id.etCreationRecetaPreparacionLecheTorta);
        limonBatido = findViewById(R.id.etCreationRecetaPreparacionLimonesTorta);
        limonRalladoBatido = findViewById(R.id.etCreationRecetaPreparacionLimonesRayaduraTorta);
        chocolateBatido = findViewById(R.id.etCreationRecetaPreparacionChocolateTorta);
        zanahoriaBatido = findViewById(R.id.etCreationRecetaPreparacionZanahoriaTorta);
        preparacionBatido = findViewById(R.id.etCreationRecetaPreparacionTorta);
        temperaturaBatido = findViewById(R.id.etCreationRecetaTemperaturaTorta);
        porcionadoBatido = findViewById(R.id.etCreationRecetaPorcionadoTorta);
        almacenamientoBatido = findViewById(R.id.etCreationRecetaAlmacenamientoTorta);
        btnCreateBatido = findViewById(R.id.btnCreationRecetaTorta);

        // Inicializar referencia de Firebase
        databaseRecetas = FirebaseDatabase.getInstance().getReference("Tortas");

        btnCreateBatido.setOnClickListener(v -> guardarReceta());
    }

    private void guardarReceta() {
        // Obtener valores de los campos
        String nombre = nombreBatido.getText().toString().trim();
        String descripcion = descripcionBatido.getText().toString().trim();
        String huevos = huevosBatido.getText().toString().trim();
        String esencia = esenciaBatido.getText().toString().trim();
        String azucar = azucarBatido.getText().toString().trim();
        String margarina = grasaBatido.getText().toString().trim();
        String harina = harinaBatido.getText().toString().trim();
        String polvoParaHornear = polvoHornearBatido.getText().toString().trim();
        String leche = lecheBatido.getText().toString().trim();
        String limones = limonBatido.getText().toString().trim();
        String limonesRallado = limonRalladoBatido.getText().toString().trim();
        String chocolate = chocolateBatido.getText().toString().trim();
        String zanahoria = zanahoriaBatido.getText().toString().trim();
        String preparacion = preparacionBatido.getText().toString().trim();
        String temperatura = temperaturaBatido.getText().toString().trim();
        String porcionado = porcionadoBatido.getText().toString().trim();
        String almacenamiento = almacenamientoBatido.getText().toString().trim();

        // Convertir las cantidades a valores numéricos y validar conversiones
        double cantidadHuevos = huevos.isEmpty() ? 0 : Double.parseDouble(huevos);
        double cantidadEsencia = esencia.isEmpty() ? 0 : Double.parseDouble(esencia);
        double cantidadAzucar = azucar.isEmpty() ? 0 : Double.parseDouble(azucar);
        double cantidadMargarina = margarina.isEmpty() ? 0 : Double.parseDouble(margarina);
        double cantidadHarina = harina.isEmpty() ? 0 : Double.parseDouble(harina);
        double cantidadLimones = limones.isEmpty() ? 0 : Double.parseDouble(limones);
        double cantidadLimonesRallados = limonesRallado.isEmpty() ? 0 : Double.parseDouble(limonesRallado);
        double cantidadChocolate = chocolate.isEmpty() ? 0 : Double.parseDouble(chocolate);
        double cantidadZanahoria = zanahoria.isEmpty() ? 0 : Double.parseDouble(zanahoria);
        double cantidadLeche = leche.isEmpty() ? 0 : Double.parseDouble(leche);
        double cantidadPolvoParaHornear = polvoParaHornear.isEmpty() ? 0 : Double.parseDouble(polvoParaHornear);
        double temperaturaHorno = temperatura.isEmpty() ? 0 : Double.parseDouble(temperatura);

        Log.d("CreationRecetaBatidos", "Valores: Nombre = " + nombre +
                ", Descripción = " + descripcion +
                ", Huevos = " + cantidadHuevos +
                ", Esencia = " + cantidadEsencia +
                ", Azúcar = " + cantidadAzucar +
                ", Margarina = " + cantidadMargarina +
                ", Harina = " + cantidadHarina +
                ", Polvo para Hornear = " + cantidadPolvoParaHornear +
                ", Limones = " + cantidadLimones +
                ", Limones Rallados = " + cantidadLimonesRallados +
                ", Leche = " + cantidadLeche +
                ", Zanahoria = " + cantidadZanahoria +
                ", Chocolate = " + cantidadChocolate +
                ", Preparación = " + preparacion +
                ", Temperatura = " + temperaturaHorno +
                ", Porcionado = " + porcionado +
                ", Almacenamiento = " + almacenamiento);

        // Crear la receta con todos los datos
        List<Ingredientes.Ingrediente> listaIngredientes = new ArrayList<>();
        listaIngredientes.add(new Ingredientes.Ingrediente("Huevos", cantidadHuevos));
        listaIngredientes.add(new Ingredientes.Ingrediente("Esencia", cantidadEsencia));
        listaIngredientes.add(new Ingredientes.Ingrediente("Azúcar", cantidadAzucar));
        listaIngredientes.add(new Ingredientes.Ingrediente("Margarina", cantidadMargarina));
        listaIngredientes.add(new Ingredientes.Ingrediente("Harina", cantidadHarina));
        listaIngredientes.add(new Ingredientes.Ingrediente("Cantidad de Limones", cantidadLimones));
        listaIngredientes.add(new Ingredientes.Ingrediente("Cantidad de Limones rallados", cantidadLimonesRallados));
        listaIngredientes.add(new Ingredientes.Ingrediente("Cantidad de zanahoria", cantidadZanahoria));
        listaIngredientes.add(new Ingredientes.Ingrediente("Cantidad de Leche", cantidadLeche));
        listaIngredientes.add(new Ingredientes.Ingrediente("Cantidad de Chocolate", cantidadChocolate));
        listaIngredientes.add(new Ingredientes.Ingrediente("Polvo para Hornear", cantidadPolvoParaHornear));

        RecetaBatidos recetaBatidos = new RecetaBatidos(
                nombre,
                descripcion,
                cantidadAzucar,
                cantidadMargarina,
                cantidadEsencia,
                cantidadHuevos,
                cantidadHarina,
                cantidadPolvoParaHornear,
                cantidadLeche,
                cantidadLimones,
                cantidadZanahoria,
                cantidadLimonesRallados,
                cantidadChocolate,
                temperaturaHorno,
                preparacion,  // Asignar `preparacion` directamente
                almacenamiento,
                porcionado,  // Cambiado para coincidir con `String` en `RecetaBatidos`
                listaIngredientes
        );

        // Guardar en Firebase y mostrar feedback
        databaseRecetas.push().setValue(recetaBatidos).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Receta guardada correctamente", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Error al guardar la receta", Toast.LENGTH_LONG).show();
            }
        });
    }
}
