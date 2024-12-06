package billetera.Controladores;

import billetera.Modelo.ServicioLogin;

public class ControladorLogin {
    private Vista vista;
    private ServicioLogin servicioLogin;

    public ControladorLogin(Vista vista, ServicioLogin servicioLogin) {
        this.vista = vista;
        this.servicioLogin = servicioLogin;
    }

    public void main(String[] args) {
        boolean accedio = false;
        vista.mostrarLogin();
        // Lógica para manejar el login
        while (!servicioLogin.login(mail, password, aceptaTerminos)) {
            vista.mostrarErrorLogin();
            vista.mostrarLogin();
        }
        new ControladorCuenta(vista).controladorCuenta();
    }
}