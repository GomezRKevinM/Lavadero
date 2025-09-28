package co.edu.udc.ejercicio28_lavadero.models.crud;

import co.edu.udc.ejercicio28_lavadero.models.entidades.Provedor;
import co.edu.udc.ejercicio28_lavadero.util.ConsultarData;
import co.edu.udc.ejercicio28_lavadero.util.InsertData;

import java.util.ArrayList;

public class ProveedorCrudl {
    private ArrayList<Provedor> listaProveedores = new ArrayList<>();

    public void agregar(Provedor provedor) throws Exception {
        InsertData.Provedor(provedor.getNombre(),provedor.getInformacionPago().getId());
    }

    public Provedor buscar(String id) throws Exception {
        return ConsultarData.Provedor(id);
    }

    public ArrayList<Provedor> listarTodo() throws Exception {
        return ConsultarData.Provedores();
    }

    public Integer contar() throws Exception {
        return listarTodo().size();
    }
}
