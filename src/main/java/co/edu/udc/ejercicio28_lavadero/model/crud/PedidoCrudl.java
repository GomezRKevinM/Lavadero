package co.edu.udc.ejercicio28_lavadero.models.crud;

import co.edu.udc.ejercicio28_lavadero.Principal;
import co.edu.udc.ejercicio28_lavadero.models.entidades.DetallePedido;
import co.edu.udc.ejercicio28_lavadero.models.entidades.Pedido;
import co.edu.udc.ejercicio28_lavadero.models.entidades.Producto;
import co.edu.udc.ejercicio28_lavadero.util.ConsultarData;
import co.edu.udc.ejercicio28_lavadero.util.DeleteData;
import co.edu.udc.ejercicio28_lavadero.util.InsertData;

import java.util.ArrayList;
import java.util.Scanner;

public class PedidoCrudl {
    public void agregar(Pedido pedido) {
        InsertData.Pedido(pedido.getEstado(),pedido.getFechaEmision(),pedido.getFechaMaximaEntrega(),pedido.getValorPagar(),pedido.getValorPagado(),Integer.parseInt(pedido.getCotizacion().getCodigo()));
    }

    public Pedido buscar(String codigo) {
       return ConsultarData.Pedido(codigo);
    }

    public void editar(Pedido pedido) throws Exception {
        for(Pedido pedidoExistente: listarTodo()){
            if(pedidoExistente.getCodigo().equals(pedido.getCodigo())){
                if(pedidoExistente.getEstado().equalsIgnoreCase("enviado")||pedidoExistente.getEstado().equalsIgnoreCase("recibido")){
                    throw new Exception("No se puede editar el pedido");
                }else{
                    Principal.limpiarPantalla();
                    System.out.println("Editar Pedido: ");
                    System.out.println("1) Agregar Producto");
                    System.out.println("2) Eliminar Producto");
                    System.out.print("Seleccione una opcion(1-2): ");
                    Scanner input = new Scanner(System.in);
                    String opcion = input.nextLine();
                    switch (opcion){
                        case "1":
                            System.out.println("Agregar Producto");
                            System.out.println("  ID  |   Nombre");
                            for(Producto producto: ConsultarData.Productos()){
                                System.out.println("  "+producto.getCodigo()+" "+producto.getNombreProducto());
                            }
                            System.out.println("Escriba el ID del producto: ");
                            int idProducto = Integer.parseInt(input.nextLine());
                            Producto producto = ConsultarData.Producto(String.valueOf(idProducto));
                            System.out.println("Cantidad: ");
                            int cantidad = Integer.parseInt(input.nextLine());

                            if(cantidad <= 0){
                                throw new Exception("La cantidad debe ser mayor a 0");
                            }

                            DetallePedido nuevoProducto = new DetallePedido(Integer.parseInt(pedido.getCodigo()),producto,cantidad,producto.getPrecio(),0,0.19,0,false);
                            InsertData.DetallePedido(Integer.parseInt(nuevoProducto.getProducto().getCodigo()),nuevoProducto.getCantidad(),nuevoProducto.getPrecioUnitario(),nuevoProducto.getDescuento(),nuevoProducto.getIva(),nuevoProducto.getCantidad_recibida(),nuevoProducto.isVerificado(),Integer.parseInt(pedido.getCotizacion().getCodigo()));
                        break;
                        case "2":
                            System.out.println("Eliminar Producto ");
                            System.out.println("  ID  |   Nombre");
                            for(DetallePedido detallePedido: pedidoExistente.getDetalles()){
                                System.out.println("  "+detallePedido.getId()+" "+detallePedido.getProducto().getNombreProducto());
                            }
                            System.out.println("Escriba el ID del producto: ");
                            int idEliminar = Integer.parseInt(input.nextLine());
                            DeleteData.DeleteTable("DetallesPedido","producto",idEliminar);
                        break;
                        default:
                            throw new Exception("Opción invalida ✖️");
                    }
                }
            }else{
                throw new Exception("Pedido no existente");
            }
        }
    }

    public void eliminar(String codigo) {
        DeleteData.DeleteTable("Pedidos","codigo",Integer.parseInt(codigo));
    }

    public ArrayList<Pedido> listarTodo() {
        return ConsultarData.Pedidos();
    }

    public Integer contar(){
        return listarTodo().size();
    }
}
