package co.edu.udc.ejercicio28_lavadero.models;

import java.util.ArrayList;


import co.edu.udc.ejercicio28_lavadero.enums.*;
import co.edu.udc.ejercicio28_lavadero.valueObjects.DocumentoIdentidad;

import java.util.List;

public class Cliente extends Persona {
    List<Vehiculo> vehiculos = new ArrayList<>();

    int turno;
    public Cliente(String nombre, TipoDocumento tipoID, DocumentoIdentidad identificacion, String correo, String telefono, String direccion) {
        super(nombre,tipoID,identificacion, correo, telefono, direccion);
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
    }

    public void eliminarVehiculo(Vehiculo vehiculo) {
        vehiculos.remove(vehiculo);
    }

    public void modificarVehiculo(Vehiculo vehiculo) {
        vehiculos.set(vehiculos.indexOf(vehiculo), vehiculo);
    }

    public Vehiculo buscarVehiculo(java.lang.String placa) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getPlaca().equals(placa)) {
                return vehiculo;
            }
        }
        return null;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
}
