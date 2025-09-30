package co.edu.udc.ejercicio28_lavadero.valueObjects;

public class CorreoElectronico {
    private final String valor;

    public CorreoElectronico(String valor) {
        if (valor == null || !valor.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,7}$"
        )) {
            throw new IllegalArgumentException("Correo electrónico inválido");
        }
        this.valor = valor;
    }

    public String obtener() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof CorreoElectronico c && c.valor.equals(this.valor);
    }

    @Override
    public int hashCode() {
        return valor.hashCode();
    }

}
