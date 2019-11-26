package com.example.psicotop.mvp.login;

import com.example.psicotop.modal.Usuario;

public interface LoginContract {

    interface View{
        void carregarActivity(Class<?> arg);
        void carregarMensagem(String msg);
        void setCarregando(boolean carregando);
    }

    interface UserActionsListener{

        void login(String email, String senha);
        void abrirActivity(Class<?> arg);
    }

}
