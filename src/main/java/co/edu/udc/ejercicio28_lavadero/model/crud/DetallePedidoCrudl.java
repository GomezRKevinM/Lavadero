package co.edu.udc.ejercicio28_lavadero.models.crud;

import co.edu.udc.ejercicio28_lavadero.models.entidades.DetallePedido;

import co.edu.udc.ejercicio28_lavadero.util.ConsultarData;
import co.edu.udc.ejercicio28_lavadero.util.DeleteData;
import co.edu.udc.ejercicio28_lavadero.util.InsertData;
import co.edu.udc.ejercicio28_lavadero.util.UpdateData;

import java.util.ArrayList;
import java.util.Scanner;

public class DetallePedidoCrudl {

    public void agregar(DetallePedido detalle){
        InsertData.DetallePedido(Integer.parseInt(detalle.getProducto().getCodigo()),detalle.getCantidad(),detalle.getPrecioUnitario(),detalle.getDescuento(),detalle.getIva(),detalle.getCantidad_recibida(),detalle.isVerificado(),detalle.getCodigo_pedio());
    }

    public DetallePedido buscar(int idDetalle, String codigoPedido){
        for(DetallePedido detalle: listarTodo(codigoPedido)){
            if(detalle.getId() == idDetalle){
                return detalle;
            }
        }
        throw new IllegalArgumentException("No existe detalle de pedido con el id: "+idDetalle);
    }

    public void editar(DetallePedido detalle) throws Exception {
        System.out.println("Editar Detalle del Pedido: #"+detalle.getCodigo_pedio()+"-"+detalle.getId());
        System.out.println("1) Cantidad: "+detalle.getCantidad());
        System.out.println("2) Precio Unitario: "+detalle.getPrecioUnitario());
        System.out.println("3) Descuento: "+detalle.getDescuento());
        System.out.println("4) IVA: "+detalle.getIva());
        System.out.println("5) Cantidad Recibida: "+detalle.getCantidad_recibida());
        System.out.println("6) Verificado: "+detalle.isVerificado());
        System.out.print("Seleccione una opcion(1-6): ");
        Scanner input = new Scanner(System.in);
        String opcion = input.nextLine();
        switch (opcion){
            case "1":
                System.out.println("Nuevo Cantidad: ");
                int nuevoCantidad = Integer.parseInt(input.nextLine());
                UpdateData.UpdateData("DetallesPedido","cantidad",String.valueOf(nuevoCantidad),"id",String.valueOf(detalle.getId()));
                break;
            case "2":
                System.out.println("Nuevo Precio Unitario: ");
                double nuevoPrecioUnitario = Double.parseDouble(input.nextLine());
                UpdateData.UpdateData("DetallesPedido","precio_unitario",String.valueOf(nuevoPrecioUnitario),"id",String.valueOf(detalle.getId()));
                break;
            case "3":
                System.out.println("Nuevo Descuento: ");
                double nuevoDescuento = Double.parseDouble(input.nextLine());
                if(nuevoDescuento>0){
                    nuevoDescuento = nuevoDescuento/100;
                }
                if(nuevoDescuento > 100){
                    throw new IllegalArgumentException("El descuento no puede ser mayor a 100%");
                }
                UpdateData.UpdateData("DetallesPedido","descuento",String.valueOf(nuevoDescuento),"id",String.valueOf(detalle.getId()));
                break;
            case "4":
                System.out.println("Nuevo IVA: ");
                double nuevoIva = Double.parseDouble(input.nextLine());
                if(nuevoIva>0){
                    nuevoIva = nuevoIva/100;
                }
                if(nuevoIva > 100){
                    throw new IllegalArgumentException("El IVA no puede ser mayor a 100%");
                }
                UpdateData.UpdateData("DetallesPedido","iva",String.valueOf(nuevoIva),"id",String.valueOf(detalle.getId()));
                break;
            case "5":
                System.out.println("Nuevo Cantidad Recibida: ");
                int nuevoCantidadRecibida = Integer.parseInt(input.nextLine());
                UpdateData.UpdateData("DetallesPedido","cantidad_recibida",String.valueOf(nuevoCantidadRecibida),"id",String.valueOf(detalle.getId()));
                break;
            case "6":
                System.out.println("Nuevo Verificado: ");
                boolean nuevoVerificado = Boolean.parseBoolean(input.nextLine());
                UpdateData.UpdateData("DetallesPedido","verificado",String.valueOf(nuevoVerificado),"id",String.valueOf(detalle.getId()));
                break;
            default:
                throw new Exception("Opción invalida ✖️");
        }
    }

    public void eliminar(int idDetalle, String codigoPedido){
        DeleteData.DeleteTable("DetallesPedido","id",Integer.parseInt(codigoPedido));
    }

    public ArrayList<DetallePedido> listarTodo(String codigoPedido){
        return ConsultarData.DetallesPedido(codigoPedido);
    }

    public Integer contar(String codigoPedido){
        return listarTodo(codigoPedido).size();
    }
}
