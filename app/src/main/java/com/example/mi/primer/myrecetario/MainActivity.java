package com.example.mi.primer.myrecetario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    // Referencias a los EditText y Button
    Button btnLogin;
    EditText etPhoneNumber, etLoginPassword;
    TextView informacionDeBloqueo;

    // Firebase Database Reference
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Colaboradores");

    // Variables para el control de intentos fallidos
    private int intentosFallidos = 0;
    private boolean bloqueado = false;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar las vistas
        btnLogin = findViewById(R.id.buttonPrueba);
        etPhoneNumber = findViewById(R.id.etLoginUser);
        etLoginPassword = findViewById(R.id.etLoginPassword);

        // Configurar el listener para el botón "Next" en el campo de número telefónico
        etPhoneNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    etLoginPassword.requestFocus(); // Cambiar el foco al campo de contraseña
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
                    autenticarUsuario(); // Llamar a la función de autenticación
                    return true;
                }
                return false;
            }
        });

        // Listener para el botón de login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autenticarUsuario();
            }
        });
    }

    // Método para autenticar usuario
    private void autenticarUsuario() {
        if (bloqueado) {
            Toast.makeText(this, "Espere el tiempo de bloqueo", Toast.LENGTH_SHORT).show();
            return;
        }

        String phoneNumber = etPhoneNumber.getText().toString().trim();
        String password = etLoginPassword.getText().toString().trim();

        if (phoneNumber.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese el número de teléfono y la contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        reference.child(phoneNumber).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String passwordFirebase = snapshot.child("Contraseña").getValue(String.class);

                    if (passwordFirebase != null && passwordFirebase.equals(password)) {
                        irAWelcome();
                        intentosFallidos = 0;  // Reiniciar los intentos fallidos después de un login exitoso
                    } else {
                        manejarIntentoFallido();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Número de teléfono no registrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(MainActivity.this, "Error de autenticación", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para manejar los intentos fallidos
    private void manejarIntentoFallido() {
        intentosFallidos++;
        if (intentosFallidos >= 5) {
            bloquearUsuario();
        } else {
            informacionDeBloqueo.setTextColor(getResources().getColor(R.color.red));
            informacionDeBloqueo.setText("Contraseña incorrecta. Intentos fallidos: " + intentosFallidos);
        }
    }

    // Método para bloquear el acceso temporalmente
    private void bloquearUsuario() {
        if (intentosFallidos == 5) {
            bloqueado = true;
            informacionDeBloqueo.setText("Demasiados intentos fallidos. Bloqueado por 1 minuto.");
            handler.postDelayed(() -> {
                bloqueado = false;
                intentosFallidos = 0; // Reiniciar los intentos después del bloqueo
                informacionDeBloqueo.setText("");
            }, 60000);  // 1 minuto de bloqueo
        } else if (intentosFallidos > 5 && intentosFallidos < 10) {
            bloqueado = true;
            informacionDeBloqueo.setText("Bloqueado por 10 minutos.");
            handler.postDelayed(() -> {
                bloqueado = false;
                informacionDeBloqueo.setText("");
            }, 600000);  // 10 minutos de bloqueo
        } else if (intentosFallidos >= 10) {
            eliminarApp();
        }
    }

    // Método para eliminar la app (puedes personalizarlo)
    private void eliminarApp() {
        informacionDeBloqueo.setText("Demasiados intentos fallidos. Eliminando la aplicación.");
        // Aquí puedes implementar la lógica para eliminar la app o evitar su uso
    }
    //ir a welcome
    public  void  irAWelcome(){
        Intent intent = new Intent(this,com.example.mi.primer.myrecetario.WelcomeSession.class);
        startActivity(intent);
        finish();
    }
}
