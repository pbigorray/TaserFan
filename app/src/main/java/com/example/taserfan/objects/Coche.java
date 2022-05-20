package com.example.taserfan.objects;

import java.util.Date;

public class Coche extends Vehiculo{
    private int numPlazas;
    private int numPuertas;

    public Coche(String matricula, String marca, String color, String estado,Tipo tipo) {
        super(matricula, marca, color, estado,tipo);
    }

//    public Coche(String matricula, String marca, String descripcion, String bateria, String tipoCarnet, String color, String estado, Date fechaAdq, double precioHora, int numPlazas, int numPuertas) {
//        super(matricula, marca, descripcion, bateria, tipoCarnet, color, estado, fechaAdq, precioHora);
//        this.numPlazas = numPlazas;
//        this.numPuertas = numPuertas;
//    }
}
