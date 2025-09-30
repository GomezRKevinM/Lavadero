package co.edu.udc.ejercicio28_lavadero.valueObjects;

import co.edu.udc.ejercicio28_lavadero.exceptions.*;

public record DocumentoIdentidad(String value) {
    public DocumentoIdentidad{

        if(value == null || value().isBlank() || value().isEmpty()){
            try {
                String patron = "^[0-9]{7,10}$";
                if(!value().matches(patron)){
                    throw new DocumentoException("El documento debe tener 7 o 10 digitos");
                }
                throw new DocumentoException("El documento no puede estar vacio");
            } catch (DocumentoException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
