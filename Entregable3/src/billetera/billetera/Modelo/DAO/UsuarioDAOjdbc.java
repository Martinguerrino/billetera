package billetera.Modelo.DAO;
import billetera.Auxiliar.Persona;
import billetera.Auxiliar.Usuario;
import billetera.Modelo.MyConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOjdbc implements UsuarioDAO 
{
    
    @Override
    public void registrarUsuario(Usuario usuario) throws SQLException 
    {
        //cargar a la persona adentro del usuario
        //cargar un usuario en la base de datos
        String sql = "INSERT INTO USUARIO (ID_PERSONA, PASSWORD, ACEPTA_TERMINOS, MAIL) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = MyConnection.getCon().prepareStatement(sql)) 
        {
            //habria que hacer un metodo el cual cargue primero la persona consiga el id y luego lo cargue en el usuario
            
            PersonaDAO personaDAO = new PersonaDAOjdbc();
            personaDAO.cargarPersona(usuario.getPersona());
            stmt.setString(2, usuario.getPasswd());
            stmt.setBoolean(3, usuario.isAceptaTerminos());
            stmt.setString(4, usuario.getGmail());
            stmt.executeUpdate();
        } 
        catch (SQLException e) 
        {
            throw new SQLException("Error al cargar el usuario: " + e.getMessage(), e);
        }
        
    }
    
    @Override
public List<Usuario> listarUsuarios() throws SQLException {
    List<Usuario> usuarios = new ArrayList<>();
    Connection con = MyConnection.getCon();
    String sql = "SELECT u.ID, u.ID_PERSONA, u.PASSWORD, u.ACEPTA_TERMINOS, u.MAIL, " +
                 "p.ID AS PERSONA_ID, p.NOMBRES, p.APELLIDOS " +
                 "FROM USUARIO u " +
                 "INNER JOIN PERSONA p ON u.ID_PERSONA = p.ID";
    try (PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
        	System.out.println(rs);
            int id = rs.getInt("ID");
            
            String password = rs.getString("PASSWORD");
            boolean aceptaTerminos = rs.getBoolean("ACEPTA_TERMINOS");
            String mail = rs.getString("MAIL");
            int personaId = rs.getInt("PERSONA_ID");
            String nombres = rs.getString("NOMBRES");
            String apellidos = rs.getString("APELLIDOS");

            Persona persona = new Persona(personaId, nombres, apellidos);
            Usuario usuario = new Usuario(id, persona, password, aceptaTerminos, mail);
            System.out.println(usuario);
            usuarios.add(usuario);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new SQLException("Error al listar los usuarios: " + e.getMessage(), e);
    }
    return usuarios;
}

  
    @Override
    
public Usuario obtenerUsuarioPorMail(String mail) throws SQLException {
    String sql = "SELECT u.ID, u.ID_PERSONA, u.PASSWORD, u.ACEPTA_TERMINOS, u.MAIL, " +
                 "p.ID AS PERSONA_ID, p.NOMBRES, p.APELLIDOS " +
                 "FROM USUARIO u " +
                 "INNER JOIN PERSONA p ON u.ID_PERSONA = p.ID " +
                 "WHERE u.MAIL = ?";
    try (PreparedStatement stmt = MyConnection.getCon().prepareStatement(sql)) {
        stmt.setString(1, mail);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int id = rs.getInt("ID");
                int idPersona = rs.getInt("ID_PERSONA");
                String password = rs.getString("PASSWORD");
                boolean aceptaTerminos = rs.getBoolean("ACEPTA_TERMINOS");
                String nombres = rs.getString("NOMBRES");
                String apellidos = rs.getString("APELLIDOS");

                Persona persona = new Persona(idPersona, nombres, apellidos);
                Usuario usuario = new Usuario(id, persona, password, aceptaTerminos, mail);
                return usuario;
            } else {
                return null;
            }
        }
    } catch (SQLException e) {
        throw new SQLException("Error al obtener el usuario: " + e.getMessage(), e);
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
            ps.setInt(1, usuario.getPersona().getId());
            ps.setString(2, usuario.getPasswd());
            ps.setBoolean(3, usuario.isAceptaTerminos());
            ps.setString(4, usuario.getGmail());
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
		
		Usuario usuarioCheck=obtenerUsuarioPorMail(usuario.getGmail());
		return usuarioCheck.getPasswd().equals(usuario.getPasswd());
	}

    
    
}

