package com.example.psicotop.modal;

public class Paciente extends Usuario{

    private String emailPsicologo;

    public String getEmailPsicologo() {
        return emailPsicologo;
    }

    public void setEmailPsicologo(String emailPsicologo) {
        this.emailPsicologo = emailPsicologo;
    }

    public Paciente(String emailPsicologo){
        this.emailPsicologo = emailPsicologo;
    }
}
