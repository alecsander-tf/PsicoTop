package com.example.psicotop.mvp.login;

import com.example.psicotop.modal.Usuario;

public interface LoginContract {

    interface View{
        void carregarActivity(Class<?> arg);
        void carregarMensagem(String msg);
    }

    interface UserActionsListener{

        void login(Usuario usuario);

        void abrirActivity(Class<?> arg);
    }

}
