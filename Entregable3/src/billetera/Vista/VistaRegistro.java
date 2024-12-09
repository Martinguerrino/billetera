package Vista;

import javax.swing.*;

import billetera.Controladores.ControladorRegistro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaRegistro extends JFrame {
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtEmail;
    private JPasswordField txtContrasena;
    private JCheckBox chkAceptarTerminos;
    private JButton btnRegistrar;
    private ControladorRegistro miControlador;

    public VistaRegistro(ControladorRegistro miControlador) {
        super("Registro");
        this.miControlador = miControlador;

        // Configuración de la vista
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centrar la ventana en la pantalla

        // Crear los componentes
        txtNombre = new JTextField(20);
        txtApellido = new JTextField(20);
        txtEmail = new JTextField(20);
        txtContrasena = new JPasswordField(20);
        chkAceptarTerminos = new JCheckBox("Acepto los términos y condiciones");
        btnRegistrar = new JButton("Registrar");

        // Acción del botón de registro
        btnRegistrar.addActionListener(new LRegistro());

        // Layout y adición de componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Apellido:"));
        panel.add(txtApellido);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Contraseña:"));
        panel.add(txtContrasena);
        panel.add(chkAceptarTerminos);
        panel.add(btnRegistrar);

        add(panel);
    }

    // Clase interna para el ActionListener del botón de Registro
    private class LRegistro implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Obtener datos de los campos
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String email = txtEmail.getText();
            String contrasena = new String(txtContrasena.getPassword());
            boolean aceptaTerminos = chkAceptarTerminos.isSelected();

            // Validar datos
            if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || contrasena.isEmpty()) {
                JOptionPane.showMessageDialog(VistaRegistro.this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!aceptaTerminos) {
                JOptionPane.showMessageDialog(VistaRegistro.this, "Debe aceptar los términos y condiciones para registrarse.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Enviar los datos al controlador
            miControlador.Registrar(nombre, apellido, email, contrasena, aceptaTerminos);
        }
    }
}
