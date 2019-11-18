package com.example.psicotop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.psicotop.modal.Emocao;
import com.example.psicotop.mvp.menu.resumo.ResumoFragment;

import java.util.Collections;
import java.util.List;

public class DetalhesResumoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_resumo);
    }

    private class EmocoesAdapter extends RecyclerView.Adapter<DetalhesResumoActivity.EmocoesAdapter.ViewHolder>{

        private List<Emocao> mEmocoes;

        public EmocoesAdapter(List<Emocao> emocaos){
            mEmocoes = emocaos;
        }

        @Override
        public DetalhesResumoActivity.EmocoesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View noteView = inflater.inflate(R.layout.emocao_item, parent, false);

            return new ViewHolder(noteView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Emocao e = mEmocoes.get(position);

            if (e.getTipoEmocao().equals("Normal")){
                holder.diaDaSemana.setText("N");
                holder.layout.setBackgroundColor(Color.parseColor("#E5E5E5"));
            }else if (e.getTipoEmocao().equals("Feliz")){
                holder.diaDaSemana.setText("F");
                holder.layout.setBackgroundColor(Color.parseColor("#E4F6DE"));
            }else {
                holder.diaDaSemana.setText("T");
                holder.layout.setBackgroundColor(Color.parseColor("#DEEAF6"));
            }
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
            private ConstraintLayout layout;

            public ViewHolder(View itemView) {
                super(itemView);
                diaDaSemana = itemView.findViewById(R.id.tvDiaDaSemana);
                layout = itemView.findViewById(R.id.layoutFundo);
            }
        }
    }
}
