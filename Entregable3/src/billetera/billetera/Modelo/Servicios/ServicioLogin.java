package billetera.Modelo.Servicios;
import billetera.Modelo.DAO.UsuarioDAOjdbc;
public class ServicioLogin 
{
    private boolean login(String mail, String password)
    {
        UsuarioDAOjdbc usuarioDAO = new UsuarioDAOjdbc();
        try {
            if (usuarioDAO.obtenerUsuarioPorMail(mail).getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }


    }
    public boolean login(String mail, String password, boolean aceptaTerminos)
    {
        if (aceptaTerminos) {
            return login(mail, password);
        } else {
            return false;
        }
    }
}
