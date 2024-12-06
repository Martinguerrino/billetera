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
    public void controladorCuenta()
    {
        Vista vista = new Vista();
        vista.mostrarMenuCuenta();
        int opcion = vista.pedirOpcion();
        switch(opcion)
        {
            case 1:
                //controlador de la billetera
                this.controladorBilletera();
                break;
            case 2:
                //controlador de la compra
                this.controladorCompra();
                break;
            
            case 4:
                //controlador de la transferencia
                this.controladorTransferencia();
                break;
            case 5:
                //controlador de la inversion
                this.controladorInversion();
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
