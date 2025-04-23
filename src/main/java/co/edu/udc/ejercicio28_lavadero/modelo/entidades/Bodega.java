package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

public class Bodega {
    private String codigo;
    private String ubicacion;
    private String nombre;
    private List<Producto> productos = new ArrayList<>();

    public Bodega(String codigo, String ubicacion, String nombre) {
        this.codigo = codigo;
        this.ubicacion = ubicacion;
        this.nombre = nombre;
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

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void addProductos(Producto producto) {
        this.productos.add(producto);
    }

    public Producto getProducto(String codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    public void eliminarProducto(String codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                productos.remove(producto);
            }
        }
    }   

    public void buscarProducto(String search, String por){
        switch (por) {
            case "Nombre":
            System.out.println("Productos con nombre: "+search);
                for(Producto producto: productos){
                    if(producto.getNombreProducto().equalsIgnoreCase(search)){
                        System.out.println(producto.getNombreProducto()+" Valor: $"+producto.getPrecio());
                    }else{
                        continue;
                    }
                }
                break;
            case "Categoria":
            System.out.println("Productos con Categoria: "+search);
            for(Producto producto: productos){
                if(producto.getCategoria().equalsIgnoreCase(search)){
                    System.out.println(producto.getNombreProducto()+" Valor: $"+producto.getPrecio());
                }else{
                    continue;
                }
            }
                break;
            case "Precio":
                System.out.println("Productos con Categoria: "+search);
                for(Producto producto: productos){
                    if(producto.getPrecio()==Integer.valueOf(search)){
                        System.out.println(producto.getNombreProducto()+" Valor: $"+producto.getPrecio());
                    }else{
                        continue;
                    }
                }
                break;
            default:
                break;
        }
    }

    public void ProductosAlerta(){
        for(Producto producto: productos){
            if(producto.getStock() <= producto.getAlerta()){
                System.out.println(producto.getNombreProducto()+" Stock: "+producto.getStock());
            }
        }
    }

}
