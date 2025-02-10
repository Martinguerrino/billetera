package Vista;

import Auxiliar.Panel;
import Auxiliar.RoundedButton;
import Auxiliar.RoundedPasswordField;
import Auxiliar.RoundedTextField;
import Controladores.ControladorLogin;
import etc.Colores;
import java.awt.*;
import java.awt.event.*;
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
    private JLabel 	labelLogo;

    // Declaración de constantes (mismos nombres que en VistaTransacciones)
    
    private static final Color TEXT_COLOR = Colores.TEXTO.getColor();
    // Constructor
    public VistaLogin(ControladorLogin miControlador) {
        super();
        this.miControlador = miControlador;
        
        // Cargar la fuente IBM Plex Sans
        Font fuente_texto = super.getFont();
        
        // Crear el JLabel (título)
        titulo = new JLabel("Iniciar Sesión");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setForeground(TEXT_COLOR); // Antes: Colores.TEXTO.getColor()
        titulo.setFont(fuente_texto.deriveFont(Font.BOLD, 28f));

        // Crear el JLabel (para el Gmail)
        labelGmail = new JLabel("Email:");
        labelGmail.setForeground(TEXT_COLOR); // Antes: Colores.TEXTO.getColor()
        labelGmail.setFont(fuente_texto.deriveFont(14f));

        // Crear el JTextField (para el Gmail)
        txtGmail = new RoundedTextField(15);
        txtGmail.setToolTipText("Ingrese su mail");
        // Se mantiene el color propio para cuadros de texto, ya que no hay constante equivalente en Transacciones
        

        // Crear el JLabel (para la contraseña)
        labelPassword = new JLabel("Contraseña:");
        labelPassword.setForeground(TEXT_COLOR); // Antes: Colores.TEXTO.getColor()
        labelPassword.setFont(fuente_texto.deriveFont(14f));

        // Crear el JPasswordField (para la contraseña)
        txtPassword = new RoundedPasswordField(15);
        txtPassword.setToolTipText("Ingrese su Contraseña");
       

        // Crear el JButton (botón de login)
        buttonLogin = new RoundedButton("Iniciar Sesión",150,40);
        buttonLogin.setBorder(null);

        // Crear el JLabel (hipervinculo de "Registrarse")
        hipervinculoRegistrar = new JLabel("Registrarse");
        hipervinculoRegistrar.setHorizontalAlignment(SwingConstants.CENTER);
        hipervinculoRegistrar.setForeground(Colores.AMARILLO.getColor()); // Antes: Colores.AMARILLO.getColor()
        hipervinculoRegistrar.setFont(fuente_texto.deriveFont(14f));
        hipervinculoRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Mantener los listeners originales
        hipervinculoRegistrar.addMouseListener(new LRegistro());
        buttonLogin.addActionListener(new LIniciarSesion());

        //logo
        // Cargar la imagen usando ImageIcon
        ImageIcon icon = new ImageIcon("src/billetera/etc/logo.png");
        // Si quieres redimensionar la imagen para que encaje en un JLabel
        Image img = icon.getImage();  
        Image newImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        labelLogo = new JLabel(icon);

        // Agregar la imagen y los demás componentes al Panel
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

        actualizarPosicion(labelLogo, 0, 270, 140, 140);
        actualizarPosicion(titulo, 0, 140, formWidth, elementHeight);
        actualizarPosicion(labelGmail, 0, 90, formWidth, 20);
        actualizarPosicion(txtGmail, 0, 60, formWidth, elementHeight);
        actualizarPosicion(labelPassword, 0, 10, formWidth, 20);
        actualizarPosicion(txtPassword, 0, -20, formWidth, elementHeight);
        actualizarPosicion(buttonLogin, 0, -80, formWidth, 50);
        actualizarPosicion(hipervinculoRegistrar, 0, -140, formWidth, 30);
    }
}