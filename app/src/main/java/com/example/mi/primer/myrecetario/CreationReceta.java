package com.example.mi.primer.myrecetario;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference.CompletionListener;

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
        // Obtener valores de los campos, si están vacíos, dejar el valor como una cadena vacía
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
        if (nombre.isEmpty() || descripcion.isEmpty()) {
            Toast.makeText(this, "El nombre y la descripción son obligatorios", Toast.LENGTH_LONG).show();
            return;
        }

        // Crear un objeto de RecetaMasa, incluso si algunos campos están vacíos
        RecetaMasa recetaMasa = new RecetaMasa(nombre, descripcion, agua, levadura, prefermento, huevos, esencia, azucar, sal, margarina, harina, preparacion, temperatura, porcionado, almacenamiento);

        // Guardar en Firebase bajo un ID único
        String recetaId = databaseRecetas.push().getKey();
        databaseRecetas.child(recetaId).setValue(recetaMasa, new CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                if (error == null) {
                    // Si no hay error, mostrar Toast de éxito
                    Toast.makeText(CreationReceta.this, "La información se ha guardado correctamente", Toast.LENGTH_SHORT).show();
                    // Redirigir a WelcomeSession
                    Intent intent = new Intent(CreationReceta.this, WelcomeSession.class);
                    startActivity(intent);
                    finish(); // Finalizar la actividad actual
                } else {
                    // Si hay un error, mostrar Toast de error
                    Toast.makeText(CreationReceta.this, "Ups, no se guardó la información. Intenta nuevamente.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
