package Vista.Ventana;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.RoundRectangle2D.Float;
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

import Auxiliar.RoundedButton;
import etc.Colores;
import etc.fuentes;

public class VentanaEmergente extends JDialog {
    public VentanaEmergente(JFrame owner, String message, String title,Color color) {
        super(owner, title, true);  // Modal
        setUndecorated(true);
        // Hacemos transparente el fondo del diálogo y root pane:
        setBackground(new Color(0,0,0,0));
        getRootPane().setOpaque(false);
        
        // Panel con esquinas redondeadas y fondo BG_COLOR (Colores.FONDO)
        RoundedPanel contentPanel = new RoundedPanel(20);
        contentPanel.setBackground(Colores.FONDO.getColor());
        contentPanel.setLayout(new BorderLayout(20, 20));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Título al estilo de VistaLogin: letras del color TEXTO (Colores.TEXTO)
        JLabel titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Colores.TEXTO.getColor());
        
        // Mensaje centrado en rojo
        JLabel messageLabel = new JLabel("<html><div style='text-align: center;'>" + message + "</div></html>");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messageLabel.setForeground(color);
        
        // Botón OK utilizando RoundedButton
        RoundedButton btnOk = new RoundedButton("OK", 40, 40);
        btnOk.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnOk.setBackground(Colores.BOTON.getColor());
        btnOk.setForeground(Color.BLACK);
        btnOk.setFocusPainted(false);
        btnOk.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnOk.addActionListener(e -> dispose());
        
        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(messageLabel, BorderLayout.CENTER);
        contentPanel.add(btnOk, BorderLayout.SOUTH);
        
        setContentPane(contentPanel);
        pack();
        setLocationRelativeTo(owner);
    }
    
    // Clase interna para un panel con esquinas redondeadas SIN pintar fondo adicional
    private class RoundedPanel extends JPanel {
        private int radius;
        
        public RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // Se dibuja el fondo con esquinas redondeadas
            Float round = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius);
            g2.setColor(getBackground());
            g2.fill(round);
            g2.dispose();
            // No se llama a super.paintComponent(g) para evitar pintar el fondo por defecto
        }
    }
    public static void llamarVentanaEmergente( String message, String title,JFrame owner, Color color) {
    	
    	new VentanaEmergente(owner, 
            message, 
            title,color).setVisible(true);
    }
}
  
