package com.example.psicotop.modal;

public class Psicologo extends Usuario {

    private String crm;

    public Psicologo(String crm) {
        this.crm = crm;
    }

    public Psicologo() {
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
}
