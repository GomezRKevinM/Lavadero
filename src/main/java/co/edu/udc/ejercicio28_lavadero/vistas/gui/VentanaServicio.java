package co.edu.udc.ejercicio28_lavadero.vistas.gui;

import co.edu.udc.ejercicio28_lavadero.Principal;
import co.edu.udc.ejercicio28_lavadero.modelo.crud.CategoriaCrudl;
import co.edu.udc.ejercicio28_lavadero.modelo.crud.ServicioCrudl;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Categoria;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Empleado;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Servicio;
import co.edu.udc.ejercicio28_lavadero.vistas.gui.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


public class VentanaServicio extends JPanel{
    private JPanel panelContenido;
    private Ventana ventanaPrincipal;
    private JLabel label = new JLabel("");
    private String src = "";

    private JButton botonAgregar = new JButton("Agregar Servicio",
            Ventana.redimensionarImagen("src/main/resources/images/icons/services.png",34,34));
    private JButton botonModificar = new JButton("Modificar Servicio",
            Ventana.redimensionarImagen("src/main/resources/images/icons/editar.png",20,20));
    private JButton botonEliminar = new JButton("Eliminar Servicio",
            Ventana.redimensionarImagen("src/main/resources/images/icons/papelera-de-reciclaje.png",20,20));
    private JButton btnSearch = new JButton("Buscar Servicio");
    private JPanel panelBotones = new JPanel();
    private JButton cancelar = new JButton("✖️ Cancelar");
    private JButton guardar = new JButton("✔️ Agregar Servicio");
    private JScrollPane scrollPane;

    public VentanaServicio(){
        panelContenido = new JPanel();
        panelContenido.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelContenido.setPreferredSize(new Dimension(getWidth()-20, getHeight()-100));
        panelContenido.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        panelContenido.setMinimumSize(new Dimension(200, 200));
        panelContenido.setLayout(new BorderLayout(10, 10));

        panelBotones.setPreferredSize(null); // Permite usar el layout manager
        panelBotones.setMaximumSize(null);
        panelBotones.setMinimumSize(new Dimension(200, 200));
        panelBotones.setLayout(new GridLayout(0, 1, 10, 10));
        panelBotones.setBorder(null);

        cargarServicios();

        JScrollPane scrollPane = new JScrollPane(panelBotones);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelContenido.add(scrollPane, BorderLayout.CENTER);

        JLabel titulo = new JLabel("SERVICIOS", SwingConstants.CENTER);
        titulo.setForeground(new Color(0x1AA6C6));
        titulo.setFont(new Font("Arial", Font.BOLD, 46));
        panelContenido.add(titulo, BorderLayout.NORTH);

        InputIcon buscador = new InputIcon(
                Ventana.redimensionarImagen("src/main/resources/images/icons/lupa.png",30,30),
                "Buscar Servicio"
        );
        buscador.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buscador.getTextField().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel btnContent = new JPanel();
        btnContent.setLayout(new BoxLayout(btnContent, BoxLayout.X_AXIS));
        btnContent.add(botonAgregar);
        botonAgregar.addActionListener(actionEvent -> {
            agregarServicio();
        });
        btnContent.add(Box.createHorizontalStrut(10));
        btnContent.add(buscador);

        buscador.getTextField().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    ServicioCrudl crud = new ServicioCrudl();
                    panelBotones.removeAll();
                    panelBotones.revalidate();
                    panelBotones.repaint();

                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.weightx = 1.0;
                    gbc.weighty = 1.0;
                    gbc.fill = GridBagConstraints.NONE;

                    try{
                        String codigo = buscador.getTextField().getText();
                        ArrayList<Servicio> servicios = crud.buscar(codigo);
                        if (servicios.size() > 0) {
                            for(Servicio service: servicios){
                                BotonModerno servicio = new BotonModerno("<html> <div style='display:flex; justify-content:space-between'> <font size='7'>"+service.getNombre()+" <hr>"+"</font>   <font size='5' style='font-family: cursive; color: #333'>precio:</font> <font>"+Principal.convertirDivisa(service.getPrecioDeVenta())+"</span>"+"</div> </html>",700,90);
                                String imagen = service.getImagen() != null ? service.getImagen() : "services.png";
                                servicio.setIcon(Ventana.redimensionarImagen("src/main/resources/images/servicios/"+service.getImagen(), 60, 60));

                                servicio.addActionListener(listenen -> {
                                    JDialog modal = new JDialog();
                                    modal.setModal(true);
                                    modal.setSize(600, 400);
                                    modal.setLocationRelativeTo(null);
                                    modal.setTitle("Informacion del servicio "+service.getNombre());
                                    modal.setIconImage(new ImageIcon("src/main/resources/images/servicios/"+imagen).getImage());
                                    modal.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

                                    JLabel ImagenLateral = new JLabel(Ventana.redimensionarImagen("src/main/resources/images/servicios/"+imagen, 64, 64));
                                    JPanel informacion = new JPanel();
                                    informacion.setLayout(new BoxLayout(informacion, BoxLayout.Y_AXIS) );

                                    LabelValue nombre = new LabelValue("Nombre: ", service.getNombre());
                                    nombre.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));


                                    LabelValue precio = new LabelValue("Precio: ", Principal.convertirDivisa(service.getPrecioDeVenta()));
                                    precio.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

                                    LabelValue descripcion = new LabelValue("Descripcion: ", service.getDescripcion());
                                    descripcion.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

                                    LabelValue categoria = new LabelValue("Categoria: ", service.getCategoria().getNombre());
                                    categoria.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

                                    LabelValue empleados = new LabelValue("Funcionarios: ",service.getFuncionario()!=null?service.getFuncionario().stream().map(Empleado::getNombre).collect(Collectors.joining(", ")): "No asignados");
                                    empleados.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

                                    Boton modificar = new Boton("cambiar","Modificar Servicio");
                                    Boton eliminar = new Boton("cancelar","Eliminar Servicio");



                                    JPanel botones = new JPanel();
                                    botones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                                    botones.add(modificar);
                                    botones.add(eliminar);

                                    informacion.add(nombre);
                                    informacion.add(precio);
                                    informacion.add(descripcion);
                                    informacion.add(categoria);
                                    informacion.add(empleados);

                                    modificar.addActionListener(e1 -> {
                                        nombre.setValue(new JLabel(""));
                                        Input nombreInput = new Input(service.getNombre());
                                        nombre.add(nombreInput);

                                        precio.setValue(new JLabel(""));
                                        Input precioInput = new Input(Principal.convertirDivisa(service.getPrecioDeVenta()));
                                        precio.add(precioInput);

                                        descripcion.setValue(new JLabel(""));
                                        Input descripcionInput = new Input(service.getDescripcion());
                                        descripcion.add(descripcionInput);

                                        categoria.setValue(new JLabel(""));
                                        JComboBox<ComboItem> categoriaSelected = new JComboBox<>();
                                        for(Categoria cat: new CategoriaCrudl().listarTodo() ){
                                            ComboItem Categoria = new ComboItem(cat.getNombre(),Integer.parseInt(cat.getCodigo()));
                                            categoriaSelected.addItem(Categoria);
                                        }
                                        categoria.add(categoriaSelected);

                                        empleados.setValue(new JLabel(""));
                                        Boton asignar = new Boton("modificar","Asignar Funcionario");
                                        asignar.addActionListener(e2 -> {

                                        });
                                        Boton eliminarEmpleado = new Boton("cancelar","Eliminar Funcionario");


                                        botones.removeAll();
                                        String iconoOld = service.getImagen();

                                        Boton guardar = new Boton("guardar","Guardar Cambios");
                                        Boton cancelar = new Boton("cancelar","Cancelar");
                                        cancelar.addActionListener(e2 -> {
                                            try{
                                                if(!iconoOld.equals("services.png") && !iconoOld.equals(imagen)){
                                                    Files.deleteIfExists(Path.of("src/main/resources/images/servicios/"+iconoOld));
                                                }
                                            }catch(IOException error){
                                                JOptionPane.showMessageDialog(null, "Error al eliminar el icono del servicio");
                                            }
                                            modal.dispose();
                                        });
                                        botones.add(guardar);
                                        botones.add(cancelar);
                                        modal.repaint();
                                        modal.revalidate();
                                        modal.setAlwaysOnTop(true);
                                    });
                                    eliminar.addActionListener(e1 -> {
                                        try{
                                            if(imagen != null && !imagen.equals("services.png")){
                                                Files.deleteIfExists(Path.of("src/main/resources/images/servicios/"+imagen));
                                            }
                                            crud.eliminar(service.getCodigo());
                                        }catch (SQLException error){
                                            JOptionPane.showMessageDialog(null, "Error al eliminar el servicio");
                                        }catch (IOException error){
                                            JOptionPane.showMessageDialog(null, "Error al eliminar icono del el servicios");
                                        }
                                    });

                                    modal.add(ImagenLateral);
                                    modal.add(informacion);
                                    modal.add(botones);
                                    modal.setVisible(true);
                                    modal.repaint();
                                    modal.revalidate();
                                    modal.setAlwaysOnTop(true);
                                    modal.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
                                    modal.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                                });
                                panelBotones.add(servicio,gbc);
                            }
                        }else{
                            JPanel mensaje = new JPanel();
                            mensaje.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                            LabelValue resultado = new LabelValue("No hay servicios con el nombre o codigo: ",buscador.getTextField().getText());
                            resultado.getValue().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                            resultado.getValue().setOpaque(true);
                            resultado.getValue().setBackground(new Color(0x010F13));
                            resultado.getValue().setForeground(new Color(0x6FCCE8));
                            resultado.getValue().setIcon(Ventana.redimensionarImagen("src/main/resources/images/icons/lupa.png", 30, 30));
                            mensaje.add(resultado);
                            panelBotones.add(mensaje);
                        }
                    }catch (SQLException exception){
                        JOptionPane.showMessageDialog(null, "Error: "+exception.getMessage());
                    }
                    panelContenido.revalidate();
                    panelContenido.repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(buscador.getTextField().getText().equals(buscador.getPrompt()) || buscador.getTextField().getText().equals("")){
                    cargarServicios();
                    System.out.println("Cargando todos los servicios");
                }
            }
        });

        btnContent.setOpaque(true);
        btnContent.setBackground(new Color(0x1AA6C6));
        btnContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelContenido.add(btnContent, BorderLayout.SOUTH);
        panelContenido.setBorder(null);

    }

    public JPanel getPanelContenido() {
        return panelContenido;
    }

    public void cargarServicios() {
        ServicioCrudl crud = new ServicioCrudl();
        panelBotones.removeAll();
        panelBotones.revalidate();
        panelBotones.repaint();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;

        try{
            for(Servicio service: crud.listarTodo()){
                BotonModerno servicio = new BotonModerno("<html> <div style='display:flex; justify-content:space-between'> <font size='7'>"+service.getNombre()+" <hr>"+"</font>   <font size='5' style='font-family: cursive; color: #333'>precio:</font> <font>"+Principal.convertirDivisa(service.getPrecioDeVenta())+"</span>"+"</div> </html>",700,90);
                String imagen = service.getImagen() != null ? service.getImagen() : "services.png";
                servicio.setIcon(Ventana.redimensionarImagen("src/main/resources/images/servicios/"+service.getImagen(), 60, 60));

                servicio.addActionListener(e -> {
                    JDialog modal = new JDialog();
                    modal.setModal(true);
                    modal.setSize(600, 400);
                    modal.setLocationRelativeTo(null);
                    modal.setTitle("Informacion del servicio "+service.getNombre());
                    modal.setIconImage(new ImageIcon("src/main/resources/images/servicios/"+imagen).getImage());
                    modal.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

                    JPanel informacion = new JPanel();
                    informacion.setLayout(new BoxLayout(informacion, BoxLayout.Y_AXIS) );

                    LabelValue nombre = new LabelValue("Nombre: ", service.getNombre());
                    nombre.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));


                    LabelValue precio = new LabelValue("Precio: ", Principal.convertirDivisa(service.getPrecioDeVenta()));
                    precio.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

                    LabelValue descripcion = new LabelValue("Descripcion: ", service.getDescripcion());
                    descripcion.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

                    LabelValue categoria = new LabelValue("Categoria: ", service.getCategoria().getNombre());
                    categoria.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

                    JLabel ImagenLateral = new JLabel(Ventana.redimensionarImagen("src/main/resources/images/servicios/"+imagen, 64, 64));

                    LabelValue empleados = new LabelValue("Funcionarios: ",service.getFuncionario()!=null?service.getFuncionario().stream().map(Empleado::getNombre).collect(Collectors.joining(", ")): "No asignados");
                    empleados.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

                    Boton modificar = new Boton("cambiar","Modificar Servicio");
                    Boton eliminar = new Boton("cancelar","Eliminar Servicio");



                    JPanel botones = new JPanel();
                    botones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                    botones.add(modificar);
                    botones.add(eliminar);

                    informacion.add(nombre);
                    informacion.add(precio);
                    informacion.add(descripcion);
                    informacion.add(categoria);
                    informacion.add(empleados);
                    JButton imgChange = new JButton("cambiar imagen");

                    imgChange.addActionListener(e1 -> {
                        JFileChooser chooser = new JFileChooser();
                        int result = chooser.showDialog(modal, "Elija la imagen del servicio");
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File seleccionada = chooser.getSelectedFile();
                            String ruta = "src/main/resources/images/servicios/";
                            File carpeta = new File(ruta);

                            if (!carpeta.exists()) {
                                carpeta.mkdirs();
                            }

                            File archivo = new File(ruta + seleccionada.getName());
                            src = "src/main/resources/images/servicios/" + seleccionada.getName();

                            try {
                                Files.copy(seleccionada.toPath(), archivo.toPath(),
                                        StandardCopyOption.REPLACE_EXISTING);
                                label.setText("Archivo Seleccionado: " + archivo.getName());
                                label.setIcon(Ventana.redimensionarImagen(src, 64, 64));
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(this,
                                        "No se pudo cambiar la imagen del servicio, error: " + ex.getMessage(),
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            label.setText("No se seleccionó ningún archivo");
                        }
                    });

                    modificar.addActionListener(e1 -> {
                        modal.add(label);
                        modal.add(imgChange);
                        modal.setSize(650, 430);
                        nombre.getValue().setVisible(false);
                        Input nombreInput = new Input(service.getNombre());
                        nombreInput.setPreferredSize(new Dimension(250, 30));
                        nombre.add(nombreInput);

                        precio.getValue().setVisible(false);
                        Input precioInput = new Input(Principal.convertirDivisa(service.getPrecioDeVenta()));
                        precioInput.setPreferredSize(new Dimension(150, 30));
                        precio.add(precioInput);

                        descripcion.getValue().setVisible(false);
                        JTextArea descripcionInput = new JTextArea(service.getDescripcion());
                        descripcionInput.setPreferredSize(new Dimension(300, 80));
                        descripcionInput.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
                        descripcion.add(descripcionInput);

                        categoria.getValue().setVisible(false);
                        JComboBox<ComboItem> categoriaSelected = new JComboBox<>();
                        for(Categoria cat: new CategoriaCrudl().listarTodo() ){
                            ComboItem Categoria = new ComboItem(cat.getNombre(),Integer.parseInt(cat.getCodigo()));
                            categoriaSelected.addItem(Categoria);
                        }
                        categoria.add(categoriaSelected);

                        empleados.getValue().setVisible(false);
                        Boton asignar = new Boton("modificar","Asignar Funcionario");
                        asignar.addActionListener(e2 -> {

                        });
                        Boton eliminarEmpleado = new Boton("cancelar","Eliminar Funcionario");


                        botones.removeAll();
                        String iconoOld = service.getImagen();

                        Boton guardar = new Boton("guardar","Guardar Cambios");
                        Boton cancelar = new Boton("cancelar","Cancelar");
                        cancelar.addActionListener(e2 -> {
                            try{
                                if(!iconoOld.equals("services.png") && !iconoOld.equals(imagen)){
                                    Files.deleteIfExists(Path.of("src/main/resources/images/servicios/"+iconoOld));
                                }
                            }catch(IOException error){
                                JOptionPane.showMessageDialog(null, "Error al eliminar el icono del servicio");
                            }
                            modal.dispose();
                        });
                        guardar.addActionListener(guardadndo -> {
                            try{
                                modal.dispose();
                                service.setImagen(imagen);
                                service.setNombre(nombreInput.getText());
                                CategoriaCrudl categoriaCrudl = new CategoriaCrudl();
                                ComboItem categoriaSeleccionada = (ComboItem) categoriaSelected.getSelectedItem();
                                Categoria newCate = categoriaCrudl.buscar(categoriaSeleccionada.getCodigo());
                                service.setCategoria(newCate);
                                service.setPrecioDeVenta(Double.parseDouble(precioInput.getText().replaceAll("[^0-9]","")));
                                if(src != null && !src.equals("default.png")){
                                    service.setImagen(src.substring(src.lastIndexOf("/")+1));
                                }
                                crud.editar(service);
                                modal.dispose();
                                cargarServicios();
                            }catch (Exception error){
                                modal.dispose();
                                System.out.println(service.toString());
                                System.out.println("Error al modificar: "+error.getMessage());
                                JOptionPane.showMessageDialog(null, error.getMessage());
                            }
                        });
                        botones.add(guardar);
                        botones.add(cancelar);
                        modal.repaint();
                        modal.revalidate();
                        modal.setAlwaysOnTop(true);
                    });
                    eliminar.addActionListener(e1 -> {
                        try{
                            int response = JOptionPane.showConfirmDialog(modal,"Desea eliminar el servicio "+service.getNombre()+" ?");
                            if(response == 0){
                                System.out.println("Respondio que si");
                                crud.eliminar(service.getCodigo());
                                modal.dispose();
                            }else{
                                System.out.println("Respondio que no");
                            }
                            if(imagen != null && !imagen.equals("services.png")){
                                Files.deleteIfExists(Path.of("src/main/resources/images/servicios/"+imagen));
                            }
                            cargarServicios();
                        }catch (SQLException error){
                            JOptionPane.showMessageDialog(null, "Error al eliminar el servicio");
                        }catch (IOException error){
                            JOptionPane.showMessageDialog(null, "Error al eliminar icono del el servicios");
                        }
                    });

                    modal.add(ImagenLateral);
                    modal.add(informacion);
                    modal.add(botones);
                    modal.setVisible(true);
                    modal.repaint();
                    modal.revalidate();
                    modal.setAlwaysOnTop(true);
                    modal.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
                    modal.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


                });
                panelBotones.add(servicio,gbc);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al cargar los servicios");
        }

        panelContenido.revalidate();
        panelContenido.repaint();
    }

    public void setVentanaPrincipal(Ventana ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    public JButton getBotonAgregar() {
        return botonAgregar;
    }

    public void agregarServicio(){
        JDialog modal = new JDialog();
        modal.setModal(true);
        modal.setSize(600, 520);
        modal.setLocationRelativeTo(null);
        modal.setTitle("Agregar Servicio");
        modal.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        modal.setIconImage(new ImageIcon("src/main/resources/images/servicios/services.png").getImage());

        JPanel tituloPanel = new JPanel();
        tituloPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel titulo = new JLabel("Agregar Servicio",SwingConstants.CENTER);
        titulo.setForeground(new Color(0x1AA6C6));
        titulo.setFont(new Font("Arial", Font.BOLD, 46));
        tituloPanel.add(titulo);

        JPanel div = new JPanel();
        div.setLayout(new BoxLayout(div, BoxLayout.Y_AXIS));
        div.add(tituloPanel);
        div.add(Box.createVerticalStrut(10));

        JPanel panelImagen = new JPanel();
        panelImagen.setLayout(new BoxLayout(panelImagen, BoxLayout.X_AXIS));

        JPanel panelInputs = new JPanel();
        panelInputs.setLayout(new BoxLayout(panelInputs,BoxLayout.Y_AXIS));

        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido,BoxLayout.X_AXIS));

        contenido.add(panelImagen);
        contenido.add(panelInputs);
        div.add(contenido);

        JLabel imagen = new JLabel(Ventana.redimensionarImagen("src/main/resources/images/icons/services.png",64,64));

        panelImagen.add(imagen);
        Dimension inputs = new Dimension(400, 40);

        JPanel nombreLine = new JPanel();
        nombreLine.setLayout(new BoxLayout(nombreLine,BoxLayout.X_AXIS));
        nombreLine.setPreferredSize(inputs);
        nombreLine.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        nombreLine.setMinimumSize(new Dimension(Integer.MIN_VALUE, 40));
        nombreLine.setSize(inputs);
        LabelValue nombre = new LabelValue("Nombre: ", "");
        nombre.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        Input nombreInput = new Input("Nombre del servicio ");
        nombreLine.add(nombre);
        nombreLine.add(nombreInput);

        JPanel descripcionLine = new JPanel();
        descripcionLine.setLayout(new BoxLayout(descripcionLine,BoxLayout.X_AXIS));
        LabelValue descripcion = new LabelValue("Descripcion: ", "");
        descripcion.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JTextArea descripcionInput = new JTextArea("Descripción del servicio...");
        descripcionInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        descripcionInput.setLineWrap(true);
        descripcionInput.setWrapStyleWord(true);
        descripcionLine.add(descripcion);
        descripcionLine.add(descripcionInput);


        JPanel precioLine = new JPanel();
        precioLine.setLayout(new BoxLayout(precioLine,BoxLayout.X_AXIS));
        LabelValue precio = new LabelValue("Precio: ", "");
        precio.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        Input precioInput = new Input("Precio de venta ");
        precioInput.setSize(new Dimension(100, 10));
        precioLine.add(precio);
        precioLine.add(precioInput);

        AtomicReference<String> icono = new AtomicReference<>("services.png");
        JPanel iconoPanel = new JPanel();
        iconoPanel.setLayout(new BoxLayout(iconoPanel,BoxLayout.X_AXIS));
        LabelValue iconoLabel = new LabelValue("Icono: ", "");
        iconoLabel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        iconoPanel.setSize(inputs);
        iconoPanel.setPreferredSize(inputs);
        iconoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        iconoPanel.setMinimumSize(new Dimension(Integer.MIN_VALUE, 40));
        Boton imagenBtn = new Boton("modificar","Seleccionar Icono");

        iconoPanel.add(iconoLabel);
        iconoPanel.add(imagenBtn);

        JPanel disponibilidadLine = new JPanel();
        disponibilidadLine.setLayout(new BoxLayout(disponibilidadLine,BoxLayout.X_AXIS));
        disponibilidadLine.setPreferredSize(inputs);
        disponibilidadLine.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        disponibilidadLine.setMinimumSize(new Dimension(Integer.MIN_VALUE, 40));
        disponibilidadLine.setSize(inputs);
        LabelValue disponibilidad = new LabelValue("Disponibilidad: ", "");
        disponibilidad.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JComboBox<String> disponibilidadInput = new JComboBox<>(new String[]{"Disponible","No disponible"});
        disponibilidadInput.setSelectedIndex(0);
        disponibilidadLine.add(disponibilidad);
        disponibilidadLine.add(disponibilidadInput);

        CategoriaCrudl categoriaCrudl = new CategoriaCrudl();
        JPanel categoriaLine = new JPanel();
        categoriaLine.setLayout(new BoxLayout(categoriaLine,BoxLayout.X_AXIS));
        categoriaLine.setPreferredSize(inputs);
        categoriaLine.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        categoriaLine.setMinimumSize(new Dimension(Integer.MIN_VALUE, 50));
        categoriaLine.setSize(inputs);
        LabelValue categoria = new LabelValue("Categoria: ", "");
        JComboBox<ComboItem> categoriaSelected = new JComboBox<>();
        for(Categoria cat: categoriaCrudl.listarTodo() ){
            ComboItem Categoria = new ComboItem(cat.getNombre(),Integer.parseInt(cat.getCodigo()));
            categoriaSelected.addItem(Categoria);
        }
        categoriaLine.add(categoria);
        categoriaLine.add(categoriaSelected);

        panelInputs.add(nombreLine);
        panelInputs.add(Box.createVerticalStrut(10));
        panelInputs.add(descripcionLine);
        panelInputs.add(Box.createVerticalStrut(10));
        panelInputs.add(precioLine);
        panelInputs.add(Box.createVerticalStrut(10));
        panelInputs.add(iconoPanel);
        panelInputs.add(Box.createVerticalStrut(10));
        panelInputs.add(disponibilidadLine);
        panelInputs.add(Box.createVerticalStrut(10));
        panelInputs.add(categoriaLine);
        panelInputs.add(Box.createVerticalStrut(10));

        JPanel controles = new JPanel();
        controles.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));


        Boton guardar = new Boton("guardar","Guardar Servicio");
        Boton cancelar = new Boton("cancelar","Cancelar");
        cancelar.addActionListener(e -> {

            if(!icono.get().equals("services.png")){
                try{
                    Files.deleteIfExists(Path.of("src/main/resources/images/servicios/"+icono.get()));
                }catch (IOException ex){
                    JOptionPane.showMessageDialog(null, "Error al eliminar el archivo");
                }
            }
            modal.dispose();
        });

        imagenBtn.addActionListener(e ->{
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showDialog(this, "Elija la imagen del servicio");
            if (result == JFileChooser.APPROVE_OPTION) {
                File seleccionada = chooser.getSelectedFile();
                String ruta = "src/main/resources/images/servicios/";
                File carpeta = new File(ruta);

                if (!carpeta.exists()) {
                    carpeta.mkdirs();
                }

                File archivo = new File(ruta + seleccionada.getName());
                src = "src/main/resources/images/servicios/" + seleccionada.getName();

                try {
                    Files.copy(seleccionada.toPath(),archivo.toPath(),StandardCopyOption.REPLACE_EXISTING);
                    imagen.setIcon(Ventana.redimensionarImagen(src,64,64));
                    imagenBtn.setText("Cambiar imagen");
                    icono.set(archivo.getName());
                }catch (IOException ex){
                    JOptionPane.showMessageDialog(null, "Error al copiar el archivo");
                }
            }
        });

        guardar.addActionListener(e -> {
            String nombreService = nombreInput.getText();
            String descripcionService = descripcionInput.getText() != null ? descripcionInput.getText() : "Sin descripción" ;
            double precioVentaService;

            precioVentaService = Double.parseDouble(precioInput.getText().replaceAll("[^0-9]", ""));
            String imagenService = icono.get();
            boolean disponible = disponibilidadInput.getSelectedIndex() == 0;

            int catCode = ((ComboItem)categoriaSelected.getSelectedItem()).getCodigo();
            Categoria categoriaS = categoriaCrudl.buscar(catCode);
            Servicio nuevo = new Servicio(0,nombreService,descripcionService,precioVentaService,imagenService,disponible,categoriaS);

            ServicioCrudl crud = new ServicioCrudl();
            try {
                crud.agregar(nuevo);
                JOptionPane.showMessageDialog(null, "Servicio agregado correctamente");
                modal.dispose();
                cargarServicios();
            }catch (Exception error){
                JOptionPane.showMessageDialog(null, "Error al agregar el servicio: "+error.getMessage());
            }
        });

        controles.add(guardar);
        controles.add(cancelar);
        div.add(controles);

        modal.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Width: "+modal.getWidth()+" Height: "+modal.getHeight());
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

        JScrollPane scrollPane = new JScrollPane(div);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 50));

        modal.add(scrollPane);
        modal.setVisible(true);
        modal.repaint();
        modal.revalidate();
        modal.setAlwaysOnTop(true);
        modal.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        modal.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
}