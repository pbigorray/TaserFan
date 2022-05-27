package com.example.taserfan.objects;


import java.io.Serializable;
import java.util.Date;

public class Vehiculo implements Serializable {
    private String matricula,marca,descripcion,tipoCarnet,color,estado;
    private String  fechaAdq;
    private int bateria;
    private double precioHora;

    @Override
    public String toString() {
        return
                "\nmatricula='" + matricula + '\'' +
                "\nmarca='" + marca + '\'' +
                "\ndescripcion='" + descripcion + '\'' +
                "\ntipoCarnet='" + tipoCarnet + '\'' +
                "\ncolor='" + color + '\'' +
                "\nestado='" + estado + '\'' +
                "\nfechaAdq='" + fechaAdq + '\'' +
                "\nbateria=" + bateria +
                "\nprecioHora=" + precioHora +
                "\ntipo=" + tipo;
    }

    private Tipo tipo;

    public Vehiculo(String matricula, String marca, String descripcion, int bateria, String tipoCarnet, String color, String estado, String fechaAdq, double precioHora, Tipo tipo) {
        this.matricula = matricula;
        this.marca = marca;
        this.descripcion = descripcion;
        this.bateria = bateria;
        this.tipoCarnet = tipoCarnet;
        this.color = color;
        this.estado = estado;
        this.fechaAdq = fechaAdq;
        this.precioHora = precioHora;
        this.tipo = tipo;
    }

    public Vehiculo(String matricula, String marca, String color, String estado, Tipo tipo) {
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

    public int getBateria() {
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

    public String getFechaAdq() {
        return fechaAdq;
    }

    public double getPrecioHora() {
        return precioHora;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipoCarnet(String tipoCarnet) {
        this.tipoCarnet = tipoCarnet;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFechaAdq(String fechaAdq) {
        this.fechaAdq = fechaAdq;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    public void setPrecioHora(double precioHora) {
        this.precioHora = precioHora;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
