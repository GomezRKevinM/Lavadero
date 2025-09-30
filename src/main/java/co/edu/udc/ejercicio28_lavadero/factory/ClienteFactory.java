package co.edu.udc.ejercicio28_lavadero.factory;

import co.edu.udc.ejercicio28_lavadero.enums.TipoDocumento;
import co.edu.udc.ejercicio28_lavadero.exceptions.DocumentoException;
import co.edu.udc.ejercicio28_lavadero.models.Cliente;

import co.edu.udc.ejercicio28_lavadero.valueObjects.DocumentoIdentidad;

public class ClienteFactory {
    public static Cliente crearCliente(String nombre, TipoDocumento tipoID, String identificacion, String correo, String telefono, String direccion) {
        DocumentoIdentidad numeroIdentificacion;
        try {
            numeroIdentificacion = new DocumentoIdentidad(identificacion);
        } catch (DocumentoException e) {
            throw new RuntimeException(e);
        }
        return new Cliente(nombre,tipoID,numeroIdentificacion, correo, telefono, direccion);
    }
}
