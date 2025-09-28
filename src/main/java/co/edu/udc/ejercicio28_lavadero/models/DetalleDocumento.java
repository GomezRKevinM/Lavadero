package co.edu.udc.ejercicio28_lavadero.models;

public abstract class DetalleDocumento {
    int cantidad;
    double precioUnitario;
    double descuento;
    double iva;
    String descripcion;
    
    public DetalleDocumento(int cantidad, double precioUnitario, double descuento, double iva) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
        this.iva = iva;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioTotal() {
        return cantidad * precioUnitario * (1 - descuento) * (1 + iva);
    }

    public double getPrecioSinIva() {
        return cantidad * precioUnitario * (1 - descuento);
    }

    public double getPrecioSinIvaSinDescuento() {
        return cantidad * precioUnitario;
    }
    
}
