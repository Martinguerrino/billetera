package billetera.Modelo.DAO;
import billetera.Modelo.MyConnection;
import billetera.Modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOjdbc implements UsuarioDAO 
{
    @Override
    public void registrarUsuario(Usuario usuario) throws SQLException 
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
    public List<Usuario> listarUsuarios() throws SQLException 
    {
        Connection con = null;
        List<Usuario> usuarios = new ArrayList<>();
        con = MyConnection.getCon();
        String sql = "SELECT * FROM USUARIO";
        try (PreparedStatement stmt = con.prepareStatement(sql)) 
        {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) 
            {
                int id = rs.getInt("ID");
                int idPersona = rs.getInt("ID_PERSONA");
                String password = rs.getString("PASSWORD");
                boolean aceptaTerminos = rs.getBoolean("ACEPTA_TERMINOS");
                String mail = rs.getString("MAIL");
                Usuario usuario = new Usuario(id, idPersona, password, aceptaTerminos, mail);
                usuarios.add(usuario);
            }
            return usuarios;
        }
        catch (SQLException e) 
        {
            throw new SQLException("Error al listar los usuarios: " + e.getMessage(), e);
            
        }
    }

    @Override
    public void actualizarUsuario(Usuario usuario) throws SQLException 
    {
        Connection con = null;
        con = MyConnection.getCon();
        String sql = "UPDATE USUARIO SET ID_PERSONA = ?, PASSWORD = ?, ACEPTA_TERMINOS = ?, MAIL = ? WHERE ID = ?";
        try(PreparedStatement ps = con.prepareStatement(sql);)
        {
            ps.setInt(1, usuario.getIdPersona());
            ps.setString(2, usuario.getPassword());
            ps.setBoolean(3, usuario.getAceptaTerminos());
            ps.setString(4, usuario.getMail());
            ps.setInt(5, usuario.getId());
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            throw new SQLException("Error al actualizar el usuario: " + e.getMessage(), e);
        }
        
    }

	@Override
	public boolean iniciarSesionUsuario(Usuario usuario) throws SQLException {
		
		Usuario usuarioCheck=obtenerUsuarioPorMail(usuario.getMail());
		return usuarioCheck.getPassword().equals(usuario.getPassword());
	}
}