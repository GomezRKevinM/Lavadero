package co.edu.udc.ejercicio28_lavadero.model.crud;

import co.edu.udc.ejercicio28_lavadero.models.Servicio;
import co.edu.udc.ejercicio28_lavadero.util.ConsultarData;
import co.edu.udc.ejercicio28_lavadero.util.DeleteData;
import co.edu.udc.ejercicio28_lavadero.util.InsertData;
import co.edu.udc.ejercicio28_lavadero.util.UpdateData;

import java.sql.SQLException;
import java.util.ArrayList;

public class ServicioCrudl {

    public void agregar(Servicio servicio){
        InsertData.Servicio(servicio);
    }

    public ArrayList<Servicio> buscar(String codigo) throws SQLException {
        return ConsultarData.Servicio(codigo);
    }

    public void editar(Servicio servicio) throws SQLException {
        UpdateData.Servicio(servicio);
    }

    public void eliminar(int codigo) throws SQLException {
        DeleteData.DeleteTable("Servicios","codigo",codigo);
    }

    public ArrayList<Servicio> listarTodo() throws SQLException {
        return ConsultarData.Servicios();
    }

    public Integer contar() throws SQLException {
        return listarTodo().size();
    }
}
