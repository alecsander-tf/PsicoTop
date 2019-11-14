package com.example.psicotop.mvp.menu.resumo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.psicotop.R;
import com.example.psicotop.banco.Post;
import com.example.psicotop.modal.Emocao;
import com.example.psicotop.modal.EmocaoEnum;

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

        List<Emocao> aux = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

/*        aux.add(new Emocao("1", EmocaoEnum.TRISTE, "chateado"));
        aux.add(new Emocao("2", EmocaoEnum.TRISTE, "BEM feliz"));
        aux.add(new Emocao("3", EmocaoEnum.NORMAL, "de boa"));*/
        mListAdapter = new EmocoesAdapter(aux);
        recyclerView.setAdapter(mListAdapter);

        return view;
    }

    @Override
    public void abrirActivity(Intent intent) {

    }

    @Override
    public void exibirEmocoes(List<Emocao> emocoes) {

        /*if (emocoes != null){

            mostrarMensagem(emocoes.get(0).getComentario());
            //mListAdapter.replaceData(emocoes);
        }else {

            mostrarMensagem("nulo");
        }*/
    }

    @Override
    public void mostrarMensagem(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    private class EmocoesAdapter extends RecyclerView.Adapter<EmocoesAdapter.ViewHolder>{

        private List<Emocao> mEmocoes;

        public EmocoesAdapter(List<Emocao> emocaos){
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
            Emocao e = mEmocoes.get(position);

            if (e.getTipoEmocao().equals(EmocaoEnum.NORMAL)){
                holder.diaDaSemana.setText("N");
                holder.layout.setBackgroundColor(Color.parseColor("#E5E5E5"));
            }else if (e.getTipoEmocao().equals(EmocaoEnum.FELIZ)){
                holder.diaDaSemana.setText("F");
                holder.layout.setBackgroundColor(Color.parseColor("#E4F6DE"));
            }else {
                holder.diaDaSemana.setText("T");
                holder.layout.setBackgroundColor(Color.parseColor("#DEEAF6"));
            }


        }

        public void replaceData(List<Emocao> emocoes){
            mEmocoes = emocoes;
            //notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return mEmocoes.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView diaDaSemana;
            private ConstraintLayout layout;

            public ViewHolder(View itemView) {
                super(itemView);
                diaDaSemana = itemView.findViewById(R.id.tvDiaDaSemana);
                layout = itemView.findViewById(R.id.layoutFundo);
            }
        }

    }

}
