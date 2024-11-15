package billetera.Modelo.Servicios;

import billetera.Modelo.Usuario;
import billetera.Modelo.DAO.UsuarioDAOjdbc;

public class ServicioRegistro 
{
    /*Registración de un usuario. Los datos solicitados son los mínimos necesarios. 
    No se contempla verificaciones a excepción de la cuenta de email, 
    que no puede estar asociada a otro usuario, 
    y la aceptación de términos y condiciones. */

    public boolean registrar(String mail, String password, boolean aceptaTerminos)
    {
        UsuarioDAOjdbc usuarioDAO = new UsuarioDAOjdbc();
        try {
            if (usuarioDAO.obtenerUsuarioPorMail(mail) == null && aceptaTerminos) {
                Usuario usuario = new Usuario(mail, password, aceptaTerminos);
                usuarioDAO.cargarUsuario(usuario);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

}
