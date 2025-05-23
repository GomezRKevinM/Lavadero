package co.edu.udc.ejercicio28_lavadero.modelo.entidades;


import java.sql.Date;

public class NotaCorrecion extends Documento {
    Date fechaMaximaConfirmacion;
    Date fechaMaximaEntrega;
    String observaciones;
    String codigoPedido;
    Empresa empresa;
    Provedor provedor;
    DetalleNotaCorrecion detalle = new DetalleNotaCorrecion();


    public NotaCorrecion(String Codigo, Date FechaEmision, Date fechaMaximaConfirmacion, String codigoPedido, String observaciones) {
        super(Codigo, FechaEmision);
        this.fechaMaximaConfirmacion = fechaMaximaConfirmacion;
        this.codigoPedido = codigoPedido;
        this.observaciones = observaciones;
    }

    public Date getFechaMaximaConfirmacion() {
        return fechaMaximaConfirmacion;
    }

    public void setFechaMaximaConfirmacion(Date fechaMaximaConfirmacion) {
        this.fechaMaximaConfirmacion = fechaMaximaConfirmacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

}
