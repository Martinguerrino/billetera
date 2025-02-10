package Vista;

import javax.swing.*;

import Auxiliar.Panel;
import Auxiliar.RoundedButton;
import Auxiliar.RoundedPasswordField;
import Auxiliar.RoundedTextField;
import Controladores.ControladorRegistro;
import etc.Colores;
import etc.fuentes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class VistaRegistro extends Panel {
    private RoundedTextField txtNombre;
    private RoundedTextField txtApellido;
    private RoundedTextField txtEmail;
    private RoundedPasswordField txtContrasena;
    private JLabel titulo;
    private JLabel[] labels;
    private JCheckBox chkAceptarTerminos;
    private RoundedButton btnRegistrar;
    private ControladorRegistro miControlador;
    private JLabel labelLogo;
    private JLabel hipervinculoTerminosYCondiciones;
    private RoundedButton btnVolver;

    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private static final Color TEXT_COLOR = Colores.TEXTO.getColor();
    public VistaRegistro(ControladorRegistro miControlador) {
    	super();

       this.miControlador=miControlador;
        // Crear los componentes
        txtNombre = new RoundedTextField(20);
        txtApellido = new RoundedTextField(20);
        txtEmail = new RoundedTextField(20);
        txtContrasena = new RoundedPasswordField(20);
        chkAceptarTerminos = new JCheckBox("Acepto los términos y condiciones");
        chkAceptarTerminos.setOpaque(false);
        chkAceptarTerminos.setForeground(Color.WHITE); // Texto en blanco
        chkAceptarTerminos.setFocusPainted(false); // Quita el borde cuando está enfocado
        btnRegistrar = new RoundedButton("Registrar", 150,40);
        btnVolver = new RoundedButton("Volver", 150,40);
        titulo = new JLabel("Registrarse");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setForeground(TEXT_COLOR); // Antes: Colores.TEXTO.getColor()
        titulo.setFont(fuentes.MAIN_FONT.getFont().deriveFont(Font.BOLD, 28f));
        
        hipervinculoTerminosYCondiciones = new JLabel("Ver terminos y condiciones");
        hipervinculoTerminosYCondiciones.setHorizontalAlignment(SwingConstants.LEFT);
        hipervinculoTerminosYCondiciones.setForeground(Colores.AMARILLO.getColor()); // Antes: Colores.AMARILLO.getColor()
        hipervinculoTerminosYCondiciones.setFont(fuentes.MAIN_FONT.getFont().deriveFont(14f));
        hipervinculoTerminosYCondiciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        hipervinculoTerminosYCondiciones.addMouseListener(new verTerminos());

        // Acción del botón de registro
        btnRegistrar.addActionListener(new LRegistro());     
        btnVolver.addActionListener(new LVolver());    
        labels=new JLabel[4];
        
        int i=0;
        String[] nombres= {
        		"Nombre: ", "Apellido: ", "Email: ", "Contraseña:"
        };
        for (int j = 0; j < nombres.length; j++) {
        	labels[j]=new JLabel(nombres[j]);
        	labels[j].setForeground(TEXT_COLOR); // Antes: Colores.TEXTO.getColor()
        	labels[j].setFont(fuentes.MAIN_FONT.getFont().deriveFont(14f));
        	add(labels[j]);
			i++;
		}

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
        add(hipervinculoTerminosYCondiciones);
        add(txtApellido);
        add(txtContrasena);
        add(txtEmail);
        add(txtNombre);
        add(btnRegistrar);
        add(btnVolver);
        add(chkAceptarTerminos);
        add(titulo);
        
        actualizarPosiciones();
 
    }

    private class verTerminos extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            miControlador.mostrarTerminos();
        }
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

    private class LVolver implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	miControlador.redirigirLogin();
        	
        }
    }

    
	@Override
	protected void actualizarPosiciones() {
        // TODO Auto-generated method stub
        int formWidth = 400;
        int elementHeight = 40;
    	int base=170;
        int buttonWidth = 300;
        int buttonHeight = 50;
        int spacing=60;
        int baseTxt=base-20;
        actualizarPosicion(labelLogo, 0, 290, 140, 140);
        actualizarPosicion(titulo, 0, base, formWidth, elementHeight);
        actualizarPosicion(labels[0], 0, base-spacing, formWidth, 20);
        actualizarPosicion(txtNombre, 0, baseTxt-spacing, formWidth, elementHeight);
        actualizarPosicion(labels[1], 0, base-spacing*2, formWidth, 20);
        actualizarPosicion(txtApellido, 0, baseTxt-spacing*2, formWidth, elementHeight);
        actualizarPosicion(labels[2], 0, base-spacing*3, formWidth, 20);
        actualizarPosicion(txtEmail, 0, baseTxt-spacing*3, formWidth, elementHeight);
        actualizarPosicion(labels[3], 0, base-spacing*4, formWidth, 20);
        actualizarPosicion(txtContrasena, 0, baseTxt-spacing*4, formWidth, elementHeight);
        actualizarPosicion(chkAceptarTerminos, 0, base-spacing*5, formWidth, 30);
        actualizarPosicion(hipervinculoTerminosYCondiciones, 0, base-spacing*5-20, formWidth, 30);
        actualizarPosicion(btnRegistrar, 0, base-spacing*6, formWidth, 30);
        actualizarPosicion(btnVolver, 0, base-spacing*6-35, buttonWidth, 30);
    }
	
}