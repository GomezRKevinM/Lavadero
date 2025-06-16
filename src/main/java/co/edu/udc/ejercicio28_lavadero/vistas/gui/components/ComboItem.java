package co.edu.udc.ejercicio28_lavadero.vistas.gui.components;

public class ComboItem {
    private String nombre;
    private int codigo;

    public ComboItem(String nombre, int codigo){
        this.nombre = nombre;
        this.codigo = codigo;
    }

    @Override
    public String toString(){
        return nombre;
    }

    public int getCodigo(){
        return codigo;
    }
}
