package co.edu.udc.ejercicio28_lavadero.models.crud;

import co.edu.udc.ejercicio28_lavadero.Color;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class ComprobantePagoCrudl {
    private ArrayList<ComprobantePago> comprobantesPago = new ArrayList<>();

    public void agregar(ComprobantePago comprobante) throws Exception {
        CargarComprobantes();
        Boolean existe = comprobantesPago.stream().anyMatch(cat -> cat.getCodigo().equals(comprobante.getCodigo()));
        if(existe){
            throw new Exception("El comprobante ya existe");
        }else{
            comprobantesPago.add(comprobante);
            guardarDatos();
        }
    }

    public ComprobantePago buscar(String id) throws Exception{
        CargarComprobantes();
        ComprobantePago encontrado = comprobantesPago.stream().filter(cat -> cat.getCodigo().equals(id)).findFirst().orElse(null);
        if(encontrado == null){
            throw new Exception("Comprobante no encontrado");
        }
        return encontrado;
    }

    public void editar(ComprobantePago comprobante) throws Exception {
        CargarComprobantes();
        Boolean existe = comprobantesPago.stream().anyMatch(cat -> cat.getCodigo().equals(comprobante.getCodigo()));
        if(!existe){
            throw new Exception(Color.ROJO_BLINK+"Comprobante no existe");
        }
        ComprobantePago comprobanteEditar = comprobantesPago.stream().filter(cat -> cat.getCodigo().equals(comprobante.getCodigo())).findFirst().orElse(null);
        int index = comprobantesPago.indexOf(comprobanteEditar);
        if(comprobanteEditar == null){
            throw new Exception("Comprobante no encontrado");
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Editar Comprobante: ");
        System.out.println("1) Empresa");
        System.out.println("2) Provedor");
        System.out.println("3) Metodo de pago");
        System.out.print("Escriba la opcion: ");
        String opcion = input.nextLine();
        switch (opcion){
            case "1":
                EmpresaCrudl CrudEmpresa = new EmpresaCrudl();
                Empresa empresa = CrudEmpresa.buscar(comprobanteEditar.getEmpresa());
                System.out.println("Empresa actual: "+empresa.getNombre() + " Codigo: "+empresa.getCodigo());
                System.out.println(Color.CYAN_BOLD+"Empresas disponibles: "+Color.RESET);
                for(Empresa em: CrudEmpresa.listarTodo()){
                    System.out.println(em.getNombre() + "Codigo: "+em.getCodigo());
                }
                System.out.println("Escriba el codigo de la empresa: "+Color.BLANCO_BOLD);
                String codigoEmpresa = input.nextLine();
                Empresa nuevoEmpresa = CrudEmpresa.buscar(codigoEmpresa);
                comprobanteEditar.setEmpresa(nuevoEmpresa.getCodigo());
                comprobantesPago.set(index,comprobanteEditar);
                guardarDatos();
                break;
            case "2":
                ProveedorCrudl CrudProveedor = new ProveedorCrudl();
                Provedor provedor = CrudProveedor.buscar(comprobanteEditar.getProveedor());
                System.out.println("Provedor actual: "+provedor.getNombre()+" codigo: "+provedor.getId() + Color.RESET);
                System.out.println(Color.CYAN_BOLD+"Proveedores disponibles: "+Color.RESET);
                for(Provedor proveedor: CrudProveedor.listarTodo()){
                    System.out.println(proveedor.getNombre()+" codigo: "+proveedor.getId());
                }
                System.out.println("Escriba el codigo del provedor: "+Color.BLANCO_BOLD);
                String codigoProveedor = input.nextLine();
                if(codigoProveedor.equalsIgnoreCase(provedor.getId())){
                    throw new Exception("El provedor ya esta seleccionado");
                }
                Provedor nuevoProvedor = CrudProveedor.buscar(codigoProveedor);
                comprobanteEditar.setProveedor(nuevoProvedor.getId());
                comprobantesPago.set(index,comprobanteEditar);
                guardarDatos();
                break;
            case "3":
                System.out.println("Metodo pago actual: "+comprobanteEditar.getTipoPago());
                System.out.println("Metodos disponibles: ");
                for(MetodoPago metodoPago: MetodoPago.values()){
                    System.out.println(metodoPago.name());
                }
                System.out.println("Escriba el metodo de pago: ");
                String metodoPago = input.nextLine();
                if(metodoPago.equalsIgnoreCase(comprobanteEditar.getTipoPago().name())){
                    throw new Exception("El metodo de pago ya esta seleccionado");
                }
                metodoPago = metodoPago.substring(0,1).toUpperCase()+metodoPago.substring(1).toLowerCase();
                System.out.println("Nuevo metodo de pago: "+metodoPago);
                try{
                    MetodoPago nuevoMetodoPago = MetodoPago.valueOf(metodoPago);
                    comprobanteEditar.setTipoPago(nuevoMetodoPago);
                    comprobantesPago.set(index,comprobanteEditar);
                    guardarDatos();
                }catch (IllegalArgumentException e){
                    throw new IllegalArgumentException("Metodo de pago no existe");
                }
                break;
            default:
                throw new IllegalArgumentException("Opción "+opcion+ " no existe");
        }
    }

    public void eliminar(String id) throws Exception {
        CargarComprobantes();
        Boolean existe = comprobantesPago.stream().anyMatch(cat -> cat.getCodigo().equals(id));
        if(!existe){
            throw new Exception("Comprobante no existe");
        }else{
            comprobantesPago.removeIf(cat -> cat.getCodigo().equals(id));
            guardarDatos();
            System.out.println("Comprobante "+id+" eliminado" );
        }
    }

    public ArrayList<ComprobantePago> listarTodo() throws Exception {
        CargarComprobantes();
        return comprobantesPago;
    }

    public Integer contar() throws Exception{
        CargarComprobantes();
        return comprobantesPago.size();
    }

    public void CargarComprobantes() throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader reader = new FileReader("DB/ComprobantesDePago.json");
        Type comprobantesTypeList = new TypeToken<ArrayList<ComprobantePago>>(){}.getType();
        comprobantesPago = gson.fromJson(reader, comprobantesTypeList);
    }

    public void guardarDatos() throws IOException, InterruptedException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(comprobantesPago);
        ManejoArchivos.escribirArchivo("DB/ComprobantesDePago.json",json);
    }
}
