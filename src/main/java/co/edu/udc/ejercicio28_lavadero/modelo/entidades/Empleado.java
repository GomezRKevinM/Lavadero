package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Empleado extends Persona{
    Contrato contrato;

    public Empleado(String nombre, TipoID tipoID,String identificacion, String correo, String telefono, String direccion, Contrato contrato) {
        super(nombre, tipoID,identificacion, correo, telefono, direccion);
        this.contrato = contrato;
    }


    public Contrato getContrato() {
        return contrato;
    }

    public void setHorario(String horario) {
        this.contrato.setHorario(horario);
    }

    public String getHorario() {
        return this.contrato.getHorario();
    }

    public void revisarPedido(Pedido pedido) {
        List<DetalleCotizacion> detallesPedido = pedido.getCotizacion().getDetalles();
        List<DetallePedido> detallesRecibido = pedido.getDetalles();
        List<Producto> recibidosCorrecto = new ArrayList<>();
        List<Producto> recibidosIncorrecto = new ArrayList<>();

        for(int i = 0; i < detallesPedido.size(); i++){
            if(detallesPedido.get(i).getCantidad() != detallesRecibido.get(i).getCantidadRecibida()){
                recibidosIncorrecto.add(detallesRecibido.get(i).geProducto());
            }else{
                recibidosCorrecto.add(detallesRecibido.get(i).geProducto());
            }
        }

        if(recibidosIncorrecto.size() > 0){
            System.out.println("Productos recibidos incorrectos: ");
            for(Producto producto: recibidosIncorrecto){
                System.out.println(producto.getNombreProducto());
            }
            Date fecha = new Date();
            String Codigo = pedido.getCodigo()+1;
            NotaCorrecion correcion = new NotaCorrecion(Codigo, fecha, pedido.getFechaMaximaConfirmacion(), pedido.getCodigo(), "Productos recibidos incorrectos");
            
        }
    }

    
}
