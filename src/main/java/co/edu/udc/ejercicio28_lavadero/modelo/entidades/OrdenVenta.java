package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import java.util.Date;
import java.util.List;

public class OrdenVenta extends Documento {
    List<DetalleVentaServicio> detalleVentaServicios;
    List<DetalleVentaProducto> detalleVentaProductos;

    public OrdenVenta(String codigo, Date fechaEmision,DetalleVentaProducto detalleVentaProducto,DetalleVentaServicio detalleVentaServicio) {
        super(codigo, fechaEmision);
        detalleVentaProductos.add(detalleVentaProducto);
        detalleVentaServicios.add(detalleVentaServicio);
    }

    public OrdenVenta(String codigo,List<DetalleVentaProducto> detalleVentaProducto, Date fechaEmision) {
        super(codigo, fechaEmision);
        detalleVentaProductos.addAll(detalleVentaProducto);
    }

    public OrdenVenta(String codigo, Date fechaEmision,List<DetalleVentaServicio> detalleVentaServicio) {
        super(codigo, fechaEmision);
        detalleVentaServicios.addAll(detalleVentaServicio);
    }

    public OrdenVenta(String codigo, Date fechaEmision) {
        super(codigo, fechaEmision);
    }

    public List<DetalleVentaServicio> getDetalleVentaServicios() {
        return detalleVentaServicios;
    }

    public void setDetalleVentaServicios(List<DetalleVentaServicio> detalleVentaServicios) {
        this.detalleVentaServicios = detalleVentaServicios;
    }

    public List<DetalleVentaProducto> getDetalleVentaProductos() {
        return detalleVentaProductos;
    }

    public void setDetalleVentaProductos(List<DetalleVentaProducto> detalleVentaProductos) {        
        this.detalleVentaProductos = detalleVentaProductos;
    }

    
    
    
}
