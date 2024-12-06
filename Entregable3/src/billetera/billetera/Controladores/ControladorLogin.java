package billetera.Controladores;

public class ControladorLogin 
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
