package com.example.psicotop.modal;

public class Usuario {

    private String nome;
    private String idade;


    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public Usuario(String nome, String idade) {
        this.nome = nome;
    }
}
