
import Vista.VistaLogin;
import billetera.Controladores.ControladorLogin;
import billetera.Modelo.Servicios.ConsultarPrecioCripto;
import java.util.Timer;
import java.util.TimerTask;

public class App {
	public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new ConsultarPrecioCripto();
        //que se actualice cada 15 segundos
        timer.schedule(task, 0, 15000);
        ControladorLogin controlador = new ControladorLogin(); // Crear el controlador (necesita implementación)
        VistaLogin vistaLogin = new VistaLogin(controlador); // Crear la vista con el controlador
        controlador.setVistaLoin(vistaLogin);
        controlador.iniciar();
    }
}