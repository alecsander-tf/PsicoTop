package com.example.psicotop.fragment;

import com.example.psicotop.banco.IPost;
import com.example.psicotop.modal.Emocao;

import java.util.ArrayList;
import java.util.List;

public class ResumoSemanalPresenter implements ResumoSemanalContract.UserInteraction {

    private ResumoSemanalContract.View view;
    private IPost post;

    public ResumoSemanalPresenter(ResumoSemanalContract.View view, IPost post) {
        this.view = view;
        this.post = post;
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
                view.mostrarMensagem(msg);
            }
        });
    }
}
