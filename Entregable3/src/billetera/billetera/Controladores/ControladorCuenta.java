package billetera.Controladores;

public class ControladorCuenta {
    private Vista vista;

    public ControladorCuenta(Vista vista) {
        this.vista = vista;
    }

    public void controladorCuenta() {
        vista.mostrarMenuCuenta();
        int opcion = vista.pedirOpcion();
        switch (opcion) {
            case 1:
                new ControladorBilletera(vista).controladorBilletera();
                break;
            case 2:
                new ControladorCompra(vista).controladorCompra();
                break;
            case 4:
                new ControladorCotizaciones(vista, new ServicioCotizaciones()).mostrarCotizaciones();
                break;
            case 6:
                new ControladorCerrarSesion(vista).controladorCerrarSesion();
                break;
            default:
                vista.mostrarErrorOpcion();
                controladorCuenta();
                break;
        }
    }
}