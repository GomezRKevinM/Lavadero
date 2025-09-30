package co.edu.udc.ejercicio28_lavadero.models;

import java.sql.Date;
import java.util.ArrayList;


import java.util.List;

import co.edu.udc.ejercicio28_lavadero.Color;
import co.edu.udc.ejercicio28_lavadero.exceptions.DocumentoException;

public class Cotizacion extends Documento {
    private Date fechaExpiracion;
    private String estado;
    private List<DetalleCotizacion> detalles = new ArrayList<>();
    private Provedor provedor;
    private Empleado realiza;
    private Empleado revisa;
    private Empresa empresa;

    public Cotizacion(String Codigo, Date FechaEmision, java.sql.Date fechaExpiracion, Empleado realizado) {
        super(Codigo, FechaEmision);
        this.fechaExpiracion = fechaExpiracion;
        this.realiza = realizado;
    }

    public Cotizacion() throws DocumentoException {
        super("1", new Date(2020, 4, 24));
        this.estado = "Pendiente";
        this.fechaExpiracion = new Date(2020, 4, 24);
        this.detalles = new ArrayList<>();
        this.provedor = new Provedor();
        this.realiza = new Empleado();
        this.revisa = new Empleado();
        this.empresa = new Empresa();

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

    public Date getFechaExpiracion(){
        return fechaExpiracion;
    }

    public void setFechaExpiracion(java.sql.Date fechaExpiracion){
        this.fechaExpiracion = fechaExpiracion;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    public Empleado getEmpleadoRealiza(){
        return realiza;
    }

    public void setRealiza(Empleado realiza){
        this.realiza = realiza;
    }

    public Empleado getEmpleadoRevisa(){
        return revisa;
    }

    public void setRevisa(Empleado revisa){
        this.revisa = revisa;
    }

    public void setProveedor(Provedor provedor){
        this.provedor = provedor;
    }

    public Provedor getProveedor(){
        return provedor;
    }

    public Empresa getEmpresa(){
        return empresa;
    }
}
