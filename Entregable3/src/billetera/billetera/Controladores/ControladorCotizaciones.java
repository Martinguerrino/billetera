package billetera.Controladores;

import billetera.Modelo.ServicioCotizaciones;

public class ControladorCotizaciones {
    private Vista vista;
    private ServicioCotizaciones servicioCotizaciones;

    public ControladorCotizaciones(Vista vista, ServicioCotizaciones servicioCotizaciones) {
        this.vista = vista;
        this.servicioCotizaciones = servicioCotizaciones;
    }

    public void mostrarCotizaciones() {
        vista.mostrarCotizaciones(servicioCotizaciones.obtenerCotizaciones());
    }

    public void manejarErrores() {
        vista.mostrarErrorCotizaciones();
        mostrarCotizaciones();
    }
}