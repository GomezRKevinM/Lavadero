package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import co.edu.udc.ejercicio28_lavadero.Color;

public class CuentaBancaria {
    private String numeroCuenta;
    private String banco;
    private String tipoCuenta;
    private double saldoActual;
    private String titular;

    public CuentaBancaria(String numeroCuenta, String banco, String tipoCuenta, double saldoActual) {
        this.numeroCuenta = numeroCuenta;
        this.banco = banco;
        this.tipoCuenta = tipoCuenta;
        this.saldoActual = saldoActual;
    }

    public CuentaBancaria() {
        numeroCuenta = "3215970852";
        banco = "Banco de la Nacion Argentina";
        tipoCuenta = "Corriente";
        saldoActual = 1000000000.00;
        titular = "Kevin Gomez";
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public void showInformacionCuenta() {
        System.out.println(Color.BLANCO_BOLD+"Numero de cuenta: " + Color.AZUL_BLINK+ numeroCuenta);
        System.out.println(Color.BLANCO_BOLD+"Banco: "+ Color.AZUL_BLINK + banco);
        System.out.println(Color.BLANCO_BOLD+"Tipo de cuenta: "+ Color.AZUL_BLINK + tipoCuenta);
        System.out.println(Color.BLANCO_BOLD+"Titular: "+ Color.AZUL_BLINK + titular+Color.RESET);
    }
}
