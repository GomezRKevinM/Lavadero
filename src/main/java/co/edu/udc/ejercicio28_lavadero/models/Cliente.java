package co.edu.udc.ejercicio28_lavadero.models;

import java.util.ArrayList;


import co.edu.udc.ejercicio28_lavadero.models.*;
import co.edu.udc.ejercicio28_lavadero.enums.*;
import java.util.List;

public class Cliente extends Persona {
    List<Vehiculo> vehiculos = new ArrayList<>();

    int turno;
    public Cliente(java.lang.String nombre, TipoDocumento tipoID, java.lang.String identificacion, java.lang.String correo, java.lang.String telefono, java.lang.String direccion) {
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
