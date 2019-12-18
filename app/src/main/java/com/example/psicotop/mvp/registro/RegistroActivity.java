package com.example.psicotop.mvp.registro;

import android.app.ProgressDialog;
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
import com.example.psicotop.utils.Util;

public class RegistroActivity extends AppCompatActivity implements RegistroContract.View{

    RegistroContract.View view;
    EditText etEmail;
    EditText etSenha;
    EditText etNome;
    EditText etSobrenome;
    EditText etConfirmaSenha;
    EditText etPsicologo;
    TextView tvPsicologo;

    ProgressDialog progDailog;

    RegistroContract.UserActionsListener presenter;

    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        bind();

    }

    public void bind(){

        view = this;
        presenter = new RegistroPresenter(this, new Post());

        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        etNome = findViewById(R.id.etNome);
        etSobrenome = findViewById(R.id.etSobrenome);
        etConfirmaSenha = findViewById(R.id.etConfirmaSenha);
        etPsicologo = findViewById(R.id.etPsicologo);
        tvPsicologo = findViewById(R.id.tvPsicologo);

        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(registrarUsuario());

        progDailog = new ProgressDialog(RegistroActivity.this);
        progDailog.setMessage("Registrando...");
        progDailog.setIndeterminate(false);
        progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDailog.setCancelable(true);

    }

    private Paciente criarUsuario(){

        Paciente paciente = new Paciente();

        paciente.setEmail(etEmail.getText().toString());
        paciente.setSenha(etSenha.getText().toString());
        paciente.setNome(etNome.getText().toString());
        paciente.setSobrenome(etSobrenome.getText().toString());
        paciente.setEmailPsicologo(etPsicologo.getText().toString());

        return paciente;
    }

    public View.OnClickListener registrarUsuario(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!camposInvalidos()){
                    if (!presenter.psicologoExiste(etPsicologo.getText().toString())){
                        view.carregarMensagem("Psicólogo não existe");
                    }else {
                        presenter.registrarUsuario(criarUsuario());
                    }
                }
            }
        };
    }

    @Override
    public void carregarActivity(Class<?> arg) {
        Intent intent = new Intent(this, arg);
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
        if (carregando){
            progDailog.show();
        }else {
            progDailog.dismiss();
        }
    }

    private boolean camposInvalidos() {

        Util util = new Util();

        String msg = "";
        boolean status = false;

        if (util.verificaCampoNulo(etEmail.getText().toString())) {
            msg = "Campo de Email vazio";
            status = true;
        }else if (util.verificaCampoNulo(etNome.getText().toString())) {
            msg = "Campo de Nome vazio";
            status = true;
        }else if (util.verificaCampoNulo(etSobrenome.getText().toString())) {
            msg = "Campo de Sobrenome vazio";
            status = true;
        }else if (util.verificaCampoNulo(etSenha.getText().toString())){
            msg = "Campo de Senha vazio";
            status = true;
        }else if (!etSenha.getText().toString().equals(etConfirmaSenha.getText().toString())){
            msg = "Campos de Senha e Confirmação não são iguais";
            status = true;
        }else if (util.verificaCampoNulo(etPsicologo.getText().toString())){
            msg = "Campo de Psicólogo vazio";
            status = true;
        }

        if (status){
            this.carregarMensagem(msg);
            return true;
        }

        return false;
    }
}






