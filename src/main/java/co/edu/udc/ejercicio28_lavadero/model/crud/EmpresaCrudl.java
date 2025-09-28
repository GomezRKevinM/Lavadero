package co.edu.udc.ejercicio28_lavadero.models.crud;

import co.edu.udc.ejercicio28_lavadero.util.ConsultarData;
import co.edu.udc.ejercicio28_lavadero.util.DeleteData;
import co.edu.udc.ejercicio28_lavadero.util.InsertData;
import co.edu.udc.ejercicio28_lavadero.util.UpdateData;

import java.util.ArrayList;
import java.util.Scanner;

public class EmpresaCrudl {
    private ArrayList<Empresa> listaEmpresas = new ArrayList<>();

    public void agregar(Empresa empresa){
        InsertData.Empresa(empresa.getNombre(),Integer.parseInt(empresa.getCatalogo().getIdCatalogo()),empresa.getInformacionDePago().getId());
    }

    public Empresa buscar(String codigo) throws Exception {
        return ConsultarData.Empresa(Integer.parseInt(codigo));
    }

    public void editar(Empresa empresa) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Editar Empresa: " + empresa.getNombre());
        System.out.println("1) cambiar nombre");
        System.out.println("2) cambiar catalogo");
        System.out.println("3) cambiar informacion de pago");
        String opcion = input.nextLine();
        switch (opcion) {
            case "1":
                System.out.println("Nuevo nombre: ");
                String nombre = input.nextLine();
                UpdateData.Empresa("nombre",nombre,Integer.parseInt(empresa.getCodigo()));
                break;
            case "2":
                System.out.println("Nuevo catalogo: ");
                for(Catalogo cat : ConsultarData.catalogos()){
                    System.out.println(Integer.parseInt(cat.getIdCatalogo())+1+") nombre: "+cat.getNombre());
                }
                System.out.print("Seleccione una opcion: ");
                String catalogo = input.nextLine();
                UpdateData.Empresa("catalogo",catalogo,Integer.parseInt(empresa.getCodigo()));
                break;
            case "3":
                System.out.println("Nuevo informacion de pago: ");
                for(InformacionPago info: ConsultarData.InformacionDePagos()){
                    System.out.println(info.getId()+1+") cuentas bancarias: "+info.getCuentas().size());
                }
                System.out.print("Seleccione una opcion: ");
                String informacionPago = input.nextLine();
                UpdateData.Empresa("informacionPago",informacionPago,Integer.parseInt(empresa.getCodigo()));
                break;
            default:
                throw new Exception("Opcion no valida");
        }
    }

    public void eliminar(String codigo) throws Exception {
        DeleteData.DeleteTable("Empresas","id",Integer.parseInt(codigo));
    }

    public ArrayList<Empresa> listarTodo() throws Exception {
        return ConsultarData.Empresas();
    }

    public Integer contar() throws Exception {
        return listaEmpresas.size();
    }
}
