package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import java.util.List;

public class Producto {
    int codigo;
    String nombre;
    String marca;
    double precio;
    String descripcion;
    String imagen;
    int tiempoGarantia;
    double precioUnitario;
    double descuento;
    double iva;
    int unidades;
    int stock;
    double ivanSinDescuento;
    double ivaConDescuento;
    boolean disponibilidad;
    Categoria categoria;
    List<Proveedor> proveedores;

    public Producto(int codigo, String nombre, double precio, String descripcion, double precioUnitario, Categoria categoria, String marca, String imagen) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.categoria = categoria;
        this.marca = marca;
        this.imagen = imagen;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getTiempoGarantia() {
        return tiempoGarantia;
    }

    public void setTiempoGarantia(int tiempoGarantia) {
        this.tiempoGarantia = tiempoGarantia;
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

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getIvanSinDescuento() {
        return ivanSinDescuento;
    }

    public void setIvanSinDescuento(double ivanSinDescuento) {
        this.ivanSinDescuento = ivanSinDescuento;
    }

    public double getIvaConDescuento() {
        return ivaConDescuento;
    }

    public void setIvaConDescuento(double ivaConDescuento) {
        this.ivaConDescuento = ivaConDescuento;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


}
