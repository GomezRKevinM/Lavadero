package co.edu.udc.ejercicio28_lavadero.models;

import co.edu.udc.ejercicio28_lavadero.enums.TipoDocumento;
import co.edu.udc.ejercicio28_lavadero.exceptions.DocumentoException;
import co.edu.udc.ejercicio28_lavadero.factory.ClienteFactory;
import co.edu.udc.ejercicio28_lavadero.model.crud.ClienteCrudl;
import co.edu.udc.ejercicio28_lavadero.valueObjects.DocumentoIdentidad;
import org.junit.Assert;
import org.junit.Test;


import java.util.ArrayList;


public class ClientTest {
    @Test
    public void debeCrearClienteConDocumentoValido() throws DocumentoException {
        var documento = new DocumentoIdentidad("123456789");
        var cliente = new Cliente("Kevin gomez", TipoDocumento.Cedula,documento,"kevin@example.com","3215970852"," Prado Cll 5B #45C-14");
        Assert.assertEquals(cliente.getIdentificacion().getValor(),"123456789");
    }

    @Test(expected = DocumentoException.class)
    public void debeLanzarExcepcionAlCrearClienteConDocumentoInvalido() throws DocumentoException {
        var documento = new DocumentoIdentidad("12345");
    }

    @Test
    public void obtenerCliente() throws Exception {
        String documento = "1001973042";
        ClienteCrudl crud = new ClienteCrudl();
        ArrayList<Cliente> resultado = crud.buscar(documento);
        Cliente encontrado = resultado.get(0);
        Assert.assertEquals(encontrado.getNombre(),"Kevin Manuel Gomez Rojas");
    }

    @Test
    public void validarClienteCreadoexitosamente(){
        Cliente creado = ClienteFactory.crearCliente("Gary Castaño",TipoDocumento.Cedula,"7304256","gcastano@uni.co","3214872567","Barrio manga manzana  2");
        Assert.assertTrue(creado.Correo, !creado.Correo.isEmpty());
    }
}
