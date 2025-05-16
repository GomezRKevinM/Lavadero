package co.edu.udc.ejercicio28_lavadero.modelo.crud;
import co.edu.udc.ejercicio28_lavadero.Color;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.AreaDeTrabajo;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Cubiculo;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.ManejoArchivos;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.awt.geom.Area;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AreaDeTrabajoCRUD {

    public static ArrayList<AreaDeTrabajo> listaAreas = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {

    }

    public static void agregar(AreaDeTrabajo area){
        try{
            cargarAreas();
            for (AreaDeTrabajo areaDeTrabajo : listaAreas) {
                if(area.getIdAreaDeTrabajo().equals(areaDeTrabajo.getIdAreaDeTrabajo())){
                    throw new Exception("El area de trabajo ya existe");
                }
            }
            listaAreas.add(area);
            guardarDatos();
            System.out.println("Area de trabajo agregada: "+area.getIdAreaDeTrabajo());
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    public AreaDeTrabajo buscar(String id) throws Exception {
        AreaDeTrabajo area = null;
        for (AreaDeTrabajo areaDeTrabajo : listaAreas) {
            if(areaDeTrabajo.getIdAreaDeTrabajo().equals(id)){
                return areaDeTrabajo;
            }
        }
        throw new Exception(Color.ROJO_BLINK+"Area no encontrada 🔍"+Color.RESET);
    }

    public void editar(AreaDeTrabajo area) throws IOException, InterruptedException {
        cargarAreas();
        int index = 0;
        Boolean encontrada = false;
        for(AreaDeTrabajo a : listaAreas){
            if(a.getIdAreaDeTrabajo().equals(area.getIdAreaDeTrabajo())){
                System.out.println("Area encontrada: "+a.getIdAreaDeTrabajo());
                index = listaAreas.indexOf(a);
                encontrada = true;
                break;
            }
        }
        if(!encontrada){
            throw new IllegalCallerException("Area no existe ✖️");
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Editar Area: "+area.getNombreAreaDeTrabajo());
        System.out.println("1) ID:" + area.getIdAreaDeTrabajo());
        System.out.println("2) Descripción: "+ area.getDescripcion());
        System.out.print("3) Cubiculos: [ ");
        for(Cubiculo c: area.getCubiculos()){
            System.out.print(c.getIdCubiculo()+" ");
        }
        System.out.println("]");
        System.out.println("Eliga la opción que desea editar: (1-3) ");
        String editar = input.nextLine();

        switch (editar){
            case "1":
                System.out.println(Color.CYAN_BOLD+"Nuevo ID: "+Color.BLANCO_BOLD);
                String nuevoID = input.nextLine();
                area.setIdAreaDeTrabajo(nuevoID);
                listaAreas.set(index,area);
                guardarDatos();
                break;
            case "2":
                System.out.println(Color.CYAN_BOLD+"Nueva descripción: "+Color.BLANCO_BOLD);
                String nuevaDescripcion = input.nextLine();
                area.setDescripcion(nuevaDescripcion);
                listaAreas.set(index,area);
                guardarDatos();
                break;
            case "3":
                System.out.println(Color.CYAN_BOLD+"1) Agregar Cubiculo ");
                System.out.println(Color.CYAN_BOLD+"2) Eliminar Cubiculo ");
                System.out.println(Color.CYAN_BOLD+"3) Atrás "+Color.BLANCO_BOLD);
                String opcion = input.nextLine();
                switch (opcion){
                    case "1":
                        System.out.println("Ingrese el ID del Cubiculo: ");
                        String id = input.nextLine();
                        System.out.println("Ingrese el nombre del Cubiculo: ");
                        String nombreCubiculo = input.nextLine();
                        Cubiculo creado = new Cubiculo(id,nombreCubiculo);
                        area.addCubiculo(creado);
                        listaAreas.set(index,area);
                        guardarDatos();
                        break;
                    case "2":
                        System.out.println("Cubiculos: ");
                        for(Cubiculo c: area.getCubiculos()){
                            System.out.println(Color.CYAN_BLINK+"ID: "+Color.BLANCO_BOLD+c.getIdCubiculo()+Color.CYAN_BOLD+" Nombre: "+Color.BLANCO_BOLD+c.getNombre());
                        }
                        System.out.println("Ingrese el ID del Cubiculo a eliminar: ");
                        String idEliminar = input.nextLine();
                        for(Cubiculo c: area.getCubiculos()){
                            if(c.getIdCubiculo().equals(idEliminar)){
                                area.removeCubiculo(c);
                                listaAreas.set(index,area);
                                guardarDatos();
                                System.out.println("Cubiculo eliminado: "+c.getNombre()+" ID: "+c.getIdCubiculo());
                                break;
                            }
                        }
                        throw new IllegalCallerException("No se encontro el Cubiculo a eliminar");
                    case "3":
                        editar(area);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        input.close();
    }

    public void eliminar(String codigo) throws FileNotFoundException {
       try{
            cargarAreas();
            Boolean encontrada = false;
            for(AreaDeTrabajo a: listaAreas){
                if(a.getIdAreaDeTrabajo().equals(codigo)){
                    listaAreas.remove(a);
                    encontrada = true;
                    break;
                }
            }
            if(!encontrada){
                throw new Exception("Area no existe");
            }
        }catch (Exception e){
           throw new RuntimeException("Error: "+ e.getMessage());
       }
    }

    public ArrayList<AreaDeTrabajo> listarTodo() throws FileNotFoundException {
        cargarAreas();
        return listaAreas;
    }

    public Integer contar() throws FileNotFoundException {
        cargarAreas();
        return listaAreas.size();
    }

    public static void cargarAreas() throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader reader = new FileReader("DB/Areas.json");
        Type AreaType = new TypeToken<ArrayList<AreaDeTrabajo>>(){}.getType();
        listaAreas = gson.fromJson(reader, AreaType);
    }

    public static void guardarDatos() throws IOException, InterruptedException {
        Gson gson = new Gson();
        String json = gson.toJson(listaAreas);
        ManejoArchivos.escribirArchivo("DB/Areas.json", json);
        System.out.println("Areas guardadas en archivo");
    }
}
