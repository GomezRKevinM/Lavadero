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

}
