package co.edu.udc.ejercicio28_lavadero.models;

import java.util.ArrayList;
import java.util.List;

import co.edu.udc.ejercicio28_lavadero.Color;

public class InformacionPago {
    private int id;
    private List<MetodoPago> metodos;
    private String direccion;
    private String telefono;
    private String email;
    private List<CuentaBancaria> cuentas = new ArrayList<>();

    public InformacionPago(int id,List<MetodoPago> metodos, String direccion, String telefono, String email, CuentaBancaria cuenta) {
        this.metodos = metodos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.cuentas.add(cuenta);
    }

    public InformacionPago(){
        id=0;
        metodos = new ArrayList<>();
        direccion = "";
        telefono = "";
        email = "";
        cuentas.add(new CuentaBancaria());
    }

    public int getId() {
        return id;
    }

    public List<MetodoPago> getMetodos() {
        return metodos;
    }

    public void setMetodos(List<MetodoPago> metodos) {
        this.metodos = metodos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CuentaBancaria> getCuentas() {        
        return cuentas;
    }

    public void setCuentas(List<CuentaBancaria> cuentas) {
        this.cuentas = cuentas;
    }

    public void agregarCuentas(CuentaBancaria cuenta) {
        this.cuentas.add(cuenta);
    }

    public void eliminarCuentas(CuentaBancaria cuenta) {
        this.cuentas.remove(cuenta);
    }

    public void modificarCuentas(CuentaBancaria cuenta, CuentaBancaria nuevaCuenta) {
        this.cuentas.set(this.cuentas.indexOf(cuenta), nuevaCuenta);
    }

    public void buscarCuentas(CuentaBancaria cuenta) {
        for (CuentaBancaria cuenta1 : cuentas) {
            if (cuenta1.equals(cuenta)) {
                System.out.println("Cuenta encontrada: " + cuenta1);
            }
        }
    }

    public void showInformacionPago() {
        System.out.println(Color.PURPLE_BOLD+"Información de pago: ");
        System.out.println(Color.BLANCO_BOLD+"Metodos de pago: "+Color.AZUL_BLINK + metodos);
        System.out.println(Color.BLANCO_BOLD+"Direccion: "+Color.AZUL_BLINK + direccion);
        System.out.println(Color.BLANCO_BOLD+"Telefono: "+Color.AZUL_BLINK + telefono);
        System.out.println(Color.BLANCO_BOLD+"Email: "+Color.AZUL_BLINK + email);
        for(CuentaBancaria cuenta:cuentas){
            cuenta.showInformacionCuenta();
        }   
    }

}
