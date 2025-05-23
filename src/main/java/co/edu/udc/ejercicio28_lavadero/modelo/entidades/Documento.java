package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import java.sql.Date;

public abstract class Documento {
    String Codigo;
    String Estado;
    Date FechaEmision;
    
    public Documento(String Codigo, Date FechaEmision){
        this.Codigo = Codigo;
        this.Estado = "Activo";
        this.FechaEmision = FechaEmision;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public Date getFechaEmision() {
        return FechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        FechaEmision = fechaEmision;
    }

    
}
