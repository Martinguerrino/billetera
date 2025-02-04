import Vista.VistaLogin;


import Controladores.ControladorLogin;
import Modelo.Servicios.ConsultarPrecioCripto;
import java.util.Timer;
import java.util.TimerTask;
import Vista.Ventana.*;

import javax.swing.JFrame;

import Auxiliar.GeneradorVentanas;

public class App {
	public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new ConsultarPrecioCripto();
        //que se actualice cada 15 segundos
        timer.schedule(task, 0, 15000);
        ControladorLogin controlador = new ControladorLogin(GeneradorVentanas.GenerarVentana(800, 600)); // Crear el controlador (necesita implementación)
        controlador.iniciar();
        System.out.println("aqui");
    }
}