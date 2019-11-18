package com.example.psicotop.modal;

import java.util.Objects;

public class Emocao {

    private String id;
    private String tipoEmocao;
    private String comentario;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Emocao() {}

    public Emocao(String id, String tipoEmocao, String comentario) {
        this.id = id;
        this.tipoEmocao = tipoEmocao;
        this.comentario = comentario;
    }

    public String getTipoEmocao() {
        return tipoEmocao;
    }

    public void setTipoEmocao(String tipoEmocao) {
        this.tipoEmocao = tipoEmocao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emocao emocao = (Emocao) o;
        return id.equals(emocao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
