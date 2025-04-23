package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import java.util.List;


public class Categoria {
    String codigo;
    String nombre;
    String icono;
    List<Producto> productos;
    List<Servicio> servicios;

    public Categoria(String nombre, String icono, String codigo) {
        this.nombre = nombre;
        this.icono = icono;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Producto getProducto(String codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    public void addProductos(Producto producto) {
        this.productos.add(producto);
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void addServicios(Servicio servicio) {
        this.servicios.add(servicio);
    }
    
    
}
