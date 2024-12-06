package billetera.Controladores;

public class ControladorCerrarSesion {
    private Vista vista;

    public ControladorCerrarSesion(Vista vista) {
        this.vista = vista;
    }

    public void controladorCerrarSesion() {
        vista.mostrarCerrarSesion();
        // Lógica para manejar el cierre de sesión
    }
}