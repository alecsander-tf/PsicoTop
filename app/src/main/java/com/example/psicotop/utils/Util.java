package com.example.psicotop.utils;

public class Util {

    public boolean verificaCampoNulo (String campo){
        if (campo.equals("") || campo.isEmpty() || campo.trim().equals("") ){
            return true;
        }

        return false;
    }

}
