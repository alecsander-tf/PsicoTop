package com.example.psicotop.utils;

import com.example.psicotop.modal.Paciente;
import com.example.psicotop.modal.Usuario;

public class SingletonUserLogged {

    static private SingletonUserLogged singletonUserLogged;
    static private Paciente pacienteSelecionado;
    static private Usuario currentUserLogged;

    private SingletonUserLogged(){

    }

    public static void getInstance(){
        if (singletonUserLogged == null){
            singletonUserLogged = new SingletonUserLogged();
        }
    }

    public static void removeInstance(){
        singletonUserLogged = null;
        pacienteSelecionado = null;
        currentUserLogged = null;
    }

    public static Paciente getPacienteSelecionado() {
        return pacienteSelecionado;
    }

    public static void setPacienteSelecionado(Paciente pacienteSelecionado) {
        SingletonUserLogged.pacienteSelecionado = pacienteSelecionado;
    }

    public static Usuario getCurrentUserLogged() {
        return currentUserLogged;
    }

    public static void setCurrentUserLogged(Usuario currentUserLogged) {
        SingletonUserLogged.currentUserLogged = currentUserLogged;
    }
}
