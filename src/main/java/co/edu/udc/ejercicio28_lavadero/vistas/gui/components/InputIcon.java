
package co.edu.udc.ejercicio28_lavadero.vistas.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputIcon extends JPanel {
    private JTextField textField;
    private JLabel iconoLabel;
    private String prompt;

    public InputIcon(ImageIcon icono, String prompt) {
        setSize(Integer.MAX_VALUE-20, 30);
        this.setBorder(BorderFactory.createLineBorder(new Color(0xFFB340), 2, true));
        this.prompt = prompt;
        textField = new JTextField("");
        textField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        iconoLabel = new JLabel(icono);


        // Luego configuramos el layout
        setLayout(new BorderLayout());

        // Configuramos el texto y otros atributos
        textField.setText(prompt);
        textField.setForeground(Color.LIGHT_GRAY);
        textField.setSize(Integer.MAX_VALUE-60, 20);

        textField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(textField.getText().equals(prompt)){
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                    textField.requestFocusInWindow();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(textField.getText().equals(prompt)){
                    textField.setText("");
                    textField.setForeground(Color.BLACK);

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
                if(textField.getText().isEmpty()){
                    textField.setText(prompt);
                    textField.setForeground(Color.LIGHT_GRAY);
                    textField.transferFocusBackward();
                }
            }
        });

        iconoLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        add(textField, BorderLayout.CENTER);
        add(iconoLabel, BorderLayout.EAST);

        setBackground(textField.getBackground());
        setBorder(textField.getBorder());
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public JTextField getTextField() {
        return textField;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    public JLabel getIconoLabel() {
        return iconoLabel;
    }

    public void setIconoLabel(JLabel iconoLabel) {
        this.iconoLabel = iconoLabel;
    }
    
}