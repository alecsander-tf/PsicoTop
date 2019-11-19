package com.example.psicotop;

import com.example.psicotop.banco.IPost;
import com.example.psicotop.banco.Post;
import com.example.psicotop.modal.Emocao;

import java.util.ArrayList;
import java.util.List;

class DetalhesResumoPresenter implements DetalhesResumoContract.UserActionsListener {

    DetalhesResumoContract.View view;
    Post post;

    public DetalhesResumoPresenter(DetalhesResumoContract.View view, Post post) {
        this.post = post;
        this.view = view;
    }

    @Override
    public void carregarDetalhesEmocoes() {
        post.carregarEmocoes(new IPost.IPostListCallback() {
            @Override
            public void onLoaded(List<?> list) {

                List<Emocao> listaDetalhesEmocoes = new ArrayList<>();

                for (Object o : list){
                    if (o instanceof Emocao){
                        listaDetalhesEmocoes.add((Emocao) o);
                    }
                }

                view.exibirDetalhesEmocoes(listaDetalhesEmocoes);
            }

            @Override
            public void onError(String msg) {
                view.carregarMensagem(msg);
            }
        });
    }
}
