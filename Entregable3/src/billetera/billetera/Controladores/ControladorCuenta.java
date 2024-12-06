package billetera.billetera.Controladores;

import billetera.Modelo.Servicios.ServicioLogin;

public class ControladorCuenta
{
    
    
    
    
    
    public void controladorCuenta()
    {
        Vista vista = new Vista();
        vista.mostrarMenuCuenta();
        int opcion = vista.pedirOpcion();
        switch(opcion)
        {
            case 1:
                //controlador de la billetera que permitira ver balance y activos
                this.controladorBilletera();
                break;
            case 2:
                //controlador de la compra
                this.controladorCompra();
                break;
            
            case 4:
                //controlador de cotizaciones
                this.controladorCotizaciones();
                break;
            
            case 6:
                //controlador de la inversion
                this.controladorCerrarSesion();
                break;
            default:
                vista.mostrarErrorOpcion();
                this.controladorCuenta();
                break;
        }
    }
    


}
