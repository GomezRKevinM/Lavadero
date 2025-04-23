package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Pedido  extends Documento{
    Date FechaMaximaEntrega;
    cotizacion cotizacion;
    String estado;
    List<DetallePedido> detalles = new ArrayList<>();
    double valorPagar;
    double valorPagado;


    public Pedido(String Codigo, String Estado, Date FechaEmision, Date FechaMaximaEntrega) {
        super(Codigo, FechaEmision);
        this.FechaMaximaEntrega = FechaMaximaEntrega;
        for(DetallePedido detalle: detalles){
            valorPagar += detalle.getPrecioTotal();
        }
    }

    public String getEstado(){
        return estado;
    }

    public void showProductosRecibidos(){
        for(DetallePedido pedido: detalles){
            System.out.println("Producto:"+pedido.geProducto().getNombreProducto() + " Recibidos: "+pedido.getCantidadRecibida());
        }
    }

    public double getValorPagar() {
        return valorPagar;
    }

    public void pagar(double valorPagado) {
        System.out.println("Se ha pagado: "+valorPagado);
        this.valorPagado += valorPagado;
        System.out.println("Nuevo acumulado en pago: "+this.valorPagado);
    }

    public void generateComprobantePago(){
        Date fechaPago = new Date();
        Scanner sc = new Scanner(System.in);
        System.out.println("Valor a pagar: "+this.getValorPagar());
        System.out.print("Se pagó: ");
        double pago = sc.nextDouble();
        this.pagar(pago);
        System.out.println("Seleccione el metodo de pago");
        int opcion = 1;
        for(MetodoPago metodo: MetodoPago.values()){
            System.out.println(opcion+") "+metodo);
            opcion++;
        }
        opcion = Integer.parseInt(sc.nextLine());
        MetodoPago metodo = MetodoPago.values()[opcion-1];

        ComprobantePago comprobante = new ComprobantePago(fechaPago, valorPagar, valorPagado, metodo);
        cotizacion.getProveedor().comprobantes.add(comprobante);
        cotizacion.getEmpresa().getComprobantes().add(comprobante);
        sc.close();
    }

    public Date getFechaMaximaEntrega() {
        return FechaMaximaEntrega;
    }

    public cotizacion getCotizacion() {
        return cotizacion;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public double getValorPagado() {
        return valorPagado;
    }

    public Date getFechaMaximaConfirmacion() {
        return FechaMaximaEntrega;
    }

    
}
