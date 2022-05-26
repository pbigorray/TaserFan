package com.example.taserfan.objects;

public enum Estado {
    PREPARADO("preparado"), TALLER("taller"), BAJA("baja"), ALQUILADO("alquilado"), RESERVADO("reservado");
    private String str;
    Estado(String str){
        this.str=str;
    }

    public String getStr() {
        return str;
    }
}
