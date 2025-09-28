package co.edu.udc.ejercicio28_lavadero.models;

public class DetallePedido extends DetalleDocumento{
    int cantidad_recibida;
    boolean verificado;
    Producto producto;
    int codigo_pedio;
    int id;

    public DetallePedido(int pedido,Producto producto,int cantidad, double precioUnitario, double descuento, double iva, int cantidad_recibida, boolean verificado) {
        super(cantidad, precioUnitario, descuento, iva);
        this.producto = producto;
        this.cantidad_recibida = cantidad_recibida;
        this.verificado = verificado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad_recibida() {
        return cantidad_recibida;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCodigo_pedio() {
        return codigo_pedio;
    }

    public void setCodigo_pedio(int codigo_pedio) {
        this.codigo_pedio = codigo_pedio;
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
