package Controladores;

import java.awt.BorderLayout;
import java.sql.SQLException;

import Vista.VistaRegistro;
import Vista.Ventana.VentanaInicio;
import Modelo.DAO.UsuarioDAO;
import Auxiliar.Persona;
import Auxiliar.Usuario;
import Modelo.DAO.FactoryDAO;
import Modelo.DAO.PersonaDAO;
import Modelo.DAO.PersonaDAOjdbc;
import Modelo.DAO.UsuarioDAO;
public class ControladorRegistro {

	private UsuarioDAO miUsuarioDAO;
	private VistaRegistro vista;
	private VentanaInicio ventana;
	private ControladorLogin controladorPrincipal;
	
	public ControladorRegistro(VentanaInicio ventana, VistaRegistro vista){
		miUsuarioDAO=FactoryDAO.getUsuarioDAO();
		this.ventana=ventana;
		this.vista=vista;
	}
	
	public boolean Registrar(String nombre, String apellido, String mail, String password, boolean aceptaTerminos) throws SQLException {
            // TODO Auto-generated method stub
            if (miUsuarioDAO.obtenerUsuarioPorMail(mail) == null && aceptaTerminos) {
                Persona persona = new Persona();
                persona.setNombre(nombre);
                persona.setApellido(apellido);
                //aca ver que constructor usar porque nose que datos pide para registar
                Usuario usuario = new Usuario(persona, password, aceptaTerminos, mail);
                miUsuarioDAO.registrarUsuario(usuario);
                this.redirigirLogin();
                return true;
            } else {
                return false;
            }
	}

	public void setVista(VistaRegistro nuevaVista) {
		// TODO Auto-generated method stub
			vista=nuevaVista;
	}
	public void iniciar() {
		ventana.getContentPane().removeAll();
		ventana.getContentPane().add(vista, BorderLayout.CENTER);
		ventana.revalidate();
		ventana.repaint();
    }

	public void redirigirLogin() {
		
		
	}
	
	public void setControladorPrincipal(ControladorLogin controladorLogin) {
		// TODO Auto-generated method stub
		controladorPrincipal=controladorLogin;
	}
	
	

}