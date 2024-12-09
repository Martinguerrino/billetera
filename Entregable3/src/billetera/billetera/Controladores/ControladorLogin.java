package billetera.Controladores;

import java.sql.SQLException;
import java.util.List;


import Vista.VistaIndex;
import Vista.VistaLogin;
import billetera.Auxiliar.Usuario;
import Vista.VistaRegistro;
import billetera.Modelo.DAO.FactoryDAO;
import billetera.Modelo.DAO.UsuarioDAO;
import billetera.Modelo.DAO.UsuarioDAOjdbc;
import Vista.VistaRegistro;
public class ControladorLogin {
    private VistaLogin miVista;
    private UsuarioDAO miModeloUsuario;
    private Usuario miUsuario;
    public ControladorLogin(){
    	miModeloUsuario= FactoryDAO.getUsuarioDAO();
    }
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
	public boolean verificarUsuario(String gmail, String password) throws SQLException {
		// TODO Auto-generated method stub
		//ESTO VOY A PENSAR QUE EL MODELO TRAE TODOS LOS USUARIOS PERO NO SE SI POR TEMAS DE SEGURIDAD DIRECTAMENTE EL MODELO DEBERIA DEVOLVER TRUE O FALSE SI EXISTE ALGUNO Q COINCIDA EN PASSWD Y MAIL, ES RE FACIL CAMBIARLO IGUAL
		//AMIGO FIJATE SI TE GUSTA ESTA ALTERNATIVO QUE NO ES TRAER TODO SINO QUE BUSCA AL UNICO USUARIO QUE USE ESE MAIL Y COMPARA SI ES SU CONTRASEÑA
		/*UsuarioDAOjdbc usuarioDAO = new UsuarioDAOjdbc();
        try {
            if (usuarioDAO.obtenerUsuarioPorMail(mail).getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }*/
		System.out.println(gmail);

		System.out.println(password);
		List<Usuario> Usuarios = miModeloUsuario.listarUsuarios();
		System.out.println(Usuarios);
		for (Usuario usuario : Usuarios) {
			System.out.println(usuario.getGmail());
			System.out.println(usuario.getPasswd());
			if(usuario.getGmail().equals(gmail) && usuario.getPasswd().equals(password)) {
				miUsuario= usuario;
				return true;
			}
		}
		return false;
	}
	public void redirigirPantallaActivos() {
		// TODO Auto-generated method stub
		ControladorIndex nuevoControlador = new ControladorIndex(miUsuario);
		VistaIndex nuevaVista= new VistaIndex(nuevoControlador);
		nuevoControlador.setMiVista(nuevaVista);
		nuevoControlador.iniciar();
	}
	public void RedirigirRegistro() {
		// TODO Auto-generated method stub
		ControladorRegistro nuevoControlador = new ControladorRegistro();
		VistaRegistro nuevaVista= new VistaRegistro(nuevoControlador);
		nuevoControlador.setMiVista(nuevaVista);
		nuevoControlador.iniciar();
		
	}
    public void iniciar() {
    	miVista.setVisible(true);
    }
}
