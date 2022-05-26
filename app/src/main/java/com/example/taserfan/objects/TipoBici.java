package com.example.taserfan.objects;

public enum TipoBici {
    MOTANA("montaña"),PASEO("paseo"),HIBRIDA("hibrida");
    private String str;
    TipoBici(String str){
        this.str=str;
    }

    public String getStr() {
        return str;
    }
}
