package menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psicotop.R;
import com.example.psicotop.banco.Post;
import com.example.psicotop.modal.Paciente;
import com.example.psicotop.utils.SingletonUserLogged;

import java.util.ArrayList;
import java.util.List;

import menuPrincipal.MenuPrincipalActivity;

public class MenuActivity extends AppCompatActivity implements MenuContract.View {

    private PacientesAdapter mListAdapter;
    private RecyclerView recyclerView;
    private MenuContract.UserInteraction presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bind();

        presenter = new MenuPresenter(this, new Post());
        presenter.buscarPacientes();
    }

    private void bind() {

        mListAdapter = new PacientesAdapter(new ArrayList<Paciente>(), new PacientesAdapter.ItemListener() {
            @Override
            public void pacienteClick(Paciente pacienteClicado) {
                SingletonUserLogged.setPacienteSelecionado(pacienteClicado);
                carregarActivity(MenuPrincipalActivity.class);
            }
        });

        recyclerView = findViewById(R.id.listEscolhaPaciente);
        recyclerView.setAdapter(mListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, R.drawable.custom_line));
    }

    @Override
    public void mostrarMensagem(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarPacientes(List<Paciente> lista) {
        mListAdapter.replaceData(lista);
    }

    @Override
    public void carregarActivity(Class<?> arg) {
        Intent intent = new Intent(getApplicationContext(), arg);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
