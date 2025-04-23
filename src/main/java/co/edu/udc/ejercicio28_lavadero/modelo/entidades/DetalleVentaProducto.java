package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

public class DetalleVentaProducto extends DetalleDocumento{
    public DetalleVentaProducto(int cantidad, double precioUnitario, double descuento, double iva) {
        super(cantidad, precioUnitario, descuento, iva);
    }
}
