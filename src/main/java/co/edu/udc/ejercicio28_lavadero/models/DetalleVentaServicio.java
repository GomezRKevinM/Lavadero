package co.edu.udc.ejercicio28_lavadero.models;

import co.edu.udc.ejercicio28_lavadero.exceptions.DocumentoException;

import java.sql.Date;

public class DetalleVentaServicio extends DetalleDocumento{
    Date fechaInicio;
    Date fechaFinal;
    Empleado empleadoEncargado;
    double valorCorrespondienteIVA;
    double precioTotalConIva;
    double precioTotal;
    Vehiculo vehiculo;

    public DetalleVentaServicio() throws DocumentoException {
        super(1, 200000, 0, 0.19);
        fechaInicio = new Date(2020,04,10);
        fechaFinal = new Date(2020,04,10);
        empleadoEncargado = new Empleado();
        vehiculo = new Vehiculo();
        valorCorrespondienteIVA = cantidad * precioUnitario * (1 - iva);
        precioTotalConIva = cantidad * precioUnitario * (1 + iva);
        precioTotal = cantidad * precioUnitario;
        if(fechaInicio.after(fechaFinal)){
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la fecha final");
        }
        if(fechaInicio.before(new Date(2020,04,10))){
            throw new IllegalArgumentException("La fecha de inicio debe ser posterior a 10/04/2020");
        }

    }


    public DetalleVentaServicio(int cantidad, double precioUnitario, double descuento, double iva, Date fechaInicio, Date fechaFinal, Empleado empleadoEncargado, Vehiculo vehiculo) {
        super(cantidad, precioUnitario, descuento, iva);
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.empleadoEncargado = empleadoEncargado;
        this.vehiculo = vehiculo;
        this.valorCorrespondienteIVA = cantidad * precioUnitario * (1 - iva);
        this.precioTotalConIva = cantidad * precioUnitario * (1 + iva);
        this.precioTotal = cantidad * precioUnitario;
        if(fechaInicio.after(fechaFinal)){
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la fecha final");
        }
        if(fechaInicio.before(new Date(2020,04,10))){
            throw new IllegalArgumentException("La fecha de inicio debe ser posterior a 10/04/2020");
        }
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

    @Override
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
