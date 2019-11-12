package com.example.psicotop.mvp.menu.resumo;

import android.content.Intent;

import com.example.psicotop.banco.IPost;
import com.example.psicotop.modal.Emocao;

import java.util.ArrayList;
import java.util.List;

public class ResumoPresenter implements ResumoContract.UserActionsListener {

    private ResumoContract.View view;
    private IPost post;

    public ResumoPresenter(ResumoContract.View view, IPost post) {
        this.view = view;
        this.post = post;
    }


    @Override
    public void carregarOutraActivity(Intent intent) {

    }

    @Override
    public void carregarDetalhesEmocoes() {

    }

    @Override
    public void carregarEmocoes() {

        post.carregarEmocoes(new IPost.IPostListCallback() {
            @Override
            public void onLoaded(List<?> list) {
                List<Emocao> listaEmocoes = new ArrayList<>();

                for (Object object : list){
                    if (object instanceof Emocao){
                        listaEmocoes.add((Emocao) object);
                    }
                }

                view.exibirEmocoes(listaEmocoes);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}