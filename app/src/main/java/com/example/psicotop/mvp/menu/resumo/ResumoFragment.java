package com.example.psicotop.mvp.menu.resumo;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.psicotop.DetalhesResumoActivity;
import com.example.psicotop.R;
import com.example.psicotop.banco.Post;
import com.example.psicotop.modal.Emocao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResumoFragment extends Fragment implements ResumoContract.View{

    private ResumoContract.UserActionsListener presenter;
    private EmocoesAdapter mListAdapter;

    public ResumoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new ResumoPresenter(this, new Post());

    }

    @Override
    public void onResume() {
        presenter.carregarEmocoes();
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_resumo, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.emocoes_list);
        TextView tvVerDetalhes = view.findViewById(R.id.tvVerDetalhes);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mListAdapter = new EmocoesAdapter(new ArrayList<Emocao>());
        recyclerView.setAdapter(mListAdapter);

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
            mListAdapter.replaceData(emocoes);
        }
    }

    @Override
    public void mostrarMensagem(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser){
            presenter.carregarEmocoes();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    private class EmocoesAdapter extends RecyclerView.Adapter<EmocoesAdapter.ViewHolder>{

        private List<Emocao> mEmocoes;

        EmocoesAdapter(List<Emocao> emocaos){
            mEmocoes = emocaos;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View noteView = inflater.inflate(R.layout.emocao_item, parent, false);

            return new ViewHolder(noteView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            if (mEmocoes != null && mEmocoes.size() > 0) {

                Emocao e = mEmocoes.get(position);

                if (e.getTipoEmocao().equals("Normal")) {
                    holder.diaDaSemana.setText("N");
                    holder.layout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.custom_normal_item));
                } else if (e.getTipoEmocao().equals("Feliz")) {
                    holder.diaDaSemana.setText("F");
                    holder.layout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.custom_feliz_item));
                } else {
                    holder.diaDaSemana.setText("T");
                    holder.layout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.custom_triste_item));
                }
            }

        }

        void replaceData(List<Emocao> emocoes){
            Collections.reverse(emocoes);
            mEmocoes = emocoes;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {

            if (mEmocoes.size() <= 6){
                return mEmocoes.size();
            }else {
                return 6;
            }
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private TextView diaDaSemana;
            private ConstraintLayout layout;

            ViewHolder(View itemView) {
                super(itemView);
                diaDaSemana = itemView.findViewById(R.id.tvDiaDaSemana);
                layout = itemView.findViewById(R.id.layoutFundo);
            }
        }
    }

}
