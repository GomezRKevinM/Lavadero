package co.edu.udc.ejercicio28_lavadero.services;
import co.edu.udc.ejercicio28_lavadero.events.DocumentoValido;
import co.edu.udc.ejercicio28_lavadero.exceptions.DocumentoException;
import co.edu.udc.ejercicio28_lavadero.util.ConsultarData;

import java.time.Instant;

public class ValidarDocumento {

    public static boolean validar(String documento) throws DocumentoException {
        if (!documento.matches("\\d{7,10}")) {
            throw new DocumentoException("Formato de documento inválido");
        }

        boolean existe = ConsultarData.existeDocumento("clientes", "cedula", documento);
        if (!existe) {
            throw new DocumentoException("El documento no existe en la base de datos");
        }

        DocumentoValido evento = new DocumentoValido(documento, Instant.now());
        System.out.println("✅ Documento validado: " + evento.documento() + " en " + evento.fechaValidacion());
        return true;
    }

}