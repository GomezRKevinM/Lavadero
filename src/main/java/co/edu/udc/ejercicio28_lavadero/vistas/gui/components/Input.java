package co.edu.udc.ejercicio28_lavadero.vistas.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Input extends JTextField implements MouseListener, FocusListener {

    private String prompt;
    private String tipo;
    public Input(String prompt) {
        super(prompt);
        this.tipo = "default";
        this.prompt=prompt;
        setForeground(Color.DARK_GRAY);
        addMouseListener(this);
        setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        addFocusListener(this);
    }

    public Input(String prompt,String tipo) {
        super(prompt);
        this.prompt=prompt;
        this.tipo = tipo;
        setForeground(Color.DARK_GRAY);
        addMouseListener(this);
        setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        addFocusListener(this);

    }



    @Override
    public void mouseClicked(MouseEvent e) {
        if(this.getText().equals(prompt)){
            setText("");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(this.getText().equals(prompt)){
            setText("");
            setFont(new Font("Cascadia Code PL", Font.PLAIN, 20));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(this.getText().isEmpty()){
            setText(prompt);
            setForeground(Color.DARK_GRAY);
            setFont(new Font("Cascadia Code PL", Font.PLAIN, 14));
            transferFocus();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(this.getText().equals(prompt)){
            setText("");
        }
        setFont(new Font("Cascadia Code PL", Font.PLAIN, 14));
        setForeground(Color.BLACK);
    }

    @Override
    public void focusLost(FocusEvent e) {
        String text = getText();
        if (tipo.equals("peso") && !text.isEmpty() && text.matches("\\d+") && text.length()> 3 && !text.contains(".")) {
            double number = Double.parseDouble(text);
            setText(co.edu.udc.ejercicio28_lavadero.Principal.convertirDivisa(number));
            setForeground(Color.BLACK);
            setFont(new Font("Cascadia Code PL", Font.PLAIN, 20));
        }

    }
}
