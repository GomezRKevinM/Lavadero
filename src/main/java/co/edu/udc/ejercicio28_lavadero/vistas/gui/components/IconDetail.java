package co.edu.udc.ejercicio28_lavadero.vistas.gui.components;

import co.edu.udc.ejercicio28_lavadero.vistas.gui.Ventana;

import javax.swing.*;
import java.awt.*;

public class IconDetail extends JPanel {
    String icono;
    String nombre;
    int cantidad;

    public IconDetail(String icono, String nombre, int cantidad) {
        this.icono = icono;
        this.nombre = nombre;
        this.cantidad = cantidad;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ImageIcon img = Ventana.redimensionarImagen(icono, 64, 64);
        JLabel labelIcono = new JLabel(img);
        labelIcono.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(labelIcono);

        JLabel labelNombre = new JLabel(nombre+" "+cantidad);
        labelNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(labelNombre);
    }

}
