package com.example.psicotop.modal;

public class Paciente extends Usuario{

    private Psicologo psicologo;

    public Paciente(){

    }

    public Psicologo getPsicologo() {
        return psicologo;
    }

    public void setPsicologo(Psicologo psicologo) {
        this.psicologo = psicologo;
    }
}
