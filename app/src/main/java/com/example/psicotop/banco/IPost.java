package com.example.psicotop.banco;

import com.example.psicotop.modal.Usuario;

import java.util.List;

public interface IPost {

    interface IPostListCallback {
        void onLoaded(List<?> list);
        void onError(String msg);
    }

    interface IPostCallback {
        void onLoaded(String msg);
        void onError(String msg);
    }

    boolean psicologoExiste(String email);
    void verificarUsuario(IPostListCallback callback);
    void loginUsuario(String email, String senha, IPostCallback callback);
    void registrarUsuario(Usuario u, IPostCallback callback);

}
