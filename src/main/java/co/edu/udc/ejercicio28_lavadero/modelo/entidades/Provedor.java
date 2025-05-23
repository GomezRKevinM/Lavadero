package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

import co.edu.udc.ejercicio28_lavadero.Color;

/**
 *
 * @author Kevin Gómez
 */
public class Provedor {
    String nombre;
    String id;
    InformacionPago informacionPago;
    List<ComprobantePago> comprobantes = new ArrayList<>();
    List<NotaCorrecion> notasCorrecion = new ArrayList<>();
    List<Cotizacion> cotizaciones = new ArrayList<>();
    List<Producto> productos = new ArrayList<>();



    public Provedor(String nombre, String id){
        this.id = id;
        this.nombre = nombre;
    }

    public Provedor(){
        id = "0001";
        nombre = "REPCOM";
    }

    public void setId(String id) {
        this.id = id;
    }

    public InformacionPago getInformacionPago() {
        return informacionPago;
    }

    public void setInformacionPago(InformacionPago informacionPago) {
        this.informacionPago = informacionPago;
    }

    public List<ComprobantePago> getComprobantes() {
        return comprobantes;
    }

    public void setComprobantes(List<ComprobantePago> comprobantes) {
        this.comprobantes = comprobantes;
    }

    public List<NotaCorrecion> getNotasCorrecion() {
        return notasCorrecion;
    }

    public void setNotasCorrecion(List<NotaCorrecion> notasCorrecion) {
        this.notasCorrecion = notasCorrecion;
    }

    public List<Cotizacion> getCotizaciones() {
        return cotizaciones;
    }

    public void setCotizaciones(List<Cotizacion> cotizaciones) {
        this.cotizaciones = cotizaciones;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {    
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void showInformacion(){
        System.out.println(Color.PURPLE_BOLD+"Información de proveedor");
        System.out.println(Color.BLANCO_BOLD+"Nombre: " + Color.AZUL_BLINK+this.getNombre());
        System.out.println(Color.BLANCO_BOLD+"ID: " + Color.AZUL_BLINK+this.getId());
        informacionPago.showInformacionPago();
    }
}
