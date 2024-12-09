import Vista.VistaLogin;
import billetera.Controladores.ControladorLogin;

public class App {
	public static void main(String[] args) {
        ControladorLogin controlador = new ControladorLogin(); // Crear el controlador (necesita implementación)
        VistaLogin vistaLogin = new VistaLogin(controlador); // Crear la vista con el controlador
        controlador.setVistaLoin(vistaLogin);
        controlador.iniciar();
    }
}
