package co.edu.udc.ejercicio28_lavadero.vistas.gui;

import co.edu.udc.ejercicio28_lavadero.models.crud.ClienteCrudl;
import co.edu.udc.ejercicio28_lavadero.models.entidades.Cliente;
import co.edu.udc.ejercicio28_lavadero.models.entidades.TipoID;

import java.util.ArrayList;

public class VentanaCliente extends JPanel {
    private JPanel panelContenido;
    private Ventana ventanaPrincipal;
    private JLabel label = new JLabel("");
    private String src = "";

    private JButton botonAgregar = new JButton("Agregar Cliente",
            Ventana.redimensionarImagen("src/main/resources/images/icons/user.png",34,34));
    private JButton botonModificar = new JButton("Modificar Cliente",
            Ventana.redimensionarImagen("src/main/resources/images/icons/editar.png",20,20));
    private JButton botonEliminar = new JButton("Eliminar Cliente",
            Ventana.redimensionarImagen("src/main/resources/images/icons/papelera-de-reciclaje.png",20,20));
    private JButton btnSearch = new JButton("Buscar Cliente");
    private JPanel panelBotones = new JPanel();
    private JButton cancelar = new JButton("✖️ Cancelar");
    private JButton guardar = new JButton("✔️ Agregar Cliente");
    private JScrollPane scrollPane;

    public VentanaCliente(){
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

        cargarClientes();

        JScrollPane scrollPane = new JScrollPane(panelBotones);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelContenido.add(scrollPane, BorderLayout.CENTER);

        JLabel titulo = new JLabel("CLIENTES", SwingConstants.CENTER);
        titulo.setForeground(new Color(0x1AA6C6));
        titulo.setFont(new Font("Arial", Font.BOLD, 46));
        panelContenido.add(titulo, BorderLayout.NORTH);

        InputIcon buscador = new InputIcon(
                Ventana.redimensionarImagen("src/main/resources/images/icons/lupa.png",50,50),
                "Buscar Cliente"
        );
        buscador.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buscador.getTextField().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel btnContent = new JPanel();
        btnContent.setLayout(new BoxLayout(btnContent, BoxLayout.X_AXIS));
        btnContent.add(botonAgregar);
        botonAgregar.setIcon(Ventana.redimensionarImagen("src/main/resources/images/icons/agregar-cliente.png", 20, 20));
        botonAgregar.addActionListener(actionEvent -> {
            agregarCliente();
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
                    ClienteCrudl crud = new ClienteCrudl();
                    panelBotones.removeAll();
                    panelBotones.revalidate();
                    panelBotones.repaint();

                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.weightx = 1.0;
                    gbc.weighty = 1.0;
                    gbc.fill = GridBagConstraints.NONE;

                    try{
                        String codigo = buscador.getTextField().getText();
                        ArrayList<Cliente> clientes = crud.buscar(codigo);
                        if (clientes.size() > 0) {
                            for(Cliente client: clientes){
                                BotonModerno cliente = new BotonModerno("<html> <div style='display:flex; justify-content:space-between'> <font size='7'>"+client.getNombre()+" <hr>"+"</font>   <font size='5' style='font-family: cursive; color: #333'>ID:</font> <font>"+client.getIdentificacion()+"</span>"+"</div> </html>",700,90);
                                String imagen = "user.png";
                                cliente.setIcon(Ventana.redimensionarImagen("src/main/resources/images/icons/"+imagen, 60, 60));

                                cliente.addActionListener(listenen -> {
                                    JDialog modal = new JDialog();
                                    modal.setModal(true);
                                    modal.setSize(600, 400);
                                    modal.setLocationRelativeTo(null);
                                    modal.setTitle("Información del cliente "+client.getNombre());
                                    modal.setIconImage(new ImageIcon("src/main/resources/images/icons/"+imagen).getImage());
                                    modal.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

                                    JLabel ImagenLateral = new JLabel(Ventana.redimensionarImagen("src/main/resources/images/icons/"+imagen, 64, 64));
                                    JPanel informacion = new JPanel();
                                    informacion.setLayout(new BoxLayout(informacion, BoxLayout.Y_AXIS) );

                                    LabelValue nombre = new LabelValue("Nombre: ", client.getNombre());
                                    nombre.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

                                    LabelValue id = new LabelValue("ID: ", client.getIdentificacion());
                                    id.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

                                    LabelValue direccion = new LabelValue("Dirección: ", client.getDireccion());
                                    direccion.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

                                    LabelValue telefono = new LabelValue("Teléfono: ", client.getTelefono());
                                    telefono.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

                                    LabelValue email = new LabelValue("Email: ", client.getCorreo());
                                    email.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

                                    Boton modificar = new Boton("cambiar","Modificar Cliente");
                                    Boton eliminar = new Boton("cancelar","Eliminar Cliente");

                                    JPanel botones = new JPanel();
                                    botones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                                    botones.add(modificar);
                                    botones.add(eliminar);

                                    informacion.add(nombre);
                                    informacion.add(id);
                                    informacion.add(direccion);
                                    informacion.add(telefono);
                                    informacion.add(email);

                                    modificar.addActionListener(e1 -> {
                                        nombre.setValue(new JLabel(""));
                                        Input nombreInput = new Input(client.getNombre());
                                        nombre.add(nombreInput);

                                        direccion.setValue(new JLabel(""));
                                        Input direccionInput = new Input(client.getDireccion());
                                        direccion.add(direccionInput);

                                        telefono.setValue(new JLabel(""));
                                        Input telefonoInput = new Input(client.getTelefono());
                                        telefono.add(telefonoInput);

                                        email.setValue(new JLabel(""));
                                        Input emailInput = new Input(client.getCorreo());
                                        email.add(emailInput);

                                        botones.removeAll();

                                        Boton guardar = new Boton("guardar","Guardar Cambios");
                                        Boton cancelar = new Boton("cancelar","Cancelar");
                                        cancelar.addActionListener(e2 -> {
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
                                            crud.eliminar(client.getIdentificacion());
                                            JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente");
                                            modal.dispose();
                                            cargarClientes();
                                        }catch (Exception error){
                                            JOptionPane.showMessageDialog(null, "Error al eliminar el cliente: " + error.getMessage());
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
                                panelBotones.add(cliente,gbc);
                            }
                        }else{
                            JPanel mensaje = new JPanel();
                            mensaje.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                            LabelValue resultado = new LabelValue("No hay clientes con el nombre o código: ",buscador.getTextField().getText());
                            resultado.getValue().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                            resultado.getValue().setOpaque(true);
                            resultado.getValue().setBackground(new Color(0x010F13));
                            resultado.getValue().setForeground(new Color(0x6FCCE8));
                            resultado.getValue().setIcon(Ventana.redimensionarImagen("src/main/resources/images/icons/lupa.png", 30, 30));
                            mensaje.add(resultado);
                            panelBotones.add(mensaje);
                        }
                    }catch (Exception exception){
                        JOptionPane.showMessageDialog(null, "Error: "+exception.getMessage());
                    }
                    panelContenido.revalidate();
                    panelContenido.repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(buscador.getTextField().getText().equals(buscador.getPrompt()) || buscador.getTextField().getText().equals("")){
                    cargarClientes();
                    System.out.println("Cargando todos los clientes");
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

    public void cargarClientes() {
        ClienteCrudl crud = new ClienteCrudl();
        panelBotones.removeAll();
        panelBotones.revalidate();
        panelBotones.repaint();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;

        try{
            for(Cliente client: crud.listarTodo()){
                BotonModerno cliente = new BotonModerno("<html> <div style='display:flex; justify-content:space-between'> <font size='7'>"+client.getNombre()+" <hr>"+"</font>   <font size='5' style='font-family: cursive; color: #333'>ID:</font> <font>"+client.getIdentificacion()+"</span>"+"</div> </html>",700,90);
                String imagen = "user.png";
                cliente.setIcon(Ventana.redimensionarImagen("src/main/resources/images/icons/"+imagen, 60, 60));

                cliente.addActionListener(e -> {
                    JDialog modal = new JDialog();
                    modal.setModal(true);
                    modal.setSize(495, 397);
                    modal.setLocationRelativeTo(null);
                    modal.setTitle("Información del cliente "+client.getNombre());
                    modal.setIconImage(new ImageIcon("src/main/resources/images/icons/clientes.png").getImage());
                    modal.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                    modal.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            System.out.println("Width: "+modal.getWidth()+" Height: "+modal.getHeight());
                        }
                    });

                    JLabel ImagenLateral = new JLabel(Ventana.redimensionarImagen("src/main/resources/images/icons/"+imagen, 64, 64));
                    JPanel informacion = new JPanel();
                    informacion.setLayout(new BoxLayout(informacion, BoxLayout.Y_AXIS) );

                    LabelValue nombre = new LabelValue("Nombre: ", client.getNombre());
                    nombre.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

                    LabelValue id = new LabelValue("ID: ", client.getIdentificacion());
                    id.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

                    LabelValue direccion = new LabelValue("Dirección: ", client.getDireccion());
                    direccion.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

                    LabelValue telefono = new LabelValue("Teléfono: ", client.getTelefono());
                    telefono.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

                    LabelValue email = new LabelValue("Email: ", client.getCorreo());
                    email.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

                    Boton modificar = new Boton("cambiar","Modificar Cliente");
                    Boton eliminar = new Boton("cancelar","Eliminar Cliente");

                    JPanel botones = new JPanel();
                    botones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                    botones.add(modificar);
                    botones.add(eliminar);

                    informacion.add(nombre);
                    informacion.add(id);
                    informacion.add(direccion);
                    informacion.add(telefono);
                    informacion.add(email);

                    modificar.addActionListener(e1 -> {
                        modal.setSize(576,459);
                        nombre.removeAll();
                        nombre.add(new LabelValue("Nombre: ",""));
                        Input nombreInput = new Input(client.getNombre());
                        nombreInput.setSize(100, 30);
                        nombre.add(nombreInput);

                        direccion.removeAll();
                        direccion.add(new LabelValue("Direccion: ",""));
                        Input direccionInput = new Input(client.getDireccion());
                        direccion.add(direccionInput);

                        telefono.removeAll();
                        telefono.add(new LabelValue("Telefono: ",""));
                        Input telefonoInput = new Input(client.getTelefono());
                        telefono.add(telefonoInput);

                        email.removeAll();
                        email.add(new LabelValue("Email: ",""));
                        Input emailInput = new Input(client.getCorreo());
                        email.add(emailInput);

                        botones.removeAll();

                        Boton guardar = new Boton("guardar","Guardar Cambios");
                        Boton cancelar = new Boton("cancelar","Cancelar");
                        cancelar.addActionListener(e2 -> {
                            modal.dispose();
                        });
                        guardar.addActionListener(e2 -> {
                            String nombreCliente = nombreInput.getText();
                            String direccionCliente = direccionInput.getText();
                            String telefonoCliente = telefonoInput.getText();
                            String emailCliente = emailInput.getText();

                            Cliente nuevo = new Cliente(nombreCliente, client.getTipoID(), client.getIdentificacion(), direccionCliente, telefonoCliente, emailCliente);

                            try {
                                crud.editar(nuevo);
                                JOptionPane.showMessageDialog(null, "Cliente modificado correctamente");
                                modal.dispose();
                                cargarClientes();
                            } catch (Exception error) {
                                JOptionPane.showMessageDialog(null, "Error al modificar el cliente: " + error.getMessage());
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
                            crud.eliminar(client.getIdentificacion());
                            JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente");
                            modal.dispose();
                            cargarClientes();
                        }catch (Exception error){
                            JOptionPane.showMessageDialog(null, "Error al eliminar el cliente: " + error.getMessage());
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
                panelBotones.add(cliente,gbc);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al cargar los clientes: " + e.getMessage());
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

    public void agregarCliente(){
        JDialog modal = new JDialog();
        modal.setModal(true);
        modal.setSize(600, 520);
        modal.setLocationRelativeTo(null);
        modal.setTitle("Agregar Cliente");
        modal.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        modal.setIconImage(new ImageIcon("src/main/resources/images/icons/agregar-usuario.png").getImage());

        JPanel tituloPanel = new JPanel();
        tituloPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel titulo = new JLabel("Agregar Cliente",SwingConstants.CENTER);
        titulo.setForeground(new Color(0x1AA6C6));
        titulo.setFont(new Font("Arial", Font.BOLD, 46));
        tituloPanel.add(titulo);

        JPanel div = new JPanel();
        div.setLayout(new BoxLayout(div, BoxLayout.Y_AXIS));
        div.add(tituloPanel);
        div.add(Box.createVerticalStrut(10));

        JPanel panelInputs = new JPanel();
        panelInputs.setLayout(new BoxLayout(panelInputs,BoxLayout.Y_AXIS));

        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido,BoxLayout.X_AXIS));

        contenido.add(panelInputs);
        div.add(contenido);

        Dimension inputs = new Dimension(400, 40);

        JPanel nombreLine = new JPanel();
        nombreLine.setLayout(new BoxLayout(nombreLine,BoxLayout.X_AXIS));
        nombreLine.setPreferredSize(inputs);
        nombreLine.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        nombreLine.setMinimumSize(new Dimension(Integer.MIN_VALUE, 40));
        nombreLine.setSize(inputs);
        LabelValue nombre = new LabelValue("Nombre: ", "");
        nombre.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        Input nombreInput = new Input("Nombre del cliente ");
        nombreLine.add(nombre);
        nombreLine.add(nombreInput);

        JPanel tipoIDLine = new JPanel();
        tipoIDLine.setLayout(new BoxLayout(tipoIDLine,BoxLayout.X_AXIS));
        tipoIDLine.setPreferredSize(inputs);
        tipoIDLine.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        tipoIDLine.setMinimumSize(new Dimension(Integer.MAX_VALUE, 40));
        tipoIDLine.setSize(inputs);
        LabelValue tipoID = new LabelValue("Tipo ID: ", "");

        tipoID.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JComboBox<TipoID> tipoIDCombo = new JComboBox<>(TipoID.values());
        tipoIDLine.add(tipoID);
        tipoIDLine.add(tipoIDCombo);

        JPanel panelIdentificacion = new JPanel();
        panelIdentificacion.setLayout(new BoxLayout(panelIdentificacion, BoxLayout.X_AXIS));
        panelIdentificacion.setPreferredSize(inputs);
        panelIdentificacion.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        panelIdentificacion.setMinimumSize(new Dimension(Integer.MAX_VALUE, 40));
        panelIdentificacion.setSize(inputs);
        LabelValue identificacion = new LabelValue("Identificación: ", "");
        identificacion.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        Input identificacionInput = new Input("Identificación del cliente ");
        panelIdentificacion.add(identificacion);
        panelIdentificacion.add(identificacionInput);

        JPanel direccionLine = new JPanel();
        direccionLine.setLayout(new BoxLayout(direccionLine,BoxLayout.X_AXIS));
        LabelValue direccion = new LabelValue("Dirección: ", "");
        direccion.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        Input direccionInput = new Input("Dirección del cliente ");
        direccionLine.add(direccion);
        direccionLine.add(direccionInput);

        JPanel telefonoLine = new JPanel();
        telefonoLine.setLayout(new BoxLayout(telefonoLine,BoxLayout.X_AXIS));
        LabelValue telefono = new LabelValue("Teléfono: ", "");
        telefono.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        Input telefonoInput = new Input("Teléfono del cliente ");
        telefonoLine.add(telefono);
        telefonoLine.add(telefonoInput);

        JPanel emailLine = new JPanel();
        emailLine.setLayout(new BoxLayout(emailLine,BoxLayout.X_AXIS));
        LabelValue email = new LabelValue("Email: ", "");
        email.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        Input emailInput = new Input("Email del cliente ");
        emailLine.add(email);
        emailLine.add(emailInput);

        panelInputs.add(nombreLine);
        panelInputs.add(Box.createVerticalStrut(10));
        panelInputs.add(tipoIDLine);
        panelInputs.add(Box.createVerticalStrut(10));
        panelInputs.add(panelIdentificacion);
        panelInputs.add(Box.createVerticalStrut(10));
        panelInputs.add(emailLine);
        panelInputs.add(Box.createVerticalStrut(10));
        panelInputs.add(direccionLine);
        panelInputs.add(Box.createVerticalStrut(10));
        panelInputs.add(telefonoLine);
        panelInputs.add(Box.createVerticalStrut(10));


        JPanel controles = new JPanel();
        controles.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        Boton guardar = new Boton("guardar","Guardar Cliente");
        Boton cancelar = new Boton("cancelar","Cancelar");

        cancelar.addActionListener(e -> {
            modal.dispose();
        });

        guardar.addActionListener(e -> {
            String nombreCliente = nombreInput.getText();
            String direccionCliente = direccionInput.getText();
            String telefonoCliente = telefonoInput.getText();
            String emailCliente = emailInput.getText();
            String tipoIDCliente = tipoIDCombo.getSelectedItem().toString();
            String identificacionCliente = identificacionInput.getText();

            Cliente nuevo = new Cliente(nombreCliente,TipoID.valueOf(tipoIDCliente), identificacionCliente, direccionCliente, telefonoCliente, emailCliente);


            ClienteCrudl crud = new ClienteCrudl();
            try {
                crud.agregar(nuevo);
                JOptionPane.showMessageDialog(null, "Cliente agregado correctamente");
                modal.dispose();
                cargarClientes();
            }catch (Exception error){
                JOptionPane.showMessageDialog(null, "Error al agregar el cliente: "+error.getMessage());
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