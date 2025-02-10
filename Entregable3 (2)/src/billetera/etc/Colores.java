package etc;

import java.awt.Color;

public enum Colores {
    NEGRO(new Color(0, 0, 0)),
    CUADROS_TEXTO(new Color(43, 49, 57)),
    AMARILLO(new Color(252, 213, 53)),
    TEXTO(new Color(234, 236, 239)),
    FONDO(new Color(30,33,39)),
    BOTON(new Color(252, 213, 53)),
    BOTON_HOVER(new Color(255, 235, 59)),
	ERROR(new Color(255,0,0));
	
    private final Color color;

    Colores(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
