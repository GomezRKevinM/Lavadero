package co.edu.udc.ejercicio28_lavadero.modelo.crud;

import co.edu.udc.ejercicio28_lavadero.Color;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Bodega;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.ManejoArchivos;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Producto;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Proveedor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BodegaCRUD {

    public static List<Bodega> bodegas = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            Bodega encontrada = buscar("0001");
            editar(encontrada);
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public static void agregar(Bodega b) throws IOException, InterruptedException {
        try {
            CargarBodegas();
            for (Bodega bodega : bodegas) {
                if (bodega.getCodigo().equals(b.getCodigo())) {
                    throw new FileNotFoundException("Esta Bodega ya existe ✖️");
                }
            }
            bodegas.add(b);
            GuardarDatos();
        }catch (FileNotFoundException e){
            throw new RuntimeException("Error al agregar la bodega: "+e.getMessage());
        }
    }

    public static Bodega buscar(String codigo) throws FileNotFoundException {
        try {
            Bodega encontrada = new Bodega();
            CargarBodegas();
            Boolean exist = false;
            for (Bodega bodega : bodegas) {
                if (bodega.getCodigo().equals(codigo)) {
                    encontrada = bodega;
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                throw new FileNotFoundException("Bodega no encontrada 🔍");
            }
            return encontrada;
        }catch (FileNotFoundException e){
            throw new FileNotFoundException(e.getMessage());
        }
    }

    public static void editar(Bodega b) throws IOException, InterruptedException {
        try {
            CargarBodegas();
            Boolean encontrado = false;
            int index = 0;
            for (Bodega bodega : bodegas) {
                if (bodega.getCodigo().equalsIgnoreCase(b.getCodigo())) {
                    bodega = b;
                    encontrado = true;

                    Scanner input = new Scanner(System.in);
                    System.out.println("Editar Bodega: "+ Color.CYAN_BOLD+bodega.getNombre() + Color.RESET);
                    System.out.println("1) Nombre: " + Color.CYAN_BOLD+bodega.getNombre()+ Color.RESET);
                    System.out.println("2) Codigo: " + Color.CYAN_BOLD+bodega.getCodigo()+ Color.RESET);
                    System.out.println("3) Ubicación: " + Color.CYAN_BOLD+bodega.getUbicacion()+ Color.RESET);
                    System.out.println("4) Productos: ");
                    List<Producto> productos = bodega.getProductos();
                    if(productos.size() > 0){
                        for(Producto producto : bodega.getProductos()){
                            System.out.println(" - "+producto.getNombreProducto());
                        }
                    }else{
                        System.out.println(Color.ROJO_BLINK+"Bodega Vacia, no registra productos en esta bodega"+Color.RESET);
                    }


                    System.out.println("Escribe el numero de la opción que deseas cambiar");
                    String answer = input.nextLine();

                    switch (answer){
                        case "1":
                            System.out.print("Escriba el nuevo nombre: ");
                            String nombre = input.nextLine();
                            bodega.setNombre(nombre);
                            bodegas.set(index,bodega);
                            GuardarDatos();
                            break;
                        case "2":
                            System.out.print("Escriba el nuevo codigo: ");
                            String codigo = input.nextLine();
                            bodega.setCodigo(codigo);
                            bodegas.set(index,bodega);
                            GuardarDatos();
                            break;
                        case "3":
                            System.out.println("Escriba la nueva ubicación: ");
                            String ubicacion = input.nextLine();
                            bodega.setUbicacion(ubicacion);
                            bodegas.set(index,bodega);
                            GuardarDatos();
                            break;
                        case "4":
                            System.out.println("1) Agregar producto a bodega");
                            if(productos.size() > 0){
                                System.out.println("2) Eliminar producto de bodega");
                            }
                            System.out.println("3) atrás");

                            answer = input.nextLine();
                            Gson gson = new Gson();
                            String json;

                                switch (answer){
                                    case "1":
                                        List<Producto> productosLocales = ProductoCRUD.listarTodo();

                                        for(Producto producto : productosLocales){
                                            System.out.println(producto.getNombreProducto()+" Codigo: "+producto.getCodigo());
                                        }

                                        System.out.println("Escribe el codigo del producto a agregar: ");
                                        Producto producto = ProductoCRUD.buscar(input.nextLine());
                                        bodega.addProductos( producto );
                                        bodegas.set(index,bodega);
                                        System.out.println(Color.VERDE_BLINK+"Producto "+producto.getNombreProducto()+" agregado a bodega"+Color.RESET);
                                        GuardarDatos();
                                        break;
                                    case "2":
                                        for(Producto producto1 : bodega.getProductos()){
                                            System.out.println(producto1.getNombreProducto()+" Codigo: "+producto1.getCodigo());
                                        }
                                        System.out.println("Codigo del producto a eliminar: ");
                                        bodega.eliminarProducto(input.nextLine());
                                        bodegas.set(index,bodega);
                                        json = gson.toJson(bodegas);
                                        ManejoArchivos.escribirArchivo("DB/Bodegas.json",json);
                                        break;
                                    case "3":
                                        editar(bodega);
                                        break;
                                }
                            break;
                        default:
                            throw new Exception("Opción no valida");
                    }
                    input.close();
                    break;
                }
                index++;
            }
            if(!encontrado){
                throw new FileNotFoundException("Esta Bodega no existe ✖️");
            }
        }catch (FileNotFoundException e){
            throw new RuntimeException("Error al editar la bodega: "+e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void eliminar(String codigo) throws IOException, InterruptedException {
        try{
            CargarBodegas();
            for(Bodega bodega : bodegas){
                if(bodega.getCodigo().equalsIgnoreCase(codigo)){
                    bodegas.remove(bodega);
                    System.out.println("Bodega eliminada: "+bodega.getNombre());
                    GuardarDatos();
                    break;
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static List<Bodega> listarTodo() throws FileNotFoundException {
        try {
            CargarBodegas();
            return bodegas;
        }catch (FileNotFoundException e){
            throw new RuntimeException("Error al listar las bodegas: "+e.getMessage());
        }
    }

    public static Integer contar() throws FileNotFoundException {
        try {
            CargarBodegas();
            Integer contador = bodegas.size();
            return contador;
        }catch (FileNotFoundException e){
            throw new RuntimeException("Error al contar las bodegas: "+e.getMessage());
        }
    }

    public static void CargarBodegas() throws FileNotFoundException {
        Gson gson = new Gson();
        Type bodegasTypeList = new TypeToken<ArrayList<Bodega>>(){}.getType();
        FileReader reader = new FileReader("DB/Bodegas.json");
        bodegas = gson.fromJson(reader, bodegasTypeList);
    }

    public static void GuardarDatos() throws IOException, InterruptedException {
        Gson gson = new Gson();
        String json = gson.toJson(bodegas);
        ManejoArchivos.escribirArchivo("DB/Bodegas.json",json);
    }

}
