package Modelo.DAO;
import Auxiliar.Persona;
import Modelo.MyConnection;
import java.sql.*;



    public class PersonaDAOjdbc implements PersonaDAO 
    {

        @Override
        public int cargarPersona(Persona persona) throws SQLException {
            String sql = "INSERT INTO PERSONA (NOMBRES, APELLIDOS) VALUES (?, ?)";
            try (Connection con = MyConnection.getCon();
                 PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, persona.getNombre());
                stmt.setString(2, persona.getApellido());
                stmt.executeUpdate();
    
                // Obtener el ID generado
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    } else {
                        throw new SQLException("Error al obtener el ID de la persona");
                    }
                }
            }
        }
    

    @Override
    public void actualizarPersona(Persona persona) 
    {
        Connection con = null;
        con = MyConnection.getCon();
        String sql = "UPDATE persona SET nombre = ?, apellido = ? WHERE id = ?";
        try(PreparedStatement ps = con.prepareStatement(sql);)
        {
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setInt(3, persona.getId());
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int obtenerIdPersona(String nombre, String apellido) 
    {
        Connection con = null;
        con = MyConnection.getCon();
        try
        {
            String query = "SELECT id FROM persona WHERE nombre = ? AND apellido = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return rs.getInt("id");
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            
        }
                return -1;
    }
    }
