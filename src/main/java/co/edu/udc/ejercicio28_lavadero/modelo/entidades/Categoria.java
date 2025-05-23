package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import java.util.List;


public class Categoria {
    String codigo;
    String nombre;
    String icono;

    public Categoria() {
        nombre="Lubricantes";
        icono="lubricantes.png";
        codigo="L001";
    }

    public Categoria(String nombre, String icono, String codigo) {
        this.nombre = nombre;
        this.icono = icono;
        this.codigo = codigo;
    }

    public Categoria(String nombre, String icono) {
        this.nombre = nombre;
        this.icono = icono;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }
}
