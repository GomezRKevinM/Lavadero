package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import java.util.List;

public class Categoria {
    int codigo;
    String nombre;
    String icono;
    List<Producto> productos;
    List<Servicio> servicios;

    public Categoria(String nombre, String icono, int codigo) {
        this.nombre = nombre;
        this.icono = icono;
        this.codigo = codigo;
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

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public List<Producto> getProductos() {
        return productos;
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
