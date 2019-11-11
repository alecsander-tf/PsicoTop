package com.example.psicotop.mvp.configuracoes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.psicotop.R;
import com.example.psicotop.banco.Post;
import com.example.psicotop.modal.Paciente;
import com.example.psicotop.modal.Usuario;

public class ConfiguracoesActivity extends AppCompatActivity implements ConfiguracoesContract.View {

    Button btnSalvar;
    EditText etEmailPsicologo;

    ProgressDialog progDailog;

    ConfiguracoesContract.UserActionsListener presenter;

    private void bind(){

        presenter = new ConfiguracoesPresenter(this, new Post());
        etEmailPsicologo = findViewById(R.id.etEmailPsicologo);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(salvarAtualizacao());

        progDailog = new ProgressDialog(ConfiguracoesActivity.this);
        progDailog.setMessage("Carregando...");
        progDailog.setIndeterminate(false);
        progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDailog.setCancelable(true);

    }

    private View.OnClickListener salvarAtualizacao(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setCarregando(true);
                if (!presenter.psicologoExiste(etEmailPsicologo.getText().toString())){
                    carregarMensagem("Psicólogo não existe!");
                    setCarregando(false);
                    return;
                }

                Paciente currentUserLogged = (Paciente) presenter.getCurrentUserLogged();
                currentUserLogged.setEmailPsicologo(etEmailPsicologo.getText().toString());
                presenter.alterarDados(currentUserLogged);
                setCarregando(false);
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

    @Override
    public void carregarMensagem(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setCarregando(boolean carregando) {

        if (carregando){
            progDailog.show();
        }else {
            progDailog.dismiss();
        }
    }
}
