package Auxiliar;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

public class LineaAmarilla extends JLabel {

    public LineaAmarilla() {
        setText(""); // No necesita texto
        setPreferredSize(new Dimension(600, 50)); // Establece el tamaño preferido
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Establecer color amarillo para la línea
        g2d.setColor(Color.YELLOW);

        // Establecer grosor de la línea
        g2d.setStroke(new BasicStroke(3));

        // Dibujar la línea (horizontal, ocupando todo el ancho del componente)
        g2d.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
    }
}