package co.edu.udc.ejercicio28_lavadero.modelo.entidades;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Contrato {
    String id;
    String FechaInicio;
    String FechaFinal;
    double SueldoBase;
    String ContratadoCC;
    String ContratanteID;
    String horario;
    ArrayList<String> clausulas;
    Cargo cargo;

    public Contrato(String id,String FechaInicio, double SueldoBase, Cargo cargo,String FechaFinal, String horario){
        this.id = id;
        this.FechaInicio = FechaInicio;
        this.FechaFinal = FechaFinal;
        this.SueldoBase = SueldoBase;
        this.cargo = cargo;
        this.horario = horario;
    }

    public Contrato(){
        id = "0001";
        FechaInicio = "2020-12-10";
        Locale.setDefault(new Locale("es", "ES"));
        FechaFinal = "2021-12-10";
        SueldoBase = 0;
        ContratadoCC = "1001973042";
        ContratanteID = "1";
        horario = "7am a 5pm";
        clausulas = new ArrayList<>();
        cargo = Cargo.Tecnico;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContratadoCC() {
        return ContratadoCC;
    }

    public void setContratadoCC(String contratadoCC) {
        ContratadoCC = contratadoCC;
    }

    public String getContratanteID() {
        return ContratanteID;
    }

    public void setContratanteID(String contratanteID) {
        ContratanteID = contratanteID;
    }

    public String getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return FechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        FechaFinal = fechaFinal;
    }

    public double getSueldoBase() {
        return SueldoBase;
    }

    public void setSueldoBase(double sueldoBase) {
        this.SueldoBase = sueldoBase;
    }

    public String getContratado() {
        return ContratadoCC;
    }

    public void setContratado(String ID) {
        this.ContratadoCC = ID;
    }

    public String getContratante() {
        return ContratanteID;
    }

    public void setContratante(String ID) {
        this.ContratanteID = ID;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public ArrayList<String> getClausulas() {
        return clausulas;
    }

    public void setClausulas(ArrayList<String> clausulas) {
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
