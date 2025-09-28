package co.edu.udc.ejercicio28_lavadero.model.crud;

import co.edu.udc.ejercicio28_lavadero.Color;
import co.edu.udc.ejercicio28_lavadero.Principal;
import co.edu.udc.ejercicio28_lavadero.models.*;
import co.edu.udc.ejercicio28_lavadero.util.ConsultarData;
import co.edu.udc.ejercicio28_lavadero.util.DeleteData;
import co.edu.udc.ejercicio28_lavadero.util.InsertData;
import co.edu.udc.ejercicio28_lavadero.util.*;

import java.util.ArrayList;
import java.util.Scanner;

public class BodegaCrudl {

    public void agregar(Bodega b){
        InsertData.Bodega((b.getNombre()),b.getUbicacion());
    }

    public Bodega buscar(String codigo) {
        for(Bodega bodega: listarTodo()){
            if(bodega.getCodigo().equals(codigo)){
                return bodega;
            }
        }
        throw new RuntimeException("Bodega no encontrada");
    }

    public void editar(Bodega b){

        Scanner input = new Scanner(System.in);
        System.out.println(Color.CYAN_BOLD+"Editar Bodega: ");
        System.out.println("1) Nombre: " + Color.BLANCO_BOLD+b.getNombre()+ Color.RESET);
        System.out.println("2) Ubicación: " + Color.BLANCO_BOLD+b.getUbicacion()+ Color.RESET);
        System.out.println("3) Productos: "+ Color.BLANCO_BOLD+b.getProductos()+ Color.RESET);
        System.out.println("Escriba el numero de la opción: ");
        String opcion = input.nextLine();
        switch (opcion){
            case "1":
                Principal.limpiarPantalla();
                System.out.println("Nuevo nombre: ");
                String nuevoNombre = input.nextLine();
                if(nuevoNombre.equalsIgnoreCase(b.getNombre())){
                    throw new IllegalArgumentException("El nombre no puede ser igual al anterior");
                }else{
                    UpdateData.UpdateData("Bodegas","nombre",nuevoNombre,"codigo",b.getCodigo());
                }
                break;
            case "2":
                Principal.limpiarPantalla();
                System.out.println("Nueva ubicación: ");
                String nuevaUbicacion = input.nextLine();
                if(nuevaUbicacion.equalsIgnoreCase(b.getUbicacion())){
                    throw new IllegalArgumentException("La nueva ubicación no puede ser igual a la anterior");
                }else{
                    UpdateData.UpdateData("Bodegas","ubicacion",nuevaUbicacion,"codigo",b.getCodigo());
                }
                break;
            case "3":
                Principal.limpiarPantalla();
                System.out.println("1) Agregar producto");
                System.out.println("2) Eliminar producto");
                String productoOpcion = input.nextLine();
                
                switch (productoOpcion){
                    case "1":
                        Principal.limpiarPantalla();
                        System.out.println("Productos existentes: ");
                        ProductoCrudl crudP = new ProductoCrudl();
                        System.out.println("  ID  NombreProducto        Precio");
                        for(Producto producto: crudP.listarTodo()){
                            System.out.println("  "+producto.getCodigo()+" "+producto.getNombreProducto()+" "+producto.getPrecio());
                        }
                        System.out.println("Digite el ID del producto a agregar: ");
                        String productoID = input.nextLine();
                        Producto producto = crudP.buscar(productoID);
                        System.out.println(Color.CYAN_BOLD+" Existen "+ producto.getStock() +" unidades de "+producto.getNombreProducto() );
                        System.out.println("Cuantas unidades desea agregar a esta bodega: ");
                        int unidadesAgregar = Integer.parseInt(input.nextLine());
                        if(unidadesAgregar > producto.getStock()){
                            throw new IllegalArgumentException("No hay suficientes unidades de "+producto.getNombreProducto());
                        } else if (unidadesAgregar < 0) {
                            throw new IllegalArgumentException("No puedes agregar unidades negativas a esta bodega");
                        }

                        InsertData.Producto_Bodega(Integer.parseInt(producto.getCodigo()),unidadesAgregar,Integer.parseInt(b.getCodigo()));

                        break;
                    case "2":
                        Principal.limpiarPantalla();
                        System.out.println("Productos existentes: ");
                        System.out.println("  ID  NombreProducto        Precio");
                        for(Producto productoEliminar: ConsultarData.productosBodega(b.getCodigo())){
                            System.out.println("  "+productoEliminar.getCodigo()+" "+productoEliminar.getNombreProducto()+" "+productoEliminar.getPrecio());
                        }
                        System.out.println("Digite el ID del producto a eliminar: ");
                        int productoEliminarID = Integer.parseInt(input.nextLine());
                        DeleteData.DeleteTable("Productos_bodega","producto",productoEliminarID);
                        break;
                    default:
                        throw new IllegalArgumentException("Opción invalidad ✖️");
                }
                break;
            default:
                throw new IllegalArgumentException("Opción invalida ✖️");
        }
    }

    public void eliminar(String codigo){
        DeleteData.DeleteTable("Bodegas","codigo",Integer.parseInt(codigo));
    }

    public ArrayList<Bodega> listarTodo() {
        return ConsultarData.Bodegas();
    }

    public Integer contar() {
        return ConsultarData.Bodegas().size();
    }

}
