package co.edu.udc.ejercicio28_lavadero.services;


public class ValidarTelefono {
    public static boolean validarTelefono(String numero){
        if(numero.isEmpty() || numero.isBlank()){
            throw new RuntimeException("El telefono no puede estar vacio");
        }else{
            String patron = "^[0-9]{7,10}$";
            return numero.matches(patron);
        }
    }
}
