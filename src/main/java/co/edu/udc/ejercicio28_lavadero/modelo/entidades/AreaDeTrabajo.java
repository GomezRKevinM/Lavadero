package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

public class AreaDeTrabajo {
    private String idAreaDeTrabajo;
    private String nombreAreaDeTrabajo;
    private String descripcion;
    private List<Cubiculo> cubiculos = new ArrayList<>();
    

    public AreaDeTrabajo(String idAreaDeTrabajo, String nombreAreaDeTrabajo) {
        this.idAreaDeTrabajo = idAreaDeTrabajo;
        this.nombreAreaDeTrabajo = nombreAreaDeTrabajo;
    }

    public void addCubiculo(Cubiculo cubiculo) {
        this.cubiculos.add(cubiculo);
    }

    public void removeCubiculo(Cubiculo cubiculo){
        this.cubiculos.remove(cubiculo);
    }

    public void addDescription(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String newDescripcion){
        descripcion = newDescripcion;
    }

    public String getIdAreaDeTrabajo() {
        return idAreaDeTrabajo;
    }

    public void setIdAreaDeTrabajo(String idAreaDeTrabajo) {
        this.idAreaDeTrabajo = idAreaDeTrabajo;
    }

    public String getNombreAreaDeTrabajo() {
        return nombreAreaDeTrabajo;
    }

    public void setNombreAreaDeTrabajo(String nombreAreaDeTrabajo) {
        this.nombreAreaDeTrabajo = nombreAreaDeTrabajo;
    }

    public List<Cubiculo> getCubiculos() {
        return cubiculos;
    }

    public void setCubiculos(List<Cubiculo> cubiculos) {
        this.cubiculos = cubiculos;
    }
    
}
