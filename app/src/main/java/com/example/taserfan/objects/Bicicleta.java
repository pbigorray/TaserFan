package com.example.taserfan.objects;

import java.util.Date;

public class Bicicleta extends Vehiculo{
    private String tipo;

    public Bicicleta(String matricula, String marca, String color, String estado) {
        super(matricula, marca, color, estado);
    }

//    public Bicicleta(String matricula, String marca, String descripcion, String bateria, String tipoCarnet, String color, String estado, Date fechaAdq, double precioHora, String tipo) {
//        super(matricula, marca, descripcion, bateria, tipoCarnet, color, estado, fechaAdq, precioHora);
//        this.tipo = tipo;
//    }
}
