package com.example.psicotop.mvp.registro;

import com.example.psicotop.banco.IPost;
import com.example.psicotop.modal.Usuario;
import com.example.psicotop.mvp.login.LoginActivity;

public class RegistroPresenter implements RegistroContract.UserActionsListener{

    private RegistroContract.View view;
    private IPost post;

    public RegistroPresenter(RegistroContract.View view, IPost post) {
        this.view = view;
        this.post = post;
    }

    @Override
    public void abrirActivity(Class<?> arg) {

    }

    @Override
    public void registrarUsuario(Usuario u) {
        post.registrarUsuario(u, new IPost.IPostCallback() {
            @Override
            public void onLoaded(String msg) {
                view.carregarActivity(LoginActivity.class);
                if (!msg.equals("")){
                    view.carregarMensagem(msg);
                }
            }

            @Override
            public void onError(String msg) {
                view.carregarMensagem(msg);
            }
        });
    }
}
