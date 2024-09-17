package com.example.mi.primer.myrecetario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Referencias a los EditText y Button
    Button btnPrueba;
    EditText etLoginUser, etLoginPassword;

     CheckBox checkboxRemember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar las vistas
        btnPrueba = findViewById(R.id.buttonPrueba);
        etLoginUser = findViewById(R.id.etLoginUser);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        checkboxRemember = findViewById(R.id.checkboxRemember);

        // Configurar el listener para el botón "Next" en el campo de usuario
        etLoginUser.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    // Cambiar el foco al campo de contraseña
                    etLoginPassword.requestFocus();
                    return true;
                }
                return false;
            }
        });

        // Configurar el listener para el botón "Done" en el campo de contraseña
        etLoginPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Mostrar un mensaje de que el login se ha completado
                    Toast.makeText(MainActivity.this, "Login completado", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        // Listener para el botón "Prueba" que abre otra actividad
        btnPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prueba();
            }
        });
    }

    // Método que abre la actividad CreationUser
    public void Prueba() {
        Intent intent = new Intent(this, CreationUser.class);
        startActivity(intent);
    }
}
