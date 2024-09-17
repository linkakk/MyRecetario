package com.example.mi.primer.myrecetario;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FirebaseDatabaseHelper {
    private DatabaseReference databaseReference;
    public FirebaseDatabaseHelper(){
        databaseReference = FirebaseDatabase.getInstance().getReference("Colaboradores");
    }

    public  interface UserCallback{
        void onCallback(Colaboradores colaboradores);
        void onError(String error);

    }

    public  void getUserByPhone(String phone,UserCallback callback){
        Query query = databaseReference.orderByChild("numeroUser").equalTo(phone);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot colaboradoresSnapshot : snapshot.getChildren()){
                        Colaboradores colaboradores = colaboradoresSnapshot.getValue(Colaboradores.class);
                        callback.onCallback(colaboradores);
                    }
                }else {
                    callback.onError("usuario no encontrado");
                }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                    callback.onError(error.getMessage());
            }
        });
    }
}
