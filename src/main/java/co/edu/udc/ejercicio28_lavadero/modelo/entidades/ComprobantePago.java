package co.edu.udc.ejercicio28_lavadero.modelo.entidades;

import java.util.Date;

public class ComprobantePago {
    String codigo;
    Date fechaPago = new Date(System.currentTimeMillis());
    Empresa empresa;
    Proveedor proveedor;
    double valorAPagar;
    double valorPagado;
    CuentaBancaria cuentaBancariaDeLaEmpresa = empresa.getInformacionDePago().getCuentas().get(0);
    MetodoPago tipoPago;

    public ComprobantePago(Date fechaPago2, double valorAPagar, double valorPagado, MetodoPago tipoPago) {
        this.fechaPago = fechaPago2;
        this.valorAPagar = valorAPagar;
        this.tipoPago = tipoPago;
        
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public Proveedor getProveedor() {
        return proveedor;
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
