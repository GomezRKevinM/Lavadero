package co.edu.udc.ejercicio28_lavadero.factory;

import co.edu.udc.ejercicio28_lavadero.enums.TipoDocumento;
import co.edu.udc.ejercicio28_lavadero.exceptions.DocumentoException;
import co.edu.udc.ejercicio28_lavadero.models.Cliente;

import co.edu.udc.ejercicio28_lavadero.services.ValidarDocumento;
import co.edu.udc.ejercicio28_lavadero.valueObjects.DocumentoIdentidad;

public class ClienteFactory {
    public static Cliente crearCliente(String nombre, TipoDocumento tipoID, String identificacion, String correo, String telefono, String direccion) throws DocumentoException {
        if(!ValidarDocumento.validar(identificacion)){
            throw new DocumentoException("El documento no es valido");
        }
        DocumentoIdentidad numeroIdentificacion = new DocumentoIdentidad(identificacion);
        return new Cliente(nombre,tipoID,numeroIdentificacion, correo, telefono, direccion);
    }
}
