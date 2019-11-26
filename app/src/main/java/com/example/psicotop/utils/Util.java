package com.example.psicotop.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public boolean verificaCampoNulo (String campo){
        if (campo.equals("") || campo.isEmpty() || campo.trim().equals("") ){
            return true;
        }

        return false;
    }

    public String getDataAtual(){

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return formatter.format(date);

    }

}
