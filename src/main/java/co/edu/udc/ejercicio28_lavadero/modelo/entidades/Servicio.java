package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import java.util.Date;

public class Servicio {
    int codigo;
    String nombre;
    String descripcion;
    double precioDeVenta;
    double porcentajeIva;
    double valorCorrespondienteIVA;
    double precioTotalConIva;
    double descuento;
    double precioTotal;
    Vehiculo vehiculo;
    Date fechaInicio;
    Date fechaFin;
    String funcionario;
    String imagen;
    boolean disponibilidad;
    Categoria categoria;

    public Servicio(int codigo, String nombre, String descripcion, double precioDeVenta, String imagen, boolean disponibilidad, Categoria categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioDeVenta = precioDeVenta;
        this.imagen = imagen;
        this.disponibilidad = disponibilidad;
        this.categoria = categoria;
    }
    
}
