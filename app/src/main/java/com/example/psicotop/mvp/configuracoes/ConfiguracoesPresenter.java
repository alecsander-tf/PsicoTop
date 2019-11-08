package com.example.psicotop.mvp.configuracoes;

import com.example.psicotop.banco.IPost;
import com.example.psicotop.modal.Paciente;
import com.example.psicotop.modal.Usuario;

public class ConfiguracoesPresenter implements ConfiguracoesContract.UserActionsListener {

    private IPost post;
    private ConfiguracoesContract.View view;

    public ConfiguracoesPresenter(ConfiguracoesContract.View view, IPost post) {
        this.post = post;
        this.view = view;
    }

    public Usuario getCurrentUserLogged(){
        return post.getCurrentUserLogged();
    }

    @Override
    public void alterarDados(Paciente paciente) {
        post.alterarPaciente(paciente);
    }
}
