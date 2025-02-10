package Vista.Ventana;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import etc.Colores;
import etc.fuentes;

public class VentanaEmergente {
    public static void llamarVentanaEmergente(String mensaje, String title) {
        // Cambiar los colores globales de JOptionPane
        UIManager.put("Panel.background", new Color(30, 30, 30)); // Fondo oscuro
        UIManager.put("OptionPane.background", new Color(30, 30, 30));
        UIManager.put("OptionPane.messageForeground", Color.WHITE); // Texto blanco
        UIManager.put("Button.background", new Color(50, 50, 50));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.border", BorderFactory.createLineBorder(Color.YELLOW));

        // Crear el JOptionPane con el mensaje
        JOptionPane optionPane = new JOptionPane(mensaje,
                JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);

        // Convertirlo en un JDialog para restringir su posición
        JDialog dialog = optionPane.createDialog(title);
        dialog.setAlwaysOnTop(true); // Se mantiene al frente
        dialog.setResizable(false);

        // Establecer un tamaño fijo para el JDialog (ancho 400, alto predeterminado)
        dialog.setSize(400, dialog.getHeight());

        // Centrar la ventana emergente en la pantalla
        dialog.setLocationRelativeTo(null);

        // Limitar la posición para que no se salga de la pantalla
        

        // Mostrar la ventana emergente
        dialog.setVisible(true);
    }
}

  
