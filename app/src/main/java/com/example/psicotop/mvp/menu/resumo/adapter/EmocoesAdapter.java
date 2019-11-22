package com.example.psicotop.mvp.menu.resumo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psicotop.R;
import com.example.psicotop.modal.Emocao;

import java.util.Collections;
import java.util.List;

public class EmocoesAdapter extends RecyclerView.Adapter<EmocoesAdapter.ViewHolder>{

    private List<Emocao> mEmocoes;
    private Context mContext;

    public EmocoesAdapter(List<Emocao> emocaos, Context context){
        mEmocoes = emocaos;
        mContext = context;
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

            int i = verificaPosicao(mEmocoes, e);

            if (e.getTipoEmocao().equals("Normal")) {



                if (i == 9){
                    holder.layout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.custom_normal_item));
                }else if (i == 0){
                    holder.layout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.custom_normal_first_item));
                }else if (i == 1){
                    holder.layout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.custom_normal_middle_item));
                }else {
                    holder.layout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.custom_normal_last_item));
                }

                holder.diaDaSemana.setText("N");

            } else if (e.getTipoEmocao().equals("Feliz")) {
                holder.diaDaSemana.setText("F");
                if (i == 9){
                    holder.layout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.custom_feliz_item));
                }else if (i == 0){
                    holder.layout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.custom_feliz_first_item));
                }else if (i == 1){
                    holder.layout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.custom_feliz_middle_item));
                }else {
                    holder.layout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.custom_feliz_last_item));
                }
            } else {
                holder.diaDaSemana.setText("T");
                if (i == 9){
                    holder.layout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.custom_triste_item));
                }else if (i == 0){
                    holder.layout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.custom_triste_first_item));
                }else if (i == 1){
                    holder.layout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.custom_triste_middle_item));
                }else {
                    holder.layout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.custom_triste_last_item));
                }
            }
        }

    }

    /**
     * retorna 0 se é o primeiro, 1 se está no meio, 2 se é o ultimo, 9 se é o único
     * */
    private int verificaPosicao(List list, Object object){

        if (list.lastIndexOf(object) >= 4){
            return 2;
        }

        if (list.get(0).equals(object)){
            if (list.size() > 1){
                return 0;
            }
            return 9;
        }else if (list.lastIndexOf(object) == list.size() - 1){
            return 2;
        }
        return 1;
    }

    public void replaceData(List<Emocao> emocoes){
        Collections.reverse(emocoes);
        mEmocoes = emocoes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        if (mEmocoes.size() <= 5){
            return mEmocoes.size();
        }else {
            return 5;
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
