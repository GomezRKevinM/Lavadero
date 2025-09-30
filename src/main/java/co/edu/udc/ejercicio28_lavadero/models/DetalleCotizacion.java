package co.edu.udc.ejercicio28_lavadero.models;

import co.edu.udc.ejercicio28_lavadero.exceptions.DocumentoException;

import java.sql.Date;

public class DetalleCotizacion extends DetalleDocumento {
    int id;
    int tiempoGarantia;
    Producto producto;
    Cotizacion cotizacion;

    public DetalleCotizacion(Producto producto,int cantidad, double precioUnitario, double descuento, double iva, int tiempoGarantia) {
        super(cantidad, precioUnitario, descuento, iva);
        this.tiempoGarantia = tiempoGarantia;
        this.producto = producto;

    }

    public DetalleCotizacion() throws DocumentoException {
        super(10,10000,10,10);
        this.producto = new Producto("Trapo limpiador","generica",5,"1",10000,5000,10,10,1);
        this.id = 0;
        this.cotizacion = new Cotizacion("0",new Date(2020,04,10),new Date(2024,12,04),new Empleado());
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTiempoGarantia() {
        return tiempoGarantia;
    }

    public void setTiempoGarantia(int tiempoGarantia) {
        this.tiempoGarantia = tiempoGarantia;
    }
    
    
}
