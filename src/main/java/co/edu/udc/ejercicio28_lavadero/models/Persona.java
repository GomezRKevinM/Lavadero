package co.edu.udc.ejercicio28_lavadero.models;

import java.util.Date;
import co.edu.udc.ejercicio28_lavadero.enums.*;

import co.edu.udc.ejercicio28_lavadero.Color;

public class  Persona {

    protected String Nombre;
    protected TipoDocumento TipoDoc;
    protected String Identificacion;
    protected Date FechaNacimiento;
    protected String Correo;
    protected String Telefono;
    protected String Direccion;
    
    public Persona(String nombre,TipoDocumento tipoID, String identificacion, String correo, String telefono, String direccion) {
        this.Nombre = nombre;
        this.Identificacion = identificacion;
        this.Correo = correo;
        this.Telefono = telefono;
        this.Direccion = direccion;
        TipoDoc = tipoID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public TipoDocumento getTipoID() {
        return TipoDoc;
    }

    public void setTipoID(TipoDocumento tipoID) {
        TipoDoc = tipoID;
    }

    public String getIdentificacion() {
        return Identificacion;
    }

    public void setIdentificacion(String identificacion) {
        Identificacion = identificacion;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getInfoContacto(){
        return Color.CYAN_BOLD+"Telefono: "+Color.BLANCO_BOLD+Telefono+Color.CYAN_BOLD+" Correo: "+Color.BLANCO_BOLD+Correo+Color.CYAN_BOLD+" Direccion: "+Color.BLANCO_BOLD+Direccion+Color.RESET;
    }

    
}
