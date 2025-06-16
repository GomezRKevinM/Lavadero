package co.edu.udc.ejercicio28_lavadero.vistas.gui.components;

import co.edu.udc.ejercicio28_lavadero.vistas.gui.Ventana;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuItem extends JMenuItem {
    public MenuItem(String nombre, String icono) {
        super(nombre, Ventana.redimensionarImagen(icono, 25, 25));

        this.setBackground(new Color(0xFF414B53, true));

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
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
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setBackground(new Color(0x07424C));
            }
        });
    }
}
