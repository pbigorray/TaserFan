package com.example.taserfan.objects;

import java.util.Date;

public class Patinete extends Vehiculo {
    private int numRuedas;
    private int tamanyo;

    public Patinete(String matricula, String marca, String color, String estado,Tipo tipo) {
        super(matricula, marca, color, estado,tipo);
    }

//    public Patinete(String matricula, String marca, String descripcion, String bateria, String tipoCarnet, String color, String estado, Date fechaAdq, double precioHora, int numRuedas, int tamanyo) {
//        super(matricula, marca, descripcion, bateria, tipoCarnet, color, estado, fechaAdq, precioHora);
//        this.numRuedas = numRuedas;
//        this.tamanyo = tamanyo;
//    }
}
