package com.example.psicotop.fragment;

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

import com.example.psicotop.R;
import com.example.psicotop.adapter.EmocoesAdapter;
import com.example.psicotop.adapter.HorizontalSpaceItemDecoration;
import com.example.psicotop.banco.Post;
import com.example.psicotop.detalhesResumo.DetalhesResumoActivity;
import com.example.psicotop.modal.Emocao;

import java.util.ArrayList;
import java.util.List;

public class ResumoCoresFragment extends Fragment implements ResumoCoresContract.View {

    private ResumoCoresContract.UserInteraction presenter;
    private EmocoesAdapter mListEmocoesAdapter;
    private TextView tvVerDetalhes;

    public ResumoCoresFragment() {
    }

    public static ResumoCoresFragment newInstance() {
        return new ResumoCoresFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ResumoCoresPresenter(this, new Post());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_resumo_cores, container, false);

        RecyclerView recyclerViewEmocoes = view.findViewById(R.id.emocoes_list);
        recyclerViewEmocoes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mListEmocoesAdapter = new EmocoesAdapter(new ArrayList<Emocao>(), getContext());
        recyclerViewEmocoes.setAdapter(mListEmocoesAdapter);
        recyclerViewEmocoes.addItemDecoration(new HorizontalSpaceItemDecoration(3));

        bind(view);

        return view;
    }

    private void bind(View view) {
        tvVerDetalhes = view.findViewById(R.id.tvVerDetalhes);
        tvVerDetalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActivity(new Intent(getContext(), DetalhesResumoActivity.class));
            }
        });
    }

    private void abrirActivity(Intent intent){
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void atualizarEmocoes(){
        presenter.carregarEmocoes();
    }

    @Override
    public void exibirEmocoes(List<Emocao> emocoes) {

        if (emocoes != null && emocoes.size() > 0){
            mListEmocoesAdapter.replaceData(emocoes);
        }
    }

    @Override
    public void mostrarMensagem(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }
}
