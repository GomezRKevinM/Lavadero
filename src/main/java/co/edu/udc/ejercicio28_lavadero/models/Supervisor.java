package co.edu.udc.ejercicio28_lavadero.models;

import co.edu.udc.ejercicio28_lavadero.enums.TipoDocumento;
import co.edu.udc.ejercicio28_lavadero.valueObjects.DocumentoIdentidad;

import java.util.ArrayList;

public class Supervisor extends Empleado {
    ArrayList<Cliente> turnos = new ArrayList<>();
    public Supervisor(String nombre, TipoDocumento tipoID, DocumentoIdentidad identificacion, String correo, String telefono, String direccion, Contrato contrato) {
        super(nombre,tipoID, identificacion, correo, telefono, direccion, contrato);
    }

    public Cliente getTurnoFirst() {
        return turnos.getFirst();
    }

    public Cliente getTurnoLast() {
        return turnos.getLast();
    }

    public void asignarHorarioEmpleado(String horario, Empleado empleado) {
        empleado.setHorario(horario);
    }


}
