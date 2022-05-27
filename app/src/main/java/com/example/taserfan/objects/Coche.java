package com.example.taserfan.objects;

import java.util.Date;

public class Coche extends Vehiculo{
    private int numPlazas;
    private int numPuertas;

    public Coche(String matricula, String marca, String color, String estado,Tipo tipo) {
        super(matricula, marca, color, estado,tipo);
    }

    public Coche(String matricula, String marca, String descripcion, int bateria, String tipoCarnet, String color, String estado, String fechaAdq, double precioHora, Tipo tipo, int numPlazas, int numPuertas) {
        super(matricula, marca, descripcion, bateria, tipoCarnet, color, estado, fechaAdq, precioHora, tipo);
        this.numPlazas = numPlazas;
        this.numPuertas = numPuertas;
    }

    @Override
    public String toString() {
        return "Coche" +super.toString()+
                "\nnumPlazas=" + numPlazas +
                "\nnumPuertas=" + numPuertas;
    }

    public int getNumPlazas() {
        return numPlazas;
    }

    public void setNumPlazas(int numPlazas) {
        this.numPlazas = numPlazas;
    }

    public int getNumPuertas() {
        return numPuertas;
    }

    public void setNumPuertas(int numPuertas) {
        this.numPuertas = numPuertas;
    }
//    public Coche(String matricula, String marca, String descripcion, String bateria, String tipoCarnet, String color, String estado, Date fechaAdq, double precioHora, int numPlazas, int numPuertas) {
//        super(matricula, marca, descripcion, bateria, tipoCarnet, color, estado, fechaAdq, precioHora);
//        this.numPlazas = numPlazas;
//        this.numPuertas = numPuertas;
//    }
}
