package co.edu.udc.ejercicio28_lavadero;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import co.edu.udc.ejercicio28_lavadero.vistas.gui.Ventana;





/**
 *
 * @author Kevin Manuel Gómez Rojas
 */
public class Principal {
    

    public static void main(String[] args) throws Exception {
        Ventana principal = new Ventana();
        principal.setVisible(true);
        principal.setLocationRelativeTo(null);
        principal.setFont(new Font("Cascadia Code PL", Font.PLAIN, 12));
    }

    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String convertirDivisa(double monto){
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.forLanguageTag("es-CO"));
        dfs.setCurrencySymbol(Currency.getInstance("COP").getSymbol());
        DecimalFormat df = new DecimalFormat("¤ #,##0", dfs);
        return df.format(monto)+" "+dfs.getCurrency().getCurrencyCode();
    }
}