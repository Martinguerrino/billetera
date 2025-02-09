package Vista;

import Auxiliar.ImagenCircular;
import Auxiliar.Panel;
import Auxiliar.RoundedButton;
import Auxiliar.RoundedPasswordField;
import Auxiliar.RoundedTextField;
import Controladores.ControladorLogin;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;
import javax.swing.*;

public class VistaLogin extends Panel {
    private JButton buttonLogin;
    private JLabel hipervinculoRegistrar;
    private JLabel titulo;
    private JLabel labelGmail;
    private JLabel labelPassword;
    private JTextField txtGmail;
    private JPasswordField txtPassword;
    private ControladorLogin miControlador;
    private JLabel labelLogo;

    // Colores de Binance
    private final Color BINANCE_YELLOW = new Color(252, 213, 53); // Botón principal
    private final Color BINANCE_INPUT_BG = new Color(43, 49, 57); // Fondo de inputs
    private final Color BINANCE_TEXT = new Color(234, 236, 239);  // Texto claro

    // Constructor
    public VistaLogin(ControladorLogin miControlador) {
    	super();
        this.miControlador = miControlador;
        

        // Cargar la fuente IBM Plex Sans
        Font binanceFont= super.getFont();
        


        // Crear el JLabel (título)
        titulo = new JLabel("Iniciar Sesión");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setForeground(BINANCE_TEXT);
        titulo.setFont(binanceFont.deriveFont(Font.BOLD, 28f));

        // Crear el JLabel (para el Gmail)
        labelGmail = new JLabel("Email:");
        labelGmail.setForeground(BINANCE_TEXT);
        labelGmail.setFont(binanceFont.deriveFont(14f));

        // Crear el JTextField (para el Gmail)
        txtGmail = new RoundedTextField(15);
        txtGmail.setToolTipText("Ingrese su mail");
        txtGmail.setBackground(BINANCE_INPUT_BG);
        txtGmail.setForeground(BINANCE_TEXT);
        txtGmail.setCaretColor(BINANCE_TEXT);
        txtGmail.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        txtGmail.setFont(binanceFont.deriveFont(14f));

        // Crear el JLabel (para la contraseña)
        labelPassword = new JLabel("Contraseña:");
        labelPassword.setForeground(BINANCE_TEXT);
        labelPassword.setFont(binanceFont.deriveFont(14f));

        // Crear el JPasswordField (para la contraseña)
        txtPassword = new RoundedPasswordField(15);
        txtPassword.setToolTipText("Ingrese su Contraseña");
        txtPassword.setBackground(BINANCE_INPUT_BG);
        txtPassword.setForeground(BINANCE_TEXT);
        txtPassword.setCaretColor(BINANCE_TEXT);
        txtPassword.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        txtPassword.setFont(binanceFont.deriveFont(14f));

        // Crear el JButton (botón de login)
        buttonLogin = createStyledButton("Iniciar Sesión", BINANCE_YELLOW, Color.BLACK, binanceFont.deriveFont(Font.BOLD, 16f), 200, 50);

        // Crear el JLabel (hipervinculo de "Registrarse")
        hipervinculoRegistrar = new JLabel("Registrarse");
        hipervinculoRegistrar.setHorizontalAlignment(SwingConstants.CENTER);
        hipervinculoRegistrar.setForeground(BINANCE_YELLOW);
        hipervinculoRegistrar.setFont(binanceFont.deriveFont(14f));
        hipervinculoRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Mantener los listeners originales
        hipervinculoRegistrar.addMouseListener(new LRegistro());
        buttonLogin.addActionListener(new LIniciarSesion());

        //logo
        // Crear la instancia del JLabel con imagen circular
     // Cargar la imagen usando ImageIcon
        ImageIcon icon = new ImageIcon("src/billetera/etc/logo.png");
        
        // Si quieres redimensionar la imagen para que encaje en un JLabel
        Image img = icon.getImage();  // Convertir la imagen a un objeto Image
        Image newImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);  // Redimensionar
        icon = new ImageIcon(newImg);  // Crear un nuevo ImageIcon con la imagen redimensionada

        labelLogo = new JLabel(icon);

        // Agregar la imagen al panel
        
        // Otros componentes de tu vista
        
        // Agregar componentes al Panel
        add(labelLogo);
        add(titulo);
        add(labelGmail);
        add(txtGmail);
        add(labelPassword);
        add(txtPassword);
        add(buttonLogin);
        add(hipervinculoRegistrar);
        addComponentListener(new miComponentAdapter());
    }

    private class miComponentAdapter extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            actualizarPosiciones();
        }
    }
    
    
   
    // Clase interna para manejar el evento del hipervínculo "Registrarse"
    private class LRegistro extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            miControlador.RedirigirRegistro();
        }
    }

    // Clase interna para manejar el evento del botón "Iniciar Sesión"
    private class LIniciarSesion implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String gmail = txtGmail.getText();
            String password = new String(txtPassword.getPassword());

            if (gmail.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(VistaLogin.this, 
                    "Por favor, ingrese tanto su Gmail como su contraseña.", 
                    "Error de inicio de sesión", 
                    JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    if (miControlador.verificarUsuario(gmail, password)) {
                        miControlador.redirigirPantallaActivos();
                    } else {
                        JOptionPane.showMessageDialog(VistaLogin.this, 
                            "Error, no existe dicho Usuario coincidente con la contraseña ingresada", 
                            "Error de inicio de sesión", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                } catch (HeadlessException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

	protected void actualizarPosiciones() {
		// TODO Auto-generated method stub
		int formWidth = 400;
        int elementHeight = 40;

        actualizarPosicion(labelLogo,0,270,140, 140);
        actualizarPosicion(titulo, 0, 140, formWidth, elementHeight);
        actualizarPosicion(labelGmail, 0, 90, formWidth, 20);
        actualizarPosicion(txtGmail, 0, 60, formWidth, elementHeight);
        actualizarPosicion(labelPassword, 0, 10, formWidth, 20);
        actualizarPosicion(txtPassword, 0, -20, formWidth, elementHeight);
        actualizarPosicion(buttonLogin, 0, -80, formWidth, 50);
        actualizarPosicion(hipervinculoRegistrar, 0, -130, formWidth, 30);
		
	}
}