package Auxiliar;

import javax.swing.JFrame;

import Vista.Ventana.VentanaInicio;

public class GeneradorVentanas {

	public static VentanaInicio GenerarVentana(int ancho, int alto) {
		VentanaInicio ventana= new VentanaInicio();
		ventana.setSize(ancho, alto); // Ancho: 800px, Alto: 600px
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        return ventana;
	}
}
