package co.edu.udc.ejercicio28_lavadero.modelo.crud;


import co.edu.udc.ejercicio28_lavadero.Color;
import co.edu.udc.ejercicio28_lavadero.Principal;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Categoria;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Producto;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

import co.edu.udc.ejercicio28_lavadero.util.ConsultarData;
import co.edu.udc.ejercicio28_lavadero.util.DeleteData;
import co.edu.udc.ejercicio28_lavadero.util.InsertData;
import co.edu.udc.ejercicio28_lavadero.util.UpdateData;
import com.google.gson.Gson;


public class ProductoCrudl {

    public void agregar(Producto p) throws Exception {
        InsertData.Producto(p.getNombreProducto(),p.getMarca(),p.getCategoria(),p.getPrecio(),p.getPrecioDeCompra(),p.getStock(),p.getAlerta(),p.getCodigoDelProveedor());
    }

    public Producto buscar(String codigo) {
        return ConsultarData.Producto(codigo);
    }


    public void editar(Producto p) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);
        CategoriaCrudl categoriaCRUD = new CategoriaCrudl();
        Categoria categoria = categoriaCRUD.buscar(p.getCategoria());
        Producto producto = ConsultarData.Producto(p.getCodigo());
        System.out.println(Color.PURPLE_BOLD+"Editar Producto: "+Color.AZUL+producto.getNombreProducto()+Color.RESET+" (ID: "+producto.getCodigo()+")");
        System.out.println("1) Stock: " + producto.getStock());
        System.out.println("2) Precio: " + convertirDivisa(producto.getPrecio()));
        System.out.println("3) Precio de compra: "+convertirDivisa(producto.getPrecioDeCompra()));
        System.out.println("4) Marca: " + producto.getMarca());
        System.out.println("5) Categoria: " + categoria.getNombre());
        System.out.println("6) Alerta: "+producto.getAlerta()+" stock minimo");
        System.out.println("7) Codigo del proveedor: "+producto.getCodigoDelProveedor());
        System.out.println("8) Nombre: " + producto.getNombreProducto());
        System.out.print(Color.AMARILLO_BLINK+"Disponibilidad: "+Color.CYAN_BOLD);
        System.out.print(producto.getDisponibilidad()?"Disponible":"No disponible");
        System.out.println(Color.RESET+"\n9) Salir");

        System.out.println("Escribe el numero del que deseas cambiar");
        String answer = input.nextLine();
        Gson gson = new Gson();
        String json;
        switch (answer){
            case "1":
                Principal.limpiarPantalla();
                System.out.println("Stock Actual: "+producto.getStock());
                System.out.print("Escriba el nuevo Stock: ");
                int stock = Integer.parseInt(input.nextLine());
                UpdateData.Bodega("stock",String.valueOf(producto.getCodigo()),String.valueOf(stock));
                break;
            case "2":
                Principal.limpiarPantalla();
                System.out.println("Precio Actual: "+convertirDivisa(producto.getPrecio()));
                System.out.print("Escriba el nuevo Precio: $");
                double precio = Double.parseDouble(input.nextLine());
                UpdateData.Producto("precio",String.valueOf(precio),Integer.parseInt(producto.getCodigo()));
                break;
            case "3":
                Principal.limpiarPantalla();
                System.out.println("Precio de compra actual: "+convertirDivisa(producto.getPrecioDeCompra()));
                System.out.print("Escriba el nuevo Precio de compra: $");
                double precioCompra = Double.parseDouble(input.nextLine());
                UpdateData.Producto("precio_de_compra",String.valueOf(precioCompra),Integer.parseInt(producto.getCodigo()));
                break;
            case "4":
                Principal.limpiarPantalla();
                System.out.println("Marca Actual: "+producto.getMarca());
                System.out.print("Escriba la nueva Marca: ");
                String marca = input.nextLine();
                UpdateData.Producto("marca",marca,Integer.parseInt(producto.getCodigo()));
                break;
            case "5":
                Principal.limpiarPantalla();
                System.out.println("Categoria Actual: "+categoria.getNombre());
                System.out.println("Categorias disponibles: ");
                for(Categoria cat: ConsultarData.Categorias()){
                    System.out.println(cat.getNombre()+" codigo: "+cat.getCodigo() );
                }
                System.out.println("Escriba el codigo de la categoria (si desea agregar una nueva cateogira solo escriba el nombre): ");
                String nuevaCategoria = input.nextLine();
                boolean esNum = nuevaCategoria.matches("-?\\d+(\\.\\d+)?");
                if(!esNum){
                    System.out.println("Icono de la categoria: ");
                    String icono = input.nextLine();
                    InsertData.Categoria(nuevaCategoria,icono);
                    int idCatagoriaNueva = Integer.parseInt(categoriaCRUD.listarTodo().getLast().getCodigo());
                    UpdateData.Producto("categoria",String.valueOf(idCatagoriaNueva),Integer.parseInt(producto.getCodigo()));
                    System.out.println("Categoria agregada con exito");
                }else{
                    UpdateData.Producto("categoria",nuevaCategoria,Integer.parseInt(producto.getCodigo()));
                }
                break;
            case "6":
                Principal.limpiarPantalla();
                System.out.println("Alerta Actual: "+producto.getAlerta()+" stock minimo");
                System.out.print("Escriba la nueva Alerta: ");
                int alerta = Integer.parseInt(input.nextLine());
                UpdateData.Producto("alerta",String.valueOf(alerta),Integer.parseInt(producto.getCodigo()));
                break;
            case "7":
                Principal.limpiarPantalla();
                System.out.println("Codigo Actual del proveedor: "+producto.getCodigoDelProveedor());
                System.out.print("Escriba el nuevo codigo del proveedor: ");
                String codigoProveedor = input.nextLine();
                UpdateData.Producto("codigo_provedor",codigoProveedor,Integer.parseInt(producto.getCodigo()));
                break;
            case "8":
                Principal.limpiarPantalla();
                System.out.println("Nombre Actual: "+producto.getNombreProducto());
                System.out.print("Escriba el nuevo nombre: ");
                String nombre = input.nextLine();
                System.out.println("Nombre actualizado: "+nombre + "en id: "+producto.getCodigo());
                UpdateData.Producto("nombre_producto",nombre,Integer.parseInt(producto.getCodigo()));
                break;
            case "9":
                Principal.limpiarPantalla();
                System.out.println("Saliendo");
                System.exit(0);
                break;
        }

        input.close();
    }



    public void eliminar(String codigo) {
        DeleteData.DeleteTable("Productos","codigo",Integer.parseInt(codigo) );
    }

    public List<Producto> listarTodo() {
        return ConsultarData.Productos();
    }

    public Integer contar() {
       return ConsultarData.Productos().size();
    }

    public String convertirDivisa(double monto){
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.forLanguageTag("es-CO"));
        dfs.setCurrencySymbol(Currency.getInstance("COP").getSymbol());
        DecimalFormat df = new DecimalFormat("¤ #,##0", dfs);
        return df.format(monto)+" "+dfs.getCurrency().getCurrencyCode();
    }
}