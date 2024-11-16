package com.example.mi.primer.myrecetario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CreaTuTarea extends AppCompatActivity {

    private static final String TAG = "CreaTuTarea";

    EditText inputDescription;
    Button btnFechaLimite, btnAddLista, btnGuardar;
    Spinner spinnerLista;
    String selectData = "Tareas diarias"; // Valor predeterminado
    ArrayList<String> listaCategorias = new ArrayList<>();
    ArrayAdapter<String> adapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Tareas");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_tu_tarea);

        // Inicialización de los campos
        inputDescription = findViewById(R.id.input_descripcion_tarea);
        btnFechaLimite = findViewById(R.id.btn_fecha_limite);
        btnAddLista = findViewById(R.id.btn_add_lista);
        btnGuardar = findViewById(R.id.btn_guardar_tarea);
        spinnerLista = findViewById(R.id.spinner_lista);

        // Configuración inicial de las categorías
        listaCategorias.add("Tareas diarias");
        listaCategorias.add("Aseo semanal");
        listaCategorias.add("Tareas mensuales");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaCategorias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLista.setAdapter(adapter);

        // Listener para Spinner
        spinnerLista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectData = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectData = "Tareas diarias"; // Valor predeterminado
            }
        });

        btnFechaLimite.setText("Sin fecha"); // Valor inicial para la fecha

        // Botón de calendario
        btnFechaLimite.setOnClickListener(v -> mostrarCalendario());

        // Botón de guardar
        btnGuardar.setOnClickListener(v -> guardarInformacion());
    }

    private void mostrarCalendario() {
        final Calendar calendario = Calendar.getInstance();
        int anio = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(CreaTuTarea.this,
                (view, year, month, dayOfMonth) -> {
                    String fechaSeleccionada = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
                    btnFechaLimite.setText(fechaSeleccionada);
                }, anio, mes, dia);

        datePickerDialog.show();
    }

    private void guardarInformacion() {
        try {
            String descripcion = inputDescription.getText().toString().trim();
            long id = System.currentTimeMillis();

            if (descripcion.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa una descripción de la tarea.", Toast.LENGTH_SHORT).show();
                return;
            }

            Map<String, Object> tareaMap = new HashMap<>();
            tareaMap.put("id", id);
            tareaMap.put("descripcion", descripcion);
            tareaMap.put("fechaLimite", btnFechaLimite.getText().toString());
            tareaMap.put("categoria", selectData != null ? selectData : "Tareas diarias");

            Log.d(TAG, "Guardando tarea: " + tareaMap);

            reference.push().setValue(tareaMap)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(CreaTuTarea.this, "Tarea guardada correctamente", Toast.LENGTH_SHORT).show();
                        inputDescription.setText("");
                        spinnerLista.setSelection(0);
                        btnFechaLimite.setText("Sin fecha");
                    })
                    .addOnFailureListener(e -> Toast.makeText(CreaTuTarea.this, "Error al guardar la tarea", Toast.LENGTH_SHORT).show());
        } catch (Exception e) {
            Log.e(TAG, "Error al guardar la tarea", e);
            Toast.makeText(this, "Ocurrió un error al guardar la tarea.", Toast.LENGTH_SHORT).show();
        }
    }
}
