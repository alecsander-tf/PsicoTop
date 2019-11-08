package com.example.psicotop.mvp.configuracoes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.psicotop.R;
import com.example.psicotop.banco.Post;
import com.example.psicotop.modal.Paciente;
import com.example.psicotop.modal.Usuario;

public class ConfiguracoesActivity extends AppCompatActivity implements ConfiguracoesContract.View {

    Button btnSalvar;
    EditText etEmailPsicologo;

    ConfiguracoesContract.UserActionsListener presenter;

    private void bind(){

        presenter = new ConfiguracoesPresenter(this, new Post());
        etEmailPsicologo = findViewById(R.id.etEmailPsicologo);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(salvarAtualizacao());

    }

    private View.OnClickListener salvarAtualizacao(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Paciente currentUserLogged = (Paciente) presenter.getCurrentUserLogged();
                currentUserLogged.setEmailPsicologo(etEmailPsicologo.getText().toString());

                presenter.alterarDados(currentUserLogged);
            }
        };
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        bind();
    }

    @Override
    public void carregarActivity(Class<?> arg) {

    }
}
