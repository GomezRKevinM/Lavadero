package co.edu.udc.ejercicio28_lavadero.modelo.crud;


import co.edu.udc.ejercicio28_lavadero.Color;
import co.edu.udc.ejercicio28_lavadero.Principal;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Categoria;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Producto;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

import co.edu.udc.ejercicio28_lavadero.util.ConsultarData;
import co.edu.udc.ejercicio28_lavadero.util.DeleteData;
import co.edu.udc.ejercicio28_lavadero.util.InsertData;
import co.edu.udc.ejercicio28_lavadero.util.UpdateData;


public class ProductoCrudl {

    public void agregar(Producto p) throws Exception {
        InsertData.Producto(p.getNombreProducto(),p.getMarca(),p.getCategoria(),p.getPrecio(),p.getPrecioDeCompra(),p.getStock(),p.getAlerta(),p.getCodigoDelProveedor(),p.getImg());
    }

    public Producto buscar(String codigo) {
        return ConsultarData.Producto(codigo);
    }

    public ArrayList<Producto> buscarProductos(String busqueda){
        return ConsultarData.ProductosSearch(busqueda);
    }


   public void editar(Producto p) throws Exception {
        UpdateData.Producto(p);
   }



    public void eliminar(String codigo) {
        DeleteData.DeleteTable("Productos","codigo",Integer.parseInt(codigo) );
    }

    public ArrayList<Producto> listarTodo() {
        return ConsultarData.Productos();
    }

    public Integer contar() {
       return ConsultarData.Productos().size();
    }

    public String convertirDivisa(double monto){
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.forLanguageTag("es-CO"));
        dfs.setCurrencySymbol(Currency.getInstance("COP").getSymbol());
        DecimalFormat df = new DecimalFormat("¤ #,##0", dfs);
        return df.format(monto)+" "+dfs.getCurrency().getCurrencyCode();
    }
}