package com.example.psicotop.banco;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.psicotop.modal.Emocao;
import com.example.psicotop.modal.Meta;
import com.example.psicotop.modal.Paciente;
import com.example.psicotop.modal.Psicologo;
import com.example.psicotop.modal.Usuario;
import com.example.psicotop.utils.SingletonUserLogged;
import com.example.psicotop.utils.Util;
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

    private static ChildEventListener psicologoChildEventListener;
    private static ChildEventListener pacienteChildEventListener;

    static private Usuario currentUserLogged;

    static private List<Paciente> pacientes = new ArrayList<>();
    static private List<Psicologo> psicologos = new ArrayList<>();
    static private List<Emocao> listaEmocoes = new ArrayList<>();
    static private List<Meta> listaMetas = new ArrayList<>();

    private DatabaseReference psicologoRef;
    private DatabaseReference pacienteRef;

    private final Util util = new Util();

    public Usuario getCurrentUserLogged(){
        return currentUserLogged;
    }

    @Override
    public void carregarMetas(IPostListCallback callback) {
        if (listaMetas != null){
            callback.onLoaded(listaMetas);
        }
    }

    @Override
    public void carregarEmocoes(IPostListCallback callback) {
        if (listaEmocoes != null){
            callback.onLoaded(listaEmocoes);
        }
    }

    @Override
    public void registrarEmocao(Emocao e, IPostCallback callback) {
        DatabaseReference emocaoRef = pacienteRef.child(currentUserLogged.getId()).child("Emocoes").push();
        e.setId(emocaoRef.getKey());
        e.setDataRegistro(util.getDataAtual());
        emocaoRef.setValue(e);
        callback.onLoaded("Registrado!");
    }

    @Override
    public void alterarPaciente(Paciente p, IPostCallback callback) {
        String id = p.getId();
        pacienteRef.child(id).setValue(p);
        callback.onLoaded("Sucesso");
    }

    @Override
    public void carregarPacientes(Psicologo psicologo, IPostListCallback callback) {

        List<Paciente> pacientesReturn = new ArrayList<>();

        for (Paciente p : pacientes) {
            if (p.getEmailPsicologo().equals(psicologo.getEmail())){
                pacientesReturn.add(p);
            }
        }

        callback.onLoaded(pacientesReturn);
    }

    public Post(){

        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference();
        myAuth = FirebaseAuth.getInstance();

        psicologoRef = myRef.child("Usuario").child("Psicologo");
        pacienteRef = myRef.child("Usuario").child("Paciente");

        if (pacienteChildEventListener == null){

            pacienteChildEventListener = pacienteRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    Paciente u = dataSnapshot.getValue(Paciente.class);

                    if (!pacientes.contains(u)) {
                        pacientes.add(u);
                    }
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        if (psicologoChildEventListener == null) {

            psicologoChildEventListener = psicologoRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    Psicologo u = dataSnapshot.getValue(Psicologo.class);

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
                        usuario.setId(usuarioRef.getKey());
                        usuarioRef.setValue(usuario);
                    }else {
                        DatabaseReference usuarioRef = myRef.child("Usuario").child("Psicologo").push();
                        usuario.setId(usuarioRef.getKey());
                        usuarioRef.setValue(usuario);
                    }

                    callback.onLoaded("Usuário registrado");

                }else {
                    callback.onError(task.getException().getMessage());
                }

            }
        });
    }

    /**
     * Envia email para usuário, avisando sobre o seu registro na aplicação
     * */
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

    private void addListenerEmocoes(final DatabaseReference child){
        child.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Emocao e = dataSnapshot.getValue(Emocao.class);

                if (!listaEmocoes.contains(e)){
                    listaEmocoes.add(e);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                Emocao e = dataSnapshot.getValue(Emocao.class);
                if (listaEmocoes.contains(e)){
                    listaEmocoes.remove(e);
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void associarEmocoesPaciente(Paciente paciente, IPostListCallback callback){
        try {
            DatabaseReference child = myRef.child("Usuario").child("Paciente").child(paciente.getId()).child("Emocoes");
            addListenerEmocoes(child);
        }catch (Exception e){
            callback.onError(e.getMessage());
        }

    }

    @Override
    public void loginUsuario(String email, String senha, final IPostCallback callback){

        final DatabaseReference[] child = new DatabaseReference[1];
        final DatabaseReference[] child2 = new DatabaseReference[1];
        listaEmocoes = new ArrayList<>();

        myAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    for (Usuario usuario : pacientes){
                        if (usuario.getEmail().equals(myAuth.getCurrentUser().getEmail())){
                            currentUserLogged = usuario;
                            SingletonUserLogged.setCurrentUserLogged(usuario);
                            child[0] = myRef.child("Usuario").child("Paciente").child(currentUserLogged.getId()).child("Emocoes");
                            child2[0] = myRef.child("Usuario").child("Paciente").child(currentUserLogged.getId()).child("Metas");
                            addListenerEmocoes(child[0]);
                            addListenerMetas(child2[0]);
                            callback.onLoaded("");
                            return;
                        }
                    }
                    callback.onError("Usuário ou senha inválidos");

                }else {
                    callback.onError(task.getException().getMessage());
                }
            }
        });



    }

    private void addListenerMetas(DatabaseReference databaseReference) {
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Meta meta = dataSnapshot.getValue(Meta.class);
                if (!listaMetas.contains(meta)){
                    listaMetas.add(meta);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Meta meta = dataSnapshot.getValue(Meta.class);
                if (listaMetas.contains(meta)){
                    listaMetas.remove(meta);
                }
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

