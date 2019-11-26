package com.example.psicotop.mvp.menu.diario;

import com.example.psicotop.modal.Emocao;
import com.example.psicotop.modal.Usuario;

public interface DiarioContract {

    interface View{
        void carregarMensagem(String msg);
        void setCarregando(boolean carregando);

    }

    interface UserActionsListener{

        Usuario getCurrentUser();
        void registrarEmocao(Emocao emocao);

    }

}
