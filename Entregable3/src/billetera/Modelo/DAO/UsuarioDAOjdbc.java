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
        //cargar un usuario en la base de datos
        String sql = "INSERT INTO USUARIO (ID_PERSONA, PASSWORD, ACEPTA_TERMINOS, MAIL) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = MyConnection.getCon().prepareStatement(sql)) 
        {
            //habria que hacer un metodo el cual cargue primero la persona consiga el id y luego lo cargue en el usuario
            stmt.setInt(1, usuario.getIdPersona());
            stmt.setString(2, usuario.getPassword());
            stmt.setBoolean(3, usuario.getAceptaTerminos());
            stmt.setString(4, usuario.getMail());
            stmt.executeUpdate();
        } 
        catch (SQLException e) 
        {
            throw new SQLException("Error al cargar el usuario: " + e.getMessage(), e);
        }
        
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
