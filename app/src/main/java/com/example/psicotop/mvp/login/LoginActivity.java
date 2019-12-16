package com.example.psicotop.mvp.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.psicotop.R;
import com.example.psicotop.banco.Post;
import com.example.psicotop.modal.Paciente;
import com.example.psicotop.modal.Usuario;
import com.example.psicotop.mvp.registro.RegistroActivity;
import com.google.firebase.FirebaseApp;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    EditText etEmail;
    EditText etSenha;
    boolean teste = true;

    TextView tvCriarNovaConta;
    Button btnLogin;

    ProgressDialog progDailog;

    private LoginContract.UserActionsListener presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        progDailog = new ProgressDialog(LoginActivity.this);
        progDailog.setMessage("Entrando...");
        progDailog.setIndeterminate(false);
        progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDailog.setCancelable(true);

        if (teste){
            etEmail.setText("atalector@gmail.com");
            etSenha.setText("123456");
        }

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

                presenter.login(etEmail.getText().toString(), etSenha.getText().toString());

            }
        };
    }

    @Override
    public void carregarActivity(Class<?> arg) {
        Intent intent = new Intent(getApplicationContext(), arg);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void carregarMensagem(String msg) {
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setCarregando(boolean carregando) {

        if (progDailog != null){
            if (carregando){
                progDailog.show();
            }else {
                progDailog.dismiss();
            }
        }
    }
}
















