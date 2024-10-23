package com.example.mi.primer.myrecetario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;

public class CreationRecetaGalletas extends AppCompatActivity {
    TextInputEditText nombreGalleta, recetaDescripcionGalleta, preparacionHuevosGalletas, cantidadDeEsenciaGalletas,preparacionAzucarGalletas,
            cantidadDeMargarinaGalletas, harinaGalletas,feculaGalletas,polvoHornear, preparacionGalletas,temperaturaGalletas, porcionadoGalleta, AlmacemamientoProductoFinal;

    Button btnCreate;

    DatabaseReference databaseRecetas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_receta_galletas);
    }
}