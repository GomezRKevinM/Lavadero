package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

public class Vehiculo {
    private String marca;
    private String modelo;
    private String placa;
    private String tipo;
    private String color;
    private String planta;
    private int identificador;

    public Vehiculo(String marca, String modelo, String placa, String color, String planta, int identificador) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.color = color;
        this.planta = planta;
        this.identificador = identificador;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlanta() {
        return planta;
    }

    public void setPlanta(String planta) {
        this.planta = planta;
    }

    public int getIdentificador() {
        return identificador;
    }

}
