import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaRegistro extends JFrame {
    private JTextField txtEmail;
    private JPasswordField txtContrasena;
    private JButton btnRegistrar;
    private Controlador miControlador;

    public VistaRegistro(Controlador miControlador) {
    	super("Registro");
    	this.miControlador = miControlador;
        // Configuración de la vista
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centrar la ventana en la pantalla

        // Crear los componentes
        txtEmail = new JTextField(20);
        txtContrasena = new JPasswordField(20);
        btnRegistrar = new JButton("Registrar");

        // Acción del botón de registro
        btnRegistrar.addActionListener(new LRegistro());

        // Layout y adición de componentes
        JPanel panel = new JPanel();
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Contraseña:"));
        panel.add(txtContrasena);
        panel.add(btnRegistrar);
        add(panel);
    }

// enlazar el controlador con la vista
    private class LRegistro implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		miControlador.Registrar();
    	}
    
    
}
