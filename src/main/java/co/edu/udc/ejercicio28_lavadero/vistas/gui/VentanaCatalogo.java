package co.edu.udc.ejercicio28_lavadero.vistas.gui;

import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Producto;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Servicio;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Categoria;
import co.edu.udc.ejercicio28_lavadero.modelo.crud.ProductoCrudl;
import co.edu.udc.ejercicio28_lavadero.modelo.crud.ServicioCrudl;
import co.edu.udc.ejercicio28_lavadero.modelo.crud.CategoriaCrudl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class VentanaCatalogo extends JPanel {
    public VentanaCatalogo() {
        setLayout(new BorderLayout());
        JLabel titulo = new JLabel("CATÁLOGO", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        titulo.setForeground(new Color(0x1AA6C6));
        add(titulo, BorderLayout.NORTH);

        JPanel panelCategorias = new JPanel();
        panelCategorias.setLayout(new BoxLayout(panelCategorias, BoxLayout.Y_AXIS));

        CategoriaCrudl categoriaCrud = new CategoriaCrudl();
        ProductoCrudl productoCrud = new ProductoCrudl();
        ServicioCrudl servicioCrud = new ServicioCrudl();

        ArrayList<Categoria> categorias = categoriaCrud.listarTodo();
        ArrayList<Producto> productos = productoCrud.listarTodo();
        ArrayList<Servicio> servicios = new ArrayList<>();
        try {
             servicios = servicioCrud.listarTodo();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al cargar los servicios");
        }

        for (Categoria categoria : categorias) {
            JPanel detailPanel = new JPanel();
            detailPanel.setLayout(new BorderLayout());
            // Botón de categoría con estilo personalizado
            JButton btnExpand = new JButton(categoria.getNombre());
            btnExpand.setFocusPainted(false);
            btnExpand.setBackground(Color.WHITE);
            btnExpand.setForeground(new Color(0x1AA6C6));
            btnExpand.setFont(new Font("Arial", Font.BOLD, 20));
            btnExpand.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(8, 16, 8, 16),
                BorderFactory.createLineBorder(new Color(0x1AA6C6), 2, true)
            ));
            // Panel de contenido de la categoría
            JPanel contentPanel = new JPanel(new GridLayout(1, 2, 20, 10));
            contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            // Por defecto, el panel estará expandido (visible)
            contentPanel.setVisible(true);

            // Panel para productos (izquierda)
            JPanel productosPanel = new JPanel();
            productosPanel.setLayout(new BoxLayout(productosPanel, BoxLayout.Y_AXIS));
            productosPanel.setBorder(BorderFactory.createTitledBorder("Productos"));
            productosPanel.setOpaque(false);

            // Panel para servicios (derecha)
            JPanel serviciosPanel = new JPanel();
            serviciosPanel.setLayout(new BoxLayout(serviciosPanel, BoxLayout.Y_AXIS));
            serviciosPanel.setBorder(BorderFactory.createTitledBorder("Servicios"));
            serviciosPanel.setOpaque(false);

            // Productos de la categoría
            for (Producto p : productos) {
                if (p.getCategoria() == Integer.parseInt(categoria.getCodigo())) {
                    JButton btnProducto = new JButton(p.getNombreProducto(), Ventana.redimensionarImagen("src/main/resources/images/productos/" + (p.getImg() != null ? p.getImg() : "default.png"),30,30));
                    btnProducto.setHorizontalAlignment(SwingConstants.LEFT);
                    btnProducto.setIconTextGap(10);
                    btnProducto.setPreferredSize(new Dimension(220, 60));
                    btnProducto.addActionListener(e -> {
                        mostrarDetalleProducto(p);
                    });
                    JPanel panelBoton = new JPanel(new BorderLayout());
                    panelBoton.add(btnProducto, BorderLayout.CENTER);
                    JButton btnAgregar = new JButton("Agregar al carrito");
                    btnAgregar.addActionListener(e -> agregarAlCarrito(p));
                    panelBoton.add(btnAgregar, BorderLayout.EAST);
                    productosPanel.add(panelBoton);
                }
            }
            // Servicios de la categoría
            for (Servicio s : servicios) {
                if (s.getCategoria().getCodigo().equals(categoria.getCodigo())) {
                    JButton btnServicio = new JButton(s.getNombre(), Ventana.redimensionarImagen("src/main/resources/images/servicios/" + (s.getImagen() != null ? s.getImagen() : "services.png"),30, 30));
                    btnServicio.setHorizontalAlignment(SwingConstants.LEFT);
                    btnServicio.setIconTextGap(10);
                    btnServicio.setPreferredSize(new Dimension(220, 60));
                    btnServicio.addActionListener(e -> {
                        mostrarDetalleServicio(s);
                    });
                    JPanel panelBoton = new JPanel(new BorderLayout());
                    panelBoton.add(btnServicio, BorderLayout.CENTER);
                    JButton btnAgregar = new JButton("Agregar al carrito");
                    btnAgregar.addActionListener(e -> agregarAlCarrito(s));
                    panelBoton.add(btnAgregar, BorderLayout.EAST);
                    serviciosPanel.add(panelBoton);
                }
            }

            contentPanel.add(productosPanel);
            contentPanel.add(serviciosPanel);

            btnExpand.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    contentPanel.setVisible(!contentPanel.isVisible());
                }
            });

            detailPanel.setBackground(Color.WHITE);
            detailPanel.add(btnExpand, BorderLayout.NORTH);
            detailPanel.add(contentPanel, BorderLayout.CENTER);
            panelCategorias.add(detailPanel);
            panelCategorias.add(Box.createVerticalStrut(10));
        }
        // Scroll para todo el panel de categorías
        JScrollPane scroll = new JScrollPane(panelCategorias);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scroll, BorderLayout.CENTER);
    }

    // Métodos auxiliares para mostrar detalles y agregar al carrito
    private void mostrarDetalleProducto(Producto p) {
        JOptionPane.showMessageDialog(this,
                "Nombre: " + p.getNombreProducto() + "\n" +
                "Precio: $" + p.getPrecio(),
                "Detalle del Producto",
                JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon("src/main/resources/images/productos/" + (p.getImg() != null ? p.getImg() : "default.png")));
    }

    private void mostrarDetalleServicio(Servicio s) {
        JOptionPane.showMessageDialog(this,
                "Nombre: " + s.getNombre() + "\n" +
                "Descripción: " + s.getDescripcion() + "\n" +
                "Precio: $" + s.getDescripcion(),
                "Detalle del Servicio",
                JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon("src/main/resources/images/servicios/" + (s.getImagen() != null ? s.getImagen() : "services.png")));
    }

    private void agregarAlCarrito(Object item) {
        // Aquí puedes implementar la lógica para agregar al carrito
        JOptionPane.showMessageDialog(this, "Agregado al carrito: " + item.toString());
    }
}
