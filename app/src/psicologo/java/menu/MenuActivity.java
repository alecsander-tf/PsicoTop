package menu;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psicotop.R;
import com.example.psicotop.banco.Post;
import com.example.psicotop.modal.Paciente;

import java.util.ArrayList;
import java.util.List;

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
        mListAdapter = new PacientesAdapter(new ArrayList<Paciente>());

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
}
