package Auxiliar;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Panel extends JPanel{

    private final Color BINANCE_BG = new Color(30, 32, 38);       // Fondo principal
    
	protected Panel() {
		setSize(800, 600);  // Mantenemos el tamaño original
        setLayout(null);     // Mantenemos el Layout absoluto
        setBackground(BINANCE_BG);
	}
	public JPanel getContent(){
		return this;
	}
	public Font getFont() {
		Font font;
        try {
        	font = Font.createFont(Font.TRUETYPE_FONT, 
                getClass().getResourceAsStream("/fonts/IBMPlexSans-Regular.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (Exception e) {
        	font = new Font("Arial", Font.PLAIN, 12);
        }
		return font;
    }

	protected void actualizarPosicion(JComponent element, int x, int y, int eWidth, int eHeight) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        element.setBounds(centerX - (eWidth / 2) + x, centerY - y, eWidth, eHeight);
    }
	
	protected RoundedButton createStyledButton(String text, Color bgColor, Color fgColor, Font font, int width, int height) {
	    RoundedButton button = new RoundedButton(text);
	    button.setBackground(bgColor);
	    button.setForeground(fgColor);
	    button.setFont(font);
	    button.setBorder(BorderFactory.createEmptyBorder());
	    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    button.setFocusPainted(false);
	    button.setPreferredSize(new Dimension(width, height));
	    return button;
	}
	
	protected abstract void actualizarPosiciones();
	
}
