package com.example.psicotop.mvp.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.psicotop.R;
import com.example.psicotop.banco.Post;
import com.example.psicotop.modal.Usuario;

public class RegistroActivity extends AppCompatActivity implements RegistroContract.View{

    EditText etEmail;
    EditText etSenha;
    EditText etNome;
    EditText etSobrenome;

    RegistroContract.UserActionsListener presenter;

    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        bind();

    }

    public void bind(){

        presenter = new RegistroPresenter(this, new Post());

        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        etNome = findViewById(R.id.etNome);
        etSobrenome = findViewById(R.id.etSobrenome);

        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(registrarUsuario());

    }

    private Usuario criarUsuario(){

        Usuario usuario = new Usuario();

        usuario.setEmail(etEmail.getText().toString());
        usuario.setSenha(etSenha.getText().toString());
        usuario.setNome(etNome.getText().toString());
        usuario.setSobrenome(etSobrenome.getText().toString());

        return usuario;
    }

    public View.OnClickListener registrarUsuario(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.registrarUsuario(criarUsuario());

            }
        };
    }

    @Override
    public void carregarActivity(Class<?> arg) {
        startActivity(new Intent(this, arg));
    }

    @Override
    public void carregarMensagem(String msg) {
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
    }
}
