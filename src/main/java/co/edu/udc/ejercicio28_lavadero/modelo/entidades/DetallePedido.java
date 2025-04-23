package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

public class DetallePedido extends DetalleDocumento{
    int cantidad_recibida;
    boolean verificado;
    Producto producto;

    public DetallePedido(Producto producto,int cantidad, double precioUnitario, double descuento, double iva, int cantidad_recibida, boolean verificado) {
        super(cantidad, precioUnitario, descuento, iva);
        this.producto = producto;
        this.cantidad_recibida = cantidad_recibida;
        this.verificado = verificado;
    }

    public int getCantidadRecibida() {
        return cantidad_recibida;
    }

    public void setCantidad_recibida(int cantidad_recibida) {
        this.cantidad_recibida = cantidad_recibida;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public Producto geProducto(){
        return producto;
    }
}
