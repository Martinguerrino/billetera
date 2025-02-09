package Vista;

import javax.swing.*;

import Auxiliar.Panel;
import Controladores.ControladorRegistro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class VistaRegistro extends Panel {
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtEmail;
    private JPasswordField txtContrasena;
    private JCheckBox chkAceptarTerminos;
    private JButton btnRegistrar;
    private ControladorRegistro miControlador;

    public VistaRegistro(ControladorRegistro miControlador) {
    	super();
        this.miControlador = miControlador;
        
        // Configuración de la vista
        setSize(400, 300);
        
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
            try {
				if(!miControlador.Registrar(nombre, apellido, email, contrasena, aceptaTerminos)) {
					JOptionPane.showMessageDialog(VistaRegistro.this, "Ya existe alguien con ese mail", "Error", JOptionPane.ERROR_MESSAGE);
	                
				}else{
					JOptionPane.showMessageDialog(VistaRegistro.this, "UsuarioRegistrado", ":)", JOptionPane.DEFAULT_OPTION);
	                
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }

	@Override
	protected void actualizarPosiciones() {
		// TODO Auto-generated method stub
		
	}
}