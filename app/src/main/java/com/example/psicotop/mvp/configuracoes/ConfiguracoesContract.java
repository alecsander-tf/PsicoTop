package com.example.psicotop.mvp.configuracoes;

import com.example.psicotop.modal.Emocao;
import com.example.psicotop.modal.Paciente;
import com.example.psicotop.modal.Usuario;

public interface ConfiguracoesContract {

    interface View{
        void carregarActivity(Class<?> arg);
        void carregarMensagem(String msg);
        void setCarregando(boolean carregando);
    }

    interface UserActionsListener{

        boolean psicologoExiste(String psicologo);
        Usuario getCurrentUserLogged();
        void alterarDados(Paciente paciente);
    }

}
