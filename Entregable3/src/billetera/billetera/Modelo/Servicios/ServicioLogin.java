package billetera.Modelo.Servicios;
import billetera.Modelo.DAO.UsuarioDAOjdbc;
import java.sql.SQLException;
public class ServicioLogin 
{
    private static boolean login(String mail, String password)
    {
        UsuarioDAOjdbc usuarioDAO = new UsuarioDAOjdbc();
        try {
            if (usuarioDAO.obtenerUsuarioPorMail(mail).getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }


    }
    public static boolean login(String mail, String password, boolean aceptaTerminos)
    {
        if (aceptaTerminos) {
            return login(mail, password);
        } else {
            return false;
        }
    }
}
