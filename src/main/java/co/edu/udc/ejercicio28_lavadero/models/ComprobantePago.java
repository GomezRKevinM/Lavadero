package co.edu.udc.ejercicio28_lavadero.models;

import co.edu.udc.ejercicio28_lavadero.enums.MetodoPago;

import java.util.Date;

public class ComprobantePago {
    String codigo;
    Date fechaPago = new Date(System.currentTimeMillis());
    String codigoEmpresa;
    String codigoProveedor;
    double valorAPagar;
    double valorPagado;
    CuentaBancaria cuentaBancariaDeLaEmpresa;
    MetodoPago tipoPago;

    public ComprobantePago(){
        codigo = "0001";
        fechaPago = new Date(System.currentTimeMillis());
        valorPagado = 20000.50;
        valorAPagar = 20000.50;
        tipoPago = MetodoPago.Efectivo;
        codigoEmpresa = "7502420013";
        codigoProveedor = "NIT-1001";
    }

    public ComprobantePago(Date fechaPago, double valorAPagar, double valorPagado, MetodoPago tipoPago) {
        this.fechaPago = fechaPago;
        this.valorPagado = valorPagado;
        this.valorAPagar = valorAPagar;
        this.tipoPago = tipoPago;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setEmpresa(String empresa) {
        this.codigoEmpresa = empresa;
    }

    public void setProveedor(String proveedor) {
        this.codigoProveedor = proveedor;
    }

    public double getValorAPagar() {
        return valorAPagar;
    }

    public void setValorAPagar(double valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public String getEmpresa() {
        return codigoEmpresa;
    }

    public String getProveedor() {
        return codigoProveedor;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(double valorPagado) {
        this.valorPagado = valorPagado;
    }

    public MetodoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(MetodoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public void imprimirComprobantePago() {
        System.out.println("Fecha de pago: " + this.getFechaPago() + " Valor pagado: " + this.getValorPagado() + " Tipo de pago: " + this.getTipoPago());
    }
}
