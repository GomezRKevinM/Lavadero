package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

public class DetalleCotizacion extends DetalleDocumento {
    int tiempoGarantia;
    Producto producto;
    public DetalleCotizacion(Producto producto,int cantidad, double precioUnitario, double descuento, double iva, int tiempoGarantia) {
        super(cantidad, precioUnitario, descuento, iva);
        this.tiempoGarantia = tiempoGarantia;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getTiempoGarantia() {
        return tiempoGarantia;
    }

    public void setTiempoGarantia(int tiempoGarantia) {
        this.tiempoGarantia = tiempoGarantia;
    }
    
    
}
