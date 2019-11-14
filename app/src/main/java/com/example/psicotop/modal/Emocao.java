package com.example.psicotop.modal;

public class Emocao {

    private String id;
    private EmocaoEnum tipoEmocao;
    private String comentario;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Emocao() {}

    public Emocao(String id, EmocaoEnum tipoEmocao, String comentario) {
        this.id = id;
        this.tipoEmocao = tipoEmocao;
        this.comentario = comentario;
    }

    public EmocaoEnum getTipoEmocao() {
        return tipoEmocao;
    }

    public void setTipoEmocao(EmocaoEnum tipoEmocao) {
        this.tipoEmocao = tipoEmocao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
