package co.edu.udc.ejercicio28_lavadero.vistas.gui.components;

import javax.swing.*;
import java.awt.*;

public class LabelValue extends JPanel {
    private JLabel label;
    private JLabel value;
    private Color colorLabel = new Color(0x1AA6C6);
    private Color colorValue = new Color(0x07424C);


    public LabelValue(String title, String valor) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        label = new JLabel(title);
        value = new JLabel(valor);
        setSize(Integer.MAX_VALUE, 30);
        label.setFont(new Font("Cascadia Code", Font.BOLD, 24));
        value.setFont(new Font("Cascadia Code", Font.CENTER_BASELINE, 15));
        label.setForeground(colorLabel);
        value.setForeground(colorValue);

        add(label);
        if(!valor.isEmpty()) {
            add(Box.createRigidArea(new Dimension(10, 0)));
            add(value);
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        }
    }

    public JLabel getLabel() {
        return label;
    }

    public JLabel getValue() {
        return value;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
    public void setValue(JLabel value) {
        this.value = value;
    }
    public Color getColorLabel() {
        return colorLabel;
    }
    public void setColorLabel(Color colorLabel) {
        this.colorLabel = colorLabel;
    }
    public Color getColorValue() {
        return colorValue;
    }
}
