package billetera.Modelo.DAO;
import billetera.Modelo.MyConnection;
import billetera.Modelo.Usuario;
import java.sql.*;
import java.util.List;

public class UsuarioDAOjdbc implements UsuarioDAO 
{
    @Override
    public void cargarUsuario(Usuario usuario) throws SQLException 
    {
        
        
    }

    @Override
    public Usuario obtenerUsuarioPorMail(String mail) throws SQLException {
        
        String sql = "SELECT * FROM USUARIO WHERE MAIL = ?";
        try (PreparedStatement stmt = MyConnection.getCon().prepareStatement(sql)) {
            stmt.setString(1, mail);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("ID");
                int idPersona = rs.getInt("ID_PERSONA");
                String password = rs.getString("PASSWORD");
                boolean aceptaTerminos = rs.getBoolean("ACEPTA_TERMINOS");
                Usuario usuario = new Usuario(id, idPersona, password, aceptaTerminos, mail);
                return usuario;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el usuario: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Usuario> listarUsuarios() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void actualizarUsuario(Usuario usuario) throws SQLException {
        // TODO Auto-generated method stub
        
    }
}
