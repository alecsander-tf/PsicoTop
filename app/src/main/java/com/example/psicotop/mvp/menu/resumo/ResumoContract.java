package com.example.psicotop.mvp.menu.resumo;

import android.content.Intent;

import com.example.psicotop.modal.Emocao;

import java.util.List;

public interface ResumoContract {

    interface View {
        void abrirActivity(Intent intent);
        void exibirEmocoes(List<Emocao> emocoes);
        void mostrarMensagem(String msg);
    }

    interface UserActionsListener {

        void carregarOutraActivity(Intent intent);
        void carregarDetalhesEmocoes();
        void carregarEmocoes();

    }

}
