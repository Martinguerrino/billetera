package billetera.billetera.Controladores;

import billetera.Modelo.Servicios.ServicioLogin;

public class Controlador
{
    public void controladorLogin()
    {
        Vista vista = new Vista();
        boolean accedio = false;
        vista.mostrarLogin();
        //asumo que me devuelve las variables mail y password Y aceptaTerminos
        while(!ServicioLogin.login(mail, password, aceptaTerminos))
        {
            vista.mostrarErrorLogin();
            //vuelvo a pedir los datos
            vista.mostrarLogin();
        }
        //controlador de la cuenta
        this.controladorCuenta();
        
    }
    public void controladorBilletera()
    {
        //para mostrar los activos deberia la vista acceder al servicio activos
        Vista vista = new Vista();
        vista.mostrarActivos();
        vista.mostrarBalance();
        while(!botonDeirse)
        {
        }
        this.controladorCuenta();
    }
    public void controladorCerrarSesion()
    {
        Vista vista = new Vista();
        vista.mostrarCerrarSesion();
        //aca nose si deberia mostrar el login y la otra opcion de cerrar la app 
        // o que corte directamente la app
    }
    public void controladorCompra()
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
