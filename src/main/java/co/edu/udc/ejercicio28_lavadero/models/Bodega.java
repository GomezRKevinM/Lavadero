package co.edu.udc.ejercicio28_lavadero.models;

import java.util.ArrayList;
import java.util.List;

public class Bodega {

    private String codigo;
    private String ubicacion;
    private String nombre;
    public ArrayList<Producto> productos = new ArrayList<>();

    public Bodega(String codigo, String ubicacion, String nombre) {
        this.codigo = codigo;
        this.ubicacion = ubicacion;
        this.nombre = nombre;
    }

    public Bodega(){
        codigo = "0001";
        ubicacion = "Local";
        nombre = "Bodega 1";
        productos = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }



}