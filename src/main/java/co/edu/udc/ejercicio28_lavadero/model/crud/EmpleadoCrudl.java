package co.edu.udc.ejercicio28_lavadero.models.crud;

import co.edu.udc.ejercicio28_lavadero.models.entidades.Empleado;
import co.edu.udc.ejercicio28_lavadero.models.entidades.TipoID;
import co.edu.udc.ejercicio28_lavadero.util.ConsultarData;
import co.edu.udc.ejercicio28_lavadero.util.DeleteData;
import co.edu.udc.ejercicio28_lavadero.util.InsertData;
import co.edu.udc.ejercicio28_lavadero.util.UpdateData;

import java.util.ArrayList;
import java.util.Scanner;

public class EmpleadoCrudl {

    public void agregar(Empleado empleado){
        InsertData.Empleado(empleado.getNombre(),empleado.getTipoID(),empleado.getCorreo(),empleado.getTelefono(),empleado.getDireccion(),Integer.parseInt(empleado.getContrato().getId()),empleado.getIdentificacion());
    }

    public Empleado buscar(String codigo){
        return ConsultarData.Empleado(codigo);
    }

    public void editar(Empleado empleado) throws Exception {
        System.out.println("Editar Empleado");
        System.out.println("1) Nombre: "+empleado.getNombre());
        System.out.println("2) Tipo de ID: "+empleado.getTipoID());
        System.out.println("3) Correo: "+empleado.getCorreo());
        System.out.println("4) Telefono: "+empleado.getTelefono());
        System.out.println("5) Direccion: "+empleado.getDireccion());
        System.out.println("6) Contrato: "+empleado.getContrato().getId());
        System.out.println("7) Identificacion: "+empleado.getIdentificacion());

        Scanner input = new Scanner(System.in);

        System.out.println("Digite el numero de la opción: ");
        String opcion = input.nextLine();
        switch (opcion){
            case "1":
                System.out.println("Nuevo nombre: ");
                String nuevoNombre = input.nextLine();
                UpdateData.Empleado("nombre",nuevoNombre,empleado.getIdentificacion());
                break;
            case "2":
                System.out.println("Nuevo tipo de ID: ");
                for(TipoID tipo: TipoID.values()){
                    System.out.println(tipo.ordinal()+1+") "+tipo.name());
                }
                System.out.println("Seleccione una opcion: ");
                int nuevoTipoID = Integer.parseInt(input.nextLine());
                TipoID tipoID = TipoID.values()[nuevoTipoID-1];
                UpdateData.Empleado("tipoID",tipoID.name(),empleado.getIdentificacion());

                break;
            case "3":
                System.out.println("Nuevo correo: ");
                String nuevoCorreo = input.nextLine();
                UpdateData.Empleado("correo",nuevoCorreo,empleado.getIdentificacion());

                break;
            case "4":
                System.out.println("Nuevo telefono: ");
                String nuevoTelefono = input.nextLine();
                UpdateData.Empleado("telefono",nuevoTelefono,empleado.getIdentificacion());

                break;
            case "5":
                System.out.println("Nueva direccion: ");
                String nuevaDireccion = input.nextLine();
                UpdateData.Empleado("direccion",nuevaDireccion,empleado.getIdentificacion());

                break;
            case "6":
                ContratoCrudl crud = new ContratoCrudl();
                crud.editar(empleado.getContrato());
                System.out.println("Contrato editado");
                break;
            case "7":
                System.out.println("Nuevo ID: ");
                String nuevoID = input.nextLine();
                UpdateData.Empleado("identificacion",nuevoID,empleado.getIdentificacion());
                break;
            default:
                throw new Exception("Opción invalida");
        }
    }

    public void eliminar(int codigo){
        DeleteData.DeleteTable("Empleados","id",codigo);
    }

    public ArrayList<Empleado> listarTodo(){
        return ConsultarData.Empleados();
    }

    public Integer contar(){
        return listarTodo().size();
    }

}
