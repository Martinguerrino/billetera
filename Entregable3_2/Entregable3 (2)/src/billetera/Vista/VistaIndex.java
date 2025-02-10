package Vista;
import Auxiliar.LineaAmarilla;
import Auxiliar.Panel;
import Auxiliar.RoundedButton;
import Controladores.ControladorIndex;
import etc.Colores;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import javax.swing.*;

public class VistaIndex extends Panel {
	private LineaAmarilla lnAmarilla;
	private RoundedButton btnBalanceActivos;
    private RoundedButton btnTransacciones;
    private RoundedButton btnCompra;
    private RoundedButton btnCotizaciones;
    private ControladorIndex miControlador;
    private JLabel labelLogoUser;
    private JLabel labelUser;

    // Declaración de constantes (mismos nombres que en VistaTransacciones)
    private static final Color BG_COLOR = Colores.FONDO.getColor();
    private static final Color TEXT_COLOR = Colores.AMARILLO.getColor();
    private static final Color BUTTON_COLOR = Colores.AMARILLO.getColor();

    public VistaIndex(ControladorIndex miControlador) {
    	super();
        this.miControlador = miControlador;

        Font fuente_texto= super.getFont();

        // Crear botones
        btnBalanceActivos = new RoundedButton("Balance y activos",150,40);
        btnTransacciones = new RoundedButton("Transacciones",150,40);
        btnCompra = new RoundedButton("Comprar",150,40);
        btnCotizaciones = new RoundedButton("Cotizaciones",150,40);

        lnAmarilla= new LineaAmarilla();
        
        ImageIcon icon = new ImageIcon("src/billetera/etc/userlogo.png");
        
        // Si quieres redimensionar la imagen para que encaje en un JLabel
        Image img = icon.getImage();  // Convertir la imagen a un objeto Image
        Image newImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);  // Redimensionar
        icon = new ImageIcon(newImg);  // Crear un nuevo ImageIcon con la imagen redimensionada

        labelLogoUser = new JLabel(icon);
        
        labelUser= new JLabel("Bienvenido: "+miControlador.getMiUsuario().getPersona().getNombre() + " " + miControlador.getMiUsuario().getPersona().getApellido());
        labelUser.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelUser.setForeground(TEXT_COLOR); // Antes: Colores.AMARILLO.getColor()
        labelUser.setFont(fuente_texto.deriveFont(Font.BOLD,15f));
        
        // Agregar botones a la vista
        add(labelLogoUser);
        add(labelUser);
        add(lnAmarilla);
        add(btnBalanceActivos);
        add(btnTransacciones);
        add(btnCompra);
        add(btnCotizaciones);

        // Agregar Listeners
        btnBalanceActivos.addActionListener(new LBalanceActivos());
        btnTransacciones.addActionListener(new LTransacciones());
        btnCompra.addActionListener(new LCompra());
        btnCotizaciones.addActionListener(new LCotizaciones());

        // Listener para actualizar posiciones cuando se cambia el tamaño
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                actualizarPosiciones();
            }
        });

        actualizarPosiciones();
    }

    protected void actualizarPosiciones() {
        JButton botones[]= {
        		btnBalanceActivos, btnTransacciones, btnCompra, btnCotizaciones
        };
    	int base=160;
        int buttonWidth = 300;
        int buttonHeight = 50;
        int spacing = 20; // Espacio entre botones
        int i=1;
        for (JButton jButton : botones) {
			actualizarPosicion(jButton, 0, base-buttonHeight*i-spacing*i, buttonWidth,buttonHeight);
			i++;
		}
        actualizarPosicion(lnAmarilla,0,base+20,buttonWidth, buttonHeight);
        actualizarPosicion(labelLogoUser,-100 ,base+90,buttonWidth,buttonHeight+30);
        actualizarPosicion(labelUser,100 ,base+90,buttonWidth,buttonHeight+30);
    }

    // Listeners para los botones
    private class LBalanceActivos implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                miControlador.redirigirBalanceActivos();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class LTransacciones implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                miControlador.redirigirTransacciones();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class LCompra implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                miControlador.redirigirCompra();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class LCotizaciones implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                miControlador.redirigirCotizaciones();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}