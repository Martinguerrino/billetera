package billetera.Controladores;

public class ControladorCompra 
{
    Vista vista = new Vista();
    vista.mostrarCompra();
    //aca deberia mostrar la lista de productos y el boton de comprar
    //aca deberia acceder al servicioCompra
    while(Modelo.ServicioCompra.comprar(monedaFiat,cantidad,moneda))
    {
        vista.mostrarErrorCompra();
        vista.mostrarCompra();
    }

    this.controladorCuenta();
}
