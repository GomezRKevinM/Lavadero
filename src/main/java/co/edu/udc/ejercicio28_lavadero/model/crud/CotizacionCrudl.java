package co.edu.udc.ejercicio28_lavadero.model.crud;

import co.edu.udc.ejercicio28_lavadero.exceptions.DocumentoException;
import co.edu.udc.ejercicio28_lavadero.models.Cotizacion;
import co.edu.udc.ejercicio28_lavadero.models.Empleado;
import co.edu.udc.ejercicio28_lavadero.models.Empresa;
import co.edu.udc.ejercicio28_lavadero.util.ConsultarData;
import co.edu.udc.ejercicio28_lavadero.util.DeleteData;
import co.edu.udc.ejercicio28_lavadero.util.InsertData;
import co.edu.udc.ejercicio28_lavadero.util.UpdateData;

import java.util.ArrayList;
import java.util.Scanner;

public class CotizacionCrudl {

    public void agregar(Cotizacion cotizacion){
        InsertData.Cotizacion(cotizacion.getFechaEmision(),cotizacion.getFechaExpiracion(),1,0,1,cotizacion.getEstado());
    }

    public Cotizacion buscar(String codigo) throws DocumentoException {
        return ConsultarData.Cotizacion(codigo);
    }

    public void editar(Cotizacion cotizacion) throws DocumentoException {
        System.out.println("Editar Cotizacion: ");
        System.out.println("1) Estado: "+cotizacion.getEstado());
        System.out.println("2) Empleado que revisa: "+cotizacion.getEmpleadoRevisa().getNombre());
        Scanner input = new Scanner(System.in);
        System.out.println("Ingrese la opcion(1-2) a editar: ");
        String opcion = input.nextLine();

        switch (opcion){
            case "1":
                System.out.println("Nuevo estado: ");
                String nuevoEstado = input.nextLine();
                if(nuevoEstado.equalsIgnoreCase(cotizacion.getEstado())){
                    throw new IllegalArgumentException("El estado no puede ser igual al anterior");
                }
                UpdateData.Cotizacion("estado",nuevoEstado,Integer.parseInt(cotizacion.getCodigo()));
            break;
            case "2":
                System.out.println("Empleado que revisa: ");
                System.out.println("  ID  |   Nombre");
                Empresa empresa = cotizacion.getEmpresa();
                for(Empleado empleado: empresa.getEmpleados()){
                    System.out.println(empleado.getId()+" "+empleado.getNombre());
                }
                System.out.println("Digite el ID del empleado que revisa: ");
                String idEmpleado = input.nextLine();
                UpdateData.Cotizacion("empleado_revisa",idEmpleado,Integer.parseInt(cotizacion.getCodigo()));
                System.out.println("Empleado que revisa: "+ConsultarData.Empleado(idEmpleado).getNombre());
                break;
        }
    }

    public void eliminar(String codigo){
        DeleteData.DeleteTable("Cotizaciones","id",Integer.parseInt(codigo));
    }

    public ArrayList<Cotizacion> listarTodo() throws DocumentoException {
        return ConsultarData.Cotizaciones();
    }

    public Integer contar() throws DocumentoException {
        return listarTodo().size();
    }
}
