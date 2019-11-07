package com.example.psicotop.mvp.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.psicotop.R;
import com.example.psicotop.banco.Post;
import com.example.psicotop.modal.Paciente;
import com.example.psicotop.modal.Usuario;
import com.example.psicotop.mvp.registro.RegistroActivity;
import com.google.firebase.FirebaseApp;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    EditText etEmail;
    EditText etSenha;

    TextView tvCriarNovaConta;
    Button btnLogin;

    private LoginContract.UserActionsListener presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseApp.initializeApp(this);
        bind();

    }

    public void bind(){

        presenter = new LoginPresenter(new Post(), this);

        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);

        tvCriarNovaConta = findViewById(R.id.tvCriarNovaConta);
        btnLogin = findViewById(R.id.btnLogin);

        tvCriarNovaConta.setOnClickListener(criarNovaConta());
        btnLogin.setOnClickListener(loginUser());

    }

    private View.OnClickListener criarNovaConta(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.abrirActivity(RegistroActivity.class);
            }
        };
    }

    private View.OnClickListener loginUser(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.login(criarUsuario());

            }
        };
    }

    private Usuario criarUsuario(){

        Usuario usuario = new Paciente();

        usuario.setEmail(etEmail.getText().toString());
        usuario.setSenha(etSenha.getText().toString());

        return usuario;
    }

    @Override
    public void carregarActivity(Class<?> arg) {
        startActivity(new Intent(getApplicationContext(), arg));
    }

    @Override
    public void carregarMensagem(String msg) {
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
    }
}
















