package menu.resumo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.psicotop.fragment.ResumoSemanalFragment;
import com.example.psicotop.modal.Meta;
import menu.detalhesResumo.DetalhesResumoActivity;
import com.example.psicotop.R;
import com.example.psicotop.banco.Post;
import com.example.psicotop.modal.Emocao;
import menu.resumo.adapter.EmocoesAdapter;
import menu.resumo.adapter.MetaAdapter;

import java.util.ArrayList;
import java.util.List;

public class ResumoFragment extends Fragment implements ResumoContract.View{

    private ResumoContract.UserActionsListener presenter;
    private MetaAdapter mListMetasAdapter;

    public ResumoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new ResumoPresenter(this, new Post());

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_resumo, container, false);

        RecyclerView recyclerViewMetas = view.findViewById(R.id.metasList);
        recyclerViewMetas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mListMetasAdapter = new MetaAdapter(new ArrayList<Meta>(), getContext());
        recyclerViewMetas.setAdapter(mListMetasAdapter);
        recyclerViewMetas.addItemDecoration(new VerticalSpaceItemDecoration(45));

        FragmentTransaction t = getFragmentManager().beginTransaction();
        t.replace(R.id.resumoSemanalFrame, ResumoSemanalFragment.newInstance());
        t.commit();

        return view;
    }

    @Override
    public void abrirActivity(Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @Override
    public void exibirMetas(List<Meta> metas) {

        if (metas != null && metas.size() > 0){
            mListMetasAdapter.replaceData(metas);
        }
    }

    @Override
    public void mostrarMensagem(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser){
            presenter.carregarMetas();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

}
