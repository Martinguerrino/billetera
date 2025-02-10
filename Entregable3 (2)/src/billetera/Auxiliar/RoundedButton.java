package Auxiliar;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import etc.Colores;
import etc.fuentes;

public class RoundedButton extends JButton {
    public RoundedButton(String label,int width,int height) {
        super(label);
        setContentAreaFilled(false);
        setBackground(Colores.AMARILLO.getColor());
	    setForeground(Colores.NEGRO.getColor());
	    setFont(fuentes.MAIN_FONT.getFont());
	    setBorder(BorderFactory.createEmptyBorder());
	    setCursor(new Cursor(Cursor.HAND_CURSOR));
	    setFocusPainted(false);
	    setPreferredSize(new Dimension(width, height));
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (getModel().isPressed()) {
            g2.setColor(getBackground().darker());
        } else if (getModel().isRollover()) {
            g2.setColor(getBackground().brighter());
        } else {
            g2.setColor(getBackground());
        }
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth()-1, getHeight()-1, 15, 15));
        super.paintComponent(g);
        g2.dispose();
    }
}