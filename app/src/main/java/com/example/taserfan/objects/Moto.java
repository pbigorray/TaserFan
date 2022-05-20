package com.example.taserfan.objects;

import java.util.Date;

public class Moto extends Vehiculo{
    private int velocidadMax;
    private int cilindrada;

    public Moto(String matricula, String marca, String color, String estado,Tipo tipo) {
        super(matricula, marca, color, estado,tipo);
    }

//    public Moto(String matricula, String marca, String descripcion, String bateria, String tipoCarnet, String color, String estado, Date fechaAdq, double precioHora, int velocidadMax, int cilindrada) {
//        super(matricula, marca, descripcion, bateria, tipoCarnet, color, estado, fechaAdq, precioHora);
//        this.velocidadMax = velocidadMax;
//        this.cilindrada = cilindrada;
//    }
}
