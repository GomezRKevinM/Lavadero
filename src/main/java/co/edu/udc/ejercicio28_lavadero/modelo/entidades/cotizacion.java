package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import co.edu.udc.ejercicio28_lavadero.Color;

public class cotizacion extends Documento {
    private Date fechaExpiracion;
    private String estado;
    private List<DetalleCotizacion> detalles = new ArrayList<>();
    private Proveedor proveedor;
    private Empleado realiza;
    private Empleado revisa;
    private Empresa empresa;

    public cotizacion(String Codigo, Date FechaEmision, Date fechaExpiracion,Empleado realizado) {
        super(Codigo, FechaEmision);
        this.fechaExpiracion = fechaExpiracion;
        this.realiza = realizado;
    }



    public void ShowCotizacion(){
        int pLen = 0;
        for(DetalleCotizacion detalles : this.detalles){
            Producto producto = detalles.getProducto();
            if(producto.getNombreProducto().length() > pLen){
                pLen = producto.getNombreProducto().length();
            }
        }
        System.out.println(Color.AZUL_BOLD+"Producto ".concat(" ".repeat(pLen+4)).concat(Color.AZUL_BOLD+"Valor unitario")+" ".repeat(10).concat(Color.AZUL_BOLD+"Cantidad"+"   Total"));
        for(DetalleCotizacion detalles : this.detalles){
            Producto producto = detalles.getProducto();
            Integer cantidad = detalles.getCantidad();
            System.out.println(producto.getNombreProducto() + producto.getPrecio() + cantidad + (producto.getPrecio() * cantidad));
        }
    }

    public void addDetalleCotizacion(DetalleCotizacion detalleCotizacion) {
        this.detalles.add(detalleCotizacion);
    }

    public List<DetalleCotizacion> getDetalles() {
        return detalles;
    }

    public String getEstado(){
        return estado;
    }

    public Empleado getEmpleadoRealiza(){
        return realiza;
    }

    public Empleado getEmpleadoRevisa(){
        return revisa;
    }

    public Proveedor getProveedor(){
        return proveedor;
    }

    public Empresa getEmpresa(){
        return empresa;
    }
}
