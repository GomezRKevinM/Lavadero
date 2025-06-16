package co.edu.udc.ejercicio28_lavadero.vistas.gui;

import co.edu.udc.ejercicio28_lavadero.modelo.crud.EmpleadoCrudl;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Empleado;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Contrato;
import co.edu.udc.ejercicio28_lavadero.modelo.crud.ContratoCrudl;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.Cargo;
import co.edu.udc.ejercicio28_lavadero.modelo.entidades.TipoID;
import co.edu.udc.ejercicio28_lavadero.vistas.gui.components.Boton;
import co.edu.udc.ejercicio28_lavadero.vistas.gui.components.Input;
import co.edu.udc.ejercicio28_lavadero.vistas.gui.components.LabelValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class VentanaEmpleado extends JPanel {
    private JPanel panelContenido;
    private Ventana ventanaPrincipal;
    private JButton botonAgregar = new JButton("Agregar Empleado", Ventana.redimensionarImagen("src/main/resources/images/icons/agregar-cliente.png", 20, 20));
    private JButton botonModificar = new JButton("Modificar Empleado", Ventana.redimensionarImagen("src/main/resources/images/icons/editar.png", 20, 20));
    private JButton botonEliminar = new JButton("Eliminar Empleado", Ventana.redimensionarImagen("src/main/resources/images/icons/papelera-de-reciclaje.png", 20, 20));
    private JPanel panelBotones = new JPanel();
    private JScrollPane scrollPane;
    private JTextField txtBuscar;
    private ArrayList<Empleado> empleadosFiltrados = new ArrayList<>();

    public VentanaEmpleado() {
        panelContenido = new JPanel();
        panelContenido.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelContenido.setPreferredSize(new Dimension(getWidth() - 20, getHeight() - 100));
        panelContenido.setLayout(new BorderLayout(10, 10));

        panelBotones.setLayout(new GridLayout(0, 1, 10, 10));
        panelBotones.setBorder(null);

        // Panel de búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtBuscar = new JTextField(20);
        JButton btnBuscar = new JButton("Buscar");
        panelBusqueda.add(new JLabel("Buscar Empleado: "));
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);
        panelContenido.add(panelBusqueda, BorderLayout.NORTH);
        txtBuscar.addActionListener(e -> filtrarEmpleados());
        btnBuscar.addActionListener(e -> filtrarEmpleados());
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtrarEmpleados();
            }
        });

        // Botón para agregar empleado
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botonAgregar.addActionListener(e -> mostrarFormularioEmpleado());
        panelTop.add(botonAgregar);
        panelContenido.add(panelTop, BorderLayout.SOUTH);

        cargarEmpleados();

        scrollPane = new JScrollPane(panelBotones);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelContenido.add(scrollPane, BorderLayout.CENTER);

        JLabel titulo = new JLabel("EMPLEADOS", SwingConstants.CENTER);
        titulo.setForeground(new Color(0x1AA6C6));
        titulo.setFont(new Font("Arial", Font.BOLD, 46));
        panelContenido.add(titulo, BorderLayout.NORTH);

        setLayout(new BorderLayout());
        add(panelContenido, BorderLayout.CENTER);
    }

    private void cargarEmpleados() {
        panelBotones.removeAll();
        EmpleadoCrudl crud = new EmpleadoCrudl();
        ArrayList<Empleado> empleados = crud.listarTodo();
        empleadosFiltrados = empleados;
        mostrarEmpleados(empleados);
    }

    private void mostrarEmpleados(ArrayList<Empleado> empleados) {
        panelBotones.removeAll();
        for (Empleado emp : empleados) {
            JButton btnEmpleado = new JButton(emp.getNombre(), Ventana.redimensionarImagen("src/main/resources/images/icons/empleados.png", 40, 40));
            btnEmpleado.setHorizontalAlignment(SwingConstants.LEFT);
            btnEmpleado.setIconTextGap(10);
            btnEmpleado.setPreferredSize(new Dimension(400, 60));
            btnEmpleado.addActionListener(e -> mostrarDetalleEmpleado(emp));
            panelBotones.add(btnEmpleado);
        }
        panelBotones.revalidate();
        panelBotones.repaint();
    }

    private void filtrarEmpleados() {
        String texto = txtBuscar.getText().trim().toLowerCase();
        if (texto.isEmpty()) {
            mostrarEmpleados(empleadosFiltrados);
            return;
        }
        ArrayList<Empleado> filtrados = new ArrayList<>();
        for (Empleado emp : empleadosFiltrados) {
            if (emp.getNombre().toLowerCase().contains(texto) ||
                emp.getIdentificacion().toLowerCase().contains(texto)) {
                filtrados.add(emp);
            }
        }
        mostrarEmpleados(filtrados);
    }

    private void mostrarDetalleEmpleado(Empleado emp) {
        JOptionPane.showMessageDialog(this,
                "Nombre: " + emp.getNombre() + "\n" +
                "Identificación: " + emp.getIdentificacion() + "\n" +
                "Cargo: " + emp.getContrato().getCargo() + "\n" +
                "Teléfono: " + emp.getTelefono() + "\n" +
                "Dirección: " + emp.getDireccion(),
                "Detalle del Empleado",
                JOptionPane.INFORMATION_MESSAGE,
                Ventana.redimensionarImagen("src/main/resources/images/icons/empleados.png", 40, 40));
    }

    private void mostrarFormularioEmpleado() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // Datos del empleado usando Input
        Input inputNombre = new Input("Nombre del Empleado");
        JComboBox<TipoID> comboTipoID = new JComboBox<>(TipoID.values());
        Input inputIdentificacion = new Input("Identificación");
        Input inputCorreo = new Input("Correo Electrónico");
        Input inputTelefono = new Input("Teléfono");
        Input inputDireccion = new Input("Dirección");
        // Datos del contrato
        JComboBox<Cargo> comboCargo = new JComboBox<>(Cargo.values());
        Input inputFechaInicio = new Input("Fecha de Inicio (YYYY-MM-DD)");
        Input inputFechaFinal = new Input("Fecha Final (YYYY-MM-DD)");
        Input inputHorario = new Input("Horario (Ej: 9:00 - 18:00)");
        Input inputSalario = new Input("Salario (Ej: 1000000 COP)","peso");

        JLabel titulo = new JLabel("Agregar Empleado", SwingConstants.CENTER);
        titulo.setIcon(Ventana.redimensionarImagen("src/main/resources/images/icons/agregar-empleado.png", 40, 40));
        LabelValue nombreLabel = new LabelValue("Nombre del Empleado", "");
        nombreLabel.add(inputNombre);
        LabelValue tipoIdLabel = new LabelValue("Tipo de Identificación", "");
        tipoIdLabel.add(comboTipoID);
        LabelValue identificacionLabel = new LabelValue("Identificacion", "");
        identificacionLabel.add(inputIdentificacion);
        LabelValue correoLabel = new LabelValue("Correo Electrónico", "");
        correoLabel.add(inputCorreo);
        LabelValue telefonoLabel = new LabelValue("Telefono", "");
        telefonoLabel.add(inputTelefono);
        LabelValue direccionLabel = new LabelValue("Direccion", "");
        direccionLabel.add(inputDireccion);
        LabelValue cargoLabel = new LabelValue("Cargo", "");
        cargoLabel.add(comboCargo);
        LabelValue fechaInicioLabel = new LabelValue("Fecha de Inicio (YYYY-MM-DD)", "");
        fechaInicioLabel.add(inputFechaInicio);
        LabelValue fechaFinalLabel = new LabelValue("Fecha Final (YYYY-MM-DD)", "");
        fechaFinalLabel.add(inputFechaFinal);
        LabelValue horarioLabel = new LabelValue("Horario", "");
        horarioLabel.add(inputHorario);
        LabelValue salarioLabel = new LabelValue("Salario", "");
        salarioLabel.add(inputSalario);

        panel.add(titulo);
        panel.add(nombreLabel);
        panel.add(tipoIdLabel);
        panel.add(identificacionLabel);
        panel.add(correoLabel);
        panel.add(telefonoLabel);
        panel.add(direccionLabel);
        panel.add(new JLabel("--- Contrato ---"));
        panel.add(cargoLabel);
        panel.add(fechaInicioLabel);
        panel.add(fechaFinalLabel);
        panel.add(horarioLabel);
        panel.add(salarioLabel);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(panelBotones);

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Agregar Empleado y Contrato", true);
        dialog.setContentPane(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);

        btnGuardar.addActionListener(ev -> {
            String nombre = inputNombre.getText();
            TipoID tipoID = (TipoID) comboTipoID.getSelectedItem();
            String identificacion = inputIdentificacion.getText();
            String correo = inputCorreo.getText();
            String telefono = inputTelefono.getText();
            String direccion = inputDireccion.getText();
            Cargo cargo = (Cargo) comboCargo.getSelectedItem();
            String fechaInicio = inputFechaInicio.getText();
            String fechaFinal = inputFechaFinal.getText();
            String horario = inputHorario.getText();
            Double salario = Double.parseDouble(inputSalario.getText().replaceAll("[^0-9]", ""));

            Contrato contrato = new Contrato();
            contrato.setCargo(cargo);
            contrato.setFechaInicio(fechaInicio);
            contrato.setFechaFinal(fechaFinal);
            contrato.setHorario(horario);
            contrato.setSueldoBase(salario);
            contrato.setContratadoCC(identificacion);
            contrato.setContratante("1");
            ContratoCrudl contratoCrudl = new ContratoCrudl();
            contratoCrudl.agregar(contrato);
            Empleado empleado = new Empleado(nombre, tipoID, identificacion, correo, telefono, direccion, contrato);
            EmpleadoCrudl empleadoCrudl = new EmpleadoCrudl();
            empleadoCrudl.agregar(empleado);
            JOptionPane.showMessageDialog(panel, "Empleado y contrato agregados correctamente.");
            dialog.dispose();
            cargarEmpleados();
        });
        btnCancelar.addActionListener(ev -> dialog.dispose());
        dialog.setVisible(true);
    }

    public JPanel getPanelContenido() {
        return panelContenido;
    }

    public void setVentanaPrincipal(Ventana ventana) {
        this.ventanaPrincipal = ventana;
    }
}
