package billetera.Controladores;

public class ControladorCotizaciones 
{
    Vista vista = new Vista();
    vista.mostrarCotizaciones();
    //aca deberia mostrar la lista de cotizaciones
    //aca deberia acceder al servicioCotizaciones
    while(Modelo.ServicioCotizaciones.obtenerCotizaciones())
    {
        vista.mostrarErrorCotizaciones();
        vista.mostrarCotizaciones();
    }

    this.controladorCuenta();

}
