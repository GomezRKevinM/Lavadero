package co.edu.udc.ejercicio28_lavadero.models;

import java.util.ArrayList;
import java.util.List;

import co.edu.udc.ejercicio28_lavadero.Color;

public class Empresa {
    private String nombre;
    private String codigo;
    private InformacionPago informacionDePago = new InformacionPago();
    private List<AreaDeTrabajo> areasDeTrabajo = new ArrayList<>();
    private List<NotaCorrecion> notasCorrecion = new ArrayList<>();
    private List<ComprobantePago> comprobantes = new ArrayList<>();
    private List<Cotizacion> cotizaciones = new ArrayList<>();
    private List<Empleado> empleados = new ArrayList<>();
    private Catalogo catalogo = new Catalogo();
    private List<Bodega> bodegas = new ArrayList<>();
    private List<Provedor> proveedores = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();

    public Empresa(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public Empresa(){
        nombre = "Empresa 1";
        codigo = "E001";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public InformacionPago getInformacionDePago() {
        return informacionDePago;
    }

    public void setInformacionDePago(InformacionPago informacionDePago) {
        this.informacionDePago = informacionDePago;
    }

    public List<Empleado> getEmpleados(){
        return empleados;
    }

    public void agregarEmpleado(Empleado contratado){
        empleados.add(contratado);
    }

    public void eliminarEmpleado(Empleado empleado){
        empleados.remove(empleado);
    }

    public Empleado getEmpleado(String identificacion){
        for (Empleado empleado : empleados) {
            if (empleado.getIdentificacion().equals(identificacion)) {
                System.out.println("Nombre: "+empleado.getNombre());
                System.out.println("Id: "+empleado.getIdentificacion());
                System.out.println("Correo: "+empleado.getCorreo());
                System.out.println("Direccion: "+empleado.getDireccion());
                System.out.println("Telefono: "+empleado.getTelefono());
                return empleado;
            }
        }
        return null; 
    }

    public List<Bodega> getBodegas() {
        return bodegas;
    }

    public void agregarBodega(Bodega bodega){
        bodegas.add(bodega);
    }

    public void eliminarBodega(Bodega bodega){
        bodegas.remove(bodega);
    }

    public List<Provedor> getProveedores() {
        return proveedores;
    }

    public void agregarProveedor(Provedor provedor) {
        proveedores.add(provedor);
    }

    public void eliminarProveedor(Provedor provedor){
        proveedores.remove(provedor);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void agregarCliente(Cliente cliente){
        this.clientes.add(cliente);
    }

    public Cliente buscarCliente(String identificacion){

        for(Cliente cliente:clientes){
            if(cliente.getIdentificacion().equals(identificacion)){
                System.out.println("Nombre: "+cliente.getNombre());
                System.out.println("Id: "+cliente.getIdentificacion());
                System.out.println("Correo: "+cliente.getCorreo());
                System.out.println("Direccion: "+cliente.getDireccion());
                System.out.println("Telefono: "+cliente.getTelefono());
                return cliente;
            }else{
                continue;
            }
        }
        return null;
    }

    public void eliminarCliente(Cliente cliente){
        clientes.remove(cliente);
    }

    public List<Contrato> getContratos() {
        List<Contrato> contratos = new ArrayList<>();
        for (Empleado empleado : empleados) {
            contratos.add(empleado.getContrato());
        }
        return contratos;
    }

    public List<Cotizacion> getCotizaciones() {
        return cotizaciones;
    }

    public List<AreaDeTrabajo> getAreasDeTrabajo() {
        return areasDeTrabajo;
    }

    public List<NotaCorrecion> getNotasCorrecion() {
        return notasCorrecion;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }


    public List<ComprobantePago> getComprobantes(){
        return comprobantes;
    }

    public void getProveedoresDeProductosAgotados(){
        for(Producto producto: catalogo.getProductos()){
            if(producto.getStock() <= producto.getAlerta()){
                for(Provedor provedor : proveedores){
                    if(Integer.parseInt(provedor.getId()) == producto.getCodigoDelProveedor()){
                        System.out.println(Color.BLANCO_BOLD+"El proveedor "+Color.AZUL_BOLD+ provedor.getNombre()+Color.BLANCO_BOLD+" provee el producto "+Color.AMARILLO_BOLD+producto.getNombreProducto()+Color.BLANCO_BOLD+" de codigo "+Color.PURPLE_BOLD+producto.getCodigo()+Color.RESET);
                    }
                    
                }
            }  
        }
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}