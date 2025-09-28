package co.edu.udc.ejercicio28_lavadero.models.crud;

import co.edu.udc.ejercicio28_lavadero.models.entidades.Catalogo;
import co.edu.udc.ejercicio28_lavadero.models.entidades.ManejoArchivos;
import co.edu.udc.ejercicio28_lavadero.util.InsertData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CatalogoCrudl {

    public ArrayList<Catalogo> listaCatalogos = new ArrayList<>();

    public void agregar(Catalogo catalogo) throws Exception {
        InsertData.Catalogo(catalogo.getIdCatalogo());
    }

    public Catalogo buscar(String id)throws Exception{
        CargarCatalogos();
        for(Catalogo c: listaCatalogos){
            if(c.getIdCatalogo().equals(id)){
                return c;
            }
        }
        throw new Exception("Catalogo no encontrado");
    }

    public void editar(Catalogo catalogo) throws Exception {
        CargarCatalogos();
        int index = 0;
        Boolean encontrado = false;
        for(Catalogo c: listaCatalogos){
            if(c.getIdCatalogo().equals(catalogo.getIdCatalogo())){
                encontrado = true;
                System.out.println("Editar Catalogo: "+c.getIdCatalogo());
                index = listaCatalogos.indexOf(c);

                System.out.println("1) Editar Productos");
                System.out.println("2) Editar Servicios");
                System.out.println("3) Editar Categorias");
            }
        }
        if(!encontrado){
            throw new Exception("Catalogo "+ catalogo.getIdCatalogo()+" no existe");
        }
    }

    public void eliminar(String id) throws Exception {
        CargarCatalogos();
        Boolean encontrado = false;
        for(Catalogo c: listaCatalogos){
            if(c.getIdCatalogo().equals(id)){
                encontrado = true;
                listaCatalogos.remove(c);
                System.out.println("Catalogo eliminado: "+c.getIdCatalogo());
                guardarDatos();
                break;
            }
        }
        if(!encontrado){
            throw new Exception("Catalogo "+ id+" no existe");
        }
    }

    public ArrayList<Catalogo> listarTodo() throws Exception {
        CargarCatalogos();
        return listaCatalogos;
    }

    public Integer contar() throws Exception {
        CargarCatalogos();
        Integer contador = listaCatalogos.size();
        return contador;
    }

    public void CargarCatalogos() throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader reader = new FileReader("DB/Catalogos.json");
        Type catalogoTypeList = new TypeToken<ArrayList<Catalogo>>(){}.getType();
        listaCatalogos = gson.fromJson(reader, catalogoTypeList);
    }

    public void guardarDatos() throws Exception{
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(listaCatalogos);
        ManejoArchivos.escribirArchivo("DB/Catalogos.json",json);
    }
}
