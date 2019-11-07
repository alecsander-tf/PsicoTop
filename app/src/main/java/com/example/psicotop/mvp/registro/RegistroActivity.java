package com.example.psicotop.mvp.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.psicotop.R;
import com.example.psicotop.banco.Post;
import com.example.psicotop.modal.Paciente;
import com.example.psicotop.modal.Psicologo;
import com.example.psicotop.modal.Usuario;
import com.example.psicotop.utils.Util;

public class RegistroActivity extends AppCompatActivity implements RegistroContract.View{

    RegistroContract.View view;
    EditText etEmail;
    EditText etSenha;
    EditText etNome;
    EditText etSobrenome;
    EditText etConfirmaSenha;
    EditText etPsicologo;
    Spinner spinnerUsuario;

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

        spinnerUsuario = findViewById(R.id.spinnerUsuario);
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        etNome = findViewById(R.id.etNome);
        etSobrenome = findViewById(R.id.etSobrenome);
        etConfirmaSenha = findViewById(R.id.etConfirmaSenha);
        etPsicologo = findViewById(R.id.etPsicologo);

        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(registrarUsuario());

    }

    private Usuario criarUsuario(){

        Usuario usuario;

        if (spinnerUsuario.getSelectedItem().toString().equals("Paciente")){
            usuario = new Paciente();
        }else {
            usuario = new Psicologo();
        }

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
        startActivity(new Intent(this, arg));
    }

    @Override
    public void carregarMensagem(String msg) {
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
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
        }else if (util.verificaCampoNulo(etPsicologo.getText().toString())){
            msg = "Campo de Psicólogo vazio";
            status = true;
        }else if (!etSenha.getText().toString().equals(etConfirmaSenha.getText().toString())){
            msg = "Campos de Senha e Confirmação não são iguais";
            status = true;
        }

        if (status){
            this.carregarMensagem(msg);
            return true;
        }

        return false;
    }
}





