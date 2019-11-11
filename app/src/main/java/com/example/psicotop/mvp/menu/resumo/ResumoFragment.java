package com.example.psicotop.mvp.menu.resumo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.psicotop.R;
import com.example.psicotop.banco.Post;
import com.example.psicotop.modal.Emocao;

import java.util.ArrayList;
import java.util.List;

public class ResumoFragment extends Fragment implements ResumoContract.View{

    private ResumoContract.UserActionsListener presenter;
    private EmocoesAdapter mListAdapter;

    public ResumoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new EmocoesAdapter(new ArrayList<Emocao>(0));
        presenter = new ResumoPresenter(this, new Post());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_resumo, container, false);

        return view;
    }

    @Override
    public void abrirActivity(Intent intent) {

    }

    @Override
    public void exibirEmocoes(List<Emocao> emocoes) {
        List<Emocao> emocaoList = new ArrayList<>();

        if (emocoes != null){
            emocaoList = new ArrayList<>(emocoes);
        }

        mListAdapter.replaceData(emocaoList);


    }

    private static class EmocoesAdapter extends RecyclerView.Adapter<EmocoesAdapter.ViewHolder>{

        private List<Emocao> mEmocoes;

        public EmocoesAdapter(List<Emocao> emocaos){
            mEmocoes = emocaos;
        }

        @Override
        public EmocoesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View noteView = inflater.inflate(R.layout.emocao_item, parent, false);

            return new ViewHolder(noteView);
        }

        @Override
        public void onBindViewHolder(@NonNull EmocoesAdapter.ViewHolder holder, int position) {
            Emocao e = mEmocoes.get(position);

            //holder.diaDaSemana.setText(e.getDiaDaSemana());
            holder.diaDaSemana.setText("S");
        }

        public void replaceData(List<Emocao> emocaos){
            mEmocoes = emocaos;
            notifyDataSetChanged();
        }

        public Emocao getItem(int position) {
            return mEmocoes.get(position);
        }

        @Override
        public int getItemCount() {
            if (mEmocoes == null){
                return 0;
            }

            return mEmocoes.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView diaDaSemana;

            public ViewHolder(View itemView) {
                super(itemView);
                diaDaSemana = itemView.findViewById(R.id.tvDiaDaSemana);

            }
        }

    }

}
