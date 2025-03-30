package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

public class InformacionPago {
    private String nombreDeEmpresa;
    private String direccion;
    private String telefono;
    private String email;
    private String cuentas[];

    public InformacionPago(String nombreDeEmpresa, String direccion, String telefono, String email, String[] cuentas) {
        this.nombreDeEmpresa = nombreDeEmpresa;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.cuentas = cuentas;
    }

    public String getNombreDeEmpresa() {
        return nombreDeEmpresa;
    }

    public void setNombreDeEmpresa(String nombreDeEmpresa) {
        this.nombreDeEmpresa = nombreDeEmpresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getCuentas() {
        return this.cuentas;
    }

    public void setCuentas(String[] cuentas) {
        this.cuentas = cuentas;
    }
}
