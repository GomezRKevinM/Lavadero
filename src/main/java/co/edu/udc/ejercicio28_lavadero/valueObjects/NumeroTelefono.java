package co.edu.udc.ejercicio28_lavadero.valueObjects;

public class NumeroTelefono {
    private final String valor;

    public NumeroTelefono(String valor) {
        if (valor == null || !valor.matches("^\\d{7,10}$")) {
            throw new IllegalArgumentException("Número de teléfono inválido");
        }
        this.valor = valor;
    }

    public String obtener() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof NumeroTelefono t && t.valor.equals(this.valor);
    }

    @Override
    public int hashCode() {
        return valor.hashCode();
    }

}
