package com.example.psicotop.fragment;

import com.example.psicotop.modal.Emocao;

import java.util.List;

public interface ResumoCoresContract {

    interface View{
        void exibirEmocoes(List<Emocao> emocoes);
        void mostrarMensagem(String msg);
    }

    interface UserInteraction{
        void carregarEmocoes();
    }
}
