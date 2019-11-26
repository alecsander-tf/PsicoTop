package com.example.psicotop.mvp.menu.detalhesResumo;

import com.example.psicotop.modal.Emocao;

import java.util.List;

public interface DetalhesResumoContract {

    interface View{
        void exibirDetalhesEmocoes(List<Emocao> emocoes);
        void carregarMensagem(String msg);
    }

    interface UserActionsListener{
        void carregarDetalhesEmocoes();
    }

}
