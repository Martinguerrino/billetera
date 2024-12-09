package billetera.Controladores;

import java.sql.SQLException;

import billetera.Auxiliar.Persona;
import billetera.Auxiliar.Usuario;
import billetera.Modelo.DAO.UsuarioDAO;
import billetera.Modelo.DAO.PersonaDAO;
import billetera.Modelo.DAO.PersonaDAOjdbc;
import billetera.Modelo.DAO.UsuarioDAOjdbc;
public class ControladorRegistro {

	UsuarioDAO miUsuarioDAO;
	
	public boolean Registrar(String nombre, String apellido, String mail, String password, boolean aceptaTerminos) {
		// TODO Auto-generated method stub
		try {
            if (miUsuarioDAO.obtenerUsuarioPorMail(mail) == null && aceptaTerminos) {
                Persona persona = new Persona();
                persona.setNombre(nombre);
                persona.setApellido(apellido);
                //aca ver que constructor usar porque nose que datos pide para registar
                Usuario usuario = new Usuario(mail, password, aceptaTerminos, persona);
                miUsuarioDAO.registrarUsuario(usuario);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
	}

}
