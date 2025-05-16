package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

public class Producto {

    private String codigo;
    private String nombreProducto;
    private String Categoria;
    private double precio;
    private int stock;
    private String marca;
    private double precioDeCompra;
    private Boolean disponibilidad;
    private int alerta;
    private String codigoDelProveedor;

    public Producto(String nombreProducto, String codigo, double precio, double precioDeCompra, String marca, int stock, int alerta, String codigoDelProveedor) {
        this.nombreProducto = nombreProducto;
        this.codigo = codigo;
        this.precio = precio;
        this.precioDeCompra = precioDeCompra;
        this.marca = marca;
        this.alerta = alerta;
        this.codigoDelProveedor = codigoDelProveedor;
        this.stock = stock;
        disponibilidad = stock > 0;
    }

    public double getPrecioDeCompra() {
        return precioDeCompra;
    }

    public void setPrecioDeCompra(double precioDeCompra) {
        this.precioDeCompra = precioDeCompra;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCategoria(){
        return Categoria;
    }

    public void setCategoria(String nombre){
        Categoria = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getAlerta() {
        return alerta;
    }

    public void setAlerta(int alerta) {
        this.alerta = alerta;
    }

    public String getCodigoDelProveedor() {
        return codigoDelProveedor;
    }

    public void setCodigoDelProveedor(String codigoDelProveedor) {
        this.codigoDelProveedor = codigoDelProveedor;
    }
}
