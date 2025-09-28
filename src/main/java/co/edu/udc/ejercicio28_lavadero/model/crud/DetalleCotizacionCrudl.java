package co.edu.udc.ejercicio28_lavadero.models.crud;

import co.edu.udc.ejercicio28_lavadero.Color;
import co.edu.udc.ejercicio28_lavadero.models.entidades.DetalleCotizacion;
import co.edu.udc.ejercicio28_lavadero.util.ConsultarData;
import co.edu.udc.ejercicio28_lavadero.util.DeleteData;
import co.edu.udc.ejercicio28_lavadero.util.InsertData;
import co.edu.udc.ejercicio28_lavadero.util.UpdateData;

import java.util.ArrayList;
import java.util.Scanner;

public class DetalleCotizacionCrudl {

    public void agregar(DetalleCotizacion detalleCotizacion){
        InsertData.DetalleCotizacion(Integer.parseInt(detalleCotizacion.getProducto().getCodigo()),detalleCotizacion.getCantidad(),detalleCotizacion.getPrecioUnitario(),detalleCotizacion.getDescuento(),detalleCotizacion.getIva(),detalleCotizacion.getTiempoGarantia(),Integer.parseInt(detalleCotizacion.getCotizacion().getCodigo()));
    }

    public DetalleCotizacion buscar(int id) throws Exception{
       for(DetalleCotizacion detalle: listarTodo()){
           if(detalle.getId() == id){
               return detalle;
           }
       }
       throw new Exception("Detalle cotización no encontrado");
    }

    public void editar(DetalleCotizacion detalle) throws Exception{
        DetalleCotizacion encontrado = buscar(detalle.getId());

        if(encontrado != null){

            if(encontrado.getCotizacion().getEstado().equalsIgnoreCase("Cotizacion completa")){
                throw new Exception("No puedes editar este detalle, ya que la cotizacion esta completa o entregada");
            }else{
                System.out.println("Editar detalle ID("+detalle.getId()+")");
                System.out.println("1) Producto: "+detalle.getProducto().getNombreProducto());
                System.out.println("2) Cantidad: "+detalle.getCantidad());
                System.out.println("3) precio unitario: "+detalle.getPrecioUnitario());
                System.out.println("4) IVA: "+detalle.getIva());
                System.out.println("5) tiempo de garantia: "+detalle.getTiempoGarantia());
                Scanner input = new Scanner(System.in);
                System.out.print("Seleccione el numero adecuado a la opción: ");
                String opcion = input.nextLine();

                switch (opcion){
                    case "1":
                        System.out.println("Nuevo producto: ");
                        for(int i = 0; i < ConsultarData.Productos().size(); i++){
                            System.out.println(i+1+") nombre: "+ConsultarData.Productos().get(i).getNombreProducto());
                        }
                        System.out.print("Seleccione una opcion: ");
                        int producto =Integer.parseInt(input.nextLine())-1;
                        UpdateData.DetalleCotizacion("producto",detalle.getProducto().getCodigo(),detalle.getId());
                        break;
                    case "2":
                        System.out.print("Nueva cantidad: ");
                        String cantidad = input.nextLine();
                        UpdateData.DetalleCotizacion("cantidad",cantidad,detalle.getId());
                        break;
                    case "3":
                        System.out.print("Nuevo precio unitario: ");
                        String precioUnitario = input.nextLine();
                        UpdateData.DetalleCotizacion("precio_unitario",precioUnitario,detalle.getId());
                        break;
                    case "4":
                        System.out.println("Nuevo IVA: ");
                        String iva = input.nextLine();
                        UpdateData.DetalleCotizacion("iva",iva,detalle.getId());
                        break;
                    case "5":
                        System.out.println("Nuevo tiempo de garantia (meses): ");
                        String tiempoGarantia = input.nextLine();
                        UpdateData.DetalleCotizacion("tiempo_garantia",tiempoGarantia,detalle.getId());
                        break;
                    default:
                    throw new Exception(Color.ROJO_BLINK+"Opción invalida ✖️");
                }
            }
        }
    }

    public void eliminar(int id) throws Exception{
        DetalleCotizacion encontrado = buscar(id);
        if(encontrado != null){
            DeleteData.DeleteTable("DetallesCotizacion", "id", encontrado.getId() );
        }else{
            throw new Exception("Detalle no existente! ✖️");
        }
    }

    public ArrayList<DetalleCotizacion> listarTodo(){
        return ConsultarData.DetallesCotiaciones();
    }

    public Integer contar(){
        return listarTodo().size();
    }
}
