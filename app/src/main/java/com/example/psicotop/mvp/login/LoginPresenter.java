package com.example.psicotop.mvp.login;

import com.example.psicotop.banco.IPost;
import com.example.psicotop.menu.MenuActivity;
import com.example.psicotop.utils.SingletonUserLogged;

public class LoginPresenter implements LoginContract.UserActionsListener{

    private LoginContract.View loginView;
    private IPost post;

    public LoginPresenter(IPost p, LoginContract.View view){
        post = p;
        loginView = view;
        SingletonUserLogged.removeInstance();
    }

    @Override
    public void login(String email, String senha) {

        loginView.setCarregando(true);
        post.loginUsuario(email, senha, new IPost.IPostCallback() {
            @Override
            public void onLoaded(String msg) {
                if (msg != null){
                    loginView.carregarMensagem(msg);
                }
                loginView.setCarregando(false);
                loginView.carregarActivity(MenuActivity.class);
                SingletonUserLogged.getInstance();
            }

            @Override
            public void onError(String msg) {

                loginView.setCarregando(false);
                loginView.carregarMensagem(msg);
            }
        });
    }

    @Override
    public void abrirActivity(Class<?> arg) {
        loginView.carregarActivity(arg);
    }

}
