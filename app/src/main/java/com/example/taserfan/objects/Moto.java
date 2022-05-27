package com.example.taserfan.objects;

import java.util.Date;

public class Moto extends Vehiculo{
    private int velocidadMax;
    private int cilindrada;

    public Moto(String matricula, String marca, String color, String estado,Tipo tipo) {
        super(matricula, marca, color, estado,tipo);
    }

    public Moto(String matricula, String marca, String descripcion, int bateria, String tipoCarnet, String color, String estado, String fechaAdq, double precioHora, Tipo tipo, int velocidadMax, int cilindrada) {
        super(matricula, marca, descripcion, bateria, tipoCarnet, color, estado, fechaAdq, precioHora, tipo);
        this.velocidadMax = velocidadMax;
        this.cilindrada = cilindrada;
    }


    @Override
    public String toString() {
        return "Moto" +super.toString()+
                "\nvelocidadMax=" + velocidadMax +
                "\ncilindrada=" + cilindrada ;
    }

    public int getVelocidadMax() {
        return velocidadMax;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setVelocidadMax(int velocidadMax) {
        this.velocidadMax = velocidadMax;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }
}
