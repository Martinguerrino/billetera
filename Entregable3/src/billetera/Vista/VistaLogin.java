package Vista;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import billetera.Controladores.ControladorLogin;

public class VistaLogin extends JFrame {
	    private JButton buttonLogin;
	    private JLabel hipervinculoRegistrar;
	    private JLabel titulo;
	    private JTextField txtGmail;
	    private JPasswordField txtPassword;
	    private ControladorLogin miControlador;

	    // Constructor
	    public VistaLogin(ControladorLogin miControlador) {
	        super("Login");
	        this.miControlador= miControlador;
	        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        super.setSize(800, 400);  // Tamaño de la ventana
	        super.setLayout(null);     // Usamos un Layout absoluto
	        super.setResizable(false); // No permitir redimensionar

	        // Crear el JLabel (título)
	        titulo = new JLabel("Iniciar Sesión:");
	        titulo.setHorizontalAlignment(SwingConstants.CENTER); // Centrado horizontal
	        titulo.setBounds(0, 50, 800, 40);  // 800 px de ancho, centrado en el eje X (por defecto)

	        // Crear el JTextField (para el Gmail)
	        txtGmail = new JTextField();
	        txtGmail.setBounds(200, 120, 400, 30);  // Centrado en X y Y (800 - 400) / 2 = 200
	        txtGmail.setToolTipText("Ingrese su Gmail");

	        // Crear el JPasswordField (para la contraseña)
	        txtPassword = new JPasswordField();
	        txtPassword.setBounds(200, 170, 400, 30);  // Centrado en X y Y
	        txtPassword.setToolTipText("Ingrese su Contraseña");

	        // Crear el JButton (botón de login)
	        buttonLogin = new JButton("Iniciar Sesión");
	        buttonLogin.setBounds(325, 220, 150, 30); // Centrado en X (800 - 150) / 2 = 325, centrado en Y en 220
	        
	        // Crear el JLabel (hipervinculo de "Registrarse")
	        hipervinculoRegistrar = new JLabel("Registrarse");
	        hipervinculoRegistrar = new JLabel("<html><a href=''>Registrarse</a></html>");
	        hipervinculoRegistrar.setBounds(365, 260, 100, 30); // 100 px de ancho, 30 px de alto
	        hipervinculoRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

	        // Agregar componentes al JFrame
	        add(titulo);
	        add(txtGmail);
	        add(txtPassword);
	        add(buttonLogin);
	        add(hipervinculoRegistrar);
	    }

    
    private class LRegistro extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			miControlador.RedirigirRegistro();
		}
    	
    }
    
    private class LIniciarSesion implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String gmail = txtGmail.getText();  // Obtener el texto del JTextField (Gmail)
            String password = new String(txtPassword.getPassword());  // Obtener la contraseña del JPasswordField
            
			if (gmail.isEmpty() || password.isEmpty()) {
                // Mostrar mensaje de error si falta información
                JOptionPane.showMessageDialog(VistaLogin.this, 
                    "Por favor, ingrese tanto su Gmail como su contraseña.", 
                    "Error de inicio de sesión", 
                    JOptionPane.ERROR_MESSAGE);
            } else {
				if(miControlador.verificarUsuario(gmail,password)){
					miControlador.redirigirPantallaActivos();
				}else {
					JOptionPane.showMessageDialog(VistaLogin.this, 
		                    "Error, no existe dicho Usuario coincidente con la contraseña ingresada","Error de inicio de sesión",
		                    JOptionPane.ERROR_MESSAGE);
				}
            }
		}

		
    	
    }

}