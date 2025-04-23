package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import java.sql.Date;

public class DetalleVentaServicio extends DetalleDocumento{
    Date fechaInicio;
    Date fechaFinal;
    Empleado empleadoEncargado;

    public DetalleVentaServicio(int cantidad, double precioUnitario, double descuento, double iva, Date fechaInicio, Date fechaFinal, Empleado empleadoEncargado) {
        super(cantidad, precioUnitario, descuento, iva);
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.empleadoEncargado = empleadoEncargado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Empleado getEmpleadoEncargado() {
        return empleadoEncargado;
    }

    public void setEmpleadoEncargado(Empleado empleadoEncargado) {
        this.empleadoEncargado = empleadoEncargado;
    }
    
    
    
}
