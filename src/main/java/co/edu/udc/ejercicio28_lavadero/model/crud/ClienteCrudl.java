package co.edu.udc.ejercicio28_lavadero.model.crud;

import co.edu.udc.ejercicio28_lavadero.exceptions.DocumentoException;
import co.edu.udc.ejercicio28_lavadero.models.Cliente;
import co.edu.udc.ejercicio28_lavadero.util.ConsultarData;
import co.edu.udc.ejercicio28_lavadero.util.DeleteData;
import co.edu.udc.ejercicio28_lavadero.util.InsertData;
import co.edu.udc.ejercicio28_lavadero.util.UpdateData;

import java.util.ArrayList;
import java.util.Scanner;

public class ClienteCrudl {

    public void agregar(Cliente cliente) throws Exception {
        InsertData.Cliente(cliente.getNombre(),cliente.getTipoID().name(), cliente.getIdentificacion().getValor(), cliente.getCorreo(),cliente.getTelefono(),cliente.getDireccion());
    }

    public ArrayList<Cliente> buscar(String identificacion) throws Exception {
        try{
            return ConsultarData.Cliente(identificacion);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void editar(Cliente cliente) throws Exception {
        Scanner input = new Scanner(System.in);
        for(Cliente cli: listarTodo()){
            if(cli.getIdentificacion().equals(cliente.getIdentificacion())){
                int index = listarTodo().indexOf(cli);
                System.out.println("Editar Cliente: ");
                System.out.println("1) Nombre: "+cli.getNombre());
                System.out.println("2) ID: "+cli.getIdentificacion());
                System.out.println("3) Correo: "+cli.getCorreo());
                System.out.println("4) Telefono: "+cli.getTelefono());
                System.out.println("5) Dirección: "+cli.getDireccion());
                System.out.println("Escriba la opción que desea editar: ");
                String opcion = input.nextLine();
                switch (opcion){
                    case "1":
                        System.out.println("Nuevo nombre: ");
                        String nuevoNombre = input.nextLine();
                        if(nuevoNombre.equalsIgnoreCase(cli.getNombre())){
                            throw new Exception("El nombre no puede ser igual al anterior");
                        }
                        cliente.setNombre(nuevoNombre);
                        UpdateData.UpdateData("Clientes","nombre",nuevoNombre,"identificacion",cliente.getIdentificacion().getValor());
                        break;
                    case "2":
                        System.out.println("Nuevo ID: ");
                        String nuevoID = input.nextLine();
                        if(nuevoID.equalsIgnoreCase(cli.getIdentificacion().getValor())){
                            throw new Exception("El ID no puede ser igual al anterior");
                        }
                        UpdateData.UpdateData("Clientes","tipo_id",nuevoID,"identificacion",cliente.getIdentificacion().getValor());
                        break;
                    case "3":
                        System.out.println("Nuevo correo: ");
                        String nuevoCorreo = input.nextLine();
                        if(nuevoCorreo.equalsIgnoreCase(cli.getCorreo())){
                            throw new Exception("El correo no puede ser igual al anterior");
                        }
                        UpdateData.UpdateData("Clientes","correo",nuevoCorreo,"identificacion",cliente.getIdentificacion().getValor());
                        break;
                    case "4":
                        System.out.println("Nuevo telefono: ");
                        String nuevoTelefono = input.nextLine();
                        if(nuevoTelefono.equalsIgnoreCase(cli.getTelefono())){
                            throw new Exception("El telefono no puede ser igual al anterior");
                        }
                        UpdateData.UpdateData("Clientes","telefono",nuevoTelefono,"identificacion",cliente.getIdentificacion().getValor());
                        break;
                    case "5":
                        System.out.println("Nueva Dirección: ");
                        String nuevaDireccion = input.nextLine();
                        if(nuevaDireccion.equalsIgnoreCase(cli.getDireccion())){
                            throw new Exception("La dirección no puede ser igual al anterior");
                        }
                        UpdateData.UpdateData("Clientes","direccion",nuevaDireccion,"identificacion",cliente.getIdentificacion().getValor());
                        break;
                    default:
                        throw new IllegalArgumentException("Opción "+opcion+" no existe ✖️");
                }
                input.close();
                break;
            }
        }
    }

    public void eliminar(String identificacion) throws Exception {
        DeleteData.DeleteTable("Clientes","identificacion",Integer.parseInt(identificacion));
    }

    public ArrayList<Cliente> listarTodo() throws DocumentoException {
        return ConsultarData.Clientes();
    }

    public Integer contar() throws DocumentoException {
        return listarTodo().size();
    }
    
}
