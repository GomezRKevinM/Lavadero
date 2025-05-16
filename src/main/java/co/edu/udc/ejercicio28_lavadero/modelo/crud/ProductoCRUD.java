package co.edu.udc.ejercicio28_lavadero.modelo.crud;


import co.edu.udc.ejercicio28_lavadero.Color;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.ManejoArchivos;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Producto;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class ProductoCRUD {

    public static  List<Producto> productos = new ArrayList<>();

    public static void main(String[] args) throws Exception {
            /*Producto producto = new Producto("Bolsa","1001",1000,100,"generic",10,10,"P01");
            agregar(producto);*/

            Scanner input = new Scanner(System.in);
            System.out.print("Escriba el codigo del producto: ");
            String codigo = input.nextLine();

            Producto encontrado = buscar(codigo);
            editar(encontrado);
            System.out.println("Producto encontrado: " + encontrado.getNombreProducto());
    }


    public static void agregar(Producto p) throws Exception {
        try {
            cargarProductos();
            for (Producto producto : productos) {
                if(producto.getCodigo().equals(p.getCodigo())){
                    throw new Exception("Producto ya existe: "+p.getNombreProducto());
                }else{
                    continue;
                }
            }
            productos.add(p);

            ManejoArchivos.escribirArchivo("DB/productos.json", new Gson().toJson(productos));
        }catch (Exception e){
            throw new RuntimeException("Error inesperado al agregar el producto: "+e.getMessage());
        }
    }

    public static Producto buscar(String codigo) throws FileNotFoundException {
        try {
            cargarProductos();

            for (Producto producto : productos) {
                if (producto.getCodigo().equals(codigo)) {
                    System.out.println(Color.CYAN_BOLD+"Producto encontrado: ");
                    System.out.println("Nombre: " + producto.getNombreProducto());

                    return producto;
                }
            }
            throw new FileNotFoundException("Producto no encontrado 🔍");
        }catch (FileNotFoundException e){
            System.out.println("Error inesperado: "+ e.getMessage());
        }
        throw new RuntimeException("Error inesperado al buscar el producto: ");
    }


    public static void editar(Producto p) throws FileNotFoundException {
        try{
            Scanner input = new Scanner(System.in);
            Boolean encontrado = false;
            cargarProductos();
            int index = 0;
            for(Producto producto : productos){
                if(producto.getCodigo().equalsIgnoreCase(p.getCodigo())){
                    producto = p;

                    System.out.println("1) Codigo: " + producto.getCodigo());
                    System.out.println("2) Stock: " + producto.getStock());
                    System.out.println("3) Precio: " + producto.getPrecio());
                    System.out.println("4) Precio de compra: "+producto.getPrecioDeCompra());
                    System.out.println("5) Marca: " + producto.getMarca());
                    System.out.println("6) Categoria: " + producto.getCategoria());
                    System.out.println("7) Alerta: "+producto.getAlerta()+" stock minimo");
                    System.out.println("8) Codigo del proveedor: "+producto.getCodigoDelProveedor());
                    System.out.print(Color.AMARILLO_BLINK+"Disponibilidad: "+Color.CYAN_BOLD);
                    System.out.print(producto.getDisponibilidad()?"Disponible":"No disponible");
                    System.out.println("\n9) Nombre: " + producto.getNombreProducto());
                    System.out.println("10) Salir");

                    System.out.println("Escribe el numero del que deseas cambiar");
                    String answer = input.nextLine();
                    Gson gson = new Gson();
                    String json;
                    switch (answer){
                        case "1":
                            System.out.println("Codigo Actual: "+producto.getCodigo());
                            System.out.print("Escriba el nuevo codigo: ");
                            String codigo = input.nextLine();
                            producto.setCodigo(codigo);
                            productos.set(index,producto);
                            json = gson.toJson(productos);
                            ManejoArchivos.escribirArchivo("DB/Productos.json",json);
                            break;
                        case "2":
                            System.out.println("Stock Actual: "+producto.getStock());
                            System.out.print("Escriba el nuevo Stock: ");
                            int stock = Integer.parseInt(input.nextLine());
                            producto.setStock(stock);
                            productos.set(index,producto);
                            json = gson.toJson(productos);
                            ManejoArchivos.escribirArchivo("DB/Productos.json",json);
                            break;
                        case "3":
                            System.out.println("Precio Actual: "+convertirDivisa(producto.getPrecio()));
                            System.out.print("Escriba el nuevo Precio: $");
                            double precio = Double.parseDouble(input.nextLine());
                            producto.setPrecio(precio);
                            productos.set(index,producto);
                            json = gson.toJson(productos);
                            ManejoArchivos.escribirArchivo("DB/Productos.json",json);
                            break;
                        case "4":
                            System.out.println("Precio de compra actual: "+convertirDivisa(producto.getPrecioDeCompra()));
                            System.out.print("Escriba el nuevo Precio de compra: $");
                            double precioCompra = Double.parseDouble(input.nextLine());
                            producto.setPrecioDeCompra(precioCompra);
                            productos.set(index,producto);
                            json = gson.toJson(productos);
                            ManejoArchivos.escribirArchivo("DB/Productos.json",json);
                            break;
                        case "5":
                            System.out.println("Marca Actual: "+producto.getMarca());
                            System.out.print("Escriba la nueva Marca: ");
                            String marca = input.nextLine();
                            producto.setMarca(marca);
                            productos.set(index,producto);
                            json = gson.toJson(productos);
                            ManejoArchivos.escribirArchivo("DB/Productos.json",json);
                            break;
                        case "6":
                            System.out.println("Categoria Actual: "+producto.getCategoria());
                            System.out.print("Escriba la nueva Categoria: ");
                            String categoria = input.nextLine();
                            producto.setCategoria(categoria);
                            productos.set(index,producto);
                            json = gson.toJson(productos);
                            ManejoArchivos.escribirArchivo("DB/Productos.json",json);
                            break;
                        case "7":
                            System.out.println("Alerta Actual: "+producto.getAlerta()+" stock minimo");
                            System.out.print("Escriba la nueva Alerta: ");
                            int alerta = Integer.parseInt(input.nextLine());
                            producto.setAlerta(alerta);
                            productos.set(index,producto);
                            json = gson.toJson(productos);
                            ManejoArchivos.escribirArchivo("DB/Productos.json",json);
                            break;
                        case "8":
                            System.out.println("Codigo Actual del proveedor: "+producto.getCodigoDelProveedor());
                            System.out.print("Escriba el nuevo codigo del proveedor: ");
                            String codigoProveedor = input.nextLine();
                            producto.setCodigoDelProveedor(codigoProveedor);
                            productos.set(index,producto);
                            json = gson.toJson(productos);
                            ManejoArchivos.escribirArchivo("DB/Productos.json",json);
                            break;
                        case "9":
                            System.out.println("Nombre Actual: "+producto.getNombreProducto());
                            System.out.print("Escriba el nuevo nombre: ");
                            String nombre = input.nextLine();
                            producto.setNombreProducto(nombre);
                            productos.set(index,producto);
                            json = gson.toJson(productos);
                            ManejoArchivos.escribirArchivo("DB/Productos.json",json);
                            break;
                        case "10":
                            System.out.println("Saliendo");
                            System.exit(0);
                            break;
                    }

                    input.close();

                    encontrado = true;
                    break;
                }
                index++;
            }
            if(!encontrado){
                throw new FileNotFoundException("Producto no encontrado: "+p.getNombreProducto());
            }
        }catch (FileNotFoundException e){
            throw new RuntimeException("Error al cargar los productos: "+e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    public  void eliminar(String codigo) {
        try{
            cargarProductos();
            for(Producto producto : productos){
                if(producto.getCodigo().equalsIgnoreCase(codigo)){
                    productos.remove(producto);
                    Gson gson = new Gson();
                    String json = gson.toJson(productos);
                    ManejoArchivos.escribirArchivo("DB/Productos.json",json);
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Producto> listarTodo() throws FileNotFoundException {
        try {
            cargarProductos();
            return productos;
        }catch (FileNotFoundException e){
            throw new RuntimeException("Error al listar los productos: "+e.getMessage());
        }
    }

    public Integer contar() throws FileNotFoundException {
       try {
           cargarProductos();
           Integer contador = productos.size();
           return contador;
       }catch (FileNotFoundException e){
           throw new RuntimeException("Error al contar los productos: "+e.getMessage());
       }
    }

    public static void cargarProductos() throws FileNotFoundException {
        Gson gson = new Gson();
        Type productListType = new TypeToken<ArrayList<Producto>>() {
        }.getType();
        FileReader reader = new FileReader("DB/productos.json");
        productos = gson.fromJson(reader, productListType);
    }

    public static String convertirDivisa(double monto){
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.forLanguageTag("es-CO"));
        dfs.setCurrencySymbol(Currency.getInstance("COP").getSymbol());
        DecimalFormat df = new DecimalFormat("¤ #,##0", dfs);
        return df.format(monto)+" "+dfs.getCurrency().getCurrencyCode();
    }
}