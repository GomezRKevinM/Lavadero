package co.edu.udc.ejercicio28_lavadero.models.crud;

import co.edu.udc.ejercicio28_lavadero.models.entidades.NotaCorrecion;
import co.edu.udc.ejercicio28_lavadero.util.InsertData;

import java.util.ArrayList;

public class NotaCorreccionCrudl {

    public void agregar(NotaCorrecion nota){
        for(NotaCorrecion notaExistente: listarTodo()){
            if(notaExistente.getCodigo().equals(nota.getCodigo())){
                throw new IllegalArgumentException("Ya existe una nota de correccion con el codigo: "+nota.getCodigo());
            }else{
                InsertData.NotaCorrecion(nota.getFechaEmision(),nota.getFechaMaximaConfirmacion(),Integer.parseInt(nota.getCodigoPedido()),nota.getObservaciones());
            }
        }
    }

    public NotaCorrecion buscar(String codigo){
        for(NotaCorrecion nota: listarTodo()){
            if(nota.getCodigo().equalsIgnoreCase(codigo)){
                return nota;
            }
        }
        throw new IllegalArgumentException("No existe nota de correccion con el codigo: "+codigo);
    }

    public void editar(NotaCorrecion nota){
        throw new IllegalArgumentException("No se puede editar una nota de correccion");
    }

    public void eliminar(String codigo){
        throw new IllegalArgumentException("No se puede eliminar una nota de correccion");
    }

    public ArrayList<NotaCorrecion> listarTodo(){
        return new ArrayList<>();
    }

    public Integer contar(){
        return listarTodo().size();
    }

}
