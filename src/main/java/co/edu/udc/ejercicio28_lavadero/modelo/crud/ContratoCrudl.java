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

    public static void main(String[] args) {
        ContratoCrudl crud = new ContratoCrudl();
        crud.agregar(new Contrato());
    }

    public void agregar(Contrato contrato){
        InsertData.Contrato(contrato.getFechaInicio(),contrato.getSueldoBase(),contrato.getCargo().name(),contrato.getFechaFinal(),contrato.getHorario(),Integer.parseInt(contrato.getContratado()),Integer.parseInt(contrato.getContratante()),contrato.getClausulas().toString());
    }

    public Contrato buscar(String codigo){
        return ConsultarData.Contrato(Integer.parseInt(codigo));
    }

    public void editar(Contrato contrato) throws Exception {
        UpdateData.Contrato(contrato);
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
