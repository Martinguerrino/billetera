package billetera.Modelo.Servicios;

import billetera.Modelo.DAO.Persona;
import billetera.Modelo.DAO.PersonaDAO;
import billetera.Modelo.DAO.PersonaDAOjdbc;
import billetera.Modelo.DAO.UsuarioDAOjdbc;
import billetera.Modelo.Usuario;
import java.sql.SQLException;

public class ServicioRegistro 
{
    /*Registración de un usuario. Los datos solicitados son los mínimos necesarios. 
    No se contempla verificaciones a excepción de la cuenta de email, 
    que no puede estar asociada a otro usuario, 
    y la aceptación de términos y condiciones. */

    public boolean registrar(String nombre, String apellido, String mail, String password, boolean aceptaTerminos)
    {
        UsuarioDAOjdbc usuarioDAO = new UsuarioDAOjdbc();
        try {
            if (usuarioDAO.obtenerUsuarioPorMail(mail) == null && aceptaTerminos) {
                Persona persona = new Persona();
                persona.setNombre(nombre);
                persona.setApellido(apellido);
                PersonaDAO  personaDAO = new PersonaDAOjdbc();
                personaDAO.cargarPersona(persona);
                //aca ver que constructor usar porque nose que datos pide para registar
                Usuario usuario = new Usuario(mail, password, aceptaTerminos);
                usuarioDAO.cargarUsuario(usuario);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

}
