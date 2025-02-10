package Auxiliar;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class RoundedComboBox<E> extends JComboBox<E> {
    private int radius;

    public RoundedComboBox(int radius) {
        super();
        this.radius = radius;
        setOpaque(false);
        // Se configura la UI personalizada para el combobox
        setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                // Se utiliza el RoundedButton definido en RoundedButton.java
                RoundedButton button = new RoundedButton("", 15, 15);
                button.setOpaque(false);
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);
                //button.setBackground(Color.YELLOW); // Color de fondo deseado para la cajita del triángulo
                // Se establece un ícono personalizado que dibuja la flecha en color blanco
                button.setIcon(new Icon() {
                    @Override
                    public void paintIcon(Component c, Graphics g, int x, int y) {
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setColor(Color.WHITE);
                        int size = 8;
                        int startX = x + (getIconWidth() - size) / 2;
                        int startY = y + (getIconHeight() - size) / 2;
                        Polygon triangle = new Polygon();
                        triangle.addPoint(startX, startY);
                        triangle.addPoint(startX + size, startY);
                        triangle.addPoint(startX + size / 2, startY + size);
                        g2.fill(triangle);
                        g2.dispose();
                    }

                    @Override
                    public int getIconWidth() {
                        return 16;
                    }

                    @Override
                    public int getIconHeight() {
                        return 16;
                    }
                });
                return button;
            }
            
            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                // Se omite el fondo por defecto para usar el nuestro en paintComponent()
            }
        });
    }

    
}
