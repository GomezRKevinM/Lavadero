package co.edu.udc.ejercicio28_lavadero.vistas.gui;

import co.edu.udc.ejercicio28_lavadero.modelo.crud.CategoriaCrudl;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Categoria;

import co.edu.udc.ejercicio28_lavadero.util.ConsultarData;
import co.edu.udc.ejercicio28_lavadero.vistas.gui.components.Boton;
import co.edu.udc.ejercicio28_lavadero.vistas.gui.components.IconDetail;
import co.edu.udc.ejercicio28_lavadero.vistas.gui.components.Input;
import co.edu.udc.ejercicio28_lavadero.vistas.gui.components.InputIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VentanaCategoria extends JPanel implements ActionListener {
    private final JPanel panelContenido;
    private Ventana ventanaPrincipal;
    private JDialog dialog = new JDialog();

    private final JButton botonAgregar = new JButton("Agregar Categoria",Ventana.redimensionarImagen("src/main/resources/images/icons/agregar-categoria.png",20,20));
    private final JButton botonModificar = new JButton("Modificar Categoria",Ventana.redimensionarImagen("src/main/resources/images/icons/editar.png",20,20));
    private final JButton botonEliminar = new JButton("Eliminar Categoria",Ventana.redimensionarImagen("src/main/resources/images/icons/papelera-de-reciclaje.png",20,20));
    private final JButton botonSearch = new JButton("Buscar Categoria");
    private final JPanel panelBotones = new JPanel();
    private final JButton cancelar = new JButton("✖️ Cancelar");
    private final JButton guardar = new JButton("✔️ Agregar Categoria");
    private final CategoriaCrudl crudCategoria = new CategoriaCrudl();
    private String iconSrc = "categorias.png";
    private JLabel Icono = new JLabel();
    private final JLabel src = new JLabel();
    


    public VentanaCategoria() {
        panelContenido = this;
        panelContenido.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelContenido.setPreferredSize(new Dimension(getWidth(), getHeight()));
        panelContenido.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        panelContenido.setMinimumSize(new Dimension(200, 200));
        panelContenido.setLayout(new BorderLayout(10, 10));

        panelBotones.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        panelBotones.setSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        panelBotones.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        panelBotones.setMinimumSize(new Dimension(200, 200));
        panelBotones.setLayout(new GridLayout(2, 8, 10, 10));


        cargarCategorias();

        JLabel titulo = new JLabel("CATEGORIAS",SwingConstants.CENTER);
        titulo.setForeground(new Color(0xFFB340));
        titulo.setFont(new Font("Arial", Font.BOLD, 46));
        panelContenido.add(titulo, BorderLayout.NORTH);

        JPanel btnContent = new JPanel();
        btnContent.setSize(Integer.MAX_VALUE, 50);
        btnContent.setLayout(new BoxLayout(btnContent, BoxLayout.X_AXIS));
        btnContent.setBackground(Color.darkGray);
        btnContent.setOpaque(true);
        btnContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        botonAgregar.addActionListener(this);
        btnContent.add(botonAgregar);
        btnContent.add(Box.createHorizontalStrut(10));
        InputIcon buscar = new InputIcon(Ventana.redimensionarImagen("src/main/resources/images/icons/lupa.png",50,50),"Buscar Categoria");
        btnContent.add(buscar);

        buscar.getTextField().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(buscar.getTextField().getText().length()>0 && !buscar.getTextField().getText().equals(buscar.getPrompt())){
                    String[] campos = new String[2];
                    campos[0] = "nombre";
                    campos[1] = "icono";
                    String resultados = ConsultarData.search("categorias",campos,buscar.getTextField().getText());
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        panelContenido.add(btnContent, BorderLayout.SOUTH);
        panelContenido.add(panelBotones, BorderLayout.CENTER);
    }

    public JPanel getPanelContenido() {
        return panelContenido;
    }

    public void cargarCategorias(){
        String iconoPATH = "src/main/resources/images/categorias/";
        ArrayList<Categoria> categorias = crudCategoria.listarTodo();
        int columnas = 4;
        int filas = (int) Math.ceil((double) categorias.size() / columnas);

        panelBotones.setLayout(new GridLayout(filas, columnas, 10, 10));

        for(Categoria cat : categorias) {
            IconDetail categoria = new IconDetail(iconoPATH+cat.getIcono(), cat.getNombre(),crudCategoria.contarItems(cat.getCodigo()));
            categoria.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    dialog.getContentPane().removeAll();
                    dialog.repaint();
                    dialog.revalidate();
                    Icono.setIcon(Ventana.redimensionarImagen("src/main/resources/images/categorias/" + cat.getIcono(), 50, 50));
                    src.setText("Icono seleccionado: " + cat.getIcono());
                    Container info = new Container();
                    info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
                    dialog.setModal(true);
                    dialog.setSize(585, 217);
                    dialog.setLocationRelativeTo(null);

                    ImageIcon imagen = Ventana.redimensionarImagen("src/main/resources/images/Categorias/" + cat.getIcono(), 150, 150);
                    dialog.setIconImage(imagen.getImage());
                    Icono = new JLabel(imagen);
                    dialog.add(Icono);

                    dialog.setTitle("Informacion de la Categoria " + cat.getNombre());
                    dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                    JPanel nombreCategoria = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
                    JLabel etiquetaNombre = new JLabel("Nombre: ");
                    Input nombre = new Input("Nuevo nombre");
                    etiquetaNombre.setFont(new Font("Cascadia Code Pl", Font.BOLD, 18));
                    etiquetaNombre.setForeground(new Color(0x07424C));
                    nombreCategoria.add(etiquetaNombre);
                    nombreCategoria.add(new JLabel(cat.getNombre()));
                    info.add(nombreCategoria);

                    JPanel codigoCategoria = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
                    JLabel etiquetaCodigo = new JLabel("Codigo : ");
                    Boton cambiar = new Boton("cambiar", "cambiar iconSrc");
                    cambiar.addActionListener(VentanaCategoria.this::actionPerformed);
                    etiquetaCodigo.setFont(new Font("Cascadia Code Pl", Font.BOLD, 18));
                    etiquetaCodigo.setForeground(new Color(0x07424C));
                    codigoCategoria.add(etiquetaCodigo);
                    codigoCategoria.add(new JLabel(cat.getCodigo()));
                    info.add(codigoCategoria);

                    JPanel src = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
                    JLabel etiquetaSrc = new JLabel("Icono: ");
                    etiquetaSrc.setFont(new Font("Cascadia Code Pl", Font.BOLD, 18));
                    etiquetaSrc.setForeground(new Color(0x07424C));
                    src.add(etiquetaSrc);
                    src.add(new JLabel(cat.getIcono()));
                    info.add(src);

                    info.add(Box.createVerticalStrut(10));

                    JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
                    Boton modificar = new Boton("cambiar", "Modificar Categoria");
                    Boton eliminar = new Boton("cancelar", "Eliminar Categoria");
                    botones.add(modificar);
                    botones.add(eliminar);

                    modificar.addActionListener(ActionEvent -> {
                        iconSrc = cat.getIcono();
                        System.out.println("Icono: "+iconSrc);
                        nombreCategoria.removeAll();
                        nombreCategoria.add(etiquetaNombre);
                        nombreCategoria.add(nombre);
                        src.removeAll();
                        src.add(etiquetaSrc);
                        src.add(cambiar);
                        botones.removeAll();
                        String iconoViejo = cat.getIcono();

                        Boton guardar = new Boton("guardar", "Guardar cambios");
                        guardar.addActionListener(action -> {
                            String nuevoNombre = nombre.getText();
                            String nuevoIcon = iconSrc;
                            if(!nuevoIcon.equals(nuevoIcon) && !iconoViejo.equals("categorias.png")){
                                String path = "src/main/resources/images/categorias/"+iconoViejo;
                                try{
                                    Files.deleteIfExists(Path.of(path));
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                            Categoria editar = new Categoria(nuevoNombre, nuevoIcon, cat.getCodigo());
                            try {
                                crudCategoria.editar(editar);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(panelContenido, "Error al editar categoria: " + ex.getMessage());
                            }
                            JOptionPane.showMessageDialog(panelContenido, "Categoria modificada correctamente");
                            dialog.dispose();
                            resetCategorias();
                        });
                        Boton cancelar = new Boton("cancelar", "Cancelar");
                        cancelar.addActionListener(action -> {
                            if(!iconoViejo.equals(iconSrc)){
                                try {
                                    Files.deleteIfExists(Path.of(iconoViejo));
                                } catch (IOException ex) {
                                    JOptionPane.showMessageDialog(panelContenido, "Error al eliminar icono antiguo: " + ex.getMessage());
                                }
                            }
                            dialog.dispose();
                        });
                        botones.add(guardar);
                        botones.add(cancelar);
                        botones.revalidate();
                        botones.repaint();
                        botones.requestFocusInWindow();
                        dialog.setTitle("Modificar Categoria " + cat.getNombre());
                        dialog.setSize(454, 218);
                        dialog.setLocationRelativeTo(null);
                        dialog.setIconImage(imagen.getImage());
                        dialog.add(Icono);
                    });
                    eliminar.addActionListener(ActionEvent -> {
                        int result= JOptionPane.showConfirmDialog(ventanaPrincipal,"<html> Esta seguro de eliminar la categoria <font color='#09f' weight='bold'>"+cat.getNombre()+"</font> ?</html>");

                        if(result==0){
                            System.out.println("Eliminando categoria: "+cat.getNombre());
                            try {
                                crudCategoria.eliminar(cat.getCodigo());
                                if(!iconSrc.equals("categorias.png")){
                                    String path = "src/main/resources/images/categorias/"+iconSrc;
                                    Files.deleteIfExists(Path.of(path));
                                }
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(panelContenido, "Error al eliminar categoria: " + ex.getMessage());
                            }
                            JOptionPane.showMessageDialog(panelContenido, "Categoria eliminada correctamente");
                            dialog.dispose();
                            resetCategorias();
                        }
                    });

                    info.add(botones);
                    dialog.add(info);
                    dialog.revalidate();
                    dialog.repaint();
                    dialog.setVisible(true);
                    dialog.setDefaultCloseOperation(dialog.DISPOSE_ON_CLOSE);
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
            panelBotones.add(categoria);
        }
    }

    public void resetCategorias(){
        panelBotones.removeAll();
        cargarCategorias();
        panelBotones.revalidate();
        panelBotones.repaint();
        panelBotones.requestFocusInWindow();
    }


    public JPanel agregarCategoria(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panel.setPreferredSize(new Dimension(getWidth()-20, getHeight()-100));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        panel.setMinimumSize(new Dimension(200, 200));

        Icono = new JLabel(Ventana.redimensionarImagen("src/main/resources/images/icons/agregar-categoria.png", 50, 50));
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        info.setPreferredSize(new Dimension(getWidth()-20, getHeight()-100));
        info.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        info.setMinimumSize(new Dimension(200, 250));

        Input nombre = new Input("Nombre de la Categoria");
        JButton selectIcon = new JButton("Seleccionar Icono");
        selectIcon.addActionListener(this::actionPerformed);

        Boton guardar = new Boton("guardar","Agregar Categoria");
        guardar.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Categoria nueva = new Categoria(nombre.getText(),iconSrc);
                System.out.println("Creado categoria: "+nombre.getText());
                crudCategoria.agregar(nueva);
                JOptionPane.showMessageDialog(panelContenido, "Categoria agregada correctamente");
                dialog.dispose();
                resetCategorias();
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

        Boton cancelar = new Boton("cancelar","Cancelar");
        cancelar.addActionListener(this::actionPerformed);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1,2,10,10));


        panelBotones.add(guardar);
        panelBotones.add(cancelar);

        info.add(nombre);
        info.add(src);
        info.add(selectIcon);
        info.add(panelBotones);
        info.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));

        panel.add(Icono);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(info);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Agregar Categoria":
                dialog.repaint();
                dialog.addNotify();
                dialog.setSize(500, 200);
                dialog.setTitle("Agregar Categoria");
                dialog.setLocationRelativeTo(null);
                dialog.setIconImage(new ImageIcon("src/main/resources/images/icons/agregar-categoria.png").getImage());
                dialog.setContentPane(agregarCategoria());
                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
                break;
            case "Seleccionar Icono":
                JFileChooser iconSrc = new JFileChooser();
                iconSrc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                iconSrc.setDialogTitle("Seleccionar Icono");
                iconSrc.setDialogType(JFileChooser.OPEN_DIALOG);
                iconSrc.setAcceptAllFileFilterUsed(false);

                if (iconSrc.showDialog(this, "Seleccionar Icono") == JFileChooser.APPROVE_OPTION) {
                    File iconoFile = iconSrc.getSelectedFile();
                    String path = "src/main/resources/images/categorias/";

                    File dir = new File(path);
                    if (!dir.exists()) {
                        dir.mkdir();
                    }

                    File archivo = new File(path + iconoFile.getName());

                    try {
                        Files.copy(iconoFile.toPath(), archivo.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        src.setText("Icono seleccionado: " + iconoFile.getName());
                        Icono.setIcon(Ventana.redimensionarImagen("src/main/resources/images/categorias/" + iconoFile.getName(), 50, 50));
                        this.iconSrc = iconoFile.getName();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error al seleccionar archivo: " + ex.getMessage());
                    }
                }
                break;
            case "Cancelar":
                dialog.dispose();
                String path = "src/main/resources/images/categorias/" + this.iconSrc;
                    Path p = Paths.get(path);
                    try {
                        Files.deleteIfExists(p);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error al cancelar: " + ex.getMessage());
                    }
                src.setText("");
            break;
            case "cambiar iconSrc":
                path = "src/main/resources/images/Categorias/";
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setDialogTitle("Cambiar Icono");
                fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);

                if (fileChooser.showDialog(this, "Seleccionar Icono") == JFileChooser.APPROVE_OPTION) {
                    File iconfile = fileChooser.getSelectedFile();

                    File dir = new File(path);
                    if (!dir.exists()) {
                        dir.mkdir();
                    }

                    File archivo = new File(path + iconfile.getName());

                    try {
                        Files.copy(iconfile.toPath(), archivo.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        src.setText("Icono seleccionado: " + iconfile.getName());
                        Icono.setIcon(Ventana.redimensionarImagen("src/main/resources/images/categorias/"+iconfile.getName(), 50, 50));
                        this.iconSrc = iconfile.getName();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error al seleccionar archivo: " + ex.getMessage());
                    }
                }

            break;
        }
    }
    
    public void setVentanaPrincipal(Ventana ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }
}
