package com.example.psicotop.mvp.menu.diario;

import com.example.psicotop.banco.IPost;
import com.example.psicotop.modal.Emocao;
import com.example.psicotop.modal.Usuario;

public class DiarioPresenter implements DiarioContract.UserActionsListener {

    private DiarioContract.View view;
    private IPost post;

    public DiarioPresenter(DiarioContract.View view, IPost post) {
        this.view = view;
        this.post = post;
    }

    @Override
    public Usuario getCurrentUser() {
        return post.getCurrentUserLogged();
    }

    @Override
    public void registrarEmocao(Emocao emocao) {
        view.setCarregando(true);
        post.registrarEmocao(emocao, new IPost.IPostCallback() {
            @Override
            public void onLoaded(String msg) {
                view.setCarregando(false);
                if (!msg.equals("")){
                    view.carregarMensagem(msg);
                }
            }

            @Override
            public void onError(String msg) {
                view.setCarregando(false);
                view.carregarMensagem(msg);
            }
        });
    }
}
