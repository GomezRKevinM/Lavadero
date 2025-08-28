package co.edu.udc.ejercicio28_lavadero.vistas.gui;

import co.edu.udc.ejercicio28_lavadero.modelo.crud.*;
import co.edu.udc.ejercicio28_lavadero.vistas.gui.components.IconDetail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Ventana extends JFrame{
    private JPanel panelContenido;
    private JPanel panelCategorias;

    public Ventana() throws Exception {
        
        CategoriaCrudl crud = new CategoriaCrudl();
        ProveedorCrudl pcrud = new ProveedorCrudl();
        setTitle("Carwash");
        setSize(new Dimension(1080, 830));
        setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        setMinimumSize(new Dimension(1080,830));
        setPreferredSize(new Dimension(1080,830));
        setIconImage(new ImageIcon("src/main/resources/images/icons/logo.png").getImage());
        VentanaCategoria ventanaCategoria = new VentanaCategoria();
        panelCategorias = ventanaCategoria.getPanelContenido();


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        panelContenido = new JPanel();
        panelContenido.setLayout(new CardLayout(10,10));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelContenido.setOpaque(false);

        VentanaProducto ventanaProducto = new VentanaProducto();
        ventanaProducto.setVentanaPrincipal(this);
        VentanaServicio ventanaServicio = new VentanaServicio();
        ventanaServicio.setVentanaPrincipal(this);
        VentanaCliente ventanaCliente = new VentanaCliente();
        ventanaCliente.setVentanaPrincipal(this);
        VentanaCatalogo ventanaCatalogo = new VentanaCatalogo();
        VentanaEmpleado ventanaEmpleado = new VentanaEmpleado();
        ventanaEmpleado.setVentanaPrincipal(this);

        JPanel panelProductos = ventanaProducto.getPanelContenido();
        JPanel panelServicios = ventanaServicio.getPanelContenido();
        JPanel panelClientes = ventanaCliente.getPanelContenido();
        JPanel panelCatalogo = ventanaCatalogo;
        JPanel panelEmpleados = ventanaEmpleado.getPanelContenido();

        panelContenido.add(getInicio(), "Inicio");
        panelContenido.add(panelProductos, "Productos");
        panelContenido.add(panelCategorias, "Categorias");
        panelContenido.add(panelServicios, "Servicios");
        panelContenido.add(panelClientes, "Clientes");
        panelContenido.add(panelCatalogo, "Catalogo");
        panelContenido.add(panelEmpleados, "Empleados");


        JMenuBar menu = new JMenuBar();
        JMenuItem Inicio = new JMenuItem("Inicio",redimensionarImagen("src/main/resources/images/icons/home.png", 25, 25));
        Inicio.setBounds(10,0,25,25);
        Inicio.addActionListener(e -> {
            CardLayout cl = (CardLayout) panelContenido.getLayout();
            cl.show(panelContenido, "Inicio");
            panelProductos.revalidate();
            panelProductos.repaint();
            panelProductos.requestFocusInWindow();
            panelProductos.requestFocus();
        });

        JMenuItem productos = new JMenuItem("Productos", redimensionarImagen("src/main/resources/images/productos/default.png", 25, 25));
        JMenuItem Servicios = new JMenuItem("Servicios",redimensionarImagen("src/main/resources/images/icons/services.png", 25, 25));
        JMenuItem Clientes = new JMenuItem("Clientes",redimensionarImagen("src/main/resources/images/icons/clientes.png", 25, 25));
        JMenuItem Empleados = new JMenuItem("Empleados",redimensionarImagen("src/main/resources/images/icons/empleados.png", 25, 25));
        JMenuItem Categorias = new JMenuItem("Categorias",redimensionarImagen("src/main/resources/images/icons/categorias.png", 25, 25));
        JMenuItem Proveedores = new JMenuItem("Proveedores",redimensionarImagen("src/main/resources/images/icons/proveedores.png", 25, 25));
        JMenuItem ventas = new JMenuItem("Ventas",redimensionarImagen("src/main/resources/images/icons/ventas.png", 25, 25));
        JMenuItem pedidos = new JMenuItem("Pedidos",redimensionarImagen("src/main/resources/images/icons/pedido.png", 25, 25));
        JMenuItem Catalogo = new JMenuItem("Catálogo",redimensionarImagen("src/main/resources/images/icons/catalogo.png", 25, 25));

        menu.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        menu.add(productos);
        menu.add(Servicios);
        menu.add(Clientes);
        menu.add(Empleados);
        menu.add(Categorias);
        menu.add(Catalogo);
        menu.add(Proveedores);
        menu.add(ventas);
        menu.add(pedidos);



        // Acciones de los item en Menu Bar
        productos.addActionListener(e -> {
            showCard("Productos");
        });

        Categorias.addActionListener(e -> {
            showCard("Categorias");
        });

        Servicios.addActionListener(e -> {
            showCard("Servicios");
        });

        Clientes.addActionListener(e -> {
            showCard("Clientes");
        });

        Catalogo.addActionListener(e -> {
            showCard("Catalogo");
        });

        Empleados.addActionListener(e -> {
            showCard("Empleados");
        });


        menu.add(Inicio);

        this.setJMenuBar(menu);
        this.add(panelContenido);

        setVisible(true);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Width: "+getWidth()+"\nHeight: "+getHeight());
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
        });
    }

    public static ImageIcon redimensionarImagen(String src, int w, int h) {
    ImageIcon imagen = new ImageIcon(src);
    Image newimg = imagen.getImage().getScaledInstance(w, h,  Image.SCALE_AREA_AVERAGING);
    return new ImageIcon(newimg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Ventana();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                    null,
                    "Error al iniciar la aplicación: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                e.printStackTrace();
            }
        });
    }

    public JPanel getInicio() throws Exception {
        EmpleadoCrudl empleadoCrudl = new EmpleadoCrudl();
        ProveedorCrudl proveedorCrudl = new ProveedorCrudl();
        ProductoCrudl productoCrudl = new ProductoCrudl();
        CategoriaCrudl categoriaCrudl = new CategoriaCrudl();
        ServicioCrudl servicioCrudl = new ServicioCrudl();
        ClienteCrudl clienteCrudl = new ClienteCrudl();


        JPanel inicio = new JPanel(new BorderLayout(40,100));
        inicio.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        inicio.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        inicio.setMinimumSize(new Dimension(200, 200));
        inicio.setPreferredSize(new Dimension(200, 200));


        JLabel titulo = new JLabel("Lavadero App",SwingConstants.CENTER);
        titulo.setForeground(new Color(0x1AA6C6));
        titulo.setFont(new Font("Arial", Font.BOLD, 46));
        inicio.add(titulo, BorderLayout.NORTH);
        JPanel btnContent = new JPanel();

        Color fondo = new Color(0x1AA6C6);
        Color letras = new Color(0xFFFFFF);

        IconDetail empleados = new IconDetail("src/main/resources/images/icons/empleados.png","Empleados",empleadoCrudl.contar());
        IconDetail categorias = new IconDetail("src/main/resources/images/icons/categorias.png","Categorias",categoriaCrudl.contar());
        IconDetail provedores = new IconDetail("src/main/resources/images/icons/proveedores.png","Proveedores",proveedorCrudl.contar());
        IconDetail productos = new IconDetail("src/main/resources/images/productos/default.png","Productos",productoCrudl.contar());
        IconDetail ventas = new IconDetail("src/main/resources/images/icons/ventas.png","Ventas",0);
        IconDetail pedidos = new IconDetail("src/main/resources/images/icons/pedido.png","Pedidos",0);
        IconDetail servicios = new IconDetail("src/main/resources/images/icons/services.png","Servicios",servicioCrudl.contar());
        IconDetail clientes = new IconDetail("src/main/resources/images/icons/clientes.png","Clientes",clienteCrudl.contar());
        IconDetail catalogo = new IconDetail("src/main/resources/images/icons/catalogo.png","Catalogo",0);

        btnContent.setLayout(new GridLayout(2, 4, 10, 10));
        btnContent.add(empleados);
        btnContent.add(categorias);
        btnContent.add(provedores);
        btnContent.add(productos);
        btnContent.add(ventas);
        btnContent.add(pedidos);
        btnContent.add(servicios);
        btnContent.add(clientes);

        inicio.add(btnContent, BorderLayout.CENTER);
        return inicio;
    }

    public void showCard(String name){
        CardLayout cl = (CardLayout) panelContenido.getLayout();
        cl.show(panelContenido, name);
    }

}