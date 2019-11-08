package com.example.psicotop.mvp.configuracoes;

import com.example.psicotop.modal.Paciente;
import com.example.psicotop.modal.Usuario;

public interface ConfiguracoesContract {

    interface View{
        void carregarActivity(Class<?> arg);
    }

    interface UserActionsListener{
        Usuario getCurrentUserLogged();
        void alterarDados(Paciente paciente);
    }

}
