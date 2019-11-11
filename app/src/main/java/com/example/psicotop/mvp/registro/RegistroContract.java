package com.example.psicotop.mvp.registro;

import com.example.psicotop.banco.IPost;
import com.example.psicotop.modal.Usuario;

public interface RegistroContract {

    interface View{
        void carregarActivity(Class<?> arg);
        void carregarMensagem(String msg);
        void setCarregando(boolean carregando);
    }

    interface UserActionsListener{
        boolean psicologoExiste(String msg);
        boolean verificarUsuarios(String msg);
        void abrirActivity(Class<?> arg);
        void registrarUsuario(Usuario u);
    }

}
