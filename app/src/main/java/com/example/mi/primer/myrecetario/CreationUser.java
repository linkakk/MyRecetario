package com.example.mi.primer.myrecetario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreationUser extends AppCompatActivity {

    // Instanciación de la parte gráfica
    Button btnCreate, btnComeBack;
    TextInputEditText nameUSerCreation, lastNameUserCreation, oldYearUserCreation, numberCellPhoneUserCreation, passwordUserCreation;
    Spinner spinnerPost;
    String selectPost;

    // Instanciación de la base de datos
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Colaboradores");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_user);

        // Conexión de la parte gráfica con la lógica
        btnCreate = findViewById(R.id.btnCreationUser);
        btnComeBack = findViewById(R.id.btnComeBack);
        nameUSerCreation = findViewById(R.id.etCreationUserUser);
        lastNameUserCreation = findViewById(R.id.etLastNameUser);
        spinnerPost = findViewById(R.id.spinnerPost);
        oldYearUserCreation = findViewById(R.id.etOldYear);
        numberCellPhoneUserCreation = findViewById(R.id.etNumberPhone);
        passwordUserCreation = findViewById(R.id.etCreationPassword);

        // Manejar la selección de cargo (Spinner)
        String[] cargosDisponibles = new String[]{
                "Panadero", "Respostero", "Pastelero", "Cocinero","Administrador"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cargosDisponibles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPost.setAdapter(adapter);

        // Manejar la selección de cargo
        spinnerPost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectPost = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Ninguna acción específica
            }
        });

        // Configurar las acciones "Next" y "Done" en los campos de texto
        nameUSerCreation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    lastNameUserCreation.requestFocus();
                    return true;
                }
                return false;
            }
        });

        lastNameUserCreation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    oldYearUserCreation.requestFocus();
                    return true;
                }
                return false;
            }
        });

        oldYearUserCreation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    numberCellPhoneUserCreation.requestFocus();
                    return true;
                }
                return false;
            }
        });

        numberCellPhoneUserCreation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    passwordUserCreation.requestFocus();
                    return true;
                }
                return false;
            }
        });

        passwordUserCreation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Toast.makeText(CreationUser.this, "Formulario completado", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        // Botón Crear Usuario
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String camposFaltantes = camposIncompletos();
                if (camposFaltantes.isEmpty()) {
                    enviarInformacion();  // Se crea el usuario y se envía la información
                } else {
                    Toast.makeText(getApplicationContext(), "¡Por favor complete los siguientes campos: " + camposFaltantes, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Botón Volver
        btnComeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VolverAPaginaAnterior();
            }
        });
    }

    // Método para verificar campos incompletos
    private String camposIncompletos() {
        StringBuilder camposFaltantes = new StringBuilder();

        if (nameUSerCreation.getText().toString().trim().isEmpty()) {
            camposFaltantes.append("Nombre, ");
        }
        if (lastNameUserCreation.getText().toString().trim().isEmpty()) {
            camposFaltantes.append("Apellido, ");
        }
        if (oldYearUserCreation.getText().toString().trim().isEmpty()) {
            camposFaltantes.append("Edad, ");
        }
        if (numberCellPhoneUserCreation.getText().toString().trim().isEmpty()) {
            camposFaltantes.append("Número Telefónico, ");
        }
        if (passwordUserCreation.getText().toString().trim().isEmpty()) {
            camposFaltantes.append("Contraseña ");
        }
        if (camposFaltantes.length() > 0) {
            camposFaltantes.delete(camposFaltantes.length() - 2, camposFaltantes.length());  // Eliminar la última coma
        }

        return camposFaltantes.toString();
    }

    // Método para crear usuario y subir la información a Firebase
    public void enviarInformacion() {
        // Obtener el número de teléfono desde el campo de entrada
        String phoneUser = numberCellPhoneUserCreation.getText().toString().trim();

        if (phoneUser.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Error: Número de teléfono no válido", Toast.LENGTH_LONG).show();
            return;
        }

        DatabaseReference newUser = reference.child(phoneUser);

        // Subir los datos del nuevo usuario a Firebase
        newUser.child("Nombre").setValue(nameUSerCreation.getText().toString());
        newUser.child("Apellido").setValue(lastNameUserCreation.getText().toString());
        newUser.child("Cargo").setValue(selectPost);
        newUser.child("Edad").setValue(oldYearUserCreation.getText().toString());
        newUser.child("Número Celular").setValue(phoneUser);  // Aquí el número de teléfono ingresado
        newUser.child("Contraseña").setValue(passwordUserCreation.getText().toString());

        Toast.makeText(getApplicationContext(), "El usuario se ha creado con éxito", Toast.LENGTH_LONG).show();

        // Navegar a la siguiente actividad
        Intent intents = new Intent(this, MainActivity.class);
        startActivity(intents);
        finish();
    }


    // Método para volver a la página anterior
    public void VolverAPaginaAnterior() {
        Intent intent = new Intent(this, MainActivity.class);  // Asegúrate de que MainActivity es la actividad correcta
        startActivity(intent);
        finish();
    }
}
