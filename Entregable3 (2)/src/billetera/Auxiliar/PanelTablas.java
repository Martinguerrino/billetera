package Auxiliar;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;

import etc.Colores;

public class PanelTablas extends JScrollPane{

	public PanelTablas(Tabla tabla){
		super(tabla);
		setBackground(Colores.FONDO.getColor());
        getViewport().setBackground(Colores.FONDO.getColor());
        setBorder(BorderFactory.createEmptyBorder());
        getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(30, 39, 58);
            }
        });
        Border topBottomBorder = BorderFactory.createMatteBorder(2, 0, 2, 0, Colores.AMARILLO.getColor()); // Líneas solo arriba y abajo (2px de grosor)
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 0, 10, 0); // Espacio entre la tabla y el borde (10px arriba y abajo)
        
        // Combinar el borde superior/inferior con el espacio vacío
        Border finalBorder = BorderFactory.createCompoundBorder(emptyBorder, topBottomBorder);
        
        // Aplicar el borde al panel
        setBorder(finalBorder);
	}
}
