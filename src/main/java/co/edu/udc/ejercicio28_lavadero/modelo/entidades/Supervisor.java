package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import java.util.ArrayList;

public class Supervisor extends Empleado {
    ArrayList<Cliente> turnos = new ArrayList<>();
    public Supervisor(String nombre,TipoID tipoID, String identificacion, String correo, String telefono, String direccion, Contrato contrato) {
        super(nombre,tipoID, identificacion, correo, telefono, direccion, contrato);
    }

    public void asignarTurnoCliente(int turno, Cliente cliente) {
        cliente.setTurno(turno);
        turnos.add(cliente);
    }

    public Cliente buscarTurno(int turno) {
        for (Cliente cliente : turnos) {
            if (cliente.getTurno() == turno) {
                return cliente;
            }
        }
        return null;
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
