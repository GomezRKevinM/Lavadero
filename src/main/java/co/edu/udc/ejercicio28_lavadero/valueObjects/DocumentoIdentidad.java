package co.edu.udc.ejercicio28_lavadero.valueObjects;

import co.edu.udc.ejercicio28_lavadero.exceptions.*;
import java.util.Objects;

public class DocumentoIdentidad {
    private String valor;
    public DocumentoIdentidad(String valor) throws DocumentoException {
        if (valor == null || valor.trim().isEmpty()) {
            throw new DocumentoException("El documento no puede estar vacío");
        }
        if (!valor.matches("\\d{7,10}")) {
            throw new DocumentoException("El documento debe tener entre 7 y 10 dígitos numéricos");
        }

        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) throws DocumentoException {
        if(valor.trim().isEmpty()){
            throw new DocumentoException("El documento no puede estar vacio");
        }

        if(!valor.matches("\\d{7,10}")) {
            throw new DocumentoException("El documento debe tener entre 7 y 10 dégitos numéricos");
        }

        if(valor.equals(this.valor)){
            throw new IllegalArgumentException("Este numero ya esta guardado!");
        }
        this.valor = valor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DocumentoIdentidad)) return false;
        DocumentoIdentidad otro = (DocumentoIdentidad) obj;
        return valor.equals(otro.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

}
