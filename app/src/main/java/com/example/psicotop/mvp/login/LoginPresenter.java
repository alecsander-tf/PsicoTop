package com.example.psicotop.mvp.login;

import android.content.Intent;

import com.example.psicotop.banco.IPost;
import com.example.psicotop.banco.Post;
import com.example.psicotop.modal.Usuario;
import com.example.psicotop.mvp.menu.MenuActivity;

public class LoginPresenter implements LoginContract.UserActionsListener{

    private LoginContract.View loginView;
    private IPost post;

    public LoginPresenter(IPost p, LoginContract.View view){
        post = p;
        loginView = view;
    }

    @Override
    public void login(Usuario usuario) {

        post.loginUsuario(usuario, new IPost.IPostCallback() {
            @Override
            public void onLoaded(String msg) {
                loginView.carregarActivity(MenuActivity.class);
                if (!msg.equals("")){
                    loginView.carregarMensagem(msg);
                }
            }

            @Override
            public void onError(String msg) {
                loginView.carregarMensagem(msg);
            }
        });
    }

    @Override
    public void abrirActivity(Class<?> arg) {
        loginView.carregarActivity(arg);
    }

}
