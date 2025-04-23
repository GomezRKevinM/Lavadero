package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import java.util.Date;
import java.util.List;

public class Contrato {
    Date FechaInicio;
    Date FechaFinal;
    double SueldoBase;
    Empleado contratado;
    Empresa contratante;
    String horario;
    List<String> clausulas;
    Cargo cargo;

    public Contrato(Date FechaInicio, double SueldoBase, Cargo cargo,Date FechaFinal, String horario){
        this.FechaInicio = FechaInicio;
        this.FechaFinal = FechaFinal;
        this.SueldoBase = SueldoBase;
        this.cargo = cargo;
        this.horario = horario;
    }

    public Date getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return FechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        FechaFinal = fechaFinal;
    }

    public double getSueldoBase() {
        return SueldoBase;
    }

    public void setSueldoBase(double sueldoBase) {
        SueldoBase = sueldoBase;
    }

    public Empleado getContratado() {
        return contratado;
    }

    public void setContratado(Empleado contratado) {
        this.contratado = contratado;
    }

    public Empresa getContratante() {
        return contratante;
    }

    public void setContratante(Empresa contratante) {
        this.contratante = contratante;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public List<String> getClausulas() {
        return clausulas;
    }

    public void setClausulas(List<String> clausulas) {
        this.clausulas = clausulas;
    }

    public void agregarClausula(String clausula) {
        clausulas.add(clausula);
    }

    public void eliminarClausula(String clausula) {
        clausulas.remove(clausula);
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }   

    

}
