package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import java.util.Date;
import java.util.List;

public class Servicio {
    String codigo;
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
    List<Empleado> funcionarios;
    String imagen;
    boolean disponibilidad;
    Categoria categoria;

    public Servicio(String codigo, String nombre, String descripcion, double precioDeVenta, String imagen, boolean disponibilidad, Categoria categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioDeVenta = precioDeVenta;
        this.imagen = imagen;
        this.disponibilidad = disponibilidad;
        this.categoria = categoria;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioDeVenta() {
        return precioDeVenta;
    }

    public void setPrecioDeVenta(double precioDeVenta) {
        this.precioDeVenta = precioDeVenta;
    }

    public double getPorcentajeIva() {
        return porcentajeIva;
    }

    public void setPorcentajeIva(double porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    public double getValorCorrespondienteIVA() {
        return valorCorrespondienteIVA;
    }

    public void setValorCorrespondienteIVA(double valorCorrespondienteIVA) {
        this.valorCorrespondienteIVA = valorCorrespondienteIVA;
    }

    public double getPrecioTotalConIva() {
        return precioTotalConIva;
    }

    public void setPrecioTotalConIva(double precioTotalConIva) {
        this.precioTotalConIva = precioTotalConIva;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Empleado> getFuncionario() {
        return funcionarios;
    }

    public void setFuncionario(List<Empleado> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void addFuncionario(Empleado empleado) {
        this.funcionarios.add(empleado);
    }

    public void removeFuncionario(Empleado empleado) {
        this.funcionarios.remove(empleado);
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    
    
}
