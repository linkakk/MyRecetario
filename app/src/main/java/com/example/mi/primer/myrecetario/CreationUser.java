package com.example.mi.primer.myrecetario;

import androidx.appcompat.app.AppCompatActivity;

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

public class CreationUser extends AppCompatActivity {

    // Instanciación de la parte gráfica
    Button btnCreate, btnComeBack;

    TextInputEditText nameUSerCreation, lastNameUserCreation, oldYearUserCreation, numberCellPhoneUserCreation, passwordUserCreation;

    Spinner spinnerPost;
    String selectPost;

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
                "Panadero", "Respostero", "Pastelero", "Cocinero"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cargosDisponibles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Cambié a un recurso estándar
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
                    lastNameUserCreation.requestFocus(); // Mover foco al campo de apellido
                    return true;
                }
                return false;
            }
        });

        lastNameUserCreation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    oldYearUserCreation.requestFocus(); // Saltar el Spinner y mover foco al campo de edad
                    return true;
                }
                return false;
            }
        });

        oldYearUserCreation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    numberCellPhoneUserCreation.requestFocus(); // Mover foco al campo de número de teléfono
                    return true;
                }
                return false;
            }
        });

        numberCellPhoneUserCreation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    passwordUserCreation.requestFocus(); // Mover foco al campo de contraseña
                    return true;
                }
                return false;
            }
        });

        // Manejar el campo de contraseña, última entrada
        passwordUserCreation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Acción cuando se presiona "Done", por ejemplo, crear usuario
                    Toast.makeText(CreationUser.this, "Formulario completado", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }
}
