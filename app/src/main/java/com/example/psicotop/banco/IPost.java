package com.example.psicotop.banco;

import com.example.psicotop.modal.Usuario;

public interface IPost {

    interface IPostCallback {
        void onLoaded(String msg);
        void onError(String msg);
    }

    void loginUsuario(Usuario u, IPostCallback callback);
    void registrarUsuario(Usuario u, IPostCallback callback);

}
