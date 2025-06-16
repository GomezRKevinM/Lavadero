package co.edu.udc.ejercicio28_lavadero.vistas.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class BotonModerno extends JButton {
    private boolean hover;
    private Dimension size = new Dimension(100, 70);

    public BotonModerno(String texto) {
        super(texto);
        configurarEstilo();
    }

    public BotonModerno(String texto, Color bg, Color fg) {
        super(texto);
        setBackground(bg);
        setForeground(fg);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setSize(100, 70);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                repaint();
            }
        });
    }

    public BotonModerno(String texto, int width, int height) {
        super(texto);
        this.size = new Dimension(width, height);
        configurarEstilo();
        setPreferredSize(size);
        setSize(size);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                repaint();
            }
        });
    }

    public void alignText(float x, float y) {
        this.setAlignmentX(x);
        this.setAlignmentY(y);
    }

    private void configurarEstilo() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setFont(new Font("cursive", Font.BOLD, 14));
        setForeground(Color.WHITE);
        try {
            Toolkit tk = Toolkit.getDefaultToolkit();
            Image imgOriginal = tk.getImage("src/main/resources/cursors/esponja.png");


            BufferedImage bufferedImage = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();


            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);


            double rotacionGrados = 45.0;
            double rotacionRadianes = Math.toRadians(rotacionGrados);
            g2d.rotate(rotacionRadianes, 16, 16);

            // Dibujar la imagen
            g2d.drawImage(imgOriginal, 0, 0, 32, 32, null);
            g2d.dispose();

            // Crear el cursor con la imagen rotada
            Cursor pointer = tk.createCustomCursor(bufferedImage, new Point(16, 16), "Pointer");
            setCursor(pointer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Agregar efectos hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                repaint();
            }
        });
    }



    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Color base y hover
        Color baseColor = new Color(141, 151, 163);
        Color hoverColor = new Color(51, 122, 183);

        // Crear gradiente
        GradientPaint gp = new GradientPaint(
                10, 10,
                hover ? hoverColor : baseColor,
                0, getHeight(),
                hover ? baseColor.darker() : baseColor.darker()
        );

        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        // Agregar brillo
        if (!hover) {
            g2.setPaint(new GradientPaint(
                    0, 0,
                    new Color(241, 241, 241, 100),
                    0, getHeight()/2,
                    new Color(255, 255, 255, 0)
            ));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        }

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return size;
    }

    @Override
    public void setSize(Dimension d) {
        super.setSize(d);
        size = d;
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
    }

    public void pack() {
        int width = getText().length() * 2 - 20;
        int height = (int) Math.round(getText().length() * 0.75);
        setSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, 90));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
    }
}
