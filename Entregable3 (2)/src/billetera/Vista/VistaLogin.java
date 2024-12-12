package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controladores.ControladorLogin;

public class VistaLogin extends JFrame  {
    private JButton buttonLogin;
    private JLabel hipervinculoRegistrar;
    private JLabel titulo;
    private JTextField txtGmail;
    private JPasswordField txtPassword;
    private ControladorLogin miControlador;

    // Constructor
    public VistaLogin(ControladorLogin miControlador) {
        super("Login");
        this.miControlador = miControlador;
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
        hipervinculoRegistrar = new JLabel("<html><a href=''>Registrarse</a></html>");
        hipervinculoRegistrar.setBounds(365, 260, 100, 30); // 100 px de ancho, 30 px de alto
        hipervinculoRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        hipervinculoRegistrar.addMouseListener(new LRegistro()); // Asocia el MouseListener
        buttonLogin.addActionListener(new LIniciarSesion());


        // Agregar componentes al JFrame
        add(titulo);
        add(txtGmail);
        add(txtPassword);
        add(buttonLogin);
        add(hipervinculoRegistrar);

        // Nota: No vinculamos `LIniciarSesion` aquí. Solo creamos la clase interna.
    }

    // Clase interna para manejar el evento del hipervínculo "Registrarse"
    private class LRegistro extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            miControlador.RedirigirRegistro();
        }
    }

    // Clase interna para manejar el evento del botón "Iniciar Sesión" (sin vincular aún)
    private class LIniciarSesion implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String gmail = txtGmail.getText();  // Obtener el texto del JTextField (Gmail)
            String password = new String(txtPassword.getPassword());  // Obtener la contraseña del JPasswordField

            if (gmail.isEmpty() || password.isEmpty()) {
                // Mostrar mensaje de error si falta información
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
    public void bloquearInteraccion() {
    	JPanel glassPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibujar un fondo semitransparente
                g.setColor(new Color(0, 0, 0, 100)); // Negro semitransparente
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        // El GlassPane interceptará los eventos
        glassPane.setOpaque(false); // Visualmente no opaco (permite la transparencia)
        glassPane.setLayout(new BorderLayout());

        // Agregar un mensaje al centro
        JLabel labelBloqueo = new JLabel("Esperando...", SwingConstants.CENTER);
        labelBloqueo.setForeground(Color.WHITE);
        glassPane.add(labelBloqueo, BorderLayout.CENTER);

        // Interceptar eventos del mouse
        glassPane.addMouseListener(new MouseAdapter() {}); // Captura clicks
        glassPane.addMouseMotionListener(new MouseMotionAdapter() {}); // Captura movimiento del mouse

        // Interceptar eventos del teclado
        glassPane.addKeyListener(new KeyAdapter() {}); // Captura teclado

        // Evitar que el foco cambie (importante para teclado)
        glassPane.setFocusable(true);
        glassPane.requestFocusInWindow();

        setGlassPane(glassPane); // Establecer el GlassPane personalizado
        glassPane.setVisible(true); // Activarlo
    }
    // Método para desbloquear la ventana
    public void desbloquearInteraccion() {
        getGlassPane().setVisible(false);
    }

}