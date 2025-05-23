package co.edu.udc.ejercicio28_lavadero.modelo.crud;

import co.edu.udc.ejercicio28_lavadero.Color;
import co.edu.udc.ejercicio28_lavadero.Principal;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Cargo;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Contrato;
import co.edu.udc.ejercicio28_lavadero.util.ConsultarData;
import co.edu.udc.ejercicio28_lavadero.util.DeleteData;
import co.edu.udc.ejercicio28_lavadero.util.InsertData;
import co.edu.udc.ejercicio28_lavadero.util.UpdateData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ContratoCrudl {
    private ArrayList<Contrato> listaContratos = new ArrayList<>();

    public void agregar(Contrato contrato){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(contrato);
        InsertData.Contrato(contrato.getFechaInicio().toString(),contrato.getSueldoBase(),contrato.getCargo().name(),contrato.getFechaFinal().toString(),contrato.getHorario(),Integer.parseInt(contrato.getContratado()),Integer.parseInt(contrato.getContratanteID()),json);
    }

    public Contrato buscar(String codigo){
        return ConsultarData.Contrato(Integer.parseInt(codigo));
    }

    public void editar(Contrato contrato) throws Exception {
        Contrato contratoEditar = buscar(contrato.getId());
        Scanner input = new Scanner(System.in);
        System.out.println("Editar contrato: ");
        System.out.println("1) Fecha final: "+contratoEditar.getFechaFinal());
        System.out.println("2) SueldoBase: "+contratoEditar.getSueldoBase());
        System.out.println("3) Horario: "+contratoEditar.getHorario());
        System.out.println("4) Clausulas");
        System.out.println("5) Cargo: "+contratoEditar.getCargo());

        String opcion = input.nextLine();
        switch (opcion){
            case "1":
                Principal.limpiarPantalla();
                System.out.println("Nueva fecha final (año/mes/dia): ");
                Date fechaFinal = new Date(input.nextLine());
                System.out.println("nueva fecha de cierre: "+ fechaFinal);
                UpdateData.UpdateData("Contratos","fecha_final",fechaFinal.toString(),"id",contratoEditar.getId());
                break;
            case "2":
                Principal.limpiarPantalla();
                System.out.println("Nuevo Sueldo base: ");
                Double sueldoBase = Double.parseDouble(input.nextLine());
                UpdateData.UpdateData("Contratos","sueldo_base",sueldoBase.toString(),"id",contratoEditar.getId());
                break;
            case "3":
                Principal.limpiarPantalla();
                System.out.println("Nuevo Horario: ");
                String horario = input.nextLine();
                UpdateData.UpdateData("Contratos","horario",horario,"id",contratoEditar.getId());
                break;
            case "4":
                Principal.limpiarPantalla();
                System.out.println("1) Agregar clausula");
                System.out.println("2) Eliminar clausula");
                String clausulaOpcion = input.nextLine();
                List<String> clausulas = contrato.getClausulas();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                switch (clausulaOpcion){
                    case "1":
                        System.out.println("Clausula: ");
                        String clausula = input.nextLine();
                        clausulas.add(clausula);
                        String json = gson.toJson(clausulas);
                        UpdateData.UpdateData("Contratos","clausulas","id",json,contratoEditar.getId());
                    break;
                    case "2":
                        System.out.println(Color.CYAN_BOLD+"Clausulas actuales"+Color.RESET);
                        for(String clausulaActual: clausulas){
                            System.out.println("---------------------------------------");
                            System.out.println(clausulas.indexOf(clausulaActual)+1+") "+clausulaActual);
                            System.out.println("---------------------------------------");
                        }
                        System.out.println("Clausula a eliminar: ");
                        int clausulaEliminar = Integer.parseInt(input.nextLine())-1;
                        clausulas.remove(clausulaEliminar);
                        json = gson.toJson(clausulas);
                        UpdateData.UpdateData("Contratos","clausulas","id",json,contratoEditar.getId());
                    break;
                    default:
                        System.out.println("Opción invalida");
                        break;
                }
                break;
            case "5":
                System.out.println("Elige un nuevo cargo");
                for(Cargo cargo: Cargo.values()){
                    System.out.println(cargo.ordinal()+1+") "+cargo.name());
                }
                int cargoOpcion = Integer.parseInt(input.nextLine())-1;
                String nuevoCargo = Cargo.values()[cargoOpcion].name();
                if(contratoEditar.getCargo().name().equalsIgnoreCase(nuevoCargo)){
                    throw new Exception("Has seleccionado el mismo cargo anterior");
                }else{
                    UpdateData.UpdateData("contratos","cargo",nuevoCargo,"id",contratoEditar.getId());
                }
                break;
            default:
                throw new Exception("Opción invalida ✖️");
        }
        input.close();
        System.out.println("Contrato editado: "+contratoEditar.getId());
        System.out.println("Información del contrato editado: ");
        System.out.println("ID: "+contratoEditar.getId());
        System.out.println("Fecha de inicio: "+contratoEditar.getFechaInicio());
        System.out.println("Fecha final: "+contratoEditar.getFechaFinal());
        System.out.println("Sueldo base: "+contratoEditar.getSueldoBase());
        System.out.println("Horario: "+contratoEditar.getHorario());
        System.out.println("Clausulas: ");
        for(String clausula: contratoEditar.getClausulas()){
            System.out.println(Color.AZUL_BLINK+contratoEditar.getClausulas().indexOf(clausula)+1+"."+Color.BLANCO_BOLD+clausula);
        }

    }

    public void eliminar(String codigo) throws Exception {
        DeleteData.DeleteTable("Contratos","id",Integer.parseInt(codigo));
    }

    public ArrayList<Contrato> listarTodo() {
        return ConsultarData.Contratos();
    }

    public Integer contar() throws Exception{
        return listaContratos.size();
    }
}
