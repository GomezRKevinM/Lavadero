package co.edu.udc.ejercicio28_lavadero.model.crud;

import co.edu.udc.ejercicio28_lavadero.models.Categoria;
import co.edu.udc.ejercicio28_lavadero.util.ConsultarData;
import co.edu.udc.ejercicio28_lavadero.util.DeleteData;
import co.edu.udc.ejercicio28_lavadero.util.InsertData;
import co.edu.udc.ejercicio28_lavadero.util.UpdateData;


import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaCrudl {

    private ArrayList<Categoria> listaCategorias = new ArrayList<>();

    public void agregar(Categoria categoria){
        InsertData.Categoria(categoria);
    }

    public Categoria buscar(int codigo){
        return ConsultarData.Categoria(String.valueOf(codigo));
    }

    public void editar(Categoria categoria) throws Exception {
        Categoria categoriaEditar = ConsultarData.Categoria(categoria.getCodigo());
        if(categoriaEditar != null){
            UpdateData.Categoria(categoria);
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

    public Integer contarItems(String codigo){
        try {
            return ConsultarData.CategoriaItems(codigo);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
