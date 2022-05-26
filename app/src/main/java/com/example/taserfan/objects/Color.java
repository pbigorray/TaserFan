package com.example.taserfan.objects;

public enum Color {
    VERDE("verde"),NEGRO("negro"),BLANCO("blanco"),ROJO("rojo"),AZUL("azul"),AMARILLO("amarillo");
    private String str;
    Color(String str){
        this.str=str;
    }

    public String getStr() {
        return str;
    }
}

