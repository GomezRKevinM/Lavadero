package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

public class Cubiculo {
    private String idCubiculo;
    private String descripcion;
    private String nombre;
    private String estado;
    private List<Producto> productos = new ArrayList<>();
    private List<Servicio> servicios = new ArrayList<>();

    public Cubiculo(String idCubiculo, String nombre) {
        this.idCubiculo = idCubiculo;
        this.nombre = nombre;
    }

    public String getIdCubiculo() {
        return idCubiculo;
    }

    public void setIdCubiculo(String idCubiculo) {
        this.idCubiculo = idCubiculo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

}
