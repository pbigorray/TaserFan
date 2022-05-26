package com.example.taserfan.objects;

public enum Carnet {
    NO("NO"),AM("AM"),A("A"),B("B"),C("C"),D("D"),E("E"),Z("Z");
    private String str;
    Carnet(String str){
        this.str=str;
    }

    public String getStr() {
        return str;
    }
}
