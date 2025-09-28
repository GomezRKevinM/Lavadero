package co.edu.udc.ejercicio28_lavadero.models;

import java.util.ArrayList;
import java.util.List;

import co.edu.udc.ejercicio28_lavadero.Color;

public class Catalogo {

    private String idCatalogo;
    private String nombre;
    private List<Categoria> categorias = new ArrayList<>();
    private List<Producto> productos = new ArrayList<>();
    private List<Servicio> servicios = new ArrayList<>();
    private List<Empleado> empleadosQueHanMostrado = new ArrayList<>();

    public Catalogo(String idCatalogo, String nombre) {
        this.idCatalogo = idCatalogo;
        this.nombre = nombre;
    }

    public Catalogo(){}
    public List<Categoria> getCategorias() {
        return categorias;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void addCategoria(Categoria categoria) {
        this.categorias.add(categoria);
    }

    public void addProducto(Producto producto) {
        this.productos.add(producto);
    }

    public void addServicio(Servicio servicio) {
        this.servicios.add(servicio);
    }

    public List<Empleado> getEmpleadosQueHanMostrado() {
        return empleadosQueHanMostrado;
    }

    public void addEmpleadoQueHaMostrado(Empleado empleado) {
        this.empleadosQueHanMostrado.add(empleado);
    }

    public void mostrarProductosAlerta(){
        System.out.println(Color.PURPLE_BOLD+"Productos con stock bajo\n");
        for (Producto producto : productos) {
            if(producto.getStock()<=producto.getAlerta()){
                System.out.println(Color.BLANCO_BOLD+"Nombre: "+producto.getNombreProducto()+Color.ROJO_BOLD+"Solo quedan: "+producto.getStock());
            }
        }
    }

    public String getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(String idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
