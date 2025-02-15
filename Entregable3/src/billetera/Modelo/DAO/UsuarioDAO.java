package billetera.Modelo.DAO;
import billetera.Modelo.Usuario;
import java.sql.SQLException;
import java.util.List;

public interface  UsuarioDAO 
{
    void cargarUsuario(Usuario usuario) throws SQLException;
    Usuario obtenerUsuarioPorMail(String mail) throws SQLException;
    List<Usuario> listarUsuarios() throws SQLException;
    //actualizarUsuario
    void actualizarUsuario(Usuario usuario) throws SQLException;

}
