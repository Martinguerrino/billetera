package Controladores;

import java.sql.SQLException;
import java.util.List;


import Vista.VistaIndex;
import Vista.VistaLogin;
import Auxiliar.Usuario;
import Vista.VistaRegistro;
import Modelo.DAO.FactoryDAO;
import Modelo.DAO.UsuarioDAO;
import Modelo.DAO.UsuarioDAOjdbc;
import Vista.VistaRegistro;
public class ControladorLogin {
    private VistaLogin miVista;
    private UsuarioDAO miModeloUsuario;
    private Usuario miUsuario;
    public ControladorLogin(){
    	miModeloUsuario= FactoryDAO.getUsuarioDAO();
    }
    public void setVistaLoin(VistaLogin miVista) {
    	this.miVista=miVista;
    }
	public boolean verificarUsuario(String gmail, String password) throws SQLException {
		// TODO Auto-generated method stub
		List<Usuario> Usuarios = miModeloUsuario.listarUsuarios();
		for (Usuario usuario : Usuarios) {
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
		this.miVista.dispose();
	}
	public void RedirigirRegistro() {
		// TODO Auto-generated method stub
		miVista.bloquearInteraccion();
		ControladorRegistro nuevoControlador = new ControladorRegistro();
		VistaRegistro nuevaVista= new VistaRegistro(nuevoControlador);
		nuevoControlador.setMiVista(nuevaVista, this);
		nuevoControlador.iniciar();
		
	}
	
	public void ReActivarVentana() {
		miVista.desbloquearInteraccion();
	}
	
    public void iniciar() {
    	miVista.setVisible(true);
    }
}
