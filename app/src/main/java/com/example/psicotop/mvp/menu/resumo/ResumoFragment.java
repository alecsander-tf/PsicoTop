package com.example.psicotop.mvp.menu.resumo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.psicotop.modal.Meta;
import com.example.psicotop.mvp.menu.detalhesResumo.DetalhesResumoActivity;
import com.example.psicotop.R;
import com.example.psicotop.banco.Post;
import com.example.psicotop.modal.Emocao;
import com.example.psicotop.mvp.menu.resumo.adapter.EmocoesAdapter;
import com.example.psicotop.mvp.menu.resumo.adapter.MetaAdapter;

import java.util.ArrayList;
import java.util.List;

public class ResumoFragment extends Fragment implements ResumoContract.View{

    private ResumoContract.UserActionsListener presenter;
    private EmocoesAdapter mListEmocoesAdapter;
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
        TextView tvVerDetalhes = view.findViewById(R.id.tvVerDetalhes);

        RecyclerView recyclerViewEmocoes = view.findViewById(R.id.emocoes_list);
        recyclerViewEmocoes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mListEmocoesAdapter = new EmocoesAdapter(new ArrayList<Emocao>(), getContext());
        recyclerViewEmocoes.setAdapter(mListEmocoesAdapter);
        recyclerViewEmocoes.addItemDecoration(new HorizontalSpaceItemDecoration(3));

        RecyclerView recyclerViewMetas = view.findViewById(R.id.metasList);
        recyclerViewMetas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mListMetasAdapter = new MetaAdapter(new ArrayList<Meta>(), getContext());
        recyclerViewMetas.setAdapter(mListMetasAdapter);
        recyclerViewMetas.addItemDecoration(new VerticalSpaceItemDecoration(45));


        tvVerDetalhes.setOnClickListener(tvVerDetalhesClick());

        return view;
    }

    private View.OnClickListener tvVerDetalhesClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActivity(new Intent(getContext(), DetalhesResumoActivity.class));
            }
        };
    }

    @Override
    public void abrirActivity(Intent intent) {

        startActivity(intent);

    }

    @Override
    public void exibirEmocoes(List<Emocao> emocoes) {

        if (emocoes != null && emocoes.size() > 0){
            mListEmocoesAdapter.replaceData(emocoes);
        }
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
            presenter.carregarEmocoes();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

}
