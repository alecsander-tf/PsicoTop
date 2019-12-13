package com.example.psicotop.detalhesResumo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.psicotop.R;
import com.example.psicotop.banco.Post;
import com.example.psicotop.modal.Emocao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DetalhesResumoActivity extends AppCompatActivity implements DetalhesResumoContract.View {

    private DetalhesResumoContract.UserActionsListener presenter;
    DetalhesAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_resumo);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        bind();
        presenter.carregarDetalhesEmocoes();
    }

    private void bind(){

        mListAdapter = new DetalhesAdapter(new ArrayList<Emocao>());

        RecyclerView recyclerView = findViewById(R.id.emocoesDetalhes_list);
        recyclerView.setAdapter(mListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, R.drawable.custom_line));

        presenter = new DetalhesResumoPresenter(this, new Post());
    }

    @Override
    public void exibirDetalhesEmocoes(List<Emocao> emocoes) {
        mListAdapter.replaceData(emocoes);
    }

    @Override
    public void carregarMensagem(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    private class DetalhesAdapter extends RecyclerView.Adapter<DetalhesResumoActivity.DetalhesAdapter.ViewHolder>{

        private List<Emocao> mEmocoes;

        public DetalhesAdapter(List<Emocao> emocaos){
            mEmocoes = emocaos;
        }

        @Override
        public DetalhesResumoActivity.DetalhesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View noteView = inflater.inflate(R.layout.emocao_detalhe_item, parent, false);

            return new ViewHolder(noteView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Emocao e = mEmocoes.get(position);

            holder.descricao.setText(e.getComentario());
            holder.diaDaSemana.setText(e.getDataRegistro());
            holder.tipoEmocao.setText(e.getTipoEmocao());

        }

        public void replaceData(List<Emocao> emocoes){
            Collections.reverse(emocoes);
            mEmocoes = emocoes;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {

            return mEmocoes.size();

        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView diaDaSemana;
            private TextView descricao;
            private TextView tipoEmocao;

            public ViewHolder(View itemView) {
                super(itemView);
                tipoEmocao = itemView.findViewById(R.id.tvTipoEmocao);
                diaDaSemana = itemView.findViewById(R.id.tvDataEmocaoDetalhe);
                descricao = itemView.findViewById(R.id.tvDescricaoEmocaoDetalhe);
            }
        }
    }
}
