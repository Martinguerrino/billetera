package billetera.Controladores;

public class ControladorBilletera {
    private Vista vista;

    public ControladorBilletera(Vista vista) {
        this.vista = vista;
    }

    public void controladorBilletera() {
        vista.mostrarActivos();
        vista.mostrarBalance();
        // Lógica para manejar la billetera
        while (!botonDeirse) {
            // Esperar acción del usuario
        }
    }
}