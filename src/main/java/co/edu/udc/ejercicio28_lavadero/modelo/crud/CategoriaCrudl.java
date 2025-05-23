package co.edu.udc.ejercicio28_lavadero.modelo.crud;

import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Categoria;
import co.edu.udc.ejercicio28_lavadero.util.ConsultarData;
import co.edu.udc.ejercicio28_lavadero.util.DeleteData;
import co.edu.udc.ejercicio28_lavadero.util.InsertData;
import co.edu.udc.ejercicio28_lavadero.util.UpdateData;


import java.util.ArrayList;
import java.util.Scanner;

public class CategoriaCrudl {

    private ArrayList<Categoria> listaCategorias = new ArrayList<>();

    public void agregar(Categoria categoria){
        InsertData.Categoria(categoria.getNombre(),categoria.getIcono());
    }

    public Categoria buscar(int codigo){
        return ConsultarData.Categoria(String.valueOf(codigo));
    }

    public void editar(Categoria categoria) throws Exception {
        Categoria categoriaEditar = ConsultarData.Categoria(categoria.getCodigo());

        Scanner input = new Scanner(System.in);
        System.out.println("Editar categoria: ");
        System.out.println("1) Nombre: "+categoriaEditar.getNombre());
        System.out.println("2) Icono: "+categoriaEditar.getIcono());
        String opcion = input.nextLine();
        switch (opcion){
            case "1":
                System.out.println("Nuevo nombre: ");
                String nuevoNombre = input.nextLine();
                if(nuevoNombre.equalsIgnoreCase(categoriaEditar.getNombre())){
                    throw new Exception("El nombre no puede ser igual al anterior");
                }
                UpdateData.Categoria("nombre",categoriaEditar.getNombre(),Integer.parseInt(categoriaEditar.getCodigo()));
                break;
            case "2":
                System.out.println("Nuevo icono: ");
                String nuevoIcono = input.nextLine();
                if(nuevoIcono.equalsIgnoreCase(categoriaEditar.getIcono())){
                    throw new Exception("El icono no puede ser igual al anterior");
                }
                UpdateData.Categoria("icono",categoriaEditar.getIcono(),Integer.parseInt(categoriaEditar.getCodigo()));
                break;
            default:
                System.out.println("Opción "+opcion+" no existe");
                editar(categoria);
                break;
        }
    }

    public void eliminar(String codigo){
        DeleteData.DeleteTable("Categorias","codigo",Integer.parseInt(codigo));
    }

    public ArrayList<Categoria> listarTodo(){
        return ConsultarData.Categorias();
    }

    public Integer contar(){
        return ConsultarData.Categorias().size();
    }


}
