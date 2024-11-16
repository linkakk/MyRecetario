package com.example.mi.primer.myrecetario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase; // Corregido el import
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListaDeTareas extends AppCompatActivity {

    private static final String TAG = "ListaDeTareasActivity";

    private ImageButton btnAgregarTarea;
    private RecyclerView recyclerView;
    private List<CreaTuLista> listaTareas;
    private DatabaseReference tareasCreadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_tareas);

        recyclerView = findViewById(R.id.recyclerView_tareas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listaTareas = new ArrayList<>(); // Inicializa la lista vacía

        btnAgregarTarea = findViewById(R.id.btnAgregarTarea);

        inicializarTareas();

        btnAgregarTarea.setOnClickListener(v -> {
            Log.d(TAG, "Botón agregar tarea presionado");
            irAgregarTarea();
        });
    }

    private void inicializarTareas() {
        TareasAdapter adapter = new TareasAdapter(listaTareas, tarea -> eliminarTarea(tarea));

        recyclerView.setAdapter(adapter);

        // Carga las tareas desde Firebase
        cargarTareasDesdeFirebase(adapter);
    }

    private void cargarTareasDesdeFirebase(TareasAdapter adapter) {
        tareasCreadas = FirebaseDatabase.getInstance().getReference("Tareas");

        tareasCreadas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaTareas.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    CreaTuLista tarea = snapshot.getValue(CreaTuLista.class);
                    if (tarea != null) {
                        listaTareas.add(tarea);
                        Log.d(TAG, "Tarea cargada: " + tarea.getDescripcion());
                    } else {
                        Log.w(TAG, "Tarea nula encontrada.");
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Error al cargar tareas: " + databaseError.getMessage());
            }
        });
    }

    private void eliminarTarea(CreaTuLista tarea) {
        // Busca la tarea en Firebase por su ID y la elimina
        tareasCreadas.orderByChild("id").equalTo(tarea.getId())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            childSnapshot.getRef().removeValue()
                                    .addOnSuccessListener(aVoid -> Log.d(TAG, "Tarea eliminada"))
                                    .addOnFailureListener(e -> Log.w(TAG, "Error al eliminar tarea", e));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e(TAG, "Error al eliminar tarea: " + databaseError.getMessage());
                    }
                });
    }

    public void irAgregarTarea() {
        Intent intent = new Intent(this, CreaTuTarea.class);
        startActivity(intent);
    }
}
