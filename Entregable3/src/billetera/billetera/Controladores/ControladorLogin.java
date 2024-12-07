package billetera.Controladores;

import java.util.List;

import Aux.Usuario;
import Vista.VistaIndex;
import Vista.VistaLogin;

public class ControladorLogin {
    private VistaLogin miVista;
    private ModeloUsuario miModeloUsuario;
    /*
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
    }*/
    public void setVistaLoin(VistaLogin miVista) {
    	this.miVista=miVista;
    }
	public boolean verificarUsuario(String gmail, String password) {
		// TODO Auto-generated method stub
		//ESTO VOY A PENSAR QUE EL MODELO TRAE TODOS LOS USUARIOS PERO NO SE SI POR TEMAS DE SEGURIDAD DIRECTAMENTE EL MODELO DEBERIA DEVOLVER TRUE O FALSE SI EXISTE ALGUNO Q COINCIDA EN PASSWD Y MAIL, ES RE FACIL CAMBIARLO IGUAL
		List<Usuario> Usuarios= miModeloUsuario.obtenerUsuarios;
		for (Usuario usuario : Usuarios) {
			if(usuario.getGmail()==gmail && usuario.getPasswd()==password) {
				return true;
			}
		}
		return false;
	}
	public void redirigirPantallaActivos() {
		// TODO Auto-generated method stub
		ControladorIndex nuevoControlador = new ControladorIndex();
		VistaIndex nuevaVista= new VistaIndex(nuevoControlador);
		nuevoControlador.setVistaLogin(nuevaVista);
	}
	public void RedirigirRegistro() {
		// TODO Auto-generated method stub
		ControladorRegistro nuevoControlador = new ControladorRegistro();
		VistaRegistro nuevaVista= new VistaRegistro(nuevoControlador);
		nuevoControlador.setVistaLogin(nuevaVista);
		
	}
    
}