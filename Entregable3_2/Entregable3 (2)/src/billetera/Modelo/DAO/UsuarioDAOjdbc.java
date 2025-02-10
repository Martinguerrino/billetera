package Modelo.DAO;
import Auxiliar.Persona;
import Auxiliar.Usuario;
import Modelo.MyConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOjdbc implements UsuarioDAO 
{
    
    
	@Override
	public void registrarUsuario(Usuario usuario) throws SQLException {
	    String sqlPersona = "INSERT INTO PERSONA (NOMBRES, APELLIDOS) VALUES (?, ?)";
	    String sqlUsuario = "INSERT INTO USUARIO (ID_PERSONA, PASSWORD, ACEPTA_TERMINOS, MAIL) VALUES (?, ?, ?, ?)";

	    try  {
	        // Iniciar una transacción para asegurarse de que ambas inserciones sean atómicas
	    	Connection conn = MyConnection.getCon();
	    	conn.setAutoCommit(false); // Desactivar auto commit

	        try (PreparedStatement stmtPersona = conn.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS)) {
	            // Insertar la persona
	            stmtPersona.setString(1, usuario.getPersona().getNombre());
	            stmtPersona.setString(2, usuario.getPersona().getApellido());
	            stmtPersona.executeUpdate();

	            // Obtener el ID generado para la persona
	            ResultSet rs = stmtPersona.getGeneratedKeys();
	            int idPersona = 0;
	            if (rs.next()) {
	                idPersona = rs.getInt(1);  // El ID generado es el primer valor
	            }

	            // Insertar el usuario utilizando el ID de la persona
	            try (PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario)) {
	                stmtUsuario.setInt(1, idPersona);  // Usamos el ID de la persona
	                stmtUsuario.setString(2, usuario.getPasswd());
	                stmtUsuario.setBoolean(3, usuario.isAceptaTerminos());
	                stmtUsuario.setString(4, usuario.getGmail());
	                stmtUsuario.executeUpdate();
	            }

	            // Confirmar la transacción si todo fue bien
	            conn.commit();
	        } catch (SQLException e) {
	            conn.rollback();
	            throw new SQLException("Error al registrar el usuario: " + e.getMessage(), e);
	        } finally {
	            // Restablecer auto commit
	        	System.out.println(99);
	            conn.setAutoCommit(true); 
	        }
	    } catch (SQLException e) {
	        throw new SQLException("Error al registrar el usuario: " + e.getMessage(), e);
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

