package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

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

    public void addProductos(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }
        if (getProducto(producto.getCodigo()) != null) {
            throw new IllegalStateException("El producto ya existe en la bodega");
        }
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
            System.out.println("Productos con nombre: " + search);
            productos.stream()
                    .filter(p -> p.getNombreProducto().equalsIgnoreCase(search))
                    .forEach(p -> System.out.println(p.getNombreProducto() + " Valor: $" + p.getPrecio()));
            break;
        case "Categoria":
            System.out.println("Productos con Categoria: " + search);
            productos.stream()
                    .filter(p -> p.getCategoria()==Integer.parseInt(search))
                    .forEach(p -> System.out.println(p.getNombreProducto() + " Valor: $" + p.getPrecio()));
            break;
        case "Precio":
            System.out.println("Productos con Precio: " + search);
            double searchPrice = Double.parseDouble(search);
            productos.stream()
                    .filter(p -> p.getPrecio() == searchPrice)
                    .forEach(p -> System.out.println(p.getNombreProducto() + " Valor: $" + p.getPrecio()));
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