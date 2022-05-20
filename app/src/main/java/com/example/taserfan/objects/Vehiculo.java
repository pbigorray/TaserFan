package com.example.taserfan.objects;


import java.io.Serializable;
import java.util.Date;

public class Vehiculo implements Serializable {
    private String matricula,marca,descripcion,bateria,tipoCarnet,color,estado;
    private Date fechaAdq;
    private double precioHora;
    private Tipo tipo;


    public Vehiculo(String matricula, String marca, String color, String estado,Tipo tipo) {
        this.matricula = matricula;
        this.marca = marca;
        this.color = color;
        this.estado = estado;
        this.tipo=tipo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getBateria() {
        return bateria;
    }

    public String getTipoCarnet() {
        return tipoCarnet;
    }

    public String getColor() {
        return color;
    }

    public String getEstado() {
        return estado;
    }

    public Date getFechaAdq() {
        return fechaAdq;
    }

    public double getPrecioHora() {
        return precioHora;
    }


}
