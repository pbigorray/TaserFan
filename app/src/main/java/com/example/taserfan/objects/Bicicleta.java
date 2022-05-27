package com.example.taserfan.objects;

public class Bicicleta extends Vehiculo{
    private String tipoBici;

    public Bicicleta(String matricula, String marca, String color, String estado,Tipo tipo) {
        super(matricula, marca, color, estado,tipo);
    }

    public Bicicleta(String matricula, String marca, String descripcion, int bateria, String tipoCarnet, String color, String estado, String fechaAdq, double precioHora, Tipo tipo, String tipoBici) {
        super(matricula, marca, descripcion, bateria, tipoCarnet, color, estado, fechaAdq, precioHora, tipo);
        this.tipoBici = tipoBici;
    }

    @Override
    public String toString() {
        return "Bicicleta" +super.toString()+
                "\ntipoBici='" + tipoBici ;
    }

    public String getTipoBici() {
        return tipoBici;
    }

    public void setTipoBici(String tipoBici) {
        this.tipoBici = tipoBici;
    }
//    public Bicicleta(String matricula, String marca, String descripcion, String bateria, String tipoCarnet, String color, String estado, Date fechaAdq, double precioHora, String tipo) {
//        super(matricula, marca, descripcion, bateria, tipoCarnet, color, estado, fechaAdq, precioHora);
//        this.tipo = tipo;
//    }
}
