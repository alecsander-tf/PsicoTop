package com.example.psicotop.banco;

import com.example.psicotop.modal.Emocao;
import com.example.psicotop.modal.Paciente;
import com.example.psicotop.modal.Psicologo;
import com.example.psicotop.modal.Usuario;

import java.util.List;

public interface IPost {

    interface IPostListCallback {
        void onLoaded(List<?> list);
        void onError(String msg);
    }

    interface IPostCallback {
        void onLoaded(String msg);
        void onError(String msg);
    }

    Usuario getCurrentUserLogged();
    void carregarMetas(IPostListCallback iPostListCallback);
    void carregarEmocoes(IPostListCallback iPostListCallback);
    void registrarEmocao(Emocao e, IPostCallback callback);
    void alterarPaciente(Paciente p, IPostCallback callback);
    void carregarPacientes(Psicologo psicologo, IPostListCallback callback);
    void associarEmocoesPaciente(Paciente p, IPostListCallback callback);
    boolean psicologoExiste(String email);
    void verificarUsuario(IPostListCallback callback);
    void loginUsuario(String email, String senha, IPostCallback callback);
    void registrarUsuario(Usuario u, IPostCallback callback);

}
