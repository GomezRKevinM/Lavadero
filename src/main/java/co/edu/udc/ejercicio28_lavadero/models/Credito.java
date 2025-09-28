package co.edu.udc.ejercicio28_lavadero.models;

import java.sql.Date;
import java.util.List;


public class Credito extends InformacionPago{

    Date FechaVencimiento;
    int limiteCredito;
    Date FechaInicial;
    double pagosMensuales;

    public Credito(int id,List<MetodoPago> metodos, String direccion, String telefono, String email, CuentaBancaria cuenta, Date fechaVencimiento, int limiteCredito, Date fechaInicial, double pagosMensuales) {
        super(id,metodos, direccion, telefono, email, cuenta);
        this.FechaVencimiento = fechaVencimiento;
        this.limiteCredito = limiteCredito;
        this.FechaInicial = fechaInicial;
        this.pagosMensuales = pagosMensuales;
    }

    public Date getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        FechaVencimiento = fechaVencimiento;
    }

    public int getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(int limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public Date getFechaInicial() {
        return FechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        FechaInicial = fechaInicial;
    }

    public double getPagosMensuales() {
        return pagosMensuales;
    }

    public void setPagosMensuales(double pagosMensuales) {
        this.pagosMensuales = pagosMensuales;
    }

}
