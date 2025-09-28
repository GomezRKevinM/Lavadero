package co.edu.udc.ejercicio28_lavadero.vistas.gui;

import co.edu.udc.ejercicio28_lavadero.Principal;
import co.edu.udc.ejercicio28_lavadero.models.crud.CategoriaCrudl;
import co.edu.udc.ejercicio28_lavadero.models.crud.ProductoCrudl;
import co.edu.udc.ejercicio28_lavadero.models.crud.ProveedorCrudl;
import co.edu.udc.ejercicio28_lavadero.models.entidades.Categoria;
import co.edu.udc.ejercicio28_lavadero.models.entidades.Producto;
import co.edu.udc.ejercicio28_lavadero.models.entidades.Provedor;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


public class VentanaProducto extends JPanel implements ActionListener{
    private JPanel panelContenido;
    private Ventana ventanaPrincipal;
    private JDialog dialog;
    private JLabel label = new JLabel("");
    private  String src = "default.png";


    private JButton botonAgregar = new JButton("Agregar Producto",new ImageIcon("src/main/resources/images/icons/agregar-producto.png"));
    private JButton botonModificar = new JButton("Modificar Producto",Ventana.redimensionarImagen("src/main/resources/images/icons/editar.png",20,20));
    private JButton botonEliminar = new JButton("Eliminar Producto",Ventana.redimensionarImagen("src/main/resources/images/icons/papelera-de-reciclaje.png",20,20));
    private JButton btnSearch = new JButton("Buscar Producto");
    private JPanel panelBotones = new JPanel();
    private JButton cancelar = new JButton("✖️ Cancelar");
    private JButton guardar = new JButton("✔️ Agregar Producto");

    public VentanaProducto() {
        ProductoCrudl crud = new ProductoCrudl();
        panelContenido = new JPanel();
        panelContenido.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelContenido.setPreferredSize(new Dimension(getWidth()-20, getHeight()-100));
        panelContenido.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        panelContenido.setMinimumSize(new Dimension(200, 200));
        panelContenido.setLayout(new BorderLayout(10, 10));



        panelBotones.setLayout(new GridLayout(0, 1, 0, 10));
        panelBotones.setAutoscrolls(true);

        cargarProductos();
        actualizarProductos();

        JScrollPane scrollPane = new JScrollPane(panelBotones);
        scrollPane.setPreferredSize(new Dimension(200, 300));
        scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.setSize(new Dimension(getWidth()-20, getHeight()-100));
        scrollPane.setOpaque(false);

        JLabel titulo = new JLabel("PRODUCTOS",SwingConstants.CENTER);
        titulo.setForeground(new Color(0x1AA6C6));
        titulo.setFont(new Font("Arial", Font.BOLD, 46));
        panelContenido.add(titulo, BorderLayout.NORTH);
        panelContenido.add(scrollPane, BorderLayout.CENTER);

        botonAgregar.addActionListener(e -> {
            JDialog dialog = new JDialog(ventanaPrincipal, "Agregar Producto", true);
            dialog.setSize(1330, 450);
            dialog.setLocationRelativeTo(null);
            dialog.setLayout(new BorderLayout(10, 10));
            try {
                dialog.add(getPanelAgregar(dialog), BorderLayout.CENTER);
                dialog.setResizable(true);
                dialog.setVisible(true);
            } catch (Exception ex) {
                JDialog mensaje = new JDialog(ventanaPrincipal, "Error: "+ex.getMessage(), true);
                mensaje.setSize(300, 100);
            }
        });

        InputIcon buscador = new InputIcon(Ventana.redimensionarImagen("src/main/resources/images/icons/lupa.png",30,30),"Buscar Producto");
        buscador.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buscador.getTextField().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inferior = new JPanel();
        inferior.setLayout(new BoxLayout(inferior, BoxLayout.X_AXIS));
        inferior.add(botonAgregar);
        inferior.add(Box.createHorizontalStrut(10));
        inferior.add(buscador);

        inferior.setOpaque(true);
        inferior.setBackground(new Color(0x1AA6C6));
        inferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelContenido.add(inferior, BorderLayout.SOUTH);

        // eventos
        buscador.getTextField().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(KeyEvent.VK_ENTER==e.getKeyCode()){

                    panelBotones.removeAll();
                    int productos = crud.buscarProductos(buscador.getTextField().getText()).size();
                    if(productos>0) {
                        for (Producto encontrado : crud.buscarProductos(buscador.getTextField().getText())) {
                            BotonModerno productoEncontrado = new BotonModerno("<html> <font size='7'>" + encontrado.getNombreProducto() + "</font> <hr> <font size='5' color='#333'> Precio </font> " + Principal.convertirDivisa(encontrado.getPrecio()) + " Unid " + encontrado.getStock());
                            productoEncontrado.setIcon(Ventana.redimensionarImagen("src/main/resources/images/productos/" + encontrado.getImg(), 50, 50));
                            productoEncontrado.addActionListener(e1 -> {
                                dialog = new JDialog(ventanaPrincipal, "Detalles del Producto " + encontrado.getNombreProducto().toUpperCase(), true);
                                dialog.setSize(1280, 450);
                                dialog.setLocationRelativeTo(null);
                                dialog.setLayout(new BorderLayout(10, 10));
                                try {
                                    dialog.add(getPanelModificar(encontrado), BorderLayout.CENTER);
                                    dialog.setResizable(true);
                                } catch (Exception ex) {
                                    JDialog mensaje = new JDialog(ventanaPrincipal, "Error: " + ex.getMessage(), true);
                                    mensaje.setSize(300, 100);
                                    mensaje.setLocationRelativeTo(null);
                                }
                                dialog.setVisible(true);
                                ventanaPrincipal.showCard("Productos");
                                dialog.dispose();

                                botonEliminar.addMouseListener(new MouseListener() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                        int borrar = JOptionPane.showConfirmDialog(ventanaPrincipal, "Esta seguro de eliminar el producto " + encontrado.getNombreProducto() + "?", "Eliminar Producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                                        if (borrar == 0) {
                                            try {
                                                crud.eliminar(encontrado.getCodigo());
                                                JOptionPane.showMessageDialog(ventanaPrincipal, "Producto " + encontrado.getNombreProducto() + " eliminado exitosamente", "Producto Eliminado", JOptionPane.INFORMATION_MESSAGE);
                                                dialog.dispose();
                                                ventanaPrincipal.dispose();
                                                ventanaPrincipal.setVisible(true);
                                            } catch (Exception ex) {
                                                JOptionPane.showMessageDialog(ventanaPrincipal, "No se pudo eliminar el producto " + encontrado.getNombreProducto() + "\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                        actualizarProductos();
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

                            });
                            productoEncontrado.pack();
                            panelBotones.add(productoEncontrado);
                        }
                    }else{
                        panelBotones.add(new LabelValue("No se encontraron resultados para la busqueda: ", buscador.getTextField().getText()));
                    }
                    panelBotones.revalidate();
                    panelBotones.repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(buscador.getTextField().getText().equals(buscador.getPrompt()) || buscador.getTextField().getText().equals("")){
                    panelBotones.removeAll();
                    actualizarProductos();
                    panelBotones.revalidate();
                    panelBotones.repaint();
                }
            }
        });
    }

    public void actualizarProductos(){
        panelBotones.removeAll();
        try {
            cargarProductos();
            panelBotones.revalidate();
            panelBotones.repaint();
        } catch (Exception ex) {
            JDialog mensaje = new JDialog(ventanaPrincipal, "Error: "+ex.getMessage(), true);
            mensaje.setSize(300, 100);
            mensaje.setLocationRelativeTo(null);
            mensaje.setLayout(new FlowLayout(FlowLayout.CENTER));
            mensaje.add(new JLabel("No se pudo actualizar la lista de productos, error: "+ex.getMessage()));
        }
    }

    public JPanel getPanelContenido() {
        return panelContenido;
    }

    public JButton getBotonAgregar() {
        return botonAgregar;
    }

    public void setBotonAgregar(JButton botonAgregar) {
        this.botonAgregar = botonAgregar;
    }

    public JButton getBotonModificar() {
        return botonModificar;
    }

    public void setBotonModificar(JButton botonModificar) {
        this.botonModificar = botonModificar;
    }

    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    public void setBotonEliminar(JButton botonEliminar) {
        this.botonEliminar = botonEliminar;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public void setBtnSearch(JButton btnSearch) {
        this.btnSearch = btnSearch;
    }

    public JPanel getPanelAgregar(JDialog modal) throws Exception{
        CategoriaCrudl crud = new CategoriaCrudl();
        ProveedorCrudl pcrud = new ProveedorCrudl();
        ProductoCrudl crudProducto = new ProductoCrudl();
        String imagen;
        JPanel panel = new JPanel(new BorderLayout(10,10));
        JPanel panelTittle = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 40));
        JPanel linea1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel linea2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel linea3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel linea4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linea1.setSize(600, 50);
        linea2.setSize(600, 50);
        linea3.setSize(600, 50);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        panel.setMinimumSize(new Dimension(200, 200));
        panel.setPreferredSize(new Dimension(200, 200));

        Font general = new Font("Cascadia Code PL", Font.BOLD, 16);
        JLabel titulo = new JLabel("Agregar Producto",SwingConstants.CENTER);
        titulo.setForeground(new Color(0x1AA6C6));
        titulo.setFont(new Font("Cascadia Code PL", Font.BOLD, 46));

        LabelValue etiquetaNombre = new LabelValue("Nombre del Producto: ","");
        etiquetaNombre.getLabel().setFont(general);
        Input nombre = new Input("Nombre del producto");
        nombre.setPreferredSize(new Dimension(200, 30));

        LabelValue etiquetaPrecioCompra = new LabelValue("Precio de compra: ", "");
        etiquetaPrecioCompra.getLabel().setFont(general);
        Input compra = new Input("Precio de compra");
        compra.setPreferredSize(new Dimension(150, 30));

        LabelValue etiquetaPrecioVenta = new LabelValue("Precio de Venta: ", "");
        etiquetaPrecioVenta.getLabel().setFont(general);
        Input precio = new Input("Precio de venta");
        precio.setPreferredSize(new Dimension(150, 30));

        LabelValue etiquetaStock = new LabelValue("Stock: ", "");
        etiquetaStock.getLabel().setFont(general);
        Input stock = new Input("Stock");
        stock.setPreferredSize(new Dimension(100, 30));

        LabelValue etiquetaMarca = new LabelValue("Marca: ", "");
        etiquetaMarca.getLabel().setFont(general);
        Input marca = new Input("Marca");
        marca.setPreferredSize(new Dimension(120, 30));

        LabelValue etiquetaAlert = new LabelValue("Alertar de stock: ", "");
        etiquetaAlert.getLabel().setFont(general);
        Input alerta = new Input("Alerta de stock");
        alerta.setPreferredSize(new Dimension(100, 30));

        LabelValue etiquetaCategoria = new  LabelValue("Categoria: ", "");
        etiquetaCategoria.getLabel().setFont(general);
        JComboBox<ComboItem> categoria = new JComboBox<>();
        for(Categoria cat: crud.listarTodo()){
            ComboItem item = new ComboItem(cat.getNombre(),Integer.parseInt(cat.getCodigo()));
            categoria.addItem(item);
        }

        LabelValue etiquetaProvedor = new  LabelValue("Proveedor: ", "");
        etiquetaProvedor.getLabel().setFont(general);
        JComboBox<ComboItem> provedor = new JComboBox<>();
        for(Provedor pro : pcrud.listarTodo()){
            ComboItem item = new ComboItem(pro.getNombre(),Integer.parseInt(pro.getId()));
            provedor.addItem(item);
        }

        JButton cancelar = new JButton("✖️ Cancelar");
        JButton guardar = new JButton("✔️ Agregar Producto");

        cancelar.setBackground(new Color(0xF44343));
        cancelar.setForeground(new Color(0x4A0011));
        guardar.setBackground(new Color(0x4CAF50));
        guardar.setForeground(new Color(0x003300));

        provedor.validate();
        nombre.validate();
        compra.validate();
        precio.validate();
        stock.validate();
        alerta.validate();
        marca.validate();
        categoria.validate();

        etiquetaNombre.getValue().setVisible(false);
        linea1.add(etiquetaNombre);
        linea1.add(nombre);
        linea1.add(Box.createHorizontalStrut(30));

        linea1.add(etiquetaPrecioCompra);
        linea1.add(compra);
        linea1.add(Box.createHorizontalStrut(30));

        linea1.add(etiquetaPrecioVenta);
        linea1.add(precio);

        linea2.add(Box.createHorizontalStrut(-90));
        linea2.add(etiquetaStock);
        linea2.add(stock);
        linea2.add(Box.createHorizontalStrut(250));


        linea2.add(etiquetaAlert);
        linea2.add(alerta);
        linea2.add(Box.createHorizontalStrut(80));

        linea2.add(etiquetaMarca);
        linea2.add(marca);
        linea2.add(Box.createHorizontalStrut(30));


        linea3.add(etiquetaProvedor);
        linea3.add(provedor);
        linea3.add(Box.createHorizontalStrut(90));

        JButton selectImage = new JButton("Cambiar imagen");
        selectImage.addActionListener(this::actionPerformed);

        linea3.add(etiquetaCategoria);
        linea3.add(categoria);
        linea3.add(Box.createHorizontalStrut(56));
        label.setText("Imagen del producto");
        label.setIcon(Ventana.redimensionarImagen("src/main/resources/images/productos/default.png", 64, 64));


        linea3.add(label);
        linea3.add(selectImage);

        linea4.add(cancelar);
        linea4.add(guardar);
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(90, 10, 90, 10));

        center.add(linea1);
        center.add(Box.createVerticalStrut(20));
        center.add(linea2);
        center.add(Box.createVerticalStrut(10));
        center.add(linea3);

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(center, BorderLayout.CENTER);
        panel.add(linea4, BorderLayout.SOUTH);

        cancelar.addActionListener(e -> {
            nombre.setText("");
            compra.setText("");
            precio.setText("");
            stock.setText("");
            alerta.setText("");
            marca.setText("");
            categoria.setSelectedIndex(0);
            provedor.setSelectedIndex(0);
            modal.dispose();
        });

        guardar.addActionListener(e -> {
            String nombreProducto = nombre.getText();
            String marcaProducto = marca.getText();
            ComboItem categoriaSelected = (ComboItem) categoria.getSelectedItem();
            assert categoriaSelected != null;
            int categoriaProducto = categoriaSelected.getCodigo() ;
            double precioVenta = Double.parseDouble(precio.getText().replaceAll("[^0-9]",""));
            double precioCompra = Double.parseDouble(compra.getText().replaceAll("[^0-9]",""));
            int stockProducto = Integer.parseInt(stock.getText());
            int alertaStock = Integer.parseInt(alerta.getText());
            ComboItem proveedorSelected = (ComboItem) provedor.getSelectedItem();
            assert proveedorSelected != null;
            int codigoProveedor = proveedorSelected.getCodigo();


            Producto nuevo = new Producto(nombreProducto,marcaProducto,categoriaProducto,"",precioVenta,precioCompra,stockProducto,alertaStock,codigoProveedor);
            nuevo.setImg(src);
            try {
                for(Producto p: crudProducto.listarTodo()){
                    if(p.getNombreProducto().equals(nuevo.getNombreProducto())){
                        JOptionPane.showMessageDialog(this, "El Producto "+nuevo.getNombreProducto()+" ya existe", "Error: Producto Existente", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                crudProducto.agregar(nuevo);
                JOptionPane.showMessageDialog(this, "El Producto"+nuevo.getNombreProducto()+" ha sido Agregado exitosamente", "Producto Agregado", JOptionPane.INFORMATION_MESSAGE);
                nombre.setText("");
                compra.setText("");
                precio.setText("");
                stock.setText("");
                alerta.setText("");
                marca.setText("");
                categoria.setSelectedIndex(0);
                provedor.setSelectedIndex(0);
                dialog.dispose();

            } catch (Exception ex) {
                JDialog mensaje = new JDialog(ventanaPrincipal, "Error", true);
                mensaje.setSize(300, 100);
                mensaje.setLocationRelativeTo(null);
                mensaje.setLayout(new FlowLayout(FlowLayout.CENTER));
                mensaje.add(new JLabel("No se pudo Agregar el Producto, error: "+ex.getMessage()));
            }
            ventanaPrincipal.showCard("Productos");
            actualizarProductos();
        });



        return panel;
    }

    public JPanel getPanelModificar (Producto producto) throws Exception{
        CategoriaCrudl crud = new CategoriaCrudl();
        ProveedorCrudl pcrud = new ProveedorCrudl();
        ProductoCrudl crudProducto = new ProductoCrudl();
        JPanel panel = new JPanel(new BorderLayout(10,10));
        JPanel panelTittle = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 40));

        JPanel linea4 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        panel.setMinimumSize(new Dimension(200, 200));
        panel.setPreferredSize(new Dimension(200, 200));


        ImageIcon details = new ImageIcon("src/main/resources/images/icons/productodetail.png");
        Image iconDetails = details.getImage().getScaledInstance(64, 64, Image.SCALE_AREA_AVERAGING);
        details.setImage(iconDetails);

        JLabel titulo = new JLabel(" Detalles del Producto "+producto.getNombreProducto().toUpperCase(),details,SwingConstants.CENTER);

        titulo.setForeground(new Color(0x1AA6C6));
        titulo.setFont(new Font("Cascadia Code PL", Font.BOLD, 46));

        JButton editar = botonModificar;
        editar.setBackground(new Color(0x1AA6C6));
        editar.setForeground(new Color(0x07424C));
        editar.setFocusPainted(false);

        JTextField nombre = new JTextField(producto.getNombreProducto());
        nombre.setPreferredSize(new Dimension(150, 30));
        nombre.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        nombre.setMinimumSize(new Dimension(150, 30));
        nombre.setEditable(false);


        JTextField compra = new JTextField(Principal.convertirDivisa(producto.getPrecioDeCompra()));
        compra.setPreferredSize(new Dimension(Integer.MAX_VALUE, 30));
        compra.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        compra.setMinimumSize(new Dimension(150, 30));
        compra.setEditable(false);


        JTextField precio = new JTextField(Principal.convertirDivisa(producto.getPrecio()));
        precio.setPreferredSize(new Dimension(Integer.MAX_VALUE, 30));
        precio.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        precio.setMinimumSize(new Dimension(150, 30));
        precio.setEditable(false);

        JTextField stock = new JTextField(String.valueOf(producto.getStock()));
        stock.setPreferredSize(new Dimension(150, 30));
        stock.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        stock.setMinimumSize(new Dimension(150, 30));
        stock.setEditable(false);

        JTextField marca = new JTextField(producto.getMarca());
        marca.setPreferredSize(new Dimension(150, 30));
        marca.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        marca.setMinimumSize(new Dimension(150, 30));
        marca.setEditable(false);

        JTextField alerta = new JTextField(String.valueOf(producto.getAlerta()));
        alerta.setPreferredSize(new Dimension(150, 30));
        alerta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        alerta.setMinimumSize(new Dimension(150, 30));
        alerta.setEditable(false);

        JComboBox<ComboItem> categoria = new JComboBox<>();
        ComboItem cateogriaActual = new ComboItem(crud.buscar(producto.getCategoria()).getNombre(),producto.getCategoria());
        categoria.addItem(cateogriaActual);
        categoria.setSelectedItem(cateogriaActual);
        for(Categoria cat: crud.listarTodo()){
            if (!cat.getCodigo().equals(String.valueOf(cateogriaActual.getCodigo()))) {
                ComboItem item = new ComboItem(cat.getNombre(),Integer.parseInt(cat.getCodigo()));
                categoria.addItem(item);
            }
        }
        categoria.setEditable(false);
        Dimension comboSize = new Dimension(150, 30);

        categoria.setPreferredSize(comboSize);
        categoria.setMaximumSize(comboSize);
        categoria.setMinimumSize(comboSize);
        ((JLabel)categoria.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);


        JComboBox<ComboItem> provedor = new JComboBox<>();
        ComboItem provedorActual = new ComboItem(pcrud.buscar(String.valueOf(producto.getCodigoDelProveedor())).getNombre(),producto.getCodigoDelProveedor());
        provedor.addItem(provedorActual);
        provedor.setSelectedItem(provedorActual);
        for(Provedor pro : pcrud.listarTodo()){
            if (!pro.getId().equals(String.valueOf(provedorActual.getCodigo()))) {
                ComboItem item = new ComboItem(pro.getNombre(),Integer.parseInt(pro.getId()));
                provedor.addItem(item);
            }
        }
        provedor.setEditable(false);
        provedor.setPreferredSize(comboSize);
        provedor.setMaximumSize(comboSize);
        provedor.setMinimumSize(comboSize);
        ((JLabel)provedor.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);


        JButton cancelar = new JButton("✖️ Cancelar");
        JButton guardar = new JButton("✔️ guardar cambios");

        cancelar.setBackground(new Color(0xF44343));
        cancelar.setForeground(new Color(0x4A0011));
        guardar.setBackground(new Color(0x4CAF50));
        guardar.setForeground(new Color(0x003300));

        linea4.add(cancelar);
        linea4.add(guardar);

        LabelValue etiquetaNombre = new LabelValue("Nombre: ",producto.getNombreProducto());
        LabelValue etiquetaMarca = new LabelValue("Marca: ",producto.getMarca());
        LabelValue etiquetaStock = new LabelValue("Stock: ",producto.getStock()+" ");
        LabelValue etiquetaAlerta = new LabelValue("Alerta: ",producto.getAlerta()+" ");
        LabelValue etiquetaCategoria = new LabelValue("Categoria: ",crud.buscar(producto.getCategoria()).getNombre());
        LabelValue etiquetaProveedor = new LabelValue("Proveedor: ",pcrud.buscar(String.valueOf(producto.getCodigoDelProveedor())).getNombre());
        LabelValue etiquetaCompra = new LabelValue("Precio de compra: ",Principal.convertirDivisa(producto.getPrecioDeCompra())+" ");
        LabelValue etiquetaPrecio = new LabelValue("Precio de venta: ",Principal.convertirDivisa(producto.getPrecio())+" ");



        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3,3,5,5));


        center.add(etiquetaNombre);
        center.add(etiquetaCompra);
        center.add(etiquetaPrecio);

        center.add(etiquetaStock);
        center.add(etiquetaMarca);
        center.add(etiquetaAlerta);

        center.add(etiquetaCategoria);
        center.add(etiquetaProveedor);


        panel.add(titulo, BorderLayout.NORTH);
        panel.add(center, BorderLayout.CENTER);
        panel.add(linea4, BorderLayout.SOUTH);

        Container botonesBottom = new Container();
        botonesBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        botonesBottom.add(editar);

        botonEliminar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        botonModificar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        botonEliminar.setBackground(new Color(0xB53838));
        botonesBottom.add(botonEliminar);


        panel.add(botonesBottom, BorderLayout.SOUTH);

        editar.addActionListener(e -> {
            center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
            ImageIcon editarIMG = new ImageIcon("src/main/resources/images/icons/editar-producto.png");
            Image edit = editarIMG.getImage().getScaledInstance(32, 32, Image.SCALE_AREA_AVERAGING);
            details.setImage(edit);
            dialog.setTitle("Modificar Producto "+producto.getNombreProducto().toUpperCase());
            dialog.setSize(430,700);
            dialog.setLocationRelativeTo(null);
            titulo.setFont(new Font("Cascadia Code PL", Font.BOLD, 26));
            linea4.setVisible(true);
            panel.add(linea4, BorderLayout.SOUTH);
            linea4.requestFocusInWindow();
            botonesBottom.setVisible(false);
            titulo.setText("Modificar Producto");
            nombre.setEditable(true);
            compra.setEditable(true);
            precio.setEditable(true);
            stock.setEditable(true);
            alerta.setEditable(true);
            marca.setEditable(true);
            categoria.setEditable(false);
            provedor.setEditable(false);
            etiquetaMarca.getLabel().setText("Marca: ");
            etiquetaMarca.getValue().setVisible(false);
            etiquetaMarca.add(marca);
            marca.setVisible(true);
            marca.requestFocusInWindow();

            etiquetaNombre.getLabel().setText("Nombre: ");
            etiquetaNombre.getValue().setVisible(false);
            etiquetaNombre.add(nombre);
            nombre.setVisible(true);
            nombre.requestFocusInWindow();

            etiquetaStock.getLabel().setText("Stock: ");
            etiquetaStock.getValue().setVisible(false);
            etiquetaStock.add(stock);
            stock.setVisible(true);
            stock.requestFocusInWindow();

            etiquetaAlerta.getLabel().setText("Alerta: ");
            etiquetaAlerta.getValue().setVisible(false);
            etiquetaAlerta.add(alerta);
            alerta.setVisible(true);
            alerta.requestFocusInWindow();

            etiquetaCategoria.getLabel().setText("Categoria: ");
            etiquetaCategoria.getValue().setVisible(false);
            etiquetaCategoria.add(categoria);
            categoria.setVisible(true);
            categoria.requestFocusInWindow();

            etiquetaProveedor.getLabel().setText("Proveedor: ");
            etiquetaProveedor.getValue().setVisible(false);
            etiquetaProveedor.add(provedor);
            provedor.setVisible(true);
            provedor.setSize(new Dimension(150, 20));
            provedor.requestFocusInWindow();


            etiquetaCompra.getLabel().setText("Precio de compra: ");
            etiquetaCompra.getValue().setVisible(false);
            etiquetaCompra.add(compra);
            compra.setVisible(true);
            compra.requestFocusInWindow();

            etiquetaPrecio.getLabel().setText("Precio de venta: ");
            etiquetaPrecio.getValue().setVisible(false);
            etiquetaPrecio.add(precio);
            precio.setVisible(true);
            precio.requestFocusInWindow();

            JButton imgChange = new JButton("Cambiar imagen");
            imgChange.setBackground(new Color(0x1AA6C6));
            imgChange.addActionListener(this::actionPerformed);

            center.add(label);
            center.add(imgChange);


        });

        cancelar.addActionListener(e -> {
            botonesBottom.setVisible(true);
            botonesBottom.requestFocusInWindow();
            dialog.setTitle("Detalles del Producto "+producto.getNombreProducto().toUpperCase());
            nombre.setText(producto.getNombreProducto());
            compra.setText(Principal.convertirDivisa(producto.getPrecioDeCompra()));
            precio.setText(Principal.convertirDivisa(producto.getPrecio()));
            stock.setText(producto.getStock()+" ");
            alerta.setText(producto.getAlerta()+" ");
            marca.setText(producto.getMarca()+" ");
            categoria.setSelectedItem(cateogriaActual);
            provedor.setSelectedItem(provedorActual);
            titulo.setText("Detalles del Producto "+producto.getNombreProducto().toUpperCase());
            editar.requestFocusInWindow();
            botonEliminar.requestFocusInWindow();
            nombre.setEditable(false);
            compra.setEditable(false);
            precio.setEditable(false);
            stock.setEditable(false);
            alerta.setEditable(false);
            marca.setEditable(false);
            categoria.setEditable(false);
            provedor.setEditable(false);
            panel.remove(linea4);
            dialog.dispose();
        });

        guardar.addActionListener(e -> {

            try {
                producto.setNombreProducto(nombre.getText());
                producto.setMarca(marca.getText());
                producto.setStock(Integer.parseInt(stock.getText()));
                producto.setAlerta(Integer.parseInt(alerta.getText()));
                String convertirCompra = compra.getText().replaceAll("[$., COP]", "");
                Double precioCompra = Double.parseDouble(convertirCompra);
                String convertirPrecio = precio.getText().replaceAll("[$., COP]", "");
                Double precioVenta = Double.parseDouble(convertirPrecio);
                producto.setPrecioDeCompra(precioCompra);
                producto.setPrecio(precioVenta);
                producto.setCategoria(Integer.parseInt(String.valueOf(((ComboItem) categoria.getSelectedItem()).getCodigo())));
                producto.setCodigoDelProveedor(Integer.parseInt(String.valueOf(((ComboItem) provedor.getSelectedItem()).getCodigo())));
                Producto update = new Producto(nombre.getText(),marca.getText(),((ComboItem) categoria.getSelectedItem()).getCodigo(),producto.getCodigo(),precioVenta,precioCompra,Integer.parseInt(stock.getText()),Integer.parseInt(alerta.getText()),((ComboItem) provedor.getSelectedItem()).getCodigo());
                if(src!=null){
                    update.setImg(src);
                }else{
                    update.setImg("default.png");
                }
                crudProducto.editar(update);
                actualizarProductos();
                dialog.dispose();
                JOptionPane.showMessageDialog(this, "Producto actualizado exitosamente", "Producto Actualizado", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException ex) {
               JOptionPane.showMessageDialog(this, "No se pudo actualizar el Producto, error: "+ex.getMessage(), "Error: Producto No Actualizado", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,"Error: "+ex.getMessage(), "Error: Producto No Actualizado", JOptionPane.ERROR_MESSAGE);
            }
        });


        return panel;
    }

    public JButton getCancelar() {
        return cancelar;
    }

    public void setCancelar(JButton cancelar) {
        this.cancelar = cancelar;
    }

    public JButton getGuardar() {
        return guardar;
    }

    public void setGuardar(JButton guardar) {
        this.guardar = guardar;
    }

    public void setVentanaPrincipal(Ventana ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    public void cargarProductos(){
        ProductoCrudl crud = new ProductoCrudl();
        for(Producto p: crud.listarTodo()){
            BotonModerno producto = new BotonModerno("<html><font size='7' >"+p.getNombreProducto()+"</font><hr> <font size='5'>Precio:</font> "+ Principal.convertirDivisa(p.getPrecio())+" Und: "+p.getStock()+"</html>");
            producto.pack();
            String urlImage = p.getImg() != null ? p.getImg() : "default.png";
            ImageIcon imagen = Ventana.redimensionarImagen("src/main/resources/images/productos/"+urlImage, 64, 64);
            producto.setIcon(imagen);
            producto.addActionListener(e -> {
                dialog = new JDialog(ventanaPrincipal, "Detalles del Producto "+p.getNombreProducto().toUpperCase(), true);
                dialog.setSize(1280, 450);
                dialog.setIconImage(new ImageIcon(p.getImg()).getImage());
                dialog.setLocationRelativeTo(null);
                dialog.setLayout(new BorderLayout(10, 10));
                try {
                    dialog.add(getPanelModificar(p), BorderLayout.CENTER);
                    dialog.setResizable(true);
                } catch (Exception ex) {
                    JDialog mensaje = new JDialog(ventanaPrincipal, "Error: "+ex.getMessage(), true);
                    mensaje.setSize(300, 100);
                    mensaje.setLocationRelativeTo(null);
                }
                dialog.setVisible(true);
                ventanaPrincipal.showCard("Productos");
                dialog.dispose();

                botonEliminar.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int borrar = JOptionPane.showConfirmDialog(ventanaPrincipal,"Esta seguro de eliminar el producto "+p.getNombreProducto()+"?","Eliminar Producto",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                        if(borrar == 0){
                            try{
                                crud.eliminar(p.getCodigo());
                                JOptionPane.showMessageDialog(ventanaPrincipal,"Producto "+p.getNombreProducto()+" eliminado exitosamente","Producto Eliminado",JOptionPane.INFORMATION_MESSAGE);
                                dialog.dispose();
                                ventanaPrincipal.dispose();
                                ventanaPrincipal.setVisible(true);
                            }catch (Exception ex){
                                JOptionPane.showMessageDialog(ventanaPrincipal, "No se pudo eliminar el producto "+p.getNombreProducto()+"\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        actualizarProductos();
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
            });
            panelBotones.add(producto, SwingConstants.CENTER);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        int result;

        switch (e.getActionCommand()) {
            case "Cambiar imagen":
                result = chooser.showDialog(this, "Elija la imagen del producto");
                if(result == JFileChooser.APPROVE_OPTION){
                    File selecionada = chooser.getSelectedFile();
                    String ruta = "src/main/resources/images/productos/";
                    File carpeta = new File(ruta);

                    if(!carpeta.exists()){
                        carpeta.mkdirs();
                    }

                    File archivo = new File(ruta+selecionada.getName());

                    src="src/main/resources/images/productos/"+selecionada.getName();

                    try{
                        Files.copy(selecionada.toPath(), archivo.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        label.setText("Archivo Seleccionado: "+archivo.getName());
                        label.setIcon(Ventana.redimensionarImagen(src, 64, 64));

                    }catch (IOException ex){
                        JOptionPane.showMessageDialog(this, "No se pudo cambiar la imagen del producto, error: "+ex.getMessage(), "Error: No se pudo cambiar la imagen del producto", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    label.setText("No se selecciono ningun archivo");
                }
            break;
        }
    }
}
