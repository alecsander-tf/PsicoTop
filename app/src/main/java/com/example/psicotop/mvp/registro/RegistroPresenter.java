package com.example.psicotop.mvp.registro;

import android.util.Log;

import com.example.psicotop.banco.IPost;
import com.example.psicotop.modal.Usuario;
import com.example.psicotop.mvp.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

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
    public boolean psicologoExiste(String msg) {
        return post.psicologoExiste(msg);
    }

    @Override
    public boolean verificarUsuarios(final String user){

        post.verificarUsuario(new IPost.IPostListCallback() {
            @Override
            public void onLoaded(List<?> list) {
                for (Object object : list){
                    if (object instanceof Usuario){
                        Log.d("Lista", ((Usuario) object).getNome());
                    }
                }
            }

            @Override
            public void onError(String msg) {
                if (!msg.equals("")){
                    view.carregarMensagem(msg);
                }
            }
        });

        return true;
    }

    @Override
    public void registrarUsuario(Usuario u) {

        view.setCarregando(true);
        post.registrarUsuario(u, new IPost.IPostCallback() {
            @Override
            public void onLoaded(String msg) {
                view.setCarregando(false);
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
