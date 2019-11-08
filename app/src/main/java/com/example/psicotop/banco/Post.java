package com.example.psicotop.banco;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.psicotop.modal.Paciente;
import com.example.psicotop.modal.Psicologo;
import com.example.psicotop.modal.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class Post implements IPost{

    private FirebaseAuth myAuth;
    private DatabaseReference myRef;
    private FirebaseDatabase mDatabase;

    private static ChildEventListener childEventListener;

    static private List<Usuario> usuarios = new ArrayList<>();
    static private List<Usuario> psicologos = new ArrayList<>();

    public Post(){

        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference();
        myAuth = FirebaseAuth.getInstance();

        DatabaseReference psicologoRef = myRef.child("Usuario").child("Psicologo");

        if (childEventListener == null) {

            childEventListener = psicologoRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    Usuario u = dataSnapshot.getValue(Psicologo.class);

                    if (!psicologos.contains(u)) {
                        psicologos.add(u);
                    }

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    if (!psicologos.contains(dataSnapshot.getValue(Psicologo.class))) {
                        psicologos.add(dataSnapshot.getValue(Psicologo.class));
                    }

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    psicologos.remove(dataSnapshot.getValue(Psicologo.class));
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public boolean psicologoExiste(String email){

        for (Usuario psicologo : psicologos){
            if (email.equals(psicologo.getEmail())){
                return true;
            }
        }

        return false;

    }

    public void registrarUsuario(final Usuario usuario, final IPostCallback callback) {
        myAuth.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    if (usuario instanceof Paciente){
                        DatabaseReference usuarioRef = myRef.child("Usuario").child("Paciente").push();
                        usuarioRef.setValue(usuario);
                        usuarios.add(usuario);
                    }else {
                        DatabaseReference usuarioRef = myRef.child("Usuario").child("Psicologo").push();
                        usuarioRef.setValue(usuario);
                        psicologos.add(usuario);
                    }

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

    @Override
    public void verificarUsuario(final IPostListCallback callback) {
        DatabaseReference usuarioRefmyRef = myRef.child("Usuario").child("Psicologo");
    }

    public void loginUsuario(String email, String senha, final IPostCallback callback){

        myAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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

