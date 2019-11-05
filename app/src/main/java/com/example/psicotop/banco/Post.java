package com.example.psicotop.banco;

import androidx.annotation.NonNull;

import com.example.psicotop.modal.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Post {

    private DatabaseReference myRef;
    private FirebaseDatabase mDatabase;

    public Post(){
        mDatabase = FirebaseDatabase.getInstance("https://psico-237e7.firebaseio.com");
        myRef = mDatabase.getReference();
    }

    public void postUser(Usuario usuario){


        DatabaseReference newRef = myRef.child("usuario").push();
        newRef.setValue(usuario);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //Getting the data from snapshot

                    postSnapshot.getValue();
                    Usuario u = dataSnapshot.getValue(Usuario.class);

                    //Adding it to a string
                    String string = "Nome: " + u.getNome();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                String string = "Erro";
            }
        });
    }
}

