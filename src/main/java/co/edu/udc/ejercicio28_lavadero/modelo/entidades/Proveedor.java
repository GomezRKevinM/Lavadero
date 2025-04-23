package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

import co.edu.udc.ejercicio28_lavadero.Color;

/**
 *
 * @author Kevin Gómez
 */
public class Proveedor {
    String nombre;
    String id;
    InformacionPago informacionPago;
    List<ComprobantePago> comprobantes = new ArrayList<>();
    List<NotaCorrecion> notasCorrecion = new ArrayList<>();
    List<cotizacion> cotizaciones = new ArrayList<>();
    List<Producto> productos = new ArrayList<>();
    


    Proveedor(String nombre, String id){
        this.id = id;
        this.nombre = nombre;
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
