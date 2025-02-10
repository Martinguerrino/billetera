package Auxiliar;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import etc.Colores;
import etc.fuentes;

public class RoundedTextField extends JTextField{
	private int radius;

    public RoundedTextField(int radius) {
        super();
        this.radius = radius;
        setOpaque(false);
        setBackground(Colores.CUADROS_TEXTO.getColor());
        setForeground(Colores.TEXTO.getColor()); // Antes: Colores.TEXTO.getColor()
        setCaretColor(Colores.TEXTO.getColor());   // Antes: Colores.TEXTO.getColor()
        setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        setFont(fuentes.MAIN_FONT.getFont().deriveFont(14f));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth()-1, getHeight()-1, radius, radius));
        super.paintComponent(g);
        g2.dispose();
    }
}
