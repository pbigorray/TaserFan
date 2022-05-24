package com.example.taserfan.objects;

import java.util.Date;

public class Patinete extends Vehiculo {
    private int numRuedas;
    private int tamanyo;

    public Patinete(String matricula, String marca, String descripcion, int bateria, String tipoCarnet, String color, String estado, String fechaAdq, double precioHora, Tipo tipo, int numRuedas, int tamanyo) {
        super(matricula, marca, descripcion, bateria, tipoCarnet, color, estado, fechaAdq, precioHora, tipo);
        this.numRuedas = numRuedas;
        this.tamanyo = tamanyo;
    }

    public Patinete(String matricula, String marca, String color, String estado, Tipo tipo) {
        super(matricula, marca, color, estado,tipo);
    }

    public int getNumRuedas() {
        return numRuedas;
    }

    public void setNumRuedas(int numRuedas) {
        this.numRuedas = numRuedas;
    }

    public int getTamanyo() {
        return tamanyo;
    }

    public void setTamanyo(int tamanyo) {
        this.tamanyo = tamanyo;
    }
//    public Patinete(String matricula, String marca, String descripcion, String bateria, String tipoCarnet, String color, String estado, Date fechaAdq, double precioHora, int numRuedas, int tamanyo) {
//        super(matricula, marca, descripcion, bateria, tipoCarnet, color, estado, fechaAdq, precioHora);
//        this.numRuedas = numRuedas;
//        this.tamanyo = tamanyo;
//    }
}
