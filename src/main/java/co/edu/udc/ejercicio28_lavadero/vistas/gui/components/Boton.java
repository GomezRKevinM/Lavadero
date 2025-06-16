package co.edu.udc.ejercicio28_lavadero.vistas.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Boton extends JButton implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public Boton(String tipo, String text) {
        setFont(new Font("Cascadia Code PL", Font.BOLD, 12));
        setText(text);
        switch (tipo) {
            case "guardar":
                setBackground(new Color(0x4CAF50));
                setForeground(new Color(0x003300));
            break;
            case "cancelar":
                setBackground(new Color(0xF44343));
                setForeground(new Color(0x4A0011));
            break;
            case "cambiar":
                setBackground(new Color(0x00BCD4));
                setForeground(new Color(0x07424C));
            break;
        }
    }
}
