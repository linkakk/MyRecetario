package com.example.mi.primer.myrecetario;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CreationReceta extends AppCompatActivity {

    // Declaración de los campos
    TextInputEditText nombreDeLaMasa, descripcionDeLaMasa, cantidadDeAguaMasa, cantidadDeLevaduraMasa,
            cantidadDePrefermentoMasa, cantidadDeHuevosMasa, cantidadDeEsenciaMasa, cantidadDeAzucarMasa, cantidadDeSalMasa,
            cantidadDeMargarinaMasa, cantidadDeHarinaMasa, preparacionMasa, temperaturasMasas, PorcionadoMasas, AlmacemamientoProductoFinal;

    Button btnCreate;

    DatabaseReference databaseRecetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_receta);

        // Inicialización de los campos de entrada
        nombreDeLaMasa = findViewById(R.id.etCreationNombreMasa);
        descripcionDeLaMasa = findViewById(R.id.etCreationRecetaDescripcion);
        cantidadDeAguaMasa = findViewById(R.id.etCreationRecetaAgua);
        cantidadDeLevaduraMasa = findViewById(R.id.etCreationRecetaPreparacionLevadura);
        cantidadDePrefermentoMasa = findViewById(R.id.etCreationRecetaPreparacionPrefermento);
        cantidadDeHuevosMasa = findViewById(R.id.etCreationRecetaPreparacionHuevos);
        cantidadDeEsenciaMasa = findViewById(R.id.etCreationRecetaPreparacionEsencia);
        cantidadDeAzucarMasa = findViewById(R.id.etCreationRecetaPreparacionAzucar);
        cantidadDeSalMasa = findViewById(R.id.etCreationRecetaPreparacionSal);
        cantidadDeMargarinaMasa = findViewById(R.id.etCreationRecetaPreparacionMargarina);
        cantidadDeHarinaMasa = findViewById(R.id.etCreationRecetaPreparacionHarina);
        preparacionMasa = findViewById(R.id.etCreationRecetaPreparacion);
        temperaturasMasas = findViewById(R.id.etCreationRecetaTemperatura);
        PorcionadoMasas = findViewById(R.id.etCreationRecetaPorcionado);
        AlmacemamientoProductoFinal = findViewById(R.id.etCreationRecetaAlmacenamiento);
        btnCreate = findViewById(R.id.btnCreationReceta);

        // Inicializar referencia de Firebase
        databaseRecetas = FirebaseDatabase.getInstance().getReference("recetas");

        // Funcionalidad del botón para guardar la receta
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarReceta();
            }
        });
    }

    private void guardarReceta() {
        // Obtener valores de los campos
        String nombre = nombreDeLaMasa.getText().toString().trim();
        String descripcion = descripcionDeLaMasa.getText().toString().trim();
        String agua = cantidadDeAguaMasa.getText().toString().trim();
        String levadura = cantidadDeLevaduraMasa.getText().toString().trim();
        String prefermento = cantidadDePrefermentoMasa.getText().toString().trim();
        String huevos = cantidadDeHuevosMasa.getText().toString().trim();
        String esencia = cantidadDeEsenciaMasa.getText().toString().trim();
        String azucar = cantidadDeAzucarMasa.getText().toString().trim();
        String sal = cantidadDeSalMasa.getText().toString().trim();
        String margarina = cantidadDeMargarinaMasa.getText().toString().trim();
        String harina = cantidadDeHarinaMasa.getText().toString().trim();
        String preparacion = preparacionMasa.getText().toString().trim();
        String temperatura = temperaturasMasas.getText().toString().trim();
        String porcionado = PorcionadoMasas.getText().toString().trim();
        String almacenamiento = AlmacemamientoProductoFinal.getText().toString().trim();

        // Validar que los campos esenciales no estén vacíos
        if (nombre.isEmpty()) {
            Toast.makeText(this, "El nombre es obligatorio", Toast.LENGTH_LONG).show();
            return;
        }

        // Convertir las cantidades a valores numéricos
        double cantidadAgua = Double.parseDouble(agua);
        double cantidadLevadura = Double.parseDouble(levadura);
        double cantidadPrefermento = Double.parseDouble(prefermento);
        double cantidadHuevos = Double.parseDouble(huevos);
        double cantidadEsencia = Double.parseDouble(esencia);
        double cantidadAzucar = Double.parseDouble(azucar);
        double cantidadSal = Double.parseDouble(sal);
        double cantidadMargarina = Double.parseDouble(margarina);
        double cantidadHarina = Double.parseDouble(harina);

        // Crear una lista de ingredientes
        List<Ingredientes.Ingrediente> listaIngredientes = new ArrayList<>();

        // Añadir ingredientes a la lista
        listaIngredientes.add(new Ingredientes.Ingrediente("Agua", cantidadAgua));
        listaIngredientes.add(new Ingredientes.Ingrediente("Levadura", cantidadLevadura));
        listaIngredientes.add(new Ingredientes.Ingrediente("Prefermento", cantidadPrefermento));
        listaIngredientes.add(new Ingredientes.Ingrediente("Huevos", cantidadHuevos));
        listaIngredientes.add(new Ingredientes.Ingrediente("Esencia", cantidadEsencia));
        listaIngredientes.add(new Ingredientes.Ingrediente("Azúcar", cantidadAzucar));
        listaIngredientes.add(new Ingredientes.Ingrediente("Sal", cantidadSal));
        listaIngredientes.add(new Ingredientes.Ingrediente("Margarina", cantidadMargarina));
        listaIngredientes.add(new Ingredientes.Ingrediente("Harina", cantidadHarina));

        // Usar la cantidad de agua como la cantidad original
        int cantidadOriginal = (int) cantidadAgua; // Ajusta según sea necesario


        // Crear un objeto de RecetaMasa
        RecetaMasa recetaMasa = new RecetaMasa(
                nombre,
                descripcion,
                cantidadAgua,
                cantidadLevadura,
                cantidadPrefermento,
                cantidadHuevos,
                cantidadEsencia,
                cantidadAzucar,
                cantidadSal,
                cantidadMargarina,
                cantidadHarina,
                preparacion,
                temperatura,
                porcionado,
                almacenamiento,
                cantidadOriginal,
                listaIngredientes // Pasar la lista de ingredientes
        );

        // Guardar la receta en Firebase
        databaseRecetas.push().setValue(recetaMasa).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Receta guardada correctamente", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Error al guardar la receta", Toast.LENGTH_LONG).show();
            }
        });
    }

}
