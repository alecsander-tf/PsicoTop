package com.example.psicotop.banco;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.psicotop.modal.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class Post implements IPost{

    private FirebaseAuth myAuth;
    private DatabaseReference myRef;
    private FirebaseDatabase mDatabase;

    public Post(){


        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference();
        myAuth = FirebaseAuth.getInstance();
    }

    public void registrarUsuario(final Usuario usuario, final IPostCallback callback) {
        myAuth.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    DatabaseReference usuarioRef = myRef.child("Usuario").push();
                    usuarioRef.setValue(usuario);

                    verificarEmail();

                    callback.onLoaded("Usuário registrado");

                }else {
                    callback.onError(task.getException().getMessage());
                }



            }
        });
    }

    private void verificarEmail(){
        FirebaseUser currentUser = myAuth.getCurrentUser();
        currentUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Log.d(TAG, "email válido");
                }else {
                    Log.d(TAG, "email inválido");
                }
            }
        });
    }

    public void loginUsuario(Usuario u, final IPostCallback callback){

        myAuth.signInWithEmailAndPassword(u.getEmail(), u.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d(TAG, "logado");
                    callback.onLoaded("Sucesso");
                }else {
                    Log.d(TAG, "nao logado");
                    Log.d(TAG, task.getException().getMessage());

                    callback.onError(task.getException().getMessage());
                }
            }
        });

    }

    public void postUser(Usuario usuario){

        /*myRef.addValueEventListener(new ValueEventListener() {
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
        });*/
    }
}

