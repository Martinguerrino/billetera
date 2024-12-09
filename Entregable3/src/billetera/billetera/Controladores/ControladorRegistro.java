package billetera.Controladores;

import java.sql.SQLException;

import Vista.VistaRegistro;
import billetera.Modelo.DAO.UsuarioDAO;
import billetera.Auxiliar.Persona;
import billetera.Auxiliar.Usuario;
import billetera.Modelo.DAO.FactoryDAO;
import billetera.Modelo.DAO.PersonaDAO;
import billetera.Modelo.DAO.PersonaDAOjdbc;
import billetera.Modelo.DAO.UsuarioDAO;
public class ControladorRegistro {

	UsuarioDAO miUsuarioDAO;
	VistaRegistro miVista;
	ControladorLogin controladorPrincipal;
	public ControladorRegistro(){
		miUsuarioDAO=FactoryDAO.getUsuarioDAO();
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
                return true;
            } else {
                return false;
            }
	}

	public void setMiVista(VistaRegistro nuevaVista) {
		// TODO Auto-generated method stub
			miVista=nuevaVista;
	}
	public void iniciar() {
    	miVista.setVisible(true);
    }

	public void cerrarVentana() {
		controladorPrincipal.ReActivarVentana();
		this.miVista.dispose();
		
	}
	
	public void setMiVista(VistaRegistro nuevaVista, ControladorLogin controladorLogin) {
		// TODO Auto-generated method stub
		controladorPrincipal=controladorLogin;
		miVista=nuevaVista;
	}
	

}