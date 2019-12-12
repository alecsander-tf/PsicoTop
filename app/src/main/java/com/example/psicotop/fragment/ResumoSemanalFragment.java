package com.example.psicotop.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.psicotop.R;
import com.example.psicotop.modal.Emocao;

import java.util.ArrayList;
import java.util.List;

import menu.resumo.HorizontalSpaceItemDecoration;
import menu.resumo.adapter.EmocoesAdapter;

public class ResumoSemanalFragment extends Fragment implements ResumoSemanalContract.View {

    private ResumoSemanalContract.UserInteraction presenter;
    private EmocoesAdapter mListEmocoesAdapter;

    public ResumoSemanalFragment() {
    }

    public static ResumoSemanalFragment newInstance() {
        return new ResumoSemanalFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_resumo_semanal, container, false);

        RecyclerView recyclerViewEmocoes = view.findViewById(R.id.emocoes_list);
        recyclerViewEmocoes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mListEmocoesAdapter = new EmocoesAdapter(new ArrayList<Emocao>(), getContext());
        recyclerViewEmocoes.setAdapter(mListEmocoesAdapter);
        recyclerViewEmocoes.addItemDecoration(new HorizontalSpaceItemDecoration(3));

        return view;
    }

    @Override
    public void exibirEmocoes(List<Emocao> emocoes) {

        if (emocoes != null && emocoes.size() > 0){
            mListEmocoesAdapter.replaceData(emocoes);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser){
            presenter.carregarEmocoes();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void mostrarMensagem(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }
}
